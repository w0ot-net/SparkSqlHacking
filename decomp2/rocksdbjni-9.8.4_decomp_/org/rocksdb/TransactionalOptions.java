package org.rocksdb;

interface TransactionalOptions extends AutoCloseable {
   boolean isSetSnapshot();

   TransactionalOptions setSetSnapshot(boolean var1);
}
