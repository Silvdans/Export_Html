import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenerateHTMLTest {
    @Test
    public void testGenerateHtmlIndex() throws IOException {
        GenerateHTML gen = new GenerateHTML();
        gen.createIndexPage();
        File file = new File("html/index.html");
        assertTrue(file.exists());
    }
    @Test
    public void testGenerateAgent() throws IOException{
        GenerateHTML gen = new GenerateHTML();
        String agent = gen.getFileManager().getListagents().get(1);
        File file = new File("html/"+agent+"/"+agent+".html");
        assertTrue(file.exists());
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        File index = new File("html");
        FileUtils.deleteDirectory(index);
    }
}
