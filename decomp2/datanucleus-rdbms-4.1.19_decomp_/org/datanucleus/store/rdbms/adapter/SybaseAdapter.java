package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.table.Table;

public class SybaseAdapter extends BaseDatastoreAdapter {
   public SybaseAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("StoredProcs");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("BooleanExpression");
      this.supportedOptions.remove("LockWithSelectForUpdate");
      this.supportedOptions.remove("AutoIncrementNullSpecification");
   }

   public String getVendorID() {
      return "sybase";
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      throw new UnsupportedOperationException("Sybase does not support dropping schema with cascade. You need to drop all tables first");
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public String getDeleteTableStatement(SQLTable tbl) {
      return "DELETE " + tbl.getAlias() + " FROM " + tbl.toString();
   }

   public SQLText getUpdateTableStatement(SQLTable tbl, SQLText setSQL) {
      SQLText sql = (new SQLText("UPDATE ")).append(tbl.getAlias().toString());
      sql.append(" ").append(setSQL);
      sql.append(" FROM ").append(tbl.toString());
      return sql;
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      SQLTypeInfo info = new SQLTypeInfo(rs);
      if (info.getTypeName().toLowerCase().startsWith("tinyint")) {
         return null;
      } else {
         return info.getTypeName().toLowerCase().startsWith("longsysname") ? null : info;
      }
   }

   public RDBMSColumnInfo newRDBMSColumnInfo(ResultSet rs) {
      RDBMSColumnInfo info = new RDBMSColumnInfo(rs);
      short dataType = info.getDataType();
      switch (dataType) {
         case 91:
         case 92:
         case 93:
            info.setDecimalDigits(0);
         default:
            return info;
      }
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "SELECT @@IDENTITY";
   }

   public String getAutoIncrementKeyword() {
      return "IDENTITY";
   }
}
