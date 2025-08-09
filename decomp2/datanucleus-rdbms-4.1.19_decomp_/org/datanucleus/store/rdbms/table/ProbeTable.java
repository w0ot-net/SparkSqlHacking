package org.datanucleus.store.rdbms.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.schema.naming.NamingCase;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ProbeTable extends TableImpl {
   public ProbeTable(RDBMSStoreManager storeMgr) {
      super(storeMgr.getIdentifierFactory().newTableIdentifier("DELETEME" + System.currentTimeMillis()), storeMgr);
   }

   public void initialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      JavaTypeMapping mapping = this.storeMgr.getMappingManager().getMapping(Integer.TYPE);
      Column column = this.addColumn(Integer.TYPE.getName(), this.storeMgr.getIdentifierFactory().newColumnIdentifier("UNUSED"), mapping, (ColumnMetaData)null);
      this.getStoreManager().getMappingManager().createDatastoreMapping(mapping, column, Integer.TYPE.getName());
      this.state = 2;
   }

   public JavaTypeMapping getIdMapping() {
      throw (new NucleusException("Attempt to get ID mapping of ProbeTable!")).setFatal();
   }

   public String[] findSchemaDetails(Connection conn) throws SQLException {
      String[] schemaDetails = new String[2];
      DatabaseMetaData dmd = conn.getMetaData();
      String table_name = this.identifier.getName();
      if (this.storeMgr.getIdentifierFactory().getNamingCase() != NamingCase.LOWER_CASE && this.storeMgr.getIdentifierFactory().getNamingCase() != NamingCase.LOWER_CASE_QUOTED) {
         if (this.storeMgr.getIdentifierFactory().getNamingCase() == NamingCase.UPPER_CASE || this.storeMgr.getIdentifierFactory().getNamingCase() == NamingCase.UPPER_CASE_QUOTED) {
            table_name = table_name.toUpperCase();
         }
      } else {
         table_name = table_name.toLowerCase();
      }

      String catalog_name = this.storeMgr.getStringProperty("datanucleus.mapping.Catalog");
      String schema_name = this.storeMgr.getStringProperty("datanucleus.mapping.Schema");
      if (!this.dba.supportsOption("CatalogInTableDefinition")) {
         catalog_name = null;
      }

      if (!this.dba.supportsOption("SchemaInTableDefinition")) {
         schema_name = null;
      }

      ResultSet rs = dmd.getTables(catalog_name, schema_name, table_name, (String[])null);

      try {
         if (!rs.next()) {
            throw new NucleusDataStoreException(Localiser.msg("057027", new Object[]{this.identifier}));
         }

         schemaDetails[0] = rs.getString(1);
         schemaDetails[1] = rs.getString(2);
      } finally {
         rs.close();
      }

      if (schemaDetails[0] == null) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057026"));
      }

      if (schemaDetails[1] == null) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057025"));
      }

      return schemaDetails;
   }

   protected boolean allowDDLOutput() {
      return false;
   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      return null;
   }
}
