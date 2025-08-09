package org.apache.derby.iapi.store.raw.data;

import java.io.File;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.Corruptable;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.StreamContainerHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.store.raw.UndoHandler;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface DataFactory extends Corruptable {
   String MODULE = "org.apache.derby.iapi.store.raw.data.DataFactory";
   String TEMP_SEGMENT_NAME = "tmp";
   String DB_LOCKFILE_NAME = "db.lck";
   String DB_EX_LOCKFILE_NAME = "dbex.lck";

   boolean isReadOnly();

   ContainerHandle openContainer(RawTransaction var1, ContainerKey var2, LockingPolicy var3, int var4) throws StandardException;

   RawContainerHandle openDroppedContainer(RawTransaction var1, ContainerKey var2, LockingPolicy var3, int var4) throws StandardException;

   long addContainer(RawTransaction var1, long var2, long var4, int var6, Properties var7, int var8) throws StandardException;

   long addAndLoadStreamContainer(RawTransaction var1, long var2, Properties var4, RowSource var5) throws StandardException;

   StreamContainerHandle openStreamContainer(RawTransaction var1, long var2, long var4, boolean var6) throws StandardException;

   void dropStreamContainer(RawTransaction var1, long var2, long var4) throws StandardException;

   void reCreateContainerForRedoRecovery(RawTransaction var1, long var2, long var4, ByteArray var6) throws StandardException;

   void dropContainer(RawTransaction var1, ContainerKey var2) throws StandardException;

   void checkpoint() throws StandardException;

   void idle() throws StandardException;

   UUID getIdentifier();

   void setRawStoreFactory(RawStoreFactory var1, boolean var2, Properties var3) throws StandardException;

   void createFinished() throws StandardException;

   FileResource getFileHandler();

   void removeStubsOK();

   int reclaimSpace(Serviceable var1, ContextManager var2) throws StandardException;

   void postRecovery() throws StandardException;

   void setupCacheCleaner(DaemonService var1);

   int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5, boolean var6) throws StandardException;

   int decrypt(byte[] var1, int var2, int var3, byte[] var4, int var5) throws StandardException;

   void decryptAllContainers(RawTransaction var1) throws StandardException;

   void encryptAllContainers(RawTransaction var1) throws StandardException;

   void removeOldVersionOfContainers() throws StandardException;

   void setDatabaseEncrypted(boolean var1);

   int getEncryptionBlockSize();

   void freezePersistentStore() throws StandardException;

   void unfreezePersistentStore();

   void writeInProgress() throws StandardException;

   void writeFinished();

   void backupDataFiles(Transaction var1, File var2) throws StandardException;

   long getMaxContainerId() throws StandardException;

   void removeDroppedContainerFileStubs(LogInstant var1) throws StandardException;

   StorageFactory getStorageFactory();

   String getRootDirectory();

   void stop();

   boolean databaseEncrypted();

   void setUndoInsertEventHandler(UndoHandler var1);
}
