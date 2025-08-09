package org.roaringbitmap;

public interface IntIterator extends Cloneable {
   IntIterator clone();

   boolean hasNext();

   int next();
}
