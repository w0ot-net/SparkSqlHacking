package org.apache.derby.impl.tools.ij;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.derby.iapi.tools.i18n.LocalizedInput;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;
import org.apache.derby.tools.JDBCDisplayUtil;

class ij implements ijConstants {
   static final String PROTOCOL_PROPERTY = "ij.protocol";
   static final String URLCHECK_PROPERTY = "ij.URLCheck";
   static final String USER_PROPERTY = "ij.user";
   static final String PASSWORD_PROPERTY = "ij.password";
   static final String FRAMEWORK_PROPERTY = "framework";
   boolean elapsedTime;
   Connection theConnection;
   ConnectionEnv currentConnEnv;
   String urlCheck;
   xaAbstractHelper xahelper;
   boolean exit;
   utilMain utilInstance;
   Hashtable ignoreErrors;
   String protocol;
   Hashtable namedProtocols;
   public ijTokenManager token_source;
   public Token token;
   public Token jj_nt;
   private Token jj_scanpos;
   private Token jj_lastpos;
   private int jj_la;
   public boolean lookingAhead;
   private boolean jj_semLA;
   private int jj_gen;
   private final int[] jj_la1;
   private static int[] jj_la1_0;
   private static int[] jj_la1_1;
   private static int[] jj_la1_2;
   private static int[] jj_la1_3;
   private static int[] jj_la1_4;
   private final JJCalls[] jj_2_rtns;
   private boolean jj_rescan;
   private int jj_gc;
   private final LookaheadSuccess jj_ls;
   private Vector jj_expentries;
   private int[] jj_expentry;
   private int jj_kind;
   private int[] jj_lasttokens;
   private int jj_endpos;

   ij(ijTokenManager var1, utilMain var2) {
      this(var1);
      this.utilInstance = var2;
   }

   void initFromEnvironment() {
      Properties var1 = (Properties)AccessController.doPrivileged(new PrivilegedAction() {
         public Properties run() {
            return System.getProperties();
         }
      });
      this.urlCheck = var1.getProperty("ij.URLCheck");
      this.protocol = var1.getProperty("ij.protocol");
      String var2 = var1.getProperty("framework");
      if (JDBC20X() && JTA()) {
         try {
            Class var3 = Class.forName("org.apache.derby.impl.tools.ij.xaHelper");
            this.xahelper = (xaAbstractHelper)var3.getConstructor().newInstance();
            this.xahelper.setFramework(var2);
         } catch (Exception var7) {
         }
      }

      this.namedProtocols = new Hashtable();
      String var8 = "ij.protocol.";
      Enumeration var4 = var1.propertyNames();

      while(var4.hasMoreElements()) {
         String var5 = (String)var4.nextElement();
         if (var5.startsWith(var8)) {
            String var6 = var5.substring(var8.length());
            this.installProtocol(var6.toUpperCase(Locale.ENGLISH), var1.getProperty(var5));
         }
      }

   }

   private static boolean JDBC20X() {
      try {
         Class.forName("javax.sql.DataSource");
         Class.forName("javax.sql.ConnectionPoolDataSource");
         Class.forName("javax.sql.PooledConnection");
         Class.forName("javax.sql.XAConnection");
         Class.forName("javax.sql.XADataSource");
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

   private static boolean JTA() {
      try {
         Class.forName("javax.transaction.xa.Xid");
         Class.forName("javax.transaction.xa.XAResource");
         Class.forName("javax.transaction.xa.XAException");
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

   public static boolean JNDI() {
      try {
         Class.forName("javax.naming.spi.Resolver");
         Class.forName("javax.naming.Referenceable");
         Class.forName("javax.naming.directory.Attribute");
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

   SQLWarning appendWarnings(SQLWarning var1, SQLWarning var2) {
      if (var1 == null) {
         return var2;
      } else {
         if (var1.getNextException() == null) {
            var1.setNextException(var2);
         } else {
            this.appendWarnings(var1.getNextWarning(), var2);
         }

         return var1;
      }
   }

   boolean getElapsedTimeState() {
      return this.elapsedTime;
   }

   String stringValue(String var1) {
      String var2 = var1.substring(1, var1.length() - 1);
      byte var3 = 39;

      for(int var4 = var2.indexOf(var3); var4 != -1; var4 = var2.indexOf(var3, var4 + 1)) {
         String var10000 = var2.substring(0, var4 + 1);
         var2 = var10000 + var2.substring(var4 + 2);
      }

      return var2;
   }

   void installProtocol(String var1, String var2) {
      try {
         util.loadDriverIfKnown(var2);
      } catch (ClassNotFoundException var4) {
         throw ijException.classNotFoundForProtocol(var2);
      } catch (IllegalArgumentException var5) {
         throw ijException.classNotFoundForProtocol(var2);
      } catch (IllegalAccessException var6) {
         throw ijException.classNotFoundForProtocol(var2);
      } catch (InstantiationException var7) {
         throw ijException.classNotFoundForProtocol(var2);
      } catch (NoSuchMethodException var8) {
         throw ijException.classNotFoundForProtocol(var2);
      } catch (InvocationTargetException var9) {
         throw ijException.classNotFoundForProtocol(var2);
      }

      if (var1 == null) {
         this.protocol = var2;
      } else {
         this.namedProtocols.put(var1, var2);
      }

   }

   private static Process execProcess(String var0) throws IOException {
      return Runtime.getRuntime().exec(var0);
   }

   void haveConnection() {
      JDBCDisplayUtil.checkNotNull(this.theConnection, "connection");
   }

   Session findSession(String var1) {
      Session var2 = this.currentConnEnv.getSession(var1);
      if (var2 == null) {
         throw ijException.noSuchConnection(var1);
      } else {
         return var2;
      }
   }

   PreparedStatement findPreparedStatement(QualifiedIdentifier var1) {
      Session var2 = this.findSession(var1.getSessionName());
      PreparedStatement var3 = var2.getPreparedStatement(var1.getLocalName());
      JDBCDisplayUtil.checkNotNull(var3, "prepared statement " + var1);
      return var3;
   }

   ResultSet findCursor(QualifiedIdentifier var1) {
      Session var2 = this.findSession(var1.getSessionName());
      ResultSet var3 = var2.getCursor(var1.getLocalName());
      JDBCDisplayUtil.checkNotNull(var3, "cursor " + var1);
      return var3;
   }

   ijResult executeImmediate(String var1) throws SQLException {
      Statement var2 = null;

      try {
         long var3 = 0L;
         long var5 = 0L;
         boolean var7 = false;
         this.haveConnection();
         var2 = this.theConnection.createStatement();
         if (this.currentConnEnv != null) {
            boolean var8 = this.currentConnEnv.getSession().getIsDNC();
            if (var8) {
               DatabaseMetaData var9 = this.theConnection.getMetaData();
               int var10 = var9.getDriverMajorVersion();
               if (var10 > 10 || var10 == 10 && var9.getDriverMinorVersion() > 1) {
                  var8 = false;
               }
            }

            if (this.currentConnEnv.getSession().getIsJCC() || var8) {
               while(var1.startsWith("--")) {
                  int var14 = var1.indexOf(10) + 1;
                  var1 = var1.substring(var14);
               }

               var1 = var1.trim();
            }
         }

         var2.execute(var1);
         return new ijStatementResult(var2, true);
      } catch (SQLException var12) {
         try {
            if (var2 != null) {
               var2.close();
            }
         } catch (SQLException var11) {
         }

         throw var12;
      }
   }

   ijResult quit() throws SQLException {
      this.exit = true;
      this.currentConnEnv.removeAllSessions();
      this.theConnection = null;
      return null;
   }

   ijResult executeAsync(String var1, QualifiedIdentifier var2) {
      Session var3 = this.findSession(var2.getSessionName());
      AsyncStatement var4 = new AsyncStatement(var3.getConnection(), var1);
      var3.addAsyncStatement(var2.getLocalName(), var4);
      var4.start();
      return null;
   }

   void setConnection(ConnectionEnv var1, boolean var2) {
      Connection var3 = var1.getConnection();
      if (var1 != this.currentConnEnv) {
         this.currentConnEnv = var1;
      }

      if (this.theConnection != var3) {
         if (this.theConnection != null && !var2) {
            throw ijException.needToDisconnect();
         } else {
            this.theConnection = var3;
         }
      }
   }

   private ijResult addSession(Connection var1, String var2) throws SQLException {
      if (this.currentConnEnv.haveSession(var2)) {
         throw ijException.alreadyHaveConnectionNamed(var2);
      } else {
         this.currentConnEnv.addSession(var1, var2);
         return new ijConnectionResult(var1);
      }
   }

   private String[] sortConnectionNames() {
      int var1 = 100;
      int var2 = 0;
      String[] var3 = new String[var1];
      Hashtable var5 = this.currentConnEnv.getSessions();

      String var4;
      for(Enumeration var6 = var5.keys(); var6.hasMoreElements(); var3[var2++] = var4) {
         if (var2 == var1) {
            var1 *= 2;
            String[] var7 = new String[var1];
            System.arraycopy(var3, 0, var7, 0, var2);
            var3 = var7;
         }

         var4 = (String)var6.nextElement();
      }

      Arrays.sort(var3, 0, var2);
      return var3;
   }

   public ijResult showConnectionsMethod(boolean var1) throws SQLException {
      Hashtable var2 = this.currentConnEnv.getSessions();
      Vector var3 = new Vector();
      SQLWarning var4 = null;
      if (var2 != null && var2.size() != 0) {
         boolean var5 = false;
         int var6 = 0;
         Enumeration var7 = var2.keys();

         while(var7.hasMoreElements()) {
            ++var6;
            var7.nextElement();
         }

         String[] var13 = this.sortConnectionNames();

         for(int var8 = 0; var8 < var6; ++var8) {
            String var9 = var13[var8];
            Session var10 = (Session)var2.get(var9);
            if (var10.getConnection().isClosed()) {
               if (this.currentConnEnv.getSession() != null && var9.equals(this.currentConnEnv.getSession().getName())) {
                  this.currentConnEnv.removeCurrentSession();
                  this.theConnection = null;
               } else {
                  this.currentConnEnv.removeSession(var9);
               }
            } else {
               StringBuffer var11 = new StringBuffer();
               var11.append(var9);
               if (this.currentConnEnv.getSession() != null && var9.equals(this.currentConnEnv.getSession().getName())) {
                  var11.append('*');
                  var5 = true;
               }

               String var12 = util.getSystemProperty("ij.dataSource");
               if (var12 == null) {
                  var11.append(" - \t");
                  var11.append(var10.getConnection().getMetaData().getURL());
               }

               var4 = this.appendWarnings(var4, var10.getConnection().getWarnings());
               var10.getConnection().clearWarnings();
               var3.addElement(var11.toString());
            }
         }

         if (var5) {
            var3.addElement(LocalizedResource.getMessage("IJ_CurreConne"));
         } else {
            var3.addElement(LocalizedResource.getMessage("IJ_NoCurreConne"));
         }
      } else if (!var1) {
         var3.addElement(LocalizedResource.getMessage("IJ_NoConneAvail"));
      }

      return new ijVectorResult(var3, var4);
   }

   public static int[] intArraySubset(int[] var0, int var1, int var2) {
      int[] var3 = new int[var2 - var1];
      System.arraycopy(var0, var1, var3, 0, var2 - var1);
      return var3;
   }

   public void verifyTableExists(String var1, String var2) throws SQLException {
      if (var1 != null) {
         ResultSet var3 = null;

         try {
            DatabaseMetaData var4 = this.theConnection.getMetaData();
            var3 = var4.getTables((String)null, var1, var2, (String[])null);
            if (!var3.next()) {
               throw ijException.noSuchTable(var2);
            }
         } finally {
            try {
               if (var3 != null) {
                  var3.close();
               }
            } catch (SQLException var10) {
            }

         }

      }
   }

   public ijResult showTables(String var1, String[] var2) throws SQLException {
      ResultSet var3 = null;

      try {
         this.haveConnection();
         DatabaseMetaData var4 = this.theConnection.getMetaData();
         var3 = var4.getTables((String)null, var1, (String)null, var2);
         int[] var5 = new int[]{var3.findColumn("TABLE_SCHEM"), var3.findColumn("TABLE_NAME"), var3.findColumn("REMARKS")};
         int[] var6 = new int[]{20, 30, 20};
         return new ijResultSetResult(var3, var5, var6);
      } catch (SQLException var8) {
         try {
            if (var3 != null) {
               var3.close();
            }
         } catch (SQLException var7) {
         }

         throw var8;
      }
   }

   private ResultSet getIndexInfoForTable(String var1, String var2) throws SQLException {
      this.haveConnection();
      this.verifyTableExists(var1, var2);
      DatabaseMetaData var3 = this.theConnection.getMetaData();
      return var3.getIndexInfo((String)null, var1, var2, false, true);
   }

   private int[] getDisplayColumnsForIndex(String var1, ResultSet var2) throws SQLException {
      int[] var3 = new int[]{var2.findColumn("TABLE_SCHEM"), var2.findColumn("TABLE_NAME"), var2.findColumn("COLUMN_NAME"), var2.findColumn("NON_UNIQUE"), var2.findColumn("TYPE"), var2.findColumn("ASC_OR_DESC"), var2.findColumn("CARDINALITY"), var2.findColumn("PAGES")};
      if (var1 != null) {
         var3 = intArraySubset(var3, 1, var3.length);
      }

      return var3;
   }

   private int[] getColumnWidthsForIndex(String var1) {
      int[] var2 = new int[]{20, 20, 20, 6, 4, 4, 8, 8};
      if (var1 != null) {
         var2 = intArraySubset(var2, 1, var2.length);
      }

      return var2;
   }

   public ijResult showIndexes(String var1, String var2) throws SQLException {
      Object var3 = null;
      int[] var4 = null;
      int[] var5 = null;

      try {
         if (var2 != null) {
            ResultSet var6 = this.getIndexInfoForTable(var1, var2);
            var4 = this.getDisplayColumnsForIndex(var1, var6);
            var5 = this.getColumnWidthsForIndex(var1);
            var3 = new ijResultSetResult(var6, var4, var5);
         } else {
            this.haveConnection();
            this.verifyTableExists(var1, var2);
            DatabaseMetaData var17 = this.theConnection.getMetaData();
            ResultSet var7 = var17.getTables((String)null, var1, (String)null, (String[])null);
            ArrayList var8 = new ArrayList();
            boolean var9 = true;
            Object var10 = null;

            while(var7.next()) {
               String var11 = var7.getString("TABLE_NAME");
               ResultSet var18 = this.getIndexInfoForTable(var1, var11);
               var8.add(var18);
               if (var9) {
                  var4 = this.getDisplayColumnsForIndex(var1, var18);
                  var5 = this.getColumnWidthsForIndex(var1);
                  var9 = false;
               }
            }

            var3 = new ijMultipleResultSetResult(var8, var4, var5);
         }

         return (ijResult)var3;
      } catch (SQLException var13) {
         try {
            if (var3 != null) {
               ((ijResult)var3).closeStatement();
            }
         } catch (SQLException var12) {
         }

         throw var13;
      }
   }

   public ijResult showProcedures(String var1) throws SQLException {
      ResultSet var2 = null;

      try {
         this.haveConnection();
         DatabaseMetaData var3 = this.theConnection.getMetaData();
         var2 = var3.getProcedures((String)null, var1, (String)null);
         int[] var4 = new int[]{var2.findColumn("PROCEDURE_SCHEM"), var2.findColumn("PROCEDURE_NAME"), var2.findColumn("REMARKS")};
         int[] var5 = new int[]{20, 30, 20};
         return new ijResultSetResult(var2, var4, var5);
      } catch (SQLException var7) {
         try {
            if (var2 != null) {
               var2.close();
            }
         } catch (SQLException var6) {
         }

         throw var7;
      }
   }

   public ijResult showFunctions(String var1) throws SQLException {
      ResultSet var2 = null;

      try {
         this.haveConnection();
         DatabaseMetaData var3 = this.theConnection.getMetaData();

         try {
            Method var4 = var3.getClass().getMethod("getFunctions", String.class, String.class, String.class);
            var2 = (ResultSet)var4.invoke(var3, null, var1, null);
         } catch (NoSuchMethodException var8) {
            throw ijException.notAvailableForDriver(var3.getDriverName());
         } catch (IllegalAccessException var9) {
            throw ijException.notAvailableForDriver(var3.getDriverName());
         } catch (AbstractMethodError var10) {
            throw ijException.notAvailableForDriver(var3.getDriverName());
         } catch (InvocationTargetException var11) {
            Throwable var6 = var11.getCause();
            if (var6 instanceof SQLException) {
               throw (SQLException)var6;
            }

            throw ijException.notAvailableForDriver(var3.getDriverName());
         }

         int[] var5 = new int[]{var2.findColumn("FUNCTION_SCHEM"), var2.findColumn("FUNCTION_NAME"), var2.findColumn("REMARKS")};
         int[] var14 = new int[]{14, 28, 35};
         return new ijResultSetResult(var2, var5, var14);
      } catch (SQLException var12) {
         try {
            if (var2 != null) {
               var2.close();
            }
         } catch (SQLException var7) {
         }

         throw var12;
      }
   }

   public ijResult showSchemas() throws SQLException {
      ResultSet var1 = null;

      try {
         this.haveConnection();
         DatabaseMetaData var2 = this.theConnection.getMetaData();
         var1 = var2.getSchemas();
         int[] var3 = new int[]{var1.findColumn("TABLE_SCHEM")};
         int[] var4 = new int[]{30};
         return new ijResultSetResult(var1, var3, var4);
      } catch (SQLException var6) {
         try {
            if (var1 != null) {
               var1.close();
            }
         } catch (SQLException var5) {
         }

         throw var6;
      }
   }

   public ijResult showRoles() throws SQLException {
      ResultSet var1 = null;

      try {
         this.haveConnection();
         if (!this.currentConnEnv.getSession().getIsDNC() && !this.currentConnEnv.getSession().getIsEmbeddedDerby()) {
            throw ijException.notAvailableForDriver(this.theConnection.getMetaData().getDriverName());
         } else {
            var1 = this.theConnection.createStatement().executeQuery("SELECT ROLEID FROM SYS.SYSROLES WHERE ISDEF='Y' ORDER BY ROLEID ASC");
            int[] var2 = new int[]{var1.findColumn("ROLEID")};
            int[] var3 = new int[]{30};
            return new ijResultSetResult(var1, var2, var3);
         }
      } catch (SQLException var5) {
         try {
            if (var1 != null) {
               var1.close();
            }
         } catch (SQLException var4) {
         }

         throw var5;
      }
   }

   public ijResult showEnabledRoles() throws SQLException {
      ResultSet var1 = null;

      try {
         this.haveConnection();
         if (!this.currentConnEnv.getSession().getIsDNC() && !this.currentConnEnv.getSession().getIsEmbeddedDerby()) {
            throw ijException.notAvailableForDriver(this.theConnection.getMetaData().getDriverName());
         } else {
            var1 = this.theConnection.createStatement().executeQuery("SELECT * FROM\t TABLE(\t   SYSCS_DIAG.CONTAINED_ROLES(CURRENT_ROLE)) T ORDER BY ROLEID");
            int[] var2 = new int[]{var1.findColumn("ROLEID")};
            int[] var3 = new int[]{30};
            return new ijResultSetResult(var1, var2, var3);
         }
      } catch (SQLException var5) {
         try {
            if (var1 != null) {
               var1.close();
            }
         } catch (SQLException var4) {
         }

         throw var5;
      }
   }

   public ijResult showSettableRoles() throws SQLException {
      ResultSet var1 = null;

      try {
         this.haveConnection();
         if (!this.currentConnEnv.getSession().getIsDNC() && !this.currentConnEnv.getSession().getIsEmbeddedDerby()) {
            throw ijException.notAvailableForDriver(this.theConnection.getMetaData().getDriverName());
         } else {
            var1 = this.theConnection.createStatement().executeQuery("select distinct * from (  select roleid from sys.sysroles s    where s.grantee = current_user or s.grantee = 'PUBLIC'  union  select roleid from sys.sysroles s    where s.isdef='Y' and current_user in        (select authorizationid from sys.sysschemas             where schemaname = 'SYS')) t order by roleid");
            int[] var2 = new int[]{var1.findColumn("ROLEID")};
            int[] var3 = new int[]{30};
            return new ijResultSetResult(var1, var2, var3);
         }
      } catch (SQLException var5) {
         try {
            if (var1 != null) {
               var1.close();
            }
         } catch (SQLException var4) {
         }

         throw var5;
      }
   }

   public ijResult describeTable(String var1, String var2) throws SQLException {
      ResultSet var3 = null;

      try {
         this.haveConnection();
         this.verifyTableExists(var1, var2);
         DatabaseMetaData var4 = this.theConnection.getMetaData();
         var3 = var4.getColumns((String)null, var1, var2, (String)null);
         int[] var5 = new int[]{var3.findColumn("TABLE_SCHEM"), var3.findColumn("TABLE_NAME"), var3.findColumn("COLUMN_NAME"), var3.findColumn("TYPE_NAME"), var3.findColumn("DECIMAL_DIGITS"), var3.findColumn("NUM_PREC_RADIX"), var3.findColumn("COLUMN_SIZE"), var3.findColumn("COLUMN_DEF"), var3.findColumn("CHAR_OCTET_LENGTH"), var3.findColumn("IS_NULLABLE")};
         int[] var6 = new int[]{20, 20, 20, 9, 4, 4, 6, 10, 10, 8};
         if (var1 != null && var2 != null) {
            var5 = intArraySubset(var5, 2, var5.length);
            var6 = intArraySubset(var6, 2, var6.length);
         }

         return new ijResultSetResult(var3, var5, var6);
      } catch (SQLException var8) {
         try {
            if (var3 != null) {
               var3.close();
            }
         } catch (SQLException var7) {
         }

         throw var8;
      }
   }

   private Object makeXid(int var1) {
      return null;
   }

   public final ijResult ijStatement() throws ParseException, SQLException {
      ijResult var1 = null;
      if (this.jj_2_1(1)) {
         if (this.getToken(1).kind == 77 && this.getToken(3).kind != 79 && this.getToken(3).kind != 80) {
            var1 = this.RollbackStatement();
         } else {
            switch (this.jj_nt.kind) {
               case 14:
                  var1 = this.AbsoluteStatement();
                  break;
               case 15:
                  var1 = this.AfterLastStatement();
                  break;
               case 16:
               case 17:
               case 18:
               case 20:
               case 27:
               case 28:
               case 29:
               case 30:
               case 35:
               case 36:
               case 37:
               case 40:
               case 42:
               case 43:
               case 44:
               case 47:
               case 50:
               case 51:
               case 52:
               case 53:
               case 57:
               case 59:
               case 61:
               case 62:
               case 63:
               case 64:
               case 67:
               case 68:
               case 69:
               case 75:
               case 76:
               case 77:
               case 79:
               case 80:
               case 81:
               case 82:
               case 83:
               case 85:
               case 87:
               case 88:
               case 89:
               case 90:
               case 91:
               case 92:
               case 93:
               case 95:
               case 96:
               case 97:
               case 103:
               case 104:
               case 107:
               case 108:
               case 111:
               case 114:
               case 115:
               case 116:
               default:
                  this.jj_la1[0] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
               case 19:
                  var1 = this.AsyncStatement();
                  break;
               case 21:
                  var1 = this.AutocommitStatement();
                  break;
               case 22:
                  var1 = this.Bang();
                  break;
               case 23:
                  var1 = this.BeforeFirstStatement();
                  break;
               case 24:
                  var1 = this.CloseStatement();
                  break;
               case 25:
                  var1 = this.CommitStatement();
                  break;
               case 26:
                  var1 = this.ConnectStatement();
                  break;
               case 31:
                  var1 = this.DescTableStatement();
                  break;
               case 32:
                  var1 = this.DisconnectStatement();
                  break;
               case 33:
                  var1 = this.DriverStatement();
                  break;
               case 34:
                  var1 = this.ElapsedTimeStatement();
                  break;
               case 38:
                  var1 = this.ExecuteStatement();
                  break;
               case 39:
               case 71:
                  var1 = this.ExitStatement();
                  break;
               case 41:
                  var1 = this.FirstStatement();
                  break;
               case 45:
                  var1 = this.GetCursorStatement();
                  break;
               case 46:
                  var1 = this.GetCurrentRowNumber();
                  break;
               case 48:
                  var1 = this.HoldForConnectionStatement();
                  break;
               case 49:
                  var1 = this.HelpStatement();
                  break;
               case 54:
                  var1 = this.LastStatement();
                  break;
               case 55:
                  var1 = this.LocalizedDisplay();
                  break;
               case 56:
                  var1 = this.MaximumDisplayWidthStatement();
                  break;
               case 58:
                  var1 = this.NextStatement();
                  break;
               case 60:
                  var1 = this.NoHoldForConnectionStatement();
                  break;
               case 65:
                  var1 = this.PrepareStatement();
                  break;
               case 66:
                  var1 = this.PreviousStatement();
                  break;
               case 70:
                  var1 = this.ProtocolStatement();
                  break;
               case 72:
                  var1 = this.ReadOnlyStatement();
                  break;
               case 73:
                  var1 = this.RelativeStatement();
                  break;
               case 74:
                  var1 = this.RemoveStatement();
                  break;
               case 78:
                  var1 = this.RunStatement();
                  break;
               case 84:
                  var1 = this.SetConnectionStatement();
                  break;
               case 86:
                  var1 = this.ShowStatement();
                  break;
               case 94:
                  var1 = this.WaitForStatement();
                  break;
               case 98:
                  var1 = this.XA_DataSourceStatement();
                  break;
               case 99:
                  var1 = this.XA_ConnectStatement();
                  break;
               case 100:
                  var1 = this.XA_CommitStatement();
                  break;
               case 101:
                  var1 = this.XA_DisconnectStatement();
                  break;
               case 102:
                  var1 = this.XA_EndStatement();
                  break;
               case 105:
                  var1 = this.XA_ForgetStatement();
                  break;
               case 106:
                  var1 = this.XA_GetConnectionStatement();
                  break;
               case 109:
                  var1 = this.XA_PrepareStatement();
                  break;
               case 110:
                  var1 = this.XA_RecoverStatement();
                  break;
               case 112:
                  var1 = this.XA_RollbackStatement();
                  break;
               case 113:
                  var1 = this.XA_StartStatement();
                  break;
               case 117:
                  var1 = this.DataSourceStatement();
                  break;
               case 118:
                  var1 = this.CP_DataSourceStatement();
                  break;
               case 119:
                  var1 = this.CP_ConnectStatement();
                  break;
               case 120:
                  var1 = this.CP_GetConnectionStatement();
                  break;
               case 121:
                  var1 = this.CP_DisconnectStatement();
            }
         }
      }

      this.jj_consume_token(0);
      return var1;
   }

   public final ijResult ProtocolStatement() throws ParseException, SQLException {
      String var2 = null;
      this.jj_consume_token(70);
      Token var1 = this.jj_consume_token(135);
      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var2 = this.identifier();
            break;
         default:
            this.jj_la1[1] = this.jj_gen;
      }

      this.installProtocol(var2, this.stringValue(var1.image));
      return null;
   }

   public final ijResult DriverStatement() throws ParseException, SQLException {
      Object var2 = null;
      this.jj_consume_token(33);
      Token var1 = this.jj_consume_token(135);

      try {
         String var10 = this.stringValue(var1.image);
         util.loadDriver(var10);
         return null;
      } catch (ClassNotFoundException var4) {
         throw ijException.classNotFound((String)var2);
      } catch (IllegalArgumentException var5) {
         throw ijException.driverNotClassName((String)var2);
      } catch (IllegalAccessException var6) {
         throw ijException.classNotFound((String)var2);
      } catch (InstantiationException var7) {
         throw ijException.classNotFound((String)var2);
      } catch (NoSuchMethodException var8) {
         throw ijException.classNotFound((String)var2);
      } catch (InvocationTargetException var9) {
         throw ijException.classNotFound((String)var2);
      }
   }

   public final ijResult ConnectStatement() throws ParseException, SQLException {
      this.jj_consume_token(26);
      ijResult var1;
      switch (this.jj_nt.kind) {
         case 79:
            this.jj_consume_token(79);
            var1 = this.dynamicConnection(true);
            return var1;
         case 131:
         case 135:
            switch (this.jj_nt.kind) {
               case 131:
                  var1 = this.staticConnection();
                  return var1;
               case 135:
                  var1 = this.dynamicConnection(false);
                  return var1;
               default:
                  this.jj_la1[2] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[3] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ijResult dynamicConnection(boolean var1) throws ParseException, SQLException {
      Token var3 = null;
      Token var4 = null;
      String var5 = null;
      String var6 = null;
      String var8 = util.getSystemProperty("ij.user");
      String var9 = util.getSystemProperty("ij.password");
      Properties var10 = new Properties();
      Token var2 = this.jj_consume_token(135);
      switch (this.jj_nt.kind) {
         case 70:
            this.jj_consume_token(70);
            var6 = this.identifier();
            break;
         default:
            this.jj_la1[4] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 91:
            this.jj_consume_token(91);
            var3 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[5] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 63:
            this.jj_consume_token(63);
            var4 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[6] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 20:
            this.jj_consume_token(20);
            if (this.jj_2_2(1)) {
               this.attributeList(var10);
            }
            break;
         default:
            this.jj_la1[7] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var5 = this.identifier();
            break;
         default:
            this.jj_la1[8] = this.jj_gen;
      }

      if (var3 != null) {
         var8 = this.stringValue(var3.image);
      }

      if (var4 != null) {
         var9 = this.stringValue(var4.image);
      }

      String var11 = util.getSystemProperty("ij.dataSource");
      if (var11 != null) {
         String var7 = this.stringValue(var2.image);
         if (!var7.startsWith("jdbc:")) {
            this.theConnection = util.getDataSourceConnection(var11, var8, var9, var7, false);
            return this.addSession(this.theConnection, var5);
         }
      }

      String var15;
      if (var1) {
         String var10000 = this.stringValue(var2.image);
         var15 = "jdbc:derby:" + var10000;
      } else {
         var15 = this.stringValue(var2.image);
      }

      if (var6 != null) {
         String var12 = (String)this.namedProtocols.get(var6);
         if (var12 == null) {
            throw ijException.noSuchProtocol(var6);
         }

         var15 = var12 + var15;
      }

      boolean var17 = false;

      try {
         if (var15.startsWith("jdbc:")) {
            util.loadDriverIfKnown(var15);
         }
      } catch (Exception var14) {
      }

      if (this.urlCheck == null || Boolean.valueOf(this.urlCheck)) {
         new URLCheck(var15);
      }

      if (!var15.startsWith("jdbc:") && var6 == null && this.protocol != null) {
         var15 = this.protocol + var15;
      }

      var10 = util.updateConnInfo(var8, var9, var10);
      this.theConnection = DriverManager.getConnection(var15, var10);
      return this.addSession(this.theConnection, var5);
   }

   public final ijResult DescTableStatement() throws ParseException, SQLException {
      String var1 = null;
      String var2 = null;
      Object var3 = null;
      this.jj_consume_token(31);
      String var4;
      String var5;
      switch (this.jj_nt.kind) {
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 36:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 45:
         case 46:
         case 47:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 77:
         case 78:
         case 79:
         case 81:
         case 82:
         case 83:
         case 84:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 131:
            var1 = this.caIdentifier();
            switch (this.jj_nt.kind) {
               case 64:
                  this.jj_consume_token(64);
                  var2 = this.caIdentifier();
                  break;
               default:
                  this.jj_la1[9] = this.jj_gen;
            }

            if (var2 == null) {
               var4 = null;
               var5 = var1;
            } else {
               var4 = var1;
               var5 = var2;
            }
            break;
         case 35:
         case 37:
         case 44:
         case 48:
         case 76:
         case 80:
         case 85:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 132:
         case 133:
         case 134:
         default:
            this.jj_la1[10] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
         case 135:
            Token var9 = this.jj_consume_token(135);
            var2 = this.stringValue(var9.image);
            if (var2.length() == 0) {
               throw ijException.noSuchTable("(missing)");
            }

            int var6 = var2.indexOf(46);
            if (var6 != -1) {
               var1 = var2.substring(0, var6);
               var2 = var2.substring(var6 + 1);
            }

            if ("*".equals(var2)) {
               var2 = null;
            }

            var4 = var1;
            var5 = var2;
      }

      if (var4 == null) {
         var4 = util.getSelectedSchema(this.theConnection);
      }

      return this.describeTable(var4, var5);
   }

   public final ijResult staticConnection() throws ParseException, SQLException {
      String var1 = null;
      int var3 = 0;
      int var4 = 0;
      ijResult var14 = null;
      Vector var2 = this.staticMethodName();
      String[] var11 = this.staticMethodArgs();
      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var1 = this.identifier();
            break;
         default:
            this.jj_la1[11] = this.jj_gen;
      }

      var4 = var2.size() - 1;

      StringBuffer var5;
      for(var5 = new StringBuffer(); var3 < var4; ++var3) {
         if (var3 > 0) {
            var5.append(".");
         }

         var5.append((String)var2.elementAt(var3));
      }

      String var7 = (String)var2.elementAt(var3);
      String var6 = var5.toString();

      try {
         int var10 = var11.length;
         Class[] var13 = new Class[var10];
         Class var12 = Class.forName("java.lang.String");

         for(int var19 = 0; var19 < var10; ++var19) {
            var13[var19] = var12;
         }

         Class var8 = Class.forName(var6);
         Method var9 = var8.getMethod(var7, var13);
         this.theConnection = (Connection)var9.invoke((Object)null, var11);
         var14 = this.addSession(this.theConnection, var1);
         return var14;
      } catch (InvocationTargetException var17) {
         Throwable var16 = var17.getTargetException();
         if (var16 instanceof SQLException) {
            throw (SQLException)var16;
         } else {
            throw new SQLException(var16.toString());
         }
      } catch (Exception var18) {
         throw new SQLException(var18.toString());
      }
   }

   public final ijResult SetConnectionStatement() throws ParseException, SQLException {
      this.jj_consume_token(84);
      this.jj_consume_token(27);
      String var1 = this.identifier();
      if (!this.currentConnEnv.haveSession(var1)) {
         throw ijException.noSuchConnection(var1);
      } else {
         this.currentConnEnv.setCurrentSession(var1);
         this.theConnection = this.currentConnEnv.getConnection();
         return new ijConnectionResult(this.theConnection);
      }
   }

   public final ijResult ShowStatement() throws ParseException, SQLException {
      String var1 = null;
      String var2 = null;
      String var3 = null;
      Object var4 = null;
      Token var5 = null;
      Token var6 = null;
      this.jj_consume_token(86);
      switch (this.jj_nt.kind) {
         case 16:
         case 89:
         case 90:
         case 93:
            switch (this.jj_nt.kind) {
               case 16:
                  this.jj_consume_token(16);
                  break;
               case 89:
                  this.jj_consume_token(89);
                  break;
               case 90:
                  var5 = this.jj_consume_token(90);
                  break;
               case 93:
                  var6 = this.jj_consume_token(93);
                  break;
               default:
                  this.jj_la1[12] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            switch (this.jj_nt.kind) {
               case 50:
                  this.jj_consume_token(50);
                  var1 = this.caIdentifier();
                  break;
               default:
                  this.jj_la1[13] = this.jj_gen;
            }

            String[] var7;
            if (var5 != null) {
               var7 = new String[]{"TABLE", "SYSTEM TABLE"};
            } else if (var6 != null) {
               var7 = new String[]{"VIEW"};
            } else {
               var7 = new String[]{"SYNONYM"};
            }

            return this.showTables(var1, var7);
         case 28:
            this.jj_consume_token(28);
            return this.showConnectionsMethod(false);
         case 35:
            this.jj_consume_token(35);
            return this.showEnabledRoles();
         case 44:
            this.jj_consume_token(44);
            switch (this.jj_nt.kind) {
               case 50:
                  this.jj_consume_token(50);
                  var1 = this.caIdentifier();
                  break;
               default:
                  this.jj_la1[18] = this.jj_gen;
            }

            return this.showFunctions(var1);
         case 51:
            this.jj_consume_token(51);
            label57:
            switch (this.jj_nt.kind) {
               case 43:
               case 50:
                  switch (this.jj_nt.kind) {
                     case 43:
                        this.jj_consume_token(43);
                        var2 = this.caIdentifier();
                        switch (this.jj_nt.kind) {
                           case 64:
                              this.jj_consume_token(64);
                              var3 = this.caIdentifier();
                              break label57;
                           default:
                              this.jj_la1[14] = this.jj_gen;
                              break label57;
                        }
                     case 50:
                        this.jj_consume_token(50);
                        var1 = this.caIdentifier();
                        break label57;
                     default:
                        this.jj_la1[15] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                  }
               default:
                  this.jj_la1[16] = this.jj_gen;
            }

            if (var3 != null) {
               var1 = var2;
               var2 = var3;
            }

            if (var1 == null && var2 != null) {
               var1 = util.getSelectedSchema(this.theConnection);
            }

            return this.showIndexes(var1, var2);
         case 68:
            this.jj_consume_token(68);
            switch (this.jj_nt.kind) {
               case 50:
                  this.jj_consume_token(50);
                  var1 = this.caIdentifier();
                  break;
               default:
                  this.jj_la1[17] = this.jj_gen;
            }

            return this.showProcedures(var1);
         case 76:
            this.jj_consume_token(76);
            return this.showRoles();
         case 81:
            this.jj_consume_token(81);
            return this.showSchemas();
         case 85:
            this.jj_consume_token(85);
            return this.showSettableRoles();
         default:
            this.jj_la1[19] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ijResult CommitStatement() throws ParseException, SQLException {
      this.jj_consume_token(25);
      switch (this.jj_nt.kind) {
         case 122 -> this.jj_consume_token(122);
         default -> this.jj_la1[20] = this.jj_gen;
      }

      this.haveConnection();
      this.theConnection.commit();
      return null;
   }

   public final ijResult RollbackStatement() throws ParseException, SQLException {
      this.jj_consume_token(77);
      switch (this.jj_nt.kind) {
         case 122 -> this.jj_consume_token(122);
         default -> this.jj_la1[21] = this.jj_gen;
      }

      this.haveConnection();
      this.theConnection.rollback();
      return null;
   }

   public final ijResult DisconnectStatement() throws ParseException, SQLException {
      Token var1;
      String var2;
      var1 = null;
      var2 = null;
      this.jj_consume_token(32);
      label27:
      switch (this.jj_nt.kind) {
         case 17:
         case 29:
         case 131:
            switch (this.jj_nt.kind) {
               case 17:
                  var1 = this.jj_consume_token(17);
                  break label27;
               case 29:
                  this.jj_consume_token(29);
                  break label27;
               case 131:
                  var2 = this.identifier();
                  break label27;
               default:
                  this.jj_la1[22] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[23] = this.jj_gen;
      }

      if (var1 == null) {
         if (var2 == null) {
            this.haveConnection();
            this.currentConnEnv.removeCurrentSession();
            this.theConnection = null;
         } else {
            if (!this.currentConnEnv.haveSession(var2)) {
               throw ijException.noSuchConnection(var2);
            }

            this.currentConnEnv.removeSession(var2);
            if (this.currentConnEnv.getSession() == null) {
               this.theConnection = null;
            }
         }
      } else {
         this.currentConnEnv.removeAllSessions();
         this.theConnection = null;
      }

      return null;
   }

   public final ijResult ExitStatement() throws ParseException, SQLException {
      switch (this.jj_nt.kind) {
         case 39:
            this.jj_consume_token(39);
            return this.quit();
         case 71:
            this.jj_consume_token(71);
            return this.quit();
         default:
            this.jj_la1[24] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ijResult PrepareStatement() throws ParseException, SQLException {
      this.jj_consume_token(65);
      switch (this.jj_nt.kind) {
         case 67:
            this.jj_consume_token(67);
            this.jj_consume_token(18);
            Token var7 = this.jj_consume_token(135);
            throw ijException.illegalStatementName("procedure");
         case 131:
            QualifiedIdentifier var2 = this.qualifiedIdentifier();
            this.jj_consume_token(18);
            Token var1 = this.jj_consume_token(135);
            Session var5 = this.findSession(var2.getSessionName());
            String var4 = this.stringValue(var1.image);
            PreparedStatement var3 = var5.getConnection().prepareStatement(var4);
            JDBCDisplayUtil.checkNotNull(var3, "prepared statement");
            var5.addPreparedStatement(var2.getLocalName(), var3);
            SQLWarning var6 = var3.getWarnings();
            var3.clearWarnings();
            return new ijWarningResult(var6);
         default:
            this.jj_la1[25] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ijResult GetCursorStatement() throws ParseException, SQLException {
      this.haveConnection();
      int var1 = 1003;
      Object var3 = null;
      Object var4 = null;
      int var5 = this.theConnection.getHoldability();
      Statement var7 = null;
      ResultSet var9 = null;
      this.jj_consume_token(45);
      switch (this.jj_nt.kind) {
         case 82:
            Token var17 = this.jj_consume_token(82);
            var1 = this.scrollType();
            break;
         default:
            this.jj_la1[26] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 95:
            Token var18 = this.jj_consume_token(95);
            var5 = this.holdType();
            break;
         default:
            this.jj_la1[27] = this.jj_gen;
      }

      this.jj_consume_token(30);
      QualifiedIdentifier var6 = this.qualifiedIdentifier();
      this.jj_consume_token(18);
      Token var2 = this.jj_consume_token(135);
      String var8 = this.stringValue(var2.image);

      try {
         Session var11 = this.findSession(var6.getSessionName());
         var7 = var11.getConnection().createStatement(var1, 1007, var5);
         JDBCDisplayUtil.checkNotNull(var7, "cursor");
         var7.setCursorName(var6.getLocalName());
         var9 = var7.executeQuery(var8);
         JDBCDisplayUtil.checkNotNull(var9, "cursor");
         var11.addCursorStatement(var6.getLocalName(), var7);
         var11.addCursor(var6.getLocalName(), var9);
      } catch (SQLException var16) {
         try {
            if (var9 != null) {
               var9.close();
            }
         } catch (SQLException var15) {
         }

         try {
            if (var7 != null) {
               var7.close();
            }
         } catch (SQLException var14) {
         }

         throw var16;
      }

      SQLWarning var21 = this.theConnection.getWarnings();
      SQLWarning var12 = var7.getWarnings();
      SQLWarning var13 = var9.getWarnings();
      this.theConnection.clearWarnings();
      var7.clearWarnings();
      var9.clearWarnings();
      SQLWarning var10 = this.appendWarnings(var21, var12);
      return new ijWarningResult(this.appendWarnings(var10, var13));
   }

   public final int scrollType() throws ParseException, SQLException {
      switch (this.jj_nt.kind) {
         case 52:
            this.jj_consume_token(52);
            return 1004;
         case 83:
            this.jj_consume_token(83);
            return 1005;
         default:
            this.jj_la1[28] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int holdType() throws ParseException, SQLException {
      switch (this.jj_nt.kind) {
         case 47:
            this.jj_consume_token(47);
            return 1;
         case 59:
            this.jj_consume_token(59);
            return 2;
         default:
            this.jj_la1[29] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ijResult AbsoluteStatement() throws ParseException, SQLException {
      this.jj_consume_token(14);
      int var1 = this.intLiteral();
      QualifiedIdentifier var2 = this.qualifiedIdentifier();
      ResultSet var3 = this.findCursor(var2);
      return this.utilInstance.absolute(var3, var1);
   }

   public final ijResult RelativeStatement() throws ParseException, SQLException {
      this.jj_consume_token(73);
      int var1 = this.intLiteral();
      QualifiedIdentifier var2 = this.qualifiedIdentifier();
      ResultSet var3 = this.findCursor(var2);
      return this.utilInstance.relative(var3, var1);
   }

   public final ijResult BeforeFirstStatement() throws ParseException, SQLException {
      this.jj_consume_token(23);
      this.jj_consume_token(41);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      ResultSet var2 = this.findCursor(var1);
      return this.utilInstance.beforeFirst(var2);
   }

   public final ijResult FirstStatement() throws ParseException, SQLException {
      this.jj_consume_token(41);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      ResultSet var2 = this.findCursor(var1);
      return this.utilInstance.first(var2);
   }

   public final ijResult NextStatement() throws ParseException, SQLException {
      this.jj_consume_token(58);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      ResultSet var2 = this.findCursor(var1);
      return new ijRowResult(var2, var2.next());
   }

   public final ijResult AfterLastStatement() throws ParseException, SQLException {
      this.jj_consume_token(15);
      this.jj_consume_token(54);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      ResultSet var2 = this.findCursor(var1);
      return this.utilInstance.afterLast(var2);
   }

   public final ijResult LastStatement() throws ParseException, SQLException {
      this.jj_consume_token(54);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      ResultSet var2 = this.findCursor(var1);
      return this.utilInstance.last(var2);
   }

   public final ijResult PreviousStatement() throws ParseException, SQLException {
      this.jj_consume_token(66);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      ResultSet var2 = this.findCursor(var1);
      return this.utilInstance.previous(var2);
   }

   public final ijResult GetCurrentRowNumber() throws ParseException, SQLException {
      this.jj_consume_token(46);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      ResultSet var2 = this.findCursor(var1);
      return new ijVectorResult(this.utilInstance.getCurrentRowNumber(var2), (SQLWarning)null);
   }

   public final ijResult CloseStatement() throws ParseException, SQLException {
      this.jj_consume_token(24);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      Session var3 = this.findSession(var1.getSessionName());
      ResultSet var4 = var3.getCursor(var1.getLocalName());
      JDBCDisplayUtil.checkNotNull(var4, "cursor " + var1);
      Statement var2 = var3.getCursorStatement(var1.getLocalName());
      JDBCDisplayUtil.checkNotNull(var2, "cursor" + var1);
      var4.close();
      var2.close();
      var3.removeCursor(var1.getLocalName());
      var3.removeCursorStatement(var1.getLocalName());
      return null;
   }

   public final ijResult ExecuteStatement() throws ParseException, SQLException {
      QualifiedIdentifier var1 = null;
      Token var2 = null;
      Object var4 = null;
      QualifiedIdentifier var5 = null;
      Token var6 = null;
      Object var7 = null;
      this.jj_consume_token(38);
      switch (this.jj_nt.kind) {
         case 67:
            this.jj_consume_token(67);
            var2 = this.jj_consume_token(135);
            this.haveConnection();
            Statement var8 = this.theConnection.createStatement();
            String var9 = "execute procedure " + var2;
            var8.execute(var9);
            return new ijStatementResult(var8, true);
         case 88:
            this.jj_consume_token(88);
            var2 = this.jj_consume_token(135);
            return this.executeImmediate(this.stringValue(var2.image));
         case 131:
         case 135:
            switch (this.jj_nt.kind) {
               case 131:
                  var1 = this.qualifiedIdentifier();
                  break;
               case 135:
                  var2 = this.jj_consume_token(135);
                  break;
               default:
                  this.jj_la1[30] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            label44:
            switch (this.jj_nt.kind) {
               case 92:
                  this.jj_consume_token(92);
                  switch (this.jj_nt.kind) {
                     case 131:
                        var5 = this.qualifiedIdentifier();
                        break label44;
                     case 135:
                        var6 = this.jj_consume_token(135);
                        break label44;
                     default:
                        this.jj_la1[31] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                  }
               default:
                  this.jj_la1[32] = this.jj_gen;
            }

            if (var5 == null && var6 == null) {
               if (var1 != null) {
                  this.haveConnection();
                  PreparedStatement var22 = this.findPreparedStatement(var1);
                  var22.execute();
                  return new ijStatementResult(var22, false);
               }

               return this.executeImmediate(this.stringValue(var2.image));
            } else {
               Object var10 = null;
               SQLWarning var12 = null;
               boolean var13 = false;
               PreparedStatement var3;
               if (var1 != null) {
                  var3 = this.findPreparedStatement(var1);
               } else {
                  String var23 = this.stringValue(var2.image);
                  var3 = this.theConnection.prepareStatement(var23);
                  var13 = true;
                  JDBCDisplayUtil.checkNotNull(var3, "prepared statement");
                  var12 = this.appendWarnings(var12, var3.getWarnings());
                  var3.clearWarnings();
               }

               PreparedStatement var11;
               if (var5 != null) {
                  var11 = this.findPreparedStatement(var5);
               } else {
                  String var24 = this.stringValue(var6.image);
                  var11 = this.theConnection.prepareStatement(var24);
                  JDBCDisplayUtil.checkNotNull(var11, "prepared statement");
                  this.appendWarnings(var12, var11.getWarnings());
                  var11.clearWarnings();
               }

               if (var11.execute()) {
                  ResultSet var14 = var11.getResultSet();
                  ResultSetMetaData var15 = var14.getMetaData();
                  int var16 = var15.getColumnCount();
                  boolean var17 = false;
                  boolean var18 = false;
                  ijMultiResult var19 = new ijMultiResult(var3, var14, var13);
                  return var19;
               }

               throw ijException.noUsingResults();
            }
         default:
            this.jj_la1[33] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ijResult AsyncStatement() throws ParseException, SQLException {
      Object var1 = null;
      this.jj_consume_token(19);
      QualifiedIdentifier var2 = this.qualifiedIdentifier();
      Token var3 = this.jj_consume_token(135);
      return this.executeAsync(this.stringValue(var3.image), var2);
   }

   public final ijResult WaitForStatement() throws ParseException, SQLException {
      Object var1 = null;
      this.jj_consume_token(94);
      this.jj_consume_token(42);
      QualifiedIdentifier var2 = this.qualifiedIdentifier();
      Session var3 = this.findSession(var2.getSessionName());
      AsyncStatement var4 = var3.getAsyncStatement(var2.getLocalName());
      if (var4 == null) {
         throw ijException.noSuchAsyncStatement(var2.toString());
      } else {
         try {
            var4.join();
         } catch (InterruptedException var6) {
            throw ijException.waitInterrupted(var6);
         }

         return var4.getResult();
      }
   }

   public final ijResult RemoveStatement() throws ParseException, SQLException {
      this.jj_consume_token(74);
      QualifiedIdentifier var1 = this.qualifiedIdentifier();
      Session var3 = this.findSession(var1.getSessionName());
      PreparedStatement var2 = var3.getPreparedStatement(var1.getLocalName());
      JDBCDisplayUtil.checkNotNull(var2, "prepared statement " + var1);
      var2.close();
      var3.removePreparedStatement(var1.getLocalName());
      return null;
   }

   public final ijResult RunStatement() throws ParseException, SQLException {
      Token var2 = null;
      this.jj_consume_token(78);
      switch (this.jj_nt.kind) {
         case 75 -> var2 = this.jj_consume_token(75);
         default -> this.jj_la1[34] = this.jj_gen;
      }

      Token var1 = this.jj_consume_token(135);
      if (this.utilInstance == null) {
         return null;
      } else {
         if (var2 == null) {
            this.utilInstance.newInput(this.stringValue(var1.image));
         } else {
            this.utilInstance.newResourceInput(this.stringValue(var1.image));
         }

         return null;
      }
   }

   public final ijResult AutocommitStatement() throws ParseException, SQLException {
      Token var1 = null;
      this.jj_consume_token(21);
      switch (this.jj_nt.kind) {
         case 61:
            this.jj_consume_token(61);
            break;
         case 62:
            var1 = this.jj_consume_token(62);
            break;
         default:
            this.jj_la1[35] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      this.haveConnection();
      this.theConnection.setAutoCommit(var1 != null);
      return null;
   }

   public final ijResult NoHoldForConnectionStatement() throws ParseException, SQLException {
      Object var1 = null;
      this.jj_consume_token(60);
      this.haveConnection();
      this.theConnection.setHoldability(2);
      return null;
   }

   public final ijResult HoldForConnectionStatement() throws ParseException, SQLException {
      Object var1 = null;
      this.jj_consume_token(48);
      this.haveConnection();
      this.theConnection.setHoldability(1);
      return null;
   }

   public final ijResult LocalizedDisplay() throws ParseException {
      Token var1 = null;
      this.jj_consume_token(55);
      switch (this.jj_nt.kind) {
         case 61:
            this.jj_consume_token(61);
            break;
         case 62:
            var1 = this.jj_consume_token(62);
            break;
         default:
            this.jj_la1[36] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      LocalizedResource.enableLocalization(var1 != null);
      return null;
   }

   public final ijResult ReadOnlyStatement() throws ParseException, SQLException {
      Token var1 = null;
      this.jj_consume_token(72);
      switch (this.jj_nt.kind) {
         case 61:
            this.jj_consume_token(61);
            break;
         case 62:
            var1 = this.jj_consume_token(62);
            break;
         default:
            this.jj_la1[37] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      this.haveConnection();
      this.theConnection.setReadOnly(var1 != null);
      return null;
   }

   public final ijResult ElapsedTimeStatement() throws ParseException {
      Token var1 = null;
      this.jj_consume_token(34);
      switch (this.jj_nt.kind) {
         case 61:
            this.jj_consume_token(61);
            break;
         case 62:
            var1 = this.jj_consume_token(62);
            break;
         default:
            this.jj_la1[38] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      this.elapsedTime = var1 != null;
      return null;
   }

   public final ijResult MaximumDisplayWidthStatement() throws ParseException {
      this.jj_consume_token(56);
      int var1 = this.intValue();
      JDBCDisplayUtil.setMaxDisplayWidth(var1);
      return null;
   }

   public final int intValue() throws ParseException {
      Token var1 = this.jj_consume_token(134);
      return Integer.parseInt(var1.image);
   }

   public final ijResult Bang() throws ParseException {
      Object var1 = null;
      this.jj_consume_token(22);
      Token var11 = this.jj_consume_token(135);
      ijResult var2 = null;

      try {
         Process var3 = execProcess(this.stringValue(var11.image));
         LocalizedInput var4 = new LocalizedInput(var3.getInputStream());
         Vector var6 = new Vector();
         StringBuffer var7 = new StringBuffer();

         int var5;
         while((var5 = var4.read()) != -1) {
            var7.append((char)var5);
         }

         var4.close();
         var4 = new LocalizedInput(var3.getErrorStream());

         while((var5 = var4.read()) != -1) {
            var7.append((char)var5);
         }

         var4.close();
         var6.addElement(var7);
         var2 = new ijVectorResult(var6, (SQLWarning)null);

         try {
            var3.waitFor();
            return var2;
         } catch (InterruptedException var9) {
            throw ijException.bangException(var9);
         }
      } catch (IOException var10) {
         throw ijException.bangException(var10);
      }
   }

   public final void StringList(Vector var1) throws ParseException {
      this.StringItem(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 135:
               this.StringItem(var1);
               break;
            default:
               this.jj_la1[39] = this.jj_gen;
               return;
         }
      }
   }

   public final void StringItem(Vector var1) throws ParseException {
      Token var2 = this.jj_consume_token(135);
      var1.addElement(var2);
   }

   public final ijResult HelpStatement() throws ParseException {
      this.jj_consume_token(49);
      Vector var1 = new Vector();
      StringTokenizer var2 = new StringTokenizer(LocalizedResource.getMessage("IJ_HelpText"), "\n");

      while(var2.hasMoreTokens()) {
         var1.addElement(var2.nextToken());
      }

      return new ijVectorResult(var1, (SQLWarning)null);
   }

   public final String identifier() throws ParseException {
      Token var1 = this.jj_consume_token(131);
      return var1.image.toUpperCase(Locale.ENGLISH);
   }

   public final QualifiedIdentifier qualifiedIdentifier() throws ParseException {
      String var1 = null;
      Object var2 = null;
      String var3 = this.identifier();
      switch (this.jj_nt.kind) {
         case 123:
            this.jj_consume_token(123);
            var1 = this.identifier();
            break;
         default:
            this.jj_la1[40] = this.jj_gen;
      }

      if (var1 == null) {
         this.haveConnection();
         var1 = this.currentConnEnv.getSession().getName();
      }

      return new QualifiedIdentifier(var1, var3);
   }

   public final String caIdentifier() throws ParseException, SQLException {
      Token var1 = null;
      String var2 = null;
      switch (this.jj_nt.kind) {
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 36:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 45:
         case 46:
         case 47:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 77:
         case 78:
         case 79:
         case 81:
         case 82:
         case 83:
         case 84:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
            var2 = this.keyword();
            break;
         case 35:
         case 37:
         case 44:
         case 48:
         case 76:
         case 80:
         case 85:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         default:
            this.jj_la1[41] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
         case 131:
            var1 = this.jj_consume_token(131);
      }

      this.haveConnection();
      DatabaseMetaData var3 = this.theConnection.getMetaData();
      String var4 = var2;
      if (var1 != null) {
         var4 = var1.image;
      }

      if (var3.storesLowerCaseIdentifiers()) {
         var4 = var4.toLowerCase(Locale.ENGLISH);
      } else if (var3.storesUpperCaseIdentifiers()) {
         var4 = var4.toUpperCase(Locale.ENGLISH);
      }

      return var4;
   }

   public final int intLiteral() throws ParseException, SQLException {
      String var1 = "";
      switch (this.jj_nt.kind) {
         case 129:
         case 130:
            var1 = this.sign();
            break;
         default:
            this.jj_la1[42] = this.jj_gen;
      }

      Token var2 = this.jj_consume_token(134);
      String var3 = var2.image;
      if (var1.equals("-")) {
         var3 = var1.concat(var3);
      }

      return Integer.parseInt(var3);
   }

   public final Vector staticMethodName() throws ParseException, SQLException {
      Vector var1 = new Vector();
      this.methodLeg(var1);

      while(true) {
         this.jj_consume_token(64);
         this.methodLeg(var1);
         switch (this.jj_nt.kind) {
            case 64:
               break;
            default:
               this.jj_la1[43] = this.jj_gen;
               return var1;
         }
      }
   }

   public final void methodLeg(Vector var1) throws ParseException, SQLException {
      Token var2 = this.jj_consume_token(131);
      var1.addElement(var2.image);
   }

   public final String[] staticMethodArgs() throws ParseException, SQLException {
      Vector var1;
      var1 = new Vector();
      this.jj_consume_token(125);
      label20:
      switch (this.jj_nt.kind) {
         case 135:
            this.oneStaticArg(var1);

            while(true) {
               switch (this.jj_nt.kind) {
                  case 124:
                     this.jj_consume_token(124);
                     this.oneStaticArg(var1);
                     break;
                  default:
                     this.jj_la1[44] = this.jj_gen;
                     break label20;
               }
            }
         default:
            this.jj_la1[45] = this.jj_gen;
      }

      this.jj_consume_token(126);
      String[] var2 = new String[var1.size()];
      var1.copyInto(var2);
      return var2;
   }

   public final void oneStaticArg(Vector var1) throws ParseException, SQLException {
      Token var2 = this.jj_consume_token(135);
      var1.addElement(this.stringValue(var2.image));
   }

   public final String sign() throws ParseException, SQLException {
      switch (this.jj_nt.kind) {
         case 129:
            Token var2 = this.jj_consume_token(129);
            return var2.image;
         case 130:
            Token var1 = this.jj_consume_token(130);
            return var1.image;
         default:
            this.jj_la1[46] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ijResult XA_DataSourceStatement() throws ParseException, SQLException {
      Token var2;
      String var3;
      Token var4;
      var4 = null;
      var2 = null;
      var3 = null;
      this.jj_consume_token(98);
      var4 = this.jj_consume_token(135);
      label12:
      switch (this.jj_nt.kind) {
         case 87:
         case 131:
            switch (this.jj_nt.kind) {
               case 87:
                  var2 = this.jj_consume_token(87);
                  break label12;
               case 131:
                  var3 = this.identifier();
                  break label12;
               default:
                  this.jj_la1[47] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[48] = this.jj_gen;
      }

      this.xahelper.XADataSourceStatement(this, var4, var2, var3);
      return null;
   }

   public final ijResult XA_ConnectStatement() throws ParseException, SQLException {
      Token var1 = null;
      Token var2 = null;
      String var3 = null;
      this.jj_consume_token(99);
      switch (this.jj_nt.kind) {
         case 91:
            this.jj_consume_token(91);
            var1 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[49] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 63:
            this.jj_consume_token(63);
            var2 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[50] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var3 = this.identifier();
            break;
         default:
            this.jj_la1[51] = this.jj_gen;
      }

      this.xahelper.XAConnectStatement(this, var1, var2, var3);
      return null;
   }

   public final ijResult XA_DisconnectStatement() throws ParseException, SQLException {
      String var1 = null;
      this.jj_consume_token(101);
      switch (this.jj_nt.kind) {
         case 131 -> var1 = this.identifier();
         default -> this.jj_la1[52] = this.jj_gen;
      }

      this.xahelper.XADisconnectStatement(this, var1);
      return null;
   }

   public final ijResult XA_CommitStatement() throws ParseException, SQLException {
      Token var1 = null;
      Token var2 = null;
      int var3 = 0;
      this.jj_consume_token(100);
      switch (this.jj_nt.kind) {
         case 96:
            var1 = this.jj_consume_token(96);
            break;
         case 97:
            var2 = this.jj_consume_token(97);
            break;
         default:
            this.jj_la1[53] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      var3 = this.intValue();
      this.xahelper.CommitStatement(this, var1, var2, var3);
      return null;
   }

   public final ijResult XA_EndStatement() throws ParseException, SQLException {
      int var1 = 0;
      int var2 = 0;
      this.jj_consume_token(102);
      var1 = this.xatmflag();
      var2 = this.intValue();
      this.xahelper.EndStatement(this, var1, var2);
      return null;
   }

   public final ijResult XA_ForgetStatement() throws ParseException, SQLException {
      int var1 = 0;
      this.jj_consume_token(105);
      var1 = this.intValue();
      this.xahelper.ForgetStatement(this, var1);
      return null;
   }

   public final ijResult XA_GetConnectionStatement() throws ParseException, SQLException {
      String var1 = "XA";
      this.jj_consume_token(106);
      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var1 = this.identifier();
            break;
         default:
            this.jj_la1[54] = this.jj_gen;
      }

      this.theConnection = this.xahelper.XAGetConnectionStatement(this, var1);
      this.currentConnEnv.addSession(this.theConnection, var1);
      return new ijConnectionResult(this.theConnection);
   }

   public final ijResult XA_PrepareStatement() throws ParseException, SQLException {
      int var1 = 0;
      this.jj_consume_token(109);
      var1 = this.intValue();
      this.xahelper.PrepareStatement(this, var1);
      return null;
   }

   public final ijResult XA_RecoverStatement() throws ParseException, SQLException {
      int var1 = 0;
      this.jj_consume_token(110);
      var1 = this.xatmflag();
      return this.xahelper.RecoverStatement(this, var1);
   }

   public final ijResult XA_RollbackStatement() throws ParseException, SQLException {
      int var1 = 0;
      this.jj_consume_token(112);
      var1 = this.intValue();
      this.xahelper.RollbackStatement(this, var1);
      return null;
   }

   public final ijResult XA_StartStatement() throws ParseException, SQLException {
      int var1 = 0;
      int var2 = 0;
      this.jj_consume_token(113);
      var1 = this.xatmflag();
      var2 = this.intValue();
      this.xahelper.StartStatement(this, var1, var2);
      return null;
   }

   public final int xatmflag() throws ParseException, SQLException {
      switch (this.jj_nt.kind) {
         case 103:
            this.jj_consume_token(103);
            return 8388608;
         case 104:
            this.jj_consume_token(104);
            return 536870912;
         case 105:
         case 106:
         case 109:
         case 110:
         case 112:
         case 113:
         default:
            this.jj_la1[55] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
         case 107:
            this.jj_consume_token(107);
            return 2097152;
         case 108:
            this.jj_consume_token(108);
            return 0;
         case 111:
            this.jj_consume_token(111);
            return 134217728;
         case 114:
            this.jj_consume_token(114);
            return 16777216;
         case 115:
            this.jj_consume_token(115);
            return 67108864;
         case 116:
            this.jj_consume_token(116);
            return 33554432;
      }
   }

   public final ijResult DataSourceStatement() throws ParseException, SQLException {
      Object var1 = null;
      Token var2 = null;
      Token var3 = null;
      Token var4 = null;
      String var5 = null;
      this.jj_consume_token(117);
      Token var6 = this.jj_consume_token(135);
      switch (this.jj_nt.kind) {
         case 70:
            this.jj_consume_token(70);
            var2 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[56] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 91:
            this.jj_consume_token(91);
            var3 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[57] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 63:
            this.jj_consume_token(63);
            var4 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[58] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var5 = this.identifier();
            break;
         default:
            this.jj_la1[59] = this.jj_gen;
      }

      this.theConnection = this.xahelper.DataSourceStatement(this, var6, var2, var3, var4, var5);
      return this.addSession(this.theConnection, var5);
   }

   public final ijResult CP_DataSourceStatement() throws ParseException, SQLException {
      Object var1 = null;
      Token var2 = null;
      this.jj_consume_token(118);
      Token var3 = this.jj_consume_token(135);
      switch (this.jj_nt.kind) {
         case 70:
            this.jj_consume_token(70);
            var2 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[60] = this.jj_gen;
      }

      this.xahelper.CPDataSourceStatement(this, var3, var2);
      return null;
   }

   public final ijResult CP_ConnectStatement() throws ParseException, SQLException {
      Token var1 = null;
      Token var2 = null;
      String var3 = null;
      this.jj_consume_token(119);
      switch (this.jj_nt.kind) {
         case 91:
            this.jj_consume_token(91);
            var1 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[61] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 63:
            this.jj_consume_token(63);
            var2 = this.jj_consume_token(135);
            break;
         default:
            this.jj_la1[62] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var3 = this.identifier();
            break;
         default:
            this.jj_la1[63] = this.jj_gen;
      }

      this.xahelper.CPConnectStatement(this, var1, var2, var3);
      return null;
   }

   public final ijResult CP_GetConnectionStatement() throws ParseException, SQLException {
      String var1 = "Pooled";
      this.jj_consume_token(120);
      switch (this.jj_nt.kind) {
         case 18:
            this.jj_consume_token(18);
            var1 = this.identifier();
            break;
         default:
            this.jj_la1[64] = this.jj_gen;
      }

      this.theConnection = this.xahelper.CPGetConnectionStatement(this, var1);
      this.currentConnEnv.addSession(this.theConnection, var1);
      return new ijConnectionResult(this.theConnection);
   }

   public final ijResult CP_DisconnectStatement() throws ParseException, SQLException {
      String var1 = null;
      this.jj_consume_token(121);
      switch (this.jj_nt.kind) {
         case 131 -> var1 = this.identifier();
         default -> this.jj_la1[65] = this.jj_gen;
      }

      this.xahelper.CPDisconnectStatement(this, var1);
      return null;
   }

   public final void attributeList(Properties var1) throws ParseException {
      if (this.getToken(2).kind != 37) {
         this.jj_consume_token(-1);
         throw new ParseException();
      } else {
         this.property(var1);

         while(true) {
            switch (this.jj_nt.kind) {
               case 124:
                  this.jj_consume_token(124);
                  this.property(var1);
                  break;
               default:
                  this.jj_la1[66] = this.jj_gen;
                  return;
            }
         }
      }
   }

   public final void property(Properties var1) throws ParseException {
      String var2 = this.caseSensitiveIdentifierOrKeyword();
      this.jj_consume_token(37);
      String var3 = this.caseSensitiveIdentifierOrKeyword();
      var1.put(var2, var3);
   }

   public final String caseSensitiveIdentifierOrKeyword() throws ParseException {
      Object var1 = null;
      switch (this.jj_nt.kind) {
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 36:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 45:
         case 46:
         case 47:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 77:
         case 78:
         case 79:
         case 81:
         case 82:
         case 83:
         case 84:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
            String var3 = this.keyword();
            return var3;
         case 35:
         case 37:
         case 44:
         case 48:
         case 76:
         case 80:
         case 85:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         default:
            this.jj_la1[67] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
         case 131:
            Token var2 = this.jj_consume_token(131);
            return var2.image;
      }
   }

   public final String caseSensitiveIdentifier() throws ParseException {
      Token var1 = this.jj_consume_token(131);
      return var1.image;
   }

   public final String keyword() throws ParseException {
      Object var2 = null;
      Token var1;
      switch (this.jj_nt.kind) {
         case 14:
            var1 = this.jj_consume_token(14);
            break;
         case 15:
            var1 = this.jj_consume_token(15);
            break;
         case 16:
            var1 = this.jj_consume_token(16);
            break;
         case 17:
            var1 = this.jj_consume_token(17);
            break;
         case 18:
            var1 = this.jj_consume_token(18);
            break;
         case 19:
            var1 = this.jj_consume_token(19);
            break;
         case 20:
            var1 = this.jj_consume_token(20);
            break;
         case 21:
            var1 = this.jj_consume_token(21);
            break;
         case 22:
            var1 = this.jj_consume_token(22);
            break;
         case 23:
            var1 = this.jj_consume_token(23);
            break;
         case 24:
            var1 = this.jj_consume_token(24);
            break;
         case 25:
            var1 = this.jj_consume_token(25);
            break;
         case 26:
            var1 = this.jj_consume_token(26);
            break;
         case 27:
            var1 = this.jj_consume_token(27);
            break;
         case 28:
            var1 = this.jj_consume_token(28);
            break;
         case 29:
            var1 = this.jj_consume_token(29);
            break;
         case 30:
            var1 = this.jj_consume_token(30);
            break;
         case 31:
            var1 = this.jj_consume_token(31);
            break;
         case 32:
            var1 = this.jj_consume_token(32);
            break;
         case 33:
            var1 = this.jj_consume_token(33);
            break;
         case 34:
            var1 = this.jj_consume_token(34);
            break;
         case 35:
         case 37:
         case 44:
         case 48:
         case 76:
         case 80:
         case 85:
         default:
            this.jj_la1[68] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
         case 36:
            var1 = this.jj_consume_token(36);
            break;
         case 38:
            var1 = this.jj_consume_token(38);
            break;
         case 39:
            var1 = this.jj_consume_token(39);
            break;
         case 40:
            var1 = this.jj_consume_token(40);
            break;
         case 41:
            var1 = this.jj_consume_token(41);
            break;
         case 42:
            var1 = this.jj_consume_token(42);
            break;
         case 43:
            var1 = this.jj_consume_token(43);
            break;
         case 45:
            var1 = this.jj_consume_token(45);
            break;
         case 46:
            var1 = this.jj_consume_token(46);
            break;
         case 47:
            var1 = this.jj_consume_token(47);
            break;
         case 49:
            var1 = this.jj_consume_token(49);
            break;
         case 50:
            var1 = this.jj_consume_token(50);
            break;
         case 51:
            var1 = this.jj_consume_token(51);
            break;
         case 52:
            var1 = this.jj_consume_token(52);
            break;
         case 53:
            var1 = this.jj_consume_token(53);
            break;
         case 54:
            var1 = this.jj_consume_token(54);
            break;
         case 55:
            var1 = this.jj_consume_token(55);
            break;
         case 56:
            var1 = this.jj_consume_token(56);
            break;
         case 57:
            var1 = this.jj_consume_token(57);
            break;
         case 58:
            var1 = this.jj_consume_token(58);
            break;
         case 59:
            var1 = this.jj_consume_token(59);
            break;
         case 60:
            var1 = this.jj_consume_token(60);
            break;
         case 61:
            var1 = this.jj_consume_token(61);
            break;
         case 62:
            var1 = this.jj_consume_token(62);
            break;
         case 63:
            var1 = this.jj_consume_token(63);
            break;
         case 64:
            var1 = this.jj_consume_token(64);
            break;
         case 65:
            var1 = this.jj_consume_token(65);
            break;
         case 66:
            var1 = this.jj_consume_token(66);
            break;
         case 67:
            var1 = this.jj_consume_token(67);
            break;
         case 68:
            var1 = this.jj_consume_token(68);
            break;
         case 69:
            var1 = this.jj_consume_token(69);
            break;
         case 70:
            var1 = this.jj_consume_token(70);
            break;
         case 71:
            var1 = this.jj_consume_token(71);
            break;
         case 72:
            var1 = this.jj_consume_token(72);
            break;
         case 73:
            var1 = this.jj_consume_token(73);
            break;
         case 74:
            var1 = this.jj_consume_token(74);
            break;
         case 75:
            var1 = this.jj_consume_token(75);
            break;
         case 77:
            var1 = this.jj_consume_token(77);
            break;
         case 78:
            var1 = this.jj_consume_token(78);
            break;
         case 79:
            var1 = this.jj_consume_token(79);
            break;
         case 81:
            var1 = this.jj_consume_token(81);
            break;
         case 82:
            var1 = this.jj_consume_token(82);
            break;
         case 83:
            var1 = this.jj_consume_token(83);
            break;
         case 84:
            var1 = this.jj_consume_token(84);
            break;
         case 86:
            var1 = this.jj_consume_token(86);
            break;
         case 87:
            var1 = this.jj_consume_token(87);
            break;
         case 88:
            var1 = this.jj_consume_token(88);
            break;
         case 89:
            var1 = this.jj_consume_token(89);
            break;
         case 90:
            var1 = this.jj_consume_token(90);
            break;
         case 91:
            var1 = this.jj_consume_token(91);
            break;
         case 92:
            var1 = this.jj_consume_token(92);
            break;
         case 93:
            var1 = this.jj_consume_token(93);
            break;
         case 94:
            var1 = this.jj_consume_token(94);
            break;
         case 95:
            var1 = this.jj_consume_token(95);
            break;
         case 96:
            var1 = this.jj_consume_token(96);
            break;
         case 97:
            var1 = this.jj_consume_token(97);
            break;
         case 98:
            var1 = this.jj_consume_token(98);
            break;
         case 99:
            var1 = this.jj_consume_token(99);
            break;
         case 100:
            var1 = this.jj_consume_token(100);
            break;
         case 101:
            var1 = this.jj_consume_token(101);
            break;
         case 102:
            var1 = this.jj_consume_token(102);
            break;
         case 103:
            var1 = this.jj_consume_token(103);
            break;
         case 104:
            var1 = this.jj_consume_token(104);
            break;
         case 105:
            var1 = this.jj_consume_token(105);
            break;
         case 106:
            var1 = this.jj_consume_token(106);
            break;
         case 107:
            var1 = this.jj_consume_token(107);
            break;
         case 108:
            var1 = this.jj_consume_token(108);
            break;
         case 109:
            var1 = this.jj_consume_token(109);
            break;
         case 110:
            var1 = this.jj_consume_token(110);
            break;
         case 111:
            var1 = this.jj_consume_token(111);
            break;
         case 112:
            var1 = this.jj_consume_token(112);
            break;
         case 113:
            var1 = this.jj_consume_token(113);
            break;
         case 114:
            var1 = this.jj_consume_token(114);
            break;
         case 115:
            var1 = this.jj_consume_token(115);
            break;
         case 116:
            var1 = this.jj_consume_token(116);
            break;
         case 117:
            var1 = this.jj_consume_token(117);
            break;
         case 118:
            var1 = this.jj_consume_token(118);
            break;
         case 119:
            var1 = this.jj_consume_token(119);
            break;
         case 120:
            var1 = this.jj_consume_token(120);
            break;
         case 121:
            var1 = this.jj_consume_token(121);
            break;
         case 122:
            var1 = this.jj_consume_token(122);
      }

      return var1.image;
   }

   private final boolean jj_2_1(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_1();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(0, var1);
      }

      return var3;
   }

   private final boolean jj_2_2(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_2();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(1, var1);
      }

      return var3;
   }

   private final boolean jj_3R_77() {
      return this.jj_scan_token(49);
   }

   private final boolean jj_3R_105() {
      return this.jj_scan_token(117);
   }

   private final boolean jj_3R_66() {
      return this.jj_scan_token(25);
   }

   private final boolean jj_3R_78() {
      return this.jj_scan_token(48);
   }

   private final boolean jj_3R_99() {
      return this.jj_scan_token(102);
   }

   private final boolean jj_3R_65() {
      return this.jj_scan_token(24);
   }

   private final boolean jj_3R_117() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(14)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(15)) {
            this.jj_scanpos = var1;
            if (this.jj_scan_token(16)) {
               this.jj_scanpos = var1;
               if (this.jj_scan_token(17)) {
                  this.jj_scanpos = var1;
                  if (this.jj_scan_token(18)) {
                     this.jj_scanpos = var1;
                     if (this.jj_scan_token(19)) {
                        this.jj_scanpos = var1;
                        if (this.jj_scan_token(20)) {
                           this.jj_scanpos = var1;
                           if (this.jj_scan_token(21)) {
                              this.jj_scanpos = var1;
                              if (this.jj_scan_token(22)) {
                                 this.jj_scanpos = var1;
                                 if (this.jj_scan_token(23)) {
                                    this.jj_scanpos = var1;
                                    if (this.jj_scan_token(24)) {
                                       this.jj_scanpos = var1;
                                       if (this.jj_scan_token(25)) {
                                          this.jj_scanpos = var1;
                                          if (this.jj_scan_token(26)) {
                                             this.jj_scanpos = var1;
                                             if (this.jj_scan_token(27)) {
                                                this.jj_scanpos = var1;
                                                if (this.jj_scan_token(28)) {
                                                   this.jj_scanpos = var1;
                                                   if (this.jj_scan_token(29)) {
                                                      this.jj_scanpos = var1;
                                                      if (this.jj_scan_token(30)) {
                                                         this.jj_scanpos = var1;
                                                         if (this.jj_scan_token(31)) {
                                                            this.jj_scanpos = var1;
                                                            if (this.jj_scan_token(32)) {
                                                               this.jj_scanpos = var1;
                                                               if (this.jj_scan_token(33)) {
                                                                  this.jj_scanpos = var1;
                                                                  if (this.jj_scan_token(34)) {
                                                                     this.jj_scanpos = var1;
                                                                     if (this.jj_scan_token(36)) {
                                                                        this.jj_scanpos = var1;
                                                                        if (this.jj_scan_token(38)) {
                                                                           this.jj_scanpos = var1;
                                                                           if (this.jj_scan_token(39)) {
                                                                              this.jj_scanpos = var1;
                                                                              if (this.jj_scan_token(40)) {
                                                                                 this.jj_scanpos = var1;
                                                                                 if (this.jj_scan_token(41)) {
                                                                                    this.jj_scanpos = var1;
                                                                                    if (this.jj_scan_token(42)) {
                                                                                       this.jj_scanpos = var1;
                                                                                       if (this.jj_scan_token(43)) {
                                                                                          this.jj_scanpos = var1;
                                                                                          if (this.jj_scan_token(45)) {
                                                                                             this.jj_scanpos = var1;
                                                                                             if (this.jj_scan_token(46)) {
                                                                                                this.jj_scanpos = var1;
                                                                                                if (this.jj_scan_token(47)) {
                                                                                                   this.jj_scanpos = var1;
                                                                                                   if (this.jj_scan_token(49)) {
                                                                                                      this.jj_scanpos = var1;
                                                                                                      if (this.jj_scan_token(50)) {
                                                                                                         this.jj_scanpos = var1;
                                                                                                         if (this.jj_scan_token(51)) {
                                                                                                            this.jj_scanpos = var1;
                                                                                                            if (this.jj_scan_token(52)) {
                                                                                                               this.jj_scanpos = var1;
                                                                                                               if (this.jj_scan_token(53)) {
                                                                                                                  this.jj_scanpos = var1;
                                                                                                                  if (this.jj_scan_token(54)) {
                                                                                                                     this.jj_scanpos = var1;
                                                                                                                     if (this.jj_scan_token(55)) {
                                                                                                                        this.jj_scanpos = var1;
                                                                                                                        if (this.jj_scan_token(56)) {
                                                                                                                           this.jj_scanpos = var1;
                                                                                                                           if (this.jj_scan_token(57)) {
                                                                                                                              this.jj_scanpos = var1;
                                                                                                                              if (this.jj_scan_token(58)) {
                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                 if (this.jj_scan_token(59)) {
                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                    if (this.jj_scan_token(60)) {
                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                       if (this.jj_scan_token(61)) {
                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                          if (this.jj_scan_token(62)) {
                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                             if (this.jj_scan_token(63)) {
                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                if (this.jj_scan_token(64)) {
                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                   if (this.jj_scan_token(65)) {
                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                      if (this.jj_scan_token(66)) {
                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                         if (this.jj_scan_token(67)) {
                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                            if (this.jj_scan_token(68)) {
                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                               if (this.jj_scan_token(69)) {
                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                  if (this.jj_scan_token(70)) {
                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                     if (this.jj_scan_token(71)) {
                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                        if (this.jj_scan_token(72)) {
                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                           if (this.jj_scan_token(73)) {
                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                              if (this.jj_scan_token(74)) {
                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                 if (this.jj_scan_token(75)) {
                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                    if (this.jj_scan_token(77)) {
                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                       if (this.jj_scan_token(78)) {
                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                          if (this.jj_scan_token(79)) {
                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                             if (this.jj_scan_token(81)) {
                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                if (this.jj_scan_token(82)) {
                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                   if (this.jj_scan_token(83)) {
                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                      if (this.jj_scan_token(84)) {
                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                         if (this.jj_scan_token(86)) {
                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                            if (this.jj_scan_token(87)) {
                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                               if (this.jj_scan_token(88)) {
                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                  if (this.jj_scan_token(89)) {
                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                     if (this.jj_scan_token(90)) {
                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                        if (this.jj_scan_token(91)) {
                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                           if (this.jj_scan_token(92)) {
                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                              if (this.jj_scan_token(93)) {
                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                 if (this.jj_scan_token(94)) {
                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                    if (this.jj_scan_token(95)) {
                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                       if (this.jj_scan_token(96)) {
                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                          if (this.jj_scan_token(97)) {
                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                             if (this.jj_scan_token(98)) {
                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                if (this.jj_scan_token(99)) {
                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                   if (this.jj_scan_token(100)) {
                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                      if (this.jj_scan_token(101)) {
                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                         if (this.jj_scan_token(102)) {
                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                            if (this.jj_scan_token(103)) {
                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                               if (this.jj_scan_token(104)) {
                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                  if (this.jj_scan_token(105)) {
                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                     if (this.jj_scan_token(106)) {
                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                        if (this.jj_scan_token(107)) {
                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                           if (this.jj_scan_token(108)) {
                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                              if (this.jj_scan_token(109)) {
                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(110)) {
                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(111)) {
                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(112)) {
                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(113)) {
                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(114)) {
                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(115)) {
                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(116)) {
                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(117)) {
                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(118)) {
                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(119)) {
                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(120)) {
                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(121)) {
                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(122)) {
                                                                                                                                                                                                                                                                                                                        return true;
                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                              }
                                                                                                                                                                                                                           }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                     }
                                                                                                                                                                                                                  }
                                                                                                                                                                                                               }
                                                                                                                                                                                                            }
                                                                                                                                                                                                         }
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }
                                                                                                                                                                                                }
                                                                                                                                                                                             }
                                                                                                                                                                                          }
                                                                                                                                                                                       }
                                                                                                                                                                                    }
                                                                                                                                                                                 }
                                                                                                                                                                              }
                                                                                                                                                                           }
                                                                                                                                                                        }
                                                                                                                                                                     }
                                                                                                                                                                  }
                                                                                                                                                               }
                                                                                                                                                            }
                                                                                                                                                         }
                                                                                                                                                      }
                                                                                                                                                   }
                                                                                                                                                }
                                                                                                                                             }
                                                                                                                                          }
                                                                                                                                       }
                                                                                                                                    }
                                                                                                                                 }
                                                                                                                              }
                                                                                                                           }
                                                                                                                        }
                                                                                                                     }
                                                                                                                  }
                                                                                                               }
                                                                                                            }
                                                                                                         }
                                                                                                      }
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                          }
                                                                                       }
                                                                                    }
                                                                                 }
                                                                              }
                                                                           }
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_83() {
      return this.jj_scan_token(60);
   }

   private final boolean jj_3R_70() {
      return this.jj_scan_token(33);
   }

   private final boolean jj_3R_96() {
      return this.jj_scan_token(100);
   }

   private final boolean jj_3R_76() {
      return this.jj_scan_token(46);
   }

   private final boolean jj_3R_68() {
      return this.jj_scan_token(31);
   }

   private final boolean jj_3R_85() {
      return this.jj_scan_token(66);
   }

   private final boolean jj_3R_116() {
      return this.jj_scan_token(131);
   }

   private final boolean jj_3R_61() {
      return this.jj_scan_token(21);
   }

   private final boolean jj_3R_97() {
      return this.jj_scan_token(101);
   }

   private final boolean jj_3R_86() {
      return this.jj_scan_token(70);
   }

   private final boolean jj_3R_115() {
      return this.jj_3R_117();
   }

   private final boolean jj_3R_114() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_115()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_116()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_75() {
      return this.jj_scan_token(45);
   }

   private final boolean jj_3R_79() {
      return this.jj_scan_token(54);
   }

   private final boolean jj_3R_111() {
      return this.jj_3R_114();
   }

   private final boolean jj_3R_63() {
      return this.jj_scan_token(22);
   }

   private final boolean jj_3R_110() {
      return false;
   }

   private final boolean jj_3R_56() {
      return this.jj_3R_109();
   }

   private final boolean jj_3R_95() {
      return this.jj_scan_token(99);
   }

   private final boolean jj_3R_55() {
      return this.jj_3R_108();
   }

   private final boolean jj_3R_54() {
      return this.jj_3R_107();
   }

   private final boolean jj_3R_90() {
      return this.jj_scan_token(78);
   }

   private final boolean jj_3R_53() {
      return this.jj_3R_106();
   }

   private final boolean jj_3R_52() {
      return this.jj_3R_105();
   }

   private final boolean jj_3R_60() {
      return this.jj_scan_token(15);
   }

   private final boolean jj_3R_51() {
      return this.jj_3R_104();
   }

   private final boolean jj_3R_50() {
      return this.jj_3R_103();
   }

   private final boolean jj_3R_92() {
      return this.jj_scan_token(86);
   }

   private final boolean jj_3R_49() {
      return this.jj_3R_102();
   }

   private final boolean jj_3R_48() {
      return this.jj_3R_101();
   }

   private final boolean jj_3R_57() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 37;
      this.lookingAhead = false;
      if (this.jj_semLA && !this.jj_3R_110()) {
         return this.jj_3R_111();
      } else {
         return true;
      }
   }

   private final boolean jj_3R_104() {
      return this.jj_scan_token(113);
   }

   private final boolean jj_3R_47() {
      return this.jj_3R_100();
   }

   private final boolean jj_3R_46() {
      return this.jj_3R_99();
   }

   private final boolean jj_3R_45() {
      return this.jj_3R_98();
   }

   private final boolean jj_3R_44() {
      return this.jj_3R_97();
   }

   private final boolean jj_3R_43() {
      return this.jj_3R_96();
   }

   private final boolean jj_3R_42() {
      return this.jj_3R_95();
   }

   private final boolean jj_3R_41() {
      return this.jj_3R_94();
   }

   private final boolean jj_3R_40() {
      return this.jj_3R_93();
   }

   private final boolean jj_3R_39() {
      return this.jj_3R_92();
   }

   private final boolean jj_3R_38() {
      return this.jj_3R_91();
   }

   private final boolean jj_3R_37() {
      return this.jj_3R_90();
   }

   private final boolean jj_3R_82() {
      return this.jj_scan_token(58);
   }

   private final boolean jj_3R_36() {
      return this.jj_3R_89();
   }

   private final boolean jj_3R_35() {
      return this.jj_3R_88();
   }

   private final boolean jj_3_2() {
      return this.jj_3R_57();
   }

   private final boolean jj_3R_34() {
      return this.jj_3R_87();
   }

   private final boolean jj_3R_33() {
      return this.jj_3R_86();
   }

   private final boolean jj_3R_32() {
      return this.jj_3R_85();
   }

   private final boolean jj_3R_109() {
      return this.jj_scan_token(121);
   }

   private final boolean jj_3R_89() {
      return this.jj_scan_token(74);
   }

   private final boolean jj_3R_84() {
      return this.jj_scan_token(65);
   }

   private final boolean jj_3R_31() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_81() {
      return this.jj_scan_token(56);
   }

   private final boolean jj_3R_30() {
      return this.jj_3R_83();
   }

   private final boolean jj_3R_94() {
      return this.jj_scan_token(98);
   }

   private final boolean jj_3R_29() {
      return this.jj_3R_82();
   }

   private final boolean jj_3R_103() {
      return this.jj_scan_token(112);
   }

   private final boolean jj_3R_28() {
      return this.jj_3R_81();
   }

   private final boolean jj_3R_27() {
      return this.jj_3R_80();
   }

   private final boolean jj_3R_26() {
      return this.jj_3R_79();
   }

   private final boolean jj_3R_24() {
      return this.jj_3R_77();
   }

   private final boolean jj_3R_23() {
      return this.jj_3R_76();
   }

   private final boolean jj_3R_22() {
      return this.jj_3R_75();
   }

   private final boolean jj_3R_91() {
      return this.jj_scan_token(84);
   }

   private final boolean jj_3R_25() {
      return this.jj_3R_78();
   }

   private final boolean jj_3R_21() {
      return this.jj_3R_74();
   }

   private final boolean jj_3R_20() {
      return this.jj_3R_73();
   }

   private final boolean jj_3R_73() {
      return this.jj_scan_token(41);
   }

   private final boolean jj_3R_19() {
      return this.jj_3R_72();
   }

   private final boolean jj_3R_18() {
      return this.jj_3R_71();
   }

   private final boolean jj_3R_17() {
      return this.jj_3R_70();
   }

   private final boolean jj_3R_16() {
      return this.jj_3R_69();
   }

   private final boolean jj_3R_15() {
      return this.jj_3R_68();
   }

   private final boolean jj_3R_113() {
      return this.jj_scan_token(71);
   }

   private final boolean jj_3R_14() {
      return this.jj_3R_67();
   }

   private final boolean jj_3R_13() {
      return this.jj_3R_66();
   }

   private final boolean jj_3R_12() {
      return this.jj_3R_65();
   }

   private final boolean jj_3R_11() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_112() {
      return this.jj_scan_token(39);
   }

   private final boolean jj_3R_108() {
      return this.jj_scan_token(120);
   }

   private final boolean jj_3R_102() {
      return this.jj_scan_token(110);
   }

   private final boolean jj_3R_74() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_112()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_113()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_10() {
      return this.jj_3R_63();
   }

   private final boolean jj_3R_9() {
      return this.jj_3R_62();
   }

   private final boolean jj_3R_8() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_7() {
      return this.jj_3R_60();
   }

   private final boolean jj_3R_6() {
      return this.jj_3R_59();
   }

   private final boolean jj_3R_71() {
      return this.jj_scan_token(34);
   }

   private final boolean jj_3R_64() {
      return this.jj_scan_token(23);
   }

   private final boolean jj_3R_93() {
      return this.jj_scan_token(94);
   }

   private final boolean jj_3R_5() {
      return this.jj_3R_58();
   }

   private final boolean jj_3_1() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 77 && this.getToken(3).kind != 79 && this.getToken(3).kind != 80;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_5()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_6()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_7()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_8()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_9()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3R_10()) {
                        this.jj_scanpos = var1;
                        if (this.jj_3R_11()) {
                           this.jj_scanpos = var1;
                           if (this.jj_3R_12()) {
                              this.jj_scanpos = var1;
                              if (this.jj_3R_13()) {
                                 this.jj_scanpos = var1;
                                 if (this.jj_3R_14()) {
                                    this.jj_scanpos = var1;
                                    if (this.jj_3R_15()) {
                                       this.jj_scanpos = var1;
                                       if (this.jj_3R_16()) {
                                          this.jj_scanpos = var1;
                                          if (this.jj_3R_17()) {
                                             this.jj_scanpos = var1;
                                             if (this.jj_3R_18()) {
                                                this.jj_scanpos = var1;
                                                if (this.jj_3R_19()) {
                                                   this.jj_scanpos = var1;
                                                   if (this.jj_3R_20()) {
                                                      this.jj_scanpos = var1;
                                                      if (this.jj_3R_21()) {
                                                         this.jj_scanpos = var1;
                                                         if (this.jj_3R_22()) {
                                                            this.jj_scanpos = var1;
                                                            if (this.jj_3R_23()) {
                                                               this.jj_scanpos = var1;
                                                               if (this.jj_3R_24()) {
                                                                  this.jj_scanpos = var1;
                                                                  if (this.jj_3R_25()) {
                                                                     this.jj_scanpos = var1;
                                                                     if (this.jj_3R_26()) {
                                                                        this.jj_scanpos = var1;
                                                                        if (this.jj_3R_27()) {
                                                                           this.jj_scanpos = var1;
                                                                           if (this.jj_3R_28()) {
                                                                              this.jj_scanpos = var1;
                                                                              if (this.jj_3R_29()) {
                                                                                 this.jj_scanpos = var1;
                                                                                 if (this.jj_3R_30()) {
                                                                                    this.jj_scanpos = var1;
                                                                                    if (this.jj_3R_31()) {
                                                                                       this.jj_scanpos = var1;
                                                                                       if (this.jj_3R_32()) {
                                                                                          this.jj_scanpos = var1;
                                                                                          if (this.jj_3R_33()) {
                                                                                             this.jj_scanpos = var1;
                                                                                             if (this.jj_3R_34()) {
                                                                                                this.jj_scanpos = var1;
                                                                                                if (this.jj_3R_35()) {
                                                                                                   this.jj_scanpos = var1;
                                                                                                   if (this.jj_3R_36()) {
                                                                                                      this.jj_scanpos = var1;
                                                                                                      if (this.jj_3R_37()) {
                                                                                                         this.jj_scanpos = var1;
                                                                                                         if (this.jj_3R_38()) {
                                                                                                            this.jj_scanpos = var1;
                                                                                                            if (this.jj_3R_39()) {
                                                                                                               this.jj_scanpos = var1;
                                                                                                               if (this.jj_3R_40()) {
                                                                                                                  this.jj_scanpos = var1;
                                                                                                                  if (this.jj_3R_41()) {
                                                                                                                     this.jj_scanpos = var1;
                                                                                                                     if (this.jj_3R_42()) {
                                                                                                                        this.jj_scanpos = var1;
                                                                                                                        if (this.jj_3R_43()) {
                                                                                                                           this.jj_scanpos = var1;
                                                                                                                           if (this.jj_3R_44()) {
                                                                                                                              this.jj_scanpos = var1;
                                                                                                                              if (this.jj_3R_45()) {
                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                 if (this.jj_3R_46()) {
                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                    if (this.jj_3R_47()) {
                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                       if (this.jj_3R_48()) {
                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                          if (this.jj_3R_49()) {
                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                             if (this.jj_3R_50()) {
                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                if (this.jj_3R_51()) {
                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                   if (this.jj_3R_52()) {
                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                      if (this.jj_3R_53()) {
                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                         if (this.jj_3R_54()) {
                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                            if (this.jj_3R_55()) {
                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                               if (this.jj_3R_56()) {
                                                                                                                                                                  return true;
                                                                                                                                                               }
                                                                                                                                                            }
                                                                                                                                                         }
                                                                                                                                                      }
                                                                                                                                                   }
                                                                                                                                                }
                                                                                                                                             }
                                                                                                                                          }
                                                                                                                                       }
                                                                                                                                    }
                                                                                                                                 }
                                                                                                                              }
                                                                                                                           }
                                                                                                                        }
                                                                                                                     }
                                                                                                                  }
                                                                                                               }
                                                                                                            }
                                                                                                         }
                                                                                                      }
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                          }
                                                                                       }
                                                                                    }
                                                                                 }
                                                                              }
                                                                           }
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_101() {
      return this.jj_scan_token(109);
   }

   private final boolean jj_3R_107() {
      return this.jj_scan_token(119);
   }

   private final boolean jj_3R_88() {
      return this.jj_scan_token(73);
   }

   private final boolean jj_3R_87() {
      return this.jj_scan_token(72);
   }

   private final boolean jj_3R_69() {
      return this.jj_scan_token(32);
   }

   private final boolean jj_3R_62() {
      return this.jj_scan_token(19);
   }

   private final boolean jj_3R_72() {
      return this.jj_scan_token(38);
   }

   private final boolean jj_3R_59() {
      return this.jj_scan_token(14);
   }

   private final boolean jj_3R_98() {
      return this.jj_scan_token(106);
   }

   private final boolean jj_3R_106() {
      return this.jj_scan_token(118);
   }

   private final boolean jj_3R_80() {
      return this.jj_scan_token(55);
   }

   private final boolean jj_3R_58() {
      return this.jj_scan_token(77);
   }

   private final boolean jj_3R_100() {
      return this.jj_scan_token(105);
   }

   private final boolean jj_3R_67() {
      return this.jj_scan_token(26);
   }

   private static void jj_la1_0() {
      jj_la1_0 = new int[]{-2014789632, 262144, 0, 0, 0, 0, 0, 1048576, 262144, 0, -16384, 262144, 65536, 0, 0, 0, 0, 0, 0, 268500992, 0, 0, 537001984, 537001984, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -16384, 0, 0, 0, 0, 0, 0, 0, 0, 0, 262144, 0, 0, 262144, 0, 0, 0, 0, 262144, 0, 0, 0, 262144, 262144, 0, 0, -16384, -16384};
   }

   private static void jj_la1_1() {
      jj_la1_1 = new int[]{365126343, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, -69673, 0, 0, 262144, 0, 264192, 264192, 262144, 262144, 528392, 0, 0, 0, 0, 128, 0, 0, 0, 1048576, 134250496, 0, 0, 0, 0, 0, 1610612736, 1610612736, 1610612736, 1610612736, 0, 0, -69673, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, 0, -69673, -69673};
   }

   private static void jj_la1_2() {
      jj_la1_2 = new int[]{1079003078, 0, 0, 32768, 64, 134217728, 0, 0, 0, 1, -2166785, 0, 637534208, 0, 1, 0, 0, 0, 0, 639766544, 0, 0, 0, 0, 128, 8, 262144, Integer.MIN_VALUE, 524288, 0, 0, 0, 268435456, 16777224, 2048, 0, 0, 0, 0, 0, 0, -2166785, 0, 1, 0, 0, 0, 8388608, 8388608, 134217728, 0, 0, 0, 0, 0, 0, 64, 134217728, 0, 0, 64, 134217728, 0, 0, 0, 0, 0, -2166785, -2166785};
   }

   private static void jj_la1_3() {
      jj_la1_3 = new int[]{65234556, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217727, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 134217727, 0, 0, 268435456, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 1874304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 268435456, 134217727, 134217727};
   }

   private static void jj_la1_4() {
      jj_la1_4 = new int[]{0, 0, 136, 136, 0, 0, 0, 0, 0, 0, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 8, 0, 0, 0, 0, 136, 136, 0, 136, 0, 0, 0, 0, 0, 128, 0, 8, 6, 0, 0, 128, 6, 8, 8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 8, 0};
   }

   public ij(CharStream var1) {
      this.elapsedTime = false;
      this.theConnection = null;
      this.currentConnEnv = null;
      this.urlCheck = null;
      this.xahelper = null;
      this.exit = false;
      this.utilInstance = null;
      this.ignoreErrors = null;
      this.protocol = null;
      this.lookingAhead = false;
      this.jj_la1 = new int[69];
      this.jj_2_rtns = new JJCalls[2];
      this.jj_rescan = false;
      this.jj_gc = 0;
      this.jj_ls = new LookaheadSuccess();
      this.jj_expentries = new Vector();
      this.jj_kind = -1;
      this.jj_lasttokens = new int[100];
      this.token_source = new ijTokenManager(var1);
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 69; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   public void ReInit(CharStream var1) {
      this.token_source.ReInit(var1);
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 69; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   public ij(ijTokenManager var1) {
      this.elapsedTime = false;
      this.theConnection = null;
      this.currentConnEnv = null;
      this.urlCheck = null;
      this.xahelper = null;
      this.exit = false;
      this.utilInstance = null;
      this.ignoreErrors = null;
      this.protocol = null;
      this.lookingAhead = false;
      this.jj_la1 = new int[69];
      this.jj_2_rtns = new JJCalls[2];
      this.jj_rescan = false;
      this.jj_gc = 0;
      this.jj_ls = new LookaheadSuccess();
      this.jj_expentries = new Vector();
      this.jj_kind = -1;
      this.jj_lasttokens = new int[100];
      this.token_source = var1;
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 69; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   public void ReInit(ijTokenManager var1) {
      this.token_source = var1;
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 69; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   private final Token jj_consume_token(int var1) throws ParseException {
      Token var2 = this.token;
      if ((this.token = this.jj_nt).next != null) {
         this.jj_nt = this.jj_nt.next;
      } else {
         this.jj_nt = this.jj_nt.next = this.token_source.getNextToken();
      }

      if (this.token.kind != var1) {
         this.jj_nt = this.token;
         this.token = var2;
         this.jj_kind = var1;
         throw this.generateParseException();
      } else {
         ++this.jj_gen;
         if (++this.jj_gc > 100) {
            this.jj_gc = 0;

            for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
               for(JJCalls var4 = this.jj_2_rtns[var3]; var4 != null; var4 = var4.next) {
                  if (var4.gen < this.jj_gen) {
                     var4.first = null;
                  }
               }
            }
         }

         return this.token;
      }
   }

   private final boolean jj_scan_token(int var1) {
      if (this.jj_scanpos == this.jj_lastpos) {
         --this.jj_la;
         if (this.jj_scanpos.next == null) {
            this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken();
         } else {
            this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next;
         }
      } else {
         this.jj_scanpos = this.jj_scanpos.next;
      }

      if (this.jj_rescan) {
         int var2 = 0;

         Token var3;
         for(var3 = this.token; var3 != null && var3 != this.jj_scanpos; var3 = var3.next) {
            ++var2;
         }

         if (var3 != null) {
            this.jj_add_error_token(var1, var2);
         }
      }

      if (this.jj_scanpos.kind != var1) {
         return true;
      } else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
         throw this.jj_ls;
      } else {
         return false;
      }
   }

   public final Token getNextToken() {
      if ((this.token = this.jj_nt).next != null) {
         this.jj_nt = this.jj_nt.next;
      } else {
         this.jj_nt = this.jj_nt.next = this.token_source.getNextToken();
      }

      ++this.jj_gen;
      return this.token;
   }

   public final Token getToken(int var1) {
      Token var2 = this.lookingAhead ? this.jj_scanpos : this.token;

      for(int var3 = 0; var3 < var1; ++var3) {
         if (var2.next != null) {
            var2 = var2.next;
         } else {
            var2 = var2.next = this.token_source.getNextToken();
         }
      }

      return var2;
   }

   private void jj_add_error_token(int var1, int var2) {
      if (var2 < 100) {
         if (var2 == this.jj_endpos + 1) {
            this.jj_lasttokens[this.jj_endpos++] = var1;
         } else if (this.jj_endpos != 0) {
            this.jj_expentry = new int[this.jj_endpos];

            for(int var3 = 0; var3 < this.jj_endpos; ++var3) {
               this.jj_expentry[var3] = this.jj_lasttokens[var3];
            }

            boolean var7 = false;
            Enumeration var4 = this.jj_expentries.elements();

            while(var4.hasMoreElements()) {
               int[] var5 = (int[])var4.nextElement();
               if (var5.length == this.jj_expentry.length) {
                  var7 = true;

                  for(int var6 = 0; var6 < this.jj_expentry.length; ++var6) {
                     if (var5[var6] != this.jj_expentry[var6]) {
                        var7 = false;
                        break;
                     }
                  }

                  if (var7) {
                     break;
                  }
               }
            }

            if (!var7) {
               this.jj_expentries.addElement(this.jj_expentry);
            }

            if (var2 != 0) {
               this.jj_lasttokens[(this.jj_endpos = var2) - 1] = var1;
            }
         }

      }
   }

   public ParseException generateParseException() {
      this.jj_expentries.removeAllElements();
      boolean[] var1 = new boolean[136];

      for(int var2 = 0; var2 < 136; ++var2) {
         var1[var2] = false;
      }

      if (this.jj_kind >= 0) {
         var1[this.jj_kind] = true;
         this.jj_kind = -1;
      }

      for(int var4 = 0; var4 < 69; ++var4) {
         if (this.jj_la1[var4] == this.jj_gen) {
            for(int var3 = 0; var3 < 32; ++var3) {
               if ((jj_la1_0[var4] & 1 << var3) != 0) {
                  var1[var3] = true;
               }

               if ((jj_la1_1[var4] & 1 << var3) != 0) {
                  var1[32 + var3] = true;
               }

               if ((jj_la1_2[var4] & 1 << var3) != 0) {
                  var1[64 + var3] = true;
               }

               if ((jj_la1_3[var4] & 1 << var3) != 0) {
                  var1[96 + var3] = true;
               }

               if ((jj_la1_4[var4] & 1 << var3) != 0) {
                  var1[128 + var3] = true;
               }
            }
         }
      }

      for(int var5 = 0; var5 < 136; ++var5) {
         if (var1[var5]) {
            this.jj_expentry = new int[1];
            this.jj_expentry[0] = var5;
            this.jj_expentries.addElement(this.jj_expentry);
         }
      }

      this.jj_endpos = 0;
      this.jj_rescan_token();
      this.jj_add_error_token(0, 0);
      int[][] var6 = new int[this.jj_expentries.size()][];

      for(int var7 = 0; var7 < this.jj_expentries.size(); ++var7) {
         var6[var7] = (int[])this.jj_expentries.elementAt(var7);
      }

      return new ParseException(this.token, var6, ijConstants.tokenImage);
   }

   public final void enable_tracing() {
   }

   public final void disable_tracing() {
   }

   private final void jj_rescan_token() {
      this.jj_rescan = true;

      for(int var1 = 0; var1 < 2; ++var1) {
         try {
            JJCalls var2 = this.jj_2_rtns[var1];

            while(true) {
               if (var2.gen > this.jj_gen) {
                  this.jj_la = var2.arg;
                  this.jj_lastpos = this.jj_scanpos = var2.first;
                  switch (var1) {
                     case 0 -> this.jj_3_1();
                     case 1 -> this.jj_3_2();
                  }
               }

               var2 = var2.next;
               if (var2 == null) {
                  break;
               }
            }
         } catch (LookaheadSuccess var3) {
         }
      }

      this.jj_rescan = false;
   }

   private final void jj_save(int var1, int var2) {
      JJCalls var3;
      for(var3 = this.jj_2_rtns[var1]; var3.gen > this.jj_gen; var3 = var3.next) {
         if (var3.next == null) {
            var3 = var3.next = new JJCalls();
            break;
         }
      }

      var3.gen = this.jj_gen + var2 - this.jj_la;
      var3.first = this.token;
      var3.arg = var2;
   }

   static {
      jj_la1_0();
      jj_la1_1();
      jj_la1_2();
      jj_la1_3();
      jj_la1_4();
   }

   private static final class LookaheadSuccess extends Error {
   }

   static final class JJCalls {
      int gen;
      Token first;
      int arg;
      JJCalls next;
   }
}
