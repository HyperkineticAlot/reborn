package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class RotPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:RotPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RotPower(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = owner;
        this.amount = amount;

        this.type = PowerType.DEBUFF;

        updateDescription();
        loadRegion("rot.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public void atEndOfTurn(boolean garbage)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(owner,
                new DamageInfo(owner, this.amount, DamageInfo.DamageType.HP_LOSS)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                new DexterityPower(owner, -this.amount)));
        flash();
        this.amount++;
    }
}
