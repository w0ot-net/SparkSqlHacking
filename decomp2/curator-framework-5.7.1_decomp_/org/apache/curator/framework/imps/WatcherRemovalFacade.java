package org.apache.curator.framework.imps;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryLoop;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;

class WatcherRemovalFacade extends CuratorFrameworkImpl implements WatcherRemoveCuratorFramework {
   private final CuratorFrameworkImpl client;
   private final WatcherRemovalManager removalManager;

   WatcherRemovalFacade(CuratorFrameworkImpl client) {
      super(client);
      this.client = client;
      this.removalManager = new WatcherRemovalManager(client);
   }

   public WatcherRemoveCuratorFramework newWatcherRemoveCuratorFramework() {
      return this.client.newWatcherRemoveCuratorFramework();
   }

   WatcherRemovalManager getRemovalManager() {
      return this.removalManager;
   }

   public QuorumVerifier getCurrentConfig() {
      return this.client.getCurrentConfig();
   }

   public void removeWatchers() {
      this.removalManager.removeWatchers();
   }

   WatcherRemovalManager getWatcherRemovalManager() {
      return this.removalManager;
   }

   public CuratorFramework nonNamespaceView() {
      return this.client.nonNamespaceView();
   }

   public CuratorFramework usingNamespace(String newNamespace) {
      return this.client.usingNamespace(newNamespace);
   }

   public String getNamespace() {
      return this.client.getNamespace();
   }

   public void start() {
      throw new UnsupportedOperationException();
   }

   public void close() {
      throw new UnsupportedOperationException();
   }

   public Listenable getConnectionStateListenable() {
      return this.client.getConnectionStateListenable();
   }

   public Listenable getCuratorListenable() {
      return this.client.getCuratorListenable();
   }

   public Listenable getUnhandledErrorListenable() {
      return this.client.getUnhandledErrorListenable();
   }

   public void sync(String path, Object context) {
      this.client.sync(path, context);
   }

   public CuratorZookeeperClient getZookeeperClient() {
      return this.client.getZookeeperClient();
   }

   RetryLoop newRetryLoop() {
      return this.client.newRetryLoop();
   }

   ZooKeeper getZooKeeper() throws Exception {
      return this.client.getZooKeeper();
   }

   void processBackgroundOperation(OperationAndData operationAndData, CuratorEvent event) {
      this.client.processBackgroundOperation(operationAndData, event);
   }

   void logError(String reason, Throwable e) {
      this.client.logError(reason, e);
   }

   String unfixForNamespace(String path) {
      return this.client.unfixForNamespace(path);
   }

   String fixForNamespace(String path) {
      return this.client.fixForNamespace(path);
   }

   String fixForNamespace(String path, boolean isSequential) {
      return this.client.fixForNamespace(path, isSequential);
   }

   public EnsurePath newNamespaceAwareEnsurePath(String path) {
      return this.client.newNamespaceAwareEnsurePath(path);
   }

   FailedDeleteManager getFailedDeleteManager() {
      return this.client.getFailedDeleteManager();
   }
}
