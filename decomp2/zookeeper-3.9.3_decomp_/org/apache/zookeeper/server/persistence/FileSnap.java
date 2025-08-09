package org.apache.zookeeper.server.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import javax.annotation.Nonnull;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.util.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSnap implements SnapShot {
   File snapDir;
   SnapshotInfo lastSnapshotInfo = null;
   private volatile boolean close = false;
   private static final int VERSION = 2;
   private static final long dbId = -1L;
   private static final Logger LOG = LoggerFactory.getLogger(FileSnap.class);
   public static final int SNAP_MAGIC = ByteBuffer.wrap("ZKSN".getBytes()).getInt();
   public static final String SNAPSHOT_FILE_PREFIX = "snapshot";

   public FileSnap(@Nonnull File snapDir) {
      this.snapDir = snapDir;
   }

   public SnapshotInfo getLastSnapshotInfo() {
      return this.lastSnapshotInfo;
   }

   public long deserialize(DataTree dt, Map sessions) throws IOException {
      List<File> snapList = this.findNValidSnapshots(100);
      if (snapList.size() == 0) {
         return -1L;
      } else {
         File snap = null;
         long snapZxid = -1L;
         boolean foundValid = false;
         int i = 0;

         for(int snapListSize = snapList.size(); i < snapListSize; ++i) {
            snap = (File)snapList.get(i);
            LOG.info("Reading snapshot {}", snap);
            snapZxid = Util.getZxidFromName(snap.getName(), "snapshot");

            try {
               CheckedInputStream snapIS = SnapStream.getInputStream(snap);

               try {
                  InputArchive ia = BinaryInputArchive.getArchive(snapIS);
                  deserialize(dt, sessions, ia);
                  SnapStream.checkSealIntegrity(snapIS, ia);
                  if (dt.deserializeZxidDigest(ia, snapZxid)) {
                     SnapStream.checkSealIntegrity(snapIS, ia);
                  }

                  if (dt.deserializeLastProcessedZxid(ia)) {
                     SnapStream.checkSealIntegrity(snapIS, ia);
                  }

                  foundValid = true;
               } catch (Throwable var14) {
                  if (snapIS != null) {
                     try {
                        snapIS.close();
                     } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                     }
                  }

                  throw var14;
               }

               if (snapIS != null) {
                  snapIS.close();
               }
               break;
            } catch (IOException e) {
               LOG.warn("problem reading snap file {}", snap, e);
            }
         }

         if (!foundValid) {
            throw new IOException("Not able to find valid snapshots in " + this.snapDir);
         } else {
            dt.lastProcessedZxid = snapZxid;
            this.lastSnapshotInfo = new SnapshotInfo(dt.lastProcessedZxid, snap.lastModified() / 1000L);
            if (dt.getDigestFromLoadedSnapshot() != null) {
               dt.compareSnapshotDigests(dt.lastProcessedZxid);
            }

            return dt.lastProcessedZxid;
         }
      }
   }

   public static void deserialize(DataTree dt, Map sessions, InputArchive ia) throws IOException {
      FileHeader header = new FileHeader();
      header.deserialize(ia, "fileheader");
      if (header.getMagic() != SNAP_MAGIC) {
         throw new IOException("mismatching magic headers " + header.getMagic() + " !=  " + SNAP_MAGIC);
      } else {
         SerializeUtils.deserializeSnapshot(dt, ia, sessions);
      }
   }

   public File findMostRecentSnapshot() {
      List<File> files = this.findNValidSnapshots(1);
      return files.size() == 0 ? null : (File)files.get(0);
   }

   protected List findNValidSnapshots(int n) {
      List<File> files = Util.sortDataDir(this.snapDir.listFiles(), "snapshot", false);
      int count = 0;
      List<File> list = new ArrayList();

      for(File f : files) {
         try {
            if (SnapStream.isValidSnapshot(f)) {
               list.add(f);
               ++count;
               if (count == n) {
                  break;
               }
            }
         } catch (IOException e) {
            LOG.warn("invalid snapshot {}", f, e);
         }
      }

      return list;
   }

   public List findNRecentSnapshots(int n) throws IOException {
      List<File> files = Util.sortDataDir(this.snapDir.listFiles(), "snapshot", false);
      int count = 0;
      List<File> list = new ArrayList();

      for(File f : files) {
         if (count == n) {
            break;
         }

         if (Util.getZxidFromName(f.getName(), "snapshot") != -1L) {
            ++count;
            list.add(f);
         }
      }

      return list;
   }

   protected void serialize(DataTree dt, Map sessions, OutputArchive oa, FileHeader header) throws IOException {
      if (header == null) {
         throw new IllegalStateException("Snapshot's not open for writing: uninitialized header");
      } else {
         header.serialize(oa, "fileheader");
         SerializeUtils.serializeSnapshot(dt, oa, sessions);
      }
   }

   public synchronized void serialize(DataTree dt, Map sessions, File snapShot, boolean fsync) throws IOException {
      if (!this.close) {
         CheckedOutputStream snapOS = SnapStream.getOutputStream(snapShot, fsync);

         try {
            OutputArchive oa = BinaryOutputArchive.getArchive(snapOS);
            FileHeader header = new FileHeader(SNAP_MAGIC, 2, -1L);
            this.serialize(dt, sessions, oa, header);
            SnapStream.sealStream(snapOS, oa);
            if (dt.serializeZxidDigest(oa)) {
               SnapStream.sealStream(snapOS, oa);
            }

            if (dt.serializeLastProcessedZxid(oa)) {
               SnapStream.sealStream(snapOS, oa);
            }

            this.lastSnapshotInfo = new SnapshotInfo(Util.getZxidFromName(snapShot.getName(), "snapshot"), snapShot.lastModified() / 1000L);
         } catch (Throwable var9) {
            if (snapOS != null) {
               try {
                  snapOS.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (snapOS != null) {
            snapOS.close();
         }

      } else {
         throw new IOException("FileSnap has already been closed");
      }
   }

   public synchronized void close() throws IOException {
      this.close = true;
   }
}
