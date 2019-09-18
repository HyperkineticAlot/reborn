package com.hyperkinetic.reborn.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MetamorphosisAction extends AbstractGameAction
{
    public MetamorphosisAction(AbstractPlayer p)
    {
        this.source = p;
        this.startDuration = this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update()
    {
        if(this.duration == this.startDuration)
        {
            if(((AbstractPlayer)source).discardPile.size() > ((AbstractPlayer)source).drawPile.size())
                AbstractDungeon.actionManager.addToBottom(new EnterUndeathAction(source));

            this.isDone = true;
        }

        tickDuration();
    }
}
