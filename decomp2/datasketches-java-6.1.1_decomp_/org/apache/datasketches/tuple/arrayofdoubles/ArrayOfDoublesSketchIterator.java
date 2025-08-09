package org.apache.datasketches.tuple.arrayofdoubles;

public interface ArrayOfDoublesSketchIterator {
   boolean next();

   long getKey();

   double[] getValues();
}
