package org.apache.derby.impl.store.raw.xact;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.store.raw.GlobalTransactionId;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.store.raw.xact.TransactionFactory;
import org.apache.derby.iapi.store.raw.xact.TransactionId;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;

public class XactFactory implements TransactionFactory, ModuleControl, ModuleSupportable {
   protected static final String USER_CONTEXT_ID = "UserTransaction";
   protected static final String NESTED_READONLY_USER_CONTEXT_ID = "NestedRawReadOnlyUserTransaction";
   protected static final String NESTED_UPDATE_USER_CONTEXT_ID = "NestedRawUpdateUserTransaction";
   protected static final String INTERNAL_CONTEXT_ID = "InternalTransaction";
   protected static final String NTT_CONTEXT_ID = "NestedTransaction";
   protected DaemonService rawStoreDaemon;
   private UUIDFactory uuidFactory;
   protected ContextService contextFactory;
   protected LockFactory lockFactory;
   protected LogFactory logFactory;
   protected DataFactory dataFactory;
   protected DataValueFactory dataValueFactory;
   protected RawStoreFactory rawStoreFactory;
   public TransactionTable ttab;
   private final AtomicLong tranId = new AtomicLong();
   private LockingPolicy[][] lockingPolicies = new LockingPolicy[3][6];
   private boolean inCreateNoLog = false;
   private Object xa_resource;
   private Object backupSemaphore = new Object();
   private long backupBlockingOperations = 0L;
   private boolean inBackup = false;

   public boolean canSupport(Properties var1) {
      return true;
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.uuidFactory = getMonitor().getUUIDFactory();
      this.dataValueFactory = (DataValueFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.types.DataValueFactory", var2);
      this.contextFactory = getContextService();
      this.lockFactory = (LockFactory)bootServiceModule(false, this, "org.apache.derby.iapi.services.locks.LockFactory", var2);
      this.lockingPolicies[0][0] = new NoLocking();
      this.lockingPolicies[1][0] = new NoLocking();
      this.lockingPolicies[1][1] = new RowLocking1(this.lockFactory);
      this.lockingPolicies[1][2] = new RowLocking2(this.lockFactory);
      this.lockingPolicies[1][3] = new RowLocking2nohold(this.lockFactory);
      this.lockingPolicies[1][4] = new RowLockingRR(this.lockFactory);
      this.lockingPolicies[1][5] = new RowLocking3(this.lockFactory);
      this.lockingPolicies[2][0] = new NoLocking();
      this.lockingPolicies[2][1] = new ContainerLocking2(this.lockFactory);
      this.lockingPolicies[2][2] = new ContainerLocking2(this.lockFactory);
      this.lockingPolicies[2][3] = new ContainerLocking2(this.lockFactory);
      this.lockingPolicies[2][4] = new ContainerLocking3(this.lockFactory);
      this.lockingPolicies[2][5] = new ContainerLocking3(this.lockFactory);
      if (var1) {
         this.ttab = new TransactionTable();
         String var3 = var2.getProperty("derby.__rt.storage.createWithNoLog");
         this.inCreateNoLog = var3 != null && Boolean.valueOf(var3);
      }

   }

   public void stop() {
      if (this.rawStoreDaemon != null) {
         this.rawStoreDaemon.stop();
      }

   }

   public LockFactory getLockFactory() {
      return this.lockFactory;
   }

   public void createFinished() throws StandardException {
      if (!this.inCreateNoLog) {
         throw StandardException.newException("XSTB5.M", new Object[0]);
      } else if (this.ttab.hasActiveUpdateTransaction()) {
         throw StandardException.newException("XSTB5.M", new Object[0]);
      } else {
         this.inCreateNoLog = false;
      }
   }

   private Xact startCommonTransaction(RawStoreFactory var1, Xact var2, ContextManager var3, boolean var4, CompatibilitySpace var5, String var6, String var7, boolean var8, boolean var9) throws StandardException {
      Xact var10 = new Xact(this, var2, this.logFactory, this.dataFactory, this.dataValueFactory, var4, var5, var9);
      var10.setTransName(var7);
      this.pushTransactionContext(var3, var6, var10, false, var1, var8);
      return var10;
   }

   public RawTransaction startTransaction(RawStoreFactory var1, ContextManager var2, String var3) throws StandardException {
      return this.startCommonTransaction(var1, (Xact)null, var2, false, (CompatibilitySpace)null, "UserTransaction", var3, true, true);
   }

   public RawTransaction startNestedReadOnlyUserTransaction(RawStoreFactory var1, RawTransaction var2, CompatibilitySpace var3, ContextManager var4, String var5) throws StandardException {
      return this.startCommonTransaction(var1, (Xact)var2, var4, true, var3, "NestedRawReadOnlyUserTransaction", var5, false, true);
   }

   public RawTransaction startNestedUpdateUserTransaction(RawStoreFactory var1, RawTransaction var2, ContextManager var3, String var4, boolean var5) throws StandardException {
      return this.startCommonTransaction(var1, (Xact)var2, var3, false, (CompatibilitySpace)null, "NestedRawUpdateUserTransaction", var4, true, var5);
   }

   public RawTransaction startGlobalTransaction(RawStoreFactory var1, ContextManager var2, int var3, byte[] var4, byte[] var5) throws StandardException {
      GlobalXactId var6 = new GlobalXactId(var3, var4, var5);
      if (this.ttab.findTransactionContextByGlobalId(var6) != null) {
         throw StandardException.newException("XSAX1.S", new Object[0]);
      } else {
         Xact var7 = this.startCommonTransaction(var1, (Xact)null, var2, false, (CompatibilitySpace)null, "UserTransaction", "UserTransaction", true, true);
         var7.setTransactionId((GlobalTransactionId)var6, var7.getId());
         return var7;
      }
   }

   public RawTransaction findUserTransaction(RawStoreFactory var1, ContextManager var2, String var3) throws StandardException {
      XactContext var4 = (XactContext)var2.getContext("UserTransaction");
      return var4 == null ? this.startTransaction(var1, var2, var3) : var4.getTransaction();
   }

   public RawTransaction startNestedTopTransaction(RawStoreFactory var1, ContextManager var2) throws StandardException {
      Xact var3 = new Xact(this, (Xact)null, this.logFactory, this.dataFactory, this.dataValueFactory, false, (CompatibilitySpace)null, false);
      var3.setPostComplete();
      this.pushTransactionContext(var2, "NestedTransaction", var3, true, var1, true);
      return var3;
   }

   public RawTransaction startInternalTransaction(RawStoreFactory var1, ContextManager var2) throws StandardException {
      InternalXact var3 = new InternalXact(this, this.logFactory, this.dataFactory, this.dataValueFactory);
      this.pushTransactionContext(var2, "InternalTransaction", var3, true, var1, true);
      return var3;
   }

   public boolean findTransaction(TransactionId var1, RawTransaction var2) {
      return this.ttab.findAndAssumeTransaction(var1, var2);
   }

   public void rollbackAllTransactions(RawTransaction var1, RawStoreFactory var2) throws StandardException {
      int var3 = 0;
      if (this.ttab.hasRollbackFirstTransaction()) {
         RawTransaction var4 = this.startInternalTransaction(var2, var1.getContextManager());
         var4.recoveryTransaction();

         while(this.ttab.getMostRecentRollbackFirstTransaction(var4)) {
            ++var3;
            var4.abort();
         }

         var4.close();
      }

      int var5 = 0;

      while(this.ttab.getMostRecentTransactionForRollback(var1)) {
         ++var5;
         var1.abort();
      }

   }

   public void handlePreparedXacts(RawStoreFactory var1) throws StandardException {
      boolean var2 = false;
      if (this.ttab.hasPreparedRecoveredXact()) {
         while(true) {
            ContextManager var3 = this.contextFactory.newContextManager();
            this.contextFactory.setCurrentContextManager(var3);

            try {
               RawTransaction var4 = this.startTransaction(this.rawStoreFactory, var3, "UserTransaction");
               if (!this.ttab.getMostRecentPreparedRecoveredXact(var4)) {
                  var4.destroy();
                  break;
               }

               var4.reprepare();
            } finally {
               this.contextFactory.resetCurrentContextManager(var3);
            }
         }
      }

   }

   public LogInstant firstUpdateInstant() {
      return this.ttab.getFirstLogInstant();
   }

   public StandardException markCorrupt(StandardException var1) {
      this.logFactory.markCorrupt(var1);
      return var1;
   }

   public void setNewTransactionId(TransactionId var1, Xact var2) {
      boolean var3 = true;
      if (var1 != null) {
         var3 = this.remove(var1);
      }

      XactId var4 = new XactId(this.tranId.getAndIncrement());
      var2.setTransactionId((GlobalTransactionId)var2.getGlobalId(), var4);
      if (var1 != null) {
         this.add(var2, var3);
      }

   }

   public void resetTranId() {
      XactId var1 = (XactId)this.ttab.largestUpdateXactId();
      long var2 = var1 == null ? 0L : var1.getId();
      this.tranId.set(var2 + 1L);
   }

   protected void pushTransactionContext(ContextManager var1, String var2, Xact var3, boolean var4, RawStoreFactory var5, boolean var6) throws StandardException {
      if (var1.getContext(var2) != null) {
         throw StandardException.newException("XSTA2.S", new Object[0]);
      } else {
         new XactContext(var1, var2, var3, var4, var5);
         this.add(var3, var6);
      }
   }

   protected void addUpdateTransaction(TransactionId var1, RawTransaction var2, int var3) {
      this.ttab.addUpdateTransaction(var1, var2, var3);
   }

   protected void removeUpdateTransaction(TransactionId var1) {
      this.ttab.removeUpdateTransaction(var1);
   }

   protected void prepareTransaction(TransactionId var1) {
      this.ttab.prepareTransaction(var1);
   }

   public boolean submitPostCommitWork(Serviceable var1) {
      return this.rawStoreDaemon != null ? this.rawStoreDaemon.enqueue(var1, var1.serviceASAP()) : false;
   }

   public void setRawStoreFactory(RawStoreFactory var1) throws StandardException {
      this.rawStoreFactory = var1;
      this.rawStoreDaemon = var1.getDaemon();
      this.logFactory = (LogFactory)findServiceModule(this, var1.getLogFactoryModule());
      this.dataFactory = (DataFactory)findServiceModule(this, var1.getDataFactoryModule());
   }

   public boolean noActiveUpdateTransaction() {
      return !this.ttab.hasActiveUpdateTransaction();
   }

   public boolean hasPreparedXact() {
      return this.ttab.hasPreparedXact();
   }

   protected boolean remove(TransactionId var1) {
      return this.ttab.remove(var1);
   }

   protected void add(Xact var1, boolean var2) {
      this.ttab.add(var1, var2);
   }

   public UUID makeNewUUID() {
      return this.uuidFactory.createUUID();
   }

   final LockingPolicy getLockingPolicy(int var1, int var2, boolean var3) {
      if (var1 == 0) {
         var2 = 0;
      }

      LockingPolicy var4 = this.lockingPolicies[var1][var2];
      if (var4 == null && var3) {
         ++var1;

         while(var1 <= 2) {
            for(int var5 = var2; var5 <= 5; ++var5) {
               var4 = this.lockingPolicies[var1][var5];
               if (var4 != null) {
                  return var4;
               }
            }

            ++var1;
         }

         return null;
      } else {
         return var4;
      }
   }

   public Formatable getTransactionTable() {
      return this.ttab;
   }

   public void useTransactionTable(Formatable var1) throws StandardException {
      if (this.ttab != null && var1 != null) {
         throw StandardException.newException("XSTB6.M", new Object[0]);
      } else {
         if (this.ttab == null) {
            if (var1 == null) {
               this.ttab = new TransactionTable();
            } else {
               this.ttab = (TransactionTable)var1;
            }
         }

      }
   }

   public TransactionInfo[] getTransactionInfo() {
      return this.ttab.getTransactionInfo();
   }

   public boolean inDatabaseCreation() {
      return this.inCreateNoLog;
   }

   public Object getXAResourceManager() throws StandardException {
      if (this.xa_resource == null) {
         this.xa_resource = new XactXAResourceManager(this.rawStoreFactory, this.ttab);
      }

      return this.xa_resource;
   }

   protected boolean blockBackup(boolean var1) {
      synchronized(this.backupSemaphore) {
         if (this.inBackup) {
            if (!var1) {
               return false;
            }

            while(this.inBackup) {
               try {
                  this.backupSemaphore.wait();
               } catch (InterruptedException var5) {
                  InterruptStatus.setInterrupted();
               }
            }
         }

         ++this.backupBlockingOperations;
         return true;
      }
   }

   protected void unblockBackup() {
      synchronized(this.backupSemaphore) {
         --this.backupBlockingOperations;
         if (this.inBackup) {
            this.backupSemaphore.notifyAll();
         }

      }
   }

   public boolean blockBackupBlockingOperations(boolean var1) {
      synchronized(this.backupSemaphore) {
         if (var1) {
            this.inBackup = true;

            try {
               while(this.backupBlockingOperations > 0L) {
                  try {
                     this.backupSemaphore.wait();
                  } catch (InterruptedException var5) {
                     InterruptStatus.setInterrupted();
                  }
               }
            } catch (RuntimeException var6) {
               this.inBackup = false;
               this.backupSemaphore.notifyAll();
               throw var6;
            }
         } else if (this.backupBlockingOperations == 0L) {
            this.inBackup = true;
         }
      }

      return this.inBackup;
   }

   public void unblockBackupBlockingOperations() {
      synchronized(this.backupSemaphore) {
         this.inBackup = false;
         this.backupSemaphore.notifyAll();
      }
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }
}
