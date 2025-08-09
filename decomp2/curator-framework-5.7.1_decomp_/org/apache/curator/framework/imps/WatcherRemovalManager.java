package org.apache.curator.framework.imps;

import java.util.List;
import java.util.Set;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatcherRemovalManager {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final CuratorFrameworkImpl client;
   private final Set entries = Sets.newConcurrentHashSet();

   WatcherRemovalManager(CuratorFrameworkImpl client) {
      this.client = client;
   }

   void add(NamespaceWatcher watcher) {
      watcher = (NamespaceWatcher)Preconditions.checkNotNull(watcher, "watcher cannot be null");
      this.entries.add(watcher);
   }

   @VisibleForTesting
   Set getEntries() {
      return Sets.newHashSet(this.entries);
   }

   void removeWatchers() {
      List<NamespaceWatcher> localEntries = Lists.newArrayList(this.entries);

      while(localEntries.size() > 0) {
         NamespaceWatcher watcher = (NamespaceWatcher)localEntries.remove(0);
         if (this.entries.remove(watcher)) {
            try {
               this.log.debug("Removing watcher for path: " + watcher.getUnfixedPath());
               RemoveWatchesBuilderImpl builder = new RemoveWatchesBuilderImpl(this.client);
               builder.internalRemoval(watcher, watcher.getUnfixedPath());
            } catch (Exception var4) {
               this.log.error("Could not remove watcher for path: " + watcher.getUnfixedPath());
            }
         }
      }

   }

   void noteTriggeredWatcher(NamespaceWatcher watcher) {
      this.entries.remove(watcher);
   }
}
