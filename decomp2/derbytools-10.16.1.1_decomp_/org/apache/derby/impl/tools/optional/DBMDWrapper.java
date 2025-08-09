package org.apache.derby.impl.tools.optional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.dictionary.OptionalTool;

public class DBMDWrapper implements OptionalTool {
   private static final int DEFAULT_PRECISION = 128;

   public void loadTool(String... var1) throws SQLException {
      this.register(true);
   }

   public void unloadTool(String... var1) throws SQLException {
      this.register(false);
   }

   private void register(boolean var1) throws SQLException {
      Connection var2 = getDefaultConnection();

      for(Method var6 : this.getClass().getDeclaredMethods()) {
         int var7 = var6.getModifiers();
         if (this.isSet(var7, 1) && this.isSet(var7, 8)) {
            if (var1) {
               this.registerFunction(var2, var6);
            } else {
               this.unregisterFunction(var2, var6);
            }
         }
      }

   }

   public static boolean allProceduresAreCallable() throws SQLException {
      return getDBMD().allProceduresAreCallable();
   }

   public static boolean allTablesAreSelectable() throws SQLException {
      return getDBMD().allTablesAreSelectable();
   }

   public static boolean autoCommitFailureClosesAllResultSets() throws SQLException {
      return getDBMD().autoCommitFailureClosesAllResultSets();
   }

   public static boolean dataDefinitionCausesTransactionCommit() throws SQLException {
      return getDBMD().dataDefinitionCausesTransactionCommit();
   }

   public static boolean dataDefinitionIgnoredInTransactions() throws SQLException {
      return getDBMD().dataDefinitionIgnoredInTransactions();
   }

   public static boolean deletesAreDetected(int var0) throws SQLException {
      return getDBMD().deletesAreDetected(var0);
   }

   public static boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
      return getDBMD().doesMaxRowSizeIncludeBlobs();
   }

   public static ResultSet getAttributes(String var0, String var1, String var2, String var3) throws SQLException {
      return getDBMD().getAttributes(var0, var1, var2, var3);
   }

   public static ResultSet getBestRowIdentifier(String var0, String var1, String var2, int var3, boolean var4) throws SQLException {
      return getDBMD().getBestRowIdentifier(var0, var1, var2, var3, var4);
   }

   public static ResultSet getCatalogs() throws SQLException {
      return getDBMD().getCatalogs();
   }

   public static String getCatalogSeparator() throws SQLException {
      return getDBMD().getCatalogSeparator();
   }

   public static String getCatalogTerm() throws SQLException {
      return getDBMD().getCatalogTerm();
   }

   public static ResultSet getClientInfoProperties() throws SQLException {
      return getDBMD().getClientInfoProperties();
   }

   public static ResultSet getColumnPrivileges(String var0, String var1, String var2, String var3) throws SQLException {
      return getDBMD().getColumnPrivileges(var0, var1, var2, var3);
   }

   public static ResultSet getColumns(String var0, String var1, String var2, String var3) throws SQLException {
      return getDBMD().getColumns(var0, var1, var2, var3);
   }

   public static ResultSet getCrossReference(String var0, String var1, String var2, String var3, String var4, String var5) throws SQLException {
      return getDBMD().getCrossReference(var0, var1, var2, var3, var4, var5);
   }

   public static int getDatabaseMajorVersion() throws SQLException {
      return getDBMD().getDatabaseMajorVersion();
   }

   public static int getDatabaseMinorVersion() throws SQLException {
      return getDBMD().getDatabaseMinorVersion();
   }

   public static String getDatabaseProductName() throws SQLException {
      return getDBMD().getDatabaseProductName();
   }

   public static String getDatabaseProductVersion() throws SQLException {
      return getDBMD().getDatabaseProductVersion();
   }

   public static int getDefaultTransactionIsolation() throws SQLException {
      return getDBMD().getDefaultTransactionIsolation();
   }

   public static int getDriverMajorVersion() throws SQLException {
      return getDBMD().getDriverMajorVersion();
   }

   public static int getDriverMinorVersion() throws SQLException {
      return getDBMD().getDriverMinorVersion();
   }

   public static String getDriverName() throws SQLException {
      return getDBMD().getDriverName();
   }

   public static String getDriverVersion() throws SQLException {
      return getDBMD().getDriverVersion();
   }

   public static ResultSet getExportedKeys(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getExportedKeys(var0, var1, var2);
   }

   public static String getExtraNameCharacters() throws SQLException {
      return getDBMD().getExtraNameCharacters();
   }

   public static ResultSet getFunctionColumns(String var0, String var1, String var2, String var3) throws SQLException {
      return getDBMD().getFunctionColumns(var0, var1, var2, var3);
   }

   public static ResultSet getFunctions(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getFunctions(var0, var1, var2);
   }

   public static String getIdentifierQuoteString() throws SQLException {
      return getDBMD().getIdentifierQuoteString();
   }

   public static ResultSet getImportedKeys(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getImportedKeys(var0, var1, var2);
   }

   public static ResultSet getIndexInfo(String var0, String var1, String var2, boolean var3, boolean var4) throws SQLException {
      return getDBMD().getIndexInfo(var0, var1, var2, var3, var4);
   }

   public static int getJDBCMajorVersion() throws SQLException {
      return getDBMD().getJDBCMajorVersion();
   }

   public static int getJDBCMinorVersion() throws SQLException {
      return getDBMD().getJDBCMinorVersion();
   }

   public static int getMaxBinaryLiteralLength() throws SQLException {
      return getDBMD().getMaxBinaryLiteralLength();
   }

   public static int getMaxCatalogNameLength() throws SQLException {
      return getDBMD().getMaxCatalogNameLength();
   }

   public static int getMaxCharLiteralLength() throws SQLException {
      return getDBMD().getMaxCharLiteralLength();
   }

   public static int getMaxColumnNameLength() throws SQLException {
      return getDBMD().getMaxColumnNameLength();
   }

   public static int getMaxColumnsInGroupBy() throws SQLException {
      return getDBMD().getMaxColumnsInGroupBy();
   }

   public static int getMaxColumnsInIndex() throws SQLException {
      return getDBMD().getMaxColumnsInIndex();
   }

   public static int getMaxColumnsInOrderBy() throws SQLException {
      return getDBMD().getMaxColumnsInOrderBy();
   }

   public static int getMaxColumnsInSelect() throws SQLException {
      return getDBMD().getMaxColumnsInSelect();
   }

   public static int getMaxColumnsInTable() throws SQLException {
      return getDBMD().getMaxColumnsInTable();
   }

   public static int getMaxConnections() throws SQLException {
      return getDBMD().getMaxConnections();
   }

   public static int getMaxCursorNameLength() throws SQLException {
      return getDBMD().getMaxCursorNameLength();
   }

   public static int getMaxIndexLength() throws SQLException {
      return getDBMD().getMaxIndexLength();
   }

   public static int getMaxProcedureNameLength() throws SQLException {
      return getDBMD().getMaxProcedureNameLength();
   }

   public static int getMaxRowSize() throws SQLException {
      return getDBMD().getMaxRowSize();
   }

   public static int getMaxSchemaNameLength() throws SQLException {
      return getDBMD().getMaxSchemaNameLength();
   }

   public static int getMaxStatementLength() throws SQLException {
      return getDBMD().getMaxStatementLength();
   }

   public static int getMaxStatements() throws SQLException {
      return getDBMD().getMaxStatements();
   }

   public static int getMaxTableNameLength() throws SQLException {
      return getDBMD().getMaxTableNameLength();
   }

   public static int getMaxTablesInSelect() throws SQLException {
      return getDBMD().getMaxTablesInSelect();
   }

   public static int getMaxUserNameLength() throws SQLException {
      return getDBMD().getMaxUserNameLength();
   }

   public static String getNumericFunctions() throws SQLException {
      return getDBMD().getNumericFunctions();
   }

   public static ResultSet getPrimaryKeys(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getPrimaryKeys(var0, var1, var2);
   }

   public static ResultSet getProcedureColumns(String var0, String var1, String var2, String var3) throws SQLException {
      return getDBMD().getProcedureColumns(var0, var1, var2, var3);
   }

   public static ResultSet getProcedures(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getProcedures(var0, var1, var2);
   }

   public static String getProcedureTerm() throws SQLException {
      return getDBMD().getProcedureTerm();
   }

   public static int getResultSetHoldability() throws SQLException {
      return getDBMD().getResultSetHoldability();
   }

   public static ResultSet getSchemas(String var0, String var1) throws SQLException {
      return getDBMD().getSchemas(var0, var1);
   }

   public static String getSchemaTerm() throws SQLException {
      return getDBMD().getSchemaTerm();
   }

   public static String getSearchStringEscape() throws SQLException {
      return getDBMD().getSearchStringEscape();
   }

   public static String getSQLKeywords() throws SQLException {
      return getDBMD().getSQLKeywords();
   }

   public static int getSQLStateType() throws SQLException {
      return getDBMD().getSQLStateType();
   }

   public static String getStringFunctions() throws SQLException {
      return getDBMD().getStringFunctions();
   }

   public static ResultSet getSuperTables(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getSuperTables(var0, var1, var2);
   }

   public static ResultSet getSuperTypes(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getSuperTypes(var0, var1, var2);
   }

   public static String getSystemFunctions() throws SQLException {
      return getDBMD().getSystemFunctions();
   }

   public static ResultSet getTablePrivileges(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getTablePrivileges(var0, var1, var2);
   }

   public static ResultSet getTables(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getTables(var0, var1, var2, (String[])null);
   }

   public static ResultSet getTableTypes() throws SQLException {
      return getDBMD().getTableTypes();
   }

   public static String getTimeDateFunctions() throws SQLException {
      return getDBMD().getTimeDateFunctions();
   }

   public static ResultSet getTypeInfo() throws SQLException {
      return getDBMD().getTypeInfo();
   }

   public static ResultSet getUDTs(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getUDTs(var0, var1, var2, (int[])null);
   }

   public static String getURL() throws SQLException {
      return getDBMD().getURL();
   }

   public static String getUserName() throws SQLException {
      return getDBMD().getUserName();
   }

   public static ResultSet getVersionColumns(String var0, String var1, String var2) throws SQLException {
      return getDBMD().getVersionColumns(var0, var1, var2);
   }

   public static boolean insertsAreDetected(int var0) throws SQLException {
      return getDBMD().insertsAreDetected(var0);
   }

   public static boolean isCatalogAtStart() throws SQLException {
      return getDBMD().isCatalogAtStart();
   }

   public static boolean isReadOnly() throws SQLException {
      return getDBMD().isReadOnly();
   }

   public static boolean locatorsUpdateCopy() throws SQLException {
      return getDBMD().locatorsUpdateCopy();
   }

   public static boolean nullPlusNonNullIsNull() throws SQLException {
      return getDBMD().nullPlusNonNullIsNull();
   }

   public static boolean nullsAreSortedAtEnd() throws SQLException {
      return getDBMD().nullsAreSortedAtEnd();
   }

   public static boolean nullsAreSortedAtStart() throws SQLException {
      return getDBMD().nullsAreSortedAtStart();
   }

   public static boolean nullsAreSortedHigh() throws SQLException {
      return getDBMD().nullsAreSortedHigh();
   }

   public static boolean nullsAreSortedLow() throws SQLException {
      return getDBMD().nullsAreSortedLow();
   }

   public static boolean othersDeletesAreVisible(int var0) throws SQLException {
      return getDBMD().othersDeletesAreVisible(var0);
   }

   public static boolean othersInsertsAreVisible(int var0) throws SQLException {
      return getDBMD().othersInsertsAreVisible(var0);
   }

   public static boolean othersUpdatesAreVisible(int var0) throws SQLException {
      return getDBMD().othersUpdatesAreVisible(var0);
   }

   public static boolean ownDeletesAreVisible(int var0) throws SQLException {
      return getDBMD().ownDeletesAreVisible(var0);
   }

   public static boolean ownInsertsAreVisible(int var0) throws SQLException {
      return getDBMD().ownInsertsAreVisible(var0);
   }

   public static boolean ownUpdatesAreVisible(int var0) throws SQLException {
      return getDBMD().ownUpdatesAreVisible(var0);
   }

   public static boolean storesLowerCaseIdentifiers() throws SQLException {
      return getDBMD().storesLowerCaseIdentifiers();
   }

   public static boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
      return getDBMD().storesLowerCaseQuotedIdentifiers();
   }

   public static boolean storesMixedCaseIdentifiers() throws SQLException {
      return getDBMD().storesMixedCaseIdentifiers();
   }

   public static boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
      return getDBMD().storesMixedCaseQuotedIdentifiers();
   }

   public static boolean storesUpperCaseIdentifiers() throws SQLException {
      return getDBMD().storesUpperCaseIdentifiers();
   }

   public static boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
      return getDBMD().storesUpperCaseQuotedIdentifiers();
   }

   public static boolean supportsAlterTableWithAddColumn() throws SQLException {
      return getDBMD().supportsAlterTableWithAddColumn();
   }

   public static boolean supportsAlterTableWithDropColumn() throws SQLException {
      return getDBMD().supportsAlterTableWithDropColumn();
   }

   public static boolean supportsANSI92EntryLevelSQL() throws SQLException {
      return getDBMD().supportsANSI92EntryLevelSQL();
   }

   public static boolean supportsANSI92FullSQL() throws SQLException {
      return getDBMD().supportsANSI92FullSQL();
   }

   public static boolean supportsANSI92IntermediateSQL() throws SQLException {
      return getDBMD().supportsANSI92IntermediateSQL();
   }

   public static boolean supportsBatchUpdates() throws SQLException {
      return getDBMD().supportsBatchUpdates();
   }

   public static boolean supportsCatalogsInDataManipulation() throws SQLException {
      return getDBMD().supportsCatalogsInDataManipulation();
   }

   public static boolean supportsCatalogsInIndexDefinitions() throws SQLException {
      return getDBMD().supportsCatalogsInIndexDefinitions();
   }

   public static boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
      return getDBMD().supportsCatalogsInPrivilegeDefinitions();
   }

   public static boolean supportsCatalogsInProcedureCalls() throws SQLException {
      return getDBMD().supportsCatalogsInProcedureCalls();
   }

   public static boolean supportsCatalogsInTableDefinitions() throws SQLException {
      return getDBMD().supportsCatalogsInTableDefinitions();
   }

   public static boolean supportsColumnAliasing() throws SQLException {
      return getDBMD().supportsColumnAliasing();
   }

   public static boolean supportsConvert(int var0, int var1) throws SQLException {
      return getDBMD().supportsConvert(var0, var1);
   }

   public static boolean supportsCoreSQLGrammar() throws SQLException {
      return getDBMD().supportsCoreSQLGrammar();
   }

   public static boolean supportsCorrelatedSubqueries() throws SQLException {
      return getDBMD().supportsCorrelatedSubqueries();
   }

   public static boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
      return getDBMD().supportsDataDefinitionAndDataManipulationTransactions();
   }

   public static boolean supportsDataManipulationTransactionsOnly() throws SQLException {
      return getDBMD().supportsDataManipulationTransactionsOnly();
   }

   public static boolean supportsDifferentTableCorrelationNames() throws SQLException {
      return getDBMD().supportsDifferentTableCorrelationNames();
   }

   public static boolean supportsExpressionsInOrderBy() throws SQLException {
      return getDBMD().supportsExpressionsInOrderBy();
   }

   public static boolean supportsExtendedSQLGrammar() throws SQLException {
      return getDBMD().supportsExtendedSQLGrammar();
   }

   public static boolean supportsFullOuterJoins() throws SQLException {
      return getDBMD().supportsFullOuterJoins();
   }

   public static boolean supportsGetGeneratedKeys() throws SQLException {
      return getDBMD().supportsGetGeneratedKeys();
   }

   public static boolean supportsGroupBy() throws SQLException {
      return getDBMD().supportsGroupBy();
   }

   public static boolean supportsGroupByBeyondSelect() throws SQLException {
      return getDBMD().supportsGroupByBeyondSelect();
   }

   public static boolean supportsGroupByUnrelated() throws SQLException {
      return getDBMD().supportsGroupByUnrelated();
   }

   public static boolean supportsIntegrityEnhancementFacility() throws SQLException {
      return getDBMD().supportsIntegrityEnhancementFacility();
   }

   public static boolean supportsLikeEscapeClause() throws SQLException {
      return getDBMD().supportsLikeEscapeClause();
   }

   public static boolean supportsLimitedOuterJoins() throws SQLException {
      return getDBMD().supportsLimitedOuterJoins();
   }

   public static boolean supportsMinimumSQLGrammar() throws SQLException {
      return getDBMD().supportsMinimumSQLGrammar();
   }

   public static boolean supportsMixedCaseIdentifiers() throws SQLException {
      return getDBMD().supportsMixedCaseIdentifiers();
   }

   public static boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
      return getDBMD().supportsMixedCaseQuotedIdentifiers();
   }

   public static boolean supportsMultipleOpenResults() throws SQLException {
      return getDBMD().supportsMultipleOpenResults();
   }

   public static boolean supportsMultipleResultSets() throws SQLException {
      return getDBMD().supportsMultipleResultSets();
   }

   public static boolean supportsMultipleTransactions() throws SQLException {
      return getDBMD().supportsMultipleTransactions();
   }

   public static boolean supportsNamedParameters() throws SQLException {
      return getDBMD().supportsNamedParameters();
   }

   public static boolean supportsNonNullableColumns() throws SQLException {
      return getDBMD().supportsNonNullableColumns();
   }

   public static boolean supportsOpenCursorsAcrossCommit() throws SQLException {
      return getDBMD().supportsOpenCursorsAcrossCommit();
   }

   public static boolean supportsOpenCursorsAcrossRollback() throws SQLException {
      return getDBMD().supportsOpenCursorsAcrossRollback();
   }

   public static boolean supportsOpenStatementsAcrossCommit() throws SQLException {
      return getDBMD().supportsOpenStatementsAcrossCommit();
   }

   public static boolean supportsOpenStatementsAcrossRollback() throws SQLException {
      return getDBMD().supportsOpenStatementsAcrossRollback();
   }

   public static boolean supportsOrderByUnrelated() throws SQLException {
      return getDBMD().supportsOrderByUnrelated();
   }

   public static boolean supportsOuterJoins() throws SQLException {
      return getDBMD().supportsOuterJoins();
   }

   public static boolean supportsPositionedDelete() throws SQLException {
      return getDBMD().supportsPositionedDelete();
   }

   public static boolean supportsPositionedUpdate() throws SQLException {
      return getDBMD().supportsPositionedUpdate();
   }

   public static boolean supportsResultSetConcurrency(int var0, int var1) throws SQLException {
      return getDBMD().supportsResultSetConcurrency(var0, var1);
   }

   public static boolean supportsResultSetHoldability(int var0) throws SQLException {
      return getDBMD().supportsResultSetHoldability(var0);
   }

   public static boolean supportsResultSetType(int var0) throws SQLException {
      return getDBMD().supportsResultSetType(var0);
   }

   public static boolean supportsSavepoints() throws SQLException {
      return getDBMD().supportsSavepoints();
   }

   public static boolean supportsSchemasInDataManipulation() throws SQLException {
      return getDBMD().supportsSchemasInDataManipulation();
   }

   public static boolean supportsSchemasInIndexDefinitions() throws SQLException {
      return getDBMD().supportsSchemasInIndexDefinitions();
   }

   public static boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
      return getDBMD().supportsSchemasInPrivilegeDefinitions();
   }

   public static boolean supportsSchemasInProcedureCalls() throws SQLException {
      return getDBMD().supportsSchemasInProcedureCalls();
   }

   public static boolean supportsSchemasInTableDefinitions() throws SQLException {
      return getDBMD().supportsSchemasInTableDefinitions();
   }

   public static boolean supportsSelectForUpdate() throws SQLException {
      return getDBMD().supportsSelectForUpdate();
   }

   public static boolean supportsStatementPooling() throws SQLException {
      return getDBMD().supportsStatementPooling();
   }

   public static boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
      return getDBMD().supportsStoredFunctionsUsingCallSyntax();
   }

   public static boolean supportsStoredProcedures() throws SQLException {
      return getDBMD().supportsStoredProcedures();
   }

   public static boolean supportsSubqueriesInComparisons() throws SQLException {
      return getDBMD().supportsSubqueriesInComparisons();
   }

   public static boolean supportsSubqueriesInExists() throws SQLException {
      return getDBMD().supportsSubqueriesInExists();
   }

   public static boolean supportsSubqueriesInIns() throws SQLException {
      return getDBMD().supportsSubqueriesInIns();
   }

   public static boolean supportsSubqueriesInQuantifieds() throws SQLException {
      return getDBMD().supportsSubqueriesInQuantifieds();
   }

   public static boolean supportsTableCorrelationNames() throws SQLException {
      return getDBMD().supportsTableCorrelationNames();
   }

   public static boolean supportsTransactionIsolationLevel(int var0) throws SQLException {
      return getDBMD().supportsTransactionIsolationLevel(var0);
   }

   public static boolean supportsTransactions() throws SQLException {
      return getDBMD().supportsTransactions();
   }

   public static boolean supportsUnion() throws SQLException {
      return getDBMD().supportsUnion();
   }

   public static boolean supportsUnionAll() throws SQLException {
      return getDBMD().supportsUnionAll();
   }

   public static boolean updatesAreDetected(int var0) throws SQLException {
      return getDBMD().updatesAreDetected(var0);
   }

   public static boolean usesLocalFilePerTable() throws SQLException {
      return getDBMD().usesLocalFilePerTable();
   }

   public static boolean usesLocalFiles() throws SQLException {
      return getDBMD().usesLocalFiles();
   }

   private boolean isSet(int var1, int var2) {
      return (var1 & var2) != 0;
   }

   private void unregisterFunction(Connection var1, Method var2) throws SQLException {
      try {
         executeDDL(var1, "drop function " + var2.getName());
      } catch (SQLException var4) {
      }

   }

   private void registerFunction(Connection var1, Method var2) throws SQLException {
      StringBuffer var3 = new StringBuffer();
      String var4 = var2.getName();
      boolean var5 = this.isTableFunction(var2);
      var3.append("create function " + var4 + "\n(");
      this.appendArgs(var3, var2);
      var3.append("\n)\n");
      var3.append("returns ");
      this.appendReturnType(var3, var1, var2);
      var3.append("\nlanguage java\nreads sql data\nparameter style ");
      if (var5) {
         var3.append("DERBY_JDBC_RESULT_SET");
      } else {
         var3.append("java");
      }

      String var10001 = this.getClass().getName();
      var3.append("\nexternal name '" + var10001 + "." + var4 + "'");
      executeDDL(var1, var3.toString());
   }

   private boolean isTableFunction(Method var1) {
      Class var2 = var1.getReturnType();
      return var2 == ResultSet.class;
   }

   private void appendArgs(StringBuffer var1, Method var2) throws SQLException {
      Class[] var3 = var2.getParameterTypes();
      int var4 = var3.length;
      String var5 = "a_";

      for(int var6 = 0; var6 < var4; ++var6) {
         Class var7 = var3[var6];
         if (var6 > 0) {
            var1.append(",");
         }

         var1.append("\n\t");
         var1.append(var5 + var6);
         var1.append(' ');
         var1.append(this.mapJavaToSQLType(var7));
      }

   }

   private void appendReturnType(StringBuffer var1, Connection var2, Method var3) throws SQLException {
      Class var4 = var3.getReturnType();
      if (ResultSet.class == var4) {
         this.appendTableFunctionSignature(var1, var2, var3);
      } else {
         var1.append(this.mapJavaToSQLType(var4));
      }

   }

   private void appendTableFunctionSignature(StringBuffer var1, Connection var2, Method var3) throws SQLException {
      var1.append("table\n(");
      Class[] var4 = var3.getParameterTypes();
      int var5 = var4.length;
      Object[] var6 = new Object[var5];

      for(int var7 = 0; var7 < var5; ++var7) {
         var6[var7] = this.getDummyValue(var4[var7]);
      }

      ResultSet var14;
      try {
         var14 = (ResultSet)var3.invoke((Object)null, var6);
      } catch (IllegalAccessException var12) {
         throw wrap(var12);
      } catch (InvocationTargetException var13) {
         throw wrap(var13);
      }

      ResultSetMetaData var8 = var14.getMetaData();
      int var9 = var8.getColumnCount();

      for(int var10 = 0; var10 < var9; ++var10) {
         int var11 = var10 + 1;
         if (var10 > 0) {
            var1.append(",");
         }

         var1.append("\n\t");
         var1.append(var8.getColumnName(var11));
         var1.append("\t");
         this.stringifyJDBCType(var1, var8, var11);
      }

      var1.append("\n)");
   }

   private Object getDummyValue(Class var1) {
      if (String.class == var1) {
         return "";
      } else if (Integer.TYPE == var1) {
         return 1;
      } else if (Short.TYPE == var1) {
         return Short.valueOf((short)1);
      } else {
         return Boolean.TYPE == var1 ? Boolean.TRUE : null;
      }
   }

   private void stringifyJDBCType(StringBuffer var1, ResultSetMetaData var2, int var3) throws SQLException {
      switch (var2.getColumnType(var3)) {
         case 1:
         case 12:
            var1.append(var2.getColumnTypeName(var3));
            var1.append("( ");
            int var4 = var2.getPrecision(var3);
            if (var4 <= 0) {
               var4 = 128;
            }

            var1.append(var4);
            var1.append(" )");
            break;
         default:
            var1.append(var2.getColumnTypeName(var3));
      }

   }

   private String mapJavaToSQLType(Class var1) throws SQLException {
      if (Short.TYPE == var1) {
         return "smallint";
      } else if (Integer.TYPE == var1) {
         return "int";
      } else if (Boolean.TYPE == var1) {
         return "boolean";
      } else if (String.class == var1) {
         return "varchar( 32672 )";
      } else {
         throw new SQLException("Unsupported type: " + var1.getName());
      }
   }

   private static DatabaseMetaData getDBMD() throws SQLException {
      return getDefaultConnection().getMetaData();
   }

   private static Connection getDefaultConnection() throws SQLException {
      return DriverManager.getConnection("jdbc:default:connection");
   }

   private static void executeDDL(Connection var0, String var1) throws SQLException {
      PreparedStatement var2 = null;

      try {
         var2 = prepareStatement(var0, var1);
         var2.execute();
      } finally {
         if (var2 != null) {
            var2.close();
         }

      }

   }

   private static PreparedStatement prepareStatement(Connection var0, String var1) throws SQLException {
      PreparedStatement var2 = var0.prepareStatement(var1);
      return var2;
   }

   private static SQLException wrap(Throwable var0) {
      return new SQLException(var0.getMessage(), var0);
   }
}
