package com.hyperkinetic.reborn.cards;

import basemod.abstracts.CustomCard;
import com.hyperkinetic.reborn.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Veilstrike extends CustomCard
{
    public static final String ID = "Reborn:Veilstrike";
    private static final CardStrings card_strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = card_strings.NAME;
    public static final String DESCRIPTION = card_strings.DESCRIPTION;

    private static final int COST = 2;

    public Veilstrike()
    {
        super(ID, NAME, "Reborn/assets/cards/beta.png", COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.REBORN_BROWN,
                CardRarity.RARE, CardTarget.SELF_AND_ENEMY);

        this.isEthereal = true;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, m.currentHealth / 2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(p,
                new DamageInfo(p, p.currentHealth / 2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Veilstrike();
    }

    @Override
    public void upgrade()
    {
        if(!upgraded)
        {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = card_strings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
