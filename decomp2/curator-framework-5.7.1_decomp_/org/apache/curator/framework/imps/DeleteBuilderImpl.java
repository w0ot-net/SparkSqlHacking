package org.apache.curator.framework.imps;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.BackgroundVersionable;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.DeleteBuilder;
import org.apache.curator.framework.api.DeleteBuilderMain;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.transaction.OperationType;
import org.apache.curator.framework.api.transaction.TransactionDeleteBuilder;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class DeleteBuilderImpl implements DeleteBuilder, BackgroundOperation, ErrorListenerPathable {
   private final CuratorFrameworkImpl client;
   private int version;
   private Backgrounding backgrounding;
   private boolean deletingChildrenIfNeeded;
   private boolean guaranteed;
   private boolean quietly;
   @VisibleForTesting
   boolean failNextDeleteForTesting = false;
   @VisibleForTesting
   boolean failBeforeNextDeleteForTesting = false;

   DeleteBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.version = -1;
      this.backgrounding = new Backgrounding();
      this.deletingChildrenIfNeeded = false;
      this.guaranteed = false;
      this.quietly = false;
   }

   public DeleteBuilderImpl(CuratorFrameworkImpl client, int version, Backgrounding backgrounding, boolean deletingChildrenIfNeeded, boolean guaranteed, boolean quietly) {
      this.client = client;
      this.version = version;
      this.backgrounding = backgrounding;
      this.deletingChildrenIfNeeded = deletingChildrenIfNeeded;
      this.guaranteed = guaranteed;
      this.quietly = quietly;
   }

   TransactionDeleteBuilder asTransactionDeleteBuilder(final Object context, final CuratorMultiTransactionRecord transaction) {
      return new TransactionDeleteBuilder() {
         public Object forPath(String path) throws Exception {
            String fixedPath = DeleteBuilderImpl.this.client.fixForNamespace(path);
            transaction.add(Op.delete(fixedPath, DeleteBuilderImpl.this.version), OperationType.DELETE, path);
            return context;
         }

         public Pathable withVersion(int version) {
            DeleteBuilderImpl.this.withVersion(version);
            return this;
         }
      };
   }

   public DeleteBuilderMain quietly() {
      this.quietly = true;
      return this;
   }

   public DeleteBuilderMain idempotent() {
      return this.quietly();
   }

   public ChildrenDeletable guaranteed() {
      this.guaranteed = true;
      return this;
   }

   public BackgroundVersionable deletingChildrenIfNeeded() {
      this.deletingChildrenIfNeeded = true;
      return this;
   }

   public BackgroundPathable withVersion(int version) {
      this.version = version;
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
      return CuratorEventType.DELETE;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("DeleteBuilderImpl-Background");
         this.client.getZooKeeper().delete((String)operationAndData.getData(), this.version, new AsyncCallback.VoidCallback() {
            public void processResult(int rc, String path, Object ctx) {
               trace.setReturnCode(rc).setPath(path).commit();
               if (rc == Code.OK.intValue() && DeleteBuilderImpl.this.failNextDeleteForTesting) {
                  DeleteBuilderImpl.this.failNextDeleteForTesting = false;
                  rc = Code.CONNECTIONLOSS.intValue();
               }

               if (rc == Code.NOTEMPTY.intValue() && DeleteBuilderImpl.this.deletingChildrenIfNeeded) {
                  DeleteBuilderImpl.this.backgroundDeleteChildrenThenNode(operationAndData);
               } else {
                  if (rc == Code.NONODE.intValue() && DeleteBuilderImpl.this.quietly) {
                     rc = Code.OK.intValue();
                  }

                  CuratorEvent event = new CuratorEventImpl(DeleteBuilderImpl.this.client, CuratorEventType.DELETE, rc, path, (String)null, ctx, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
                  DeleteBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
               }

            }
         }, this.backgrounding.getContext());
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   private void backgroundDeleteChildrenThenNode(final OperationAndData mainOperationAndData) {
      BackgroundOperation<String> operation = new BackgroundOperation() {
         public void performBackgroundOperation(OperationAndData dummy) throws Exception {
            try {
               ZKPaths.deleteChildren(DeleteBuilderImpl.this.client.getZooKeeper(), (String)mainOperationAndData.getData(), false);
            } catch (KeeperException var3) {
            }

            DeleteBuilderImpl.this.client.queueOperation(mainOperationAndData);
         }

         public CuratorEventType getBackgroundEventType() {
            return CuratorEventType.DELETE;
         }
      };
      OperationAndData<String> parentOperation = new OperationAndData(operation, mainOperationAndData);
      this.client.queueOperation(parentOperation);
   }

   public Void forPath(String path) throws Exception {
      this.client.getSchemaSet().getSchema(path).validateDelete(path);
      final String unfixedPath = path;
      path = this.client.fixForNamespace(path);
      if (this.backgrounding.inBackground()) {
         OperationAndData.ErrorCallback<String> errorCallback = null;
         if (this.guaranteed) {
            errorCallback = new OperationAndData.ErrorCallback() {
               public void retriesExhausted(OperationAndData operationAndData) {
                  DeleteBuilderImpl.this.client.getFailedDeleteManager().addFailedOperation(unfixedPath);
               }
            };
         }

         OperationAndData<String> operationAndData = new OperationAndData(this, path, this.backgrounding.getCallback(), errorCallback, this.backgrounding.getContext(), (Watching)null) {
            void callPerformBackgroundOperation() throws Exception {
               if (DeleteBuilderImpl.this.failBeforeNextDeleteForTesting) {
                  DeleteBuilderImpl.this.failBeforeNextDeleteForTesting = false;
                  throw new KeeperException.ConnectionLossException();
               } else {
                  super.callPerformBackgroundOperation();
               }
            }
         };
         this.client.processBackgroundOperation(operationAndData, (CuratorEvent)null);
      } else {
         this.pathInForeground(path, unfixedPath);
      }

      return null;
   }

   protected int getVersion() {
      return this.version;
   }

   private void pathInForeground(final String path, String unfixedPath) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("DeleteBuilderImpl-Foreground");

      try {
         RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
            public Void call() throws Exception {
               if (DeleteBuilderImpl.this.failBeforeNextDeleteForTesting) {
                  DeleteBuilderImpl.this.failBeforeNextDeleteForTesting = false;
                  throw new KeeperException.ConnectionLossException();
               } else {
                  try {
                     DeleteBuilderImpl.this.client.getZooKeeper().delete(path, DeleteBuilderImpl.this.version);
                  } catch (KeeperException.NoNodeException e) {
                     if (!DeleteBuilderImpl.this.quietly) {
                        throw e;
                     }
                  } catch (KeeperException.NotEmptyException e) {
                     if (!DeleteBuilderImpl.this.deletingChildrenIfNeeded) {
                        throw e;
                     }

                     ZKPaths.deleteChildren(DeleteBuilderImpl.this.client.getZooKeeper(), path, true);
                  }

                  if (DeleteBuilderImpl.this.failNextDeleteForTesting) {
                     DeleteBuilderImpl.this.failNextDeleteForTesting = false;
                     throw new KeeperException.ConnectionLossException();
                  } else {
                     return null;
                  }
               }
            }
         });
      } catch (Exception var5) {
         ThreadUtils.checkInterrupted(var5);
         if ((this.client.getZookeeperClient().getRetryPolicy().allowRetry(var5) || var5 instanceof InterruptedException) && this.guaranteed) {
            this.client.getFailedDeleteManager().addFailedOperation(unfixedPath);
         }

         throw var5;
      }

      trace.setPath(path).commit();
   }
}
