package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.WhirlGainEnergyAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Whirl extends CustomCard
{
    public static final String ID = "Reborn:Whirl";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DMG = 7;

    public Whirl()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);

        this.baseDamage = this.damage = DMG;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new WhirlGainEnergyAction());
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Whirl();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            upgradeDamage(3);
        }
    }
}
