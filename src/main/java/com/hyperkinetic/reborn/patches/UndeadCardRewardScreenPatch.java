package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.hyperkinetic.reborn.actions.UndeathDiscoveryAction;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;

@SpirePatch(
        clz = CardRewardScreen.class,
        method = "discoveryOpen",
        paramtypez = {}
)
public class UndeadCardRewardScreenPatch
{
    private static boolean isUndeath = false;
    @SpireInsertPatch(
            rloc = 0,
            localvars = { "this" }
    )
    public static void checkUndeath(CardRewardScreen theScreen)
    {
        if(theScreen.rewardGroup == UndeathDiscoveryAction.UNDEATH_OPTIONS) isUndeath = true;
    }

    @SpireInsertPatch(
            rloc = 32,
            localvars = { "this" }
    )
    public static void insertUndeath(CardRewardScreen theScreen)
    {
        if(isUndeath)
        {
            theScreen.rewardGroup = UndeathDiscoveryAction.UNDEATH_OPTIONS;
            isUndeath = false;
        }
    }
}
