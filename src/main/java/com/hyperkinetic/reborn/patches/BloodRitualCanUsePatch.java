package com.hyperkinetic.reborn.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

@SpirePatch(
        clz = AbstractCard.class,
        method = "hasEnoughEnergy"
)
public class BloodRitualCanUsePatch
{
    public static ExprEditor Instrument()
    {
        return new ExprEditor()
        {
            @Override
            public void edit(FieldAccess f) throws CannotCompileException
            {
                if(f.getClassName().equals(AbstractCard.class.getName()) && f.getFieldName().equals("costForTurn"))
                {
                    StringBuilder s = new StringBuilder();
                    s.append("{ $_ = ");
                    s.append(AbstractDungeon.class.getName());
                    s.append(".player.hasPower(\"Reborn:BloodRitualPower\") ? ");
                    s.append(" 0 : this.costForTurn; }");
                    f.replace(s.toString());
                }
            }
        };
    }
}
