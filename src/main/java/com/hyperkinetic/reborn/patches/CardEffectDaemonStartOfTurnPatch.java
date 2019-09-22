package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.hyperkinetic.reborn.RebornMod;
import com.hyperkinetic.reborn.actions.RavenousDetritivoreAction;
import com.hyperkinetic.reborn.actions.RewindLifestreamAction;
import com.hyperkinetic.reborn.cards.GraveStrength;
import com.hyperkinetic.reborn.cards.MorbidIntuition;
import com.hyperkinetic.reborn.cards.RavenousDetritivore;
import com.hyperkinetic.reborn.util.CardEffectDaemon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "applyStartOfTurnRelics"
)
public class CardEffectDaemonStartOfTurnPatch
{
    @SpirePrefixPatch
    public static void applyStartOfTurnEffects()
    {
        for(CardEffectDaemon effect : RebornMod.tracker)
        {
            effect.atStartOfTurn();
        }

        ArrayList<Integer> detritivores = new ArrayList<>();
        for(AbstractCard c : AbstractDungeon.player.discardPile.group)
        {
            if(c.cardID.equals(GraveStrength.ID) || c.cardID.equals(MorbidIntuition.ID))
            {
                c.use(AbstractDungeon.player, null);
            }
            if(c.cardID.equals(RavenousDetritivore.ID))
            {
                detritivores.add(c.magicNumber);
            }
        }

        if(!detritivores.isEmpty())
            AbstractDungeon.actionManager.addToBottom(new RavenousDetritivoreAction(detritivores));

        RewindLifestreamAction.startCombatHP = AbstractDungeon.player.currentHealth;
    }
}
