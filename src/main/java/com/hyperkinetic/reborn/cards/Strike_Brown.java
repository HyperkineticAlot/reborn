package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
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

public class Strike_Brown extends CustomCard
{
    public static final String ID = "Reborn:Strike_Brown";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DMG = 6;

    public Strike_Brown()
    {
        super(ID, NAME, "Reborn/assets/cards/strike_brown.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.BASIC, CardTarget.ENEMY);

        this.baseDamage = DMG;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Strike_Brown();
    }

    @Override
    public void upgrade()
    {
        if(upgraded)
        {
            upgradeName();
            upgradeDamage(3);
        }
    }
}
