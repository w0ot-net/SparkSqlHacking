package org.apache.parquet.column.statistics;

import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

public class LongStatistics extends Statistics {
   private static final PrimitiveType DEFAULT_FAKE_TYPE;
   private long max;
   private long min;

   /** @deprecated */
   @Deprecated
   public LongStatistics() {
      this(DEFAULT_FAKE_TYPE);
   }

   LongStatistics(PrimitiveType type) {
      super(type);
   }

   private LongStatistics(LongStatistics other) {
      super(other.type());
      if (other.hasNonNullValue()) {
         this.initializeStats(other.min, other.max);
      }

      this.setNumNulls(other.getNumNulls());
   }

   public void updateStats(long value) {
      if (!this.hasNonNullValue()) {
         this.initializeStats(value, value);
      } else {
         this.updateStats(value, value);
      }

   }

   public void mergeStatisticsMinMax(Statistics stats) {
      LongStatistics longStats = (LongStatistics)stats;
      if (!this.hasNonNullValue()) {
         this.initializeStats(longStats.getMin(), longStats.getMax());
      } else {
         this.updateStats(longStats.getMin(), longStats.getMax());
      }

   }

   public void setMinMaxFromBytes(byte[] minBytes, byte[] maxBytes) {
      this.max = BytesUtils.bytesToLong(maxBytes);
      this.min = BytesUtils.bytesToLong(minBytes);
      this.markAsNotEmpty();
   }

   public byte[] getMaxBytes() {
      return BytesUtils.longToBytes(this.max);
   }

   public byte[] getMinBytes() {
      return BytesUtils.longToBytes(this.min);
   }

   String stringify(Long value) {
      return this.stringifier.stringify(value);
   }

   public boolean isSmallerThan(long size) {
      return !this.hasNonNullValue() || 16L < size;
   }

   public void updateStats(long min_value, long max_value) {
      if (this.comparator().compare(this.min, min_value) > 0) {
         this.min = min_value;
      }

      if (this.comparator().compare(this.max, max_value) < 0) {
         this.max = max_value;
      }

   }

   public void initializeStats(long min_value, long max_value) {
      this.min = min_value;
      this.max = max_value;
      this.markAsNotEmpty();
   }

   public Long genericGetMin() {
      return this.min;
   }

   public Long genericGetMax() {
      return this.max;
   }

   public int compareMinToValue(long value) {
      return this.comparator().compare(this.min, value);
   }

   public int compareMaxToValue(long value) {
      return this.comparator().compare(this.max, value);
   }

   public long getMax() {
      return this.max;
   }

   public long getMin() {
      return this.min;
   }

   public void setMinMax(long min, long max) {
      this.max = max;
      this.min = min;
      this.markAsNotEmpty();
   }

   public LongStatistics copy() {
      return new LongStatistics(this);
   }

   static {
      DEFAULT_FAKE_TYPE = (PrimitiveType)Types.optional(PrimitiveType.PrimitiveTypeName.INT64).named("fake_int64_type");
   }
}
