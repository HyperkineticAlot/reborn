package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Entropy extends CustomCard
{
    public static final String ID = "Reborn:Entropy";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;

    public Entropy()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.COMMON, CardTarget.NONE);

        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<AbstractCard> targets = new ArrayList<>();
        for(AbstractCard c : p.discardPile.group)
        {
            if(c.costForTurn > 0) targets.add(c);
        }

        if(targets.isEmpty()) return;
        int index = Math.round((float)(Math.random() * targets.size()));
        targets.get(index).costForTurn = 0;
        targets.get(index).isCostModifiedForTurn = true;
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Entropy();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}
