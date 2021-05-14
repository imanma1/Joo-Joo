package sample.apps.image.controller;

import javafx.stage.FileChooser;
import sample.apps.image.listener.SelectImageListener;
import sample.config.Config;
import sample.controller.Controller;

import java.io.*;
import java.nio.channels.FileChannel;

public class ImageController extends Controller {

    public void saveImage(File file) throws IOException {
        if (file != null){
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try {
                sourceChannel = new FileInputStream(file).getChannel();
                destChannel = new FileOutputStream(new File(Config.getInstance().getImagesConfigPath()+"\\"+file.getName())).getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            }finally{
                sourceChannel.close();
                destChannel.close();
            }
        }
    }

    public String uploadImage(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            try {
                saveImage(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file.getName();
        }
        return "";
    }
}
