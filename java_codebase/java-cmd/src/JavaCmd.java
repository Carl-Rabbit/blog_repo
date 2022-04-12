import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaCmd {
    public static void main(String[] args) {
        String command = "cmd /c dir";
        long time = -System.nanoTime();
        String res = execCmd(command);
        time += System.nanoTime();
        System.out.println(res);
        System.out.println("Return in " + (time / 1000_000) + " ms");
    }

    private static String execCmd(String command) {
        StringBuilder sb = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB2312"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
