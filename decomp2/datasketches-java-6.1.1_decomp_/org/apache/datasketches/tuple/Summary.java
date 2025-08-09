package org.apache.datasketches.tuple;

public interface Summary {
   Summary copy();

   byte[] toByteArray();
}
