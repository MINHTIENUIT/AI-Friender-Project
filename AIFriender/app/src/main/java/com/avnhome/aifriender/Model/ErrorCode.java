package com.avnhome.aifriender.Model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ErrorCode {

	@SerializedName("errorCode")
	private String errorCode;

	@SerializedName("message")
	private String message;

	public void setErrorCode(String errorCode){
		this.errorCode = errorCode;
	}

	public String getErrorCode(){
		return errorCode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ErrorCode{" +
			"errorCode = '" + errorCode + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}