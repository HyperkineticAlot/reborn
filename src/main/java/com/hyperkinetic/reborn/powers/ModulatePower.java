package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class ModulatePower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:ModulatePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int THRESH = 15;
    private static final int HEAL = 5;

    private int undeadAmount;

    public ModulatePower(AbstractCreature owner, int shroud)
    {
        this.ID = P_ID;
        this.name = NAME;
        this.owner = owner;

        this.undeadAmount = 1;
        this.amount = owner.hasPower("Reborn:UndeathPower") ? this.undeadAmount : shroud;

        updateDescription();
        loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = owner.hasPower("Reborn:UndeathPower") ? DESCRIPTIONS[3] + THRESH*this.amount + DESCRIPTIONS[4] :
                DESCRIPTIONS[0] + undeadAmount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void stackPower(int amount)
    {
        super.stackPower(amount);
        this.undeadAmount++;
    }

    @Override
    public void atStartOfTurn()
    {
        if(this.owner.hasPower("Reborn:UndeathPower"))
        {
            if(this.owner.hasPower("Reborn:ShroudPower") && this.owner.getPower("Reborn:ShroudPower").amount >= THRESH)
            {
                int timesToRemove = 0;
                while(timesToRemove < this.amount && this.owner.getPower("Reborn:ShroudPower").amount >= THRESH * timesToRemove)
                    timesToRemove++;

                flash();
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, "Reborn:ShroudPower", THRESH * timesToRemove));
                AbstractDungeon.actionManager.addToBottom(new HealAction(owner, owner, HEAL * timesToRemove));
            }
        }
        else
        {
            flash();
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(owner, owner, undeadAmount));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ShroudPower(owner, this.amount)));
        }
    }

    public void modulate()
    {
        this.amount = this.undeadAmount;
        this.description = DESCRIPTIONS[3] + THRESH*this.amount + DESCRIPTIONS[4];
    }

}
