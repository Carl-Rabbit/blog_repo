import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedInputStreamDemo {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("test.txt");
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            byte[] buffer = new byte[256];
            int byteNum = bis.read(buffer);

            System.out.printf("byte num: %d%n", byteNum);
            for (int i = 0; i < byteNum; i++) {
                System.out.printf("%02X ", buffer[i]);
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("The pathname does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed or interrupted when doing the I/O operations");
            e.printStackTrace();
        }
    }
}
