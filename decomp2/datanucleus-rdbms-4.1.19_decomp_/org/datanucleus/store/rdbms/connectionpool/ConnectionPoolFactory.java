package org.datanucleus.store.rdbms.connectionpool;

import org.datanucleus.store.StoreManager;

public interface ConnectionPoolFactory {
   ConnectionPool createConnectionPool(StoreManager var1);
}
