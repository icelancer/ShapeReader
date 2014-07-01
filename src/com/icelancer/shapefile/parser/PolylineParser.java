package com.icelancer.shapefile.parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.icelancer.shapefile.shape.Point;
import com.icelancer.shapefile.shape.Polyline;
import com.icelancer.shapefile.shape.Shape;

public class PolylineParser extends AbstractParser {
	private PointParser pointParser;
	
	public PolylineParser (DataInputStream di) {
		super(di);
		
		this.pointParser = new PointParser(di);
	}

	@Override
	public Shape next() throws IOException {
		int i;
		
		this.skipRecordHeader();		// skip record header and shapeType
		
		double xMin = this.readLittleEndialDouble();
		double yMin = this.readLittleEndialDouble();
		double xMax = this.readLittleEndialDouble();
		double yMax = this.readLittleEndialDouble();
		
		double[] bbox = {xMin, yMin, xMax, yMax};
		
		int numParts = this.readLittleEndialInt();
		int numPoints = this.readLittleEndialInt();
		
		int[] parts = new int[numParts];
		
		for (i = 0; i < numParts; i++) {
			parts[i] = this.readLittleEndialInt();
		}
		
		List<List<Point>> partsList = new ArrayList<>();
		ArrayList<Point> list = null;
		int partIdx = 0;
		
		for (i = 0; i < numPoints; i++) {
			if (parts[partIdx] == i) {
				list = new ArrayList<Point>();
				partsList.add(list);

				partIdx++;
				
				if (partIdx >= numParts) {
					partIdx = numParts - 1;
				}
			}
			
			Point point = (Point) pointParser.readPoint();
			list.add(point);
		}
		
		return new Polyline(bbox, numParts, numPoints, parts, partsList);
	}

}
