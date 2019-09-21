package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.hyperkinetic.reborn.powers.ShroudPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class MortalDuality extends CustomCard
{
    public static final String ID = "Reborn:MortalDuality";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 1;
    private static final int DMG = 6;
    private static final int VULN = 1;
    private static final int TIMES = 2;
    private static final int UP_TIMES = 3;

    public boolean isCopy;

    public MortalDuality()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = DMG;
        this.baseMagicNumber = this.magicNumber = VULN;
        isCopy = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int timesToAttack = 1;
        if(p.hasPower("Reborn:UndeathPower"))
        {
            if(upgraded) timesToAttack = UP_TIMES;
            else timesToAttack = TIMES;
        }

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
                this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                new VulnerablePower(m, this.magicNumber, false), this.magicNumber));

        if(!isCopy)
        {
            for(int i = 1; i < timesToAttack; i++)
            {
                AbstractCard temp = this.makeSameInstanceOf();
                ((MortalDuality)temp).isCopy = true;
                temp.current_x = this.current_x;
                temp.current_y = this.current_y;
                temp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                temp.target_y = (float)Settings.HEIGHT / 2.0F;
                temp.calculateCardDamage(m);
                temp.purgeOnUse = true;

                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(temp, m, this.energyOnUse, true));
            }
        }
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new MortalDuality();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            rawDescription = card_strings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
