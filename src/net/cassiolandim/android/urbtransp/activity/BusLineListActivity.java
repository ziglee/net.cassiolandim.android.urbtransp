package net.cassiolandim.android.urbtransp.activity;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import net.cassiolandim.android.urbtransp.service.BusLineServiceFake;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class BusLineListActivity extends ListActivity {

	private BusLineService busLineService = new BusLineServiceFake();
	private List<BusLine> busLines;
	private EditText searchText;
	private Button searchButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busline_list);
		
		busLineService = new BusLineServiceFake();
		
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
		getListView().setAdapter(new ComplexRowAdapter(this, busLines));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent i = new Intent(this, BusLineDetailsActivity.class);
		i.putExtra(BusLine.BUS_LINE_ID, id);
		startActivity(i);
	}

	class ComplexRowAdapter extends ArrayAdapter<BusLine> {

		private Activity context;
		private List<BusLine> busLines;
		
		ComplexRowAdapter(Activity context, List<BusLine> busLines) {
			super(context, R.layout.complex_row, busLines);
			this.context = context;
			this.busLines = busLines;
		}
		

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = context.getLayoutInflater();
				row = inflater.inflate(R.layout.complex_row, null);
			}

			TextView label = (TextView) row.findViewById(R.id.text1);
			label.setText(busLines.get(position).number);

			label = (TextView) row.findViewById(R.id.text2);
			label.setText(busLines.get(position).name);

			return (row);
		}
		
		@Override
		public long getItemId(int position) {
			return busLines.get(position).id;
		}
	}
}
