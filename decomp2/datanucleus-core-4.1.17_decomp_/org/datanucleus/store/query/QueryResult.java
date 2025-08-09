package org.datanucleus.store.query;

import java.util.Collection;

public interface QueryResult extends Collection {
   void close();

   void disconnect();
}
