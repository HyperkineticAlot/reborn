package com.hyperkinetic.reborn.powers;

import com.hyperkinetic.reborn.actions.UndeathDiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UnnaturalEvolutionPower extends AbstractRebornPower
{
    public static final String P_ID = "Reborn:UnnaturalEvolutionPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(P_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int threshhold;
    private int played;

    public UnnaturalEvolutionPower(AbstractPlayer p, int amount, int threshhold)
    {
        this.owner = p;
        this.amount = amount;
        this.threshhold = threshhold;
        played = 0;

        this.ID = P_ID;
        this.name = NAME;

        this.updateDescription();
        this.loadRegion("beta.png");
    }

    @Override
    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.threshhold + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn()
    {
        played = 0;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)
    {
        if(c.type == AbstractCard.CardType.ATTACK) played++;
        if(played >= threshhold)
        {
            UndeathDiscoveryAction.upgradeOptions();
            played = 0;
        }
    }
}
