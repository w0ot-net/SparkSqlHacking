package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.parquet.filter2.predicate.Statistics;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveType;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.longs.LongList;

class LongColumnIndexBuilder extends ColumnIndexBuilder {
   private final LongList minValues = new LongArrayList();
   private final LongList maxValues = new LongArrayList();

   private static long convert(ByteBuffer buffer) {
      return buffer.order(ByteOrder.LITTLE_ENDIAN).getLong(0);
   }

   private static ByteBuffer convert(long value) {
      return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(0, value);
   }

   void addMinMaxFromBytes(ByteBuffer min, ByteBuffer max) {
      this.minValues.add(convert(min));
      this.maxValues.add(convert(max));
   }

   void addMinMax(Object min, Object max) {
      this.minValues.add((Long)min);
      this.maxValues.add((Long)max);
   }

   ColumnIndexBuilder.ColumnIndexBase createColumnIndex(PrimitiveType type) {
      LongColumnIndex columnIndex = new LongColumnIndex(type);
      columnIndex.minValues = this.minValues.toLongArray();
      columnIndex.maxValues = this.maxValues.toLongArray();
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
      return 8;
   }

   public long getMinMaxSize() {
      return (long)this.minValues.size() * 8L + (long)this.maxValues.size() * 8L;
   }

   private static class LongColumnIndex extends ColumnIndexBuilder.ColumnIndexBase {
      private long[] minValues;
      private long[] maxValues;

      private LongColumnIndex(PrimitiveType type) {
         super(type);
      }

      ByteBuffer getMinValueAsBytes(int pageIndex) {
         return LongColumnIndexBuilder.convert(this.minValues[pageIndex]);
      }

      ByteBuffer getMaxValueAsBytes(int pageIndex) {
         return LongColumnIndexBuilder.convert(this.maxValues[pageIndex]);
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
         final long v = (Long)value;
         return new ColumnIndexBuilder.ColumnIndexBase.ValueComparator() {
            int compareValueToMin(int arrayIndex) {
               return LongColumnIndex.this.comparator.compare(v, LongColumnIndex.this.minValues[arrayIndex]);
            }

            int compareValueToMax(int arrayIndex) {
               return LongColumnIndex.this.comparator.compare(v, LongColumnIndex.this.maxValues[arrayIndex]);
            }
         };
      }
   }
}
