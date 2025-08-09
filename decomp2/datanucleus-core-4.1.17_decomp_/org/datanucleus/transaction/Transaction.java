package org.datanucleus.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.transaction.Synchronization;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.datanucleus.NucleusContextHelper;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.omg.CORBA.SystemException;

public class Transaction {
   public static final int STATUS_ACTIVE = 0;
   public static final int STATUS_MARKED_ROLLBACK = 1;
   public static final int STATUS_PREPARED = 2;
   public static final int STATUS_COMMITTED = 3;
   public static final int STATUS_ROLLEDBACK = 4;
   public static final int STATUS_UNKNOWN = 5;
   public static final int STATUS_NO_TRANSACTION = 6;
   public static final int STATUS_PREPARING = 7;
   public static final int STATUS_COMMITTING = 8;
   public static final int STATUS_ROLLING_BACK = 9;
   private static final int NODE_ID;
   private static int NEXT_GLOBAL_TRANSACTION_ID;
   private int nextBranchId = 1;
   private final Xid xid;
   private int status;
   private boolean completing = false;
   private List synchronization = null;
   private List enlistedResources = new ArrayList();
   private Map branches = new HashMap();
   private Map activeBranches = new HashMap();
   private Map suspendedResources = new HashMap();

   Transaction() {
      this.xid = new XidImpl(NODE_ID, 0, NEXT_GLOBAL_TRANSACTION_ID++);
      if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
         NucleusLogger.TRANSACTION.debug("Transaction created " + this.toString());
      }

   }

   public int getStatus() throws SystemException {
      return this.status;
   }

   public boolean isEnlisted(XAResource xaRes) {
      if (xaRes == null) {
         return false;
      } else {
         Xid activeXid = (Xid)this.activeBranches.get(xaRes);
         if (activeXid != null) {
            return true;
         } else {
            Xid branchXid = (Xid)this.suspendedResources.get(xaRes);
            if (branchXid != null) {
               return true;
            } else {
               for(XAResource resourceManager : this.enlistedResources) {
                  try {
                     if (resourceManager.isSameRM(xaRes)) {
                        return true;
                     }
                  } catch (XAException var7) {
                  }
               }

               return false;
            }
         }
      }
   }

   public boolean enlistResource(XAResource xaRes) throws RollbackException, IllegalStateException, SystemException {
      if (xaRes == null) {
         return false;
      } else if (this.status == 1) {
         throw new RollbackException();
      } else if (this.status != 0) {
         throw new IllegalStateException();
      } else {
         Xid activeXid = (Xid)this.activeBranches.get(xaRes);
         if (activeXid != null) {
            return false;
         } else {
            boolean alreadyEnlisted = false;
            int flag = 0;
            Xid branchXid = (Xid)this.suspendedResources.get(xaRes);
            if (branchXid == null) {
               Iterator<XAResource> enlistedIterator = this.enlistedResources.iterator();

               while(!alreadyEnlisted && enlistedIterator.hasNext()) {
                  XAResource resourceManager = (XAResource)enlistedIterator.next();

                  try {
                     if (resourceManager.isSameRM(xaRes)) {
                        flag = 2097152;
                        alreadyEnlisted = true;
                     }
                  } catch (XAException var10) {
                  }
               }

               branchXid = new XidImpl(this.nextBranchId++, this.xid.getFormatId(), this.xid.getGlobalTransactionId());
            } else {
               alreadyEnlisted = true;
               flag = 134217728;
               this.suspendedResources.remove(xaRes);
            }

            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(Localiser.msg("015039", "enlist", xaRes, getXAFlag(flag), this.toString()));
            }

            try {
               xaRes.start(branchXid, flag);
            } catch (XAException e) {
               NucleusLogger.TRANSACTION.error(Localiser.msg("015038", "enlist", xaRes, getXAErrorCode(e), this.toString()));
               return false;
            }

            if (!alreadyEnlisted) {
               this.enlistedResources.add(xaRes);
            }

            this.branches.put(branchXid, xaRes);
            this.activeBranches.put(xaRes, branchXid);
            return true;
         }
      }
   }

   public boolean delistResource(XAResource xaRes, int flag) throws IllegalStateException, SystemException {
      if (xaRes == null) {
         return false;
      } else if (this.status != 0) {
         throw new IllegalStateException();
      } else {
         Xid xid = (Xid)this.activeBranches.get(xaRes);
         if (xid == null) {
            throw new IllegalStateException();
         } else {
            this.activeBranches.remove(xaRes);
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(Localiser.msg("015039", "delist", xaRes, getXAFlag(flag), this.toString()));
            }

            XAException exception = null;

            try {
               xaRes.end(xid, flag);
            } catch (XAException e) {
               exception = e;
            }

            if (exception != null) {
               NucleusLogger.TRANSACTION.error(Localiser.msg("015038", "delist", xaRes, getXAErrorCode(exception), this.toString()));
               return false;
            } else {
               if (flag == 33554432) {
                  this.suspendedResources.put(xaRes, xid);
               }

               return true;
            }
         }
      }
   }

   public void registerSynchronization(Synchronization sync) throws RollbackException, IllegalStateException, SystemException {
      if (sync != null) {
         if (this.status == 1) {
            throw new RollbackException();
         } else if (this.status != 0) {
            throw new IllegalStateException();
         } else {
            if (this.synchronization == null) {
               this.synchronization = new ArrayList();
            }

            this.synchronization.add(sync);
         }
      }
   }

   public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
      if (!this.completing) {
         if (this.status == 1) {
            this.rollback();
         } else {
            try {
               this.completing = true;
               if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
                  NucleusLogger.TRANSACTION.debug("Committing " + this.toString());
               }

               if (this.status != 0) {
                  throw new IllegalStateException();
               }

               if (this.synchronization != null) {
                  Iterator<Synchronization> syncIterator = this.synchronization.iterator();

                  while(syncIterator.hasNext()) {
                     ((Synchronization)syncIterator.next()).beforeCompletion();
                  }
               }

               List failures = null;
               boolean failed = false;
               Iterator<Xid> branchKeys = this.branches.keySet().iterator();
               if (this.enlistedResources.size() == 1) {
                  this.status = 8;

                  while(branchKeys.hasNext()) {
                     Xid key = (Xid)branchKeys.next();
                     XAResource resourceManager = (XAResource)this.branches.get(key);

                     try {
                        if (!failed) {
                           resourceManager.commit(key, true);
                        } else {
                           resourceManager.rollback(key);
                        }
                     } catch (Throwable e) {
                        if (failures == null) {
                           failures = new ArrayList();
                        }

                        failures.add(e);
                        failed = true;
                        this.status = 1;
                        NucleusLogger.TRANSACTION.error(Localiser.msg("015038", "commit", resourceManager, getXAErrorCode(e), this.toString()));
                     }
                  }

                  if (!failed) {
                     this.status = 3;
                  } else {
                     this.status = 4;
                  }
               } else if (this.enlistedResources.size() > 0) {
                  this.status = 7;

                  while(!failed && branchKeys.hasNext()) {
                     Xid key = (Xid)branchKeys.next();
                     XAResource resourceManager = (XAResource)this.branches.get(key);

                     try {
                        resourceManager.prepare(key);
                     } catch (Throwable e) {
                        if (failures == null) {
                           failures = new ArrayList();
                        }

                        failures.add(e);
                        failed = true;
                        this.status = 1;
                        NucleusLogger.TRANSACTION.error(Localiser.msg("015038", "prepare", resourceManager, getXAErrorCode(e), this.toString()));
                     }
                  }

                  if (!failed) {
                     this.status = 2;
                  }

                  if (failed) {
                     this.status = 9;
                     failed = false;

                     for(Xid key : this.branches.keySet()) {
                        XAResource resourceManager = (XAResource)this.branches.get(key);

                        try {
                           resourceManager.rollback(key);
                        } catch (Throwable e) {
                           NucleusLogger.TRANSACTION.error(Localiser.msg("015038", "rollback", resourceManager, getXAErrorCode(e), this.toString()));
                           if (failures == null) {
                              failures = new ArrayList();
                           }

                           failures.add(e);
                           failed = true;
                        }
                     }

                     this.status = 4;
                  } else {
                     this.status = 8;

                     for(Xid key : this.branches.keySet()) {
                        XAResource resourceManager = (XAResource)this.branches.get(key);

                        try {
                           resourceManager.commit(key, false);
                        } catch (Throwable e) {
                           NucleusLogger.TRANSACTION.error(Localiser.msg("015038", "commit", resourceManager, getXAErrorCode(e), this.toString()));
                           if (failures == null) {
                              failures = new ArrayList();
                           }

                           failures.add(e);
                           failed = true;
                        }
                     }

                     this.status = 3;
                  }
               }

               if (this.synchronization != null) {
                  Iterator<Synchronization> syncIterator = this.synchronization.iterator();

                  while(syncIterator.hasNext()) {
                     ((Synchronization)syncIterator.next()).afterCompletion(this.status);
                  }
               }

               if (this.status == 4) {
                  if (failed) {
                     if (failures.size() == 1) {
                        throw new HeuristicRollbackException("Transaction rolled back due to failure during commit", (Throwable)failures.get(0));
                     }

                     throw new HeuristicRollbackException("Multiple failures");
                  }

                  throw new RollbackException();
               }

               if (this.status == 3 && failed) {
                  throw new HeuristicMixedException();
               }
            } finally {
               this.completing = false;
            }

         }
      }
   }

   public void rollback() throws IllegalStateException, SystemException {
      if (!this.completing) {
         try {
            this.completing = true;
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug("Rolling back " + this.toString());
            }

            if (this.status != 0 && this.status != 1) {
               throw new IllegalStateException();
            }

            List failures = null;
            Iterator<Xid> branchKeys = this.branches.keySet().iterator();
            this.status = 9;

            while(branchKeys.hasNext()) {
               Xid xid = (Xid)branchKeys.next();
               XAResource resourceManager = (XAResource)this.branches.get(xid);

               try {
                  resourceManager.rollback(xid);
               } catch (Throwable e) {
                  if (failures == null) {
                     failures = new ArrayList();
                  }

                  failures.add(e);
                  NucleusLogger.TRANSACTION.error(Localiser.msg("015038", "rollback", resourceManager, getXAErrorCode(e), this.toString()));
               }
            }

            this.status = 4;
            if (this.synchronization != null) {
               Iterator<Synchronization> syncIterator = this.synchronization.iterator();

               while(syncIterator.hasNext()) {
                  ((Synchronization)syncIterator.next()).afterCompletion(this.status);
               }
            }
         } finally {
            this.completing = false;
         }

      }
   }

   public void setRollbackOnly() throws IllegalStateException, SystemException {
      this.status = 1;
   }

   public static String getXAErrorCode(Throwable xae) {
      if (!(xae instanceof XAException)) {
         return "UNKNOWN";
      } else {
         switch (((XAException)xae).errorCode) {
            case -9:
               return "XAER_OUTSIDE";
            case -8:
               return "XAER_DUPID";
            case -7:
               return "XAER_RMFAIL";
            case -6:
               return "XAER_PROTO";
            case -5:
               return "XAER_INVAL";
            case -4:
               return "XAER_NOTA";
            case -3:
               return "XAER_RMERR";
            case -2:
               return "XAER_ASYNC";
            case 3:
               return "XA_RDONLY";
            case 4:
               return "XA_RETRY";
            case 5:
               return "XA_HEURMIX";
            case 6:
               return "XA_HEURRB";
            case 7:
               return "XA_HEURCOM";
            case 8:
               return "XA_HEURHAZ";
            case 9:
               return "XA_NOMIGRATE";
            case 100:
               return "XA_RBBASE";
            case 101:
               return "XA_RBCOMMFAIL";
            case 102:
               return "XA_RBBEADLOCK";
            case 103:
               return "XA_RBINTEGRITY";
            case 104:
               return "XA_RBOTHER";
            case 105:
               return "XA_RBPROTO";
            case 106:
               return "XA_RBTIMEOUT";
            case 107:
               return "XA_RBEND";
            default:
               return "UNKNOWN";
         }
      }
   }

   private static String getXAFlag(int flag) {
      switch (flag) {
         case 0:
            return "TMNOFLAGS";
         case 2097152:
            return "TMJOIN";
         case 8388608:
            return "TMENDRSCAN";
         case 16777216:
            return "TMSTARTRSCAN";
         case 33554432:
            return "TMSUSPEND";
         case 67108864:
            return "TMSUCCESS";
         case 134217728:
            return "TMRESUME";
         case 536870912:
            return "TMFAIL";
         case 1073741824:
            return "TMONEPHASE";
         default:
            return "UNKNOWN";
      }
   }

   public String toString() {
      String resString = null;
      synchronized(this.enlistedResources) {
         resString = this.enlistedResources.toString();
      }

      return "[DataNucleus Transaction, ID=" + this.xid.toString() + ", enlisted resources=" + resString + "]";
   }

   static {
      NODE_ID = NucleusContextHelper.random.nextInt();
      NEXT_GLOBAL_TRANSACTION_ID = 1;
   }
}
