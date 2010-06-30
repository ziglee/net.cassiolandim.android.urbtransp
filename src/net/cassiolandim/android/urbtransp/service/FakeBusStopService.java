package net.cassiolandim.android.urbtransp.service;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.BusStop;

import com.google.android.maps.GeoPoint;

public class FakeBusStopService implements BusStopService {

	private static final List<BusStop> stops = new ArrayList<BusStop>();
	
	public List<BusStop> find(BusLine line) {
		List<BusLine> lines = new ArrayList<BusLine>();
		lines.add(new BusLine(1L));
		lines.add(new BusLine(2L));
		lines.add(new BusLine(3L));
		lines.add(new BusLine(4L));
		
		stops.add(new BusStop("P549", "R. T4", lines, new GeoPoint(-16693870,-49277600)));
		stops.add(new BusStop("P712", "Av. Padre Wendel", lines, new GeoPoint(-16695124,-49279316)));
		stops.add(new BusStop("P283", "Av. Mutirão", lines, new GeoPoint(-16693870,-49280464)));
		stops.add(new BusStop("P118", "R. 90", lines, new GeoPoint(-16692914,-49279016)));
		stops.add(new BusStop("P352", "R. C261", lines, new GeoPoint(-16693870,-49277653)));
		
		return stops;
	}

	public BusStop findById(String id) {
		for(BusStop stop : stops){
			if(stop.id.equals(id)) return stop;
		}
		
		return null;
	}

}
