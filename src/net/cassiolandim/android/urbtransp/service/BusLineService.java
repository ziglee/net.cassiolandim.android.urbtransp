package net.cassiolandim.android.urbtransp.service;

import java.util.List;

import net.cassiolandim.android.urbtransp.entity.LineRegion;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.LineKind;

public interface BusLineService {

	List<BusLine> findAll();
	List<BusLine> find(String search);
	List<BusLine> find(LineKind lineKind);
	List<BusLine> find(LineRegion area);
	List<BusLine> find(LineKind lineKind, LineRegion area);
	BusLine findById(Long id);
}
