package org.apache.derby.impl.store.raw.xact;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Stack;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Limit;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.raw.Compensation;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.StreamContainerHandle;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.log.Logger;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public class Xact extends RawTransaction implements Limit, LockOwner {
   protected static final int CLOSED = 0;
   protected static final int IDLE = 1;
   protected static final int ACTIVE = 2;
   protected static final int UPDATE = 3;
   protected static final int PREPARED = 4;
   public static final int END_ABORTED = 1;
   public static final int END_PREPARED = 2;
   public static final int END_COMMITTED = 4;
   public static final int RECOVERY_ROLLBACK_FIRST = 16;
   public static final int INTERNAL_TRANSACTION = 32;
   public static final int NESTED_TOP_TRANSACTION = 64;
   private static final int COMMIT_SYNC = 65536;
   private static final int COMMIT_NO_SYNC = 131072;
   private static final int COMMIT_PREPARE = 262144;
   private int savedEndStatus;
   private boolean needSync;
   private boolean justCreated = true;
   protected XactContext xc;
   protected final XactFactory xactFactory;
   protected final DataFactory dataFactory;
   protected final LogFactory logFactory;
   protected final DataValueFactory dataValueFactory;
   private final CompatibilitySpace compatibilitySpace;
   private LockingPolicy defaultLocking;
   private GlobalTransactionId myGlobalId;
   private volatile TransactionId myId;
   private volatile TransactionId parentTransactionId;
   protected Logger logger;
   protected volatile int state;
   private Integer inComplete = null;
   private boolean seenUpdates;
   private boolean inPostCommitProcessing;
   private LogInstant logStart;
   private LogInstant logLast;
   private Stack savePoints;
   protected List postCommitWorks;
   protected List postAbortWorks;
   protected List postTerminationWorks;
   private boolean recoveryTransaction;
   DynamicByteArrayOutputStream logBuffer;
   private boolean postCompleteMode;
   private boolean sanityCheck_xaclosed;
   private String transName;
   private boolean readOnly;
   private boolean flush_log_on_xact_end;
   private boolean backupBlocked;
   private boolean dontWaitForLocks;

   protected Xact(XactFactory var1, Xact var2, LogFactory var3, DataFactory var4, DataValueFactory var5, boolean var6, CompatibilitySpace var7, boolean var8) {
      this.xactFactory = var1;
      if (var2 != null) {
         this.parentTransactionId = var2.getId();
      }

      this.logFactory = var3;
      this.dataFactory = var4;
      this.dataValueFactory = var5;
      this.readOnly = var6;
      this.flush_log_on_xact_end = var8;
      if (var7 == null) {
         this.compatibilitySpace = this.getLockFactory().createCompatibilitySpace(this);
      } else {
         this.compatibilitySpace = var7;
      }

      this.resetDefaultLocking();
      var1.setNewTransactionId((XactId)null, this);
      this.setIdleState();
      this.backupBlocked = false;
   }

   public final LockFactory getLockFactory() {
      return this.xactFactory.getLockFactory();
   }

   public final DataFactory getDataFactory() {
      return this.dataFactory;
   }

   public final LogFactory getLogFactory() {
      return this.logFactory;
   }

   public boolean anyoneBlocked() {
      return this.getLockFactory().anyoneBlocked();
   }

   public DynamicByteArrayOutputStream getLogBuffer() {
      if (this.logBuffer == null) {
         this.logBuffer = new DynamicByteArrayOutputStream(1024);
      } else {
         this.logBuffer.reset();
      }

      return this.logBuffer;
   }

   public void logAndUndo(Compensation var1, LogInstant var2, LimitObjectInput var3) throws StandardException {
      this.setActiveState();
      if (this.state == 2) {
         this.setUpdateState();
      }

      this.seenUpdates = true;
      LogInstant var4 = this.logger.logAndUndo(this, var1, var2, var3);
      this.setLastLogInstant(var4);
      if (this.savePoints != null && !this.savePoints.empty()) {
         SavePoint var5 = (SavePoint)this.savePoints.peek();
         if (var5.getSavePoint() == null) {
            var5.setSavePoint(var4);
         }
      }

   }

   public void addUpdateTransaction(int var1) {
      if (this.myId != null) {
         this.xactFactory.addUpdateTransaction(this.myId, this, var1);
      }

   }

   public void removeUpdateTransaction() {
      if (this.myId != null) {
         this.xactFactory.removeUpdateTransaction(this.myId);
      }

   }

   public void prepareTransaction() {
      if (this.myId != null) {
         this.xactFactory.prepareTransaction(this.myId);
      }

   }

   public void setFirstLogInstant(LogInstant var1) {
      this.logStart = var1;
   }

   public LogInstant getFirstLogInstant() {
      return this.logStart;
   }

   public void setLastLogInstant(LogInstant var1) {
      this.logLast = var1;
   }

   public LogInstant getLastLogInstant() {
      return this.logLast;
   }

   void setTransactionId(GlobalTransactionId var1, TransactionId var2) {
      this.myGlobalId = var1;
      this.myId = var2;
   }

   public void setTransactionId(Loggable var1, TransactionId var2) {
      this.myId = var2;
      this.myGlobalId = ((BeginXact)var1).getGlobalId();
   }

   public void setup(PersistentSet var1) throws StandardException {
      int var2 = PropertyUtil.getServiceInt(var1, "derby.locks.escalationThreshold", 100, Integer.MAX_VALUE, 5000);
      this.getLockFactory().setLimit(this.compatibilitySpace, this, var2, this);
   }

   public final GlobalTransactionId getGlobalId() {
      return this.myGlobalId;
   }

   public final ContextManager getContextManager() {
      return this.xc.getContextManager();
   }

   public final CompatibilitySpace getCompatibilitySpace() {
      return this.compatibilitySpace;
   }

   public boolean noWait() {
      return this.dontWaitForLocks;
   }

   public void setNoLockWait(boolean var1) {
      this.dontWaitForLocks = var1;
   }

   public final TransactionId getId() {
      return this.myId;
   }

   protected final TransactionId getIdNoCheck() {
      return this.myId;
   }

   public final String getContextId() {
      XactContext var1 = this.xc;
      return var1 == null ? null : var1.getIdName();
   }

   public LockingPolicy getDefaultLockingPolicy() {
      return this.defaultLocking;
   }

   public final LockingPolicy newLockingPolicy(int var1, int var2, boolean var3) {
      return this.xactFactory.getLockingPolicy(var1, var2, var3);
   }

   public final void setDefaultLockingPolicy(LockingPolicy var1) {
      if (var1 == null) {
         var1 = this.xactFactory.getLockingPolicy(0, 0, false);
      }

      this.defaultLocking = var1;
   }

   public LogInstant commit() throws StandardException {
      return this.commit(65536);
   }

   public LogInstant commitNoSync(int var1) throws StandardException {
      return this.state == 1 && this.savePoints == null && (var1 & 4) != 0 ? null : this.commit(131072 | var1);
   }

   private LogInstant prepareCommit(int var1) throws StandardException {
      LogInstant var2 = null;
      if (this.state == 0) {
         throw StandardException.newException("40XT8", new Object[]{this.toInternalDetailString()});
      } else {
         try {
            this.preComplete(COMMIT);
            if (this.seenUpdates) {
               EndXact var3 = new EndXact(this.getGlobalId(), ((var1 & 262144) == 0 ? 4 : 2) | this.statusForEndXactLog());
               var2 = this.logger.logAndDo(this, var3);
               if (this.flush_log_on_xact_end) {
                  if ((var1 & 65536) == 0) {
                     this.needSync = true;
                  } else {
                     this.logger.flush(var2);
                     this.needSync = false;
                  }
               }
            } else if (this.needSync && (var1 & 65536) != 0) {
               this.logger.flushAll();
               this.needSync = false;
            }

            return var2;
         } catch (StandardException var4) {
            if (var4.getSeverity() < 30000) {
               throw StandardException.newException("40XT1", var4, new Object[0]);
            } else {
               throw var4;
            }
         }
      }
   }

   private void completeCommit(int var1) throws StandardException {
      this.postComplete(var1, COMMIT);
      if ((var1 & 2) == 0) {
         this.postTermination();
      } else {
         this.setActiveState();
      }

      this.myGlobalId = null;
   }

   private LogInstant commit(int var1) throws StandardException {
      LogInstant var2 = this.prepareCommit(var1);
      this.completeCommit(var1);
      return var2;
   }

   public void abort() throws StandardException {
      if (this.state != 0) {
         try {
            this.preComplete(ABORT);
            if (this.getFirstLogInstant() != null) {
               if (this.logger == null) {
                  throw StandardException.newException("XSTB3.M", new Object[0]);
               }

               this.logger.undo(this, this.getId(), this.getFirstLogInstant(), this.getLastLogInstant());
               EndXact var1 = new EndXact(this.getGlobalId(), 1 | this.statusForEndXactLog());
               this.logger.flush(this.logger.logAndDo(this, var1));
            } else if (this.needSync) {
               this.logger.flushAll();
            }

            this.needSync = false;
         } catch (StandardException var2) {
            if (var2.getSeverity() < 50000) {
               throw this.logFactory.markCorrupt(StandardException.newException("XSTB0.M", var2, new Object[0]));
            }

            throw var2;
         }

         this.postComplete(0, ABORT);
         if (this.postCommitWorks != null && !this.postCommitWorks.isEmpty()) {
            this.postCommitWorks.clear();
         }

         this.postTermination();
         this.myGlobalId = null;
      }
   }

   public void reprepare() throws StandardException {
      if (this.state == 0) {
         throw StandardException.newException("40XT8", new Object[]{this.toInternalDetailString()});
      } else {
         try {
            if (this.logger == null) {
               throw StandardException.newException("XSTB3.M", new Object[0]);
            } else {
               this.state = 3;
               this.logger.reprepare(this, this.getId(), this.getFirstLogInstant(), this.getLastLogInstant());
               this.state = 4;
               this.seenUpdates = true;
            }
         } catch (StandardException var2) {
            if (var2.getSeverity() < 50000) {
               throw this.logFactory.markCorrupt(StandardException.newException("XSTB0.M", var2, new Object[0]));
            } else {
               throw var2;
            }
         }
      }
   }

   public void destroy() throws StandardException {
      if (this.state != 0) {
         this.abort();
      }

      this.close();
   }

   public void close() throws StandardException {
      switch (this.state) {
         case 0:
            return;
         case 1:
            this.getLockFactory().clearLimit(this.compatibilitySpace, this);
            if (this.myId != null) {
               this.xactFactory.remove((XactId)this.myId);
            }

            this.xc.popMe();
            this.xc = null;
            this.myGlobalId = null;
            this.myId = null;
            this.logStart = null;
            this.logLast = null;
            this.state = 0;
            return;
         default:
            throw StandardException.newException("40XT4", new Object[0]);
      }
   }

   public void logAndDo(Loggable var1) throws StandardException {
      LogInstant var2 = null;
      if (this.logger == null) {
         this.getLogger();
      }

      if (this.logger == null) {
         throw StandardException.newException("XSTB2.M", new Object[0]);
      } else {
         this.setActiveState();
         if (this.state == 2) {
            var2 = this.logger.logAndDo(this, new BeginXact(this.getGlobalId(), this.statusForBeginXactLog()));
            this.setUpdateState();
         }

         this.seenUpdates = true;
         if (var1 != null) {
            var2 = this.logger.logAndDo(this, var1);
            if (var2 != null) {
               this.setLastLogInstant(var2);
               if (this.savePoints != null && !this.savePoints.empty()) {
                  for(int var3 = this.savePoints.size() - 1; var3 >= 0; --var3) {
                     SavePoint var4 = (SavePoint)this.savePoints.elementAt(var3);
                     if (var4.getSavePoint() != null) {
                        break;
                     }

                     var4.setSavePoint(var2);
                  }
               }
            }
         } else if (var2 != null) {
            this.setLastLogInstant(var2);
         }

      }
   }

   public void addPostCommitWork(Serviceable var1) {
      if (!this.recoveryTransaction) {
         if (this.postCommitWorks == null) {
            this.postCommitWorks = new ArrayList(1);
         }

         this.postCommitWorks.add(var1);
      }
   }

   public void addPostAbortWork(Serviceable var1) {
      if (!this.recoveryTransaction) {
         if (this.postAbortWorks == null) {
            this.postAbortWorks = new ArrayList(1);
         }

         this.postAbortWorks.add(var1);
      }
   }

   public void addPostTerminationWork(Serviceable var1) {
      if (!this.recoveryTransaction) {
         if (this.postTerminationWorks == null) {
            this.postTerminationWorks = new ArrayList(2);
         }

         this.postTerminationWorks.add(var1);
      }
   }

   public ContainerHandle openContainer(ContainerKey var1, int var2) throws StandardException {
      return this.openContainer(var1, this.defaultLockingPolicy(), var2);
   }

   public ContainerHandle openContainer(ContainerKey var1, LockingPolicy var2, int var3) throws StandardException {
      this.setActiveState();
      if (var2 == null) {
         var2 = this.xactFactory.getLockingPolicy(0, 0, false);
      }

      return this.dataFactory.openContainer(this, var1, var2, var3);
   }

   public RawContainerHandle openDroppedContainer(ContainerKey var1, LockingPolicy var2) throws StandardException {
      this.setActiveState();
      if (var2 == null) {
         var2 = this.xactFactory.getLockingPolicy(0, 0, false);
      }

      RawContainerHandle var3 = null;

      try {
         var3 = this.dataFactory.openDroppedContainer(this, var1, var2, 4);
      } catch (StandardException var5) {
         var3 = this.dataFactory.openDroppedContainer(this, var1, var2, 8);
      }

      return var3;
   }

   public long addContainer(long var1, long var3, int var5, Properties var6, int var7) throws StandardException {
      this.setActiveState();
      return this.dataFactory.addContainer(this, var1, var3, var5, var6, var7);
   }

   public long addAndLoadStreamContainer(long var1, Properties var3, RowSource var4) throws StandardException {
      this.setActiveState();
      return this.dataFactory.addAndLoadStreamContainer(this, var1, var3, var4);
   }

   public StreamContainerHandle openStreamContainer(long var1, long var3, boolean var5) throws StandardException {
      this.setActiveState();
      return this.dataFactory.openStreamContainer(this, var1, var3, var5);
   }

   public void dropStreamContainer(long var1, long var3) throws StandardException {
      this.setActiveState();
      this.dataFactory.dropStreamContainer(this, var1, var3);
   }

   public void reCreateContainerForRedoRecovery(long var1, long var3, ByteArray var5) throws StandardException {
      this.setActiveState();
      this.dataFactory.reCreateContainerForRedoRecovery(this, var1, var3, var5);
   }

   public void dropContainer(ContainerKey var1) throws StandardException {
      this.setActiveState();
      this.dataFactory.dropContainer(this, var1);
   }

   public int setSavePoint(String var1, Object var2) throws StandardException {
      if (var2 != null && var2 instanceof String) {
         this.throwExceptionIfSQLSavepointNotAllowed(var2);
      }

      if (this.getSavePointPosition(var1, var2, false) != -1) {
         throw StandardException.newException("3B501.S", new Object[0]);
      } else {
         if (this.savePoints == null) {
            this.savePoints = new Stack();
         }

         this.savePoints.push(new SavePoint(var1, var2));
         return this.savePoints.size();
      }
   }

   private void throwExceptionIfSQLSavepointNotAllowed(Object var1) throws StandardException {
      boolean var2 = false;
      if (this.savePoints != null && !this.savePoints.empty()) {
         for(int var3 = this.savePoints.size() - 1; var3 >= 0; --var3) {
            SavePoint var4 = (SavePoint)this.savePoints.elementAt(var3);
            if (var4.isThisUserDefinedsavepoint()) {
               var2 = true;
               break;
            }
         }
      }

      if (var2) {
         throw StandardException.newException("3B002.S", new Object[0]);
      }
   }

   public int releaseSavePoint(String var1, Object var2) throws StandardException {
      int var3 = this.getSavePointPosition(var1, var2, true);
      if (var3 == -1) {
         if (var2 != null && !(var2 instanceof String)) {
            var1 = var1.substring(2);
         }

         throw StandardException.newException("3B001.S", new Object[]{var1});
      } else {
         this.popSavePoints(var3, true);
         return this.savePoints.size();
      }
   }

   public int rollbackToSavePoint(String var1, Object var2) throws StandardException {
      int var3 = this.getSavePointPosition(var1, var2, true);
      if (var3 == -1) {
         if (var2 != null && !(var2 instanceof String)) {
            var1 = var1.substring(2);
         }

         throw StandardException.newException("3B001.S", new Object[]{var1});
      } else {
         this.notifyObservers(SAVEPOINT_ROLLBACK);
         this.popSavePoints(var3, false);
         return this.savePoints.size();
      }
   }

   private void getLogger() {
      this.logger = this.logFactory.getLogger();
   }

   protected void assumeIdentity(TransactionTableEntry var1) {
      if (var1 != null) {
         var1.setXact(this);
         this.myId = var1.getXid();
         this.logStart = var1.getFirstLog();
         this.logLast = var1.getLastLog();
         this.myGlobalId = null;
         if (this.state == 1) {
            this.state = 2;
         }

         if (this.logger == null) {
            this.getLogger();
         }

         this.savedEndStatus = 0;
      } else {
         this.myGlobalId = null;
         this.myId = null;
         this.logStart = null;
         this.logLast = null;
         this.state = 1;
      }

   }

   protected void assumeGlobalXactIdentity(TransactionTableEntry var1) {
      this.myId = var1.getXid();
      this.myGlobalId = var1.getGid();
      this.logStart = var1.getFirstLog();
      this.logLast = var1.getLastLog();
      if (this.state == 1) {
         this.state = 2;
      }

      if (var1.isPrepared()) {
         this.state = 4;
      }

      var1.setXact(this);
      if (this.logger == null) {
         this.getLogger();
      }

      this.savedEndStatus = 0;
   }

   private final void setUpdateState() throws StandardException {
      if (this.readOnly) {
         throw StandardException.newException("40XT8", new Object[]{this.toInternalDetailString()});
      } else {
         this.state = 3;
      }
   }

   protected void setIdleState() {
      this.state = 1;
      this.seenUpdates = false;
      this.logStart = null;
      this.logLast = null;
   }

   protected final void setActiveState() throws StandardException {
      if (this.state != 0 && (this.inAbort() || this.state != 4)) {
         if (this.state == 1) {
            synchronized(this) {
               this.state = 2;
            }

            if (!this.justCreated) {
               this.xactFactory.setNewTransactionId(this.myId, this);
            }

            this.justCreated = false;
         }

      } else {
         throw StandardException.newException("40XT8", new Object[]{this.toInternalDetailString()});
      }
   }

   protected final void setPrepareState() throws StandardException {
      if (this.state != 4 && this.state != 0) {
         this.state = 4;
      } else {
         throw StandardException.newException("40XT8", new Object[]{this.toInternalDetailString()});
      }
   }

   public final LockingPolicy defaultLockingPolicy() {
      return this.defaultLocking;
   }

   private final void releaseAllLocks() {
      this.getLockFactory().unlockGroup(this.getCompatibilitySpace(), this);
   }

   void resetDefaultLocking() {
      this.setDefaultLockingPolicy(this.newLockingPolicy(1, 5, true));
   }

   protected void preComplete(Integer var1) throws StandardException {
      if (this.inComplete != null) {
         if (var1.equals(COMMIT)) {
            throw this.logFactory.markCorrupt(StandardException.newException("40XT1", new Object[0]));
         } else {
            throw this.logFactory.markCorrupt(StandardException.newException("XSTB0.M", new Object[0]));
         }
      } else {
         this.inComplete = var1;
         if (!this.postCompleteMode) {
            this.doComplete(var1);
         }

      }
   }

   protected void postComplete(int var1, Integer var2) throws StandardException {
      if (this.postCompleteMode) {
         this.doComplete(var2);
      }

      if ((var1 & 2) == 0) {
         this.releaseAllLocks();
      }

      this.setIdleState();
      this.inComplete = null;
   }

   protected void doComplete(Integer var1) throws StandardException {
      if (this.savePoints != null) {
         this.savePoints.removeAllElements();
      }

      do {
         this.notifyObservers(var1);
         this.checkObserverException();
      } while(this.countObservers() > 0);

   }

   private void checkObserverException() throws StandardException {
      if (this.observerException != null) {
         StandardException var1 = this.observerException;
         this.observerException = null;
         throw var1;
      }
   }

   protected boolean doPostCommitWorkInTran() {
      return !this.inPostCommitProcessing && !this.recoveryTransaction && this.isUserTransaction() && this.myGlobalId == null;
   }

   public boolean handlesPostTerminationWork() {
      return !this.recoveryTransaction;
   }

   public void recoveryTransaction() {
      this.recoveryTransaction = true;
      this.xactFactory.remove(this.myId);
   }

   private void transferPostCommitorAbortWork(List var1) throws StandardException {
      if (var1 != null && !var1.isEmpty()) {
         int var2 = var1.size();
         if (this.doPostCommitWorkInTran()) {
            try {
               this.inPostCommitProcessing = true;
               Serviceable[] var12 = new Serviceable[var2];
               var12 = (Serviceable[])var1.toArray(var12);
               var1.clear();
               boolean var4 = this.xactFactory.inDatabaseCreation();

               for(int var5 = 0; var5 < var2; ++var5) {
                  if (var4 || var12[var5].serviceImmediately()) {
                     try {
                        if (var12[var5].performWork(this.xc.getContextManager()) == 1) {
                           var12[var5] = null;
                        }
                     } catch (StandardException var10) {
                        var12[var5] = null;
                        this.xc.cleanupOnError(var10);
                     }
                  }

                  if (var12[var5] != null) {
                     boolean var6 = this.xactFactory.submitPostCommitWork(var12[var5]);
                     var12[var5] = null;
                     if (var6) {
                        var4 = true;
                     }
                  }
               }
            } finally {
               this.inPostCommitProcessing = false;
               if (var1 != null) {
                  var1.clear();
               }

            }
         } else {
            for(int var3 = 0; var3 < var2; ++var3) {
               this.xactFactory.submitPostCommitWork((Serviceable)var1.get(var3));
            }
         }

         var1.clear();
      }

   }

   private final void postTermination() throws StandardException {
      int var1 = this.postTerminationWorks == null ? 0 : this.postTerminationWorks.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         this.addPostCommitWork((Serviceable)this.postTerminationWorks.get(var2));
      }

      if (var1 > 0) {
         this.postTerminationWorks.clear();
      }

      this.transferPostCommitorAbortWork(this.postCommitWorks);
      this.transferPostCommitorAbortWork(this.postAbortWorks);
      this.unblockBackup();
   }

   private int getSavePointPosition(String var1, Object var2, boolean var3) {
      if (this.savePoints != null && !this.savePoints.empty()) {
         for(int var4 = this.savePoints.size() - 1; var4 >= 0; --var4) {
            SavePoint var5 = (SavePoint)this.savePoints.elementAt(var4);
            if (var5.getName().equals(var1)) {
               if (!var3 || var5.getKindOfSavepoint() == null) {
                  return var4;
               }

               if (var5.getKindOfSavepoint().equals(var2)) {
                  return var4;
               }
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   protected boolean popSavePoints(int var1, boolean var2) throws StandardException {
      if (var2) {
         this.savePoints.setSize(var1);
         return false;
      } else {
         LogInstant var3 = null;
         int var4 = this.savePoints.size();

         for(int var5 = var1; var5 < var4; ++var5) {
            SavePoint var6 = (SavePoint)this.savePoints.elementAt(var5);
            LogInstant var7 = var6.getSavePoint();
            if (var7 != null) {
               var3 = var7;
               break;
            }
         }

         this.savePoints.setSize(var1 + 1);
         if (var3 == null) {
            return false;
         } else {
            try {
               this.logger.undo(this, this.getId(), var3, this.getLastLogInstant());
               return true;
            } catch (StandardException var8) {
               if (var8.getSeverity() < 30000) {
                  throw StandardException.newException("40XT2", var8, new Object[0]);
               } else {
                  throw var8;
               }
            }
         }
      }
   }

   public RawTransaction startNestedTopTransaction() throws StandardException {
      return this.xactFactory.startNestedTopTransaction(this.xc.getFactory(), this.xc.getContextManager());
   }

   private boolean isUserTransaction() {
      String var1 = this.getContextId();
      return var1 == "UserTransaction" || var1.equals("UserTransaction");
   }

   public final boolean isActive() {
      int var1 = this.state;
      return var1 != 0 && var1 != 1;
   }

   public final boolean isPrepared() {
      return this.state == 4;
   }

   public boolean isIdle() {
      return this.state == 1;
   }

   public boolean isPristine() {
      return this.state == 1 || this.state == 2;
   }

   public boolean inAbort() {
      return ABORT.equals(this.inComplete);
   }

   public FileResource getFileHandler() {
      return this.dataFactory.getFileHandler();
   }

   protected int statusForBeginXactLog() {
      return this.recoveryRollbackFirst() ? 16 : 0;
   }

   protected int statusForEndXactLog() {
      return this.savedEndStatus;
   }

   void setPostComplete() {
      this.postCompleteMode = true;
   }

   public boolean blockBackup(boolean var1) throws StandardException {
      if (!this.backupBlocked) {
         this.backupBlocked = this.xactFactory.blockBackup(var1);
      }

      return this.backupBlocked;
   }

   private void unblockBackup() {
      if (this.backupBlocked) {
         this.xactFactory.unblockBackup();
      }

      this.backupBlocked = false;
   }

   public boolean isBlockingBackup() {
      return this.backupBlocked;
   }

   public void reached(CompatibilitySpace var1, Object var2, int var3, Enumeration var4, int var5) throws StandardException {
      Hashtable var6 = new Hashtable();

      while(var4.hasMoreElements()) {
         Object var7 = var4.nextElement();
         if (var7 instanceof RecordHandle) {
            ContainerKey var8 = ((RecordHandle)var7).getContainerId();
            LockCount var9 = (LockCount)((Dictionary)var6).get(var8);
            if (var9 == null) {
               var9 = new LockCount();
               ((Dictionary)var6).put(var8, var9);
            }

            ++var9.count;
         }
      }

      int var14 = var3 / (((Dictionary)var6).size() + 1);
      if (var14 < var3 / 4) {
         var14 = var3 / 4;
      }

      boolean var15 = false;
      Enumeration var16 = ((Dictionary)var6).keys();

      while(var16.hasMoreElements()) {
         ContainerKey var10 = (ContainerKey)var16.nextElement();
         LockCount var11 = (LockCount)((Dictionary)var6).get(var10);
         if (var11.count >= var14) {
            try {
               if (this.openContainer(var10, new RowLocking3Escalate(this.getLockFactory()), 196) != null) {
                  var15 = true;
               }
            } catch (StandardException var13) {
               if (!var13.isLockTimeout()) {
                  throw var13;
               }
            }
         }
      }

      if (var15) {
         this.notifyObservers(LOCK_ESCALATE);
         this.checkObserverException();
      }

   }

   public void createXATransactionFromLocalTransaction(int var1, byte[] var2, byte[] var3) throws StandardException {
      GlobalXactId var4 = new GlobalXactId(var1, var2, var3);
      if (((TransactionTable)this.xactFactory.getTransactionTable()).findTransactionContextByGlobalId(var4) != null) {
         throw StandardException.newException("XSAX1.S", new Object[0]);
      } else {
         this.setTransactionId((GlobalTransactionId)var4, this.getId());
      }
   }

   public void xa_commit(boolean var1) throws StandardException {
      if (var1) {
         if (this.state == 4) {
            throw StandardException.newException("40XT8", new Object[]{this.toInternalDetailString()});
         }

         this.prepareCommit(65536);
         this.completeCommit(65536);
      } else {
         if (this.state != 4) {
            throw StandardException.newException("40XT8", new Object[]{this.toInternalDetailString()});
         }

         this.prepareCommit(65536);
         this.completeCommit(65536);
      }

   }

   public int xa_prepare() throws StandardException {
      if (this.state != 1 && this.state != 2) {
         this.prepareCommit(327682);
         this.inComplete = null;
         this.setPrepareState();
         return 2;
      } else {
         this.abort();
         return 1;
      }
   }

   public void xa_rollback() throws StandardException {
      this.abort();
   }

   public String toString() {
      try {
         return this.myId.toString();
      } catch (Throwable var2) {
         return "null";
      }
   }

   public String toInternalDetailString() {
      return "savedEndStatus = " + this.savedEndStatus + "\nneedSync = " + this.needSync + "\njustCreated = " + this.justCreated + "\nmyGlobalId = " + this.myGlobalId + "\nmyId = " + this.myId + "\nstate = " + this.state + "\ninComplete = " + this.inComplete + "\nseenUpdates = " + this.seenUpdates + "\ninPostCommitProcessing = " + this.inPostCommitProcessing + "\nlogStart = " + this.logStart + "\nlogLast = " + this.logLast + "\nrecoveryTransaction = " + this.recoveryTransaction + "\npostCompleteMode = " + this.postCompleteMode + "\nsanityCheck_xaclosed = " + this.sanityCheck_xaclosed + "\ntransName = " + this.transName + "\nreadOnly = " + this.readOnly + "\nflush_log_on_xact_end = " + this.flush_log_on_xact_end + "\nbackupBlocked = " + this.backupBlocked + "\ndontWaitForLocks = " + this.dontWaitForLocks + "\n";
   }

   public String getActiveStateTxIdString() {
      if (!this.justCreated && this.state == 1) {
         this.xactFactory.setNewTransactionId(this.myId, this);
         this.justCreated = true;
      }

      return this.toString();
   }

   public DataValueFactory getDataValueFactory() throws StandardException {
      return this.dataValueFactory;
   }

   String getState() {
      int var1 = this.state;
      switch (var1) {
         case 0:
            return "CLOSED";
         case 1:
            return "IDLE";
         case 2:
         case 3:
            return "ACTIVE";
         case 4:
            return "PREPARED";
         default:
            return null;
      }
   }

   public String getTransName() {
      return this.transName;
   }

   public void setTransName(String var1) {
      this.transName = var1;
   }

   public boolean inRollForwardRecovery() {
      return this.logFactory.inRFR();
   }

   public void checkpointInRollForwardRecovery(LogInstant var1, long var2, long var4) throws StandardException {
      this.logFactory.checkpointInRFR(var1, var2, var4, this.dataFactory);
   }

   public boolean isNestedOwner() {
      return this.parentTransactionId != null;
   }

   public boolean nestsUnder(LockOwner var1) {
      if (this.parentTransactionId == null) {
         return false;
      } else {
         return !(var1 instanceof Xact) ? false : this.parentTransactionId.equals(((Xact)var1).getId());
      }
   }
}
