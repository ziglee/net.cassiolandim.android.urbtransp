package net.cassiolandim.android.urbtransp.entity;

public enum LineKind {

	EXPRESS ("Expressa"),
	AXIS ("Eixo"),
	FEEDER ("Alimentadora");
	
	private final String name;
	
	private LineKind(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
