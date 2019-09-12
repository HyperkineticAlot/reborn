package com.hyperkinetic.reborn.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.hyperkinetic.reborn.actions.DredgeAction;
import com.megacrit.cardcrawl.actions.common.MillAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IchorousMemento extends CustomRelic
{
    public static final String ID = "Reborn:IchorousMemento";

    public IchorousMemento()
    {
        super(ID, new Texture("Reborn/assets/relics/memento.png"), RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new IchorousMemento();
    }

    @Override
    public void atBattleStartPreDraw()
    {
        AbstractDungeon.actionManager.addToBottom(new MillAction(AbstractDungeon.player, AbstractDungeon.player, 3));
        AbstractDungeon.actionManager.addToBottom(new DredgeAction(1));
    }
}
