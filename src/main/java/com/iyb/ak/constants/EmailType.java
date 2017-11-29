package com.iyb.ak.constants;


import org.apache.commons.lang3.StringUtils;

//@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum EmailType {

	TEXT("文本"),
	
	HTML("富文本"),
	
	UNKNOW("未知的类型");
	

	private String value;
	private String name;

    private EmailType(String name) {
    	this.value= this.toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
	public String getValue() {
		return value;
	}
 
    
    public static EmailType formatEnum(String key){
    	
    	if(StringUtils.isBlank(key)){
    		return null;
    	}
    	try {
			return EmailType.valueOf(key);
		} catch (Exception e) {
			return UNKNOW;
		}    	
    }
	
}