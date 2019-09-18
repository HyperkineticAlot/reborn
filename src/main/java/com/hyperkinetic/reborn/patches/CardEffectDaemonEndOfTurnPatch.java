package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.hyperkinetic.reborn.RebornMod;
import com.hyperkinetic.reborn.util.CardEffectDaemon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch(
        clz = AbstractRoom.class,
        method = "applyEndOfTurnRelics"
)
public class CardEffectDaemonEndOfTurnPatch
{
    @SpirePrefixPatch
    public static void applyEndOfTurnEffects()
    {
        for(CardEffectDaemon effect : RebornMod.tracker)
        {
            effect.atEndOfTurn();
        }
    }
}
