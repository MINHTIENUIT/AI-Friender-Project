package com.avnhome.aifriender.Model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PersonalityOfChart {

	@SerializedName("big5_openness")
	protected double openness;

	@SerializedName("big5_agreeableness")
	protected double agreeableness;

	@SerializedName("big5_neuroticism")
	protected double neuroticism;

	@SerializedName("big5_conscientiousness")
	protected double conscientiousness;

	@SerializedName("big5_extraversion")
	protected double extraversion;

	public double getOpenness() {
		return openness;
	}

	public void setOpenness(double openness) {
		this.openness = openness;
	}

	public double getAgreeableness() {
		return agreeableness;
	}

	public void setAgreeableness(double agreeableness) {
		this.agreeableness = agreeableness;
	}

	public double getNeuroticism() {
		return neuroticism;
	}

	public void setNeuroticism(double neuroticism) {
		this.neuroticism = neuroticism;
	}

	public double getConscientiousness() {
		return conscientiousness;
	}

	public void setConscientiousness(double conscientiousness) {
		this.conscientiousness = conscientiousness;
	}

	public double getExtraversion() {
		return extraversion;
	}

	public void setExtraversion(double extraversion) {
		this.extraversion = extraversion;
	}

	@Override
	public String toString() {
		return "PersonalityOfChart{" +
				"openness=" + openness +
				", agreeableness=" + agreeableness +
				", neuroticism=" + neuroticism +
				", conscientiousness=" + conscientiousness +
				", extraversion=" + extraversion +
				'}';
	}
}