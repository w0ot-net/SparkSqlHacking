package org.apache.curator.framework.imps;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryLoop;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.ZooKeeper;

class NamespaceFacade extends CuratorFrameworkImpl {
   private final CuratorFrameworkImpl client;
   private final NamespaceImpl namespace;
   private final FailedDeleteManager failedDeleteManager = new FailedDeleteManager(this);

   NamespaceFacade(CuratorFrameworkImpl client, String namespace) {
      super(client);
      this.client = client;
      this.namespace = new NamespaceImpl(client, namespace);
   }

   public CuratorFramework nonNamespaceView() {
      return this.usingNamespace((String)null);
   }

   public CuratorFramework usingNamespace(String newNamespace) {
      return this.client.getNamespaceFacadeCache().get(newNamespace);
   }

   public String getNamespace() {
      return this.namespace.getNamespace();
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
      throw new UnsupportedOperationException("getCuratorListenable() is only available from a non-namespaced CuratorFramework instance");
   }

   public Listenable getUnhandledErrorListenable() {
      return this.client.getUnhandledErrorListenable();
   }

   public void sync(String path, Object context) {
      this.internalSync(this, path, context);
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
      return this.namespace.unfixForNamespace(path);
   }

   String fixForNamespace(String path) {
      return this.namespace.fixForNamespace(path, false);
   }

   String fixForNamespace(String path, boolean isSequential) {
      return this.namespace.fixForNamespace(path, isSequential);
   }

   public EnsurePath newNamespaceAwareEnsurePath(String path) {
      return this.namespace.newNamespaceAwareEnsurePath(path);
   }

   FailedDeleteManager getFailedDeleteManager() {
      return this.failedDeleteManager;
   }
}
