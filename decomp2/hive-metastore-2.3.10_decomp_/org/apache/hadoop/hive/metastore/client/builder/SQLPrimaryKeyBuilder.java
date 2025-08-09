package org.apache.hadoop.hive.metastore.client.builder;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;

public class SQLPrimaryKeyBuilder extends ConstraintBuilder {
   public SQLPrimaryKeyBuilder() {
      super.setChild(this);
   }

   public SQLPrimaryKeyBuilder setPrimaryKeyName(String name) {
      return (SQLPrimaryKeyBuilder)this.setConstraintName(name);
   }

   public SQLPrimaryKey build() throws MetaException {
      this.checkBuildable("primary_key");
      return new SQLPrimaryKey(this.dbName, this.tableName, this.columnName, this.keySeq, this.constraintName, this.enable, this.validate, this.rely);
   }
}
