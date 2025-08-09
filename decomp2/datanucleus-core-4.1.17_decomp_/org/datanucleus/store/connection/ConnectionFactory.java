package org.datanucleus.store.connection;

import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.Transaction;

public interface ConnectionFactory {
   String DATANUCLEUS_CONNECTION_RESOURCE_TYPE = "datanucleus.connection.resourceType";
   String DATANUCLEUS_CONNECTION2_RESOURCE_TYPE = "datanucleus.connection2.resourceType";

   ManagedConnection getConnection(ExecutionContext var1, Transaction var2, Map var3);

   ManagedConnection createManagedConnection(ExecutionContext var1, Map var2);

   void close();

   String getResourceType();
}
