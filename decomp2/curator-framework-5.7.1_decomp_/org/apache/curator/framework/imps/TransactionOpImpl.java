package org.apache.curator.framework.imps;

import org.apache.curator.framework.api.transaction.TransactionCheckBuilder;
import org.apache.curator.framework.api.transaction.TransactionCreateBuilder;
import org.apache.curator.framework.api.transaction.TransactionDeleteBuilder;
import org.apache.curator.framework.api.transaction.TransactionOp;
import org.apache.curator.framework.api.transaction.TransactionSetDataBuilder;

public class TransactionOpImpl implements TransactionOp {
   private final CuratorFrameworkImpl client;

   public TransactionOpImpl(CuratorFrameworkImpl client) {
      this.client = client;
   }

   public TransactionCreateBuilder create() {
      ExtractingCuratorOp op = new ExtractingCuratorOp();
      return (new CreateBuilderImpl(this.client)).asTransactionCreateBuilder(op, op.getRecord());
   }

   public TransactionDeleteBuilder delete() {
      ExtractingCuratorOp op = new ExtractingCuratorOp();
      return (new DeleteBuilderImpl(this.client)).asTransactionDeleteBuilder(op, op.getRecord());
   }

   public TransactionSetDataBuilder setData() {
      ExtractingCuratorOp op = new ExtractingCuratorOp();
      return (new SetDataBuilderImpl(this.client)).asTransactionSetDataBuilder(op, op.getRecord());
   }

   public TransactionCheckBuilder check() {
      ExtractingCuratorOp op = new ExtractingCuratorOp();
      return CuratorTransactionImpl.makeTransactionCheckBuilder(this.client, op, op.getRecord());
   }
}
