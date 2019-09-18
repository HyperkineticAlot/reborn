package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.EnterUndeathAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.RotPower;
import com.hyperkinetic.reborn.powers.ShroudPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DesperateContract extends CustomCard
{
    public static final String ID = "Reborn:DesperateContract";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int SHROUD = 30;

    public DesperateContract()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = SHROUD;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new ShroudPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new EnterUndeathAction(p));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new RotPower(p, 8), 8));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new DesperateContract();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(12);
        }
    }
}
