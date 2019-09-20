package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.DredgeAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.ShroudPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SiftRot extends CustomCard
{
    public static final String ID = "Reborn:SiftRot";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DREDGE = 1;

    public SiftRot()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.UNCOMMON, CardTarget.NONE);

        this.baseMagicNumber = this.magicNumber = DREDGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int i = 0;
        while(i < p.drawPile.size() - 1 && p.drawPile.getNCardFromTop(i).type != CardType.SKILL) i++;

        AbstractDungeon.actionManager.addToBottom(new MillAction(p, p,i + 1));
        AbstractDungeon.actionManager.addToBottom(new DredgeAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new SiftRot();
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
