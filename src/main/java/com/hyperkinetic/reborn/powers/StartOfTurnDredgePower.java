package com.hyperkinetic.reborn.powers;

import com.hyperkinetic.reborn.actions.DredgeAction;
import com.megacrit.cardcrawl.actions.common.MillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class StartOfTurnDredgePower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:StartOfTurnDredgePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public StartOfTurnDredgePower(AbstractPlayer p, int amount)
    {
        this.owner = p;
        this.amount = amount;
        this.name = NAME;
        this.ID = P_ID;

        updateDescription();
        loadRegion("lichform.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new MillAction(owner, owner, ((AbstractPlayer)owner).drawPile.size()));
        AbstractDungeon.actionManager.addToBottom(new DredgeAction(this.amount));
    }
}
