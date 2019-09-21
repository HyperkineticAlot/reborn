package com.hyperkinetic.reborn.powers;

import com.hyperkinetic.reborn.actions.DredgeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UndeadInsightPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:UndeadInsightPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UndeadInsightPower(AbstractCreature p, int amount)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = p;
        this.amount = amount;

        updateDescription();
        loadRegion("undeadinsight.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + CardCrawlGame.languagePack.getUIString("Period").TEXT[0];
    }

    @Override
    public void atStartOfTurn()
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new DredgeAction(this.amount));
    }
}
