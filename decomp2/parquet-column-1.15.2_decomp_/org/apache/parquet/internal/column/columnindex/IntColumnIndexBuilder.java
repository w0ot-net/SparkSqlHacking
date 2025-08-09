package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.parquet.filter2.predicate.Statistics;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveType;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntList;

class IntColumnIndexBuilder extends ColumnIndexBuilder {
   private final IntList minValues = new IntArrayList();
   private final IntList maxValues = new IntArrayList();

   private static int convert(ByteBuffer buffer) {
      return buffer.order(ByteOrder.LITTLE_ENDIAN).getInt(0);
   }

   private static ByteBuffer convert(int value) {
      return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(0, value);
   }

   void addMinMaxFromBytes(ByteBuffer min, ByteBuffer max) {
      this.minValues.add(convert(min));
      this.maxValues.add(convert(max));
   }

   void addMinMax(Object min, Object max) {
      this.minValues.add((Integer)min);
      this.maxValues.add((Integer)max);
   }

   ColumnIndexBuilder.ColumnIndexBase createColumnIndex(PrimitiveType type) {
      IntColumnIndex columnIndex = new IntColumnIndex(type);
      columnIndex.minValues = this.minValues.toIntArray();
      columnIndex.maxValues = this.maxValues.toIntArray();
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
      return 4;
   }

   public long getMinMaxSize() {
      return (long)this.minValues.size() * 4L + (long)this.maxValues.size() * 4L;
   }

   private static class IntColumnIndex extends ColumnIndexBuilder.ColumnIndexBase {
      private int[] minValues;
      private int[] maxValues;

      private IntColumnIndex(PrimitiveType type) {
         super(type);
      }

      ByteBuffer getMinValueAsBytes(int pageIndex) {
         return IntColumnIndexBuilder.convert(this.minValues[pageIndex]);
      }

      ByteBuffer getMaxValueAsBytes(int pageIndex) {
         return IntColumnIndexBuilder.convert(this.maxValues[pageIndex]);
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
         final int v = (Integer)value;
         return new ColumnIndexBuilder.ColumnIndexBase.ValueComparator() {
            int compareValueToMin(int arrayIndex) {
               return IntColumnIndex.this.comparator.compare(v, IntColumnIndex.this.minValues[arrayIndex]);
            }

            int compareValueToMax(int arrayIndex) {
               return IntColumnIndex.this.comparator.compare(v, IntColumnIndex.this.maxValues[arrayIndex]);
            }
         };
      }
   }
}
