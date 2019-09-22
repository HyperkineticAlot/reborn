package com.hyperkinetic.reborn.powers;

import com.hyperkinetic.reborn.cards.Life;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BirthAddLifePower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:AddLifePower";
    private static final PowerStrings power_strings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = power_strings.NAME;
    public static final String[] DESCRIPTIONS = power_strings.DESCRIPTIONS;

    private boolean upgraded;

    public BirthAddLifePower(boolean isUpgraded)
    {
        this.ID = P_ID;
        this.name = NAME;
        this.amount = -1;
        this.owner = AbstractDungeon.player;
        upgraded = isUpgraded;

        updateDescription();
        loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        if(upgraded)
        {
            Life life = new Life();
            life.upgrade();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(life, 1));
        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Life(), 1));
        }

        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
