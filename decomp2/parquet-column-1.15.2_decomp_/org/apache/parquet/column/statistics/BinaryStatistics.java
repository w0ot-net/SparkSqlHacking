package org.apache.parquet.column.statistics;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

public class BinaryStatistics extends Statistics {
   private static final PrimitiveType DEFAULT_FAKE_TYPE;
   private Binary max;
   private Binary min;

   /** @deprecated */
   @Deprecated
   public BinaryStatistics() {
      this(DEFAULT_FAKE_TYPE);
   }

   BinaryStatistics(PrimitiveType type) {
      super(type);
   }

   private BinaryStatistics(BinaryStatistics other) {
      super(other.type());
      if (other.hasNonNullValue()) {
         this.initializeStats(other.min, other.max);
      }

      this.setNumNulls(other.getNumNulls());
   }

   public void updateStats(Binary value) {
      if (!this.hasNonNullValue()) {
         this.min = value.copy();
         this.max = value.copy();
         this.markAsNotEmpty();
      } else if (this.comparator().compare(this.min, value) > 0) {
         this.min = value.copy();
      } else if (this.comparator().compare(this.max, value) < 0) {
         this.max = value.copy();
      }

   }

   public void mergeStatisticsMinMax(Statistics stats) {
      BinaryStatistics binaryStats = (BinaryStatistics)stats;
      if (!this.hasNonNullValue()) {
         this.initializeStats(binaryStats.getMin(), binaryStats.getMax());
      } else {
         this.updateStats(binaryStats.getMin(), binaryStats.getMax());
      }

   }

   public void setMinMaxFromBytes(byte[] minBytes, byte[] maxBytes) {
      this.max = Binary.fromReusedByteArray(maxBytes);
      this.min = Binary.fromReusedByteArray(minBytes);
      this.markAsNotEmpty();
   }

   public byte[] getMaxBytes() {
      return this.max == null ? null : this.max.getBytes();
   }

   public byte[] getMinBytes() {
      return this.min == null ? null : this.min.getBytes();
   }

   String stringify(Binary value) {
      return this.stringifier.stringify(value);
   }

   public boolean isSmallerThan(long size) {
      return !this.hasNonNullValue() || (long)(this.min.length() + this.max.length()) < size;
   }

   public boolean isSmallerThanWithTruncation(long size, int truncationLength) {
      if (!this.hasNonNullValue()) {
         return true;
      } else {
         int minTruncateLength = Math.min(this.min.length(), truncationLength);
         int maxTruncateLength = Math.min(this.max.length(), truncationLength);
         return (long)(minTruncateLength + maxTruncateLength) < size;
      }
   }

   /** @deprecated */
   @Deprecated
   public void updateStats(Binary min_value, Binary max_value) {
      if (this.comparator().compare(this.min, min_value) > 0) {
         this.min = min_value.copy();
      }

      if (this.comparator().compare(this.max, max_value) < 0) {
         this.max = max_value.copy();
      }

   }

   /** @deprecated */
   @Deprecated
   public void initializeStats(Binary min_value, Binary max_value) {
      this.min = min_value.copy();
      this.max = max_value.copy();
      this.markAsNotEmpty();
   }

   public Binary genericGetMin() {
      return this.min;
   }

   public Binary genericGetMax() {
      return this.max;
   }

   /** @deprecated */
   @Deprecated
   public Binary getMax() {
      return this.max;
   }

   /** @deprecated */
   @Deprecated
   public Binary getMin() {
      return this.min;
   }

   /** @deprecated */
   @Deprecated
   public void setMinMax(Binary min, Binary max) {
      this.max = max;
      this.min = min;
      this.markAsNotEmpty();
   }

   public BinaryStatistics copy() {
      return new BinaryStatistics(this);
   }

   static {
      DEFAULT_FAKE_TYPE = (PrimitiveType)Types.optional(PrimitiveType.PrimitiveTypeName.BINARY).named("fake_binary_type");
   }
}
