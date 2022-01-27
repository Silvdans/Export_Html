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

        File fileindex = new File("html/index.html");

        try {
            fileOut = new PrintWriter(fileindex);
            // Ouverture des TAGS HTML
            fileOut.println("<html>");
            fileOut.println("<head>");
            fileOut.println("</head>");
            fileOut.println("<body>");

            //PRINT de la list d'agents
            for (String string : this.agents) {
                System.out.println(string);
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
            File fileindex = new File("html/"+string+".html");
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
            fileOut.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Document</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "    <!-- back pour retourner à la liste des agents -->\n" +
                    "\n" +
                    "    <a href=\"\" class=\"retour\"><img src=\"back.png\" alt=\"\"> <p>Retour à la liste des agents</p></a>\n" +
                    "\n" +
                    "    <!-- Main pour l'affihage des données -->\n" +
                    "    <section id=\"main\">\n" +
                    "\n" +
                    "        <div class=\"head\">\n" +
                    "            <div class=\"identification\"> NOM </div>\n" +
                    "            <div class=\"photo\"></div>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <!-- Listes des items -->\n" +
                    "        <div class=\"objets\">");

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
                fileOut.println("<div>");
                fileOut.println("<label for=\"coding\">"+value+"</label>");
                fileOut.println("<input type=\"checkbox\" id=\"coding\" name=\"interest\" value=\"coding\" onclick=\"return false;\""+check+">");
                fileOut.println("</div>");

            }
            // Fermeture des TAGS HTML
            fileOut.println("</div>");
            fileOut.println("</section>");
            fileOut.println("</body>");
            fileOut.println("</html>");
            fileOut.close();
        }
    }
}


