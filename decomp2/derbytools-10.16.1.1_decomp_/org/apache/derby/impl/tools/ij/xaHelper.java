package org.apache.derby.impl.tools.ij;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Locale;
import java.util.Vector;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;

class xaHelper implements xaAbstractHelper {
   private XADataSource currentXADataSource;
   private XAConnection currentXAConnection;
   private String databaseName;
   private DataSource currentDataSource;
   private ConnectionPoolDataSource currentCPDataSource;
   private PooledConnection currentPooledConnection;
   private boolean isJCC;
   private boolean isNetClient;
   private String framework;
   private static final Class[] STRING_P = new Class[]{"".getClass()};
   private static final Class[] INT_P;
   private static final Class[] BOOLEAN_P;

   public xaHelper() {
   }

   public void setFramework(String var1) {
      if (var1 != null) {
         this.framework = var1.toUpperCase(Locale.ENGLISH);
         if (!this.framework.endsWith("NET") && !this.framework.equals("DB2JCC")) {
            if (this.framework.equals("DERBYNETCLIENT")) {
               this.isNetClient = true;
            }
         } else {
            this.isJCC = true;
         }

      }
   }

   private Xid makeXid(int var1) {
      try {
         return new ijXid(var1, this.databaseName.getBytes("UTF-8"));
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public void XADataSourceStatement(ij var1, Token var2, Token var3, String var4) throws SQLException {
      try {
         this.currentXADataSource = this.getXADataSource();
         this.databaseName = var1.stringValue(var2.image);
         if (this.isJCC || this.isNetClient) {
            String var5 = System.getProperty("hostName");
            if (var5 != null && !var5.equals("localhost")) {
               setDataSourceProperty(this.currentXADataSource, "ServerName", var5);
            } else {
               setDataSourceProperty(this.currentXADataSource, "ServerName", "localhost");
            }

            setDataSourceProperty(this.currentXADataSource, "portNumber", 1527);
            String var6 = "APP";
            String var7 = "APP";
            setDataSourceProperty(this.currentXADataSource, "user", var6);
            setDataSourceProperty(this.currentXADataSource, "password", var7);
         }

         if (this.isJCC) {
            setDataSourceProperty(this.currentXADataSource, "driverType", 4);
            setDataSourceProperty(this.currentXADataSource, "retrieveMessagesFromServerOnGetMessage", true);
         }

         setDataSourceProperty(this.currentXADataSource, "databaseName", this.databaseName);
         if (var3 != null && var3.toString().toLowerCase(Locale.ENGLISH).equals("shutdown")) {
            if (!this.isJCC && !this.isNetClient) {
               setDataSourceProperty(this.currentXADataSource, "shutdownDatabase", "shutdown");
            } else {
               setDataSourceProperty(this.currentXADataSource, "databaseName", this.databaseName + ";shutdown=true");
            }

            this.currentXADataSource.getXAConnection().getConnection();
            this.currentXADataSource = null;
            this.currentXAConnection = null;
         } else if (var4 != null && var4.toLowerCase(Locale.ENGLISH).equals("create")) {
            if (!this.isJCC && !this.isNetClient) {
               setDataSourceProperty(this.currentXADataSource, "createDatabase", "create");
            } else {
               setDataSourceProperty(this.currentXADataSource, "databaseName", this.databaseName + ";create=true");
            }

            XAConnection var9 = this.currentXADataSource.getXAConnection();
            var9.close();
            setDataSourceProperty(this.currentXADataSource, "createDatabase", (String)null);
         }

      } catch (Throwable var8) {
         throw this.handleException(var8);
      }
   }

   public void XAConnectStatement(ij var1, Token var2, Token var3, String var4) throws SQLException {
      try {
         if (this.currentXAConnection != null) {
            try {
               this.currentXAConnection.close();
            } catch (SQLException var7) {
            }

            this.currentXAConnection = null;
         }

         Object var5 = null;
         String var6 = "";
         if (var3 != null) {
            var6 = var1.stringValue(var3.image);
         }

         if (var2 != null) {
            String var9 = var1.stringValue(var2.image);
            this.currentXAConnection = this.currentXADataSource.getXAConnection(var9, var6);
         } else {
            this.currentXAConnection = this.currentXADataSource.getXAConnection();
         }

      } catch (Throwable var8) {
         throw this.handleException(var8);
      }
   }

   public void XADisconnectStatement(ij var1, String var2) throws SQLException {
      if (this.currentXAConnection == null) {
         throw ijException.noSuchConnection("XAConnection");
      } else {
         this.currentXAConnection.close();
         this.currentXAConnection = null;
      }
   }

   public Connection XAGetConnectionStatement(ij var1, String var2) throws SQLException {
      try {
         return this.currentXAConnection.getConnection();
      } catch (Throwable var4) {
         throw this.handleException(var4);
      }
   }

   public void CommitStatement(ij var1, Token var2, Token var3, int var4) throws SQLException {
      try {
         this.currentXAConnection.getXAResource().commit(this.makeXid(var4), var2 != null);
      } catch (Throwable var6) {
         throw this.handleException(var6);
      }
   }

   public void EndStatement(ij var1, int var2, int var3) throws SQLException {
      try {
         this.currentXAConnection.getXAResource().end(this.makeXid(var3), var2);
      } catch (Throwable var5) {
         throw this.handleException(var5);
      }
   }

   public void ForgetStatement(ij var1, int var2) throws SQLException {
      try {
         this.currentXAConnection.getXAResource().forget(this.makeXid(var2));
      } catch (Throwable var4) {
         throw this.handleException(var4);
      }
   }

   public void PrepareStatement(ij var1, int var2) throws SQLException {
      try {
         this.currentXAConnection.getXAResource().prepare(this.makeXid(var2));
      } catch (Throwable var4) {
         throw this.handleException(var4);
      }
   }

   public ijResult RecoverStatement(ij var1, int var2) throws SQLException {
      Object var3 = null;

      try {
         var7 = this.currentXAConnection.getXAResource().recover(var2);
      } catch (Throwable var6) {
         throw this.handleException(var6);
      }

      Vector var4 = new Vector();
      var4.addElement("");
      var4.addElement(LocalizedResource.getMessage("IJ_Reco0InDoubT", LocalizedResource.getNumber(var7.length)));
      var4.addElement("");

      for(int var5 = 0; var5 < var7.length; ++var5) {
         var4.addElement(LocalizedResource.getMessage("IJ_Tran01", LocalizedResource.getNumber(var5 + 1), var7[var5].toString()));
      }

      return new ijVectorResult(var4, (SQLWarning)null);
   }

   public void RollbackStatement(ij var1, int var2) throws SQLException {
      try {
         this.currentXAConnection.getXAResource().rollback(this.makeXid(var2));
      } catch (Throwable var4) {
         throw this.handleException(var4);
      }
   }

   public void StartStatement(ij var1, int var2, int var3) throws SQLException {
      try {
         this.currentXAConnection.getXAResource().start(this.makeXid(var3), var2);
      } catch (Throwable var5) {
         throw this.handleException(var5);
      }
   }

   private SQLException handleException(Throwable var1) {
      if (var1 instanceof SQLException) {
         return (SQLException)var1;
      } else if (var1 instanceof XAException) {
         int var4 = ((XAException)var1).errorCode;
         String var3 = LocalizedResource.getMessage("IJ_IlleValu");
         switch (var4) {
            case -9 -> var3 = "XAER_OUTSIDE ";
            case -8 -> var3 = "XAER_DUPID ";
            case -7 -> var3 = "XAER_RMFAIL ";
            case -6 -> var3 = "XAER_PROTO ";
            case -5 -> var3 = "XAER_INVAL ";
            case -4 -> var3 = "XAER_NOTA ";
            case -3 -> var3 = "XAER_RMERR ";
            case -2 -> var3 = "XAER_ASYNC ";
            case 3 -> var3 = "XA_RDONLY ";
            case 4 -> var3 = "XA_RETRY ";
            case 5 -> var3 = "XA_HEURMIX";
            case 6 -> var3 = "XA_HEURRB ";
            case 7 -> var3 = "XA_HEURCOM ";
            case 8 -> var3 = "XA_HEURHAZ";
            case 9 -> var3 = "XA_NOMIGRATE ";
            case 100 -> var3 = "XA_RBROLLBACK ";
            case 101 -> var3 = "XA_RBCOMMFAIL ";
            case 102 -> var3 = "XA_RBDEADLOCK ";
            case 103 -> var3 = "XA_RBINTEGRITY ";
            case 104 -> var3 = "XA_RBOTHER ";
            case 105 -> var3 = "XA_RBPROTO ";
            case 106 -> var3 = "XA_RBTIMEOUT ";
            case 107 -> var3 = "XA_RBTRANSIENT ";
         }

         throw new ijException(var3);
      } else {
         String var2 = LocalizedResource.getMessage("IJ_01SeeLog", var1.toString(), var1.getMessage());
         throw new ijException(var2);
      }
   }

   public Connection DataSourceStatement(ij var1, Token var2, Token var3, Token var4, Token var5, String var6) throws SQLException {
      try {
         Class var7 = Class.forName("org.apache.derby.jdbc.EmbeddedDataSource");
         this.currentDataSource = (DataSource)var7.getConstructor().newInstance();
      } catch (Exception var10) {
         throw new SQLException(var10.toString());
      }

      this.databaseName = var1.stringValue(var2.image);
      setDataSourceProperty(this.currentDataSource, "databaseName", this.databaseName);
      setDataSourceProperty(this.currentXADataSource, "dataSourceName", this.databaseName);
      Object var11 = null;
      Object var8 = null;
      String var9 = "";
      if (var5 != null) {
         var9 = var1.stringValue(var5.image);
      }

      Connection var12;
      if (var4 != null) {
         String var13 = var1.stringValue(var4.image);
         var12 = this.currentDataSource.getConnection(var13, var9);
      } else {
         var12 = this.currentDataSource.getConnection();
      }

      return var12;
   }

   public void CPDataSourceStatement(ij var1, Token var2, Token var3) throws SQLException {
      try {
         Class var4 = Class.forName("org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource");
         this.currentCPDataSource = (ConnectionPoolDataSource)var4.getConstructor().newInstance();
      } catch (Exception var5) {
         throw new SQLException(var5.toString());
      }

      this.databaseName = var1.stringValue(var2.image);
      setDataSourceProperty(this.currentCPDataSource, "databaseName", this.databaseName);
   }

   public void CPConnectStatement(ij var1, Token var2, Token var3, String var4) throws SQLException {
      Object var5 = null;
      String var6 = "";
      if (var3 != null) {
         var6 = var1.stringValue(var3.image);
      }

      if (var2 != null) {
         String var7 = var1.stringValue(var2.image);
         this.currentPooledConnection = this.currentCPDataSource.getPooledConnection(var7, var6);
      } else {
         this.currentPooledConnection = this.currentCPDataSource.getPooledConnection();
      }

   }

   public Connection CPGetConnectionStatement(ij var1, String var2) throws SQLException {
      return this.currentPooledConnection.getConnection();
   }

   public void CPDisconnectStatement(ij var1, String var2) throws SQLException {
      if (this.currentPooledConnection == null) {
         throw ijException.noSuchConnection(LocalizedResource.getMessage("PooledConnection"));
      } else {
         this.currentPooledConnection.close();
         this.currentPooledConnection = null;
      }
   }

   private XADataSource getXADataSource() throws Exception {
      try {
         if (this.isJCC) {
            Class var17 = Class.forName("com.ibm.db2.jcc.DB2XADataSource");
            return (XADataSource)var17.getConstructor().newInstance();
         }

         if (this.isNetClient) {
            if (ij.JNDI()) {
               try {
                  Class var16 = Class.forName("org.apache.derby.jdbc.ClientXADataSource40");
                  return (XADataSource)var16.getConstructor().newInstance();
               } catch (ClassNotFoundException var3) {
               } catch (UnsupportedClassVersionError var4) {
               }

               Class var15 = Class.forName("org.apache.derby.jdbc.ClientXADataSource");
               return (XADataSource)var15.getConstructor().newInstance();
            }

            Class var14 = Class.forName("org.apache.derby.jdbc.BasicClientXADataSource40");
            return (XADataSource)var14.getConstructor().newInstance();
         }

         if (ij.JNDI()) {
            try {
               Class var13 = Class.forName("org.apache.derby.jdbc.EmbeddedXADataSource40");
               return (XADataSource)var13.getConstructor().newInstance();
            } catch (ClassNotFoundException var5) {
            } catch (UnsupportedClassVersionError var6) {
            }

            Class var12 = Class.forName("org.apache.derby.jdbc.EmbeddedXADataSource");
            return (XADataSource)var12.getConstructor().newInstance();
         }

         Class var1 = Class.forName("org.apache.derby.jdbc.BasicEmbeddedXADataSource40");
         return (XADataSource)var1.getConstructor().newInstance();
      } catch (ClassNotFoundException var7) {
         throw new ijException(LocalizedResource.getMessage("IJ_XAClass"));
      } catch (InstantiationException var8) {
      } catch (IllegalAccessException var9) {
      } catch (NoSuchMethodException var10) {
      } catch (InvocationTargetException var11) {
      }

      throw new ijException(LocalizedResource.getMessage("IJ_XANoI"));
   }

   private static void setDataSourceProperty(Object var0, String var1, int var2) throws SQLException {
      char var10000 = Character.toUpperCase(var1.charAt(0));
      String var3 = "set" + var10000 + var1.substring(1);

      try {
         Method var4 = var0.getClass().getMethod(var3, INT_P);
         var4.invoke(var0, var2);
      } catch (Exception var5) {
         throw new SQLException(var1 + " ???" + var5.getMessage());
      }
   }

   private static void setDataSourceProperty(Object var0, String var1, String var2) throws SQLException {
      char var10000 = Character.toUpperCase(var1.charAt(0));
      String var3 = "set" + var10000 + var1.substring(1);

      try {
         Method var4 = var0.getClass().getMethod(var3, STRING_P);
         var4.invoke(var0, var2);
      } catch (Exception var5) {
         throw new SQLException(var1 + " ???");
      }
   }

   private static void setDataSourceProperty(Object var0, String var1, boolean var2) throws SQLException {
      char var10000 = Character.toUpperCase(var1.charAt(0));
      String var3 = "set" + var10000 + var1.substring(1);

      try {
         Method var4 = var0.getClass().getMethod(var3, BOOLEAN_P);
         var4.invoke(var0, var2);
      } catch (Exception var5) {
         throw new SQLException(var1 + " ???");
      }
   }

   static {
      INT_P = new Class[]{Integer.TYPE};
      BOOLEAN_P = new Class[]{Boolean.TYPE};
   }
}
