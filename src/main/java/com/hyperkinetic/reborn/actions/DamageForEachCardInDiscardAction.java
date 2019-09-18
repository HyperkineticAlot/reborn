package com.hyperkinetic.reborn.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamageForEachCardInDiscardAction extends AbstractGameAction
{
    private int damage;

    public DamageForEachCardInDiscardAction(AbstractCreature owner, AbstractCreature target, int damage, DamageInfo.DamageType type)
    {
        this.source = owner;
        this.target = target;
        this.damage = damage;
        this.damageType = type;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update()
    {
        if(this.duration == this.startDuration)
        {
            for(int i = 0; i < ((AbstractPlayer)source).discardPile.group.size(); ++i)
            {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(target,
                        new DamageInfo(source, damage, damageType), AttackEffect.POISON));
            }

            this.isDone = true;
        }

        tickDuration();
    }
}
