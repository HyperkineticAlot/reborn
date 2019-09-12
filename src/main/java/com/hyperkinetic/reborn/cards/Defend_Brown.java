package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend_Brown extends CustomCard
{
    public static final String ID = "Reborn:Defend_Brown";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final int COST = 1;
    private static final int BLOCK = 5;

    public Defend_Brown()
    {
        super(ID, NAME, "Reborn/assets/cards/defend_brown.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.BASIC, CardTarget.SELF);

        this.baseBlock = BLOCK;
        this.tags.add(BaseModCardTags.BASIC_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Defend_Brown();
    }

    @Override
    public void upgrade()
    {
        if(upgraded)
        {
            upgradeName();
            upgradeBlock(3);
        }
    }
}
