package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class FlexibilityPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:FlexibilityPower";
    private static final PowerStrings power_strings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = power_strings.NAME;
    public static final String[] DESCRIPTIONS = power_strings.DESCRIPTIONS;

    public FlexibilityPower(AbstractCreature owner, int amount)
    {
        this.owner = owner;
        this.amount = amount;
        this.ID = P_ID;
        this.name = NAME;

        updateDescription();
        loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + ((this.amount > 1) ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
    }
}
