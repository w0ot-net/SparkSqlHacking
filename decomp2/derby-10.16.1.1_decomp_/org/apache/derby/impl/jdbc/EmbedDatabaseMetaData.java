package org.apache.derby.impl.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.impl.sql.execute.GenericConstantActionFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.info.JVMInfo;
import org.apache.derby.shared.common.info.ProductVersionHolder;

public class EmbedDatabaseMetaData extends ConnectionChild implements DatabaseMetaData {
   private static final int ILLEGAL_UDT_TYPE = 0;
   private final String url;
   private GenericConstantActionFactory constantActionFactory;
   private static Properties queryDescriptions;
   private static Properties queryDescriptions_net;

   public EmbedDatabaseMetaData(EmbedConnection var1, String var2) throws SQLException {
      super(var1);
      this.url = var2;
   }

   private Properties getQueryDescriptions(boolean var1) {
      Properties var2 = var1 ? queryDescriptions_net : queryDescriptions;
      if (var2 != null) {
         return var2;
      } else {
         this.loadQueryDescriptions();
         return var1 ? queryDescriptions_net : queryDescriptions;
      }
   }

   private void PBloadQueryDescriptions() {
      String[] var1 = new String[]{"metadata.properties", "/org/apache/derby/impl/sql/catalog/metadata_net.properties"};
      Properties[] var2 = new Properties[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         try {
            var2[var3] = new Properties();
            InputStream var4 = this.getClass().getResourceAsStream(var1[var3]);
            var2[var3].load(var4);
            var4.close();
         } catch (IOException var5) {
         }
      }

      queryDescriptions = var2[0];
      queryDescriptions_net = var2[1];
   }

   public boolean allProceduresAreCallable() {
      return true;
   }

   public boolean allTablesAreSelectable() {
      return true;
   }

   public final String getURL() {
      if (this.url == null) {
         return this.url;
      } else {
         int var1 = this.url.indexOf(59);
         return var1 == -1 ? this.url : this.url.substring(0, var1);
      }
   }

   public String getUserName() {
      return this.getEmbedConnection().getTR().getUserName();
   }

   public boolean isReadOnly() {
      return this.getLanguageConnectionContext().getDatabase().isReadOnly();
   }

   public boolean nullsAreSortedHigh() {
      return true;
   }

   public boolean nullsAreSortedLow() {
      return false;
   }

   public boolean nullsAreSortedAtStart() {
      return false;
   }

   public boolean nullsAreSortedAtEnd() {
      return false;
   }

   public String getDatabaseProductName() {
      return EmbedConnection.getMonitor().getEngineVersion().getProductName();
   }

   public String getDatabaseProductVersion() {
      ProductVersionHolder var1 = EmbedConnection.getMonitor().getEngineVersion();
      return var1.getVersionBuildString(true);
   }

   public String getDriverName() {
      return "Apache Derby Embedded JDBC Driver";
   }

   public String getDriverVersion() {
      return this.getDatabaseProductVersion();
   }

   public int getDriverMajorVersion() {
      return this.getEmbedConnection().getLocalDriver().getMajorVersion();
   }

   public int getDriverMinorVersion() {
      return this.getEmbedConnection().getLocalDriver().getMinorVersion();
   }

   public boolean usesLocalFiles() {
      return !this.url.contains("memory");
   }

   public boolean usesLocalFilePerTable() {
      return !this.url.contains("memory");
   }

   public boolean supportsMixedCaseIdentifiers() {
      return false;
   }

   public boolean storesUpperCaseIdentifiers() {
      return true;
   }

   public boolean storesLowerCaseIdentifiers() {
      return false;
   }

   public boolean storesMixedCaseIdentifiers() {
      return false;
   }

   public boolean supportsMixedCaseQuotedIdentifiers() {
      return true;
   }

   public boolean storesUpperCaseQuotedIdentifiers() {
      return false;
   }

   public boolean storesLowerCaseQuotedIdentifiers() {
      return false;
   }

   public boolean storesMixedCaseQuotedIdentifiers() {
      return true;
   }

   public String getIdentifierQuoteString() {
      return "\"";
   }

   public String getSQLKeywords() {
      return "ALIAS,BIGINT,BOOLEAN,CALL,CLASS,COPY,DB2J_DEBUG,EXECUTE,EXPLAIN,FILE,FILTER,GETCURRENTCONNECTION,INDEX,INSTANCEOF,METHOD,NEW,OFF,PROPERTIES,RECOMPILE,RENAME,RUNTIMESTATISTICS,STATEMENT,STATISTICS,TIMING,WAIT";
   }

   public String getNumericFunctions() {
      return "ABS,ACOS,ASIN,ATAN,ATAN2,CEILING,COS,COT,DEGREES,EXP,FLOOR,LOG,LOG10,MOD,PI,RADIANS,RAND,SIGN,SIN,SQRT,TAN";
   }

   public String getStringFunctions() {
      return "CONCAT,LENGTH,LCASE,LOCATE,LTRIM,RTRIM,SUBSTRING,UCASE";
   }

   public String getSystemFunctions() {
      return "USER";
   }

   public String getTimeDateFunctions() {
      return "CURDATE,CURTIME,HOUR,MINUTE,MONTH,SECOND,TIMESTAMPADD,TIMESTAMPDIFF,YEAR";
   }

   public String getSearchStringEscape() {
      return "";
   }

   public String getExtraNameCharacters() {
      return "";
   }

   public boolean supportsAlterTableWithAddColumn() {
      return true;
   }

   public boolean supportsAlterTableWithDropColumn() {
      return true;
   }

   public boolean supportsColumnAliasing() {
      return true;
   }

   public boolean nullPlusNonNullIsNull() {
      return true;
   }

   public boolean supportsConvert() {
      return false;
   }

   public boolean supportsConvert(int var1, int var2) {
      return false;
   }

   public boolean supportsTableCorrelationNames() {
      return true;
   }

   public boolean supportsDifferentTableCorrelationNames() {
      return true;
   }

   public boolean supportsExpressionsInOrderBy() {
      return true;
   }

   public boolean supportsOrderByUnrelated() {
      return false;
   }

   public boolean supportsGroupBy() {
      return true;
   }

   public boolean supportsGroupByUnrelated() {
      return true;
   }

   public boolean supportsGroupByBeyondSelect() {
      return true;
   }

   public boolean supportsLikeEscapeClause() {
      return true;
   }

   public boolean supportsMultipleResultSets() {
      return true;
   }

   public boolean supportsMultipleTransactions() {
      return true;
   }

   public boolean supportsNonNullableColumns() {
      return true;
   }

   public boolean supportsMinimumSQLGrammar() {
      return true;
   }

   public boolean supportsCoreSQLGrammar() {
      return false;
   }

   public boolean supportsExtendedSQLGrammar() {
      return false;
   }

   public boolean supportsANSI92EntryLevelSQL() {
      return true;
   }

   public boolean supportsANSI92IntermediateSQL() {
      return false;
   }

   public boolean supportsANSI92FullSQL() {
      return false;
   }

   public boolean supportsIntegrityEnhancementFacility() {
      return false;
   }

   public boolean supportsOuterJoins() {
      return true;
   }

   public boolean supportsFullOuterJoins() {
      return false;
   }

   public boolean supportsLimitedOuterJoins() {
      return true;
   }

   public String getSchemaTerm() {
      return "SCHEMA";
   }

   public String getProcedureTerm() {
      return "PROCEDURE";
   }

   public String getCatalogTerm() {
      return "CATALOG";
   }

   public boolean isCatalogAtStart() {
      return false;
   }

   public String getCatalogSeparator() {
      return "";
   }

   public boolean supportsSchemasInDataManipulation() {
      return true;
   }

   public boolean supportsSchemasInProcedureCalls() {
      return true;
   }

   public boolean supportsSchemasInTableDefinitions() {
      return true;
   }

   public boolean supportsSchemasInIndexDefinitions() {
      return true;
   }

   public boolean supportsSchemasInPrivilegeDefinitions() {
      return true;
   }

   public boolean supportsCatalogsInDataManipulation() {
      return false;
   }

   public boolean supportsCatalogsInProcedureCalls() {
      return false;
   }

   public boolean supportsCatalogsInTableDefinitions() {
      return false;
   }

   public boolean supportsCatalogsInIndexDefinitions() {
      return false;
   }

   public boolean supportsCatalogsInPrivilegeDefinitions() {
      return false;
   }

   public boolean supportsPositionedDelete() {
      return true;
   }

   public boolean supportsPositionedUpdate() {
      return true;
   }

   public boolean supportsRefCursors() {
      return false;
   }

   public boolean supportsSelectForUpdate() {
      return true;
   }

   public boolean supportsStoredProcedures() {
      return true;
   }

   public boolean supportsSubqueriesInComparisons() {
      return true;
   }

   public boolean supportsSubqueriesInExists() {
      return true;
   }

   public boolean supportsSubqueriesInIns() {
      return true;
   }

   public boolean supportsSubqueriesInQuantifieds() {
      return true;
   }

   public boolean supportsCorrelatedSubqueries() {
      return true;
   }

   public boolean supportsUnion() {
      return true;
   }

   public boolean supportsUnionAll() {
      return true;
   }

   public boolean supportsOpenCursorsAcrossCommit() {
      return false;
   }

   public boolean supportsOpenCursorsAcrossRollback() {
      return false;
   }

   public boolean supportsOpenStatementsAcrossCommit() {
      return true;
   }

   public boolean supportsOpenStatementsAcrossRollback() {
      return false;
   }

   public int getMaxBinaryLiteralLength() {
      return 0;
   }

   public int getMaxCharLiteralLength() {
      return 0;
   }

   public int getMaxColumnNameLength() {
      return 128;
   }

   public int getMaxColumnsInGroupBy() {
      return 0;
   }

   public int getMaxColumnsInIndex() {
      return 0;
   }

   public int getMaxColumnsInOrderBy() {
      return 0;
   }

   public int getMaxColumnsInSelect() {
      return 0;
   }

   public int getMaxColumnsInTable() {
      return 0;
   }

   public int getMaxConnections() {
      return 0;
   }

   public int getMaxCursorNameLength() {
      return 128;
   }

   public int getMaxIndexLength() {
      return 0;
   }

   public long getMaxLogicalLobSize() {
      return 0L;
   }

   public int getMaxSchemaNameLength() {
      return 128;
   }

   public int getMaxProcedureNameLength() {
      return 128;
   }

   public int getMaxCatalogNameLength() {
      return 0;
   }

   public int getMaxRowSize() {
      return 0;
   }

   public boolean doesMaxRowSizeIncludeBlobs() {
      return true;
   }

   public int getMaxStatementLength() {
      return 0;
   }

   public int getMaxStatements() {
      return 0;
   }

   public int getMaxTableNameLength() {
      return 128;
   }

   public int getMaxTablesInSelect() {
      return 0;
   }

   public int getMaxUserNameLength() {
      return 128;
   }

   public int getDefaultTransactionIsolation() {
      return 2;
   }

   public boolean supportsTransactions() {
      return true;
   }

   public boolean supportsTransactionIsolationLevel(int var1) {
      return var1 == 8 || var1 == 4 || var1 == 2 || var1 == 1;
   }

   public boolean supportsDataDefinitionAndDataManipulationTransactions() {
      return true;
   }

   public boolean supportsDataManipulationTransactionsOnly() {
      return false;
   }

   public boolean dataDefinitionCausesTransactionCommit() {
      return false;
   }

   public boolean dataDefinitionIgnoredInTransactions() {
      return false;
   }

   public ResultSet getProcedures(String var1, String var2, String var3) throws SQLException {
      return this.doGetProcs(var1, var2, var3, "getProcedures40");
   }

   public ResultSet getProceduresForODBC(String var1, String var2, String var3) throws SQLException {
      return this.doGetProcs(var1, var2, var3, "odbc_getProcedures");
   }

   public ResultSet getFunctions(String var1, String var2, String var3) throws SQLException {
      return this.doGetProcs(var1, var2, var3, "getFunctions");
   }

   private ResultSet doGetProcs(String var1, String var2, String var3, String var4) throws SQLException {
      PreparedStatement var5 = this.getPreparedQuery(var4);
      var5.setString(1, swapNull(var1));
      var5.setString(2, swapNull(var2));
      var5.setString(3, swapNull(var3));
      return var5.executeQuery();
   }

   public ResultSet getProcedureColumns(String var1, String var2, String var3, String var4) throws SQLException {
      return this.doGetProcCols(var1, var2, var3, var4, "getProcedureColumns40");
   }

   public ResultSet getProcedureColumnsForODBC(String var1, String var2, String var3, String var4) throws SQLException {
      return this.doGetProcCols(var1, var2, var3, var4, "odbc_getProcedureColumns");
   }

   public ResultSet getFunctionColumns(String var1, String var2, String var3, String var4) throws SQLException {
      return this.doGetProcCols(var1, var2, var3, var4, "getFunctionColumns");
   }

   private ResultSet doGetProcCols(String var1, String var2, String var3, String var4, String var5) throws SQLException {
      PreparedStatement var6 = this.getPreparedQuery(var5);
      var6.setString(1, swapNull(var2));
      var6.setString(2, swapNull(var3));
      var6.setString(3, swapNull(var4));
      return var6.executeQuery();
   }

   public ResultSet getTables(String var1, String var2, String var3, String[] var4) throws SQLException {
      PreparedStatement var5 = this.getPreparedQuery("getTables");
      var5.setString(1, swapNull(var1));
      var5.setString(2, swapNull(var2));
      var5.setString(3, swapNull(var3));
      if (var4 == null) {
         var4 = new String[]{"TABLE", "VIEW", "SYNONYM", "SYSTEM TABLE"};
      }

      String[] var6 = new String[4];

      for(int var7 = 0; var7 < 4; ++var7) {
         var6[var7] = null;
      }

      for(int var8 = 0; var8 < var4.length; ++var8) {
         if ("TABLE".equals(var4[var8])) {
            var6[0] = "T";
         } else if ("VIEW".equals(var4[var8])) {
            var6[1] = "V";
         } else if ("SYNONYM".equals(var4[var8])) {
            var6[2] = "A";
         } else if ("SYSTEM TABLE".equals(var4[var8]) || "SYSTEM_TABLE".equals(var4[var8])) {
            var6[3] = "S";
         }
      }

      for(int var9 = 0; var9 < 4; ++var9) {
         if (var6[var9] == null) {
            var5.setNull(var9 + 4, 1);
         } else {
            var5.setString(var9 + 4, var6[var9]);
         }
      }

      return var5.executeQuery();
   }

   public ResultSet getSchemas() throws SQLException {
      return this.getSchemas((String)null, (String)null);
   }

   public ResultSet getCatalogs() throws SQLException {
      return this.getSimpleQuery("getCatalogs");
   }

   public ResultSet getTableTypes() throws SQLException {
      return this.getSimpleQuery("getTableTypes");
   }

   public ResultSet getColumns(String var1, String var2, String var3, String var4) throws SQLException {
      return this.doGetCols(var1, var2, var3, var4, "getColumns");
   }

   public ResultSet getColumnsForODBC(String var1, String var2, String var3, String var4) throws SQLException {
      return this.doGetCols(var1, var2, var3, var4, "odbc_getColumns");
   }

   private ResultSet doGetCols(String var1, String var2, String var3, String var4, String var5) throws SQLException {
      PreparedStatement var6 = this.getPreparedQuery(var5);
      var6.setString(1, swapNull(var1));
      var6.setString(2, swapNull(var2));
      var6.setString(3, swapNull(var3));
      var6.setString(4, swapNull(var4));
      return var6.executeQuery();
   }

   public ResultSet getColumnPrivileges(String var1, String var2, String var3, String var4) throws SQLException {
      if (var3 == null) {
         throw Util.generateCsSQLException("XJ103.S");
      } else {
         PreparedStatement var5 = this.getPreparedQuery("getColumnPrivileges");
         var5.setString(1, swapNull(var1));
         var5.setString(2, swapNull(var2));
         var5.setString(3, var3);
         var5.setString(4, swapNull(var4));
         return var5.executeQuery();
      }
   }

   public ResultSet getTablePrivileges(String var1, String var2, String var3) throws SQLException {
      PreparedStatement var4 = this.getPreparedQuery("getTablePrivileges");
      var4.setString(1, swapNull(var1));
      var4.setString(2, swapNull(var2));
      var4.setString(3, swapNull(var3));
      return var4.executeQuery();
   }

   public ResultSet getBestRowIdentifier(String var1, String var2, String var3, int var4, boolean var5) throws SQLException {
      return this.doGetBestRowId(var1, var2, var3, var4, var5, "");
   }

   public ResultSet getBestRowIdentifierForODBC(String var1, String var2, String var3, int var4, boolean var5) throws SQLException {
      return this.doGetBestRowId(var1, var2, var3, var4, var5, "odbc_");
   }

   private ResultSet doGetBestRowId(String var1, String var2, String var3, int var4, boolean var5, String var6) throws SQLException {
      if (var3 == null) {
         throw Util.generateCsSQLException("XJ103.S");
      } else {
         byte var7 = 0;
         if (var5) {
            var7 = 1;
         }

         if (var1 == null) {
            var1 = "%";
         }

         if (var2 == null) {
            var2 = "%";
         }

         if (var4 >= 0 && var4 <= 2) {
            PreparedStatement var8 = this.getPreparedQuery("getBestRowIdentifierPrimaryKey");
            var8.setString(1, var1);
            var8.setString(2, var2);
            var8.setString(3, var3);
            ResultSet var10 = var8.executeQuery();
            boolean var9 = var10.next();
            String var11 = "";
            if (var9) {
               var11 = var10.getString(1);
            }

            var10.close();
            var8.close();
            if (var9) {
               var8 = this.getPreparedQuery(var6 + "getBestRowIdentifierPrimaryKeyColumns");
               var8.setString(1, var11);
               var8.setString(2, var11);
               return var8.executeQuery();
            } else {
               var8 = this.getPreparedQuery("getBestRowIdentifierUniqueConstraint");
               var8.setString(1, var1);
               var8.setString(2, var2);
               var8.setString(3, var3);
               var10 = var8.executeQuery();
               var9 = var10.next();
               if (var9) {
                  var11 = var10.getString(1);
               }

               var10.close();
               var8.close();
               if (var9) {
                  var8 = this.getPreparedQuery(var6 + "getBestRowIdentifierUniqueKeyColumns");
                  var8.setString(1, var11);
                  var8.setString(2, var11);
                  var8.setInt(3, var7);
                  return var8.executeQuery();
               } else {
                  var8 = this.getPreparedQuery("getBestRowIdentifierUniqueIndex");
                  var8.setString(1, var1);
                  var8.setString(2, var2);
                  var8.setString(3, var3);
                  var10 = var8.executeQuery();
                  var9 = var10.next();
                  long var12 = 0L;
                  if (var9) {
                     var12 = var10.getLong(1);
                  }

                  var10.close();
                  var8.close();
                  if (var9) {
                     var8 = this.getPreparedQuery(var6 + "getBestRowIdentifierUniqueIndexColumns");
                     var8.setLong(1, var12);
                     var8.setInt(2, var7);
                     return var8.executeQuery();
                  } else {
                     var8 = this.getPreparedQuery(var6 + "getBestRowIdentifierAllColumns");
                     var8.setString(1, var1);
                     var8.setString(2, var2);
                     var8.setString(3, var3);
                     var8.setInt(4, var4);
                     var8.setInt(5, var7);
                     return var8.executeQuery();
                  }
               }
            }
         } else {
            throw newSQLException("42XAT", new Object[]{var4});
         }
      }
   }

   public ResultSet getVersionColumns(String var1, String var2, String var3) throws SQLException {
      return this.doGetVersionCols(var1, var2, var3, "getVersionColumns");
   }

   public ResultSet getVersionColumnsForODBC(String var1, String var2, String var3) throws SQLException {
      return this.doGetVersionCols(var1, var2, var3, "odbc_getVersionColumns");
   }

   private ResultSet doGetVersionCols(String var1, String var2, String var3, String var4) throws SQLException {
      if (var3 == null) {
         throw Util.generateCsSQLException("XJ103.S");
      } else {
         PreparedStatement var5 = this.getPreparedQuery(var4);
         var5.setString(1, swapNull(var1));
         var5.setString(2, swapNull(var2));
         var5.setString(3, var3);
         return var5.executeQuery();
      }
   }

   private boolean notInSoftUpgradeMode() throws SQLException {
      if (this.getEmbedConnection().isClosed()) {
         throw Util.noCurrentConnection();
      } else {
         LanguageConnectionContext var2 = this.getLanguageConnectionContext();

         try {
            boolean var1 = var2.getDataDictionary().checkVersion(-1, (String)null);
            InterruptStatus.restoreIntrFlagIfSeen();
            return var1;
         } catch (Throwable var4) {
            throw this.handleException(var4);
         }
      }
   }

   public ResultSet getPrimaryKeys(String var1, String var2, String var3) throws SQLException {
      PreparedStatement var4 = this.getPreparedQuery("getPrimaryKeys");
      if (var3 == null) {
         throw Util.generateCsSQLException("XJ103.S");
      } else {
         var4.setString(1, swapNull(var1));
         var4.setString(2, swapNull(var2));
         var4.setString(3, var3);
         return var4.executeQuery();
      }
   }

   public ResultSet getImportedKeys(String var1, String var2, String var3) throws SQLException {
      if (var3 == null) {
         throw Util.generateCsSQLException("XJ103.S");
      } else {
         PreparedStatement var4 = this.getPreparedQuery("getImportedKeys");
         var4.setString(1, swapNull(var1));
         var4.setString(2, swapNull(var2));
         var4.setString(3, var3);
         return var4.executeQuery();
      }
   }

   public ResultSet getExportedKeys(String var1, String var2, String var3) throws SQLException {
      if (var3 == null) {
         throw Util.generateCsSQLException("XJ103.S");
      } else {
         PreparedStatement var4 = this.getPreparedQuery("getCrossReference");
         var4.setString(1, swapNull(var1));
         var4.setString(2, swapNull(var2));
         var4.setString(3, var3);
         var4.setString(4, swapNull((String)null));
         var4.setString(5, swapNull((String)null));
         var4.setString(6, swapNull((String)null));
         return var4.executeQuery();
      }
   }

   public ResultSet getCrossReference(String var1, String var2, String var3, String var4, String var5, String var6) throws SQLException {
      if (var3 != null && var6 != null) {
         PreparedStatement var7 = this.getPreparedQuery("getCrossReference");
         var7.setString(1, swapNull(var1));
         var7.setString(2, swapNull(var2));
         var7.setString(3, var3);
         var7.setString(4, swapNull(var4));
         var7.setString(5, swapNull(var5));
         var7.setString(6, var6);
         return var7.executeQuery();
      } else {
         throw Util.generateCsSQLException("XJ103.S");
      }
   }

   public ResultSet getCrossReferenceForODBC(String var1, String var2, String var3, String var4, String var5, String var6) throws SQLException {
      PreparedStatement var7 = this.getPreparedQuery("odbc_getCrossReference");
      var7.setString(1, swapNull(var1));
      var7.setString(2, swapNull(var2));
      var7.setString(3, swapNull(var3));
      var7.setString(4, swapNull(var4));
      var7.setString(5, swapNull(var5));
      var7.setString(6, swapNull(var6));
      return var7.executeQuery();
   }

   public ResultSet getTypeInfo() throws SQLException {
      return this.getTypeInfoMinion("getTypeInfo");
   }

   public ResultSet getTypeInfoForODBC() throws SQLException {
      return this.getTypeInfoMinion("odbc_getTypeInfo");
   }

   private ResultSet getTypeInfoMinion(String var1) throws SQLException {
      try {
         boolean var2 = this.getLanguageConnectionContext().getDataDictionary().checkVersion(190, (String)null);
         PreparedStatement var3 = this.getPreparedQuery(var1);
         var3.setBoolean(1, var2);
         return var3.executeQuery();
      } catch (StandardException var4) {
         throw this.handleException(var4);
      }
   }

   public ResultSet getIndexInfo(String var1, String var2, String var3, boolean var4, boolean var5) throws SQLException {
      return this.doGetIndexInfo(var1, var2, var3, var4, var5, "getIndexInfo");
   }

   public ResultSet getIndexInfoForODBC(String var1, String var2, String var3, boolean var4, boolean var5) throws SQLException {
      return this.doGetIndexInfo(var1, var2, var3, var4, var5, "odbc_getIndexInfo");
   }

   private ResultSet doGetIndexInfo(String var1, String var2, String var3, boolean var4, boolean var5, String var6) throws SQLException {
      if (var3 == null) {
         throw Util.generateCsSQLException("XJ103.S");
      } else {
         byte var7 = 0;
         if (var5) {
            var7 = 1;
         }

         PreparedStatement var8 = this.getPreparedQuery(var6);
         var8.setString(1, swapNull(var1));
         var8.setString(2, swapNull(var2));
         var8.setString(3, var3);
         var8.setBoolean(4, var4);
         var8.setInt(5, var7);
         return var8.executeQuery();
      }
   }

   public boolean supportsResultSetType(int var1) {
      return var1 == 1003 || var1 == 1004;
   }

   public boolean supportsResultSetConcurrency(int var1, int var2) {
      return var1 != 1005;
   }

   public boolean ownUpdatesAreVisible(int var1) {
      return var1 == 1004;
   }

   public boolean ownDeletesAreVisible(int var1) {
      return var1 == 1004;
   }

   public boolean ownInsertsAreVisible(int var1) {
      return false;
   }

   public boolean othersUpdatesAreVisible(int var1) {
      return var1 == 1003;
   }

   public boolean othersDeletesAreVisible(int var1) {
      return var1 == 1003;
   }

   public boolean othersInsertsAreVisible(int var1) {
      return var1 == 1003;
   }

   public boolean updatesAreDetected(int var1) {
      return var1 == 1004;
   }

   public boolean deletesAreDetected(int var1) {
      return var1 == 1004;
   }

   public boolean insertsAreDetected(int var1) {
      return false;
   }

   public boolean supportsBatchUpdates() {
      return true;
   }

   public ResultSet getUDTs(String var1, String var2, String var3, int[] var4) throws SQLException {
      short var5 = 0;
      if (var4 == null) {
         var5 = 2000;
      } else if (var4.length > 0) {
         for(int var6 = 0; var6 < var4.length; ++var6) {
            if (var4[var6] == 2000) {
               var5 = 2000;
            }
         }
      }

      PreparedStatement var7 = this.getPreparedQuery("getUDTs");
      var7.setInt(1, 2000);
      var7.setString(2, swapNull(var1));
      var7.setString(3, swapNull(var2));
      var7.setString(4, swapNull(var3));
      var7.setInt(5, var5);
      return var7.executeQuery();
   }

   public Connection getConnection() {
      return this.getEmbedConnection().getApplicationConnection();
   }

   public boolean supportsStatementPooling() {
      return false;
   }

   public boolean supportsSavepoints() {
      return true;
   }

   public boolean supportsNamedParameters() {
      return false;
   }

   public boolean supportsMultipleOpenResults() {
      return true;
   }

   public boolean supportsGetGeneratedKeys() {
      return false;
   }

   public boolean supportsResultSetHoldability(int var1) {
      return true;
   }

   public int getResultSetHoldability() {
      return 1;
   }

   public int getDatabaseMajorVersion() {
      ProductVersionHolder var1 = EmbedConnection.getMonitor().getEngineVersion();
      return var1 == null ? -1 : var1.getMajorVersion();
   }

   public int getDatabaseMinorVersion() {
      ProductVersionHolder var1 = EmbedConnection.getMonitor().getEngineVersion();
      return var1 == null ? -1 : var1.getMinorVersion();
   }

   public int getJDBCMajorVersion() {
      return 4;
   }

   public int getJDBCMinorVersion() {
      return JVMInfo.jdbcMinorVersion();
   }

   public int getSQLStateType() {
      return 2;
   }

   public boolean locatorsUpdateCopy() throws SQLException {
      return true;
   }

   public ResultSet getSuperTypes(String var1, String var2, String var3) throws SQLException {
      return this.getSimpleQuery("getSuperTypes");
   }

   public ResultSet getSuperTables(String var1, String var2, String var3) throws SQLException {
      return this.getSimpleQuery("getSuperTables");
   }

   public ResultSet getAttributes(String var1, String var2, String var3, String var4) throws SQLException {
      return this.getSimpleQuery("getAttributes");
   }

   public ResultSet getClientInfoProperties() throws SQLException {
      return this.getSimpleQuery("getClientInfoProperties");
   }

   public ResultSet getSchemas(String var1, String var2) throws SQLException {
      PreparedStatement var3 = this.getPreparedQuery("getSchemas");
      var3.setString(1, swapNull(var1));
      var3.setString(2, swapNull(var2));
      return var3.executeQuery();
   }

   public RowIdLifetime getRowIdLifetime() throws SQLException {
      return RowIdLifetime.ROWID_UNSUPPORTED;
   }

   public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
      return true;
   }

   public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
      return true;
   }

   public final boolean isWrapperFor(Class var1) {
      return var1.isInstance(this);
   }

   public final Object unwrap(Class var1) throws SQLException {
      try {
         return var1.cast(this);
      } catch (ClassCastException var3) {
         throw newSQLException("XJ128.S", new Object[]{var1});
      }
   }

   public boolean generatedKeyAlwaysReturned() {
      return true;
   }

   public ResultSet getPseudoColumns(String var1, String var2, String var3, String var4) throws SQLException {
      return this.getSimpleQuery("getPseudoColumns");
   }

   public ResultSet getClientCachedMetaData() throws SQLException {
      return this.getSimpleQuery("METADATA", true);
   }

   private ResultSet getSimpleQuery(String var1, boolean var2) throws SQLException {
      PreparedStatement var3 = this.getPreparedQuery(var1, var2);
      return var3 == null ? null : var3.executeQuery();
   }

   protected ResultSet getSimpleQuery(String var1) throws SQLException {
      return this.getSimpleQuery(var1, false);
   }

   private PreparedStatement getPreparedQueryUsingSystemTables(String var1, boolean var2) throws SQLException {
      synchronized(this.getConnectionSynchronization()) {
         this.setupContextStack();
         Object var4 = null;

         try {
            String var5 = this.getQueryDescriptions(var2).getProperty(var1);
            if (var5 == null) {
               throw Util.notImplemented(var1);
            }

            var14 = this.prepareSPS(var1, var5, var2);
            InterruptStatus.restoreIntrFlagIfSeen(this.getLanguageConnectionContext());
         } catch (Throwable var11) {
            throw this.handleException(var11);
         } finally {
            this.restoreContextStack();
         }

         return var14;
      }
   }

   private PreparedStatement getPreparedQuery(String var1, boolean var2) throws SQLException {
      PreparedStatement var3;
      if (this.notInSoftUpgradeMode() && !this.isReadOnly()) {
         var3 = this.getPreparedQueryUsingSystemTables(var1, var2);
      } else {
         try {
            String var4 = this.getQueryFromDescription(var1, var2);
            var3 = this.getEmbedConnection().prepareMetaDataStatement(var4);
         } catch (Throwable var5) {
            throw this.handleException(var5);
         }
      }

      return var3;
   }

   protected PreparedStatement getPreparedQuery(String var1) throws SQLException {
      return this.getPreparedQuery(var1, false);
   }

   private String getQueryFromDescription(String var1, boolean var2) throws StandardException {
      DataDictionary var3 = this.getLanguageConnectionContext().getDataDictionary();
      if (!var3.checkVersion(140, (String)null)) {
         if (var1.equals("getColumnPrivileges")) {
            var1 = "getColumnPrivileges_10_1";
         }

         if (var1.equals("getTablePrivileges")) {
            var1 = "getTablePrivileges_10_1";
         }
      }

      return this.getQueryDescriptions(var2).getProperty(var1);
   }

   private PreparedStatement prepareSPS(String var1, String var2, boolean var3) throws StandardException, SQLException {
      LanguageConnectionContext var4 = this.getLanguageConnectionContext();
      var4.beginNestedTransaction(true);
      DataDictionary var5 = this.getLanguageConnectionContext().getDataDictionary();
      SPSDescriptor var6 = var5.getSPSDescriptor(var1, var3 ? var5.getSysIBMSchemaDescriptor() : var5.getSystemSchemaDescriptor());
      var4.commitNestedTransaction();
      if (var6 == null) {
         throw Util.notImplemented(var1);
      } else {
         String var7 = "EXECUTE STATEMENT " + (var3 ? "SYSIBM" : "SYS") + ".\"" + var1 + "\"";
         return this.getEmbedConnection().prepareMetaDataStatement(var7);
      }
   }

   protected static final String swapNull(String var0) {
      return var0 == null ? "%" : var0;
   }

   private LanguageConnectionContext getLanguageConnectionContext() {
      return this.getEmbedConnection().getLanguageConnection();
   }

   private void loadQueryDescriptions() {
      this.run();
   }

   public final Object run() {
      this.PBloadQueryDescriptions();
      return null;
   }
}
