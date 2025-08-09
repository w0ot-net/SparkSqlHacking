package org.datanucleus.store.types;

public interface SCOContainer extends SCO {
   void load();

   boolean isLoaded();

   void setValue(Object var1);
}
