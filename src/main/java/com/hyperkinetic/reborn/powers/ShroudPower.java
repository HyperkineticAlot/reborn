package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class ShroudPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:ShroudPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ShroudPower(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = owner;
        this.amount = amount;

        updateDescription();
        loadRegion("shroud.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + CardCrawlGame.languagePack.getUIString("Period").TEXT[0];
    }
}
