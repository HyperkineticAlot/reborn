package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "useCard"
)
public class BloodRitualUseCardPatch
{
    private static int costToRestore = -3;

    @SpirePrefixPatch
    public static void preUseCard(AbstractPlayer p, AbstractCard c, AbstractMonster m)
    {
        if(c.costForTurn == -1 || !AbstractDungeon.player.hasPower("Reborn:BloodRitualPower")) return;

        costToRestore = c.costForTurn;
        c.costForTurn = 0;
    }

    @SpirePostfixPatch
    public static void postUseCard(AbstractPlayer p, AbstractCard c, AbstractMonster m)
    {
        if(c.costForTurn == -1 || !AbstractDungeon.player.hasPower("Reborn:BloodRitualPower")) return;

        c.costForTurn = costToRestore;
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player,
                AbstractDungeon.player, costToRestore));
    }
}
