package com.icelancer.shapefile.shape;

import java.util.List;

import com.icelancer.shapefile.ShapeTypeEnum;

public class Polygon implements Shape {
	private double bbox[];		// Bounding Box
	private int numParts;		// Number of Parts
	private int numPoints;		// Total Number of Points
	private int[] parts;		// Index to First Point in Part
	private List<List<Point>> partsList;
	
	public Polygon(double[] bbox, int numParts, int numPoints, int[] parts, List<List<Point>> partsList) {
		this.bbox = bbox;
		this.numParts = numParts;
		this.numPoints = numPoints;
		this.parts = parts;
		this.partsList = partsList;
	}
	
	public double[] getBbox() {
		return bbox;
	}


	public void setBbox(double[] bbox) {
		this.bbox = bbox;
	}


	public int getNumParts() {
		return numParts;
	}


	public void setNumParts(int numParts) {
		this.numParts = numParts;
	}


	public int getNumPoints() {
		return numPoints;
	}


	public void setNumPoints(int numPoints) {
		this.numPoints = numPoints;
	}


	public int[] getParts() {
		return parts;
	}


	public void setParts(int[] parts) {
		this.parts = parts;
	}

	public List<List<Point>> getPartsList() {
		return partsList;
	}
	
	@Override
	public ShapeTypeEnum getType() {
		return ShapeTypeEnum.POLYGON;
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		
		sb.append("bbox: ").append(bbox[0]).append(", ").append(bbox[1]).
			append(", ").append(bbox[2]).append(", ").append(bbox[3]).append("\n");
		
		sb.append("numParts:").append(numParts).append("\n");
		
		sb.append("numPoints:").append(numPoints).append("\n");
		
		sb.append("parts:");
		for (int i = 0; i < parts.length; i++) {
			sb.append(parts[i]).append(", ");
		}
		sb.append("\n");
		
		sb.append("pointList: ");
		int idx = 0;
		for (List<Point> list: partsList) {
			sb.append("\n").append(idx++).append(": ");
			
			for (Point point: list) {
				sb.append(point.toString()).append(", ");
			}
		}
		
		return sb.toString();
	}

}
