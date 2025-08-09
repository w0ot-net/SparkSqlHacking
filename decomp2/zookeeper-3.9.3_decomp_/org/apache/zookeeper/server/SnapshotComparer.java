package org.apache.zookeeper.server;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.CheckedInputStream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.InputArchive;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.persistence.FileSnap;
import org.apache.zookeeper.server.persistence.SnapStream;
import org.apache.zookeeper.util.ServiceUtils;

public class SnapshotComparer {
   private final Options options = new Options();
   private static final String leftOption = "left";
   private static final String rightOption = "right";
   private static final String byteThresholdOption = "bytes";
   private static final String nodeThresholdOption = "nodes";
   private static final String debugOption = "debug";
   private static final String interactiveOption = "interactive";

   private SnapshotComparer() {
      this.options.addOption(Option.builder("l").hasArg().required(true).longOpt("left").desc("(Required) The left snapshot file.").argName("LEFT").type(File.class).build());
      this.options.addOption(Option.builder("r").hasArg().required(true).longOpt("right").desc("(Required) The right snapshot file.").argName("RIGHT").type(File.class).build());
      this.options.addOption(Option.builder("b").hasArg().required(true).longOpt("bytes").desc("(Required) The node data delta size threshold, in bytes, for printing the node.").argName("BYTETHRESHOLD").type(String.class).build());
      this.options.addOption(Option.builder("n").hasArg().required(true).longOpt("nodes").desc("(Required) The descendant node delta size threshold, in nodes, for printing the node.").argName("NODETHRESHOLD").type(String.class).build());
      this.options.addOption("d", "debug", false, "Use debug output.");
      this.options.addOption("i", "interactive", false, "Enter interactive mode.");
   }

   private void usage() {
      HelpFormatter help = new HelpFormatter();
      help.printHelp(120, "java -cp <classPath> " + SnapshotComparer.class.getName(), "", this.options, "");
   }

   public static void main(String[] args) throws Exception {
      SnapshotComparer app = new SnapshotComparer();
      app.compareSnapshots(args);
   }

   private void compareSnapshots(String[] args) throws Exception {
      CommandLine parsedOptions;
      try {
         parsedOptions = (new DefaultParser()).parse(this.options, args);
      } catch (ParseException e) {
         System.err.println(e.getMessage());
         this.usage();
         ServiceUtils.requestSystemExit(ExitCode.INVALID_INVOCATION.getValue());
         return;
      }

      File left = (File)parsedOptions.getParsedOptionValue("left");
      File right = (File)parsedOptions.getParsedOptionValue("right");
      int byteThreshold = Integer.parseInt((String)parsedOptions.getParsedOptionValue("bytes"));
      int nodeThreshold = Integer.parseInt((String)parsedOptions.getParsedOptionValue("nodes"));
      boolean debug = parsedOptions.hasOption("debug");
      boolean interactive = parsedOptions.hasOption("interactive");
      System.out.println("Successfully parsed options!");
      TreeInfo leftTree = new TreeInfo(left);
      TreeInfo rightTree = new TreeInfo(right);
      System.out.println(leftTree.toString());
      System.out.println(rightTree.toString());
      compareTrees(leftTree, rightTree, byteThreshold, nodeThreshold, debug, interactive);
   }

   private static DataTree getSnapshot(File file) throws Exception {
      DataTree dataTree = new DataTree();
      Map<Long, Integer> sessions = new HashMap();
      CheckedInputStream snapIS = SnapStream.getInputStream(file);
      long beginning = System.nanoTime();
      InputArchive ia = BinaryInputArchive.getArchive(snapIS);
      FileSnap.deserialize(dataTree, sessions, ia);
      long end = System.nanoTime();
      System.out.println(String.format("Deserialized snapshot in %s in %f seconds", file.getName(), (double)(end - beginning) / (double)1000000.0F / (double)1000.0F));
      return dataTree;
   }

   private static void printThresholdInfo(int byteThreshold, int nodeThreshold) {
      System.out.println(String.format("Printing analysis for nodes difference larger than %d bytes or node count difference larger than %d.", byteThreshold, nodeThreshold));
   }

   private static void compareTrees(TreeInfo left, TreeInfo right, int byteThreshold, int nodeThreshold, boolean debug, boolean interactive) {
      int maxDepth = Math.max(left.nodesAtDepths.size(), right.nodesAtDepths.size());
      if (!interactive) {
         printThresholdInfo(byteThreshold, nodeThreshold);

         for(int i = 0; i < maxDepth; ++i) {
            System.out.println(String.format("Analysis for depth %d", i));
            compareLine(left, right, i, byteThreshold, nodeThreshold, debug, interactive);
         }
      } else {
         Scanner scanner = new Scanner(System.in);
         int currentDepth = 0;

         label41:
         while(true) {
            while(true) {
               if (currentDepth >= maxDepth) {
                  break label41;
               }

               System.out.println(String.format("Current depth is %d", currentDepth));
               System.out.println("- Press enter to move to print current depth layer;\n- Type a number to jump to and print all nodes at a given depth;\n- Enter an ABSOLUTE path to print the immediate subtree of a node. Path must start with '/'.");
               String input = scanner.nextLine();
               printThresholdInfo(byteThreshold, nodeThreshold);
               if (input.isEmpty()) {
                  System.out.println(String.format("Analysis for depth %d", currentDepth));
                  compareLine(left, right, currentDepth, byteThreshold, nodeThreshold, debug, interactive);
                  ++currentDepth;
                  break;
               }

               if (input.startsWith("/")) {
                  System.out.println(String.format("Analysis for node %s", input));
                  compareSubtree(left, right, input, byteThreshold, nodeThreshold, debug, interactive);
                  break;
               }

               try {
                  int depth = Integer.parseInt(input);
                  if (depth >= 0 && depth < maxDepth) {
                     currentDepth = depth;
                     System.out.println(String.format("Analysis for depth %d", depth));
                     compareLine(left, right, depth, byteThreshold, nodeThreshold, debug, interactive);
                     break;
                  }

                  System.out.println(String.format("Depth must be in range [%d, %d]", 0, maxDepth - 1));
               } catch (NumberFormatException var11) {
                  System.out.println(String.format("Input %s is not valid. Depth must be in range [%d, %d]. Path must be an absolute path which starts with '/'.", input, 0, maxDepth - 1));
                  break;
               }
            }

            System.out.println("");
         }
      }

      System.out.println("All layers compared.");
   }

   private static void compareSubtree(TreeInfo left, TreeInfo right, String path, int byteThreshold, int nodeThreshold, boolean debug, boolean interactive) {
      TreeInfo.TreeNode leftRoot = (TreeInfo.TreeNode)left.nodesByName.get(path);
      TreeInfo.TreeNode rightRoot = (TreeInfo.TreeNode)right.nodesByName.get(path);
      List<TreeInfo.TreeNode> leftList = (List<TreeInfo.TreeNode>)(leftRoot == null ? new ArrayList() : leftRoot.children);
      List<TreeInfo.TreeNode> rightList = (List<TreeInfo.TreeNode>)(rightRoot == null ? new ArrayList() : rightRoot.children);
      if (leftRoot == null && rightRoot == null) {
         System.out.println(String.format("Path %s is neither found in left tree nor right tree.", path));
      } else {
         compareNodes(leftList, rightList, byteThreshold, nodeThreshold, debug, interactive);
      }

   }

   private static void compareLine(TreeInfo left, TreeInfo right, int depth, int byteThreshold, int nodeThreshold, boolean debug, boolean interactive) {
      List<TreeInfo.TreeNode> leftList = (List<TreeInfo.TreeNode>)(depth >= left.nodesAtDepths.size() ? new ArrayList() : (List)left.nodesAtDepths.get(depth));
      List<TreeInfo.TreeNode> rightList = (List<TreeInfo.TreeNode>)(depth >= right.nodesAtDepths.size() ? new ArrayList() : (List)right.nodesAtDepths.get(depth));
      compareNodes(leftList, rightList, byteThreshold, nodeThreshold, debug, interactive);
   }

   private static void compareNodes(List leftList, List rightList, int byteThreshold, int nodeThreshold, boolean debug, boolean interactive) {
      Comparator<TreeInfo.TreeNode> alphabeticComparator = SnapshotComparer.TreeInfo.MakeAlphabeticComparator();
      Collections.sort(leftList, alphabeticComparator);
      Collections.sort(rightList, alphabeticComparator);
      int leftIndex = 0;
      int rightIndex = 0;
      boolean leftRemaining = leftList.size() > leftIndex;

      for(boolean rightRemaining = rightList.size() > rightIndex; leftRemaining || rightRemaining; rightRemaining = rightList.size() > rightIndex) {
         TreeInfo.TreeNode leftNode = null;
         if (leftRemaining) {
            leftNode = (TreeInfo.TreeNode)leftList.get(leftIndex);
         }

         TreeInfo.TreeNode rightNode = null;
         if (rightRemaining) {
            rightNode = (TreeInfo.TreeNode)rightList.get(rightIndex);
         }

         if (leftNode != null && rightNode != null) {
            if (debug) {
               System.out.println(String.format("Comparing %s to %s", leftNode.label, rightNode.label));
            }

            int result = leftNode.label.compareTo(rightNode.label);
            if (result < 0) {
               if (debug) {
                  System.out.println("left is less");
               }

               printLeftOnly(leftNode, byteThreshold, nodeThreshold, debug, interactive);
               ++leftIndex;
            } else if (result > 0) {
               if (debug) {
                  System.out.println("right is less");
               }

               printRightOnly(rightNode, byteThreshold, nodeThreshold, debug, interactive);
               ++rightIndex;
            } else {
               if (debug) {
                  System.out.println("same");
               }

               printBoth(leftNode, rightNode, byteThreshold, nodeThreshold, debug, interactive);
               ++leftIndex;
               ++rightIndex;
            }
         } else if (leftNode != null) {
            printLeftOnly(leftNode, byteThreshold, nodeThreshold, debug, interactive);
            ++leftIndex;
         } else {
            printRightOnly(rightNode, byteThreshold, nodeThreshold, debug, interactive);
            ++rightIndex;
         }

         leftRemaining = leftList.size() > leftIndex;
      }

   }

   static void printLeftOnly(TreeInfo.TreeNode node, int byteThreshold, int nodeThreshold, boolean debug, boolean interactive) {
      if (node.descendantSize <= (long)byteThreshold && node.descendantCount <= (long)nodeThreshold) {
         if (debug || interactive) {
            System.out.println(String.format("Filtered left node %s of size %d", node.label, node.descendantSize));
         }
      } else {
         StringBuilder builder = new StringBuilder();
         builder.append(String.format("Node %s found only in left tree. ", node.label));
         printNode(node, builder);
         System.out.println(builder.toString());
      }

   }

   static void printRightOnly(TreeInfo.TreeNode node, int byteThreshold, int nodeThreshold, boolean debug, boolean interactive) {
      if (node.descendantSize <= (long)byteThreshold && node.descendantCount <= (long)nodeThreshold) {
         if (debug || interactive) {
            System.out.println(String.format("Filtered right node %s of size %d", node.label, node.descendantSize));
         }
      } else {
         StringBuilder builder = new StringBuilder();
         builder.append(String.format("Node %s found only in right tree. ", node.label));
         printNode(node, builder);
         System.out.println(builder.toString());
      }

   }

   static void printBoth(TreeInfo.TreeNode leftNode, TreeInfo.TreeNode rightNode, int byteThreshold, int nodeThreshold, boolean debug, boolean interactive) {
      if (Math.abs(rightNode.descendantSize - leftNode.descendantSize) <= (long)byteThreshold && Math.abs(rightNode.descendantCount - leftNode.descendantCount) <= (long)nodeThreshold) {
         if (debug || interactive) {
            System.out.println(String.format("Filtered node %s of left size %d, right size %d", leftNode.label, leftNode.descendantSize, rightNode.descendantSize));
         }
      } else {
         System.out.println(String.format("Node %s found in both trees. Delta: %d bytes, %d descendants", leftNode.label, rightNode.descendantSize - leftNode.descendantSize, rightNode.descendantCount - leftNode.descendantCount));
      }

   }

   static void printNode(TreeInfo.TreeNode node, StringBuilder builder) {
      builder.append(String.format("Descendant size: %d. Descendant count: %d", node.descendantSize, node.descendantCount));
   }

   private static class TreeInfo {
      final TreeNode root;
      long count;
      List nodesAtDepths = new ArrayList();
      Map nodesByName = new HashMap();

      TreeInfo(File snapshot) throws Exception {
         DataTree dataTree = SnapshotComparer.getSnapshot(snapshot);
         this.count = 0L;
         long beginning = System.nanoTime();
         DataNode root = dataTree.getNode("");
         long size = root.data == null ? 0L : (long)root.data.length;
         this.root = new TreeNode("", size);
         this.root.populateChildren("", dataTree, this);
         long end = System.nanoTime();
         System.out.println(String.format("Processed data tree in %f seconds", ((double)end - (double)beginning) / (double)1000000.0F / (double)1000.0F));
      }

      void registerNode(TreeNode node, int depth) {
         while(depth > this.nodesAtDepths.size()) {
            this.nodesAtDepths.add(new ArrayList());
         }

         ((ArrayList)this.nodesAtDepths.get(depth - 1)).add(node);
         this.nodesByName.put(node.label, node);
         ++this.count;
      }

      public String toString() {
         StringBuilder builder = new StringBuilder();
         builder.append(String.format("Node count: %d%n", this.count));
         builder.append(String.format("Total size: %d%n", this.root.descendantSize));
         builder.append(String.format("Max depth: %d%n", this.nodesAtDepths.size()));

         for(int i = 0; i < this.nodesAtDepths.size(); ++i) {
            builder.append(String.format("Count of nodes at depth %d: %d%n", i, ((ArrayList)this.nodesAtDepths.get(i)).size()));
         }

         return builder.toString();
      }

      public static Comparator MakeAlphabeticComparator() {
         return new TreeNode.AlphabeticComparator();
      }

      public static class TreeNode {
         final String label;
         final long size;
         final List children;
         long descendantSize;
         long descendantCount;

         public TreeNode(String label, long size) {
            this.label = label;
            this.size = size;
            this.children = new ArrayList();
         }

         void populateChildren(String path, DataTree dataTree, TreeInfo treeInfo) throws Exception {
            this.populateChildren(path, dataTree, treeInfo, 1);
         }

         void populateChildren(String path, DataTree dataTree, TreeInfo treeInfo, int currentDepth) throws Exception {
            List<String> childLabels = null;
            childLabels = dataTree.getChildren(path, (Stat)null, (Watcher)null);
            if (childLabels != null && !childLabels.isEmpty()) {
               for(String childName : childLabels) {
                  String childPath = path + "/" + childName;
                  DataNode childNode = dataTree.getNode(childPath);
                  long size;
                  synchronized(childNode) {
                     size = childNode.data == null ? 0L : (long)childNode.data.length;
                  }

                  TreeNode childTreeNode = new TreeNode(childPath, size);
                  childTreeNode.populateChildren(childPath, dataTree, treeInfo, currentDepth + 1);
                  this.children.add(childTreeNode);
               }
            }

            this.descendantSize = 0L;
            this.descendantCount = 0L;

            for(TreeNode child : this.children) {
               this.descendantSize += child.descendantSize;
               this.descendantCount += child.descendantCount;
            }

            this.descendantSize += this.size;
            this.descendantCount += (long)this.children.size();
            treeInfo.registerNode(this, currentDepth);
         }

         public static class AlphabeticComparator implements Comparator, Serializable {
            private static final long serialVersionUID = 2601197766392565593L;

            public int compare(TreeNode left, TreeNode right) {
               if (left == right) {
                  return 0;
               } else if (left == null) {
                  return -1;
               } else {
                  return right == null ? 1 : left.label.compareTo(right.label);
               }
            }
         }
      }
   }
}
