package org.apache.derby.iapi.store.raw;

import java.io.Serializable;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.iapi.store.raw.xact.TransactionFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface RawStoreFactory extends Corruptable {
   int DERBY_STORE_MINOR_VERSION_1 = 1;
   int DERBY_STORE_MINOR_VERSION_2 = 2;
   int DERBY_STORE_MINOR_VERSION_3 = 3;
   int DERBY_STORE_MINOR_VERSION_4 = 4;
   int DERBY_STORE_MINOR_VERSION_10 = 10;
   int DERBY_STORE_MAJOR_VERSION_10 = 10;
   int PAGE_SIZE_DEFAULT = 4096;
   int PAGE_SIZE_MINIMUM = 1024;
   String PAGE_SIZE_STRING = "2048";
   String PAGE_CACHE_SIZE_PARAMETER = "derby.storage.pageCacheSize";
   int PAGE_CACHE_SIZE_DEFAULT = 1000;
   int PAGE_CACHE_SIZE_MINIMUM = 40;
   int PAGE_CACHE_SIZE_MAXIMUM = Integer.MAX_VALUE;
   String CONTAINER_CACHE_SIZE_PARAMETER = "derby.storage.fileCacheSize";
   int CONTAINER_CACHE_SIZE_DEFAULT = 100;
   int CONTAINER_CACHE_SIZE_MINIMUM = 2;
   int CONTAINER_CACHE_SIZE_MAXIMUM = Integer.MAX_VALUE;
   short MAX_CONTAINER_INITIAL_PAGES = 1000;
   String MINIMUM_RECORD_SIZE_PARAMETER = "derby.storage.minimumRecordSize";
   int MINIMUM_RECORD_SIZE_DEFAULT = 12;
   int MINIMUM_RECORD_SIZE_MINIMUM = 1;
   String PAGE_RESERVED_SPACE_PARAMETER = "derby.storage.pageReservedSpace";
   String PAGE_RESERVED_ZERO_SPACE_STRING = "0";
   String PRE_ALLOCATE_PAGE = "derby.storage.pagePerAllocate";
   String PAGE_REUSABLE_RECORD_ID = "derby.storage.reusableRecordId";
   String STREAM_FILE_BUFFER_SIZE_PARAMETER = "derby.storage.streamFileBufferSize";
   int STREAM_FILE_BUFFER_SIZE_DEFAULT = 16384;
   int STREAM_FILE_BUFFER_SIZE_MINIMUM = 1024;
   int STREAM_FILE_BUFFER_SIZE_MAXIMUM = Integer.MAX_VALUE;
   String CONTAINER_INITIAL_PAGES = "derby.storage.initialPages";
   int ENCRYPTION_ALIGNMENT = 8;
   int DEFAULT_ENCRYPTION_BLOCKSIZE = 8;
   String ENCRYPTION_BLOCKSIZE = "derby.encryptionBlockSize";
   String DATA_ENCRYPT_ALGORITHM_VERSION = "data_encrypt_algorithm_version";
   String LOG_ENCRYPT_ALGORITHM_VERSION = "log_encrypt_algorithm_version";
   String ENCRYPTED_KEY = "encryptedBootPassword";
   String OLD_ENCRYPTED_KEY = "OldEncryptedBootPassword";
   String DB_ENCRYPTION_STATUS = "derby.storage.databaseEncryptionStatus";
   int DB_ENCRYPTION_IN_PROGRESS = 1;
   int DB_ENCRYPTION_IN_UNDO = 2;
   int DB_ENCRYPTION_IN_CLEANUP = 3;
   String CRYPTO_OLD_EXTERNAL_KEY_VERIFY_FILE = "verifyOldKey.dat";
   String KEEP_TRANSACTION_LOG = "derby.storage.keepTransactionLog";
   String PATCH_INITPAGE_RECOVER_ERROR = "derby.storage.patchInitPageRecoverError";
   String MODULE = "org.apache.derby.iapi.store.raw.RawStoreFactory";

   boolean isReadOnly();

   LockFactory getLockFactory();

   void setUndoInsertEventHandler(UndoHandler var1) throws StandardException;

   Transaction startTransaction(ContextManager var1, String var2) throws StandardException;

   Transaction startGlobalTransaction(ContextManager var1, int var2, byte[] var3, byte[] var4) throws StandardException;

   Transaction findUserTransaction(ContextManager var1, String var2) throws StandardException;

   Transaction startInternalTransaction(ContextManager var1) throws StandardException;

   Transaction startNestedReadOnlyUserTransaction(Transaction var1, CompatibilitySpace var2, ContextManager var3, String var4) throws StandardException;

   Transaction startNestedUpdateUserTransaction(Transaction var1, ContextManager var2, String var3, boolean var4) throws StandardException;

   TransactionInfo[] getTransactionInfo();

   void startReplicationMaster(String var1, String var2, int var3, String var4) throws StandardException;

   void stopReplicationMaster() throws StandardException;

   void failover(String var1) throws StandardException;

   void freeze() throws StandardException;

   void unfreeze() throws StandardException;

   void backup(String var1, boolean var2) throws StandardException;

   void backupAndEnableLogArchiveMode(String var1, boolean var2, boolean var3) throws StandardException;

   void disableLogArchiveMode(boolean var1) throws StandardException;

   void checkpoint() throws StandardException;

   void idle() throws StandardException;

   ScanHandle openFlushedScan(DatabaseInstant var1, int var2) throws StandardException;

   DaemonService getDaemon();

   String getTransactionFactoryModule();

   String getDataFactoryModule();

   String getLogFactoryModule();

   Object getXAResourceManager() throws StandardException;

   void createFinished() throws StandardException;

   void getRawStoreProperties(PersistentSet var1) throws StandardException;

   void freezePersistentStore() throws StandardException;

   void unfreezePersistentStore() throws StandardException;

   int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5, boolean var6) throws StandardException;

   int decrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException;

   int getEncryptionBlockSize();

   int random();

   Serializable changeBootPassword(Properties var1, Serializable var2) throws StandardException;

   long getMaxContainerId() throws StandardException;

   TransactionFactory getXactFactory();

   boolean checkVersion(int var1, int var2, String var3) throws StandardException;

   void createDataWarningFile() throws StandardException;
}
