package org.apache.derby.impl.store.access;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.security.Securable;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.cache.CacheFactory;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.cache.CacheableFactory;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.iapi.services.property.PropertyFactory;
import org.apache.derby.iapi.services.property.PropertySetCallback;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.ConglomerateFactory;
import org.apache.derby.iapi.store.access.conglomerate.MethodFactory;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.shared.common.error.StandardException;

public abstract class RAMAccessManager implements AccessFactory, CacheableFactory, ModuleControl, PropertySetCallback {
   private RawStoreFactory rawstore;
   private Hashtable implhash = new Hashtable();
   private Hashtable formathash = new Hashtable();
   private Properties serviceProperties;
   LockingPolicy system_default_locking_policy;
   private PropertyConglomerate xactProperties;
   private PropertyFactory pf;
   protected LockingPolicy[] table_level_policy;
   protected LockingPolicy[] record_level_policy;
   protected ConglomerateFactory[] conglom_map;
   private CacheManager conglom_cache;
   private long conglom_nextid = 0L;

   protected LockingPolicy getDefaultLockingPolicy() {
      return this.system_default_locking_policy;
   }

   RawStoreFactory getRawStore() {
      return this.rawstore;
   }

   PropertyConglomerate getTransactionalProperties() {
      return this.xactProperties;
   }

   private void boot_load_conglom_map() throws StandardException {
      this.conglom_map = new ConglomerateFactory[2];
      MethodFactory var1 = this.findMethodFactoryByImpl("heap");
      if (var1 != null && var1 instanceof ConglomerateFactory) {
         this.conglom_map[0] = (ConglomerateFactory)var1;
         var1 = this.findMethodFactoryByImpl("BTREE");
         if (var1 != null && var1 instanceof ConglomerateFactory) {
            this.conglom_map[1] = (ConglomerateFactory)var1;
         } else {
            throw StandardException.newException("XSAM3.S", new Object[]{"BTREE"});
         }
      } else {
         throw StandardException.newException("XSAM3.S", new Object[]{"heap"});
      }
   }

   protected abstract int getSystemLockLevel();

   protected abstract void bootLookupSystemLockLevel(TransactionController var1) throws StandardException;

   protected long getNextConglomId(int var1) throws StandardException {
      int var2;
      synchronized(this.conglom_cache) {
         if (this.conglom_nextid == 0L) {
            this.conglom_nextid = (this.rawstore.getMaxContainerId() >> 4) + 1L;
         }

         var2 = this.conglom_nextid++;
      }

      return var2 << 4 | (long)var1;
   }

   ConglomerateFactory getFactoryFromConglomId(long var1) throws StandardException {
      try {
         return this.conglom_map[(int)(15L & var1)];
      } catch (ArrayIndexOutOfBoundsException var4) {
         throw StandardException.newException("XSAI2.S", new Object[]{var1});
      }
   }

   private void conglomCacheInit() throws StandardException {
      CacheFactory var1 = (CacheFactory)startSystemModule("org.apache.derby.iapi.services.cache.CacheFactory");
      this.conglom_cache = var1.newCacheManager(this, "ConglomerateDirectoryCache", 200, 300);
   }

   Conglomerate conglomCacheFind(long var1) throws StandardException {
      Conglomerate var3 = null;
      Long var4 = var1;
      CacheableConglomerate var5 = (CacheableConglomerate)this.conglom_cache.find(var4);
      if (var5 != null) {
         var3 = var5.getConglom();
         this.conglom_cache.release(var5);
      }

      return var3;
   }

   protected void conglomCacheInvalidate() throws StandardException {
      this.conglom_cache.ageOut();
   }

   void conglomCacheAddEntry(long var1, Conglomerate var3) throws StandardException {
      CacheableConglomerate var4 = (CacheableConglomerate)this.conglom_cache.create(var1, var3);
      this.conglom_cache.release(var4);
   }

   void conglomCacheRemoveEntry(long var1) throws StandardException {
      CacheableConglomerate var3 = (CacheableConglomerate)this.conglom_cache.findCached(var1);
      if (var3 != null) {
         this.conglom_cache.remove(var3);
      }

   }

   RAMTransactionContext getCurrentTransactionContext() {
      RAMTransactionContext var1 = (RAMTransactionContext)getContext("RAMInternalContext");
      if (var1 == null) {
         var1 = (RAMTransactionContext)getContext("RAMChildContext");
      }

      if (var1 == null) {
         var1 = (RAMTransactionContext)getContext("RAMTransactionContext");
      }

      return var1;
   }

   public void createFinished() throws StandardException {
      this.rawstore.createFinished();
   }

   public MethodFactory findMethodFactoryByFormat(UUID var1) {
      MethodFactory var2 = (MethodFactory)this.formathash.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         Enumeration var3 = this.formathash.elements();

         while(var3.hasMoreElements()) {
            var2 = (MethodFactory)var3.nextElement();
            if (var2.supportsFormat(var1)) {
               return var2;
            }
         }

         return null;
      }
   }

   public MethodFactory findMethodFactoryByImpl(String var1) throws StandardException {
      MethodFactory var2 = (MethodFactory)this.implhash.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         Enumeration var3 = this.implhash.elements();

         while(var3.hasMoreElements()) {
            var2 = (MethodFactory)var3.nextElement();
            if (var2.supportsImplementation(var1)) {
               return var2;
            }
         }

         var2 = null;
         Properties var4 = new Properties(this.serviceProperties);
         var4.put("derby.access.Conglomerate.type", var1);

         try {
            var2 = (MethodFactory)bootServiceModule(false, this, "org.apache.derby.iapi.store.access.conglomerate.MethodFactory", var1, var4);
         } catch (StandardException var6) {
            if (!var6.getMessageId().equals("XBM02.D")) {
               throw var6;
            }
         }

         Object var9 = null;
         if (var2 != null) {
            this.registerAccessMethod(var2);
            return var2;
         } else {
            return null;
         }
      }
   }

   public LockFactory getLockFactory() {
      return this.rawstore.getLockFactory();
   }

   public TransactionController getTransaction(ContextManager var1) throws StandardException {
      return this.getAndNameTransaction(var1, "UserTransaction");
   }

   public TransactionController getAndNameTransaction(ContextManager var1, String var2) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         RAMTransactionContext var3 = (RAMTransactionContext)var1.getContext("RAMTransactionContext");
         if (var3 == null) {
            Transaction var4 = this.rawstore.findUserTransaction(var1, var2);
            RAMTransaction var5 = new RAMTransaction(this, var4, (RAMTransaction)null);
            var3 = new RAMTransactionContext(var1, "RAMTransactionContext", var5, false);
            RAMTransaction var6 = var3.getTransaction();
            if (this.xactProperties != null) {
               var4.setup(var6);
               var6.commit();
            }

            var4.setDefaultLockingPolicy(this.system_default_locking_policy);
            var6.commit();
            return var6;
         } else {
            return var3.getTransaction();
         }
      }
   }

   public Object startXATransaction(ContextManager var1, int var2, byte[] var3, byte[] var4) throws StandardException {
      RAMTransaction var5 = null;
      if (var1 == null) {
         return null;
      } else {
         RAMTransactionContext var6 = (RAMTransactionContext)var1.getContext("RAMTransactionContext");
         if (var6 == null) {
            Transaction var7 = this.rawstore.startGlobalTransaction(var1, var2, var3, var4);
            var5 = new RAMTransaction(this, var7, (RAMTransaction)null);
            new RAMTransactionContext(var1, "RAMTransactionContext", var5, false);
            if (this.xactProperties != null) {
               var7.setup(var5);
               var5.commitNoSync(5);
            }

            var7.setDefaultLockingPolicy(this.system_default_locking_policy);
            var5.commitNoSync(5);
         }

         return var5;
      }
   }

   public Object getXAResourceManager() throws StandardException {
      return this.rawstore.getXAResourceManager();
   }

   public void registerAccessMethod(MethodFactory var1) {
      this.implhash.put(var1.primaryImplementationType(), var1);
      this.formathash.put(var1.primaryFormat(), var1);
   }

   public boolean isReadOnly() {
      return this.rawstore.isReadOnly();
   }

   public void createReadMeFiles() throws StandardException {
      this.rawstore.createDataWarningFile();
      LogFactory var1 = (LogFactory)findServiceModule(this, this.rawstore.getLogFactoryModule());
      var1.createDataWarningFile();
      DataFactory var2 = (DataFactory)findServiceModule(this, this.rawstore.getDataFactoryModule());
      PersistentService var3 = getMonitor().getServiceType(this.rawstore);
      var3.createDataWarningFile(var2.getStorageFactory());
   }

   private void addPropertySetNotification(PropertySetCallback var1, TransactionController var2) {
      this.pf.addPropertySetNotification(var1);
      Hashtable var3 = new Hashtable();

      try {
         this.xactProperties.getProperties(var2, var3, false, false);
      } catch (StandardException var5) {
         return;
      }

      boolean var4 = PropertyUtil.isDBOnly((Dictionary)var3);
      var1.init(var4, var3);
   }

   public TransactionInfo[] getTransactionInfo() {
      return this.rawstore.getTransactionInfo();
   }

   public void startReplicationMaster(String var1, String var2, int var3, String var4) throws StandardException {
      this.rawstore.startReplicationMaster(var1, var2, var3, var4);
   }

   public void failover(String var1) throws StandardException {
      this.rawstore.failover(var1);
   }

   public void stopReplicationMaster() throws StandardException {
      this.rawstore.stopReplicationMaster();
   }

   public void freeze() throws StandardException {
      SecurityUtil.authorize(Securable.FREEZE_DATABASE);
      this.rawstore.freeze();
   }

   public void unfreeze() throws StandardException {
      SecurityUtil.authorize(Securable.UNFREEZE_DATABASE);
      this.rawstore.unfreeze();
   }

   public void backup(String var1, boolean var2) throws StandardException {
      SecurityUtil.authorize(var2 ? Securable.BACKUP_DATABASE : Securable.BACKUP_DATABASE_NOWAIT);
      this.rawstore.backup(var1, var2);
   }

   public void backupAndEnableLogArchiveMode(String var1, boolean var2, boolean var3) throws StandardException {
      SecurityUtil.authorize(var3 ? Securable.BACKUP_DATABASE_AND_ENABLE_LOG_ARCHIVE_MODE : Securable.BACKUP_DATABASE_AND_ENABLE_LOG_ARCHIVE_MODE_NOWAIT);
      this.rawstore.backupAndEnableLogArchiveMode(var1, var2, var3);
   }

   public void disableLogArchiveMode(boolean var1) throws StandardException {
      SecurityUtil.authorize(Securable.DISABLE_LOG_ARCHIVE_MODE);
      this.rawstore.disableLogArchiveMode(var1);
   }

   public void checkpoint() throws StandardException {
      SecurityUtil.authorize(Securable.CHECKPOINT_DATABASE);
      this.rawstore.checkpoint();
   }

   public void waitForPostCommitToFinishWork() {
      this.rawstore.getDaemon().waitUntilQueueIsEmpty();
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.serviceProperties = var2;
      this.boot_load_conglom_map();
      if (var1) {
         this.conglom_nextid = 1L;
      }

      this.rawstore = (RawStoreFactory)bootServiceModule(var1, this, "org.apache.derby.iapi.store.raw.RawStoreFactory", this.serviceProperties);
      this.rawstore.setUndoInsertEventHandler(new RAMAccessUndoHandler(this));
      bootServiceModule(var1, this, "org.apache.derby.iapi.services.property.PropertyFactory", var2);
      this.conglomCacheInit();
      RAMTransaction var3 = (RAMTransaction)this.getAndNameTransaction(getContextService().getCurrentContextManager(), "UserTransaction");
      int var4 = 2;
      this.system_default_locking_policy = var3.getRawStoreXact().newLockingPolicy(var4, 5, true);
      this.table_level_policy = new LockingPolicy[6];
      this.table_level_policy[0] = var3.getRawStoreXact().newLockingPolicy(2, 0, true);
      this.table_level_policy[1] = var3.getRawStoreXact().newLockingPolicy(2, 1, true);
      this.table_level_policy[2] = var3.getRawStoreXact().newLockingPolicy(2, 2, true);
      this.table_level_policy[3] = var3.getRawStoreXact().newLockingPolicy(2, 3, true);
      this.table_level_policy[4] = var3.getRawStoreXact().newLockingPolicy(2, 4, true);
      this.table_level_policy[5] = var3.getRawStoreXact().newLockingPolicy(2, 5, true);
      this.record_level_policy = new LockingPolicy[6];
      this.record_level_policy[0] = var3.getRawStoreXact().newLockingPolicy(1, 0, true);
      this.record_level_policy[1] = var3.getRawStoreXact().newLockingPolicy(1, 1, true);
      this.record_level_policy[2] = var3.getRawStoreXact().newLockingPolicy(1, 2, true);
      this.record_level_policy[3] = var3.getRawStoreXact().newLockingPolicy(1, 3, true);
      this.record_level_policy[4] = var3.getRawStoreXact().newLockingPolicy(1, 4, true);
      this.record_level_policy[5] = var3.getRawStoreXact().newLockingPolicy(1, 5, true);
      var3.commit();
      this.pf = (PropertyFactory)findServiceModule(this, "org.apache.derby.iapi.services.property.PropertyFactory");
      this.xactProperties = new PropertyConglomerate(var3, var1, var2, this.pf);
      if (var1) {
         this.rawstore.createDataWarningFile();
      }

      this.rawstore.getRawStoreProperties(var3);
      this.bootLookupSystemLockLevel(var3);
      var4 = this.getSystemLockLevel() == 7 ? 2 : 1;
      this.system_default_locking_policy = var3.getRawStoreXact().newLockingPolicy(var4, 5, true);
      this.addPropertySetNotification(this.getLockFactory(), var3);
      this.addPropertySetNotification(this, var3);
      var3.commit();
      var3.destroy();
      Object var5 = null;
   }

   public void stop() {
   }

   public void init(boolean var1, Dictionary var2) {
   }

   public boolean validate(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (var1.equals("encryptionAlgorithm")) {
         throw StandardException.newException("XBCXD.S", new Object[0]);
      } else if (var1.equals("encryptionProvider")) {
         throw StandardException.newException("XBCXE.S", new Object[0]);
      } else {
         return true;
      }
   }

   public Serviceable apply(String var1, Serializable var2, Dictionary var3) throws StandardException {
      return null;
   }

   public Serializable map(String var1, Serializable var2, Dictionary var3) throws StandardException {
      return null;
   }

   public Cacheable newCacheable(CacheManager var1) {
      return new CacheableConglomerate(this);
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static Object startSystemModule(String var0) throws StandardException {
      return Monitor.startSystemModule(var0);
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3);
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, String var3, Properties var4) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3, var4);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }
}
