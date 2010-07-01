package net.cassiolandim.android.urbtransp.activity;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import net.cassiolandim.android.urbtransp.service.FakeBusLineService;
import net.cassiolandim.android.urbtransp.service.LocalService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BusLineDetailsActivity extends Activity {

	private BusLineService busLineService = new FakeBusLineService();
	private BusLine line;
	private Intent servico;
	
	@Override
	protected void onResume() {
		super.onResume();
		startService(servico);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		stopService(servico);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_line_details);
		
		servico = new Intent(this, LocalService.class);
		
		Bundle extras = getIntent().getExtras();
	    Long id = extras.getLong(BusLine.BUS_LINE_ID);
	    line = busLineService.findById(id);
	    
	    TextView number = (TextView)findViewById(R.id.bus_line_details_number);
	    number.setText(line.number + " " + line.name);
	    
	    TextView name = (TextView)findViewById(R.id.bus_line_details_name);
	    name.setText("Área: " + line.area + " - Tipo: " + line.lineKind);
	    
	    ImageView area = (ImageView) findViewById(R.id.bus_line_details_area_img);
    	area.setImageResource(line.area.getAreaImageResource());
    	
    	ImageView buttonItineraryBack = (ImageView) findViewById(R.id.button_itinerary_back);
    	buttonItineraryBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i2 = new Intent(BusLineDetailsActivity.this, ItineraryListActivity.class);
				i2.putExtra(BusLine.BUS_LINE_ITINERARY, line.wayBack);
				startActivity(i2);
			}
		});
    	
    	ImageView buttonItineraryGoing = (ImageView) findViewById(R.id.button_itinerary_going);
    	buttonItineraryGoing.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i2 = new Intent(BusLineDetailsActivity.this, ItineraryListActivity.class);
				i2.putExtra(BusLine.BUS_LINE_ITINERARY, line.wayGoing);
				startActivity(i2);
			}
		});
    	
    	ImageView showOnMap = (ImageView) findViewById(R.id.button_show_on_map);
    	showOnMap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i3 = new Intent(BusLineDetailsActivity.this, BusLineMapActivity.class);
		    	i3.putExtra(BusLine.BUS_LINE_ID, line.id);
		    	startActivity(i3);
			}
		});
    	
    	ImageView showSchedule = (ImageView) findViewById(R.id.button_show_schedule);
    	showSchedule.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(BusLineDetailsActivity.this, "Exibir tabela de horários para " + line.name, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
