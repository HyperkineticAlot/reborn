package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.hyperkinetic.reborn.cards.PiercingVision;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.HashSet;
import java.util.Set;

@SpirePatch(
        clz = MillAction.class,
        method = "update"
)
public class MillActionPatch
{
    private static final Set<String> mill_listeners;

    @SpireInsertPatch(rloc = 3)
    public static void checkForMillListeners()
    {
        if(mill_listeners.contains(AbstractDungeon.player.drawPile.getTopCard().cardID))
        {
            AbstractDungeon.player.drawPile.getTopCard().use(AbstractDungeon.player, null);
        }

        if(AbstractDungeon.player.drawPile.getTopCard().type == AbstractCard.CardType.STATUS &&
            AbstractDungeon.player.hasPower("Reborn:FlexibilityPower"))
        {
            AbstractPower flex = AbstractDungeon.player.getPower("Reborn:FlexibilityPower");
            flex.flash();
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, flex.amount));
        }
    }

    @SpireInsertPatch(rloc = 7)
    public static void secondCheck()
    {
        checkForMillListeners();
    }

    static
    {
        mill_listeners = new HashSet<>();
        mill_listeners.add(PiercingVision.ID);
    }
}
