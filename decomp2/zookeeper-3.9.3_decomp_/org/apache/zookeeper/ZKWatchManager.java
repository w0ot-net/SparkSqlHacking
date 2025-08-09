package org.apache.zookeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.zookeeper.server.watch.PathParentIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ZKWatchManager implements ClientWatchManager {
   private static final Logger LOG = LoggerFactory.getLogger(ZKWatchManager.class);
   private final Map dataWatches = new HashMap();
   private final Map existWatches = new HashMap();
   private final Map childWatches = new HashMap();
   private final Map persistentWatches = new HashMap();
   private final Map persistentRecursiveWatches = new HashMap();
   private final boolean disableAutoWatchReset;
   private volatile Watcher defaultWatcher;

   ZKWatchManager(boolean disableAutoWatchReset, Watcher defaultWatcher) {
      this.disableAutoWatchReset = disableAutoWatchReset;
      this.defaultWatcher = defaultWatcher;
   }

   void setDefaultWatcher(Watcher defaultWatcher) {
      this.defaultWatcher = defaultWatcher;
   }

   Watcher getDefaultWatcher() {
      return this.defaultWatcher;
   }

   List getDataWatchList() {
      synchronized(this.dataWatches) {
         return new ArrayList(this.dataWatches.keySet());
      }
   }

   List getChildWatchList() {
      synchronized(this.childWatches) {
         return new ArrayList(this.childWatches.keySet());
      }
   }

   List getExistWatchList() {
      synchronized(this.existWatches) {
         return new ArrayList(this.existWatches.keySet());
      }
   }

   List getPersistentWatchList() {
      synchronized(this.persistentWatches) {
         return new ArrayList(this.persistentWatches.keySet());
      }
   }

   List getPersistentRecursiveWatchList() {
      synchronized(this.persistentRecursiveWatches) {
         return new ArrayList(this.persistentRecursiveWatches.keySet());
      }
   }

   Map getDataWatches() {
      return this.dataWatches;
   }

   Map getExistWatches() {
      return this.existWatches;
   }

   Map getChildWatches() {
      return this.childWatches;
   }

   Map getPersistentWatches() {
      return this.persistentWatches;
   }

   Map getPersistentRecursiveWatches() {
      return this.persistentRecursiveWatches;
   }

   private void addTo(Set from, Set to) {
      if (from != null) {
         to.addAll(from);
      }

   }

   public Map removeWatcher(String clientPath, Watcher watcher, Watcher.WatcherType watcherType, boolean local, int rc) throws KeeperException {
      this.containsWatcher(clientPath, watcher, watcherType);
      Map<Watcher.Event.EventType, Set<Watcher>> removedWatchers = new HashMap();
      HashSet<Watcher> childWatchersToRem = new HashSet();
      removedWatchers.put(Watcher.Event.EventType.ChildWatchRemoved, childWatchersToRem);
      HashSet<Watcher> dataWatchersToRem = new HashSet();
      removedWatchers.put(Watcher.Event.EventType.DataWatchRemoved, dataWatchersToRem);
      HashSet<Watcher> persistentWatchersToRem = new HashSet();
      removedWatchers.put(Watcher.Event.EventType.PersistentWatchRemoved, persistentWatchersToRem);
      boolean removedWatcher = false;
      switch (watcherType) {
         case Children:
            synchronized(this.childWatches) {
               removedWatcher = this.removeWatches(this.childWatches, watcher, clientPath, local, rc, childWatchersToRem);
               break;
            }
         case Data:
            synchronized(this.dataWatches) {
               removedWatcher = this.removeWatches(this.dataWatches, watcher, clientPath, local, rc, dataWatchersToRem);
            }

            synchronized(this.existWatches) {
               boolean removedDataWatcher = this.removeWatches(this.existWatches, watcher, clientPath, local, rc, dataWatchersToRem);
               removedWatcher |= removedDataWatcher;
               break;
            }
         case Persistent:
            synchronized(this.persistentWatches) {
               removedWatcher = this.removeWatches(this.persistentWatches, watcher, clientPath, local, rc, persistentWatchersToRem);
               break;
            }
         case PersistentRecursive:
            synchronized(this.persistentRecursiveWatches) {
               removedWatcher = this.removeWatches(this.persistentRecursiveWatches, watcher, clientPath, local, rc, persistentWatchersToRem);
               break;
            }
         case Any:
            synchronized(this.childWatches) {
               removedWatcher = this.removeWatches(this.childWatches, watcher, clientPath, local, rc, childWatchersToRem);
            }

            synchronized(this.dataWatches) {
               boolean removedDataWatcher = this.removeWatches(this.dataWatches, watcher, clientPath, local, rc, dataWatchersToRem);
               removedWatcher |= removedDataWatcher;
            }

            synchronized(this.existWatches) {
               boolean removedDataWatcher = this.removeWatches(this.existWatches, watcher, clientPath, local, rc, dataWatchersToRem);
               removedWatcher |= removedDataWatcher;
            }

            synchronized(this.persistentWatches) {
               boolean removedPersistentWatcher = this.removeWatches(this.persistentWatches, watcher, clientPath, local, rc, persistentWatchersToRem);
               removedWatcher |= removedPersistentWatcher;
            }

            synchronized(this.persistentRecursiveWatches) {
               boolean removedPersistentRecursiveWatcher = this.removeWatches(this.persistentRecursiveWatches, watcher, clientPath, local, rc, persistentWatchersToRem);
               removedWatcher |= removedPersistentRecursiveWatcher;
            }
      }

      if (!removedWatcher) {
         throw new KeeperException.NoWatcherException(clientPath);
      } else {
         return removedWatchers;
      }
   }

   private boolean contains(String path, Watcher watcherObj, Map pathVsWatchers) {
      boolean watcherExists = true;
      if (pathVsWatchers != null && pathVsWatchers.size() != 0) {
         Set<Watcher> watchers = (Set)pathVsWatchers.get(path);
         if (watchers == null) {
            watcherExists = false;
         } else if (watcherObj == null) {
            watcherExists = watchers.size() > 0;
         } else {
            watcherExists = watchers.contains(watcherObj);
         }
      } else {
         watcherExists = false;
      }

      return watcherExists;
   }

   void containsWatcher(String path, Watcher watcher, Watcher.WatcherType watcherType) throws KeeperException.NoWatcherException {
      boolean containsWatcher = false;
      switch (watcherType) {
         case Children:
            synchronized(this.childWatches) {
               containsWatcher = this.contains(path, watcher, this.childWatches);
               break;
            }
         case Data:
            synchronized(this.dataWatches) {
               containsWatcher = this.contains(path, watcher, this.dataWatches);
            }

            synchronized(this.existWatches) {
               boolean contains_temp = this.contains(path, watcher, this.existWatches);
               containsWatcher |= contains_temp;
               break;
            }
         case Persistent:
            synchronized(this.persistentWatches) {
               containsWatcher |= this.contains(path, watcher, this.persistentWatches);
               break;
            }
         case PersistentRecursive:
            synchronized(this.persistentRecursiveWatches) {
               containsWatcher |= this.contains(path, watcher, this.persistentRecursiveWatches);
               break;
            }
         case Any:
            synchronized(this.childWatches) {
               containsWatcher = this.contains(path, watcher, this.childWatches);
            }

            synchronized(this.dataWatches) {
               boolean contains_temp = this.contains(path, watcher, this.dataWatches);
               containsWatcher |= contains_temp;
            }

            synchronized(this.existWatches) {
               boolean contains_temp = this.contains(path, watcher, this.existWatches);
               containsWatcher |= contains_temp;
            }

            synchronized(this.persistentWatches) {
               boolean contains_temp = this.contains(path, watcher, this.persistentWatches);
               containsWatcher |= contains_temp;
            }

            synchronized(this.persistentRecursiveWatches) {
               boolean contains_temp = this.contains(path, watcher, this.persistentRecursiveWatches);
               containsWatcher |= contains_temp;
            }
      }

      if (!containsWatcher) {
         throw new KeeperException.NoWatcherException(path);
      }
   }

   protected boolean removeWatches(Map pathVsWatcher, Watcher watcher, String path, boolean local, int rc, Set removedWatchers) throws KeeperException {
      if (!local && rc != KeeperException.Code.OK.intValue()) {
         throw KeeperException.create(KeeperException.Code.get(rc), path);
      } else {
         boolean success = false;
         if (rc == KeeperException.Code.OK.intValue() || local && rc != KeeperException.Code.OK.intValue()) {
            if (watcher == null) {
               Set<Watcher> pathWatchers = (Set)pathVsWatcher.remove(path);
               if (pathWatchers != null) {
                  removedWatchers.addAll(pathWatchers);
                  success = true;
               }
            } else {
               Set<Watcher> watchers = (Set)pathVsWatcher.get(path);
               if (watchers != null && watchers.remove(watcher)) {
                  removedWatchers.add(watcher);
                  if (watchers.size() <= 0) {
                     pathVsWatcher.remove(path);
                  }

                  success = true;
               }
            }
         }

         return success;
      }
   }

   public Set materialize(Watcher.Event.KeeperState state, Watcher.Event.EventType type, String clientPath) {
      Set<Watcher> result = new HashSet();
      switch (type) {
         case None:
            if (this.defaultWatcher != null) {
               result.add(this.defaultWatcher);
            }

            boolean clear = this.disableAutoWatchReset && state != Watcher.Event.KeeperState.SyncConnected;
            synchronized(this.dataWatches) {
               for(Set ws : this.dataWatches.values()) {
                  result.addAll(ws);
               }

               if (clear) {
                  this.dataWatches.clear();
               }
            }

            synchronized(this.existWatches) {
               for(Set ws : this.existWatches.values()) {
                  result.addAll(ws);
               }

               if (clear) {
                  this.existWatches.clear();
               }
            }

            synchronized(this.childWatches) {
               for(Set ws : this.childWatches.values()) {
                  result.addAll(ws);
               }

               if (clear) {
                  this.childWatches.clear();
               }
            }

            synchronized(this.persistentWatches) {
               for(Set ws : this.persistentWatches.values()) {
                  result.addAll(ws);
               }
            }

            synchronized(this.persistentRecursiveWatches) {
               for(Set ws : this.persistentRecursiveWatches.values()) {
                  result.addAll(ws);
               }

               return result;
            }
         case NodeDataChanged:
         case NodeCreated:
            synchronized(this.dataWatches) {
               this.addTo((Set)this.dataWatches.remove(clientPath), result);
            }

            synchronized(this.existWatches) {
               this.addTo((Set)this.existWatches.remove(clientPath), result);
            }

            this.addPersistentWatches(clientPath, type, result);
            break;
         case NodeChildrenChanged:
            synchronized(this.childWatches) {
               this.addTo((Set)this.childWatches.remove(clientPath), result);
            }

            this.addPersistentWatches(clientPath, type, result);
            break;
         case NodeDeleted:
            synchronized(this.dataWatches) {
               this.addTo((Set)this.dataWatches.remove(clientPath), result);
            }

            synchronized(this.existWatches) {
               Set<Watcher> list = (Set)this.existWatches.remove(clientPath);
               if (list != null) {
                  this.addTo(list, result);
                  LOG.warn("We are triggering an exists watch for delete! Shouldn't happen!");
               }
            }

            synchronized(this.childWatches) {
               this.addTo((Set)this.childWatches.remove(clientPath), result);
            }

            this.addPersistentWatches(clientPath, type, result);
            break;
         default:
            String errorMsg = String.format("Unhandled watch event type %s with state %s on path %s", type, state, clientPath);
            LOG.error(errorMsg);
            throw new RuntimeException(errorMsg);
      }

      return result;
   }

   private void addPersistentWatches(String clientPath, Watcher.Event.EventType type, Set result) {
      synchronized(this.persistentWatches) {
         this.addTo((Set)this.persistentWatches.get(clientPath), result);
      }

      if (type != Watcher.Event.EventType.NodeChildrenChanged) {
         synchronized(this.persistentRecursiveWatches) {
            for(String path : PathParentIterator.forAll(clientPath).asIterable()) {
               this.addTo((Set)this.persistentRecursiveWatches.get(path), result);
            }

         }
      }
   }
}
