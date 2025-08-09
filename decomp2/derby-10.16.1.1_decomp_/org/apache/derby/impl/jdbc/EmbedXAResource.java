package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.apache.derby.iapi.jdbc.BrokeredConnection;
import org.apache.derby.iapi.jdbc.ResourceAdapter;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.xa.XAResourceManager;
import org.apache.derby.iapi.store.access.xa.XAXactId;
import org.apache.derby.shared.common.error.ExceptionUtil;
import org.apache.derby.shared.common.error.StandardException;

class EmbedXAResource implements XAResource {
   private EmbedPooledConnection con;
   private ResourceAdapter ra;
   private XAXactId currentXid;
   private int timeoutSeconds;
   private LanguageConnectionContext lcc;

   EmbedXAResource(EmbedPooledConnection var1, ResourceAdapter var2) {
      this.con = var1;
      this.ra = var2;
      this.timeoutSeconds = 0;
   }

   public final synchronized void commit(Xid var1, boolean var2) throws XAException {
      this.checkXAActive();
      XAXactId var3 = new XAXactId(var1);
      XATransactionState var4 = this.getTransactionState(var3);
      if (var4 == null) {
         XAResourceManager var5 = this.ra.getXAResourceManager();
         ContextManager var6 = var5.find(var1);
         if (var6 == null) {
            throw new XAException(-4);
         } else {
            ContextService var7 = getContextService();
            var7.setCurrentContextManager(var6);

            try {
               var5.commit(var6, var3, var2);
               var6.cleanupOnError(StandardException.closeException(), false);
            } catch (StandardException var22) {
               var6.cleanupOnError(var22, this.con.isActive());
               throw wrapInXAException(var22);
            } finally {
               var7.resetCurrentContextManager(var6);
            }

         }
      } else {
         synchronized(var4) {
            this.checkUserCredentials(var4.creatingResource);
            switch (var4.associationState) {
               case -1:
                  throw new XAException(var4.rollbackOnlyCode);
               case 0:
                  if (var4.suspendedList != null && var4.suspendedList.size() != 0) {
                     throw new XAException(-6);
                  } else if (var4.isPrepared == var2) {
                     throw new XAException(-6);
                  } else {
                     try {
                        var4.xa_commit(var2);
                     } catch (SQLException var24) {
                        throw wrapInXAException(var24);
                     } finally {
                        this.returnConnectionToResource(var4, var3);
                     }

                     return;
                  }
               default:
                  throw new XAException(-6);
            }
         }
      }
   }

   public final synchronized void end(Xid var1, int var2) throws XAException {
      this.checkXAActive();

      try {
         if (this.con.currentConnectionHandle != null) {
            this.con.currentConnectionHandle.getIsolationUptoDate();
         }
      } catch (SQLException var7) {
         throw wrapInXAException(var7);
      }

      XAXactId var3 = new XAXactId(var1);
      boolean var4 = false;
      if (this.currentXid != null) {
         if (!this.currentXid.equals(var3)) {
            throw new XAException(-6);
         }

         var4 = true;
      }

      XATransactionState var5 = this.getTransactionState(var3);
      if (var5 == null) {
         throw new XAException(-4);
      } else {
         boolean var6 = var5.end(this, var2, var4);
         if (var4) {
            this.currentXid = null;
            this.con.realConnection = null;
         }

         if (var6) {
            throw new XAException(var5.rollbackOnlyCode);
         }
      }
   }

   public final synchronized int prepare(Xid var1) throws XAException {
      this.checkXAActive();
      XAXactId var2 = new XAXactId(var1);
      XATransactionState var3 = this.getTransactionState(var2);
      if (var3 == null) {
         XAResourceManager var4 = this.ra.getXAResourceManager();
         ContextManager var10 = var4.find(var1);
         if (var10 == null) {
            throw new XAException(-4);
         } else {
            throw new XAException(-6);
         }
      } else {
         synchronized(var3) {
            this.checkUserCredentials(var3.creatingResource);
            switch (var3.associationState) {
               case -1:
                  throw new XAException(var3.rollbackOnlyCode);
               case 0:
                  if (var3.suspendedList != null && var3.suspendedList.size() != 0) {
                     throw new XAException(-6);
                  } else if (var3.isPrepared) {
                     throw new XAException(-6);
                  } else {
                     byte var10000;
                     try {
                        int var5 = var3.xa_prepare();
                        if (var5 == 2) {
                           var3.isPrepared = true;
                           var10000 = 0;
                           return var10000;
                        }

                        this.returnConnectionToResource(var3, var2);
                        var10000 = 3;
                     } catch (SQLException var8) {
                        XAException var6 = wrapInXAException(var8);
                        if (ExceptionUtil.isDeferredConstraintViolation(var8.getSQLState())) {
                           this.returnConnectionToResource(var3, var2);
                        }

                        throw var6;
                     }

                     return var10000;
                  }
               default:
                  throw new XAException(-6);
            }
         }
      }
   }

   public synchronized int getTransactionTimeout() {
      return this.timeoutSeconds;
   }

   public final synchronized boolean isSameRM(XAResource var1) throws XAException {
      this.checkXAActive();
      if (var1 instanceof EmbedXAResource) {
         return this.ra == ((EmbedXAResource)var1).ra;
      } else {
         return false;
      }
   }

   public final synchronized Xid[] recover(int var1) throws XAException {
      this.checkXAActive();

      try {
         return this.ra.getXAResourceManager().recover(var1);
      } catch (StandardException var3) {
         throw wrapInXAException(var3);
      }
   }

   public final synchronized void forget(Xid var1) throws XAException {
      this.checkXAActive();
      XAXactId var2 = new XAXactId(var1);
      XATransactionState var3 = this.getTransactionState(var2);
      if (var3 == null) {
         XAResourceManager var4 = this.ra.getXAResourceManager();
         ContextManager var5 = var4.find(var1);
         if (var5 == null) {
            throw new XAException(-4);
         } else {
            ContextService var6 = getContextService();
            var6.setCurrentContextManager(var5);

            try {
               var4.forget(var5, var2);
               var5.cleanupOnError(StandardException.closeException(), false);
            } catch (StandardException var11) {
               var5.cleanupOnError(var11, this.con.isActive());
               throw wrapInXAException(var11);
            } finally {
               var6.resetCurrentContextManager(var5);
            }

         }
      } else {
         throw new XAException(-6);
      }
   }

   public final synchronized void rollback(Xid var1) throws XAException {
      this.checkXAActive();
      XAXactId var2 = new XAXactId(var1);
      XATransactionState var3 = this.getTransactionState(var2);
      if (var3 == null) {
         XAResourceManager var4 = this.ra.getXAResourceManager();
         ContextManager var5 = var4.find(var1);
         if (var5 == null) {
            throw new XAException(-4);
         } else {
            ContextService var6 = getContextService();
            var6.setCurrentContextManager(var5);

            try {
               var4.rollback(var5, var2);
               var5.cleanupOnError(StandardException.closeException(), false);
            } catch (StandardException var21) {
               var5.cleanupOnError(var21, this.con.isActive());
               throw wrapInXAException(var21);
            } finally {
               var6.resetCurrentContextManager(var5);
            }

         }
      } else {
         synchronized(var3) {
            switch (var3.associationState) {
               case -1:
               case 0:
                  if (var3.suspendedList != null && var3.suspendedList.size() != 0) {
                     throw new XAException(-6);
                  } else {
                     this.checkUserCredentials(var3.creatingResource);

                     try {
                        var3.xa_rollback();
                     } catch (SQLException var23) {
                        throw wrapInXAException(var23);
                     } finally {
                        this.returnConnectionToResource(var3, var2);
                     }

                     return;
                  }
               default:
                  throw new XAException(-6);
            }
         }
      }
   }

   public synchronized boolean setTransactionTimeout(int var1) throws XAException {
      if (var1 < 0) {
         throw new XAException(-5);
      } else {
         this.timeoutSeconds = var1;
         return true;
      }
   }

   private long getDefaultXATransactionTimeout() throws XAException {
      try {
         LanguageConnectionContext var1 = this.getLanguageConnectionContext(this.con);
         TransactionController var2 = var1.getTransactionExecute();
         long var3 = 1000L * (long)PropertyUtil.getServiceInt(var2, "derby.jdbc.xaTransactionTimeout", 0, Integer.MAX_VALUE, 0);
         return var3;
      } catch (SQLException var5) {
         throw wrapInXAException(var5);
      } catch (StandardException var6) {
         throw wrapInXAException(var6);
      }
   }

   public final synchronized void start(Xid var1, int var2) throws XAException {
      this.checkXAActive();
      if (this.currentXid != null) {
         throw new XAException(-6);
      } else {
         XAXactId var3 = new XAXactId(var1);
         XATransactionState var4 = this.getTransactionState(var3);
         switch (var2) {
            case 0:
               if (var4 != null) {
                  throw new XAException(-8);
               }

               try {
                  if (this.con.realConnection == null) {
                     this.con.openRealConnection();
                     if (this.con.currentConnectionHandle != null) {
                        this.con.currentConnectionHandle.setState(true);
                        this.con.realConnection.setApplicationConnection(this.con.currentConnectionHandle);
                     }
                  } else {
                     if (this.con.currentConnectionHandle != null && this.con.currentConnectionHandle.getAutoCommit()) {
                        this.con.currentConnectionHandle.rollback();
                     }

                     if (!this.con.realConnection.transactionIsIdle()) {
                        throw new XAException(-9);
                     }

                     if (this.con.currentConnectionHandle != null) {
                        this.con.currentConnectionHandle.getIsolationUptoDate();
                        this.con.currentConnectionHandle.setState(true);
                        this.con.realConnection.rollback();
                     } else {
                        this.con.resetRealConnection();
                     }
                  }

                  this.con.realConnection.setAutoCommit(false);
                  this.con.realConnection.setHoldability(2);
                  this.getLanguageConnectionContext(this.con.realConnection).getTransactionExecute().createXATransactionFromLocalTransaction(var3.getFormatId(), var3.getGlobalTransactionId(), var3.getBranchQualifier());
               } catch (StandardException var8) {
                  throw wrapInXAException(var8);
               } catch (SQLException var9) {
                  throw wrapInXAException(var9);
               }

               var4 = new XATransactionState(getContextManager(this.con.realConnection), this.con.realConnection, this, var3);
               if (!this.ra.addConnection(var3, var4)) {
                  throw new XAException(-8);
               }

               this.currentXid = var3;
               if (this.timeoutSeconds != Integer.MAX_VALUE) {
                  long var5;
                  if (this.timeoutSeconds > 0) {
                     var5 = (long)(1000 * this.timeoutSeconds);
                  } else {
                     var5 = this.getDefaultXATransactionTimeout();
                  }

                  if (var5 > 0L) {
                     var4.scheduleTimeoutTask(var5);
                  }
               }
               break;
            case 2097152:
            case 134217728:
               if (var4 == null) {
                  throw new XAException(-4);
               }

               var4.start(this, var2);
               if (var4.conn != this.con.realConnection) {
                  if (this.con.realConnection != null) {
                     if (!this.con.realConnection.transactionIsIdle()) {
                        throw new XAException(-9);
                     }

                     try {
                        if (this.con.currentConnectionHandle != null) {
                           this.con.currentConnectionHandle.getIsolationUptoDate();
                        }
                     } catch (SQLException var10) {
                        throw wrapInXAException(var10);
                     }

                     closeUnusedConnection(this.con.realConnection);
                  }

                  this.con.realConnection = var4.conn;
                  if (this.con.currentConnectionHandle != null) {
                     try {
                        this.con.currentConnectionHandle.setState(false);
                        this.con.realConnection.setApplicationConnection(this.con.currentConnectionHandle);
                     } catch (SQLException var7) {
                        throw wrapInXAException(var7);
                     }
                  }
               }
               break;
            default:
               throw new XAException(-5);
         }

         this.currentXid = var3;
      }
   }

   Xid getCurrentXid() {
      return this.currentXid;
   }

   private XATransactionState getTransactionState(XAXactId var1) {
      return (XATransactionState)this.ra.findConnection(var1);
   }

   private void checkUserCredentials(EmbedXAResource var1) throws XAException {
      if (var1 != this) {
         if (!var1.con.getPassword().equals(this.con.getPassword()) || !var1.con.getUsername().equals(this.con.getUsername())) {
            throw new XAException(103);
         }
      }
   }

   private void checkXAActive() throws XAException {
      try {
         this.con.checkActive();
      } catch (SQLException var2) {
         throw wrapInXAException(var2);
      }
   }

   private static XAException wrapInXAException(SQLException var0) {
      String var1 = var0.getSQLState();
      String var2 = var0.getMessage();
      int var3 = var0.getErrorCode();
      byte var4;
      if (var1.equals(StandardException.getSQLStateFromIdentifier("XSAX1.S"))) {
         var4 = -8;
      } else if (var1.equals(StandardException.getSQLStateFromIdentifier("XSAX0.S"))) {
         var4 = 105;
      } else if (var1.equals("40001")) {
         var4 = 102;
      } else if (var1.equals("40XL1")) {
         var4 = 106;
      } else if (var3 >= 40000) {
         var4 = -7;
      } else if (ExceptionUtil.isDeferredConstraintViolation(var1)) {
         var4 = 103;
      } else {
         var4 = -3;
      }

      XAException var5 = new XAException(var2);
      var5.errorCode = var4;
      var5.initCause(var0);
      return var5;
   }

   private static XAException wrapInXAException(StandardException var0) {
      return wrapInXAException(TransactionResourceImpl.wrapInSQLException(var0));
   }

   void returnConnectionToResource(XATransactionState var1, XAXactId var2) {
      this.removeXATransaction(var2);
      synchronized(var1) {
         var1.associationState = 3;
         var1.notifyAll();
         EmbedConnection var4 = var1.conn;
         if (var1.creatingResource.con.realConnection == var4 || var1.creatingResource.con.realConnection == null) {
            var1.creatingResource.con.realConnection = var4;
            BrokeredConnection var5 = var1.creatingResource.con.currentConnectionHandle;
            var4.setApplicationConnection(var5);
            if (var5 != null) {
               try {
                  var5.setState(true);
               } catch (SQLException var8) {
                  closeUnusedConnection(var1.conn);
                  var1.creatingResource.con.realConnection = null;
               }
            }

            return;
         }
      }

      closeUnusedConnection(var1.conn);
   }

   private static void closeUnusedConnection(EmbedConnection var0) {
      if (var0 != null) {
         try {
            var0.close();
         } catch (SQLException var2) {
         }
      }

   }

   void removeXATransaction(XAXactId var1) {
      XATransactionState var2 = (XATransactionState)this.ra.removeConnection(var1);
      if (var2 != null) {
         var2.popMe();
      }

   }

   void setCurrentXid(XAXactId var1) {
      this.currentXid = var1;
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static ContextManager getContextManager(EmbedConnection var0) {
      return var0.getContextManager();
   }

   private LanguageConnectionContext getLanguageConnectionContext(EmbedConnection var1) {
      return var1.getLanguageConnection();
   }

   private LanguageConnectionContext getLanguageConnectionContext(EmbedPooledConnection var1) throws SQLException {
      if (this.lcc == null) {
         try {
            this.lcc = var1.getLanguageConnection();
         } catch (Exception var3) {
            throw Util.javaException(var3);
         }
      }

      return this.lcc;
   }
}
