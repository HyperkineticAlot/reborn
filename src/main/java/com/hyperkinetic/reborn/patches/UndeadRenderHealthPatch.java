package com.hyperkinetic.reborn.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;

/*@SpirePatch(
        clz = AbstractCreature.class,
        method = "renderRedHealthBar",
        paramtypez = {SpriteBatch.class, float.class, float.class })*/
public class UndeadRenderHealthPatch
{
    private static final Color undead_black = new Color(0.0F, 0.0F, 0.0F, 0.0F);

    /*@SpireInsertPatch(
            rloc = 6,
            localvars = {"this"})*/
    public static void insertRenderUndeadHealth(SpriteBatch sb, float x, float y, AbstractCreature theCreature)
    {
        if(!theCreature.hasPower("Reborn:UndeathPower")) return;

        sb.setColor(undead_black);
    }
}
