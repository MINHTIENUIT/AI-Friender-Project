package com.avnhome.aifriender.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PersonalityOfChart implements Serializable {

	@SerializedName("big5_openness")
	protected double openness = -1;

	@SerializedName("big5_agreeableness")
	protected double agreeableness = -1;

	@SerializedName("big5_neuroticism")
	protected double neuroticism = -1;

	@SerializedName("big5_conscientiousness")
	protected double conscientiousness = -1;

	@SerializedName("big5_extraversion")
	protected double extraversion = -1;

	public PersonalityOfChart() {
	}

	public PersonalityOfChart(double openness, double agreeableness, double neuroticism, double conscientiousness, double extraversion) {
		this.openness = openness;
		this.agreeableness = agreeableness;
		this.neuroticism = neuroticism;
		this.conscientiousness = conscientiousness;
		this.extraversion = extraversion;
	}

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

	public List<Double> getPersonalities(){
		return Arrays.asList(openness*100,agreeableness*100,neuroticism*100,conscientiousness*100,extraversion*100);
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