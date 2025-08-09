package org.datanucleus;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.properties.PropertyStore;
import org.datanucleus.store.connection.ConnectionResourceType;
import org.datanucleus.transaction.NucleusTransactionException;
import org.datanucleus.transaction.jta.JTASyncRegistry;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class JTATransactionImpl extends TransactionImpl implements Synchronization {
   private static boolean JBOSS_SERVER = System.getProperty("jboss.server.name") != null;
   private TransactionManager jtaTM;
   private javax.transaction.Transaction jtaTx;
   private JTASyncRegistry jtaSyncRegistry;
   protected JoinStatus joinStatus;
   private UserTransaction userTransaction;
   protected boolean autoJoin;

   JTATransactionImpl(ExecutionContext ec, boolean autoJoin, PropertyStore properties) {
      super(ec, properties);
      this.joinStatus = JTATransactionImpl.JoinStatus.NO_TXN;
      this.autoJoin = true;
      this.autoJoin = autoJoin;
      Configuration conf = ec.getNucleusContext().getConfiguration();
      if (ConnectionResourceType.JTA.toString().equalsIgnoreCase(conf.getStringProperty("datanucleus.connection.resourceType")) && ConnectionResourceType.JTA.toString().equalsIgnoreCase(conf.getStringProperty("datanucleus.connection2.resourceType"))) {
         this.txnMgr.setContainerManagedConnections(true);
         this.jtaTM = ec.getNucleusContext().getJtaTransactionManager();
         if (this.jtaTM == null) {
            throw new NucleusTransactionException(Localiser.msg("015030"));
         } else {
            this.jtaSyncRegistry = ec.getNucleusContext().getJtaSyncRegistry();
            if (autoJoin) {
               this.joinTransaction();
            }

         }
      } else {
         throw new NucleusException("Internal error: either datanucleus.connection.resourceType or datanucleus.connection2.resourceType have not been set to JTA; this should have happened automatically.");
      }
   }

   public boolean isJoined() {
      return this.joinStatus == JTATransactionImpl.JoinStatus.JOINED;
   }

   private int getTransactionStatus() {
      try {
         return this.jtaTM.getStatus();
      } catch (SystemException se) {
         throw new NucleusTransactionException(Localiser.msg("015026"), se);
      }
   }

   public void joinTransaction() {
      if (this.joinStatus != JTATransactionImpl.JoinStatus.JOINED) {
         try {
            javax.transaction.Transaction txn = this.jtaTM.getTransaction();
            int txnstat = this.jtaTM.getStatus();
            if (this.jtaTx != null && !this.jtaTx.equals(txn)) {
               if (this.joinStatus != JTATransactionImpl.JoinStatus.IMPOSSIBLE) {
                  throw new InternalError("JTA Transaction changed without being notified");
               }

               this.jtaTx = null;
               this.joinStatus = JTATransactionImpl.JoinStatus.NO_TXN;
               this.joinTransaction();
            } else if (this.jtaTx == null) {
               this.jtaTx = txn;
               boolean allow_join = txnstat == 0;
               if (allow_join) {
                  this.joinStatus = JTATransactionImpl.JoinStatus.IMPOSSIBLE;

                  try {
                     if (this.jtaSyncRegistry != null) {
                        this.jtaSyncRegistry.register(this);
                     } else {
                        this.jtaTx.registerSynchronization(this);
                     }

                     boolean was_active = super.isActive();
                     if (!was_active) {
                        this.internalBegin();
                     }
                  } catch (Exception e) {
                     throw new NucleusTransactionException("Cannot register Synchronization to a valid JTA Transaction", e);
                  }

                  this.joinStatus = JTATransactionImpl.JoinStatus.JOINED;
               } else if (this.jtaTx != null) {
                  this.joinStatus = JTATransactionImpl.JoinStatus.IMPOSSIBLE;
               }
            }
         } catch (SystemException e) {
            throw new NucleusTransactionException(Localiser.msg("015026"), e);
         }
      }

   }

   public boolean getIsActive() {
      if (this.closed) {
         return false;
      } else {
         int txnStatus = this.getTransactionStatus();
         return txnStatus != 3 && txnStatus != 4 ? super.getIsActive() : false;
      }
   }

   public boolean isActive() {
      if (this.autoJoin) {
         if (this.joinStatus == JTATransactionImpl.JoinStatus.JOINED) {
            return super.isActive();
         } else {
            this.joinTransaction();
            return super.isActive() || this.joinStatus == JTATransactionImpl.JoinStatus.IMPOSSIBLE;
         }
      } else {
         return super.isActive();
      }
   }

   public void begin() {
      this.joinTransaction();
      if (this.joinStatus != JTATransactionImpl.JoinStatus.NO_TXN) {
         throw new NucleusTransactionException("JTA Transaction is already active");
      } else {
         UserTransaction utx;
         try {
            Context ctx = new InitialContext();
            if (JBOSS_SERVER) {
               utx = (UserTransaction)ctx.lookup("UserTransaction");
            } else {
               utx = (UserTransaction)ctx.lookup("java:comp/UserTransaction");
            }
         } catch (NamingException e) {
            throw this.ec.getApiAdapter().getUserExceptionForException("Failed to obtain UserTransaction", e);
         }

         try {
            utx.begin();
         } catch (NotSupportedException e) {
            throw this.ec.getApiAdapter().getUserExceptionForException("Failed to begin UserTransaction", e);
         } catch (SystemException e) {
            throw this.ec.getApiAdapter().getUserExceptionForException("Failed to begin UserTransaction", e);
         }

         this.joinTransaction();
         if (this.joinStatus != JTATransactionImpl.JoinStatus.JOINED) {
            throw new NucleusTransactionException("Cannot join an auto started UserTransaction");
         } else {
            this.userTransaction = utx;
         }
      }
   }

   public void commit() {
      if (this.userTransaction == null) {
         throw new NucleusTransactionException("No internal UserTransaction");
      } else {
         try {
            this.userTransaction.commit();
         } catch (Exception e) {
            throw this.ec.getApiAdapter().getUserExceptionForException("Failed to commit UserTransaction", e);
         } finally {
            this.userTransaction = null;
         }

      }
   }

   public void rollback() {
      if (this.userTransaction == null) {
         throw new NucleusTransactionException("No internal UserTransaction");
      } else {
         try {
            this.userTransaction.rollback();
         } catch (Exception e) {
            throw this.ec.getApiAdapter().getUserExceptionForException("Failed to rollback UserTransaction", e);
         } finally {
            this.userTransaction = null;
         }

      }
   }

   public void setRollbackOnly() {
      if (this.userTransaction == null) {
         throw new NucleusTransactionException("No internal UserTransaction");
      } else {
         try {
            this.userTransaction.setRollbackOnly();
         } catch (Exception e) {
            throw this.ec.getApiAdapter().getUserExceptionForException("Failed to rollback-only UserTransaction", e);
         }
      }
   }

   public void beforeCompletion() {
      RuntimeException thr = null;
      boolean success = false;

      try {
         this.flush();
         this.internalPreCommit();
         this.flush();
         success = true;
      } catch (RuntimeException e) {
         thr = e;
         throw e;
      } finally {
         if (!success) {
            NucleusLogger.TRANSACTION.error(Localiser.msg("015044"), thr);

            try {
               this.jtaTx.setRollbackOnly();
            } catch (Exception e) {
               NucleusLogger.TRANSACTION.fatal(Localiser.msg("015045"), e);
            }
         }

      }

   }

   public synchronized void afterCompletion(int status) {
      if (this.closed) {
         NucleusLogger.TRANSACTION.warn(Localiser.msg("015048", this));
      } else {
         RuntimeException thr = null;
         boolean success = false;

         try {
            if (status == 4) {
               super.rollback();
            } else if (status == 3) {
               super.internalPostCommit();
            } else {
               NucleusLogger.TRANSACTION.fatal(Localiser.msg("015047", (long)status));
            }

            success = true;
         } catch (RuntimeException re) {
            thr = re;
            NucleusLogger.TRANSACTION.error("Exception in afterCompletion : " + re.getMessage(), re);
            throw re;
         } finally {
            this.jtaTx = null;
            this.joinStatus = JTATransactionImpl.JoinStatus.NO_TXN;
            if (!success) {
               NucleusLogger.TRANSACTION.error(Localiser.msg("015046"), thr);
            }

         }

         if (this.active) {
            throw new NucleusTransactionException("internal error, must not be active after afterCompletion()!");
         }
      }
   }

   private static enum JoinStatus {
      NO_TXN,
      IMPOSSIBLE,
      JOINED;
   }
}
