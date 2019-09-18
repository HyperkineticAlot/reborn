package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LethalCombination extends CustomCard
{
    public static final String ID = "Reborn:LethalCombination";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 0;
    private static final int DMG = 15;
    private static final int THRESH = 4;

    public LethalCombination()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = DMG;
        this.baseMagicNumber = this.magicNumber = THRESH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        int atks = 0;
        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
        {
            if(c.type == CardType.ATTACK) atks++;
        }

        return (atks == this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new LethalCombination();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeDamage(4);
            upgradeMagicNumber(-1);
        }
    }
}
