package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.ShroudPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShroudedStrike extends CustomCard
{
    public static final String ID = "Reborn:ShroudedStrike";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DMG = 6;
    private static final int SHROUD = 7;

    public ShroudedStrike()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);

        this.tags.add(CardTags.STRIKE);
        this.baseDamage = this.damage = DMG;
        this.baseMagicNumber = this.magicNumber = SHROUD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new ShroudPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new ShroudedStrike();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(3);
        }
    }
}
