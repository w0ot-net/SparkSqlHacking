package org.datanucleus.store.scostore;

import java.util.Collection;
import java.util.Iterator;
import org.datanucleus.state.ObjectProvider;

public interface CollectionStore extends Store {
   boolean hasOrderMapping();

   boolean updateEmbeddedElement(ObjectProvider var1, Object var2, int var3, Object var4);

   Iterator iterator(ObjectProvider var1);

   int size(ObjectProvider var1);

   boolean contains(ObjectProvider var1, Object var2);

   boolean add(ObjectProvider var1, Object var2, int var3);

   boolean addAll(ObjectProvider var1, Collection var2, int var3);

   boolean remove(ObjectProvider var1, Object var2, int var3, boolean var4);

   boolean removeAll(ObjectProvider var1, Collection var2, int var3);

   void clear(ObjectProvider var1);

   void update(ObjectProvider var1, Collection var2);
}
