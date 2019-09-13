package com.hyperkinetic.reborn.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbstractRebornPower extends AbstractPower
{
    @Override
    protected void loadRegion(String fileName) {
        this.region48 = new TextureAtlas.AtlasRegion(
                ImageMaster.loadImage("Reborn/assets/powers/small/" + fileName),
                0, 0, 48, 48);
        this.region128 = new TextureAtlas.AtlasRegion(
                ImageMaster.loadImage("Reborn/assets/powers/large/" + fileName),
                0, 0, 128, 128);
    }
}
