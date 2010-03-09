package net.cassiolandim.android.urbtransp.adapter;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.entity.BusLine;
import net.cassiolandim.android.urbtransp.service.BusLineService;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ComplexBusLineRowAdapter extends ArrayAdapter<BusLine> {

	private BusLineService busLineService;
	private Activity context;
	private List<BusLine> busLines;
	
	public ComplexBusLineRowAdapter(Activity context, List<BusLine> busLines, BusLineService busLineService) {
		super(context, R.layout.complex_row, busLines);
		this.context = context;
		this.busLines = busLines;
		this.busLineService = busLineService;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			row = inflater.inflate(R.layout.complex_row, null);
		}
		
		BusLine busLine = busLines.get(position);
		
		if(busLine.number == null) busLine = busLineService.findById(busLine.id);

		TextView label = (TextView) row.findViewById(R.id.text1);
		label.setText(busLine.number);

		label = (TextView) row.findViewById(R.id.text2);
		label.setText(busLine.name);

		return (row);
	}
	
	@Override
	public long getItemId(int position) {
		return busLines.get(position).id;
	}
}