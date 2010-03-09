package net.cassiolandim.android.urbtransp.activity;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.adapter.ComplexBusLineRowAdapter;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.BusStop;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import net.cassiolandim.android.urbtransp.service.BusStopService;
import net.cassiolandim.android.urbtransp.service.FakeBusLineService;
import net.cassiolandim.android.urbtransp.service.FakeBusStopService;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class BusStopDetailsActivity extends ListActivity {
	
	private BusStopService busStopService = new FakeBusStopService();
	private BusLineService busLineService = new FakeBusLineService();
	private BusStop busStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_stop_details);
		
		Bundle extras = getIntent().getExtras();
	    String id = extras.getString(BusStop.BUS_STOP_ID);
	    busStop = busStopService.findById(id);
	    
	    TextView number = (TextView)findViewById(R.id.bus_stop_details_id);
	    number.setText(busStop.id);
	    
	    getListView().setAdapter(new ComplexBusLineRowAdapter(this, busStop.lines, busLineService));
		getListView().setTextFilterEnabled(true);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent i = new Intent(this, BusLineDetailsActivity.class);
		i.putExtra(BusLine.BUS_LINE_ID, id);
		startActivity(i);
	}
}
