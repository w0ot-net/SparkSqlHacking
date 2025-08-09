package org.datanucleus.store.rdbms.valuegenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.valuegenerator.ValueGenerationBlock;
import org.datanucleus.store.valuegenerator.ValueGenerationException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public final class SequenceGenerator extends AbstractRDBMSGenerator {
   protected String sequenceName = null;

   public SequenceGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 1;
      if (this.properties != null) {
         if (this.properties.get("key-increment-by") != null) {
            try {
               this.allocationSize = Integer.parseInt((String)this.properties.get("key-increment-by"));
            } catch (Exception var5) {
               throw new ValueGenerationException(Localiser.msg("040006", new Object[]{this.properties.get("key-increment-by")}));
            }
         } else if (this.properties.get("key-cache-size") != null) {
            try {
               this.allocationSize = Integer.parseInt((String)this.properties.get("key-cache-size"));
            } catch (Exception var4) {
               throw new ValueGenerationException(Localiser.msg("040006", new Object[]{this.properties.get("key-cache-size")}));
            }
         }

         if (this.properties.get("sequence-name") == null) {
            throw new ValueGenerationException(Localiser.msg("040007", new Object[]{this.properties.get("sequence-name")}));
         }
      }

   }

   protected synchronized ValueGenerationBlock reserveBlock(long size) {
      if (size < 1L) {
         return null;
      } else {
         PreparedStatement ps = null;
         ResultSet rs = null;
         List oid = new ArrayList();
         RDBMSStoreManager srm = (RDBMSStoreManager)this.storeMgr;
         SQLController sqlControl = srm.getSQLController();

         ValueGenerationBlock var22;
         try {
            DatastoreAdapter dba = srm.getDatastoreAdapter();
            String stmt = dba.getSequenceNextStmt(this.getSequenceName());
            ps = sqlControl.getStatementForQuery(this.connection, stmt);
            rs = sqlControl.executeStatementQuery((ExecutionContext)null, this.connection, stmt, ps);
            Long nextId = 0L;
            if (rs.next()) {
               nextId = rs.getLong(1);
               oid.add(nextId);
            }

            for(int i = 1; (long)i < size; ++i) {
               nextId = nextId + 1L;
               oid.add(nextId);
            }

            if (NucleusLogger.VALUEGENERATION.isDebugEnabled()) {
               NucleusLogger.VALUEGENERATION.debug(Localiser.msg("040004", new Object[]{"" + size}));
            }

            var22 = new ValueGenerationBlock(oid);
         } catch (SQLException e) {
            throw new ValueGenerationException(Localiser.msg("061001", new Object[]{e.getMessage()}), e);
         } finally {
            try {
               if (rs != null) {
                  rs.close();
               }

               if (ps != null) {
                  sqlControl.closeStatement(this.connection, ps);
               }
            } catch (SQLException var19) {
            }

         }

         return var22;
      }
   }

   protected String getSequenceName() {
      if (this.sequenceName == null) {
         String sequenceCatalogName = this.properties.getProperty("sequence-catalog-name");
         if (sequenceCatalogName == null) {
            sequenceCatalogName = this.properties.getProperty("catalog-name");
         }

         String sequenceSchemaName = this.properties.getProperty("sequence-schema-name");
         if (sequenceSchemaName == null) {
            sequenceSchemaName = this.properties.getProperty("schema-name");
         }

         String sequenceName = this.properties.getProperty("sequence-name");
         RDBMSStoreManager srm = (RDBMSStoreManager)this.storeMgr;
         DatastoreAdapter dba = srm.getDatastoreAdapter();
         DatastoreIdentifier identifier = srm.getIdentifierFactory().newSequenceIdentifier(sequenceName);
         if (dba.supportsOption("CatalogInTableDefinition") && sequenceCatalogName != null) {
            identifier.setCatalogName(sequenceCatalogName);
         }

         if (dba.supportsOption("SchemaInTableDefinition") && sequenceSchemaName != null) {
            identifier.setSchemaName(sequenceSchemaName);
         }

         this.sequenceName = identifier.getFullyQualifiedName(true);
      }

      return this.sequenceName;
   }

   protected boolean requiresRepository() {
      return true;
   }

   protected boolean repositoryExists() {
      String sequenceCatalogName = this.properties.getProperty("sequence-catalog-name");
      if (sequenceCatalogName == null) {
         sequenceCatalogName = this.properties.getProperty("catalog-name");
      }

      if (!StringUtils.isWhitespace(sequenceCatalogName)) {
         IdentifierFactory idFactory = ((RDBMSStoreManager)this.storeMgr).getIdentifierFactory();
         sequenceCatalogName = idFactory.getIdentifierInAdapterCase(sequenceCatalogName);
      }

      String sequenceSchemaName = this.properties.getProperty("sequence-schema-name");
      if (sequenceSchemaName == null) {
         sequenceSchemaName = this.properties.getProperty("schema-name");
      }

      if (!StringUtils.isWhitespace(sequenceSchemaName)) {
         IdentifierFactory idFactory = ((RDBMSStoreManager)this.storeMgr).getIdentifierFactory();
         sequenceSchemaName = idFactory.getIdentifierInAdapterCase(sequenceSchemaName);
      }

      String seqName = this.properties.getProperty("sequence-name");
      IdentifierFactory idFactory = ((RDBMSStoreManager)this.storeMgr).getIdentifierFactory();
      seqName = idFactory.getIdentifierInAdapterCase(seqName);
      return ((RDBMSStoreManager)this.storeMgr).getDatastoreAdapter().sequenceExists((Connection)this.connection.getConnection(), sequenceCatalogName, sequenceSchemaName, seqName);
   }

   protected boolean createRepository() {
      PreparedStatement ps = null;
      RDBMSStoreManager srm = (RDBMSStoreManager)this.storeMgr;
      DatastoreAdapter dba = srm.getDatastoreAdapter();
      SQLController sqlControl = srm.getSQLController();
      if (!srm.getSchemaHandler().isAutoCreateTables()) {
         throw new NucleusUserException(Localiser.msg("040010", new Object[]{this.getSequenceName()}));
      } else {
         Integer min = this.properties.containsKey("key-min-value") ? Integer.valueOf(this.properties.getProperty("key-min-value")) : null;
         Integer max = this.properties.containsKey("key-max-value") ? Integer.valueOf(this.properties.getProperty("key-max-value")) : null;
         Integer start = this.properties.containsKey("key-initial-value") ? Integer.valueOf(this.properties.getProperty("key-initial-value")) : null;
         Integer incr = this.properties.containsKey("key-cache-size") ? Integer.valueOf(this.properties.getProperty("key-cache-size")) : null;
         Integer cacheSize = this.properties.containsKey("key-database-cache-size") ? Integer.valueOf(this.properties.getProperty("key-database-cache-size")) : null;
         String stmt = dba.getSequenceCreateStmt(this.getSequenceName(), min, max, start, incr, cacheSize);

         try {
            ps = sqlControl.getStatementForUpdate(this.connection, stmt, false);
            sqlControl.executeStatementUpdate((ExecutionContext)null, this.connection, stmt, ps, true);
         } catch (SQLException e) {
            NucleusLogger.DATASTORE.error(e);
            throw new ValueGenerationException(Localiser.msg("061000", new Object[]{e.getMessage()}) + stmt);
         } finally {
            try {
               if (ps != null) {
                  sqlControl.closeStatement(this.connection, ps);
               }
            } catch (SQLException var18) {
            }

         }

         return true;
      }
   }
}
