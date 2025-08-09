package org.apache.hive.beeline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import jline.Terminal;
import jline.TerminalFactory;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;

class BeeLineOpts implements Completer {
   public static final int DEFAULT_MAX_WIDTH = 80;
   public static final int DEFAULT_MAX_HEIGHT = 80;
   public static final int DEFAULT_HEADER_INTERVAL = 100;
   public static final String DEFAULT_ISOLATION_LEVEL = "TRANSACTION_REPEATABLE_READ";
   public static final String PROPERTY_PREFIX = "beeline.";
   public static final String PROPERTY_NAME_EXIT = "beeline.system.exit";
   public static final String DEFAULT_NULL_STRING = "NULL";
   public static final char DEFAULT_DELIMITER_FOR_DSV = '|';
   public static final int DEFAULT_MAX_COLUMN_WIDTH = 50;
   public static final int DEFAULT_INCREMENTAL_BUFFER_ROWS = 1000;
   public static final String URL_ENV_PREFIX = "BEELINE_URL_";
   private final BeeLine beeLine;
   private boolean autosave = false;
   private boolean silent = false;
   private boolean color = false;
   private boolean showHeader = true;
   private boolean showDbInPrompt = false;
   private int headerInterval = 100;
   private boolean fastConnect = true;
   private boolean autoCommit = true;
   private boolean verbose = false;
   private boolean force = false;
   private boolean incremental = true;
   private int incrementalBufferRows = 1000;
   private boolean showWarnings = false;
   private boolean showNestedErrs = false;
   private boolean showElapsedTime = true;
   private boolean entireLineAsCommand = false;
   private String numberFormat = "default";
   private final Terminal terminal = TerminalFactory.get();
   private int maxWidth = 80;
   private int maxHeight = 80;
   private int maxColumnWidth = 50;
   int timeout = -1;
   private String isolation = "TRANSACTION_REPEATABLE_READ";
   private String outputFormat = "table";
   private HiveConf conf;
   private boolean trimScripts = true;
   private boolean allowMultiLineCommand = true;
   private boolean nullEmptyString = false;
   private boolean truncateTable = false;
   private final File rcFile = new File(this.saveDir(), "beeline.properties");
   private String historyFile = (new File(this.saveDir(), "history")).getAbsolutePath();
   private int maxHistoryRows = 500;
   private String scriptFile = null;
   private String[] initFiles = null;
   private String authType = null;
   private char delimiterForDSV = '|';
   private Map hiveVariables = new HashMap();
   private Map hiveConfVariables = new HashMap();
   private boolean helpAsked;
   private String lastConnectedUrl = null;
   private TreeSet cachedPropertyNameSet = null;
   public static Env env = new Env() {
      public String get(String envVar) {
         return System.getenv(envVar);
      }
   };

   public BeeLineOpts(BeeLine beeLine, Properties props) {
      this.beeLine = beeLine;
      if (this.terminal.getWidth() > 0) {
         this.maxWidth = this.terminal.getWidth();
      }

      if (this.terminal.getHeight() > 0) {
         this.maxHeight = this.terminal.getHeight();
      }

      this.loadProperties(props);
   }

   public Completer[] optionCompleters() {
      return new Completer[]{this};
   }

   public String[] possibleSettingValues() {
      List<String> vals = new LinkedList();
      vals.addAll(Arrays.asList("yes", "no"));
      return (String[])vals.toArray(new String[vals.size()]);
   }

   public File saveDir() {
      String dir = System.getProperty("beeline.rcfile");
      if (dir != null && dir.length() > 0) {
         return new File(dir);
      } else {
         File f = (new File(System.getProperty("user.home"), (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1 ? "" : ".") + "beeline")).getAbsoluteFile();

         try {
            f.mkdirs();
         } catch (Exception var4) {
         }

         return f;
      }
   }

   public int complete(String buf, int pos, List cand) {
      try {
         return (new StringsCompleter(this.propertyNames())).complete(buf, pos, cand);
      } catch (Exception e) {
         this.beeLine.handleException(e);
         return -1;
      }
   }

   public void save() throws IOException {
      OutputStream out = new FileOutputStream(this.rcFile);
      Throwable var2 = null;

      try {
         this.save(out);
      } catch (Throwable var11) {
         var2 = var11;
         throw var11;
      } finally {
         if (out != null) {
            if (var2 != null) {
               try {
                  out.close();
               } catch (Throwable var10) {
                  var2.addSuppressed(var10);
               }
            } else {
               out.close();
            }
         }

      }

   }

   public void save(OutputStream out) throws IOException {
      try {
         Properties props = this.toProperties();
         props.remove("beeline.maxwidth");
         props.store(out, this.beeLine.getApplicationTitle());
      } catch (Exception e) {
         this.beeLine.handleException(e);
      }

   }

   String[] propertyNames() throws IllegalAccessException, InvocationTargetException {
      Set<String> names = this.propertyNamesSet();
      return (String[])names.toArray(new String[names.size()]);
   }

   Set propertyNamesSet() throws IllegalAccessException, InvocationTargetException {
      if (this.cachedPropertyNameSet == null) {
         TreeSet<String> names = new TreeSet();
         Method[] m = this.getClass().getDeclaredMethods();

         for(int i = 0; m != null && i < m.length; ++i) {
            if (m[i].getName().startsWith("get") && m[i].getAnnotation(Ignore.class) == null && m[i].getParameterTypes().length == 0) {
               String propName = m[i].getName().substring(3).toLowerCase();
               names.add(propName);
            }
         }

         this.cachedPropertyNameSet = names;
      }

      return this.cachedPropertyNameSet;
   }

   public Properties toProperties() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
      Properties props = new Properties();
      String[] names = this.propertyNames();

      for(int i = 0; names != null && i < names.length; ++i) {
         Object o = this.beeLine.getReflector().invoke(this, "get" + names[i], (Object[])(new Object[0]));
         props.setProperty("beeline." + names[i], o == null ? "" : o.toString());
      }

      this.beeLine.debug("properties: " + props.toString());
      return props;
   }

   public void load() throws IOException {
      InputStream in = new FileInputStream(this.rcFile);
      Throwable var2 = null;

      try {
         this.load(in);
      } catch (Throwable var11) {
         var2 = var11;
         throw var11;
      } finally {
         if (in != null) {
            if (var2 != null) {
               try {
                  in.close();
               } catch (Throwable var10) {
                  var2.addSuppressed(var10);
               }
            } else {
               in.close();
            }
         }

      }

   }

   public void load(InputStream fin) throws IOException {
      Properties p = new Properties();
      p.load(fin);
      this.loadProperties(p);
   }

   public void updateBeeLineOptsFromConf() {
      if (!this.beeLine.isBeeLine()) {
         if (this.conf == null) {
            this.conf = this.beeLine.getCommands().getHiveConf(false);
         }

         this.setForce(HiveConf.getBoolVar(this.conf, ConfVars.CLIIGNOREERRORS));
      }

   }

   public void setHiveConf(HiveConf conf) {
      this.conf = conf;
   }

   public void loadProperties(Properties props) {
      for(Object element : props.keySet()) {
         String key = element.toString();
         if (!key.equals("beeline.system.exit") && key.startsWith("beeline.")) {
            this.set(key.substring("beeline.".length()), props.getProperty(key));
         }
      }

   }

   public void set(String key, String value) {
      this.set(key, value, false);
   }

   public boolean set(String key, String value, boolean quiet) {
      try {
         this.beeLine.getReflector().invoke(this, "set" + key, (Object[])(new Object[]{value}));
         return true;
      } catch (Exception e) {
         if (!quiet) {
            this.beeLine.error(this.beeLine.loc("error-setting", new Object[]{key, e}));
         }

         return false;
      }
   }

   public void setFastConnect(boolean fastConnect) {
      this.fastConnect = fastConnect;
   }

   public String getAuthType() {
      return this.authType;
   }

   public void setAuthType(String authType) {
      this.authType = authType;
   }

   public boolean getFastConnect() {
      return this.fastConnect;
   }

   public void setAutoCommit(boolean autoCommit) {
      this.autoCommit = autoCommit;
   }

   public boolean getAutoCommit() {
      return this.autoCommit;
   }

   public void setVerbose(boolean verbose) {
      this.verbose = verbose;
   }

   public boolean getVerbose() {
      return this.verbose;
   }

   public void setShowWarnings(boolean showWarnings) {
      this.showWarnings = showWarnings;
   }

   public boolean getShowWarnings() {
      return this.showWarnings;
   }

   public void setShowNestedErrs(boolean showNestedErrs) {
      this.showNestedErrs = showNestedErrs;
   }

   public boolean getShowNestedErrs() {
      return this.showNestedErrs;
   }

   public void setShowElapsedTime(boolean showElapsedTime) {
      this.showElapsedTime = showElapsedTime;
   }

   public boolean getShowElapsedTime() {
      return this.showElapsedTime;
   }

   public void setNumberFormat(String numberFormat) {
      this.numberFormat = numberFormat;
   }

   public String getNumberFormat() {
      return this.numberFormat;
   }

   public void setMaxWidth(int maxWidth) {
      this.maxWidth = maxWidth;
   }

   public int getMaxWidth() {
      return this.maxWidth;
   }

   public void setMaxColumnWidth(int maxColumnWidth) {
      this.maxColumnWidth = maxColumnWidth;
   }

   public int getMaxColumnWidth() {
      return this.maxColumnWidth;
   }

   public void setTimeout(int timeout) {
      this.timeout = timeout;
   }

   public int getTimeout() {
      return this.timeout;
   }

   public void setIsolation(String isolation) {
      this.isolation = isolation;
   }

   public String getIsolation() {
      return this.isolation;
   }

   public void setEntireLineAsCommand(boolean entireLineAsCommand) {
      this.entireLineAsCommand = entireLineAsCommand;
   }

   public boolean getEntireLineAsCommand() {
      return this.entireLineAsCommand;
   }

   public void setHistoryFile(String historyFile) {
      this.historyFile = historyFile;
   }

   public String getHistoryFile() {
      return this.historyFile;
   }

   public void setMaxHistoryRows(int numRows) {
      this.maxHistoryRows = numRows;
   }

   public int getMaxHistoryRows() {
      return this.maxHistoryRows;
   }

   public void setScriptFile(String scriptFile) {
      this.scriptFile = scriptFile;
   }

   public String getScriptFile() {
      return this.scriptFile;
   }

   public String[] getInitFiles() {
      return this.initFiles;
   }

   public void setInitFiles(String[] initFiles) {
      this.initFiles = initFiles;
   }

   public void setColor(boolean color) {
      this.color = color;
   }

   public boolean getColor() {
      return this.color;
   }

   public void setShowHeader(boolean showHeader) {
      this.showHeader = showHeader;
   }

   public boolean getShowHeader() {
      if (this.beeLine.isBeeLine()) {
         return this.showHeader;
      } else {
         HiveConf conf = this.beeLine.getCommands().getHiveConf(true);
         boolean header = HiveConf.getBoolVar(conf, ConfVars.HIVE_CLI_PRINT_HEADER);
         return header;
      }
   }

   public void setShowDbInPrompt(boolean showDbInPrompt) {
      this.showDbInPrompt = showDbInPrompt;
   }

   public boolean getShowDbInPrompt() {
      if (this.beeLine.isBeeLine()) {
         return this.showDbInPrompt;
      } else {
         HiveConf conf = this.beeLine.getCommands().getHiveConf(true);
         return HiveConf.getBoolVar(conf, ConfVars.CLIPRINTCURRENTDB);
      }
   }

   public void setHeaderInterval(int headerInterval) {
      this.headerInterval = headerInterval;
   }

   public int getHeaderInterval() {
      return this.headerInterval;
   }

   public void setForce(boolean force) {
      this.force = force;
   }

   public boolean getForce() {
      return this.force;
   }

   public void setIncremental(boolean incremental) {
      this.incremental = incremental;
   }

   public boolean getIncremental() {
      return this.incremental;
   }

   public void setIncrementalBufferRows(int incrementalBufferRows) {
      this.incrementalBufferRows = incrementalBufferRows;
   }

   public int getIncrementalBufferRows() {
      return this.incrementalBufferRows;
   }

   public void setSilent(boolean silent) {
      this.silent = silent;
   }

   public boolean isSilent() {
      return this.silent;
   }

   public void setAutosave(boolean autosave) {
      this.autosave = autosave;
   }

   public boolean getAutosave() {
      return this.autosave;
   }

   public void setOutputFormat(String outputFormat) {
      if (outputFormat.equalsIgnoreCase("csv") || outputFormat.equalsIgnoreCase("tsv")) {
         this.beeLine.info("Format " + outputFormat + " is deprecated, please use " + outputFormat + "2");
      }

      this.outputFormat = outputFormat;
   }

   public String getOutputFormat() {
      return this.outputFormat;
   }

   public void setTrimScripts(boolean trimScripts) {
      this.trimScripts = trimScripts;
   }

   public boolean getTrimScripts() {
      return this.trimScripts;
   }

   public void setMaxHeight(int maxHeight) {
      this.maxHeight = maxHeight;
   }

   public int getMaxHeight() {
      return this.maxHeight;
   }

   @BeeLineOpts.Ignore
   public File getPropertiesFile() {
      return this.rcFile;
   }

   public Map getHiveVariables() {
      return this.hiveVariables;
   }

   public void setHiveVariables(Map hiveVariables) {
      this.hiveVariables = hiveVariables;
   }

   public boolean isAllowMultiLineCommand() {
      return this.allowMultiLineCommand;
   }

   public void setAllowMultiLineCommand(boolean allowMultiLineCommand) {
      this.allowMultiLineCommand = allowMultiLineCommand;
   }

   public boolean getNullEmptyString() {
      return this.nullEmptyString;
   }

   public void setNullEmptyString(boolean nullStringEmpty) {
      this.nullEmptyString = nullStringEmpty;
   }

   @BeeLineOpts.Ignore
   public String getNullString() {
      return this.nullEmptyString ? "" : "NULL";
   }

   public Map getHiveConfVariables() {
      return this.hiveConfVariables;
   }

   public void setHiveConfVariables(Map hiveConfVariables) {
      this.hiveConfVariables = hiveConfVariables;
   }

   public boolean getTruncateTable() {
      return this.truncateTable;
   }

   public void setTruncateTable(boolean truncateTable) {
      this.truncateTable = truncateTable;
   }

   public char getDelimiterForDSV() {
      return this.delimiterForDSV;
   }

   public void setDelimiterForDSV(char delimiterForDSV) {
      this.delimiterForDSV = delimiterForDSV;
   }

   @BeeLineOpts.Ignore
   public HiveConf getConf() {
      return this.conf;
   }

   public void setHelpAsked(boolean helpAsked) {
      this.helpAsked = helpAsked;
   }

   public boolean isHelpAsked() {
      return this.helpAsked;
   }

   public String getLastConnectedUrl() {
      return this.lastConnectedUrl;
   }

   public void setLastConnectedUrl(String lastConnectedUrl) {
      this.lastConnectedUrl = lastConnectedUrl;
   }

   @BeeLineOpts.Ignore
   public static Env getEnv() {
      return env;
   }

   @BeeLineOpts.Ignore
   public static void setEnv(Env envToUse) {
      env = envToUse;
   }

   public interface Env {
      String get(String var1);
   }

   @Retention(RetentionPolicy.RUNTIME)
   public @interface Ignore {
   }
}
