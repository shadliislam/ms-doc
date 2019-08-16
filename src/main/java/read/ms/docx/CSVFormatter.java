package read.ms.docx;

import java.text.Format;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import doc.model.Token;
import doc.model.Tokens;

public class CSVFormatter {
	HashMap<String,Boolean> isValveChecked = new HashMap<String,Boolean>();
	// valve: age,sex,wt,ht,sat,valve_key,valve_value,annulus_value
	public List<String> formatedString(String fromatStr, List<Tokens> tokens,int fileId) {
		isValveChecked = new HashMap<String,Boolean>();
		StringBuilder sb = new StringBuilder(fileId+",");
		String[] ar = fromatStr.split(",");
		List<String> valves = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		for(Tokens t: tokens) {
			for(String s: fromatStr.split(",")) {				
				if(s.contains("valve") && t.getToken().getKey().contains("valve")) {
					valves.add(getValveInfo(t));
				}else if(s.equalsIgnoreCase(t.getToken().getKey())) {
					sb.append(t.getToken().getValue().trim()+",");
				}
			}
		}
		for(String s:valves) {
			if(s.length()>0) {
				results.add(sb.toString()+s);
			}
		}
		//System.out.println(sb.toString());
		return results;
	}
	// valve: PFX: age,sex,wt,ht,sat: SFX: valve_key,valve_value,annulus_value
	public String formatedStringPrefix(String fromatStr, List<Tokens> tokens,int fileId) {
		isValveChecked = new HashMap<String,Boolean>();
		StringBuilder sb = new StringBuilder(fileId+",");
		String[] ar = fromatStr.split(",");
		List<String> valves = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		for(Tokens t: tokens) {
			for(String s: fromatStr.split(",")) {				
				if(s.equalsIgnoreCase(t.getToken().getKey())) {
					sb.append(t.getToken().getValue().trim()+",");
				}
			}
		}
		return sb.toString();
	}
	// valve: valve_key,valve_value,annulus_value
	public List<String> formatedValveString(String fromatStr, List<Tokens> tokens, String prefix) {
		isValveChecked = new HashMap<String,Boolean>();
		StringBuilder sbPrefix = new StringBuilder(prefix);
		String[] ar = fromatStr.split(",");
		List<String> valves = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		for(Tokens t: tokens) {
			for(String s: fromatStr.split(",")) {				
				if(s.contains("valve") && t.getToken().getKey().contains("valve")) {
					valves.add(getValveInfo(t));
				}
			}
		}
		for(String s:valves) {
			if(s.length()>0) {
				if(sbPrefix.length()>0) {
					results.add(sbPrefix.toString()+s);
				}else {
					results.add(s);
				}
			}
		}
		//System.out.println(sb.toString());
		return results;
	}
	//LV_STUDY: prfx: age,sex,wt,ht,sat, SFX: ef,esv,lv_key,lv_value
	public List<String> formatedLvsString(String fromatStr, List<Tokens> tokens, String prefix, List<String> lvs) {
		isValveChecked = new HashMap<String,Boolean>();
		StringBuilder sbPrefix = new StringBuilder(prefix);
		String[] ar = fromatStr.split(",");
		List<String> valves = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		
		for(Tokens t: tokens) {
			for(String s: lvs) {				
				if(lvs.contains(t.getToken().getKey())) {
					valves.add(getValveInfo(t));
				}
			}
		}
		
		
		for(String s:valves) {
			if(s.length()>0) {
				if(sbPrefix.length()>0) {
					results.add(sbPrefix.toString()+s);
				}else {
					results.add(s);
				}
			}
		}
		//System.out.println(sb.toString());
		return results;
	}	
	//valve_key,valve_value,annulus_value
	private String getValveInfo(Tokens tokens) {
		StringBuilder sb = new StringBuilder("");
		String key = tokens.getToken().getKey();
		if(this.isValveChecked.containsKey(key)) {
			sb.append("");
		}else {
			this.isValveChecked.put(key, true);
			sb.append(key+",");
			String val=(tokens.getToken().getValue()!=null)?tokens.getToken().getValue().trim():"0";
			sb.append(val+",");
			for(Token token: tokens.getSubTokens()) {
				if("annulus".equalsIgnoreCase(token.getKey())) {
					String vals=(token.getValue()!=null)?token.getValue().trim():"0";
					sb.append(vals+",");
				}
			}
		}
		return sb.toString();
	}
}
