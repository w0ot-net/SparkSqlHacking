package org.apache.curator.utils;

import java.util.Collections;
import java.util.List;
import org.apache.curator.shaded.com.google.common.base.Splitter;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKPaths {
   public static final String PATH_SEPARATOR = "/";
   private static final char PATH_SEPARATOR_CHAR = '/';
   private static final CreateMode NON_CONTAINER_MODE;
   static final int SEQUENTIAL_SUFFIX_DIGITS = 10;
   private static final Splitter PATH_SPLITTER;

   public static CreateMode getContainerCreateMode() {
      return ZKPaths.CreateModeHolder.containerCreateMode;
   }

   public static boolean hasContainerSupport() {
      return getContainerCreateMode() != NON_CONTAINER_MODE;
   }

   public static String fixForNamespace(String namespace, String path) {
      return fixForNamespace(namespace, path, false);
   }

   public static String fixForNamespace(String namespace, String path, boolean isSequential) {
      PathUtils.validatePath(path, isSequential);
      return namespace != null ? makePath(namespace, path) : path;
   }

   public static String getNodeFromPath(String path) {
      PathUtils.validatePath(path);
      int i = path.lastIndexOf(47);
      if (i < 0) {
         return path;
      } else {
         return i + 1 >= path.length() ? "" : path.substring(i + 1);
      }
   }

   public static PathAndNode getPathAndNode(String path) {
      PathUtils.validatePath(path);
      int i = path.lastIndexOf(47);
      if (i < 0) {
         return new PathAndNode(path, "");
      } else if (i + 1 >= path.length()) {
         return new PathAndNode("/", "");
      } else {
         String node = path.substring(i + 1);
         String parentPath = i > 0 ? path.substring(0, i) : "/";
         return new PathAndNode(parentPath, node);
      }
   }

   public static String extractSequentialSuffix(String path) {
      int length = path.length();
      return length > 10 ? path.substring(length - 10) : path;
   }

   public static List split(String path) {
      PathUtils.validatePath(path);
      return PATH_SPLITTER.splitToList(path);
   }

   public static void mkdirs(ZooKeeper zookeeper, String path) throws InterruptedException, KeeperException {
      mkdirs(zookeeper, path, true, (InternalACLProvider)null, false);
   }

   public static void mkdirs(ZooKeeper zookeeper, String path, boolean makeLastNode) throws InterruptedException, KeeperException {
      mkdirs(zookeeper, path, makeLastNode, (InternalACLProvider)null, false);
   }

   public static void mkdirs(ZooKeeper zookeeper, String path, boolean makeLastNode, InternalACLProvider aclProvider) throws InterruptedException, KeeperException {
      mkdirs(zookeeper, path, makeLastNode, aclProvider, false);
   }

   public static void mkdirs(ZooKeeper zookeeper, String path, boolean makeLastNode, InternalACLProvider aclProvider, boolean asContainers) throws InterruptedException, KeeperException {
      PathUtils.validatePath(path);
      int pos = path.length();

      String subPath;
      do {
         pos = path.lastIndexOf(47, pos - 1);
         subPath = path.substring(0, pos);
      } while(pos > 0 && zookeeper.exists(subPath, false) == null);

      do {
         pos = path.indexOf(47, pos + 1);
         if (pos == -1) {
            if (!makeLastNode) {
               break;
            }

            pos = path.length();
         }

         subPath = path.substring(0, pos);

         try {
            List<ACL> acl = null;
            if (aclProvider != null) {
               acl = aclProvider.getAclForPath(subPath);
               if (acl == null) {
                  acl = aclProvider.getDefaultAcl();
               }
            }

            if (acl == null) {
               acl = Ids.OPEN_ACL_UNSAFE;
            }

            zookeeper.create(subPath, new byte[0], acl, getCreateMode(asContainers));
         } catch (KeeperException.NodeExistsException var8) {
         }
      } while(pos < path.length());

   }

   public static void deleteChildren(ZooKeeper zookeeper, String path, boolean deleteSelf) throws InterruptedException, KeeperException {
      PathUtils.validatePath(path);

      List<String> children;
      try {
         children = zookeeper.getChildren(path, (Watcher)null);
      } catch (KeeperException.NoNodeException var9) {
         return;
      }

      for(String child : children) {
         String fullPath = makePath(path, child);
         deleteChildren(zookeeper, fullPath, true);
      }

      if (deleteSelf) {
         try {
            zookeeper.delete(path, -1);
         } catch (KeeperException.NotEmptyException var7) {
            deleteChildren(zookeeper, path, true);
         } catch (KeeperException.NoNodeException var8) {
         }
      }

   }

   public static List getSortedChildren(ZooKeeper zookeeper, String path) throws InterruptedException, KeeperException {
      List<String> children = zookeeper.getChildren(path, false);
      List<String> sortedList = Lists.newArrayList((Iterable)children);
      Collections.sort(sortedList);
      return sortedList;
   }

   public static String makePath(String parent, String child) {
      int maxPathLength = nullableStringLength(parent) + nullableStringLength(child) + 2;
      StringBuilder path = new StringBuilder(maxPathLength);
      joinPath(path, parent, child);
      return path.toString();
   }

   public static String makePath(String parent, String firstChild, String... restChildren) {
      int maxPathLength = nullableStringLength(parent) + nullableStringLength(firstChild) + 2;
      if (restChildren != null) {
         for(String child : restChildren) {
            maxPathLength += nullableStringLength(child) + 1;
         }
      }

      StringBuilder path = new StringBuilder(maxPathLength);
      joinPath(path, parent, firstChild);
      if (restChildren == null) {
         return path.toString();
      } else {
         for(String child : restChildren) {
            joinPath(path, "", child);
         }

         return path.toString();
      }
   }

   private static int nullableStringLength(String s) {
      return s != null ? s.length() : 0;
   }

   private static void joinPath(StringBuilder path, String parent, String child) {
      if (parent != null && parent.length() > 0) {
         if (parent.charAt(0) != '/') {
            path.append('/');
         }

         if (parent.charAt(parent.length() - 1) == '/') {
            path.append(parent, 0, parent.length() - 1);
         } else {
            path.append(parent);
         }
      }

      if (child == null || child.length() == 0 || child.length() == 1 && child.charAt(0) == '/') {
         if (path.length() == 0) {
            path.append('/');
         }

      } else {
         path.append('/');
         int childAppendBeginIndex;
         if (child.charAt(0) == '/') {
            childAppendBeginIndex = 1;
         } else {
            childAppendBeginIndex = 0;
         }

         int childAppendEndIndex;
         if (child.charAt(child.length() - 1) == '/') {
            childAppendEndIndex = child.length() - 1;
         } else {
            childAppendEndIndex = child.length();
         }

         path.append(child, childAppendBeginIndex, childAppendEndIndex);
      }
   }

   private ZKPaths() {
   }

   private static CreateMode getCreateMode(boolean asContainers) {
      return asContainers ? getContainerCreateMode() : CreateMode.PERSISTENT;
   }

   static {
      NON_CONTAINER_MODE = CreateMode.PERSISTENT;
      PATH_SPLITTER = Splitter.on('/').omitEmptyStrings();
   }

   private static class CreateModeHolder {
      private static final Logger log = LoggerFactory.getLogger(ZKPaths.class);
      private static final CreateMode containerCreateMode;

      static {
         CreateMode localCreateMode;
         try {
            localCreateMode = CreateMode.valueOf("CONTAINER");
         } catch (IllegalArgumentException var2) {
            localCreateMode = ZKPaths.NON_CONTAINER_MODE;
            log.warn("The version of ZooKeeper being used doesn't support Container nodes. CreateMode.PERSISTENT will be used instead.");
         }

         containerCreateMode = localCreateMode;
      }
   }

   public static class PathAndNode {
      private final String path;
      private final String node;

      public PathAndNode(String path, String node) {
         this.path = path;
         this.node = node;
      }

      public String getPath() {
         return this.path;
      }

      public String getNode() {
         return this.node;
      }

      public int hashCode() {
         int prime = 31;
         int result = 1;
         result = 31 * result + this.node.hashCode();
         result = 31 * result + this.path.hashCode();
         return result;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            PathAndNode other = (PathAndNode)obj;
            if (!this.node.equals(other.node)) {
               return false;
            } else {
               return this.path.equals(other.path);
            }
         }
      }

      public String toString() {
         return "PathAndNode [path=" + this.path + ", node=" + this.node + "]";
      }
   }
}
