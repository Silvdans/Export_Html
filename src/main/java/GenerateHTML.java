import java.awt.List;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class GenerateHTML {

    private FileManager fileManager;
    private ArrayList<String> agents;
    private PrintWriter fileOut;


    public GenerateHTML(){
        this.fileManager = new FileManager();
        this.agents = fileManager.getListagents();
    }

    public void createIndexPage() {

        fileManager.createArrayAgents();
        File fileindex = new File("index.html");

        try {
            if (!fileindex.exists()) {
                fileindex.createNewFile();
            }
        }catch (IOException e) {
            // Print the exception
            System.out.print(e.getMessage());
        }
        try {
            fileOut = new PrintWriter(fileindex);
            // Ouverture des TAGS HTML
            fileOut.println("<html>");
            fileOut.println("<head>");
            fileOut.println("</head>");
            fileOut.println("<body>");

            //PRINT de la list d'agents
            for (String string : this.agents) {
                fileOut.println("   <br>");
                fileOut.println("<a href="+string+".html>"+string+"</a>");
                fileOut.println("   <br>");
            }
            // Fermeture des TAGS HTML
            fileOut.println("</body>");
            fileOut.println("</html>");

            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public void createHtmlForEachAgents() {
        FileManager fileManager = new FileManager();
        ArrayList<String >items;
        for (String string : this.agents) {
            //création du fichier s'il n'existe pas
            File fileindex = new File("HTML/"+string+".html");
            try {
                fileOut = new PrintWriter(fileindex);
                if (!fileindex.exists()) {
                    fileindex.createNewFile();
                }
            }catch (IOException e) {
                // Print the exception
                System.out.print(e.getMessage());
            }
            // Ouverture tags
            fileOut.println("<html>");
            fileOut.println("<head>");
            fileOut.println("</head>");
            fileOut.println("<body>");

            //Trouver le fichier

            items = fileManager.getListObjectAffectedToAgent(string);
            String check;
            for (String objet : fileManager.getMap().keySet()){
                String value = fileManager.getMap().get(objet);
                if(items.contains(objet)) {
                    check = "checked";
                }
                else{
                    check ="";
                }
                fileOut.println("<label for=\"coding\">"+value+"</label>");
                fileOut.println("<input type=\"checkbox\" id=\"coding\" name=\"interest\" value=\"coding\" onclick=\"return false;\""+check+">");
            }
            // Fermeture des TAGS HTML
            fileOut.println("</body>");
            fileOut.println("</html>");
            fileOut.close();
        }
    }
}


