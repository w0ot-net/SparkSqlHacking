package org.datanucleus.store.rdbms.adapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.schema.VirtuosoTypeInfo;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.NucleusLogger;

public class VirtuosoAdapter extends BaseDatastoreAdapter {
   public VirtuosoAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.add("PrimaryKeyInCreateStatements");
      this.supportedOptions.add("StoredProcs");
      this.supportedOptions.add("IdentityColumns");
   }

   public SQLTypeInfo newSQLTypeInfo(ResultSet rs) {
      return new VirtuosoTypeInfo(rs);
   }

   public String getVendorID() {
      return "virtuoso";
   }

   public String getAddPrimaryKeyStatement(PrimaryKey pk, IdentifierFactory factory) {
      return null;
   }

   public String getDropTableStatement(Table table) {
      return "DROP TABLE " + table.toString();
   }

   public ResultSet getExistingIndexes(Connection conn, String catalog, String schema, String table) throws SQLException {
      String GET_INDEXES_STMT = "SELECT name_part(SYS_KEYS.KEY_TABLE,0) AS table_cat, name_part(SYS_KEYS.KEY_TABLE,1) AS table_schem, name_part(SYS_KEYS.KEY_TABLE,2) AS table_name, iszero(SYS_KEYS.KEY_IS_UNIQUE) AS non_unique, name_part(SYS_KEYS.KEY_TABLE,0) AS index_qualifier, SYS_KEYS.KEY_NAME AS index_name, ((SYS_KEYS.KEY_IS_OBJECT_ID*8)+(3-(2*iszero(SYS_KEYS.KEY_CLUSTER_ON_ID)))) AS type, (SYS_KEY_PARTS.KP_NTH+1) AS ordinal_position, SYS_COLS.\\COLUMN AS column_name, NULL AS asc_or_desc, NULL AS cardinality, NULL AS pages, NULL AS filter_condition FROM DB.DBA.SYS_KEYS SYS_KEYS, DB.DBA.SYS_KEY_PARTS SYS_KEY_PARTS, DB.DBA.SYS_COLS SYS_COLS WHERE name_part(SYS_KEYS.KEY_TABLE,0) LIKE ? AND __any_grants(SYS_KEYS.KEY_TABLE) AND name_part(SYS_KEYS.KEY_TABLE,1) LIKE ? AND name_part(SYS_KEYS.KEY_TABLE,2) LIKE ? AND SYS_KEYS.KEY_MIGRATE_TO IS NULL AND SYS_KEY_PARTS.KP_KEY_ID=SYS_KEYS.KEY_ID AND SYS_KEY_PARTS.KP_NTH < SYS_KEYS.KEY_DECL_PARTS AND SYS_COLS.COL_ID=SYS_KEY_PARTS.KP_COL AND SYS_COLS.\\COLUMN<>'_IDN' AND SYS_KEYS.KEY_IS_MAIN=0";
      if (catalog == null) {
         catalog = conn.getCatalog();
      }

      NucleusLogger.DATASTORE_SCHEMA.debug("Retrieving table indexes using the following SQL : " + GET_INDEXES_STMT);
      NucleusLogger.DATASTORE_SCHEMA.debug("Catalog: " + catalog + " Schema: " + schema + " Table: " + table);
      PreparedStatement stmt = conn.prepareStatement(GET_INDEXES_STMT);
      stmt.setString(1, catalog);
      stmt.setString(2, schema);
      stmt.setString(3, table);
      return stmt.executeQuery();
   }

   public String getAutoIncrementStmt(Table table, String columnName) {
      return "SELECT identity_value()";
   }

   public String getAutoIncrementKeyword() {
      return "IDENTITY";
   }

   public String getDatastoreDateStatement() {
      return "SELECT now()";
   }
}
