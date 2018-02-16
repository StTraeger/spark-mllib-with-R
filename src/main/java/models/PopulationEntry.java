package models;

import java.io.Serializable;

public class PopulationEntry implements Serializable{

	int year;
	int population;

	public PopulationEntry(int year, int population) {
		super();
		this.year = year;
		this.population = population;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "PopulationEntry [year=" + year + ", population=" + population + "]";
	}
	
	

}
