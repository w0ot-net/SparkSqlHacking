package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.FirebirdTypeInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class FirebirdAdapter extends BaseDatastoreAdapter {
   public FirebirdAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("BooleanExpression");
      this.supportedOptions.remove("NullsInCandidateKeys");
      this.supportedOptions.remove("ColumnOptions_NullsKeyword");
      this.supportedOptions.remove("IncludeOrderByColumnsInSelect");
      this.supportedOptions.add("AlterTableDropForeignKey_Syntax");
      this.supportedOptions.add("CreateIndexesBeforeForeignKeys");
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("Sequences");
      this.supportedOptions.add("OrderByWithNullsDirectives");
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("GroupByIncludesAllSelectPrimaries");
      this.supportedOptions.remove("HoldCursorsOverCommit");
      if (this.datastoreMajorVersion < 2) {
         this.supportedOptions.remove("ANSI_CrossJoin_Syntax");
         this.supportedOptions.add("ANSI_CrossJoinAsInner11_Syntax");
      }

   }

   public String getVendorID() {
      return "firebird";
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new FirebirdTypeInfo(rs);
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("CREATE SEQUENCE ");
         stmt.append(sequence_name);
         return stmt.toString();
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("SELECT GEN_ID(");
         stmt.append(sequence_name);
         stmt.append(",1) FROM RDB$DATABASE");
         return stmt.toString();
      }
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      if (offset >= 0L && count > 0L) {
         return "ROWS " + (offset + 1L) + " TO " + (offset + count) + " ";
      } else if (offset <= 0L && count > 0L) {
         return "ROWS 1 TO " + count + " ";
      } else {
         return offset >= 0L && count < 0L ? "ROWS " + offset + " " : "";
      }
   }

   public boolean supportsCharLengthFunction() {
      return this.datastoreMajorVersion > 1;
   }
}
