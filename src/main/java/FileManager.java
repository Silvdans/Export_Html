
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileManager {

    private final HashMap<String,String> map;
    private ArrayList<String> listagents;

    public FileManager(){
        listagents = new ArrayList<>();
        map = new HashMap<>();
        createArrayAgents();
        createItemHashMapObjet();
    }
    public String getFirstName(String agent) throws IOException {
        return Files.readAllLines(Paths.get("html/GO_Securi_Groupe_3/"+agent+".txt")).get(1);
    }
    public String getLastName(String agent) throws IOException {
        return Files.readAllLines(Paths.get("html/GO_Securi_Groupe_3/"+agent+".txt")).get(0);
    }
    public String getImageName(String agent){
        return "html/GO_Securi_Groupe_3/"+agent+".jpg";
    }
    public void createItemHashMapObjet(){
        try{
            File file = new File("html/GO_Securi_Groupe_3/liste.txt");
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
        File fileagents = new File("html/GO_Securi_Groupe_3/"+agent+".txt");
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
    public void createArrayAgents(){
        try{
            File file = new File("html/GO_Securi_Groupe_3/agents.txt");

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