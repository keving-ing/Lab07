package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages {
	
	private int id;
	private Nerc nerc;
	private LocalDateTime start;
	private LocalDateTime end;
	private int affectedPeople;
	private long durata_in_ore;
	private int year;
	
	public PowerOutages(int id, Nerc nerc, LocalDateTime start, LocalDateTime end, int affectedPeople) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.start = start;
		this.end = end;
		this.affectedPeople = affectedPeople;
		
		this.durata_in_ore = start.until(end, ChronoUnit.HOURS);
		this.year = start.getYear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public int getAffectedPeople() {
		return affectedPeople;
	}

	public void setAffectedPeople(int affectedPeople) {
		this.affectedPeople = affectedPeople;
	}

	public long getDurata_in_ore() {
		return durata_in_ore;
	}

	public void setDurata_in_ore(long durata_in_ore) {
		this.durata_in_ore = durata_in_ore;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PowerOutages [id=" + id + ", start=" + start + ", end=" + end + ", affectedPeople=" + affectedPeople
				+ "]\n";
	}
	
	
	
	
	
	
	

}
