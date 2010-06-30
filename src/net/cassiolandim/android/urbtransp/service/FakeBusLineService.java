package net.cassiolandim.android.urbtransp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.android.maps.GeoPoint;

import net.cassiolandim.android.urbtransp.entity.LineRegion;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.ItineraryPoint;
import net.cassiolandim.android.urbtransp.entity.LineKind;

public class FakeBusLineService implements BusLineService {

	private static final List<BusLine> lines = new ArrayList<BusLine>();
	
	static{
		List<GeoPoint> path = new ArrayList<GeoPoint>();
		path.add(new GeoPoint(-16693750,-49277480));
		path.add(new GeoPoint(-16694408,-49278381));
		path.add(new GeoPoint(-16695004,-49279196));
		path.add(new GeoPoint(-16695692,-49280119));
		path.add(new GeoPoint(-16694408,-49281160));
		path.add(new GeoPoint(-16693750,-49280344));
		path.add(new GeoPoint(-16693185,-49279357));
		path.add(new GeoPoint(-16692794,-49278896));
		path.add(new GeoPoint(-16692948,-49278177));
		path.add(new GeoPoint(-16693750,-49277533));
		
		ArrayList<ItineraryPoint> way = new ArrayList<ItineraryPoint>();
		way.add(new ItineraryPoint("Rua X-16","St. Bueno"));
		way.add(new ItineraryPoint("Av. W-7","St. Bueno"));
		way.add(new ItineraryPoint("Av. W-5","St. Bueno"));
		way.add(new ItineraryPoint("Av. W-1","St. Bueno"));
		way.add(new ItineraryPoint("Rua 44","St. Marista"));
		way.add(new ItineraryPoint("Rua Aruanã","St. Marista"));
		way.add(new ItineraryPoint("Av. Planalto","St. Marista"));
		way.add(new ItineraryPoint("Av. Leonardo da Vinci","St. Marista"));
		way.add(new ItineraryPoint("Av. Contorno","St. Marista"));
		way.add(new ItineraryPoint("Av. Anônio Queiroz de Barreto","St. Marista"));
		way.add(new ItineraryPoint("Av. 3ª Radial","St. Bela Vista"));
		way.add(new ItineraryPoint("T. Isidória","St. Bela Vista"));
		way.add(new ItineraryPoint("Av. 1ª Radial","St. Bela Vista"));
		way.add(new ItineraryPoint("Rua 90","St. Bela Vista"));
		way.add(new ItineraryPoint("Pç Cruzeiro","St. Bela Vista"));
		way.add(new ItineraryPoint("Rua 84","St. Central"));
		way.add(new ItineraryPoint("Pç Cívica (Rua 82)","St. Central"));
		way.add(new ItineraryPoint("Av. Araguaia","St. Central"));
		way.add(new ItineraryPoint("Av. Paranaíba","St. Central"));
		
		ArrayList<ItineraryPoint> wayReverse = new ArrayList<ItineraryPoint>(way);
		Collections.reverse(wayReverse);
		
		long id = 0;
		lines.add(new BusLine(++id, "004", "T. Garavelo / Centro - Eixo T - 9", LineRegion.SOUTH, LineKind.AXIS, way, wayReverse, path));
		lines.add(new BusLine(++id, "021", "Flamboyant / T. da Bíblia", LineRegion.SOUTH, LineKind.FEEDER, way, wayReverse, path));
		lines.add(new BusLine(++id, "027", "T. Bandeiras / T. da Bíblia - Via T - 7", LineRegion.SOUTH, LineKind.AXIS, way, wayReverse, path));
		lines.add(new BusLine(++id, "035", "T. Garavelo / Centro - Eixo T - 63", LineRegion.EAST, LineKind.AXIS, way, wayReverse, path));
		lines.add(new BusLine(++id, "052", "Vera Cruz / Campinas / Centro", LineRegion.WEST, LineKind.AXIS, way, wayReverse, path));
		lines.add(new BusLine(++id, "107", "T. Vl. Brasília / T. Cruzeiro", LineRegion.SOUTH, LineKind.AXIS, way, wayReverse, path));
		lines.add(new BusLine(++id, "501", "T. Garavelo / Goiânia Park Sul", LineRegion.WEST, LineKind.EXPRESS, way, wayReverse, path));
		lines.add(new BusLine(++id, "204", "PC Laranjeiras / Pq. Santa Cruz", LineRegion.SOUTH, LineKind.FEEDER, way, wayReverse, path));
		lines.add(new BusLine(++id, "600", "Santa Luzia / Centro", LineRegion.SOUTH, LineKind.EXPRESS, way, wayReverse, path));
	}
	
	public List<BusLine> findAll() {
		return lines;
	}
	
	public BusLine findById(Long id){
		for(BusLine line : lines){
			if(line.id.equals(id)) return line;
		}
		
		return null;
	}
	
	public List<BusLine> find(String search) {
		List<BusLine> lines = new ArrayList<BusLine>();
		
		for(BusLine line : findAll()){
			if(line.name.indexOf(search) >= 0 ||
					line.number.indexOf(search) >= 0) lines.add(line);
		}
		
		return lines;
	}

	public List<BusLine> find(LineKind lineKind) {
		List<BusLine> lines = new ArrayList<BusLine>();
		
		for(BusLine line : findAll()){
			if(line.lineKind.equals(lineKind)) lines.add(line);
		}
		
		return lines;
	}

	public List<BusLine> find(LineRegion area) {
		List<BusLine> lines = new ArrayList<BusLine>();
		
		for(BusLine line : findAll()){
			if(line.area.equals(area)) lines.add(line);
		}
		
		return lines;
	}

	public List<BusLine> find(LineKind lineKind, LineRegion area) {
		List<BusLine> lines = new ArrayList<BusLine>();
		
		for(BusLine line : findAll()){
			if(line.lineKind.equals(lineKind) && line.area.equals(area)) lines.add(line);
		}
		
		return lines;
	}
}
