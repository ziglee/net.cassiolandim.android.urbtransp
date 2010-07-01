package net.cassiolandim.android.urbtransp.activity;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.map.ListItemizedOverlay;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import net.cassiolandim.android.urbtransp.service.FakeBusLineService;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.maps.OverlayItem;

public class BusMapActivity extends MapActivity {
	
	private BusLineService busLineService = new FakeBusLineService();
	private MapView mapView;
	private MapController mapController;
	private LinearLayout linearLayout;
	private List<Overlay> mapOverlays;
	private Drawable drawable;
	private ListItemizedOverlay itemizedOverlay;
	private LocationManager locationManager;

	private static final int MENU_MY_POSITION = 1;
	private static final int MENU_SEARCH_LINE = 2;
	private static final int MENU_CONF = 3;
	private static final int MENU_EXIT = 4;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_map);
        
        Bundle extras = getIntent().getExtras();
        if(extras != null){
        	Long id = extras.getLong(BusLine.BUS_LINE_ID);
        	if(id != null){
        		BusLine line = busLineService.findById(id);
        		Toast.makeText(this, "Exibir rota no mapa para " + line.name, Toast.LENGTH_SHORT).show();
        	}
        }
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mapView = (MapView) findViewById(R.id.mapview);
        drawable = this.getResources().getDrawable(R.drawable.user);
        linearLayout = (LinearLayout) findViewById(R.id.zoomview);
        linearLayout.addView(mapView.getZoomControls());

        mapOverlays = mapView.getOverlays();
        mapController = mapView.getController();
        
        itemizedOverlay = new ListItemizedOverlay(drawable);
        
        GeoPoint point = new GeoPoint(-16695600,-49276500);
        OverlayItem overlayitem = new OverlayItem(point, "", "");
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        
		mapController.setCenter(point);
        mapController.setZoom(14);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, MENU_MY_POSITION, 0, "Meu local").setIcon(R.drawable.flag);
	    menu.add(0, MENU_SEARCH_LINE, 1, "Buscar linhas").setIcon(R.drawable.search);
	    menu.add(0, MENU_CONF, 2, "Conf").setIcon(R.drawable.system);
	    menu.add(0, MENU_EXIT, 3, "Sair").setIcon(R.drawable.close);
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
	        
	    case MENU_SEARCH_LINE:
	    	Intent i = new Intent(this, BusLineListActivity.class);
	    	startActivity(i);
	        return true;
	        
	    case MENU_CONF:
	    	Toast.makeText(this, "Exibir configurações", Toast.LENGTH_SHORT).show();
	    	return true;
	    	
	    case MENU_EXIT:
	    	finish();
	    	return true;
	    }
	    
	    return false;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
