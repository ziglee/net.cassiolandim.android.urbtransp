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
		
		stops.add(new BusStop("P5498", "Rua bla", lines, new GeoPoint(-16695700,-49276600)));
		
		return stops;
	}

}
