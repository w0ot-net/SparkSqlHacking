package org.datanucleus.store.scostore;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.datanucleus.state.ObjectProvider;

public interface ListStore extends CollectionStore {
   void add(ObjectProvider var1, Object var2, int var3, int var4);

   boolean addAll(ObjectProvider var1, Collection var2, int var3, int var4);

   Object remove(ObjectProvider var1, int var2, int var3);

   Object get(ObjectProvider var1, int var2);

   Object set(ObjectProvider var1, int var2, Object var3, boolean var4);

   List subList(ObjectProvider var1, int var2, int var3);

   int indexOf(ObjectProvider var1, Object var2);

   int lastIndexOf(ObjectProvider var1, Object var2);

   ListIterator listIterator(ObjectProvider var1);
}
