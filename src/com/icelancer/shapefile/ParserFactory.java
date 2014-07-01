package com.icelancer.shapefile;

import java.io.DataInputStream;

import com.icelancer.shapefile.parser.Parser;
import com.icelancer.shapefile.parser.PointParser;
import com.icelancer.shapefile.parser.PolygonParser;
import com.icelancer.shapefile.parser.PolylineParser;

public class ParserFactory {
	public static Parser createParser (ShapeTypeEnum type, DataInputStream di) {
		
		switch (type) {
		case POINT:
			return new PointParser(di);
		case POLYLINE:
			return new PolylineParser(di);
		case POLYGON:
			return new PolygonParser(di);
		default:
			return null;
		}
	} 
}
