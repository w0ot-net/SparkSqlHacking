package org.apache.hadoop.hive.cli;

import com.google.common.base.Splitter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import jline.console.history.FileHistory;
import jline.console.history.History;
import jline.console.history.PersistentHistory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.HiveInterruptUtils;
import org.apache.hadoop.hive.common.LogUtils;
import org.apache.hadoop.hive.common.cli.ShellCmdExecutor;
import org.apache.hadoop.hive.common.io.CachingPrintStream;
import org.apache.hadoop.hive.common.io.FetchConverter;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveVariableSource;
import org.apache.hadoop.hive.conf.Validator;
import org.apache.hadoop.hive.conf.VariableSubstitution;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.exec.FunctionRegistry;
import org.apache.hadoop.hive.ql.exec.mr.HadoopJobExecHelper;
import org.apache.hadoop.hive.ql.exec.tez.TezJobExecHelper;
import org.apache.hadoop.hive.ql.parse.HiveParser;
import org.apache.hadoop.hive.ql.processors.CommandProcessor;
import org.apache.hadoop.hive.ql.processors.CommandProcessorFactory;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.StringUtils;
import org.apache.hive.common.util.ShutdownHookManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class CliDriver {
   public static String prompt = null;
   public static String prompt2 = null;
   public static final int LINES_TO_FETCH = 40;
   public static final int DELIMITED_CANDIDATE_THRESHOLD = 10;
   public static final String HIVERCFILE = ".hiverc";
   private final SessionState.LogHelper console;
   protected ConsoleReader reader;
   private Configuration conf;

   public CliDriver() {
      SessionState ss = SessionState.get();
      this.conf = (Configuration)(ss != null ? ss.getConf() : new Configuration());
      Logger LOG = LoggerFactory.getLogger("CliDriver");
      if (LOG.isDebugEnabled()) {
         LOG.debug("CliDriver inited with classpath {}", System.getProperty("java.class.path"));
      }

      this.console = new SessionState.LogHelper(LOG);
   }

   public int processCmd(String cmd) {
      CliSessionState ss = (CliSessionState)SessionState.get();
      ss.setLastCommand(cmd);
      ss.updateThreadName();
      ss.err.flush();
      String cmd_trimmed = cmd.trim();
      String[] tokens = this.tokenizeCmd(cmd_trimmed);
      int ret = 0;
      if (!cmd_trimmed.toLowerCase().equals("quit") && !cmd_trimmed.toLowerCase().equals("exit")) {
         if (tokens[0].equalsIgnoreCase("source")) {
            String cmd_1 = this.getFirstCmd(cmd_trimmed, tokens[0].length());
            cmd_1 = (new VariableSubstitution(new HiveVariableSource() {
               public Map getHiveVariable() {
                  return SessionState.get().getHiveVariables();
               }
            })).substitute(ss.getConf(), cmd_1);
            File sourceFile = new File(cmd_1);
            if (!sourceFile.isFile()) {
               this.console.printError("File: " + cmd_1 + " is not a file.");
               ret = 1;
            } else {
               try {
                  ret = this.processFile(cmd_1);
               } catch (IOException e) {
                  this.console.printError("Failed processing file " + cmd_1 + " " + e.getLocalizedMessage(), StringUtils.stringifyException(e));
                  ret = 1;
               }
            }
         } else if (cmd_trimmed.startsWith("!")) {
            String shell_cmd = cmd_trimmed.substring(1);
            shell_cmd = (new VariableSubstitution(new HiveVariableSource() {
               public Map getHiveVariable() {
                  return SessionState.get().getHiveVariables();
               }
            })).substitute(ss.getConf(), shell_cmd);

            try {
               ShellCmdExecutor executor = new ShellCmdExecutor(shell_cmd, ss.out, ss.err);
               ret = executor.execute();
               if (ret != 0) {
                  this.console.printError("Command failed with exit code = " + ret);
               }
            } catch (Exception e) {
               this.console.printError("Exception raised from Shell command " + e.getLocalizedMessage(), StringUtils.stringifyException(e));
               ret = 1;
            }
         } else {
            try {
               CommandProcessor proc = CommandProcessorFactory.get(tokens, (HiveConf)this.conf);
               ret = this.processLocalCmd(cmd, proc, ss);
            } catch (SQLException e) {
               this.console.printError("Failed processing command " + tokens[0] + " " + e.getLocalizedMessage(), StringUtils.stringifyException(e));
               ret = 1;
            }
         }
      } else {
         ss.close();
         System.exit(0);
      }

      ss.resetThreadName();
      return ret;
   }

   void setConf(Configuration conf) {
      this.conf = conf;
   }

   private String getFirstCmd(String cmd, int length) {
      return cmd.substring(length).trim();
   }

   private String[] tokenizeCmd(String cmd) {
      return cmd.split("\\s+");
   }

   int processLocalCmd(String cmd, CommandProcessor proc, CliSessionState ss) {
      int tryCount = 0;
      int ret = 0;

      boolean needRetry;
      do {
         try {
            needRetry = false;
            if (proc != null) {
               if (proc instanceof Driver) {
                  Driver qp = (Driver)proc;
                  PrintStream out = ss.out;
                  long start = System.currentTimeMillis();
                  if (ss.getIsVerbose()) {
                     out.println(cmd);
                  }

                  qp.setTryCount(tryCount);
                  ret = qp.run(cmd).getResponseCode();
                  if (ret != 0) {
                     qp.close();
                     return ret;
                  }

                  long end = System.currentTimeMillis();
                  double timeTaken = (double)(end - start) / (double)1000.0F;
                  ArrayList<String> res = new ArrayList();
                  this.printHeader(qp, out);
                  int counter = 0;

                  try {
                     if (out instanceof FetchConverter) {
                        ((FetchConverter)out).fetchStarted();
                     }

                     while(qp.getResults(res)) {
                        for(String r : res) {
                           out.println(r);
                        }

                        counter += res.size();
                        res.clear();
                        if (out.checkError()) {
                           break;
                        }
                     }
                  } catch (IOException e) {
                     this.console.printError("Failed with exception " + e.getClass().getName() + ":" + e.getMessage(), "\n" + StringUtils.stringifyException(e));
                     ret = 1;
                  }

                  int cret = qp.close();
                  if (ret == 0) {
                     ret = cret;
                  }

                  if (out instanceof FetchConverter) {
                     ((FetchConverter)out).fetchFinished();
                  }

                  this.console.printInfo("Time taken: " + timeTaken + " seconds" + (counter == 0 ? "" : ", Fetched: " + counter + " row(s)"));
               } else {
                  String firstToken = this.tokenizeCmd(cmd.trim())[0];
                  String cmd_1 = this.getFirstCmd(cmd.trim(), firstToken.length());
                  if (ss.getIsVerbose()) {
                     ss.out.println(firstToken + " " + cmd_1);
                  }

                  CommandProcessorResponse res = proc.run(cmd_1);
                  if (res.getResponseCode() != 0) {
                     ss.out.println("Query returned non-zero code: " + res.getResponseCode() + ", cause: " + res.getErrorMessage());
                  }

                  if (res.getConsoleMessages() != null) {
                     for(String consoleMsg : res.getConsoleMessages()) {
                        this.console.printInfo(consoleMsg);
                     }
                  }

                  ret = res.getResponseCode();
               }
            }
         } catch (CommandNeedRetryException var20) {
            this.console.printInfo("Retry query with a different approach...");
            ++tryCount;
            needRetry = true;
         }
      } while(needRetry);

      return ret;
   }

   private void printHeader(Driver qp, PrintStream out) {
      List<FieldSchema> fieldSchemas = qp.getSchema().getFieldSchemas();
      if (HiveConf.getBoolVar(this.conf, ConfVars.HIVE_CLI_PRINT_HEADER) && fieldSchemas != null) {
         boolean first_col = true;

         for(FieldSchema fs : fieldSchemas) {
            if (!first_col) {
               out.print('\t');
            }

            out.print(fs.getName());
            first_col = false;
         }

         out.println();
      }

   }

   public int processLine(String line) {
      return this.processLine(line, false);
   }

   public int processLine(String line, boolean allowInterrupting) {
      SignalHandler oldSignal = null;
      Signal interruptSignal = null;
      if (allowInterrupting) {
         interruptSignal = new Signal("INT");
         oldSignal = Signal.handle(interruptSignal, new SignalHandler() {
            private boolean interruptRequested;

            public void handle(Signal signal) {
               boolean initialRequest = !this.interruptRequested;
               this.interruptRequested = true;
               if (!initialRequest) {
                  CliDriver.this.console.printInfo("Exiting the JVM");
                  System.exit(127);
               }

               CliDriver.this.console.printInfo("Interrupting... Be patient, this might take some time.");
               CliDriver.this.console.printInfo("Press Ctrl+C again to kill JVM");
               HadoopJobExecHelper.killRunningJobs();
               TezJobExecHelper.killRunningJobs();
               HiveInterruptUtils.interrupt();
            }
         });
      }

      try {
         int lastRet = 0;
         int ret = 0;
         List<String> commands = splitSemiColon(line);
         String command = "";

         for(String oneCmd : commands) {
            if (org.apache.commons.lang3.StringUtils.endsWith(oneCmd, "\\")) {
               command = command + org.apache.commons.lang3.StringUtils.chop(oneCmd) + ";";
            } else {
               command = command + oneCmd;
               if (!org.apache.commons.lang3.StringUtils.isBlank(command)) {
                  ret = this.processCmd(command);
                  command = "";
                  lastRet = ret;
                  boolean ignoreErrors = HiveConf.getBoolVar(this.conf, ConfVars.CLIIGNOREERRORS);
                  if (ret != 0 && !ignoreErrors) {
                     CommandProcessorFactory.clean((HiveConf)this.conf);
                     int var12 = ret;
                     return var12;
                  }
               }
            }
         }

         CommandProcessorFactory.clean((HiveConf)this.conf);
         int var17 = lastRet;
         return var17;
      } finally {
         if (oldSignal != null && interruptSignal != null) {
            Signal.handle(interruptSignal, oldSignal);
         }

      }
   }

   public static List splitSemiColon(String line) {
      boolean insideSingleQuote = false;
      boolean insideDoubleQuote = false;
      boolean escape = false;
      int beginIndex = 0;
      List<String> ret = new ArrayList();

      for(int index = 0; index < line.length(); ++index) {
         if (line.charAt(index) == '\'') {
            if (!escape) {
               insideSingleQuote = !insideSingleQuote;
            }
         } else if (line.charAt(index) == '"') {
            if (!escape) {
               insideDoubleQuote = !insideDoubleQuote;
            }
         } else if (line.charAt(index) == ';' && !insideSingleQuote && !insideDoubleQuote) {
            ret.add(line.substring(beginIndex, index));
            beginIndex = index + 1;
         }

         if (escape) {
            escape = false;
         } else if (line.charAt(index) == '\\') {
            escape = true;
         }
      }

      ret.add(line.substring(beginIndex));
      return ret;
   }

   public int processReader(BufferedReader r) throws IOException {
      StringBuilder qsb = new StringBuilder();

      String line;
      while((line = r.readLine()) != null) {
         if (!line.startsWith("--")) {
            qsb.append(line + "\n");
         }
      }

      return this.processLine(qsb.toString());
   }

   public int processFile(String fileName) throws IOException {
      Path path = new Path(fileName);
      FileSystem fs;
      if (!path.toUri().isAbsolute()) {
         fs = FileSystem.getLocal(this.conf);
         path = fs.makeQualified(path);
      } else {
         fs = FileSystem.get(path.toUri(), this.conf);
      }

      BufferedReader bufferReader = null;
      int rc = 0;

      try {
         bufferReader = new BufferedReader(new InputStreamReader(fs.open(path)));
         rc = this.processReader(bufferReader);
      } finally {
         IOUtils.closeStream(bufferReader);
      }

      return rc;
   }

   public void processInitFiles(CliSessionState ss) throws IOException {
      boolean saveSilent = ss.getIsSilent();
      ss.setIsSilent(true);

      for(String initFile : ss.initFiles) {
         int rc = this.processFile(initFile);
         if (rc != 0) {
            System.exit(rc);
         }
      }

      if (ss.initFiles.size() == 0) {
         if (System.getenv("HIVE_HOME") != null) {
            String hivercDefault = System.getenv("HIVE_HOME") + File.separator + "bin" + File.separator + ".hiverc";
            if ((new File(hivercDefault)).exists()) {
               int rc = this.processFile(hivercDefault);
               if (rc != 0) {
                  System.exit(rc);
               }

               this.console.printError("Putting the global hiverc in $HIVE_HOME/bin/.hiverc is deprecated. Please use $HIVE_CONF_DIR/.hiverc instead.");
            }
         }

         if (System.getenv("HIVE_CONF_DIR") != null) {
            String hivercDefault = System.getenv("HIVE_CONF_DIR") + File.separator + ".hiverc";
            if ((new File(hivercDefault)).exists()) {
               int rc = this.processFile(hivercDefault);
               if (rc != 0) {
                  System.exit(rc);
               }
            }
         }

         if (System.getProperty("user.home") != null) {
            String hivercUser = System.getProperty("user.home") + File.separator + ".hiverc";
            if ((new File(hivercUser)).exists()) {
               int rc = this.processFile(hivercUser);
               if (rc != 0) {
                  System.exit(rc);
               }
            }
         }
      }

      ss.setIsSilent(saveSilent);
   }

   public void processSelectDatabase(CliSessionState ss) throws IOException {
      String database = ss.database;
      if (database != null) {
         int rc = this.processLine("use " + database + ";");
         if (rc != 0) {
            System.exit(rc);
         }
      }

   }

   public static Completer[] getCommandCompleter() {
      List<String> candidateStrings = new ArrayList();

      for(String s : FunctionRegistry.getFunctionNames()) {
         if (s.matches("[a-z_]+")) {
            candidateStrings.add(s + "(");
         } else {
            candidateStrings.add(s);
         }
      }

      for(String s : HiveParser.getKeywords()) {
         candidateStrings.add(s);
         candidateStrings.add(s.toLowerCase());
      }

      StringsCompleter strCompleter = new StringsCompleter(candidateStrings);
      ArgumentCompleter.ArgumentDelimiter delim = new ArgumentCompleter.AbstractArgumentDelimiter() {
         public boolean isDelimiterChar(CharSequence buffer, int pos) {
            char c = buffer.charAt(pos);
            return Character.isWhitespace(c) || c == '(' || c == ')' || c == '[' || c == ']';
         }
      };
      final ArgumentCompleter argCompleter = new ArgumentCompleter(delim, new Completer[]{strCompleter});
      argCompleter.setStrict(false);
      Completer customCompletor = new Completer() {
         public int complete(String buffer, int offset, List completions) {
            int ret = argCompleter.complete(buffer, offset, completions);
            if (completions.size() == 1 && ((String)completions.get(0)).endsWith("( ")) {
               completions.set(0, ((String)completions.get(0)).trim());
            }

            return ret;
         }
      };
      List<String> vars = new ArrayList();

      for(HiveConf.ConfVars conf : ConfVars.values()) {
         vars.add(conf.varname);
      }

      StringsCompleter confCompleter = new StringsCompleter(vars) {
         public int complete(String buffer, int cursor, List clist) {
            int result = super.complete(buffer, cursor, clist);
            if (clist.isEmpty() && cursor > 1 && buffer.charAt(cursor - 1) == '=') {
               HiveConf.ConfVars var = HiveConf.getConfVars(buffer.substring(0, cursor - 1));
               if (var == null) {
                  return result;
               } else {
                  if (var.getValidator() instanceof Validator.StringSet) {
                     Validator.StringSet validator = (Validator.StringSet)var.getValidator();
                     clist.addAll(validator.getExpected());
                  } else if (var.getValidator() != null) {
                     clist.addAll(Arrays.asList(var.getValidator().toDescription(), ""));
                  } else {
                     clist.addAll(Arrays.asList("Expects " + var.typeString() + " type value", ""));
                  }

                  return cursor;
               }
            } else {
               if (clist.size() > 10) {
                  Set<CharSequence> delimited = new LinkedHashSet();

                  for(CharSequence candidate : clist) {
                     Iterator<String> it = Splitter.on(".").split(candidate.subSequence(cursor, candidate.length())).iterator();
                     if (it.hasNext()) {
                        String next = (String)it.next();
                        if (next.isEmpty()) {
                           next = ".";
                        }

                        candidate = buffer != null ? buffer.substring(0, cursor) + next : next;
                     }

                     delimited.add(candidate);
                  }

                  clist.clear();
                  clist.addAll(delimited);
               }

               return result;
            }
         }
      };
      StringsCompleter setCompleter = new StringsCompleter(new String[]{"set"}) {
         public int complete(String buffer, int cursor, List clist) {
            return buffer != null && buffer.equals("set") ? super.complete(buffer, cursor, clist) : -1;
         }
      };
      ArgumentCompleter propCompleter = new ArgumentCompleter(new Completer[]{setCompleter, confCompleter}) {
         public int complete(String buffer, int offset, List completions) {
            int ret = super.complete(buffer, offset, completions);
            if (completions.size() == 1) {
               completions.set(0, ((String)completions.get(0)).trim());
            }

            return ret;
         }
      };
      return new Completer[]{propCompleter, customCompletor};
   }

   public static void main(String[] args) throws Exception {
      int ret = (new CliDriver()).run(args);
      System.exit(ret);
   }

   public int run(String[] args) throws Exception {
      OptionsProcessor oproc = new OptionsProcessor();
      if (!oproc.process_stage1(args)) {
         return 1;
      } else {
         boolean logInitFailed = false;

         String logInitDetailMessage;
         try {
            logInitDetailMessage = LogUtils.initHiveLog4j();
         } catch (LogUtils.LogInitializationException e) {
            logInitFailed = true;
            logInitDetailMessage = e.getMessage();
         }

         CliSessionState ss = new CliSessionState(new HiveConf(SessionState.class));
         ss.in = System.in;

         try {
            ss.out = new PrintStream(System.out, true, "UTF-8");
            ss.info = new PrintStream(System.err, true, "UTF-8");
            ss.err = new CachingPrintStream(System.err, true, "UTF-8");
         } catch (UnsupportedEncodingException var14) {
            return 3;
         }

         if (!oproc.process_stage2(ss)) {
            return 2;
         } else {
            if (!ss.getIsSilent()) {
               if (logInitFailed) {
                  System.err.println(logInitDetailMessage);
               } else {
                  SessionState.getConsole().printInfo(logInitDetailMessage);
               }
            }

            HiveConf conf = ss.getConf();

            for(Map.Entry item : ss.cmdProperties.entrySet()) {
               conf.set((String)item.getKey(), (String)item.getValue());
               ss.getOverriddenConfigurations().put((String)item.getKey(), (String)item.getValue());
            }

            prompt = conf.getVar(ConfVars.CLIPROMPT);
            prompt = (new VariableSubstitution(new HiveVariableSource() {
               public Map getHiveVariable() {
                  return SessionState.get().getHiveVariables();
               }
            })).substitute(conf, prompt);
            prompt2 = spacesForString(prompt);
            if (HiveConf.getBoolVar(conf, ConfVars.HIVE_CLI_TEZ_SESSION_ASYNC)) {
               SessionState.beginStart(ss, this.console);
            } else {
               SessionState.start(ss);
            }

            ss.updateThreadName();

            int var16;
            try {
               var16 = this.executeDriver(ss, conf, oproc);
            } finally {
               ss.resetThreadName();
               ss.close();
            }

            return var16;
         }
      }
   }

   private int executeDriver(CliSessionState ss, HiveConf conf, OptionsProcessor oproc) throws Exception {
      CliDriver cli = new CliDriver();
      cli.setHiveVariables(oproc.getHiveVariables());
      cli.processSelectDatabase(ss);
      cli.processInitFiles(ss);
      if (ss.execString != null) {
         int cmdProcessStatus = cli.processLine(ss.execString);
         return cmdProcessStatus;
      } else {
         try {
            if (ss.fileName != null) {
               return cli.processFile(ss.fileName);
            }
         } catch (FileNotFoundException e) {
            System.err.println("Could not open input file for reading. (" + e.getMessage() + ")");
            return 3;
         }

         if ("mr".equals(HiveConf.getVar(conf, ConfVars.HIVE_EXECUTION_ENGINE))) {
            this.console.printInfo(HiveConf.generateMrDeprecationWarning());
         }

         this.setupConsoleReader();
         int ret = 0;
         String prefix = "";
         String curDB = getFormattedDb(conf, ss);
         String curPrompt = prompt + curDB;
         String dbSpaces = spacesForString(curDB);

         String line;
         while((line = this.reader.readLine(curPrompt + "> ")) != null) {
            if (!prefix.equals("")) {
               prefix = prefix + '\n';
            }

            if (!line.trim().startsWith("--")) {
               if (line.trim().endsWith(";") && !line.trim().endsWith("\\;")) {
                  line = prefix + line;
                  ret = cli.processLine(line, true);
                  prefix = "";
                  curDB = getFormattedDb(conf, ss);
                  curPrompt = prompt + curDB;
                  dbSpaces = dbSpaces.length() == curDB.length() ? dbSpaces : spacesForString(curDB);
               } else {
                  prefix = prefix + line;
                  curPrompt = prompt2 + dbSpaces;
               }
            }
         }

         return ret;
      }
   }

   private void setupCmdHistory() {
      String HISTORYFILE = ".hivehistory";
      String historyDirectory = System.getProperty("user.home");
      PersistentHistory history = null;

      try {
         if ((new File(historyDirectory)).exists()) {
            String historyFile = historyDirectory + File.separator + ".hivehistory";
            PersistentHistory var6 = new FileHistory(new File(historyFile));
            this.reader.setHistory(var6);
         } else {
            System.err.println("WARNING: Directory for Hive history file: " + historyDirectory + " does not exist.   History will not be available during this session.");
         }
      } catch (Exception e) {
         System.err.println("WARNING: Encountered an error while trying to initialize Hive's history file.  History will not be available during this session.");
         System.err.println(e.getMessage());
      }

      ShutdownHookManager.addShutdownHook(new Runnable() {
         public void run() {
            History h = CliDriver.this.reader.getHistory();
            if (h instanceof FileHistory) {
               try {
                  ((FileHistory)h).flush();
               } catch (IOException e) {
                  System.err.println("WARNING: Failed to write command history file: " + e.getMessage());
               }
            }

         }
      });
   }

   protected void setupConsoleReader() throws IOException {
      this.reader = new ConsoleReader();
      this.reader.setExpandEvents(false);
      this.reader.setBellEnabled(false);

      for(Completer completer : getCommandCompleter()) {
         this.reader.addCompleter(completer);
      }

      this.setupCmdHistory();
   }

   private static String getFormattedDb(HiveConf conf, CliSessionState ss) {
      if (!HiveConf.getBoolVar(conf, ConfVars.CLIPRINTCURRENTDB)) {
         return "";
      } else {
         String currDb = SessionState.get().getCurrentDatabase();
         return currDb == null ? "" : " (" + currDb + ")";
      }
   }

   private static String spacesForString(String s) {
      return s != null && s.length() != 0 ? String.format("%1$-" + s.length() + "s", "") : "";
   }

   public void setHiveVariables(Map hiveVariables) {
      SessionState.get().setHiveVariables(hiveVariables);
   }
}
