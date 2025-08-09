package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.schema.SQLiteTypeInfo;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.StoreSchemaHandler;

public class SQLiteAdapter extends BaseDatastoreAdapter {
   protected static final int MAX_IDENTIFIER_LENGTH = 128;

   public SQLiteAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("CheckInEndCreateStatements");
      this.supportedOptions.add("UniqueInEndCreateStatements");
      this.supportedOptions.add("FKInEndCreateStatements");
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("AutoIncrementPkInCreateTableColumnDef");
      this.supportedOptions.add("OrderByNullsUsingColumnIsNull");
      this.supportedOptions.remove("TxIsolationReadCommitted");
      this.supportedOptions.remove("TxIsolationReadRepeatableRead");
      this.supportedOptions.remove("AutoIncrementNullSpecification");
      this.supportedOptions.remove("RightOuterJoin");
      this.supportedOptions.remove("SomeAllAnySubqueries");
      this.supportedOptions.remove("UpdateStmtAllowTableAliasInSet");
      this.supportedOptions.remove("UpdateDeleteStmtAllowTableAliasInWhere");
      this.maxTableNameLength = 128;
      this.maxColumnNameLength = 128;
      this.maxConstraintNameLength = 128;
      this.maxIndexNameLength = 128;
   }

   public String getVendorID() {
      return "sqlite";
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      SQLTypeInfo sqlType = new SQLiteTypeInfo("double", (short)8, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "double", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)8, sqlType, true);
      SQLTypeInfo var4 = new SQLiteTypeInfo("float", (short)6, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "float", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)6, var4, true);
      var4 = new SQLiteTypeInfo("decimal", (short)3, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "decimal", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)3, var4, true);
      var4 = new SQLiteTypeInfo("numeric", (short)2, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "numeric", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2, var4, true);
      var4 = new SQLiteTypeInfo("integer", (short)16, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "integer", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)16, var4, true);
      var4 = new SQLiteTypeInfo("integer", (short)-7, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "integer", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-7, var4, true);
      var4 = new SQLiteTypeInfo("tinyint", (short)-6, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "tinyint", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-6, var4, true);
      var4 = new SQLiteTypeInfo("smallint", (short)5, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "smallint", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)5, var4, true);
      var4 = new SQLiteTypeInfo("bigint", (short)-5, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "bigint", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-5, var4, true);
      var4 = new SQLiteTypeInfo("char", (short)1, 255, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "char", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)1, var4, true);
      var4 = new SQLiteTypeInfo("varchar", (short)12, 255, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "varchar", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)12, var4, true);
      var4 = new SQLiteTypeInfo("longvarchar", (short)-1, 16777215, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "longvarchar", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-1, var4, true);
      var4 = new SQLiteTypeInfo("clob", (short)2005, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "clob", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2005, var4, true);
      var4 = new SQLiteTypeInfo("date", (short)91, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "date", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)91, var4, true);
      var4 = new SQLiteTypeInfo("time", (short)92, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "time", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)92, var4, true);
      var4 = new SQLiteTypeInfo("timestamp", (short)93, 0, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "timestamp", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)93, var4, true);
      var4 = new SQLiteTypeInfo("blob", (short)-2, 255, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "blob", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-2, var4, true);
      var4 = new SQLiteTypeInfo("blob", (short)-3, 255, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "blob", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-3, var4, true);
      var4 = new SQLiteTypeInfo("blob", (short)-4, 16777215, (String)null, (String)null, (String)null, 1, true, (short)3, false, false, false, "blob", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-4, var4, true);
   }

   public String getCreateDatabaseStatement(String catalogName, String schemaName) {
      throw new UnsupportedOperationException("SQLite does not support CREATE SCHEMA; everything is in a single schema");
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      throw new UnsupportedOperationException("SQLite does not support DROP SCHEMA; everything is in a single schema");
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getAddCandidateKeyStatement(CandidateKey ck, IdentifierFactory factory) {
      return null;
   }

   public String getAddForeignKeyStatement(ForeignKey fk, IdentifierFactory factory) {
      return null;
   }

   public SQLText getUpdateTableStatement(SQLTable tbl, SQLText setSQL) {
      SQLText sql = (new SQLText("UPDATE ")).append(tbl.getTable().toString());
      sql.append(" ").append(setSQL);
      return sql;
   }

   public String getDeleteTableStatement(SQLTable tbl) {
      return "DELETE FROM " + tbl.getTable().toString();
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      if (offset >= 0L && count > 0L) {
         return "LIMIT " + count + " OFFSET " + offset + " ";
      } else if (offset <= 0L && count > 0L) {
         return "LIMIT " + count + " ";
      } else {
         return offset >= 0L && count < 0L ? "LIMIT 9223372036854775807 OFFSET " + offset + " " : "";
      }
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "SELECT last_insert_rowid()";
   }

   public String getAutoIncrementKeyword() {
      return "autoincrement";
   }

   public Class getAutoIncrementJavaTypeForType(Class type) {
      return type.isPrimitive() ? Integer.TYPE : Integer.class;
   }
}
