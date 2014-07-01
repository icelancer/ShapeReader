package com.icelancer.shapefile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.icelancer.NotSupportedFileFormat;
import com.icelancer.shapefile.parser.Parser;
import com.icelancer.shapefile.shape.Shape;

public class ShapeReader {
	private DataInputStream di;
	private ShapeTypeEnum shapeType;
	private Parser parser;
	
	private double[] bbox;
	
	
	public ShapeReader (InputStream input) throws FileNotFoundException, NotSupportedFileFormat {
		byte[] buffer = new byte[4];
		
		di = new DataInputStream(new BufferedInputStream(input));
		
		try {
			int fileCode = di.readInt();
			
			if (fileCode != 9994) {
				throw new NotSupportedFileFormat("NOT SHP File Format");
			}
			
			di.skipBytes(28);
			
			di.read(buffer);
			
			int type = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
			this.shapeType = getShapeType(type);
			this.bbox = parseBBox(di);
			
			di.skipBytes(32);
			this.parser = ParserFactory.createParser(this.shapeType, di);
			
		} catch (IOException e) {
			e.printStackTrace();
			this.close();
		}
	}
	
	public double[] getBBox () {
		return this.bbox;
	}
	
	private double[] parseBBox (DataInputStream di) throws IOException {
		double minX = readDouble(di);
		double minY = readDouble(di);
		double maxX = readDouble(di);
		double maxY = readDouble(di);
		
		double bbox[] = {minX, minY, maxX, maxY};
		
		return bbox;
	}
	
	private double readDouble (DataInputStream dis) throws IOException {
		byte buffer[] = new byte[8];
		di.read(buffer);
		
		return convertBytesToDouble(buffer);
	}
	
	private double convertBytesToDouble (byte[] buffer) {
		return ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getDouble();
	}
	
	public boolean hasNext () throws IOException {
		return this.parser.hasNext();
	}
	
	public Shape next () throws IOException {
		return this.parser.next();
	}
	
	private ShapeTypeEnum getShapeType (int type) {
		switch (type) {
		case 0:
			return ShapeTypeEnum.NULL;
		case 1:
			return ShapeTypeEnum.POINT;
		case 3:
			return ShapeTypeEnum.POLYLINE;
		case 5:
			return ShapeTypeEnum.POLYGON;
		case 8:
			return ShapeTypeEnum.MULTIPOINT;
		case 11:
			return ShapeTypeEnum.POINTZ;
		case 13:
			return ShapeTypeEnum.POLYLINEZ;
		case 15:
			return ShapeTypeEnum.POLYGONZ;
		case 18:
			return ShapeTypeEnum.MULTIPOINTZ;
		case 21:
			return ShapeTypeEnum.POINTM;
		case 23:
			return ShapeTypeEnum.POLYLINEM;
		case 25:
			return ShapeTypeEnum.POLYGONM;
		case 28:
			return ShapeTypeEnum.MULTIPOINTM;
		case 31:
			return ShapeTypeEnum.MULTIPATCH;
		}
		
		return null;
	}
	
	public void close () {
		this.parser.close();
	}
}
