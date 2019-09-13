package com.hyperkinetic.reborn;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyperkinetic.reborn.cards.*;
import com.hyperkinetic.reborn.characters.Reborn;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.enums.RebornClassEnum;
import com.hyperkinetic.reborn.relics.IchorousMemento;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpireInitializer
public class RebornMod implements EditCardsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber,
        EditStringsSubscriber, EditRelicsSubscriber
{
    private static final Color REBORN_COLOR = CardHelper.getColor(0, 0, 0);

    private static final String ATTACK_BG = "Reborn/assets/small/attack_bg.png";
    private static final String SKILL_BG = "Reborn/assets/small/skill_bg.png";
    private static final String POWER_BG = "Reborn/assets/small/power_bg.png";
    private static final String ENERGY_ORB = "Reborn/assets/small/card_energy_orb.png";
    private static final String CARD_ENERGY_ORB = "Reborn/assets/small/energy_orb.png";

    private static final String ATTACK_BG_PORTRAIT = "Reborn/assets/large/attack_bg.png";
    private static final String SKILL_BG_PORTRAIT = "Reborn/assets/large/skill_bg.png";
    private static final String POWER_BG_PORTRAIT = "Reborn/assets/large/power_bg.png";
    private static final String ENERGY_ORB_PORTRAIT = "Reborn/assets/large/energy_orb.png";

    public RebornMod()
    {
        BaseMod.addColor(AbstractCardEnum.REBORN_BROWN, REBORN_COLOR, ATTACK_BG, SKILL_BG, POWER_BG, ENERGY_ORB,
                ATTACK_BG_PORTRAIT, SKILL_BG_PORTRAIT, POWER_BG_PORTRAIT, ENERGY_ORB_PORTRAIT, CARD_ENERGY_ORB);
        BaseMod.subscribe(this);
    }

    public static void initialize()
    {
        new RebornMod();
    }

    public static String regionString()
    {
        switch(Settings.language)
        {
            default:
                return "eng";
        }
    }

    @Override
    public void receiveEditCards()
    {
        // Basic
        BaseMod.addCard(new Strike_Brown());
        BaseMod.addCard(new Defend_Brown());
        BaseMod.addCard(new Fester());
        BaseMod.addCard(new Excoriate());

        // Special
        BaseMod.addCard(new UndeadAgility());
        BaseMod.addCard(new UndeadInsight());
        BaseMod.addCard(new UndeadMight());
    }

    @Override
    public void receiveEditCharacters()
    {
        BaseMod.addCharacter(new Reborn(CardCrawlGame.playerName),
                "Reborn/assets/charSelect/button.png",
                "Reborn/assets/charSelect/splash.png",
                RebornClassEnum.REBORN);
    }

    @Override
    public void receiveEditStrings()
    {
        BaseMod.loadCustomStringsFile(CardStrings.class, "Reborn/localization/eng/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "Reborn/localization/eng/characters.json");
        //BaseMod.loadCustomStringsFile(PowerStrings.class, "Reborn/localization/eng/powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "Reborn/localization/eng/relics.json");
        BaseMod.loadCustomStringsFile(KeywordStrings.class, "Reborn/localization/eng/keywords.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "Reborn/localization/eng/ui.json");
    }

    @Override
    public void receiveEditKeywords()
    {
        final Gson gson = new Gson();
        String language = regionString();

        String keyStrings = Gdx.files.internal("Reborn/localization/" + language + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Type type = new TypeToken<Map<String, Keyword>>(){}.getType();

        Map<String, Keyword> keywords;
        keywords = gson.fromJson(keyStrings, type);
        keywords.forEach((k, v) -> {
            BaseMod.addKeyword(v.NAMES, v.DESCRIPTION);
        });
    }

    @Override
    public void receiveEditRelics()
    {
        BaseMod.addRelicToCustomPool(new IchorousMemento(), AbstractCardEnum.REBORN_BROWN);
    }

}
