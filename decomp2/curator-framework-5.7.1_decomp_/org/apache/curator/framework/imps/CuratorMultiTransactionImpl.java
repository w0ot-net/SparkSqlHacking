package org.apache.curator.framework.imps;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.TimeTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.ErrorListenerMultiTransactionMain;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.api.transaction.CuratorMultiTransaction;
import org.apache.curator.framework.api.transaction.CuratorMultiTransactionMain;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.schema.Schema;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.OpResult;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.proto.DeleteRequest;
import org.apache.zookeeper.proto.SetDataRequest;

public class CuratorMultiTransactionImpl implements CuratorMultiTransaction, CuratorMultiTransactionMain, BackgroundOperation, ErrorListenerMultiTransactionMain {
   private final CuratorFrameworkImpl client;
   private Backgrounding backgrounding = new Backgrounding();

   public CuratorMultiTransactionImpl(CuratorFrameworkImpl client) {
      this.client = client;
   }

   public CuratorMultiTransactionImpl(CuratorFrameworkImpl client, Backgrounding backgrounding) {
      this.client = client;
      this.backgrounding = backgrounding;
   }

   public ErrorListenerMultiTransactionMain inBackground() {
      this.backgrounding = new Backgrounding(true);
      return this;
   }

   public ErrorListenerMultiTransactionMain inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public ErrorListenerMultiTransactionMain inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerMultiTransactionMain inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerMultiTransactionMain inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(callback, executor);
      return this;
   }

   public ErrorListenerMultiTransactionMain inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public CuratorMultiTransactionMain withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public List forOperations(CuratorOp... operations) throws Exception {
      List<CuratorOp> ops = (List<CuratorOp>)(operations != null ? Arrays.asList(operations) : Lists.newArrayList());
      return this.forOperations(ops);
   }

   public List forOperations(List operations) throws Exception {
      operations = (List)Preconditions.checkNotNull(operations, "operations cannot be null");
      Preconditions.checkArgument(!operations.isEmpty(), "operations list cannot be empty");
      CuratorMultiTransactionRecord record = new CuratorMultiTransactionRecord();

      for(CuratorOp curatorOp : operations) {
         Schema schema = this.client.getSchemaSet().getSchema(curatorOp.getTypeAndPath().getForPath());
         record.add(curatorOp.get(), curatorOp.getTypeAndPath().getType(), curatorOp.getTypeAndPath().getForPath());
         if (curatorOp.get().getType() != 1 && curatorOp.get().getType() != 19) {
            if (curatorOp.get().getType() != 2 && curatorOp.get().getType() != 20) {
               if (curatorOp.get().getType() == 5) {
                  SetDataRequest setDataRequest = (SetDataRequest)curatorOp.get().toRequestRecord();
                  schema.validateGeneral(setDataRequest.getPath(), setDataRequest.getData(), (List)null);
               }
            } else {
               DeleteRequest deleteRequest = (DeleteRequest)curatorOp.get().toRequestRecord();
               schema.validateDelete(deleteRequest.getPath());
            }
         } else {
            CreateRequest createRequest = (CreateRequest)curatorOp.get().toRequestRecord();
            CreateMode createMode = CreateMode.fromFlag(createRequest.getFlags(), CreateMode.PERSISTENT);
            schema.validateCreate(createMode, createRequest.getPath(), createRequest.getData(), createRequest.getAcl());
         }
      }

      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, record, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), (Watching)null), (CuratorEvent)null);
         return null;
      } else {
         return this.forOperationsInForeground(record);
      }
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.TRANSACTION;
   }

   public void performBackgroundOperation(final OperationAndData operationAndData) throws Exception {
      try {
         final TimeTrace trace = this.client.getZookeeperClient().startTracer("CuratorMultiTransactionImpl-Background");
         AsyncCallback.MultiCallback callback = new AsyncCallback.MultiCallback() {
            public void processResult(int rc, String path, Object ctx, List opResults) {
               trace.commit();
               List<CuratorTransactionResult> curatorResults = opResults != null ? CuratorTransactionImpl.wrapResults(CuratorMultiTransactionImpl.this.client, opResults, (CuratorMultiTransactionRecord)operationAndData.getData()) : null;
               CuratorEvent event = new CuratorEventImpl(CuratorMultiTransactionImpl.this.client, CuratorEventType.TRANSACTION, rc, path, (String)null, ctx, (Stat)null, (byte[])null, (List)null, (WatchedEvent)null, (List)null, curatorResults);
               CuratorMultiTransactionImpl.this.client.processBackgroundOperation(operationAndData, event);
            }
         };
         this.client.getZooKeeper().multi((Iterable)operationAndData.getData(), callback, this.backgrounding.getContext());
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   private List forOperationsInForeground(final CuratorMultiTransactionRecord record) throws Exception {
      TimeTrace trace = this.client.getZookeeperClient().startTracer("CuratorMultiTransactionImpl-Foreground");
      List<OpResult> responseData = (List)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public List call() throws Exception {
            return CuratorMultiTransactionImpl.this.client.getZooKeeper().multi(record);
         }
      });
      trace.commit();
      return CuratorTransactionImpl.wrapResults(this.client, responseData, record);
   }
}
