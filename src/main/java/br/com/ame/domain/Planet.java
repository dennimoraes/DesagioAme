package br.com.ame.domain;

import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {
	
	private String _id;

	private String name;

	private String climate;

	private String terrain;

	private List<String> films;
	
	@Transient
	private Integer appearances;
	
	
	public Planet() {
		
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}


	public void setClimate(String climate) {
		this.climate = climate;
	}



	public String getTerrain() {
		return terrain;
	}



	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}



	public List<String> getFilms() {
		return films;
	}



	public void setFilms(List<String> films) {
		this.films = films;
	}



	public Integer getAppearances() {
		return  this.films != null ? this.films.size() : 0;
	}



	public void setAppearances(Integer appearances) {
		this.appearances = appearances;
	}



	@Override
	public String toString() {
		return "Planet {" +
				"name='" + name + '\'' +
				", climate=" + climate +
				", films=" + films +
				'}';
	}


	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}
}
