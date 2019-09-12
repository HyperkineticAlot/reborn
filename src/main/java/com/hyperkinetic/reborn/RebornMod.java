package com.hyperkinetic.reborn;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.hyperkinetic.reborn.cards.*;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;

@SpireInitializer
public class RebornMod implements EditCardsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber,
        EditStringsSubscriber, EditRelicsSubscriber
{
    private static final Color REBORN_COLOR = CardHelper.getColor(0, 0, 0);

    private static final String ATTACK_BG = "assets/small/attack_bg.png";
    private static final String SKILL_BG = "assets/small/skill_bg.png";
    private static final String POWER_BG = "assets/small/power_bg.png";
    private static final String ENERGY_ORB = "assets/small/card_energy_orb.png";
    private static final String CARD_ENERGY_ORB = "assets/small/energy_orb.png";

    private static final String ATTACK_BG_PORTRAIT = "assets/large/attack_bg.png";
    private static final String SKILL_BG_PORTRAIT = "assets/large/skill_bg.png";
    private static final String POWER_BG_PORTRAIT = "assets/large/power_bg.png";
    private static final String ENERGY_ORB_PORTRAIT = "assets/large/energy_orb.png";

    public RebornMod()
    {
        BaseMod.addColor(AbstractCardEnum.REBORN_BROWN, REBORN_COLOR, ATTACK_BG, SKILL_BG, POWER_BG, ENERGY_ORB,
                ATTACK_BG_PORTRAIT, SKILL_BG_PORTRAIT, POWER_BG_PORTRAIT, ENERGY_ORB_PORTRAIT, CARD_ENERGY_ORB);
        BaseMod.subscribe(this);
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
    }

    @Override
    public void receiveEditCharacters()
    {

    }

    @Override
    public void receiveEditStrings()
    {
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/eng/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/eng/characters.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/eng/powers.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/eng/relics.json");
        BaseMod.loadCustomStringsFile(KeywordStrings.class, "localization/eng/keywords.json");
    }

    @Override
    public void receiveEditKeywords()
    {

    }

    @Override
    public void receiveEditRelics()
    {

    }

}
