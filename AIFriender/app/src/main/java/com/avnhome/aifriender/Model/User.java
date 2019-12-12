package com.avnhome.aifriender.Model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("com.robohorse.robopojogenerator")
public class User extends PersonalityOfChart implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("dob")
	private String dob;

	@SerializedName("name")
	private String name;

	@SerializedName("age")
	private String age;

	@SerializedName("userName")
	private String userName;

	@SerializedName("email")
	private String email;

	@SerializedName("twitterId")
	private String twitterId;

	public User(double openness, double agreeableness, double neuroticism, double conscientiousness, double extraversion, String id, String phoneNumber, String dob, String name, String age, String userName, String email, String twitterId) {
		super(openness, agreeableness, neuroticism, conscientiousness, extraversion);
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.name = name;
		this.age = age;
		this.userName = userName;
		this.email = email;
		this.twitterId = "@" + twitterId;
	}

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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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
				", age='" + age + '\'' +
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

	public static class UserBuilder{
		private String id;
		private String phoneNumber;
		private String dob;
		private String name;
		private String age;
		private String userName;
		private String email;
		private String twitterId;
		private PersonalityOfChart personality;

		public UserBuilder(String twitterId){
			this.twitterId = twitterId;
		}
		public UserBuilder(User user){
			this.twitterId = user.twitterId;
			this.id = user.id;
			this.phoneNumber = user.phoneNumber;
			this.dob = user.dob;
			this.name = user.name;
			this.age = user.age;
			this.userName = user.userName;
			this.email = user.email;
			personality = user;
		}

		public UserBuilder withId(String id){
			this.id = id;
			return this;
		}

		public UserBuilder withPhoneNumber(String phoneNumber){
			this.phoneNumber = phoneNumber;
			return this;
		}

		public UserBuilder withDOB(String dob){
			this.dob = dob;
			return this;
		}

		public UserBuilder withName(String name){
			this.name = name;
			return this;
		}

		public UserBuilder withAge(String age){
			this.age = age;
			return this;
		}

		public UserBuilder withUserName(String userName){
			this.userName = userName;
			return this;
		}

		public UserBuilder withEmail(String email){
			this.email = email;
			return this;
		}

		public UserBuilder withPersonality(PersonalityOfChart personality){
			this.personality = personality;
			return this;
		}

		public User build(){
			User user = new User(personality != null? personality.openness: 0,
					personality != null? personality.agreeableness : 0,
					personality != null? personality.neuroticism : 0,
					personality != null? personality.conscientiousness : 0,
					personality != null? personality.extraversion : 0,
					this.id,
					this.phoneNumber,
					this.dob,
					this.name,
					this.age,
					this.userName,
					this.email,
					this.twitterId
					);

			return user;
		}
	}
}