package org.datanucleus.store.types;

public interface SCOCollection extends SCOContainer {
   void updateEmbeddedElement(Object var1, int var2, Object var3, boolean var4);

   boolean remove(Object var1, boolean var2);
}
