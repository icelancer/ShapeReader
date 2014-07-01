package com.icelancer.shapefile.parser;

import java.io.DataInputStream;
import java.io.IOException;

import com.icelancer.shapefile.shape.Point;
import com.icelancer.shapefile.shape.Shape;

public class PointParser extends AbstractParser{
	
	public PointParser (DataInputStream di) {
		super(di);
	}
	
	@Override
	public Shape next() throws IOException {
		this.skipRecordHeader();		// skip record header and shapeType
		
		return readPoint();
	}
	
	public Point readPoint () throws IOException {
		
		double pointX = this.readLittleEndialDouble();
		double pointY = this.readLittleEndialDouble();
		
		return new Point(pointX, pointY);
	}

}
