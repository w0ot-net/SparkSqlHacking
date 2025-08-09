package org.apache.derby.impl.store.raw.log;

import java.io.File;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.property.PersistentSet;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.ScanHandle;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.log.LogScan;
import org.apache.derby.iapi.store.raw.log.Logger;
import org.apache.derby.iapi.store.raw.xact.TransactionFactory;
import org.apache.derby.iapi.store.replication.master.MasterFactory;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

public class ReadOnly implements LogFactory, ModuleSupportable {
   private String logArchiveDirectory = null;

   public Logger getLogger() {
      return null;
   }

   public void createDataWarningFile() throws StandardException {
   }

   public void setRawStoreFactory(RawStoreFactory var1) {
   }

   public void recover(DataFactory var1, TransactionFactory var2) throws StandardException {
      if (var2 != null) {
         var2.useTransactionTable((Formatable)null);
      }

   }

   public boolean checkpoint(RawStoreFactory var1, DataFactory var2, TransactionFactory var3, boolean var4) {
      return true;
   }

   public StandardException markCorrupt(StandardException var1) {
      return var1;
   }

   public void flush(LogInstant var1) throws StandardException {
   }

   public boolean canSupport(Properties var1) {
      String var2 = var1.getProperty("derby.__rt.storage.log");
      return var2 == null ? false : var2.equals("readonly");
   }

   public LogInstant setTruncationLWM(UUID var1, LogInstant var2, RawStoreFactory var3, TransactionFactory var4) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public void setTruncationLWM(UUID var1, LogInstant var2) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public void removeTruncationLWM(UUID var1, RawStoreFactory var2, TransactionFactory var3) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public LogInstant getTruncationLWM(UUID var1) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public void removeTruncationLWM(UUID var1) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public ScanHandle openFlushedScan(DatabaseInstant var1, int var2) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public LogScan openForwardsScan(LogInstant var1, LogInstant var2) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public LogInstant getFirstUnflushedInstant() {
      return null;
   }

   public long getFirstUnflushedInstantAsLong() {
      return 0L;
   }

   public LogScan openForwardsFlushedScan(LogInstant var1) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public void freezePersistentStore() throws StandardException {
   }

   public void unfreezePersistentStore() throws StandardException {
   }

   public boolean logArchived() {
      return this.logArchiveDirectory != null;
   }

   public void getLogFactoryProperties(PersistentSet var1) {
   }

   public StorageFile getLogDirectory() {
      return null;
   }

   public String getCanonicalLogPath() {
      return null;
   }

   public void enableLogArchiveMode() {
   }

   public void disableLogArchiveMode() {
   }

   public void deleteOnlineArchivedLogFiles() {
   }

   public boolean inRFR() {
      return false;
   }

   public void checkpointInRFR(LogInstant var1, long var2, long var4, DataFactory var6) throws StandardException {
   }

   public void startLogBackup(File var1) throws StandardException {
   }

   public void endLogBackup(File var1) throws StandardException {
   }

   public void abortLogBackup() {
   }

   public void setDatabaseEncrypted(boolean var1, boolean var2) {
   }

   public void startNewLogFile() throws StandardException {
   }

   public boolean isCheckpointInLastLogFile() throws StandardException {
      return false;
   }

   public void deleteLogFileAfterCheckpointLogFile() throws StandardException {
   }

   public boolean checkVersion(int var1, int var2, String var3) throws StandardException {
      throw StandardException.newException("XSAI3.S", new Object[0]);
   }

   public void startReplicationMasterRole(MasterFactory var1) throws StandardException {
      throw StandardException.newException("XRE00", new Object[0]);
   }

   public boolean inReplicationMasterMode() {
      return false;
   }

   public void stopReplicationMasterRole() {
   }
}
