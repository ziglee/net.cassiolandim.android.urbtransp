package net.cassiolandim.android.urbtransp.map;

import java.util.List;

import net.cassiolandim.android.urbtransp.R;
import net.cassiolandim.android.urbtransp.activity.BusStopDetailsActivity;
import net.cassiolandim.android.urbtransp.entity.BusStop;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;

import com.google.android.maps.GeoPoint;
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
		
		Paint paint = new Paint();
		paint.setStrokeWidth(3);
        paint.setStyle(Style.FILL);
        paint.setARGB(80, 100, 50, 30);

        Bitmap bitmap = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.thumbtack_blue);
        
		Projection projection = mapView.getProjection();
        for(BusStop stop : stops){
        	Point point = new Point(); 
        	projection.toPixels(stop.geoPoint, point);
        	canvas.drawPoint(point.x, point.y, paint);
        	canvas.drawBitmap(bitmap, (point.x - 6), (point.y - 32), null);
        }
	}

	@Override
	public boolean onTap(GeoPoint geoPoint, MapView mapView) {
		Projection projection = mapView.getProjection();
		Point tappedPoint = new Point(); 
    	projection.toPixels(geoPoint, tappedPoint);
    	
		for(BusStop stop : stops){
			Point point = new Point(); 
        	projection.toPixels(stop.geoPoint, point);
			
			if(isBetweenXLimits(tappedPoint.x, point.x) && isBetweenYLimits(tappedPoint.y, point.y)){
				Context context = mapView.getContext();
				Intent i = new Intent(context, BusStopDetailsActivity.class);
				i.putExtra(BusStop.BUS_STOP_ID, stop.id);
				context.startActivity(i);
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean isBetweenXLimits(int sample, int target){
		return (sample > (target - 16) && sample < (target + 16));
	}
	
	private static boolean isBetweenYLimits(int sample, int target){
		return (sample > (target - 32) && sample < (target));
	}
}
