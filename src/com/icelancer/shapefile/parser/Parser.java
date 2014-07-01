package com.icelancer.shapefile.parser;

import java.io.IOException;

import com.icelancer.shapefile.shape.Shape;

public interface Parser {
	public boolean hasNext() throws IOException;
	public Shape next() throws IOException;
	public void close();
}
