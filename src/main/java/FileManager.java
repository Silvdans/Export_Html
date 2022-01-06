
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {

    private final HashMap<String,String> map = new HashMap<>();

    public void createItemHashMap(){
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
            for(String string : map.values()){
                System.out.println(string);
            }
            for(String string : map.keySet()){
                System.out.println(string);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateHtml(){

    }
}