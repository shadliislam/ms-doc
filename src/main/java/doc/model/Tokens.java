package doc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import read.ms.docx.TextReader;

public class Tokens {
	private String subTokenStr;
	public Token token;
	public List<Token> subTokens = new ArrayList<Token>();
	//public List<Token> results = new ArrayList<Token>();
	
	
	public Tokens(Token t) {
		this.token=t;
		/*
		//Age- 17m   
		Token age = new Token("age", "m", "m","-");
		//Mitral valve: Normal, annulus – 1.3 cm, No  MR is present;
		Token mitralValve = new Token("mitral valve", "uk", ",",":");
		Token annulus = new Token("annulus", "cm", "c","-");
		//IVSs(MM)                   0.825 cm
		Token IVSs_mm = new Token("ivss(mm)", "cm", "c","");
		
		this.tokens.add(age);
		this.tokens.add(mitralValve);
		this.tokens.add(annulus);
		this.tokens.add(IVSs_mm);
		*/
		
	}

	public void updateToken(String inText){
		String pText = inText.toLowerCase().replaceAll("\\(", "_").replaceAll("\\)", "_");
		this.token=getValue(pText, this.token);
		for(Token t: this.subTokens) {
			try {
				t=getValue(this.subTokenStr, t);
			}catch(Exception e) {
				System.err.println(e);
			}
		}
		
	}
	public Token getValue(String pText, Token t) {
		String val="-";
		if(pText.contains(t.key.replaceAll("\\(", "_").replaceAll("\\)", "_").toLowerCase())) {
			String[] tmp = pText.split(t.key.replaceAll("\\(", "_").replaceAll("\\)", "_").toLowerCase());
			if(tmp.length>0) {
				this.subTokenStr=tmp[1];
				String txt = tmp[1].replaceAll(" ", "");
				//System.out.println(txt);
				int stIndex = (t.separator.length()>0)?t.separator.length():0;
				String actEndTag = ",";
				int min = 9999;
				for(String etg:t.endTags) {
					if(min>txt.indexOf(etg)) {
						min = txt.indexOf(etg);
						actEndTag=etg;
					}
				}
				val = txt.substring(stIndex, txt.indexOf(actEndTag));
				val = val.endsWith(".")?val.substring(0, val.length()-1):val;
				if(t.key.equalsIgnoreCase("sex")) {
					t.setUnit(actEndTag);
					val = txt.substring(stIndex, txt.indexOf(actEndTag)+1);
				}
				else if(t.key.equalsIgnoreCase("age")) {
					int factor = "m".equalsIgnoreCase(actEndTag)?30:1;
					factor = "y".equalsIgnoreCase(actEndTag)?365:1;
					float ageV = Float.parseFloat(val)*(factor);
					t.setUnit("d");
					val=ageV+"";
				}
				
				else if(t.key.equalsIgnoreCase("annulus")||t.key.toLowerCase().contains("mm")||t.key.toLowerCase().contains("av cusp"))
				{
					//t.setUnit(actEndTag.equalsIgnoreCase("c")?"cm":"mm");
					//System.out.println(val);
					int factor = "c".equalsIgnoreCase(actEndTag)?10:1;
					float ageV = Float.parseFloat(val)*(factor);
					t.setUnit("c".equalsIgnoreCase(actEndTag)?"m":t.getUnit());
					val=String.format("%.3f", ageV);
					//System.out.println(val);
				}
				//System.out.println(val);
				t.setValue(val.trim());				
			}
		}
		return t;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextReader tr = new TextReader();
		//Age- 17m   
		Token age = new Token("age", "m", "m~d~y","-");
		Token sex = new Token("sex", "", "m~f","-");
		Token wt = new Token("wt", "kg", "k~","-");
		Token ht = new Token("ht", "cm", "c~","-");
		Token sat = new Token("sat", "%", "%~","-");
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
		Token IVSs_mm = new Token("IVSs(mm)", "cm", "c~","");
		//AoR Diam(MM)  1.5 cm
		Token AoR_Diam_mm = new Token("AoR Diam(MM)", "cm", "c~","");
		//IVSd (MM)           0.570 cm
		Token IVSd_mm = new Token("IVSd (MM)", "cm", "c~","");
		//FS(MM- Teich)	 36 %
		Token FS_mm = new Token("FS(MM- Teich)", "%", "%~","");
		//EDV (MM-Teich)  18.0 m
		Token EDV_mm = new Token("EDV (MM-Teich)", "m", "m~","");
		//lLA Diam (MM)          1.6 cm
		Token lLA_Diam_mm = new Token("lLA Diam (MM)", "cm", "c~","");
		//LVIDd (MM)       2.30 cm
		Token LVIDd_mm = new Token("LVIDd (MM)", "cm", "c~","");
		//LVIDs (MM)         1.45 cm
		Token LVIDs_mm = new Token("LVIDs (MM)", "cm", "c~","");
		//EF(MM-Teich)                       68.0%
		Token ef_mm = new Token("EF(MM-Teich)", "%", "%~","");
		//ESV (MM-Teich)-   5.0 ml
		Token ESV_mm = new Token("ESV (MM-Teich)", "ml", "m~","-");
		//AV Cusp Sep     1.1 cm
		Token AV_Cusp_Sep_mm = new Token("AV Cusp Sep", "cm", "c~","");
		//LVPWd (MM)    0.645 cm
		Token LVPWd_mm = new Token("LVPWd (MM)", "cm", "c~","");
		//LVPWs (MM)      0.805 cm
		Token LVPWs_mm = new Token("LVPWs (MM)", "cm", "c~","");
		
		
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
		
		
		List<Tokens> tokens = new ArrayList<Tokens>();
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
		
		
		
		
		System.out.println(tokens);
		String input= tr.getTextFromFile("C:\\test-doc\\FORMAT.docx");//"Age- 17m Mitral valve: Normal, annulus – 1.3 cm, No  MR is present; IVSs(MM)                   0.825 cm";
		//String input="Age- 17m Mitral valve: Normal, annulus – 1.3 cm, No  MR is present; IVSs(MM)                   0.825 cm";;
		//String input="s name:  Oroni Roy              Age- 17m               Sex-F                            MR No :190800494                                                                                                                                                    	     Profiles:                  Wt-8 kg                 Ht-00.0cm             BSA – 0.00  m2          Sat -98%                                        	     Referred  by- Prof Abid H Mollah                                                                            Date - 01/08/19                                                                 ";
		System.out.println(input);
		for(Tokens t: tokens) {
			t.updateToken(input);
		}
		System.out.println(tokens);
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public List<Token> getSubTokens() {
		return subTokens;
	}

	public void setSubTokens(List<Token> subTokens) {
		this.subTokens = subTokens;
	}

	public void addSubToken(Token t) {
		this.subTokens.add(t);
	}

	@Override
	public String toString() {
		return "Tokens [token=" + token + ", subTokens=" + subTokens + "]\n";
	}
	
}
