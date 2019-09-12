package com.hyperkinetic.reborn.characters;

import basemod.abstracts.CustomPlayer;
import com.hyperkinetic.reborn.enums.RebornClassEnum;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class Reborn extends CustomPlayer
{
    public static final int ENERGY_PER_TURN = 3;
    public static final String CHAR_SHOULDER = "assets/char/shoulder.png";
    public static final String CHAR_SHOULDER_2 = "assets/char/shoulder.png";
    public static final String CORPSE = "assets/char/corpse.png";
    public static final String MODEL = "assets/char/thearchmage.png";
    /*public static final String SKELETON_ATLAS = "Archmage/assets/char/skeleton.atlas";
    public static final String SKELETON_JSON = "Archmage/assets/char/skeleton.json";*/

    public static final int START_HP = 65;
    public static final int MAX_HP = 65;
    public static final int START_GOLD = 99;
    public static final int HAND_SIZE = 5;

    private static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("Reborn");
    public static final String NAME = charStrings.NAMES[0];
    public static final String DESCRIPTION = charStrings.TEXT[0];

    public Reborn(String name)
    {
        super(name, RebornClassEnum.REBORN, null, "assets/char/orb/vfx.png",
                null, (String) null);

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);

        initializeClass(MODEL, CHAR_SHOULDER_2, CHAR_SHOULDER, CORPSE, getLoadout(),
                20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        /*loadAnimation(SKELETON_ATLAS, SKELETON_JSON, 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());*/
    }
}
