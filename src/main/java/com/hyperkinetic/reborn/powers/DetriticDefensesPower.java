package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.HashSet;
import java.util.Set;

public class DetriticDefensesPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:DetriticDefensesPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DetriticDefensesPower(AbstractPlayer p, int amount)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = p;
        this.amount = amount;

        updateDescription();
        loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        if(!isPlayer) return;
        Set<AbstractCard.CardType> types = new HashSet<>();
        for(AbstractCard c : ((AbstractPlayer)owner).discardPile.group)
        {
            types.add(c.type);
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner,
                types.size() * this.amount));
    }
}
