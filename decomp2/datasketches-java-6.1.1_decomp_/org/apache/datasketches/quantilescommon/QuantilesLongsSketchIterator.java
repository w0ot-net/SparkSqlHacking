package org.apache.datasketches.quantilescommon;

public interface QuantilesLongsSketchIterator extends QuantilesSketchIterator {
   long getQuantile();
}
