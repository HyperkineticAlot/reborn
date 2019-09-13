package com.hyperkinetic.reborn.powers;

import com.hyperkinetic.reborn.actions.DredgeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UndeadInsightPower extends AbstractPower
{
    public static final String P_ID = "Reborn:UndeadInsightPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UndeadInsightPower(AbstractPlayer p)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = p;
        this.amount = 1;

        updateDescription();
        loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount;
    }

    @Override
    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new DredgeAction(this.amount));
    }
}
