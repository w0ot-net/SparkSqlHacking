package org.datanucleus.store.rdbms.datasource.dbcp.datasources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPool;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedPoolableObjectFactory;

class KeyedCPDSConnectionFactory implements KeyedPoolableObjectFactory, ConnectionEventListener, PooledConnectionManager {
   private static final String NO_KEY_MESSAGE = "close() was called on a Connection, but I have no record of the underlying PooledConnection.";
   private final ConnectionPoolDataSource _cpds;
   private final String _validationQuery;
   private final boolean _rollbackAfterValidation;
   private final KeyedObjectPool _pool;
   private final Map validatingMap;
   private final WeakHashMap pcMap;

   public KeyedCPDSConnectionFactory(ConnectionPoolDataSource cpds, KeyedObjectPool pool, String validationQuery) {
      this(cpds, pool, validationQuery, false);
   }

   public KeyedCPDSConnectionFactory(ConnectionPoolDataSource cpds, KeyedObjectPool pool, String validationQuery, boolean rollbackAfterValidation) {
      this.validatingMap = new HashMap();
      this.pcMap = new WeakHashMap();
      this._cpds = cpds;
      this._pool = pool;
      pool.setFactory(this);
      this._validationQuery = validationQuery;
      this._rollbackAfterValidation = rollbackAfterValidation;
   }

   public KeyedObjectPool getPool() {
      return this._pool;
   }

   public synchronized Object makeObject(Object key) throws Exception {
      Object obj = null;
      UserPassKey upkey = (UserPassKey)key;
      PooledConnection pc = null;
      String username = upkey.getUsername();
      String password = upkey.getPassword();
      if (username == null) {
         pc = this._cpds.getPooledConnection();
      } else {
         pc = this._cpds.getPooledConnection(username, password);
      }

      if (pc == null) {
         throw new IllegalStateException("Connection pool data source returned null from getPooledConnection");
      } else {
         pc.addConnectionEventListener(this);
         Object var7 = new PooledConnectionAndInfo(pc, username, password);
         this.pcMap.put(pc, var7);
         return var7;
      }
   }

   public void destroyObject(Object key, Object obj) throws Exception {
      if (obj instanceof PooledConnectionAndInfo) {
         PooledConnection pc = ((PooledConnectionAndInfo)obj).getPooledConnection();
         pc.removeConnectionEventListener(this);
         this.pcMap.remove(pc);
         pc.close();
      }

   }

   public boolean validateObject(Object key, Object obj) {
      boolean valid = false;
      if (obj instanceof PooledConnectionAndInfo) {
         PooledConnection pconn = ((PooledConnectionAndInfo)obj).getPooledConnection();
         String query = this._validationQuery;
         if (null != query) {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rset = null;
            this.validatingMap.put(pconn, (Object)null);

            try {
               conn = pconn.getConnection();
               stmt = conn.createStatement();
               rset = stmt.executeQuery(query);
               if (rset.next()) {
                  valid = true;
               } else {
                  valid = false;
               }

               if (this._rollbackAfterValidation) {
                  conn.rollback();
               }
            } catch (Exception var26) {
               valid = false;
            } finally {
               if (rset != null) {
                  try {
                     rset.close();
                  } catch (Throwable var25) {
                  }
               }

               if (stmt != null) {
                  try {
                     stmt.close();
                  } catch (Throwable var24) {
                  }
               }

               if (conn != null) {
                  try {
                     conn.close();
                  } catch (Throwable var23) {
                  }
               }

               this.validatingMap.remove(pconn);
            }
         } else {
            valid = true;
         }
      } else {
         valid = false;
      }

      return valid;
   }

   public void passivateObject(Object key, Object obj) {
   }

   public void activateObject(Object key, Object obj) {
   }

   public void connectionClosed(ConnectionEvent event) {
      PooledConnection pc = (PooledConnection)event.getSource();
      if (!this.validatingMap.containsKey(pc)) {
         PooledConnectionAndInfo info = (PooledConnectionAndInfo)this.pcMap.get(pc);
         if (info == null) {
            throw new IllegalStateException("close() was called on a Connection, but I have no record of the underlying PooledConnection.");
         }

         try {
            this._pool.returnObject(info.getUserPassKey(), info);
         } catch (Exception var7) {
            System.err.println("CLOSING DOWN CONNECTION AS IT COULD NOT BE RETURNED TO THE POOL");
            pc.removeConnectionEventListener(this);

            try {
               this._pool.invalidateObject(info.getUserPassKey(), info);
            } catch (Exception e3) {
               System.err.println("EXCEPTION WHILE DESTROYING OBJECT " + info);
               e3.printStackTrace();
            }
         }
      }

   }

   public void connectionErrorOccurred(ConnectionEvent event) {
      PooledConnection pc = (PooledConnection)event.getSource();
      if (null != event.getSQLException()) {
         System.err.println("CLOSING DOWN CONNECTION DUE TO INTERNAL ERROR (" + event.getSQLException() + ")");
      }

      pc.removeConnectionEventListener(this);
      PooledConnectionAndInfo info = (PooledConnectionAndInfo)this.pcMap.get(pc);
      if (info == null) {
         throw new IllegalStateException("close() was called on a Connection, but I have no record of the underlying PooledConnection.");
      } else {
         try {
            this._pool.invalidateObject(info.getUserPassKey(), info);
         } catch (Exception e) {
            System.err.println("EXCEPTION WHILE DESTROYING OBJECT " + info);
            e.printStackTrace();
         }

      }
   }

   public void invalidate(PooledConnection pc) throws SQLException {
      PooledConnectionAndInfo info = (PooledConnectionAndInfo)this.pcMap.get(pc);
      if (info == null) {
         throw new IllegalStateException("close() was called on a Connection, but I have no record of the underlying PooledConnection.");
      } else {
         UserPassKey key = info.getUserPassKey();

         try {
            this._pool.invalidateObject(key, info);
            this._pool.clear(key);
         } catch (Exception ex) {
            throw (SQLException)(new SQLException("Error invalidating connection")).initCause(ex);
         }
      }
   }

   public void setPassword(String password) {
   }

   public void closePool(String username) throws SQLException {
      try {
         this._pool.clear(new UserPassKey(username, (String)null));
      } catch (Exception ex) {
         throw (SQLException)(new SQLException("Error closing connection pool")).initCause(ex);
      }
   }
}
