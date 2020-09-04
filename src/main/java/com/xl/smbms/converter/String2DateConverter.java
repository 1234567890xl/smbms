package com.xl.smbms.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;



public class String2DateConverter  implements Converter<String, Date>{
	
	private String [] pattern ;
	public String[] getPattern() {
		return pattern;
	}
	public void setPattern(String[] pattern) {
		this.pattern = pattern;
	}



	public Date convert(String arg0) {
		System.out.println("String2DateConverter");
		
		for(int i = 0 ;  i < pattern.length ; i++){
			try {
				return new SimpleDateFormat(pattern[i]).parse(arg0);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				continue;
			}
			
		}
	
		return null;
		
	}

	

}
