package com.hyperkinetic.reborn.powers;

import com.hyperkinetic.reborn.util.IDredgeListener;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GrislyCarapacePower extends AbstractRebornPower implements IDredgeListener
{
    public static final String P_ID = "Reborn:GrislyCarapacePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GrislyCarapacePower(AbstractPlayer owner, int amount)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = owner;
        this.amount = amount;

        updateDescription();
        loadRegion("grislycarapace.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onDredgeCard(AbstractCard dredged)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, this.amount));
    }
}
