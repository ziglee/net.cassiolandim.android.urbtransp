package net.cassiolandim.android.urbtransp.entity;

import java.io.Serializable;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class BusStop implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String BUS_STOP_ID = "BUS_STOP_ID";
	
	public String id;
	public String address;
	public List<BusLine> lines;
	public GeoPoint geoPoint;
	
	public BusStop(String id, String address, List<BusLine> lines, GeoPoint geoPoint) {
		this.id = id;
		this.address = address;
		this.lines = lines;
		this.geoPoint = geoPoint;
	}
}
