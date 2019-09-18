package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TripleStrike extends CustomCard
{
    public static final String ID = "Reborn:TripleStrike";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DMG = 7;
    private static final int BLOCK = 7;

    public TripleStrike()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.RARE, CardTarget.ENEMY);

        this.baseBlock = this.block = BLOCK;
        this.baseDamage = this.damage = DMG;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean hasAtk, hasSkill, hasPwr;
        hasAtk = hasSkill = hasPwr = false;
        for(AbstractCard c : p.discardPile.group)
        {
            if(c.type == CardType.ATTACK) hasAtk = true;
            else if (c.type == CardType.SKILL) hasSkill = true;
            else if (c.type == CardType.POWER) hasPwr = true;

            if(hasAtk && hasSkill && hasPwr) break;
        }

        if(hasAtk)
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                    new DamageInfo(m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if(hasSkill)
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        if(hasPwr)
        {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        }
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new TripleStrike();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeBlock(3);
            upgradeDamage(3);
        }
    }
}
