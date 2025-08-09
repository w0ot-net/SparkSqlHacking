package org.datanucleus.store.rdbms.datasource.dbcp.cpdsadapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.datanucleus.store.rdbms.datasource.dbcp.PoolablePreparedStatement;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPool;

class PoolablePreparedStatementStub extends PoolablePreparedStatement {
   public PoolablePreparedStatementStub(PreparedStatement stmt, Object key, KeyedObjectPool pool, Connection conn) {
      super(stmt, key, pool, conn);
   }

   protected void activate() throws SQLException {
      super.activate();
   }

   protected void passivate() throws SQLException {
      super.passivate();
   }
}
