package read.ms.docx;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class FileWriter {
	
	public void writeToFileInAppenMode(String filePath, String data){
		File file = new File(filePath);
		try {
			//FileUtils.writeStringToFile(file, data, true);
			FileUtils.writeStringToFile(file, data+"\r\n", StandardCharsets.UTF_8, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
