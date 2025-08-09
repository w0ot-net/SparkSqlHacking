package org.apache.derby.impl.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TimerTask;
import javax.transaction.xa.XAException;
import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.timer.TimerFactory;
import org.apache.derby.iapi.store.access.xa.XAXactId;
import org.apache.derby.shared.common.error.ExceptionUtil;
import org.apache.derby.shared.common.error.StandardException;

final class XATransactionState extends ContextImpl {
   static final int TRO_TIMEOUT = -3;
   static final int TRO_DEADLOCK = -2;
   static final int TRO_FAIL = -1;
   static final int T0_NOT_ASSOCIATED = 0;
   static final int T1_ASSOCIATED = 1;
   static final int TC_COMPLETED = 3;
   final EmbedConnection conn;
   final EmbedXAResource creatingResource;
   private EmbedXAResource associatedResource;
   final XAXactId xid;
   CleanupOrCancelMonitor cleanupOrCancelMonitor = new CleanupOrCancelMonitor();
   HashMap suspendedList;
   int associationState;
   int rollbackOnlyCode;
   boolean isPrepared;
   boolean performTimeoutRollback;
   CancelXATransactionTask timeoutTask = null;

   private static TimerFactory getTimerFactory() {
      return getMonitor().getTimerFactory();
   }

   XATransactionState(ContextManager var1, EmbedConnection var2, EmbedXAResource var3, XAXactId var4) {
      super(var1, "XATransactionState");
      this.conn = var2;
      this.associatedResource = var3;
      this.creatingResource = var3;
      this.associationState = 1;
      this.xid = var4;
      this.performTimeoutRollback = false;
   }

   public void cleanupOnError(Throwable var1) {
      if (this.cleanupOrCancelMonitor.okToCleanup() && var1 instanceof StandardException var2) {
         if (var2.getSeverity() >= 40000) {
            this.popMe();
            return;
         }

         if (var2.getSeverity() == 30000) {
            synchronized(this) {
               this.notifyAll();
               this.associationState = -1;
               if ("40001".equals(var2.getMessageId())) {
                  this.rollbackOnlyCode = 102;
               } else if (var2.isLockTimeout()) {
                  this.rollbackOnlyCode = 106;
               } else {
                  this.rollbackOnlyCode = 104;
               }
            }
         }
      }

   }

   void start(EmbedXAResource var1, int var2) throws XAException {
      synchronized(this) {
         if (this.associationState == -1) {
            throw new XAException(this.rollbackOnlyCode);
         } else {
            boolean var4 = this.suspendedList != null && this.suspendedList.get(var1) != null;
            if (var2 == 134217728) {
               if (!var4) {
                  throw new XAException(-6);
               }
            } else if (var4) {
               throw new XAException(-6);
            }

            while(this.associationState == 1) {
               try {
                  this.wait();
               } catch (InterruptedException var7) {
                  throw new XAException(4);
               }
            }

            switch (this.associationState) {
               case -3:
               case -2:
               case -1:
                  throw new XAException(this.rollbackOnlyCode);
               case 0:
                  if (this.isPrepared) {
                     throw new XAException(-6);
                  }

                  if (var4) {
                     this.suspendedList.remove(var1);
                  }

                  this.associationState = 1;
                  this.associatedResource = var1;
                  return;
               default:
                  throw new XAException(-4);
            }
         }
      }
   }

   boolean end(EmbedXAResource var1, int var2, boolean var3) throws XAException {
      boolean var4 = false;
      synchronized(this) {
         boolean var6 = this.suspendedList != null && this.suspendedList.get(var1) != null;
         if (!var3) {
            while(this.associationState == 1) {
               try {
                  this.wait();
               } catch (InterruptedException var9) {
                  throw new XAException(4);
               }
            }
         }

         switch (this.associationState) {
            case -1:
               if (!var3) {
                  throw new XAException(this.rollbackOnlyCode);
               } else {
                  var2 = 536870912;
               }
            default:
               boolean var7 = false;
               switch (var2) {
                  case 33554432:
                     if (var6) {
                        throw new XAException(-6);
                     }

                     if (var1 != this.associatedResource) {
                        throw new XAException(-6);
                     }

                     if (this.suspendedList == null) {
                        this.suspendedList = new HashMap();
                     }

                     this.suspendedList.put(var1, this);
                     this.associationState = 0;
                     this.associatedResource = null;
                     this.conn.setApplicationConnection((Connection)null);
                     var7 = true;
                     break;
                  case 67108864:
                     if (var6) {
                        this.suspendedList.remove(var1);
                     } else {
                        if (var1 != this.associatedResource) {
                           throw new XAException(-6);
                        }

                        this.associationState = 0;
                        this.associatedResource = null;
                        var7 = true;
                     }

                     this.conn.setApplicationConnection((Connection)null);
                     break;
                  case 536870912:
                     if (var6) {
                        this.suspendedList.remove(var1);
                     } else {
                        if (var1 != this.associatedResource) {
                           throw new XAException(-6);
                        }

                        this.associatedResource = null;
                     }

                     if (this.associationState != -1) {
                        this.associationState = -1;
                        this.rollbackOnlyCode = 100;
                     }

                     this.conn.setApplicationConnection((Connection)null);
                     var7 = true;
                     var4 = true;
                     break;
                  default:
                     throw new XAException(-5);
               }

               if (var7) {
                  this.notifyAll();
               }

               return var4;
            case 3:
               throw new XAException(-4);
         }
      }
   }

   synchronized void scheduleTimeoutTask(long var1) {
      this.performTimeoutRollback = true;
      if (var1 > 0L) {
         this.timeoutTask = new CancelXATransactionTask(this);
         getTimerFactory().schedule(this.timeoutTask, var1);
      } else {
         this.timeoutTask = null;
      }

   }

   synchronized void xa_rollback() throws SQLException {
      this.conn.xa_rollback();
      this.xa_finalize();
   }

   synchronized void xa_commit(boolean var1) throws SQLException {
      this.conn.xa_commit(var1);
      this.xa_finalize();
   }

   synchronized int xa_prepare() throws SQLException {
      int var1;
      try {
         var1 = this.conn.xa_prepare();
      } catch (SQLException var3) {
         if (ExceptionUtil.isDeferredConstraintViolation(var3.getSQLState())) {
            this.xa_finalize();
         }

         throw var3;
      }

      if (var1 == 1) {
         this.xa_finalize();
      }

      return var1;
   }

   private void xa_finalize() {
      if (this.timeoutTask != null) {
         getTimerFactory().cancel(this.timeoutTask);
         this.timeoutTask = null;
      }

      this.performTimeoutRollback = false;
   }

   void cancel(String var1) throws XAException {
      if (this.cleanupOrCancelMonitor.okToCancel()) {
         synchronized(this) {
            this.creatingResource.removeXATransaction(this.xid);
            if (this.performTimeoutRollback) {
               if (var1 != null) {
                  Monitor.logTextMessage(var1, this.xid.toString());
               }

               if (this.associationState == 1) {
                  this.conn.cancelRunningStatement();
                  EmbedXAResource var3 = this.associatedResource;
                  this.end(var3, 536870912, true);
               }

               try {
                  this.conn.xa_rollback();
               } catch (SQLException var6) {
                  XAException var4 = new XAException(-3);
                  var4.initCause(var6);
                  throw var4;
               }

               this.creatingResource.returnConnectionToResource(this, this.xid);
            }
         }
      }

   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static class CleanupOrCancelMonitor {
      private Long cancelThreadId;
      private Long cleanupThreadId;

      public synchronized boolean okToCancel() {
         boolean var1 = false;
         if (null == this.cancelThreadId && null == this.cleanupThreadId) {
            this.cancelThreadId = Thread.currentThread().getId();
            var1 = true;
         }

         return var1;
      }

      private synchronized boolean okToCleanup() {
         boolean var1 = false;
         if (null == this.cleanupThreadId && null == this.cancelThreadId) {
            this.cleanupThreadId = Thread.currentThread().getId();
            var1 = true;
         }

         return var1;
      }
   }

   private static class CancelXATransactionTask extends TimerTask {
      private XATransactionState xaState;

      public CancelXATransactionTask(XATransactionState var1) {
         this.xaState = var1;
      }

      public synchronized boolean cancel() {
         this.xaState = null;
         return super.cancel();
      }

      public synchronized void run() {
         try {
            if (null != this.xaState) {
               this.xaState.cancel("J135");
            }
         } catch (Throwable var2) {
            Monitor.logThrowable(var2);
         }

      }
   }
}
