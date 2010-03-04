package net.cassiolandim.android.urbtransp.activity;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.BusStop;
import net.cassiolandim.android.urbtransp.map.BusLinePathOverlay;
import net.cassiolandim.android.urbtransp.service.BusStopService;
import net.cassiolandim.android.urbtransp.service.BusStopServiceFake;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
	
	private BusStopService busLineService = new BusStopServiceFake();
	private List<BusStop> stops;
	private MapView mapView;
	private MapController mapController;
	private LinearLayout linearLayout;
	private List<Overlay> mapOverlays;
	private Drawable drawable;
	private LocationManager locationManager;

	private static final int MENU_MY_POSITION = 1;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_map);
        
        Bundle extras = getIntent().getExtras();
	    Long id = extras.getLong(BusLine.BUS_LINE_ID);
	    stops = busLineService.findByLine(id);
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mapView = (MapView) findViewById(R.id.mapview);
        drawable = this.getResources().getDrawable(R.drawable.ic_map_marker);
        linearLayout = (LinearLayout) findViewById(R.id.zoomview);
        linearLayout.addView(mapView.getZoomControls());

        mapOverlays = mapView.getOverlays();
        mapController = mapView.getController();
        
        mapOverlays.add(new BusLinePathOverlay(stops));
        
		mapController.setCenter(new GeoPoint(-16695600,-49276500));
        mapController.setZoom(20);
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
	    	Toast.makeText(this, "Exibir minha posição no mapa!", Toast.LENGTH_SHORT).show();
	    	GeoPoint point = new GeoPoint(-16695600,-49276500);
	    	mapController.animateTo(point);
	        return true;
	    }
	    return false;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
