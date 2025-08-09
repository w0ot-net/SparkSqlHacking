package org.apache.hive.beeline;

import com.google.common.annotations.VisibleForTesting;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import jline.console.ConsoleReader;
import jline.console.completer.Completer;
import jline.console.completer.FileNameCompleter;
import jline.console.completer.StringsCompleter;
import jline.console.history.FileHistory;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.io.IOUtils;
import org.apache.hive.beeline.cli.CliOptionsProcessor;
import org.apache.hive.beeline.hs2connection.BeelineHS2ConnectionFileParseException;
import org.apache.hive.beeline.hs2connection.HS2ConnectionFileParser;
import org.apache.hive.beeline.hs2connection.HS2ConnectionFileUtils;
import org.apache.hive.beeline.hs2connection.HiveSiteHS2ConnectionFileParser;
import org.apache.hive.beeline.hs2connection.UserHS2ConnectionFileParser;
import org.apache.hive.common.util.ShutdownHookManager;
import org.apache.hive.jdbc.Utils;
import org.apache.thrift.transport.TTransportException;

public class BeeLine implements Closeable {
   private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(BeeLine.class.getSimpleName());
   private final BeeLineSignalHandler signalHandler;
   private final Runnable shutdownHook;
   private static final String separator = System.getProperty("line.separator");
   private boolean exit;
   private final DatabaseConnections connections;
   public static final String COMMAND_PREFIX = "!";
   private Collection drivers;
   private final BeeLineOpts opts;
   private String lastProgress;
   private final Map seenWarnings;
   private final Commands commands;
   private OutputFile scriptOutputFile;
   private OutputFile recordOutputFile;
   private PrintStream outputStream;
   private PrintStream errorStream;
   private ConsoleReader consoleReader;
   private List batch;
   private final Reflector reflector;
   private String dbName;
   private String currentDatabase;
   private FileHistory history;
   private boolean isBeeLine;
   private static final Options options = new Options();
   public static final String BEELINE_DEFAULT_JDBC_DRIVER = "org.apache.hive.jdbc.HiveDriver";
   public static final String DEFAULT_DATABASE_NAME = "default";
   private static final String SCRIPT_OUTPUT_PREFIX = ">>>";
   private static final int SCRIPT_OUTPUT_PAD_SIZE = 5;
   private static final int ERRNO_OK = 0;
   private static final int ERRNO_ARGS = 1;
   private static final int ERRNO_OTHER = 2;
   private static final String HIVE_VAR_PREFIX = "--hivevar";
   private static final String HIVE_CONF_PREFIX = "--hiveconf";
   private static final String PROP_FILE_PREFIX = "--property-file";
   static final String PASSWD_MASK = "[passwd stripped]";
   private final Map formats;
   private List supportedLocalDriver;
   final CommandHandler[] commandHandlers;
   private final Completer beeLineCommandCompleter;
   static final SortedSet KNOWN_DRIVERS = new TreeSet(Arrays.asList("org.apache.hive.jdbc.HiveDriver", "org.apache.hadoop.hive.jdbc.HiveDriver"));

   static Manifest getManifest() throws IOException {
      URL base = BeeLine.class.getResource("/META-INF/MANIFEST.MF");
      URLConnection c = base.openConnection();
      return c instanceof JarURLConnection ? ((JarURLConnection)c).getManifest() : null;
   }

   String getManifestAttribute(String name) {
      try {
         Manifest m = getManifest();
         if (m == null) {
            return "??";
         } else {
            Attributes attrs = m.getAttributes("beeline");
            if (attrs == null) {
               return "???";
            } else {
               String val = attrs.getValue(name);
               return val != null && !"".equals(val) ? val : "????";
            }
         }
      } catch (Exception e) {
         e.printStackTrace(this.errorStream);
         return "?????";
      }
   }

   String getApplicationTitle() {
      Package pack = BeeLine.class.getPackage();
      return this.loc("app-introduction", new Object[]{"Beeline", pack.getImplementationVersion() == null ? "???" : pack.getImplementationVersion(), "Apache Hive"});
   }

   String getApplicationContactInformation() {
      return this.getManifestAttribute("Implementation-Vendor");
   }

   String loc(String res) {
      return this.loc(res, new Object[0]);
   }

   String loc(String res, int param) {
      try {
         return MessageFormat.format((new ChoiceFormat(resourceBundle.getString(res))).format((long)param), new Integer(param));
      } catch (Exception var4) {
         return res + ": " + param;
      }
   }

   String loc(String res, Object param1) {
      return this.loc(res, new Object[]{param1});
   }

   String loc(String res, Object param1, Object param2) {
      return this.loc(res, new Object[]{param1, param2});
   }

   String loc(String res, Object[] params) {
      try {
         return MessageFormat.format(resourceBundle.getString(res), params);
      } catch (Exception e) {
         e.printStackTrace(this.getErrorStream());

         try {
            return res + ": " + Arrays.asList(params);
         } catch (Exception var5) {
            return res;
         }
      }
   }

   protected String locElapsedTime(long milliseconds) {
      return this.getOpts().getShowElapsedTime() ? this.loc("time-ms", new Object[]{new Double((double)milliseconds / (double)1000.0F)}) : "";
   }

   public static void main(String[] args) throws IOException {
      mainWithInputRedirection(args, (InputStream)null);
   }

   public static void mainWithInputRedirection(String[] args, InputStream inputStream) throws IOException {
      BeeLine beeLine = new BeeLine();

      try {
         int status = beeLine.begin(args, inputStream);
         if (!Boolean.getBoolean("beeline.system.exit")) {
            System.exit(status);
         }
      } finally {
         beeLine.close();
      }

   }

   public BeeLine() {
      this(true);
   }

   public BeeLine(boolean isBeeLine) {
      this.exit = false;
      this.connections = new DatabaseConnections();
      this.drivers = null;
      this.opts = new BeeLineOpts(this, System.getProperties());
      this.lastProgress = null;
      this.seenWarnings = new HashMap();
      this.commands = new Commands(this);
      this.scriptOutputFile = null;
      this.recordOutputFile = null;
      this.outputStream = new PrintStream(System.out, true);
      this.errorStream = new PrintStream(System.err, true);
      this.batch = null;
      this.reflector = new Reflector(this);
      this.dbName = null;
      this.currentDatabase = null;
      this.isBeeLine = true;
      this.formats = map(new Object[]{"vertical", new VerticalOutputFormat(this), "table", new TableOutputFormat(this), "csv2", new SeparatedValuesOutputFormat(this, ','), "tsv2", new SeparatedValuesOutputFormat(this, '\t'), "dsv", new SeparatedValuesOutputFormat(this, '|'), "csv", new DeprecatedSeparatedValuesOutputFormat(this, ','), "tsv", new DeprecatedSeparatedValuesOutputFormat(this, '\t'), "xmlattr", new XMLAttributeOutputFormat(this), "xmlelements", new XMLElementOutputFormat(this)});
      this.supportedLocalDriver = new ArrayList(Arrays.asList("com.mysql.jdbc.Driver", "org.postgresql.Driver"));
      this.commandHandlers = new CommandHandler[]{new ReflectiveCommandHandler(this, new String[]{"quit", "done", "exit"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"connect", "open"}, new Completer[]{new StringsCompleter(this.getConnectionURLExamples())}), new ReflectiveCommandHandler(this, new String[]{"describe"}, new Completer[]{new TableNameCompletor(this)}), new ReflectiveCommandHandler(this, new String[]{"indexes"}, new Completer[]{new TableNameCompletor(this)}), new ReflectiveCommandHandler(this, new String[]{"primarykeys"}, new Completer[]{new TableNameCompletor(this)}), new ReflectiveCommandHandler(this, new String[]{"exportedkeys"}, new Completer[]{new TableNameCompletor(this)}), new ReflectiveCommandHandler(this, new String[]{"manual"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"importedkeys"}, new Completer[]{new TableNameCompletor(this)}), new ReflectiveCommandHandler(this, new String[]{"procedures"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"tables"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"typeinfo"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"columns"}, new Completer[]{new TableNameCompletor(this)}), new ReflectiveCommandHandler(this, new String[]{"reconnect"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"dropall"}, new Completer[]{new TableNameCompletor(this)}), new ReflectiveCommandHandler(this, new String[]{"history"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"metadata"}, new Completer[]{new StringsCompleter(this.getMetadataMethodNames())}), new ReflectiveCommandHandler(this, new String[]{"nativesql"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"dbinfo"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"rehash"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"verbose"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"run"}, new Completer[]{new FileNameCompleter()}), new ReflectiveCommandHandler(this, new String[]{"batch"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"list"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"all"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"go", "#"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"script"}, new Completer[]{new FileNameCompleter()}), new ReflectiveCommandHandler(this, new String[]{"record"}, new Completer[]{new FileNameCompleter()}), new ReflectiveCommandHandler(this, new String[]{"brief"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"close"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"closeall"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"isolation"}, new Completer[]{new StringsCompleter(this.getIsolationLevels())}), new ReflectiveCommandHandler(this, new String[]{"outputformat"}, new Completer[]{new StringsCompleter((String[])this.formats.keySet().toArray(new String[0]))}), new ReflectiveCommandHandler(this, new String[]{"autocommit"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"commit"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"properties"}, new Completer[]{new FileNameCompleter()}), new ReflectiveCommandHandler(this, new String[]{"rollback"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"help", "?"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"set"}, this.getOpts().optionCompleters()), new ReflectiveCommandHandler(this, new String[]{"save"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"scan"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"sql"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"sh"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"call"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"nullemptystring"}, new Completer[]{new BooleanCompleter()}), new ReflectiveCommandHandler(this, new String[]{"addlocaldriverjar"}, (Completer[])null), new ReflectiveCommandHandler(this, new String[]{"addlocaldrivername"}, (Completer[])null)};
      this.beeLineCommandCompleter = new BeeLineCommandCompleter(Arrays.asList(this.commandHandlers));
      this.isBeeLine = isBeeLine;
      this.signalHandler = new SunSignalHandler(this);
      this.shutdownHook = new Runnable() {
         public void run() {
            try {
               if (BeeLine.this.history != null) {
                  BeeLine.this.history.setMaxSize(BeeLine.this.getOpts().getMaxHistoryRows());
                  BeeLine.this.history.flush();
               }
            } catch (IOException e) {
               BeeLine.this.error((Throwable)e);
            } finally {
               BeeLine.this.close();
            }

         }
      };
   }

   DatabaseConnection getDatabaseConnection() {
      return this.getDatabaseConnections().current();
   }

   Connection getConnection() throws SQLException {
      if (this.getDatabaseConnections().current() != null && this.getDatabaseConnections().current().getConnection() != null) {
         return this.getDatabaseConnections().current().getConnection();
      } else {
         throw new IllegalArgumentException(this.loc("no-current-connection"));
      }
   }

   DatabaseMetaData getDatabaseMetaData() {
      if (this.getDatabaseConnections().current() == null) {
         throw new IllegalArgumentException(this.loc("no-current-connection"));
      } else if (this.getDatabaseConnections().current().getDatabaseMetaData() == null) {
         throw new IllegalArgumentException(this.loc("no-current-connection"));
      } else {
         return this.getDatabaseConnections().current().getDatabaseMetaData();
      }
   }

   public String[] getIsolationLevels() {
      return new String[]{"TRANSACTION_NONE", "TRANSACTION_READ_COMMITTED", "TRANSACTION_READ_UNCOMMITTED", "TRANSACTION_REPEATABLE_READ", "TRANSACTION_SERIALIZABLE"};
   }

   public String[] getMetadataMethodNames() {
      try {
         TreeSet<String> mnames = new TreeSet();
         Method[] m = DatabaseMetaData.class.getDeclaredMethods();

         for(int i = 0; m != null && i < m.length; ++i) {
            mnames.add(m[i].getName());
         }

         return (String[])mnames.toArray(new String[0]);
      } catch (Throwable var4) {
         return new String[0];
      }
   }

   public String[] getConnectionURLExamples() {
      return new String[]{"jdbc:JSQLConnect://<hostname>/database=<database>", "jdbc:cloudscape:<database>;create=true", "jdbc:twtds:sqlserver://<hostname>/<database>", "jdbc:daffodilDB_embedded:<database>;create=true", "jdbc:datadirect:db2://<hostname>:50000;databaseName=<database>", "jdbc:inetdae:<hostname>:1433", "jdbc:datadirect:oracle://<hostname>:1521;SID=<database>;MaxPooledStatements=0", "jdbc:datadirect:sqlserver://<hostname>:1433;SelectMethod=cursor;DatabaseName=<database>", "jdbc:datadirect:sybase://<hostname>:5000", "jdbc:db2://<hostname>/<database>", "jdbc:hive2://<hostname>", "jdbc:hsqldb:<database>", "jdbc:idb:<database>.properties", "jdbc:informix-sqli://<hostname>:1526/<database>:INFORMIXSERVER=<database>", "jdbc:interbase://<hostname>//<database>.gdb", "jdbc:microsoft:sqlserver://<hostname>:1433;DatabaseName=<database>;SelectMethod=cursor", "jdbc:mysql://<hostname>/<database>?autoReconnect=true", "jdbc:oracle:thin:@<hostname>:1521:<database>", "jdbc:pointbase:<database>,database.home=<database>,create=true", "jdbc:postgresql://<hostname>:5432/<database>", "jdbc:postgresql:net//<hostname>/<database>", "jdbc:sybase:Tds:<hostname>:4100/<database>?ServiceName=<database>", "jdbc:weblogic:mssqlserver4:<database>@<hostname>:1433", "jdbc:odbc:<database>", "jdbc:sequelink://<hostname>:4003/[Oracle]", "jdbc:sequelink://<hostname>:4004/[Informix];Database=<database>", "jdbc:sequelink://<hostname>:4005/[Sybase];Database=<database>", "jdbc:sequelink://<hostname>:4006/[SQLServer];Database=<database>", "jdbc:sequelink://<hostname>:4011/[ODBC MS Access];Database=<database>", "jdbc:openlink://<hostname>/DSN=SQLServerDB/UID=sa/PWD=", "jdbc:solid://<hostname>:<port>/<UID>/<PWD>", "jdbc:dbaw://<hostname>:8889/<database>"};
   }

   ColorBuffer getColorBuffer() {
      return new ColorBuffer(this.getOpts().getColor());
   }

   ColorBuffer getColorBuffer(String msg) {
      return new ColorBuffer(msg, this.getOpts().getColor());
   }

   int initArgsFromCliVars(String[] args) {
      List<String> commands = Collections.emptyList();
      CliOptionsProcessor optionsProcessor = new CliOptionsProcessor();
      if (!optionsProcessor.process(args)) {
         return 1;
      } else {
         CommandLine commandLine = optionsProcessor.getCommandLine();
         Properties confProps = commandLine.getOptionProperties("hiveconf");

         for(String propKey : confProps.stringPropertyNames()) {
            this.setHiveConfVar(propKey, confProps.getProperty(propKey));
         }

         Properties hiveVars = commandLine.getOptionProperties("define");

         for(String propKey : hiveVars.stringPropertyNames()) {
            this.getOpts().getHiveConfVariables().put(propKey, hiveVars.getProperty(propKey));
         }

         Properties hiveVars2 = commandLine.getOptionProperties("hivevar");

         for(String propKey : hiveVars2.stringPropertyNames()) {
            this.getOpts().getHiveConfVariables().put(propKey, hiveVars2.getProperty(propKey));
         }

         this.getOpts().setScriptFile(commandLine.getOptionValue("f"));
         if (commandLine.getOptionValues("i") != null) {
            this.getOpts().setInitFiles(commandLine.getOptionValues("i"));
         }

         this.dbName = commandLine.getOptionValue("database");
         this.getOpts().setVerbose(Boolean.parseBoolean(commandLine.getOptionValue("verbose")));
         this.getOpts().setSilent(Boolean.parseBoolean(commandLine.getOptionValue("slient")));
         int code = 0;
         if (commandLine.getOptionValues("e") != null) {
            commands = Arrays.asList(commandLine.getOptionValues("e"));
         }

         if (!commands.isEmpty() && this.getOpts().getScriptFile() != null) {
            System.err.println("The '-e' and '-f' options cannot be specified simultaneously");
            optionsProcessor.printCliUsage();
            return 1;
         } else {
            if (!commands.isEmpty()) {
               this.embeddedConnect();
               this.connectDBInEmbededMode();
               Iterator<String> i = commands.iterator();

               while(i.hasNext()) {
                  String command = ((String)i.next()).toString();
                  this.debug(this.loc("executing-command", (Object)command));
                  if (!this.dispatch(command)) {
                     ++code;
                  }
               }

               this.exit = true;
            }

            return code;
         }
      }
   }

   int initArgs(String[] args) {
      List<String> commands = Collections.emptyList();

      CommandLine cl;
      BeelineParser beelineParser;
      try {
         beelineParser = new BeelineParser();
         cl = beelineParser.parse(options, args);
      } catch (ParseException e1) {
         this.output(e1.getMessage());
         this.usage();
         return -1;
      }

      boolean connSuccessful = this.connectUsingArgs(beelineParser, cl);
      if (!connSuccessful && !this.exit) {
         connSuccessful = this.defaultBeelineConnect();
      }

      int code = 0;
      if (cl.getOptionValues('e') != null) {
         commands = Arrays.asList(cl.getOptionValues('e'));
      }

      if (!commands.isEmpty() && this.getOpts().getScriptFile() != null) {
         this.error("The '-e' and '-f' options cannot be specified simultaneously");
         return 1;
      } else if (!commands.isEmpty() && !connSuccessful) {
         this.error("Cannot run commands specified using -e. No current connection");
         return 1;
      } else {
         if (!commands.isEmpty()) {
            Iterator<String> i = commands.iterator();

            while(i.hasNext()) {
               String command = ((String)i.next()).toString();
               this.debug(this.loc("executing-command", (Object)command));
               if (!this.dispatch(command)) {
                  ++code;
               }
            }

            this.exit = true;
         }

         return code;
      }
   }

   private boolean connectUsingArgs(BeelineParser beelineParser, CommandLine cl) {
      String driver = null;
      String user = null;
      String pass = "";
      String url = null;
      String auth = null;
      if (cl.hasOption("help")) {
         this.usage();
         this.getOpts().setHelpAsked(true);
         return true;
      } else {
         Properties hiveVars = cl.getOptionProperties("hivevar");

         for(String key : hiveVars.stringPropertyNames()) {
            this.getOpts().getHiveVariables().put(key, hiveVars.getProperty(key));
         }

         Properties hiveConfs = cl.getOptionProperties("hiveconf");

         for(String key : hiveConfs.stringPropertyNames()) {
            this.setHiveConfVar(key, hiveConfs.getProperty(key));
         }

         driver = cl.getOptionValue("d");
         auth = cl.getOptionValue("a");
         user = cl.getOptionValue("n");
         this.getOpts().setAuthType(auth);
         if (cl.hasOption("w")) {
            pass = this.obtainPasswordFromFile(cl.getOptionValue("w"));
         } else if (beelineParser.isPasswordOptionSet) {
            pass = cl.getOptionValue("p");
         }

         url = cl.getOptionValue("u");
         if (url == null && cl.hasOption("reconnect")) {
            url = this.getOpts().getLastConnectedUrl();
         }

         this.getOpts().setInitFiles(cl.getOptionValues("i"));
         this.getOpts().setScriptFile(cl.getOptionValue("f"));
         if (url != null) {
            String com;
            String comForDebug;
            if (pass != null) {
               com = this.constructCmd(url, user, pass, driver, false);
               comForDebug = this.constructCmd(url, user, pass, driver, true);
            } else {
               com = this.constructCmdUrl(url, user, driver, false);
               comForDebug = this.constructCmdUrl(url, user, driver, true);
            }

            this.debug(comForDebug);
            return this.dispatch(com);
         } else {
            String propertyFile = cl.getOptionValue("property-file");
            if (propertyFile != null) {
               try {
                  this.consoleReader = new ConsoleReader();
               } catch (IOException e) {
                  this.handleException(e);
               }

               if (!this.dispatch("!properties " + propertyFile)) {
                  this.exit = true;
                  return false;
               }
            }

            return false;
         }
      }
   }

   private void setHiveConfVar(String key, String val) {
      this.getOpts().getHiveConfVariables().put(key, val);
      if (ConfVars.HIVE_EXECUTION_ENGINE.varname.equals(key) && "mr".equals(val)) {
         this.info(HiveConf.generateMrDeprecationWarning());
      }

   }

   private String constructCmd(String url, String user, String pass, String driver, boolean stripPasswd) {
      return "!connect " + url + " " + (user != null && user.length() != 0 ? user : "''") + " " + (stripPasswd ? "[passwd stripped]" : (pass.length() == 0 ? "''" : pass)) + " " + (driver == null ? "" : driver);
   }

   private String constructCmdUrl(String url, String user, String driver, boolean stripPasswd) {
      StringBuilder command = new StringBuilder("!connect ");
      command.append(url);
      if (this.isTrailingSlashNeeded(url)) {
         command.append('/');
      }

      command.append(';');
      if (Utils.parsePropertyFromUrl(url, "user") == null) {
         command.append("user");
         command.append('=');
         command.append(user != null && user.length() != 0 ? user : "''");
      }

      if (stripPasswd) {
         int startIndex = command.indexOf("password=") + "password".length() + 2;
         if (startIndex != -1) {
            int endIndex = command.toString().indexOf(";", startIndex);
            command.replace(startIndex, endIndex == -1 ? command.length() : endIndex, "[passwd stripped]");
         }
      }

      if (Utils.parsePropertyFromUrl(url, "driver") == null && driver != null) {
         command.append(';');
         command.append("driver");
         command.append("=");
         command.append(driver);
      }

      return command.toString();
   }

   private boolean isTrailingSlashNeeded(String url) {
      if (url.toLowerCase().startsWith("jdbc:hive2://")) {
         return url.indexOf(47, "jdbc:hive2://".length()) < 0;
      } else {
         return false;
      }
   }

   private String obtainPasswordFromFile(String passwordFilePath) {
      try {
         Path path = Paths.get(passwordFilePath);
         byte[] passwordFileContents = Files.readAllBytes(path);
         return (new String(passwordFileContents, "UTF-8")).trim();
      } catch (Exception e) {
         throw new RuntimeException("Unable to read user password from the password file: " + passwordFilePath, e);
      }
   }

   public void updateOptsForCli() {
      this.getOpts().updateBeeLineOptsFromConf();
      this.getOpts().setShowHeader(false);
      this.getOpts().setOutputFormat("dsv");
      this.getOpts().setDelimiterForDSV(' ');
      this.getOpts().setNullEmptyString(true);
   }

   public int begin(String[] args, InputStream inputStream) throws IOException {
      try {
         this.getOpts().load();
      } catch (Exception var6) {
      }

      this.setupHistory();
      this.addBeelineShutdownHook();
      ConsoleReader reader = this.initializeConsoleReader(inputStream);
      if (this.isBeeLine) {
         int code = this.initArgs(args);
         if (code != 0) {
            return code;
         }
      } else {
         int code = this.initArgsFromCliVars(args);
         if (code != 0 || this.exit) {
            return code;
         }

         this.defaultConnect(false);
      }

      if (this.getOpts().isHelpAsked()) {
         return 0;
      } else if (this.getOpts().getScriptFile() != null) {
         return this.executeFile(this.getOpts().getScriptFile());
      } else {
         try {
            this.info(this.getApplicationTitle());
         } catch (Exception var5) {
         }

         return this.execute(reader, false);
      }
   }

   private boolean defaultBeelineConnect() {
      String url;
      try {
         url = this.getDefaultConnectionUrl();
         if (url == null) {
            this.debug("Default hs2 connection config file not found");
            return false;
         }
      } catch (BeelineHS2ConnectionFileParseException e) {
         this.error((Throwable)e);
         return false;
      }

      return this.dispatch("!connect " + url);
   }

   private String getDefaultConnectionUrl() throws BeelineHS2ConnectionFileParseException {
      HS2ConnectionFileParser userHS2ConnFileParser = this.getUserHS2ConnFileParser();
      if (!userHS2ConnFileParser.configExists()) {
         return null;
      } else {
         Properties userConnectionProperties = userHS2ConnFileParser.getConnectionProperties();
         HS2ConnectionFileParser hiveSiteParser = this.getHiveSiteHS2ConnectionFileParser();
         Properties hiveSiteConnectionProperties = hiveSiteParser.getConnectionProperties();

         for(String key : userConnectionProperties.stringPropertyNames()) {
            if (hiveSiteConnectionProperties.containsKey(key)) {
               this.debug("Overriding connection url property " + key + " from user connection configuration file");
            }

            hiveSiteConnectionProperties.setProperty(key, userConnectionProperties.getProperty(key));
         }

         return HS2ConnectionFileUtils.getUrl(hiveSiteConnectionProperties);
      }
   }

   @VisibleForTesting
   public HS2ConnectionFileParser getUserHS2ConnFileParser() {
      return new UserHS2ConnectionFileParser();
   }

   @VisibleForTesting
   public HS2ConnectionFileParser getHiveSiteHS2ConnectionFileParser() {
      return new HiveSiteHS2ConnectionFileParser();
   }

   int runInit() {
      String[] initFiles = this.getOpts().getInitFiles();
      if (initFiles != null && initFiles.length != 0) {
         int var3 = initFiles.length;
         byte var4 = 0;
         if (var4 < var3) {
            String initFile = initFiles[var4];
            this.info("Running init script " + initFile);

            int var6;
            try {
               var6 = this.executeFile(initFile);
            } finally {
               this.exit = false;
            }

            return var6;
         }
      }

      return 0;
   }

   private int embeddedConnect() {
      return !this.execCommandWithPrefix("!connect jdbc:hive2:// '' ''") ? 2 : 0;
   }

   private int connectDBInEmbededMode() {
      return this.dbName != null && !this.dbName.isEmpty() && !this.dispatch("use " + this.dbName + ";") ? 2 : 0;
   }

   public int defaultConnect(boolean exitOnError) {
      if (this.embeddedConnect() != 0 && exitOnError) {
         return 2;
      } else {
         return this.connectDBInEmbededMode() != 0 && exitOnError ? 2 : 0;
      }
   }

   private int executeFile(String fileName) {
      InputStream fileStream = null;

      byte fs;
      try {
         if (!this.isBeeLine) {
            org.apache.hadoop.fs.Path path = new org.apache.hadoop.fs.Path(fileName);
            HiveConf conf = this.getCommands().getHiveConf(true);
            FileSystem fs;
            if (!path.toUri().isAbsolute()) {
               fs = FileSystem.getLocal(conf);
               path = fs.makeQualified(path);
            } else {
               fs = FileSystem.get(path.toUri(), conf);
            }

            fileStream = fs.open(path);
         } else {
            fileStream = new FileInputStream(fileName);
         }

         int var11 = this.execute(this.initializeConsoleReader(fileStream), !this.getOpts().getForce());
         return var11;
      } catch (Throwable t) {
         this.handleException(t);
         fs = 2;
      } finally {
         IOUtils.closeStream(fileStream);
      }

      return fs;
   }

   private int execute(ConsoleReader reader, boolean exitOnError) {
      int lastExecutionResult = 0;

      while(!this.exit) {
         try {
            String line = this.getOpts().isSilent() && this.getOpts().getScriptFile() != null ? reader.readLine((String)null, '\u0000') : reader.readLine(this.getPrompt());
            if (line != null) {
               line = line.trim();
            }

            if (!this.dispatch(line)) {
               lastExecutionResult = 2;
               if (exitOnError) {
                  break;
               }
            } else if (line != null) {
               lastExecutionResult = 0;
            }
         } catch (Throwable t) {
            this.handleException(t);
            return 2;
         }
      }

      return lastExecutionResult;
   }

   public void close() {
      this.commands.closeall((String)null);
   }

   private void setupHistory() throws IOException {
      if (this.history == null) {
         this.history = new FileHistory(new File(this.getOpts().getHistoryFile()));
      }
   }

   private void addBeelineShutdownHook() throws IOException {
      ShutdownHookManager.addShutdownHook(this.getShutdownHook());
   }

   public ConsoleReader initializeConsoleReader(InputStream inputStream) throws IOException {
      if (inputStream != null) {
         InputStream inputStreamAppendedNewline = new SequenceInputStream(inputStream, new ByteArrayInputStream((new String("\n")).getBytes()));
         this.consoleReader = new ConsoleReader(inputStreamAppendedNewline, this.getOutputStream());
         this.consoleReader.setCopyPasteDetection(true);
      } else {
         this.consoleReader = new ConsoleReader();
      }

      this.consoleReader.setExpandEvents(false);

      try {
         this.consoleReader.setHistory(this.history);
      } catch (Exception e) {
         this.handleException(e);
      }

      if (!(inputStream instanceof FileInputStream) && !(inputStream instanceof FSDataInputStream)) {
         this.consoleReader.addCompleter(new BeeLineCompleter(this));
         return this.consoleReader;
      } else {
         return this.consoleReader;
      }
   }

   void usage() {
      this.output(this.loc("cmd-usage"));
   }

   public boolean execCommandWithPrefix(String line) {
      Map<String, CommandHandler> cmdMap = new TreeMap();
      line = line.substring(1);

      for(int i = 0; i < this.commandHandlers.length; ++i) {
         String match = this.commandHandlers[i].matches(line);
         if (match != null) {
            cmdMap.put(match, this.commandHandlers[i]);
         }
      }

      if (cmdMap.size() == 0) {
         return this.error(this.loc("unknown-command", (Object)line));
      } else if (cmdMap.size() > 1) {
         CommandHandler handler = (CommandHandler)cmdMap.get(line);
         if (handler == null) {
            return this.error(this.loc("multiple-matches", (Object)cmdMap.keySet().toString()));
         } else {
            return handler.execute(line);
         }
      } else {
         return ((CommandHandler)cmdMap.values().iterator().next()).execute(line);
      }
   }

   boolean dispatch(String line) {
      if (line == null) {
         this.exit = true;
         return true;
      } else if (line.trim().length() == 0) {
         return true;
      } else if (this.isComment(line)) {
         return true;
      } else {
         line = line.trim();
         if (this.scriptOutputFile != null) {
            this.scriptOutputFile.addLine(line);
         }

         if (this.isHelpRequest(line)) {
            line = "!help";
         }

         if (this.isBeeLine) {
            return line.startsWith("!") ? this.execCommandWithPrefix(line) : this.commands.sql(line, this.getOpts().getEntireLineAsCommand());
         } else {
            return this.commands.sql(line, this.getOpts().getEntireLineAsCommand());
         }
      }
   }

   boolean needsContinuation(String line) {
      if (this.isHelpRequest(line)) {
         return false;
      } else if (line.startsWith("!")) {
         return false;
      } else if (this.isComment(line)) {
         return false;
      } else {
         String trimmed = line.trim();
         if (trimmed.length() == 0) {
            return false;
         } else if (!this.getOpts().isAllowMultiLineCommand()) {
            return false;
         } else {
            return !trimmed.endsWith(";");
         }
      }
   }

   boolean isHelpRequest(String line) {
      return line.equals("?") || line.equalsIgnoreCase("help");
   }

   boolean isComment(String line) {
      String lineTrimmed = line.trim();
      return lineTrimmed.startsWith("#") || lineTrimmed.startsWith("--");
   }

   void output(String msg) {
      this.output(msg, true);
   }

   void info(String msg) {
      if (!this.getOpts().isSilent()) {
         this.output(msg, true, this.getErrorStream());
      }

   }

   void info(ColorBuffer msg) {
      if (!this.getOpts().isSilent()) {
         this.output(msg, true, this.getErrorStream());
      }

   }

   boolean error(String msg) {
      this.output(this.getColorBuffer().red(msg), true, this.getErrorStream());
      return false;
   }

   boolean error(Throwable t) {
      this.handleException(t);
      return false;
   }

   void debug(String msg) {
      if (this.getOpts().getVerbose()) {
         this.output(this.getColorBuffer().blue(msg), true, this.getErrorStream());
      }

   }

   void output(ColorBuffer msg) {
      this.output(msg, true);
   }

   void output(String msg, boolean newline, PrintStream out) {
      this.output(this.getColorBuffer(msg), newline, out);
   }

   void output(ColorBuffer msg, boolean newline) {
      this.output(msg, newline, this.getOutputStream());
   }

   void output(ColorBuffer msg, boolean newline, PrintStream out) {
      if (newline) {
         out.println(msg.getColor());
      } else {
         out.print(msg.getColor());
      }

      if (this.recordOutputFile != null) {
         if (newline) {
            this.recordOutputFile.addLine(msg.getMono());
         } else {
            this.recordOutputFile.print(msg.getMono());
         }

      }
   }

   void output(String msg, boolean newline) {
      this.output(this.getColorBuffer(msg), newline);
   }

   void autocommitStatus(Connection c) throws SQLException {
      this.info(this.loc("autocommit-status", (Object)(c.getAutoCommit() + "")));
   }

   boolean assertAutoCommit() {
      if (!this.assertConnection()) {
         return false;
      } else {
         try {
            return this.getDatabaseConnection().getConnection().getAutoCommit() ? this.error(this.loc("autocommit-needs-off")) : true;
         } catch (Exception e) {
            return this.error((Throwable)e);
         }
      }
   }

   boolean assertConnection() {
      try {
         if (this.getDatabaseConnection() != null && this.getDatabaseConnection().getConnection() != null) {
            return this.getDatabaseConnection().getConnection().isClosed() ? this.error(this.loc("connection-is-closed")) : true;
         } else {
            return this.error(this.loc("no-current-connection"));
         }
      } catch (SQLException var2) {
         return this.error(this.loc("no-current-connection"));
      }
   }

   void showWarnings() {
      try {
         if (this.getDatabaseConnection().getConnection() == null || !this.getOpts().getVerbose()) {
            return;
         }

         this.showWarnings(this.getDatabaseConnection().getConnection().getWarnings());
      } catch (Exception e) {
         this.handleException(e);
      }

   }

   void showWarnings(SQLWarning warn) {
      if (warn != null) {
         if (this.seenWarnings.get(warn) == null) {
            this.seenWarnings.put(warn, new Date());
            this.handleSQLException(warn);
         }

         SQLWarning next = warn.getNextWarning();
         if (next != warn) {
            this.showWarnings(next);
         }

      }
   }

   String getPrompt() {
      return this.isBeeLine ? this.getPromptForBeeline() : this.getPromptForCli();
   }

   String getPromptForCli() {
      HiveConf conf = this.getCommands().getHiveConf(true);
      String prompt = conf.getVar(ConfVars.CLIPROMPT);
      prompt = this.getCommands().substituteVariables(conf, prompt);
      return prompt + this.getFormattedDb() + "> ";
   }

   String getFormattedDb() {
      if (!this.getOpts().getShowDbInPrompt()) {
         return "";
      } else {
         String currDb = this.getCurrentDatabase();
         return currDb == null ? "" : " (" + currDb + ")";
      }
   }

   String getPromptForBeeline() {
      if (this.getDatabaseConnection() != null && this.getDatabaseConnection().getUrl() != null) {
         String printClosed = this.getDatabaseConnection().isClosed() ? " (closed)" : "";
         return getPromptForBeeline(this.getDatabaseConnections().getIndex() + ": " + this.getDatabaseConnection().getUrl()) + printClosed + this.getFormattedDb() + "> ";
      } else {
         return "beeline> ";
      }
   }

   static String getPromptForBeeline(String url) {
      if (url == null || url.length() == 0) {
         url = "beeline";
      }

      if (url.indexOf(";") > -1) {
         url = url.substring(0, url.indexOf(";"));
      }

      if (url.indexOf("?") > -1) {
         url = url.substring(0, url.indexOf("?"));
      }

      if (url.length() > 45) {
         url = url.substring(0, 45);
      }

      return url;
   }

   int getSize(ResultSet rs) {
      try {
         if (rs.getType() == 1003) {
            return -1;
         } else {
            rs.last();
            int total = rs.getRow();
            rs.beforeFirst();
            return total;
         }
      } catch (SQLException var3) {
         return -1;
      } catch (AbstractMethodError var4) {
         return -1;
      }
   }

   ResultSet getColumns(String table) throws SQLException {
      return !this.assertConnection() ? null : this.getDatabaseConnection().getDatabaseMetaData().getColumns(this.getDatabaseConnection().getDatabaseMetaData().getConnection().getCatalog(), (String)null, table, "%");
   }

   ResultSet getTables() throws SQLException {
      return !this.assertConnection() ? null : this.getDatabaseConnection().getDatabaseMetaData().getTables(this.getDatabaseConnection().getDatabaseMetaData().getConnection().getCatalog(), (String)null, "%", new String[]{"TABLE"});
   }

   String[] getColumnNames(DatabaseMetaData meta) throws SQLException {
      Set<String> names = new HashSet();
      this.info(this.loc("building-tables"));

      try {
         ResultSet columns = this.getColumns("%");

         try {
            int total = this.getSize(columns);
            int index = 0;

            while(columns.next()) {
               this.progress(index++, total);
               String name = columns.getString("TABLE_NAME");
               names.add(name);
               names.add(columns.getString("COLUMN_NAME"));
               names.add(columns.getString("TABLE_NAME") + "." + columns.getString("COLUMN_NAME"));
            }

            this.progress(index, index);
         } finally {
            columns.close();
         }

         this.info(this.loc("done"));
         return (String[])names.toArray(new String[0]);
      } catch (Throwable t) {
         this.handleException(t);
         return new String[0];
      }
   }

   String[] split(String line) {
      return this.split(line, " ");
   }

   String dequote(String str) {
      if (str == null) {
         return null;
      } else {
         while(str.startsWith("'") && str.endsWith("'") || str.startsWith("\"") && str.endsWith("\"")) {
            str = str.substring(1, str.length() - 1);
         }

         return str;
      }
   }

   String[] split(String line, String delim) {
      StringTokenizer tok = new StringTokenizer(line, delim);
      String[] ret = new String[tok.countTokens()];

      String t;
      for(int index = 0; tok.hasMoreTokens(); ret[index++] = t) {
         t = tok.nextToken();
         t = this.dequote(t);
      }

      return ret;
   }

   static Map map(Object[] obs) {
      Map<Object, Object> m = new HashMap();

      for(int i = 0; i < obs.length - 1; i += 2) {
         m.put(obs[i], obs[i + 1]);
      }

      return Collections.unmodifiableMap(m);
   }

   static boolean getMoreResults(Statement stmnt) {
      try {
         return stmnt.getMoreResults();
      } catch (Throwable var2) {
         return false;
      }
   }

   static String xmlattrencode(String str) {
      str = replace(str, "\"", "&quot;");
      str = replace(str, "<", "&lt;");
      return str;
   }

   static String replace(String source, String from, String to) {
      if (source == null) {
         return null;
      } else if (from.equals(to)) {
         return source;
      } else {
         StringBuilder replaced = new StringBuilder();

         int index;
         for(index = -1; (index = source.indexOf(from)) != -1; source = source.substring(index + from.length())) {
            replaced.append(source.substring(0, index));
            replaced.append(to);
         }

         replaced.append(source);
         return replaced.toString();
      }
   }

   String[] split(String line, int assertLen, String usage) {
      String[] ret = this.split(line);
      if (ret.length != assertLen) {
         this.error(usage);
         return null;
      } else {
         return ret;
      }
   }

   String wrap(String toWrap, int len, int start) {
      StringBuilder buff = new StringBuilder();
      StringBuilder line = new StringBuilder();
      char[] head = new char[start];
      Arrays.fill(head, ' ');

      String next;
      for(StringTokenizer tok = new StringTokenizer(toWrap, " "); tok.hasMoreTokens(); line.append(line.length() == 0 ? "" : " ").append(next)) {
         next = tok.nextToken();
         if (line.length() + next.length() > len) {
            buff.append(line).append(separator).append(head);
            line.setLength(0);
         }
      }

      buff.append(line);
      return buff.toString();
   }

   void progress(int cur, int max) {
      StringBuilder out = new StringBuilder();
      if (this.lastProgress != null) {
         char[] back = new char[this.lastProgress.length()];
         Arrays.fill(back, '\b');
         out.append(back);
      }

      String progress = cur + "/" + (max == -1 ? "?" : "" + max) + " " + (max == -1 ? "(??%)" : "(" + cur * 100 / (max == 0 ? 1 : max) + "%)");
      if (cur >= max && max != -1) {
         progress = progress + " " + this.loc("done") + separator;
         this.lastProgress = null;
      } else {
         this.lastProgress = progress;
      }

      out.append(progress);
      this.outputStream.print(out.toString());
      this.outputStream.flush();
   }

   void handleException(Throwable e) {
      while(e instanceof InvocationTargetException) {
         e = ((InvocationTargetException)e).getTargetException();
      }

      if (e instanceof SQLException) {
         this.handleSQLException((SQLException)e);
      } else if (e instanceof EOFException) {
         this.setExit(true);
      } else if (!this.getOpts().getVerbose()) {
         if (e.getMessage() == null) {
            this.error(e.getClass().getName());
         } else {
            this.error(e.getMessage());
         }
      } else {
         e.printStackTrace(this.getErrorStream());
      }

   }

   void handleSQLException(SQLException e) {
      if (!(e instanceof SQLWarning) || this.getOpts().getShowWarnings()) {
         if (e.getCause() instanceof TTransportException) {
            switch (((TTransportException)e.getCause()).getType()) {
               case 0:
                  this.error(this.loc("hs2-unknown-connection-problem"));
                  break;
               case 1:
                  this.error(this.loc("hs2-could-not-open-connection"));
                  break;
               case 2:
                  this.error(this.loc("hs2-connection-already-open"));
                  break;
               case 3:
                  this.error(this.loc("hs2-connection-timed-out"));
                  break;
               case 4:
                  this.error(this.loc("hs2-unexpected-end-of-file"));
                  break;
               default:
                  this.error(this.loc("hs2-unexpected-error"));
            }
         }

         this.error(this.loc(e instanceof SQLWarning ? "Warning" : "Error", new Object[]{e.getMessage() == null ? "" : e.getMessage().trim(), e.getSQLState() == null ? "" : e.getSQLState().trim(), new Integer(e.getErrorCode())}));
         if (this.getOpts().getVerbose()) {
            e.printStackTrace(this.getErrorStream());
         }

         if (this.getOpts().getShowNestedErrs()) {
            for(SQLException nested = e.getNextException(); nested != null && nested != e; nested = nested.getNextException()) {
               this.handleSQLException(nested);
            }

         }
      }
   }

   boolean scanForDriver(String url) {
      try {
         if (this.findRegisteredDriver(url) != null) {
            return true;
         } else {
            this.scanDrivers(true);
            if (this.findRegisteredDriver(url) != null) {
               return true;
            } else {
               this.scanDrivers(false);
               if (this.findRegisteredDriver(url) != null) {
                  return true;
               } else {
                  return this.findLocalDriver(url) != null;
               }
            }
         }
      } catch (Exception e) {
         this.debug(e.toString());
         return false;
      }
   }

   private Driver findRegisteredDriver(String url) {
      Enumeration drivers = DriverManager.getDrivers();

      while(drivers != null && drivers.hasMoreElements()) {
         Driver driver = (Driver)drivers.nextElement();

         try {
            if (driver.acceptsURL(url)) {
               return driver;
            }
         } catch (Exception var5) {
         }
      }

      return null;
   }

   public Driver findLocalDriver(String url) throws Exception {
      if (this.drivers == null) {
         return null;
      } else {
         for(Driver d : this.drivers) {
            try {
               String clazzName = d.getClass().getName();
               Driver driver = (Driver)Class.forName(clazzName, true, Thread.currentThread().getContextClassLoader()).newInstance();
               if (driver.acceptsURL(url) && this.isSupportedLocalDriver(driver)) {
                  return driver;
               }
            } catch (SQLException e) {
               this.error((Throwable)e);
               throw new Exception(e);
            }
         }

         return null;
      }
   }

   public boolean isSupportedLocalDriver(Driver driver) {
      String driverName = driver.getClass().getName();

      for(String name : this.supportedLocalDriver) {
         if (name.equals(driverName)) {
            return true;
         }
      }

      return false;
   }

   public void addLocalDriverClazz(String driverClazz) {
      this.supportedLocalDriver.add(driverClazz);
   }

   Driver[] scanDrivers(String line) throws IOException {
      return this.scanDrivers(false);
   }

   Driver[] scanDrivers(boolean knownOnly) throws IOException {
      long start = System.currentTimeMillis();
      Set<String> classNames = new HashSet();
      if (!knownOnly) {
         classNames.addAll(Arrays.asList(ClassNameCompleter.getClassNames()));
      }

      classNames.addAll(KNOWN_DRIVERS);
      Set driverClasses = new HashSet();
      Iterator<String> i = classNames.iterator();

      while(i.hasNext()) {
         String className = ((String)i.next()).toString();
         if (className.toLowerCase().indexOf("driver") != -1) {
            try {
               Class c = Class.forName(className, false, Thread.currentThread().getContextClassLoader());
               if (Driver.class.isAssignableFrom(c) && !Modifier.isAbstract(c.getModifiers())) {
                  driverClasses.add(c.newInstance());
               }
            } catch (Throwable var9) {
            }
         }
      }

      this.info("scan complete in " + (System.currentTimeMillis() - start) + "ms");
      return (Driver[])driverClasses.toArray(new Driver[0]);
   }

   private Driver[] scanDriversOLD(String line) {
      long start = System.currentTimeMillis();
      Set<String> paths = new HashSet();
      Set driverClasses = new HashSet();
      StringTokenizer tok = new StringTokenizer(System.getProperty("java.ext.dirs"), System.getProperty("path.separator"));

      while(tok.hasMoreTokens()) {
         File[] files = (new File(tok.nextToken())).listFiles();

         for(int i = 0; files != null && i < files.length; ++i) {
            paths.add(files[i].getAbsolutePath());
         }
      }

      tok = new StringTokenizer(System.getProperty("java.class.path"), System.getProperty("path.separator"));

      while(tok.hasMoreTokens()) {
         paths.add((new File(tok.nextToken())).getAbsolutePath());
      }

      Iterator<String> i = paths.iterator();

      while(i.hasNext()) {
         File f = new File((String)i.next());
         this.output(this.getColorBuffer().pad((String)this.loc("scanning", (Object)f.getAbsolutePath()), 60), false);

         try {
            ZipFile zf = new ZipFile(f);
            int total = zf.size();
            int index = 0;
            Enumeration zfEnum = zf.entries();

            while(zfEnum.hasMoreElements()) {
               ZipEntry entry = (ZipEntry)zfEnum.nextElement();
               String name = entry.getName();
               this.progress(index++, total);
               if (name.endsWith(".class")) {
                  name = name.replace('/', '.');
                  name = name.substring(0, name.length() - 6);

                  try {
                     if (name.toLowerCase().indexOf("driver") != -1) {
                        Class c = Class.forName(name, false, this.getClass().getClassLoader());
                        if (Driver.class.isAssignableFrom(c) && !Modifier.isAbstract(c.getModifiers())) {
                           try {
                              Class.forName(name);
                           } catch (Exception var16) {
                           }

                           driverClasses.add(c.newInstance());
                        }
                     }
                  } catch (Throwable var17) {
                  }
               }
            }

            this.progress(total, total);
         } catch (Exception var18) {
         }
      }

      this.info("scan complete in " + (System.currentTimeMillis() - start) + "ms");
      return (Driver[])driverClasses.toArray(new Driver[0]);
   }

   int print(ResultSet rs) throws SQLException {
      String format = this.getOpts().getOutputFormat();
      OutputFormat f = (OutputFormat)this.formats.get(format);
      if (f == null) {
         this.error(this.loc("unknown-format", new Object[]{format, this.formats.keySet()}));
         f = new TableOutputFormat(this);
      }

      Rows rows;
      if (f instanceof TableOutputFormat) {
         if (this.getOpts().getIncremental()) {
            rows = new IncrementalRowsWithNormalization(this, rs);
         } else {
            rows = new BufferedRows(this, rs);
         }
      } else {
         rows = new IncrementalRows(this, rs);
      }

      return f.print(rows);
   }

   Statement createStatement() throws SQLException {
      Statement stmnt = this.getDatabaseConnection().getConnection().createStatement();
      if (this.getOpts().timeout > -1) {
         stmnt.setQueryTimeout(this.getOpts().timeout);
      }

      if (this.signalHandler != null) {
         this.signalHandler.setStatement(stmnt);
      }

      return stmnt;
   }

   void runBatch(List statements) {
      try {
         Statement stmnt = this.createStatement();

         try {
            Iterator<String> i = statements.iterator();

            while(i.hasNext()) {
               stmnt.addBatch(((String)i.next()).toString());
            }

            int[] counts = stmnt.executeBatch();
            this.output(this.getColorBuffer().pad((ColorBuffer)this.getColorBuffer().bold("COUNT"), 8).append(this.getColorBuffer().bold("STATEMENT")));

            for(int i = 0; counts != null && i < counts.length; ++i) {
               this.output(this.getColorBuffer().pad((String)(counts[i] + ""), 8).append(((String)statements.get(i)).toString()));
            }
         } finally {
            try {
               stmnt.close();
            } catch (Exception var11) {
            }

         }
      } catch (Exception e) {
         this.handleException(e);
      }

   }

   public int runCommands(String[] cmds) {
      return this.runCommands(Arrays.asList(cmds));
   }

   public int runCommands(List cmds) {
      int successCount = 0;

      try {
         for(String cmd : cmds) {
            this.info(this.getColorBuffer().pad((String)">>>", 5).append(cmd));
            if (!this.dispatch(cmd) && !this.getOpts().getForce()) {
               this.error(this.loc("abort-on-error", (Object)cmd));
               return successCount;
            }

            ++successCount;
         }
      } catch (Exception e) {
         this.handleException(e);
      }

      return successCount;
   }

   void setCompletions() throws SQLException, IOException {
      if (this.getDatabaseConnection() != null) {
         this.getDatabaseConnection().setCompletions(this.getOpts().getFastConnect());
      }

   }

   public BeeLineOpts getOpts() {
      return this.opts;
   }

   DatabaseConnections getDatabaseConnections() {
      return this.connections;
   }

   Runnable getShutdownHook() {
      return this.shutdownHook;
   }

   Completer getCommandCompletor() {
      return this.beeLineCommandCompleter;
   }

   public boolean isExit() {
      return this.exit;
   }

   public void setExit(boolean exit) {
      this.exit = exit;
   }

   Collection getDrivers() {
      return this.drivers;
   }

   void setDrivers(Collection drivers) {
      this.drivers = drivers;
   }

   public static String getSeparator() {
      return separator;
   }

   Commands getCommands() {
      return this.commands;
   }

   OutputFile getScriptOutputFile() {
      return this.scriptOutputFile;
   }

   void setScriptOutputFile(OutputFile script) {
      this.scriptOutputFile = script;
   }

   OutputFile getRecordOutputFile() {
      return this.recordOutputFile;
   }

   void setRecordOutputFile(OutputFile record) {
      this.recordOutputFile = record;
   }

   public void setOutputStream(PrintStream outputStream) {
      this.outputStream = new PrintStream(outputStream, true);
   }

   PrintStream getOutputStream() {
      return this.outputStream;
   }

   public void setErrorStream(PrintStream errorStream) {
      this.errorStream = new PrintStream(errorStream, true);
   }

   PrintStream getErrorStream() {
      return this.errorStream;
   }

   ConsoleReader getConsoleReader() {
      return this.consoleReader;
   }

   void setConsoleReader(ConsoleReader reader) {
      this.consoleReader = reader;
   }

   List getBatch() {
      return this.batch;
   }

   void setBatch(List batch) {
      this.batch = batch;
   }

   protected Reflector getReflector() {
      return this.reflector;
   }

   public boolean isBeeLine() {
      return this.isBeeLine;
   }

   public void setBeeLine(boolean isBeeLine) {
      this.isBeeLine = isBeeLine;
   }

   public String getCurrentDatabase() {
      if (this.currentDatabase == null) {
         this.currentDatabase = "default";
      }

      return this.currentDatabase;
   }

   public void setCurrentDatabase(String currentDatabase) {
      this.currentDatabase = currentDatabase;
   }

   static {
      try {
         Class.forName("jline.console.ConsoleReader");
      } catch (Throwable var1) {
         throw new ExceptionInInitializerError("jline-missing");
      }

      Options var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("driver class");
      OptionBuilder.withDescription("the driver class to use");
      var10000.addOption(OptionBuilder.create('d'));
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("database url");
      OptionBuilder.withDescription("the JDBC URL to connect to");
      var10000.addOption(OptionBuilder.create('u'));
      var10000 = options;
      OptionBuilder.withLongOpt("reconnect");
      OptionBuilder.withDescription("Reconnect to last saved connect url (in conjunction with !save)");
      var10000.addOption(OptionBuilder.create('r'));
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("username");
      OptionBuilder.withDescription("the username to connect as");
      var10000.addOption(OptionBuilder.create('n'));
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("password");
      OptionBuilder.withDescription("the password to connect as");
      OptionBuilder.hasOptionalArg();
      var10000.addOption(OptionBuilder.create('p'));
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("password-file");
      OptionBuilder.withDescription("the password file to read password from");
      OptionBuilder.withLongOpt("password-file");
      var10000.addOption(OptionBuilder.create('w'));
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("authType");
      OptionBuilder.withDescription("the authentication type");
      var10000.addOption(OptionBuilder.create('a'));
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("init");
      OptionBuilder.withDescription("script file for initialization");
      var10000.addOption(OptionBuilder.create('i'));
      var10000 = options;
      OptionBuilder.hasArgs();
      OptionBuilder.withArgName("query");
      OptionBuilder.withDescription("query that should be executed");
      var10000.addOption(OptionBuilder.create('e'));
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withArgName("file");
      OptionBuilder.withDescription("script file that should be executed");
      var10000.addOption(OptionBuilder.create('f'));
      var10000 = options;
      OptionBuilder.withLongOpt("help");
      OptionBuilder.withDescription("display this message");
      var10000.addOption(OptionBuilder.create('h'));
      var10000 = options;
      OptionBuilder.withValueSeparator();
      OptionBuilder.hasArgs(2);
      OptionBuilder.withArgName("key=value");
      OptionBuilder.withLongOpt("hivevar");
      OptionBuilder.withDescription("hive variable name and value");
      var10000.addOption(OptionBuilder.create());
      var10000 = options;
      OptionBuilder.withValueSeparator();
      OptionBuilder.hasArgs(2);
      OptionBuilder.withArgName("property=value");
      OptionBuilder.withLongOpt("hiveconf");
      OptionBuilder.withDescription("Use value for given property");
      var10000.addOption(OptionBuilder.create());
      var10000 = options;
      OptionBuilder.hasArg();
      OptionBuilder.withLongOpt("property-file");
      OptionBuilder.withDescription("the file to read configuration properties from");
      var10000.addOption(OptionBuilder.create());
   }

   public class BeelineParser extends GnuParser {
      private boolean isPasswordOptionSet = false;

      protected void processOption(String arg, ListIterator iter) throws ParseException {
         if (this.isBeeLineOpt(arg)) {
            this.processBeeLineOpt(arg);
         } else {
            if ("-p".equals(arg)) {
               this.isPasswordOptionSet = true;
               if (iter.hasNext()) {
                  String next = (String)iter.next();
                  if (this.isBeeLineOpt(next)) {
                     this.processBeeLineOpt(next);
                     return;
                  }

                  iter.previous();
               }
            }

            super.processOption(arg, iter);
         }

      }

      private void processBeeLineOpt(String arg) {
         String stripped = arg.substring(2, arg.length());
         String[] parts = BeeLine.this.split(stripped, "=");
         BeeLine.this.debug(BeeLine.this.loc("setting-prop", (Object)Arrays.asList(parts)));
         if (parts.length >= 2) {
            BeeLine.this.getOpts().set(parts[0], parts[1], true);
         } else {
            BeeLine.this.getOpts().set(parts[0], "true", true);
         }

      }

      private boolean isBeeLineOpt(String arg) {
         return arg.startsWith("--") && !"--hivevar".equals(arg) && !"--hiveconf".equals(arg) && !"--help".equals(arg) && !"--property-file".equals(arg);
      }
   }
}
