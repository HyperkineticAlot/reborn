package com.hyperkinetic.reborn.actions;

import com.hyperkinetic.reborn.powers.ShroudPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlockIntoShroudAction extends AbstractGameAction
{
    public BlockIntoShroudAction(AbstractCreature p)
    {
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.target = p;
    }

    @Override
    public void update()
    {
        if(this.duration == this.startDuration)
        {
            int block = target.currentBlock;
            if(block == 0)
            {
                isDone = true;
            }
            else
            {
                AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(target, target));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, target,
                        new ShroudPower(target, block), block));
                isDone = true;
            }
        }

        tickDuration();
    }
}
