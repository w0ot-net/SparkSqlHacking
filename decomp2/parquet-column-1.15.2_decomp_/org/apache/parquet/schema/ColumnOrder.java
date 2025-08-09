package org.apache.parquet.schema;

import java.util.Objects;

public class ColumnOrder {
   private static final ColumnOrder UNDEFINED_COLUMN_ORDER;
   private static final ColumnOrder TYPE_DEFINED_COLUMN_ORDER;
   private final ColumnOrderName columnOrderName;

   public static ColumnOrder undefined() {
      return UNDEFINED_COLUMN_ORDER;
   }

   public static ColumnOrder typeDefined() {
      return TYPE_DEFINED_COLUMN_ORDER;
   }

   private ColumnOrder(ColumnOrderName columnOrderName) {
      this.columnOrderName = (ColumnOrderName)Objects.requireNonNull(columnOrderName, "columnOrderName cannot be null");
   }

   public ColumnOrderName getColumnOrderName() {
      return this.columnOrderName;
   }

   public boolean equals(Object obj) {
      if (obj instanceof ColumnOrder) {
         return this.columnOrderName == ((ColumnOrder)obj).columnOrderName;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.columnOrderName.hashCode();
   }

   public String toString() {
      return this.columnOrderName.toString();
   }

   static {
      UNDEFINED_COLUMN_ORDER = new ColumnOrder(ColumnOrder.ColumnOrderName.UNDEFINED);
      TYPE_DEFINED_COLUMN_ORDER = new ColumnOrder(ColumnOrder.ColumnOrderName.TYPE_DEFINED_ORDER);
   }

   public static enum ColumnOrderName {
      UNDEFINED,
      TYPE_DEFINED_ORDER;
   }
}
