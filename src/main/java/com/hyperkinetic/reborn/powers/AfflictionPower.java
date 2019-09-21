package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AfflictionPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:AfflictionPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AfflictionPower(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = owner;
        this.amount = amount;

        updateDescription();
        loadRegion("affliction.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                new ShroudPower(owner, this.amount), this.amount));
    }
}
