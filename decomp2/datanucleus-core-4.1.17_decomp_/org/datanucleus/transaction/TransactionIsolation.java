package org.datanucleus.transaction;

public interface TransactionIsolation {
   int NONE = 0;
   int READ_UNCOMMITTED = 1;
   int READ_COMMITTED = 2;
   int REPEATABLE_READ = 4;
   int SERIALIZABLE = 8;
}
