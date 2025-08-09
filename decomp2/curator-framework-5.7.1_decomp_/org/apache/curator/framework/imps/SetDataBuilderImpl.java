package org.apache.curator.framework.imps;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.drivers.OperationTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathAndBytesable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerPathAndBytesable;
import org.apache.curator.framework.api.PathAndBytesable;
import org.apache.curator.framework.api.SetDataBackgroundVersionable;
import org.apache.curator.framework.api.SetDataBuilder;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.VersionPathAndBytesable;
import org.apache.curator.framework.api.transaction.OperationType;
import org.apache.curator.framework.api.transaction.TransactionSetDataBuilder;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class SetDataBuilderImpl implements SetDataBuilder, BackgroundOperation, ErrorListenerPathAndBytesable {
   private final CuratorFrameworkImpl client;
   private Backgrounding backgrounding;
   private int version;
   private boolean compress;
   private boolean idempotent = false;
   @VisibleForTesting
   boolean failNextSetForTesting = false;
   @VisibleForTesting
   boolean failBeforeNextSetForTesting = false;
   @VisibleForTesting
   boolean failNextIdempotentCheckForTesting = false;

   SetDataBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.backgrounding = new Backgrounding();
      this.version = -1;
      this.compress = false;
   }

   public SetDataBuilderImpl(CuratorFrameworkImpl client, Backgrounding backgrounding, int version, boolean compress) {
      this.client = client;
      this.backgrounding = backgrounding;
      this.version = version;
      this.compress = compress;
   }

   TransactionSetDataBuilder asTransactionSetDataBuilder(final Object context, final CuratorMultiTransactionRecord transaction) {
      return new TransactionSetDataBuilder() {
         public Object forPath(String path, byte[] data) throws Exception {
            if (SetDataBuilderImpl.this.compress) {
               data = SetDataBuilderImpl.this.client.getCompressionProvider().compress(path, data);
            }

            String fixedPath = SetDataBuilderImpl.this.client.fixForNamespace(path);
            transaction.add(Op.setData(fixedPath, data, SetDataBuilderImpl.this.version), OperationType.SET_DATA, path);
            return context;
         }

         public Object forPath(String path) throws Exception {
            return this.forPath(path, SetDataBuilderImpl.this.client.getDefaultData());
         }

         public PathAndBytesable withVersion(int version) {
            SetDataBuilderImpl.this.withVersion(version);
            return this;
         }

         public VersionPathAndBytesable compressed() {
            SetDataBuilderImpl.this.compress = true;
            return this;
         }
      };
   }

   public SetDataBackgroundVersionable compressed() {
      this.compress = true;
      return new SetDataBackgroundVersionable() {
         public ErrorListenerPathAndBytesable inBackground() {
            return SetDataBuilderImpl.this.inBackground();
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
            return SetDataBuilderImpl.this.inBackground(callback, context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
            return SetDataBuilderImpl.this.inBackground(callback, context, executor);
         }

         public ErrorListenerPathAndBytesable inBackground(Object context) {
            return SetDataBuilderImpl.this.inBackground(context);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
            return SetDataBuilderImpl.this.inBackground(callback);
         }

         public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
            return SetDataBuilderImpl.this.inBackground(callback, executor);
         }

         public Stat forPath(String path, byte[] data) throws Exception {
            return SetDataBuilderImpl.this.forPath(path, data);
         }

         public Stat forPath(String path) throws Exception {
            return SetDataBuilderImpl.this.forPath(path);
         }

         public BackgroundPathAndBytesable withVersion(int version) {
            return SetDataBuilderImpl.this.withVersion(version);
         }
      };
   }

   public BackgroundPathAndBytesable withVersion(int version) {
      this.version = version;
      return this;
   }

   public SetDataBuilder idempotent() {
      this.idempotent = true;
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground() {
      this.backgrounding = new Backgrounding(true);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public ErrorListenerPathAndBytesable inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, executor);
      return this;
   }

   public PathAndBytesable withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   private void backgroundCheckIdempotent(final CuratorFrameworkImpl client, final OperationAndData mainOperationAndData, final String path, final Backgrounding backgrounding) {
      final AsyncCallback.DataCallback dataCallback = new AsyncCallback.DataCallback() {
         public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            if (rc == Code.OK.intValue()) {
               if (SetDataBuilderImpl.this.failNextIdempotentCheckForTesting) {
                  SetDataBuilderImpl.this.failNextIdempotentCheckForTesting = false;
                  rc = Code.CONNECTIONLOSS.intValue();
               } else if (!SetDataBuilderImpl.this.idempotentSetMatches(stat.getVersion(), ((PathAndBytes)mainOperationAndData.getData()).getData(), data)) {
                  rc = Code.BADVERSION.intValue();
               }
            }

            CuratorEvent event = new CuratorEventImpl(client, CuratorEventType.SET_DATA, rc, path, (String)null, ctx, stat, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
            client.processBackgroundOperation(mainOperationAndData, event);
         }
      };
      BackgroundOperation<PathAndBytes> operation = new BackgroundOperation() {
         public void performBackgroundOperation(OperationAndData op) throws Exception {
            client.getZooKeeper().getData(path, false, dataCallback, backgrounding.getContext());
         }

         public CuratorEventType getBackgroundEventType() {
            return CuratorEventType.SET_DATA;
         }
      };
      client.queueOperation(new OperationAndData(operation, mainOperationAndData));
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.SET_DATA;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("SetDataBuilderImpl-Background");
         final byte[] data = ((PathAndBytes)operationAndData.getData()).getData();
         this.client.getZooKeeper().setData(((PathAndBytes)operationAndData.getData()).getPath(), data, this.version, new AsyncCallback.StatCallback() {
            public void processResult(int rc, String path, Object ctx, Stat stat) {
               trace.setReturnCode(rc).setRequestBytesLength(data).setPath(path).setStat(stat).commit();
               if (rc == Code.OK.intValue() && SetDataBuilderImpl.this.failNextSetForTesting) {
                  SetDataBuilderImpl.this.failNextSetForTesting = false;
                  rc = Code.CONNECTIONLOSS.intValue();
               }

               if (rc == Code.BADVERSION.intValue() && SetDataBuilderImpl.this.idempotent) {
                  SetDataBuilderImpl.this.backgroundCheckIdempotent(SetDataBuilderImpl.this.client, operationAndData, ((PathAndBytes)operationAndData.getData()).getPath(), SetDataBuilderImpl.this.backgrounding);
               } else {
                  CuratorEvent event = new CuratorEventImpl(SetDataBuilderImpl.this.client, CuratorEventType.SET_DATA, rc, path, (String)null, ctx, stat, (byte[])null, (List)null, (WatchedEvent)null, (List)null, (List)null);
                  SetDataBuilderImpl.this.client.processBackgroundOperation(operationAndData, event);
               }

            }
         }, this.backgrounding.getContext());
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   public Stat forPath(String path) throws Exception {
      return this.forPath(path, this.client.getDefaultData());
   }

   public Stat forPath(String path, byte[] data) throws Exception {
      this.client.getSchemaSet().getSchema(path).validateGeneral(path, data, (List)null);
      if (this.compress) {
         data = this.client.getCompressionProvider().compress(path, data);
      }

      path = this.client.fixForNamespace(path);
      Stat resultStat = null;
      if (this.backgrounding.inBackground()) {
         OperationAndData<PathAndBytes> operationAndData = new OperationAndData(this, new PathAndBytes(path, data), this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), (Watching)null) {
            void callPerformBackgroundOperation() throws Exception {
               if (SetDataBuilderImpl.this.failBeforeNextSetForTesting) {
                  SetDataBuilderImpl.this.failBeforeNextSetForTesting = false;
                  throw new KeeperException.ConnectionLossException();
               } else {
                  super.callPerformBackgroundOperation();
               }
            }
         };
         this.client.processBackgroundOperation(operationAndData, (CuratorEvent)null);
      } else {
         resultStat = this.pathInForeground(path, data);
      }

      return resultStat;
   }

   int getVersion() {
      return this.version;
   }

   private boolean idempotentSetMatches(int getVersion, byte[] data, byte[] getData) {
      return (this.version == -1 || this.version + 1 == getVersion) && Arrays.equals(data, getData);
   }

   private Stat pathInForeground(final String path, final byte[] data) throws Exception {
      OperationTrace trace = this.client.getZookeeperClient().startAdvancedTracer("SetDataBuilderImpl-Foreground");
      Stat resultStat = (Stat)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public Stat call() throws Exception {
            if (SetDataBuilderImpl.this.failBeforeNextSetForTesting) {
               SetDataBuilderImpl.this.failBeforeNextSetForTesting = false;
               throw new KeeperException.ConnectionLossException();
            } else {
               Stat localResultStat = null;

               try {
                  localResultStat = SetDataBuilderImpl.this.client.getZooKeeper().setData(path, data, SetDataBuilderImpl.this.version);
               } catch (KeeperException.BadVersionException e) {
                  if (!SetDataBuilderImpl.this.idempotent) {
                     throw e;
                  }

                  Stat getStat = new Stat();
                  if (SetDataBuilderImpl.this.failNextIdempotentCheckForTesting) {
                     SetDataBuilderImpl.this.failNextIdempotentCheckForTesting = false;
                     throw new KeeperException.ConnectionLossException();
                  }

                  byte[] existingData = SetDataBuilderImpl.this.client.getZooKeeper().getData(path, false, getStat);
                  if (!SetDataBuilderImpl.this.idempotentSetMatches(getStat.getVersion(), data, existingData)) {
                     throw e;
                  }

                  localResultStat = getStat;
               }

               if (SetDataBuilderImpl.this.failNextSetForTesting) {
                  SetDataBuilderImpl.this.failNextSetForTesting = false;
                  throw new KeeperException.ConnectionLossException();
               } else {
                  return localResultStat;
               }
            }
         }
      });
      trace.setRequestBytesLength(data).setPath(path).setStat(resultStat).commit();
      return resultStat;
   }
}
