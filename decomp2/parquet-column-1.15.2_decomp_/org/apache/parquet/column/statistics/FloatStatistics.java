package org.apache.parquet.column.statistics;

import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

public class FloatStatistics extends Statistics {
   private static final PrimitiveType DEFAULT_FAKE_TYPE;
   private float max;
   private float min;

   /** @deprecated */
   @Deprecated
   public FloatStatistics() {
      this(DEFAULT_FAKE_TYPE);
   }

   FloatStatistics(PrimitiveType type) {
      super(type);
   }

   private FloatStatistics(FloatStatistics other) {
      super(other.type());
      if (other.hasNonNullValue()) {
         this.initializeStats(other.min, other.max);
      }

      this.setNumNulls(other.getNumNulls());
   }

   public void updateStats(float value) {
      if (!this.hasNonNullValue()) {
         this.initializeStats(value, value);
      } else {
         this.updateStats(value, value);
      }

   }

   public void mergeStatisticsMinMax(Statistics stats) {
      FloatStatistics floatStats = (FloatStatistics)stats;
      if (!this.hasNonNullValue()) {
         this.initializeStats(floatStats.getMin(), floatStats.getMax());
      } else {
         this.updateStats(floatStats.getMin(), floatStats.getMax());
      }

   }

   public void setMinMaxFromBytes(byte[] minBytes, byte[] maxBytes) {
      this.max = Float.intBitsToFloat(BytesUtils.bytesToInt(maxBytes));
      this.min = Float.intBitsToFloat(BytesUtils.bytesToInt(minBytes));
      this.markAsNotEmpty();
   }

   public byte[] getMaxBytes() {
      return BytesUtils.intToBytes(Float.floatToIntBits(this.max));
   }

   public byte[] getMinBytes() {
      return BytesUtils.intToBytes(Float.floatToIntBits(this.min));
   }

   String stringify(Float value) {
      return this.stringifier.stringify(value);
   }

   public boolean isSmallerThan(long size) {
      return !this.hasNonNullValue() || 8L < size;
   }

   public void updateStats(float min_value, float max_value) {
      if (this.comparator().compare(this.min, min_value) > 0) {
         this.min = min_value;
      }

      if (this.comparator().compare(this.max, max_value) < 0) {
         this.max = max_value;
      }

   }

   public void initializeStats(float min_value, float max_value) {
      this.min = min_value;
      this.max = max_value;
      this.markAsNotEmpty();
   }

   public Float genericGetMin() {
      return this.min;
   }

   public Float genericGetMax() {
      return this.max;
   }

   public int compareMinToValue(float value) {
      return this.comparator().compare(this.min, value);
   }

   public int compareMaxToValue(float value) {
      return this.comparator().compare(this.max, value);
   }

   public float getMax() {
      return this.max;
   }

   public float getMin() {
      return this.min;
   }

   public void setMinMax(float min, float max) {
      this.max = max;
      this.min = min;
      this.markAsNotEmpty();
   }

   public FloatStatistics copy() {
      return new FloatStatistics(this);
   }

   static {
      DEFAULT_FAKE_TYPE = (PrimitiveType)Types.optional(PrimitiveType.PrimitiveTypeName.FLOAT).named("fake_float_type");
   }
}
