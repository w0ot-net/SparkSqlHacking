package org.apache.curator.framework.recipes.atomic;

public interface AtomicValue {
   boolean succeeded();

   Object preValue();

   Object postValue();

   AtomicStats getStats();
}
