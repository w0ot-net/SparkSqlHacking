package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class SQLAnywhereAdapter extends BaseDatastoreAdapter {
   protected int datastoreBuildVersion = -1;
   protected int driverBuildVersion = -1;
   protected boolean usingjConnect = true;

   public SQLAnywhereAdapter(DatabaseMetaData metadata) {
      super(metadata);

      try {
         this.datastoreBuildVersion = Integer.parseInt(this.datastoreProductVersion.substring(this.datastoreProductVersion.lastIndexOf(".") + 1));
         if (this.driverName.equals("SQL Anywhere JDBC Driver")) {
            this.usingjConnect = false;
            this.driverBuildVersion = Integer.parseInt(this.driverVersion.substring(this.driverVersion.lastIndexOf(".") + 1));
         } else {
            this.driverBuildVersion = -1;
         }
      } catch (Throwable var8) {
      }

      try {
         Connection conn = metadata.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT \"reserved_word\" FROM sa_reserved_words() ORDER BY \"reserved_word\"");

         while(rs.next()) {
            this.reservedKeywords.add(rs.getString(1).trim().toUpperCase());
         }

         rs.close();
         rs = stmt.executeQuery("SELECT \"option\", \"setting\" FROM SYS.SYSOPTION WHERE \"option\" = 'reserved_keywords' or \"option\" = 'non_keywords'");

         while(rs.next()) {
            if (rs.getString(1).toLowerCase().equals("reserved_keywords")) {
               String originalUserKeywords = rs.getString(2).trim().toUpperCase();
               StringTokenizer tokens = new StringTokenizer(originalUserKeywords, ",");
               Set<String> userReservedWordSet = new HashSet();

               while(tokens.hasMoreTokens()) {
                  userReservedWordSet.add(tokens.nextToken().trim().toUpperCase());
               }

               if (!userReservedWordSet.contains("LIMIT")) {
                  userReservedWordSet.add("LIMIT");
                  conn.createStatement().executeUpdate("SET OPTION PUBLIC.reserved_keywords = 'LIMIT" + (originalUserKeywords.length() != 0 ? "," : "") + originalUserKeywords + "'");
               }

               this.reservedKeywords.addAll(userReservedWordSet);
            } else if (rs.getString(1).toLowerCase().equals("non_keywords")) {
               this.reservedKeywords.removeAll(StringUtils.convertCommaSeparatedStringToSet(rs.getString(2).trim().toUpperCase()));
            }
         }

         rs.close();
         stmt.close();
      } catch (Throwable var9) {
      }

      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("StoredProcs");
      if (this.datastoreMajorVersion >= 12) {
         this.supportedOptions.add("Sequences");
      }

      this.supportedOptions.add("ProjectionInTableReferenceJoins");
      this.supportedOptions.add("AnalysisMethods");
      this.supportedOptions.add("CatalogInTableDefinition");
      this.supportedOptions.add("SchemaInTableDefinition");
      this.supportedOptions.add("LowerCaseIdentifiers");
      this.supportedOptions.add("MixedCaseIdentifiers");
      this.supportedOptions.add("UpperCaseIdentifiers");
      this.supportedOptions.add("LowerCaseQuotedIdentifiers");
      this.supportedOptions.add("MixedCaseQuotedIdentifiers");
      this.supportedOptions.add("UpperCaseQuotedIdentifiers");
      this.supportedOptions.add("AlterTableDropForeignKey_Syntax");
      this.supportedOptions.add("StatementBatching");
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("AutoIncrementPkInCreateTableColumnDef");
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("LockOptionAfterFromClause");
      this.supportedOptions.add("BitwiseAndOperator");
      this.supportedOptions.add("BitwiseOrOperator");
      this.supportedOptions.add("BitwiseXOrOperator");
      this.supportedOptions.remove("GetGeneratedKeysStatement");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("ANSI_Join_Syntax");
      this.supportedOptions.remove("ANSI_CrossJoin_Syntax");
      this.supportedOptions.remove("AutoIncrementNullSpecification");
      this.supportedOptions.remove("BooleanExpression");
      this.supportedJdbcTypesById.clear();
      this.supportedJdbcTypesById.put(-5, "BIGINT");
      this.supportedJdbcTypesById.put(-2, "BINARY");
      this.supportedJdbcTypesById.put(-7, "BIT");
      this.supportedJdbcTypesById.put(2004, "LONG BINARY");
      this.supportedJdbcTypesById.put(16, "BIT");
      this.supportedJdbcTypesById.put(1, "CHAR");
      this.supportedJdbcTypesById.put(2005, "LONG VARCHAR");
      this.supportedJdbcTypesById.put(91, "DATE");
      this.supportedJdbcTypesById.put(3, "DECIMAL");
      this.supportedJdbcTypesById.put(8, "DOUBLE");
      this.supportedJdbcTypesById.put(6, "FLOAT");
      this.supportedJdbcTypesById.put(4, "INTEGER");
      this.supportedJdbcTypesById.put(-4, "LONG BINARY");
      this.supportedJdbcTypesById.put(-1, "LONG VARCHAR");
      this.supportedJdbcTypesById.put(2, "NUMERIC");
      this.supportedJdbcTypesById.put(7, "REAL");
      this.supportedJdbcTypesById.put(5, "SMALLINT");
      this.supportedJdbcTypesById.put(2009, "XML");
      this.supportedJdbcTypesById.put(92, "TIME");
      this.supportedJdbcTypesById.put(93, "TIMESTAMP");
      this.supportedJdbcTypesById.put(-6, "TINYINT");
      this.supportedJdbcTypesById.put(-3, "BINARY");
      this.supportedJdbcTypesById.put(12, "VARCHAR");
      this.supportedJdbcTypesById.put(-9, "NVARCHAR");
      this.supportedJdbcTypesById.put(-15, "NCHAR");
      this.supportedJdbcTypesById.put(2011, "LONG NVARCHAR");
      this.supportedJdbcTypesById.put(1111, "OTHER");
      this.unsupportedJdbcTypesById.clear();
      this.unsupportedJdbcTypesById.put(2003, "ARRAY");
      this.unsupportedJdbcTypesById.put(70, "DATALINK");
      this.unsupportedJdbcTypesById.put(2001, "DISTINCT");
      this.unsupportedJdbcTypesById.put(2000, "JAVA_OBJECT");
      this.unsupportedJdbcTypesById.put(0, "NULL");
      this.unsupportedJdbcTypesById.put(2006, "REF");
      this.unsupportedJdbcTypesById.put(2002, "STRUCT");
   }

   public String getAddCandidateKeyStatement(CandidateKey ck, IdentifierFactory factory) {
      return super.getAddCandidateKeyStatement(ck, factory);
   }

   public String getVendorID() {
      return "sqlanywhere";
   }

   public String getCreateDatabaseStatement(String catalogName, String schemaName) throws UnsupportedOperationException {
      throw new UnsupportedOperationException("SQL Anywhere does not support CREATE DATABASE via a schema name");
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) throws UnsupportedOperationException {
      throw new UnsupportedOperationException("SQL Anywhere does not support DROP DATABASE via a schema name");
   }

   public String getCreateTableStatement(TableImpl table, Column[] columns, Properties props, IdentifierFactory factory) {
      StringBuilder createStmt = new StringBuilder();
      String indent = "    ";
      if (this.getContinuationString().length() == 0) {
         indent = "";
      }

      createStmt.append("CREATE TABLE ").append(table.toString()).append(this.getContinuationString()).append("(").append(this.getContinuationString());

      for(int i = 0; i < columns.length; ++i) {
         if (i > 0) {
            createStmt.append(",").append(this.getContinuationString());
         }

         createStmt.append(indent).append(columns[i].getSQLDefinition());
      }

      if (this.supportsOption("PrimaryKeyInCreateStatements")) {
         PrimaryKey pk = table.getPrimaryKey();
         if (pk != null && pk.size() > 0) {
            boolean includePk = true;
            if (this.supportsOption("AutoIncrementPkInCreateTableColumnDef")) {
               for(Column pkCol : pk.getColumns()) {
                  if (pkCol.isIdentity()) {
                     includePk = false;
                     break;
                  }
               }
            }

            if (includePk) {
               createStmt.append(",").append(this.getContinuationString());
               if (pk.getName() != null) {
                  String identifier = factory.getIdentifierInAdapterCase(pk.getName());
                  createStmt.append(indent).append("CONSTRAINT ").append(identifier).append(" ").append(pk.toString());
               } else {
                  createStmt.append(indent).append(pk.toString());
               }
            }
         }
      }

      if (this.supportsOption("UniqueInEndCreateStatements")) {
         StringBuilder uniqueConstraintStmt = new StringBuilder();

         for(int i = 0; i < columns.length; ++i) {
            if (columns[i].isUnique()) {
               if (uniqueConstraintStmt.length() < 1) {
                  uniqueConstraintStmt.append(",").append(this.getContinuationString());
                  uniqueConstraintStmt.append(indent).append(" UNIQUE (");
               } else {
                  uniqueConstraintStmt.append(",");
               }

               uniqueConstraintStmt.append(columns[i].getIdentifier().toString());
            }
         }

         if (uniqueConstraintStmt.length() > 1) {
            uniqueConstraintStmt.append(")");
            createStmt.append(uniqueConstraintStmt.toString());
         }
      }

      if (this.supportsOption("FKInEndCreateStatements")) {
         StringBuilder fkConstraintStmt = new StringBuilder();
         ClassLoaderResolver clr = table.getStoreManager().getNucleusContext().getClassLoaderResolver((ClassLoader)null);
         List<ForeignKey> fks = table.getExpectedForeignKeys(clr);
         if (fks != null && !fks.isEmpty()) {
            for(ForeignKey fk : fks) {
               createStmt.append(",").append(this.getContinuationString());
               if (fk.getName() != null) {
                  String identifier = factory.getIdentifierInAdapterCase(fk.getName());
                  createStmt.append(indent).append("CONSTRAINT ").append(identifier).append(" ").append(fk.toString());
               } else {
                  createStmt.append(indent).append(fk.toString());
               }
            }
         }

         if (fkConstraintStmt.length() > 1) {
            createStmt.append(fkConstraintStmt.toString());
         }
      }

      if (this.supportsOption("CheckInEndCreateStatements")) {
         StringBuilder checkConstraintStmt = new StringBuilder();

         for(int i = 0; i < columns.length; ++i) {
            if (columns[i].getConstraints() != null) {
               checkConstraintStmt.append(",").append(this.getContinuationString());
               checkConstraintStmt.append(indent).append(columns[i].getConstraints());
            }
         }

         if (checkConstraintStmt.length() > 1) {
            createStmt.append(checkConstraintStmt.toString());
         }
      }

      createStmt.append(this.getContinuationString()).append(")");
      return createStmt.toString();
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public String getSelectWithLockOption() {
      return "XLOCK";
   }

   public String getDeleteTableStatement(SQLTable tbl) {
      return "DELETE " + tbl.getAlias() + " FROM " + tbl.toString();
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getAddForeignKeyStatement(ForeignKey fk, IdentifierFactory factory) {
      return null;
   }

   public SQLText getUpdateTableStatement(SQLTable tbl, SQLText setSQL) {
      SQLText sql = (new SQLText("UPDATE ")).append(tbl.getAlias().toString());
      sql.append(" ").append(setSQL);
      sql.append(" FROM ").append(tbl.toString());
      return sql;
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      SQLTypeInfo info = new SQLTypeInfo(rs);
      return info.getTypeName().toLowerCase().startsWith("tinyint") ? null : info;
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
      return "NOT NULL DEFAULT AUTOINCREMENT";
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
            stmt.append(" NO CACHE");
         }

         return stmt.toString();
      }
   }

   public boolean sequenceExists(Connection conn, String catalogName, String schemaName, String seqName) {
      try {
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT 1 FROM SYS.SYSSEQUENCE WHERE SEQUENCENAME = '" + seqName + "'");
         boolean sequenceFound = rs.next();
         rs.close();
         stmt.close();
         return sequenceFound;
      } catch (Throwable var8) {
         return false;
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("SELECT ");
         stmt.append(sequence_name);
         stmt.append(".nextval");
         return stmt.toString();
      }
   }

   public String getSelectNewUUIDStmt() {
      return "SELECT newid()";
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) throws UnsupportedOperationException {
      if (offset >= 0L && count > 0L) {
         return "LIMIT " + offset + "," + count + " ";
      } else if (offset <= 0L && count > 0L) {
         return "LIMIT " + count + " ";
      } else {
         return offset >= 0L && count < 0L ? "LIMIT " + offset + "," + Long.MAX_VALUE + " " : "";
      }
   }
}
