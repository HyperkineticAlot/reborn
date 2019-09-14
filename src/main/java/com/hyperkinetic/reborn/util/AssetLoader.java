package com.hyperkinetic.reborn.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class AssetLoader
{
    private AssetManager assets = new AssetManager();

    //private static HashMap<String, Texture> textures = new HashMap<>();
    //public static final Logger logger = LogManager.getLogger(TextureLoader.class.getName());

    public Texture loadImage(String imagePath)
    {
        if(!assets.isLoaded(imagePath))
        {
            TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
            param.minFilter = Texture.TextureFilter.Linear;
            param.magFilter = Texture.TextureFilter.Linear;
            assets.load(imagePath, Texture.class, param);

            try
            {
                assets.finishLoadingAsset(imagePath);
            }
            catch(GdxRuntimeException e)
            {
                e.printStackTrace();
                return null;
            }
        }

        return assets.get(imagePath, Texture.class);

        /*if(textures.get(imagePath) == null)
        {
            try
            {
                logger.info("RebornMod | Loading texture: " + imagePath);
                Texture texture = new Texture(imagePath);
                texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                textures.put(imagePath, texture);
            }
            catch(GdxRuntimeException e)
            {
                logger.error("Could not find texture: " + imagePath);
                e.printStackTrace();
                return null;
            }
        }

        return textures.get(imagePath);*/
    }
}
