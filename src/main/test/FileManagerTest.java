import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileManagerTest
{
    private static FileManager fm;
    @BeforeAll
    static void setup() throws IOException {
        fm = new FileManager();
    }
    @Test
    public void createArrayAgentCreated() throws IOException {
        fm.createArrayAgents();
        boolean isListEmpty = true;
        if(fm.getListagents().size() > 0){
            isListEmpty = false;
        }
        assertFalse(isListEmpty);
    }

    @Test
    public void createHashMapAgent() throws IOException {
        fm.createItemHashMapObjet();
        boolean isMapEmpty = true;
        if(fm.getMap().size() > 0){
            isMapEmpty = false;
        }
        assertFalse(isMapEmpty);
    }
    @Test
    public void createHTPasswdAndHtaccess() throws IOException {
        fm.createhtpasswd();
        File htaccess = new File("html/.htaccess");
        File htpasswd = new File("html/.htpasswd");
        assertTrue(htaccess.exists());
        assertTrue(htpasswd.exists());
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        File index = new File("html");
        FileUtils.deleteDirectory(index);
    }

}
