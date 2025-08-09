package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.H2TypeInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class H2Adapter extends BaseDatastoreAdapter {
   private String schemaName;

   public H2Adapter(DatabaseMetaData metadata) {
      super(metadata);

      try {
         ResultSet rs = metadata.getSchemas();

         while(rs.next()) {
            if (rs.getBoolean("IS_DEFAULT")) {
               this.schemaName = rs.getString("TABLE_SCHEM");
            }
         }
      } catch (SQLException e) {
         NucleusLogger.DATASTORE_SCHEMA.warn("Exception when trying to get default schema name for datastore", e);
      }

      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("CheckInEndCreateStatements");
      this.supportedOptions.add("UniqueInEndCreateStatements");
      this.supportedOptions.add("OrderByWithNullsDirectives");
      this.supportedOptions.add("Sequences");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("TxIsolationReadRepeatableRead");
      this.supportedOptions.remove("TxIsolationNone");
      this.supportedOptions.add("CreateIndexesBeforeForeignKeys");
   }

   public String getVendorID() {
      return "h2";
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new H2TypeInfo(rs);
   }

   public int getDatastoreIdentifierMaxLength(IdentifierType identifierType) {
      if (identifierType == IdentifierType.TABLE) {
         return 128;
      } else if (identifierType == IdentifierType.COLUMN) {
         return 128;
      } else if (identifierType == IdentifierType.CANDIDATE_KEY) {
         return 128;
      } else if (identifierType == IdentifierType.FOREIGN_KEY) {
         return 128;
      } else if (identifierType == IdentifierType.INDEX) {
         return 128;
      } else if (identifierType == IdentifierType.PRIMARY_KEY) {
         return 128;
      } else {
         return identifierType == IdentifierType.SEQUENCE ? 128 : super.getDatastoreIdentifierMaxLength(identifierType);
      }
   }

   public String getCreateDatabaseStatement(String catalogName, String schemaName) {
      return "CREATE SCHEMA IF NOT EXISTS " + schemaName;
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      return "DROP SCHEMA IF EXISTS " + schemaName;
   }

   public String getAddColumnStatement(Table table, Column col) {
      return "ALTER TABLE " + table.toString() + " ADD COLUMN " + col.getSQLDefinition();
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      if (offset >= 0L && count > 0L) {
         return "LIMIT " + count + " OFFSET " + offset + " ";
      } else if (offset <= 0L && count > 0L) {
         return "LIMIT " + count + " ";
      } else {
         return offset >= 0L && count < 0L ? "LIMIT 2147483647 OFFSET " + offset + " " : "";
      }
   }

   public String getSchemaName(Connection conn) throws SQLException {
      return this.schemaName;
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "CALL IDENTITY()";
   }

   public String getAutoIncrementKeyword() {
      return "IDENTITY";
   }

   public String getInsertStatementForNoColumns(Table table) {
      return "INSERT INTO " + table.toString() + " VALUES(NULL)";
   }

   public boolean isValidPrimaryKeyType(JdbcType datatype) {
      return true;
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE IF NOT EXISTS ");
         stmt.append(sequence_name);
         if (min != null) {
            stmt.append(" START WITH " + min);
         } else if (start != null) {
            stmt.append(" START WITH " + start);
         }

         if (max != null) {
            throw new NucleusUserException(Localiser.msg("051022"));
         } else {
            if (increment != null) {
               stmt.append(" INCREMENT BY " + increment);
            }

            if (cache_size != null) {
               stmt.append(" CACHE " + cache_size);
            }

            return stmt.toString();
         }
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CALL NEXT VALUE FOR ");
         stmt.append(sequence_name);
         return stmt.toString();
      }
   }

   public boolean isStatementCancel(SQLException sqle) {
      return sqle.getErrorCode() == 90051;
   }
}
