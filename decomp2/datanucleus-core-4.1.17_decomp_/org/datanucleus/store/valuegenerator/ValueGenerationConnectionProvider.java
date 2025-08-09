package org.datanucleus.store.valuegenerator;

import org.datanucleus.store.connection.ManagedConnection;

public interface ValueGenerationConnectionProvider {
   ManagedConnection retrieveConnection();

   void releaseConnection();
}
