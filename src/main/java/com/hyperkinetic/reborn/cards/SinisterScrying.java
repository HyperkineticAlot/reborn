package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.DredgeAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashSet;
import java.util.Set;

public class SinisterScrying extends CustomCard
{
    public static final String ID = "Reborn:SinisterScrying";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 2;

    public SinisterScrying()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Set<CardType> types = new HashSet<>();
        for(AbstractCard c : p.discardPile.group)
        {
            types.add(c.type);
        }

        AbstractDungeon.actionManager.addToBottom(new DredgeAction(types.size()));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new SinisterScrying();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}
