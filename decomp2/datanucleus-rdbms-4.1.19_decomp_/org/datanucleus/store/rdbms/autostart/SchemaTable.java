package org.datanucleus.store.rdbms.autostart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MissingTableException;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.TableImpl;

public class SchemaTable extends TableImpl {
   private JavaTypeMapping classMapping = null;
   private JavaTypeMapping tableMapping = null;
   private JavaTypeMapping typeMapping = null;
   private JavaTypeMapping ownerMapping = null;
   private JavaTypeMapping versionMapping = null;
   private JavaTypeMapping interfaceNameMapping = null;
   private String insertStmt = null;
   private String deleteStmt = null;
   private String deleteAllStmt = null;
   private String fetchAllStmt = null;
   private String fetchStmt = null;

   public SchemaTable(RDBMSStoreManager storeMgr, String tableName) {
      super(storeMgr.getIdentifierFactory().newTableIdentifier(tableName != null ? tableName : "NUCLEUS_TABLES"), storeMgr);
   }

   public void initialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
      MappingManager mapMgr = this.getStoreManager().getMappingManager();
      this.classMapping = mapMgr.getMapping(String.class);
      Column class_column = this.addColumn(String.class.getName(), idFactory.newColumnIdentifier("CLASS_NAME"), this.classMapping, (ColumnMetaData)null);
      mapMgr.createDatastoreMapping(this.classMapping, class_column, String.class.getName());
      class_column.getColumnMetaData().setLength(128);
      class_column.getColumnMetaData().setJdbcType(JdbcType.VARCHAR);
      class_column.setPrimaryKey();
      this.tableMapping = mapMgr.getMapping(String.class);
      Column table_column = this.addColumn(String.class.getName(), idFactory.newColumnIdentifier("TABLE_NAME"), this.tableMapping, (ColumnMetaData)null);
      mapMgr.createDatastoreMapping(this.tableMapping, table_column, String.class.getName());
      table_column.getColumnMetaData().setLength(128);
      table_column.getColumnMetaData().setJdbcType(JdbcType.VARCHAR);
      this.typeMapping = mapMgr.getMapping(String.class);
      Column type_column = this.addColumn(String.class.getName(), idFactory.newColumnIdentifier("TYPE"), this.typeMapping, (ColumnMetaData)null);
      mapMgr.createDatastoreMapping(this.typeMapping, type_column, String.class.getName());
      type_column.getColumnMetaData().setLength(4);
      type_column.getColumnMetaData().setJdbcType(JdbcType.VARCHAR);
      this.ownerMapping = mapMgr.getMapping(String.class);
      Column owner_column = this.addColumn(String.class.getName(), idFactory.newColumnIdentifier("OWNER"), this.ownerMapping, (ColumnMetaData)null);
      mapMgr.createDatastoreMapping(this.ownerMapping, owner_column, String.class.getName());
      owner_column.getColumnMetaData().setLength(2);
      owner_column.getColumnMetaData().setJdbcType(JdbcType.VARCHAR);
      this.versionMapping = mapMgr.getMapping(String.class);
      Column version_column = this.addColumn(String.class.getName(), idFactory.newColumnIdentifier("VERSION"), this.versionMapping, (ColumnMetaData)null);
      mapMgr.createDatastoreMapping(this.versionMapping, version_column, String.class.getName());
      version_column.getColumnMetaData().setLength(20);
      version_column.getColumnMetaData().setJdbcType(JdbcType.VARCHAR);
      this.interfaceNameMapping = mapMgr.getMapping(String.class);
      Column interfaceName_column = this.addColumn(String.class.getName(), idFactory.newColumnIdentifier("INTERFACE_NAME"), this.interfaceNameMapping, (ColumnMetaData)null);
      mapMgr.createDatastoreMapping(this.interfaceNameMapping, interfaceName_column, String.class.getName());
      interfaceName_column.getColumnMetaData().setLength(255);
      interfaceName_column.getColumnMetaData().setJdbcType(JdbcType.VARCHAR);
      interfaceName_column.setNullable(true);
      this.insertStmt = "INSERT INTO " + this.identifier.getFullyQualifiedName(false) + " (" + class_column.getIdentifier() + "," + table_column.getIdentifier() + "," + type_column.getIdentifier() + "," + owner_column.getIdentifier() + "," + version_column.getIdentifier() + "," + interfaceName_column.getIdentifier() + ") VALUES (?,?,?,?,?,?)";
      this.deleteStmt = "DELETE FROM " + this.identifier.getFullyQualifiedName(false) + " WHERE " + idFactory.getIdentifierInAdapterCase("CLASS_NAME") + "=?";
      this.deleteAllStmt = "DELETE FROM " + this.identifier.getFullyQualifiedName(false);
      this.fetchAllStmt = "SELECT " + class_column.getIdentifier() + "," + table_column.getIdentifier() + "," + type_column.getIdentifier() + "," + owner_column.getIdentifier() + "," + version_column.getIdentifier() + "," + interfaceName_column.getIdentifier() + " FROM " + this.identifier.getFullyQualifiedName(false) + " ORDER BY " + table_column.getIdentifier();
      this.fetchStmt = "SELECT 1 FROM " + this.identifier.getFullyQualifiedName(false) + " WHERE " + idFactory.getIdentifierInAdapterCase("CLASS_NAME") + " = ? ";
      this.state = 2;
   }

   public JavaTypeMapping getIdMapping() {
      throw (new NucleusException("Attempt to get ID mapping of SchemaTable!")).setFatal();
   }

   public HashSet getAllClasses(ManagedConnection conn) throws SQLException {
      HashSet schema_data = new HashSet();
      if (this.storeMgr.getDdlWriter() != null && !this.tableExists((Connection)conn.getConnection())) {
         return schema_data;
      } else {
         SQLController sqlControl = this.storeMgr.getSQLController();
         PreparedStatement ps = sqlControl.getStatementForQuery(conn, this.fetchAllStmt);

         try {
            ResultSet rs = sqlControl.executeStatementQuery((ExecutionContext)null, conn, this.fetchAllStmt, ps);

            try {
               while(rs.next()) {
                  StoreData data = new RDBMSStoreData(rs.getString(1), rs.getString(2), rs.getString(4).equals("1"), rs.getString(3).equals("FCO") ? 1 : 2, rs.getString(6));
                  schema_data.add(data);
               }
            } finally {
               rs.close();
            }
         } finally {
            sqlControl.closeStatement(conn, ps);
         }

         return schema_data;
      }
   }

   public void addClass(RDBMSStoreData data, ManagedConnection conn) throws SQLException {
      if (this.storeMgr.getDdlWriter() == null) {
         if (!this.hasClass(data, conn)) {
            SQLController sqlControl = this.storeMgr.getSQLController();
            PreparedStatement ps = sqlControl.getStatementForUpdate(conn, this.insertStmt, false);

            try {
               int jdbc_id = 1;
               this.classMapping.setString((ExecutionContext)null, ps, MappingHelper.getMappingIndices(jdbc_id, this.classMapping), data.getName());
               jdbc_id += this.classMapping.getNumberOfDatastoreMappings();
               this.tableMapping.setString((ExecutionContext)null, ps, MappingHelper.getMappingIndices(jdbc_id, this.tableMapping), data.hasTable() ? data.getTableName() : "");
               jdbc_id += this.tableMapping.getNumberOfDatastoreMappings();
               this.typeMapping.setString((ExecutionContext)null, ps, MappingHelper.getMappingIndices(jdbc_id, this.typeMapping), data.isFCO() ? "FCO" : "SCO");
               jdbc_id += this.typeMapping.getNumberOfDatastoreMappings();
               this.ownerMapping.setString((ExecutionContext)null, ps, MappingHelper.getMappingIndices(jdbc_id, this.ownerMapping), data.isTableOwner() ? "1" : "0");
               jdbc_id += this.ownerMapping.getNumberOfDatastoreMappings();
               this.versionMapping.setString((ExecutionContext)null, ps, MappingHelper.getMappingIndices(jdbc_id, this.versionMapping), "DataNucleus");
               jdbc_id += this.versionMapping.getNumberOfDatastoreMappings();
               this.interfaceNameMapping.setString((ExecutionContext)null, ps, MappingHelper.getMappingIndices(jdbc_id, this.interfaceNameMapping), data.getInterfaceName());
               int var10000 = jdbc_id + this.interfaceNameMapping.getNumberOfDatastoreMappings();
               sqlControl.executeStatementUpdate((ExecutionContext)null, conn, this.insertStmt, ps, true);
            } finally {
               sqlControl.closeStatement(conn, ps);
            }

         }
      }
   }

   private boolean hasClass(StoreData data, ManagedConnection conn) throws SQLException {
      if (!this.tableExists((Connection)conn.getConnection())) {
         return false;
      } else {
         SQLController sqlControl = this.storeMgr.getSQLController();
         PreparedStatement ps = sqlControl.getStatementForQuery(conn, this.fetchStmt);

         boolean var7;
         try {
            int jdbc_id = 1;
            this.tableMapping.setString((ExecutionContext)null, ps, MappingHelper.getMappingIndices(jdbc_id, this.tableMapping), data.getName());
            ResultSet rs = sqlControl.executeStatementQuery((ExecutionContext)null, conn, this.fetchStmt, ps);

            try {
               if (!rs.next()) {
                  return false;
               }

               var7 = true;
            } finally {
               rs.close();
            }
         } finally {
            sqlControl.closeStatement(conn, ps);
         }

         return var7;
      }
   }

   public void deleteClass(String class_name, ManagedConnection conn) throws SQLException {
      SQLController sqlControl = this.storeMgr.getSQLController();
      PreparedStatement ps = sqlControl.getStatementForUpdate(conn, this.deleteStmt, false);

      try {
         ps.setString(1, class_name);
         sqlControl.executeStatementUpdate((ExecutionContext)null, conn, this.deleteStmt, ps, true);
      } finally {
         sqlControl.closeStatement(conn, ps);
      }

   }

   public void deleteAllClasses(ManagedConnection conn) throws SQLException {
      SQLController sqlControl = this.storeMgr.getSQLController();
      PreparedStatement ps = sqlControl.getStatementForUpdate(conn, this.deleteAllStmt, false);

      try {
         sqlControl.executeStatementUpdate((ExecutionContext)null, conn, this.deleteAllStmt, ps, true);
      } finally {
         sqlControl.closeStatement(conn, ps);
      }

   }

   private boolean tableExists(Connection conn) throws SQLException {
      try {
         this.exists(conn, false);
         return true;
      } catch (MissingTableException var3) {
         return false;
      }
   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      return null;
   }
}
