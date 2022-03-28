import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileManager extends Thread{

    private final HashMap<String,String> map;
    private ArrayList<String> listagents;

    public FileManager() throws IOException{
        listagents = new ArrayList<>();
        map = new HashMap<>();
        Files.createDirectories(Paths.get("html"));
        this.start();
    }
    @Override
    public void run() {
        createArrayAgents();
        createItemHashMapObjet();
        try {
            createhtpasswd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFirstName(String agent) throws IOException {
        return Files.readAllLines(Paths.get("GO_Securi_Groupe_3/"+agent+".txt")).get(1);
    }
    public String getLastName(String agent) throws IOException {
        return Files.readAllLines(Paths.get("GO_Securi_Groupe_3/"+agent+".txt")).get(0);
    }
    public String getImageName(String agent){
        return "GO_Securi_Groupe_3/"+agent+".jpg";
    }
    public void createItemHashMapObjet(){
        try{
            File file = new File("GO_Securi_Groupe_3/liste.txt");
            FileReader fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> list = new ArrayList<>();

            String line;
            while((line = br.readLine()) != null)
            {
                list.add(line);
            }
            fr.close();
            for(String string : list){
                String[] res = string.split("\t");
                map.put(res[0],res[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getListObjectAffectedToAgent(String agent){
        File fileagents = new File("GO_Securi_Groupe_3/"+agent+".txt");
        ArrayList<String> items = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(fileagents);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(this.map.keySet().contains(line)){
                    items.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }
    public void createhtpasswd() throws IOException {
        HashMap<String,String> userPasswd = new HashMap<>();
        for (String agent : this.listagents){
            String password = Files.readAllLines(Paths.get("GO_Securi_Groupe_3/"+agent+".txt")).get(3);
            userPasswd.put(agent,password);
        }
        File htaccess = new File("html/.htaccess");
        PrintWriter fileOut = new PrintWriter(htaccess);
        fileOut.println("AuthType Basic\n" +
                "AuthName \"Restricted Content\"\n" +
                "AuthUserFile /usr/local/apache2/htdocs/.htpasswd\n" +
                "Require valid-user");
        fileOut.close();
        File htpassw = new File("html/.htpasswd");
        StringBuilder output = new StringBuilder();
        PrintWriter fileOutpasswd = new PrintWriter(htpassw);
        for(String agent : this.listagents) {
            Process process = Runtime.getRuntime().exec("htpasswd -nbm " + agent + " " + userPasswd.get(agent));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            line = reader.readLine();
            output.append(line).append("\n");
        }
        fileOutpasswd.print(output);
        fileOutpasswd.close();
    }
    public void createArrayAgents(){
        try{
            File file = new File("GO_Securi_Groupe_3/agents.txt");

            FileReader fr2 = new FileReader(file);
            BufferedReader br = new BufferedReader(fr2);
            String line;
            while((line = br.readLine()) != null)
            {
                this.listagents.add(line);
            }
            fr2.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getListagents() {
        return this.listagents;
    }

    public void setListagents(ArrayList<String> listagents) {
        this.listagents = listagents;
    }

    public HashMap<String, String> getMap() {
        return this.map;
    }
}