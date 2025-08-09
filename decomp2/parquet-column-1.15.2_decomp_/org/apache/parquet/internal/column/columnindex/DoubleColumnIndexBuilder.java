package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.parquet.filter2.predicate.Statistics;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveType;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleList;

class DoubleColumnIndexBuilder extends ColumnIndexBuilder {
   private final DoubleList minValues = new DoubleArrayList();
   private final DoubleList maxValues = new DoubleArrayList();
   private boolean invalid;

   private static double convert(ByteBuffer buffer) {
      return buffer.order(ByteOrder.LITTLE_ENDIAN).getDouble(0);
   }

   private static ByteBuffer convert(double value) {
      return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(0, value);
   }

   void addMinMaxFromBytes(ByteBuffer min, ByteBuffer max) {
      this.minValues.add(convert(min));
      this.maxValues.add(convert(max));
   }

   void addMinMax(Object min, Object max) {
      double dMin = (Double)min;
      double dMax = (Double)max;
      if (Double.isNaN(dMin) || Double.isNaN(dMax)) {
         this.invalid = true;
      }

      if (Double.compare(dMin, (double)0.0F) == 0) {
         dMin = (double)-0.0F;
      }

      if (Double.compare(dMax, (double)-0.0F) == 0) {
         dMax = (double)0.0F;
      }

      this.minValues.add(dMin);
      this.maxValues.add(dMax);
   }

   ColumnIndexBuilder.ColumnIndexBase createColumnIndex(PrimitiveType type) {
      if (this.invalid) {
         return null;
      } else {
         DoubleColumnIndex columnIndex = new DoubleColumnIndex(type);
         columnIndex.minValues = this.minValues.toDoubleArray();
         columnIndex.maxValues = this.maxValues.toDoubleArray();
         return columnIndex;
      }
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

   private static class DoubleColumnIndex extends ColumnIndexBuilder.ColumnIndexBase {
      private double[] minValues;
      private double[] maxValues;

      private DoubleColumnIndex(PrimitiveType type) {
         super(type);
      }

      ByteBuffer getMinValueAsBytes(int pageIndex) {
         return DoubleColumnIndexBuilder.convert(this.minValues[pageIndex]);
      }

      ByteBuffer getMaxValueAsBytes(int pageIndex) {
         return DoubleColumnIndexBuilder.convert(this.maxValues[pageIndex]);
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
         final double v = (Double)value;
         return new ColumnIndexBuilder.ColumnIndexBase.ValueComparator() {
            int compareValueToMin(int arrayIndex) {
               return DoubleColumnIndex.this.comparator.compare(v, DoubleColumnIndex.this.minValues[arrayIndex]);
            }

            int compareValueToMax(int arrayIndex) {
               return DoubleColumnIndex.this.comparator.compare(v, DoubleColumnIndex.this.maxValues[arrayIndex]);
            }
         };
      }
   }
}
