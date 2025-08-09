package org.apache.curator.framework.imps;

import java.io.Closeable;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

class NamespaceWatcher implements Watcher, Closeable {
   private volatile CuratorFrameworkImpl client;
   private volatile Watcher actualWatcher;
   private final String unfixedPath;
   private volatile CuratorWatcher curatorWatcher;

   NamespaceWatcher(CuratorFrameworkImpl client, Watcher actualWatcher, String unfixedPath) {
      this.client = client;
      this.actualWatcher = actualWatcher;
      this.unfixedPath = (String)Preconditions.checkNotNull(unfixedPath, "unfixedPath cannot be null");
      this.curatorWatcher = null;
   }

   NamespaceWatcher(CuratorFrameworkImpl client, CuratorWatcher curatorWatcher, String unfixedPath) {
      this.client = client;
      this.actualWatcher = null;
      this.curatorWatcher = curatorWatcher;
      this.unfixedPath = (String)Preconditions.checkNotNull(unfixedPath, "unfixedPath cannot be null");
   }

   String getUnfixedPath() {
      return this.unfixedPath;
   }

   public void close() {
      this.client = null;
      this.actualWatcher = null;
      this.curatorWatcher = null;
   }

   public void process(WatchedEvent event) {
      if (this.client != null) {
         if (event.getType() != EventType.None && this.client.getWatcherRemovalManager() != null) {
            this.client.getWatcherRemovalManager().noteTriggeredWatcher(this);
         }

         if (this.actualWatcher != null) {
            this.actualWatcher.process(new NamespaceWatchedEvent(this.client, event));
         } else if (this.curatorWatcher != null) {
            try {
               this.curatorWatcher.process(new NamespaceWatchedEvent(this.client, event));
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               this.client.logError("Watcher exception", e);
            }
         }
      }

   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o == null) {
         return false;
      } else if (this.getClass() != o.getClass()) {
         return false;
      } else {
         NamespaceWatcher watcher = (NamespaceWatcher)o;
         return Objects.equal(this.unfixedPath, watcher.getUnfixedPath()) && Objects.equal(this.actualWatcher, watcher.actualWatcher) && Objects.equal(this.curatorWatcher, watcher.curatorWatcher);
      }
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{this.actualWatcher, this.unfixedPath, this.curatorWatcher});
   }
}
