package org.apache.derby.impl.tools.ij;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;
import org.apache.derby.tools.JDBCDisplayUtil;

class ConnectionEnv {
   Hashtable sessions = new Hashtable();
   private Session currSession;
   private String tag;
   private boolean only;
   private static final String CONNECTION_PROPERTY = "ij.connection";
   private String protocol;

   ConnectionEnv(int var1, boolean var2, boolean var3) {
      if (var2) {
         this.tag = "(" + (var1 + 1) + ")";
      }

      this.only = var3;
   }

   void init(LocalizedOutput var1) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      Connection var2 = util.startJBMS((String)null, (String)null);
      if (this.only) {
         Properties var3 = System.getProperties();
         this.protocol = var3.getProperty("ij.protocol");
         String var4 = "ij.connection.";
         Enumeration var5 = var3.propertyNames();

         while(var5.hasMoreElements()) {
            String var6 = (String)var5.nextElement();
            if (var6.startsWith(var4)) {
               String var7 = var6.substring(var4.length());
               this.installConnection(var7.toUpperCase(Locale.ENGLISH), var3.getProperty(var6), var1);
            }
         }
      }

      if (var2 != null) {
         String var8 = "CONNECTION" + this.sessions.size();
         Session var9 = new Session(var2, this.tag, var8);
         this.sessions.put(var8, var9);
         this.currSession = var9;
      }

   }

   void doPrompt(boolean var1, LocalizedOutput var2) {
      if (this.currSession != null) {
         this.currSession.doPrompt(var1, var2, this.sessions.size() > 1);
      } else {
         utilMain.doPrompt(var1, var2, this.tag);
      }

   }

   Connection getConnection() {
      return this.currSession == null ? null : this.currSession.getConnection();
   }

   void addSession(Connection var1, String var2) {
      String var3;
      if (var2 == null) {
         var3 = this.getUniqueConnectionName();
      } else {
         var3 = var2;
      }

      Session var4 = new Session(var1, this.tag, var3);
      this.sessions.put(var3, var4);
      this.currSession = var4;
   }

   public String getUniqueConnectionName() {
      int var1 = 0;
      boolean var2 = false;

      String var3;
      for(var3 = ""; !var2; ++var1) {
         var3 = "CONNECTION" + var1;
         var2 = true;
         Enumeration var4 = this.sessions.keys();

         while(var4.hasMoreElements() && var2) {
            if (((String)var4.nextElement()).equals(var3)) {
               var2 = false;
            }
         }
      }

      return var3;
   }

   Session getSession() {
      return this.currSession;
   }

   Hashtable getSessions() {
      return this.sessions;
   }

   Session getSession(String var1) {
      return (Session)this.sessions.get(var1);
   }

   Session setCurrentSession(String var1) {
      this.currSession = (Session)this.sessions.get(var1);
      return this.currSession;
   }

   boolean haveSession(String var1) {
      return var1 != null && this.sessions.size() > 0 && null != this.sessions.get(var1);
   }

   void removeCurrentSession() throws SQLException {
      if (this.currSession != null) {
         this.sessions.remove(this.currSession.getName());
         this.currSession.close();
         this.currSession = null;
      }
   }

   void removeSession(String var1) throws SQLException {
      Session var2 = (Session)this.sessions.remove(var1);
      var2.close();
      if (this.currSession == var2) {
         this.currSession = null;
      }

   }

   void removeAllSessions() throws SQLException {
      if (this.sessions != null && this.sessions.size() != 0) {
         Enumeration var1 = this.sessions.keys();

         while(var1.hasMoreElements()) {
            String var2 = (String)var1.nextElement();
            this.removeSession(var2);
         }

      }
   }

   private void installConnection(String var1, String var2, LocalizedOutput var3) throws SQLException {
      boolean var4 = false;

      try {
         try {
            if (var2.startsWith("jdbc:")) {
               util.loadDriverIfKnown(var2);
            }
         } catch (Exception var10) {
         }

         DriverManager.getDriver(var2);
      } catch (SQLException var11) {
         var4 = true;
      }

      if (var4 && this.protocol != null) {
         var2 = this.protocol + var2;
      }

      if (this.sessions.get(var1) != null) {
         throw ijException.alreadyHaveConnectionNamed(var1);
      } else {
         try {
            String var5 = util.getSystemProperty("ij.user");
            String var6 = util.getSystemProperty("ij.password");
            Properties var7 = util.updateConnInfo(var5, var6, (Properties)null);
            Connection var8 = DriverManager.getConnection(var2, var7);
            this.addSession(var8, var1);
         } catch (Throwable var9) {
            JDBCDisplayUtil.ShowException((PrintWriter)var3, var9);
         }

      }
   }
}
