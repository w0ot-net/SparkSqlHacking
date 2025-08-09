package org.datanucleus.store.connection;

import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.Transaction;

public interface ConnectionManager {
   ConnectionFactory lookupConnectionFactory(String var1);

   void registerConnectionFactory(String var1, ConnectionFactory var2);

   void closeAllConnections(ConnectionFactory var1, ExecutionContext var2);

   ManagedConnection allocateConnection(ConnectionFactory var1, ExecutionContext var2, Transaction var3, Map var4);

   void disableConnectionPool();
}
