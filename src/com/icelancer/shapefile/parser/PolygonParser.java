package com.icelancer.shapefile.parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.icelancer.shapefile.shape.Point;
import com.icelancer.shapefile.shape.Polygon;
import com.icelancer.shapefile.shape.Shape;
/**
 * 폴리곤은 하나 이상의 ring으로 구성. 하나의 ring은 4개 이상의 점으로 구성된 폐곡선이다.
 * 하나의 폴리곤 내에서는 교차할 수 없으며, 여러 개의 외곽 ring을 가질 수 있다.
 **/
public class PolygonParser extends AbstractParser {
	private PointParser pointParser;
	
	public PolygonParser(DataInputStream di) {
		super(di);
		
		this.pointParser = new PointParser(di);
	}

	@Override
	public Shape next() throws IOException {
		int i;

		this.skipRecordHeader();
		
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
		
		return new Polygon(bbox, numParts, numPoints, parts, partsList);
	}

}
