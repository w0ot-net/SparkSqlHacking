package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.DerbyTypeInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class DerbyAdapter extends BaseDatastoreAdapter {
   private static final String CLOUDSCAPE_RESERVED_WORDS = "ADD,ALL,ALLOCATE,ALTER,AND,ANY,ARE,AS,ASC,ASSERTION,AT,AUTHORIZATION,AVG,BEGIN,BETWEEN,BIT,BIT_LENGTH,BOOLEAN,BOTH,BY,CALL,CASCADE,CASCADED,CASE,CAST,CHAR,CHARACTER,CHARACTER_LENGTH,CHAR_LENGTH,CHECK,CLOSE,COLLATE,COLLATION,COLUMN,COMMIT,CONNECT,CONNECTION,CONSTRAINT,CONSTRAINTS,CONTINUE,CONVERT,CORRESPONDING,COUNT,CREATE,CROSS,CURRENT,CURRENT_DATE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_USER,CURSOR,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFERRABLE,DEFERRED,DELETE,DESC,DESCRIBE,DIAGNOSTICS,DISCONNECT,DISTINCT,DOUBLE,DROP,ELSE,END,ENDEXEC,ESCAPE,EXCEPT,EXCEPTION,EXEC,EXECUTE,EXISTS,EXPLAIN,EXTERNAL,EXTRACT,FALSE,FETCH,FIRST,FLOAT,FOR,FOREIGN,FOUND,FROM,FULL,FUNCTION,GET,GET_CURRENT_CONNECTION,GLOBAL,GO,GOTO,GRANT,GROUP,HAVING,HOUR,IDENTITY,IMMEDIATE,IN,INDICATOR,INITIALLY,INNER,INOUT,INPUT,INSENSITIVE,INSERT,INT,INTEGER,INTERSECT,INTO,IS,ISOLATION,JOIN,KEY,LAST,LEADING,LEFT,LIKE,LOCAL,LONGINT,LOWER,LTRIM,MATCH,MAX,MIN,MINUTE,NATIONAL,NATURAL,NCHAR,NVARCHAR,NEXT,NO,NOT,NULL,NULLIF,NUMERIC,OCTET_LENGTH,OF,ON,ONLY,OPEN,OPTION,OR,ORDER,OUT,OUTER,OUTPUT,OVERLAPS,PAD,PARTIAL,PREPARE,PRESERVE,PRIMARY,PRIOR,PRIVILEGES,PROCEDURE,PUBLIC,READ,REAL,REFERENCES,RELATIVE,RESTRICT,REVOKE,RIGHT,ROLLBACK,ROWS,RTRIM,RUNTIMESTATISTICS,SCHEMA,SCROLL,SECOND,SELECT,SESSION_USER,SET,SMALLINT,SOME,SPACE,SQL,SQLCODE,SQLERROR,SQLSTATE,SUBSTR,SUBSTRING,SUM,SYSTEM_USER,TABLE,TEMPORARY,TIMEZONE_HOUR,TIMEZONE_MINUTE,TINYINT,TO,TRAILING,TRANSACTION,TRANSLATE,TRANSLATION,TRIM,TRUE,UNION,UNIQUE,UNKNOWN,UPDATE,UPPER,USER,USING,VALUES,VARCHAR,VARYING,VIEW,WHENEVER,WHERE,WITH,WORK,WRITE,YEAR";

   public DerbyAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ADD,ALL,ALLOCATE,ALTER,AND,ANY,ARE,AS,ASC,ASSERTION,AT,AUTHORIZATION,AVG,BEGIN,BETWEEN,BIT,BIT_LENGTH,BOOLEAN,BOTH,BY,CALL,CASCADE,CASCADED,CASE,CAST,CHAR,CHARACTER,CHARACTER_LENGTH,CHAR_LENGTH,CHECK,CLOSE,COLLATE,COLLATION,COLUMN,COMMIT,CONNECT,CONNECTION,CONSTRAINT,CONSTRAINTS,CONTINUE,CONVERT,CORRESPONDING,COUNT,CREATE,CROSS,CURRENT,CURRENT_DATE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_USER,CURSOR,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFERRABLE,DEFERRED,DELETE,DESC,DESCRIBE,DIAGNOSTICS,DISCONNECT,DISTINCT,DOUBLE,DROP,ELSE,END,ENDEXEC,ESCAPE,EXCEPT,EXCEPTION,EXEC,EXECUTE,EXISTS,EXPLAIN,EXTERNAL,EXTRACT,FALSE,FETCH,FIRST,FLOAT,FOR,FOREIGN,FOUND,FROM,FULL,FUNCTION,GET,GET_CURRENT_CONNECTION,GLOBAL,GO,GOTO,GRANT,GROUP,HAVING,HOUR,IDENTITY,IMMEDIATE,IN,INDICATOR,INITIALLY,INNER,INOUT,INPUT,INSENSITIVE,INSERT,INT,INTEGER,INTERSECT,INTO,IS,ISOLATION,JOIN,KEY,LAST,LEADING,LEFT,LIKE,LOCAL,LONGINT,LOWER,LTRIM,MATCH,MAX,MIN,MINUTE,NATIONAL,NATURAL,NCHAR,NVARCHAR,NEXT,NO,NOT,NULL,NULLIF,NUMERIC,OCTET_LENGTH,OF,ON,ONLY,OPEN,OPTION,OR,ORDER,OUT,OUTER,OUTPUT,OVERLAPS,PAD,PARTIAL,PREPARE,PRESERVE,PRIMARY,PRIOR,PRIVILEGES,PROCEDURE,PUBLIC,READ,REAL,REFERENCES,RELATIVE,RESTRICT,REVOKE,RIGHT,ROLLBACK,ROWS,RTRIM,RUNTIMESTATISTICS,SCHEMA,SCROLL,SECOND,SELECT,SESSION_USER,SET,SMALLINT,SOME,SPACE,SQL,SQLCODE,SQLERROR,SQLSTATE,SUBSTR,SUBSTRING,SUM,SYSTEM_USER,TABLE,TEMPORARY,TIMEZONE_HOUR,TIMEZONE_MINUTE,TINYINT,TO,TRAILING,TRANSACTION,TRANSLATE,TRANSLATION,TRIM,TRUE,UNION,UNIQUE,UNKNOWN,UPDATE,UPPER,USER,USING,VALUES,VARCHAR,VARYING,VIEW,WHENEVER,WHERE,WITH,WORK,WRITE,YEAR"));
      this.supportedOptions.add("IdentityColumns");
      this.supportedOptions.add("LockWithSelectForUpdate");
      this.supportedOptions.add("CreateIndexesBeforeForeignKeys");
      this.supportedOptions.add("StoredProcs");
      this.supportedOptions.add("Sequences");
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.remove("NullsInCandidateKeys");
      this.supportedOptions.remove("ColumnOptions_DefaultWithNotNull");
      if (this.datastoreMajorVersion >= 10) {
         this.supportedOptions.remove("ColumnOptions_NullsKeyword");
      } else {
         this.supportedOptions.add("ColumnOptions_NullsKeyword");
      }

      if (this.datastoreMajorVersion < 10 || this.datastoreMajorVersion == 10 && this.datastoreMinorVersion < 6) {
         this.supportedOptions.remove("ANSI_CrossJoin_Syntax");
         this.supportedOptions.add("ANSI_CrossJoinAsInner11_Syntax");
         this.supportedOptions.remove("Sequences");
      }

      if (this.datastoreMajorVersion >= 11 || this.datastoreMinorVersion > 4) {
         this.supportedOptions.add("OrderByWithNullsDirectives");
      }

   }

   public void initialiseDatastore(Object conn) {
      try {
         Statement st = ((Connection)conn).createStatement();

         try {
            st.execute("DROP FUNCTION NUCLEUS_ASCII");
         } catch (SQLException var7) {
         }

         try {
            st.execute("CREATE FUNCTION NUCLEUS_ASCII(C CHAR(1)) RETURNS INTEGER EXTERNAL NAME 'org.datanucleus.store.rdbms.adapter.DerbySQLFunction.ascii' CALLED ON NULL INPUT LANGUAGE JAVA PARAMETER STYLE JAVA");
         } catch (SQLException sqle) {
            NucleusLogger.DATASTORE.warn(Localiser.msg("051027", new Object[]{sqle}));
         }

         try {
            st.execute("DROP FUNCTION NUCLEUS_MATCHES");
         } catch (SQLException var5) {
         }

         try {
            st.execute("CREATE FUNCTION NUCLEUS_MATCHES(TEXT VARCHAR(8000), PATTERN VARCHAR(8000)) RETURNS INTEGER EXTERNAL NAME 'org.datanucleus.store.rdbms.adapter.DerbySQLFunction.matches' CALLED ON NULL INPUT LANGUAGE JAVA PARAMETER STYLE JAVA");
         } catch (SQLException sqle) {
            NucleusLogger.DATASTORE.warn(Localiser.msg("051027", new Object[]{sqle}));
         }

         st.close();
      } catch (SQLException e) {
         NucleusLogger.DATASTORE_SCHEMA.warn("Exception when trying to initialise datastore", e);
         throw new NucleusDataStoreException(e.getMessage(), e);
      }
   }

   public String getSchemaName(Connection conn) throws SQLException {
      return conn.getMetaData().getUserName().toUpperCase();
   }

   public String getCatalogName(Connection conn) throws SQLException {
      String catalog = conn.getCatalog();
      return catalog != null ? catalog : "";
   }

   public String getVendorID() {
      return "derby";
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new DerbyTypeInfo(rs);
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      throw new UnsupportedOperationException("Derby does not support dropping schema with cascade. You need to drop all tables first");
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public String getAddCandidateKeyStatement(CandidateKey ck, IdentifierFactory factory) {
      if (ck.getName() != null) {
         String identifier = factory.getIdentifierInAdapterCase(ck.getName());
         return "CREATE UNIQUE INDEX " + identifier + " ON " + ck.getTable().toString() + " " + ck.getColumnList();
      } else {
         return "ALTER TABLE " + ck.getTable().toString() + " ADD " + ck;
      }
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "VALUES IDENTITY_VAL_LOCAL()";
   }

   public String getAutoIncrementKeyword() {
      return "generated always as identity (start with 1)";
   }

   public boolean isIdentityFieldDataType(String columnDef) {
      return columnDef != null && columnDef.toUpperCase().indexOf("AUTOINCREMENT") >= 0;
   }

   public String getInsertStatementForNoColumns(Table table) {
      return "INSERT INTO " + table.toString() + " VALUES (DEFAULT)";
   }

   public String getDatastoreDateStatement() {
      return "VALUES CURRENT_TIMESTAMP";
   }

   public String getSelectForUpdateText() {
      return "WITH RR";
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

   public String getNumericConversionFunction() {
      return "NUCLEUS_ASCII";
   }

   public boolean isStatementCancel(SQLException sqle) {
      return sqle.getSQLState().equalsIgnoreCase("XCL52");
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

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      if (sequence_name == null) {
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

         if (max != null) {
            stmt.append(" MAXVALUE " + max);
         } else {
            stmt.append(" NO MAXVALUE");
         }

         if (min != null) {
            stmt.append(" MINVALUE " + min);
         } else {
            stmt.append(" NO MINVALUE");
         }

         if (cache_size != null) {
            throw new NucleusUserException(Localiser.msg("051023"));
         } else {
            return stmt.toString();
         }
      }
   }

   public String getSequenceNextStmt(String sequence_name) {
      if (sequence_name == null) {
         throw new NucleusUserException(Localiser.msg("051028"));
      } else {
         StringBuilder stmt = new StringBuilder("VALUES NEXT VALUE FOR ");
         stmt.append(sequence_name);
         stmt.append(" ");
         return stmt.toString();
      }
   }
}
