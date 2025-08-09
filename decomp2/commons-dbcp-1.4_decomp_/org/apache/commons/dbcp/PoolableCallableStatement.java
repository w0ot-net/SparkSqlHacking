package org.apache.commons.dbcp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.pool.KeyedObjectPool;

public class PoolableCallableStatement extends DelegatingCallableStatement implements CallableStatement {
   private final KeyedObjectPool _pool;
   private final Object _key;

   public PoolableCallableStatement(CallableStatement stmt, Object key, KeyedObjectPool pool, Connection conn) {
      super((DelegatingConnection)conn, stmt);
      this._pool = pool;
      this._key = key;
      if (this._conn != null) {
         this._conn.removeTrace(this);
      }

   }

   public void close() throws SQLException {
      if (!this.isClosed()) {
         try {
            this._pool.returnObject(this._key, this);
         } catch (SQLException e) {
            throw e;
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLNestedException("Cannot close CallableStatement (return to pool failed)", e);
         }
      }

   }

   protected void activate() throws SQLException {
      this._closed = false;
      if (this._conn != null) {
         this._conn.addTrace(this);
      }

      super.activate();
   }

   protected void passivate() throws SQLException {
      this._closed = true;
      if (this._conn != null) {
         this._conn.removeTrace(this);
      }

      List resultSets = this.getTrace();
      if (resultSets != null) {
         ResultSet[] set = (ResultSet[])resultSets.toArray(new ResultSet[resultSets.size()]);

         for(int i = 0; i < set.length; ++i) {
            set[i].close();
         }

         this.clearTrace();
      }

      super.passivate();
   }
}
