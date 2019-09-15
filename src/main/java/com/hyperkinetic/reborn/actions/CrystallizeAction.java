package com.hyperkinetic.reborn.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CrystallizeAction extends AbstractGameAction
{
    public CrystallizeAction(AbstractCreature owner)
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.target = owner;
    }

    @Override
    public void update()
    {
        if(this.duration == Settings.ACTION_DUR_FAST)
        {
            if(target.hasPower("Reborn:UndeathPower"))
            {
                if(!target.hasPower("Reborn:ShroudPower"))
                {
                    isDone = true;
                    tickDuration();
                    return;
                }

                target.getPower("Reborn:ShroudPower").flash();
                int amt = target.getPower("Reborn:ShroudPower").amount;

                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, target, "Reborn:ShroudPower"));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(target, target, amt));
            }
        }

        tickDuration();
    }
}
