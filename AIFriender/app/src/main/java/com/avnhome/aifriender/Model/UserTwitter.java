package com.avnhome.aifriender.Model;

public class UserTwitter{
	private String twitterId;

	public UserTwitter(String twitterId) {
		this.twitterId = twitterId;
	}

	public void setTwitterId(String twitterId){
		this.twitterId = twitterId;
	}

	public String getTwitterId(){
		return twitterId;
	}

	@Override
 	public String toString(){
		return 
			"UserTwitter{" + 
			"twitterId = '" + twitterId + '\'' + 
			"}";
		}
}
