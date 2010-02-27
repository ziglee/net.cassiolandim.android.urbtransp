package net.cassiolandim.android.urbtransp.entity;

import java.io.Serializable;

public class ItineraryPoint implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String street;
	public String district;
	
	public ItineraryPoint(String street, String district) {
		this.street = street;
		this.district = district;
	}
}
