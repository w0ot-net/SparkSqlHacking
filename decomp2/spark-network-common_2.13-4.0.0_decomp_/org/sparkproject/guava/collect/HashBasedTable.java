package org.sparkproject.guava.collect;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Supplier;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
public class HashBasedTable extends StandardTable {
   private static final long serialVersionUID = 0L;

   public static HashBasedTable create() {
      return new HashBasedTable(new LinkedHashMap(), new Factory(0));
   }

   public static HashBasedTable create(int expectedRows, int expectedCellsPerRow) {
      CollectPreconditions.checkNonnegative(expectedCellsPerRow, "expectedCellsPerRow");
      Map<R, Map<C, V>> backingMap = Maps.newLinkedHashMapWithExpectedSize(expectedRows);
      return new HashBasedTable(backingMap, new Factory(expectedCellsPerRow));
   }

   public static HashBasedTable create(Table table) {
      HashBasedTable<R, C, V> result = create();
      result.putAll(table);
      return result;
   }

   HashBasedTable(Map backingMap, Factory factory) {
      super(backingMap, factory);
   }

   private static class Factory implements Supplier, Serializable {
      final int expectedSize;
      private static final long serialVersionUID = 0L;

      Factory(int expectedSize) {
         this.expectedSize = expectedSize;
      }

      public Map get() {
         return Maps.newLinkedHashMapWithExpectedSize(this.expectedSize);
      }
   }
}
