package org.datanucleus.store.rdbms.connectionpool;

import javax.sql.DataSource;

public interface ConnectionPool {
   void close();

   DataSource getDataSource();
}
