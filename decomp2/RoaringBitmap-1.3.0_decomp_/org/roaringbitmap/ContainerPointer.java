package org.roaringbitmap;

public interface ContainerPointer extends Comparable, Cloneable {
   void advance();

   ContainerPointer clone();

   int getCardinality();

   Container getContainer();

   boolean isBitmapContainer();

   boolean isRunContainer();

   char key();
}
