package stepDefinitions;

import io.cucumber.java.BeforeAll;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {

    @BeforeAll
    public static void before_all() throws IOException {
        String env = null;
        if(System.getenv("Environment") != null || System.getProperty("Environment") != null) {
            if(System.getenv("Environment") == null) {
                env = System.getProperty("Environment");
            } else if(System.getProperty("Environment") == null) {
                env = System.getenv("Environment");
            }
            Properties prop = new Properties();
            FileOutputStream fo = new FileOutputStream(
                    System.getProperty("user.dir") + "/src/test/java/resources/environment.properties");
            prop.setProperty("environment", env);
            prop.store(fo, null);
            fo.close();
        }
    }

}
