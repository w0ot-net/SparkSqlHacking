package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.datanucleus.metadata.JdbcType;
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
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.ForeignKeyInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
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

public interface DatastoreAdapter {
   String IDENTITY_COLUMNS = "IdentityColumns";
   String SEQUENCES = "Sequences";
   String BIT_IS_REALLY_BOOLEAN = "BitIsReallyBoolean";
   String RIGHT_OUTER_JOIN = "RightOuterJoin";
   String SOME_ANY_ALL_SUBQUERY_EXPRESSIONS = "SomeAllAnySubqueries";
   String BOOLEAN_COMPARISON = "BooleanExpression";
   String ESCAPE_EXPRESSION_IN_LIKE_PREDICATE = "EscapeExpressionInLikePredicate";
   String PROJECTION_IN_TABLE_REFERENCE_JOINS = "ProjectionInTableReferenceJoins";
   String ANALYSIS_METHODS = "AnalysisMethods";
   String CATALOGS_IN_TABLE_DEFINITIONS = "CatalogInTableDefinition";
   String SCHEMAS_IN_TABLE_DEFINITIONS = "SchemaInTableDefinition";
   String IDENTIFIERS_LOWERCASE = "LowerCaseIdentifiers";
   String IDENTIFIERS_MIXEDCASE = "MixedCaseIdentifiers";
   String IDENTIFIERS_UPPERCASE = "UpperCaseIdentifiers";
   String IDENTIFIERS_LOWERCASE_QUOTED = "LowerCaseQuotedIdentifiers";
   String IDENTIFIERS_MIXEDCASE_QUOTED = "MixedCaseQuotedIdentifiers";
   String IDENTIFIERS_UPPERCASE_QUOTED = "UpperCaseQuotedIdentifiers";
   String IDENTIFIERS_MIXEDCASE_SENSITIVE = "MixedCaseSensitiveIdentifiers";
   String IDENTIFIERS_MIXEDCASE_QUOTED_SENSITIVE = "MixedCaseQuotedSensitiveIdentifiers";
   String VIEWS = "Views";
   String UNION_SYNTAX = "Union_Syntax";
   String USE_UNION_ALL = "UseUnionAll";
   String EXISTS_SYNTAX = "Exists_Syntax";
   String ALTER_TABLE_DROP_CONSTRAINT_SYNTAX = "AlterTableDropConstraint_Syntax";
   String ALTER_TABLE_DROP_FOREIGN_KEY_CONSTRAINT = "AlterTableDropForeignKey_Syntax";
   String DEFERRED_CONSTRAINTS = "DeferredConstraints";
   String DISTINCT_WITH_SELECT_FOR_UPDATE = "DistinctWithSelectForUpdate";
   String GROUPING_WITH_SELECT_FOR_UPDATE = "GroupingWithSelectForUpdate";
   String HAVING_WITH_SELECT_FOR_UPDATE = "HavingWithSelectForUpdate";
   String ORDERING_WITH_SELECT_FOR_UPDATE = "OrderingWithSelectForUpdate";
   String MULTITABLES_WITH_SELECT_FOR_UPDATE = "MultipleTablesWithSelectForUpdate";
   String UPDATE_STATEMENT_ALLOW_TABLE_ALIAS_IN_SET_CLAUSE = "UpdateStmtAllowTableAliasInSet";
   String UPDATE_DELETE_STATEMENT_ALLOW_TABLE_ALIAS_IN_WHERE_CLAUSE = "UpdateDeleteStmtAllowTableAliasInWhere";
   String GROUP_BY_REQUIRES_ALL_SELECT_PRIMARIES = "GroupByIncludesAllSelectPrimaries";
   String PERSIST_OF_UNASSIGNED_CHAR = "PersistOfUnassignedChar";
   String CHAR_COLUMNS_PADDED_WITH_SPACES = "CharColumnsPaddedWithSpaces";
   String NULL_EQUALS_EMPTY_STRING = "NullEqualsEmptyString";
   String STATEMENT_BATCHING = "StatementBatching";
   String CHECK_IN_CREATE_STATEMENTS = "CheckInCreateStatements";
   String CHECK_IN_END_CREATE_STATEMENTS = "CheckInEndCreateStatements";
   String UNIQUE_IN_END_CREATE_STATEMENTS = "UniqueInEndCreateStatements";
   String FK_IN_END_CREATE_STATEMENTS = "FKInEndCreateStatements";
   String PRIMARYKEY_IN_CREATE_STATEMENTS = "PrimaryKeyInCreateStatements";
   String GET_GENERATED_KEYS_STATEMENT = "GetGeneratedKeysStatement";
   String NULLS_IN_CANDIDATE_KEYS = "NullsInCandidateKeys";
   String NULLS_KEYWORD_IN_COLUMN_OPTIONS = "ColumnOptions_NullsKeyword";
   String DEFAULT_KEYWORD_IN_COLUMN_OPTIONS = "ColumnOptions_DefaultKeyword";
   String DEFAULT_KEYWORD_WITH_NOT_NULL_IN_COLUMN_OPTIONS = "ColumnOptions_DefaultWithNotNull";
   String DEFAULT_BEFORE_NULL_IN_COLUMN_OPTIONS = "ColumnOptions_DefaultBeforeNull";
   String ANSI_JOIN_SYNTAX = "ANSI_Join_Syntax";
   String ANSI_CROSSJOIN_SYNTAX = "ANSI_CrossJoin_Syntax";
   String CROSSJOIN_ASINNER11_SYNTAX = "ANSI_CrossJoinAsInner11_Syntax";
   String AUTO_INCREMENT_KEYS_NULL_SPECIFICATION = "AutoIncrementNullSpecification";
   String AUTO_INCREMENT_COLUMN_TYPE_SPECIFICATION = "AutoIncrementColumnTypeSpecification";
   String AUTO_INCREMENT_PK_IN_CREATE_TABLE_COLUMN_DEF = "AutoIncrementPkInCreateTableColumnDef";
   String LOCK_WITH_SELECT_FOR_UPDATE = "LockWithSelectForUpdate";
   String SELECT_FOR_UPDATE_NOWAIT = "SelectForUpdateNoWait";
   String LOCK_OPTION_PLACED_AFTER_FROM = "LockOptionAfterFromClause";
   String LOCK_OPTION_PLACED_WITHIN_JOIN = "LockOptionWithinJoinClause";
   String BLOB_SET_USING_SETSTRING = "BlobSetUsingSetString";
   String CLOB_SET_USING_SETSTRING = "ClobSetUsingSetString";
   String CREATE_INDEXES_BEFORE_FOREIGN_KEYS = "CreateIndexesBeforeForeignKeys";
   String INCLUDE_ORDERBY_COLS_IN_SELECT = "IncludeOrderByColumnsInSelect";
   String DATETIME_STORES_MILLISECS = "DateTimeStoresMillisecs";
   String ACCESS_PARENTQUERY_IN_SUBQUERY_JOINED = "AccessParentQueryInSubquery";
   String SUBQUERY_IN_HAVING = "SubqueryInHaving";
   String ORDERBY_USING_SELECT_COLUMN_INDEX = "OrderByUsingSelectColumnIndex";
   String ORDERBY_NULLS_DIRECTIVES = "OrderByWithNullsDirectives";
   String ORDERBY_NULLS_USING_ISNULL = "OrderByNullsUsingIsNull";
   String ORDERBY_NULLS_USING_COLUMN_IS_NULL = "OrderByNullsUsingColumnIsNull";
   String ORDERBY_NULLS_USING_CASE_NULL = "OrderByNullsUsingCaseNull";
   String STORED_PROCEDURES = "StoredProcs";
   String FK_UPDATE_ACTION_CASCADE = "FkUpdateActionCascade";
   String FK_UPDATE_ACTION_DEFAULT = "FkUpdateActionDefault";
   String FK_UPDATE_ACTION_NULL = "FkUpdateActionNull";
   String FK_UPDATE_ACTION_RESTRICT = "FkUpdateActionRestrict";
   String FK_DELETE_ACTION_CASCADE = "FkDeleteActionCascade";
   String FK_DELETE_ACTION_DEFAULT = "FkDeleteActionDefault";
   String FK_DELETE_ACTION_NULL = "FkDeleteActionNull";
   String FK_DELETE_ACTION_RESTRICT = "FkDeleteActionRestrict";
   String TX_ISOLATION_NONE = "TxIsolationNone";
   String TX_ISOLATION_READ_COMMITTED = "TxIsolationReadCommitted";
   String TX_ISOLATION_READ_UNCOMMITTED = "TxIsolationReadUncommitted";
   String TX_ISOLATION_REPEATABLE_READ = "TxIsolationReadRepeatableRead";
   String TX_ISOLATION_SERIALIZABLE = "TxIsolationSerializable";
   String RESULTSET_TYPE_FORWARD_ONLY = "ResultSetTypeForwardOnly";
   String RESULTSET_TYPE_SCROLL_SENSITIVE = "ResultSetTypeScrollSens";
   String RESULTSET_TYPE_SCROLL_INSENSITIVE = "ResultSetTypeScrollInsens";
   String HOLD_CURSORS_OVER_COMMIT = "HoldCursorsOverCommit";
   String OPERATOR_BITWISE_AND = "BitwiseAndOperator";
   String OPERATOR_BITWISE_OR = "BitwiseOrOperator";
   String OPERATOR_BITWISE_XOR = "BitwiseXOrOperator";

   Collection getSupportedOptions();

   boolean supportsOption(String var1);

   String getNameForJDBCType(int var1);

   int getJDBCTypeForName(String var1);

   MappingManager getMappingManager(RDBMSStoreManager var1);

   String getVendorID();

   void initialiseTypes(StoreSchemaHandler var1, ManagedConnection var2);

   void setProperties(Map var1);

   void removeUnsupportedMappings(StoreSchemaHandler var1, ManagedConnection var2);

   boolean isReservedKeyword(String var1);

   void initialiseDatastore(Object var1);

   String getIdentifierQuoteString();

   String getCatalogSeparator();

   long getAdapterTime(Timestamp var1);

   String getDatastoreProductName();

   String getDatastoreProductVersion();

   String getDatastoreDriverName();

   String getDatastoreDriverVersion();

   int getDriverMajorVersion();

   int getDriverMinorVersion();

   boolean isIdentityFieldDataType(String var1);

   int getDatastoreIdentifierMaxLength(IdentifierType var1);

   Class getAutoIncrementJavaTypeForType(Class var1);

   int getMaxForeignKeys();

   int getMaxIndexes();

   boolean supportsQueryFetchSize(int var1);

   String toString();

   boolean supportsTransactionIsolation(int var1);

   String getRangeByLimitEndOfStatementClause(long var1, long var3, boolean var5);

   String getRangeByRowNumberColumn();

   String getRangeByRowNumberColumn2();

   ResultSet getColumns(Connection var1, String var2, String var3, String var4, String var5) throws SQLException;

   String getInsertStatementForNoColumns(Table var1);

   int getUnlimitedLengthPrecisionValue(SQLTypeInfo var1);

   String getAutoIncrementStmt(Table var1, String var2);

   String getAutoIncrementKeyword();

   String getCreateDatabaseStatement(String var1, String var2);

   String getDropDatabaseStatement(String var1, String var2);

   String getDropTableStatement(Table var1);

   String getDeleteTableStatement(SQLTable var1);

   SQLText getUpdateTableStatement(SQLTable var1, SQLText var2);

   String getAddCandidateKeyStatement(CandidateKey var1, IdentifierFactory var2);

   boolean isValidPrimaryKeyType(JdbcType var1);

   String getAddColumnStatement(Table var1, Column var2);

   String getCreateIndexStatement(Index var1, IdentifierFactory var2);

   ResultSet getExistingIndexes(Connection var1, String var2, String var3, String var4) throws SQLException;

   String getCreateTableStatement(TableImpl var1, Column[] var2, Properties var3, IdentifierFactory var4);

   String getAddPrimaryKeyStatement(PrimaryKey var1, IdentifierFactory var2);

   String getAddForeignKeyStatement(ForeignKey var1, IdentifierFactory var2);

   String getDropViewStatement(ViewImpl var1);

   String getSelectForUpdateText();

   String getSurrogateForEmptyStrings();

   int getTransactionIsolationForSchemaCreation();

   int getRequiredTransactionIsolationLevel();

   String getCatalogName(Connection var1) throws SQLException;

   String getSchemaName(Connection var1) throws SQLException;

   String getSelectWithLockOption();

   String getSelectNewUUIDStmt();

   String getSequenceNextStmt(String var1);

   String getSequenceCreateStmt(String var1, Integer var2, Integer var3, Integer var4, Integer var5, Integer var6);

   boolean sequenceExists(Connection var1, String var2, String var3, String var4);

   Iterator iteratorReservedWords();

   String getDatastoreDateStatement();

   String getCheckConstraintForValues(DatastoreIdentifier var1, Object[] var2, boolean var3);

   SQLTypeInfo newSQLTypeInfo(ResultSet var1);

   RDBMSColumnInfo newRDBMSColumnInfo(ResultSet var1);

   ForeignKeyInfo newFKInfo(ResultSet var1);

   String getOrderString(StoreManager var1, String var2, SQLExpression var3);

   boolean validToSelectMappingInStatement(SQLStatement var1, JavaTypeMapping var2);

   boolean isStatementCancel(SQLException var1);

   boolean isStatementTimeout(SQLException var1);

   String getNumericConversionFunction();

   String getEscapePatternExpression();

   String getEscapeCharacter();

   String getPatternExpressionAnyCharacter();

   String getPatternExpressionZeroMoreCharacters();

   boolean validToIndexMapping(JavaTypeMapping var1);
}
