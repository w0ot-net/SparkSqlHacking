package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.ACLableExistBuilderMain;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.ExistsBuilder;
import org.apache.curator.framework.api.ExistsBuilderMain;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class ExistsBuilderImpl implements ExistsBuilder, BackgroundOperation, ErrorListenerPathable, ACLableExistBuilderMain {
   private final CuratorFrameworkImpl client;
   private Backgrounding backgrounding;
   private Watching watching;
   private boolean createParentsIfNeeded;
   private boolean createParentContainersIfNeeded;
   private ACLing acling;

   ExistsBuilderImpl(CuratorFrameworkImpl client) {
      this(client, new Backgrounding(), (Watcher)null, false, false);
   }

   public ExistsBuilderImpl(CuratorFrameworkImpl client, Backgrounding backgrounding, Watcher watcher, boolean createParentsIfNeeded, boolean createParentContainersIfNeeded) {
      this.client = client;
      this.backgrounding = backgrounding;
      this.watching = new Watching(client, watcher);
      this.createParentsIfNeeded = createParentsIfNeeded;
      this.createParentContainersIfNeeded = createParentContainersIfNeeded;
      this.acling = new ACLing(client.getAclProvider());
   }

   public ACLableExistBuilderMain creatingParentsIfNeeded() {
      this.createParentContainersIfNeeded = false;
      this.createParentsIfNeeded = true;
      return this;
   }

   public ACLableExistBuilderMain creatingParentContainersIfNeeded() {
      this.createParentContainersIfNeeded = true;
      this.createParentsIfNeeded = false;
      return this;
   }

   public ExistsBuilderMain withACL(List aclList) {
      this.acling = new ACLing(this.client.getAclProvider(), aclList, true);
      return this;
   }

   public BackgroundPathable watched() {
      this.watching = new Watching(this.client, true);
      return this;
   }

   public BackgroundPathable usingWatcher(Watcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return this;
   }

   public BackgroundPathable usingWatcher(CuratorWatcher watcher) {
      this.watching = new Watching(this.client, watcher);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerPathable inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, executor);
      return this;
   }

   public ErrorListenerPathable inBackground() {
      this.backgrounding = new Backgrounding(true);
      return this;
   }

   public ErrorListenerPathable inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public Pathable withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.EXISTS;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("ExistsBuilderImpl-Background");
         AsyncCallback.StatCallback callback = new AsyncCallback.StatCallback() {
            public void processResult(int rc, String path, Object ctx, Stat stat) {
               ExistsBuilderImpl.this.watching.commitWatcher(rc, true);
               trace.setReturnCode(rc).setPath(path).setWithWatcher(ExistsBuilderImpl.this.watching.hasWatcher()).setStat(stat).commit();
               CuratorEvent event = new CuratorEventImpl(ExistsBuilderImpl.this.client, CuratorEventType.EXISTS, rc, path, (String)null, ctx, stat, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
               ExistsBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         if (this.watching.isWatched()) {
            this.client.getZooKeeper().exists((String)operationAndData.getData(), true, callback, this.backgrounding.getContext());
         } else {
            this.client.getZooKeeper().exists((String)operationAndData.getData(), this.watching.getWatcher((String)operationAndData.getData()), callback, this.backgrounding.getContext());
         }
      } catch (Throwable e) {
         this.backgrounding.checkError(e, this.watching);
      }

   }

   public Stat forPath(String path) throws Exception {
      path = this.client.fixForNamespace(path);
      this.client.getSchemaSet().getSchema(path).validateWatch(path, this.watching.isWatched() || this.watching.hasWatcher());
      Stat returnStat = null;
      if (this.backgrounding.inBackground()) {
         OperationAndData<String> operationAndData = new OperationAndData(this, path, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), this.watching);
         if (!this.createParentContainersIfNeeded && !this.createParentsIfNeeded) {
            this.client.processBackgroundOperation(operationAndData, (CuratorEvent)null);
         } else {
            CreateBuilderImpl.backgroundCreateParentsThenNode(this.client, operationAndData, (String)operationAndData.getData(), this.acling.getACLProviderForParents(), this.createParentContainersIfNeeded);
         }
      } else {
         returnStat = this.pathInForeground(path);
      }

      return returnStat;
   }

   private Stat pathInForeground(String path) throws Exception {
      if (this.createParentContainersIfNeeded || this.createParentsIfNeeded) {
         final String parent = ZKPaths.getPathAndNode(path).getPath();
         if (!parent.equals("/")) {
            OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("ExistsBuilderImpl-Foreground-CreateParents");
            RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
               public Void call() throws Exception {
                  try {
                     ZKPaths.mkdirs(ExistsBuilderImpl.this.client.getZooKeeper(), parent, true, ExistsBuilderImpl.this.acling.getACLProviderForParents(), ExistsBuilderImpl.this.createParentContainersIfNeeded);
                  } catch (KeeperException.NodeExistsException var2) {
                  } catch (KeeperException.NoNodeException var3) {
                  }

                  return null;
               }
            });
            trace.setPath(path).commit();
         }
      }

      return this.pathInForegroundStandard(path);
   }

   private Stat pathInForegroundStandard(final String path) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("ExistsBuilderImpl-Foreground");
      Stat returnStat = (Stat)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public Stat call() throws Exception {
            Stat returnStat;
            if (ExistsBuilderImpl.this.watching.isWatched()) {
               returnStat = ExistsBuilderImpl.this.client.getZooKeeper().exists(path, true);
            } else {
               returnStat = ExistsBuilderImpl.this.client.getZooKeeper().exists(path, ExistsBuilderImpl.this.watching.getWatcher(path));
               int rc = returnStat != null ? Code.OK.intValue() : Code.NONODE.intValue();
               ExistsBuilderImpl.this.watching.commitWatcher(rc, true);
            }

            return returnStat;
         }
      });
      trace.setPath(path).setWithWatcher(this.watching.hasWatcher()).setStat(returnStat).commit();
      return returnStat;
   }
}
