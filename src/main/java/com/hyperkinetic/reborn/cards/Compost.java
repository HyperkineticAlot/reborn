package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashSet;
import java.util.Set;

public class Compost extends CustomCard
{
    public static final String ID = "Reborn:Compost";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 0;

    public Compost()
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

        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, p.hand.size(), true));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, types.size() + (this.upgraded ? 1 : 0)));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Compost();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            this.rawDescription = card_strings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
