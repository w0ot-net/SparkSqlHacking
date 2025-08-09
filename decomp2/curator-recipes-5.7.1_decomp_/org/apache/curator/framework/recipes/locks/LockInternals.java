package org.apache.curator.framework.recipes.locks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.RetryLoop;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Iterables;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class LockInternals {
   private final WatcherRemoveCuratorFramework client;
   private final String path;
   private final String basePath;
   private final LockInternalsDriver driver;
   private final String lockName;
   private final AtomicReference revocable = new AtomicReference((Object)null);
   private final CuratorWatcher revocableWatcher = new CuratorWatcher() {
      public void process(WatchedEvent event) throws Exception {
         if (event.getType() == EventType.NodeDataChanged) {
            LockInternals.this.checkRevocableWatcher(event.getPath());
         }

      }
   };
   private final Watcher watcher = new Watcher() {
      public void process(WatchedEvent event) {
         LockInternals.this.client.postSafeNotify(LockInternals.this);
      }
   };
   private volatile int maxLeases;
   static final byte[] REVOKE_MESSAGE = "__REVOKE__".getBytes();

   public void clean() throws Exception {
      try {
         this.client.delete().forPath(this.basePath);
      } catch (KeeperException.BadVersionException var2) {
      } catch (KeeperException.NotEmptyException var3) {
      }

   }

   LockInternals(CuratorFramework client, LockInternalsDriver driver, String path, String lockName, int maxLeases) {
      this.driver = driver;
      this.lockName = lockName;
      this.maxLeases = maxLeases;
      this.client = client.newWatcherRemoveCuratorFramework();
      this.basePath = PathUtils.validatePath(path);
      this.path = ZKPaths.makePath(path, lockName);
   }

   synchronized void setMaxLeases(int maxLeases) {
      this.maxLeases = maxLeases;
      this.notifyAll();
   }

   void makeRevocable(RevocationSpec entry) {
      this.revocable.set(entry);
   }

   final void releaseLock(String lockPath) throws Exception {
      this.client.removeWatchers();
      this.revocable.set((Object)null);
      this.deleteOurPath(lockPath);
   }

   CuratorFramework getClient() {
      return this.client;
   }

   public static Collection getParticipantNodes(CuratorFramework client, final String basePath, String lockName, LockInternalsSorter sorter) throws Exception {
      List<String> names = getSortedChildren(client, basePath, lockName, sorter);
      Iterable<String> transformed = Iterables.transform(names, new Function() {
         public String apply(String name) {
            return ZKPaths.makePath(basePath, name);
         }
      });
      return ImmutableList.copyOf(transformed);
   }

   public static List getSortedChildren(CuratorFramework client, String basePath, final String lockName, final LockInternalsSorter sorter) throws Exception {
      try {
         List<String> children = (List)client.getChildren().forPath(basePath);
         List<String> sortedList = Lists.newArrayList(children);
         Collections.sort(sortedList, new Comparator() {
            public int compare(String lhs, String rhs) {
               return sorter.fixForSorting(lhs, lockName).compareTo(sorter.fixForSorting(rhs, lockName));
            }
         });
         return sortedList;
      } catch (KeeperException.NoNodeException var6) {
         return Collections.emptyList();
      }
   }

   public static List getSortedChildren(final String lockName, final LockInternalsSorter sorter, List children) {
      List<String> sortedList = Lists.newArrayList(children);
      Collections.sort(sortedList, new Comparator() {
         public int compare(String lhs, String rhs) {
            return sorter.fixForSorting(lhs, lockName).compareTo(sorter.fixForSorting(rhs, lockName));
         }
      });
      return sortedList;
   }

   List getSortedChildren() throws Exception {
      return getSortedChildren(this.client, this.basePath, this.lockName, this.driver);
   }

   String getLockName() {
      return this.lockName;
   }

   LockInternalsDriver getDriver() {
      return this.driver;
   }

   String attemptLock(long time, TimeUnit unit, byte[] lockNodeBytes) throws Exception {
      long startMillis = System.currentTimeMillis();
      Long millisToWait = unit != null ? unit.toMillis(time) : null;
      byte[] localLockNodeBytes = this.revocable.get() != null ? new byte[0] : lockNodeBytes;
      int retryCount = 0;
      String ourPath = null;
      boolean hasTheLock = false;
      boolean isDone = false;

      while(!isDone) {
         isDone = true;

         try {
            ourPath = this.driver.createsTheLock(this.client, this.path, localLockNodeBytes);
            hasTheLock = this.internalLockLoop(startMillis, millisToWait, ourPath);
         } catch (KeeperException.NoNodeException e) {
            if (!this.client.getZookeeperClient().getRetryPolicy().allowRetry(retryCount++, System.currentTimeMillis() - startMillis, RetryLoop.getDefaultRetrySleeper())) {
               throw e;
            }

            isDone = false;
         }
      }

      return hasTheLock ? ourPath : null;
   }

   private void checkRevocableWatcher(String path) throws Exception {
      RevocationSpec entry = (RevocationSpec)this.revocable.get();
      if (entry != null) {
         try {
            byte[] bytes = (byte[])((BackgroundPathable)this.client.getData().usingWatcher(this.revocableWatcher)).forPath(path);
            if (Arrays.equals(bytes, REVOKE_MESSAGE)) {
               entry.getExecutor().execute(entry.getRunnable());
            }
         } catch (KeeperException.NoNodeException var4) {
         }
      }

   }

   private boolean internalLockLoop(long startMillis, Long millisToWait, String ourPath) throws Exception {
      boolean haveTheLock = false;

      try {
         if (this.revocable.get() != null) {
            ((BackgroundPathable)this.client.getData().usingWatcher(this.revocableWatcher)).forPath(ourPath);
         }

         while(this.client.getState() == CuratorFrameworkState.STARTED && !haveTheLock) {
            List<String> children = this.getSortedChildren();
            String sequenceNodeName = ourPath.substring(this.basePath.length() + 1);
            PredicateResults predicateResults = this.driver.getsTheLock(this.client, children, sequenceNodeName, this.maxLeases);
            if (!predicateResults.getsTheLock()) {
               String previousSequencePath = this.basePath + "/" + predicateResults.getPathToWatch();
               synchronized(this) {
                  try {
                     ((BackgroundPathable)this.client.getData().usingWatcher(this.watcher)).forPath(previousSequencePath);
                     if (millisToWait != null) {
                        millisToWait = millisToWait - (System.currentTimeMillis() - startMillis);
                        startMillis = System.currentTimeMillis();
                        if (millisToWait <= 0L) {
                           break;
                        }

                        this.wait(millisToWait);
                     } else {
                        this.wait();
                     }
                  } catch (KeeperException.NoNodeException var13) {
                  }
               }
            } else {
               haveTheLock = true;
            }
         }
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         this.deleteOurPathQuietly(ourPath, e);
         throw e;
      }

      if (!haveTheLock) {
         this.deleteOurPath(ourPath);
      }

      return haveTheLock;
   }

   private void deleteOurPathQuietly(String ourPath, Exception ex) {
      try {
         this.deleteOurPath(ourPath);
      } catch (Exception suppressed) {
         ex.addSuppressed(suppressed);
      }

   }

   private void deleteOurPath(String ourPath) throws Exception {
      try {
         ((ChildrenDeletable)this.client.delete().guaranteed()).forPath(ourPath);
      } catch (KeeperException.NoNodeException var3) {
      }

   }
}
