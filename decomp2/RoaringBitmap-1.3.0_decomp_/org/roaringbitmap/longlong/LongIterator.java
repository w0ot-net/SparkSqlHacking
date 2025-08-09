package org.roaringbitmap.longlong;

public interface LongIterator extends Cloneable {
   LongIterator clone();

   boolean hasNext();

   long next();
}
