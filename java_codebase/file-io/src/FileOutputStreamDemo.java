import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {

	public static void main(String[] args) {
		try (FileOutputStream fos = new FileOutputStream("test.txt")){
			
			byte[] buffer = new byte[256];
			for(int i = 0; i < buffer.length; i++){
				buffer[i] = (byte) i;
			} 

			fos.write(buffer);										
			fos.flush();
		} catch (FileNotFoundException e) {
			System.out.println("The pathname does not exist.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Failed or interrupted when doing the I/O operations");
			e.printStackTrace();
		}
	}
}
