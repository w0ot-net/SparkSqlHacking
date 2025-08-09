package org.apache.arrow.vector.util;

import java.util.Collection;
import java.util.Set;

public interface MapWithOrdinal {
   Object getByOrdinal(int var1);

   int getOrdinal(Object var1);

   int size();

   boolean isEmpty();

   Object get(Object var1);

   Collection getAll(Object var1);

   boolean put(Object var1, Object var2, boolean var3);

   Collection values();

   boolean containsKey(Object var1);

   boolean remove(Object var1, Object var2);

   boolean removeAll(Object var1);

   void clear();

   Set keys();
}
