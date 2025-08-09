package org.apache.zookeeper.server.watch;

import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.ServerWatcher;
import org.apache.zookeeper.server.util.BitHashSet;
import org.apache.zookeeper.server.util.BitMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatchManagerOptimized implements IWatchManager, IDeadWatcherListener {
   private static final Logger LOG = LoggerFactory.getLogger(WatchManagerOptimized.class);
   private final ConcurrentHashMap pathWatches = new ConcurrentHashMap();
   private final BitMap watcherBitIdMap = new BitMap();
   private final WatcherCleaner watcherCleaner = new WatcherCleaner(this);
   private final ReentrantReadWriteLock addRemovePathRWLock = new ReentrantReadWriteLock();

   public WatchManagerOptimized() {
      this.watcherCleaner.start();
   }

   public boolean addWatch(String path, Watcher watcher) {
      boolean result = false;
      this.addRemovePathRWLock.readLock().lock();

      try {
         if (this.isDeadWatcher(watcher)) {
            LOG.debug("Ignoring addWatch with closed cnxn");
         } else {
            Integer bit = this.watcherBitIdMap.add(watcher);
            BitHashSet watchers = (BitHashSet)this.pathWatches.get(path);
            if (watchers == null) {
               watchers = new BitHashSet();
               BitHashSet existingWatchers = (BitHashSet)this.pathWatches.putIfAbsent(path, watchers);
               if (existingWatchers != null) {
                  watchers = existingWatchers;
               }
            }

            result = watchers.add(bit);
         }
      } finally {
         this.addRemovePathRWLock.readLock().unlock();
      }

      return result;
   }

   public boolean containsWatcher(String path, Watcher watcher) {
      BitHashSet watchers = (BitHashSet)this.pathWatches.get(path);
      return watchers != null && watchers.contains(this.watcherBitIdMap.getBit(watcher));
   }

   public boolean removeWatcher(String path, Watcher watcher) {
      this.addRemovePathRWLock.writeLock().lock();

      boolean var4;
      try {
         BitHashSet list = (BitHashSet)this.pathWatches.get(path);
         if (list != null && list.remove(this.watcherBitIdMap.getBit(watcher))) {
            if (list.isEmpty()) {
               this.pathWatches.remove(path);
            }

            var4 = true;
            return var4;
         }

         var4 = false;
      } finally {
         this.addRemovePathRWLock.writeLock().unlock();
      }

      return var4;
   }

   public void removeWatcher(Watcher watcher) {
      this.addRemovePathRWLock.writeLock().lock();

      Integer watcherBit;
      label32: {
         try {
            watcherBit = this.watcherBitIdMap.getBit(watcher);
            if (watcherBit != null) {
               break label32;
            }
         } finally {
            this.addRemovePathRWLock.writeLock().unlock();
         }

         return;
      }

      this.watcherCleaner.addDeadWatcher(watcherBit);
   }

   public void processDeadWatchers(Set deadWatchers) {
      BitSet bits = new BitSet();

      for(int dw : deadWatchers) {
         bits.set(dw);
      }

      for(BitHashSet watchers : this.pathWatches.values()) {
         watchers.remove(deadWatchers, bits);
      }

      for(Integer wbit : deadWatchers) {
         this.watcherBitIdMap.remove(wbit);
      }

   }

   public WatcherOrBitSet triggerWatch(String path, Watcher.Event.EventType type, long zxid, List acl) {
      return this.triggerWatch(path, type, zxid, acl, (WatcherOrBitSet)null);
   }

   public WatcherOrBitSet triggerWatch(String path, Watcher.Event.EventType type, long zxid, List acl, WatcherOrBitSet suppress) {
      WatchedEvent e = new WatchedEvent(type, Watcher.Event.KeeperState.SyncConnected, path, zxid);
      BitHashSet watchers = this.remove(path);
      if (watchers == null) {
         return null;
      } else {
         int triggeredWatches = 0;
         synchronized(watchers) {
            for(Integer wBit : watchers) {
               if (suppress == null || !suppress.contains(wBit)) {
                  Watcher w = (Watcher)this.watcherBitIdMap.get(wBit);
                  if (w != null && !this.isDeadWatcher(w)) {
                     if (w instanceof ServerWatcher) {
                        ((ServerWatcher)w).process(e, acl);
                     } else {
                        w.process(e);
                     }

                     ++triggeredWatches;
                  }
               }
            }
         }

         this.updateMetrics(type, triggeredWatches);
         return new WatcherOrBitSet(watchers);
      }
   }

   public int size() {
      int size = 0;

      for(BitHashSet watches : this.pathWatches.values()) {
         size += watches.size();
      }

      return size;
   }

   public void shutdown() {
      if (this.watcherCleaner != null) {
         this.watcherCleaner.shutdown();
      }

   }

   private BitHashSet remove(String path) {
      this.addRemovePathRWLock.writeLock().lock();

      BitHashSet var2;
      try {
         var2 = (BitHashSet)this.pathWatches.remove(path);
      } finally {
         this.addRemovePathRWLock.writeLock().unlock();
      }

      return var2;
   }

   void updateMetrics(Watcher.Event.EventType type, int size) {
      switch (type) {
         case NodeCreated:
            ServerMetrics.getMetrics().NODE_CREATED_WATCHER.add((long)size);
            break;
         case NodeDeleted:
            ServerMetrics.getMetrics().NODE_DELETED_WATCHER.add((long)size);
            break;
         case NodeDataChanged:
            ServerMetrics.getMetrics().NODE_CHANGED_WATCHER.add((long)size);
            break;
         case NodeChildrenChanged:
            ServerMetrics.getMetrics().NODE_CHILDREN_WATCHER.add((long)size);
      }

   }

   boolean isDeadWatcher(Watcher watcher) {
      return watcher instanceof ServerCnxn && ((ServerCnxn)watcher).isStale();
   }

   int pathSize() {
      return this.pathWatches.size();
   }

   public WatchesSummary getWatchesSummary() {
      return new WatchesSummary(this.watcherBitIdMap.size(), this.pathSize(), this.size());
   }

   public WatchesReport getWatches() {
      Map<Long, Set<String>> id2paths = new HashMap();

      for(Map.Entry e : this.getWatcher2PathesMap().entrySet()) {
         Long id = ((ServerCnxn)e.getKey()).getSessionId();
         Set<String> paths = new HashSet((Collection)e.getValue());
         id2paths.put(id, paths);
      }

      return new WatchesReport(id2paths);
   }

   public WatchesPathReport getWatchesByPath() {
      Map<String, Set<Long>> path2ids = new HashMap();

      for(Map.Entry e : this.pathWatches.entrySet()) {
         BitHashSet watchers = (BitHashSet)e.getValue();
         synchronized(watchers) {
            Set<Long> ids = new HashSet(watchers.size());
            path2ids.put((String)e.getKey(), ids);

            for(Integer wbit : watchers) {
               Watcher watcher = (Watcher)this.watcherBitIdMap.get(wbit);
               if (watcher instanceof ServerCnxn) {
                  ids.add(((ServerCnxn)watcher).getSessionId());
               }
            }
         }
      }

      return new WatchesPathReport(path2ids);
   }

   public Map getWatcher2PathesMap() {
      Map<Watcher, Set<String>> watcher2paths = new HashMap();

      for(Map.Entry e : this.pathWatches.entrySet()) {
         String path = (String)e.getKey();
         BitHashSet watchers = (BitHashSet)e.getValue();
         synchronized(watchers) {
            for(Integer wbit : watchers) {
               Watcher w = (Watcher)this.watcherBitIdMap.get(wbit);
               if (w != null) {
                  if (!watcher2paths.containsKey(w)) {
                     watcher2paths.put(w, new HashSet());
                  }

                  ((Set)watcher2paths.get(w)).add(path);
               }
            }
         }
      }

      return watcher2paths;
   }

   public void dumpWatches(PrintWriter pwriter, boolean byPath) {
      if (byPath) {
         for(Map.Entry e : this.pathWatches.entrySet()) {
            pwriter.println((String)e.getKey());
            BitHashSet watchers = (BitHashSet)e.getValue();
            synchronized(watchers) {
               for(Integer wbit : watchers) {
                  Watcher w = (Watcher)this.watcherBitIdMap.get(wbit);
                  if (w instanceof ServerCnxn) {
                     pwriter.print("\t0x");
                     pwriter.print(Long.toHexString(((ServerCnxn)w).getSessionId()));
                     pwriter.print("\n");
                  }
               }
            }
         }
      } else {
         for(Map.Entry e : this.getWatcher2PathesMap().entrySet()) {
            pwriter.print("0x");
            pwriter.println(Long.toHexString(((ServerCnxn)e.getKey()).getSessionId()));

            for(String path : (Set)e.getValue()) {
               pwriter.print("\t");
               pwriter.println(path);
            }
         }
      }

   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.watcherBitIdMap.size()).append(" connections watching ").append(this.pathSize()).append(" paths\n");
      sb.append("Total watches:").append(this.size());
      return sb.toString();
   }
}
