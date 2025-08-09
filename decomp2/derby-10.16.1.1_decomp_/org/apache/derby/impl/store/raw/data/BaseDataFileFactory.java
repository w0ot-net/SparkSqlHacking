package org.apache.derby.impl.store.raw.data;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.cache.CacheFactory;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.cache.CacheableFactory;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.StreamContainerHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.UndoHandler;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.iapi.util.Matchable;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.StorageRandomAccessFile;
import org.apache.derby.io.WritableStorageFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.info.ProductVersionHolder;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public class BaseDataFileFactory implements DataFactory, CacheableFactory, ModuleControl, ModuleSupportable {
   StorageFactory storageFactory;
   WritableStorageFactory writableStorageFactory;
   private long nextContainerId = System.currentTimeMillis();
   private boolean databaseEncrypted;
   private CacheManager pageCache;
   private CacheManager containerCache;
   private LogFactory logFactory;
   private ProductVersionHolder jbmsVersion;
   private String jvmVersion;
   private String osInfo;
   private String jarCPath;
   private RawStoreFactory rawStoreFactory;
   private String dataDirectory;
   private boolean throwDBlckException;
   private UUID identifier;
   private final Object freezeSemaphore = new Object();
   private boolean isFrozen;
   private int writersInProgress;
   private boolean removeStubsOK;
   private boolean isCorrupt;
   private boolean inCreateNoLog;
   private StorageRandomAccessFile fileLockOnDB;
   private StorageFile exFileLock;
   private HeaderPrintWriter istream;
   private static final String LINE = "----------------------------------------------------------------";
   boolean dataNotSyncedAtAllocation = true;
   boolean dataNotSyncedAtCheckpoint = false;
   private PageActions loggablePageActions;
   private AllocationActions loggableAllocActions;
   private boolean readOnly;
   private boolean supportsRandomAccess;
   private FileResource fileHandler;
   private Hashtable droppedTableStubInfo;
   private Hashtable postRecoveryRemovedFiles;
   private int actionCode;
   private static final int REMOVE_TEMP_DIRECTORY_ACTION = 2;
   private static final int GET_CONTAINER_PATH_ACTION = 3;
   private static final int GET_ALTERNATE_CONTAINER_PATH_ACTION = 4;
   private static final int FIND_MAX_CONTAINER_ID_ACTION = 5;
   private static final int DELETE_IF_EXISTS_ACTION = 6;
   private static final int GET_PATH_ACTION = 7;
   private static final int POST_RECOVERY_REMOVE_ACTION = 8;
   private static final int REMOVE_STUBS_ACTION = 9;
   private static final int BOOT_ACTION = 10;
   private static final int GET_LOCK_ON_DB_ACTION = 11;
   private static final int RELEASE_LOCK_ON_DB_ACTION = 12;
   private static final int RESTORE_DATA_DIRECTORY_ACTION = 13;
   private static final int GET_CONTAINER_NAMES_ACTION = 14;
   private ContainerKey containerId;
   private boolean stub;
   private StorageFile actionFile;
   private UUID myUUID;
   private UUIDFactory uuidFactory;
   private String databaseDirectory;
   private File backupRoot;
   private String[] bfilelist;
   private UndoHandler undo_handler = null;

   public BaseDataFileFactory() {
      SecurityUtil.checkDerbyInternalsPrivilege();
   }

   public boolean canSupport(Properties var1) {
      String var2 = var1.getProperty("derby.__rt.serviceType");
      if (var2 == null) {
         return false;
      } else if (!this.handleServiceType(var2)) {
         return false;
      } else {
         return var1.getProperty("derby.__rt.serviceDirectory") != null;
      }
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.jbmsVersion = getMonitor().getEngineVersion();
      this.jvmVersion = buildJvmVersion();
      this.osInfo = buildOSinfo();
      this.jarCPath = jarClassPath(this.getClass());
      this.dataDirectory = var2.getProperty("derby.__rt.serviceDirectory");
      UUIDFactory var3 = getMonitor().getUUIDFactory();
      this.identifier = var3.createUUID();
      PersistentService var4 = getMonitor().getServiceType(this);

      try {
         this.storageFactory = var4.getStorageFactoryInstance(true, this.dataDirectory, var2.getProperty("derby.storage.tempDirectory", PropertyUtil.getSystemProperty("derby.storage.tempDirectory")), this.identifier.toANSIidentifier());
      } catch (IOException var16) {
         if (var1) {
            throw StandardException.newException("XBM0H.D", var16, new Object[]{this.dataDirectory});
         }

         throw StandardException.newException("XJ004.C", var16, new Object[]{this.dataDirectory});
      }

      if (this.luceneLoaded()) {
         String var5 = var2.getProperty("dataEncryption");
         if (var5 != null && "TRUE".equals(var5.toUpperCase())) {
            throw StandardException.newException("42XBL", new Object[0]);
         }
      }

      if (this.storageFactory instanceof WritableStorageFactory) {
         this.writableStorageFactory = (WritableStorageFactory)this.storageFactory;
      }

      this.actionCode = 10;

      try {
         this.run();
      } catch (Exception var15) {
      }

      String var18 = var2.getProperty("derby.database.forceDatabaseLock", PropertyUtil.getSystemProperty("derby.database.forceDatabaseLock"));
      this.throwDBlckException = Boolean.valueOf(var18 != null ? var18.trim() : var18);
      if (!this.isReadOnly()) {
         this.getJBMSLockOnDB(this.identifier, var3, this.dataDirectory);
      }

      Object var6 = null;
      String var19 = var2.getProperty("createFrom");
      if (var19 == null) {
         var19 = var2.getProperty("restoreFrom");
      }

      if (var19 == null) {
         var19 = var2.getProperty("rollForwardRecoveryFrom");
      }

      if (var19 != null) {
         try {
            String var7 = var2.getProperty("dataEncryption");
            this.databaseEncrypted = Boolean.valueOf(var7);
            this.restoreDataDirectory(var19);
         } catch (StandardException var14) {
            this.releaseJBMSLockOnDB();
            throw var14;
         }
      }

      this.logMsg("----------------------------------------------------------------");
      String var20 = this.isReadOnly() ? "D000" : "D001";
      boolean var8 = Boolean.valueOf(var2.getProperty("derby.stream.error.logBootTrace", PropertyUtil.getSystemProperty("derby.stream.error.logBootTrace")));
      Date var10001 = new Date();
      this.logMsg(var10001 + MessageService.getTextMessage(var20, new Object[]{this.jbmsVersion, this.identifier, this.dataDirectory, this.getClass().getClassLoader(), this.jarCPath}));
      this.logMsg(this.jvmVersion);
      this.logMsg(this.osInfo);
      this.logMsg("derby.system.home=" + PropertyUtil.getSystemProperty("derby.system.home"));
      String var9 = PropertyUtil.getSystemProperty("derby.stream.error.style");
      if (var9 != null) {
         this.logMsg("derby.stream.error.style=" + var9);
      }

      var9 = PropertyUtil.getSystemProperty("derby.stream.error.file");
      if (var9 != null) {
         this.logMsg("derby.stream.error.file=" + var9);
      }

      var9 = PropertyUtil.getSystemProperty("derby.stream.error.method");
      if (var9 != null) {
         this.logMsg("derby.stream.error.method=" + var9);
      }

      var9 = PropertyUtil.getSystemProperty("derby.stream.error.field");
      if (var9 != null) {
         this.logMsg("derby.stream.error.field=" + var9);
      }

      if (var8) {
         Monitor.logThrowable(new Throwable("boot trace"));
      }

      Object var17 = null;
      CacheFactory var10 = (CacheFactory)startSystemModule("org.apache.derby.iapi.services.cache.CacheFactory");
      int var11 = this.getIntParameter("derby.storage.pageCacheSize", (Properties)null, 1000, 40, Integer.MAX_VALUE);
      this.pageCache = var10.newCacheManager(this, "PageCache", var11 / 2, var11);
      int var12 = this.getIntParameter("derby.storage.fileCacheSize", (Properties)null, 100, 2, Integer.MAX_VALUE);
      this.containerCache = var10.newCacheManager(this, "ContainerCache", var12 / 2, var12);
      this.pageCache.registerMBean(this.dataDirectory);
      this.containerCache.registerMBean(this.dataDirectory);
      if (var1) {
         String var13 = var2.getProperty("derby.__rt.storage.createWithNoLog");
         this.inCreateNoLog = var13 != null && Boolean.valueOf(var13);
      }

      this.droppedTableStubInfo = new Hashtable();
      if ("test".equalsIgnoreCase(PropertyUtil.getSystemProperty("derby.system.durability"))) {
         this.dataNotSyncedAtCheckpoint = true;
         Monitor.logMessage(MessageService.getTextMessage("D013", new Object[]{"derby.system.durability", "test"}));
      }

      this.fileHandler = new RFResource(this);
   }

   public void stop() {
      boolean var1 = false;
      if (this.rawStoreFactory != null) {
         DaemonService var2 = this.rawStoreFactory.getDaemon();
         if (var2 != null) {
            var2.stop();
         }
      }

      boolean var5 = PropertyUtil.getSystemBoolean("derby.stream.error.logBootTrace");
      this.logMsg("----------------------------------------------------------------");
      Date var10001 = new Date();
      this.logMsg(var10001 + MessageService.getTextMessage("D002", new Object[]{this.getIdentifier(), this.getRootDirectory(), this.getClass().getClassLoader()}));
      if (var5) {
         Monitor.logThrowable(new Throwable("shutdown trace"));
      }

      if (!this.isCorrupt) {
         try {
            if (this.pageCache != null && this.containerCache != null) {
               this.pageCache.shutdown();
               this.containerCache.shutdown();
               var1 = true;
            }
         } catch (StandardException var4) {
            var4.printStackTrace(this.istream.getPrintWriter());
         }
      }

      this.removeTempDirectory();
      if (this.isReadOnly()) {
         if (this.storageFactory != null) {
            this.storageFactory.shutdown();
         }

      } else {
         if (this.removeStubsOK && var1) {
            this.removeStubs();
         }

         this.releaseJBMSLockOnDB();
         if (this.writableStorageFactory != null) {
            this.writableStorageFactory.shutdown();
         }

      }
   }

   public Cacheable newCacheable(CacheManager var1) {
      if (var1 == this.pageCache) {
         StoredPage var2 = new StoredPage();
         var2.setFactory(this);
         return var2;
      } else {
         return this.newContainerObject();
      }
   }

   public void createFinished() throws StandardException {
      if (!this.inCreateNoLog) {
         throw StandardException.newException("XSDG5.D", new Object[0]);
      } else {
         this.checkpoint();
         this.inCreateNoLog = false;
      }
   }

   public ContainerHandle openContainer(RawTransaction var1, ContainerKey var2, LockingPolicy var3, int var4) throws StandardException {
      return this.openContainer(var1, var2, var3, var4, false);
   }

   public RawContainerHandle openDroppedContainer(RawTransaction var1, ContainerKey var2, LockingPolicy var3, int var4) throws StandardException {
      var4 |= 1024;
      return this.openContainer(var1, var2, var3, var4, true);
   }

   private RawContainerHandle openContainer(RawTransaction var1, ContainerKey var2, LockingPolicy var3, int var4, boolean var5) throws StandardException {
      boolean var6 = (var4 & 128) == 0;
      if ((var4 & 64) != 0) {
         BaseContainerHandle var13 = new BaseContainerHandle(this.getIdentifier(), var1, var2, var3, var4);
         return var13.useContainer(true, var6) ? var13 : null;
      } else {
         FileContainer var8 = (FileContainer)this.containerCache.find(var2);
         if (var8 == null) {
            return null;
         } else {
            if (var2.getSegmentId() == -1L) {
               if ((var4 & 2048) == 2048) {
                  var4 |= 1;
               } else {
                  var4 |= 257;
               }

               var3 = var1.newLockingPolicy(0, 0, true);
            } else {
               if (this.inCreateNoLog) {
                  var4 |= 3;
               } else if (!this.logFactory.logArchived() && !this.logFactory.inReplicationMasterMode()) {
                  if (((var4 & 1) == 1 || (var4 & 2) == 2) && !var1.blockBackup(false)) {
                     var4 &= -4;
                  }
               } else {
                  var4 &= -4;
               }

               if ((var4 & 1) == 1 && (var4 & 2) == 0) {
                  var4 |= 512;
               }
            }

            Object var9 = null;
            Object var10 = null;
            if ((var4 & 4) == 4) {
               if ((var4 & 1) == 0) {
                  var9 = this.getLoggablePageActions();
                  var10 = this.getLoggableAllocationActions();
               } else {
                  var9 = new DirectActions();
                  var10 = new DirectAllocActions();
               }
            }

            BaseContainerHandle var7 = new BaseContainerHandle(this.getIdentifier(), var1, (PageActions)var9, (AllocationActions)var10, var3, var8, var4);

            try {
               if (!var7.useContainer(var5, var6)) {
                  this.containerCache.release(var8);
                  return null;
               } else {
                  return var7;
               }
            } catch (StandardException var12) {
               this.containerCache.release(var8);
               throw var12;
            }
         }
      }
   }

   public long addContainer(RawTransaction var1, long var2, long var4, int var6, Properties var7, int var8) throws StandardException {
      long var9 = var4 != 0L ? var4 : this.getNextId();
      ContainerKey var11 = new ContainerKey(var2, var9);
      boolean var12 = var2 == -1L;
      ContainerHandle var13 = null;
      LockingPolicy var14 = null;
      if (!var12) {
         if (this.isReadOnly()) {
            throw StandardException.newException("40XD1", new Object[0]);
         }

         var14 = var1.newLockingPolicy(2, 5, true);
         var13 = var1.openContainer(var11, var14, 68);
      }

      FileContainer var15 = (FileContainer)this.containerCache.create(var11, var7);
      ContainerHandle var16 = null;
      Page var17 = null;

      try {
         if (var12 && (var8 & 2) == 2) {
            var6 |= 2048;
         }

         var16 = var1.openContainer(var11, (LockingPolicy)null, 4 | var6);
         if (!var12) {
            RawContainerHandle var18 = (RawContainerHandle)var16;
            ContainerOperation var19 = new ContainerOperation(var18, (byte)1);
            var18.preDirty(true);

            try {
               var1.logAndDo(var19);
               this.flush(var1.getLastLogInstant());
            } finally {
               var18.preDirty(false);
            }
         }

         var17 = var16.addPage();
      } finally {
         if (var17 != null) {
            var17.unlatch();
            Object var29 = null;
         }

         this.containerCache.release(var15);
         if (var16 != null) {
            var16.close();
            Object var28 = null;
         }

         if (!var12) {
            var14.unlockContainer(var1, var13);
         }

      }

      return var9;
   }

   public long addAndLoadStreamContainer(RawTransaction var1, long var2, Properties var4, RowSource var5) throws StandardException {
      long var6 = this.getNextId();
      ContainerKey var8 = new ContainerKey(var2, var6);
      StreamFileContainer var9 = new StreamFileContainer(var8, this, var4);
      var9.load(var5);
      return var6;
   }

   public StreamContainerHandle openStreamContainer(RawTransaction var1, long var2, long var4, boolean var6) throws StandardException {
      ContainerKey var7 = new ContainerKey(var2, var4);
      StreamFileContainer var9 = new StreamFileContainer(var7, this);
      var9 = var9.open(false);
      if (var9 == null) {
         return null;
      } else {
         StreamFileContainerHandle var8 = new StreamFileContainerHandle(this.getIdentifier(), var1, var9, var6);
         return var8.useContainer() ? var8 : null;
      }
   }

   public void dropStreamContainer(RawTransaction var1, long var2, long var4) throws StandardException {
      boolean var6 = var2 == -1L;
      StreamContainerHandle var7 = null;

      try {
         ContainerKey var8 = new ContainerKey(var2, var4);
         var1.notifyObservers(var8);
         var7 = var1.openStreamContainer(var2, var4, false);
         if (!var6 || var7 == null) {
            return;
         }

         var7.removeContainer();
      } finally {
         if (var7 != null) {
            var7.close();
         }

      }

   }

   public void reCreateContainerForRedoRecovery(RawTransaction var1, long var2, long var4, ByteArray var6) throws StandardException {
      ContainerKey var7 = new ContainerKey(var2, var4);
      FileContainer var8 = (FileContainer)this.containerCache.create(var7, var6);
      this.containerCache.release(var8);
   }

   public void dropContainer(RawTransaction var1, ContainerKey var2) throws StandardException {
      boolean var3 = var2.getSegmentId() == -1L;
      LockingPolicy var4 = null;
      if (!var3) {
         if (this.isReadOnly()) {
            throw StandardException.newException("40XD1", new Object[0]);
         }

         var4 = var1.newLockingPolicy(2, 5, true);
      }

      var1.notifyObservers(var2);
      RawContainerHandle var5 = (RawContainerHandle)var1.openContainer(var2, var4, 4);

      try {
         if (var5 != null && var5.getContainerStatus() == 1) {
            if (var3) {
               var5.dropContainer((LogInstant)null, true);
               var5.removeContainer((LogInstant)null);
            } else {
               ContainerOperation var6 = new ContainerOperation(var5, (byte)2);
               var5.preDirty(true);

               try {
                  var1.logAndDo(var6);
               } finally {
                  var5.preDirty(false);
               }

               ReclaimSpace var7 = new ReclaimSpace(1, var2, this, true);
               var1.addPostCommitWork(var7);
            }

            return;
         }

         if (!var3) {
            throw StandardException.newException("40XD2", new Object[]{var2});
         }

         if (var5 != null) {
            var5.removeContainer((LogInstant)null);
         }
      } finally {
         if (var5 != null) {
            var5.close();
         }

      }

   }

   public void checkpoint() throws StandardException {
      this.pageCache.cleanAll();
      this.containerCache.cleanAll();
   }

   public void idle() throws StandardException {
      this.pageCache.ageOut();
      this.containerCache.ageOut();
   }

   public void setRawStoreFactory(RawStoreFactory var1, boolean var2, Properties var3) throws StandardException {
      this.rawStoreFactory = var1;
      this.bootLogFactory(var2, var3);
   }

   public UUID getIdentifier() {
      return this.identifier;
   }

   public int reclaimSpace(Serviceable var1, ContextManager var2) throws StandardException {
      if (var1 == null) {
         return 1;
      } else {
         Transaction var3 = this.rawStoreFactory.findUserTransaction(var2, "SystemTransaction");
         return ReclaimSpaceHelper.reclaimSpace(this, (RawTransaction)var3, (ReclaimSpace)var1);
      }
   }

   public StandardException markCorrupt(StandardException var1) {
      boolean var2 = !this.isCorrupt;
      this.isCorrupt = true;
      if (this.getLogFactory() != null) {
         this.getLogFactory().markCorrupt(var1);
      }

      if (var2) {
         if (this.pageCache != null) {
            this.pageCache.discard((Matchable)null);
         }

         if (this.containerCache != null) {
            this.containerCache.discard((Matchable)null);
         }

         this.pageCache = null;
         this.containerCache = null;
         this.releaseJBMSLockOnDB();
      }

      return var1;
   }

   public FileResource getFileHandler() {
      return this.fileHandler;
   }

   public void removeStubsOK() {
      this.removeStubsOK = true;
   }

   public void setUndoInsertEventHandler(UndoHandler var1) {
      this.undo_handler = var1;
   }

   protected void insertUndoNotify(RawTransaction var1, PageKey var2) throws StandardException {
      if (this.undo_handler != null) {
         this.undo_handler.insertUndoNotify(var1, var2);
      }

   }

   public int getIntParameter(String var1, Properties var2, int var3, int var4, int var5) {
      String var7 = null;
      if (var2 != null) {
         var7 = var2.getProperty(var1);
      }

      if (var7 == null) {
         var7 = PropertyUtil.getSystemProperty(var1);
      }

      if (var7 != null) {
         try {
            int var6 = Integer.parseInt(var7);
            if (var6 >= var4 && var6 <= var5) {
               return var6;
            }
         } catch (NumberFormatException var9) {
         }
      }

      return var3;
   }

   CacheManager getContainerCache() {
      return this.containerCache;
   }

   CacheManager getPageCache() {
      return this.pageCache;
   }

   void flush(LogInstant var1) throws StandardException {
      this.getLogFactory().flush(var1);
   }

   LogFactory getLogFactory() {
      return this.logFactory;
   }

   RawStoreFactory getRawStoreFactory() {
      return this.rawStoreFactory;
   }

   public String getRootDirectory() {
      return this.dataDirectory;
   }

   Cacheable newContainerObject() {
      return (Cacheable)(this.supportsRandomAccess ? this.newRAFContainer(this) : new InputStreamContainer(this));
   }

   protected Cacheable newRAFContainer(BaseDataFileFactory var1) {
      return new RAFContainer(var1);
   }

   private PageActions getLoggablePageActions() throws StandardException {
      if (this.loggablePageActions == null) {
         this.loggablePageActions = new LoggableActions();
      }

      return this.loggablePageActions;
   }

   private AllocationActions getLoggableAllocationActions() {
      if (this.loggableAllocActions == null) {
         this.loggableAllocActions = new LoggableAllocActions();
      }

      return this.loggableAllocActions;
   }

   private synchronized void removeTempDirectory() {
      if (this.storageFactory != null) {
         this.actionCode = 2;

         try {
            this.run();
         } catch (Exception var2) {
         }
      }

   }

   public StorageFile getContainerPath(ContainerKey var1, boolean var2) {
      return this.getContainerPath(var1, var2, 3);
   }

   private synchronized StorageFile getContainerPath(ContainerKey var1, boolean var2, int var3) {
      this.actionCode = var3;

      Object var5;
      try {
         this.containerId = var1;
         this.stub = var2;

         try {
            StorageFile var4 = (StorageFile)this.run();
            return var4;
         } catch (Exception var9) {
            var5 = null;
         }
      } finally {
         this.containerId = null;
      }

      return (StorageFile)var5;
   }

   public StorageFile getAlternateContainerPath(ContainerKey var1, boolean var2) {
      return this.getContainerPath(var1, var2, 4);
   }

   private synchronized void removeStubs() {
      if (this.storageFactory != null) {
         this.actionCode = 9;

         try {
            this.run();
         } catch (Exception var2) {
         }
      }

   }

   public void stubFileToRemoveAfterCheckPoint(StorageFile var1, LogInstant var2, Object var3) {
      if (this.droppedTableStubInfo != null) {
         Object[] var4 = new Object[]{var1, var3};
         this.droppedTableStubInfo.put(var2, var4);
      }

   }

   public void removeDroppedContainerFileStubs(LogInstant var1) throws StandardException {
      if (this.droppedTableStubInfo != null) {
         synchronized(this.droppedTableStubInfo) {
            Enumeration var3 = this.droppedTableStubInfo.keys();

            while(var3.hasMoreElements()) {
               LogInstant var4 = (LogInstant)var3.nextElement();
               if (var4.lessThan(var1)) {
                  Object[] var5 = this.droppedTableStubInfo.get(var4);
                  Object var6 = var5[1];
                  Cacheable var7 = this.containerCache.findCached(var6);
                  if (var7 != null) {
                     this.containerCache.remove(var7);
                  }

                  synchronized(this) {
                     this.actionFile = (StorageFile)var5[0];
                     this.actionCode = 6;

                     try {
                        if (this.run() != null) {
                           this.droppedTableStubInfo.remove(var4);
                        }
                     } catch (Exception var12) {
                     }
                  }
               }
            }
         }
      }

   }

   private synchronized long findMaxContainerId() {
      this.actionCode = 5;

      try {
         return (Long)this.run();
      } catch (Exception var2) {
         return 0L;
      }
   }

   private void bootLogFactory(boolean var1, Properties var2) throws StandardException {
      if (this.isReadOnly()) {
         var2.put("derby.__rt.storage.log", "readonly");
      }

      this.logFactory = (LogFactory)bootServiceModule(var1, this, this.rawStoreFactory.getLogFactoryModule(), var2);
   }

   private boolean handleServiceType(String var1) {
      try {
         PersistentService var2 = getMonitor().getServiceProvider(var1);
         return var2 != null && var2.hasStorageFactory();
      } catch (StandardException var3) {
         return false;
      }
   }

   private void getJBMSLockOnDB(UUID var1, UUIDFactory var2, String var3) throws StandardException {
      if (this.fileLockOnDB == null) {
         if (!this.isReadOnly()) {
            synchronized(this) {
               this.actionCode = 11;
               this.myUUID = var1;
               this.uuidFactory = var2;
               this.databaseDirectory = var3;

               try {
                  this.run();
               } catch (IOException var11) {
                  throw StandardException.plainWrapException(var11);
               } finally {
                  this.myUUID = null;
                  this.uuidFactory = null;
                  this.databaseDirectory = null;
               }

            }
         }
      }
   }

   private void privGetJBMSLockOnDB() throws StandardException {
      boolean var1 = false;
      String var2 = null;
      StorageFile var3 = this.storageFactory.newStorageFile("db.lck");

      try {
         if (var3.exists()) {
            var1 = true;
            this.fileLockOnDB = var3.getRandomAccessFile("rw");

            try {
               var2 = this.fileLockOnDB.readUTF();
            } catch (IOException var19) {
               var1 = false;
            }

            this.fileLockOnDB.close();
            this.fileLockOnDB = null;
            if (!var3.delete()) {
               throw StandardException.newException("XSDB6.D", new Object[]{this.databaseDirectory});
            }
         }

         this.fileLockOnDB = var3.getRandomAccessFile("rw");
         var3.limitAccessToOwner();
         this.fileLockOnDB.writeUTF(this.myUUID.toString());
         this.fileLockOnDB.sync();
         this.fileLockOnDB.seek(0L);
         UUID var4 = this.uuidFactory.recreateUUID(this.fileLockOnDB.readUTF());
         if (!var4.equals(this.myUUID)) {
            throw StandardException.newException("XSDB6.D", new Object[]{this.databaseDirectory});
         }
      } catch (IOException var20) {
         this.readOnly = true;

         try {
            if (this.fileLockOnDB != null) {
               this.fileLockOnDB.close();
            }
         } catch (IOException var15) {
         }

         this.fileLockOnDB = null;
         return;
      }

      if (var3.delete()) {
         Object[] var21 = new Object[]{this.myUUID, this.databaseDirectory, var2};
         int var5 = 0;
         if (!this.throwDBlckException) {
            this.exFileLock = this.storageFactory.newStorageFile("dbex.lck");
            var5 = this.exFileLock.getExclusiveFileLock();
         }

         if (var5 == 0 && var1 && !this.throwDBlckException) {
            String var6 = MessageService.getTextMessage("XSDB7.D", var21);
            this.logMsg(var6);
            System.err.println(var6);
         }

         try {
            if (this.fileLockOnDB != null) {
               this.fileLockOnDB.close();
            }

            this.fileLockOnDB = var3.getRandomAccessFile("rw");
            var3.limitAccessToOwner();
            this.fileLockOnDB.writeUTF(this.myUUID.toString());
            this.fileLockOnDB.sync();
            this.fileLockOnDB.close();
         } catch (IOException var17) {
            try {
               this.fileLockOnDB.close();
            } catch (IOException var16) {
            }
         } finally {
            this.fileLockOnDB = null;
         }

         if (var1 && this.throwDBlckException) {
            throw StandardException.newException("XSDB8.D", var21);
         }

         if (var5 == 2) {
            throw StandardException.newException("XSDB6.D", new Object[]{this.databaseDirectory});
         }
      }

   }

   private void releaseJBMSLockOnDB() {
      if (!this.isReadOnly()) {
         synchronized(this) {
            this.actionCode = 12;

            try {
               this.run();
            } catch (Exception var8) {
            } finally {
               this.fileLockOnDB = null;
            }

         }
      }
   }

   private void privReleaseJBMSLockOnDB() throws IOException {
      if (this.fileLockOnDB != null) {
         this.fileLockOnDB.close();
      }

      if (this.storageFactory != null) {
         StorageFile var1 = this.storageFactory.newStorageFile("db.lck");
         var1.delete();
      }

      if (this.exFileLock != null) {
         this.exFileLock.releaseExclusiveFileLock();
      }

   }

   private void logMsg(String var1) {
      if (this.istream == null) {
         this.istream = Monitor.getStream();
      }

      this.istream.println(var1);
   }

   public final boolean databaseEncrypted() {
      return this.databaseEncrypted;
   }

   public void setDatabaseEncrypted(boolean var1) {
      this.databaseEncrypted = var1;
   }

   public int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5, boolean var6) throws StandardException {
      return this.rawStoreFactory.encrypt(var1, var2, var3, var4, var5, var6);
   }

   public int decrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException {
      return this.rawStoreFactory.decrypt(var1, var2, var3, var4, var5);
   }

   public void decryptAllContainers(RawTransaction var1) throws StandardException {
      EncryptOrDecryptData var2 = new EncryptOrDecryptData(this);
      var2.decryptAllContainers(var1);
   }

   public void encryptAllContainers(RawTransaction var1) throws StandardException {
      EncryptOrDecryptData var2 = new EncryptOrDecryptData(this);
      var2.encryptAllContainers(var1);
   }

   public void removeOldVersionOfContainers() throws StandardException {
      EncryptOrDecryptData var1 = new EncryptOrDecryptData(this);
      var1.removeOldVersionOfContainers();
   }

   private static String jarClassPath(Class var0) {
      Object var1 = null;
      CodeSource var3 = var0.getProtectionDomain().getCodeSource();
      if (var3 != null && var3.getLocation() != null) {
         URL var2 = var3.getLocation();
         return var2.toString();
      } else {
         return null;
      }
   }

   private static String buildOSinfo() {
      String var0 = "";
      String var1 = PropertyUtil.getSystemProperty("os.name");
      if (var1 != null) {
         var0 = "os.name=" + var1 + "\n";
      }

      if ((var1 = PropertyUtil.getSystemProperty("os.arch")) != null) {
         var0 = var0 + "os.arch=" + var1 + "\n";
      }

      if ((var1 = PropertyUtil.getSystemProperty("os.version")) != null) {
         var0 = var0 + "os.version=" + var1;
      }

      return var0;
   }

   private static String buildJvmVersion() {
      String var0 = "";
      String var1 = PropertyUtil.getSystemProperty("java.vendor");
      if (var1 != null) {
         var0 = "java.vendor=" + var1;
      }

      if ((var1 = PropertyUtil.getSystemProperty("java.runtime.version")) != null) {
         var0 = var0 + "\njava.runtime.version=" + var1;
      }

      if ((var1 = PropertyUtil.getSystemProperty("java.fullversion")) != null) {
         var0 = var0 + "\njava.fullversion=" + var1;
      }

      if ((var1 = PropertyUtil.getSystemProperty("user.dir")) != null) {
         var0 = var0 + "\nuser.dir=" + var1;
      }

      return var0;
   }

   public int getEncryptionBlockSize() {
      return this.rawStoreFactory.getEncryptionBlockSize();
   }

   public String getVersionedName(String var1, long var2) {
      return var1.concat(".G".concat(Long.toString(var2)));
   }

   public long getMaxContainerId() throws StandardException {
      return this.findMaxContainerId();
   }

   synchronized long getNextId() {
      return (long)(this.nextContainerId++);
   }

   int random() {
      return this.databaseEncrypted ? this.rawStoreFactory.random() : 0;
   }

   void fileToRemove(StorageFile var1, boolean var2) {
      if (this.postRecoveryRemovedFiles == null) {
         this.postRecoveryRemovedFiles = new Hashtable();
      }

      String var3 = null;
      synchronized(this) {
         this.actionCode = 7;
         this.actionFile = var1;

         try {
            var3 = (String)this.run();
         } catch (Exception var11) {
         } finally {
            this.actionFile = null;
         }
      }

      if (var2) {
         this.postRecoveryRemovedFiles.put(var3, var1);
      } else {
         this.postRecoveryRemovedFiles.remove(var3);
      }

   }

   public void postRecovery() throws StandardException {
      DaemonService var1 = this.rawStoreFactory.getDaemon();
      if (var1 != null) {
         if (this.postRecoveryRemovedFiles != null) {
            synchronized(this) {
               this.actionCode = 8;

               try {
                  this.run();
               } catch (Exception var5) {
               }
            }

            this.postRecoveryRemovedFiles = null;
         }

      }
   }

   public void setupCacheCleaner(DaemonService var1) {
      this.containerCache.useDaemonService(var1);
      this.pageCache.useDaemonService(var1);
   }

   public void freezePersistentStore() throws StandardException {
      synchronized(this.freezeSemaphore) {
         if (this.isFrozen) {
            throw StandardException.newException("XSRS0.S", new Object[0]);
         } else {
            this.isFrozen = true;

            try {
               while(this.writersInProgress > 0) {
                  try {
                     this.freezeSemaphore.wait();
                  } catch (InterruptedException var4) {
                     InterruptStatus.setInterrupted();
                  }
               }
            } catch (RuntimeException var5) {
               this.isFrozen = false;
               this.freezeSemaphore.notifyAll();
               throw var5;
            }

         }
      }
   }

   public void unfreezePersistentStore() {
      synchronized(this.freezeSemaphore) {
         this.isFrozen = false;
         this.freezeSemaphore.notifyAll();
      }
   }

   public void writeInProgress() throws StandardException {
      synchronized(this.freezeSemaphore) {
         while(this.isFrozen) {
            try {
               this.freezeSemaphore.wait();
            } catch (InterruptedException var4) {
               InterruptStatus.setInterrupted();
            }
         }

         ++this.writersInProgress;
      }
   }

   public void writeFinished() {
      synchronized(this.freezeSemaphore) {
         --this.writersInProgress;
         this.freezeSemaphore.notifyAll();
      }
   }

   public void backupDataFiles(Transaction var1, File var2) throws StandardException {
      String[] var3 = this.getContainerNames();
      if (var3 != null) {
         LockingPolicy var4 = var1.newLockingPolicy(0, 0, false);
         long var5 = 0L;

         for(int var7 = var3.length - 1; var7 >= 0; --var7) {
            long var8;
            try {
               var8 = Long.parseLong(var3[var7].substring(1, var3[var7].length() - 4), 16);
            } catch (Throwable var12) {
               continue;
            }

            ContainerKey var10 = new ContainerKey(var5, var8);
            RawContainerHandle var11 = this.openDroppedContainer((RawTransaction)var1, var10, var4, 8);
            if (var11 != null) {
               var11.backupContainer(var2.getPath());
               var11.close();
            }
         }
      }

   }

   synchronized String[] getContainerNames() {
      this.actionCode = 14;

      try {
         return (String[])this.run();
      } catch (Exception var2) {
         return null;
      }
   }

   private void restoreDataDirectory(String var1) throws StandardException {
      File var2 = new File(var1);
      String[] var3 = var2.list();
      if (var3 == null) {
         throw StandardException.newException("XSDG6.D", new Object[]{var2});
      } else {
         boolean var4 = false;

         for(int var5 = 0; var5 < var3.length; ++var5) {
            if (var3[var5].startsWith("seg")) {
               File var6 = new File(var2, var3[var5]);
               boolean var7 = Boolean.valueOf(var6.exists());
               if (var7) {
                  boolean var8 = Boolean.valueOf(var6.isDirectory());
                  if (var8) {
                     var4 = true;
                     break;
                  }
               }
            }
         }

         if (!var4) {
            throw StandardException.newException("XSDG6.D", new Object[]{var2});
         } else {
            synchronized(this) {
               this.actionCode = 13;
               this.backupRoot = var2;
               this.bfilelist = var3;

               try {
                  this.run();
               } catch (IOException var14) {
                  throw StandardException.plainWrapException(var14);
               } finally {
                  this.backupRoot = null;
                  this.bfilelist = null;
               }

            }
         }
      }
   }

   private void privRestoreDataDirectory() throws StandardException {
      StorageFile var2 = this.storageFactory.newStorageFile((String)null);
      String[] var3 = var2.list();
      if (var3 != null) {
         for(int var4 = 0; var4 < var3.length; ++var4) {
            if (var3[var4].startsWith("seg") || "LUCENE".equals(var3[var4])) {
               StorageFile var1 = this.storageFactory.newStorageFile(var3[var4]);
               if (!var1.deleteAll()) {
                  throw StandardException.newException("XSDG7.D", new Object[]{var1});
               }
            }
         }
      }

      for(int var8 = 0; var8 < this.bfilelist.length; ++var8) {
         if (!this.bfilelist[var8].startsWith("seg") && !"LUCENE".equals(this.bfilelist[var8])) {
            if (this.databaseEncrypted && this.bfilelist[var8].startsWith("verifyKey.dat")) {
               File var9 = new File(this.backupRoot, this.bfilelist[var8]);
               StorageFile var6 = this.storageFactory.newStorageFile(this.bfilelist[var8]);
               if (!FileUtil.copyFile(this.writableStorageFactory, var9, var6)) {
                  throw StandardException.newException("XSDG8.D", new Object[]{this.bfilelist[var8], var6});
               }
            }
         } else {
            StorageFile var7 = this.storageFactory.newStorageFile(this.bfilelist[var8]);
            File var5 = new File(this.backupRoot, this.bfilelist[var8]);
            if (!FileUtil.copyDirectory(this.writableStorageFactory, var5, var7)) {
               throw StandardException.newException("XSDG8.D", new Object[]{var5, var7});
            }
         }
      }

   }

   public boolean isReadOnly() {
      return this.readOnly;
   }

   public boolean luceneLoaded() throws StandardException {
      StorageFactory var1 = this.getStorageFactory();
      StorageFile var2 = var1.newStorageFile("LUCENE");
      return var2.exists();
   }

   public StorageFactory getStorageFactory() {
      return this.storageFactory;
   }

   public final Object run() throws IOException, StandardException {
      switch (this.actionCode) {
         case 2:
            StorageFile var1 = this.storageFactory.getTempDir();
            if (var1 != null) {
               var1.deleteAll();
            }

            return null;
         case 3:
         case 4:
            StringBuffer var17 = new StringBuffer("seg");
            var17.append(this.containerId.getSegmentId());
            var17.append(this.storageFactory.getSeparator());
            if (this.actionCode == 3) {
               var17.append((char)(this.stub ? 'd' : 'c'));
               var17.append(Long.toHexString(this.containerId.getContainerId()));
               var17.append(".dat");
            } else {
               var17.append((char)(this.stub ? 'D' : 'C'));
               var17.append(Long.toHexString(this.containerId.getContainerId()));
               var17.append(".DAT");
            }

            return this.storageFactory.newStorageFile(var17.toString());
         case 5:
            long var16 = 1L;
            StorageFile var20 = this.storageFactory.newStorageFile("seg0");
            if (var20.exists() && var20.isDirectory()) {
               String[] var21 = var20.list();

               for(int var22 = var21.length - 1; var22 >= 0; --var22) {
                  try {
                     long var23 = Long.parseLong(var21[var22].substring(1, var21[var22].length() - 4), 16);
                     if (var23 > var16) {
                        var16 = var23;
                     }
                  } catch (Throwable var11) {
                  }
               }
            }

            return var16;
         case 6:
            boolean var15 = this.actionFile.exists() && this.actionFile.delete();
            this.actionFile = null;
            return var15 ? this : null;
         case 7:
            String var14 = this.actionFile.getPath();
            this.actionFile = null;
            return var14;
         case 8:
            Enumeration var13 = this.postRecoveryRemovedFiles.elements();

            while(var13.hasMoreElements()) {
               StorageFile var18 = (StorageFile)var13.nextElement();
               if (var18.exists()) {
                  boolean var19 = var18.delete();
               }
            }

            return null;
         case 9:
            char var12 = this.storageFactory.getSeparator();
            StorageFile var3 = this.storageFactory.newStorageFile((String)null);
            String[] var4 = var3.list();

            for(int var5 = var4.length - 1; var5 >= 0; --var5) {
               if (var4[var5].startsWith("seg")) {
                  StorageFile var6 = this.storageFactory.newStorageFile(var3, var4[var5]);
                  if (var6.exists() && var6.isDirectory()) {
                     String[] var7 = var6.list();

                     for(int var8 = var7.length - 1; var8 >= 0; --var8) {
                        if (var7[var8].startsWith("D") || var7[var8].startsWith("d")) {
                           StorageFile var9 = this.storageFactory.newStorageFile(var3, var4[var5] + var12 + var7[var8]);
                           boolean var10 = var9.delete();
                        }
                     }
                  }
               }
            }
         default:
            return null;
         case 10:
            this.readOnly = this.storageFactory.isReadOnlyDatabase();
            this.supportsRandomAccess = this.storageFactory.supportsRandomAccess();
            return null;
         case 11:
            this.privGetJBMSLockOnDB();
            return null;
         case 12:
            this.privReleaseJBMSLockOnDB();
            return null;
         case 13:
            this.privRestoreDataDirectory();
            return null;
         case 14:
            StorageFile var2 = this.storageFactory.newStorageFile("seg0");
            return var2.exists() && var2.isDirectory() ? var2.list() : null;
      }
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
}
