package org.apache.hadoop.hive.metastore.client.builder;

import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;

public class IndexBuilder extends StorageDescriptorBuilder {
   private String dbName;
   private String tableName;
   private String indexName;
   private String indexTableName;
   private String handlerClass;
   private int createTime;
   private int lastAccessTime;
   private Map indexParams = new HashMap();
   private boolean deferredRebuild;

   public IndexBuilder() {
      this.createTime = this.lastAccessTime = (int)(System.currentTimeMillis() / 1000L);
      super.setChild(this);
   }

   public IndexBuilder setDbName(String dbName) {
      this.dbName = dbName;
      return this;
   }

   public IndexBuilder setTableName(String tableName) {
      this.tableName = tableName;
      return this;
   }

   public IndexBuilder setDbAndTableName(Table table) {
      this.dbName = table.getDbName();
      this.tableName = table.getTableName();
      return this;
   }

   public IndexBuilder setCreateTime(int createTime) {
      this.createTime = createTime;
      return this;
   }

   public IndexBuilder setLastAccessTime(int lastAccessTime) {
      this.lastAccessTime = lastAccessTime;
      return this;
   }

   public IndexBuilder setIndexParams(Map indexParams) {
      this.indexParams = indexParams;
      return this;
   }

   public IndexBuilder setIndexName(String indexName) {
      this.indexName = indexName;
      return this;
   }

   public IndexBuilder setIndexTableName(String indexTableName) {
      this.indexTableName = indexTableName;
      return this;
   }

   public IndexBuilder setHandlerClass(String handlerClass) {
      this.handlerClass = handlerClass;
      return this;
   }

   public IndexBuilder setDeferredRebuild(boolean deferredRebuild) {
      this.deferredRebuild = deferredRebuild;
      return this;
   }

   public Index build() throws MetaException {
      if (this.dbName != null && this.tableName != null && this.indexName != null) {
         if (this.indexTableName == null) {
            this.indexTableName = this.tableName + "_" + this.indexName + "_table";
         }

         return new Index(this.indexName, this.handlerClass, this.dbName, this.tableName, this.createTime, this.lastAccessTime, this.indexTableName, this.buildSd(), this.indexParams, this.deferredRebuild);
      } else {
         throw new MetaException("You must provide database name, table name, and index name");
      }
   }
}
