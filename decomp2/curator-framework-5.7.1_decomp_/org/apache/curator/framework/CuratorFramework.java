package org.apache.curator.framework;

import java.io.Closeable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.api.DeleteBuilder;
import org.apache.curator.framework.api.ExistsBuilder;
import org.apache.curator.framework.api.GetACLBuilder;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.api.GetConfigBuilder;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.api.ReconfigBuilder;
import org.apache.curator.framework.api.RemoveWatchesBuilder;
import org.apache.curator.framework.api.SetACLBuilder;
import org.apache.curator.framework.api.SetDataBuilder;
import org.apache.curator.framework.api.SyncBuilder;
import org.apache.curator.framework.api.WatchesBuilder;
import org.apache.curator.framework.api.transaction.CuratorMultiTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.TransactionOp;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.schema.SchemaSet;
import org.apache.curator.framework.state.ConnectionStateErrorPolicy;
import org.apache.curator.utils.EnsurePath;
import org.apache.curator.utils.ZookeeperCompatibility;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;

public interface CuratorFramework extends Closeable {
   void start();

   void close();

   CuratorFrameworkState getState();

   /** @deprecated */
   @Deprecated
   boolean isStarted();

   CreateBuilder create();

   DeleteBuilder delete();

   ExistsBuilder checkExists();

   GetDataBuilder getData();

   SetDataBuilder setData();

   GetChildrenBuilder getChildren();

   GetACLBuilder getACL();

   SetACLBuilder setACL();

   ReconfigBuilder reconfig();

   GetConfigBuilder getConfig();

   /** @deprecated */
   CuratorTransaction inTransaction();

   CuratorMultiTransaction transaction();

   TransactionOp transactionOp();

   /** @deprecated */
   @Deprecated
   void sync(String var1, Object var2);

   void createContainers(String var1) throws Exception;

   SyncBuilder sync();

   /** @deprecated */
   RemoveWatchesBuilder watches();

   WatchesBuilder watchers();

   Listenable getConnectionStateListenable();

   Listenable getCuratorListenable();

   Listenable getUnhandledErrorListenable();

   /** @deprecated */
   @Deprecated
   CuratorFramework nonNamespaceView();

   CuratorFramework usingNamespace(String var1);

   String getNamespace();

   CuratorZookeeperClient getZookeeperClient();

   ZookeeperCompatibility getZookeeperCompatibility();

   /** @deprecated */
   @Deprecated
   EnsurePath newNamespaceAwareEnsurePath(String var1);

   /** @deprecated */
   @Deprecated
   void clearWatcherReferences(Watcher var1);

   boolean blockUntilConnected(int var1, TimeUnit var2) throws InterruptedException;

   void blockUntilConnected() throws InterruptedException;

   WatcherRemoveCuratorFramework newWatcherRemoveCuratorFramework();

   ConnectionStateErrorPolicy getConnectionStateErrorPolicy();

   QuorumVerifier getCurrentConfig();

   SchemaSet getSchemaSet();

   default CompletableFuture postSafeNotify(Object monitorHolder) {
      return this.runSafe(() -> {
         synchronized(monitorHolder) {
            monitorHolder.notifyAll();
         }
      });
   }

   CompletableFuture runSafe(Runnable var1);
}
