import java.io.*;

public class ReadWriteWorker {
    public static void main(String[] args) {
        (new Reader()).start();
        (new Writer()).start();
    }

    private static class Reader extends Thread {
        @Override
        public void run() {
            System.out.println("Reader starts");

            File file = new File("./test.txt");
            try {
                for (int i = 0; i < 10_000; i++) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Reader ends");
        }
    }

    private static class Writer extends Thread {
        @Override
        public void run() {
            System.out.println("Writer starts");

            File file = new File("./test.txt");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                for (int i = 0; i < 10_000; i++) {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    for (int j = 0; j < 10_000; j++) {
                        bw.write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                        bw.flush();
                    }
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Writer ends");
        }
    }
}


