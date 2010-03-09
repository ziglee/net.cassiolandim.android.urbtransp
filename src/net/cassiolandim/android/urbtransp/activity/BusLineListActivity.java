package net.cassiolandim.android.urbtransp.activity;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.adapter.ComplexBusLineRowAdapter;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import net.cassiolandim.android.urbtransp.service.FakeBusLineService;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class BusLineListActivity extends ListActivity {

	private BusLineService busLineService = new FakeBusLineService();
	private List<BusLine> busLines;
	private EditText searchText;
	private Button searchButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busline_list);
		
		busLineService = new FakeBusLineService();
		
		searchText = (EditText)findViewById(R.id.bus_line_search_field);
		searchButton = (Button)findViewById(R.id.bus_line_search_button);		
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String text = searchText.getText().toString();
				busLines = busLineService.find(text);
				refreshList();
			}
		});
	}

	private void refreshList() {
		getListView().setAdapter(new ComplexBusLineRowAdapter(this, busLines, busLineService));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent i = new Intent(this, BusLineDetailsActivity.class);
		i.putExtra(BusLine.BUS_LINE_ID, id);
		startActivity(i);
	}
}
