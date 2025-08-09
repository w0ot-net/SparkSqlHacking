package org.apache.parquet.column.statistics;

import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveType;

class NoopStatistics extends Statistics {
   NoopStatistics(PrimitiveType type) {
      super(type);
   }

   public void updateStats(int value) {
   }

   public void updateStats(long value) {
   }

   public void updateStats(float value) {
   }

   public void updateStats(double value) {
   }

   public void updateStats(boolean value) {
   }

   public void updateStats(Binary value) {
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (!(other instanceof Statistics)) {
         return false;
      } else {
         Statistics stats = (Statistics)other;
         return this.type().equals(stats.type());
      }
   }

   public int hashCode() {
      return 31 * this.type().hashCode();
   }

   protected void mergeStatisticsMinMax(Statistics stats) {
   }

   public void setMinMaxFromBytes(byte[] minBytes, byte[] maxBytes) {
   }

   public Comparable genericGetMin() {
      throw new UnsupportedOperationException("genericGetMin is not supported by " + this.getClass().getName());
   }

   public Comparable genericGetMax() {
      throw new UnsupportedOperationException("genericGetMax is not supported by " + this.getClass().getName());
   }

   public byte[] getMaxBytes() {
      throw new UnsupportedOperationException("getMaxBytes is not supported by " + this.getClass().getName());
   }

   public byte[] getMinBytes() {
      throw new UnsupportedOperationException("getMinBytes is not supported by " + this.getClass().getName());
   }

   String stringify(Comparable value) {
      throw new UnsupportedOperationException("stringify is not supported by " + this.getClass().getName());
   }

   public boolean isSmallerThan(long size) {
      throw new UnsupportedOperationException("isSmallerThan is not supported by " + this.getClass().getName());
   }

   public long getNumNulls() {
      return -1L;
   }

   public boolean isEmpty() {
      return true;
   }

   public boolean hasNonNullValue() {
      return false;
   }

   public boolean isNumNullsSet() {
      return false;
   }

   public Statistics copy() {
      return new NoopStatistics(this.type());
   }
}
