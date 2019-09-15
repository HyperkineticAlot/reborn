package com.hyperkinetic.reborn.actions;

import basemod.ReflectionHacks;
import com.hyperkinetic.reborn.cards.BurstOfSpeed;
import com.hyperkinetic.reborn.cards.UndeadInsight;
import com.hyperkinetic.reborn.cards.UndeadMight;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UndeathDiscoveryAction extends AbstractGameAction
{
    public static final ArrayList<AbstractCard> UNDEATH_OPTIONS;
    private static final UIStrings ui_strings;
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
        CardRewardScreen screen = AbstractDungeon.cardRewardScreen;
        if(this.duration == Settings.ACTION_DUR_FAST)
        {
            try
            {
                screen.confirmButton.hideInstantly();
                ReflectionHacks.setPrivate(screen, CardRewardScreen.class, "touchCard", null);
                screen.rItem = null;
                ReflectionHacks.setPrivate(screen, CardRewardScreen.class, "codex", false);
                ReflectionHacks.setPrivate(screen, CardRewardScreen.class, "discovery", true);
                screen.discoveryCard = null;
                ReflectionHacks.setPrivate(screen, CardRewardScreen.class, "draft", false);
                screen.codexCard = null;
                ((SingingBowlButton) ReflectionHacks.getPrivate(screen, CardRewardScreen.class, "bowlButton")).hide();
                ((SkipCardButton) ReflectionHacks.getPrivate(screen, CardRewardScreen.class, "skipButton")).hide();
                screen.onCardSelect = true;
                AbstractDungeon.topPanel.unhoverHitboxes();

                ArrayList<AbstractCard> options = new ArrayList<>();
                for (AbstractCard c : UNDEATH_OPTIONS) {
                    options.add(c.makeCopy());
                    UnlockTracker.markCardAsSeen(c.cardID);
                }

                screen.rewardGroup = options;
                AbstractDungeon.isScreenUp = true;
                AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
                AbstractDungeon.dynamicBanner.appear(ui_strings.TEXT[0]);
                AbstractDungeon.overlayMenu.showBlackScreen();

                Method setCards = CardRewardScreen.class.getDeclaredMethod("placeCards", float.class, float.class);
                setCards.setAccessible(true);
                setCards.invoke(screen, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT * 0.45F);

                this.tickDuration();
            }
            catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
            {
                this.isDone = true;
                e.printStackTrace();
            }
        }
        else
        {
            if(!receivedBuff)
            {
                if(screen.discoveryCard != null)
                {
                    screen.discoveryCard.use(AbstractDungeon.player, null);
                    screen.discoveryCard = null;
                }

                receivedBuff = true;
            }

            this.tickDuration();
        }
    }

    static
    {
        UNDEATH_OPTIONS = new ArrayList<>();
        UNDEATH_OPTIONS.add(new BurstOfSpeed());
        UNDEATH_OPTIONS.add(new UndeadInsight());
        UNDEATH_OPTIONS.add(new UndeadMight());

        ui_strings = CardCrawlGame.languagePack.getUIString("Reborn:UndeathDiscoveryAction");
    }
}
