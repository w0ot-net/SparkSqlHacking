package org.apache.zookeeper.server.watch;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ServerWatcher;
import org.apache.zookeeper.server.ZooTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatchManager implements IWatchManager {
   private static final Logger LOG = LoggerFactory.getLogger(WatchManager.class);
   private final Map watchTable = new HashMap();
   private final Map watch2Paths = new HashMap();
   private int recursiveWatchQty = 0;

   public synchronized int size() {
      int result = 0;

      for(Set watches : this.watchTable.values()) {
         result += watches.size();
      }

      return result;
   }

   private boolean isDeadWatcher(Watcher watcher) {
      return watcher instanceof ServerCnxn && ((ServerCnxn)watcher).isStale();
   }

   public boolean addWatch(String path, Watcher watcher) {
      return this.addWatch(path, watcher, WatcherMode.DEFAULT_WATCHER_MODE);
   }

   public synchronized boolean addWatch(String path, Watcher watcher, WatcherMode watcherMode) {
      if (this.isDeadWatcher(watcher)) {
         LOG.debug("Ignoring addWatch with closed cnxn");
         return false;
      } else {
         Set<Watcher> list = (Set)this.watchTable.get(path);
         if (list == null) {
            list = new HashSet(4);
            this.watchTable.put(path, list);
         }

         list.add(watcher);
         Map<String, WatchStats> paths = (Map)this.watch2Paths.get(watcher);
         if (paths == null) {
            paths = new HashMap();
            this.watch2Paths.put(watcher, paths);
         }

         WatchStats stats = (WatchStats)paths.getOrDefault(path, WatchStats.NONE);
         WatchStats newStats = stats.addMode(watcherMode);
         if (newStats != stats) {
            paths.put(path, newStats);
            if (watcherMode.isRecursive()) {
               ++this.recursiveWatchQty;
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public synchronized void removeWatcher(Watcher watcher) {
      Map<String, WatchStats> paths = (Map)this.watch2Paths.remove(watcher);
      if (paths != null) {
         for(String p : paths.keySet()) {
            Set<Watcher> list = (Set)this.watchTable.get(p);
            if (list != null) {
               list.remove(watcher);
               if (list.isEmpty()) {
                  this.watchTable.remove(p);
               }
            }
         }

         for(WatchStats stats : paths.values()) {
            if (stats.hasMode(WatcherMode.PERSISTENT_RECURSIVE)) {
               --this.recursiveWatchQty;
            }
         }

      }
   }

   public WatcherOrBitSet triggerWatch(String path, Watcher.Event.EventType type, long zxid, List acl) {
      return this.triggerWatch(path, type, zxid, acl, (WatcherOrBitSet)null);
   }

   public WatcherOrBitSet triggerWatch(String path, Watcher.Event.EventType type, long zxid, List acl, WatcherOrBitSet supress) {
      WatchedEvent e = new WatchedEvent(type, Watcher.Event.KeeperState.SyncConnected, path, zxid);
      Set<Watcher> watchers = new HashSet();
      synchronized(this) {
         PathParentIterator pathParentIterator = this.getPathParentIterator(path);

         for(String localPath : pathParentIterator.asIterable()) {
            Set<Watcher> thisWatchers = (Set)this.watchTable.get(localPath);
            if (thisWatchers != null && !thisWatchers.isEmpty()) {
               Iterator<Watcher> iterator = thisWatchers.iterator();

               while(iterator.hasNext()) {
                  Watcher watcher = (Watcher)iterator.next();
                  Map<String, WatchStats> paths = (Map)this.watch2Paths.getOrDefault(watcher, Collections.emptyMap());
                  WatchStats stats = (WatchStats)paths.get(localPath);
                  if (stats == null) {
                     LOG.warn("inconsistent watch table for watcher {}, {} not in path list", watcher, localPath);
                  } else if (!pathParentIterator.atParentPath()) {
                     watchers.add(watcher);
                     WatchStats newStats = stats.removeMode(WatcherMode.STANDARD);
                     if (newStats == WatchStats.NONE) {
                        iterator.remove();
                        paths.remove(localPath);
                     } else if (newStats != stats) {
                        paths.put(localPath, newStats);
                     }
                  } else if (stats.hasMode(WatcherMode.PERSISTENT_RECURSIVE)) {
                     watchers.add(watcher);
                  }
               }

               if (thisWatchers.isEmpty()) {
                  this.watchTable.remove(localPath);
               }
            }
         }
      }

      if (watchers.isEmpty()) {
         if (LOG.isTraceEnabled()) {
            ZooTrace.logTraceMessage(LOG, 64L, "No watchers for " + path);
         }

         return null;
      } else {
         for(Watcher w : watchers) {
            if (supress == null || !supress.contains(w)) {
               if (w instanceof ServerWatcher) {
                  ((ServerWatcher)w).process(e, acl);
               } else {
                  w.process(e);
               }
            }
         }

         switch (type) {
            case NodeCreated:
               ServerMetrics.getMetrics().NODE_CREATED_WATCHER.add((long)watchers.size());
               break;
            case NodeDeleted:
               ServerMetrics.getMetrics().NODE_DELETED_WATCHER.add((long)watchers.size());
               break;
            case NodeDataChanged:
               ServerMetrics.getMetrics().NODE_CHANGED_WATCHER.add((long)watchers.size());
               break;
            case NodeChildrenChanged:
               ServerMetrics.getMetrics().NODE_CHILDREN_WATCHER.add((long)watchers.size());
         }

         return new WatcherOrBitSet(watchers);
      }
   }

   public synchronized String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.watch2Paths.size()).append(" connections watching ").append(this.watchTable.size()).append(" paths\n");
      int total = 0;

      for(Map paths : this.watch2Paths.values()) {
         total += paths.size();
      }

      sb.append("Total watches:").append(total);
      return sb.toString();
   }

   public synchronized void dumpWatches(PrintWriter pwriter, boolean byPath) {
      if (byPath) {
         for(Map.Entry e : this.watchTable.entrySet()) {
            pwriter.println((String)e.getKey());

            for(Watcher w : (Set)e.getValue()) {
               pwriter.print("\t0x");
               pwriter.print(Long.toHexString(((ServerCnxn)w).getSessionId()));
               pwriter.print("\n");
            }
         }
      } else {
         for(Map.Entry e : this.watch2Paths.entrySet()) {
            pwriter.print("0x");
            pwriter.println(Long.toHexString(((ServerCnxn)e.getKey()).getSessionId()));

            for(String path : ((Map)e.getValue()).keySet()) {
               pwriter.print("\t");
               pwriter.println(path);
            }
         }
      }

   }

   public synchronized boolean containsWatcher(String path, Watcher watcher) {
      return this.containsWatcher(path, watcher, (WatcherMode)null);
   }

   public synchronized boolean containsWatcher(String path, Watcher watcher, WatcherMode watcherMode) {
      Map<String, WatchStats> paths = (Map)this.watch2Paths.get(watcher);
      if (paths == null) {
         return false;
      } else {
         WatchStats stats = (WatchStats)paths.get(path);
         return stats != null && (watcherMode == null || stats.hasMode(watcherMode));
      }
   }

   private WatchStats unwatch(String path, Watcher watcher, Map paths, Set watchers) {
      WatchStats stats = (WatchStats)paths.remove(path);
      if (stats == null) {
         return WatchStats.NONE;
      } else {
         if (paths.isEmpty()) {
            this.watch2Paths.remove(watcher);
         }

         watchers.remove(watcher);
         if (watchers.isEmpty()) {
            this.watchTable.remove(path);
         }

         return stats;
      }
   }

   public synchronized boolean removeWatcher(String path, Watcher watcher, WatcherMode watcherMode) {
      Map<String, WatchStats> paths = (Map)this.watch2Paths.get(watcher);
      Set<Watcher> watchers = (Set)this.watchTable.get(path);
      if (paths != null && watchers != null) {
         WatchStats oldStats;
         WatchStats newStats;
         if (watcherMode != null) {
            oldStats = (WatchStats)paths.getOrDefault(path, WatchStats.NONE);
            newStats = oldStats.removeMode(watcherMode);
            if (newStats != WatchStats.NONE) {
               if (newStats != oldStats) {
                  paths.put(path, newStats);
               }
            } else if (oldStats != WatchStats.NONE) {
               this.unwatch(path, watcher, paths, watchers);
            }
         } else {
            oldStats = this.unwatch(path, watcher, paths, watchers);
            newStats = WatchStats.NONE;
         }

         if (oldStats.hasMode(WatcherMode.PERSISTENT_RECURSIVE) && !newStats.hasMode(WatcherMode.PERSISTENT_RECURSIVE)) {
            --this.recursiveWatchQty;
         }

         return oldStats != newStats;
      } else {
         return false;
      }
   }

   public synchronized boolean removeWatcher(String path, Watcher watcher) {
      return this.removeWatcher(path, watcher, (WatcherMode)null);
   }

   Map getWatch2Paths() {
      return this.watch2Paths;
   }

   public synchronized WatchesReport getWatches() {
      Map<Long, Set<String>> id2paths = new HashMap();

      for(Map.Entry e : this.watch2Paths.entrySet()) {
         Long id = ((ServerCnxn)e.getKey()).getSessionId();
         Set<String> paths = new HashSet(((Map)e.getValue()).keySet());
         id2paths.put(id, paths);
      }

      return new WatchesReport(id2paths);
   }

   public synchronized WatchesPathReport getWatchesByPath() {
      Map<String, Set<Long>> path2ids = new HashMap();

      for(Map.Entry e : this.watchTable.entrySet()) {
         Set<Long> ids = new HashSet(((Set)e.getValue()).size());
         path2ids.put((String)e.getKey(), ids);

         for(Watcher watcher : (Set)e.getValue()) {
            ids.add(((ServerCnxn)watcher).getSessionId());
         }
      }

      return new WatchesPathReport(path2ids);
   }

   public synchronized WatchesSummary getWatchesSummary() {
      int totalWatches = 0;

      for(Map paths : this.watch2Paths.values()) {
         totalWatches += paths.size();
      }

      return new WatchesSummary(this.watch2Paths.size(), this.watchTable.size(), totalWatches);
   }

   public void shutdown() {
   }

   synchronized int getRecursiveWatchQty() {
      return this.recursiveWatchQty;
   }

   private PathParentIterator getPathParentIterator(String path) {
      return this.getRecursiveWatchQty() == 0 ? PathParentIterator.forPathOnly(path) : PathParentIterator.forAll(path);
   }
}
