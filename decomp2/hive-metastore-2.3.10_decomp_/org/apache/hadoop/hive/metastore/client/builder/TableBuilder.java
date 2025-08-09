package org.apache.hadoop.hive.metastore.client.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.utils.SecurityUtils;

public class TableBuilder extends StorageDescriptorBuilder {
   private String dbName;
   private String tableName;
   private String owner;
   private String viewOriginalText;
   private String viewExpandedText;
   private String type;
   private List partCols;
   private int createTime;
   private int lastAccessTime;
   private int retention;
   private Map tableParams = new HashMap();
   private boolean rewriteEnabled;
   private boolean temporary;

   public TableBuilder() {
      this.createTime = this.lastAccessTime = (int)(System.currentTimeMillis() / 1000L);
      this.retention = 0;
      super.setChild(this);
   }

   public TableBuilder setDbName(String dbName) {
      this.dbName = dbName;
      return this;
   }

   public TableBuilder setDbName(Database db) {
      this.dbName = db.getName();
      return this;
   }

   public TableBuilder setTableName(String tableName) {
      this.tableName = tableName;
      return this;
   }

   public TableBuilder setOwner(String owner) {
      this.owner = owner;
      return this;
   }

   public TableBuilder setViewOriginalText(String viewOriginalText) {
      this.viewOriginalText = viewOriginalText;
      return this;
   }

   public TableBuilder setViewExpandedText(String viewExpandedText) {
      this.viewExpandedText = viewExpandedText;
      return this;
   }

   public TableBuilder setType(String type) {
      this.type = type;
      return this;
   }

   public TableBuilder setPartCols(List partCols) {
      this.partCols = partCols;
      return this;
   }

   public TableBuilder addPartCol(String name, String type, String comment) {
      if (this.partCols == null) {
         this.partCols = new ArrayList();
      }

      this.partCols.add(new FieldSchema(name, type, comment));
      return this;
   }

   public TableBuilder addPartCol(String name, String type) {
      return this.addPartCol(name, type, "");
   }

   public TableBuilder setCreateTime(int createTime) {
      this.createTime = createTime;
      return this;
   }

   public TableBuilder setLastAccessTime(int lastAccessTime) {
      this.lastAccessTime = lastAccessTime;
      return this;
   }

   public TableBuilder setRetention(int retention) {
      this.retention = retention;
      return this;
   }

   public TableBuilder setTableParams(Map tableParams) {
      this.tableParams = tableParams;
      return this;
   }

   public TableBuilder addTableParam(String key, String value) {
      if (this.tableParams == null) {
         this.tableParams = new HashMap();
      }

      this.tableParams.put(key, value);
      return this;
   }

   public TableBuilder setRewriteEnabled(boolean rewriteEnabled) {
      this.rewriteEnabled = rewriteEnabled;
      return this;
   }

   public TableBuilder setTemporary(boolean temporary) {
      this.temporary = temporary;
      return this;
   }

   public Table build() throws MetaException {
      if (this.dbName != null && this.tableName != null) {
         if (this.owner == null) {
            try {
               this.owner = SecurityUtils.getUser();
            } catch (IOException e) {
               throw new MetaException(e.getMessage());
            }
         }

         Table t = new Table(this.tableName, this.dbName, this.owner, this.createTime, this.lastAccessTime, this.retention, this.buildSd(), this.partCols, this.tableParams, this.viewOriginalText, this.viewExpandedText, this.type);
         if (this.rewriteEnabled) {
            t.setRewriteEnabled(true);
         }

         if (this.temporary) {
            t.setTemporary(this.temporary);
         }

         return t;
      } else {
         throw new MetaException("You must set the database and table name");
      }
   }
}
