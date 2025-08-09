package org.rocksdb;

import java.util.Objects;

public class TableFileCreationInfo extends TableFileCreationBriefInfo {
   private final long fileSize;
   private final TableProperties tableProperties;
   private final Status status;

   protected TableFileCreationInfo(long var1, TableProperties var3, Status var4, String var5, String var6, String var7, int var8, byte var9) {
      super(var5, var6, var7, var8, var9);
      this.fileSize = var1;
      this.tableProperties = var3;
      this.status = var4;
   }

   public long getFileSize() {
      return this.fileSize;
   }

   public TableProperties getTableProperties() {
      return this.tableProperties;
   }

   public Status getStatus() {
      return this.status;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         TableFileCreationInfo var2 = (TableFileCreationInfo)var1;
         return this.fileSize == var2.fileSize && Objects.equals(this.tableProperties, var2.tableProperties) && Objects.equals(this.status, var2.status);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.fileSize, this.tableProperties, this.status});
   }

   public String toString() {
      return "TableFileCreationInfo{fileSize=" + this.fileSize + ", tableProperties=" + this.tableProperties + ", status=" + this.status + '}';
   }
}
