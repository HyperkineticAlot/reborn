package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GraveStrength extends CustomCard
{
    public static final String ID = "Reborn:GraveStrength";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = -2;
    private static final int STRENGTH = 2;

    public GraveStrength()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCardEnum.REBORN_BROWN,
                AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = STRENGTH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new StrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new LoseStrengthPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new GraveStrength();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        return false;
    }
}
