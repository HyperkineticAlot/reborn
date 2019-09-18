package com.hyperkinetic.reborn.util;

import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class CardEffectDaemon
{
    protected AbstractCard card;

    public CardEffectDaemon(AbstractCard c)
    {
        card = c;
    }

    public void atEndOfTurn() {}

    public void atStartOfTurn() {}
}
