package com.hyperkinetic.reborn.actions;

import com.hyperkinetic.reborn.cards.RewindLifestream;
import com.hyperkinetic.reborn.powers.UndeathPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAndEnableControlsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;

public class RewindLifestreamAction extends AbstractGameAction
{
    public static int startCombatHP = AbstractDungeon.player.startingMaxHP;

    public RewindLifestreamAction()
    {

    }

    @Override
    public void update()
    {
        AbstractDungeon.player.heal(startCombatHP - AbstractDungeon.player.currentHealth);

        AbstractRoom room = AbstractDungeon.getCurrRoom();

        //CardCrawlGame.music.silenceTempBgmInstantly();
        //CardCrawlGame.music.silenceBGMInstantly();

        AbstractDungeon.fadeIn();
        AbstractDungeon.player.resetControllerValues();
        AbstractDungeon.effectList.clear();
        AbstractDungeon.topLevelEffects.clear();
        AbstractDungeon.topLevelEffectsQueue.clear();
        AbstractDungeon.effectsQueue.clear();
        AbstractDungeon.dungeonMapScreen.dismissable = true;
        AbstractDungeon.dungeonMapScreen.map.legend.isLegendHighlighted = false;

        AbstractDungeon.player.orbs.clear();
        AbstractDungeon.player.animX = 0.0F;
        AbstractDungeon.player.animY = 0.0F;
        AbstractDungeon.player.hideHealthBar();
        AbstractDungeon.player.hand.clear();
        AbstractDungeon.player.discardPile.clear();
        AbstractDungeon.player.drawPile.clear();
        AbstractDungeon.player.exhaustPile.clear();
        AbstractDungeon.player.limbo.clear();
        AbstractDungeon.player.loseBlock(true);
        AbstractDungeon.player.damagedThisCombat = 0;
        GameActionManager.turn = 1;

        for(AbstractPower power : AbstractDungeon.player.powers)
        {
            if(power.type == AbstractPower.PowerType.DEBUFF || power.ID.equals(UndeathPower.P_ID))
                AbstractDungeon.player.powers.remove(power);
        }

        AbstractDungeon.actionManager.monsterQueue.clear();
        AbstractDungeon.actionManager.monsterAttacksQueued = true;

        for(AbstractRelic r : AbstractDungeon.player.relics)
        {
            r.onEnterRoom(room);
        }

        AbstractDungeon.actionManager.clear();

        room.monsters = MonsterHelper.getEncounter(AbstractDungeon.lastCombatMetricKey);
        room.monsters.init();

        for (AbstractMonster m : room.monsters.monsters)
        {
            m.showHealthBar();
            m.usePreBattleAction();
            m.useUniversalPreBattleAction();
        }

        AbstractDungeon.player.preBattlePrep();

        AbstractDungeon.actionManager.turnHasEnded = true;
        AbstractDungeon.topLevelEffects.add(new BattleStartEffect(false));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAndEnableControlsAction(AbstractDungeon.player.energy.energyMaster));

        AbstractDungeon.player.applyStartOfCombatPreDrawLogic();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.gameHandSize));

        AbstractDungeon.actionManager.addToBottom(new EnableEndTurnButtonAction());
        AbstractDungeon.overlayMenu.showCombatPanels();
        AbstractDungeon.player.applyStartOfCombatLogic();
        AbstractDungeon.player.applyStartOfTurnRelics();
        AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
        AbstractDungeon.player.applyStartOfTurnCards();
        AbstractDungeon.player.applyStartOfTurnPowers();
        AbstractDungeon.player.applyStartOfTurnOrbs();

        for(AbstractCard card : AbstractDungeon.player.drawPile.group)
        {
            if(card.cardID.equals(RewindLifestream.ID))
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card,
                        AbstractDungeon.player.drawPile));
        }
        for(AbstractCard card : AbstractDungeon.player.discardPile.group)
        {
            if(card.cardID.equals(RewindLifestream.ID))
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card,
                        AbstractDungeon.player.discardPile));
        }
        for(AbstractCard card : AbstractDungeon.player.hand.group)
        {
            if(card.cardID.equals(RewindLifestream.ID))
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card,
                        AbstractDungeon.player.hand));
        }

        isDone = true;
    }
}
