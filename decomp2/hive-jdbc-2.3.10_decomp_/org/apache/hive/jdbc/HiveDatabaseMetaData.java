package org.apache.hive.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.jar.Attributes.Name;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hive.service.cli.GetInfoType;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TGetCatalogsReq;
import org.apache.hive.service.rpc.thrift.TGetCatalogsResp;
import org.apache.hive.service.rpc.thrift.TGetColumnsReq;
import org.apache.hive.service.rpc.thrift.TGetColumnsResp;
import org.apache.hive.service.rpc.thrift.TGetCrossReferenceReq;
import org.apache.hive.service.rpc.thrift.TGetCrossReferenceResp;
import org.apache.hive.service.rpc.thrift.TGetFunctionsReq;
import org.apache.hive.service.rpc.thrift.TGetFunctionsResp;
import org.apache.hive.service.rpc.thrift.TGetInfoReq;
import org.apache.hive.service.rpc.thrift.TGetInfoResp;
import org.apache.hive.service.rpc.thrift.TGetInfoType;
import org.apache.hive.service.rpc.thrift.TGetPrimaryKeysReq;
import org.apache.hive.service.rpc.thrift.TGetPrimaryKeysResp;
import org.apache.hive.service.rpc.thrift.TGetSchemasReq;
import org.apache.hive.service.rpc.thrift.TGetSchemasResp;
import org.apache.hive.service.rpc.thrift.TGetTableTypesReq;
import org.apache.hive.service.rpc.thrift.TGetTableTypesResp;
import org.apache.hive.service.rpc.thrift.TGetTablesReq;
import org.apache.hive.service.rpc.thrift.TGetTablesResp;
import org.apache.hive.service.rpc.thrift.TGetTypeInfoReq;
import org.apache.hive.service.rpc.thrift.TGetTypeInfoResp;
import org.apache.hive.service.rpc.thrift.TSessionHandle;
import org.apache.thrift.TException;

public class HiveDatabaseMetaData implements DatabaseMetaData {
   private final HiveConnection connection;
   private final TCLIService.Iface client;
   private final TSessionHandle sessHandle;
   private static final String CATALOG_SEPARATOR = ".";
   private static final char SEARCH_STRING_ESCAPE = '\\';
   private static final int maxColumnNameLength = 128;
   private String dbVersion = null;

   public HiveDatabaseMetaData(HiveConnection connection, TCLIService.Iface client, TSessionHandle sessHandle) {
      this.connection = connection;
      this.client = client;
      this.sessHandle = sessHandle;
   }

   public boolean allProceduresAreCallable() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean allTablesAreSelectable() throws SQLException {
      return true;
   }

   public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean deletesAreDetected(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getCatalogSeparator() throws SQLException {
      return ".";
   }

   public String getCatalogTerm() throws SQLException {
      return "instance";
   }

   public ResultSet getCatalogs() throws SQLException {
      TGetCatalogsResp catalogResp;
      try {
         catalogResp = this.client.GetCatalogs(new TGetCatalogsReq(this.sessHandle));
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(catalogResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(catalogResp.getOperationHandle()).build();
   }

   public ResultSet getClientInfoProperties() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean generatedKeyAlwaysReturned() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   private String convertPattern(String pattern) {
      if (pattern == null) {
         return ".*";
      } else {
         StringBuilder result = new StringBuilder(pattern.length());
         boolean escaped = false;
         int i = 0;

         for(int len = pattern.length(); i < len; ++i) {
            char c = pattern.charAt(i);
            if (escaped) {
               if (c != '\\') {
                  escaped = false;
               }

               result.append(c);
            } else if (c == '\\') {
               escaped = true;
            } else if (c == '%') {
               result.append(".*");
            } else if (c == '_') {
               result.append('.');
            } else {
               result.append(Character.toLowerCase(c));
            }
         }

         return result.toString();
      }
   }

   public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
      TGetColumnsReq colReq = new TGetColumnsReq();
      colReq.setSessionHandle(this.sessHandle);
      colReq.setCatalogName(catalog);
      colReq.setSchemaName(schemaPattern);
      colReq.setTableName(tableNamePattern);
      colReq.setColumnName(columnNamePattern);

      TGetColumnsResp colResp;
      try {
         colResp = this.client.GetColumns(colReq);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(colResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(colResp.getOperationHandle()).build();
   }

   public Connection getConnection() throws SQLException {
      return this.connection;
   }

   public ResultSet getCrossReference(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
      TGetCrossReferenceReq getFKReq = new TGetCrossReferenceReq(this.sessHandle);
      getFKReq.setParentTableName(primaryTable);
      getFKReq.setParentSchemaName(primarySchema);
      getFKReq.setParentCatalogName(primaryCatalog);
      getFKReq.setForeignTableName(foreignTable);
      getFKReq.setForeignSchemaName(foreignSchema);
      getFKReq.setForeignCatalogName(foreignCatalog);

      TGetCrossReferenceResp getFKResp;
      try {
         getFKResp = this.client.GetCrossReference(getFKReq);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(getFKResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(getFKResp.getOperationHandle()).build();
   }

   public int getDatabaseMajorVersion() throws SQLException {
      return Utils.getVersionPart(this.getDatabaseProductVersion(), 0);
   }

   public int getDatabaseMinorVersion() throws SQLException {
      return Utils.getVersionPart(this.getDatabaseProductVersion(), 1);
   }

   public String getDatabaseProductName() throws SQLException {
      TGetInfoResp resp = this.getServerInfo(GetInfoType.CLI_DBMS_NAME.toTGetInfoType());
      return resp.getInfoValue().getStringValue();
   }

   public String getDatabaseProductVersion() throws SQLException {
      if (this.dbVersion != null) {
         return this.dbVersion;
      } else {
         TGetInfoResp resp = this.getServerInfo(GetInfoType.CLI_DBMS_VER.toTGetInfoType());
         this.dbVersion = resp.getInfoValue().getStringValue();
         return this.dbVersion;
      }
   }

   public int getDefaultTransactionIsolation() throws SQLException {
      return 0;
   }

   public int getDriverMajorVersion() {
      return HiveDriver.getMajorDriverVersion();
   }

   public int getDriverMinorVersion() {
      return HiveDriver.getMinorDriverVersion();
   }

   public String getDriverName() throws SQLException {
      return HiveDriver.fetchManifestAttribute(Name.IMPLEMENTATION_TITLE);
   }

   public String getDriverVersion() throws SQLException {
      return HiveDriver.fetchManifestAttribute(Name.IMPLEMENTATION_VERSION);
   }

   public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getExtraNameCharacters() throws SQLException {
      return "";
   }

   public ResultSet getFunctionColumns(String arg0, String arg1, String arg2, String arg3) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getFunctions(String catalogName, String schemaPattern, String functionNamePattern) throws SQLException {
      TGetFunctionsReq getFunctionsReq = new TGetFunctionsReq();
      getFunctionsReq.setSessionHandle(this.sessHandle);
      getFunctionsReq.setCatalogName(catalogName);
      getFunctionsReq.setSchemaName(schemaPattern);
      getFunctionsReq.setFunctionName(functionNamePattern);

      TGetFunctionsResp funcResp;
      try {
         funcResp = this.client.GetFunctions(getFunctionsReq);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(funcResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(funcResp.getOperationHandle()).build();
   }

   public String getIdentifierQuoteString() throws SQLException {
      return " ";
   }

   public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setEmptyResultSet(true).setSchema(Arrays.asList("PKTABLE_CAT", "PKTABLE_SCHEM", "PKTABLE_NAME", "PKCOLUMN_NAME", "FKTABLE_CAT", "FKTABLE_SCHEM", "FKTABLE_NAME", "FKCOLUMN_NAME", "KEY_SEQ", "UPDATE_RULE", "DELETE_RULE", "FK_NAME", "PK_NAME", "DEFERRABILITY"), Arrays.asList("STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "SMALLINT", "SMALLINT", "SMALLINT", "STRING", "STRING", "STRING")).build();
   }

   public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setEmptyResultSet(true).setSchema(Arrays.asList("TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "NON_UNIQUE", "INDEX_QUALIFIER", "INDEX_NAME", "TYPE", "ORDINAL_POSITION", "COLUMN_NAME", "ASC_OR_DESC", "CARDINALITY", "PAGES", "FILTER_CONDITION"), Arrays.asList("STRING", "STRING", "STRING", "BOOLEAN", "STRING", "STRING", "SHORT", "SHORT", "STRING", "STRING", "INT", "INT", "STRING")).build();
   }

   public int getJDBCMajorVersion() throws SQLException {
      return 3;
   }

   public int getJDBCMinorVersion() throws SQLException {
      return 0;
   }

   public int getMaxBinaryLiteralLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxCatalogNameLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxCharLiteralLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxColumnNameLength() throws SQLException {
      return 128;
   }

   public int getMaxColumnsInGroupBy() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxColumnsInIndex() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxColumnsInOrderBy() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxColumnsInSelect() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxColumnsInTable() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxConnections() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxCursorNameLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxIndexLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxProcedureNameLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxRowSize() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxSchemaNameLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxStatementLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxStatements() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxTableNameLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxTablesInSelect() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxUserNameLength() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getNumericFunctions() throws SQLException {
      return "";
   }

   public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
      TGetPrimaryKeysReq getPKReq = new TGetPrimaryKeysReq(this.sessHandle);
      getPKReq.setTableName(table);
      getPKReq.setSchemaName(schema);
      getPKReq.setCatalogName(catalog);

      TGetPrimaryKeysResp getPKResp;
      try {
         getPKResp = this.client.GetPrimaryKeys(getPKReq);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(getPKResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(getPKResp.getOperationHandle()).build();
   }

   public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setEmptyResultSet(true).setSchema(Arrays.asList("PROCEDURE_CAT", "PROCEDURE_SCHEM", "PROCEDURE_NAME", "COLUMN_NAME", "COLUMN_TYPE", "DATA_TYPE", "TYPE_NAME", "PRECISION", "LENGTH", "SCALE", "RADIX", "NULLABLE", "REMARKS", "COLUMN_DEF", "SQL_DATA_TYPE", "SQL_DATETIME_SUB", "CHAR_OCTET_LENGTH", "ORDINAL_POSITION", "IS_NULLABLE", "SPECIFIC_NAME"), Arrays.asList("STRING", "STRING", "STRING", "STRING", "SMALLINT", "INT", "STRING", "INT", "INT", "SMALLINT", "SMALLINT", "SMALLINT", "STRING", "STRING", "INT", "INT", "INT", "INT", "STRING", "STRING")).build();
   }

   public String getProcedureTerm() throws SQLException {
      return new String("UDF");
   }

   public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setEmptyResultSet(true).setSchema(Arrays.asList("PROCEDURE_CAT", "PROCEDURE_SCHEM", "PROCEDURE_NAME", "RESERVERD", "RESERVERD", "RESERVERD", "REMARKS", "PROCEDURE_TYPE", "SPECIFIC_NAME"), Arrays.asList("STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "STRING", "SMALLINT", "STRING")).build();
   }

   public int getResultSetHoldability() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public RowIdLifetime getRowIdLifetime() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getSQLKeywords() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getSQLStateType() throws SQLException {
      return 2;
   }

   public String getSchemaTerm() throws SQLException {
      return "database";
   }

   public ResultSet getSchemas() throws SQLException {
      return this.getSchemas((String)null, (String)null);
   }

   public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
      TGetSchemasReq schemaReq = new TGetSchemasReq();
      schemaReq.setSessionHandle(this.sessHandle);
      if (catalog != null) {
         schemaReq.setCatalogName(catalog);
      }

      if (schemaPattern == null) {
         schemaPattern = "%";
      }

      schemaReq.setSchemaName(schemaPattern);

      TGetSchemasResp schemaResp;
      try {
         schemaResp = this.client.GetSchemas(schemaReq);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(schemaResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(schemaResp.getOperationHandle()).build();
   }

   public String getSearchStringEscape() throws SQLException {
      return String.valueOf('\\');
   }

   public String getStringFunctions() throws SQLException {
      return "";
   }

   public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getSystemFunctions() throws SQLException {
      return "";
   }

   public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getTableTypes() throws SQLException {
      TGetTableTypesResp tableTypeResp;
      try {
         tableTypeResp = this.client.GetTableTypes(new TGetTableTypesReq(this.sessHandle));
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(tableTypeResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(tableTypeResp.getOperationHandle()).build();
   }

   public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {
      if (schemaPattern == null) {
         schemaPattern = "%";
      }

      TGetTablesReq getTableReq = new TGetTablesReq(this.sessHandle);
      getTableReq.setTableName(tableNamePattern);
      if (types != null) {
         getTableReq.setTableTypes(Arrays.asList(types));
      }

      getTableReq.setSchemaName(schemaPattern);

      TGetTablesResp getTableResp;
      try {
         getTableResp = this.client.GetTables(getTableReq);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(getTableResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(getTableResp.getOperationHandle()).build();
   }

   public static String toJdbcTableType(String hivetabletype) {
      if (hivetabletype == null) {
         return null;
      } else if (hivetabletype.equals(TableType.MANAGED_TABLE.toString())) {
         return "TABLE";
      } else if (hivetabletype.equals(TableType.VIRTUAL_VIEW.toString())) {
         return "VIEW";
      } else if (hivetabletype.equals(TableType.EXTERNAL_TABLE.toString())) {
         return "EXTERNAL TABLE";
      } else {
         return hivetabletype.equals(TableType.MATERIALIZED_VIEW.toString()) ? "MATERIALIZED VIEW" : hivetabletype;
      }
   }

   public String getTimeDateFunctions() throws SQLException {
      return "";
   }

   public ResultSet getTypeInfo() throws SQLException {
      TGetTypeInfoReq getTypeInfoReq = new TGetTypeInfoReq();
      getTypeInfoReq.setSessionHandle(this.sessHandle);

      TGetTypeInfoResp getTypeInfoResp;
      try {
         getTypeInfoResp = this.client.GetTypeInfo(getTypeInfoReq);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(getTypeInfoResp.getStatus());
      return (new HiveQueryResultSet.Builder(this.connection)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(getTypeInfoResp.getOperationHandle()).build();
   }

   public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
      return new HiveMetaDataResultSet(Arrays.asList("TYPE_CAT", "TYPE_SCHEM", "TYPE_NAME", "CLASS_NAME", "DATA_TYPE", "REMARKS", "BASE_TYPE"), Arrays.asList("STRING", "STRING", "STRING", "STRING", "INT", "STRING", "INT"), (List)null) {
         public boolean next() throws SQLException {
            return false;
         }

         public Object getObject(String columnLabel, Class type) throws SQLException {
            throw new SQLFeatureNotSupportedException("Method not supported");
         }

         public Object getObject(int columnIndex, Class type) throws SQLException {
            throw new SQLFeatureNotSupportedException("Method not supported");
         }
      };
   }

   public String getURL() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public String getUserName() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean insertsAreDetected(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isCatalogAtStart() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isReadOnly() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean locatorsUpdateCopy() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean nullPlusNonNullIsNull() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean nullsAreSortedAtEnd() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean nullsAreSortedAtStart() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean nullsAreSortedHigh() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean nullsAreSortedLow() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean othersDeletesAreVisible(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean othersInsertsAreVisible(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean othersUpdatesAreVisible(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean ownDeletesAreVisible(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean ownInsertsAreVisible(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean ownUpdatesAreVisible(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean storesLowerCaseIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean storesMixedCaseIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean storesUpperCaseIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsANSI92EntryLevelSQL() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsANSI92FullSQL() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsANSI92IntermediateSQL() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsAlterTableWithAddColumn() throws SQLException {
      return true;
   }

   public boolean supportsAlterTableWithDropColumn() throws SQLException {
      return false;
   }

   public boolean supportsBatchUpdates() throws SQLException {
      return false;
   }

   public boolean supportsCatalogsInDataManipulation() throws SQLException {
      return false;
   }

   public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
      return false;
   }

   public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
      return false;
   }

   public boolean supportsCatalogsInProcedureCalls() throws SQLException {
      return false;
   }

   public boolean supportsCatalogsInTableDefinitions() throws SQLException {
      return false;
   }

   public boolean supportsColumnAliasing() throws SQLException {
      return true;
   }

   public boolean supportsConvert() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsConvert(int fromType, int toType) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsCoreSQLGrammar() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsCorrelatedSubqueries() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsDifferentTableCorrelationNames() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsExpressionsInOrderBy() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsExtendedSQLGrammar() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsFullOuterJoins() throws SQLException {
      return true;
   }

   public boolean supportsGetGeneratedKeys() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsGroupBy() throws SQLException {
      return true;
   }

   public boolean supportsGroupByBeyondSelect() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsGroupByUnrelated() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsIntegrityEnhancementFacility() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsLikeEscapeClause() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsLimitedOuterJoins() throws SQLException {
      return true;
   }

   public boolean supportsMinimumSQLGrammar() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsMixedCaseIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsMultipleOpenResults() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsMultipleResultSets() throws SQLException {
      return false;
   }

   public boolean supportsMultipleTransactions() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsNamedParameters() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsNonNullableColumns() throws SQLException {
      return false;
   }

   public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsOrderByUnrelated() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsOuterJoins() throws SQLException {
      return true;
   }

   public boolean supportsPositionedDelete() throws SQLException {
      return false;
   }

   public boolean supportsPositionedUpdate() throws SQLException {
      return false;
   }

   public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsResultSetHoldability(int holdability) throws SQLException {
      return false;
   }

   public boolean supportsResultSetType(int type) throws SQLException {
      return true;
   }

   public boolean supportsSavepoints() throws SQLException {
      return false;
   }

   public boolean supportsSchemasInDataManipulation() throws SQLException {
      return true;
   }

   public boolean supportsSchemasInIndexDefinitions() throws SQLException {
      return false;
   }

   public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
      return false;
   }

   public boolean supportsSchemasInProcedureCalls() throws SQLException {
      return false;
   }

   public boolean supportsSchemasInTableDefinitions() throws SQLException {
      return true;
   }

   public boolean supportsSelectForUpdate() throws SQLException {
      return false;
   }

   public boolean supportsStatementPooling() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsStoredProcedures() throws SQLException {
      return false;
   }

   public boolean supportsSubqueriesInComparisons() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsSubqueriesInExists() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsSubqueriesInIns() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsSubqueriesInQuantifieds() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsTableCorrelationNames() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean supportsTransactions() throws SQLException {
      return false;
   }

   public boolean supportsUnion() throws SQLException {
      return false;
   }

   public boolean supportsUnionAll() throws SQLException {
      return true;
   }

   public boolean updatesAreDetected(int type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean usesLocalFilePerTable() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean usesLocalFiles() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public static void main(String[] args) throws SQLException {
      HiveDatabaseMetaData meta = new HiveDatabaseMetaData((HiveConnection)null, (TCLIService.Iface)null, (TSessionHandle)null);
      System.out.println("DriverName: " + meta.getDriverName());
      System.out.println("DriverVersion: " + meta.getDriverVersion());
   }

   private TGetInfoResp getServerInfo(TGetInfoType type) throws SQLException {
      TGetInfoReq req = new TGetInfoReq(this.sessHandle, type);

      TGetInfoResp resp;
      try {
         resp = this.client.GetInfo(req);
      } catch (TException e) {
         throw new SQLException(e.getMessage(), "08S01", e);
      }

      Utils.verifySuccess(resp.getStatus());
      return resp;
   }

   private class GetColumnsComparator implements Comparator {
      public int compare(JdbcColumn o1, JdbcColumn o2) {
         int compareName = o1.getTableName().compareTo(o2.getTableName());
         if (compareName == 0) {
            if (o1.getOrdinalPos() > o2.getOrdinalPos()) {
               return 1;
            } else {
               return o1.getOrdinalPos() < o2.getOrdinalPos() ? -1 : 0;
            }
         } else {
            return compareName;
         }
      }
   }

   private class GetTablesComparator implements Comparator {
      public int compare(JdbcTable o1, JdbcTable o2) {
         int compareType = o1.getType().compareTo(o2.getType());
         return compareType == 0 ? o1.getTableName().compareTo(o2.getTableName()) : compareType;
      }
   }
}
