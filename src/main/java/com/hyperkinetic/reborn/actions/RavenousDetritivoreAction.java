package com.hyperkinetic.reborn.actions;

import basemod.ReflectionHacks;
import com.hyperkinetic.reborn.cards.Infection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import sun.reflect.Reflection;

import java.util.ArrayList;
import java.util.Collections;

public class RavenousDetritivoreAction extends AbstractGameAction
{
    private ArrayList<Integer> detritivores;

    private static final UIStrings ui_strings = CardCrawlGame.languagePack.getUIString("Reborn:RavenousDetritivoreAction");
    public static final String[] TEXT = ui_strings.TEXT;

    public RavenousDetritivoreAction(ArrayList<Integer> detritivores)
    {
        this.startDuration = this.duration = Settings.ACTION_DUR_FAST;
        this.detritivores = detritivores;
        Collections.sort(detritivores);
    }

    @Override
    public void update()
    {
        if(detritivores == null || detritivores.isEmpty())
        {
            this.isDone = true;
        }

        else if(this.duration == this.startDuration)
        {
            ArrayList<AbstractCard> infections = new ArrayList<>();
            for(AbstractCard c : AbstractDungeon.player.discardPile.group)
            {
                if(c.type == AbstractCard.CardType.STATUS) infections.add(c);
            }

            if(infections.size() < detritivores.get(0))
            {
                this.isDone = true;
                tickDuration();
                return;
            }

            int numCardsToSelect = detritivores.get(0);
            int i = 1;
            while(i < detritivores.size() && (numCardsToSelect + detritivores.get(i)) < AbstractDungeon.player.discardPile.size())
                numCardsToSelect += detritivores.get(i++);

            CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard c : infections) temp.addToBottom(c);

            AbstractDungeon.gridSelectScreen.open(temp, numCardsToSelect, true, TEXT[0]);
        }

        else if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())
        {
            int toExhaust = 0;
            int i = 0;
            while(i < detritivores.size() && (toExhaust + detritivores.get(i)) <= AbstractDungeon.gridSelectScreen.selectedCards.size())
                toExhaust += detritivores.get(i++);

            if(i == 0)
            {
                this.isDone = true;
                tickDuration();
                return;
            }

            for(int j = 0; j < toExhaust; j++)
            {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(
                        AbstractDungeon.gridSelectScreen.selectedCards.get(j), AbstractDungeon.player.discardPile
                ));
            }

            String bubble = TEXT[1];
            for(int j = 0; j < i; j++)
            {
                AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
                if(j != 0) bubble += TEXT[2];
            }
            bubble += TEXT[3];

            Hitbox discardHb = (Hitbox)(ReflectionHacks.getPrivate(AbstractDungeon.overlayMenu.discardPilePanel,
                    DiscardPilePanel.class, "hb"));
            AbstractDungeon.effectList.add(new ThoughtBubble(discardHb.x, discardHb.y + (discardHb.height / 4), 3.0F, bubble, false));

            this.isDone = true;
        }

        tickDuration();
    }
}
