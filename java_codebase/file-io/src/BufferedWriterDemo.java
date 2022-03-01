import java.io.*;

public class BufferedWriterDemo {

	public static void main(String[] args) {
		try (FileOutputStream fos = new FileOutputStream("test.txt");
		     OutputStreamWriter osw = new OutputStreamWriter(fos, "gb18030");
		     BufferedWriter bWriter = new BufferedWriter(osw)){

			bWriter.write("你好！\n");
			bWriter.flush();
			
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
