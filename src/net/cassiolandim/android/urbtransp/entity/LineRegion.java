package net.cassiolandim.android.urbtransp.entity;

import net.cassiolandim.android.urbtransp.R;

public enum LineRegion {

	ALL ("Todas"),
	SOUTH ("Sul"),
	EAST ("Leste"),
	WEST ("Oeste");
	
	private final String name;
	
	private LineRegion(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public int getAreaImageResource(){
		switch(this){
	    case EAST:
	    	return R.drawable.area_leste_nav;
	    case WEST:
	    	return R.drawable.area_oeste_nav;
	    case SOUTH:
	    	return R.drawable.area_sul_nav;
	    }
		return R.drawable.area_todas_nav;
	}
}
