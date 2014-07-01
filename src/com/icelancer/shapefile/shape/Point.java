package com.icelancer.shapefile.shape;

import com.icelancer.shapefile.ShapeTypeEnum;

public class Point implements Shape{
	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public ShapeTypeEnum getType() {
		return ShapeTypeEnum.POINT;
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("type: Point, x=").append(this.x).append(", y=").append(this.y);
		
		return sb.toString();
	}
	
}
