package org.datanucleus.store.rdbms.datasource.dbcp.pool;

public interface KeyedObjectPoolFactory {
   KeyedObjectPool createPool() throws IllegalStateException;
}
