package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.hyperkinetic.reborn.RebornMod;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

/*SpirePatch(
        clz = AbstractPlayer.class,
        method = "preBattlePrep"
)*/
public class InitializeTrackerPatch
{

    /*@SpireInsertPatch(
            locator = Locator.class,
            localvars = {"this.drawPile"}
    )*/
    public static void initializeRebornTracker(CardGroup drawPile)
    {
        //RebornMod.initializeCardEffectDaemons(drawPile);
    }

    private static class Locator extends SpireInsertLocator
    {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException
        {
            Matcher matcher = new Matcher.MethodCallMatcher(EnergyManager.class, "prep");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), matcher);
        }
    }
}
