package com.hyperkinetic.reborn.powers;

import com.hyperkinetic.reborn.cards.Death;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class LifeAddDeathPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:AddDeathPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean upgraded;

    public LifeAddDeathPower(boolean isUpgraded)
    {
        this.ID = P_ID;
        this.name = NAME;
        this.upgraded = isUpgraded;
        this.owner = AbstractDungeon.player;
        this.amount = -1;

        this.updateDescription();
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
            Death death = new Death();
            death.upgrade();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(death, 1));
        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Death(), 1));
        }

        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
