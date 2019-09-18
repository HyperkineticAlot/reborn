package com.hyperkinetic.reborn.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WhirlGainEnergyAction extends AbstractGameAction
{
    public WhirlGainEnergyAction()
    {
        this.startDuration = this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update()
    {
        if(this.duration == this.startDuration)
        {
            int m = 0;
            for(AbstractMonster thisMon : AbstractDungeon.getMonsters().monsters)
            {
                if(!thisMon.halfDead && !thisMon.isDying) m++;
            }

            if(m == 1) AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            this.isDone = true;
        }

        tickDuration();
    }
}
