package org.apache.parquet.column.statistics;

import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

public class DoubleStatistics extends Statistics {
   private static final PrimitiveType DEFAULT_FAKE_TYPE;
   private double max;
   private double min;

   /** @deprecated */
   @Deprecated
   public DoubleStatistics() {
      this(DEFAULT_FAKE_TYPE);
   }

   DoubleStatistics(PrimitiveType type) {
      super(type);
   }

   private DoubleStatistics(DoubleStatistics other) {
      super(other.type());
      if (other.hasNonNullValue()) {
         this.initializeStats(other.min, other.max);
      }

      this.setNumNulls(other.getNumNulls());
   }

   public void updateStats(double value) {
      if (!this.hasNonNullValue()) {
         this.initializeStats(value, value);
      } else {
         this.updateStats(value, value);
      }

   }

   public void mergeStatisticsMinMax(Statistics stats) {
      DoubleStatistics doubleStats = (DoubleStatistics)stats;
      if (!this.hasNonNullValue()) {
         this.initializeStats(doubleStats.getMin(), doubleStats.getMax());
      } else {
         this.updateStats(doubleStats.getMin(), doubleStats.getMax());
      }

   }

   public void setMinMaxFromBytes(byte[] minBytes, byte[] maxBytes) {
      this.max = Double.longBitsToDouble(BytesUtils.bytesToLong(maxBytes));
      this.min = Double.longBitsToDouble(BytesUtils.bytesToLong(minBytes));
      this.markAsNotEmpty();
   }

   public byte[] getMaxBytes() {
      return BytesUtils.longToBytes(Double.doubleToLongBits(this.max));
   }

   public byte[] getMinBytes() {
      return BytesUtils.longToBytes(Double.doubleToLongBits(this.min));
   }

   String stringify(Double value) {
      return this.stringifier.stringify(value);
   }

   public boolean isSmallerThan(long size) {
      return !this.hasNonNullValue() || 16L < size;
   }

   public void updateStats(double min_value, double max_value) {
      if (this.comparator().compare(this.min, min_value) > 0) {
         this.min = min_value;
      }

      if (this.comparator().compare(this.max, max_value) < 0) {
         this.max = max_value;
      }

   }

   public void initializeStats(double min_value, double max_value) {
      this.min = min_value;
      this.max = max_value;
      this.markAsNotEmpty();
   }

   public Double genericGetMin() {
      return this.min;
   }

   public Double genericGetMax() {
      return this.max;
   }

   public int compareMinToValue(double value) {
      return this.comparator().compare(this.min, value);
   }

   public int compareMaxToValue(double value) {
      return this.comparator().compare(this.max, value);
   }

   public double getMax() {
      return this.max;
   }

   public double getMin() {
      return this.min;
   }

   public void setMinMax(double min, double max) {
      this.max = max;
      this.min = min;
      this.markAsNotEmpty();
   }

   public DoubleStatistics copy() {
      return new DoubleStatistics(this);
   }

   static {
      DEFAULT_FAKE_TYPE = (PrimitiveType)Types.optional(PrimitiveType.PrimitiveTypeName.DOUBLE).named("fake_double_type");
   }
}
