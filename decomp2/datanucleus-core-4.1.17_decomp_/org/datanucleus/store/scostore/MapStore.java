package org.datanucleus.store.scostore;

import java.util.Map;
import org.datanucleus.state.ObjectProvider;

public interface MapStore extends Store {
   boolean keysAreEmbedded();

   boolean keysAreSerialised();

   boolean valuesAreEmbedded();

   boolean valuesAreSerialised();

   boolean containsValue(ObjectProvider var1, Object var2);

   boolean containsKey(ObjectProvider var1, Object var2);

   Object get(ObjectProvider var1, Object var2);

   Object put(ObjectProvider var1, Object var2, Object var3);

   void putAll(ObjectProvider var1, Map var2);

   Object remove(ObjectProvider var1, Object var2);

   Object remove(ObjectProvider var1, Object var2, Object var3);

   void clear(ObjectProvider var1);

   SetStore keySetStore();

   CollectionStore valueCollectionStore();

   SetStore entrySetStore();

   boolean updateEmbeddedKey(ObjectProvider var1, Object var2, int var3, Object var4);

   boolean updateEmbeddedValue(ObjectProvider var1, Object var2, int var3, Object var4);
}
