package org.apache.curator.framework.api.transaction;

/** @deprecated */
public interface CuratorTransaction {
   TransactionCreateBuilder create();

   TransactionDeleteBuilder delete();

   TransactionSetDataBuilder setData();

   TransactionCheckBuilder check();
}
