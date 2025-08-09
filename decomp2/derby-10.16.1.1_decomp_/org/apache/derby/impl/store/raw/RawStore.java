package org.apache.derby.impl.store.raw;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Properties;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.crypto.CipherFactory;
import org.apache.derby.iapi.services.crypto.CipherFactoryBuilder;
import org.apache.derby.iapi.services.crypto.CipherProvider;
import org.apache.derby.iapi.services.daemon.DaemonFactory;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.monitor.PersistentService;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.ScanHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.UndoHandler;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.store.raw.xact.TransactionFactory;
import org.apache.derby.iapi.store.replication.master.MasterFactory;
import org.apache.derby.iapi.store.replication.slave.SlaveFactory;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.services.monitor.UpdateServiceProperties;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.io.WritableStorageFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public final class RawStore implements RawStoreFactory, ModuleControl, ModuleSupportable {
   private static final String BACKUP_HISTORY = "BACKUP.HISTORY";
   protected TransactionFactory xactFactory;
   protected DataFactory dataFactory;
   protected LogFactory logFactory;
   private SlaveFactory slaveFactory;
   private StorageFactory storageFactory;
   private SecureRandom random;
   private boolean isEncryptedDatabase;
   private CipherProvider encryptionEngine;
   private CipherProvider decryptionEngine;
   private CipherProvider newEncryptionEngine;
   private CipherProvider newDecryptionEngine;
   private CipherFactory currentCipherFactory;
   private CipherFactory newCipherFactory = null;
   private int counter_encrypt;
   private int counter_decrypt;
   private int encryptionBlockSize = 8;
   protected DaemonService rawStoreDaemon;
   private int actionCode;
   private static final int FILE_WRITER_ACTION = 1;
   private StorageFile actionStorageFile;
   private StorageFile actionToStorageFile;
   private boolean actionAppend;
   private static final int REGULAR_FILE_EXISTS_ACTION = 2;
   private File actionRegularFile;
   private static final int STORAGE_FILE_EXISTS_ACTION = 3;
   private static final int REGULAR_FILE_DELETE_ACTION = 4;
   private static final int REGULAR_FILE_MKDIRS_ACTION = 5;
   private static final int REGULAR_FILE_IS_DIRECTORY_ACTION = 6;
   private static final int REGULAR_FILE_REMOVE_DIRECTORY_ACTION = 7;
   private static final int REGULAR_FILE_RENAME_TO_ACTION = 8;
   private File actionRegularFile2;
   private static final int COPY_STORAGE_DIRECTORY_TO_REGULAR_ACTION = 9;
   private byte[] actionBuffer;
   private String[] actionFilter;
   private boolean actionCopySubDirs;
   private static final int COPY_REGULAR_DIRECTORY_TO_STORAGE_ACTION = 10;
   private static final int COPY_REGULAR_FILE_TO_STORAGE_ACTION = 11;
   private static final int REGULAR_FILE_LIST_DIRECTORY_ACTION = 12;
   private static final int STORAGE_FILE_LIST_DIRECTORY_ACTION = 13;
   private static final int COPY_STORAGE_FILE_TO_REGULAR_ACTION = 14;
   private static final int REGULAR_FILE_GET_CANONICALPATH_ACTION = 15;
   private static final int STORAGE_FILE_GET_CANONICALPATH_ACTION = 16;
   private static final int COPY_STORAGE_FILE_TO_STORAGE_ACTION = 17;
   private static final int STORAGE_FILE_DELETE_ACTION = 18;
   private static final int README_FILE_OUTPUTSTREAM_WRITER_ACTION = 19;
   public static final String TEST_REENCRYPT_CRASH_BEFORE_COMMT = null;
   public static final String TEST_REENCRYPT_CRASH_AFTER_COMMT = null;
   public static final String TEST_REENCRYPT_CRASH_AFTER_SWITCH_TO_NEWKEY = null;
   public static final String TEST_REENCRYPT_CRASH_AFTER_CHECKPOINT = null;
   public static final String TEST_REENCRYPT_CRASH_AFTER_RECOVERY_UNDO_LOGFILE_DELETE = null;
   public static final String TEST_REENCRYPT_CRASH_AFTER_RECOVERY_UNDO_REVERTING_KEY = null;
   public static final String TEST_REENCRYPT_CRASH_BEFORE_RECOVERY_FINAL_CLEANUP = null;

   public boolean canSupport(Properties var1) {
      return true;
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      boolean var3 = false;
      boolean var4 = false;
      String var5 = var2.getProperty("replication.slave.mode");
      if (var5 != null && var5.equals("slavemode")) {
         var4 = true;
      }

      DaemonFactory var6 = (DaemonFactory)startSystemModule("org.apache.derby.iapi.services.daemon.DaemonFactory");
      this.rawStoreDaemon = var6.createNewDaemon("rawStoreDaemon");
      this.xactFactory = (TransactionFactory)bootServiceModule(var1, this, this.getTransactionFactoryModule(), var2);
      this.dataFactory = (DataFactory)bootServiceModule(var1, this, this.getDataFactoryModule(), var2);
      this.storageFactory = this.dataFactory.getStorageFactory();
      String var7 = null;
      if (var2 != null) {
         var7 = var2.getProperty("createFrom");
         if (var7 == null) {
            var7 = var2.getProperty("restoreFrom");
         }

         if (var7 == null) {
            var7 = var2.getProperty("rollForwardRecoveryFrom");
         }
      }

      if (var1) {
         var3 = this.setupEncryptionEngines(var1, var2);
      }

      this.dataFactory.setRawStoreFactory(this, var1, var2);
      this.xactFactory.setRawStoreFactory(this);
      if (var2 instanceof UpdateServiceProperties && this.storageFactory instanceof WritableStorageFactory) {
         ((UpdateServiceProperties)var2).setStorageFactory((WritableStorageFactory)this.storageFactory);
      }

      this.logFactory = (LogFactory)findServiceModule(this, this.getLogFactoryModule());
      if (var7 != null) {
         this.restoreRemainingFromBackup(var7);
      }

      String var8 = var2.getProperty("logDevice");
      if (var8 != null) {
         if (!this.isReadOnly() && (var1 || !var8.equals(this.logFactory.getCanonicalLogPath()) || var7 != null)) {
            var2.put("logDevice", this.logFactory.getCanonicalLogPath());
            var2.put("derby.storage.logDeviceWhenBackedUp", this.logFactory.getCanonicalLogPath());
         }
      } else if (var7 != null && this.logFactory.getCanonicalLogPath() != null) {
         var2.put("logDevice", this.logFactory.getCanonicalLogPath());
      } else {
         var2.remove("derby.storage.logDeviceWhenBackedUp");
      }

      if (var7 != null) {
         ((UpdateServiceProperties)var2).saveServiceProperties();
      }

      if (!var1) {
         if (var2.getProperty("derby.storage.databaseEncryptionStatus") != null) {
            this.handleIncompleteDbCryptoOperation(var2);
         }

         var3 = this.setupEncryptionEngines(var1, var2);
      }

      if (this.isEncryptedDatabase) {
         this.logFactory.setDatabaseEncrypted(true, false);
         this.dataFactory.setDatabaseEncrypted(true);
      }

      this.logFactory.setRawStoreFactory(this);
      if (var4) {
         this.slaveFactory = (SlaveFactory)bootServiceModule(var1, this, this.getSlaveFactoryModule(), var2);
         this.slaveFactory.startSlave(this, this.logFactory);
      }

      this.logFactory.recover(this.dataFactory, this.xactFactory);
      if (var3) {
         this.applyBulkCryptoOperation(var2, this.newCipherFactory);
      }

   }

   public void stop() {
      if (this.rawStoreDaemon != null) {
         this.rawStoreDaemon.stop();
      }

      if (this.logFactory != null) {
         try {
            if (this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, false) && this.dataFactory != null) {
               this.dataFactory.removeStubsOK();
            }
         } catch (StandardException var2) {
            this.markCorrupt(var2);
         }

      }
   }

   public boolean isReadOnly() {
      return this.dataFactory.isReadOnly();
   }

   public LockFactory getLockFactory() {
      return this.xactFactory.getLockFactory();
   }

   public TransactionFactory getXactFactory() {
      return this.xactFactory;
   }

   public void setUndoInsertEventHandler(UndoHandler var1) throws StandardException {
      this.dataFactory.setUndoInsertEventHandler(var1);
   }

   public Object getXAResourceManager() throws StandardException {
      return this.xactFactory.getXAResourceManager();
   }

   public Transaction startGlobalTransaction(ContextManager var1, int var2, byte[] var3, byte[] var4) throws StandardException {
      return this.xactFactory.startGlobalTransaction(this, var1, var2, var3, var4);
   }

   public Transaction startTransaction(ContextManager var1, String var2) throws StandardException {
      return this.xactFactory.startTransaction(this, var1, var2);
   }

   public Transaction startNestedReadOnlyUserTransaction(Transaction var1, CompatibilitySpace var2, ContextManager var3, String var4) throws StandardException {
      return this.xactFactory.startNestedReadOnlyUserTransaction(this, (RawTransaction)var1, var2, var3, var4);
   }

   public Transaction startNestedUpdateUserTransaction(Transaction var1, ContextManager var2, String var3, boolean var4) throws StandardException {
      return this.xactFactory.startNestedUpdateUserTransaction(this, (RawTransaction)var1, var2, var3, var4);
   }

   public Transaction findUserTransaction(ContextManager var1, String var2) throws StandardException {
      return this.xactFactory.findUserTransaction(this, var1, var2);
   }

   public Transaction startInternalTransaction(ContextManager var1) throws StandardException {
      return this.xactFactory.startInternalTransaction(this, var1);
   }

   public void checkpoint() throws StandardException {
      this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, true);
   }

   public void startReplicationMaster(String var1, String var2, int var3, String var4) throws StandardException {
      if (this.isReadOnly()) {
         throw StandardException.newException("XRE00", new Object[0]);
      } else {
         RawTransaction var5 = this.xactFactory.findUserTransaction(this, getContextService().getCurrentContextManager(), "UserTransaction");
         if (var5.isBlockingBackup()) {
            throw StandardException.newException("XRE23", new Object[0]);
         } else {
            Properties var6 = new Properties();
            var6.setProperty("derby.__rt.replication.master.mode", var4);
            MasterFactory var7 = (MasterFactory)bootServiceModule(true, this, this.getMasterFactoryModule(), var6);
            var7.startMaster(this, this.dataFactory, this.logFactory, var2, var3, var1);
         }
      }
   }

   public void stopReplicationMaster() throws StandardException {
      MasterFactory var1 = null;
      if (this.isReadOnly()) {
         throw StandardException.newException("XRE00", new Object[0]);
      } else {
         try {
            var1 = (MasterFactory)findServiceModule(this, this.getMasterFactoryModule());
         } catch (StandardException var3) {
            throw StandardException.newException("XRE07", new Object[0]);
         }

         var1.stopMaster();
      }
   }

   public void failover(String var1) throws StandardException {
      MasterFactory var2 = null;
      if (this.isReadOnly()) {
         throw StandardException.newException("XRE00", new Object[0]);
      } else {
         try {
            var2 = (MasterFactory)findServiceModule(this, this.getMasterFactoryModule());
         } catch (StandardException var4) {
            throw StandardException.newException("XRE07", new Object[0]);
         }

         var2.startFailover();
      }
   }

   public void freeze() throws StandardException {
      this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, true);
      this.dataFactory.freezePersistentStore();
      this.logFactory.freezePersistentStore();
   }

   public void unfreeze() throws StandardException {
      this.logFactory.unfreezePersistentStore();
      this.dataFactory.unfreezePersistentStore();
   }

   public void backup(String var1, boolean var2) throws StandardException {
      if (var1 != null && !var1.equals("")) {
         String var3 = null;

         try {
            URL var4 = new URL(var1);
            var3 = var4.getFile();
         } catch (MalformedURLException var8) {
         }

         if (var3 != null) {
            var1 = var3;
         }

         RawTransaction var10 = this.xactFactory.findUserTransaction(this, getContextService().getCurrentContextManager(), "UserTransaction");

         try {
            if (var10.isBlockingBackup()) {
               throw StandardException.newException("XSRSB.S", new Object[0]);
            }

            if (!this.xactFactory.blockBackupBlockingOperations(var2)) {
               throw StandardException.newException("XSRSA.S", new Object[0]);
            }

            this.backup(var10, new File(var1));
         } finally {
            this.xactFactory.unblockBackupBlockingOperations();
         }

      } else {
         throw StandardException.newException("XSRS6.S", new Object[]{(File)null});
      }
   }

   public synchronized void backup(Transaction var1, File var2) throws StandardException {
      if (!this.privExists(var2)) {
         this.createBackupDirectory(var2);
      } else {
         if (!this.privIsDirectory(var2)) {
            throw StandardException.newException("XSRS1.S", new Object[]{var2});
         }

         if (this.privExists(new File(var2, "service.properties"))) {
            throw StandardException.newException("XSRSC.S", new Object[]{var2});
         }
      }

      boolean var3 = true;
      boolean var4 = false;
      boolean var5 = false;
      File var6 = null;
      File var7 = null;
      OutputStreamWriter var8 = null;
      StorageFile var9 = null;
      File var10 = null;
      LogInstant var11 = this.logFactory.getFirstUnflushedInstant();

      try {
         StorageFile var12 = this.storageFactory.newStorageFile((String)null);
         String var13 = this.storageFactory.getCanonicalName();
         String var14 = StringUtil.shortDBName(var13, this.storageFactory.getSeparator());
         var8 = this.privFileWriter(this.storageFactory.newStorageFile("BACKUP.HISTORY"), true);
         var7 = new File(var2, var14);
         this.logHistory(var8, MessageService.getTextMessage("D004", new Object[]{var13, this.getFilePath(var7)}));
         if (this.privExists(var7)) {
            var6 = new File(var2, var14 + ".OLD");
            if (this.privExists(var6)) {
               if (this.privIsDirectory(var6)) {
                  this.privRemoveDirectory(var6);
               } else {
                  this.privDelete(var6);
               }
            }

            if (!this.privRenameTo(var7, var6)) {
               var5 = true;
               throw StandardException.newException("XSRS4.S", new Object[]{var7, var6});
            }

            this.logHistory(var8, MessageService.getTextMessage("D005", new Object[]{this.getFilePath(var7), this.getFilePath(var6)}));
            var4 = true;
         }

         this.createBackupDirectory(var7);
         var9 = this.storageFactory.newStorageFile("BACKUP.HISTORY");
         var10 = new File(var7, "BACKUP.HISTORY");
         if (!this.privCopyFile(var9, var10)) {
            throw StandardException.newException("XSRS5.S", new Object[]{var9, var10});
         } else {
            StorageFile var15 = this.storageFactory.newStorageFile("jar");
            if (this.privExists(var15)) {
               String[] var16 = this.privList(var15);
               File var17 = new File(var7, "jar");
               this.createBackupDirectory(var17);
               LanguageConnectionContext var18 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
               boolean var19 = var18.getDataDictionary().checkVersion(210, (String)null);
               if (var19) {
                  for(int var48 = 0; var48 < var16.length; ++var48) {
                     StorageFile var50 = this.storageFactory.newStorageFile(var15, var16[var48]);
                     File var52 = new File(var17, var16[var48]);
                     if (!this.privIsDirectory(new File(var50.getPath())) && !this.privCopyFile(var50, var52)) {
                        throw StandardException.newException("XSRS5.S", new Object[]{var50, var52});
                     }
                  }
               } else {
                  for(int var20 = 0; var20 < var16.length; ++var20) {
                     StorageFile var21 = this.storageFactory.newStorageFile(var15, var16[var20]);
                     File var22 = new File(var17, var16[var20]);
                     if (!this.privCopyDirectory(var21, var22, (byte[])null, (String[])null, false)) {
                        throw StandardException.newException("XSRS5.S", new Object[]{var21, var22});
                     }
                  }
               }
            }

            StorageFile var40 = this.logFactory.getLogDirectory();

            try {
               String var41 = getServiceName(this);
               PersistentService var43 = getMonitor().getServiceType(this);
               String var46 = var43.getCanonicalServiceName(var41);
               Properties var49 = var43.getServiceProperties(var46, (Properties)null);
               StorageFile var51 = this.storageFactory.newStorageFile("log");
               if (!var40.equals(var51)) {
                  var49.remove("logDevice");
                  this.logHistory(var8, MessageService.getTextMessage("D007", new Object[0]));
               }

               var43.saveServiceProperties(var7.getPath(), var49);
            } catch (StandardException var37) {
               String var10002 = MessageService.getTextMessage("D008", new Object[0]);
               this.logHistory(var8, var10002 + var37);
               return;
            }

            StorageFile var42 = this.storageFactory.newStorageFile("verifyKey.dat");
            if (this.privExists(var42)) {
               File var44 = new File(var7, "verifyKey.dat");
               if (!this.privCopyFile(var42, var44)) {
                  throw StandardException.newException("XSRS5.S", new Object[]{var42, var44});
               }
            }

            File var45 = new File(var7, "log");
            if (this.privExists(var45)) {
               this.privRemoveDirectory(var45);
            }

            this.createBackupDirectory(var45);
            this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, true);
            this.logFactory.startLogBackup(var45);
            File var47 = new File(var7, "seg0");
            this.createBackupDirectory(var47);
            this.dataFactory.backupDataFiles(var1, var47);
            this.logHistory(var8, MessageService.getTextMessage("D006", new Object[]{this.getFilePath(var47)}));
            this.logFactory.endLogBackup(var45);
            this.logHistory(var8, MessageService.getTextMessage("D009", new Object[]{this.getFilePath(var40), this.getFilePath(var45)}));
            var3 = false;
         }
      } catch (IOException var38) {
         throw StandardException.newException("XSRS7.S", var38, new Object[0]);
      } finally {
         try {
            if (var3) {
               this.logFactory.abortLogBackup();
               if (!var5) {
                  this.privRemoveDirectory(var7);
               }

               if (var4) {
                  this.privRenameTo(var6, var7);
               }

               this.logHistory(var8, MessageService.getTextMessage("D010", new Object[0]));
            } else {
               if (var4 && this.privExists(var6)) {
                  this.privRemoveDirectory(var6);
                  this.logHistory(var8, MessageService.getTextMessage("D011", new Object[]{this.getFilePath(var6)}));
               }

               this.logHistory(var8, MessageService.getTextMessage("D012", new Object[]{var11}));
               if (!this.privCopyFile(var9, var10)) {
                  throw StandardException.newException("XSRS5.S", new Object[]{var9, var10});
               }
            }

            var8.close();
         } catch (IOException var36) {
            try {
               var8.close();
            } catch (IOException var35) {
            }

            throw StandardException.newException("XSRS7.S", var36, new Object[0]);
         }
      }
   }

   private void createBackupDirectory(File var1) throws StandardException {
      boolean var2 = false;
      IOException var3 = null;

      try {
         var2 = this.privMkdirs(var1);
      } catch (IOException var5) {
         var3 = var5;
      }

      if (!var2) {
         throw StandardException.newException("XSRS6.S", var3, new Object[]{var1});
      }
   }

   public void backupAndEnableLogArchiveMode(String var1, boolean var2, boolean var3) throws StandardException {
      boolean var4 = false;

      try {
         if (!this.logFactory.logArchived()) {
            this.logFactory.enableLogArchiveMode();
            var4 = true;
         }

         this.backup(var1, var3);
         if (var2) {
            this.logFactory.deleteOnlineArchivedLogFiles();
         }

      } catch (Throwable var6) {
         if (var4) {
            this.logFactory.disableLogArchiveMode();
         }

         throw StandardException.plainWrapException(var6);
      }
   }

   public void disableLogArchiveMode(boolean var1) throws StandardException {
      this.logFactory.disableLogArchiveMode();
      if (var1) {
         this.logFactory.deleteOnlineArchivedLogFiles();
      }

   }

   private void restoreRemainingFromBackup(String var1) throws StandardException {
      File var2 = new File(var1, "jar");
      StorageFile var3 = this.storageFactory.newStorageFile("jar");
      if (!this.privExists(var3) && this.privExists(var2) && !this.privCopyDirectory(var2, var3)) {
         throw StandardException.newException("XBM0Z.D", new Object[]{var2, var3});
      } else {
         StorageFile var4 = this.storageFactory.newStorageFile("BACKUP.HISTORY");
         File var5 = new File(var1, "BACKUP.HISTORY");
         if (this.privExists(var5) && !this.privExists(var4) && !this.privCopyFile(var5, var4)) {
            throw StandardException.newException("XSRS5.S", new Object[]{var5, var4});
         }
      }
   }

   public void idle() throws StandardException {
      this.dataFactory.idle();
   }

   public TransactionInfo[] getTransactionInfo() {
      return this.xactFactory.getTransactionInfo();
   }

   public ScanHandle openFlushedScan(DatabaseInstant var1, int var2) throws StandardException {
      return this.logFactory.openFlushedScan(var1, var2);
   }

   public DaemonService getDaemon() {
      return this.rawStoreDaemon;
   }

   public void createFinished() throws StandardException {
      this.xactFactory.createFinished();
      this.dataFactory.createFinished();
   }

   public void getRawStoreProperties(PersistentSet var1) throws StandardException {
      this.logFactory.getLogFactoryProperties(var1);
   }

   public void freezePersistentStore() throws StandardException {
      this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, true);
      this.logFactory.freezePersistentStore();
   }

   public void unfreezePersistentStore() throws StandardException {
      this.logFactory.unfreezePersistentStore();
   }

   private boolean setupEncryptionEngines(boolean var1, Properties var2) throws StandardException {
      boolean var3 = isTrue(var2, "decryptDatabase");
      boolean var4 = isTrue(var2, "dataEncryption");
      boolean var5 = false;
      if (!var1) {
         String var6 = getServiceName(this);
         PersistentService var7 = getMonitor().getServiceType(this);
         String var8 = var7.getCanonicalServiceName(var6);
         Properties var9 = var7.getServiceProperties(var8, (Properties)null);
         this.isEncryptedDatabase = isTrue(var9, "dataEncryption");
         if (!this.isEncryptedDatabase) {
            if (var4 && var3) {
               throw StandardException.newException("XJ048.C", new Object[]{"decryptDatabase, dataEncryption"});
            }
         } else {
            var5 = isSet(var2, "newBootPassword") || isSet(var2, "newEncryptionKey");
            var4 = var5;
         }

         if ((var4 || var3) && this.isReadOnly()) {
            throw StandardException.newException("XBCXQ.S", new Object[0]);
         }
      }

      if (this.isEncryptedDatabase || var4) {
         boolean var10 = var1 || var4 && !var5;
         CipherFactoryBuilder var11 = (CipherFactoryBuilder)startSystemModule("org.apache.derby.iapi.services.crypto.CipherFactoryBuilder");
         this.currentCipherFactory = var11.createCipherFactory(var10, var2, false);
         this.currentCipherFactory.verifyKey(var10, this.storageFactory, var2);
         this.encryptionEngine = this.currentCipherFactory.createNewCipher(1);
         if (var10) {
            this.encryptionBlockSize = this.encryptionEngine.getEncryptionBlockSize();
            if (var1) {
               var2.put("derby.encryptionBlockSize", String.valueOf(this.encryptionBlockSize));
            }
         } else if (isSet(var2, "derby.encryptionBlockSize")) {
            this.encryptionBlockSize = Integer.parseInt(var2.getProperty("derby.encryptionBlockSize"));
         } else {
            this.encryptionBlockSize = this.encryptionEngine.getEncryptionBlockSize();
         }

         this.decryptionEngine = this.currentCipherFactory.createNewCipher(2);
         this.random = this.currentCipherFactory.getSecureRandom();
         if (var4) {
            if (var5) {
               this.newCipherFactory = var11.createCipherFactory(var10, var2, true);
               this.newDecryptionEngine = this.newCipherFactory.createNewCipher(2);
               this.newEncryptionEngine = this.newCipherFactory.createNewCipher(1);
            } else {
               this.newDecryptionEngine = this.decryptionEngine;
               this.newEncryptionEngine = this.encryptionEngine;
            }
         }

         if (var1) {
            this.currentCipherFactory.saveProperties(var2);
            this.isEncryptedDatabase = true;
         }
      }

      return !var1 && (var4 || this.isEncryptedDatabase && var3);
   }

   public int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5, boolean var6) throws StandardException {
      if (this.encryptionEngine == null && this.newEncryptionEngine == null) {
         throw StandardException.newException("XSAI3.S", new Object[0]);
      } else {
         ++this.counter_encrypt;
         return var6 ? this.newEncryptionEngine.encrypt(var1, var2, var3, var4, var5) : this.encryptionEngine.encrypt(var1, var2, var3, var4, var5);
      }
   }

   public int decrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException {
      if (this.isEncryptedDatabase && this.decryptionEngine != null) {
         ++this.counter_decrypt;
         return this.decryptionEngine.decrypt(var1, var2, var3, var4, var5);
      } else {
         throw StandardException.newException("XSAI3.S", new Object[0]);
      }
   }

   public int getEncryptionBlockSize() {
      return this.encryptionBlockSize;
   }

   public int random() {
      return this.isEncryptedDatabase ? this.random.nextInt() : 0;
   }

   public Serializable changeBootPassword(Properties var1, Serializable var2) throws StandardException {
      if (this.isReadOnly()) {
         throw StandardException.newException("XBCX9.S", new Object[0]);
      } else if (!this.isEncryptedDatabase) {
         throw StandardException.newException("XBCX8.S", new Object[0]);
      } else if (var2 == null) {
         throw StandardException.newException("XBCX5.S", new Object[0]);
      } else if (!(var2 instanceof String)) {
         throw StandardException.newException("XBCX6.S", new Object[0]);
      } else {
         String var3 = (String)var2;
         return this.currentCipherFactory.changeBootPassword((String)var2, var1, this.encryptionEngine);
      }
   }

   private void crashOnDebugFlag(String var1, boolean var2) throws StandardException {
   }

   private void applyBulkCryptoOperation(Properties var1, CipherFactory var2) throws StandardException {
      boolean var3 = this.isEncryptedDatabase && isTrue(var1, "decryptDatabase");
      boolean var4 = this.isEncryptedDatabase && (isSet(var1, "newBootPassword") || isSet(var1, "newEncryptionKey"));
      this.cryptoOperationAllowed(var4, var3);
      boolean var5 = isSet(var1, "encryptionKey");
      this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, true);
      RawTransaction var6 = this.xactFactory.startTransaction(this, getContextService().getCurrentContextManager(), "UserTransaction");

      try {
         if (var3) {
            this.dataFactory.decryptAllContainers(var6);
         } else {
            this.dataFactory.encryptAllContainers(var6);
         }

         if (!this.logFactory.isCheckpointInLastLogFile()) {
            this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, true);
         }

         if (var3) {
            this.isEncryptedDatabase = false;
            this.logFactory.setDatabaseEncrypted(false, true);
            this.dataFactory.setDatabaseEncrypted(false);
         } else {
            this.logFactory.setDatabaseEncrypted(true, true);
            if (var4) {
               this.decryptionEngine = this.newDecryptionEngine;
               this.encryptionEngine = this.newEncryptionEngine;
               this.currentCipherFactory = var2;
            } else {
               this.isEncryptedDatabase = true;
               this.dataFactory.setDatabaseEncrypted(true);
            }
         }

         this.logFactory.startNewLogFile();
         var1.put("derby.storage.databaseEncryptionStatus", String.valueOf(1));
         if (var4) {
            if (var5) {
               StorageFile var7 = this.storageFactory.newStorageFile("verifyKey.dat");
               StorageFile var8 = this.storageFactory.newStorageFile("verifyOldKey.dat");
               if (!this.privCopyFile(var7, var8)) {
                  throw StandardException.newException("XSRS5.S", new Object[]{var7, var8});
               }

               this.currentCipherFactory.verifyKey(var4, this.storageFactory, var1);
            } else {
               String var14 = var1.getProperty("encryptedBootPassword");
               if (var14 != null) {
                  var1.put("OldEncryptedBootPassword", var14);
               }
            }
         } else if (var3) {
            var1.put("dataEncryption", "false");
         } else {
            var1.put("derby.encryptionBlockSize", String.valueOf(this.encryptionBlockSize));
         }

         this.currentCipherFactory.saveProperties(var1);
         var6.commit();
         this.logFactory.checkpoint(this, this.dataFactory, this.xactFactory, true);
         var1.put("derby.storage.databaseEncryptionStatus", String.valueOf(3));
         this.dataFactory.removeOldVersionOfContainers();
         if (var3) {
            this.removeCryptoProperties(var1);
         } else if (var4) {
            if (var5) {
               StorageFile var15 = this.storageFactory.newStorageFile("verifyOldKey.dat");
               if (!this.privDelete(var15)) {
                  throw StandardException.newException("XBM0R.D", new Object[]{var15});
               }
            } else {
               var1.remove("OldEncryptedBootPassword");
            }
         }

         var1.remove("derby.storage.databaseEncryptionStatus");
         var6.close();
      } catch (StandardException var12) {
         throw StandardException.newException("XBCXU.S", var12, new Object[]{var12.getMessage()});
      } finally {
         this.newDecryptionEngine = null;
         this.newEncryptionEngine = null;
      }

   }

   public void handleIncompleteDbCryptoOperation(Properties var1) throws StandardException {
      int var2 = 0;
      String var3 = var1.getProperty("derby.storage.databaseEncryptionStatus");
      if (var3 != null) {
         var2 = Integer.parseInt(var3);
      }

      boolean var4 = false;
      boolean var5 = isSet(var1, "dataEncryption") && !isTrue(var1, "dataEncryption");
      if (var2 == 1) {
         if (this.logFactory.isCheckpointInLastLogFile()) {
            var2 = 3;
         } else {
            var2 = 2;
            var1.put("derby.storage.databaseEncryptionStatus", String.valueOf(var2));
         }
      }

      if (var2 == 2) {
         this.logFactory.deleteLogFileAfterCheckpointLogFile();
         StorageFile var6 = this.storageFactory.newStorageFile("verifyKey.dat");
         if (this.privExists(var6)) {
            StorageFile var7 = this.storageFactory.newStorageFile("verifyOldKey.dat");
            if (this.privExists(var7)) {
               if (!this.privCopyFile(var7, var6)) {
                  throw StandardException.newException("XSRS5.S", new Object[]{var7, var6});
               }

               var4 = true;
            } else if (!var5 && !this.privDelete(var6)) {
               throw StandardException.newException("XBM0R.D", new Object[]{var6});
            }
         } else {
            String var9 = var1.getProperty("OldEncryptedBootPassword");
            if (var9 != null) {
               var1.put("encryptedBootPassword", var9);
               var4 = true;
            }
         }

         if (!var5 && !var4) {
            this.removeCryptoProperties(var1);
         }
      }

      if (var2 == 3) {
         this.dataFactory.removeOldVersionOfContainers();
      }

      StorageFile var8 = this.storageFactory.newStorageFile("verifyOldKey.dat");
      if (this.privExists(var8)) {
         if (!this.privDelete(var8)) {
            throw StandardException.newException("XBM0R.D", new Object[]{var8});
         }
      } else {
         var1.remove("OldEncryptedBootPassword");
      }

      if (var5) {
         if (var2 == 2) {
            var1.setProperty("dataEncryption", "true");
         } else {
            this.removeCryptoProperties(var1);
         }
      }

      var1.remove("derby.storage.databaseEncryptionStatus");
   }

   private void cryptoOperationAllowed(boolean var1, boolean var2) throws StandardException {
      String var3;
      if (var2) {
         var3 = "decryptDatabase attribute";
      } else if (var1) {
         var3 = "newBootPassword/newEncryptionKey attribute";
      } else {
         var3 = "dataEncryption attribute on an existing database";
      }

      int var4 = var2 ? 10 : 2;
      this.logFactory.checkVersion(10, var4, var3);
      if (this.xactFactory.hasPreparedXact()) {
         throw StandardException.newException("XBCXO.S", new Object[0]);
      } else if (this.logFactory.logArchived()) {
         throw StandardException.newException("XBCXS.S", new Object[0]);
      }
   }

   public StandardException markCorrupt(StandardException var1) {
      this.logFactory.markCorrupt(var1);
      this.dataFactory.markCorrupt(var1);
      this.xactFactory.markCorrupt(var1);
      return var1;
   }

   public String getTransactionFactoryModule() {
      return "org.apache.derby.iapi.store.raw.xact.TransactionFactory";
   }

   public String getSlaveFactoryModule() {
      return "org.apache.derby.iapi.store.replication.slave.SlaveFactory";
   }

   public String getMasterFactoryModule() {
      return "org.apache.derby.iapi.store.replication.master.MasterFactory";
   }

   public String getDataFactoryModule() {
      return "org.apache.derby.iapi.store.raw.data.DataFactory";
   }

   public String getLogFactoryModule() {
      return "org.apache.derby.iapi.store.raw.log.LogFactory";
   }

   private void logHistory(OutputStreamWriter var1, String var2) throws IOException {
      Date var3 = new Date();
      String var10001 = var3.toString();
      var1.write(var10001 + ":" + var2 + "\n");
      var1.flush();
   }

   private String getFilePath(StorageFile var1) {
      String var2 = this.privGetCanonicalPath(var1);
      return var2 != null ? var2 : var1.getPath();
   }

   private String getFilePath(File var1) {
      String var2 = this.privGetCanonicalPath(var1);
      return var2 != null ? var2 : var1.getPath();
   }

   protected boolean privCopyDirectory(StorageFile var1, File var2) throws StandardException {
      return this.privCopyDirectory(var1, var2, (byte[])null, (String[])null, true);
   }

   protected boolean privCopyDirectory(File var1, StorageFile var2) {
      return this.privCopyDirectory(var1, var2, (byte[])null, (String[])null);
   }

   public long getMaxContainerId() throws StandardException {
      return this.dataFactory.getMaxContainerId();
   }

   public boolean checkVersion(int var1, int var2, String var3) throws StandardException {
      return this.logFactory.checkVersion(var1, var2, var3);
   }

   private void removeCryptoProperties(Properties var1) {
      var1.remove("dataEncryption");
      var1.remove("log_encrypt_algorithm_version");
      var1.remove("data_encrypt_algorithm_version");
      var1.remove("derby.encryptionBlockSize");
      var1.remove("encryptionKeyLength");
      var1.remove("encryptionProvider");
      var1.remove("encryptionAlgorithm");
      var1.remove("encryptedBootPassword");
   }

   private synchronized OutputStreamWriter privFileWriter(StorageFile var1, boolean var2) throws IOException {
      this.actionCode = 1;
      this.actionStorageFile = var1;
      this.actionAppend = var2;

      OutputStreamWriter var3;
      try {
         var3 = (OutputStreamWriter)this.run();
      } catch (StandardException var7) {
         throw this.wrapSE(var7);
      } finally {
         this.actionStorageFile = null;
      }

      return var3;
   }

   private IOException wrapSE(Exception var1) {
      return new IOException(var1.getMessage(), var1);
   }

   private synchronized boolean privExists(File var1) {
      this.actionCode = 2;
      this.actionRegularFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (Exception var7) {
         var3 = false;
      } finally {
         this.actionRegularFile = null;
      }

      return var3;
   }

   private synchronized boolean privExists(StorageFile var1) {
      this.actionCode = 3;
      this.actionStorageFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (Exception var7) {
         var3 = false;
      } finally {
         this.actionStorageFile = null;
      }

      return var3;
   }

   private synchronized OutputStreamWriter privGetOutputStreamWriter(StorageFile var1) throws IOException {
      this.actionCode = 19;
      this.actionStorageFile = var1;

      try {
         return (OutputStreamWriter)this.run();
      } catch (StandardException var3) {
         throw this.wrapSE(var3);
      }
   }

   private synchronized boolean privDelete(File var1) {
      this.actionCode = 4;
      this.actionRegularFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (Exception var7) {
         var3 = false;
      } finally {
         this.actionRegularFile = null;
      }

      return var3;
   }

   private synchronized boolean privDelete(StorageFile var1) {
      this.actionCode = 18;
      this.actionStorageFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (Exception var7) {
         var3 = false;
      } finally {
         this.actionStorageFile = null;
      }

      return var3;
   }

   private synchronized boolean privMkdirs(File var1) throws IOException {
      this.actionCode = 5;
      this.actionRegularFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
      } catch (StandardException var7) {
         throw this.wrapSE(var7);
      } finally {
         this.actionRegularFile = null;
      }

      return var3;
   }

   private synchronized boolean privIsDirectory(File var1) {
      this.actionCode = 6;
      this.actionRegularFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (Exception var7) {
         var3 = false;
      } finally {
         this.actionRegularFile = null;
      }

      return var3;
   }

   private synchronized boolean privRemoveDirectory(File var1) {
      this.actionCode = 7;
      this.actionRegularFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (Exception var7) {
         var3 = false;
      } finally {
         this.actionRegularFile = null;
      }

      return var3;
   }

   private synchronized boolean privRenameTo(File var1, File var2) {
      this.actionCode = 8;
      this.actionRegularFile = var1;
      this.actionRegularFile2 = var2;

      boolean var4;
      try {
         Object var3 = this.run();
         var4 = (Boolean)var3;
         return var4;
      } catch (Exception var8) {
         var4 = false;
      } finally {
         this.actionRegularFile = null;
         this.actionRegularFile2 = null;
      }

      return var4;
   }

   private synchronized boolean privCopyDirectory(StorageFile var1, File var2, byte[] var3, String[] var4, boolean var5) throws StandardException {
      this.actionCode = 9;
      this.actionStorageFile = var1;
      this.actionRegularFile = var2;
      this.actionBuffer = var3;
      this.actionFilter = var4;
      this.actionCopySubDirs = var5;

      boolean var7;
      try {
         Object var6 = this.run();
         var7 = (Boolean)var6;
      } catch (IOException var11) {
         throw StandardException.plainWrapException(var11);
      } finally {
         this.actionStorageFile = null;
         this.actionRegularFile = null;
         this.actionBuffer = null;
         this.actionFilter = null;
      }

      return var7;
   }

   private synchronized boolean privCopyDirectory(File var1, StorageFile var2, byte[] var3, String[] var4) {
      this.actionCode = 10;
      this.actionStorageFile = var2;
      this.actionRegularFile = var1;
      this.actionBuffer = var3;
      this.actionFilter = var4;

      boolean var6;
      try {
         Object var5 = this.run();
         var6 = (Boolean)var5;
         return var6;
      } catch (Exception var10) {
         var6 = false;
      } finally {
         this.actionStorageFile = null;
         this.actionRegularFile = null;
         this.actionBuffer = null;
         this.actionFilter = null;
      }

      return var6;
   }

   private synchronized boolean privCopyFile(File var1, StorageFile var2) {
      this.actionCode = 11;
      this.actionStorageFile = var2;
      this.actionRegularFile = var1;

      boolean var4;
      try {
         Object var3 = this.run();
         var4 = (Boolean)var3;
         return var4;
      } catch (Exception var8) {
         var4 = false;
      } finally {
         this.actionStorageFile = null;
         this.actionRegularFile = null;
      }

      return var4;
   }

   private synchronized boolean privCopyFile(StorageFile var1, File var2) throws StandardException {
      this.actionCode = 14;
      this.actionStorageFile = var1;
      this.actionRegularFile = var2;

      boolean var4;
      try {
         Object var3 = this.run();
         var4 = (Boolean)var3;
      } catch (IOException var8) {
         throw StandardException.plainWrapException(var8);
      } finally {
         this.actionStorageFile = null;
         this.actionRegularFile = null;
      }

      return var4;
   }

   private synchronized boolean privCopyFile(StorageFile var1, StorageFile var2) {
      this.actionCode = 17;
      this.actionStorageFile = var1;
      this.actionToStorageFile = var2;

      boolean var4;
      try {
         Object var3 = this.run();
         var4 = (Boolean)var3;
         return var4;
      } catch (Exception var8) {
         var4 = false;
      } finally {
         this.actionStorageFile = null;
         this.actionToStorageFile = null;
      }

      return var4;
   }

   private synchronized String[] privList(StorageFile var1) {
      this.actionCode = 13;
      this.actionStorageFile = var1;

      Object var3;
      try {
         String[] var2 = (String[])this.run();
         return var2;
      } catch (Exception var7) {
         var3 = null;
      } finally {
         this.actionStorageFile = null;
      }

      return (String[])var3;
   }

   private synchronized String privGetCanonicalPath(StorageFile var1) {
      this.actionCode = 16;
      this.actionStorageFile = var1;

      Object var3;
      try {
         String var2 = (String)this.run();
         return var2;
      } catch (Exception var7) {
         var3 = null;
      } finally {
         this.actionStorageFile = null;
      }

      return (String)var3;
   }

   private synchronized String privGetCanonicalPath(File var1) {
      this.actionCode = 15;
      this.actionRegularFile = var1;

      Object var3;
      try {
         String var2 = (String)this.run();
         return var2;
      } catch (Exception var7) {
         var3 = null;
      } finally {
         this.actionRegularFile = null;
      }

      return (String)var3;
   }

   public final Object run() throws IOException, StandardException {
      switch (this.actionCode) {
         case 1:
            return new OutputStreamWriter(this.actionStorageFile.getOutputStream(this.actionAppend));
         case 2:
            return this.actionRegularFile.exists();
         case 3:
            return this.actionStorageFile.exists();
         case 4:
            return this.actionRegularFile.delete();
         case 5:
            boolean var1 = this.actionRegularFile.mkdirs();
            FileUtil.limitAccessToOwner(this.actionRegularFile);
            return var1;
         case 6:
            return this.actionRegularFile.isDirectory();
         case 7:
            return FileUtil.removeDirectory(this.actionRegularFile);
         case 8:
            return this.actionRegularFile.renameTo(this.actionRegularFile2);
         case 9:
            return FileUtil.copyDirectory(this.storageFactory, this.actionStorageFile, this.actionRegularFile, this.actionBuffer, this.actionFilter, this.actionCopySubDirs);
         case 10:
            return FileUtil.copyDirectory((WritableStorageFactory)this.storageFactory, this.actionRegularFile, this.actionStorageFile, this.actionBuffer, this.actionFilter);
         case 11:
            return FileUtil.copyFile((WritableStorageFactory)this.storageFactory, this.actionRegularFile, this.actionStorageFile);
         case 12:
            return this.actionRegularFile.list();
         case 13:
            return this.actionStorageFile.list();
         case 14:
            return FileUtil.copyFile((StorageFactory)((WritableStorageFactory)this.storageFactory), (StorageFile)this.actionStorageFile, (File)this.actionRegularFile);
         case 15:
            return this.actionRegularFile.getCanonicalPath();
         case 16:
            return this.actionStorageFile.getCanonicalPath();
         case 17:
            return FileUtil.copyFile((WritableStorageFactory)this.storageFactory, this.actionStorageFile, this.actionToStorageFile);
         case 18:
            return this.actionStorageFile.delete();
         case 19:
            return new OutputStreamWriter(this.actionStorageFile.getOutputStream(), "UTF8");
         default:
            return null;
      }
   }

   private static boolean isSet(Properties var0, String var1) {
      return var0.getProperty(var1) != null;
   }

   private static boolean isTrue(Properties var0, String var1) {
      return Boolean.valueOf(var0.getProperty(var1));
   }

   public void createDataWarningFile() throws StandardException {
      StorageFile var1 = this.storageFactory.newStorageFile("seg0", "README_DO_NOT_TOUCH_FILES.txt");
      OutputStreamWriter var2 = null;
      if (!this.privExists(var1)) {
         try {
            var2 = this.privGetOutputStreamWriter(var1);
            var2.write(MessageService.getTextMessage("M007", new Object[0]));
         } catch (IOException var12) {
         } finally {
            if (var2 != null) {
               try {
                  var2.close();
               } catch (IOException var11) {
               }
            }

         }
      }

   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }

   private static String getServiceName(Object var0) {
      return Monitor.getServiceName(var0);
   }

   private static Object startSystemModule(String var0) throws StandardException {
      return Monitor.startSystemModule(var0);
   }

   private static Object bootServiceModule(boolean var0, Object var1, String var2, Properties var3) throws StandardException {
      return Monitor.bootServiceModule(var0, var1, var2, var3);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }
}
