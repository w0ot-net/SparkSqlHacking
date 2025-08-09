package org.glassfish.jersey.internal.guava;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HashBasedTable extends StandardTable {
   private static final long serialVersionUID = 0L;

   private HashBasedTable(Map backingMap, Factory factory) {
      super(backingMap, factory);
   }

   public static HashBasedTable create() {
      return new HashBasedTable(new HashMap(), new Factory(0));
   }

   public boolean contains(Object rowKey, Object columnKey) {
      return super.contains(rowKey, columnKey);
   }

   public boolean containsColumn(Object columnKey) {
      return super.containsColumn(columnKey);
   }

   public boolean containsValue(Object value) {
      return super.containsValue(value);
   }

   public Object get(Object rowKey, Object columnKey) {
      return super.get(rowKey, columnKey);
   }

   public boolean equals(Object obj) {
      return super.equals(obj);
   }

   private static class Factory implements Supplier, Serializable {
      private static final long serialVersionUID = 0L;
      final int expectedSize;

      Factory(int expectedSize) {
         this.expectedSize = expectedSize;
      }

      public Map get() {
         return Maps.newHashMapWithExpectedSize(this.expectedSize);
      }
   }
}
