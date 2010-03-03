package net.cassiolandim.android.urbtransp.activity;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import net.cassiolandim.android.urbtransp.service.BusLineServiceFake;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BusLineDetailsActivity extends Activity {

	private BusLineService busLineService = new BusLineServiceFake();
	private BusLine line;
	
	private static final int MENU_TIME_TABLE = 1;
	private static final int MENU_ON_MAP = 2;
	private static final int MENU_ITINERARY_GOING = 3;
	private static final int MENU_ITINERARY_BACK = 4;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_line_details);
		
		Bundle extras = getIntent().getExtras();
	    Long id = extras.getLong(BusLine.BUS_LINE_ID);
	    line = busLineService.findById(id);
	    
	    TextView number = (TextView)findViewById(R.id.bus_line_details_number);
	    number.setText(line.number + " " + line.name);
	    
	    TextView name = (TextView)findViewById(R.id.bus_line_details_name);
	    name.setText("Área: " + line.area + " - Tipo: " + line.lineKind);
	    
	    ImageView area = (ImageView) findViewById(R.id.bus_line_details_area_img);
    	area.setImageResource(line.area.getAreaImageResource());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, MENU_TIME_TABLE, 0, "Horários").setIcon(android.R.drawable.ic_menu_info_details);
	    menu.add(0, MENU_ON_MAP, 1, "No mapa").setIcon(android.R.drawable.ic_menu_mapmode);
	    menu.add(0, MENU_ITINERARY_GOING, 2, "Caminho ida");
	    menu.add(0, MENU_ITINERARY_BACK, 3, "Caminho volta");
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case MENU_TIME_TABLE:
	    	Toast.makeText(this, "Exibir tabela de horários para " + line.name, Toast.LENGTH_SHORT).show();
	        return true;
	        
	    case MENU_ON_MAP:
	    	Intent i3 = new Intent(this, BusMapActivity.class);
	    	i3.putExtra(BusLine.BUS_LINE_ID, line.id);
	    	startActivity(i3);
	        return true;
	        
	    case MENU_ITINERARY_GOING:
		    Intent i1 = new Intent(this, ItineraryListActivity.class);
			i1.putExtra(BusLine.BUS_LINE_ITINERARY, line.wayGoing);
			startActivity(i1);
	        return true;
	        
	    case MENU_ITINERARY_BACK:
	    	Intent i2 = new Intent(this, ItineraryListActivity.class);
			i2.putExtra(BusLine.BUS_LINE_ITINERARY, line.wayBack);
			startActivity(i2);
	        return true;
	    }
	    
	    return false;
	}
}
