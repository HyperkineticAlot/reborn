package com.hyperkinetic.reborn.actions;

import com.hyperkinetic.reborn.powers.ModulatePower;
import com.hyperkinetic.reborn.powers.UndeathPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class EnterUndeathAction extends AbstractGameAction
{
    private static TutorialStrings tutorial_strings = CardCrawlGame.languagePack.getTutorialString("Reborn:Tutorials");

    public EnterUndeathAction(AbstractCreature p)
    {
        this.target = p;
        this.startDuration = this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update()
    {
        if(this.duration == this.startDuration)
        {
            if(this.target.hasPower("Reborn:UndeathPower"))
            {
                isDone = true;
            }

            else if(!this.target.hasPower("Reborn:ShroudPower"))
            {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                                                                 AbstractDungeon.player.dialogY,
                                                                 3.0F, tutorial_strings.TEXT[0], true));
                this.isDone = true;
            }

            else
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, target, new UndeathPower((AbstractPlayer)target)));
                if(this.target.hasPower("Reborn:ModulatePower"))
                {
                    this.target.getPower("Reborn:ModulatePower").flash();
                    ((ModulatePower)this.target.getPower("Reborn:ModulatePower")).modulate();
                }

                this.isDone = true;
            }
        }

        tickDuration();
    }
}
