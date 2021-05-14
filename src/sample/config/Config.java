package sample.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config instance;

    public static Config getInstance() {
        if (instance == null){
            instance = new Config();
        }
        return instance;
    }

    private static final String MAIN_CONFIG_PATH = "resources\\config\\mainConfig";
    private String DBPath;
    private String imagesConfigPath;

    public Config()  {
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(MAIN_CONFIG_PATH);
        properties.load(fileReader);
        DBPath = properties.getProperty("DBPath");
        imagesConfigPath = properties.getProperty("imagesConfigPath");
    }

    public String getDBPath() {
        return DBPath;
    }

    public String getImagesConfigPath() {
        return imagesConfigPath;
    }
}
