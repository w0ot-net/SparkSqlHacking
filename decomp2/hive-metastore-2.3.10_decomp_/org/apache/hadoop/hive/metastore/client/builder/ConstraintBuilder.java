package org.apache.hadoop.hive.metastore.client.builder;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;

abstract class ConstraintBuilder {
   protected String dbName;
   protected String tableName;
   protected String columnName;
   protected String constraintName;
   protected int keySeq = 1;
   protected boolean enable = true;
   protected boolean validate;
   protected boolean rely;
   private Object child;

   protected ConstraintBuilder() {
      this.validate = this.rely = false;
   }

   protected void setChild(Object child) {
      this.child = child;
   }

   protected void checkBuildable(String defaultConstraintName) throws MetaException {
      if (this.dbName != null && this.tableName != null && this.columnName != null) {
         if (this.constraintName == null) {
            this.constraintName = this.dbName + "_" + this.tableName + "_" + this.columnName + "_" + defaultConstraintName;
         }

      } else {
         throw new MetaException("You must provide database name, table name, and column name");
      }
   }

   public Object setDbName(String dbName) {
      this.dbName = dbName;
      return this.child;
   }

   public Object setTableName(String tableName) {
      this.tableName = tableName;
      return this.child;
   }

   public Object setDbAndTableName(Table table) {
      this.dbName = table.getDbName();
      this.tableName = table.getTableName();
      return this.child;
   }

   public Object setColumnName(String columnName) {
      this.columnName = columnName;
      return this.child;
   }

   public Object setConstraintName(String constraintName) {
      this.constraintName = constraintName;
      return this.child;
   }

   public Object setKeySeq(int keySeq) {
      this.keySeq = keySeq;
      return this.child;
   }

   public Object setEnable(boolean enable) {
      this.enable = enable;
      return this.child;
   }

   public Object setValidate(boolean validate) {
      this.validate = validate;
      return this.child;
   }

   public Object setRely(boolean rely) {
      this.rely = rely;
      return this.child;
   }
}
