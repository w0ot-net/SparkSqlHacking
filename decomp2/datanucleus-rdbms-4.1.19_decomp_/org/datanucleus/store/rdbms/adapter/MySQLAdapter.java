package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Properties;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedMapping;
import org.datanucleus.store.rdbms.schema.MySQLTypeInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.StringUtils;

public class MySQLAdapter extends BaseDatastoreAdapter {
   public static final String NONSQL92_RESERVED_WORDS = "ANALYZE,AUTO_INCREMENT,BDB,BERKELEYDB,BIGINT,BINARY,BLOB,BTREE,CHANGE,COLUMNS,DATABASE,DATABASES,DAY_HOUR,DAY_MINUTE,DAY_SECOND,DELAYED,DISTINCTROW,DIV,ENCLOSED,ERRORS,ESCAPED,EXPLAIN,FIELDS,FORCE,FULLTEXT,FUNCTION,GEOMETRY,HASH,HELP,HIGH_PRIORITY,HOUR_MINUTE,HOUR_SECOND,IF,IGNORE,INDEX,INFILE,INNODB,KEYS,KILL,LIMIT,LINES,LOAD,LOCALTIME,LOCALTIMESTAMP,LOCK,LONG,LONGBLOB,LONGTEXT,LOW_PRIORITY,MASTER_SERVER_ID,MEDIUMBLOB,MEDIUMINT,MEDIUMTEXT,MIDDLEINT,MINUTE_SECOND,MOD,MRG_MYISAM,OPTIMIZE,OPTIONALLY,OUTFILE,PURGE,REGEXP,RENAME,REPLACE,REQUIRE,RETURNS,RLIKE,RTREE,SHOW,SONAME,SPATIAL,SQL_BIG_RESULT,SQL_CALC_FOUND_ROWS,SQL_SMALL_RESULT,SSL,STARTING,STRAIGHT_JOIN,STRIPED,TABLES,TERMINATED,TINYBLOB,TINYINT,TINYTEXT,TYPES,UNLOCK,UNSIGNED,USE,USER_RESOURCES,VARBINARY,VARCHARACTER,WARNINGS,XOR,YEAR_MONTH,ZEROFILL";

   public MySQLAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ANALYZE,AUTO_INCREMENT,BDB,BERKELEYDB,BIGINT,BINARY,BLOB,BTREE,CHANGE,COLUMNS,DATABASE,DATABASES,DAY_HOUR,DAY_MINUTE,DAY_SECOND,DELAYED,DISTINCTROW,DIV,ENCLOSED,ERRORS,ESCAPED,EXPLAIN,FIELDS,FORCE,FULLTEXT,FUNCTION,GEOMETRY,HASH,HELP,HIGH_PRIORITY,HOUR_MINUTE,HOUR_SECOND,IF,IGNORE,INDEX,INFILE,INNODB,KEYS,KILL,LIMIT,LINES,LOAD,LOCALTIME,LOCALTIMESTAMP,LOCK,LONG,LONGBLOB,LONGTEXT,LOW_PRIORITY,MASTER_SERVER_ID,MEDIUMBLOB,MEDIUMINT,MEDIUMTEXT,MIDDLEINT,MINUTE_SECOND,MOD,MRG_MYISAM,OPTIMIZE,OPTIONALLY,OUTFILE,PURGE,REGEXP,RENAME,REPLACE,REQUIRE,RETURNS,RLIKE,RTREE,SHOW,SONAME,SPATIAL,SQL_BIG_RESULT,SQL_CALC_FOUND_ROWS,SQL_SMALL_RESULT,SSL,STARTING,STRAIGHT_JOIN,STRIPED,TABLES,TERMINATED,TINYBLOB,TINYINT,TINYTEXT,TYPES,UNLOCK,UNSIGNED,USE,USER_RESOURCES,VARBINARY,VARCHARACTER,WARNINGS,XOR,YEAR_MONTH,ZEROFILL"));
      this.supportedOptions.remove("AlterTableDropConstraint_Syntax");
      if (this.datastoreMajorVersion >= 4 && (this.datastoreMajorVersion != 4 || this.datastoreMinorVersion != 0 || this.datastoreRevisionVersion >= 13)) {
         this.supportedOptions.add("AlterTableDropForeignKey_Syntax");
      } else {
         this.supportedOptions.remove("AlterTableDropForeignKey_Syntax");
      }

      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("ColumnOptions_DefaultBeforeNull");
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      if (this.datastoreMajorVersion >= 5 || this.datastoreMajorVersion >= 4 && this.datastoreMinorVersion >= 1) {
         this.supportedOptions.add("Exists_Syntax");
      } else {
         this.supportedOptions.remove("Exists_Syntax");
      }

      if (this.datastoreMajorVersion < 4) {
         this.supportedOptions.remove("Union_Syntax");
      } else {
         this.supportedOptions.add("Union_Syntax");
      }

      this.supportedOptions.add("BlobSetUsingSetString");
      this.supportedOptions.add("ClobSetUsingSetString");
      this.supportedOptions.add("CreateIndexesBeforeForeignKeys");
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("StoredProcs");
      this.supportedOptions.add("OrderByNullsUsingIsNull");
      this.supportedOptions.remove("DateTimeStoresMillisecs");
      if (!this.driverName.equalsIgnoreCase("mariadb-jdbc")) {
      }

      this.supportedOptions.add("BitwiseAndOperator");
      this.supportedOptions.add("BitwiseOrOperator");
      this.supportedOptions.add("BitwiseXOrOperator");
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      SQLTypeInfo sqlType = new MySQLTypeInfo("MEDIUMBLOB", (short)2004, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, false, (short)1, false, false, false, "MEDIUMBLOB", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2004, sqlType, true);
      SQLTypeInfo var4 = new MySQLTypeInfo("MEDIUMTEXT", (short)2005, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, true, (short)1, false, false, false, "MEDIUMTEXT", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2005, var4, true);
   }

   public String getVendorID() {
      return "mysql";
   }

   public RDBMSColumnInfo newRDBMSColumnInfo(ResultSet rs) {
      RDBMSColumnInfo info = super.newRDBMSColumnInfo(rs);
      short dataType = info.getDataType();
      String typeName = info.getTypeName();
      if (dataType == -4 && typeName.equalsIgnoreCase("mediumblob")) {
         info.setDataType((short)2004);
      }

      if (dataType == -1 && typeName.equalsIgnoreCase("mediumtext")) {
         info.setDataType((short)2005);
      }

      return info;
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      SQLTypeInfo info = new MySQLTypeInfo(rs);
      return info;
   }

   public String getCreateDatabaseStatement(String catalogName, String schemaName) {
      return "CREATE DATABASE IF NOT EXISTS " + catalogName;
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      return "DROP DATABASE IF EXISTS " + catalogName;
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getCreateTableStatement(TableImpl table, Column[] columns, Properties props, IdentifierFactory factory) {
      StringBuilder createStmt = new StringBuilder(super.getCreateTableStatement(table, columns, props, factory));
      String engineType = "INNODB";
      if (props != null && props.containsKey("mysql-engine-type")) {
         engineType = props.getProperty("mysql-engine-type");
      } else if (table.getStoreManager().hasProperty("datanucleus.rdbms.mysql.engineType")) {
         engineType = table.getStoreManager().getStringProperty("datanucleus.rdbms.mysql.engineType");
      }

      String collation = null;
      if (props != null && props.contains("collation")) {
         collation = props.getProperty("mysql-collation");
      } else if (table.getStoreManager().hasProperty("datanucleus.rdbms.mysql.collation")) {
         collation = table.getStoreManager().getStringProperty("datanucleus.rdbms.mysql.collation");
      }

      String charset = null;
      if (props != null && props.contains("characterSet")) {
         charset = props.getProperty("mysql-character-set");
      } else if (table.getStoreManager().hasProperty("datanucleus.rdbms.mysql.characterSet")) {
         charset = table.getStoreManager().getStringProperty("datanucleus.rdbms.mysql.characterSet");
      }

      boolean engineKeywordPresent = false;
      if (this.datastoreMajorVersion >= 5 || this.datastoreMajorVersion == 4 && this.datastoreMinorVersion >= 1 && this.datastoreRevisionVersion >= 2 || this.datastoreMajorVersion == 4 && this.datastoreMinorVersion == 0 && this.datastoreRevisionVersion >= 18) {
         engineKeywordPresent = true;
      }

      if (engineKeywordPresent) {
         createStmt.append(" ENGINE=" + engineType);
      } else {
         createStmt.append(" TYPE=" + engineType);
      }

      if (collation != null) {
         createStmt.append(" COLLATE=").append(collation);
      }

      if (charset != null) {
         createStmt.append(" CHARACTER SET=").append(charset);
      }

      return createStmt.toString();
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public String getAddColumnStatement(Table table, Column col) {
      return "ALTER TABLE " + table.toString() + " ADD COLUMN " + col.getSQLDefinition();
   }

   public String getDeleteTableStatement(SQLTable tbl) {
      return "DELETE " + tbl.getAlias() + " FROM " + tbl.toString();
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "SELECT LAST_INSERT_ID()";
   }

   public String getAutoIncrementKeyword() {
      return "AUTO_INCREMENT";
   }

   public String getSelectNewUUIDStmt() {
      return "SELECT uuid()";
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      if (offset >= 0L && count > 0L) {
         return "LIMIT " + offset + "," + count + " ";
      } else if (offset <= 0L && count > 0L) {
         return "LIMIT " + count + " ";
      } else {
         return offset >= 0L && count < 0L ? "LIMIT " + offset + "," + Long.MAX_VALUE + " " : "";
      }
   }

   public String getEscapePatternExpression() {
      return "ESCAPE '\\\\'";
   }

   public boolean validToIndexMapping(JavaTypeMapping mapping) {
      return mapping instanceof SerialisedMapping ? false : super.validToIndexMapping(mapping);
   }
}
