package org.apache.hadoop.hive.serde2;

public class SerDeStats {
   private long rawDataSize = 0L;
   private long rowCount = 0L;

   public long getRawDataSize() {
      return this.rawDataSize;
   }

   public void setRawDataSize(long uSize) {
      this.rawDataSize = uSize;
   }

   public long getRowCount() {
      return this.rowCount;
   }

   public void setRowCount(long rowCount) {
      this.rowCount = rowCount;
   }
}
