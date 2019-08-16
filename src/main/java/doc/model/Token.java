package doc.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Token {
	String key, value, unit, endTag, separator;
	ArrayList<String> endTags = new ArrayList<String>();

	public Token(String key, String unit, String endTag,String separator) {
		super();
		this.key = key;
		this.unit = unit;
		this.endTag = endTag;
		this.separator=separator;
		this.endTags.addAll(Arrays.asList(endTag.split("~")));
	}

	public Token() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEndTag() {
		return endTag;
	}

	public void setEndTag(String endTag) {
		this.endTag = endTag;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	@Override
	public String toString() {
		//return "Token [key=" + key + ", value=" + value + ", unit=" + unit + ", endTag=" + endTag + ", separator="+ separator + "]";
		return "Token [key=" + key + ", value=" + value + ", unit=" + unit + "]";
	}

	
}
