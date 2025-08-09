package org.apache.parquet.column.statistics;

import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

public class IntStatistics extends Statistics {
   private static final PrimitiveType DEFAULT_FAKE_TYPE;
   private int max;
   private int min;

   /** @deprecated */
   @Deprecated
   public IntStatistics() {
      this(DEFAULT_FAKE_TYPE);
   }

   IntStatistics(PrimitiveType type) {
      super(type);
   }

   private IntStatistics(IntStatistics other) {
      super(other.type());
      if (other.hasNonNullValue()) {
         this.initializeStats(other.min, other.max);
      }

      this.setNumNulls(other.getNumNulls());
   }

   public void updateStats(int value) {
      if (!this.hasNonNullValue()) {
         this.initializeStats(value, value);
      } else {
         this.updateStats(value, value);
      }

   }

   public void mergeStatisticsMinMax(Statistics stats) {
      IntStatistics intStats = (IntStatistics)stats;
      if (!this.hasNonNullValue()) {
         this.initializeStats(intStats.getMin(), intStats.getMax());
      } else {
         this.updateStats(intStats.getMin(), intStats.getMax());
      }

   }

   public void setMinMaxFromBytes(byte[] minBytes, byte[] maxBytes) {
      this.max = BytesUtils.bytesToInt(maxBytes);
      this.min = BytesUtils.bytesToInt(minBytes);
      this.markAsNotEmpty();
   }

   public byte[] getMaxBytes() {
      return BytesUtils.intToBytes(this.max);
   }

   public byte[] getMinBytes() {
      return BytesUtils.intToBytes(this.min);
   }

   String stringify(Integer value) {
      return this.stringifier.stringify(value);
   }

   public boolean isSmallerThan(long size) {
      return !this.hasNonNullValue() || 8L < size;
   }

   public void updateStats(int min_value, int max_value) {
      if (this.comparator().compare(this.min, min_value) > 0) {
         this.min = min_value;
      }

      if (this.comparator().compare(this.max, max_value) < 0) {
         this.max = max_value;
      }

   }

   public void initializeStats(int min_value, int max_value) {
      this.min = min_value;
      this.max = max_value;
      this.markAsNotEmpty();
   }

   public Integer genericGetMin() {
      return this.min;
   }

   public Integer genericGetMax() {
      return this.max;
   }

   public int compareMinToValue(int value) {
      return this.comparator().compare(this.min, value);
   }

   public int compareMaxToValue(int value) {
      return this.comparator().compare(this.max, value);
   }

   public int getMax() {
      return this.max;
   }

   public int getMin() {
      return this.min;
   }

   public void setMinMax(int min, int max) {
      this.max = max;
      this.min = min;
      this.markAsNotEmpty();
   }

   public IntStatistics copy() {
      return new IntStatistics(this);
   }

   static {
      DEFAULT_FAKE_TYPE = (PrimitiveType)Types.optional(PrimitiveType.PrimitiveTypeName.INT32).named("fake_int32_type");
   }
}
