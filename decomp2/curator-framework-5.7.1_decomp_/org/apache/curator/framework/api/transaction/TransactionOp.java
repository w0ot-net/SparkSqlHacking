package org.apache.curator.framework.api.transaction;

public interface TransactionOp {
   TransactionCreateBuilder create();

   TransactionDeleteBuilder delete();

   TransactionSetDataBuilder setData();

   TransactionCheckBuilder check();
}
