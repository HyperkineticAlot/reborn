package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.hyperkinetic.reborn.cards.*;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
import com.sun.naming.internal.FactoryEnumeration;

import java.util.ArrayList;

@SpirePatch(
        clz = EmptyDeckShuffleAction.class,
        method = "update"
)

public class NecroticEmptyDeckShufflePatch
{
    private static ArrayList<String> necrotic_ids = new ArrayList<>();
    private static ArrayList<AbstractCard> removed_cards = new ArrayList<>();

    @SpirePrefixPatch
    public static void removeNecroticCards()
    {
        for(AbstractCard c : AbstractDungeon.player.discardPile.group)
        {
            if(necrotic_ids.contains(c.cardID))
            {
                removed_cards.add(c);
            }
        }

        for(AbstractCard c : removed_cards) AbstractDungeon.player.discardPile.removeCard(c);
    }

    @SpirePostfixPatch
    public static void readdNecroticCards()
    {
        for(AbstractCard c : removed_cards)
        {
            AbstractDungeon.player.discardPile.addToTop(c);
        }
        removed_cards.clear();
    }

    static
    {
        necrotic_ids.add(Fester.ID);
        necrotic_ids.add(CrushingSwing.ID);
        necrotic_ids.add(LaunchEntrails.ID);
        necrotic_ids.add(SinisterSurge.ID);
        necrotic_ids.add(HellboundTechnique.ID);
        necrotic_ids.add(Scab.ID);
        necrotic_ids.add(Infection.ID);
        necrotic_ids.add(FatesVerdict.ID);
    }
}
