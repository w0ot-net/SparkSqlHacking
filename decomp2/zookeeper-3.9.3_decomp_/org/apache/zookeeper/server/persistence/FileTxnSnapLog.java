package org.apache.zookeeper.server.persistence;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ServerStats;
import org.apache.zookeeper.server.ZooTrace;
import org.apache.zookeeper.txn.CreateSessionTxn;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTxnSnapLog {
   final File dataDir;
   final File snapDir;
   TxnLog txnLog;
   SnapShot snapLog;
   private final boolean autoCreateDB;
   private final boolean trustEmptySnapshot;
   public static final int VERSION = 2;
   public static final String version = "version-";
   private static final Logger LOG = LoggerFactory.getLogger(FileTxnSnapLog.class);
   public static final String ZOOKEEPER_DATADIR_AUTOCREATE = "zookeeper.datadir.autocreate";
   public static final String ZOOKEEPER_DATADIR_AUTOCREATE_DEFAULT = "true";
   static final String ZOOKEEPER_DB_AUTOCREATE = "zookeeper.db.autocreate";
   private static final String ZOOKEEPER_DB_AUTOCREATE_DEFAULT = "true";
   public static final String ZOOKEEPER_SNAPSHOT_TRUST_EMPTY = "zookeeper.snapshot.trust.empty";
   private static final String EMPTY_SNAPSHOT_WARNING = "No snapshot found, but there are log entries. ";

   public FileTxnSnapLog(File dataDir, File snapDir) throws IOException {
      LOG.debug("Opening datadir:{} snapDir:{}", dataDir, snapDir);
      this.dataDir = new File(dataDir, "version-2");
      this.snapDir = new File(snapDir, "version-2");
      boolean enableAutocreate = Boolean.parseBoolean(System.getProperty("zookeeper.datadir.autocreate", "true"));
      this.trustEmptySnapshot = Boolean.getBoolean("zookeeper.snapshot.trust.empty");
      LOG.info("{} : {}", "zookeeper.snapshot.trust.empty", this.trustEmptySnapshot);
      if (!this.dataDir.exists()) {
         if (!enableAutocreate) {
            throw new DatadirException(String.format("Missing data directory %s, automatic data directory creation is disabled (%s is false). Please create this directory manually.", this.dataDir, "zookeeper.datadir.autocreate"));
         }

         if (!this.dataDir.mkdirs() && !this.dataDir.exists()) {
            throw new DatadirException("Unable to create data directory " + this.dataDir);
         }
      }

      if (!this.dataDir.canWrite()) {
         throw new DatadirException("Cannot write to data directory " + this.dataDir);
      } else {
         if (!this.snapDir.exists()) {
            if (!enableAutocreate) {
               throw new DatadirException(String.format("Missing snap directory %s, automatic data directory creation is disabled (%s is false).Please create this directory manually.", this.snapDir, "zookeeper.datadir.autocreate"));
            }

            if (!this.snapDir.mkdirs() && !this.snapDir.exists()) {
               throw new DatadirException("Unable to create snap directory " + this.snapDir);
            }
         }

         if (!this.snapDir.canWrite()) {
            throw new DatadirException("Cannot write to snap directory " + this.snapDir);
         } else {
            if (!this.dataDir.getPath().equals(this.snapDir.getPath())) {
               this.checkLogDir();
               this.checkSnapDir();
            }

            this.txnLog = new FileTxnLog(this.dataDir);
            this.snapLog = new FileSnap(this.snapDir);
            this.autoCreateDB = Boolean.parseBoolean(System.getProperty("zookeeper.db.autocreate", "true"));
         }
      }
   }

   public void setServerStats(ServerStats serverStats) {
      this.txnLog.setServerStats(serverStats);
   }

   private void checkLogDir() throws LogDirContentCheckException {
      File[] files = this.dataDir.listFiles(new FilenameFilter() {
         public boolean accept(File dir, String name) {
            return Util.isSnapshotFileName(name);
         }
      });
      if (files != null && files.length > 0) {
         throw new LogDirContentCheckException("Log directory has snapshot files. Check if dataLogDir and dataDir configuration is correct.");
      }
   }

   private void checkSnapDir() throws SnapDirContentCheckException {
      File[] files = this.snapDir.listFiles(new FilenameFilter() {
         public boolean accept(File dir, String name) {
            return Util.isLogFileName(name);
         }
      });
      if (files != null && files.length > 0) {
         throw new SnapDirContentCheckException("Snapshot directory has log files. Check if dataLogDir and dataDir configuration is correct.");
      }
   }

   public File getDataLogDir() {
      return this.dataDir;
   }

   public File getSnapDir() {
      return this.snapDir;
   }

   public SnapshotInfo getLastSnapshotInfo() {
      return this.snapLog.getLastSnapshotInfo();
   }

   public boolean shouldForceWriteInitialSnapshotAfterLeaderElection() {
      return this.trustEmptySnapshot && this.getLastSnapshotInfo() == null;
   }

   public long restore(DataTree dt, Map sessions, PlayBackListener listener) throws IOException {
      long snapLoadingStartTime = Time.currentElapsedTime();
      long deserializeResult = this.snapLog.deserialize(dt, sessions);
      ServerMetrics.getMetrics().STARTUP_SNAP_LOAD_TIME.add(Time.currentElapsedTime() - snapLoadingStartTime);
      FileTxnLog txnLog = new FileTxnLog(this.dataDir);
      File initFile = new File(this.dataDir.getParent(), "initialize");
      boolean trustEmptyDB;
      if (Files.deleteIfExists(initFile.toPath())) {
         LOG.info("Initialize file found, an empty database will not block voting participation");
         trustEmptyDB = true;
      } else {
         trustEmptyDB = this.autoCreateDB;
      }

      RestoreFinalizer finalizer = () -> {
         long highestZxid = this.fastForwardFromEdits(dt, sessions, listener);
         DataTree.ZxidDigest snapshotZxidDigest = dt.getDigestFromLoadedSnapshot();
         if (snapshotZxidDigest != null) {
            LOG.warn("Highest txn zxid 0x{} is not covering the snapshot digest zxid 0x{}, which might lead to inconsistent state", Long.toHexString(highestZxid), Long.toHexString(snapshotZxidDigest.getZxid()));
         }

         return highestZxid;
      };
      if (-1L == deserializeResult) {
         if (txnLog.getLastLoggedZxid() != -1L) {
            if (!this.trustEmptySnapshot) {
               throw new IOException("No snapshot found, but there are log entries. Something is broken!");
            } else {
               LOG.warn("{}This should only be allowed during upgrading.", "No snapshot found, but there are log entries. ");
               return finalizer.run();
            }
         } else if (trustEmptyDB) {
            this.save(dt, (ConcurrentHashMap)sessions, false);
            return 0L;
         } else {
            LOG.warn("Unexpected empty data tree, setting zxid to -1");
            dt.lastProcessedZxid = -1L;
            return -1L;
         }
      } else {
         return finalizer.run();
      }
   }

   public long fastForwardFromEdits(DataTree dt, Map sessions, PlayBackListener listener) throws IOException {
      TxnLog.TxnIterator itr = this.txnLog.read(dt.lastProcessedZxid + 1L);
      long highestZxid = dt.lastProcessedZxid;
      int txnLoaded = 0;
      long startTime = Time.currentElapsedTime();

      try {
         do {
            TxnHeader hdr = itr.getHeader();
            if (hdr == null) {
               long var11 = dt.lastProcessedZxid;
               return var11;
            }

            if (hdr.getZxid() < highestZxid && highestZxid != 0L) {
               LOG.error("{}(highestZxid) > {}(next log) for type {}", new Object[]{highestZxid, hdr.getZxid(), hdr.getType()});
            } else {
               highestZxid = hdr.getZxid();
            }

            try {
               this.processTransaction(hdr, dt, sessions, itr.getTxn());
               dt.compareDigest(hdr, itr.getTxn(), itr.getDigest());
               ++txnLoaded;
            } catch (KeeperException.NoNodeException e) {
               throw new IOException("Failed to process transaction type: " + hdr.getType() + " error: " + e.getMessage(), e);
            }

            listener.onTxnLoaded(hdr, itr.getTxn(), itr.getDigest());
         } while(itr.next());
      } finally {
         if (itr != null) {
            itr.close();
         }

      }

      long loadTime = Time.currentElapsedTime() - startTime;
      LOG.info("{} txns loaded in {} ms", txnLoaded, loadTime);
      ServerMetrics.getMetrics().STARTUP_TXNS_LOADED.add((long)txnLoaded);
      ServerMetrics.getMetrics().STARTUP_TXNS_LOAD_TIME.add(loadTime);
      return highestZxid;
   }

   public TxnLog.TxnIterator readTxnLog(long zxid) throws IOException {
      return this.readTxnLog(zxid, true);
   }

   public TxnLog.TxnIterator readTxnLog(long zxid, boolean fastForward) throws IOException {
      FileTxnLog txnLog = new FileTxnLog(this.dataDir);
      return txnLog.read(zxid, fastForward);
   }

   public void processTransaction(TxnHeader hdr, DataTree dt, Map sessions, Record txn) throws KeeperException.NoNodeException {
      DataTree.ProcessTxnResult rc;
      switch (hdr.getType()) {
         case -11:
            sessions.remove(hdr.getClientId());
            if (LOG.isTraceEnabled()) {
               ZooTrace.logTraceMessage(LOG, 32L, "playLog --- close session in log: 0x" + Long.toHexString(hdr.getClientId()));
            }

            rc = dt.processTxn(hdr, txn);
            break;
         case -10:
            sessions.put(hdr.getClientId(), ((CreateSessionTxn)txn).getTimeOut());
            if (LOG.isTraceEnabled()) {
               ZooTrace.logTraceMessage(LOG, 32L, "playLog --- create session in log: 0x" + Long.toHexString(hdr.getClientId()) + " with timeout: " + ((CreateSessionTxn)txn).getTimeOut());
            }

            rc = dt.processTxn(hdr, txn);
            break;
         default:
            rc = dt.processTxn(hdr, txn);
      }

      if (rc.err != KeeperException.Code.OK.intValue()) {
         LOG.debug("Ignoring processTxn failure hdr: {}, error: {}, path: {}", new Object[]{hdr.getType(), rc.err, rc.path});
      }

   }

   public long getLastLoggedZxid() {
      FileTxnLog txnLog = new FileTxnLog(this.dataDir);
      return txnLog.getLastLoggedZxid();
   }

   public File save(DataTree dataTree, ConcurrentHashMap sessionsWithTimeouts, boolean syncSnap) throws IOException {
      long lastZxid = dataTree.lastProcessedZxid;
      File snapshotFile = new File(this.snapDir, Util.makeSnapshotName(lastZxid));
      LOG.info("Snapshotting: 0x{} to {}", Long.toHexString(lastZxid), snapshotFile);

      try {
         this.snapLog.serialize(dataTree, sessionsWithTimeouts, snapshotFile, syncSnap);
         return snapshotFile;
      } catch (IOException e) {
         if (snapshotFile.length() == 0L) {
            if (snapshotFile.delete()) {
               LOG.info("Deleted empty snapshot file: {}", snapshotFile.getAbsolutePath());
            } else {
               LOG.warn("Could not delete empty snapshot file: {}", snapshotFile.getAbsolutePath());
            }
         }

         throw e;
      }
   }

   public boolean truncateLog(long zxid) {
      try {
         this.close();
         FileTxnLog truncLog = new FileTxnLog(this.dataDir);

         boolean var5;
         try {
            boolean truncated = truncLog.truncate(zxid);
            this.txnLog = new FileTxnLog(this.dataDir);
            this.snapLog = new FileSnap(this.snapDir);
            var5 = truncated;
         } catch (Throwable var7) {
            try {
               truncLog.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         truncLog.close();
         return var5;
      } catch (IOException e) {
         LOG.error("Unable to truncate Txn log", e);
         return false;
      }
   }

   public File findMostRecentSnapshot() throws IOException {
      FileSnap snaplog = new FileSnap(this.snapDir);
      return snaplog.findMostRecentSnapshot();
   }

   public List findNRecentSnapshots(int n) throws IOException {
      FileSnap snaplog = new FileSnap(this.snapDir);
      return snaplog.findNRecentSnapshots(n);
   }

   public List findNValidSnapshots(int n) {
      FileSnap snaplog = new FileSnap(this.snapDir);
      return snaplog.findNValidSnapshots(n);
   }

   public File[] getSnapshotLogs(long zxid) {
      return FileTxnLog.getLogFiles(this.dataDir.listFiles(), zxid);
   }

   public boolean append(Request si) throws IOException {
      return this.txnLog.append(si);
   }

   public void commit() throws IOException {
      this.txnLog.commit();
   }

   public long getTxnLogElapsedSyncTime() {
      return this.txnLog.getTxnLogSyncElapsedTime();
   }

   public void rollLog() throws IOException {
      this.txnLog.rollLog();
   }

   public void close() throws IOException {
      TxnLog txnLogToClose = this.txnLog;
      if (txnLogToClose != null) {
         txnLogToClose.close();
      }

      this.txnLog = null;
      SnapShot snapSlogToClose = this.snapLog;
      if (snapSlogToClose != null) {
         snapSlogToClose.close();
      }

      this.snapLog = null;
   }

   public void setTotalLogSize(long size) {
      this.txnLog.setTotalLogSize(size);
   }

   public long getTotalLogSize() {
      return this.txnLog.getTotalLogSize();
   }

   public static class DatadirException extends IOException {
      public DatadirException(String msg) {
         super(msg);
      }

      public DatadirException(String msg, Exception e) {
         super(msg, e);
      }
   }

   public static class LogDirContentCheckException extends DatadirException {
      public LogDirContentCheckException(String msg) {
         super(msg);
      }
   }

   public static class SnapDirContentCheckException extends DatadirException {
      public SnapDirContentCheckException(String msg) {
         super(msg);
      }
   }

   public interface PlayBackListener {
      void onTxnLoaded(TxnHeader var1, Record var2, TxnDigest var3);
   }

   private interface RestoreFinalizer {
      long run() throws IOException;
   }
}
