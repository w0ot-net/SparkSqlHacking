package org.rocksdb;

interface TransactionalDB extends AutoCloseable {
   Transaction beginTransaction(WriteOptions var1);

   Transaction beginTransaction(WriteOptions var1, TransactionalOptions var2);

   Transaction beginTransaction(WriteOptions var1, Transaction var2);

   Transaction beginTransaction(WriteOptions var1, TransactionalOptions var2, Transaction var3);
}
