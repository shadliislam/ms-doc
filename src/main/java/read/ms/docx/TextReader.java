package read.ms.docx;

import java.io.FileInputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class TextReader {
	
	public String getTextFromFile(String fileName) {
		 try {
			   FileInputStream fis = new FileInputStream(fileName);//"C:\\test-doc\\FORMAT.docx"
			   XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			   XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			   //System.out.println(extractor.getText());
			   return extractor.getText();
			} catch(Exception ex) {
			    ex.printStackTrace();
			}
		 return "Cannot Read File! or Empty File! File name : "+fileName;
	}
	
	public static void main(String[] args) {
	 try {
		   FileInputStream fis = new FileInputStream("C:\\test-doc\\FORMAT.docx");
		   XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
		   XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
		   System.out.println(extractor.getText());
		} catch(Exception ex) {
		    ex.printStackTrace();
		}
 }

}