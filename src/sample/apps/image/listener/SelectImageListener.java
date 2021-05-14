package sample.apps.image.listener;

import sample.apps.image.controller.ImageController;

import java.io.File;
import java.io.IOException;

public class SelectImageListener {
    private final ImageController controller;

    public SelectImageListener(){
        controller = new ImageController();
    }

    public void saveImage(File file){
        try {
            controller.saveImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
