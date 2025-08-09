package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.RDBMSMappingManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.ForeignKeyInfo;
import org.datanucleus.store.rdbms.schema.JDBCTypeInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.RDBMSTypesInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.store.rdbms.table.ViewImpl;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class BaseDatastoreAdapter implements DatastoreAdapter {
   protected Map supportedJdbcTypesById = new HashMap();
   protected Map unsupportedJdbcTypesById = new HashMap();
   protected final HashSet reservedKeywords = new HashSet();
   protected String datastoreProductName;
   protected String datastoreProductVersion;
   protected int datastoreMajorVersion;
   protected int datastoreMinorVersion;
   protected int datastoreRevisionVersion = 0;
   protected String identifierQuoteString;
   protected Collection supportedOptions = new HashSet();
   protected String driverName;
   protected String driverVersion;
   protected int driverMajorVersion;
   protected int driverMinorVersion;
   protected int maxTableNameLength;
   protected int maxConstraintNameLength;
   protected int maxIndexNameLength;
   protected int maxColumnNameLength;
   protected String catalogSeparator;
   protected Map properties = null;

   protected BaseDatastoreAdapter(DatabaseMetaData metadata) {
      this.supportedJdbcTypesById.put(-5, "BIGINT");
      this.supportedJdbcTypesById.put(-7, "BIT");
      this.supportedJdbcTypesById.put(2004, "BLOB");
      this.supportedJdbcTypesById.put(16, "BOOLEAN");
      this.supportedJdbcTypesById.put(1, "CHAR");
      this.supportedJdbcTypesById.put(2005, "CLOB");
      this.supportedJdbcTypesById.put(70, "DATALINK");
      this.supportedJdbcTypesById.put(91, "DATE");
      this.supportedJdbcTypesById.put(3, "DECIMAL");
      this.supportedJdbcTypesById.put(8, "DOUBLE");
      this.supportedJdbcTypesById.put(6, "FLOAT");
      this.supportedJdbcTypesById.put(4, "INTEGER");
      this.supportedJdbcTypesById.put(-4, "LONGVARBINARY");
      this.supportedJdbcTypesById.put(-1, "LONGVARCHAR");
      this.supportedJdbcTypesById.put(2, "NUMERIC");
      this.supportedJdbcTypesById.put(7, "REAL");
      this.supportedJdbcTypesById.put(5, "SMALLINT");
      this.supportedJdbcTypesById.put(92, "TIME");
      this.supportedJdbcTypesById.put(93, "TIMESTAMP");
      this.supportedJdbcTypesById.put(-6, "TINYINT");
      this.supportedJdbcTypesById.put(-3, "VARBINARY");
      this.supportedJdbcTypesById.put(12, "VARCHAR");
      this.supportedJdbcTypesById.put(-9, "NVARCHAR");
      this.supportedJdbcTypesById.put(-15, "NCHAR");
      this.supportedJdbcTypesById.put(2011, "NCLOB");
      this.supportedJdbcTypesById.put(1111, "OTHER");
      this.unsupportedJdbcTypesById.put(2003, "ARRAY");
      this.unsupportedJdbcTypesById.put(-2, "BINARY");
      this.unsupportedJdbcTypesById.put(2001, "DISTINCT");
      this.unsupportedJdbcTypesById.put(2000, "JAVA_OBJECT");
      this.unsupportedJdbcTypesById.put(0, "NULL");
      this.unsupportedJdbcTypesById.put(2006, "REF");
      this.unsupportedJdbcTypesById.put(2002, "STRUCT");
      this.unsupportedJdbcTypesById.put(2009, "SQLXML");
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ABSOLUTE,ACTION,ADD,ALL,ALLOCATE,ALTER,AND,ANY,ARE,AS,ASC,ASSERTION,AT,AUTHORIZATION,AVG,BEGIN,BETWEEN,BIT,BIT_LENGTH,BOTH,BY,CASCADE,CASCADED,CASE,CAST,CATALOG,CHAR,CHARACTER,CHAR_LENGTH,CHARACTER_LENGTH,CHECK,CLOSE,COALESCE,COLLATE,COLLATION,COLUMN,COMMIT,CONNECT,CONNECTION,CONSTRAINT,CONSTRAINTS,CONTINUE,CONVERT,CORRESPONDING,COUNT,CREATE,CROSS,CURRENT,CURRENT_DATE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_USER,CURSOR,DATE,DAY,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFAULT,DEFERRABLE,DEFERRED,DELETE,DESC,DESCRIBE,DESCRIPTOR,DIAGNOSTICS,DISCONNECT,DISTINCT,DOMAIN,DOUBLE,DROP,ELSE,END,END-EXEC,ESCAPE,EXCEPT,EXCEPTION,EXEC,EXECUTE,EXISTS,EXTERNAL,EXTRACT,FALSE,FETCH,FIRST,FLOAT,FOR,FOREIGN,FOUND,FROM,FULL,GET,GLOBAL,GO,GOTO,GRANT,GROUP,HAVING,HOUR,IDENTITY,IMMEDIATE,IN,INDICATOR,INITIALLY,INNER,INPUT,INSENSITIVE,INSERT,INT,INTEGER,INTERSECT,INTERVAL,INTO,IS,ISOLATION,JOIN,KEY,LANGUAGE,LAST,LEADING,LEFT,LEVEL,LIKE,LOCAL,LOWER,MATCH,MAX,MIN,MINUTE,MODULE,MONTH,NAMES,NATIONAL,NATURAL,NCHAR,NEXT,NO,NOT,NULL,NULLIF,NUMERIC,OCTET_LENGTH,OF,ON,ONLY,OPEN,OPTION,OR,ORDER,OUTER,OUTPUT,OVERLAPS,PAD,PARTIAL,POSITION,PRECISION,PREPARE,PRESERVE,PRIMARY,PRIOR,PRIVILEGES,PROCEDURE,PUBLIC,READ,REAL,REFERENCES,RELATIVE,RESTRICT,REVOKE,RIGHT,ROLLBACK,ROWS,SCHEMA,SCROLL,SECOND,SECTION,SELECT,SESSION,SESSION_USER,SET,SIZE,SMALLINT,SOME,SPACE,SQL,SQLCODE,SQLERROR,SQLSTATE,SUBSTRING,SUM,SYSTEM_USER,TABLE,TEMPORARY,THEN,TIME,TIMESTAMP,TIMEZONE_HOUR,TIMEZONE_MINUTE,TO,TRAILING,TRANSACTION,TRANSLATE,TRANSLATION,TRIM,TRUE,UNION,UNIQUE,UNKNOWN,UPDATE,UPPER,USAGE,USER,USING,VALUE,VALUES,VARCHAR,VARYING,VIEW,WHEN,WHENEVER,WHERE,WITH,WORK,WRITE,YEAR,ZONE"));
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ABSOLUTE,ACTION,ADD,AFTER,ALL,ALLOCATE,ALTER,AND,ANY,ARE,ARRAY,AS,ASC,ASENSITIVE,ASSERTION,ASYMMETRIC,AT,ATOMIC,AUTHORIZATION,BEFORE,BEGIN,BETWEEN,BINARY,BIT,BLOB,BOOLEAN,BOTH,BREADTH,BY,CALL,CALLED,CASCADE,CASCADED,CASE,CAST,CATALOG,CHAR,CHARACTER,CHECK,CLOB,CLOSE,COLLATE,COLLATION,COLUMN,COMMIT,CONDITION,CONNECT,CONNECTION,CONSTRAINT,CONSTRAINTS,CONSTRUCTOR,CONTINUE,CORRESPONDING,CREATE,CROSS,CUBE,CURRENT,CURRENT_DATE,CURRENT_DEFAULT_TRANSFORM_GROUP,CURRENT_PATH,CURRENT_ROLE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_TRANSFORM_GROUP_FOR_TYPE,CURRENT_USER,CURSOR,CYCLE,DATA,DATE,DAY,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFAULT,DEFERRABLE,DEFERRED,DELETE,DEPTH,DEREF,DESC,DESCRIBE,DESCRIPTOR,DETERMINISTIC,DIAGNOSTICS,DISCONNECT,DISTINCT,DO,DOMAIN,DOUBLE,DROP,DYNAMIC,EACH,ELSE,ELSEIF,END,EQUALS,ESCAPE,EXCEPT,EXCEPTION,EXEC,EXECUTE,EXISTS,EXIT,EXTERNAL,FALSE,FETCH,FILTER,FIRST,FLOAT,FOR,FOREIGN,FOUND,FREE,FROM,FULL,FUNCTION,GENERAL,GET,GLOBAL,GO,GOTO,GRANT,GROUP,GROUPING,HANDLER,HAVING,HOLD,HOUR,IDENTITY,IF,IMMEDIATE,IN,INDICATOR,INITIALLY,INNER,INOUT,INPUT,INSENSITIVE,INSERT,INT,INTEGER,INTERSECT,INTERVAL,INTO,IS,ISOLATION,ITERATE,JOIN,KEY,LANGUAGE,LARGE,LAST,LATERAL,LEADING,LEAVE,LEFT,LEVEL,LIKE,LOCAL,LOCALTIME,LOCALTIMESTAMP,LOCATOR,LOOP,MAP,MATCH,METHOD,MINUTE,MODIFIES,MODULE,MONTH,NAMES,NATIONAL,NATURAL,NCHAR,NCLOB,NEW,NEXT,NO,NONE,NOT,NULL,NUMERIC,OBJECT,OF,OLD,ON,ONLY,OPEN,OPTION,OR,ORDER,ORDINALITY,OUT,OUTER,OUTPUT,OVER,OVERLAPS,PAD,PARAMETER,PARTIAL,PARTITION,PATH,PRECISION,PREPARE,PRESERVE,PRIMARY,PRIOR,PRIVILEGES,PROCEDURE,PUBLIC,RANGE,READ,READS,REAL,RECURSIVE,REF,REFERENCES,REFERENCING,RELATIVE,RELEASE,REPEAT,RESIGNAL,RESTRICT,RESULT,RETURN,RETURNS,REVOKE,RIGHT,ROLE,ROLLBACK,ROLLUP,ROUTINE,ROW,ROWS,SAVEPOINT,SCHEMA,SCOPE,SCROLL,SEARCH,SECOND,SECTION,SELECT,SENSITIVE,SESSION,SESSION_USER,SET,SETS,SIGNAL,SIMILAR,SIZE,SMALLINT,SOME,SPACE,SPECIFIC,SPECIFICTYPE,SQL,SQLEXCEPTION,SQLSTATE,SQLWARNING,START,STATE,STATIC,SYMMETRIC,SYSTEM,SYSTEM_USER,TABLE,TEMPORARY,THEN,TIME,TIMESTAMP,TIMEZONE_HOUR,TIMEZONE_MINUTE,TO,TRAILING,TRANSACTION,TRANSLATION,TREAT,TRIGGER,TRUE,UNDER,UNDO,UNION,UNIQUE,UNKNOWN,UNNEST,UNTIL,UPDATE,USAGE,USER,USING,VALUE,VALUES,VARCHAR,VARYING,VIEW,WHEN,WHENEVER,WHERE,WHILE,WINDOW,WITH,WITHIN,WITHOUT,WORK,WRITE,YEAR,ZONE"));
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ADD,ALL,ALLOCATE,ALTER,AND,ANY,ARE,ARRAY,AS,ASENSITIVE,ASYMMETRIC,AT,ATOMIC,AUTHORIZATION,BEGIN,BETWEEN,BIGINT,BINARY,BLOB,BOOLEAN,BOTH,BY,CALL,CALLED,CASCADED,CASE,CAST,CHAR,CHARACTER,CHECK,CLOB,CLOSE,COLLATE,COLUMN,COMMIT,CONDITION,CONNECT,CONSTRAINT,CONTINUE,CORRESPONDING,CREATE,CROSS,CUBE,CURRENT,CURRENT_DATE,CURRENT_DEFAULT_TRANSFORM_GROUP,CURRENT_PATH,CURRENT_ROLE,CURRENT_TIME,CURRENT_TIMESTAMP,CURRENT_TRANSFORM_GROUP_FOR_TYPE,CURRENT_USER,CURSOR,CYCLE,DATE,DAY,DEALLOCATE,DEC,DECIMAL,DECLARE,DEFAULT,DELETE,DEREF,DESCRIBE,DETERMINISTIC,DISCONNECT,DISTINCT,DO,DOUBLE,DROP,DYNAMIC,EACH,ELEMENT,ELSE,ELSEIF,END,ESCAPE,EXCEPT,EXEC,EXECUTE,EXISTS,EXIT,EXTERNAL,FALSE,FETCH,FILTER,FLOAT,FOR,FOREIGN,FREE,FROM,FULL,FUNCTION,GET,GLOBAL,GRANT,GROUP,GROUPING,HANDLER,HAVING,HOLD,HOUR,IDENTITY,IF,IMMEDIATE,IN,INDICATOR,INNER,INOUT,INPUT,INSENSITIVE,INSERT,INT,INTEGER,INTERSECT,INTERVAL,INTO,IS,ITERATE,JOIN,LANGUAGE,LARGE,LATERAL,LEADING,LEAVE,LEFT,LIKE,LOCAL,LOCALTIME,LOCALTIMESTAMP,LOOP,MATCH,MEMBER,MERGE,METHOD,MINUTE,MODIFIES,MODULE,MONTH,MULTISET,NATIONAL,NATURAL,NCHAR,NCLOB,NEW,NO,NONE,NOT,NULL,NUMERIC,OF,OLD,ON,ONLY,OPEN,OR,ORDER,OUT,OUTER,OUTPUT,OVER,OVERLAPS,PARAMETER,PARTITION,PRECISION,PREPARE,PRIMARY,PROCEDURE,RANGE,READS,REAL,RECURSIVE,REF,REFERENCES,REFERENCING,RELEASE,REPEAT,RESIGNAL,RESULT,RETURN,RETURNS,REVOKE,RIGHT,ROLLBACK,ROLLUP,ROW,ROWS,SAVEPOINT,SCOPE,SCROLL,SEARCH,SECOND,SELECT,SENSITIVE,SESSION_USER,SET,SIGNAL,SIMILAR,SMALLINT,SOME,SPECIFIC,SPECIFICTYPE,SQL,SQLEXCEPTION,SQLSTATE,SQLWARNING,START,STATIC,SUBMULTISET,SYMMETRIC,SYSTEM,SYSTEM_USER,TABLE,TABLESAMPLE,THEN,TIME,TIMESTAMP,TIMEZONE_HOUR,TIMEZONE_MINUTE,TO,TRAILING,TRANSLATION,TREAT,TRIGGER,TRUE,UNDO,UNION,UNIQUE,UNKNOWN,UNNEST,UNTIL,UPDATE,USER,USING,VALUE,VALUES,VARCHAR,VARYING,WHEN,WHENEVER,WHERE,WHILE,WINDOW,WITH,WITHIN,WITHOUT,YEAR"));
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("ADA,C,CATALOG_NAME,CHARACTER_SET_CATALOG,CHARACTER_SET_NAME,CHARACTER_SET_SCHEMA,CLASS_ORIGIN,COBOL,COLLATION_CATALOG,COLLATION_NAME,COLLATION_SCHEMA,COLUMN_NAME,COMMAND_FUNCTION,COMMITTED,CONDITION_NUMBER,CONNECTION_NAME,CONSTRAINT_CATALOG,CONSTRAINT_NAME,CONSTRAINT_SCHEMA,CURSOR_NAME,DATA,DATETIME_INTERVAL_CODE,DATETIME_INTERVAL_PRECISION,DYNAMIC_FUNCTION,FORTRAN,LENGTH,MESSAGE_LENGTH,MESSAGE_OCTET_LENGTH,MESSAGE_TEXT,MORE,MUMPS,NAME,NULLABLE,NUMBER,PASCAL,PLI,REPEATABLE,RETURNED_LENGTH,RETURNED_OCTET_LENGTH,RETURNED_SQLSTATE,ROW_COUNT,SCALE,SCHEMA_NAME,SERIALIZABLE,SERVER_NAME,SUBCLASS_ORIGIN,TABLE_NAME,TYPE,UNCOMMITTED,UNNAMED"));

      try {
         try {
            String sqlKeywordsString = metadata.getSQLKeywords();
            this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet(sqlKeywordsString));
         } catch (SQLFeatureNotSupportedException var13) {
         }

         this.driverMinorVersion = metadata.getDriverMinorVersion();
         this.driverMajorVersion = metadata.getDriverMajorVersion();
         this.driverName = metadata.getDriverName();
         this.driverVersion = metadata.getDriverVersion();
         this.datastoreProductName = metadata.getDatabaseProductName();
         this.datastoreProductVersion = metadata.getDatabaseProductVersion();
         StringBuilder strippedProductVersion = new StringBuilder();
         char previousChar = ' ';

         for(int i = 0; i < this.datastoreProductVersion.length(); ++i) {
            char c = this.datastoreProductVersion.charAt(i);
            if (!Character.isDigit(c) && c != '.') {
               previousChar = ' ';
            } else {
               if (previousChar != ' ') {
                  if (strippedProductVersion.length() == 0) {
                     strippedProductVersion.append(previousChar);
                  }

                  strippedProductVersion.append(c);
               }

               previousChar = c;
            }
         }

         this.datastoreMajorVersion = metadata.getDatabaseMajorVersion();
         this.datastoreMinorVersion = metadata.getDatabaseMinorVersion();

         try {
            boolean noDBVersion = false;
            if (this.datastoreMajorVersion <= 0 && this.datastoreMinorVersion <= 0) {
               noDBVersion = true;
            }

            StringTokenizer parts = new StringTokenizer(strippedProductVersion.toString(), ".");
            if (parts.hasMoreTokens()) {
               if (noDBVersion) {
                  try {
                     this.datastoreMajorVersion = Integer.parseInt(parts.nextToken());
                  } catch (Exception var12) {
                     this.datastoreMajorVersion = -1;
                  }
               } else {
                  parts.nextToken();
               }
            }

            if (parts.hasMoreTokens()) {
               if (noDBVersion) {
                  try {
                     this.datastoreMinorVersion = Integer.parseInt(parts.nextToken());
                  } catch (Exception var11) {
                     this.datastoreMajorVersion = -1;
                  }
               } else {
                  parts.nextToken();
               }
            }

            if (parts.hasMoreTokens()) {
               try {
                  this.datastoreRevisionVersion = Integer.parseInt(parts.nextToken());
               } catch (Exception var10) {
                  this.datastoreRevisionVersion = -1;
               }
            }
         } catch (Throwable var14) {
            StringTokenizer parts = new StringTokenizer(strippedProductVersion.toString(), ".");
            if (parts.hasMoreTokens()) {
               try {
                  this.datastoreMajorVersion = Integer.parseInt(parts.nextToken());
               } catch (Exception var9) {
                  this.datastoreMajorVersion = -1;
               }
            }

            if (parts.hasMoreTokens()) {
               try {
                  this.datastoreMinorVersion = Integer.parseInt(parts.nextToken());
               } catch (Exception var8) {
                  this.datastoreMajorVersion = -1;
               }
            }

            if (parts.hasMoreTokens()) {
               try {
                  this.datastoreRevisionVersion = Integer.parseInt(parts.nextToken());
               } catch (Exception var7) {
                  this.datastoreRevisionVersion = -1;
               }
            }
         }

         this.maxTableNameLength = metadata.getMaxTableNameLength();
         this.maxConstraintNameLength = metadata.getMaxTableNameLength();
         this.maxIndexNameLength = metadata.getMaxTableNameLength();
         this.maxColumnNameLength = metadata.getMaxColumnNameLength();
         if (metadata.supportsCatalogsInTableDefinitions()) {
            this.supportedOptions.add("CatalogInTableDefinition");
         }

         if (metadata.supportsSchemasInTableDefinitions()) {
            this.supportedOptions.add("SchemaInTableDefinition");
         }

         if (metadata.supportsBatchUpdates()) {
            this.supportedOptions.add("StatementBatching");
         }

         if (metadata.storesLowerCaseIdentifiers()) {
            this.supportedOptions.add("LowerCaseIdentifiers");
         }

         if (metadata.storesMixedCaseIdentifiers()) {
            this.supportedOptions.add("MixedCaseIdentifiers");
         }

         if (metadata.storesUpperCaseIdentifiers()) {
            this.supportedOptions.add("UpperCaseIdentifiers");
         }

         if (metadata.storesLowerCaseQuotedIdentifiers()) {
            this.supportedOptions.add("LowerCaseQuotedIdentifiers");
         }

         if (metadata.storesMixedCaseQuotedIdentifiers()) {
            this.supportedOptions.add("MixedCaseQuotedIdentifiers");
         }

         if (metadata.storesUpperCaseQuotedIdentifiers()) {
            this.supportedOptions.add("UpperCaseQuotedIdentifiers");
         }

         if (metadata.supportsMixedCaseIdentifiers()) {
            this.supportedOptions.add("MixedCaseSensitiveIdentifiers");
         }

         if (metadata.supportsMixedCaseQuotedIdentifiers()) {
            this.supportedOptions.add("MixedCaseQuotedSensitiveIdentifiers");
         }

         this.supportedOptions.add("HoldCursorsOverCommit");
         this.catalogSeparator = metadata.getCatalogSeparator();
         this.catalogSeparator = this.catalogSeparator != null && this.catalogSeparator.trim().length() >= 1 ? this.catalogSeparator : ".";
         this.identifierQuoteString = metadata.getIdentifierQuoteString();
         this.identifierQuoteString = null != this.identifierQuoteString && this.identifierQuoteString.trim().length() >= 1 ? this.identifierQuoteString : "\"";
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("051004"), e);
      }

      this.supportedOptions.add("ResultSetTypeForwardOnly");
      this.supportedOptions.add("ResultSetTypeScrollSens");
      this.supportedOptions.add("ResultSetTypeScrollInsens");
      this.supportedOptions.add("RightOuterJoin");
      this.supportedOptions.add("SomeAllAnySubqueries");
      this.supportedOptions.add("UpdateStmtAllowTableAliasInSet");
      this.supportedOptions.add("UpdateDeleteStmtAllowTableAliasInWhere");
      this.supportedOptions.add("Views");
      this.supportedOptions.add("DateTimeStoresMillisecs");
      this.supportedOptions.add("EscapeExpressionInLikePredicate");
      this.supportedOptions.add("Union_Syntax");
      this.supportedOptions.add("Exists_Syntax");
      this.supportedOptions.add("AlterTableDropConstraint_Syntax");
      this.supportedOptions.add("DeferredConstraints");
      this.supportedOptions.add("DistinctWithSelectForUpdate");
      this.supportedOptions.add("GroupingWithSelectForUpdate");
      this.supportedOptions.add("HavingWithSelectForUpdate");
      this.supportedOptions.add("OrderingWithSelectForUpdate");
      this.supportedOptions.add("MultipleTablesWithSelectForUpdate");
      this.supportedOptions.add("PersistOfUnassignedChar");
      this.supportedOptions.add("CheckInCreateStatements");
      this.supportedOptions.add("GetGeneratedKeysStatement");
      this.supportedOptions.add("BooleanExpression");
      this.supportedOptions.add("NullsInCandidateKeys");
      this.supportedOptions.add("ColumnOptions_NullsKeyword");
      this.supportedOptions.add("ColumnOptions_DefaultKeyword");
      this.supportedOptions.add("ColumnOptions_DefaultWithNotNull");
      this.supportedOptions.add("ColumnOptions_DefaultBeforeNull");
      this.supportedOptions.add("ANSI_Join_Syntax");
      this.supportedOptions.add("ANSI_CrossJoin_Syntax");
      this.supportedOptions.add("AutoIncrementNullSpecification");
      this.supportedOptions.add("AutoIncrementColumnTypeSpecification");
      this.supportedOptions.add("IncludeOrderByColumnsInSelect");
      this.supportedOptions.add("AccessParentQueryInSubquery");
      this.supportedOptions.add("SubqueryInHaving");
      this.supportedOptions.add("FkDeleteActionCascade");
      this.supportedOptions.add("FkDeleteActionRestrict");
      this.supportedOptions.add("FkDeleteActionDefault");
      this.supportedOptions.add("FkDeleteActionNull");
      this.supportedOptions.add("FkUpdateActionCascade");
      this.supportedOptions.add("FkUpdateActionRestrict");
      this.supportedOptions.add("FkUpdateActionDefault");
      this.supportedOptions.add("FkUpdateActionNull");
      this.supportedOptions.add("TxIsolationReadCommitted");
      this.supportedOptions.add("TxIsolationReadUncommitted");
      this.supportedOptions.add("TxIsolationReadRepeatableRead");
      this.supportedOptions.add("TxIsolationSerializable");
   }

   public void initialiseDatastore(Object conn) {
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)handler.getStoreManager();
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      PluginManager pluginMgr = storeMgr.getNucleusContext().getPluginManager();
      MappingManager mapMgr = storeMgr.getMappingManager();
      mapMgr.loadDatastoreMapping(pluginMgr, clr, this.getVendorID());
      handler.getSchemaData(mconn.getConnection(), "types", (Object[])null);
   }

   public String getNameForJDBCType(int jdbcType) {
      String typeName = (String)this.supportedJdbcTypesById.get(jdbcType);
      if (typeName == null) {
         typeName = (String)this.unsupportedJdbcTypesById.get(jdbcType);
      }

      return typeName;
   }

   public int getJDBCTypeForName(String typeName) {
      if (typeName == null) {
         return 0;
      } else {
         for(Map.Entry entry : this.supportedJdbcTypesById.entrySet()) {
            if (typeName.equalsIgnoreCase((String)entry.getValue())) {
               return (Integer)entry.getKey();
            }
         }

         return 0;
      }
   }

   public void setProperties(Map props) {
      if (props != null) {
         this.properties = new HashMap();
      }

      this.properties.putAll(props);
   }

   public Object getValueForProperty(String name) {
      return this.properties != null ? this.properties.get(name) : null;
   }

   public void removeUnsupportedMappings(StoreSchemaHandler handler, ManagedConnection mconn) {
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)handler.getStoreManager();
      RDBMSMappingManager mapMgr = (RDBMSMappingManager)storeMgr.getMappingManager();
      RDBMSTypesInfo types = (RDBMSTypesInfo)handler.getSchemaData(mconn.getConnection(), "types", (Object[])null);

      for(int jdbcType : this.supportedJdbcTypesById.keySet()) {
         if (types.getChild("" + jdbcType) == null) {
            mapMgr.deregisterDatastoreMappingsForJDBCType((String)this.supportedJdbcTypesById.get(jdbcType));
         }
      }

      for(int jdbcType : this.unsupportedJdbcTypesById.keySet()) {
         if (types.getChild("" + jdbcType) == null) {
            mapMgr.deregisterDatastoreMappingsForJDBCType((String)this.unsupportedJdbcTypesById.get(jdbcType));
         }
      }

   }

   protected Collection getSQLTypeInfoForJdbcType(StoreSchemaHandler handler, ManagedConnection mconn, short jdbcTypeNumber) {
      RDBMSTypesInfo types = (RDBMSTypesInfo)handler.getSchemaData(mconn.getConnection(), "types", (Object[])null);
      String key = "" + jdbcTypeNumber;
      JDBCTypeInfo jdbcType = (JDBCTypeInfo)types.getChild(key);
      return jdbcType == null ? null : jdbcType.getChildren().values();
   }

   protected void addSQLTypeForJDBCType(StoreSchemaHandler handler, ManagedConnection mconn, short jdbcTypeNumber, SQLTypeInfo sqlType, boolean addIfNotPresent) {
      RDBMSTypesInfo types = (RDBMSTypesInfo)handler.getSchemaData(mconn.getConnection(), "types", (Object[])null);
      String key = "" + jdbcTypeNumber;
      JDBCTypeInfo jdbcType = (JDBCTypeInfo)types.getChild(key);
      if (jdbcType == null || addIfNotPresent) {
         if (jdbcType == null) {
            jdbcType = new JDBCTypeInfo(jdbcTypeNumber);
            types.addChild(jdbcType);
            jdbcType.addChild(sqlType);
         } else {
            jdbcType.addChild(sqlType);
         }

      }
   }

   public boolean supportsTransactionIsolation(int level) {
      return level == 0 && this.supportsOption("TxIsolationNone") || level == 2 && this.supportsOption("TxIsolationReadCommitted") || level == 1 && this.supportsOption("TxIsolationReadUncommitted") || level == 4 && this.supportsOption("TxIsolationReadRepeatableRead") || level == 8 && this.supportsOption("TxIsolationSerializable");
   }

   public Collection getSupportedOptions() {
      return this.supportedOptions;
   }

   public boolean supportsOption(String option) {
      return this.supportedOptions.contains(option);
   }

   public MappingManager getMappingManager(RDBMSStoreManager storeMgr) {
      return new RDBMSMappingManager(storeMgr);
   }

   public long getAdapterTime(Timestamp time) {
      long timestamp = this.getTime(time.getTime(), (long)time.getNanos());
      int ms = this.getMiliseconds((long)time.getNanos());
      return timestamp + (long)ms;
   }

   protected long getTime(long time, long nanos) {
      return nanos < 0L ? (time / 1000L - 1L) * 1000L : time / 1000L * 1000L;
   }

   protected int getMiliseconds(long nanos) {
      return (int)(nanos / 1000000L);
   }

   public String getDatastoreProductName() {
      return this.datastoreProductName;
   }

   public String getDatastoreProductVersion() {
      return this.datastoreProductVersion;
   }

   public String getDatastoreDriverName() {
      return this.driverName;
   }

   public String getDatastoreDriverVersion() {
      return this.driverVersion;
   }

   public boolean supportsQueryFetchSize(int size) {
      return true;
   }

   public String getVendorID() {
      return null;
   }

   public boolean isReservedKeyword(String word) {
      return this.reservedKeywords.contains(word.toUpperCase());
   }

   public String getIdentifierQuoteString() {
      return this.identifierQuoteString;
   }

   public int getDriverMajorVersion() {
      return this.driverMajorVersion;
   }

   public int getDriverMinorVersion() {
      return this.driverMinorVersion;
   }

   public int getDatastoreIdentifierMaxLength(IdentifierType identifierType) {
      if (identifierType == IdentifierType.TABLE) {
         return this.maxTableNameLength;
      } else if (identifierType == IdentifierType.COLUMN) {
         return this.maxColumnNameLength;
      } else if (identifierType == IdentifierType.CANDIDATE_KEY) {
         return this.maxConstraintNameLength;
      } else if (identifierType == IdentifierType.FOREIGN_KEY) {
         return this.maxConstraintNameLength;
      } else if (identifierType == IdentifierType.INDEX) {
         return this.maxIndexNameLength;
      } else if (identifierType == IdentifierType.PRIMARY_KEY) {
         return this.maxConstraintNameLength;
      } else {
         return identifierType == IdentifierType.SEQUENCE ? this.maxTableNameLength : -1;
      }
   }

   public int getMaxForeignKeys() {
      return 9999;
   }

   public int getMaxIndexes() {
      return 9999;
   }

   public Iterator iteratorReservedWords() {
      return this.reservedKeywords.iterator();
   }

   public RDBMSColumnInfo newRDBMSColumnInfo(ResultSet rs) {
      return new RDBMSColumnInfo(rs);
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new SQLTypeInfo(rs);
   }

   public ForeignKeyInfo newFKInfo(ResultSet rs) {
      return new ForeignKeyInfo(rs);
   }

   public int getUnlimitedLengthPrecisionValue(SQLTypeInfo typeInfo) {
      return typeInfo.getCreateParams() != null && typeInfo.getCreateParams().length() > 0 ? typeInfo.getPrecision() : -1;
   }

   public boolean isValidPrimaryKeyType(JdbcType datatype) {
      return datatype != JdbcType.BLOB && datatype != JdbcType.CLOB && datatype != JdbcType.LONGVARBINARY;
   }

   public String getSurrogateForEmptyStrings() {
      return null;
   }

   public int getTransactionIsolationForSchemaCreation() {
      return 8;
   }

   public int getRequiredTransactionIsolationLevel() {
      return -1;
   }

   public String getCatalogName(Connection conn) throws SQLException {
      throw new UnsupportedOperationException(Localiser.msg("051015", new Object[]{this.datastoreProductName, this.datastoreProductVersion}));
   }

   public String getSchemaName(Connection conn) throws SQLException {
      throw new UnsupportedOperationException(Localiser.msg("051016", new Object[]{this.datastoreProductName, this.datastoreProductVersion}));
   }

   public String getCatalogSeparator() {
      return this.catalogSeparator;
   }

   public String getSelectWithLockOption() {
      return null;
   }

   public String getSelectForUpdateText() {
      return "FOR UPDATE";
   }

   public String getSelectNewUUIDStmt() {
      return null;
   }

   public String getNewUUIDFunction() {
      return null;
   }

   public String getOrderString(StoreManager storeMgr, String orderString, SQLExpression sqlExpr) {
      return orderString;
   }

   public boolean validToSelectMappingInStatement(SQLStatement stmt, JavaTypeMapping m) {
      return true;
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      throw new UnsupportedOperationException(Localiser.msg("051019"));
   }

   public String getAutoIncrementKeyword() {
      throw new UnsupportedOperationException(Localiser.msg("051019"));
   }

   public Class getAutoIncrementJavaTypeForType(Class type) {
      return type;
   }

   public boolean isIdentityFieldDataType(String typeName) {
      throw new UnsupportedOperationException(Localiser.msg("051019"));
   }

   public String getInsertStatementForNoColumns(Table table) {
      return "INSERT INTO " + table.toString() + " () VALUES ()";
   }

   public boolean sequenceExists(Connection conn, String catalogName, String schemaName, String seqName) {
      return true;
   }

   public String getSequenceCreateStmt(String sequence_name, Integer min, Integer max, Integer start, Integer increment, Integer cache_size) {
      throw new UnsupportedOperationException(Localiser.msg("051020"));
   }

   public String getSequenceNextStmt(String sequence_name) {
      throw new UnsupportedOperationException(Localiser.msg("051020"));
   }

   public ResultSet getExistingIndexes(Connection conn, String catalog, String schema, String table) throws SQLException {
      return null;
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
               NucleusLogger.GENERAL.debug(">> TODO Add FK in CREATE TABLE as " + fk);
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

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      if (pk.getName() != null) {
         String identifier = factory.getIdentifierInAdapterCase(pk.getName());
         return "ALTER TABLE " + pk.getTable().toString() + " ADD CONSTRAINT " + identifier + ' ' + pk;
      } else {
         return "ALTER TABLE " + pk.getTable().toString() + " ADD " + pk;
      }
   }

   public String getAddCandidateKeyStatement(CandidateKey ck, IdentifierFactory factory) {
      if (ck.getName() != null) {
         String identifier = factory.getIdentifierInAdapterCase(ck.getName());
         return "ALTER TABLE " + ck.getTable().toString() + " ADD CONSTRAINT " + identifier + ' ' + ck;
      } else {
         return "ALTER TABLE " + ck.getTable().toString() + " ADD " + ck;
      }
   }

   public String getAddForeignKeyStatement(ForeignKey fk, IdentifierFactory factory) {
      if (fk.getName() != null) {
         String identifier = factory.getIdentifierInAdapterCase(fk.getName());
         return "ALTER TABLE " + fk.getTable().toString() + " ADD CONSTRAINT " + identifier + ' ' + fk;
      } else {
         return "ALTER TABLE " + fk.getTable().toString() + " ADD " + fk;
      }
   }

   public String getAddColumnStatement(Table table, Column col) {
      return "ALTER TABLE " + table.toString() + " ADD " + col.getSQLDefinition();
   }

   public String getCreateIndexStatement(Index idx, IdentifierFactory factory) {
      DatastoreIdentifier indexIdentifier = factory.newTableIdentifier(idx.getName());
      return "CREATE " + (idx.getUnique() ? "UNIQUE " : "") + "INDEX " + indexIdentifier.getFullyQualifiedName(true) + " ON " + idx.getTable().toString() + ' ' + idx + (idx.getExtendedIndexSettings() == null ? "" : " " + idx.getExtendedIndexSettings());
   }

   public String getCheckConstraintForValues(DatastoreIdentifier identifier, Object[] values, boolean nullable) {
      StringBuilder constraints = new StringBuilder("CHECK (");
      constraints.append(identifier);
      constraints.append(" IN (");

      for(int i = 0; i < values.length; ++i) {
         if (i > 0) {
            constraints.append(",");
         }

         if (values[i] instanceof String) {
            constraints.append("'").append(values[i]).append("'");
         } else {
            constraints.append(values[i]);
         }
      }

      constraints.append(")");
      if (nullable) {
         constraints.append(" OR " + identifier + " IS NULL");
      }

      constraints.append(")");
      return constraints.toString();
   }

   public String getCreateDatabaseStatement(String catalogName, String schemaName) {
      return "CREATE SCHEMA " + schemaName;
   }

   public String getDropDatabaseStatement(String catalogName, String schemaName) {
      return "DROP SCHEMA " + schemaName;
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString() + " CASCADE";
   }

   public String getDropViewStatement(ViewImpl view) {
      return "DROP VIEW " + view.toString();
   }

   public String getDeleteTableStatement(SQLTable tbl) {
      return "DELETE FROM " + tbl.toString();
   }

   public SQLText getUpdateTableStatement(SQLTable tbl, SQLText setSQL) {
      SQLText sql = new SQLText("UPDATE ");
      sql.append(tbl.toString());
      sql.append(" ").append(setSQL);
      return sql;
   }

   public String getRangeByLimitEndOfStatementClause(long offset, long count, boolean hasOrdering) {
      return "";
   }

   public String getRangeByRowNumberColumn() {
      return "";
   }

   public String getRangeByRowNumberColumn2() {
      return "";
   }

   public ResultSet getColumns(Connection conn, String catalog, String schema, String table, String columnNamePattern) throws SQLException {
      DatabaseMetaData dmd = conn.getMetaData();
      return dmd.getColumns(catalog, schema, table, columnNamePattern);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("================ DatabaseAdapter ==================");
      sb.append("\n");
      sb.append("Adapter : " + this.getClass().getName());
      sb.append("\n");
      sb.append("Datastore : name=\"" + this.datastoreProductName + "\" version=\"" + this.datastoreProductVersion + "\" (major=" + this.datastoreMajorVersion + ", minor=" + this.datastoreMinorVersion + ", revision=" + this.datastoreRevisionVersion + ")");
      sb.append("\n");
      sb.append("Driver : name=\"" + this.driverName + "\" version=\"" + this.driverVersion + "\" (major=" + this.driverMajorVersion + ", minor=" + this.driverMinorVersion + ")");
      sb.append("\n");
      sb.append("===================================================");
      return sb.toString();
   }

   public String getDatastoreDateStatement() {
      return "SELECT CURRENT_TIMESTAMP";
   }

   public String getPatternExpressionAnyCharacter() {
      return "_";
   }

   public String getPatternExpressionZeroMoreCharacters() {
      return "%";
   }

   public String getEscapePatternExpression() {
      return "ESCAPE '\\'";
   }

   public String getEscapeCharacter() {
      return "\\";
   }

   public String getContinuationString() {
      return "\n";
   }

   public String getNumericConversionFunction() {
      return "ASCII";
   }

   public String getOperatorConcat() {
      return "||";
   }

   public boolean isStatementCancel(SQLException sqle) {
      return false;
   }

   public boolean isStatementTimeout(SQLException sqle) {
      return false;
   }

   public boolean validToIndexMapping(JavaTypeMapping mapping) {
      return true;
   }
}
