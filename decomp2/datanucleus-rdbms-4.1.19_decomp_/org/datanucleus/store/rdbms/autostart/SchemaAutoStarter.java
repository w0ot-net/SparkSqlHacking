package org.datanucleus.store.rdbms.autostart;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.autostart.AbstractAutoStartMechanism;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.exceptions.DatastoreInitialisationException;
import org.datanucleus.store.rdbms.RDBMSStoreData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.MissingTableException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class SchemaAutoStarter extends AbstractAutoStartMechanism {
   protected SchemaTable schemaTable = null;
   protected RDBMSStoreManager storeMgr = null;
   protected ManagedConnection mconn;

   public SchemaAutoStarter(StoreManager store_mgr, ClassLoaderResolver clr) {
      this.storeMgr = (RDBMSStoreManager)store_mgr;
      String tableName = this.storeMgr.getStringProperty("datanucleus.rdbms.schemaTable.tableName");
      this.schemaTable = new SchemaTable(this.storeMgr, tableName);
      this.schemaTable.initialize(clr);
      ManagedConnection mconn = this.storeMgr.getConnection(0);
      Connection conn = (Connection)mconn.getConnection();

      try {
         this.schemaTable.exists(conn, true);
         if (this.storeMgr.getDdlWriter() != null) {
            try {
               this.schemaTable.validate(conn, true, false, (Collection)null);
            } catch (MissingTableException var16) {
            }
         } else {
            this.schemaTable.validate(conn, true, false, (Collection)null);
         }
      } catch (Exception e) {
         NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("049001", new Object[]{this.storeMgr.getSchemaName(), e}));

         try {
            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("049002", new Object[]{this.schemaTable.toString()}));
            }

            try {
               this.schemaTable.drop(conn);
            } catch (SQLException var14) {
            }

            this.schemaTable.exists(conn, true);
            this.schemaTable.validate(conn, true, false, (Collection)null);
         } catch (Exception e2) {
            NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("049001", new Object[]{this.storeMgr.getSchemaName(), e2}));
         }
      } finally {
         mconn.release();
      }

   }

   public Collection getAllClassData() throws DatastoreInitialisationException {
      try {
         this.assertIsOpen();
         Collection data = null;

         try {
            data = this.schemaTable.getAllClasses(this.mconn);
         } catch (SQLException sqe2) {
            NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("049000", new Object[]{sqe2}));
         }

         return data;
      } catch (Exception e) {
         throw new DatastoreInitialisationException(Localiser.msg("049010", new Object[]{e}), e);
      }
   }

   private void assertIsOpen() {
      if (this.mconn == null) {
         throw (new NucleusException(Localiser.msg("049008"))).setFatal();
      }
   }

   private void assertIsClosed() {
      if (this.mconn != null) {
         throw (new NucleusException(Localiser.msg("049009"))).setFatal();
      }
   }

   public void open() {
      this.assertIsClosed();
      this.mconn = this.storeMgr.getConnection(0);
   }

   public void close() {
      this.assertIsOpen();

      try {
         this.mconn.release();
         this.mconn = null;
      } catch (NucleusException sqe2) {
         NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("050005", new Object[]{sqe2}));
      }

   }

   public boolean isOpen() {
      return this.mconn != null;
   }

   public void addClass(StoreData data) {
      RDBMSStoreData tableData = (RDBMSStoreData)data;
      this.assertIsOpen();

      try {
         this.schemaTable.addClass(tableData, this.mconn);
      } catch (SQLException sqe2) {
         String msg = Localiser.msg("049003", new Object[]{data.getName(), sqe2});
         NucleusLogger.DATASTORE_SCHEMA.error(msg);
         throw new NucleusDataStoreException(msg, sqe2);
      }
   }

   public void deleteClass(String class_name) {
      this.assertIsOpen();

      try {
         this.schemaTable.deleteClass(class_name, this.mconn);
      } catch (SQLException sqe2) {
         NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("049005", new Object[]{class_name, sqe2}));
      }

   }

   public void deleteAllClasses() {
      this.assertIsOpen();

      try {
         this.schemaTable.deleteAllClasses(this.mconn);
      } catch (SQLException sqe2) {
         NucleusLogger.DATASTORE_SCHEMA.error(Localiser.msg("049006", new Object[]{sqe2}));
      }

   }

   public String getStorageDescription() {
      return Localiser.msg("049007", new Object[]{this.schemaTable.toString()});
   }
}
