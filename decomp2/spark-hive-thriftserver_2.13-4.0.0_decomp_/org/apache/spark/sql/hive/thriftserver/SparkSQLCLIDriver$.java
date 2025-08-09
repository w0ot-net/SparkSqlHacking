package org.apache.spark.sql.hive.thriftserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import jline.console.history.FileHistory;
import jline.console.history.History;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.cli.CliDriver;
import org.apache.hadoop.hive.cli.CliSessionState;
import org.apache.hadoop.hive.cli.OptionsProcessor;
import org.apache.hadoop.hive.common.HiveInterruptUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.hive.security.HiveDelegationTokenProvider;
import org.apache.spark.sql.internal.SessionResourceLoader;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps.;
import scala.collection.mutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class SparkSQLCLIDriver$ implements Logging {
   public static final SparkSQLCLIDriver$ MODULE$ = new SparkSQLCLIDriver$();
   private static final String prompt;
   private static final String continuedPrompt;
   private static int exitCode;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      prompt = "spark-sql";
      continuedPrompt = .MODULE$.padTo$extension(scala.Predef..MODULE$.augmentString(""), MODULE$.prompt().length(), ' ');
      exitCode = 0;
      MODULE$.initializeLogIfNecessary(true);
      MODULE$.installSignalHandler();
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private String prompt() {
      return prompt;
   }

   private String continuedPrompt() {
      return continuedPrompt;
   }

   private final String SPARK_HADOOP_PROP_PREFIX() {
      return "spark.hadoop.";
   }

   private int exitCode() {
      return exitCode;
   }

   private void exitCode_$eq(final int x$1) {
      exitCode = x$1;
   }

   public void installSignalHandler() {
      HiveInterruptUtils.add(() -> {
         if (SparkSQLEnv$.MODULE$.sparkContext() != null) {
            SparkSQLEnv$.MODULE$.sparkContext().cancelAllJobs();
         }
      });
   }

   public void exit(final int code) {
      this.exitCode_$eq(code);
      System.exit(this.exitCode());
   }

   public void main(final String[] args) {
      OptionsProcessor oproc = new OptionsProcessor();
      if (!oproc.process_stage1(args)) {
         System.exit(org.apache.spark.util.SparkExitCode..MODULE$.EXIT_FAILURE());
      }

      SparkConf sparkConf = new SparkConf(true);
      Configuration hadoopConf = org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().newConfiguration(sparkConf);
      HiveConf cliConf = org.apache.spark.sql.hive.client.HiveClientImpl..MODULE$.newHiveConf(sparkConf, hadoopConf, org.apache.spark.sql.hive.client.HiveClientImpl..MODULE$.newHiveConf$default$3(), org.apache.spark.sql.hive.client.HiveClientImpl..MODULE$.newHiveConf$default$4());
      CliSessionState sessionState = new CliSessionState(cliConf);
      sessionState.in = System.in;

      try {
         sessionState.out = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
         sessionState.info = new PrintStream(System.err, true, StandardCharsets.UTF_8.name());
         sessionState.err = new PrintStream(System.err, true, StandardCharsets.UTF_8.name());
      } catch (UnsupportedEncodingException var31) {
         this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$closeHiveSessionStateIfStarted(sessionState);
         this.exit(org.apache.spark.util.SparkExitCode..MODULE$.ERROR_PATH_NOT_FOUND());
      }

      if (!oproc.process_stage2(sessionState)) {
         this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$closeHiveSessionStateIfStarted(sessionState);
         this.exit(org.apache.spark.util.SparkExitCode..MODULE$.ERROR_MISUSE_SHELL_BUILTIN());
      }

      HiveConf conf = sessionState.getConf();
      conf.setClassLoader(Thread.currentThread().getContextClassLoader());
      scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(sessionState.cmdProperties.entrySet()).asScala().foreach((item) -> {
         String key;
         String value;
         label14: {
            key = item.getKey().toString();
            value = item.getValue().toString();
            String var5 = "javax.jdo.option.ConnectionURL";
            if (key == null) {
               if (var5 != null) {
                  break label14;
               }
            } else if (!key.equals(var5)) {
               break label14;
            }

            return BoxedUnit.UNIT;
         }

         conf.set(key, value);
         return sessionState.getOverriddenConfigurations().put(key, value);
      });
      HiveDelegationTokenProvider tokenProvider = new HiveDelegationTokenProvider();
      if (tokenProvider.delegationTokensRequired(sparkConf, hadoopConf)) {
         Credentials credentials = new Credentials();
         tokenProvider.obtainDelegationTokens(hadoopConf, sparkConf, credentials);
         UserGroupInformation.getCurrentUser().addCredentials(credentials);
      }

      String warehousePath = org.apache.spark.sql.internal.SharedState..MODULE$.resolveWarehousePath(sparkConf, conf, org.apache.spark.sql.internal.SharedState..MODULE$.resolveWarehousePath$default$3());
      String qualified = org.apache.spark.sql.internal.SharedState..MODULE$.qualifyWarehousePath(conf, warehousePath);
      org.apache.spark.sql.internal.SharedState..MODULE$.setWarehousePathConf(sparkConf, conf, qualified);
      SessionState.setCurrentSessionState(sessionState);
      org.apache.spark.util.ShutdownHookManager..MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> {
         MODULE$.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$closeHiveSessionStateIfStarted(sessionState);
         SparkSQLEnv$.MODULE$.stop(MODULE$.exitCode());
      });
      Set hiveConfFromCmd = scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(sessionState.getOverriddenConfigurations().entrySet()).asScala();
      Set newHiveConf = (Set)hiveConfFromCmd.map((kv) -> {
         String k = (String)kv.getKey();
         String v = (String)scala.sys.package..MODULE$.props().getOrElseUpdate("spark.hadoop." + k, () -> (String)kv.getValue());
         return new Tuple2(k, v);
      });
      SparkSQLCLIDriver cli = new SparkSQLCLIDriver();
      cli.setHiveVariables(oproc.getHiveVariables());
      String auxJars = HiveConf.getVar(conf, HiveConf.getConfVars("hive.aux.jars.path"));
      if (StringUtils.isNotBlank(auxJars)) {
         SessionResourceLoader resourceLoader = SparkSQLEnv$.MODULE$.sparkSession().sessionState().resourceLoader();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])StringUtils.split(auxJars, ",")), (x$1) -> {
            $anonfun$main$5(resourceLoader, x$1);
            return BoxedUnit.UNIT;
         });
      }

      sessionState.getConf().setClassLoader(SparkSQLEnv$.MODULE$.sparkSession().sharedState().jarClassLoader());
      sessionState.in = System.in;

      try {
         sessionState.out = new PrintStream(System.out, true, StandardCharsets.UTF_8.name());
         sessionState.info = new PrintStream(System.err, true, StandardCharsets.UTF_8.name());
         sessionState.err = new PrintStream(System.err, true, StandardCharsets.UTF_8.name());
      } catch (UnsupportedEncodingException var30) {
         this.exit(org.apache.spark.util.SparkExitCode..MODULE$.ERROR_PATH_NOT_FOUND());
      }

      newHiveConf.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$main$6(check$ifrefutable$1))).withFilter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$main$7(x$2))).foreach((x$3) -> {
         $anonfun$main$8(x$3);
         return BoxedUnit.UNIT;
      });
      if (sessionState.database != null) {
         SparkSQLEnv$.MODULE$.sparkSession().sql("USE " + sessionState.database);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      cli.processInitFiles(sessionState);
      cli.printMasterAndAppId();
      if (sessionState.execString != null) {
         this.exit(cli.processLine(sessionState.execString));
      }

      try {
         if (sessionState.fileName != null) {
            this.exit(cli.processFile(sessionState.fileName));
         }
      } catch (FileNotFoundException var29) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not open input file for reading. (", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var29.getMessage())})))));
         this.exit(org.apache.spark.util.SparkExitCode..MODULE$.ERROR_PATH_NOT_FOUND());
      }

      ConsoleReader reader = new ConsoleReader();
      reader.setBellEnabled(false);
      reader.setExpandEvents(false);
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.getCommandCompleter()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$main$10(reader, x$1)));
      String historyDirectory = System.getProperty("user.home");

      try {
         if ((new File(historyDirectory)).exists()) {
            String historyFile = historyDirectory + File.separator + ".hivehistory";
            reader.setHistory(new FileHistory(new File(historyFile)));
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Directory for Hive history file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HISTORY_DIR..MODULE$, historyDirectory)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" does not exist. History will not be available during this session."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }
      } catch (Exception var28) {
         this.logWarning((Function0)(() -> "Encountered an error while trying to initialize Hive's history file. History will not be available during this session."), var28);
      }

      org.apache.spark.util.ShutdownHookManager..MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> {
         History var2 = reader.getHistory();
         if (var2 instanceof FileHistory var3) {
            try {
               var3.flush();
               BoxedUnit var7 = BoxedUnit.UNIT;
            } catch (IOException var5) {
               MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to write command history file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var5.getMessage())})))));
               BoxedUnit var6 = BoxedUnit.UNIT;
            }

         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      });
      int ret = 0;
      String prefix = "";
      String currentPrompt = this.promptWithCurrentDB$1(conf, sessionState);

      for(String line = reader.readLine(currentPrompt + "> "); line != null; line = reader.readLine(currentPrompt + "> ")) {
         if (!line.startsWith("--")) {
            if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(prefix))) {
               prefix = prefix + '\n';
            }

            if (line.trim().endsWith(";") && !line.trim().endsWith("\\;")) {
               line = prefix + line;
               ret = cli.processLine(line, true);
               prefix = "";
               currentPrompt = this.promptWithCurrentDB$1(conf, sessionState);
            } else {
               prefix = prefix + line;
               currentPrompt = this.continuedPromptWithDBSpaces$1(conf, sessionState);
            }
         }
      }

      this.org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$closeHiveSessionStateIfStarted(sessionState);
      this.exit(ret);
   }

   public void printUsage() {
      OptionsProcessor processor = new OptionsProcessor();
      ReflectionUtils$.MODULE$.invoke(OptionsProcessor.class, processor, "printUsage", scala.collection.immutable.Nil..MODULE$);
   }

   public void org$apache$spark$sql$hive$thriftserver$SparkSQLCLIDriver$$closeHiveSessionStateIfStarted(final SessionState state) {
      if (BoxesRunTime.unboxToBoolean(ReflectionUtils$.MODULE$.getSuperField(state, "isStarted"))) {
         state.close();
      }
   }

   private Completer[] getCommandCompleter() {
      ArrayList candidateStrings = new ArrayList();
      ((IterableOnceOps)org.apache.spark.sql.catalyst.analysis.FunctionRegistry..MODULE$.builtin().listFunction().map((x$4) -> x$4.funcName())).foreach((s) -> BoxesRunTime.boxToBoolean($anonfun$getCommandCompleter$2(candidateStrings, s)));
      org.apache.spark.sql.catalyst.util.SQLKeywordUtils..MODULE$.keywords().foreach((s) -> BoxesRunTime.boxToBoolean($anonfun$getCommandCompleter$3(candidateStrings, s)));
      StringsCompleter strCompleter = new StringsCompleter(candidateStrings);
      ArgumentCompleter.AbstractArgumentDelimiter delim = new ArgumentCompleter.AbstractArgumentDelimiter() {
         public boolean isDelimiterChar(final CharSequence buffer, final int pos) {
            char c = buffer.charAt(pos);
            return Character.isWhitespace(c) || c == '(' || c == ')' || c == '[' || c == ']';
         }
      };
      ArgumentCompleter argCompleter = new ArgumentCompleter(delim, new Completer[]{strCompleter});
      argCompleter.setStrict(false);
      Completer customCompleter = new Completer(argCompleter) {
         private final ArgumentCompleter argCompleter$1;

         public int complete(final String buffer, final int offset, final List completions) {
            int ret = this.argCompleter$1.complete(buffer, offset, completions);
            if (completions.size() == 1 && ((String)completions.get(0)).endsWith("( ")) {
               completions.set(0, ((String)completions.get(0)).trim());
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return ret;
         }

         public {
            this.argCompleter$1 = argCompleter$1;
         }
      };
      StringsCompleter confCompleter = new StringsCompleter() {
         public int complete(final String buffer, final int cursor, final List clist) {
            return super.complete(buffer, cursor, clist);
         }
      };
      StringsCompleter setCompleter = new StringsCompleter() {
         public int complete(final String buffer, final int cursor, final List clist) {
            return buffer != null && buffer.equalsIgnoreCase("set") ? super.complete(buffer, cursor, clist) : -1;
         }
      };
      ArgumentCompleter propCompleter = new ArgumentCompleter(setCompleter, confCompleter) {
         public int complete(final String buffer, final int offset, final List completions) {
            int ret = super.complete(buffer, offset, completions);
            if (completions.size() == 1) {
               completions.set(0, ((String)completions.get(0)).trim());
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return ret;
         }
      };
      return (Completer[])((Object[])(new Completer[]{propCompleter, customCompleter}));
   }

   // $FF: synthetic method
   public static final void $anonfun$main$5(final SessionResourceLoader resourceLoader$1, final String x$1) {
      resourceLoader$1.addJar(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$main$6(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$main$7(final Tuple2 x$2) {
      if (x$2 == null) {
         throw new MatchError(x$2);
      } else {
         boolean var10000;
         label30: {
            String k = (String)x$2._1();
            String var4 = "hive.metastore.warehouse.dir";
            if (k == null) {
               if (var4 != null) {
                  break label30;
               }
            } else if (!k.equals(var4)) {
               break label30;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$main$8(final Tuple2 x$3) {
      if (x$3 != null) {
         String k = (String)x$3._1();
         String v = (String)x$3._2();
         SparkSQLEnv$.MODULE$.sparkSession().conf().set(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$3);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$main$10(final ConsoleReader reader$1, final Completer x$1) {
      return reader$1.addCompleter(x$1);
   }

   private static final Object currentDB$1(final HiveConf conf$1, final CliSessionState sessionState$1) {
      return !BoxesRunTime.unboxToBoolean(SparkSQLEnv$.MODULE$.sparkSession().sessionState().conf().getConf(org.apache.spark.sql.internal.SQLConf..MODULE$.LEGACY_EMPTY_CURRENT_DB_IN_CLI())) ? " (" + SparkSQLEnv$.MODULE$.sparkSession().catalog().currentDatabase() + ")" : ReflectionUtils$.MODULE$.invokeStatic(CliDriver.class, "getFormattedDb", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(HiveConf.class), conf$1), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(CliSessionState.class), sessionState$1)})));
   }

   private final String promptWithCurrentDB$1(final HiveConf conf$1, final CliSessionState sessionState$1) {
      String var10000 = this.prompt();
      return var10000 + currentDB$1(conf$1, sessionState$1);
   }

   private final String continuedPromptWithDBSpaces$1(final HiveConf conf$1, final CliSessionState sessionState$1) {
      String var10000 = this.continuedPrompt();
      return var10000 + ReflectionUtils$.MODULE$.invokeStatic(CliDriver.class, "spacesForString", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(String.class), currentDB$1(conf$1, sessionState$1))})));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCommandCompleter$2(final ArrayList candidateStrings$1, final String s) {
      return s.matches("[a-z_]+") ? candidateStrings$1.add(s + "(") : candidateStrings$1.add(s);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCommandCompleter$3(final ArrayList candidateStrings$1, final String s) {
      candidateStrings$1.add(s);
      return candidateStrings$1.add(s.toLowerCase(Locale.ROOT));
   }

   private SparkSQLCLIDriver$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
