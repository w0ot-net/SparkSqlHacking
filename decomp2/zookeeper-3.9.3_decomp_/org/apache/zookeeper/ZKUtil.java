package org.apache.zookeeper;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKUtil {
   private static final Logger LOG = LoggerFactory.getLogger(ZKUtil.class);
   private static final Map permCache = new ConcurrentHashMap();

   public static boolean deleteRecursive(ZooKeeper zk, String pathRoot, int batchSize) throws InterruptedException, KeeperException {
      PathUtils.validatePath(pathRoot);
      List<String> tree = listSubTreeBFS(zk, pathRoot);
      LOG.debug("Deleting tree: {}", tree);
      if (batchSize > 0) {
         return deleteInBatch(zk, tree, batchSize);
      } else {
         for(int i = tree.size() - 1; i >= 0; --i) {
            zk.delete((String)tree.get(i), -1);
         }

         return true;
      }
   }

   public static void deleteRecursive(ZooKeeper zk, String pathRoot) throws InterruptedException, KeeperException {
      deleteRecursive(zk, pathRoot, 0);
   }

   private static boolean deleteInBatch(ZooKeeper zk, List tree, int batchSize) throws InterruptedException {
      int rateLimit = 10;
      List<Op> ops = new ArrayList();
      BatchedDeleteCbContext context = new BatchedDeleteCbContext(rateLimit);
      AsyncCallback.MultiCallback cb = (rc, path, ctx, opResults) -> {
         if (rc != KeeperException.Code.OK.intValue()) {
            ((BatchedDeleteCbContext)ctx).success.set(false);
         }

         ((BatchedDeleteCbContext)ctx).sem.release();
      };

      for(int i = tree.size() - 1; i >= 0; --i) {
         ops.add(Op.delete((String)tree.get(i), -1));
         if (ops.size() == batchSize || i == 0) {
            if (!context.success.get()) {
               break;
            }

            context.sem.acquire();
            zk.multi(ops, cb, context);
            ops = new ArrayList();
         }
      }

      context.sem.acquire(rateLimit);
      return context.success.get();
   }

   public static void deleteRecursive(ZooKeeper zk, String pathRoot, AsyncCallback.VoidCallback cb, Object ctx) throws InterruptedException, KeeperException {
      PathUtils.validatePath(pathRoot);
      List<String> tree = listSubTreeBFS(zk, pathRoot);
      LOG.debug("Deleting tree: {}", tree);

      for(int i = tree.size() - 1; i >= 0; --i) {
         zk.delete((String)tree.get(i), -1, cb, ctx);
      }

   }

   public static String validateFileInput(String filePath) {
      File file = new File(filePath);
      if (!file.exists()) {
         return "File '" + file.getAbsolutePath() + "' does not exist.";
      } else if (!file.canRead()) {
         return "Read permission is denied on the file '" + file.getAbsolutePath() + "'";
      } else {
         return file.isDirectory() ? "'" + file.getAbsolutePath() + "' is a directory. it must be a file." : null;
      }
   }

   public static List listSubTreeBFS(ZooKeeper zk, String pathRoot) throws KeeperException, InterruptedException {
      Queue<String> queue = new ArrayDeque();
      List<String> tree = new ArrayList();
      queue.add(pathRoot);
      tree.add(pathRoot);

      while(!queue.isEmpty()) {
         String node = (String)queue.poll();

         for(String child : zk.getChildren(node, false)) {
            String childPath = (node.equals("/") ? "" : node) + "/" + child;
            queue.add(childPath);
            tree.add(childPath);
         }
      }

      return tree;
   }

   public static void visitSubTreeDFS(ZooKeeper zk, String path, boolean watch, AsyncCallback.StringCallback cb) throws KeeperException, InterruptedException {
      PathUtils.validatePath(path);
      zk.getData(path, watch, (Stat)null);
      cb.processResult(KeeperException.Code.OK.intValue(), path, (Object)null, path);
      visitSubTreeDFSHelper(zk, path, watch, cb);
   }

   private static void visitSubTreeDFSHelper(ZooKeeper zk, String path, boolean watch, AsyncCallback.StringCallback cb) throws KeeperException, InterruptedException {
      boolean isRoot = path.length() == 1;

      try {
         List<String> children = zk.getChildren(path, watch, (Stat)null);
         Collections.sort(children);

         for(String child : children) {
            String childPath = (isRoot ? path : path + "/") + child;
            cb.processResult(KeeperException.Code.OK.intValue(), childPath, (Object)null, child);
         }

         for(String child : children) {
            String childPath = (isRoot ? path : path + "/") + child;
            visitSubTreeDFSHelper(zk, childPath, watch, cb);
         }

      } catch (KeeperException.NoNodeException var9) {
      }
   }

   public static String getPermString(int perms) {
      return (String)permCache.computeIfAbsent(perms, (k) -> constructPermString(k));
   }

   private static String constructPermString(int perms) {
      StringBuilder p = new StringBuilder();
      if ((perms & 4) != 0) {
         p.append('c');
      }

      if ((perms & 8) != 0) {
         p.append('d');
      }

      if ((perms & 1) != 0) {
         p.append('r');
      }

      if ((perms & 2) != 0) {
         p.append('w');
      }

      if ((perms & 16) != 0) {
         p.append('a');
      }

      return p.toString();
   }

   public static String aclToString(List acls) {
      StringBuilder sb = new StringBuilder();

      for(ACL acl : acls) {
         sb.append(acl.getId().getScheme());
         sb.append(":");
         sb.append(acl.getId().getId());
         sb.append(":");
         sb.append(getPermString(acl.getPerms()));
      }

      return sb.toString();
   }

   private static class BatchedDeleteCbContext {
      public Semaphore sem;
      public AtomicBoolean success;

      public BatchedDeleteCbContext(int rateLimit) {
         this.sem = new Semaphore(rateLimit);
         this.success = new AtomicBoolean(true);
      }
   }
}
