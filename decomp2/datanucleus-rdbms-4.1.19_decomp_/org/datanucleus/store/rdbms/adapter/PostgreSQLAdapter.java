package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.ForeignKeyInfo;
import org.datanucleus.store.rdbms.schema.PostgresqlTypeInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class PostgreSQLAdapter extends BaseDatastoreAdapter {
   public static final String POSTGRESQL_RESERVED_WORDS = "ALL,ANALYSE,ANALYZE,DO,FREEZE,ILIKE,ISNULL,OFFSET,PLACING,VERBOSE";
   protected Hashtable psqlTypes;

   public PostgreSQLAdapter(DatabaseMetaData metadata) {
      super(metadata);
      if (this.datastoreMajorVersion < 7) {
         throw new NucleusDataStoreException("PostgreSQL version is " + this.datastoreMajorVersion + '.' + this.datastoreMinorVersion + ", 7.0 or later required");
      } else {
         if (this.datastoreMajorVersion == 7 && this.datastoreMinorVersion <= 2) {
            --this.maxTableNameLength;
            --this.maxConstraintNameLength;
            --this.maxIndexNameLength;
         }

         this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ALL,ANALYSE,ANALYZE,DO,FREEZE,ILIKE,ISNULL,OFFSET,PLACING,VERBOSE"));
         this.supportedOptions.add("LockWithSelectForUpdate");
         this.supportedOptions.add("SelectForUpdateNoWait");
         this.supportedOptions.add("PrimaryKeyInCreateStatements");
         this.supportedOptions.add("Sequences");
         this.supportedOptions.add("IdentityColumns");
         this.supportedOptions.add("OrderByWithNullsDirectives");
         this.supportedOptions.remove("AutoIncrementColumnTypeSpecification");
         this.supportedOptions.remove("AutoIncrementNullSpecification");
         this.supportedOptions.remove("DistinctWithSelectForUpdate");
         this.supportedOptions.remove("PersistOfUnassignedChar");
         if (this.datastoreMajorVersion >= 7 && (this.datastoreMajorVersion != 7 || this.datastoreMinorVersion >= 2)) {
            this.supportedOptions.add("AlterTableDropConstraint_Syntax");
         } else {
            this.supportedOptions.remove("AlterTableDropConstraint_Syntax");
         }

         this.supportedOptions.add("BitIsReallyBoolean");
         this.supportedOptions.add("CharColumnsPaddedWithSpaces");
         this.supportedOptions.add("StoredProcs");
         this.supportedOptions.remove("TxIsolationNone");
         this.supportedOptions.remove("UpdateStmtAllowTableAliasInSet");
         this.supportedOptions.remove("TxIsolationReadUncommitted");
         this.supportedOptions.remove("StoredProcs");
         this.supportedOptions.add("BitwiseAndOperator");
         this.supportedOptions.add("BitwiseOrOperator");
         this.supportedOptions.add("BitwiseXOrOperator");
      }
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      SQLTypeInfo sqlType = new PostgresqlTypeInfo("char", (short)1, 65000, (String)null, (String)null, (String)null, 0, false, (short)3, false, false, false, "char", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)1, sqlType, true);
      SQLTypeInfo var4 = new PostgresqlTypeInfo("text", (short)2005, 9, (String)null, (String)null, (String)null, 0, false, (short)3, false, false, false, (String)null, (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2005, var4, true);
      var4 = new PostgresqlTypeInfo("BYTEA", (short)2004, 9, (String)null, (String)null, (String)null, 0, false, (short)3, false, false, false, (String)null, (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2004, var4, true);
      var4 = new PostgresqlTypeInfo("bool", (short)16, 0, (String)null, (String)null, (String)null, 1, false, (short)3, true, false, false, "bool", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)16, var4, true);
      var4 = new PostgresqlTypeInfo("int2", (short)-6, 0, (String)null, (String)null, (String)null, 1, false, (short)3, false, false, false, "int2", (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-6, var4, true);
   }

   public String getVendorID() {
      return "postgresql";
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      SQLTypeInfo info = new PostgresqlTypeInfo(rs);
      if (this.psqlTypes == null) {
         this.psqlTypes = new Hashtable();
         this.psqlTypes.put("-7", "bool");
         this.psqlTypes.put("93", "timestamptz");
         this.psqlTypes.put("-5", "int8");
         this.psqlTypes.put("1", "char");
         this.psqlTypes.put("91", "date");
         this.psqlTypes.put("8", "float8");
         this.psqlTypes.put("4", "int4");
         this.psqlTypes.put("-1", "text");
         this.psqlTypes.put("2005", "text");
         this.psqlTypes.put("2004", "bytea");
         this.psqlTypes.put("2", "numeric");
         this.psqlTypes.put("7", "float4");
         this.psqlTypes.put("5", "int2");
         this.psqlTypes.put("92", "time");
         this.psqlTypes.put("12", "varchar");
         this.psqlTypes.put("1111", "uuid");
      }

      Object obj = this.psqlTypes.get("" + info.getDataType());
      if (obj != null) {
         String psql_type_name = (String)obj;
         if (!info.getTypeName().equalsIgnoreCase(psql_type_name)) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("051007", new Object[]{info.getTypeName(), this.getNameForJDBCType(info.getDataType())}));
            return null;
         }
      }

      return info;
   }

   public RDBMSColumnInfo newRDBMSColumnInfo(ResultSet rs) {
      RDBMSColumnInfo info = new RDBMSColumnInfo(rs);
      String typeName = info.getTypeName();
      if (typeName.equalsIgnoreCase("text")) {
         info.setDataType((short)-1);
      } else if (typeName.equalsIgnoreCase("bytea")) {
         info.setDataType((short)-4);
      }

      int columnSize = info.getColumnSize();
      if (columnSize > 65000) {
         info.setColumnSize(-1);
      }

      int decimalDigits = info.getDecimalDigits();
      if (decimalDigits > 65000) {
         info.setDecimalDigits(-1);
      }

      String columnDef = info.getColumnDef();
      if (columnDef != null && columnDef.contains("::")) {
         if (columnDef.startsWith("nextval(")) {
            info.setColumnDef((String)null);
         } else if ((!columnDef.startsWith("'") || !columnDef.endsWith("'")) && columnDef.contains("::")) {
            info.setColumnDef(columnDef.substring(0, columnDef.indexOf("::")));
         }
      }

      return info;
   }

   public ForeignKeyInfo newFKInfo(ResultSet rs) {
      ForeignKeyInfo info = super.newFKInfo(rs);
      String fkName = (String)info.getProperty("fk_name");
      int firstBackslashIdx = fkName.indexOf(92);
      if (firstBackslashIdx > 0) {
         info.addProperty("fk_name", fkName.substring(0, firstBackslashIdx));
      }

      return info;
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      return "DROP SCHEMA IF EXISTS " + schemaName + " CASCADE";
   }

   public String getAddColumnStatement(Table table, Column col) {
      return "ALTER TABLE " + table.toString() + " ADD COLUMN " + col.getSQLDefinition();
   }

   public String getInsertStatementForNoColumns(Table table) {
      return "INSERT INTO " + table.toString() + " VALUES (DEFAULT)";
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getDropTableStatement(Table table) {
      return this.datastoreMajorVersion >= 7 && (this.datastoreMajorVersion != 7 || this.datastoreMinorVersion >= 3) ? "DROP TABLE " + table.toString() + " CASCADE" : "DROP TABLE " + table.toString();
   }

   public String getCreateIndexStatement(Index idx, IdentifierFactory factory) {
      String idxIdentifier = factory.getIdentifierInAdapterCase(idx.getName());
      return "CREATE " + (idx.getUnique() ? "UNIQUE " : "") + "INDEX " + idxIdentifier + " ON " + idx.getTable().toString() + ' ' + idx + (idx.getExtendedIndexSettings() == null ? "" : " " + idx.getExtendedIndexSettings());
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      StringBuilder stmt = new StringBuilder("SELECT currval('");
      if (table.getSchemaName() != null) {
         stmt.append(table.getSchemaName().replace(this.getIdentifierQuoteString(), ""));
         stmt.append(this.getCatalogSeparator());
      }

      String tableName = table.getIdentifier().toString();
      boolean quoted = tableName.startsWith(this.getIdentifierQuoteString());
      if (quoted) {
         stmt.append(this.getIdentifierQuoteString());
      }

      stmt.append(tableName.replace(this.getIdentifierQuoteString(), ""));
      stmt.append("_");
      stmt.append(columnName.replace(this.getIdentifierQuoteString(), ""));
      stmt.append("_seq");
      if (quoted) {
         stmt.append(this.getIdentifierQuoteString());
      }

      stmt.append("')");
      return stmt.toString();
   }

   public String getAutoIncrementKeyword() {
      return "SERIAL";
   }

   public boolean sequenceExists(Connection conn, String catalogName, String schemaName, String seqName) {
      String stmtStr = "SELECT relname FROM pg_class WHERE relname=?";
      PreparedStatement ps = null;
      ResultSet rs = null;
      String seqNameToSearch = seqName;
      if (seqName.startsWith("\"") && seqName.endsWith("\"")) {
         seqNameToSearch = seqName.substring(1, seqName.length() - 1);
      }

      boolean var9;
      try {
         NucleusLogger.DATASTORE_NATIVE.debug(stmtStr + " : for sequence=" + seqNameToSearch);
         ps = conn.prepareStatement(stmtStr);
         ps.setString(1, seqNameToSearch);
         rs = ps.executeQuery();
         if (!rs.next()) {
            var9 = false;
            return var9;
         }

         var9 = true;
      } catch (SQLException sqle) {
         NucleusLogger.DATASTORE_RETRIEVE.debug("Exception while executing query for sequence " + seqNameToSearch + " : " + stmtStr + " - " + sqle.getMessage());
         return super.sequenceExists(conn, catalogName, schemaName, seqName);
      } finally {
         try {
            try {
               if (rs != null && !rs.isClosed()) {
                  rs.close();
               }
            } finally {
               if (ps != null) {
                  ps.close();
               }

            }
         } catch (SQLException var66) {
         }

      }

      return var9;
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE ");
         stmt.append(sequence_name);
         if (min != null) {
            stmt.append(" MINVALUE " + min);
         }

         if (max != null) {
            stmt.append(" MAXVALUE " + max);
         }

         if (start != null) {
            stmt.append(" START WITH " + start);
         }

         if (increment != null) {
            stmt.append(" INCREMENT BY " + increment);
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
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("SELECT nextval('");
         stmt.append(sequence_name);
         stmt.append("')");
         return stmt.toString();
      }
   }

   public boolean supportsQueryFetchSize(int size) {
      return this.driverMajorVersion > 7;
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      if (offset <= 0L && count <= 0L) {
         return "";
      } else if (this.datastoreMajorVersion < 8 || this.datastoreMajorVersion == 8 && this.datastoreMinorVersion <= 3) {
         String str = "";
         if (count > 0L) {
            str = str + "LIMIT " + count + " ";
         }

         if (offset >= 0L) {
            str = str + "OFFSET " + offset + " ";
         }

         return str;
      } else {
         StringBuilder str = new StringBuilder();
         if (offset > 0L) {
            str.append("OFFSET " + offset + (offset > 1L ? " ROWS " : " ROW "));
         }

         if (count > 0L) {
            str.append("FETCH NEXT " + (count > 1L ? count + " ROWS ONLY " : "ROW ONLY "));
         }

         return str.toString();
      }
   }

   public String getEscapePatternExpression() {
      return this.datastoreMajorVersion <= 8 && (this.datastoreMajorVersion != 8 || this.datastoreMinorVersion < 3) ? "ESCAPE '\\\\'" : "ESCAPE E'\\\\'";
   }

   public boolean isStatementCancel(SQLException sqle) {
      return sqle.getErrorCode() == 57014;
   }
}
