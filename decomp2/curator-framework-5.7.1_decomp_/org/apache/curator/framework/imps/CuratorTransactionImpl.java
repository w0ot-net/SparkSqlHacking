package org.apache.curator.framework.imps;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.curator.RetryLoop;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionBridge;
import org.apache.curator.framework.api.transaction.CuratorTransactionFinal;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.api.transaction.OperationType;
import org.apache.curator.framework.api.transaction.TransactionCheckBuilder;
import org.apache.curator.framework.api.transaction.TransactionCreateBuilder;
import org.apache.curator.framework.api.transaction.TransactionDeleteBuilder;
import org.apache.curator.framework.api.transaction.TransactionSetDataBuilder;
import org.apache.curator.framework.api.transaction.TypeAndPath;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.OpResult;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

class CuratorTransactionImpl implements CuratorTransaction, CuratorTransactionBridge, CuratorTransactionFinal {
   private final CuratorFrameworkImpl client;
   private final CuratorMultiTransactionRecord transaction;
   private boolean isCommitted = false;

   CuratorTransactionImpl(CuratorFrameworkImpl client) {
      this.client = client;
      this.transaction = new CuratorMultiTransactionRecord();
   }

   public CuratorTransactionFinal and() {
      return this;
   }

   public TransactionCreateBuilder create() {
      Preconditions.checkState(!this.isCommitted, "transaction already committed");
      return (new CreateBuilderImpl(this.client)).asTransactionCreateBuilder(this, this.transaction);
   }

   public TransactionDeleteBuilder delete() {
      Preconditions.checkState(!this.isCommitted, "transaction already committed");
      return (new DeleteBuilderImpl(this.client)).asTransactionDeleteBuilder(this, this.transaction);
   }

   public TransactionSetDataBuilder setData() {
      Preconditions.checkState(!this.isCommitted, "transaction already committed");
      return (new SetDataBuilderImpl(this.client)).asTransactionSetDataBuilder(this, this.transaction);
   }

   public TransactionCheckBuilder check() {
      Preconditions.checkState(!this.isCommitted, "transaction already committed");
      return makeTransactionCheckBuilder(this.client, this, this.transaction);
   }

   static TransactionCheckBuilder makeTransactionCheckBuilder(final CuratorFrameworkImpl client, final Object context, final CuratorMultiTransactionRecord transaction) {
      return new TransactionCheckBuilder() {
         private int version = -1;

         public Object forPath(String path) throws Exception {
            String fixedPath = client.fixForNamespace(path);
            transaction.add(Op.check(fixedPath, this.version), OperationType.CHECK, path);
            return context;
         }

         public Pathable withVersion(int version) {
            this.version = version;
            return this;
         }
      };
   }

   public Collection commit() throws Exception {
      Preconditions.checkState(!this.isCommitted, "transaction already committed");
      this.isCommitted = true;
      List<OpResult> resultList = (List)RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public List call() throws Exception {
            return CuratorTransactionImpl.this.doOperation();
         }
      });
      if (resultList.size() != this.transaction.metadataSize()) {
         throw new IllegalStateException(String.format("Result size (%d) doesn't match input size (%d)", resultList.size(), this.transaction.metadataSize()));
      } else {
         return wrapResults(this.client, resultList, this.transaction);
      }
   }

   static List wrapResults(CuratorFrameworkImpl client, List resultList, CuratorMultiTransactionRecord transaction) {
      ImmutableList.Builder<CuratorTransactionResult> builder = ImmutableList.builder();

      for(int i = 0; i < resultList.size(); ++i) {
         OpResult opResult = (OpResult)resultList.get(i);
         TypeAndPath metadata = transaction.getMetadata(i);
         CuratorTransactionResult curatorResult = makeCuratorResult(client, opResult, metadata);
         builder.add(curatorResult);
      }

      return builder.build();
   }

   static CuratorTransactionResult makeCuratorResult(CuratorFrameworkImpl client, OpResult opResult, TypeAndPath metadata) {
      String resultPath = null;
      Stat resultStat = null;
      int error = 0;
      switch (opResult.getType()) {
         case -1:
            OpResult.ErrorResult errorResult = (OpResult.ErrorResult)opResult;
            error = errorResult.getErr();
            break;
         case 1:
            OpResult.CreateResult createResult = (OpResult.CreateResult)opResult;
            resultPath = client.unfixForNamespace(createResult.getPath());
            break;
         case 5:
            OpResult.SetDataResult setDataResult = (OpResult.SetDataResult)opResult;
            resultStat = setDataResult.getStat();
      }

      return new CuratorTransactionResult(metadata.getType(), metadata.getForPath(), resultPath, resultStat, error);
   }

   private List doOperation() throws Exception {
      List<OpResult> opResults = this.client.getZooKeeper().multi(this.transaction);
      if (opResults.size() > 0) {
         OpResult firstResult = (OpResult)opResults.get(0);
         if (firstResult.getType() == -1) {
            OpResult.ErrorResult error = (OpResult.ErrorResult)firstResult;
            KeeperException.Code code = Code.get(error.getErr());
            if (code == null) {
               code = Code.UNIMPLEMENTED;
            }

            throw KeeperException.create(code);
         }
      }

      return opResults;
   }
}
