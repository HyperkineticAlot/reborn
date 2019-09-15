package com.hyperkinetic.reborn.actions;

import basemod.BaseMod;
import com.hyperkinetic.reborn.util.IDredgeListener;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DredgeAction extends AbstractGameAction
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Reborn:DredgeAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private float startingDuration;

    public DredgeAction(int amount)
    {
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = startingDuration;
    }

    public void update()
    {
        if(this.duration == this.startingDuration)
        {
            if(AbstractDungeon.player.discardPile.isEmpty())
            {
                this.isDone = true;
                return;
            }

            CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(int i = 0; i < AbstractDungeon.player.discardPile.size(); ++i)
            {
                temp.addToBottom(AbstractDungeon.player.discardPile.group.get(i));
            }

            AbstractDungeon.gridSelectScreen.open(temp, Math.min(this.amount, AbstractDungeon.player.discardPile.size()),
                    true, TEXT[0]);
        }
        else if(!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())
        {
            for(AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
            {
                if(AbstractDungeon.player.hand.group.size() >= BaseMod.MAX_HAND_SIZE)
                {
                    AbstractDungeon.player.createHandIsFullDialog();
                    break;
                }

                AbstractDungeon.player.discardPile.moveToHand(c, AbstractDungeon.player.discardPile);

                for(AbstractPower p : AbstractDungeon.player.powers)
                {
                    if(p instanceof IDredgeListener) ((IDredgeListener) p).onDredgeCard(c);
                }
                for(AbstractRelic r : AbstractDungeon.player.relics)
                {
                    if(r instanceof IDredgeListener) ((IDredgeListener) r).onDredgeCard(c);
                }
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }
}
