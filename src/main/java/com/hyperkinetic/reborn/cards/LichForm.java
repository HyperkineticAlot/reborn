package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.StartOfTurnDredgePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;

public class LichForm extends CustomCard
{
    public static final String ID = "Reborn:LichForm";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 3;
    private static final int DREDGE = 3;

    public LichForm()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.REBORN_BROWN,
                CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = DREDGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new NoDrawPower(p)
                {
                    @Override
                    public void atEndOfTurn(boolean isPlayer)
                    {

                    }
                }));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new StartOfTurnDredgePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new LichForm();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}
