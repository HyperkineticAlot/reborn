package com.hyperkinetic.reborn.actions;

import com.hyperkinetic.reborn.cards.UndeadAgility;
import com.hyperkinetic.reborn.cards.UndeadInsight;
import com.hyperkinetic.reborn.cards.UndeadMight;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class UndeathDiscoveryAction extends AbstractGameAction
{
    public static final ArrayList<AbstractCard> UNDEATH_OPTIONS;
    private boolean receivedBuff;

    public UndeathDiscoveryAction()
    {
        this.actionType = ActionType.POWER;
        this.duration = Settings.ACTION_DUR_FAST;
        this.receivedBuff = false;
    }

    @Override
    public void update()
    {
        if(this.duration == Settings.ACTION_DUR_FAST)
        {
            AbstractDungeon.cardRewardScreen.rewardGroup = UNDEATH_OPTIONS;
            AbstractDungeon.cardRewardScreen.discoveryOpen();
            this.tickDuration();
        }
        else
        {
            if(!receivedBuff)
            {
                if(AbstractDungeon.cardRewardScreen.discoveryCard != null)
                {
                    AbstractDungeon.cardRewardScreen.discoveryCard.use(AbstractDungeon.player, null);
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                receivedBuff = true;
            }

            this.tickDuration();
        }
    }

    static
    {
        UNDEATH_OPTIONS = new ArrayList<>();
        UNDEATH_OPTIONS.add(new UndeadAgility());
        UNDEATH_OPTIONS.add(new UndeadInsight());
        UNDEATH_OPTIONS.add(new UndeadMight());
    }
}
