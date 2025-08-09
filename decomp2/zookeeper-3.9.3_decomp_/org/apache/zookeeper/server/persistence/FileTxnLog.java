package org.apache.zookeeper.server.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.zip.Adler32;
import java.util.zip.Checksum;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ServerStats;
import org.apache.zookeeper.server.TxnLogEntry;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.apache.zookeeper.txn.TxnDigest;
import org.apache.zookeeper.txn.TxnHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTxnLog implements TxnLog, Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(FileTxnLog.class);
   public static final int TXNLOG_MAGIC = ByteBuffer.wrap("ZKLG".getBytes()).getInt();
   public static final int VERSION = 2;
   public static final String LOG_FILE_PREFIX = "log";
   static final String FSYNC_WARNING_THRESHOLD_MS_PROPERTY = "fsync.warningthresholdms";
   static final String ZOOKEEPER_FSYNC_WARNING_THRESHOLD_MS_PROPERTY = "zookeeper.fsync.warningthresholdms";
   private static final long fsyncWarningThresholdMS;
   private static final String txnLogSizeLimitSetting = "zookeeper.txnLogSizeLimitInKb";
   private static long txnLogSizeLimit = -1L;
   long lastZxidSeen;
   volatile BufferedOutputStream logStream = null;
   volatile OutputArchive oa;
   volatile FileOutputStream fos = null;
   File logDir;
   private final boolean forceSync = !System.getProperty("zookeeper.forceSync", "yes").equals("no");
   long dbId;
   private final Queue streamsToFlush = new ArrayDeque();
   File logFileWrite = null;
   private FilePadding filePadding = new FilePadding();
   private ServerStats serverStats;
   private volatile long syncElapsedMS = -1L;
   private long prevLogsRunningTotal;
   long filePosition = 0L;
   private long unFlushedSize = 0L;
   private long fileSize = 0L;

   public FileTxnLog(File logDir) {
      this.logDir = logDir;
   }

   public static void setPreallocSize(long size) {
      FilePadding.setPreallocSize(size);
   }

   public synchronized void setServerStats(ServerStats serverStats) {
      this.serverStats = serverStats;
   }

   public static void setTxnLogSizeLimit(long size) {
      txnLogSizeLimit = size;
   }

   public synchronized long getCurrentLogSize() {
      return this.logFileWrite != null ? this.fileSize : 0L;
   }

   public synchronized void setTotalLogSize(long size) {
      this.prevLogsRunningTotal = size;
   }

   public synchronized long getTotalLogSize() {
      return this.prevLogsRunningTotal + this.getCurrentLogSize();
   }

   public static long getTxnLogSizeLimit() {
      return txnLogSizeLimit;
   }

   protected Checksum makeChecksumAlgorithm() {
      return new Adler32();
   }

   public synchronized void rollLog() throws IOException {
      if (this.logStream != null) {
         this.logStream.flush();
         this.prevLogsRunningTotal += this.getCurrentLogSize();
         this.logStream = null;
         this.oa = null;
         this.fileSize = 0L;
         this.filePosition = 0L;
         this.unFlushedSize = 0L;
      }

   }

   public synchronized void close() throws IOException {
      if (this.logStream != null) {
         this.logStream.close();
      }

      for(FileOutputStream log : this.streamsToFlush) {
         log.close();
      }

   }

   public synchronized boolean append(Request request) throws IOException {
      TxnHeader hdr = request.getHdr();
      if (hdr == null) {
         return false;
      } else {
         if (hdr.getZxid() <= this.lastZxidSeen) {
            LOG.warn("Current zxid {} is <= {} for {}", new Object[]{hdr.getZxid(), this.lastZxidSeen, Request.op2String(hdr.getType())});
         } else {
            this.lastZxidSeen = hdr.getZxid();
         }

         if (this.logStream == null) {
            LOG.info("Creating new log file: {}", Util.makeLogName(hdr.getZxid()));
            this.logFileWrite = new File(this.logDir, Util.makeLogName(hdr.getZxid()));
            this.fos = new FileOutputStream(this.logFileWrite);
            this.logStream = new BufferedOutputStream(this.fos);
            this.oa = BinaryOutputArchive.getArchive(this.logStream);
            FileHeader fhdr = new FileHeader(TXNLOG_MAGIC, 2, this.dbId);
            long dataSize = this.oa.getDataSize();
            fhdr.serialize(this.oa, "fileheader");
            this.logStream.flush();
            this.filePosition += this.oa.getDataSize() - dataSize;
            this.filePadding.setCurrentSize(this.filePosition);
            this.streamsToFlush.add(this.fos);
         }

         this.fileSize = this.filePadding.padFile(this.fos.getChannel(), this.filePosition);
         byte[] buf = request.getSerializeData();
         if (buf != null && buf.length != 0) {
            long dataSize = this.oa.getDataSize();
            Checksum crc = this.makeChecksumAlgorithm();
            crc.update(buf, 0, buf.length);
            this.oa.writeLong(crc.getValue(), "txnEntryCRC");
            Util.writeTxnBytes(this.oa, buf);
            this.unFlushedSize += this.oa.getDataSize() - dataSize;
            return true;
         } else {
            throw new IOException("Faulty serialization for header and txn");
         }
      }
   }

   public static File[] getLogFiles(File[] logDirList, long snapshotZxid) {
      List<File> files = Util.sortDataDir(logDirList, "log", true);
      long logZxid = 0L;

      for(File f : files) {
         long fzxid = Util.getZxidFromName(f.getName(), "log");
         if (fzxid > snapshotZxid) {
            break;
         }

         if (fzxid > logZxid) {
            logZxid = fzxid;
         }
      }

      List<File> v = new ArrayList(5);

      for(File f : files) {
         long fzxid = Util.getZxidFromName(f.getName(), "log");
         if (fzxid >= logZxid) {
            v.add(f);
         }
      }

      return (File[])v.toArray(new File[0]);
   }

   public long getLastLoggedZxid() {
      File[] files = getLogFiles(this.logDir.listFiles(), 0L);
      long maxLog = files.length > 0 ? Util.getZxidFromName(files[files.length - 1].getName(), "log") : -1L;
      long zxid = maxLog;

      try {
         FileTxnLog txn = new FileTxnLog(this.logDir);

         try {
            TxnLog.TxnIterator itr = txn.read(maxLog);

            try {
               while(itr.next()) {
                  TxnHeader hdr = itr.getHeader();
                  zxid = hdr.getZxid();
               }
            } catch (Throwable var12) {
               if (itr != null) {
                  try {
                     itr.close();
                  } catch (Throwable var11) {
                     var12.addSuppressed(var11);
                  }
               }

               throw var12;
            }

            if (itr != null) {
               itr.close();
            }
         } catch (Throwable var13) {
            try {
               txn.close();
            } catch (Throwable var10) {
               var13.addSuppressed(var10);
            }

            throw var13;
         }

         txn.close();
      } catch (IOException e) {
         LOG.warn("Unexpected exception", e);
      }

      return zxid;
   }

   public synchronized void commit() throws IOException {
      if (this.logStream != null) {
         this.logStream.flush();
         this.filePosition += this.unFlushedSize;
         if (this.filePosition > this.fileSize) {
            this.fileSize = this.filePosition;
         }

         this.unFlushedSize = 0L;
      }

      for(FileOutputStream log : this.streamsToFlush) {
         log.flush();
         if (this.forceSync) {
            long startSyncNS = System.nanoTime();
            FileChannel channel = log.getChannel();
            channel.force(false);
            this.syncElapsedMS = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startSyncNS);
            if (this.syncElapsedMS > fsyncWarningThresholdMS) {
               if (this.serverStats != null) {
                  this.serverStats.incrementFsyncThresholdExceedCount();
               }

               LOG.warn("fsync-ing the write ahead log in {} took {}ms which will adversely effect operation latency.File size is {} bytes. See the ZooKeeper troubleshooting guide", new Object[]{Thread.currentThread().getName(), this.syncElapsedMS, channel.size()});
            }

            ServerMetrics.getMetrics().FSYNC_TIME.add(this.syncElapsedMS);
         }
      }

      while(this.streamsToFlush.size() > 1) {
         ((FileOutputStream)this.streamsToFlush.poll()).close();
      }

      if (txnLogSizeLimit > 0L) {
         long logSize = this.getCurrentLogSize();
         if (logSize > txnLogSizeLimit) {
            LOG.debug("Log size limit reached: {}", logSize);
            this.rollLog();
         }
      }

   }

   public long getTxnLogSyncElapsedTime() {
      return this.syncElapsedMS;
   }

   public TxnLog.TxnIterator read(long zxid) throws IOException {
      return this.read(zxid, true);
   }

   public TxnLog.TxnIterator read(long zxid, boolean fastForward) throws IOException {
      return new FileTxnIterator(this.logDir, zxid, fastForward);
   }

   public boolean truncate(long zxid) throws IOException {
      FileTxnIterator itr = new FileTxnIterator(this.logDir, zxid);

      try {
         PositionInputStream input = itr.inputStream;
         if (input == null) {
            throw new IOException("No log files found to truncate! This could happen if you still have snapshots from an old setup or log files were deleted accidentally or dataLogDir was changed in zoo.cfg.");
         }

         long pos = input.getPosition();
         RandomAccessFile raf = new RandomAccessFile(itr.logFile, "rw");
         raf.setLength(pos);
         raf.close();

         while(itr.goToNextLog()) {
            if (!itr.logFile.delete()) {
               LOG.warn("Unable to truncate {}", itr.logFile);
            }
         }
      } catch (Throwable var9) {
         try {
            itr.close();
         } catch (Throwable var8) {
            var9.addSuppressed(var8);
         }

         throw var9;
      }

      itr.close();
      return true;
   }

   private static FileHeader readHeader(File file) throws IOException {
      InputStream is = null;

      FileHeader var4;
      try {
         is = new BufferedInputStream(new FileInputStream(file));
         InputArchive ia = BinaryInputArchive.getArchive(is);
         FileHeader hdr = new FileHeader();
         hdr.deserialize(ia, "fileheader");
         var4 = hdr;
      } finally {
         try {
            if (is != null) {
               is.close();
            }
         } catch (IOException e) {
            LOG.warn("Ignoring exception during close", e);
         }

      }

      return var4;
   }

   public long getDbId() throws IOException {
      FileTxnIterator itr = new FileTxnIterator(this.logDir, 0L);
      FileHeader fh = readHeader(itr.logFile);
      itr.close();
      if (fh == null) {
         throw new IOException("Unsupported Format.");
      } else {
         return fh.getDbid();
      }
   }

   public boolean isForceSync() {
      return this.forceSync;
   }

   static {
      Long fsyncWarningThreshold;
      if ((fsyncWarningThreshold = Long.getLong("zookeeper.fsync.warningthresholdms")) == null) {
         fsyncWarningThreshold = Long.getLong("fsync.warningthresholdms", 1000L);
      }

      fsyncWarningThresholdMS = fsyncWarningThreshold;
      Long logSize = Long.getLong("zookeeper.txnLogSizeLimitInKb", -1L);
      if (logSize > 0L) {
         LOG.info("{} = {}", "zookeeper.txnLogSizeLimitInKb", logSize);
         logSize = logSize * 1024L;
         txnLogSizeLimit = logSize;
      }

   }

   static class PositionInputStream extends FilterInputStream {
      long position = 0L;

      protected PositionInputStream(InputStream in) {
         super(in);
      }

      public int read() throws IOException {
         int rc = super.read();
         if (rc > -1) {
            ++this.position;
         }

         return rc;
      }

      public int read(byte[] b) throws IOException {
         int rc = super.read(b);
         if (rc > 0) {
            this.position += (long)rc;
         }

         return rc;
      }

      public int read(byte[] b, int off, int len) throws IOException {
         int rc = super.read(b, off, len);
         if (rc > 0) {
            this.position += (long)rc;
         }

         return rc;
      }

      public long skip(long n) throws IOException {
         long rc = super.skip(n);
         if (rc > 0L) {
            this.position += rc;
         }

         return rc;
      }

      public long getPosition() {
         return this.position;
      }

      public boolean markSupported() {
         return false;
      }

      public void mark(int readLimit) {
         throw new UnsupportedOperationException("mark");
      }

      public void reset() {
         throw new UnsupportedOperationException("reset");
      }
   }

   public static class FileTxnIterator implements TxnLog.TxnIterator {
      File logDir;
      long zxid;
      TxnHeader hdr;
      Record record;
      TxnDigest digest;
      File logFile;
      InputArchive ia;
      static final String CRC_ERROR = "CRC check failed";
      PositionInputStream inputStream;
      private ArrayList storedFiles;

      public FileTxnIterator(File logDir, long zxid, boolean fastForward) throws IOException {
         this.inputStream = null;
         this.logDir = logDir;
         this.zxid = zxid;
         this.init();
         if (fastForward && this.hdr != null) {
            while(this.hdr.getZxid() < zxid && this.next()) {
            }
         }

      }

      public FileTxnIterator(File logDir, long zxid) throws IOException {
         this(logDir, zxid, true);
      }

      void init() throws IOException {
         this.storedFiles = new ArrayList();

         for(File f : Util.sortDataDir(FileTxnLog.getLogFiles(this.logDir.listFiles(), 0L), "log", false)) {
            if (Util.getZxidFromName(f.getName(), "log") >= this.zxid) {
               this.storedFiles.add(f);
            } else if (Util.getZxidFromName(f.getName(), "log") < this.zxid) {
               this.storedFiles.add(f);
               break;
            }
         }

         this.goToNextLog();
         this.next();
      }

      public long getStorageSize() {
         long sum = 0L;

         for(File f : this.storedFiles) {
            sum += f.length();
         }

         return sum;
      }

      private boolean goToNextLog() throws IOException {
         if (!this.storedFiles.isEmpty()) {
            this.logFile = (File)this.storedFiles.remove(this.storedFiles.size() - 1);

            try {
               this.ia = this.createInputArchive(this.logFile);
               return true;
            } catch (EOFException ex) {
               if (this.storedFiles.isEmpty() && this.logFile.length() == 0L) {
                  boolean deleted = this.logFile.delete();
                  if (!deleted) {
                     throw new IOException("Failed to delete empty tail log file: " + this.logFile.getName());
                  } else {
                     FileTxnLog.LOG.warn("Delete empty tail log file to recover from corruption file: {}", this.logFile.getName());
                     return false;
                  }
               } else {
                  throw ex;
               }
            }
         } else {
            return false;
         }
      }

      protected void inStreamCreated(InputArchive ia, InputStream is) throws IOException {
         FileHeader header = new FileHeader();
         header.deserialize(ia, "fileheader");
         if (header.getMagic() != FileTxnLog.TXNLOG_MAGIC) {
            throw new IOException("Transaction log: " + this.logFile + " has invalid magic number " + header.getMagic() + " != " + FileTxnLog.TXNLOG_MAGIC);
         }
      }

      protected InputArchive createInputArchive(File logFile) throws IOException {
         if (this.inputStream == null) {
            this.inputStream = new PositionInputStream(new BufferedInputStream(new FileInputStream(logFile)));
            FileTxnLog.LOG.debug("Created new input stream: {}", logFile);
            this.ia = BinaryInputArchive.getArchive(this.inputStream);
            this.inStreamCreated(this.ia, this.inputStream);
            FileTxnLog.LOG.debug("Created new input archive: {}", logFile);
         }

         return this.ia;
      }

      protected Checksum makeChecksumAlgorithm() {
         return new Adler32();
      }

      public boolean next() throws IOException {
         if (this.ia == null) {
            return false;
         } else {
            try {
               long crcValue = this.ia.readLong("crcvalue");
               byte[] bytes = Util.readTxnBytes(this.ia);
               if (bytes != null && bytes.length != 0) {
                  Checksum crc = this.makeChecksumAlgorithm();
                  crc.update(bytes, 0, bytes.length);
                  if (crcValue != crc.getValue()) {
                     throw new IOException("CRC check failed");
                  } else {
                     TxnLogEntry logEntry = SerializeUtils.deserializeTxn(bytes);
                     this.hdr = logEntry.getHeader();
                     this.record = logEntry.getTxn();
                     this.digest = logEntry.getDigest();
                     return true;
                  }
               } else {
                  throw new EOFException("Failed to read " + this.logFile);
               }
            } catch (EOFException e) {
               FileTxnLog.LOG.debug("EOF exception", e);
               this.inputStream.close();
               this.inputStream = null;
               this.ia = null;
               this.hdr = null;
               return !this.goToNextLog() ? false : this.next();
            } catch (IOException e) {
               this.inputStream.close();
               throw e;
            }
         }
      }

      public TxnHeader getHeader() {
         return this.hdr;
      }

      public Record getTxn() {
         return this.record;
      }

      public TxnDigest getDigest() {
         return this.digest;
      }

      public void close() throws IOException {
         if (this.inputStream != null) {
            this.inputStream.close();
         }

      }
   }
}
