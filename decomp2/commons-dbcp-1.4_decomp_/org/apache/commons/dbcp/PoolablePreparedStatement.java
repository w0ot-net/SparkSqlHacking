package org.apache.commons.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.pool.KeyedObjectPool;

public class PoolablePreparedStatement extends DelegatingPreparedStatement implements PreparedStatement {
   protected KeyedObjectPool _pool = null;
   protected Object _key = null;
   private volatile boolean batchAdded = false;

   public PoolablePreparedStatement(PreparedStatement stmt, Object key, KeyedObjectPool pool, Connection conn) {
      super((DelegatingConnection)conn, stmt);
      this._pool = pool;
      this._key = key;
      if (this._conn != null) {
         this._conn.removeTrace(this);
      }

   }

   public void addBatch() throws SQLException {
      super.addBatch();
      this.batchAdded = true;
   }

   public void clearBatch() throws SQLException {
      this.batchAdded = false;
      super.clearBatch();
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
            throw new SQLNestedException("Cannot close preparedstatement (return to pool failed)", e);
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

      if (this.batchAdded) {
         this.clearBatch();
      }

      super.passivate();
   }
}
