package org.datanucleus.store.rdbms.datasource.dbcp.managed;

public interface TransactionContextListener {
   void afterCompletion(TransactionContext var1, boolean var2);
}
