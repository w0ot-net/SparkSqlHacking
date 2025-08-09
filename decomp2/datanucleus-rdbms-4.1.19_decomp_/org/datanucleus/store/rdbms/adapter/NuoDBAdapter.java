package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.NuoDBTypeInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class NuoDBAdapter extends BaseDatastoreAdapter {
   public static final String NONSQL92_RESERVED_WORDS = "BIGINT,BINARY,BLOB,BOOLEAN,CLOB,LIMIT,NCLOB,OFFSET,ROLE,TRIGGER";
   public static final String NUODB_EXTRA_RESERVED_WORDS = "BITS,BREAK,CATCH,CONTAINING,END_FOR,END_IF,END_PROCEDURE,END_TRIGGER,END_TRY,END_WHILE,ENUM,FOR_UPDATE,IF,LOGICAL_AND,LOGICAL_NOT,LOGICAL_OR,NEXT_VALUE,NOT_BETWEEN,NOT_CONTAINING,NOT_IN,NOT_LIKE,NOT_STARTING,NVARCHAR,OFF,RECORD_BATCHING,REGEXP,SHOW,SMALLDATETIME,STARTING,STRING_TYPE,THROW,TINYBLOB,TINYINT,TRY,VAR,VER";

   public NuoDBAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("BIGINT,BINARY,BLOB,BOOLEAN,CLOB,LIMIT,NCLOB,OFFSET,ROLE,TRIGGER"));
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("BITS,BREAK,CATCH,CONTAINING,END_FOR,END_IF,END_PROCEDURE,END_TRIGGER,END_TRY,END_WHILE,ENUM,FOR_UPDATE,IF,LOGICAL_AND,LOGICAL_NOT,LOGICAL_OR,NEXT_VALUE,NOT_BETWEEN,NOT_CONTAINING,NOT_IN,NOT_LIKE,NOT_STARTING,NVARCHAR,OFF,RECORD_BATCHING,REGEXP,SHOW,SMALLDATETIME,STARTING,STRING_TYPE,THROW,TINYBLOB,TINYINT,TRY,VAR,VER"));
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("Sequences");
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("StoredProcs");
      if (this.maxTableNameLength <= 0) {
         this.maxTableNameLength = 128;
      }

      if (this.maxColumnNameLength <= 0) {
         this.maxColumnNameLength = 128;
      }

      if (this.maxConstraintNameLength <= 0) {
         this.maxConstraintNameLength = 128;
      }

      if (this.maxIndexNameLength <= 0) {
         this.maxIndexNameLength = 128;
      }

      this.supportedOptions.remove("ANSI_CrossJoin_Syntax");
      this.supportedOptions.add("ANSI_CrossJoinAsInner11_Syntax");
      this.supportedOptions.remove("FkDeleteActionRestrict");
      this.supportedOptions.remove("FkDeleteActionNull");
      this.supportedOptions.remove("FkUpdateActionRestrict");
      this.supportedOptions.remove("FkUpdateActionNull");
      this.supportedOptions.remove("FkDeleteActionCascade");
      this.supportedOptions.remove("FkDeleteActionDefault");
      this.supportedOptions.remove("FkUpdateActionCascade");
      this.supportedOptions.remove("FkUpdateActionDefault");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("ResultSetTypeScrollSens");
      this.supportedOptions.remove("ResultSetTypeScrollInsens");
      this.supportedOptions.remove("TxIsolationReadRepeatableRead");
      this.supportedOptions.remove("TxIsolationReadUncommitted");
      this.supportedOptions.remove("TxIsolationNone");
      this.supportedOptions.remove("AccessParentQueryInSubquery");
      this.supportedOptions.add("BitwiseAndOperator");
      this.supportedOptions.add("BitwiseOrOperator");
      this.supportedOptions.add("BitwiseXOrOperator");
   }

   public String getVendorID() {
      return "nuodb";
   }

   public String getCatalogName(Connection conn) throws SQLException {
      return null;
   }

   public String getSchemaName(Connection conn) throws SQLException {
      Statement stmt = conn.createStatement();

      String var5;
      try {
         String stmtText = "SELECT CURRENT_SCHEMA FROM DUAL";
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
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      SQLTypeInfo sqlType = new NuoDBTypeInfo("FLOAT", (short)8, 53, (String)null, (String)null, (String)null, 1, false, (short)2, false, false, false, (String)null, (short)0, (short)0, 2);
      this.addSQLTypeForJDBCType(handler, mconn, (short)8, sqlType, true);
      SQLTypeInfo var4 = new NuoDBTypeInfo("TEXT", (short)2005, Integer.MAX_VALUE, (String)null, (String)null, (String)null, 1, true, (short)1, false, false, false, "TEXT", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2005, var4, true);
   }

   public String getCreateIndexStatement(Index idx, IdentifierFactory factory) {
      String idxIdentifier = factory.getIdentifierInAdapterCase(idx.getName());
      return "CREATE " + (idx.getUnique() ? "UNIQUE " : "") + "INDEX " + idxIdentifier + " ON " + idx.getTable().toString() + ' ' + idx + (idx.getExtendedIndexSettings() == null ? "" : " " + idx.getExtendedIndexSettings());
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      return "DROP SCHEMA " + schemaName + " CASCADE";
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public boolean sequenceExists(Connection conn, String catalogName, String schemaName, String seqName) {
      return true;
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE ");
         stmt.append(sequence_name);
         if (start != null) {
            stmt.append(" START WITH " + start);
         }

         return stmt.toString();
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("SELECT NEXT VALUE FOR ");
         stmt.append(sequence_name);
         stmt.append(" FROM DUAL");
         return stmt.toString();
      }
   }

   public String getAutoIncrementKeyword() {
      return "GENERATED BY DEFAULT AS IDENTITY";
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      if (this.datastoreMajorVersion >= 10 && (this.datastoreMajorVersion != 10 || this.datastoreMinorVersion >= 5)) {
         if (offset <= 0L && count <= 0L) {
            return "";
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
      } else {
         return "";
      }
   }

   public String getDatastoreDateStatement() {
      return "SELECT CURRENT_DATE FROM DUAL";
   }
}
