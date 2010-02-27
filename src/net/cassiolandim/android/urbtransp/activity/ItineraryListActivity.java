package net.cassiolandim.android.urbtransp.activity;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.entity.ItineraryPoint;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItineraryListActivity extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itinerary_list);
		
		Bundle extras = getIntent().getExtras();
		List<ItineraryPoint> points = (List<ItineraryPoint>)extras.getSerializable(BusLine.BUS_LINE_ITINERARY);
		getListView().setAdapter(new ComplexRowAdapter(this, points));
		getListView().setTextFilterEnabled(true);
	}
	
	class ComplexRowAdapter extends ArrayAdapter<ItineraryPoint> {

		private Activity context;
		private List<ItineraryPoint> points;
		
		ComplexRowAdapter(Activity context, List<ItineraryPoint> points) {
			super(context, R.layout.complex_row, points);
			this.context = context;
			this.points = points;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = context.getLayoutInflater();
				row = inflater.inflate(R.layout.twoline_row, null);
			}

			TextView label = (TextView) row.findViewById(R.id.itinerary_street);
			label.setText(points.get(position).street);

			label = (TextView) row.findViewById(R.id.itinerary_district);
			label.setText(points.get(position).district);

			return (row);
		}
	}
}
