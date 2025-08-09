package org.apache.spark.launcher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SparkLauncher extends AbstractLauncher {
   private static final Logger LOG = Logger.getLogger(SparkLauncher.class.getName());
   public static final String SPARK_MASTER = "spark.master";
   public static final String SPARK_REMOTE = "spark.remote";
   public static final String SPARK_API_MODE = "spark.api.mode";
   public static final String DEPLOY_MODE = "spark.submit.deployMode";
   public static final String DRIVER_MEMORY = "spark.driver.memory";
   public static final String DRIVER_DEFAULT_EXTRA_CLASS_PATH = "spark.driver.defaultExtraClassPath";
   public static final String DRIVER_DEFAULT_EXTRA_CLASS_PATH_VALUE = "hive-jackson/*";
   public static final String DRIVER_EXTRA_CLASSPATH = "spark.driver.extraClassPath";
   public static final String DRIVER_DEFAULT_JAVA_OPTIONS = "spark.driver.defaultJavaOptions";
   public static final String DRIVER_EXTRA_JAVA_OPTIONS = "spark.driver.extraJavaOptions";
   public static final String DRIVER_EXTRA_LIBRARY_PATH = "spark.driver.extraLibraryPath";
   public static final String EXECUTOR_MEMORY = "spark.executor.memory";
   public static final String EXECUTOR_DEFAULT_EXTRA_CLASS_PATH = "spark.executor.defaultExtraClassPath";
   public static final String EXECUTOR_DEFAULT_EXTRA_CLASS_PATH_VALUE = "hive-jackson/*";
   public static final String EXECUTOR_EXTRA_CLASSPATH = "spark.executor.extraClassPath";
   public static final String EXECUTOR_DEFAULT_JAVA_OPTIONS = "spark.executor.defaultJavaOptions";
   public static final String EXECUTOR_EXTRA_JAVA_OPTIONS = "spark.executor.extraJavaOptions";
   public static final String EXECUTOR_EXTRA_LIBRARY_PATH = "spark.executor.extraLibraryPath";
   public static final String EXECUTOR_CORES = "spark.executor.cores";
   static final String PYSPARK_DRIVER_PYTHON = "spark.pyspark.driver.python";
   static final String PYSPARK_PYTHON = "spark.pyspark.python";
   static final String SPARKR_R_SHELL = "spark.r.shell.command";
   public static final String CHILD_PROCESS_LOGGER_NAME = "spark.launcher.childProcLoggerName";
   public static final String NO_RESOURCE = "spark-internal";
   /** @deprecated */
   @Deprecated(
      since = "3.2.0"
   )
   public static final String DEPRECATED_CHILD_CONNECTION_TIMEOUT = "spark.launcher.childConectionTimeout";
   public static final String CHILD_CONNECTION_TIMEOUT = "spark.launcher.childConnectionTimeout";
   private static final AtomicInteger COUNTER = new AtomicInteger();
   static final ThreadFactory REDIRECTOR_FACTORY = new NamedThreadFactory("launcher-proc-%d");
   static final Map launcherConfig = new HashMap();
   File workingDir;
   boolean redirectErrorStream;
   ProcessBuilder.Redirect errorStream;
   ProcessBuilder.Redirect outputStream;

   public static void setConfig(String name, String value) {
      launcherConfig.put(name, value);
   }

   public SparkLauncher() {
      this((Map)null);
   }

   public SparkLauncher(Map env) {
      if (env != null) {
         this.builder.childEnv.putAll(env);
      }

   }

   public SparkLauncher setJavaHome(String javaHome) {
      CommandBuilderUtils.checkNotNull(javaHome, "javaHome");
      this.builder.javaHome = javaHome;
      return this;
   }

   public SparkLauncher setSparkHome(String sparkHome) {
      CommandBuilderUtils.checkNotNull(sparkHome, "sparkHome");
      this.builder.childEnv.put("SPARK_HOME", sparkHome);
      return this;
   }

   public SparkLauncher directory(File dir) {
      this.workingDir = dir;
      return this;
   }

   public SparkLauncher redirectError() {
      this.redirectErrorStream = true;
      return this;
   }

   public SparkLauncher redirectError(ProcessBuilder.Redirect to) {
      this.errorStream = to;
      return this;
   }

   public SparkLauncher redirectOutput(ProcessBuilder.Redirect to) {
      this.outputStream = to;
      return this;
   }

   public SparkLauncher redirectError(File errFile) {
      this.errorStream = Redirect.to(errFile);
      return this;
   }

   public SparkLauncher redirectOutput(File outFile) {
      this.outputStream = Redirect.to(outFile);
      return this;
   }

   public SparkLauncher redirectToLog(String loggerName) {
      this.setConf("spark.launcher.childProcLoggerName", loggerName);
      return this;
   }

   public SparkLauncher setPropertiesFile(String path) {
      return (SparkLauncher)super.setPropertiesFile(path);
   }

   public SparkLauncher setConf(String key, String value) {
      return (SparkLauncher)super.setConf(key, value);
   }

   public SparkLauncher setAppName(String appName) {
      return (SparkLauncher)super.setAppName(appName);
   }

   public SparkLauncher setMaster(String master) {
      return (SparkLauncher)super.setMaster(master);
   }

   public SparkLauncher setDeployMode(String mode) {
      return (SparkLauncher)super.setDeployMode(mode);
   }

   public SparkLauncher setAppResource(String resource) {
      return (SparkLauncher)super.setAppResource(resource);
   }

   public SparkLauncher setMainClass(String mainClass) {
      return (SparkLauncher)super.setMainClass(mainClass);
   }

   public SparkLauncher addSparkArg(String arg) {
      return (SparkLauncher)super.addSparkArg(arg);
   }

   public SparkLauncher addSparkArg(String name, String value) {
      return (SparkLauncher)super.addSparkArg(name, value);
   }

   public SparkLauncher addAppArgs(String... args) {
      return (SparkLauncher)super.addAppArgs(args);
   }

   public SparkLauncher addJar(String jar) {
      return (SparkLauncher)super.addJar(jar);
   }

   public SparkLauncher addFile(String file) {
      return (SparkLauncher)super.addFile(file);
   }

   public SparkLauncher addPyFile(String file) {
      return (SparkLauncher)super.addPyFile(file);
   }

   public SparkLauncher setVerbose(boolean verbose) {
      return (SparkLauncher)super.setVerbose(verbose);
   }

   public Process launch() throws IOException {
      ProcessBuilder pb = this.createBuilder();
      boolean outputToLog = this.outputStream == null;
      boolean errorToLog = !this.redirectErrorStream && this.errorStream == null;
      String loggerName = this.getLoggerName();
      if (loggerName != null && outputToLog && errorToLog) {
         pb.redirectErrorStream(true);
      }

      Process childProc = pb.start();
      if (loggerName != null) {
         InputStream logStream = outputToLog ? childProc.getInputStream() : childProc.getErrorStream();
         new OutputRedirector(logStream, loggerName, REDIRECTOR_FACTORY);
      }

      return childProc;
   }

   public SparkAppHandle startApplication(SparkAppHandle.Listener... listeners) throws IOException {
      LauncherServer server = LauncherServer.getOrCreateServer();
      ChildProcAppHandle handle = new ChildProcAppHandle(server);

      for(SparkAppHandle.Listener l : listeners) {
         handle.addListener(l);
      }

      String secret = server.registerHandle(handle);
      String loggerName = this.getLoggerName();
      ProcessBuilder pb = this.createBuilder();
      if (LOG.isLoggable(Level.FINE)) {
         LOG.fine(String.format("Launching Spark application:%n%s", CommandBuilderUtils.join(" ", (Iterable)pb.command())));
      }

      boolean outputToLog = this.outputStream == null;
      boolean errorToLog = !this.redirectErrorStream && this.errorStream == null;
      if (loggerName == null && (outputToLog || errorToLog)) {
         String appName;
         if (this.builder.appName != null) {
            appName = this.builder.appName;
         } else if (this.builder.mainClass != null) {
            int dot = this.builder.mainClass.lastIndexOf(".");
            if (dot >= 0 && dot < this.builder.mainClass.length() - 1) {
               appName = this.builder.mainClass.substring(dot + 1, this.builder.mainClass.length());
            } else {
               appName = this.builder.mainClass;
            }
         } else if (this.builder.appResource != null) {
            appName = (new File(this.builder.appResource)).getName();
         } else {
            appName = String.valueOf(COUNTER.incrementAndGet());
         }

         String loggerPrefix = this.getClass().getPackage().getName();
         loggerName = String.format("%s.app.%s", loggerPrefix, appName);
      }

      if (outputToLog && errorToLog) {
         pb.redirectErrorStream(true);
      }

      pb.environment().put("_SPARK_LAUNCHER_PORT", String.valueOf(server.getPort()));
      pb.environment().put("_SPARK_LAUNCHER_SECRET", secret);

      try {
         Process child = pb.start();
         InputStream logStream = null;
         if (loggerName != null) {
            logStream = outputToLog ? child.getInputStream() : child.getErrorStream();
         }

         handle.setChildProc(child, loggerName, logStream);
         return handle;
      } catch (IOException ioe) {
         handle.kill();
         throw ioe;
      }
   }

   private ProcessBuilder createBuilder() throws IOException {
      List<String> cmd = new ArrayList();
      cmd.add(this.findSparkSubmit());
      cmd.addAll(this.builder.buildSparkSubmitArgs());
      if (CommandBuilderUtils.isWindows()) {
         List<String> winCmd = new ArrayList();

         for(String arg : cmd) {
            winCmd.add(CommandBuilderUtils.quoteForBatchScript(arg));
         }

         cmd = winCmd;
      }

      ProcessBuilder pb = new ProcessBuilder((String[])cmd.toArray(new String[cmd.size()]));

      for(Map.Entry e : this.builder.childEnv.entrySet()) {
         pb.environment().put((String)e.getKey(), (String)e.getValue());
      }

      if (this.workingDir != null) {
         pb.directory(this.workingDir);
      }

      CommandBuilderUtils.checkState(!this.redirectErrorStream || this.errorStream == null, "Cannot specify both redirectError() and redirectError(...) ");
      CommandBuilderUtils.checkState(this.getLoggerName() == null || !this.redirectErrorStream && this.errorStream == null || this.outputStream == null, "Cannot used redirectToLog() in conjunction with other redirection methods.");
      if (this.redirectErrorStream) {
         pb.redirectErrorStream(true);
      }

      if (this.errorStream != null) {
         pb.redirectError(this.errorStream);
      }

      if (this.outputStream != null) {
         pb.redirectOutput(this.outputStream);
      }

      return pb;
   }

   SparkLauncher self() {
      return this;
   }

   String findSparkSubmit() {
      String script = CommandBuilderUtils.isWindows() ? "spark-submit.cmd" : "spark-submit";
      return CommandBuilderUtils.join(File.separator, this.builder.getSparkHome(), "bin", script);
   }

   private String getLoggerName() throws IOException {
      return (String)this.builder.getEffectiveConfig().get("spark.launcher.childProcLoggerName");
   }
}
