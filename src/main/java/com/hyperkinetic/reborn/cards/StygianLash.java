package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.actions.EnterUndeathAction;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.ShroudPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class StygianLash extends CustomCard
{
    public static final String ID = "Reborn:StygianLash";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DMG = 7;
    private static final int WEAK = 1;

    public StygianLash()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                AbstractCard.CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);

        this.baseDamage = this.damage = DMG;
        this.baseMagicNumber = this.magicNumber = WEAK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                new WeakPower(m, this.magicNumber, false), this.magicNumber));

        int atks = 0;
        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
        {
            if(c.type == CardType.ATTACK) atks++;
        }
        if(atks == 3) AbstractDungeon.actionManager.addToBottom(new EnterUndeathAction(p));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new StygianLash();
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
