package org.apache.hadoop.hive.metastore.client.builder;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.HiveObjectRef;
import org.apache.hadoop.hive.metastore.api.HiveObjectType;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

public class HiveObjectRefBuilder {
   private HiveObjectType objectType;
   private String dbName;
   private String objectName;
   private String columnName;
   private List partValues;

   public HiveObjectRef buildGlobalReference() {
      return new HiveObjectRef(HiveObjectType.GLOBAL, (String)null, (String)null, new ArrayList(), (String)null);
   }

   public HiveObjectRef buildDatabaseReference(Database db) {
      return new HiveObjectRef(HiveObjectType.DATABASE, db.getName(), (String)null, new ArrayList(), (String)null);
   }

   public HiveObjectRef buildTableReference(Table table) {
      return new HiveObjectRef(HiveObjectType.TABLE, table.getDbName(), table.getTableName(), new ArrayList(), (String)null);
   }

   public HiveObjectRef buildPartitionReference(Partition part) {
      return new HiveObjectRef(HiveObjectType.PARTITION, part.getDbName(), part.getTableName(), part.getValues(), (String)null);
   }

   public HiveObjectRef buildColumnReference(Table table, String columnName) {
      return new HiveObjectRef(HiveObjectType.TABLE, table.getDbName(), table.getTableName(), new ArrayList(), columnName);
   }
}
