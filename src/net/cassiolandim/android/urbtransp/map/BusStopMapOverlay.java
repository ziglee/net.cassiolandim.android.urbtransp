package net.cassiolandim.android.urbtransp.map;

import java.util.List;

import net.cassiolandim.android.urbtransp.entity.BusStop;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class BusStopMapOverlay extends Overlay {

	private List<BusStop> stops;
	
	public BusStopMapOverlay(List<BusStop> stops) {
		super();
		this.stops = stops;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		
		Projection projection = mapView.getProjection();
		
		Paint paint = new Paint();
		paint.setStrokeWidth(3);
        paint.setStyle(Style.FILL);
        paint.setARGB(80, 100, 50, 30); // Nice strong Android-Green

        for(BusStop stop : stops){
        	Point point = projection.toPixels(stop.geoPoint, null);
        	canvas.drawPoint(point.x, point.y, paint);
        }
	}
}
