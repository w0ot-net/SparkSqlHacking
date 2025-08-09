package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import org.apache.parquet.filter2.predicate.Statistics;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveType;
import shaded.parquet.it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.booleans.BooleanList;

class BooleanColumnIndexBuilder extends ColumnIndexBuilder {
   private final BooleanList minValues = new BooleanArrayList();
   private final BooleanList maxValues = new BooleanArrayList();

   private static boolean convert(ByteBuffer buffer) {
      return buffer.get(0) != 0;
   }

   private static ByteBuffer convert(boolean value) {
      return ByteBuffer.allocate(1).put(0, (byte)(value ? 1 : 0));
   }

   void addMinMaxFromBytes(ByteBuffer min, ByteBuffer max) {
      this.minValues.add(convert(min));
      this.maxValues.add(convert(max));
   }

   void addMinMax(Object min, Object max) {
      this.minValues.add((Boolean)min);
      this.maxValues.add((Boolean)max);
   }

   ColumnIndexBuilder.ColumnIndexBase createColumnIndex(PrimitiveType type) {
      BooleanColumnIndex columnIndex = new BooleanColumnIndex(type);
      columnIndex.minValues = this.minValues.toBooleanArray();
      columnIndex.maxValues = this.maxValues.toBooleanArray();
      return columnIndex;
   }

   void clearMinMax() {
      this.minValues.clear();
      this.maxValues.clear();
   }

   int compareMinValues(PrimitiveComparator comparator, int index1, int index2) {
      return comparator.compare(this.minValues.get(index1), this.minValues.get(index2));
   }

   int compareMaxValues(PrimitiveComparator comparator, int index1, int index2) {
      return comparator.compare(this.maxValues.get(index1), this.maxValues.get(index2));
   }

   int sizeOf(Object value) {
      return 1;
   }

   public long getMinMaxSize() {
      return (long)(this.minValues.size() + this.maxValues.size());
   }

   private static class BooleanColumnIndex extends ColumnIndexBuilder.ColumnIndexBase {
      private boolean[] minValues;
      private boolean[] maxValues;

      private BooleanColumnIndex(PrimitiveType type) {
         super(type);
      }

      ByteBuffer getMinValueAsBytes(int pageIndex) {
         return BooleanColumnIndexBuilder.convert(this.minValues[pageIndex]);
      }

      ByteBuffer getMaxValueAsBytes(int pageIndex) {
         return BooleanColumnIndexBuilder.convert(this.maxValues[pageIndex]);
      }

      String getMinValueAsString(int pageIndex) {
         return this.stringifier.stringify(this.minValues[pageIndex]);
      }

      String getMaxValueAsString(int pageIndex) {
         return this.stringifier.stringify(this.maxValues[pageIndex]);
      }

      Statistics createStats(int arrayIndex) {
         return new Statistics(this.minValues[arrayIndex], this.maxValues[arrayIndex], this.comparator);
      }

      ColumnIndexBuilder.ColumnIndexBase.ValueComparator createValueComparator(Object value) {
         final boolean v = (Boolean)value;
         return new ColumnIndexBuilder.ColumnIndexBase.ValueComparator() {
            int compareValueToMin(int arrayIndex) {
               return BooleanColumnIndex.this.comparator.compare(v, BooleanColumnIndex.this.minValues[arrayIndex]);
            }

            int compareValueToMax(int arrayIndex) {
               return BooleanColumnIndex.this.comparator.compare(v, BooleanColumnIndex.this.maxValues[arrayIndex]);
            }
         };
      }
   }
}
