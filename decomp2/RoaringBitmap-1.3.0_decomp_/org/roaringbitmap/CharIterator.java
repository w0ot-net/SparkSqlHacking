package org.roaringbitmap;

public interface CharIterator extends Cloneable {
   CharIterator clone();

   boolean hasNext();

   char next();

   int nextAsInt();

   void remove();
}
