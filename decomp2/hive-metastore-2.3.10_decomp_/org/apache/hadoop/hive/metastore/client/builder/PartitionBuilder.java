package org.apache.hadoop.hive.metastore.client.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

public class PartitionBuilder extends StorageDescriptorBuilder {
   private String dbName;
   private String tableName;
   private int createTime;
   private int lastAccessTime;
   private Map partParams = new HashMap();
   private List values;

   public PartitionBuilder() {
      this.createTime = this.lastAccessTime = (int)(System.currentTimeMillis() / 1000L);
      super.setChild(this);
   }

   public PartitionBuilder setDbName(String dbName) {
      this.dbName = dbName;
      return this;
   }

   public PartitionBuilder setTableName(String tableName) {
      this.tableName = tableName;
      return this;
   }

   public PartitionBuilder setDbAndTableName(Table table) {
      this.dbName = table.getDbName();
      this.tableName = table.getTableName();
      return this;
   }

   public PartitionBuilder setValues(List values) {
      this.values = values;
      return this;
   }

   public PartitionBuilder addValue(String value) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(value);
      return this;
   }

   public PartitionBuilder setCreateTime(int createTime) {
      this.createTime = createTime;
      return this;
   }

   public PartitionBuilder setLastAccessTime(int lastAccessTime) {
      this.lastAccessTime = lastAccessTime;
      return this;
   }

   public PartitionBuilder setPartParams(Map partParams) {
      this.partParams = partParams;
      return this;
   }

   public PartitionBuilder addPartParam(String key, String value) {
      if (this.partParams == null) {
         this.partParams = new HashMap();
      }

      this.partParams.put(key, value);
      return this;
   }

   public Partition build() throws MetaException {
      if (this.dbName != null && this.tableName != null) {
         if (this.values == null) {
            throw new MetaException("You must provide partition values");
         } else {
            return new Partition(this.values, this.dbName, this.tableName, this.createTime, this.lastAccessTime, this.buildSd(), this.partParams);
         }
      } else {
         throw new MetaException("database name and table name must be provided");
      }
   }
}
