package com.example.restservice;

public class Distance {

	private final String from;
	private final String to;
	private final String distance;
	private final String km;

	public Distance(String from, String to, String distance, String km) {
		this.from = from;
		this.to = to;
		this.distance = distance;
		this.km=km;

	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}
	public String getDistance() {
		return distance;
	}
	public String getKm() {
		return km;
	}
}
