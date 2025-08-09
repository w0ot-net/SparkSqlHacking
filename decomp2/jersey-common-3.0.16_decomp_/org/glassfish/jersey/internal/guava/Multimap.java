package org.glassfish.jersey.internal.guava;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Multimap {
   int size();

   boolean containsKey(Object var1);

   boolean containsValue(Object var1);

   boolean containsEntry(Object var1, Object var2);

   boolean put(Object var1, Object var2);

   boolean remove(Object var1, Object var2);

   boolean putAll(Object var1, Iterable var2);

   Collection removeAll(Object var1);

   void clear();

   Collection get(Object var1);

   Set keySet();

   Collection values();

   Collection entries();

   Map asMap();

   boolean equals(Object var1);

   int hashCode();
}
