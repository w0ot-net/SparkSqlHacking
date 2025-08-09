package org.apache.datasketches.quantilescommon;

public interface QuantilesSketchIterator {
   long getWeight();

   boolean next();
}
