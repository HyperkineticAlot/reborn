package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.hyperkinetic.reborn.cards.*;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

@SpirePatch(
        clz = ShuffleAllAction.class,
        method = "update"
)
public class NecroticShuffleAllPatch
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
    }
}
