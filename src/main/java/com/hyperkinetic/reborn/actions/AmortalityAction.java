package com.hyperkinetic.reborn.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AmortalityAction extends AbstractGameAction
{
    public AmortalityAction(AbstractPlayer p, int amount)
    {
        this.startDuration = this.duration = Settings.ACTION_DUR_FAST;
        this.target = p;
        this.amount = amount;
    }

    @Override
    public void update()
    {
        if(this.duration == this.startDuration)
        {
            if(target.hasPower("Reborn:ShroudPower") &&
                    target.getPower("Reborn:ShroudPower").amount >= this.amount)
                AbstractDungeon.actionManager.addToBottom(new EnterUndeathAction(target));

            this.isDone = true;
        }

        tickDuration();
    }
}
