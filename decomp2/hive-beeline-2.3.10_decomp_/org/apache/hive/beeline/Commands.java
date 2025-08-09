package org.apache.hive.beeline;

import com.google.common.annotations.VisibleForTesting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.apache.hadoop.hive.common.cli.ShellCmdExecutor;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveVariableSource;
import org.apache.hadoop.hive.conf.VariableSubstitution;
import org.apache.hadoop.io.IOUtils;
import org.apache.hive.beeline.logs.BeelineInPlaceUpdateStream;
import org.apache.hive.jdbc.HiveStatement;
import org.apache.hive.jdbc.Utils;
import org.apache.hive.jdbc.logs.InPlaceUpdateStream;

public class Commands {
   private final BeeLine beeLine;
   private static final int DEFAULT_QUERY_PROGRESS_INTERVAL = 1000;
   private static final int DEFAULT_QUERY_PROGRESS_THREAD_TIMEOUT = 10000;

   Commands(BeeLine beeLine) {
      this.beeLine = beeLine;
   }

   public boolean metadata(String line) {
      this.beeLine.debug(line);
      String[] parts = this.beeLine.split(line);
      List<String> params = new LinkedList(Arrays.asList(parts));
      if (parts != null && parts.length != 0) {
         params.remove(0);
         params.remove(0);
         this.beeLine.debug(params.toString());
         return this.metadata(parts[1], (String[])params.toArray(new String[0]));
      } else {
         return this.dbinfo("");
      }
   }

   public boolean metadata(String cmd, String[] args) {
      if (!this.beeLine.assertConnection()) {
         return false;
      } else {
         try {
            Method[] m = this.beeLine.getDatabaseMetaData().getClass().getMethods();
            Set<String> methodNames = new TreeSet();
            Set<String> methodNamesUpper = new TreeSet();

            for(int i = 0; i < m.length; ++i) {
               methodNames.add(m[i].getName());
               methodNamesUpper.add(m[i].getName().toUpperCase());
            }

            if (methodNamesUpper.contains(cmd.toUpperCase())) {
               Object res = this.beeLine.getReflector().invoke(this.beeLine.getDatabaseMetaData(), DatabaseMetaData.class, cmd, Arrays.asList(args));
               if (res instanceof ResultSet) {
                  ResultSet rs = (ResultSet)res;
                  if (rs != null) {
                     try {
                        this.beeLine.print(rs);
                     } finally {
                        rs.close();
                     }
                  }
               } else if (res != null) {
                  this.beeLine.output(res.toString());
               }

               return true;
            } else {
               this.beeLine.error(this.beeLine.loc("no-such-method", (Object)cmd));
               this.beeLine.error(this.beeLine.loc("possible-methods"));
               Iterator<String> i = methodNames.iterator();

               while(i.hasNext()) {
                  this.beeLine.error("   " + (String)i.next());
               }

               return false;
            }
         } catch (Exception e) {
            return this.beeLine.error((Throwable)e);
         }
      }
   }

   public boolean addlocaldrivername(String line) {
      String driverName = this.arg1(line, "driver class name");

      try {
         this.beeLine.setDrivers(Arrays.asList(this.beeLine.scanDrivers(false)));
      } catch (IOException e) {
         this.beeLine.error("Fail to scan drivers due to the exception:" + e);
         this.beeLine.error((Throwable)e);
      }

      for(Driver d : this.beeLine.getDrivers()) {
         if (driverName.equals(d.getClass().getName())) {
            this.beeLine.addLocalDriverClazz(driverName);
            return true;
         }
      }

      this.beeLine.error("Fail to find a driver which contains the driver class");
      return false;
   }

   public boolean addlocaldriverjar(String line) {
      String jarPath = this.arg1(line, "jar path");
      File p = new File(jarPath);
      if (!p.exists()) {
         this.beeLine.error("The jar file in the path " + jarPath + " can't be found!");
         return false;
      } else {
         ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

         try {
            this.beeLine.debug(jarPath + " is added to the local beeline.");
            URLClassLoader newClassLoader = new URLClassLoader(new URL[]{p.toURL()}, classLoader);
            Thread.currentThread().setContextClassLoader(newClassLoader);
            this.beeLine.setDrivers(Arrays.asList(this.beeLine.scanDrivers(false)));
         } catch (Exception e) {
            this.beeLine.error("Fail to add local jar due to the exception:" + e);
            this.beeLine.error((Throwable)e);
         }

         return true;
      }
   }

   public boolean history(String line) {
      Iterator hist = this.beeLine.getConsoleReader().getHistory().entries();

      while(hist.hasNext()) {
         String[] tmp = hist.next().toString().split(":", 2);
         tmp[0] = Integer.toString(Integer.parseInt(tmp[0]) + 1);
         this.beeLine.output(this.beeLine.getColorBuffer().pad((String)tmp[0], 6).append(":" + tmp[1]));
      }

      return true;
   }

   String arg1(String line, String paramname) {
      return this.arg1(line, paramname, (String)null);
   }

   String arg1(String line, String paramname, String def) {
      String[] ret = this.beeLine.split(line);
      if (ret != null && ret.length == 2) {
         return ret[1];
      } else if (def != null) {
         return def;
      } else {
         throw new IllegalArgumentException(this.beeLine.loc("arg-usage", new Object[]{ret.length == 0 ? "" : ret[0], paramname}));
      }
   }

   public boolean indexes(String line) throws Exception {
      return this.metadata("getIndexInfo", new String[]{this.beeLine.getConnection().getCatalog(), null, this.arg1(line, "table name"), "false", "true"});
   }

   public boolean primarykeys(String line) throws Exception {
      return this.metadata("getPrimaryKeys", new String[]{this.beeLine.getConnection().getCatalog(), null, this.arg1(line, "table name")});
   }

   public boolean exportedkeys(String line) throws Exception {
      return this.metadata("getExportedKeys", new String[]{this.beeLine.getConnection().getCatalog(), null, this.arg1(line, "table name")});
   }

   public boolean importedkeys(String line) throws Exception {
      return this.metadata("getImportedKeys", new String[]{this.beeLine.getConnection().getCatalog(), null, this.arg1(line, "table name")});
   }

   public boolean procedures(String line) throws Exception {
      return this.metadata("getProcedures", new String[]{this.beeLine.getConnection().getCatalog(), null, this.arg1(line, "procedure name pattern", "%")});
   }

   public boolean tables(String line) throws Exception {
      return this.metadata("getTables", new String[]{this.beeLine.getConnection().getCatalog(), null, this.arg1(line, "table name", "%"), null});
   }

   public boolean typeinfo(String line) throws Exception {
      return this.metadata("getTypeInfo", new String[0]);
   }

   public boolean nativesql(String sql) throws Exception {
      if (sql.startsWith("!")) {
         sql = sql.substring(1);
      }

      if (sql.startsWith("native")) {
         sql = sql.substring("native".length() + 1);
      }

      String nat = this.beeLine.getConnection().nativeSQL(sql);
      this.beeLine.output(nat);
      return true;
   }

   public boolean columns(String line) throws Exception {
      return this.metadata("getColumns", new String[]{this.beeLine.getConnection().getCatalog(), null, this.arg1(line, "table name"), "%"});
   }

   public boolean dropall(String line) {
      if (this.beeLine.getDatabaseConnection() != null && this.beeLine.getDatabaseConnection().getUrl() != null) {
         try {
            if (!this.beeLine.getConsoleReader().readLine(this.beeLine.loc("really-drop-all")).equals("y")) {
               return this.beeLine.error("abort-drop-all");
            } else {
               List<String> cmds = new LinkedList();
               ResultSet rs = this.beeLine.getTables();

               try {
                  while(rs.next()) {
                     cmds.add("DROP TABLE " + rs.getString("TABLE_NAME") + ";");
                  }
               } finally {
                  try {
                     rs.close();
                  } catch (Exception var11) {
                  }

               }

               return this.beeLine.runCommands(cmds) == cmds.size();
            }
         } catch (Exception e) {
            return this.beeLine.error((Throwable)e);
         }
      } else {
         return this.beeLine.error(this.beeLine.loc("no-current-connection"));
      }
   }

   public boolean reconnect(String line) {
      if (this.beeLine.getDatabaseConnection() != null && this.beeLine.getDatabaseConnection().getUrl() != null) {
         this.beeLine.info(this.beeLine.loc("reconnecting", (Object)this.beeLine.getDatabaseConnection().getUrl()));

         try {
            this.beeLine.getDatabaseConnection().reconnect();
            return true;
         } catch (Exception e) {
            return this.beeLine.error((Throwable)e);
         }
      } else {
         String lastConnectedUrl = this.beeLine.getOpts().getLastConnectedUrl();
         if (lastConnectedUrl != null) {
            Properties props = new Properties();
            props.setProperty("url", lastConnectedUrl);

            try {
               return this.connect(props);
            } catch (IOException e) {
               return this.beeLine.error((Throwable)e);
            }
         } else {
            return this.beeLine.error(this.beeLine.loc("no-current-connection"));
         }
      }
   }

   public boolean scan(String line) throws IOException {
      TreeSet<String> names = new TreeSet();
      if (this.beeLine.getDrivers() == null) {
         this.beeLine.setDrivers(Arrays.asList(this.beeLine.scanDrivers(line)));
      }

      this.beeLine.info(this.beeLine.loc("drivers-found-count", this.beeLine.getDrivers().size()));
      Iterator<Driver> i = this.beeLine.getDrivers().iterator();

      while(i.hasNext()) {
         names.add(((Driver)i.next()).getClass().getName());
      }

      this.beeLine.output(this.beeLine.getColorBuffer().bold(this.beeLine.getColorBuffer().pad((String)this.beeLine.loc("compliant"), 10).getMono()).bold(this.beeLine.getColorBuffer().pad((String)this.beeLine.loc("jdbc-version"), 8).getMono()).bold(this.beeLine.getColorBuffer(this.beeLine.loc("driver-class")).getMono()));
      i = names.iterator();

      while(i.hasNext()) {
         String name = ((String)i.next()).toString();

         try {
            Driver driver = (Driver)Class.forName(name).newInstance();
            ColorBuffer msg = this.beeLine.getColorBuffer().pad((String)(driver.jdbcCompliant() ? "yes" : "no"), 10).pad((String)(driver.getMajorVersion() + "." + driver.getMinorVersion()), 8).append(name);
            if (driver.jdbcCompliant()) {
               this.beeLine.output(msg);
            } else {
               this.beeLine.output(this.beeLine.getColorBuffer().red(msg.getMono()));
            }
         } catch (Throwable var7) {
            this.beeLine.output(this.beeLine.getColorBuffer().red(name));
         }
      }

      return true;
   }

   public boolean save(String line) throws IOException {
      this.beeLine.info(this.beeLine.loc("saving-options", (Object)this.beeLine.getOpts().getPropertiesFile()));
      this.beeLine.getOpts().save();
      return true;
   }

   public boolean load(String line) throws IOException {
      this.beeLine.getOpts().load();
      this.beeLine.info(this.beeLine.loc("loaded-options", (Object)this.beeLine.getOpts().getPropertiesFile()));
      return true;
   }

   public boolean config(String line) {
      try {
         Properties props = this.beeLine.getOpts().toProperties();

         for(String key : new TreeSet(props.keySet())) {
            BeeLine var10000 = this.beeLine;
            ColorBuffer var10001 = this.beeLine.getColorBuffer();
            ColorBuffer var10002 = this.beeLine.getColorBuffer();
            this.beeLine.getOpts();
            var10000.output(var10001.green(var10002.pad((String)key.substring("beeline.".length()), 20).getMono()).append(props.getProperty(key)));
         }

         return true;
      } catch (Exception e) {
         return this.beeLine.error((Throwable)e);
      }
   }

   public boolean set(String line) {
      if (line != null && !line.trim().equals("set") && line.length() != 0) {
         String[] parts = this.beeLine.split(line, 3, "Usage: set <key> <value>");
         if (parts == null) {
            return false;
         } else {
            String key = parts[1];
            String value = parts[2];
            boolean success = this.beeLine.getOpts().set(key, value, false);
            if (success && this.beeLine.getOpts().getAutosave()) {
               try {
                  this.beeLine.getOpts().save();
               } catch (Exception var7) {
               }
            }

            return success;
         }
      } else {
         return this.config((String)null);
      }
   }

   public boolean commit(String line) throws SQLException {
      if (!this.beeLine.assertConnection()) {
         return false;
      } else if (!this.beeLine.assertAutoCommit()) {
         return false;
      } else {
         try {
            long start = System.currentTimeMillis();
            this.beeLine.getDatabaseConnection().getConnection().commit();
            long end = System.currentTimeMillis();
            this.beeLine.showWarnings();
            this.beeLine.info(this.beeLine.loc("commit-complete") + " " + this.beeLine.locElapsedTime(end - start));
            return true;
         } catch (Exception e) {
            return this.beeLine.error((Throwable)e);
         }
      }
   }

   public boolean rollback(String line) throws SQLException {
      if (!this.beeLine.assertConnection()) {
         return false;
      } else if (!this.beeLine.assertAutoCommit()) {
         return false;
      } else {
         try {
            long start = System.currentTimeMillis();
            this.beeLine.getDatabaseConnection().getConnection().rollback();
            long end = System.currentTimeMillis();
            this.beeLine.showWarnings();
            this.beeLine.info(this.beeLine.loc("rollback-complete") + " " + this.beeLine.locElapsedTime(end - start));
            return true;
         } catch (Exception e) {
            return this.beeLine.error((Throwable)e);
         }
      }
   }

   public boolean autocommit(String line) throws SQLException {
      if (!this.beeLine.assertConnection()) {
         return false;
      } else {
         if (line.endsWith("on")) {
            this.beeLine.getDatabaseConnection().getConnection().setAutoCommit(true);
         } else if (line.endsWith("off")) {
            this.beeLine.getDatabaseConnection().getConnection().setAutoCommit(false);
         }

         this.beeLine.showWarnings();
         this.beeLine.autocommitStatus(this.beeLine.getDatabaseConnection().getConnection());
         return true;
      }
   }

   public boolean dbinfo(String line) {
      if (!this.beeLine.assertConnection()) {
         return false;
      } else {
         this.beeLine.showWarnings();
         int padlen = 50;
         String[] m = new String[]{"allProceduresAreCallable", "allTablesAreSelectable", "dataDefinitionCausesTransactionCommit", "dataDefinitionIgnoredInTransactions", "doesMaxRowSizeIncludeBlobs", "getCatalogSeparator", "getCatalogTerm", "getDatabaseProductName", "getDatabaseProductVersion", "getDefaultTransactionIsolation", "getDriverMajorVersion", "getDriverMinorVersion", "getDriverName", "getDriverVersion", "getExtraNameCharacters", "getIdentifierQuoteString", "getMaxBinaryLiteralLength", "getMaxCatalogNameLength", "getMaxCharLiteralLength", "getMaxColumnNameLength", "getMaxColumnsInGroupBy", "getMaxColumnsInIndex", "getMaxColumnsInOrderBy", "getMaxColumnsInSelect", "getMaxColumnsInTable", "getMaxConnections", "getMaxCursorNameLength", "getMaxIndexLength", "getMaxProcedureNameLength", "getMaxRowSize", "getMaxSchemaNameLength", "getMaxStatementLength", "getMaxStatements", "getMaxTableNameLength", "getMaxTablesInSelect", "getMaxUserNameLength", "getNumericFunctions", "getProcedureTerm", "getSchemaTerm", "getSearchStringEscape", "getSQLKeywords", "getStringFunctions", "getSystemFunctions", "getTimeDateFunctions", "getURL", "getUserName", "isCatalogAtStart", "isReadOnly", "nullPlusNonNullIsNull", "nullsAreSortedAtEnd", "nullsAreSortedAtStart", "nullsAreSortedHigh", "nullsAreSortedLow", "storesLowerCaseIdentifiers", "storesLowerCaseQuotedIdentifiers", "storesMixedCaseIdentifiers", "storesMixedCaseQuotedIdentifiers", "storesUpperCaseIdentifiers", "storesUpperCaseQuotedIdentifiers", "supportsAlterTableWithAddColumn", "supportsAlterTableWithDropColumn", "supportsANSI92EntryLevelSQL", "supportsANSI92FullSQL", "supportsANSI92IntermediateSQL", "supportsBatchUpdates", "supportsCatalogsInDataManipulation", "supportsCatalogsInIndexDefinitions", "supportsCatalogsInPrivilegeDefinitions", "supportsCatalogsInProcedureCalls", "supportsCatalogsInTableDefinitions", "supportsColumnAliasing", "supportsConvert", "supportsCoreSQLGrammar", "supportsCorrelatedSubqueries", "supportsDataDefinitionAndDataManipulationTransactions", "supportsDataManipulationTransactionsOnly", "supportsDifferentTableCorrelationNames", "supportsExpressionsInOrderBy", "supportsExtendedSQLGrammar", "supportsFullOuterJoins", "supportsGroupBy", "supportsGroupByBeyondSelect", "supportsGroupByUnrelated", "supportsIntegrityEnhancementFacility", "supportsLikeEscapeClause", "supportsLimitedOuterJoins", "supportsMinimumSQLGrammar", "supportsMixedCaseIdentifiers", "supportsMixedCaseQuotedIdentifiers", "supportsMultipleResultSets", "supportsMultipleTransactions", "supportsNonNullableColumns", "supportsOpenCursorsAcrossCommit", "supportsOpenCursorsAcrossRollback", "supportsOpenStatementsAcrossCommit", "supportsOpenStatementsAcrossRollback", "supportsOrderByUnrelated", "supportsOuterJoins", "supportsPositionedDelete", "supportsPositionedUpdate", "supportsSchemasInDataManipulation", "supportsSchemasInIndexDefinitions", "supportsSchemasInPrivilegeDefinitions", "supportsSchemasInProcedureCalls", "supportsSchemasInTableDefinitions", "supportsSelectForUpdate", "supportsStoredProcedures", "supportsSubqueriesInComparisons", "supportsSubqueriesInExists", "supportsSubqueriesInIns", "supportsSubqueriesInQuantifieds", "supportsTableCorrelationNames", "supportsTransactions", "supportsUnion", "supportsUnionAll", "usesLocalFilePerTable", "usesLocalFiles"};

         for(int i = 0; i < m.length; ++i) {
            try {
               this.beeLine.output(this.beeLine.getColorBuffer().pad(m[i], padlen).append("" + this.beeLine.getReflector().invoke(this.beeLine.getDatabaseMetaData(), m[i], (Object[])(new Object[0]))));
            } catch (Exception e) {
               this.beeLine.output(this.beeLine.getColorBuffer().pad(m[i], padlen), false);
               this.beeLine.handleException(e);
            }
         }

         return true;
      }
   }

   public boolean verbose(String line) {
      this.beeLine.info("verbose: on");
      return this.set("set verbose true");
   }

   public boolean outputformat(String line) {
      return this.set("set " + line);
   }

   public boolean brief(String line) {
      this.beeLine.info("verbose: off");
      return this.set("set verbose false");
   }

   public boolean isolation(String line) throws SQLException {
      if (!this.beeLine.assertConnection()) {
         return false;
      } else {
         int i;
         if (line.endsWith("TRANSACTION_NONE")) {
            i = 0;
         } else if (line.endsWith("TRANSACTION_READ_COMMITTED")) {
            i = 2;
         } else if (line.endsWith("TRANSACTION_READ_UNCOMMITTED")) {
            i = 1;
         } else if (line.endsWith("TRANSACTION_REPEATABLE_READ")) {
            i = 4;
         } else {
            if (!line.endsWith("TRANSACTION_SERIALIZABLE")) {
               return this.beeLine.error("Usage: isolation <TRANSACTION_NONE | TRANSACTION_READ_COMMITTED | TRANSACTION_READ_UNCOMMITTED | TRANSACTION_REPEATABLE_READ | TRANSACTION_SERIALIZABLE>");
            }

            i = 8;
         }

         this.beeLine.getDatabaseConnection().getConnection().setTransactionIsolation(i);
         int isol = this.beeLine.getDatabaseConnection().getConnection().getTransactionIsolation();
         String isoldesc;
         switch (i) {
            case 0:
               isoldesc = "TRANSACTION_NONE";
               break;
            case 1:
               isoldesc = "TRANSACTION_READ_UNCOMMITTED";
               break;
            case 2:
               isoldesc = "TRANSACTION_READ_COMMITTED";
               break;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               isoldesc = "UNKNOWN";
               break;
            case 4:
               isoldesc = "TRANSACTION_REPEATABLE_READ";
               break;
            case 8:
               isoldesc = "TRANSACTION_SERIALIZABLE";
         }

         this.beeLine.info(this.beeLine.loc("isolation-status", (Object)isoldesc));
         return true;
      }
   }

   public boolean batch(String line) {
      if (!this.beeLine.assertConnection()) {
         return false;
      } else if (this.beeLine.getBatch() == null) {
         this.beeLine.setBatch(new LinkedList());
         this.beeLine.info(this.beeLine.loc("batch-start"));
         return true;
      } else {
         this.beeLine.info(this.beeLine.loc("running-batch"));

         boolean var3;
         try {
            this.beeLine.runBatch(this.beeLine.getBatch());
            boolean var2 = true;
            return var2;
         } catch (Exception e) {
            var3 = this.beeLine.error((Throwable)e);
         } finally {
            this.beeLine.setBatch((List)null);
         }

         return var3;
      }
   }

   public boolean sql(String line) {
      return this.execute(line, false, false);
   }

   private Map getHiveVariables() {
      Map<String, String> result = new HashMap();
      BufferedRows rows = this.getConfInternal(true);
      if (rows != null) {
         while(rows.hasNext()) {
            Rows.Row row = (Rows.Row)rows.next();
            if (!row.isMeta) {
               result.put(row.values[0], row.values[1]);
            }
         }
      }

      return result;
   }

   public HiveConf getHiveConf(boolean call) {
      HiveConf hiveConf = this.beeLine.getOpts().getConf();
      return hiveConf != null && call ? hiveConf : this.getHiveConfHelper(call);
   }

   public HiveConf getHiveConfHelper(boolean call) {
      HiveConf conf = new HiveConf();
      BufferedRows rows = this.getConfInternal(call);

      while(rows != null && rows.hasNext()) {
         this.addConf((Rows.Row)rows.next(), conf);
      }

      return conf;
   }

   private BufferedRows getConfInternal(boolean call) {
      Statement stmnt = null;
      BufferedRows rows = null;

      try {
         boolean hasResults = false;
         DatabaseConnection dbconn = this.beeLine.getDatabaseConnection();
         Connection conn = null;
         if (dbconn != null) {
            conn = dbconn.getConnection();
         }

         if (conn != null) {
            if (call) {
               stmnt = conn.prepareCall("set");
               hasResults = ((CallableStatement)stmnt).execute();
            } else {
               stmnt = this.beeLine.createStatement();
               hasResults = stmnt.execute("set");
            }
         }

         if (hasResults) {
            ResultSet rs = stmnt.getResultSet();
            rows = new BufferedRows(this.beeLine, rs);
         }
      } catch (SQLException e) {
         this.beeLine.error((Throwable)e);
      } finally {
         if (stmnt != null) {
            try {
               stmnt.close();
            } catch (SQLException e1) {
               this.beeLine.error((Throwable)e1);
            }
         }

      }

      return rows;
   }

   private void addConf(Rows.Row r, HiveConf hiveConf) {
      if (!r.isMeta) {
         if (r.values != null && r.values[0] != null && !r.values[0].isEmpty()) {
            String val = r.values[0];
            if (!r.values[0].startsWith("system:") && !r.values[0].startsWith("env:")) {
               String[] kv = val.split("=", 2);
               if (kv.length == 2) {
                  hiveConf.set(kv[0], kv[1]);
               }

            }
         }
      }
   }

   private String getFirstCmd(String cmd, int length) {
      return cmd.substring(length).trim();
   }

   private String[] tokenizeCmd(String cmd) {
      return cmd.split("\\s+");
   }

   private boolean isSourceCMD(String cmd) {
      if (cmd != null && !cmd.isEmpty()) {
         String[] tokens = this.tokenizeCmd(cmd);
         return tokens[0].equalsIgnoreCase("source");
      } else {
         return false;
      }
   }

   private boolean sourceFile(String cmd) {
      String[] tokens = this.tokenizeCmd(cmd);
      String cmd_1 = this.getFirstCmd(cmd, tokens[0].length());
      cmd_1 = this.substituteVariables(this.getHiveConf(false), cmd_1);
      File sourceFile = new File(cmd_1);
      if (!sourceFile.isFile()) {
         return false;
      } else {
         try {
            boolean ret = this.sourceFileInternal(sourceFile);
            return ret;
         } catch (IOException e) {
            this.beeLine.error((Throwable)e);
            return false;
         }
      }
   }

   private boolean sourceFileInternal(File sourceFile) throws IOException {
      BufferedReader reader = null;

      try {
         reader = new BufferedReader(new FileReader(sourceFile));
         String extra = reader.readLine();
         String lines = null;

         while(extra != null) {
            if (!this.beeLine.isComment(extra)) {
               if (lines == null) {
                  lines = extra;
               } else {
                  lines = lines + "\n" + extra;
               }

               extra = reader.readLine();
            }
         }

         String[] cmds = lines.split(";");

         for(String c : cmds) {
            c = c.trim();
            if (!this.executeInternal(c, false)) {
               boolean var10 = false;
               return var10;
            }
         }

         return true;
      } finally {
         if (reader != null) {
            reader.close();
         }

      }
   }

   public String cliToBeelineCmd(String cmd) {
      if (cmd == null) {
         return null;
      } else if (!cmd.toLowerCase().equals("quit") && !cmd.toLowerCase().equals("exit")) {
         if (cmd.startsWith("!")) {
            String shell_cmd = cmd.substring(1);
            return "!sh " + shell_cmd;
         } else {
            return cmd;
         }
      } else {
         return "!" + cmd;
      }
   }

   private boolean executeInternal(String sql, boolean call) {
      if (!this.beeLine.isBeeLine()) {
         sql = this.cliToBeelineCmd(sql);
      }

      if (sql != null && sql.length() != 0) {
         if (this.beeLine.isComment(sql)) {
            return true;
         } else if (this.isSourceCMD(sql)) {
            return this.sourceFile(sql);
         } else if (sql.startsWith("!")) {
            return this.beeLine.execCommandWithPrefix(sql);
         } else {
            String prefix = call ? "call" : "sql";
            if (sql.startsWith(prefix)) {
               sql = sql.substring(prefix.length());
            }

            if (this.beeLine.getBatch() != null) {
               this.beeLine.getBatch().add(sql);
               return true;
            } else if (!this.beeLine.assertConnection()) {
               return false;
            } else {
               ClientHook hook = ClientCommandHookFactory.get().getHook(this.beeLine, sql);

               try {
                  Statement stmnt = null;
                  Thread logThread = null;

                  try {
                     long start = System.currentTimeMillis();
                     boolean hasResults;
                     if (call) {
                        stmnt = this.beeLine.getDatabaseConnection().getConnection().prepareCall(sql);
                        hasResults = ((CallableStatement)stmnt).execute();
                     } else {
                        stmnt = this.beeLine.createStatement();
                        if (this.beeLine.getOpts().isSilent()) {
                           hasResults = stmnt.execute(sql);
                        } else {
                           InPlaceUpdateStream.EventNotifier eventNotifier = new InPlaceUpdateStream.EventNotifier();
                           logThread = new Thread(this.createLogRunnable(stmnt, eventNotifier));
                           logThread.setDaemon(true);
                           logThread.start();
                           if (stmnt instanceof HiveStatement) {
                              HiveStatement hiveStatement = (HiveStatement)stmnt;
                              hiveStatement.setInPlaceUpdateStream(new BeelineInPlaceUpdateStream(this.beeLine.getErrorStream(), eventNotifier));
                           }

                           hasResults = stmnt.execute(sql);
                           logThread.interrupt();
                           logThread.join(10000L);
                        }
                     }

                     this.beeLine.showWarnings();
                     if (hasResults) {
                        do {
                           ResultSet rs = stmnt.getResultSet();

                           try {
                              int count = this.beeLine.print(rs);
                              long end = System.currentTimeMillis();
                              this.beeLine.info(this.beeLine.loc("rows-selected", count) + " " + this.beeLine.locElapsedTime(end - start));
                           } finally {
                              if (logThread != null) {
                                 logThread.join(10000L);
                                 this.showRemainingLogsIfAny(stmnt);
                                 logThread = null;
                              }

                              rs.close();
                           }
                        } while(BeeLine.getMoreResults(stmnt));
                     } else {
                        int count = stmnt.getUpdateCount();
                        long end = System.currentTimeMillis();
                        this.beeLine.info(this.beeLine.loc("rows-affected", count) + " " + this.beeLine.locElapsedTime(end - start));
                     }
                  } finally {
                     if (logThread != null) {
                        if (!logThread.isInterrupted()) {
                           logThread.interrupt();
                        }

                        logThread.join(10000L);
                        this.showRemainingLogsIfAny(stmnt);
                     }

                     if (stmnt != null) {
                        stmnt.close();
                     }

                  }
               } catch (Exception e) {
                  return this.beeLine.error((Throwable)e);
               }

               this.beeLine.showWarnings();
               if (hook != null) {
                  hook.postHook(this.beeLine);
               }

               return true;
            }
         }
      } else {
         return true;
      }
   }

   @VisibleForTesting
   String removeComments(String line, int[] startQuote) {
      if (line != null && !line.isEmpty()) {
         if (startQuote[0] == -1 && this.beeLine.isComment(line)) {
            return "";
         } else {
            StringBuilder builder = new StringBuilder();

            for(int index = 0; index < line.length(); ++index) {
               if (startQuote[0] == -1 && index < line.length() - 1 && line.charAt(index) == '-' && line.charAt(index + 1) == '-') {
                  return builder.toString().trim();
               }

               char letter = line.charAt(index);
               if (startQuote[0] != letter || index != 0 && line.charAt(index - 1) == '\\') {
                  if (startQuote[0] == -1 && (letter == '\'' || letter == '"') && (index == 0 || line.charAt(index - 1) != '\\')) {
                     startQuote[0] = letter;
                  }
               } else {
                  startQuote[0] = -1;
               }

               builder.append(letter);
            }

            return builder.toString().trim();
         }
      } else {
         return line;
      }
   }

   public String handleMultiLineCmd(String line) throws IOException {
      int[] startQuote = new int[]{-1};
      line = this.removeComments(line, startQuote);

      while(this.isMultiLine(line) && this.beeLine.getOpts().isAllowMultiLineCommand()) {
         StringBuilder prompt = new StringBuilder(this.beeLine.getPrompt());
         if (!this.beeLine.getOpts().isSilent()) {
            for(int i = 0; i < prompt.length() - 1; ++i) {
               if (prompt.charAt(i) != '>') {
                  prompt.setCharAt(i, (char)(i % 2 == 0 ? '.' : ' '));
               }
            }
         }

         if (this.beeLine.getConsoleReader() == null) {
            throw new RuntimeException("Console reader not initialized. This could happen when there is a multi-line command using -e option and which requires further reading from console");
         }

         String extra;
         if (this.beeLine.getOpts().isSilent() && this.beeLine.getOpts().getScriptFile() != null) {
            extra = this.beeLine.getConsoleReader().readLine((String)null, '\u0000');
         } else {
            extra = this.beeLine.getConsoleReader().readLine(prompt.toString());
         }

         if (extra == null) {
            break;
         }

         extra = this.removeComments(extra, startQuote);
         if (extra != null && !extra.isEmpty()) {
            line = line + "\n" + extra;
         }
      }

      return line;
   }

   private boolean isMultiLine(String line) {
      line = line.trim();
      if (!line.endsWith(";") && !this.beeLine.isComment(line)) {
         List<String> cmds = this.getCmdList(line, false);
         return cmds.isEmpty() || !((String)cmds.get(cmds.size() - 1)).trim().startsWith("--");
      } else {
         return false;
      }
   }

   public boolean sql(String line, boolean entireLineAsCommand) {
      return this.execute(line, false, entireLineAsCommand);
   }

   public String substituteVariables(HiveConf conf, String line) {
      return !this.beeLine.isBeeLine() ? (new VariableSubstitution(new HiveVariableSource() {
         public Map getHiveVariable() {
            return Commands.this.getHiveVariables();
         }
      })).substitute(conf, line) : line;
   }

   public boolean sh(String line) {
      if (line != null && line.length() != 0) {
         if (!line.startsWith("sh")) {
            return false;
         } else {
            line = line.substring("sh".length()).trim();
            if (!this.beeLine.isBeeLine()) {
               line = this.substituteVariables(this.getHiveConf(false), line.trim());
            }

            try {
               ShellCmdExecutor executor = new ShellCmdExecutor(line, this.beeLine.getOutputStream(), this.beeLine.getErrorStream());
               int ret = executor.execute();
               if (ret != 0) {
                  this.beeLine.output("Command failed with exit code = " + ret);
                  return false;
               } else {
                  return true;
               }
            } catch (Exception e) {
               this.beeLine.error("Exception raised from Shell command " + e);
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public boolean call(String line) {
      return this.execute(line, true, false);
   }

   private boolean execute(String line, boolean call, boolean entireLineAsCommand) {
      if (line != null && line.length() != 0) {
         try {
            line = this.handleMultiLineCmd(line);
         } catch (Exception e) {
            this.beeLine.handleException(e);
         }

         line = line.trim();
         List<String> cmdList = this.getCmdList(line, entireLineAsCommand);

         for(int i = 0; i < cmdList.size(); ++i) {
            String sql = ((String)cmdList.get(i)).trim();
            if (sql.length() != 0 && !this.executeInternal(sql, call)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private List getCmdList(String line, boolean entireLineAsCommand) {
      List<String> cmdList = new ArrayList();
      if (entireLineAsCommand) {
         cmdList.add(line);
      } else {
         StringBuilder command = new StringBuilder();
         boolean hasUnterminatedDoubleQuote = false;
         boolean hasUnterminatedSingleQuote = false;
         int lastSemiColonIndex = 0;
         char[] lineChars = line.toCharArray();
         boolean wasPrevEscape = false;

         int index;
         for(index = 0; index < lineChars.length; ++index) {
            switch (lineChars[index]) {
               case '"':
                  if (!hasUnterminatedSingleQuote && !wasPrevEscape) {
                     hasUnterminatedDoubleQuote = !hasUnterminatedDoubleQuote;
                  }

                  wasPrevEscape = false;
                  break;
               case '\'':
                  if (!hasUnterminatedDoubleQuote && !wasPrevEscape) {
                     hasUnterminatedSingleQuote = !hasUnterminatedSingleQuote;
                  }

                  wasPrevEscape = false;
                  break;
               case ';':
                  if (!hasUnterminatedDoubleQuote && !hasUnterminatedSingleQuote) {
                     this.addCmdPart(cmdList, command, line.substring(lastSemiColonIndex, index));
                     lastSemiColonIndex = index + 1;
                  }

                  wasPrevEscape = false;
                  break;
               case '\\':
                  wasPrevEscape = !wasPrevEscape;
                  break;
               default:
                  wasPrevEscape = false;
            }
         }

         if (lastSemiColonIndex != index || lineChars.length == 0) {
            this.addCmdPart(cmdList, command, line.substring(lastSemiColonIndex, index));
         }
      }

      return cmdList;
   }

   private void addCmdPart(List cmdList, StringBuilder command, String cmdpart) {
      if (cmdpart.endsWith("\\")) {
         command.append(cmdpart.substring(0, cmdpart.length() - 1)).append(";");
      } else {
         command.append(cmdpart);
         cmdList.add(command.toString());
         command.setLength(0);
      }
   }

   private Runnable createLogRunnable(Statement statement, InPlaceUpdateStream.EventNotifier eventNotifier) {
      if (statement instanceof HiveStatement) {
         return new LogRunnable(this, (HiveStatement)statement, 1000L, eventNotifier);
      } else {
         this.beeLine.debug("The statement instance is not HiveStatement type: " + statement.getClass());
         return new Runnable() {
            public void run() {
            }
         };
      }
   }

   private void error(Throwable throwable) {
      this.beeLine.error(throwable);
   }

   private void debug(String message) {
      this.beeLine.debug(message);
   }

   private void showRemainingLogsIfAny(Statement statement) {
      if (statement instanceof HiveStatement) {
         HiveStatement hiveStatement = (HiveStatement)statement;
         List<String> logs = null;

         do {
            try {
               logs = hiveStatement.getQueryLog();
            } catch (SQLException e) {
               this.beeLine.error((Throwable)(new SQLWarning(e)));
               return;
            }

            for(String log : logs) {
               this.beeLine.info(log);
            }
         } while(logs.size() > 0);
      } else {
         this.beeLine.debug("The statement instance is not HiveStatement type: " + statement.getClass());
      }

   }

   public boolean quit(String line) {
      this.beeLine.setExit(true);
      this.close((String)null);
      return true;
   }

   public boolean exit(String line) {
      return this.quit(line);
   }

   public boolean closeall(String line) {
      if (!this.close((String)null)) {
         return false;
      } else {
         while(this.close((String)null)) {
         }

         return true;
      }
   }

   public boolean close(String line) {
      if (this.beeLine.getDatabaseConnection() == null) {
         return false;
      } else {
         try {
            if (this.beeLine.getDatabaseConnection().getCurrentConnection() != null && !this.beeLine.getDatabaseConnection().getCurrentConnection().isClosed()) {
               int index = this.beeLine.getDatabaseConnections().getIndex();
               this.beeLine.info(this.beeLine.loc("closing", index, this.beeLine.getDatabaseConnection()));
               this.beeLine.getDatabaseConnection().getCurrentConnection().close();
            } else {
               this.beeLine.info(this.beeLine.loc("already-closed"));
            }
         } catch (Exception e) {
            return this.beeLine.error((Throwable)e);
         }

         this.beeLine.getDatabaseConnections().remove();
         return true;
      }
   }

   public boolean properties(String line) throws Exception {
      String example = "";
      example = example + "Usage: properties <properties file>" + BeeLine.getSeparator();
      String[] parts = this.beeLine.split(line);
      if (parts.length < 2) {
         return this.beeLine.error(example);
      } else {
         int successes = 0;

         for(int i = 1; i < parts.length; ++i) {
            Properties props = new Properties();
            InputStream stream = new FileInputStream(parts[i]);

            try {
               props.load(stream);
            } finally {
               IOUtils.closeStream(stream);
            }

            if (this.connect(props)) {
               ++successes;
            }
         }

         return successes == parts.length - 1;
      }
   }

   public boolean connect(String line) throws Exception {
      String example = "Usage: connect <url> <username> <password> [driver]" + BeeLine.getSeparator();
      String[] parts = this.beeLine.split(line);
      if (parts == null) {
         return false;
      } else if (parts.length < 2) {
         return this.beeLine.error(example);
      } else {
         String url = parts.length < 2 ? null : parts[1];
         String user = parts.length < 3 ? null : parts[2];
         String pass = parts.length < 4 ? null : parts[3];
         String driver = parts.length < 5 ? null : parts[4];
         Properties props = new Properties();
         if (url != null) {
            String saveUrl = this.getUrlToUse(url);
            props.setProperty("url", saveUrl);
         }

         String value = null;
         if (driver != null) {
            props.setProperty("driver", driver);
         } else {
            value = Utils.parsePropertyFromUrl(url, "driver");
            if (value != null) {
               props.setProperty("driver", value);
            }
         }

         if (user != null) {
            props.setProperty("user", user);
         } else {
            value = Utils.parsePropertyFromUrl(url, "user");
            if (value != null) {
               props.setProperty("user", value);
            }
         }

         if (pass != null) {
            props.setProperty("password", pass);
         } else {
            value = Utils.parsePropertyFromUrl(url, "password");
            if (value != null) {
               props.setProperty("password", value);
            }
         }

         value = Utils.parsePropertyFromUrl(url, "auth");
         if (value != null) {
            props.setProperty("auth", value);
         }

         return this.connect(props);
      }
   }

   private String getUrlToUse(String urlParam) {
      boolean useIndirectUrl = false;

      try {
         URI tryParse = new URI(urlParam);
         if (tryParse.getScheme() == null) {
            useIndirectUrl = true;
         }
      } catch (URISyntaxException var4) {
         useIndirectUrl = true;
      }

      if (useIndirectUrl) {
         this.beeLine.getOpts();
         String envUrl = BeeLineOpts.getEnv().get("BEELINE_URL_" + urlParam.toUpperCase());
         if (envUrl != null) {
            return envUrl;
         }
      }

      return urlParam;
   }

   private String getProperty(Properties props, String[] keys) {
      for(int i = 0; i < keys.length; ++i) {
         String val = props.getProperty(keys[i]);
         if (val != null) {
            return val;
         }
      }

      for(String key : props.keySet()) {
         for(int j = 0; j < keys.length; ++j) {
            if (key.endsWith(keys[j])) {
               return props.getProperty(key);
            }
         }
      }

      return null;
   }

   public boolean connect(Properties props) throws IOException {
      String url = this.getProperty(props, new String[]{"url", "javax.jdo.option.ConnectionURL", "ConnectionURL"});
      String driver = this.getProperty(props, new String[]{"driver", "javax.jdo.option.ConnectionDriverName", "ConnectionDriverName"});
      String username = this.getProperty(props, new String[]{"user", "javax.jdo.option.ConnectionUserName", "ConnectionUserName"});
      String password = this.getProperty(props, new String[]{"password", "javax.jdo.option.ConnectionPassword", "ConnectionPassword"});
      if (url != null && url.length() != 0) {
         if ((driver == null || driver.length() == 0) && !this.beeLine.scanForDriver(url)) {
            return this.beeLine.error(this.beeLine.loc("no-driver", (Object)url));
         } else {
            String auth = this.getProperty(props, new String[]{"auth"});
            if (auth == null) {
               auth = this.beeLine.getOpts().getAuthType();
               if (auth != null) {
                  props.setProperty("auth", auth);
               }
            }

            this.beeLine.info("Connecting to " + url);
            if (Utils.parsePropertyFromUrl(url, "principal") == null) {
               String urlForPrompt = url.substring(0, url.contains(";") ? url.indexOf(59) : url.length());
               if (username == null) {
                  username = this.beeLine.getConsoleReader().readLine("Enter username for " + urlForPrompt + ": ");
               }

               props.setProperty("user", username);
               if (password == null) {
                  password = this.beeLine.getConsoleReader().readLine("Enter password for " + urlForPrompt + ": ", new Character('*'));
               }

               props.setProperty("password", password);
            }

            try {
               this.beeLine.getDatabaseConnections().setConnection(new DatabaseConnection(this.beeLine, driver, url, props));
               this.beeLine.getDatabaseConnection().getConnection();
               if (!this.beeLine.isBeeLine()) {
                  this.beeLine.updateOptsForCli();
               }

               this.beeLine.runInit();
               this.beeLine.setCompletions();
               this.beeLine.getOpts().setLastConnectedUrl(url);
               return true;
            } catch (SQLException sqle) {
               this.beeLine.getDatabaseConnections().remove();
               return this.beeLine.error((Throwable)sqle);
            } catch (IOException ioe) {
               return this.beeLine.error((Throwable)ioe);
            }
         }
      } else {
         return this.beeLine.error("Property \"url\" is required");
      }
   }

   public boolean rehash(String line) {
      try {
         if (!this.beeLine.assertConnection()) {
            return false;
         } else {
            if (this.beeLine.getDatabaseConnection() != null) {
               this.beeLine.getDatabaseConnection().setCompletions(false);
            }

            return true;
         }
      } catch (Exception e) {
         return this.beeLine.error((Throwable)e);
      }
   }

   public boolean list(String line) {
      int index = 0;
      this.beeLine.info(this.beeLine.loc("active-connections", this.beeLine.getDatabaseConnections().size()));

      for(DatabaseConnection c : this.beeLine.getDatabaseConnections()) {
         boolean closed = false;

         try {
            closed = c.getConnection().isClosed();
         } catch (Exception var7) {
            closed = true;
         }

         this.beeLine.output(this.beeLine.getColorBuffer().pad((String)(" #" + index + ""), 5).pad((String)(closed ? this.beeLine.loc("closed") : this.beeLine.loc("open")), 9).append(c.getUrl()));
         ++index;
      }

      return true;
   }

   public boolean all(String line) {
      int index = this.beeLine.getDatabaseConnections().getIndex();
      boolean success = true;

      for(int i = 0; i < this.beeLine.getDatabaseConnections().size(); ++i) {
         this.beeLine.getDatabaseConnections().setIndex(i);
         this.beeLine.output(this.beeLine.loc("executing-con", (Object)this.beeLine.getDatabaseConnection()));
         success = this.sql(line.substring("all ".length())) && success;
      }

      this.beeLine.getDatabaseConnections().setIndex(index);
      return success;
   }

   public boolean go(String line) {
      String[] parts = this.beeLine.split(line, 2, "Usage: go <connection index>");
      if (parts == null) {
         return false;
      } else {
         int index = Integer.parseInt(parts[1]);
         if (!this.beeLine.getDatabaseConnections().setIndex(index)) {
            this.beeLine.error(this.beeLine.loc("invalid-connection", (Object)("" + index)));
            this.list("");
            return false;
         } else {
            return true;
         }
      }
   }

   public boolean script(String line) {
      return this.beeLine.getScriptOutputFile() == null ? this.startScript(line) : this.stopScript(line);
   }

   private boolean stopScript(String line) {
      try {
         this.beeLine.getScriptOutputFile().close();
      } catch (Exception e) {
         this.beeLine.handleException(e);
      }

      this.beeLine.output(this.beeLine.loc("script-closed", (Object)this.beeLine.getScriptOutputFile()));
      this.beeLine.setScriptOutputFile((OutputFile)null);
      return true;
   }

   private boolean startScript(String line) {
      if (this.beeLine.getScriptOutputFile() != null) {
         return this.beeLine.error(this.beeLine.loc("script-already-running", (Object)this.beeLine.getScriptOutputFile()));
      } else {
         String[] parts = this.beeLine.split(line, 2, "Usage: script <filename>");
         if (parts == null) {
            return false;
         } else {
            try {
               this.beeLine.setScriptOutputFile(new OutputFile(parts[1]));
               this.beeLine.output(this.beeLine.loc("script-started", (Object)this.beeLine.getScriptOutputFile()));
               return true;
            } catch (Exception e) {
               return this.beeLine.error((Throwable)e);
            }
         }
      }
   }

   public boolean run(String line) {
      String[] parts = this.beeLine.split(line, 2, "Usage: run <scriptfile>");
      if (parts == null) {
         return false;
      } else {
         List<String> cmds = new LinkedList();

         try {
            BufferedReader reader = new BufferedReader(new FileReader(parts[1]));

            try {
               StringBuilder cmd = null;

               while(true) {
                  String scriptLine = reader.readLine();
                  if (scriptLine == null) {
                     if (cmd != null) {
                        cmd.append(";");
                        cmds.add(cmd.toString());
                     }
                     break;
                  }

                  String trimmedLine = scriptLine.trim();
                  if (this.beeLine.getOpts().getTrimScripts()) {
                     scriptLine = trimmedLine;
                  }

                  if (cmd != null) {
                     cmd.append(" \n");
                     cmd.append(scriptLine);
                     if (trimmedLine.endsWith(";")) {
                        cmds.add(cmd.toString());
                        cmd = null;
                     }
                  } else if (this.beeLine.needsContinuation(scriptLine)) {
                     cmd = new StringBuilder(scriptLine);
                  } else {
                     cmds.add(scriptLine);
                  }
               }
            } finally {
               reader.close();
            }

            return this.beeLine.runCommands(cmds) == cmds.size();
         } catch (Exception e) {
            return this.beeLine.error((Throwable)e);
         }
      }
   }

   public boolean record(String line) {
      return this.beeLine.getRecordOutputFile() == null ? this.startRecording(line) : this.stopRecording(line);
   }

   private boolean stopRecording(String line) {
      try {
         this.beeLine.getRecordOutputFile().close();
      } catch (Exception e) {
         this.beeLine.handleException(e);
      }

      this.beeLine.setRecordOutputFile((OutputFile)null);
      this.beeLine.output(this.beeLine.loc("record-closed", (Object)this.beeLine.getRecordOutputFile()));
      return true;
   }

   private boolean startRecording(String line) {
      if (this.beeLine.getRecordOutputFile() != null) {
         return this.beeLine.error(this.beeLine.loc("record-already-running", (Object)this.beeLine.getRecordOutputFile()));
      } else {
         String[] parts = this.beeLine.split(line, 2, "Usage: record <filename>");
         if (parts == null) {
            return false;
         } else {
            try {
               OutputFile recordOutput = new OutputFile(parts[1]);
               this.beeLine.output(this.beeLine.loc("record-started", (Object)recordOutput));
               this.beeLine.setRecordOutputFile(recordOutput);
               return true;
            } catch (Exception e) {
               return this.beeLine.error((Throwable)e);
            }
         }
      }
   }

   public boolean describe(String line) throws SQLException {
      String[] table = this.beeLine.split(line, 2, "Usage: describe <table name>");
      if (table == null) {
         return false;
      } else {
         ResultSet rs;
         if (table[1].equals("tables")) {
            rs = this.beeLine.getTables();
         } else {
            rs = this.beeLine.getColumns(table[1]);
         }

         if (rs == null) {
            return false;
         } else {
            this.beeLine.print(rs);
            rs.close();
            return true;
         }
      }
   }

   public boolean help(String line) {
      String[] parts = this.beeLine.split(line);
      String cmd = parts.length > 1 ? parts[1] : "";
      int count = 0;
      TreeSet<ColorBuffer> clist = new TreeSet();

      for(int i = 0; i < this.beeLine.commandHandlers.length; ++i) {
         if (cmd.length() == 0 || Arrays.asList(this.beeLine.commandHandlers[i].getNames()).contains(cmd)) {
            clist.add(this.beeLine.getColorBuffer().pad((String)("!" + this.beeLine.commandHandlers[i].getName()), 20).append(this.beeLine.wrap(this.beeLine.commandHandlers[i].getHelpText(), 60, 20)));
         }
      }

      Iterator<ColorBuffer> i = clist.iterator();

      while(i.hasNext()) {
         this.beeLine.output((ColorBuffer)i.next());
      }

      if (cmd.length() == 0) {
         this.beeLine.output("");
         this.beeLine.output(this.beeLine.loc("comments", (Object)this.beeLine.getApplicationContactInformation()));
      }

      return true;
   }

   public boolean manual(String line) throws IOException {
      InputStream in = BeeLine.class.getResourceAsStream("manual.txt");
      if (in == null) {
         return this.beeLine.error(this.beeLine.loc("no-manual"));
      } else {
         BufferedReader breader = new BufferedReader(new InputStreamReader(in));
         int index = 0;

         String man;
         while((man = breader.readLine()) != null) {
            ++index;
            this.beeLine.output(man);
            if (index % (this.beeLine.getOpts().getMaxHeight() - 1) == 0) {
               String ret = this.beeLine.getConsoleReader().readLine(this.beeLine.loc("enter-for-more"));
               if (ret != null && ret.startsWith("q")) {
                  break;
               }
            }
         }

         breader.close();
         return true;
      }
   }

   static class LogRunnable implements Runnable {
      private final Commands commands;
      private final HiveStatement hiveStatement;
      private final long queryProgressInterval;
      private final InPlaceUpdateStream.EventNotifier notifier;

      LogRunnable(Commands commands, HiveStatement hiveStatement, long queryProgressInterval, InPlaceUpdateStream.EventNotifier eventNotifier) {
         this.hiveStatement = hiveStatement;
         this.commands = commands;
         this.queryProgressInterval = queryProgressInterval;
         this.notifier = eventNotifier;
      }

      private void updateQueryLog() {
         try {
            List<String> queryLogs = this.hiveStatement.getQueryLog();

            for(String log : queryLogs) {
               this.commands.beeLine.info(log);
            }

            if (!queryLogs.isEmpty()) {
               this.notifier.operationLogShowedToUser();
            }
         } catch (SQLException e) {
            this.commands.error(new SQLWarning(e));
         }

      }

      public void run() {
         try {
            for(; this.hiveStatement.hasMoreLogs(); Thread.sleep(this.queryProgressInterval)) {
               if (this.notifier.canOutputOperationLogs()) {
                  this.commands.debug("going to print operations logs");
                  this.updateQueryLog();
                  this.commands.debug("printed operations logs");
               }
            }
         } catch (InterruptedException var5) {
            this.commands.debug("Getting log thread is interrupted, since query is done!");
         } finally {
            this.commands.showRemainingLogsIfAny(this.hiveStatement);
         }

      }
   }
}
