package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.UnnaturalEvolutionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UnnaturalEvolution extends CustomCard
{
    public static final String ID = "Reborn:UnnaturalEvolution";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 2;
    private static final int THRESH = 3;

    public UnnaturalEvolution()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.REBORN_BROWN,
                CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = THRESH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new UnnaturalEvolutionPower(p, -1, this.magicNumber), 1));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new UnnaturalEvolution();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(-1);
        }
    }
}
