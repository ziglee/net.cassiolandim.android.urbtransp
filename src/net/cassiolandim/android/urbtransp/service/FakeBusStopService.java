package net.cassiolandim.android.urbtransp.service;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

import net.cassiolandim.android.urbtransp.entity.BusStop;

public class FakeBusStopService implements BusStopService {

	public List<BusStop> findByLine(Long id) {
		ArrayList<BusStop> stops = new ArrayList<BusStop>();
		
		List<String> lines = new ArrayList<String>();
		lines.add("502");
		lines.add("351");
		lines.add("135");
		lines.add("451");
		
		stops.add(new BusStop("P549", "Rua bla", lines, new GeoPoint(-16693870,-49277600)));
		stops.add(new BusStop("P712", "Rua aaerr", lines, new GeoPoint(-16695124,-49279316)));
		stops.add(new BusStop("P283", "Rua bbaaaaa", lines, new GeoPoint(-16693870,-49280464)));
		stops.add(new BusStop("P118", "Rua eer tre", lines, new GeoPoint(-16692914,-49279016)));
		stops.add(new BusStop("P352", "Rua tea ce", lines, new GeoPoint(-16693870,-49277653)));
		
		return stops;
	}

}
