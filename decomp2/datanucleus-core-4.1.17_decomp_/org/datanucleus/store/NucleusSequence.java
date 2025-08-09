package org.datanucleus.store;

public interface NucleusSequence {
   void allocate(int var1);

   Object current();

   long currentValue();

   String getName();

   Object next();

   long nextValue();
}
