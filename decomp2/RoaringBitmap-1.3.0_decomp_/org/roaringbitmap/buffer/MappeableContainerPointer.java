package org.roaringbitmap.buffer;

public interface MappeableContainerPointer extends Comparable, Cloneable {
   void advance();

   MappeableContainerPointer clone();

   int getCardinality();

   MappeableContainer getContainer();

   int getSizeInBytes();

   boolean hasContainer();

   boolean isBitmapContainer();

   boolean isRunContainer();

   char key();

   void previous();
}
