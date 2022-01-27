import java.io.*;
import java.util.*;

public class GenerateHTML {

    FileManager fileManager = new FileManager();
    PrintWriter fileOut;

    public void index() {

        //Variable
        fileManager.createArrayAgents();
        Scanner scanner = new Scanner(System.in);
        Scanner fileIn; //input file connection
        //HTML file connection
        String line = null; // a line from the input file

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
            ArrayList<String> agents = fileManager.getListagents();
            for (String string : agents) {
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


    public void agents() {
        FileManager fileManager = new FileManager();
        ArrayList<String> agents = fileManager.getListagents();

        for (String string : agents) {

            File fileindex = new File(string+".html");

            try {
                if (!fileindex.exists()) {
                    fileindex.createNewFile();
                }
            }catch (IOException e) {
                // Print the exception
                System.out.print(e.getMessage());
            }
            fileOut = new PrintWriter(fileindex);

        }

    }
}


