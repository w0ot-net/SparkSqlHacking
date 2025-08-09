package org.apache.zookeeper.server.watch;

import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.zookeeper.Watcher;

public interface IWatchManager {
   boolean addWatch(String var1, Watcher var2);

   default boolean addWatch(String path, Watcher watcher, WatcherMode watcherMode) {
      if (watcherMode == WatcherMode.DEFAULT_WATCHER_MODE) {
         return this.addWatch(path, watcher);
      } else {
         throw new UnsupportedOperationException();
      }
   }

   boolean containsWatcher(String var1, Watcher var2);

   default boolean containsWatcher(String path, Watcher watcher, @Nullable WatcherMode watcherMode) {
      if (watcherMode != null && watcherMode != WatcherMode.DEFAULT_WATCHER_MODE) {
         throw new UnsupportedOperationException("persistent watch");
      } else {
         return this.containsWatcher(path, watcher);
      }
   }

   boolean removeWatcher(String var1, Watcher var2);

   default boolean removeWatcher(String path, Watcher watcher, WatcherMode watcherMode) {
      if (watcherMode != null && watcherMode != WatcherMode.DEFAULT_WATCHER_MODE) {
         throw new UnsupportedOperationException("persistent watch");
      } else {
         return this.removeWatcher(path, watcher);
      }
   }

   void removeWatcher(Watcher var1);

   WatcherOrBitSet triggerWatch(String var1, Watcher.Event.EventType var2, long var3, List var5);

   WatcherOrBitSet triggerWatch(String var1, Watcher.Event.EventType var2, long var3, List var5, WatcherOrBitSet var6);

   int size();

   void shutdown();

   WatchesSummary getWatchesSummary();

   WatchesReport getWatches();

   WatchesPathReport getWatchesByPath();

   void dumpWatches(PrintWriter var1, boolean var2);
}
