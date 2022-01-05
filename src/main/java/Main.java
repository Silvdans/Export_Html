import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("git clone git@github.com:Silvdans/GO_Securi_Groupe_3.git");
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader (new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Success!");
            System.out.println(output);
            System.exit(0);
        } else {
            //abnormal...
        }


    }
}


