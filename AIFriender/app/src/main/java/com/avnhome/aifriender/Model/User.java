package com.avnhome.aifriender.Model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class User extends PersonalityOfChart{

	@SerializedName("id")
	private String id;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("dob")
	private String dob;

	@SerializedName("name")
	private String name;

	@SerializedName("userName")
	private String userName;

	@SerializedName("email")
	private String email;

	@SerializedName("twitterId")
	private String twitterId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setTwitterId(String twitterId){
		this.twitterId = twitterId;
	}

	public String getTwitterId(){
		return twitterId;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", dob='" + dob + '\'' +
				", name='" + name + '\'' +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", twitterId='" + twitterId + '\'' +
				", openness='" + openness + '\'' +
				", agreeableness='" + agreeableness + '\'' +
				", neuroticism='" + neuroticism + '\'' +
				", conscientiousness='" + conscientiousness + '\'' +
				", extraversion='" + extraversion +
				'}';
	}
}