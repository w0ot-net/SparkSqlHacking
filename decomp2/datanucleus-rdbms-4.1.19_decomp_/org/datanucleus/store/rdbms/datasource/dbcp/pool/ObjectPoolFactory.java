package org.datanucleus.store.rdbms.datasource.dbcp.pool;

public interface ObjectPoolFactory {
   ObjectPool createPool() throws IllegalStateException;
}
