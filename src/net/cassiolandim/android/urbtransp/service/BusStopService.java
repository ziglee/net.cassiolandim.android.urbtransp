package net.cassiolandim.android.urbtransp.service;

import java.util.List;

import net.cassiolandim.android.urbtransp.entity.BusStop;

public interface BusStopService {

	List<BusStop> findByLine(Long id);
	
}
