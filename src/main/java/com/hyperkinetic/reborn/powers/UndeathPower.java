package com.hyperkinetic.reborn.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.hyperkinetic.reborn.actions.UndeathDiscoveryAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class UndeathPower extends AbstractRebornPower implements HealthBarRenderPower
{
    public static final String P_ID = "Reborn:UndeathPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int maxHP;
    private int currentHP;

    public UndeathPower(AbstractPlayer owner)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = owner;

        updateDescription();
        loadRegion("beta.png");

        maxHP = owner.maxHealth;
        currentHP = owner.currentHealth;

        owner.maxHealth = owner.currentHealth = owner.getPower("Reborn:ShroudPower").amount;
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner,"Reborn:ShroudPower"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new RotPower(owner, 1), 1));
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new UndeathDiscoveryAction());
    }

    @Override
    public void onRemove()
    {
        owner.maxHealth = maxHP;
        owner.currentHealth = currentHP;
    }

    @Override
    public int getHealthBarAmount()
    {
        return AbstractDungeon.player.currentHealth;
    }

    @Override
    public Color getColor()
    {
        return new Color(0.0F, 0.0F, 0.3F, 1.0F);
    }
}
