package net.cassiolandim.android.urbtransp.service;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

import net.cassiolandim.android.urbtransp.entity.BusStop;

public class BusStopServiceFake implements BusStopService {

	public List<BusStop> findByLine(Long id) {
		ArrayList<BusStop> stops = new ArrayList<BusStop>();
		
		List<String> lines = new ArrayList<String>();
		lines.add("502");
		lines.add("351");
		lines.add("135");
		lines.add("451");
		
		stops.add(new BusStop("P549", "Rua bla", lines, new GeoPoint(-16695700,-49276600)));
		stops.add(new BusStop("P712", "Rua aaerr", lines, new GeoPoint(-16695800,-49276700)));
		stops.add(new BusStop("P283", "Rua bbaaaaa", lines, new GeoPoint(-16695900,-49276800)));
		stops.add(new BusStop("P118", "Rua eer tre", lines, new GeoPoint(-16696100,-49276800)));
		stops.add(new BusStop("P352", "Rua tea ce", lines, new GeoPoint(-16696100,-49277000)));
		
		return stops;
	}

}
