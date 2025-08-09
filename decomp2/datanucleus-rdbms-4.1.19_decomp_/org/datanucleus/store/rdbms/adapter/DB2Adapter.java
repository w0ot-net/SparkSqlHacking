package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.schema.DB2TypeInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class DB2Adapter extends BaseDatastoreAdapter {
   public static final String DB2_RESERVED_WORDS = "ACCESS,ALIAS,ALLOW,ASUTIME,AUDIT,AUX,AUXILIARY,BUFFERPOOL,CAPTURE,CCSID,CLUSTER,COLLECTION,COLLID,COMMENT,CONCAT,CONTAINS,COUNT_BIG,CURRENT_LC_PATH,CURRENT_SERVER,CURRENT_TIMEZONE,DATABASE,DAYS,DB2GENERAL,DB2SQL,DBA,DBINFO,DBSPACE,DISALLOW,DSSIZE,EDITPROC,ERASE,EXCLUSIVE,EXPLAIN,FENCED,FIELDPROC,FILE,FINAL,GENERATED,GRAPHIC,HOURS,IDENTIFIED,INDEX,INTEGRITY,ISOBID,JAVA,LABEL,LC_CTYPE,LINKTYPE,LOCALE,LOCATORS,LOCK,LOCKSIZE,LONG,MICROSECOND,MICROSECONDS,MINUTES,MODE,MONTHS,NAME,NAMED,NHEADER,NODENAME,NODENUMBER,NULLS,NUMPARTS,OBID,OPTIMIZATION,OPTIMIZE,PACKAGE,PAGE,PAGES,PART,PCTFREE,PCTINDEX,PIECESIZE,PLAN,PRIQTY,PRIVATE,PROGRAM,PSID,QYERYNO,RECOVERY,RENAME,RESET,RESOURCE,RRN,RUN,SCHEDULE,SCRATCHPAD,SECONDS,SECQTY,SECURITY,SHARE,SIMPLE,SOURCE,STANDARD,STATISTICS,STAY,STOGROUP,STORES,STORPOOL,STYLE,SUBPAGES,SYNONYM,TABLESPACE,TYPE,VALIDPROC,VARIABLE,VARIANT,VCAT,VOLUMES,WLM,YEARS";

   public DB2Adapter(DatabaseMetaData metadata) {
      super(metadata);
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ACCESS,ALIAS,ALLOW,ASUTIME,AUDIT,AUX,AUXILIARY,BUFFERPOOL,CAPTURE,CCSID,CLUSTER,COLLECTION,COLLID,COMMENT,CONCAT,CONTAINS,COUNT_BIG,CURRENT_LC_PATH,CURRENT_SERVER,CURRENT_TIMEZONE,DATABASE,DAYS,DB2GENERAL,DB2SQL,DBA,DBINFO,DBSPACE,DISALLOW,DSSIZE,EDITPROC,ERASE,EXCLUSIVE,EXPLAIN,FENCED,FIELDPROC,FILE,FINAL,GENERATED,GRAPHIC,HOURS,IDENTIFIED,INDEX,INTEGRITY,ISOBID,JAVA,LABEL,LC_CTYPE,LINKTYPE,LOCALE,LOCATORS,LOCK,LOCKSIZE,LONG,MICROSECOND,MICROSECONDS,MINUTES,MODE,MONTHS,NAME,NAMED,NHEADER,NODENAME,NODENUMBER,NULLS,NUMPARTS,OBID,OPTIMIZATION,OPTIMIZE,PACKAGE,PAGE,PAGES,PART,PCTFREE,PCTINDEX,PIECESIZE,PLAN,PRIQTY,PRIVATE,PROGRAM,PSID,QYERYNO,RECOVERY,RENAME,RESET,RESOURCE,RRN,RUN,SCHEDULE,SCRATCHPAD,SECONDS,SECQTY,SECURITY,SHARE,SIMPLE,SOURCE,STANDARD,STATISTICS,STAY,STOGROUP,STORES,STORPOOL,STYLE,SUBPAGES,SYNONYM,TABLESPACE,TYPE,VALIDPROC,VARIABLE,VARIANT,VCAT,VOLUMES,WLM,YEARS"));
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("Sequences");
      this.supportedOptions.add("AnalysisMethods");
      this.supportedOptions.add("StoredProcs");
      this.supportedOptions.add("UseUnionAll");
      this.supportedOptions.add("OrderByWithNullsDirectives");
      this.supportedOptions.remove("BooleanExpression");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("NullsInCandidateKeys");
      this.supportedOptions.remove("ColumnOptions_NullsKeyword");
      this.supportedOptions.remove("DistinctWithSelectForUpdate");
      this.supportedOptions.remove("GroupingWithSelectForUpdate");
      this.supportedOptions.remove("HavingWithSelectForUpdate");
      this.supportedOptions.remove("OrderingWithSelectForUpdate");
      this.supportedOptions.remove("MultipleTablesWithSelectForUpdate");
      this.supportedOptions.remove("FkDeleteActionDefault");
      this.supportedOptions.remove("FkUpdateActionDefault");
      this.supportedOptions.remove("FkUpdateActionCascade");
      this.supportedOptions.remove("FkUpdateActionNull");
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      SQLTypeInfo sqlType = new DB2TypeInfo("FLOAT", (short)6, 53, (String)null, (String)null, (String)null, 1, false, (short)2, false, false, false, (String)null, (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)6, sqlType, true);
      SQLTypeInfo var4 = new DB2TypeInfo("NUMERIC", (short)2, 31, (String)null, (String)null, "PRECISION,SCALE", 1, false, (short)2, false, false, false, (String)null, (short)0, (short)31, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2, var4, true);
      var4 = new DB2TypeInfo("BIGINT", (short)-5, 20, (String)null, (String)null, (String)null, 1, false, (short)2, false, true, false, (String)null, (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-5, var4, true);
      var4 = new DB2TypeInfo("XML", (short)2009, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, false, (short)2, false, false, false, (String)null, (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2009, var4, true);
      var4 = new DB2TypeInfo("SMALLINT", (short)5, 5, (String)null, (String)null, (String)null, 1, false, (short)2, false, true, false, (String)null, (short)0, (short)0, 10);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-7, var4, true);
   }

   public String getVendorID() {
      return "db2";
   }

   public String getSchemaName(Connection conn) throws SQLException {
      Statement stmt = conn.createStatement();

      String var5;
      try {
         String stmtText = "VALUES (CURRENT SCHEMA)";
         ResultSet rs = stmt.executeQuery(stmtText);

         try {
            if (!rs.next()) {
               throw (new NucleusDataStoreException("No result returned from " + stmtText)).setFatal();
            }

            var5 = rs.getString(1).trim();
         } finally {
            rs.close();
         }
      } finally {
         stmt.close();
      }

      return var5;
   }

   public int getDatastoreIdentifierMaxLength(IdentifierType identifierType) {
      if (identifierType == IdentifierType.CANDIDATE_KEY) {
         return 18;
      } else if (identifierType == IdentifierType.FOREIGN_KEY) {
         return 18;
      } else if (identifierType == IdentifierType.INDEX) {
         return 18;
      } else {
         return identifierType == IdentifierType.PRIMARY_KEY ? 18 : super.getDatastoreIdentifierMaxLength(identifierType);
      }
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new DB2TypeInfo(rs);
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

   public String getDropDatabaseStatement(String schemaName, String catalogName) {
      throw new UnsupportedOperationException("DB2 does not support dropping schema with cascade. You need to drop all tables first");
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "VALUES IDENTITY_VAL_LOCAL()";
   }

   public String getAutoIncrementKeyword() {
      return "generated always as identity (start with 1)";
   }

   public String getContinuationString() {
      return "";
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE ");
         stmt.append(sequence_name);
         stmt.append(" AS INTEGER ");
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
            stmt.append(" NOCACHE");
         }

         return stmt.toString();
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("VALUES NEXTVAL FOR ");
         stmt.append(sequence_name);
         return stmt.toString();
      }
   }

   public String getRangeByRowNumberColumn() {
      return "row_number()over()";
   }

   public boolean isStatementCancel(SQLException sqle) {
      return sqle.getErrorCode() == -952;
   }

   public boolean isStatementTimeout(SQLException sqle) {
      return sqle.getSQLState() == null || !sqle.getSQLState().equalsIgnoreCase("57014") || sqle.getErrorCode() != -952 && sqle.getErrorCode() != -905 ? super.isStatementTimeout(sqle) : true;
   }
}
