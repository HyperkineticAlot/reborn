package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.hyperkinetic.reborn.RebornMod;
import com.hyperkinetic.reborn.cards.GraveStrength;
import com.hyperkinetic.reborn.util.CardEffectDaemon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

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

        for(AbstractCard c : AbstractDungeon.player.discardPile.group)
        {
            if(c.cardID.equals(GraveStrength.ID))
            {
                c.use(AbstractDungeon.player, null);
            }
        }
    }
}
