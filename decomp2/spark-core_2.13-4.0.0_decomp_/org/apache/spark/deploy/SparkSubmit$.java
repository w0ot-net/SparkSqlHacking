package org.apache.spark.deploy;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkUserAppException;
import org.apache.spark.deploy.rest.RestSubmissionClientApp;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.CommandLineLoggingUtils;
import org.apache.spark.util.CommandLineUtils;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.Option.;

public final class SparkSubmit$ implements CommandLineUtils, Logging {
   public static final SparkSubmit$ MODULE$ = new SparkSubmit$();
   private static final int org$apache$spark$deploy$SparkSubmit$$YARN;
   private static final int org$apache$spark$deploy$SparkSubmit$$STANDALONE;
   private static final int org$apache$spark$deploy$SparkSubmit$$LOCAL;
   private static final int org$apache$spark$deploy$SparkSubmit$$KUBERNETES;
   private static final int org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS;
   private static final int org$apache$spark$deploy$SparkSubmit$$CLIENT;
   private static final int org$apache$spark$deploy$SparkSubmit$$CLUSTER;
   private static final int org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES;
   private static final String SPARK_SHELL;
   private static final String org$apache$spark$deploy$SparkSubmit$$PYSPARK_SHELL;
   private static final String org$apache$spark$deploy$SparkSubmit$$SPARKR_SHELL;
   private static final String CONNECT_SHELL;
   private static final String org$apache$spark$deploy$SparkSubmit$$SPARKR_PACKAGE_ARCHIVE;
   private static final String org$apache$spark$deploy$SparkSubmit$$R_PACKAGE_ARCHIVE;
   private static final int org$apache$spark$deploy$SparkSubmit$$CLASS_NOT_FOUND_EXIT_STATUS;
   private static final String YARN_CLUSTER_SUBMIT_CLASS;
   private static final String REST_CLUSTER_SUBMIT_CLASS;
   private static final String STANDALONE_CLUSTER_SUBMIT_CLASS;
   private static final String KUBERNETES_CLUSTER_SUBMIT_CLASS;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static Function1 exitFn;
   private static PrintStream printStream;

   static {
      CommandLineLoggingUtils.$init$(MODULE$);
      Logging.$init$(MODULE$);
      org$apache$spark$deploy$SparkSubmit$$YARN = 1;
      org$apache$spark$deploy$SparkSubmit$$STANDALONE = 2;
      org$apache$spark$deploy$SparkSubmit$$LOCAL = 8;
      org$apache$spark$deploy$SparkSubmit$$KUBERNETES = 16;
      org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS = MODULE$.org$apache$spark$deploy$SparkSubmit$$YARN() | MODULE$.org$apache$spark$deploy$SparkSubmit$$STANDALONE() | MODULE$.org$apache$spark$deploy$SparkSubmit$$LOCAL() | MODULE$.org$apache$spark$deploy$SparkSubmit$$KUBERNETES();
      org$apache$spark$deploy$SparkSubmit$$CLIENT = 1;
      org$apache$spark$deploy$SparkSubmit$$CLUSTER = 2;
      org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES = MODULE$.org$apache$spark$deploy$SparkSubmit$$CLIENT() | MODULE$.org$apache$spark$deploy$SparkSubmit$$CLUSTER();
      SPARK_SHELL = "spark-shell";
      org$apache$spark$deploy$SparkSubmit$$PYSPARK_SHELL = "pyspark-shell";
      org$apache$spark$deploy$SparkSubmit$$SPARKR_SHELL = "sparkr-shell";
      CONNECT_SHELL = "connect-shell";
      org$apache$spark$deploy$SparkSubmit$$SPARKR_PACKAGE_ARCHIVE = "sparkr.zip";
      org$apache$spark$deploy$SparkSubmit$$R_PACKAGE_ARCHIVE = "rpkg.zip";
      org$apache$spark$deploy$SparkSubmit$$CLASS_NOT_FOUND_EXIT_STATUS = 101;
      YARN_CLUSTER_SUBMIT_CLASS = "org.apache.spark.deploy.yarn.YarnClusterApplication";
      REST_CLUSTER_SUBMIT_CLASS = RestSubmissionClientApp.class.getName();
      STANDALONE_CLUSTER_SUBMIT_CLASS = ClientApp.class.getName();
      KUBERNETES_CLUSTER_SUBMIT_CLASS = "org.apache.spark.deploy.k8s.submit.KubernetesClientApplication";
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

   public void printMessage(final String str) {
      CommandLineLoggingUtils.printMessage$(this, str);
   }

   public void printErrorAndExit(final String str) {
      CommandLineLoggingUtils.printErrorAndExit$(this, str);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Function1 exitFn() {
      return exitFn;
   }

   public void exitFn_$eq(final Function1 x$1) {
      exitFn = x$1;
   }

   public PrintStream printStream() {
      return printStream;
   }

   public void printStream_$eq(final PrintStream x$1) {
      printStream = x$1;
   }

   public int org$apache$spark$deploy$SparkSubmit$$YARN() {
      return org$apache$spark$deploy$SparkSubmit$$YARN;
   }

   public int org$apache$spark$deploy$SparkSubmit$$STANDALONE() {
      return org$apache$spark$deploy$SparkSubmit$$STANDALONE;
   }

   public int org$apache$spark$deploy$SparkSubmit$$LOCAL() {
      return org$apache$spark$deploy$SparkSubmit$$LOCAL;
   }

   public int org$apache$spark$deploy$SparkSubmit$$KUBERNETES() {
      return org$apache$spark$deploy$SparkSubmit$$KUBERNETES;
   }

   public int org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS() {
      return org$apache$spark$deploy$SparkSubmit$$ALL_CLUSTER_MGRS;
   }

   public int org$apache$spark$deploy$SparkSubmit$$CLIENT() {
      return org$apache$spark$deploy$SparkSubmit$$CLIENT;
   }

   public int org$apache$spark$deploy$SparkSubmit$$CLUSTER() {
      return org$apache$spark$deploy$SparkSubmit$$CLUSTER;
   }

   public int org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES() {
      return org$apache$spark$deploy$SparkSubmit$$ALL_DEPLOY_MODES;
   }

   private String SPARK_SHELL() {
      return SPARK_SHELL;
   }

   public String org$apache$spark$deploy$SparkSubmit$$PYSPARK_SHELL() {
      return org$apache$spark$deploy$SparkSubmit$$PYSPARK_SHELL;
   }

   public String org$apache$spark$deploy$SparkSubmit$$SPARKR_SHELL() {
      return org$apache$spark$deploy$SparkSubmit$$SPARKR_SHELL;
   }

   private String CONNECT_SHELL() {
      return CONNECT_SHELL;
   }

   public String org$apache$spark$deploy$SparkSubmit$$SPARKR_PACKAGE_ARCHIVE() {
      return org$apache$spark$deploy$SparkSubmit$$SPARKR_PACKAGE_ARCHIVE;
   }

   public String org$apache$spark$deploy$SparkSubmit$$R_PACKAGE_ARCHIVE() {
      return org$apache$spark$deploy$SparkSubmit$$R_PACKAGE_ARCHIVE;
   }

   public int org$apache$spark$deploy$SparkSubmit$$CLASS_NOT_FOUND_EXIT_STATUS() {
      return org$apache$spark$deploy$SparkSubmit$$CLASS_NOT_FOUND_EXIT_STATUS;
   }

   public String YARN_CLUSTER_SUBMIT_CLASS() {
      return YARN_CLUSTER_SUBMIT_CLASS;
   }

   public String REST_CLUSTER_SUBMIT_CLASS() {
      return REST_CLUSTER_SUBMIT_CLASS;
   }

   public String STANDALONE_CLUSTER_SUBMIT_CLASS() {
      return STANDALONE_CLUSTER_SUBMIT_CLASS;
   }

   public String KUBERNETES_CLUSTER_SUBMIT_CLASS() {
      return KUBERNETES_CLUSTER_SUBMIT_CLASS;
   }

   public void main(final String[] args) {
      .MODULE$.apply(System.getenv("SPARK_PREFER_IPV6")).foreach((x$27) -> System.setProperty("java.net.preferIPv6Addresses", x$27));
      SparkSubmit submit = new SparkSubmit() {
         public SparkSubmitArguments parseArguments(final String[] args) {
            return new SparkSubmitArguments(args) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public void logInfo(final Function0 msg) {
                  this.$outer.logInfo(msg);
               }

               public void logInfo(final LogEntry entry) {
                  this.$outer.logInfo(entry);
               }

               public void logWarning(final Function0 msg) {
                  this.$outer.logWarning(msg);
               }

               public void logWarning(final LogEntry entry) {
                  this.$outer.logWarning(entry);
               }

               public void logError(final Function0 msg) {
                  this.$outer.logError(msg);
               }

               public void logError(final LogEntry entry) {
                  this.$outer.logError(entry);
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                  }
               }
            };
         }

         public void logInfo(final Function0 msg) {
            SparkSubmit$.MODULE$.printMessage((String)msg.apply());
         }

         public void logInfo(final LogEntry entry) {
            SparkSubmit$.MODULE$.printMessage(entry.message());
         }

         public void logWarning(final Function0 msg) {
            SparkSubmit$.MODULE$.printMessage("Warning: " + msg.apply());
         }

         public void logWarning(final LogEntry entry) {
            SparkSubmit$.MODULE$.printMessage("Warning: " + entry.message());
         }

         public void logError(final Function0 msg) {
            SparkSubmit$.MODULE$.printMessage("Error: " + msg.apply());
         }

         public void logError(final LogEntry entry) {
            SparkSubmit$.MODULE$.printMessage("Error: " + entry.message());
         }

         public void doSubmit(final String[] args) {
            try {
               super.doSubmit(args);
            } catch (SparkUserAppException var3) {
               SparkSubmit$.MODULE$.exitFn().apply$mcVI$sp(var3.exitCode());
            }

         }
      };
      submit.doSubmit(args);
   }

   public boolean isUserJar(final String res) {
      return !this.isShell(res) && !this.isPython(res) && !this.isInternal(res) && !this.isR(res);
   }

   public boolean isShell(final String res) {
      boolean var10000;
      label48: {
         String var2 = this.SPARK_SHELL();
         if (res == null) {
            if (var2 == null) {
               break label48;
            }
         } else if (res.equals(var2)) {
            break label48;
         }

         String var3 = this.org$apache$spark$deploy$SparkSubmit$$PYSPARK_SHELL();
         if (res == null) {
            if (var3 == null) {
               break label48;
            }
         } else if (res.equals(var3)) {
            break label48;
         }

         String var4 = this.org$apache$spark$deploy$SparkSubmit$$SPARKR_SHELL();
         if (res == null) {
            if (var4 == null) {
               break label48;
            }
         } else if (res.equals(var4)) {
            break label48;
         }

         String var5 = this.CONNECT_SHELL();
         if (res == null) {
            if (var5 == null) {
               break label48;
            }
         } else if (res.equals(var5)) {
            break label48;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean isSqlShell(final String mainClass) {
      boolean var10000;
      label23: {
         String var2 = "org.apache.spark.sql.hive.thriftserver.SparkSQLCLIDriver";
         if (mainClass == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (mainClass.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean org$apache$spark$deploy$SparkSubmit$$isThriftServer(final String mainClass) {
      boolean var10000;
      label23: {
         String var2 = "org.apache.spark.sql.hive.thriftserver.HiveThriftServer2";
         if (mainClass == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (mainClass.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean org$apache$spark$deploy$SparkSubmit$$isConnectServer(final String mainClass) {
      boolean var10000;
      label23: {
         String var2 = "org.apache.spark.sql.connect.service.SparkConnectServer";
         if (mainClass == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (mainClass.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public boolean isPython(final String res) {
      boolean var10000;
      if (res == null || !res.endsWith(".py")) {
         label31: {
            String var2 = this.org$apache$spark$deploy$SparkSubmit$$PYSPARK_SHELL();
            if (res == null) {
               if (var2 == null) {
                  break label31;
               }
            } else if (res.equals(var2)) {
               break label31;
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean isR(final String res) {
      boolean var10000;
      if (res == null || !res.endsWith(".R") && !res.endsWith(".r")) {
         label34: {
            String var2 = this.org$apache$spark$deploy$SparkSubmit$$SPARKR_SHELL();
            if (res == null) {
               if (var2 == null) {
                  break label34;
               }
            } else if (res.equals(var2)) {
               break label34;
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean isInternal(final String res) {
      boolean var10000;
      label23: {
         String var2 = "spark-internal";
         if (res == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (res.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   private SparkSubmit$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
