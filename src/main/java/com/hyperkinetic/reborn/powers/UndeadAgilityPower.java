package com.hyperkinetic.reborn.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javafx.util.Pair;

import java.util.ArrayList;

public class UndeadAgilityPower extends AbstractPower
{
    public static final String P_ID = "Reborn:UndeadAgilityPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private ArrayList<Pair<AbstractCard, Integer> > reducedCost;

    public UndeadAgilityPower(AbstractPlayer p)
    {
        this.name = NAME;
        this.ID = P_ID;
        this.owner = p;
        this.amount = 1;

        reducedCost = new ArrayList<>();

        updateDescription();
        loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn()
    {
        for(AbstractCard c : ((AbstractPlayer)owner).hand.group)
        {
            reduceCost(c);
        }

        for(AbstractCard c : ((AbstractPlayer)owner).discardPile.group)
        {
            reduceCost(c);
        }
    }

    @Override
    public void onCardDraw(AbstractCard c)
    {
        reduceCost(c);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)
    {
        if(c.type == AbstractCard.CardType.ATTACK) resetCosts();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        resetCosts();
    }

    private void reduceCost(AbstractCard c)
    {
        for(Pair<AbstractCard, Integer> pair : reducedCost)
        {
            if(pair.getKey() == c) return;
        }

        if(c.type != AbstractCard.CardType.ATTACK) return;
        reducedCost.add(new Pair<>(c, c.costForTurn));
        c.modifyCostForTurn(-1 * amount);
    }

    private void resetCosts()
    {
        for(Pair<AbstractCard, Integer> pair : reducedCost)
        {
            pair.getKey().setCostForTurn(pair.getValue());
        }
    }
}
