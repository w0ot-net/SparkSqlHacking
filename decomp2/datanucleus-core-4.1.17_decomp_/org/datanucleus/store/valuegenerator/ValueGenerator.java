package org.datanucleus.store.valuegenerator;

public interface ValueGenerator {
   String getName();

   Object next();

   void allocate(int var1);

   Object current();

   long nextValue();

   long currentValue();
}
