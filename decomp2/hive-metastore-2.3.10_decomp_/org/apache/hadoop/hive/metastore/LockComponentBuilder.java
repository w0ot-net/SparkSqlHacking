package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.metastore.api.DataOperationType;
import org.apache.hadoop.hive.metastore.api.LockComponent;
import org.apache.hadoop.hive.metastore.api.LockLevel;
import org.apache.hadoop.hive.metastore.api.LockType;

public class LockComponentBuilder {
   private LockComponent component = new LockComponent();
   private boolean tableNameSet;
   private boolean partNameSet;

   public LockComponentBuilder() {
      this.tableNameSet = this.partNameSet = false;
   }

   public LockComponentBuilder setExclusive() {
      this.component.setType(LockType.EXCLUSIVE);
      return this;
   }

   public LockComponentBuilder setSemiShared() {
      this.component.setType(LockType.SHARED_WRITE);
      return this;
   }

   public LockComponentBuilder setShared() {
      this.component.setType(LockType.SHARED_READ);
      return this;
   }

   public LockComponentBuilder setDbName(String dbName) {
      this.component.setDbname(dbName);
      return this;
   }

   public LockComponentBuilder setOperationType(DataOperationType dop) {
      this.component.setOperationType(dop);
      return this;
   }

   public LockComponentBuilder setIsAcid(boolean t) {
      this.component.setIsAcid(t);
      return this;
   }

   public LockComponentBuilder setTableName(String tableName) {
      this.component.setTablename(tableName);
      this.tableNameSet = true;
      return this;
   }

   public LockComponentBuilder setPartitionName(String partitionName) {
      this.component.setPartitionname(partitionName);
      this.partNameSet = true;
      return this;
   }

   public LockComponentBuilder setIsDynamicPartitionWrite(boolean t) {
      this.component.setIsDynamicPartitionWrite(t);
      return this;
   }

   public LockComponent build() {
      LockLevel level = LockLevel.DB;
      if (this.tableNameSet) {
         level = LockLevel.TABLE;
      }

      if (this.partNameSet) {
         level = LockLevel.PARTITION;
      }

      this.component.setLevel(level);
      return this.component;
   }
}
