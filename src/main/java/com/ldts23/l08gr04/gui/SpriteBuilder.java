package com.ldts23.l08gr04.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteBuilder {

    private String resourcePath = "./src/main/resources/pngs/";
    private Map<String , BufferedImage> cache;

    public SpriteBuilder() throws IOException {
        super();
        this.cache = new HashMap<>();
    }

    public boolean isInCache(String name) {
        return cache.containsKey(name);
    }

    public BufferedImage getFromCache(String name) {
        return cache.get(name);
    }

    public void setToCache(String name, BufferedImage image) {
        cache.put(name, image);
    }


    public BufferedImage loadImage(String filename) {
        if(isInCache(filename)) return cache.get(filename);

        BufferedImage image = null;
        try {
            File file = new File(resourcePath + filename + ".png");
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Error reading image!" + filename);
        }
        setToCache(filename, image);
        return image;
    }

}
