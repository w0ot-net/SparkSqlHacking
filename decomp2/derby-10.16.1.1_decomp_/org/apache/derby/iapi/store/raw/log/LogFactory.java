package org.apache.derby.iapi.store.raw.log;

import java.io.File;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.raw.Corruptable;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.ScanHandle;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.xact.TransactionFactory;
import org.apache.derby.iapi.store.replication.master.MasterFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

public interface LogFactory extends Corruptable {
   String RUNTIME_ATTRIBUTES = "derby.__rt.storage.log";
   String RT_READONLY = "readonly";
   String LOG_DIRECTORY_NAME = "log";
   String MODULE = "org.apache.derby.iapi.store.raw.log.LogFactory";

   Logger getLogger();

   void createDataWarningFile() throws StandardException;

   void setRawStoreFactory(RawStoreFactory var1);

   void recover(DataFactory var1, TransactionFactory var2) throws StandardException;

   boolean checkpoint(RawStoreFactory var1, DataFactory var2, TransactionFactory var3, boolean var4) throws StandardException;

   void flush(LogInstant var1) throws StandardException;

   LogScan openForwardsFlushedScan(LogInstant var1) throws StandardException;

   ScanHandle openFlushedScan(DatabaseInstant var1, int var2) throws StandardException;

   LogScan openForwardsScan(LogInstant var1, LogInstant var2) throws StandardException;

   LogInstant getFirstUnflushedInstant();

   long getFirstUnflushedInstantAsLong();

   void freezePersistentStore() throws StandardException;

   void unfreezePersistentStore() throws StandardException;

   boolean logArchived();

   boolean inReplicationMasterMode();

   void getLogFactoryProperties(PersistentSet var1) throws StandardException;

   StorageFile getLogDirectory() throws StandardException;

   String getCanonicalLogPath();

   void enableLogArchiveMode() throws StandardException;

   void disableLogArchiveMode() throws StandardException;

   void deleteOnlineArchivedLogFiles();

   boolean inRFR();

   void checkpointInRFR(LogInstant var1, long var2, long var4, DataFactory var6) throws StandardException;

   void startLogBackup(File var1) throws StandardException;

   void endLogBackup(File var1) throws StandardException;

   void abortLogBackup();

   void setDatabaseEncrypted(boolean var1, boolean var2) throws StandardException;

   void startNewLogFile() throws StandardException;

   boolean isCheckpointInLastLogFile() throws StandardException;

   void deleteLogFileAfterCheckpointLogFile() throws StandardException;

   boolean checkVersion(int var1, int var2, String var3) throws StandardException;

   void startReplicationMasterRole(MasterFactory var1) throws StandardException;

   void stopReplicationMasterRole();
}
