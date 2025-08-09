package org.apache.hadoop.hive.metastore.client.builder;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;

public class SQLForeignKeyBuilder extends ConstraintBuilder {
   private String pkDb;
   private String pkTable;
   private String pkColumn;
   private String pkName;
   private int updateRule;
   private int deleteRule;

   public SQLForeignKeyBuilder() {
      this.updateRule = this.deleteRule = 0;
   }

   public SQLForeignKeyBuilder setPkDb(String pkDb) {
      this.pkDb = pkDb;
      return this;
   }

   public SQLForeignKeyBuilder setPkTable(String pkTable) {
      this.pkTable = pkTable;
      return this;
   }

   public SQLForeignKeyBuilder setPkColumn(String pkColumn) {
      this.pkColumn = pkColumn;
      return this;
   }

   public SQLForeignKeyBuilder setPkName(String pkName) {
      this.pkName = pkName;
      return this;
   }

   public SQLForeignKeyBuilder setPrimaryKey(SQLPrimaryKey pk) {
      this.pkDb = pk.getTable_db();
      this.pkTable = pk.getTable_name();
      this.pkColumn = pk.getColumn_name();
      this.pkName = pk.getPk_name();
      return this;
   }

   public SQLForeignKeyBuilder setUpdateRule(int updateRule) {
      this.updateRule = updateRule;
      return this;
   }

   public SQLForeignKeyBuilder setDeleteRule(int deleteRule) {
      this.deleteRule = deleteRule;
      return this;
   }

   public SQLForeignKey build() throws MetaException {
      this.checkBuildable("foreign_key");
      if (this.pkDb != null && this.pkTable != null && this.pkColumn != null && this.pkName != null) {
         return new SQLForeignKey(this.pkDb, this.pkTable, this.pkColumn, this.dbName, this.tableName, this.columnName, this.keySeq, this.updateRule, this.deleteRule, this.constraintName, this.pkName, this.enable, this.validate, this.rely);
      } else {
         throw new MetaException("You must provide the primary key database, table, column, and name");
      }
   }
}
