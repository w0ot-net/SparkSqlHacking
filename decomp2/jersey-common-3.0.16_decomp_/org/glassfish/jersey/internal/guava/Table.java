package org.glassfish.jersey.internal.guava;

import java.util.Map;
import java.util.Set;

public interface Table {
   boolean contains(Object var1, Object var2);

   boolean containsRow(Object var1);

   boolean containsColumn(Object var1);

   boolean containsValue(Object var1);

   Object get(Object var1, Object var2);

   int size();

   boolean equals(Object var1);

   int hashCode();

   void clear();

   Object put(Object var1, Object var2, Object var3);

   void putAll(Table var1);

   Object remove(Object var1, Object var2);

   Map row(Object var1);

   Map column(Object var1);

   Set cellSet();

   Set rowKeySet();

   Set columnKeySet();

   Map rowMap();

   Map columnMap();

   public interface Cell {
      Object getRowKey();

      Object getColumnKey();

      Object getValue();

      boolean equals(Object var1);

      int hashCode();
   }
}
