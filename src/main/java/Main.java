import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        GenerateHTML generateHTML= new GenerateHTML();
        generateHTML.createIndexPage();
        generateHTML.createHtmlForEachAgents();

        System.exit(0);
    }
}


