package org.datanucleus.store.rdbms.valuegenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.valuegenerator.ValueGenerationBlock;
import org.datanucleus.store.valuegenerator.ValueGenerationException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public final class TableGenerator extends AbstractRDBMSGenerator {
   private SequenceTable sequenceTable = null;
   private final String sequenceName;
   public static final String DEFAULT_TABLE_NAME = "SEQUENCE_TABLE";
   public static final String DEFAULT_SEQUENCE_COLUMN_NAME = "SEQUENCE_NAME";
   public static final String DEFAULT_NEXTVALUE_COLUMN_NAME = "NEXT_VAL";

   public TableGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 5;
      this.initialValue = -1;
      if (this.properties != null) {
         if (this.properties.get("key-cache-size") != null) {
            try {
               this.allocationSize = Integer.parseInt(this.properties.getProperty("key-cache-size"));
            } catch (Exception var5) {
               throw new ValueGenerationException(Localiser.msg("Sequence040006", new Object[]{this.properties.get("key-cache-size")}));
            }
         }

         if (this.properties.get("key-initial-value") != null) {
            try {
               this.initialValue = Integer.parseInt(this.properties.getProperty("key-initial-value"));
            } catch (NumberFormatException var4) {
            }
         }

         if (this.properties.getProperty("sequence-name") != null) {
            this.sequenceName = this.properties.getProperty("sequence-name");
         } else if (this.properties.getProperty("sequence-table-basis") != null && this.properties.getProperty("sequence-table-basis").equalsIgnoreCase("table")) {
            this.sequenceName = this.properties.getProperty("table-name");
         } else {
            this.sequenceName = this.properties.getProperty("root-class-name");
         }
      } else {
         this.sequenceName = "SEQUENCENAME";
      }

   }

   public SequenceTable getTable() {
      return this.sequenceTable;
   }

   public ValueGenerationBlock reserveBlock(long size) {
      if (size < 1L) {
         return null;
      } else {
         List<Long> oid = new ArrayList();

         try {
            if (this.sequenceTable == null) {
               this.initialiseSequenceTable();
            }

            DatastoreIdentifier sourceTableIdentifier = null;
            if (this.properties.getProperty("table-name") != null) {
               sourceTableIdentifier = ((RDBMSStoreManager)this.storeMgr).getIdentifierFactory().newTableIdentifier(this.properties.getProperty("table-name"));
            }

            Long nextId = this.sequenceTable.getNextVal(this.sequenceName, this.connection, (int)size, sourceTableIdentifier, this.properties.getProperty("column-name"), this.initialValue);

            for(int i = 0; (long)i < size; ++i) {
               oid.add(nextId);
               nextId = nextId + 1L;
            }

            if (NucleusLogger.VALUEGENERATION.isDebugEnabled()) {
               NucleusLogger.VALUEGENERATION.debug(Localiser.msg("040004", new Object[]{"" + size}));
            }

            return new ValueGenerationBlock(oid);
         } catch (SQLException e) {
            throw new ValueGenerationException(Localiser.msg("061001", new Object[]{e.getMessage()}));
         }
      }
   }

   protected boolean requiresRepository() {
      return true;
   }

   protected boolean repositoryExists() {
      if (this.repositoryExists) {
         return this.repositoryExists;
      } else if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.omitDatabaseMetaDataGetColumns")) {
         this.repositoryExists = true;
         return this.repositoryExists;
      } else {
         try {
            if (this.sequenceTable == null) {
               this.initialiseSequenceTable();
            }

            this.sequenceTable.exists((Connection)this.connection.getConnection(), true);
            this.repositoryExists = true;
            return true;
         } catch (SQLException sqle) {
            throw new ValueGenerationException("Exception thrown calling table.exists() for " + this.sequenceTable, sqle);
         }
      }
   }

   protected boolean createRepository() {
      RDBMSStoreManager srm = (RDBMSStoreManager)this.storeMgr;
      if (!srm.getSchemaHandler().isAutoCreateTables()) {
         throw new NucleusUserException(Localiser.msg("040011", new Object[]{this.sequenceTable}));
      } else {
         try {
            if (this.sequenceTable == null) {
               this.initialiseSequenceTable();
            }

            this.sequenceTable.exists((Connection)this.connection.getConnection(), true);
            this.repositoryExists = true;
            return true;
         } catch (SQLException sqle) {
            throw new ValueGenerationException("Exception thrown calling table.exists() for " + this.sequenceTable, sqle);
         }
      }
   }

   protected void initialiseSequenceTable() {
      String catalogName = this.properties.getProperty("sequence-catalog-name");
      if (catalogName == null) {
         catalogName = this.properties.getProperty("catalog-name");
      }

      String schemaName = this.properties.getProperty("sequence-schema-name");
      if (schemaName == null) {
         schemaName = this.properties.getProperty("schema-name");
      }

      String tableName = this.properties.getProperty("sequence-table-name") == null ? "SEQUENCE_TABLE" : this.properties.getProperty("sequence-table-name");
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.storeMgr;
      DatastoreAdapter dba = storeMgr.getDatastoreAdapter();
      DatastoreIdentifier identifier = storeMgr.getIdentifierFactory().newTableIdentifier(tableName);
      if (dba.supportsOption("CatalogInTableDefinition") && catalogName != null) {
         identifier.setCatalogName(catalogName);
      }

      if (dba.supportsOption("SchemaInTableDefinition") && schemaName != null) {
         identifier.setSchemaName(schemaName);
      }

      DatastoreClass table = storeMgr.getDatastoreClass(identifier);
      if (table != null) {
         this.sequenceTable = (SequenceTable)table;
      } else {
         String sequenceNameColumnName = "SEQUENCE_NAME";
         String nextValColumnName = "NEXT_VAL";
         if (this.properties.getProperty("sequence-name-column-name") != null) {
            sequenceNameColumnName = this.properties.getProperty("sequence-name-column-name");
         }

         if (this.properties.getProperty("sequence-nextval-column-name") != null) {
            nextValColumnName = this.properties.getProperty("sequence-nextval-column-name");
         }

         this.sequenceTable = new SequenceTable(identifier, storeMgr, sequenceNameColumnName, nextValColumnName);
         this.sequenceTable.initialize(storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null));
      }

   }
}
