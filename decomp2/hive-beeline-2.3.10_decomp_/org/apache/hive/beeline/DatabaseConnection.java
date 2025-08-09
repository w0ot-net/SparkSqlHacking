package org.apache.hive.beeline;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import org.apache.hive.jdbc.HiveConnection;

class DatabaseConnection {
   private static final String HIVE_VAR_PREFIX = "hivevar:";
   private static final String HIVE_CONF_PREFIX = "hiveconf:";
   private final BeeLine beeLine;
   private Connection connection;
   private DatabaseMetaData meta;
   private final String driver;
   private final String url;
   private final Properties info;
   private Schema schema = null;
   private Completer sqlCompleter = null;

   public boolean isClosed() {
      return null == this.connection;
   }

   public DatabaseConnection(BeeLine beeLine, String driver, String url, Properties info) throws SQLException {
      this.beeLine = beeLine;
      this.driver = driver;
      this.url = url;
      this.info = info;
   }

   public String toString() {
      return this.getUrl() + "";
   }

   void setCompletions(boolean skipmeta) throws SQLException, IOException {
      final String extraNameCharacters = this.getDatabaseMetaData() != null && this.getDatabaseMetaData().getExtraNameCharacters() != null ? this.getDatabaseMetaData().getExtraNameCharacters() : "";
      this.sqlCompleter = new ArgumentCompleter(new ArgumentCompleter.AbstractArgumentDelimiter() {
         public boolean isDelimiterChar(CharSequence buffer, int pos) {
            char c = buffer.charAt(pos);
            if (Character.isWhitespace(c)) {
               return true;
            } else {
               return !Character.isLetterOrDigit(c) && c != '_' && extraNameCharacters.indexOf(c) == -1;
            }
         }
      }, new Completer[]{new SQLCompleter(SQLCompleter.getSQLCompleters(this.beeLine, skipmeta))});
      ((ArgumentCompleter)this.sqlCompleter).setStrict(false);
   }

   boolean connect() throws SQLException {
      try {
         if (this.driver != null && this.driver.length() != 0) {
            Class.forName(this.driver);
         }
      } catch (ClassNotFoundException cnfe) {
         return this.beeLine.error((Throwable)cnfe);
      }

      boolean isDriverRegistered = false;

      try {
         isDriverRegistered = DriverManager.getDriver(this.getUrl()) != null;
      } catch (Exception var11) {
      }

      try {
         this.close();
      } catch (Exception e) {
         return this.beeLine.error((Throwable)e);
      }

      Map<String, String> hiveVars = this.beeLine.getOpts().getHiveVariables();
      if (hiveVars != null) {
         for(Map.Entry var : hiveVars.entrySet()) {
            this.info.put("hivevar:" + (String)var.getKey(), var.getValue());
         }
      }

      Map<String, String> hiveConfVars = this.beeLine.getOpts().getHiveConfVariables();
      if (hiveConfVars != null) {
         for(Map.Entry var : hiveConfVars.entrySet()) {
            this.info.put("hiveconf:" + (String)var.getKey(), var.getValue());
         }
      }

      if (isDriverRegistered) {
         this.setConnection(DriverManager.getConnection(this.getUrl(), this.info));
      } else {
         this.beeLine.debug("Use the driver from local added jar file.");
         this.setConnection(this.getConnectionFromLocalDriver(this.getUrl(), this.info));
      }

      this.setDatabaseMetaData(this.getConnection().getMetaData());

      try {
         this.beeLine.info(this.beeLine.loc("connected", new Object[]{this.getDatabaseMetaData().getDatabaseProductName(), this.getDatabaseMetaData().getDatabaseProductVersion()}));
      } catch (Exception e) {
         this.beeLine.handleException(e);
      }

      try {
         this.beeLine.info(this.beeLine.loc("driver", new Object[]{this.getDatabaseMetaData().getDriverName(), this.getDatabaseMetaData().getDriverVersion()}));
      } catch (Exception e) {
         this.beeLine.handleException(e);
      }

      try {
         this.getConnection().setAutoCommit(this.beeLine.getOpts().getAutoCommit());
      } catch (Exception e) {
         this.beeLine.handleException(e);
      }

      try {
         this.beeLine.getCommands().isolation("isolation: " + this.beeLine.getOpts().getIsolation());
      } catch (Exception e) {
         this.beeLine.handleException(e);
      }

      return true;
   }

   public Connection getConnectionFromLocalDriver(String url, Properties properties) {
      for(Driver d : this.beeLine.getDrivers()) {
         try {
            if (d.acceptsURL(url) && this.beeLine.isSupportedLocalDriver(d)) {
               String clazzName = d.getClass().getName();
               this.beeLine.debug("Driver name is " + clazzName);
               Driver driver = (Driver)Class.forName(clazzName, true, Thread.currentThread().getContextClassLoader()).newInstance();
               return driver.connect(url, properties);
            }
         } catch (Exception e) {
            this.beeLine.error("Fail to connect with a local driver due to the exception:" + e);
            this.beeLine.error((Throwable)e);
         }
      }

      return null;
   }

   public Connection getConnection() throws SQLException {
      if (this.connection != null) {
         return this.connection;
      } else {
         this.connect();
         return this.connection;
      }
   }

   public Connection getCurrentConnection() {
      return this.connection;
   }

   public void reconnect() throws Exception {
      this.close();
      this.getConnection();
   }

   public void close() {
      try {
         if (this.connection != null && !this.connection.isClosed()) {
            this.beeLine.output(this.beeLine.loc("closing", (Object)this.connection));
            this.connection.close();
         }
      } catch (Exception e) {
         this.beeLine.handleException(e);
      } finally {
         this.setConnection((Connection)null);
         this.setDatabaseMetaData((DatabaseMetaData)null);
      }

   }

   public String[] getTableNames(boolean force) {
      Schema.Table[] t = this.getSchema().getTables();
      Set<String> names = new TreeSet();

      for(int i = 0; t != null && i < t.length; ++i) {
         names.add(t[i].getName());
      }

      return (String[])names.toArray(new String[names.size()]);
   }

   Schema getSchema() {
      if (this.schema == null) {
         this.schema = new Schema();
      }

      return this.schema;
   }

   void setConnection(Connection connection) {
      this.connection = connection;
   }

   DatabaseMetaData getDatabaseMetaData() {
      return this.meta;
   }

   void setDatabaseMetaData(DatabaseMetaData meta) {
      this.meta = meta;
   }

   String getUrl() {
      return this.url;
   }

   public String getConnectedUrl() {
      return this.connection instanceof HiveConnection ? ((HiveConnection)this.connection).getConnectedUrl() : this.getUrl();
   }

   Completer getSQLCompleter() {
      return this.sqlCompleter;
   }

   class Schema {
      private Table[] tables = null;

      Table[] getTables() {
         if (this.tables != null) {
            return this.tables;
         } else {
            List<Table> tnames = new LinkedList();

            try {
               ResultSet rs = DatabaseConnection.this.getDatabaseMetaData().getTables(DatabaseConnection.this.getConnection().getCatalog(), (String)null, "%", new String[]{"TABLE"});

               try {
                  while(rs.next()) {
                     tnames.add(new Table(rs.getString("TABLE_NAME")));
                  }
               } finally {
                  try {
                     rs.close();
                  } catch (Exception var10) {
                  }

               }
            } catch (Throwable var12) {
            }

            return this.tables = (Table[])tnames.toArray(new Table[0]);
         }
      }

      Table getTable(String name) {
         Table[] t = this.getTables();

         for(int i = 0; t != null && i < t.length; ++i) {
            if (name.equalsIgnoreCase(t[i].getName())) {
               return t[i];
            }
         }

         return null;
      }

      class Table {
         final String name;
         Column[] columns;

         public Table(String name) {
            this.name = name;
         }

         public String getName() {
            return this.name;
         }

         class Column {
            final String name;
            boolean isPrimaryKey;

            public Column(String name) {
               this.name = name;
            }
         }
      }
   }
}
