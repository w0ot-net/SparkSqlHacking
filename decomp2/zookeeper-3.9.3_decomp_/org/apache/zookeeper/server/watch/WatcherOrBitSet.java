package org.apache.zookeeper.server.watch;

import java.util.Set;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.server.util.BitHashSet;

public class WatcherOrBitSet {
   private Set watchers;
   private BitHashSet watcherBits;

   public WatcherOrBitSet(Set watchers) {
      this.watchers = watchers;
   }

   public WatcherOrBitSet(BitHashSet watcherBits) {
      this.watcherBits = watcherBits;
   }

   public boolean contains(Watcher watcher) {
      return this.watchers == null ? false : this.watchers.contains(watcher);
   }

   public boolean contains(int watcherBit) {
      return this.watcherBits == null ? false : this.watcherBits.contains(watcherBit);
   }

   public int size() {
      if (this.watchers != null) {
         return this.watchers.size();
      } else {
         return this.watcherBits != null ? this.watcherBits.size() : 0;
      }
   }
}
