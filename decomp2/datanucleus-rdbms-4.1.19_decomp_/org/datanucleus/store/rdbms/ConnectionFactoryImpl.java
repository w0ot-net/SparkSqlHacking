package org.datanucleus.store.rdbms;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.ConnectionFactoryNotFoundException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.exceptions.UnsupportedConnectionFactoryException;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.AbstractConnectionFactory;
import org.datanucleus.store.connection.AbstractEmulatedXAResource;
import org.datanucleus.store.connection.AbstractManagedConnection;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.connection.ManagedConnectionResourceListener;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.connectionpool.ConnectionPool;
import org.datanucleus.store.rdbms.connectionpool.ConnectionPoolFactory;
import org.datanucleus.transaction.TransactionUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ConnectionFactoryImpl extends AbstractConnectionFactory {
   DataSource[] dataSources;
   ConnectionPool pool = null;

   public ConnectionFactoryImpl(StoreManager storeMgr, String resourceName) {
      super(storeMgr, resourceName);
      if (resourceName.equals("tx")) {
         this.initialiseDataSources();
      }

   }

   public void close() {
      if (this.pool != null) {
         if (NucleusLogger.CONNECTION.isDebugEnabled()) {
            NucleusLogger.CONNECTION.debug(Localiser.msg("047010", new Object[]{this.getResourceName()}));
         }

         this.pool.close();
      }

      super.close();
   }

   protected synchronized void initialiseDataSources() {
      if (this.getResourceName().equals("tx")) {
         String requiredPoolingType = this.storeMgr.getStringProperty("datanucleus.connectionPoolingType");
         Object connDS = this.storeMgr.getConnectionFactory();
         String connJNDI = this.storeMgr.getConnectionFactoryName();
         String connURL = this.storeMgr.getConnectionURL();
         this.dataSources = this.generateDataSources(this.storeMgr, connDS, connJNDI, this.getResourceName(), requiredPoolingType, connURL);
         if (this.dataSources == null) {
            throw (new NucleusUserException(Localiser.msg("047009", new Object[]{"transactional"}))).setFatal();
         }
      } else {
         String requiredPoolingType = this.storeMgr.getStringProperty("datanucleus.connectionPoolingType.nontx");
         if (requiredPoolingType == null) {
            requiredPoolingType = this.storeMgr.getStringProperty("datanucleus.connectionPoolingType");
         }

         Object connDS = this.storeMgr.getConnectionFactory2();
         String connJNDI = this.storeMgr.getConnectionFactory2Name();
         String connURL = this.storeMgr.getConnectionURL();
         this.dataSources = this.generateDataSources(this.storeMgr, connDS, connJNDI, this.getResourceName(), requiredPoolingType, connURL);
         if (this.dataSources == null) {
            connDS = this.storeMgr.getConnectionFactory();
            connJNDI = this.storeMgr.getConnectionFactoryName();
            this.dataSources = this.generateDataSources(this.storeMgr, connDS, connJNDI, this.getResourceName(), requiredPoolingType, connURL);
         }

         if (this.dataSources == null) {
            throw (new NucleusUserException(Localiser.msg("047009", new Object[]{"non-transactional"}))).setFatal();
         }
      }

   }

   private DataSource[] generateDataSources(StoreManager storeMgr, Object connDS, String connJNDI, String resourceType, String requiredPoolingType, String connURL) {
      DataSource[] dataSources = null;
      if (connDS != null) {
         if (!(connDS instanceof DataSource) && !(connDS instanceof XADataSource)) {
            throw new UnsupportedConnectionFactoryException(connDS);
         }

         dataSources = new DataSource[]{(DataSource)connDS};
      } else if (connJNDI != null) {
         String[] connectionFactoryNames = StringUtils.split(connJNDI, ",");
         dataSources = new DataSource[connectionFactoryNames.length];

         for(int i = 0; i < connectionFactoryNames.length; ++i) {
            Object obj;
            try {
               obj = (new InitialContext()).lookup(connectionFactoryNames[i]);
            } catch (NamingException e) {
               throw new ConnectionFactoryNotFoundException(connectionFactoryNames[i], e);
            }

            if (!(obj instanceof DataSource) && !(obj instanceof XADataSource)) {
               throw new UnsupportedConnectionFactoryException(obj);
            }

            dataSources[i] = (DataSource)obj;
         }
      } else if (connURL != null) {
         dataSources = new DataSource[1];
         String poolingType = calculatePoolingType(storeMgr, requiredPoolingType);

         try {
            ConnectionPoolFactory connPoolFactory = (ConnectionPoolFactory)storeMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store.rdbms.connectionpool", "name", poolingType, "class-name", (Class[])null, (Object[])null);
            if (connPoolFactory == null) {
               throw (new NucleusUserException(Localiser.msg("047003", new Object[]{poolingType}))).setFatal();
            }

            this.pool = connPoolFactory.createConnectionPool(storeMgr);
            dataSources[0] = this.pool.getDataSource();
            if (NucleusLogger.CONNECTION.isDebugEnabled()) {
               NucleusLogger.CONNECTION.debug(Localiser.msg("047008", new Object[]{resourceType, poolingType}));
            }
         } catch (ClassNotFoundException cnfe) {
            throw (new NucleusUserException(Localiser.msg("047003", new Object[]{poolingType}), cnfe)).setFatal();
         } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
               InvocationTargetException ite = (InvocationTargetException)e;
               throw (new NucleusException(Localiser.msg("047004", new Object[]{poolingType, ite.getTargetException().getMessage()}), ite.getTargetException())).setFatal();
            }

            throw (new NucleusException(Localiser.msg("047004", new Object[]{poolingType, e.getMessage()}), e)).setFatal();
         }
      }

      return dataSources;
   }

   public ManagedConnection createManagedConnection(ExecutionContext ec, Map options) {
      if (this.dataSources == null) {
         this.initialiseDataSources();
      }

      ManagedConnection mconn = new ManagedConnectionImpl(ec, options);
      boolean singleConnection = this.storeMgr.getBooleanProperty("datanucleus.connection.singleConnectionPerExecutionContext");
      boolean releaseAfterUse = this.storeMgr.getBooleanProperty("datanucleus.connection.nontx.releaseAfterUse");
      if (ec != null && !ec.getTransaction().isActive() && (!releaseAfterUse || singleConnection)) {
         mconn.setCloseOnRelease(false);
      }

      return mconn;
   }

   protected static String calculatePoolingType(StoreManager storeMgr, String requiredPoolingType) {
      String poolingType = requiredPoolingType;
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      if (requiredPoolingType != null) {
         if (requiredPoolingType.equalsIgnoreCase("DBCP") && !dbcpPresent(clr)) {
            NucleusLogger.CONNECTION.warn("DBCP specified but not present in CLASSPATH (or one of dependencies)");
            poolingType = null;
         } else if (requiredPoolingType.equalsIgnoreCase("C3P0") && !c3p0Present(clr)) {
            NucleusLogger.CONNECTION.warn("C3P0 specified but not present in CLASSPATH (or one of dependencies)");
            poolingType = null;
         } else if (requiredPoolingType.equalsIgnoreCase("Proxool") && !proxoolPresent(clr)) {
            NucleusLogger.CONNECTION.warn("Proxool specified but not present in CLASSPATH (or one of dependencies)");
            poolingType = null;
         } else if (requiredPoolingType.equalsIgnoreCase("BoneCP") && !bonecpPresent(clr)) {
            NucleusLogger.CONNECTION.warn("BoneCP specified but not present in CLASSPATH (or one of dependencies)");
            poolingType = null;
         }
      }

      if (poolingType == null && dbcpPresent(clr)) {
         poolingType = "DBCP";
      }

      if (poolingType == null && c3p0Present(clr)) {
         poolingType = "C3P0";
      }

      if (poolingType == null && proxoolPresent(clr)) {
         poolingType = "Proxool";
      }

      if (poolingType == null && bonecpPresent(clr)) {
         poolingType = "BoneCP";
      }

      if (poolingType == null) {
         poolingType = "dbcp-builtin";
      }

      return poolingType;
   }

   protected static boolean dbcpPresent(ClassLoaderResolver clr) {
      try {
         clr.classForName("org.apache.commons.pool.ObjectPool");
         clr.classForName("org.apache.commons.dbcp.ConnectionFactory");
         return true;
      } catch (ClassNotResolvedException var2) {
         return false;
      }
   }

   protected static boolean c3p0Present(ClassLoaderResolver clr) {
      try {
         clr.classForName("com.mchange.v2.c3p0.ComboPooledDataSource");
         return true;
      } catch (ClassNotResolvedException var2) {
         return false;
      }
   }

   protected static boolean proxoolPresent(ClassLoaderResolver clr) {
      try {
         clr.classForName("org.logicalcobwebs.proxool.ProxoolDriver");
         clr.classForName("org.apache.commons.logging.Log");
         return true;
      } catch (ClassNotResolvedException var2) {
         return false;
      }
   }

   protected static boolean bonecpPresent(ClassLoaderResolver clr) {
      try {
         clr.classForName("com.jolbox.bonecp.BoneCPDataSource");
         clr.classForName("org.slf4j.Logger");
         clr.classForName("com.google.common.collect.Multiset");
         return true;
      } catch (ClassNotResolvedException var2) {
         return false;
      }
   }

   class ManagedConnectionImpl extends AbstractManagedConnection {
      ExecutionContext ec;
      XAResource xaRes = null;
      int isolation;
      boolean needsCommitting = false;
      ConnectionProvider connProvider = null;
      private Map savepoints = null;

      ManagedConnectionImpl(ExecutionContext ec, Map options) {
         if (options != null && options.get("transaction.isolation") != null) {
            this.isolation = ((Number)options.get("transaction.isolation")).intValue();
         } else {
            this.isolation = TransactionUtils.getTransactionIsolationLevelForName(ConnectionFactoryImpl.this.storeMgr.getStringProperty("datanucleus.transactionIsolation"));
         }

         try {
            this.connProvider = (ConnectionProvider)ConnectionFactoryImpl.this.storeMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store.rdbms.connectionprovider", "name", ConnectionFactoryImpl.this.storeMgr.getStringProperty("datanucleus.rdbms.connectionProviderName"), "class-name", (Class[])null, (Object[])null);
            if (this.connProvider == null) {
               throw (new NucleusException(Localiser.msg("050000", new Object[]{ConnectionFactoryImpl.this.storeMgr.getStringProperty("datanucleus.rdbms.connectionProviderName")}))).setFatal();
            } else {
               this.connProvider.setFailOnError(ConnectionFactoryImpl.this.storeMgr.getBooleanProperty("datanucleus.rdbms.connectionProviderFailOnError"));
            }
         } catch (Exception e) {
            throw (new NucleusException(Localiser.msg("050001", new Object[]{ConnectionFactoryImpl.this.storeMgr.getStringProperty("datanucleus.rdbms.connectionProviderName"), e.getMessage()}), e)).setFatal();
         }
      }

      public void release() {
         if (this.commitOnRelease) {
            try {
               DatastoreAdapter dba = ((RDBMSStoreManager)ConnectionFactoryImpl.this.storeMgr).getDatastoreAdapter();
               if (!dba.supportsOption("HoldCursorsOverCommit")) {
                  for(int i = 0; i < this.listeners.size(); ++i) {
                     ((ManagedConnectionResourceListener)this.listeners.get(i)).managedConnectionPreClose();
                  }
               }

               Connection conn = this.getSqlConnection();
               if (conn != null && !conn.isClosed() && !conn.getAutoCommit()) {
                  ((RDBMSStoreManager)ConnectionFactoryImpl.this.storeMgr).getSQLController().processConnectionStatement(this);
                  this.needsCommitting = false;
                  if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                     NucleusLogger.CONNECTION.debug(Localiser.msg("009015", new Object[]{this.toString()}));
                  }

                  conn.commit();
               }
            } catch (SQLException sqle) {
               throw new NucleusDataStoreException(sqle.getMessage(), sqle);
            }
         }

         super.release();
      }

      public XAResource getXAResource() {
         if (this.xaRes != null) {
            return this.xaRes;
         } else {
            if (this.getConnection() instanceof Connection) {
               this.xaRes = new EmulatedXAResource(this);
            } else {
               try {
                  this.xaRes = ((XAConnection)this.getConnection()).getXAResource();
               } catch (SQLException e) {
                  throw new NucleusDataStoreException(e.getMessage(), e);
               }
            }

            return this.xaRes;
         }
      }

      public Object getConnection() {
         if (this.conn == null) {
            Connection cnx = null;

            try {
               RDBMSStoreManager rdbmsMgr = (RDBMSStoreManager)ConnectionFactoryImpl.this.storeMgr;
               boolean readOnly = this.ec != null ? this.ec.getBooleanProperty("datanucleus.readOnlyDatastore") : ConnectionFactoryImpl.this.storeMgr.getBooleanProperty("datanucleus.readOnlyDatastore");
               if (rdbmsMgr.getDatastoreAdapter() != null) {
                  DatastoreAdapter rdba = rdbmsMgr.getDatastoreAdapter();
                  int reqdIsolationLevel = this.isolation;
                  if (rdba.getRequiredTransactionIsolationLevel() >= 0) {
                     reqdIsolationLevel = rdba.getRequiredTransactionIsolationLevel();
                  }

                  cnx = this.connProvider.getConnection(ConnectionFactoryImpl.this.dataSources);
                  boolean succeeded = false;

                  try {
                     if (cnx.isReadOnly() != readOnly) {
                        NucleusLogger.CONNECTION.debug("Setting readonly=" + readOnly + " to connection: " + cnx.toString());
                        cnx.setReadOnly(readOnly);
                     }

                     if (reqdIsolationLevel == 0) {
                        if (!cnx.getAutoCommit()) {
                           cnx.setAutoCommit(true);
                        }
                     } else {
                        if (cnx.getAutoCommit()) {
                           cnx.setAutoCommit(false);
                        }

                        if (rdba.supportsTransactionIsolation(reqdIsolationLevel)) {
                           int currentIsolationLevel = cnx.getTransactionIsolation();
                           if (currentIsolationLevel != reqdIsolationLevel) {
                              cnx.setTransactionIsolation(reqdIsolationLevel);
                           }
                        } else {
                           NucleusLogger.CONNECTION.warn(Localiser.msg("051008", (long)reqdIsolationLevel));
                        }
                     }

                     if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                        NucleusLogger.CONNECTION.debug(Localiser.msg("009012", new Object[]{this.toString(), TransactionUtils.getNameForTransactionIsolationLevel(reqdIsolationLevel), cnx.getAutoCommit()}));
                     }

                     if (reqdIsolationLevel != this.isolation && this.isolation == 0 && !cnx.getAutoCommit()) {
                        NucleusLogger.CONNECTION.debug("Setting autocommit=true for connection: " + StringUtils.toJVMIDString(cnx));
                        cnx.setAutoCommit(true);
                     }

                     succeeded = true;
                  } catch (SQLException e) {
                     throw new NucleusDataStoreException(e.getMessage(), e);
                  } finally {
                     if (!succeeded) {
                        try {
                           cnx.close();
                        } catch (SQLException var15) {
                        }

                        if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                           NucleusLogger.CONNECTION.debug(Localiser.msg("009013", new Object[]{this.toString()}));
                        }
                     }

                  }
               } else {
                  cnx = ConnectionFactoryImpl.this.dataSources[0].getConnection();
                  if (cnx == null) {
                     String msg = Localiser.msg("009010", new Object[]{ConnectionFactoryImpl.this.dataSources[0]});
                     NucleusLogger.CONNECTION.error(msg);
                     throw new NucleusDataStoreException(msg);
                  }

                  if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                     NucleusLogger.CONNECTION.debug(Localiser.msg("009011", new Object[]{this.toString()}));
                  }
               }
            } catch (SQLException e) {
               throw new NucleusDataStoreException(e.getMessage(), e);
            }

            this.conn = cnx;
         }

         this.needsCommitting = true;
         return this.conn;
      }

      public void close() {
         for(int i = 0; i < this.listeners.size(); ++i) {
            ((ManagedConnectionResourceListener)this.listeners.get(i)).managedConnectionPreClose();
         }

         Connection conn = this.getSqlConnection();
         if (conn != null) {
            try {
               if (this.commitOnRelease && this.needsCommitting && !conn.isClosed() && !conn.getAutoCommit()) {
                  SQLController sqlController = ((RDBMSStoreManager)ConnectionFactoryImpl.this.storeMgr).getSQLController();
                  if (sqlController != null) {
                     sqlController.processConnectionStatement(this);
                  }

                  conn.commit();
                  this.needsCommitting = false;
                  if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                     NucleusLogger.CONNECTION.debug(Localiser.msg("009015", new Object[]{this.toString()}));
                  }
               }
            } catch (SQLException sqle) {
               throw new NucleusDataStoreException(sqle.getMessage(), sqle);
            } finally {
               try {
                  if (!conn.isClosed()) {
                     if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                        NucleusLogger.CONNECTION.debug(Localiser.msg("009013", new Object[]{this.toString()}));
                     }

                     conn.close();
                  } else if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                     NucleusLogger.CONNECTION.debug(Localiser.msg("009014", new Object[]{this.toString()}));
                  }
               } catch (SQLException sqle) {
                  throw new NucleusDataStoreException(sqle.getMessage(), sqle);
               }

            }
         }

         try {
            for(int i = 0; i < this.listeners.size(); ++i) {
               ((ManagedConnectionResourceListener)this.listeners.get(i)).managedConnectionPostClose();
            }
         } finally {
            this.listeners.clear();
         }

         if (this.savepoints != null) {
            this.savepoints.clear();
            this.savepoints = null;
         }

         this.conn = null;
         this.xaRes = null;
      }

      private Connection getSqlConnection() {
         if (this.conn != null && this.conn instanceof Connection) {
            return (Connection)this.conn;
         } else if (this.conn != null && this.conn instanceof XAConnection) {
            try {
               return ((XAConnection)this.conn).getConnection();
            } catch (SQLException e) {
               throw new NucleusDataStoreException(e.getMessage(), e);
            }
         } else {
            return null;
         }
      }

      public void setSavepoint(String name) {
         try {
            Savepoint sp = ((Connection)this.conn).setSavepoint(name);
            if (this.savepoints == null) {
               this.savepoints = new HashMap();
            }

            this.savepoints.put(name, sp);
         } catch (SQLException sqle) {
            throw new NucleusDataStoreException("Exception setting savepoint " + name, sqle);
         }
      }

      public void releaseSavepoint(String name) {
         try {
            if (this.savepoints != null) {
               Savepoint sp = (Savepoint)this.savepoints.remove(name);
               if (sp == null) {
                  throw new IllegalStateException("No savepoint with name " + name);
               } else {
                  ((Connection)this.conn).releaseSavepoint(sp);
               }
            }
         } catch (SQLException sqle) {
            throw new NucleusDataStoreException("Exception releasing savepoint " + name, sqle);
         }
      }

      public void rollbackToSavepoint(String name) {
         try {
            if (this.savepoints != null) {
               Savepoint sp = (Savepoint)this.savepoints.get(name);
               if (sp == null) {
                  throw new IllegalStateException("No savepoint with name " + name);
               } else {
                  ((Connection)this.conn).rollback(sp);
               }
            }
         } catch (SQLException sqle) {
            throw new NucleusDataStoreException("Exception rolling back to savepoint " + name, sqle);
         }
      }

      public boolean closeAfterTransactionEnd() {
         return ConnectionFactoryImpl.this.storeMgr.getBooleanProperty("datanucleus.connection.singleConnectionPerExecutionContext") ? false : super.closeAfterTransactionEnd();
      }
   }

   static class EmulatedXAResource extends AbstractEmulatedXAResource {
      Connection conn;

      EmulatedXAResource(ManagedConnection mconn) {
         super(mconn);
         this.conn = (Connection)mconn.getConnection();
      }

      public void commit(Xid xid, boolean onePhase) throws XAException {
         super.commit(xid, onePhase);

         try {
            this.conn.commit();
            ((ManagedConnectionImpl)this.mconn).xaRes = null;
         } catch (SQLException e) {
            NucleusLogger.CONNECTION.debug(Localiser.msg("009020", new Object[]{this.mconn.toString(), xid.toString(), onePhase}));
            XAException xe = new XAException(StringUtils.getStringFromStackTrace(e));
            xe.initCause(e);
            throw xe;
         }
      }

      public void rollback(Xid xid) throws XAException {
         super.rollback(xid);

         try {
            this.conn.rollback();
            ((ManagedConnectionImpl)this.mconn).xaRes = null;
         } catch (SQLException e) {
            NucleusLogger.CONNECTION.debug(Localiser.msg("009022", new Object[]{this.mconn.toString(), xid.toString()}));
            XAException xe = new XAException(StringUtils.getStringFromStackTrace(e));
            xe.initCause(e);
            throw xe;
         }
      }

      public void end(Xid xid, int flags) throws XAException {
         super.end(xid, flags);
         ((ManagedConnectionImpl)this.mconn).xaRes = null;
      }
   }
}
