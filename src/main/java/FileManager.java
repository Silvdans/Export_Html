import java.awt.desktop.SystemEventListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {

    private final HashMap<String,String> map = new HashMap<>();
    public void cloneRepo(){
        try{
            Process process = Runtime.getRuntime().exec("rm -rf GO_Securi_Groupe3 ; git clone git@github.com:Silvdans/GO_Securi_Groupe_3.git");
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader (new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
            } else {
                System.exit(1);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
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