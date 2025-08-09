package org.apache.zookeeper.server;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.InputArchive;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.data.StatPersisted;
import org.apache.zookeeper.server.persistence.FileSnap;
import org.apache.zookeeper.server.persistence.SnapStream;
import org.apache.zookeeper.server.persistence.Util;
import org.apache.zookeeper.util.ServiceUtils;

@Public
public class SnapshotFormatter {
   private static Integer INODE_IDX = 1000;

   public static void main(String[] args) throws Exception {
      String snapshotFile = null;
      boolean dumpData = false;
      boolean dumpJson = false;

      int i;
      for(i = 0; i < args.length; ++i) {
         if (args[i].equals("-d")) {
            dumpData = true;
         } else {
            if (!args[i].equals("-json")) {
               snapshotFile = args[i];
               ++i;
               break;
            }

            dumpJson = true;
         }
      }

      if (args.length == i && snapshotFile != null) {
         String error = ZKUtil.validateFileInput(snapshotFile);
         if (null != error) {
            System.err.println(error);
            ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
         }

         if (dumpData && dumpJson) {
            System.err.println("Cannot specify both data dump (-d) and json mode (-json) in same call");
            ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
         }

         (new SnapshotFormatter()).run(snapshotFile, dumpData, dumpJson);
      } else {
         System.err.println("USAGE: SnapshotFormatter [-d|-json] snapshot_file");
         System.err.println("       -d dump the data for each znode");
         System.err.println("       -json dump znode info in json format");
         ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
      }
   }

   public void run(String snapshotFileName, boolean dumpData, boolean dumpJson) throws IOException {
      File snapshotFile = new File(snapshotFileName);
      InputStream is = SnapStream.getInputStream(snapshotFile);

      try {
         InputArchive ia = BinaryInputArchive.getArchive(is);
         DataTree dataTree = new DataTree();
         Map<Long, Integer> sessions = new HashMap();
         FileSnap.deserialize(dataTree, sessions, ia);
         long fileNameZxid = Util.getZxidFromName(snapshotFile.getName(), "snapshot");
         if (dumpJson) {
            this.printSnapshotJson(dataTree);
         } else {
            this.printDetails(dataTree, sessions, dumpData, fileNameZxid);
         }
      } catch (Throwable var12) {
         if (is != null) {
            try {
               is.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }
         }

         throw var12;
      }

      if (is != null) {
         is.close();
      }

   }

   private void printDetails(DataTree dataTree, Map sessions, boolean dumpData, long fileNameZxid) {
      long dtZxid = this.printZnodeDetails(dataTree, dumpData);
      this.printSessionDetails(dataTree, sessions);
      DataTree.ZxidDigest targetZxidDigest = dataTree.getDigestFromLoadedSnapshot();
      if (targetZxidDigest != null) {
         System.out.println(String.format("Target zxid digest is: %s, %s", Long.toHexString(targetZxidDigest.zxid), targetZxidDigest.digest));
      }

      System.out.println(String.format("----%nLast zxid: 0x%s", Long.toHexString(Math.max(fileNameZxid, dtZxid))));
   }

   private long printZnodeDetails(DataTree dataTree, boolean dumpData) {
      System.out.println(String.format("ZNode Details (count=%d):", dataTree.getNodeCount()));
      long zxid = this.printZnode(dataTree, "/", dumpData);
      System.out.println("----");
      return zxid;
   }

   private long printZnode(DataTree dataTree, String name, boolean dumpData) {
      System.out.println("----");
      DataNode n = dataTree.getNode(name);
      Set<String> children;
      long zxid;
      synchronized(n) {
         System.out.println(name);
         this.printStat(n.stat);
         zxid = Math.max(n.stat.getMzxid(), n.stat.getPzxid());
         if (dumpData) {
            System.out.println("  data = " + (n.data == null ? "" : Base64.getEncoder().encodeToString(n.data)));
         } else {
            System.out.println("  dataLength = " + (n.data == null ? 0 : n.data.length));
         }

         children = n.getChildren();
      }

      if (children != null) {
         for(String child : children) {
            long cxid = this.printZnode(dataTree, name + (name.equals("/") ? "" : "/") + child, dumpData);
            zxid = Math.max(zxid, cxid);
         }
      }

      return zxid;
   }

   private void printSessionDetails(DataTree dataTree, Map sessions) {
      System.out.println("Session Details (sid, timeout, ephemeralCount):");

      for(Map.Entry e : sessions.entrySet()) {
         long sid = (Long)e.getKey();
         System.out.println(String.format("%#016x, %d, %d", sid, e.getValue(), dataTree.getEphemerals(sid).size()));
      }

   }

   private void printStat(StatPersisted stat) {
      this.printHex("cZxid", stat.getCzxid());
      System.out.println("  ctime = " + (new Date(stat.getCtime())).toString());
      this.printHex("mZxid", stat.getMzxid());
      System.out.println("  mtime = " + (new Date(stat.getMtime())).toString());
      this.printHex("pZxid", stat.getPzxid());
      System.out.println("  cversion = " + stat.getCversion());
      System.out.println("  dataVersion = " + stat.getVersion());
      System.out.println("  aclVersion = " + stat.getAversion());
      this.printHex("ephemeralOwner", stat.getEphemeralOwner());
   }

   private void printHex(String prefix, long value) {
      System.out.println(String.format("  %s = %#016x", prefix, value));
   }

   private void printSnapshotJson(DataTree dataTree) {
      JsonStringEncoder encoder = JsonStringEncoder.getInstance();
      System.out.printf("[1,0,{\"progname\":\"SnapshotFormatter.java\",\"progver\":\"0.01\",\"timestamp\":%d}", System.currentTimeMillis());
      this.printZnodeJson(dataTree, "/", encoder);
      System.out.print("]");
   }

   private void printZnodeJson(DataTree dataTree, String fullPath, JsonStringEncoder encoder) {
      DataNode n = dataTree.getNode(fullPath);
      if (null == n) {
         System.err.println("DataTree Node for " + fullPath + " doesn't exist");
      } else {
         String name = fullPath.equals("/") ? fullPath : fullPath.substring(fullPath.lastIndexOf("/") + 1);
         System.out.print(",");
         int dataLen;
         synchronized(n) {
            dataLen = n.data == null ? 0 : n.data.length;
         }

         StringBuilder nodeSB = new StringBuilder();
         nodeSB.append("{");
         nodeSB.append("\"name\":\"").append(encoder.quoteAsString(name)).append("\"").append(",");
         nodeSB.append("\"asize\":").append(dataLen).append(",");
         nodeSB.append("\"dsize\":").append(dataLen).append(",");
         nodeSB.append("\"dev\":").append(0).append(",");
         nodeSB.append("\"ino\":").append(INODE_IDX = INODE_IDX + 1);
         nodeSB.append("}");
         Set<String> children;
         synchronized(n) {
            children = n.getChildren();
         }

         if (children != null && children.size() > 0) {
            System.out.print("[" + nodeSB);

            for(String child : children) {
               this.printZnodeJson(dataTree, fullPath + (fullPath.equals("/") ? "" : "/") + child, encoder);
            }

            System.out.print("]");
         } else {
            System.out.print(nodeSB);
         }

      }
   }
}
