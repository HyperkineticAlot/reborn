package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.DredgeAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GleanFromGore extends CustomCard
{
    public static final String ID = "Reborn:GleanFromGore";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 0;
    private static final int DREDGE = 2;

    public GleanFromGore()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.COMMON, CardTarget.NONE);

        this.baseMagicNumber = this.magicNumber = DREDGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DredgeAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Infection(), 1, false, true));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new GleanFromGore();
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
