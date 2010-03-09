package net.cassiolandim.android.urbtransp.service;

import java.util.List;

import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.BusStop;

public interface BusStopService {

	BusStop findById(String id);
	List<BusStop> find(BusLine line);
	
}
