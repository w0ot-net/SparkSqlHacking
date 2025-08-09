package org.apache.zookeeper.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.InputArchive;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.server.persistence.FileSnap;
import org.apache.zookeeper.server.persistence.SnapStream;

@Public
public class SnapshotRecursiveSummary {
   public static void main(String[] args) throws Exception {
      if (args.length != 3) {
         System.err.println(getUsage());
         System.exit(2);
      }

      int maxDepth = 0;

      try {
         maxDepth = Integer.parseInt(args[2]);
      } catch (NumberFormatException var3) {
         System.err.println(getUsage());
         System.exit(2);
      }

      (new SnapshotRecursiveSummary()).run(args[0], args[1], maxDepth);
   }

   public void run(String snapshotFileName, String startingNode, int maxDepth) throws IOException {
      File snapshotFile = new File(snapshotFileName);
      InputStream is = SnapStream.getInputStream(snapshotFile);

      try {
         InputArchive ia = BinaryInputArchive.getArchive(is);
         DataTree dataTree = new DataTree();
         Map<Long, Integer> sessions = new HashMap();
         FileSnap.deserialize(dataTree, sessions, ia);
         this.printZnodeDetails(dataTree, startingNode, maxDepth);
      } catch (Throwable var10) {
         if (is != null) {
            try {
               is.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }
         }

         throw var10;
      }

      if (is != null) {
         is.close();
      }

   }

   private void printZnodeDetails(DataTree dataTree, String startingNode, int maxDepth) {
      StringBuilder builder = new StringBuilder();
      this.printZnode(dataTree, startingNode, builder, 0, maxDepth);
      System.out.println(builder);
   }

   private long[] printZnode(DataTree dataTree, String name, StringBuilder builder, int level, int maxDepth) {
      DataNode n = dataTree.getNode(name);
      long dataSum = 0L;
      Set<String> children;
      synchronized(n) {
         if (n.data != null) {
            dataSum += (long)n.data.length;
         }

         children = n.getChildren();
      }

      long[] result = new long[]{1L, dataSum};
      if (children.size() == 0) {
         return result;
      } else {
         StringBuilder childBuilder = new StringBuilder();

         for(String child : children) {
            long[] childResult = this.printZnode(dataTree, name + (name.equals("/") ? "" : "/") + child, childBuilder, level + 1, maxDepth);
            result[0] += childResult[0];
            result[1] += childResult[1];
         }

         if (maxDepth == 0 || level <= maxDepth) {
            String tab = String.join("", Collections.nCopies(level, "--"));
            builder.append(tab + " " + name + "\n");
            builder.append(tab + "   children: " + (result[0] - 1L) + "\n");
            builder.append(tab + "   data: " + result[1] + "\n");
            builder.append(childBuilder);
         }

         return result;
      }
   }

   public static String getUsage() {
      String newLine = System.getProperty("line.separator");
      return String.join(newLine, "USAGE:", newLine, "SnapshotRecursiveSummary  <snapshot_file>  <starting_node>  <max_depth>", newLine, "snapshot_file:    path to the zookeeper snapshot", "starting_node:    the path in the zookeeper tree where the traversal should begin", "max_depth:        defines the depth where the tool still writes to the output. 0 means there is no depth limit, every non-leaf node's stats will be displayed, 1 means it will only contain the starting node's and it's children's stats, 2 ads another level and so on. This ONLY affects the level of details displayed, NOT the calculation.");
   }
}
