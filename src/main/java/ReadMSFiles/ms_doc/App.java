package ReadMSFiles.ms_doc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import doc.model.Token;
import doc.model.Tokens;
import read.ms.docx.CSVFormatter;
import read.ms.docx.FileWriter;
import read.ms.docx.TextReader;

/**
 * Hello world!
 *
 */
public class App 
{
	private List<Tokens> tokens = new ArrayList<Tokens>();
	private List<String> lvisKeys = new ArrayList<String>();
	public App() {
		initTokens();
	}
	private void initTokens() {
		//Age- 17m   
		Token age = new Token("age", "m", "m~d~y","-");
		Token sex = new Token("sex", "", "m~f","-");
		Token wt = new Token("wt", "kg", "k","-");
		Token ht = new Token("ht", "cm", "c","-");
		Token sat = new Token("sat", "%", "%","-");
		
		//Mitral valve: Normal, annulus – 1.3 cm, No  MR is present;
		Token mitralValve = new Token("mitral valve", "uk", ",~;",":");
		Token mitralAnnulus = new Token("annulus", "cm", "c~m","-");
		// Tricuspid valve: Normal, annulus- 1.6 cm, mild  TR w maxPG- 17mmHg; 
		Token tricuspidValve = new Token("tricuspid valve", "uk", ",~;",":");
		Token tricuspidAnnulus = new Token("annulus", "cm", "c~m","-");
		//Aortic valve: Normal; annulus – 1.1 cm; AV maxPG- 4 mmHg; 
		Token aorticValve = new Token("aortic valve", "uk", ",~;",":");
		Token aorticAnnulus = new Token("annulus", "cm", "c~m","-");
		//Pulmonary valve: Annulus- 1.0 cm; PV maxPG-  4 mmHg. 
		Token pulmonaryValve = new Token("pulmonary valve", "uk", ",~;",":");
		Token pulmonaryAnnulus = new Token("annulus", "cm", "c~m","-");


		//IVSs(MM)                   0.825 cm
		Token IVSs_mm = new Token("IVSs(mm)", "cm", "c~m","");
		//AoR Diam(MM)  1.5 cm
		Token AoR_Diam_mm = new Token("AoR Diam(MM)", "cm", "c~m","");
		//IVSd (MM)           0.570 cm
		Token IVSd_mm = new Token("IVSd (MM)", "cm", "c~m","");
		//FS(MM- Teich)	 36 %
		Token FS_mm = new Token("FS(MM- Teich)", "%", "%","");
		//EDV (MM-Teich)  18.0 m
		Token EDV_mm = new Token("EDV (MM-Teich)", "m", "c~m","");
		//lLA Diam (MM)          1.6 cm
		Token lLA_Diam_mm = new Token("lLA Diam (MM)", "cm", "c~m","");
		//LVIDd (MM)       2.30 cm
		Token LVIDd_mm = new Token("LVIDd (MM)", "cm", "c~m","");
		//LVIDs (MM)         1.45 cm
		Token LVIDs_mm = new Token("LVIDs (MM)", "cm", "c~m","");
		//EF(MM-Teich)                       68.0%
		Token ef_mm = new Token("EF(MM-Teich)", "%", "%","");
		//ESV (MM-Teich)-   5.0 ml
		Token ESV_mm = new Token("ESV (MM-Teich)", "ml", "m","-");
		//AV Cusp Sep     1.1 cm
		Token AV_Cusp_Sep_mm = new Token("AV Cusp Sep", "cm", "c~m","");
		//LVPWd (MM)    0.645 cm
		Token LVPWd_mm = new Token("LVPWd (MM)", "cm", "c~m","");
		//LVPWs (MM)      0.805 cm
		Token LVPWs_mm = new Token("LVPWs (MM)", "cm", "c~m","");


		Tokens ageTokens = new Tokens(age);
		Tokens sexTokens = new Tokens(sex);
		Tokens wtTokens = new Tokens(wt);
		Tokens htTokens = new Tokens(ht);
		Tokens satTokens = new Tokens(sat);

		Tokens IVSs_mmTokens = new Tokens(IVSs_mm);		
		Tokens AoR_Diam_mmTokens = new Tokens(AoR_Diam_mm);		
		Tokens IVSd_mmTokens = new Tokens(IVSd_mm);
		Tokens FS_mmTokens = new Tokens(FS_mm);		
		Tokens EDV_mmTokens = new Tokens(EDV_mm);
		Tokens lLA_Diam_mmTokens = new Tokens(lLA_Diam_mm);		
		Tokens LVIDd_mmTokens = new Tokens(LVIDd_mm);		
		Tokens LVIDs_mmTokens = new Tokens(LVIDs_mm);		
		Tokens ef_mmTokens = new Tokens(ef_mm);		
		Tokens ESV_mmTokens = new Tokens(ESV_mm);		
		Tokens AV_Cusp_Sep_mmTokens = new Tokens(AV_Cusp_Sep_mm);		
		Tokens LVPWd_mmTokens = new Tokens(LVPWd_mm);		
		Tokens LVPWs_mmTokens = new Tokens(LVPWs_mm);


		Tokens mitralValveTokens = new Tokens(mitralValve);
		mitralValveTokens.addSubToken(mitralAnnulus);
		Tokens tricuspidValveTokens = new Tokens(tricuspidValve);
		tricuspidValveTokens.addSubToken(tricuspidAnnulus);
		Tokens aorticValveTokens = new Tokens(aorticValve);
		aorticValveTokens.addSubToken(aorticAnnulus);
		Tokens pulmonaryValveTokens = new Tokens(pulmonaryValve);
		pulmonaryValveTokens.addSubToken(pulmonaryAnnulus);


		//List<Tokens> tokens = new ArrayList<Tokens>();
		tokens.add(ageTokens);
		tokens.add(sexTokens);
		tokens.add(wtTokens);
		tokens.add(htTokens);
		tokens.add(satTokens);

		tokens.add(tricuspidValveTokens);
		tokens.add(mitralValveTokens);
		tokens.add(aorticValveTokens);
		tokens.add(pulmonaryValveTokens);

		tokens.add(AoR_Diam_mmTokens);
		tokens.add(IVSs_mmTokens);		
		tokens.add(IVSd_mmTokens);
		tokens.add(FS_mmTokens);		
		tokens.add(EDV_mmTokens);		
		tokens.add(lLA_Diam_mmTokens);		
		tokens.add(LVIDd_mmTokens);		
		tokens.add(LVIDs_mmTokens);		
		tokens.add(ef_mmTokens);		
		tokens.add(ESV_mmTokens);		
		tokens.add(AV_Cusp_Sep_mmTokens);		
		tokens.add(LVPWd_mmTokens);		
		tokens.add(LVPWs_mmTokens);
		
		this.lvisKeys.add("AoR Diam(MM)");
		this.lvisKeys.add("IVSs(mm)");
		this.lvisKeys.add("IVSd (MM)");
		this.lvisKeys.add("lLA Diam (MM)");
		this.lvisKeys.add("LVIDd (MM)");
		this.lvisKeys.add("LVIDs (MM)");
		this.lvisKeys.add("AV Cusp Sep");
		this.lvisKeys.add("LVPWd (MM)");
		this.lvisKeys.add("LVPWs (MM)");
		this.lvisKeys.add("EDV (MM-Teich)");
		this.lvisKeys.add("ESV (MM-Teich)");
		
		//this.lvisKeys.add("FS(MM- Teich)");
		//this.lvisKeys.add("EF(MM-Teich)");
		
	}
	private List<File> getListOfFilesFromTheDir(String dirLoc) throws IOException {
		List<File> fileList = new ArrayList<File>();
		File dir = new File(dirLoc);
		String[] extensions = new String[] { "docx" };
		System.out.println("Getting all .docx and .jsp files in " + dir.getCanonicalPath() + " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			//System.out.println("file: " + file.getCanonicalPath());
			fileList.add(file);
		}	
		return fileList;
	}
	public static void main( String[] args )
	{
		App app = new App();
		TextReader tr = new TextReader();
		CSVFormatter csvFormatter = new CSVFormatter();
		FileWriter fwr = new FileWriter();
		String valveFile = "C:\\test-doc\\valve_test.csv";
		String lvsFile = "C:\\test-doc\\lvs_test.csv";
		//valve: age,sex,wt,ht,sat,valve_key,valve_value,annulus_value
		//fwr.writeToFileInAppenMode(filePath, data);
		//String valveFileHeader="id,age,sex,wt,ht,sat,ef,fs,valve_type,valve_status,annulus_in_cm";
		String valveFileHeader="id,age(day),sex,wt(kg),ht(mm),sat%,valve_type,valve_status,annulus_in_cm";
		String lvsFileHeader="id,age(day),sex,wt(kg),ht(mm),sat%,ef%,fs%,lvs_type,lvis_in_mm";
		//String valveFileDate="age,sex,wt,ht,sat,EF(MM-Teich),FS(MM- Teich),valve_key,valve_value,annulus_value";
		String valveFileDate="age,sex,wt,ht,sat,valve_key,valve_value,annulus_value";
		String vlvPrfxStr="id,age,sex,wt,ht,sat";
		String lvsPrfxStr="id,age,sex,wt,ht,sat,EF(MM-Teich),FS(MM- Teich)";
		//LV_STUDY: age,sex,wt,ht,sat,ef,esv,lv_key,lv_value
		
		
		try {
			FileUtils.writeStringToFile(new File(valveFile), valveFileHeader+"\r\n", StandardCharsets.UTF_8, false);
			FileUtils.writeStringToFile(new File(lvsFile), lvsFileHeader+"\r\n", StandardCharsets.UTF_8, false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			List<File> files = app.getListOfFilesFromTheDir("C:\\test-doc\\");
			int index =1;
			for (File file : files) {
				System.out.println("index # "+index +" -> file: " + file.getCanonicalPath());
				String input= tr.getTextFromFile(file.getCanonicalPath());
				//System.out.println(input);
				for(Tokens t: app.tokens) {
					t.updateToken(input);
				}
				System.out.println(app.tokens);
				//List<String> csvDataList = csvFormatter.formatedString(valveFileDate, app.tokens,index);
				String vlvPrfx=csvFormatter.formatedStringPrefix(vlvPrfxStr, app.tokens, index);
				//System.out.println(vlvPrfx);
				
				List<String> csvValveDataList = csvFormatter.formatedValveString("valve_key,valve_value,annulus_value",app.tokens, vlvPrfx);
				System.out.println("csvValveDataList");
				System.out.println(csvValveDataList);
				for(String csvData:csvValveDataList) {
					fwr.writeToFileInAppenMode(valveFile, csvData.trim().substring(0, csvData.length()-1));
				}
				
				String lvsPrfx=csvFormatter.formatedStringPrefix(lvsPrfxStr, app.tokens, index);
				//System.out.println(lvsPrfx);
				
				List<String> csvLvsDataList = csvFormatter.formatedLvsString("lv_key,lv_value", app.tokens, lvsPrfx, app.lvisKeys);
				System.out.println("csvLvsDataList");
				System.out.println(csvLvsDataList);
				for(String csvData:csvLvsDataList) {
					fwr.writeToFileInAppenMode(lvsFile, csvData.trim().substring(0, csvData.length()-1));
				}
				/*
				List<String> csvDataList = csvFormatter.formatedString(valveFileDate, app.tokens,index);
				System.out.println(csvDataList);
				for(String csvData:csvDataList) {
					fwr.writeToFileInAppenMode(valveFile, csvData.trim().substring(0, csvData.length()-1));
				}
				*/
				
				//fileList.add(file);
				index++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println( "Hello World!" );
	}
}
