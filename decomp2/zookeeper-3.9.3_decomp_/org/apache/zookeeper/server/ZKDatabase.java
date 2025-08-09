package org.apache.zookeeper.server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.CheckedInputStream;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.persistence.FileSnap;
import org.apache.zookeeper.server.persistence.FileTxnSnapLog;
import org.apache.zookeeper.server.persistence.SnapStream;
import org.apache.zookeeper.server.persistence.TxnLog;
import org.apache.zookeeper.server.quorum.Leader;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKDatabase {
   private static final Logger LOG = LoggerFactory.getLogger(ZKDatabase.class);
   protected DataTree dataTree = this.createDataTree();
   protected ConcurrentHashMap sessionsWithTimeouts = new ConcurrentHashMap();
   protected FileTxnSnapLog snapLog;
   protected long minCommittedLog;
   protected long maxCommittedLog;
   public static final String SNAPSHOT_SIZE_FACTOR = "zookeeper.snapshotSizeFactor";
   public static final double DEFAULT_SNAPSHOT_SIZE_FACTOR = 0.33;
   private double snapshotSizeFactor;
   public static final String COMMIT_LOG_COUNT = "zookeeper.commitLogCount";
   public static final int DEFAULT_COMMIT_LOG_COUNT = 500;
   public int commitLogCount;
   protected Queue committedLog = new ArrayDeque();
   protected ReentrantReadWriteLock logLock = new ReentrantReadWriteLock();
   private volatile boolean initialized = false;
   private AtomicInteger txnCount = new AtomicInteger(0);
   private final FileTxnSnapLog.PlayBackListener commitProposalPlaybackListener = new FileTxnSnapLog.PlayBackListener() {
      public void onTxnLoaded(TxnHeader hdr, Record txn, TxnDigest digest) {
         ZKDatabase.this.addCommittedProposal(hdr, txn, digest);
      }
   };

   public ZKDatabase(FileTxnSnapLog snapLog) {
      this.snapLog = snapLog;

      try {
         this.snapshotSizeFactor = Double.parseDouble(System.getProperty("zookeeper.snapshotSizeFactor", Double.toString(0.33)));
         if (this.snapshotSizeFactor > (double)1.0F) {
            this.snapshotSizeFactor = 0.33;
            LOG.warn("The configured {} is invalid, going to use the default {}", "zookeeper.snapshotSizeFactor", 0.33);
         }
      } catch (NumberFormatException var4) {
         LOG.error("Error parsing {}, using default value {}", "zookeeper.snapshotSizeFactor", 0.33);
         this.snapshotSizeFactor = 0.33;
      }

      LOG.info("{} = {}", "zookeeper.snapshotSizeFactor", this.snapshotSizeFactor);

      try {
         this.commitLogCount = Integer.parseInt(System.getProperty("zookeeper.commitLogCount", Integer.toString(500)));
         if (this.commitLogCount < 500) {
            this.commitLogCount = 500;
            LOG.warn("The configured commitLogCount {} is less than the recommended {}, going to use the recommended one", "zookeeper.commitLogCount", 500);
         }
      } catch (NumberFormatException var3) {
         LOG.error("Error parsing {} - use default value {}", "zookeeper.commitLogCount", 500);
         this.commitLogCount = 500;
      }

      LOG.info("{}={}", "zookeeper.commitLogCount", this.commitLogCount);
   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void clear() {
      this.minCommittedLog = 0L;
      this.maxCommittedLog = 0L;
      this.dataTree.shutdownWatcher();
      this.dataTree = this.createDataTree();
      this.sessionsWithTimeouts.clear();
      ReentrantReadWriteLock.WriteLock lock = this.logLock.writeLock();

      try {
         lock.lock();
         this.committedLog.clear();
      } finally {
         lock.unlock();
      }

      this.initialized = false;
   }

   public DataTree getDataTree() {
      return this.dataTree;
   }

   public long getmaxCommittedLog() {
      return this.maxCommittedLog;
   }

   public long getminCommittedLog() {
      return this.minCommittedLog;
   }

   public ReentrantReadWriteLock getLogLock() {
      return this.logLock;
   }

   public synchronized Collection getCommittedLog() {
      ReentrantReadWriteLock.ReadLock rl = this.logLock.readLock();
      Collection<Leader.Proposal> result;
      if (this.logLock.getReadHoldCount() > 0) {
         result = this.committedLog;
      } else {
         rl.lock();

         try {
            result = new ArrayList(this.committedLog);
         } finally {
            rl.unlock();
         }
      }

      return Collections.unmodifiableCollection(result);
   }

   public long getDataTreeLastProcessedZxid() {
      return this.dataTree.lastProcessedZxid;
   }

   public Collection getSessions() {
      return this.dataTree.getSessions();
   }

   public long getSessionCount() {
      return (long)this.sessionsWithTimeouts.size();
   }

   public ConcurrentHashMap getSessionWithTimeOuts() {
      return this.sessionsWithTimeouts;
   }

   public long loadDataBase() throws IOException {
      long startTime = Time.currentElapsedTime();
      long zxid = this.snapLog.restore(this.dataTree, this.sessionsWithTimeouts, this.commitProposalPlaybackListener);
      this.initialized = true;
      long loadTime = Time.currentElapsedTime() - startTime;
      ServerMetrics.getMetrics().DB_INIT_TIME.add(loadTime);
      LOG.info("Snapshot loaded in {} ms, highest zxid is 0x{}, digest is {}", new Object[]{loadTime, Long.toHexString(zxid), this.dataTree.getTreeDigest()});
      return zxid;
   }

   public long fastForwardDataBase() throws IOException {
      long zxid = this.snapLog.fastForwardFromEdits(this.dataTree, this.sessionsWithTimeouts, this.commitProposalPlaybackListener);
      this.initialized = true;
      return zxid;
   }

   private void addCommittedProposal(TxnHeader hdr, Record txn, TxnDigest digest) {
      Request r = new Request(0L, hdr.getCxid(), hdr.getType(), hdr, txn, hdr.getZxid());
      r.setTxnDigest(digest);
      this.addCommittedProposal(r);
   }

   public void addCommittedProposal(Request request) {
      ReentrantReadWriteLock.WriteLock wl = this.logLock.writeLock();

      try {
         wl.lock();
         if (this.committedLog.size() > this.commitLogCount) {
            this.committedLog.remove();
            this.minCommittedLog = ((Leader.Proposal)this.committedLog.peek()).getZxid();
         }

         if (this.committedLog.isEmpty()) {
            this.minCommittedLog = request.zxid;
            this.maxCommittedLog = request.zxid;
         }

         Leader.PureRequestProposal p = new Leader.PureRequestProposal(request);
         this.committedLog.add(p);
         this.maxCommittedLog = p.getZxid();
      } finally {
         wl.unlock();
      }

   }

   public boolean isTxnLogSyncEnabled() {
      boolean enabled = this.snapshotSizeFactor >= (double)0.0F;
      if (enabled) {
         LOG.info("On disk txn sync enabled with snapshotSizeFactor {}", this.snapshotSizeFactor);
      } else {
         LOG.info("On disk txn sync disabled");
      }

      return enabled;
   }

   public long calculateTxnLogSizeLimit() {
      long snapSize = 0L;

      try {
         File snapFile = this.snapLog.findMostRecentSnapshot();
         if (snapFile != null) {
            snapSize = snapFile.length();
         }
      } catch (IOException var4) {
         LOG.error("Unable to get size of most recent snapshot");
      }

      return (long)((double)snapSize * this.snapshotSizeFactor);
   }

   public Iterator getProposalsFromTxnLog(long startZxid, long sizeLimit) {
      if (sizeLimit < 0L) {
         LOG.debug("Negative size limit - retrieving proposal via txnlog is disabled");
         return TxnLogProposalIterator.EMPTY_ITERATOR;
      } else {
         TxnLog.TxnIterator itr = null;

         try {
            itr = this.snapLog.readTxnLog(startZxid, false);
            if (itr.getHeader() != null && itr.getHeader().getZxid() > startZxid) {
               LOG.warn("Unable to find proposals from txnlog for zxid: 0x{}", Long.toHexString(startZxid));
               itr.close();
               return TxnLogProposalIterator.EMPTY_ITERATOR;
            }

            if (sizeLimit > 0L) {
               long txnSize = itr.getStorageSize();
               if (txnSize > sizeLimit) {
                  LOG.info("Txnlog size: {} exceeds sizeLimit: {}", txnSize, sizeLimit);
                  itr.close();
                  return TxnLogProposalIterator.EMPTY_ITERATOR;
               }
            }
         } catch (IOException e) {
            LOG.error("Unable to read txnlog from disk", e);

            try {
               if (itr != null) {
                  itr.close();
               }
            } catch (IOException ioe) {
               LOG.warn("Error closing file iterator", ioe);
            }

            return TxnLogProposalIterator.EMPTY_ITERATOR;
         }

         return new TxnLogProposalIterator(itr);
      }
   }

   public List aclForNode(DataNode n) {
      return this.dataTree.getACL(n);
   }

   public void removeCnxn(ServerCnxn cnxn) {
      this.dataTree.removeCnxn(cnxn);
   }

   public void killSession(long sessionId, long zxid) {
      this.dataTree.killSession(sessionId, zxid);
   }

   public void dumpEphemerals(PrintWriter pwriter) {
      this.dataTree.dumpEphemerals(pwriter);
   }

   public Map getEphemerals() {
      return this.dataTree.getEphemerals();
   }

   public int getNodeCount() {
      return this.dataTree.getNodeCount();
   }

   public Set getEphemerals(long sessionId) {
      return this.dataTree.getEphemerals(sessionId);
   }

   public void setlastProcessedZxid(long zxid) {
      this.dataTree.lastProcessedZxid = zxid;
   }

   public DataTree.ProcessTxnResult processTxn(TxnHeader hdr, Record txn, TxnDigest digest) {
      return this.dataTree.processTxn(hdr, txn, digest);
   }

   public Stat statNode(String path, ServerCnxn serverCnxn) throws KeeperException.NoNodeException {
      return this.dataTree.statNode(path, serverCnxn);
   }

   public DataNode getNode(String path) {
      return this.dataTree.getNode(path);
   }

   public byte[] getData(String path, Stat stat, Watcher watcher) throws KeeperException.NoNodeException {
      return this.dataTree.getData(path, stat, watcher);
   }

   public void setWatches(long relativeZxid, List dataWatches, List existWatches, List childWatches, List persistentWatches, List persistentRecursiveWatches, Watcher watcher) {
      this.dataTree.setWatches(relativeZxid, dataWatches, existWatches, childWatches, persistentWatches, persistentRecursiveWatches, watcher);
   }

   public void addWatch(String basePath, Watcher watcher, int mode) {
      this.dataTree.addWatch(basePath, watcher, mode);
   }

   public List getACL(String path, Stat stat) throws KeeperException.NoNodeException {
      return this.dataTree.getACL(path, stat);
   }

   public List getChildren(String path, Stat stat, Watcher watcher) throws KeeperException.NoNodeException {
      return this.dataTree.getChildren(path, stat, watcher);
   }

   public int getAllChildrenNumber(String path) throws KeeperException.NoNodeException {
      return this.dataTree.getAllChildrenNumber(path);
   }

   public boolean isSpecialPath(String path) {
      return this.dataTree.isSpecialPath(path);
   }

   public int getAclSize() {
      return this.dataTree.aclCacheSize();
   }

   public boolean truncateLog(long zxid) throws IOException {
      this.clear();
      boolean truncated = this.snapLog.truncateLog(zxid);
      if (!truncated) {
         return false;
      } else {
         this.loadDataBase();
         return true;
      }
   }

   public void deserializeSnapshot(InputArchive ia) throws IOException {
      this.clear();
      SerializeUtils.deserializeSnapshot(this.getDataTree(), ia, this.getSessionWithTimeOuts());
      this.initialized = true;
   }

   public void deserializeSnapshot(InputArchive ia, CheckedInputStream is) throws IOException {
      this.clear();
      DataTree dataTree = this.getDataTree();
      FileSnap.deserialize(dataTree, this.getSessionWithTimeOuts(), ia);
      SnapStream.checkSealIntegrity(is, ia);
      if (dataTree.deserializeZxidDigest(ia, 0L)) {
         SnapStream.checkSealIntegrity(is, ia);
      }

      if (dataTree.deserializeLastProcessedZxid(ia)) {
         SnapStream.checkSealIntegrity(is, ia);
      }

      if (dataTree.getDigestFromLoadedSnapshot() != null) {
         dataTree.compareSnapshotDigests(dataTree.lastProcessedZxid);
      }

      this.initialized = true;
   }

   public void serializeSnapshot(OutputArchive oa) throws IOException, InterruptedException {
      SerializeUtils.serializeSnapshot(this.getDataTree(), oa, this.getSessionWithTimeOuts());
   }

   public boolean append(Request si) throws IOException {
      if (this.snapLog.append(si)) {
         this.txnCount.incrementAndGet();
         return true;
      } else {
         return false;
      }
   }

   public void rollLog() throws IOException {
      this.snapLog.rollLog();
      this.resetTxnCount();
   }

   public void commit() throws IOException {
      this.snapLog.commit();
   }

   public void close() throws IOException {
      this.snapLog.close();
   }

   public synchronized void initConfigInZKDatabase(QuorumVerifier qv) {
      if (qv != null) {
         try {
            if (this.dataTree.getNode("/zookeeper/config") == null) {
               LOG.warn("configuration znode missing (should only happen during upgrade), creating the node");
               this.dataTree.addConfigNode();
            }

            this.dataTree.setData("/zookeeper/config", qv.toString().getBytes(StandardCharsets.UTF_8), -1, qv.getVersion(), Time.currentWallTime());
         } catch (KeeperException.NoNodeException var3) {
            System.out.println("configuration node missing - should not happen");
         }

      }
   }

   public void setSnapshotSizeFactor(double snapshotSizeFactor) {
      this.snapshotSizeFactor = snapshotSizeFactor;
   }

   public boolean containsWatcher(String path, Watcher.WatcherType type, Watcher watcher) {
      return this.dataTree.containsWatcher(path, type, watcher);
   }

   public boolean removeWatch(String path, Watcher.WatcherType type, Watcher watcher) {
      return this.dataTree.removeWatch(path, type, watcher);
   }

   public DataTree createDataTree() {
      return new DataTree();
   }

   public void resetTxnCount() {
      this.txnCount.set(0);
      this.snapLog.setTotalLogSize(0L);
   }

   public int getTxnCount() {
      return this.txnCount.get();
   }

   public long getTxnSize() {
      return this.snapLog.getTotalLogSize();
   }

   public boolean compareDigest(TxnHeader header, Record txn, TxnDigest digest) {
      return this.dataTree.compareDigest(header, txn, digest);
   }
}
