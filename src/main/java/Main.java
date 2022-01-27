
public class Main {
    public static void main(String[] args){

        GenerateHTML generateHTML= new GenerateHTML();
        generateHTML.createIndexPage();
        generateHTML.createHtmlForEachAgents();

        System.exit(0);
    }
}


