package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BloodRitualPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:BloodRitualPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BloodRitualPower(AbstractCreature owner)
    {
        this.ID = P_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = -1;

        updateDescription();
        loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
