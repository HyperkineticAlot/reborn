package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.actions.common.MillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ShriekPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:ShriekPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ShriekPower(AbstractPlayer p, int amount)
    {
        this.ID = P_ID;
        this.name = NAME;
        this.owner = p;
        this.amount = amount;

        updateDescription();
        loadRegion("shriek.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.amount > 1 ? "s" : "") + DESCRIPTIONS[2];
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new MillAction(owner, owner, this.amount));
    }
}
