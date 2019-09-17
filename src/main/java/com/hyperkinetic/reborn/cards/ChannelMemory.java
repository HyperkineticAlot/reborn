package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.DredgeAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChannelMemory extends CustomCard
{
    public static final String ID = "Reborn:ChannelMemory";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int DREDGE = 2;

    public ChannelMemory()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.REBORN_BROWN,
                CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseBlock = this.block = BLOCK;
        this.baseMagicNumber = this.magicNumber = DREDGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new DredgeAction(this.magicNumber, p.hasPower("Reborn:UndeathPower")));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new ChannelMemory();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeBlock(4);
            upgradeMagicNumber(1);
        }
    }
}
