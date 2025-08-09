package org.datanucleus.store.rdbms.datasource.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.ObjectPool;

public class PoolableConnection extends DelegatingConnection {
   protected ObjectPool _pool = null;

   public PoolableConnection(Connection conn, ObjectPool pool) {
      super(conn);
      this._pool = pool;
   }

   public PoolableConnection(Connection conn, ObjectPool pool, AbandonedConfig config) {
      super(conn, config);
      this._pool = pool;
   }

   public synchronized void close() throws SQLException {
      if (!this._closed) {
         boolean isUnderlyingConectionClosed;
         try {
            isUnderlyingConectionClosed = this._conn.isClosed();
         } catch (SQLException e) {
            try {
               this._pool.invalidateObject(this);
            } catch (IllegalStateException var4) {
               this.passivate();
               this.getInnermostDelegate().close();
            } catch (Exception var5) {
            }

            throw (SQLException)(new SQLException("Cannot close connection (isClosed check failed)")).initCause(e);
         }

         if (!isUnderlyingConectionClosed) {
            try {
               this._pool.returnObject(this);
            } catch (IllegalStateException var6) {
               this.passivate();
               this.getInnermostDelegate().close();
            } catch (SQLException e) {
               throw e;
            } catch (RuntimeException e) {
               throw e;
            } catch (Exception e) {
               throw (SQLException)(new SQLException("Cannot close connection (return to pool failed)")).initCause(e);
            }

         } else {
            try {
               this._pool.invalidateObject(this);
            } catch (IllegalStateException var10) {
               this.passivate();
               this.getInnermostDelegate().close();
            } catch (Exception var11) {
            }

            throw new SQLException("Already closed.");
         }
      }
   }

   public void reallyClose() throws SQLException {
      super.close();
   }
}
