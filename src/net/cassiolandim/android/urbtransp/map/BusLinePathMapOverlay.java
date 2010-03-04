package net.cassiolandim.android.urbtransp.map;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class BusLinePathMapOverlay extends Overlay {

	private List<GeoPoint> path;
	
	public BusLinePathMapOverlay(List<GeoPoint> path) {
		super();
		this.path = path;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		
		Projection projection = mapView.getProjection();
		
		Paint paint = new Paint();
        //paint.setTextSize(14);
		paint.setStrokeWidth(3);
        paint.setStyle(Style.FILL);
        paint.setARGB(255, 80, 150, 30); // Nice strong Android-Green

        Point previousPoint = null;
        for(GeoPoint stop : path){
        	Point point = projection.toPixels(stop, null);
        	
        	if(previousPoint != null){
        		canvas.drawLine(previousPoint.x, previousPoint.y, point.x, point.y, paint);
        	}
        	
        	previousPoint = point;
        }
	}
}
