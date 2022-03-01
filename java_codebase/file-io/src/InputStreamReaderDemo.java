import java.io.*;

public class InputStreamReaderDemo {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("test.txt");
             InputStreamReader isr = new InputStreamReader(fis, "gb18030")) {

            char[] charBuffer = new char[256];
            int fileLen = isr.read(charBuffer);

            System.out.println(fileLen);
            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            System.out.println("The pathname does not exist.");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Character Encoding is not supported.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed or interrupted when doing the I/O operations");
            e.printStackTrace();
        }
    }
}
