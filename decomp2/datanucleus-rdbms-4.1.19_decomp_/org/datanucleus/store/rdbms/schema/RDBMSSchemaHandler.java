package org.datanucleus.store.rdbms.schema;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.AbstractStoreSchemaHandler;
import org.datanucleus.store.schema.StoreSchemaData;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class RDBMSSchemaHandler extends AbstractStoreSchemaHandler {
   protected final long COLUMN_INFO_EXPIRATION_MS = 300000L;
   protected final RDBMSStoreManager rdbmsStoreMgr;
   public static final String TYPE_TYPES = "types";
   public static final String TYPE_TABLES = "tables";
   public static final String TYPE_FKS = "foreign-keys";
   public static final String TYPE_PKS = "primary-keys";
   public static final String TYPE_INDICES = "indices";
   public static final String TYPE_COLUMNS = "columns";
   public static final String TYPE_COLUMN = "column";
   public static final String TYPE_SCHEMA = "schema";
   public static final String TYPE_CATALOG = "catalog";
   protected Map schemaDataByName = new HashMap();

   public RDBMSSchemaHandler(StoreManager storeMgr) {
      super(storeMgr);
      this.rdbmsStoreMgr = (RDBMSStoreManager)storeMgr;
   }

   protected DatastoreAdapter getDatastoreAdapter() {
      return this.rdbmsStoreMgr.getDatastoreAdapter();
   }

   public void clear() {
      this.schemaDataByName.clear();
   }

   public void createSchema(String schemaName, Properties props, Object connection) {
      try {
         RDBMSStoreManager rdbmsStoreMgr = (RDBMSStoreManager)this.storeMgr;
         String stmtText = this.getDatastoreAdapter().getCreateDatabaseStatement(rdbmsStoreMgr.getCatalogName(), schemaName);
         ManagedConnection mconn = null;
         Connection conn = (Connection)connection;
         if (connection == null) {
            mconn = this.storeMgr.getConnection(0);
            conn = (Connection)mconn.getConnection();
         }

         Statement stmt = null;

         try {
            stmt = conn.createStatement();
            NucleusLogger.DATASTORE_SCHEMA.debug(stmtText);
            boolean success = stmt.execute(stmtText);
            NucleusLogger.DATASTORE_SCHEMA.debug("createSchema returned " + success);
         } catch (SQLException sqle) {
            NucleusLogger.DATASTORE_SCHEMA.error("Exception thrown creating schema " + schemaName, sqle);
            throw new NucleusException("Exception thrown in createSchema. See the log for full details : " + sqle.getMessage());
         } finally {
            if (stmt != null) {
               try {
                  stmt.close();
               } catch (SQLException var17) {
               }
            }

            if (mconn != null) {
               mconn.release();
            }

         }

      } catch (UnsupportedOperationException var20) {
      }
   }

   public void deleteSchema(String schemaName, Properties props, Object connection) {
      try {
         RDBMSStoreManager rdbmsStoreMgr = (RDBMSStoreManager)this.storeMgr;
         String stmtText = this.getDatastoreAdapter().getDropDatabaseStatement(rdbmsStoreMgr.getCatalogName(), schemaName);
         ManagedConnection mconn = null;
         Connection conn = (Connection)connection;
         if (connection == null) {
            mconn = this.storeMgr.getConnection(0);
            conn = (Connection)mconn.getConnection();
         }

         Statement stmt = null;

         try {
            stmt = conn.createStatement();
            NucleusLogger.DATASTORE_SCHEMA.debug(stmtText);
            boolean success = stmt.execute(stmtText);
            NucleusLogger.DATASTORE_SCHEMA.debug("deleteSchema returned " + success);
         } catch (SQLException sqle) {
            NucleusLogger.DATASTORE_SCHEMA.error("Exception thrown in deleteSchema " + schemaName, sqle);
            throw new NucleusException("Exception thrown in deleteSchema. See the log for full details : " + sqle.getMessage());
         } finally {
            if (stmt != null) {
               try {
                  stmt.close();
               } catch (SQLException var17) {
               }
            }

            if (mconn != null) {
               mconn.release();
            }

         }

      } catch (UnsupportedOperationException var20) {
      }
   }

   public void createSchemaForClasses(Set classNames, Properties props, Object connection) {
      ((RDBMSStoreManager)this.storeMgr).createSchemaForClasses(classNames, props);
   }

   public void deleteSchemaForClasses(Set classNames, Properties props, Object connection) {
      ((RDBMSStoreManager)this.storeMgr).deleteSchemaForClasses(classNames, props);
   }

   public void validateSchema(Set classNames, Properties props, Object connection) {
      ((RDBMSStoreManager)this.storeMgr).validateSchemaForClasses(classNames, props);
   }

   public StoreSchemaData getSchemaData(Object connection, String name, Object[] values) {
      if (values == null) {
         if (name.equalsIgnoreCase("types")) {
            StoreSchemaData info = (StoreSchemaData)this.schemaDataByName.get("types");
            if (info == null) {
               info = this.getRDBMSTypesInfo((Connection)connection);
            }

            return info;
         } else if (name.equalsIgnoreCase("tables")) {
            StoreSchemaData info = (StoreSchemaData)this.schemaDataByName.get("tables");
            if (info == null) {
            }

            return info;
         } else {
            throw new NucleusException("Attempt to get schema information for component " + name + " but this is not supported by RDBMSSchemaHandler");
         }
      } else if (values.length == 1) {
         if (name.equalsIgnoreCase("foreign-keys") && values[0] instanceof Table) {
            return this.getRDBMSTableFKInfoForTable((Connection)connection, (Table)values[0]);
         } else if (name.equalsIgnoreCase("primary-keys") && values[0] instanceof Table) {
            return this.getRDBMSTablePKInfoForTable((Connection)connection, (Table)values[0]);
         } else if (name.equalsIgnoreCase("indices") && values[0] instanceof Table) {
            return this.getRDBMSTableIndexInfoForTable((Connection)connection, (Table)values[0]);
         } else {
            return (StoreSchemaData)(name.equalsIgnoreCase("columns") && values[0] instanceof Table ? this.getRDBMSTableInfoForTable((Connection)connection, (Table)values[0]) : this.getSchemaData(connection, name, (Object[])null));
         }
      } else if (values.length == 2) {
         if (name.equalsIgnoreCase("tables")) {
            return this.getRDBMSSchemaInfoForCatalogSchema((Connection)connection, (String)values[0], (String)values[1]);
         } else if (name.equalsIgnoreCase("column") && values[0] instanceof Table && values[1] instanceof String) {
            return this.getRDBMSColumnInfoForColumn((Connection)connection, (Table)values[0], (String)values[1]);
         } else {
            return (StoreSchemaData)(name.equalsIgnoreCase("schema") ? this.getRDBMSSchemasInfo((Connection)connection, (String)values[0], (String)values[1]) : this.getSchemaData(connection, name, (Object[])null));
         }
      } else {
         if (values.length == 3) {
            if (name.equalsIgnoreCase("columns") && values[0] instanceof String && values[1] instanceof String && values[2] instanceof String) {
               return this.getRDBMSTableInfoForTable((Connection)connection, (String)values[0], (String)values[1], (String)values[2]);
            }

            if (name.equalsIgnoreCase("indices") && values[0] instanceof String && values[1] instanceof String && values[2] instanceof String) {
               return this.getRDBMSTableIndexInfoForTable((Connection)connection, (String)values[0], (String)values[1], (String)values[2]);
            }

            if (name.equalsIgnoreCase("primary-keys") && values[0] instanceof String && values[1] instanceof String && values[2] instanceof String) {
               return this.getRDBMSTablePKInfoForTable((Connection)connection, (String)values[0], (String)values[1], (String)values[2]);
            }

            if (name.equalsIgnoreCase("foreign-keys") && values[0] instanceof String && values[1] instanceof String && values[2] instanceof String) {
               return this.getRDBMSTableFKInfoForTable((Connection)connection, (String)values[0], (String)values[1], (String)values[2]);
            }
         }

         throw new NucleusException("Attempt to get schema information for component " + name + " but this is not supported by RDBMSSchemaHandler");
      }
   }

   public String getTableType(Connection conn, Table table) throws SQLException {
      String tableType = null;
      DatastoreAdapter dba = this.getDatastoreAdapter();
      String[] c = splitTableIdentifierName(dba.getCatalogSeparator(), table.getIdentifier().getName());
      String catalogName = table.getCatalogName();
      String schemaName = table.getSchemaName();
      String tableName = table.getIdentifier().getName();
      if (c[0] != null) {
         catalogName = c[0];
      }

      if (c[1] != null) {
         schemaName = c[1];
      }

      if (c[2] != null) {
         tableName = c[2];
      }

      catalogName = this.getIdentifierForUseWithDatabaseMetaData(catalogName);
      schemaName = this.getIdentifierForUseWithDatabaseMetaData(schemaName);
      tableName = this.getIdentifierForUseWithDatabaseMetaData(tableName);

      try {
         ResultSet rs = conn.getMetaData().getTables(catalogName, schemaName, tableName, (String[])null);

         try {
            boolean insensitive = this.identifiersCaseInsensitive();

            while(rs.next()) {
               if (insensitive && tableName.equalsIgnoreCase(rs.getString(3)) || !insensitive && tableName.equals(rs.getString(3))) {
                  tableType = rs.getString(4).toUpperCase();
                  break;
               }
            }
         } finally {
            rs.close();
         }

         return tableType;
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown finding table type using DatabaseMetaData.getTables()", sqle);
      }
   }

   protected RDBMSTypesInfo getRDBMSTypesInfo(Connection conn) {
      RDBMSTypesInfo info = new RDBMSTypesInfo();

      try {
         if (conn == null) {
            return null;
         }

         DatabaseMetaData dmd = conn.getMetaData();
         ResultSet rs = dmd.getTypeInfo();

         try {
            DatastoreAdapter dba = this.getDatastoreAdapter();

            while(rs.next()) {
               SQLTypeInfo sqlType = dba.newSQLTypeInfo(rs);
               if (sqlType != null) {
                  String key = "" + sqlType.getDataType();
                  JDBCTypeInfo jdbcType = (JDBCTypeInfo)info.getChild(key);
                  if (jdbcType == null) {
                     jdbcType = new JDBCTypeInfo(sqlType.getDataType());
                     jdbcType.addChild(sqlType);
                     info.addChild(jdbcType);
                  } else {
                     jdbcType.addChild(sqlType);
                  }
               }
            }
         } finally {
            rs.close();
         }
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown retrieving type information from datastore", sqle);
      }

      this.schemaDataByName.put("types", info);
      return info;
   }

   protected RDBMSSchemaInfo getRDBMSSchemasInfo(Connection conn, String schemaName, String catalogName) {
      try {
         if (conn == null) {
            return null;
         } else {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rs = dmd.getSchemas();

            try {
               while(rs.next()) {
                  String schema = rs.getString("TABLE_SCHEM");
                  boolean schemaCorrect = false;
                  if (StringUtils.isWhitespace(schemaName) && StringUtils.isWhitespace(schema)) {
                     schemaCorrect = true;
                  } else if (schemaName != null && schemaName.equals(schema)) {
                     schemaCorrect = true;
                  } else if (schema != null && StringUtils.isWhitespace(schemaName) && schema.equals(((RDBMSStoreManager)this.storeMgr).getSchemaName())) {
                     schemaCorrect = true;
                  }

                  boolean catalogCorrect = false;
                  String catalog = catalogName;

                  try {
                     catalog = rs.getString("TABLE_CATALOG");
                     if (StringUtils.isWhitespace(catalogName) && StringUtils.isWhitespace(catalog)) {
                        catalogCorrect = true;
                     } else if (catalogName != null && catalogName.equals(catalog)) {
                        catalogCorrect = true;
                     } else if (catalog != null && StringUtils.isWhitespace(catalogName) && catalog.equals(((RDBMSStoreManager)this.storeMgr).getCatalogName())) {
                        catalogCorrect = true;
                     }
                  } catch (SQLException var15) {
                     if (catalogName == null) {
                        catalogCorrect = true;
                     }
                  }

                  if (schemaCorrect && catalogCorrect) {
                     RDBMSSchemaInfo sqle = new RDBMSSchemaInfo(catalog, schema);
                     return sqle;
                  }
               }

               return null;
            } finally {
               rs.close();
            }
         }
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown retrieving schema information from datastore", sqle);
      }
   }

   protected RDBMSTableFKInfo getRDBMSTableFKInfoForTable(Connection conn, Table table) {
      DatastoreAdapter dba = this.getDatastoreAdapter();
      String[] c = splitTableIdentifierName(dba.getCatalogSeparator(), table.getIdentifier().getName());
      String catalogName = table.getCatalogName();
      String schemaName = table.getSchemaName();
      String tableName = table.getIdentifier().getName();
      if (c[0] != null) {
         catalogName = c[0];
      }

      if (c[1] != null) {
         schemaName = c[1];
      }

      if (c[2] != null) {
         tableName = c[2];
      }

      catalogName = this.getIdentifierForUseWithDatabaseMetaData(catalogName);
      schemaName = this.getIdentifierForUseWithDatabaseMetaData(schemaName);
      tableName = this.getIdentifierForUseWithDatabaseMetaData(tableName);
      return this.getRDBMSTableFKInfoForTable(conn, catalogName, schemaName, tableName);
   }

   protected RDBMSTableFKInfo getRDBMSTableFKInfoForTable(Connection conn, String catalogName, String schemaName, String tableName) {
      RDBMSTableFKInfo info = new RDBMSTableFKInfo(catalogName, schemaName, tableName);
      DatastoreAdapter dba = this.getDatastoreAdapter();

      try {
         ResultSet rs = conn.getMetaData().getImportedKeys(catalogName, schemaName, tableName);

         try {
            while(rs.next()) {
               ForeignKeyInfo fki = dba.newFKInfo(rs);
               if (!info.getChildren().contains(fki)) {
                  info.addChild(fki);
               }
            }
         } finally {
            rs.close();
         }

         return info;
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown while querying foreign keys for table=" + tableName, sqle);
      }
   }

   protected RDBMSTablePKInfo getRDBMSTablePKInfoForTable(Connection conn, Table table) {
      DatastoreAdapter dba = this.getDatastoreAdapter();
      String[] c = splitTableIdentifierName(dba.getCatalogSeparator(), table.getIdentifier().getName());
      String catalogName = table.getCatalogName();
      String schemaName = table.getSchemaName();
      String tableName = table.getIdentifier().getName();
      if (c[0] != null) {
         catalogName = c[0];
      }

      if (c[1] != null) {
         schemaName = c[1];
      }

      if (c[2] != null) {
         tableName = c[2];
      }

      catalogName = this.getIdentifierForUseWithDatabaseMetaData(catalogName);
      schemaName = this.getIdentifierForUseWithDatabaseMetaData(schemaName);
      tableName = this.getIdentifierForUseWithDatabaseMetaData(tableName);
      return this.getRDBMSTablePKInfoForTable(conn, catalogName, schemaName, tableName);
   }

   protected RDBMSTablePKInfo getRDBMSTablePKInfoForTable(Connection conn, String catalogName, String schemaName, String tableName) {
      RDBMSTablePKInfo info = new RDBMSTablePKInfo(catalogName, schemaName, tableName);

      try {
         ResultSet rs = conn.getMetaData().getPrimaryKeys(catalogName, schemaName, tableName);

         try {
            while(rs.next()) {
               PrimaryKeyInfo pki = new PrimaryKeyInfo(rs);
               if (!info.getChildren().contains(pki)) {
                  info.addChild(pki);
               }
            }
         } finally {
            rs.close();
         }

         return info;
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown while querying primary keys for table=" + tableName, sqle);
      }
   }

   protected RDBMSTableIndexInfo getRDBMSTableIndexInfoForTable(Connection conn, Table table) {
      DatastoreAdapter dba = this.getDatastoreAdapter();
      String[] c = splitTableIdentifierName(dba.getCatalogSeparator(), table.getIdentifier().getName());
      String catalogName = table.getCatalogName();
      String schemaName = table.getSchemaName();
      String tableName = table.getIdentifier().getName();
      if (c[0] != null) {
         catalogName = c[0];
      }

      if (c[1] != null) {
         schemaName = c[1];
      }

      if (c[2] != null) {
         tableName = c[2];
      }

      catalogName = this.getIdentifierForUseWithDatabaseMetaData(catalogName);
      schemaName = this.getIdentifierForUseWithDatabaseMetaData(schemaName);
      tableName = this.getIdentifierForUseWithDatabaseMetaData(tableName);
      return this.getRDBMSTableIndexInfoForTable(conn, catalogName, schemaName, tableName);
   }

   protected RDBMSTableIndexInfo getRDBMSTableIndexInfoForTable(Connection conn, String catalogName, String schemaName, String tableName) {
      RDBMSTableIndexInfo info = new RDBMSTableIndexInfo(catalogName, schemaName, tableName);
      DatastoreAdapter dba = this.getDatastoreAdapter();

      try {
         String schemaNameTmp = schemaName;
         if (schemaName == null && this.rdbmsStoreMgr.getSchemaName() != null) {
            schemaNameTmp = this.rdbmsStoreMgr.getSchemaName();
            schemaNameTmp = this.getIdentifierForUseWithDatabaseMetaData(schemaNameTmp);
         }

         ResultSet rs = dba.getExistingIndexes(conn, catalogName, schemaNameTmp, tableName);
         if (rs == null) {
            rs = conn.getMetaData().getIndexInfo(catalogName, schemaName, tableName, false, true);
         }

         try {
            while(rs.next()) {
               IndexInfo idxInfo = new IndexInfo(rs);
               if (!info.getChildren().contains(idxInfo)) {
                  info.addChild(idxInfo);
               }
            }
         } finally {
            if (rs != null) {
               Statement st = rs.getStatement();
               rs.close();
               if (st != null) {
                  st.close();
               }
            }

         }

         return info;
      } catch (SQLException sqle) {
         NucleusLogger.DATASTORE_SCHEMA.warn("Exception thrown while querying indices for table=" + tableName, sqle);
         throw new NucleusDataStoreException("Exception thrown while querying indices for table=" + tableName, sqle);
      }
   }

   protected RDBMSSchemaInfo getRDBMSSchemaInfoForCatalogSchema(Connection conn, String catalog, String schema) {
      if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.omitDatabaseMetaDataGetColumns")) {
         return null;
      } else {
         RDBMSSchemaInfo schemaInfo = new RDBMSSchemaInfo(catalog, schema);
         ResultSet rs = null;

         try {
            String catalogName = this.getIdentifierForUseWithDatabaseMetaData(catalog);
            String schemaName = this.getIdentifierForUseWithDatabaseMetaData(schema);
            rs = this.getDatastoreAdapter().getColumns(conn, catalogName, schemaName, (String)null, (String)null);

            while(rs.next()) {
               String colCatalogName = rs.getString(1);
               String colSchemaName = rs.getString(2);
               String colTableName = rs.getString(3);
               if (StringUtils.isWhitespace(colTableName)) {
                  throw new NucleusDataStoreException("Invalid 'null' table name identifier returned by database. Check with your JDBC driver vendor (ref:DatabaseMetaData.getColumns).");
               }

               if (rs.wasNull() || colCatalogName != null && colCatalogName.length() < 1) {
                  colCatalogName = null;
               }

               if (rs.wasNull() || colSchemaName != null && colSchemaName.length() < 1) {
                  colSchemaName = null;
               }

               String tableKey = this.getTableKeyInRDBMSSchemaInfo(catalog, schema, colTableName);
               RDBMSTableInfo table = (RDBMSTableInfo)schemaInfo.getChild(tableKey);
               if (table == null) {
                  table = new RDBMSTableInfo(colCatalogName, colSchemaName, colTableName);
                  table.addProperty("table_key", tableKey);
                  schemaInfo.addChild(table);
               }

               RDBMSColumnInfo col = this.getDatastoreAdapter().newRDBMSColumnInfo(rs);
               table.addChild(col);
            }
         } catch (SQLException sqle) {
            NucleusLogger.DATASTORE_SCHEMA.warn("Exception thrown obtaining schema column information from datastore", sqle);
            throw new NucleusDataStoreException("Exception thrown obtaining schema column information from datastore", sqle);
         } finally {
            try {
               if (rs != null) {
                  Statement stmt = rs.getStatement();
                  rs.close();
                  if (stmt != null) {
                     stmt.close();
                  }
               }
            } catch (SQLException sqle) {
               throw new NucleusDataStoreException("Exception thrown closing results of DatabaseMetaData.getColumns()", sqle);
            }

         }

         return schemaInfo;
      }
   }

   protected RDBMSTableInfo getRDBMSTableInfoForTable(Connection conn, Table table) {
      String[] c = splitTableIdentifierName(this.getDatastoreAdapter().getCatalogSeparator(), table.getIdentifier().getName());
      String catalogName = table.getCatalogName();
      String schemaName = table.getSchemaName();
      String tableName = table.getIdentifier().getName();
      if (c[0] != null) {
         catalogName = c[0];
      }

      if (c[1] != null) {
         schemaName = c[1];
      }

      if (c[2] != null) {
         tableName = c[2];
      }

      catalogName = this.getIdentifierForUseWithDatabaseMetaData(catalogName);
      schemaName = this.getIdentifierForUseWithDatabaseMetaData(schemaName);
      tableName = this.getIdentifierForUseWithDatabaseMetaData(tableName);
      return this.getRDBMSTableInfoForTable(conn, catalogName, schemaName, tableName);
   }

   protected RDBMSTableInfo getRDBMSTableInfoForTable(Connection conn, String catalogName, String schemaName, String tableName) {
      RDBMSSchemaInfo info = (RDBMSSchemaInfo)this.getSchemaData(conn, "tables", (Object[])null);
      if (info == null) {
         info = new RDBMSSchemaInfo(this.rdbmsStoreMgr.getCatalogName(), this.rdbmsStoreMgr.getSchemaName());
         this.schemaDataByName.put("tables", info);
      }

      String tableKey = this.getTableKeyInRDBMSSchemaInfo(catalogName, schemaName, tableName);
      RDBMSTableInfo tableInfo = (RDBMSTableInfo)info.getChild(tableKey);
      if (tableInfo != null) {
         long time = (Long)tableInfo.getProperty("time");
         long now = System.currentTimeMillis();
         if (now < time + 300000L) {
            return tableInfo;
         }
      }

      boolean insensitiveIdentifiers = this.identifiersCaseInsensitive();
      Collection tableNames = new HashSet();
      Collection tables = this.rdbmsStoreMgr.getManagedTables(catalogName, schemaName);
      if (tables.size() > 0) {
         for(Table tbl : tables) {
            tableNames.add(insensitiveIdentifiers ? tbl.getIdentifier().getName().toLowerCase() : tbl.getIdentifier().getName());
         }
      }

      tableNames.add(insensitiveIdentifiers ? tableName.toLowerCase() : tableName);
      this.refreshTableData(conn, catalogName, schemaName, tableNames);
      tableInfo = (RDBMSTableInfo)info.getChild(tableKey);
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         if (tableInfo != null && tableInfo.getNumberOfChildren() != 0) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050032", new Object[]{tableName, "" + tableInfo.getNumberOfChildren()}));
         } else {
            NucleusLogger.DATASTORE_SCHEMA.info(Localiser.msg("050030", new Object[]{tableName}));
         }
      }

      return tableInfo;
   }

   protected RDBMSColumnInfo getRDBMSColumnInfoForColumn(Connection conn, Table table, String columnName) {
      RDBMSColumnInfo colInfo = null;
      RDBMSTableInfo tableInfo = this.getRDBMSTableInfoForTable(conn, table);
      if (tableInfo != null) {
         colInfo = (RDBMSColumnInfo)tableInfo.getChild(columnName);
         if (colInfo == null) {
         }
      }

      return colInfo;
   }

   private void refreshTableData(Object connection, String catalog, String schema, Collection tableNames) {
      if (!this.storeMgr.getBooleanProperty("datanucleus.rdbms.omitDatabaseMetaDataGetColumns")) {
         if (tableNames != null && tableNames.size() != 0) {
            RDBMSSchemaInfo info = (RDBMSSchemaInfo)this.getSchemaData(connection, "tables", (Object[])null);
            if (info == null) {
               info = new RDBMSSchemaInfo(this.rdbmsStoreMgr.getCatalogName(), this.rdbmsStoreMgr.getSchemaName());
               this.schemaDataByName.put("tables", info);
            }

            Long now = System.currentTimeMillis();
            ResultSet rs = null;
            Set<String> tablesProcessed = new HashSet();

            try {
               Connection conn = (Connection)connection;
               String catalogName = this.getIdentifierForUseWithDatabaseMetaData(catalog);
               String schemaName = this.getIdentifierForUseWithDatabaseMetaData(schema);
               if (tableNames.size() == 1) {
                  String tableName = this.getIdentifierForUseWithDatabaseMetaData((String)tableNames.iterator().next());
                  if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
                     NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050028", new Object[]{tableName, catalogName, schemaName}));
                  }

                  rs = this.getDatastoreAdapter().getColumns(conn, catalogName, schemaName, tableName, (String)null);
               } else {
                  if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
                     NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050028", new Object[]{StringUtils.collectionToString(tableNames), catalogName, schemaName}));
                  }

                  rs = this.getDatastoreAdapter().getColumns(conn, catalogName, schemaName, (String)null, (String)null);
               }

               boolean insensitiveIdentifiers = this.identifiersCaseInsensitive();

               while(rs.next()) {
                  String colCatalogName = rs.getString(1);
                  String colSchemaName = rs.getString(2);
                  String colTableName = rs.getString(3);
                  if (StringUtils.isWhitespace(colTableName)) {
                     throw new NucleusDataStoreException("Invalid 'null' table name identifier returned by database. Check with your JDBC driver vendor (ref:DatabaseMetaData.getColumns).");
                  }

                  if (rs.wasNull() || colCatalogName != null && colCatalogName.length() < 1) {
                     colCatalogName = null;
                  }

                  if (rs.wasNull() || colSchemaName != null && colSchemaName.length() < 1) {
                     colSchemaName = null;
                  }

                  String colTableNameToCheck = colTableName;
                  if (insensitiveIdentifiers) {
                     colTableNameToCheck = colTableName.toLowerCase();
                  }

                  if (tableNames.contains(colTableNameToCheck)) {
                     String tableKey = this.getTableKeyInRDBMSSchemaInfo(catalog, schema, colTableName);
                     RDBMSTableInfo table = (RDBMSTableInfo)info.getChild(tableKey);
                     if (tablesProcessed.add(tableKey)) {
                        if (table == null) {
                           table = new RDBMSTableInfo(colCatalogName, colSchemaName, colTableName);
                           table.addProperty("table_key", tableKey);
                           info.addChild(table);
                        } else {
                           table.clearChildren();
                        }

                        table.addProperty("time", now);
                     }

                     RDBMSColumnInfo col = this.getDatastoreAdapter().newRDBMSColumnInfo(rs);
                     table.addChild(col);
                  }
               }
            } catch (NullPointerException npe) {
               NucleusLogger.DATASTORE_SCHEMA.warn("Exception thrown obtaining schema column information from datastore", npe);
               throw new NucleusDataStoreException("Exception thrown obtaining schema column information from datastore", npe);
            } catch (SQLException sqle) {
               NucleusLogger.DATASTORE_SCHEMA.warn("Exception thrown obtaining schema column information from datastore", sqle);
               throw new NucleusDataStoreException("Exception thrown obtaining schema column information from datastore", sqle);
            } finally {
               try {
                  if (rs != null) {
                     Statement stmt = rs.getStatement();
                     rs.close();
                     if (stmt != null) {
                        stmt.close();
                     }
                  }
               } catch (SQLException sqle) {
                  throw new NucleusDataStoreException("Exception thrown closing results of DatabaseMetaData.getColumns()", sqle);
               }

            }

            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050029", new Object[]{catalog, schema, "" + tablesProcessed.size(), "" + (System.currentTimeMillis() - now)}));
            }

         }
      }
   }

   private String getTableKeyInRDBMSSchemaInfo(String catalog, String schema, String table) {
      DatastoreIdentifier fullyQualifiedTableName = this.rdbmsStoreMgr.getIdentifierFactory().newTableIdentifier(table);
      fullyQualifiedTableName.setCatalogName(catalog);
      fullyQualifiedTableName.setSchemaName(schema);
      return fullyQualifiedTableName.getFullyQualifiedName(true);
   }

   private static String[] splitTableIdentifierName(String separator, String name) {
      String[] result = new String[3];
      int p = name.indexOf(separator);
      if (p < 0) {
         result[2] = name;
      } else {
         int p1 = name.indexOf(separator, p + separator.length());
         if (p1 < 0) {
            result[1] = name.substring(0, p);
            result[2] = name.substring(p + separator.length());
         } else {
            result[0] = name.substring(0, p);
            result[1] = name.substring(p + separator.length(), p1);
            result[2] = name.substring(p1 + separator.length());
         }
      }

      if (result[1] != null && result[1].length() < 1) {
         result[1] = null;
      }

      if (result[0] != null && result[0].length() < 1) {
         result[0] = null;
      }

      return result;
   }

   private String getIdentifierForUseWithDatabaseMetaData(String identifier) {
      return identifier == null ? null : identifier.replace(this.getDatastoreAdapter().getIdentifierQuoteString(), "");
   }

   private boolean identifiersCaseInsensitive() {
      DatastoreAdapter dba = this.getDatastoreAdapter();
      return !dba.supportsOption("MixedCaseSensitiveIdentifiers") && !dba.supportsOption("MixedCaseQuotedSensitiveIdentifiers");
   }
}
