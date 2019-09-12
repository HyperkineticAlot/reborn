package com.hyperkinetic.reborn.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.hyperkinetic.reborn.cards.Defend_Brown;
import com.hyperkinetic.reborn.cards.Strike_Brown;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.enums.RebornClassEnum;
import com.hyperkinetic.reborn.relics.IchorousMemento;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

public class Reborn extends CustomPlayer
{
    public static final int ENERGY_PER_TURN = 3;
    public static final String CHAR_SHOULDER = "Reborn/assets/char/shoulder.png";
    public static final String CHAR_SHOULDER_2 = "Reborn/assets/char/shoulder.png";
    public static final String CORPSE = "Reborn/assets/char/corpse.png";
    public static final String MODEL = "Reborn/assets/char/thearchmage.png";
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
        super(name, RebornClassEnum.REBORN, null, "Reborn/assets/char/orb/vfx.png",
                null, (String) null);

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);

        initializeClass(MODEL, CHAR_SHOULDER_2, CHAR_SHOULDER, CORPSE, getLoadout(),
                20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        /*loadAnimation(SKELETON_ATLAS, SKELETON_JSON, 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());*/
    }

    @Override
    public ArrayList<String> getStartingDeck()
    {
        ArrayList<String> starter = new ArrayList<>();

        starter.add(Strike_Brown.ID);
        starter.add(Strike_Brown.ID);
        starter.add(Strike_Brown.ID);
        starter.add(Strike_Brown.ID);

        starter.add(Defend_Brown.ID);
        starter.add(Defend_Brown.ID);
        starter.add(Defend_Brown.ID);
        starter.add(Defend_Brown.ID);

        return starter;
    }

    @Override
    public ArrayList<String> getStartingRelics()
    {
        ArrayList<String> start = new ArrayList<>();
        start.add(IchorousMemento.ID);
        UnlockTracker.markRelicAsSeen(IchorousMemento.ID);
        return start;
    }

    @Override
    public CharSelectInfo getLoadout()
    {
        return new CharSelectInfo(NAME, DESCRIPTION, START_HP, MAX_HP, 0, START_GOLD, HAND_SIZE, this,
                getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass pClass) {
        return NAME;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.REBORN_BROWN;
    }

    @Override
    public Color getCardRenderColor() {
        return new Color(0.2F, 0.0F, 0.8F, 0.9F);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_Brown();
    }

    @Override
    public Color getCardTrailColor() {
        return new Color(0.2F, 0.0F, 0.8F, 0.9F);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_MAGIC_BEAM_SHORT";
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAME;
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Reborn(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return charStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.BROWN;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect()
    {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_LIGHT
        };
    }

    @Override
    public String getVampireText()
    {
        return Vampires.DESCRIPTIONS[0];
    }
}
