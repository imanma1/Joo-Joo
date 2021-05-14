package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.config.Config;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cont {

    @FXML
    ImageView imageView;

    public void heh() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);

        if (file != null){
            System.out.println(file.getParent());
            System.out.println(file.getName());
            System.out.println(file.getCanonicalPath());
//            FileChannel sourceChannel = null;
//            FileChannel destChannel = null;
//            try {
//                sourceChannel = new FileInputStream(file).getChannel();
//                destChannel = new FileOutputStream(new File(Config.getInstance().getImagesConfigPath()+"\\"+file.getName())).getChannel();
//                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
//            }finally{
//                sourceChannel.close();
//                destChannel.close();
//            }
            Image image = new Image(new FileInputStream(file));
            imageView.setImage(image);
        }
    }
}
