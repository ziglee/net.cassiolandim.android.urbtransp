package net.cassiolandim.android.urbtransp.activity;

import net.cassiolandim.android.urbtransp.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, BusMapActivity.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("map").setIndicator("Mapa", res.getDrawable(android.R.drawable.ic_dialog_map)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this, BusLineListActivity.class);
		spec = tabHost.newTabSpec("lines").setIndicator("Linhas", res.getDrawable(android.R.drawable.ic_dialog_info)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
}
