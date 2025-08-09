package org.datanucleus.store.rdbms;

import java.sql.Connection;
import java.sql.SQLException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.transaction.TransactionUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractSchemaTransaction {
   protected RDBMSStoreManager rdbmsMgr;
   protected final int isolationLevel;
   protected final int maxRetries;
   protected ManagedConnection mconn;
   private Connection conn;

   public AbstractSchemaTransaction(RDBMSStoreManager rdbmsMgr, int isolationLevel) {
      this.rdbmsMgr = rdbmsMgr;
      this.isolationLevel = isolationLevel;
      this.maxRetries = rdbmsMgr.getIntProperty("datanucleus.rdbms.classAdditionMaxRetries");
   }

   public abstract String toString();

   protected abstract void run(ClassLoaderResolver var1) throws SQLException;

   protected Connection getCurrentConnection() throws SQLException {
      if (this.conn == null) {
         this.mconn = this.rdbmsMgr.getConnection(this.isolationLevel);
         this.conn = (Connection)this.mconn.getConnection();
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050057", new Object[]{StringUtils.toJVMIDString(this.conn), TransactionUtils.getNameForTransactionIsolationLevel(this.isolationLevel)}));
         }
      }

      return this.conn;
   }

   public final void execute(ClassLoaderResolver clr) {
      int attempts = 0;

      while(true) {
         try {
            try {
               boolean succeeded = false;

               try {
                  this.run(clr);
                  succeeded = true;
               } finally {
                  if (this.conn != null && this.isolationLevel != 0 && !this.conn.getAutoCommit()) {
                     if (succeeded) {
                        if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
                           NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050053", new Object[]{StringUtils.toJVMIDString(this.conn)}));
                        }

                        this.conn.commit();
                     } else {
                        if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
                           NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050054", new Object[]{StringUtils.toJVMIDString(this.conn)}));
                        }

                        this.conn.rollback();
                     }
                  }

               }
            } finally {
               if (this.conn != null) {
                  if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
                     NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050055", new Object[]{StringUtils.toJVMIDString(this.conn)}));
                  }

                  this.mconn.release();
                  this.conn = null;
               }

            }

            return;
         } catch (SQLException e) {
            ++attempts;
            if (attempts >= this.maxRetries) {
               throw new NucleusDataStoreException(Localiser.msg("050056", new Object[]{this}), e);
            }
         }
      }
   }
}
