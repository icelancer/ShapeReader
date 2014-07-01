package com.icelancer.shapefile.parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class AbstractParser implements Parser{
	private DataInputStream di;
	
	public AbstractParser (DataInputStream di) {
		this.di = di;
	}
	
	public boolean hasNext() throws IOException {
		return this.getInputStream().available() > 0;
	}
	
	public void close() {
		if (this.di != null) {
			try {
				di.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				this.di = null;
			}
		}
	}
	
	public void skipRecordHeader () throws IOException {
		this.di.skipBytes(12);
	}
	
	public double readLittleEndialDouble () throws IOException {
		byte[] buffer = new byte[8];
		
		this.di.read(buffer);
		
		return this.toLittleEndianDouble(buffer);
	}
	
	public int readLittleEndialInt () throws IOException {
		byte[] buffer = new byte[4];
		
		this.di.read(buffer);
		
		return this.toLittleEndianInt(buffer);
	}
	
	public double toLittleEndianDouble (byte[] buffer) {
		return ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getDouble();
	}
	
	public int toLittleEndianInt (byte[] buffer) {
		return ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
	}
	
	public DataInputStream getInputStream () {
		return this.di;
	}
	
}
