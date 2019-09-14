package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.EnterUndeathAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.ShroudPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Undeath extends CustomCard
{
    public static final String ID = "Reborn:Undeath";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 2;

    public Undeath()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.COMMON, CardTarget.SELF);

        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(upgraded)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ShroudPower(p, 3), 3));

        AbstractDungeon.actionManager.addToBottom(new EnterUndeathAction(p));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Undeath();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            this.rawDescription = card_strings.UPGRADE_DESCRIPTION;
        }
    }
}
