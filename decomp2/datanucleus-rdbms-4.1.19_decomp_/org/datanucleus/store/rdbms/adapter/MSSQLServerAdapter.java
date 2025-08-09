package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.MSSQLTypeInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class MSSQLServerAdapter extends BaseDatastoreAdapter {
   private static final String MSSQL_RESERVED_WORDS = "ADD,ALL,ALTER,AND,ANY,AS,ASC,AUTHORIZATION,BACKUP,BEGIN,BETWEEN,BREAK,BROWSE,BULK,BY,CASCADE,CASE,CHECK,CHECKPOINT,CLOSE,CLUSTERED,COALESCE,COLLATE,COLUMN,COMMIT,COMPUTE,CONSTRAINT,CONTAINS,CONTAINSTABLE,CONTINUE,CONVERT,CREATE,CROSS,CURRENT,CURRENT_DATE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_USER,CURSOR,DBCC,DEALLOCATE,DECLARE,DEFAULT,DELETE,DENY,DESC,DISK,DISTINCT,DISTRIBUTED,DOUBLE,DROP,DUMMY,DUMP,ELSE,END,ERRLVL,ESCAPE,EXCEPT,EXEC,EXECUTE,EXISTS,EXIT,FETCH,FILE,FILLFACTOR,FOR,FOREIGN,FREETEXT,FREETEXTTABLE,FROM,FULL,FUNCTION,GOTO,GRANT,GROUP,HAVING,HOLDLOCK,IDENTITY,IDENTITY_INSERT,IDENTITYCOL,IF,IN,INDEX,INNER,INSERT,INTERSECT,INTO,IS,JOIN,KEY,KILL,LEFT,LIKE,LINENO,LOAD,NATIONAL,NOCHECK,NONCLUSTERED,NOT,NULL,NULLIF,OF,OFF,OFFSETS,ON,OPEN,OPENDATASOURCE,OPENQUERY,OPENROWSET,OPENXML,OPTION,OR,ORDER,OUTER,OVER,PERCENT,PLAN,PRECISION,PRIMARY,PRINT,PROC,PROCEDURE,PUBLIC,RAISERROR,READ,READTEXT,RECONFIGURE,REFERENCES,REPLICATION,RESTORE,RESTRICT,RETURN,REVOKE,RIGHT,ROLLBACK,ROWCOUNT,ROWGUIDCOL,RULE,SAVE,SCHEMA,SELECT,SESSION_USER,SET,SETUSER,SHUTDOWN,SOME,STATISTICS,SYSTEM_USER,TABLE,TEXTSIZE,THEN,TO,TOP,TRAN,DATABASE,TRANSACTION,TRIGGER,TRUNCATE,TSEQUAL,UNION,UNIQUE,UPDATE,UPDATETEXT,USE,USER,VALUES,VARYING,VIEW,WAITFOR,WHEN,WHERE,WHILE,WITH,WRITETEXT";

   public MSSQLServerAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ADD,ALL,ALTER,AND,ANY,AS,ASC,AUTHORIZATION,BACKUP,BEGIN,BETWEEN,BREAK,BROWSE,BULK,BY,CASCADE,CASE,CHECK,CHECKPOINT,CLOSE,CLUSTERED,COALESCE,COLLATE,COLUMN,COMMIT,COMPUTE,CONSTRAINT,CONTAINS,CONTAINSTABLE,CONTINUE,CONVERT,CREATE,CROSS,CURRENT,CURRENT_DATE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_USER,CURSOR,DBCC,DEALLOCATE,DECLARE,DEFAULT,DELETE,DENY,DESC,DISK,DISTINCT,DISTRIBUTED,DOUBLE,DROP,DUMMY,DUMP,ELSE,END,ERRLVL,ESCAPE,EXCEPT,EXEC,EXECUTE,EXISTS,EXIT,FETCH,FILE,FILLFACTOR,FOR,FOREIGN,FREETEXT,FREETEXTTABLE,FROM,FULL,FUNCTION,GOTO,GRANT,GROUP,HAVING,HOLDLOCK,IDENTITY,IDENTITY_INSERT,IDENTITYCOL,IF,IN,INDEX,INNER,INSERT,INTERSECT,INTO,IS,JOIN,KEY,KILL,LEFT,LIKE,LINENO,LOAD,NATIONAL,NOCHECK,NONCLUSTERED,NOT,NULL,NULLIF,OF,OFF,OFFSETS,ON,OPEN,OPENDATASOURCE,OPENQUERY,OPENROWSET,OPENXML,OPTION,OR,ORDER,OUTER,OVER,PERCENT,PLAN,PRECISION,PRIMARY,PRINT,PROC,PROCEDURE,PUBLIC,RAISERROR,READ,READTEXT,RECONFIGURE,REFERENCES,REPLICATION,RESTORE,RESTRICT,RETURN,REVOKE,RIGHT,ROLLBACK,ROWCOUNT,ROWGUIDCOL,RULE,SAVE,SCHEMA,SELECT,SESSION_USER,SET,SETUSER,SHUTDOWN,SOME,STATISTICS,SYSTEM_USER,TABLE,TEXTSIZE,THEN,TO,TOP,TRAN,DATABASE,TRANSACTION,TRIGGER,TRUNCATE,TSEQUAL,UNION,UNIQUE,UPDATE,UPDATETEXT,USE,USER,VALUES,VARYING,VIEW,WAITFOR,WHEN,WHERE,WHILE,WITH,WRITETEXT"));
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("LockOptionAfterFromClause");
      this.supportedOptions.add("LockOptionWithinJoinClause");
      this.supportedOptions.add("AnalysisMethods");
      this.supportedOptions.add("StoredProcs");
      this.supportedOptions.add("OrderByNullsUsingCaseNull");
      this.supportedOptions.remove("BooleanExpression");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("FkDeleteActionDefault");
      this.supportedOptions.remove("FkDeleteActionRestrict");
      this.supportedOptions.remove("FkDeleteActionNull");
      this.supportedOptions.remove("FkUpdateActionDefault");
      this.supportedOptions.remove("FkUpdateActionRestrict");
      this.supportedOptions.remove("FkUpdateActionNull");
      if (this.datastoreMajorVersion >= 11) {
         this.supportedOptions.add("Sequences");
      }

      if (this.datastoreMajorVersion >= 12) {
         this.supportedOptions.add("BitwiseAndOperator");
         this.supportedOptions.add("BitwiseOrOperator");
         this.supportedOptions.add("BitwiseXOrOperator");
      }

   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      SQLTypeInfo sqlType = new MSSQLTypeInfo("UNIQUEIDENTIFIER", (short)1, 36, "'", "'", "", 1, false, (short)2, false, false, false, "UNIQUEIDENTIFIER", (short)0, (short)0, 10);
      sqlType.setAllowsPrecisionSpec(false);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-11, sqlType, true);
      sqlType = new MSSQLTypeInfo("IMAGE", (short)2004, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, false, (short)1, false, false, false, "BLOB", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2004, sqlType, true);
      sqlType = new MSSQLTypeInfo("TEXT", (short)2005, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, true, (short)1, false, false, false, "TEXT", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2005, sqlType, true);
      sqlType = new MSSQLTypeInfo("float", (short)8, 53, (String)null, (String)null, (String)null, 1, false, (short)2, false, false, false, (String)null, (short)0, (short)0, 2);
      this.addSQLTypeForJDBCType(handler, mconn, (short)8, sqlType, true);
      sqlType = new MSSQLTypeInfo("IMAGE", (short)-4, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, false, (short)1, false, false, false, "LONGVARBINARY", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-4, sqlType, true);
      if (this.datastoreMajorVersion > 9) {
         sqlType = new MSSQLTypeInfo("TIME", (short)92, 0, (String)null, (String)null, (String)null, 1, false, (short)1, true, true, false, "TIME", (short)0, (short)0, 0);
         this.addSQLTypeForJDBCType(handler, mconn, (short)92, sqlType, true);
         sqlType = new MSSQLTypeInfo("DATE", (short)91, 0, (String)null, (String)null, (String)null, 1, false, (short)1, true, true, false, "DATE", (short)0, (short)0, 0);
         this.addSQLTypeForJDBCType(handler, mconn, (short)91, sqlType, true);
      }

   }

   public String getVendorID() {
      return "sqlserver";
   }

   public String getCatalogName(Connection conn) throws SQLException {
      String catalog = conn.getCatalog();
      return catalog != null ? catalog : "";
   }

   public String getSchemaName(Connection conn) throws SQLException {
      if (this.datastoreMajorVersion >= 9) {
         Statement stmt = conn.createStatement();

         String var5;
         try {
            String stmtText = "SELECT SCHEMA_NAME();";
            ResultSet rs = stmt.executeQuery(stmtText);

            try {
               if (!rs.next()) {
                  throw (new NucleusDataStoreException("No result returned from " + stmtText)).setFatal();
               }

               var5 = rs.getString(1);
            } finally {
               rs.close();
            }
         } finally {
            stmt.close();
         }

         return var5;
      } else {
         return "";
      }
   }

   public boolean isReservedKeyword(String word) {
      if (super.isReservedKeyword(word)) {
         return true;
      } else {
         return word != null && word.indexOf(32) >= 0;
      }
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      throw new UnsupportedOperationException("SQLServer does not support dropping schema with cascade. You need to drop all tables first");
   }

   public String getCreateIndexStatement(Index idx, IdentifierFactory factory) {
      String idxIdentifier = factory.getIdentifierInAdapterCase(idx.getName());
      return "CREATE " + (idx.getUnique() ? "UNIQUE " : "") + "INDEX " + idxIdentifier + " ON " + idx.getTable().toString() + ' ' + idx + (idx.getExtendedIndexSettings() == null ? "" : " " + idx.getExtendedIndexSettings());
   }

   public String getSelectNewUUIDStmt() {
      return "SELECT NEWID()";
   }

   public String getNewUUIDFunction() {
      return "NEWID()";
   }

   public boolean supportsQueryFetchSize(int size) {
      return size >= 1;
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

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      SQLTypeInfo ti = new MSSQLTypeInfo(rs);
      String typeName = ti.getTypeName();
      return typeName.toLowerCase().startsWith("tinyint") ? null : ti;
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

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "SELECT @@IDENTITY";
   }

   public String getAutoIncrementKeyword() {
      return "IDENTITY";
   }

   public boolean isIdentityFieldDataType(String columnDef) {
      if (columnDef == null) {
         return false;
      } else {
         return columnDef.equalsIgnoreCase("uniqueidentifier");
      }
   }

   public String getInsertStatementForNoColumns(Table table) {
      return "INSERT INTO " + table.toString() + " DEFAULT VALUES";
   }

   public String getOperatorConcat() {
      return "+";
   }

   public String getSelectWithLockOption() {
      return "(UPDLOCK, ROWLOCK)";
   }

   public boolean validToSelectMappingInStatement(SQLStatement stmt, JavaTypeMapping m) {
      if (m.getNumberOfDatastoreMappings() <= 0) {
         return true;
      } else {
         for(int i = 0; i < m.getNumberOfDatastoreMappings(); ++i) {
            Column col = m.getDatastoreMapping(i).getColumn();
            if (col.getJdbcType() == JdbcType.CLOB || col.getJdbcType() == JdbcType.BLOB) {
               if (stmt.isDistinct()) {
                  NucleusLogger.QUERY.debug("Not selecting " + m + " since is for BLOB/CLOB and using DISTINCT");
                  return false;
               }

               if (stmt.getNumberOfUnions() > 0) {
                  NucleusLogger.QUERY.debug("Not selecting " + m + " since is for BLOB/CLOB and using UNION");
                  return false;
               }
            }
         }

         return true;
      }
   }

   public boolean isStatementTimeout(SQLException sqle) {
      return sqle.getSQLState() != null && sqle.getSQLState().equalsIgnoreCase("HY008") ? true : super.isStatementTimeout(sqle);
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      if (this.datastoreMajorVersion < 11) {
         return "";
      } else if (offset <= 0L && count <= 0L) {
         return "";
      } else if (!hasOrdering) {
         return "";
      } else {
         StringBuilder str = new StringBuilder();
         str.append("OFFSET " + offset + (offset == 1L ? " ROW " : " ROWS "));
         if (count > 0L) {
            str.append("FETCH NEXT " + count + (count == 1L ? " ROW " : " ROWS ") + "ONLY");
         }

         return str.toString();
      }
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (this.datastoreMajorVersion < 11) {
         return super.getSequenceCreateStmt(sequence_name, min, max, start, increment, cache_size);
      } else if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE ");
         stmt.append(sequence_name);
         if (start != null) {
            stmt.append(" START WITH " + start);
         }

         if (increment != null) {
            stmt.append(" INCREMENT BY " + increment);
         }

         if (min != null) {
            stmt.append(" MINVALUE " + min);
         }

         if (max != null) {
            stmt.append(" MAXVALUE " + max);
         }

         if (cache_size != null) {
            stmt.append(" CACHE " + cache_size);
         } else {
            stmt.append(" CACHE 1");
         }

         return stmt.toString();
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (this.datastoreMajorVersion < 11) {
         return super.getSequenceNextStmt(sequence_name);
      } else if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("SELECT NEXT VALUE FOR '");
         stmt.append(sequence_name);
         stmt.append("'");
         return stmt.toString();
      }
   }
}
