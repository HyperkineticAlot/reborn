package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MillAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

public class BileBolt extends CustomCard
{
    public static final String ID = "Reborn:BileBolt";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DMG = 4;
    private static final int MILL = 4;

    public BileBolt()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = DMG;
        this.baseMagicNumber = this.magicNumber = MILL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new MillAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new EnergizedPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new BileBolt();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
        }
    }
}
