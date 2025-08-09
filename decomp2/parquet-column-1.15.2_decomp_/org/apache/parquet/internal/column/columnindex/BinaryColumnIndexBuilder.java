package org.apache.parquet.internal.column.columnindex;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.parquet.filter2.predicate.Statistics;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;
import org.apache.parquet.schema.PrimitiveType;

class BinaryColumnIndexBuilder extends ColumnIndexBuilder {
   private final List minValues = new ArrayList();
   private final List maxValues = new ArrayList();
   private final BinaryTruncator truncator;
   private final int truncateLength;

   private static Binary convert(ByteBuffer buffer) {
      return Binary.fromReusedByteBuffer(buffer);
   }

   private static ByteBuffer convert(Binary value) {
      return value.toByteBuffer();
   }

   BinaryColumnIndexBuilder(PrimitiveType type, int truncateLength) {
      this.truncator = BinaryTruncator.getTruncator(type);
      this.truncateLength = truncateLength;
   }

   void addMinMaxFromBytes(ByteBuffer min, ByteBuffer max) {
      this.minValues.add(convert(min));
      this.maxValues.add(convert(max));
   }

   void addMinMax(Object min, Object max) {
      this.minValues.add(min == null ? null : this.truncator.truncateMin((Binary)min, this.truncateLength));
      this.maxValues.add(max == null ? null : this.truncator.truncateMax((Binary)max, this.truncateLength));
   }

   ColumnIndexBuilder.ColumnIndexBase createColumnIndex(PrimitiveType type) {
      BinaryColumnIndex columnIndex = new BinaryColumnIndex(type);
      columnIndex.minValues = (Binary[])this.minValues.toArray(new Binary[0]);
      columnIndex.maxValues = (Binary[])this.maxValues.toArray(new Binary[0]);
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
      return ((Binary)value).length();
   }

   public long getMinMaxSize() {
      long minSizesSum = this.minValues.stream().mapToLong(Binary::length).sum();
      long maxSizesSum = this.maxValues.stream().mapToLong(Binary::length).sum();
      return minSizesSum + maxSizesSum;
   }

   private static class BinaryColumnIndex extends ColumnIndexBuilder.ColumnIndexBase {
      private Binary[] minValues;
      private Binary[] maxValues;

      private BinaryColumnIndex(PrimitiveType type) {
         super(type);
      }

      ByteBuffer getMinValueAsBytes(int pageIndex) {
         return BinaryColumnIndexBuilder.convert(this.minValues[pageIndex]);
      }

      ByteBuffer getMaxValueAsBytes(int pageIndex) {
         return BinaryColumnIndexBuilder.convert(this.maxValues[pageIndex]);
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
         final Binary v = (Binary)value;
         return new ColumnIndexBuilder.ColumnIndexBase.ValueComparator() {
            int compareValueToMin(int arrayIndex) {
               return BinaryColumnIndex.this.comparator.compare(v, BinaryColumnIndex.this.minValues[arrayIndex]);
            }

            int compareValueToMax(int arrayIndex) {
               return BinaryColumnIndex.this.comparator.compare(v, BinaryColumnIndex.this.maxValues[arrayIndex]);
            }
         };
      }
   }
}
