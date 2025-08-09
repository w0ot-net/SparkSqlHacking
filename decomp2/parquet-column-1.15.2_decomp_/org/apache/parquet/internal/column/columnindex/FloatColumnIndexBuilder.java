package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.parquet.filter2.predicate.Statistics;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveType;
import shaded.parquet.it.unimi.dsi.fastutil.floats.FloatArrayList;
import shaded.parquet.it.unimi.dsi.fastutil.floats.FloatList;

class FloatColumnIndexBuilder extends ColumnIndexBuilder {
   private final FloatList minValues = new FloatArrayList();
   private final FloatList maxValues = new FloatArrayList();
   private boolean invalid;

   private static float convert(ByteBuffer buffer) {
      return buffer.order(ByteOrder.LITTLE_ENDIAN).getFloat(0);
   }

   private static ByteBuffer convert(float value) {
      return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(0, value);
   }

   void addMinMaxFromBytes(ByteBuffer min, ByteBuffer max) {
      this.minValues.add(convert(min));
      this.maxValues.add(convert(max));
   }

   void addMinMax(Object min, Object max) {
      float fMin = (Float)min;
      float fMax = (Float)max;
      if (Float.isNaN(fMin) || Float.isNaN(fMax)) {
         this.invalid = true;
      }

      if (Float.compare(fMin, 0.0F) == 0) {
         fMin = -0.0F;
      }

      if (Float.compare(fMax, -0.0F) == 0) {
         fMax = 0.0F;
      }

      this.minValues.add(fMin);
      this.maxValues.add(fMax);
   }

   ColumnIndexBuilder.ColumnIndexBase createColumnIndex(PrimitiveType type) {
      if (this.invalid) {
         return null;
      } else {
         FloatColumnIndex columnIndex = new FloatColumnIndex(type);
         columnIndex.minValues = this.minValues.toFloatArray();
         columnIndex.maxValues = this.maxValues.toFloatArray();
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
      return 4;
   }

   public long getMinMaxSize() {
      return (long)this.minValues.size() * 4L + (long)this.maxValues.size() * 4L;
   }

   private static class FloatColumnIndex extends ColumnIndexBuilder.ColumnIndexBase {
      private float[] minValues;
      private float[] maxValues;

      private FloatColumnIndex(PrimitiveType type) {
         super(type);
      }

      ByteBuffer getMinValueAsBytes(int pageIndex) {
         return FloatColumnIndexBuilder.convert(this.minValues[pageIndex]);
      }

      ByteBuffer getMaxValueAsBytes(int pageIndex) {
         return FloatColumnIndexBuilder.convert(this.maxValues[pageIndex]);
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
         final float v = (Float)value;
         return new ColumnIndexBuilder.ColumnIndexBase.ValueComparator() {
            int compareValueToMin(int arrayIndex) {
               return FloatColumnIndex.this.comparator.compare(v, FloatColumnIndex.this.minValues[arrayIndex]);
            }

            int compareValueToMax(int arrayIndex) {
               return FloatColumnIndex.this.comparator.compare(v, FloatColumnIndex.this.maxValues[arrayIndex]);
            }
         };
      }
   }
}
