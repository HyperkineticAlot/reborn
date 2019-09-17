package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Infection extends CustomCard
{
    public static final String ID = "Reborn:Infection";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int hpLoss = 1;

    public Infection()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", -2, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS,
                CardRarity.COMMON, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(this.dontTriggerOnUseCard)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(p,
                    new DamageInfo(p, hpLoss, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard()
    {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Infection();
    }

    @Override
    public void upgrade()
    {

    }
}
