package com.hyperkinetic.reborn.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hyperkinetic.reborn.RebornMod;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbstractRebornPower extends AbstractPower
{
    @Override
    protected void loadRegion(String fileName) {
        System.out.println("Loading texture from file " + fileName + " for power of class " + this.getClass());

        Texture small = RebornMod.assets.loadImage("Reborn/assets/powers/small/" + fileName);
        Texture large = RebornMod.assets.loadImage("Reborn/assets/powers/large/" + fileName);
        System.out.println("small texture: " + small + " " + small.getWidth() + "x" + small.getHeight()
                + " large texture: " + large + " " + large.getWidth() + "x" + large.getHeight());

        this.region48 = new TextureAtlas.AtlasRegion(small, 0, 0, 48, 48);
        this.region128 = new TextureAtlas.AtlasRegion(large, 0, 0, 128, 128);

        System.out.println("resulting in region48: " + this.region48 + " region128: " + this.region128);
    }
}
