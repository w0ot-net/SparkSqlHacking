package com.clearspring.analytics.stream.cardinality;

import java.io.IOException;

public interface ICardinality {
   boolean offer(Object var1);

   boolean offerHashed(long var1);

   boolean offerHashed(int var1);

   long cardinality();

   int sizeof();

   byte[] getBytes() throws IOException;

   ICardinality merge(ICardinality... var1) throws CardinalityMergeException;
}
