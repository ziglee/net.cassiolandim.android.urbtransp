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

public class BusLinePathOverlay extends Overlay {

	private List<BusStop> stops;
	
	public BusLinePathOverlay(List<BusStop> stops) {
		super();
		this.stops = stops;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		
		Projection projection = mapView.getProjection();
		
		Paint paint = new Paint();
        paint.setTextSize(14); 
        paint.setStyle(Style.FILL);
        paint.setARGB(255, 80, 150, 30); // Nice strong Android-Green

        Point previousPoint = null;
        for(BusStop stop : stops){
        	Point point = projection.toPixels(stop.geoPoint, null);
        	canvas.drawPoint(point.x, point.y, paint);
        	
        	if(previousPoint != null){
        		canvas.drawLine(previousPoint.x, previousPoint.y, point.x, point.y, paint);
        	}
        	
        	previousPoint = point;
        }
	}
}
