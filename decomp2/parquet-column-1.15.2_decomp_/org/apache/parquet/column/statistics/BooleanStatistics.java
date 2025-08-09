package org.apache.parquet.column.statistics;

import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

public class BooleanStatistics extends Statistics {
   private static final PrimitiveType DEFAULT_FAKE_TYPE;
   private boolean max;
   private boolean min;

   /** @deprecated */
   @Deprecated
   public BooleanStatistics() {
      this(DEFAULT_FAKE_TYPE);
   }

   BooleanStatistics(PrimitiveType type) {
      super(type);
   }

   private BooleanStatistics(BooleanStatistics other) {
      super(other.type());
      if (other.hasNonNullValue()) {
         this.initializeStats(other.min, other.max);
      }

      this.setNumNulls(other.getNumNulls());
   }

   public void updateStats(boolean value) {
      if (!this.hasNonNullValue()) {
         this.initializeStats(value, value);
      } else {
         this.updateStats(value, value);
      }

   }

   public void mergeStatisticsMinMax(Statistics stats) {
      BooleanStatistics boolStats = (BooleanStatistics)stats;
      if (!this.hasNonNullValue()) {
         this.initializeStats(boolStats.getMin(), boolStats.getMax());
      } else {
         this.updateStats(boolStats.getMin(), boolStats.getMax());
      }

   }

   public void setMinMaxFromBytes(byte[] minBytes, byte[] maxBytes) {
      this.max = BytesUtils.bytesToBool(maxBytes);
      this.min = BytesUtils.bytesToBool(minBytes);
      this.markAsNotEmpty();
   }

   public byte[] getMaxBytes() {
      return BytesUtils.booleanToBytes(this.max);
   }

   public byte[] getMinBytes() {
      return BytesUtils.booleanToBytes(this.min);
   }

   String stringify(Boolean value) {
      return this.stringifier.stringify(value);
   }

   public boolean isSmallerThan(long size) {
      return !this.hasNonNullValue() || 2L < size;
   }

   public void updateStats(boolean min_value, boolean max_value) {
      if (this.comparator().compare(this.min, min_value) > 0) {
         this.min = min_value;
      }

      if (this.comparator().compare(this.max, max_value) < 0) {
         this.max = max_value;
      }

   }

   public void initializeStats(boolean min_value, boolean max_value) {
      this.min = min_value;
      this.max = max_value;
      this.markAsNotEmpty();
   }

   public Boolean genericGetMin() {
      return this.min;
   }

   public Boolean genericGetMax() {
      return this.max;
   }

   public int compareMinToValue(boolean value) {
      return this.comparator().compare(this.min, value);
   }

   public int compareMaxToValue(boolean value) {
      return this.comparator().compare(this.max, value);
   }

   public boolean getMax() {
      return this.max;
   }

   public boolean getMin() {
      return this.min;
   }

   public void setMinMax(boolean min, boolean max) {
      this.max = max;
      this.min = min;
      this.markAsNotEmpty();
   }

   public BooleanStatistics copy() {
      return new BooleanStatistics(this);
   }

   static {
      DEFAULT_FAKE_TYPE = (PrimitiveType)Types.optional(PrimitiveType.PrimitiveTypeName.BOOLEAN).named("fake_boolean_type");
   }
}
