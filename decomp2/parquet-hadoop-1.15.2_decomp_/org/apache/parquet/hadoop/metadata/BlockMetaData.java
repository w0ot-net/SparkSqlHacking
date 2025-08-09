package org.apache.parquet.hadoop.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockMetaData {
   private List columns = new ArrayList();
   private long rowCount;
   private long totalByteSize;
   private String path;
   private int ordinal;
   private long rowIndexOffset = -1L;

   public void setPath(String path) {
      this.path = path;
   }

   public String getPath() {
      return this.path;
   }

   public long getRowCount() {
      return this.rowCount;
   }

   public void setRowCount(long rowCount) {
      this.rowCount = rowCount;
   }

   public long getRowIndexOffset() {
      return this.rowIndexOffset;
   }

   public void setRowIndexOffset(long rowIndexOffset) {
      this.rowIndexOffset = rowIndexOffset;
   }

   public long getTotalByteSize() {
      return this.totalByteSize;
   }

   public void setTotalByteSize(long totalByteSize) {
      this.totalByteSize = totalByteSize;
   }

   public void addColumn(ColumnChunkMetaData column) {
      this.columns.add(column);
   }

   public List getColumns() {
      return Collections.unmodifiableList(this.columns);
   }

   public long getStartingPos() {
      return ((ColumnChunkMetaData)this.getColumns().get(0)).getStartingPos();
   }

   public String toString() {
      String rowIndexOffsetStr = "";
      if (this.rowIndexOffset != -1L) {
         rowIndexOffsetStr = ", rowIndexOffset = " + this.rowIndexOffset;
      }

      return "BlockMetaData{" + this.rowCount + ", " + this.totalByteSize + rowIndexOffsetStr + " " + this.columns + "}";
   }

   public long getCompressedSize() {
      long totalSize = 0L;

      for(ColumnChunkMetaData col : this.getColumns()) {
         totalSize += col.getTotalSize();
      }

      return totalSize;
   }

   public int getOrdinal() {
      return this.ordinal;
   }

   public void setOrdinal(int ordinal) {
      this.ordinal = ordinal;
   }
}
