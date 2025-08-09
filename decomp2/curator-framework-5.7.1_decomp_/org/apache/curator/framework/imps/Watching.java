package org.apache.curator.framework.imps;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;

public class Watching {
   private final Watcher watcher;
   private final CuratorWatcher curatorWatcher;
   private final boolean watched;
   private final CuratorFrameworkImpl client;
   private NamespaceWatcher namespaceWatcher;

   public Watching(CuratorFrameworkImpl client, boolean watched) {
      this.client = client;
      this.watcher = null;
      this.curatorWatcher = null;
      this.watched = watched;
   }

   public Watching(CuratorFrameworkImpl client, Watcher watcher) {
      this.client = client;
      this.watcher = watcher;
      this.curatorWatcher = null;
      this.watched = false;
   }

   public Watching(CuratorFrameworkImpl client, CuratorWatcher watcher) {
      this.client = client;
      this.watcher = null;
      this.curatorWatcher = watcher;
      this.watched = false;
   }

   public Watching(CuratorFrameworkImpl client) {
      this.client = client;
      this.watcher = null;
      this.watched = false;
      this.curatorWatcher = null;
   }

   Watcher getWatcher(String unfixedPath) {
      this.namespaceWatcher = null;
      if (this.watcher != null) {
         this.namespaceWatcher = new NamespaceWatcher(this.client, this.watcher, unfixedPath);
      } else if (this.curatorWatcher != null) {
         this.namespaceWatcher = new NamespaceWatcher(this.client, this.curatorWatcher, unfixedPath);
      }

      return this.namespaceWatcher;
   }

   boolean hasWatcher() {
      return this.watcher != null || this.curatorWatcher != null;
   }

   boolean isWatched() {
      return this.watched;
   }

   void commitWatcher(int rc, boolean isExists) {
      boolean doCommit = false;
      if (isExists) {
         doCommit = rc == Code.OK.intValue() || rc == Code.NONODE.intValue();
      } else {
         doCommit = rc == Code.OK.intValue();
      }

      if (doCommit && this.namespaceWatcher != null && this.client.getWatcherRemovalManager() != null) {
         this.client.getWatcherRemovalManager().add(this.namespaceWatcher);
      }

   }
}
