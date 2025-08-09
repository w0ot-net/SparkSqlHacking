package org.datanucleus;

import javax.transaction.RollbackException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import org.datanucleus.properties.PropertyStore;
import org.datanucleus.transaction.NucleusTransactionException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JTAJCATransactionImpl extends TransactionImpl implements Synchronization {
   private TransactionManager jtaTM;
   private javax.transaction.Transaction jtaTx;
   private boolean markedForRollback = false;

   JTAJCATransactionImpl(ExecutionContext ec, PropertyStore properties) {
      super(ec, properties);
      this.joinTransaction();
   }

   public boolean getIsActive() {
      return this.isActive();
   }

   public boolean isActive() {
      boolean isActive = super.isActive();
      if (isActive) {
         return true;
      } else {
         this.joinTransaction();
         return this.active;
      }
   }

   private synchronized void joinTransaction() {
      if (!this.active) {
         try {
            if (this.jtaTM == null) {
               this.jtaTM = this.ec.getNucleusContext().getJtaTransactionManager();
               if (this.jtaTM == null) {
                  throw new NucleusTransactionException(Localiser.msg("015030"));
               }
            }

            this.jtaTx = this.jtaTM.getTransaction();
            if (this.jtaTx != null && this.jtaTx.getStatus() == 0) {
               if (!this.ec.getNucleusContext().isJcaMode()) {
                  this.jtaTx.registerSynchronization(this);
               }

               this.begin();
            } else if (this.markedForRollback) {
               this.rollback();
               this.markedForRollback = false;
            }
         } catch (SystemException se) {
            throw new NucleusTransactionException(Localiser.msg("015026"), se);
         } catch (RollbackException e) {
            NucleusLogger.TRANSACTION.error("Exception while joining transaction: " + StringUtils.getStringFromStackTrace(e));
         }

      }
   }

   public void beforeCompletion() {
      try {
         this.internalPreCommit();
      } catch (Throwable th) {
         NucleusLogger.TRANSACTION.error("Exception flushing work in JTA transaction. Mark for rollback", th);

         try {
            this.jtaTx.setRollbackOnly();
         } catch (Exception e) {
            NucleusLogger.TRANSACTION.fatal("Cannot mark transaction for rollback after exception in beforeCompletion. PersistenceManager might be in inconsistent state", e);
         }
      }

   }

   public synchronized void afterCompletion(int status) {
      try {
         if (status == 4) {
            this.rollback();
         } else if (status == 3) {
            this.internalPostCommit();
         } else {
            NucleusLogger.TRANSACTION.fatal("Received unexpected transaction status + " + status);
         }
      } catch (Throwable var6) {
         NucleusLogger.TRANSACTION.error("Exception during afterCompletion in JTA transaction. PersistenceManager might be in inconsistent state");
      } finally {
         this.jtaTx = null;
      }

   }
}
