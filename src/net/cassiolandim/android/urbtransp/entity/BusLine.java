package net.cassiolandim.android.urbtransp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class BusLine implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String BUS_LINE_ID = "BUS_LINE_ID";
	public static final String BUS_LINE_ITINERARY = "BUS_LINE_ITINERARY";
	
	public Long id;
	public String number;
	public String name;
	public Area area;
	public LineKind lineKind;
	public ArrayList<ItineraryPoint> wayGoing;
	public ArrayList<ItineraryPoint> wayBack;
	public List<GeoPoint> path;
	
	public BusLine(Long id){
		this.id = id;
	}
	
	public BusLine(Long id, String number, String name, Area area, LineKind lineKind, ArrayList<ItineraryPoint> wayGoing, ArrayList<ItineraryPoint> wayBack, List<GeoPoint> path) {
		this.id = id;
		this.number = number;
		this.name = name;
		this.area = area;
		this.lineKind = lineKind;
		this.wayGoing = wayGoing;
		this.wayBack = wayBack;
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BusLine other = (BusLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
