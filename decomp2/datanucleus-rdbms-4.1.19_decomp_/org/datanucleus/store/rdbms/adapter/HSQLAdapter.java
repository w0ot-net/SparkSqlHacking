package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.HSQLTypeInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.Localiser;

public class HSQLAdapter extends BaseDatastoreAdapter {
   public HSQLAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("Sequences");
      this.supportedOptions.add("UniqueInEndCreateStatements");
      if (this.datastoreMajorVersion < 2) {
         this.supportedOptions.remove("StatementBatching");
         this.supportedOptions.remove("GetGeneratedKeysStatement");
      }

      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("CheckInCreateStatements");
      this.supportedOptions.remove("AutoIncrementNullSpecification");
      if (this.datastoreMajorVersion >= 2) {
         this.supportedOptions.add("LockWithSelectForUpdate");
         this.supportedOptions.add("OrderByWithNullsDirectives");
      }

      if (this.datastoreMajorVersion >= 1 && (this.datastoreMajorVersion != 1 || this.datastoreMinorVersion >= 7) && (this.datastoreMajorVersion != 1 || this.datastoreMinorVersion != 7 || this.datastoreRevisionVersion >= 2)) {
         this.supportedOptions.add("CheckInEndCreateStatements");
      } else {
         this.supportedOptions.remove("CheckInEndCreateStatements");
      }

      this.supportedOptions.remove("AccessParentQueryInSubquery");
      this.supportedOptions.remove("SubqueryInHaving");
      if (this.datastoreMajorVersion >= 1 && (this.datastoreMajorVersion != 1 || this.datastoreMinorVersion >= 7)) {
         if (this.datastoreMajorVersion < 2) {
            this.supportedOptions.remove("FkDeleteActionRestrict");
            this.supportedOptions.remove("FkUpdateActionRestrict");
         }
      } else {
         this.supportedOptions.remove("FkDeleteActionCascade");
         this.supportedOptions.remove("FkDeleteActionRestrict");
         this.supportedOptions.remove("FkDeleteActionDefault");
         this.supportedOptions.remove("FkDeleteActionNull");
         this.supportedOptions.remove("FkUpdateActionCascade");
         this.supportedOptions.remove("FkUpdateActionRestrict");
         this.supportedOptions.remove("FkUpdateActionDefault");
         this.supportedOptions.remove("FkUpdateActionNull");
      }

      if (this.datastoreMajorVersion < 2) {
         this.supportedOptions.remove("TxIsolationReadRepeatableRead");
         if (this.datastoreMinorVersion <= 7) {
            this.supportedOptions.remove("TxIsolationReadCommitted");
            this.supportedOptions.remove("TxIsolationSerializable");
         }
      }

      this.supportedOptions.remove("TxIsolationNone");
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      SQLTypeInfo sqlType = new HSQLTypeInfo("LONGVARCHAR", (short)2005, Integer.MAX_VALUE, "'", "'", (String)null, 1, true, (short)3, false, false, false, "LONGVARCHAR", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2005, sqlType, true);
      SQLTypeInfo var6 = new HSQLTypeInfo("LONGVARBINARY", (short)2004, Integer.MAX_VALUE, "'", "'", (String)null, 1, false, (short)3, false, false, false, "LONGVARBINARY", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)2004, var6, true);
      if (this.datastoreMajorVersion >= 2) {
         var6 = new HSQLTypeInfo("LONGVARBINARY", (short)-4, Integer.MAX_VALUE, "'", "'", (String)null, 1, false, (short)3, false, false, false, "LONGVARBINARY", (short)0, (short)0, 0);
         this.addSQLTypeForJDBCType(handler, mconn, (short)-4, var6, true);
      }

      var6 = new HSQLTypeInfo("LONGVARCHAR", (short)-1, Integer.MAX_VALUE, "'", "'", (String)null, 1, true, (short)3, false, false, false, "LONGVARCHAR", (short)0, (short)0, 0);
      this.addSQLTypeForJDBCType(handler, mconn, (short)-1, var6, true);
      Collection<SQLTypeInfo> sqlTypes = this.getSQLTypeInfoForJdbcType(handler, mconn, (short)2004);
      if (sqlTypes != null) {
         for(SQLTypeInfo var9 : sqlTypes) {
            var9.setAllowsPrecisionSpec(false);
         }
      }

      sqlTypes = this.getSQLTypeInfoForJdbcType(handler, mconn, (short)2005);
      if (sqlTypes != null) {
         for(SQLTypeInfo var10 : sqlTypes) {
            var10.setAllowsPrecisionSpec(false);
         }
      }

      sqlTypes = this.getSQLTypeInfoForJdbcType(handler, mconn, (short)-4);
      if (sqlTypes != null) {
         for(SQLTypeInfo var11 : sqlTypes) {
            var11.setAllowsPrecisionSpec(false);
         }
      }

      sqlTypes = this.getSQLTypeInfoForJdbcType(handler, mconn, (short)-1);
      if (sqlTypes != null) {
         for(SQLTypeInfo var12 : sqlTypes) {
            var12.setAllowsPrecisionSpec(false);
         }
      }

   }

   public String getVendorID() {
      return "hsql";
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

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      return "DROP SCHEMA IF EXISTS " + schemaName + " CASCADE";
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

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new HSQLTypeInfo(rs);
   }

   public String getSchemaName(Connection conn) throws SQLException {
      return "";
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
      return "GENERATED BY DEFAULT AS IDENTITY";
   }

   public String getInsertStatementForNoColumns(Table table) {
      return "INSERT INTO " + table.toString() + " VALUES (null)";
   }

   public boolean isValidPrimaryKeyType(JdbcType datatype) {
      return datatype != JdbcType.BLOB && datatype != JdbcType.CLOB && datatype != JdbcType.LONGVARBINARY && datatype != JdbcType.OTHER && datatype != JdbcType.LONGVARCHAR;
   }

   public String getDatastoreDateStatement() {
      return "CALL NOW()";
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE ");
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
               throw new NucleusUserException(Localiser.msg("051023"));
            } else {
               return stmt.toString();
            }
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
}
