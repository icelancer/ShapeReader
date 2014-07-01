package com.icelancer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.icelancer.shapefile.ShapeReader;
import com.icelancer.shapefile.shape.Shape;

public class Main {
	public static void main(String[] args) throws IOException {
		ShapeReader reader = null;
		try {
			reader = new ShapeReader(new FileInputStream("shapefile/Polygon.shp"));
			int i = 1;
			while(reader.hasNext()) {
				Shape shape = reader.next();
				
				System.out.println("======== " + i++ + "===========");
				System.out.println(shape);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotSupportedFileFormat e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
}


//DataInputStream di = null;
//byte buffer[] = new byte[4];
//byte buffer2[] = new byte[8];
//
//
//try {
//	di = new DataInputStream(new FileInputStream("shapefile/point.shp"));
//	// File Code
//	System.out.println("File Code:\t" + di.readInt());
//	di.skipBytes(20);
//
//	// File Length
//	System.out.println("File Length:\t" + di.readInt());
//	
//	// Version
//	di.read(buffer);
//	System.out.println("Version:\t" + java.nio.ByteBuffer.wrap(buffer).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt());
//	
//	// Shape Type
//	di.read(buffer);
//	System.out.println("Shape Type:\t" + java.nio.ByteBuffer.wrap(buffer).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt());
//	
//	// Bounding Box Xmin, if shapefile is empty, 
//	di.read(buffer2);
//	System.out.println("BBox minX:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	// Bounding Box Ymin
//	di.read(buffer2);
//	System.out.println("BBox minY:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	// Bounding Box Xmax
//	di.read(buffer2);
//	System.out.println("BBox maxX:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	// Bounding Box Ymax
//	di.read(buffer2);
//	System.out.println("BBox maxY:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	// Bounding Box Zmin
//	di.read(buffer2);
//	System.out.println("BBox Zmin:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	// Bounding Box Zmax
//	di.read(buffer2);
//	System.out.println("BBox Zmax:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	// Bounding Box Mmin
//	di.read(buffer2);
//	System.out.println("BBox Mmin:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	// Bounding Box Mmax
//	di.read(buffer2);
//	
//	System.out.println("BBox Mmax:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	
//	
//	//===
//	
//	int i = 1;
//	while(di.available() > 0) {
//		i++;
//		System.out.println("\nRecord Number:\t" + di.readInt());
//		System.out.println("Content Length:\t" + di.readInt());
//		// Shape Type
//		di.read(buffer);
//		System.out.println("Shape Type:\t" + java.nio.ByteBuffer.wrap(buffer).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt());
//		
//		di.read(buffer2);
//		System.out.println("PointX:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//		
//		di.read(buffer2);
//		System.out.println("PointY:\t" + java.nio.ByteBuffer.wrap(buffer2).order(java.nio.ByteOrder.LITTLE_ENDIAN).getDouble());
//	}
//	
//	System.out.println("\ntotal: " + i);
////	System.out.println(di.readInt());
////	System.out.println(di.readInt());
//	
//} catch (FileNotFoundException e) {
//	e.printStackTrace();
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} finally {
//	try {
//		di.close();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}