package net.cassiolandim.android.urbtransp.activity;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.BusStop;
import net.cassiolandim.android.urbtransp.map.BusLinePathMapOverlay;
import net.cassiolandim.android.urbtransp.map.BusStopMapOverlay;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import net.cassiolandim.android.urbtransp.service.BusStopService;
import net.cassiolandim.android.urbtransp.service.FakeBusLineService;
import net.cassiolandim.android.urbtransp.service.FakeBusStopService;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class BusLineMapActivity extends MapActivity {
	
	private BusLineService busLineService = new FakeBusLineService();
	private BusStopService busStopService = new FakeBusStopService();
	private BusLine busLine;
	private List<BusStop> stops;
	private MapView mapView;
	private MapController mapController;
	private LinearLayout linearLayout;
	private List<Overlay> mapOverlays;
	private LocationManager locationManager;

	private static final int MENU_MY_POSITION = 1;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_map);
        
        Bundle extras = getIntent().getExtras();
	    Long id = extras.getLong(BusLine.BUS_LINE_ID);
	    busLine = busLineService.findById(id);
	    stops = busStopService.find(busLine);
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mapView = (MapView) findViewById(R.id.mapview);
        linearLayout = (LinearLayout) findViewById(R.id.zoomview);
        linearLayout.addView(mapView.getZoomControls());

        mapOverlays = mapView.getOverlays();
        mapController = mapView.getController();
        
        mapOverlays.add(new BusLinePathMapOverlay(busLine.path));
        mapOverlays.add(new BusStopMapOverlay(stops));
        
		mapController.setCenter(busLine.path.get(0));
        mapController.setZoom(15);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, MENU_MY_POSITION, 0, "Meu local").setIcon(android.R.drawable.ic_menu_mylocation);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case MENU_MY_POSITION:
	    	Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    	if(location == null) location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    	if(location != null){
		    	int lat = (int) (location.getLatitude() * 1E6);
		    	int lon = (int) (location.getLongitude() * 1E6);
		    	GeoPoint point = new GeoPoint(lat, lon);
		    	mapController.animateTo(point);
		        return true;
	    	}else{
	    		Toast.makeText(this, "Não foi possível encontrar sua localização", Toast.LENGTH_SHORT).show();
	    	}
	    }
	    return false;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
