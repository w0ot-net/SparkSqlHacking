package org.apache.spark.launcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class SparkSubmitCommandBuilder extends AbstractCommandBuilder {
   static final String PYSPARK_SHELL = "pyspark-shell-main";
   static final String PYSPARK_SHELL_RESOURCE = "pyspark-shell";
   static final String SPARKR_SHELL = "sparkr-shell-main";
   static final String SPARKR_SHELL_RESOURCE = "sparkr-shell";
   static final String RUN_EXAMPLE = "run-example";
   static final String EXAMPLE_CLASS_PREFIX = "org.apache.spark.examples.";
   private static final Map specialClasses = new HashMap();
   final List userArgs;
   private final List parsedArgs;
   private final boolean isSpecialCommand;
   private final boolean isExample;
   private boolean allowsMixedArguments;

   SparkSubmitCommandBuilder() {
      this.isSpecialCommand = false;
      this.isExample = false;
      this.parsedArgs = new ArrayList();
      this.userArgs = new ArrayList();
   }

   SparkSubmitCommandBuilder(List args) {
      this.allowsMixedArguments = false;
      this.parsedArgs = new ArrayList();
      boolean isExample = false;
      List<String> submitArgs = args;
      this.userArgs = Collections.emptyList();
      this.isRemote |= "connect".equalsIgnoreCase(getApiMode(this.conf));
      if (args.size() > 0) {
         switch ((String)args.get(0)) {
            case "pyspark-shell-main":
               this.allowsMixedArguments = true;
               this.appResource = "pyspark-shell-main";
               submitArgs = args.subList(1, args.size());
               break;
            case "sparkr-shell-main":
               this.allowsMixedArguments = true;
               this.appResource = "sparkr-shell-main";
               submitArgs = args.subList(1, args.size());
               break;
            case "run-example":
               isExample = true;
               this.appResource = this.findExamplesAppJar();
               submitArgs = args.subList(1, args.size());
         }

         this.isExample = isExample;
         OptionParser parser = new OptionParser(true);
         parser.parse(submitArgs);
         this.isSpecialCommand = parser.isSpecialCommand;
      } else {
         this.isExample = isExample;
         this.isSpecialCommand = true;
      }

   }

   private static String getApiMode(Map conf) {
      boolean connectByDefault = "1".equals(System.getenv("SPARK_CONNECT_MODE"));
      String defaultApiMode = connectByDefault ? "connect" : "classic";
      String apiMode = (String)conf.get("spark.api.mode");
      return !"classic".equalsIgnoreCase(apiMode) && !"connect".equalsIgnoreCase(apiMode) ? defaultApiMode : apiMode;
   }

   public List buildCommand(Map env) throws IOException, IllegalArgumentException {
      if ("pyspark-shell-main".equals(this.appResource) && !this.isSpecialCommand) {
         return this.buildPySparkShellCommand(env);
      } else {
         return "sparkr-shell-main".equals(this.appResource) && !this.isSpecialCommand ? this.buildSparkRCommand(env) : this.buildSparkSubmitCommand(env);
      }
   }

   List buildSparkSubmitArgs() {
      return this.buildSparkSubmitArgs(true);
   }

   List buildSparkSubmitArgs(boolean includeRemote) {
      List<String> args = new ArrayList();
      OptionParser parser = new OptionParser(false);
      boolean isSpecialCommand;
      if (!this.userArgs.isEmpty()) {
         parser.parse(this.userArgs);
         isSpecialCommand = parser.isSpecialCommand;
      } else {
         isSpecialCommand = this.isSpecialCommand;
      }

      if (!this.allowsMixedArguments && !isSpecialCommand) {
         CommandBuilderUtils.checkArgument(this.appResource != null, "Missing application resource.");
      }

      if (this.verbose) {
         Objects.requireNonNull(parser);
         args.add("--verbose");
      }

      if (this.master != null) {
         Objects.requireNonNull(parser);
         args.add("--master");
         args.add(this.master);
      }

      if (includeRemote && this.remote != null) {
         Objects.requireNonNull(parser);
         args.add("--remote");
         args.add(this.remote);
      }

      if (this.deployMode != null) {
         Objects.requireNonNull(parser);
         args.add("--deploy-mode");
         args.add(this.deployMode);
      }

      if (this.appName != null) {
         Objects.requireNonNull(parser);
         args.add("--name");
         args.add(this.appName);
      }

      for(Map.Entry e : this.conf.entrySet()) {
         if (includeRemote || !((String)e.getKey()).equalsIgnoreCase("spark.api.mode") && !((String)e.getKey()).equalsIgnoreCase("spark.remote")) {
            Objects.requireNonNull(parser);
            args.add("--conf");
            args.add(String.format("%s=%s", e.getKey(), e.getValue()));
         }
      }

      if (this.propertiesFile != null) {
         Objects.requireNonNull(parser);
         args.add("--properties-file");
         args.add(this.propertiesFile);
      }

      if (this.isExample) {
         this.jars.addAll(this.findExamplesJars());
      }

      if (!this.jars.isEmpty()) {
         Objects.requireNonNull(parser);
         args.add("--jars");
         args.add(CommandBuilderUtils.join(",", (Iterable)this.jars));
      }

      if (!this.files.isEmpty()) {
         Objects.requireNonNull(parser);
         args.add("--files");
         args.add(CommandBuilderUtils.join(",", (Iterable)this.files));
      }

      if (!this.pyFiles.isEmpty()) {
         Objects.requireNonNull(parser);
         args.add("--py-files");
         args.add(CommandBuilderUtils.join(",", (Iterable)this.pyFiles));
      }

      if (this.isExample && !isSpecialCommand) {
         CommandBuilderUtils.checkArgument(this.mainClass != null, "Missing example class name.");
      }

      if (this.mainClass != null) {
         Objects.requireNonNull(parser);
         args.add("--class");
         if (this.isRemote && "1".equals(this.getenv("SPARK_SCALA_SHELL"))) {
            args.add("org.apache.spark.sql.application.ConnectRepl");
         } else {
            args.add(this.mainClass);
         }
      }

      args.addAll(this.parsedArgs);
      if (this.appResource != null) {
         if (this.isRemote && "1".equals(this.getenv("SPARK_SCALA_SHELL"))) {
            args.add("connect-shell");
         } else {
            args.add(this.appResource);
         }
      }

      args.addAll(this.appArgs);
      return args;
   }

   private List buildSparkSubmitCommand(Map env) throws IOException, IllegalArgumentException {
      Map<String, String> config = this.getEffectiveConfig();
      boolean isClientMode = this.isClientMode(config);
      String extraClassPath = isClientMode ? (String)config.get("spark.driver.extraClassPath") : null;
      String defaultExtraClassPath = (String)config.get("spark.driver.defaultExtraClassPath");
      if (extraClassPath != null && !extraClassPath.trim().isEmpty()) {
         extraClassPath = extraClassPath + File.pathSeparator + defaultExtraClassPath;
      } else {
         extraClassPath = defaultExtraClassPath;
      }

      List<String> cmd = this.buildJavaCommand(extraClassPath);
      if (this.isThriftServer(this.mainClass) || this.isConnectServer(this.mainClass)) {
         this.addOptionString(cmd, System.getenv("SPARK_DAEMON_JAVA_OPTS"));
      }

      this.addOptionString(cmd, System.getenv("SPARK_SUBMIT_OPTS"));
      String driverDefaultJavaOptions = (String)config.get("spark.driver.defaultJavaOptions");
      this.checkJavaOptions(driverDefaultJavaOptions);
      String driverExtraJavaOptions = (String)config.get("spark.driver.extraJavaOptions");
      this.checkJavaOptions(driverExtraJavaOptions);
      if (isClientMode) {
         String tsMemory = !this.isThriftServer(this.mainClass) && !this.isConnectServer(this.mainClass) ? null : System.getenv("SPARK_DAEMON_MEMORY");
         String memory = CommandBuilderUtils.firstNonEmpty(tsMemory, (String)config.get("spark.driver.memory"), System.getenv("SPARK_DRIVER_MEMORY"), System.getenv("SPARK_MEM"), "1g");
         cmd.add("-Xmx" + memory);
         this.addOptionString(cmd, driverDefaultJavaOptions);
         this.addOptionString(cmd, driverExtraJavaOptions);
         CommandBuilderUtils.mergeEnvPathList(env, CommandBuilderUtils.getLibPathEnvName(), (String)config.get("spark.driver.extraLibraryPath"));
      }

      this.addOptionString(cmd, JavaModuleOptions.defaultModuleOptions());
      this.addOptionString(cmd, "-Dderby.connection.requireAuthentication=false");
      cmd.add("org.apache.spark.deploy.SparkSubmit");
      cmd.addAll(this.buildSparkSubmitArgs());
      return cmd;
   }

   private void checkJavaOptions(String javaOptions) {
      if (!CommandBuilderUtils.isEmpty(javaOptions)) {
         for(String javaOption : CommandBuilderUtils.parseOptionString(javaOptions)) {
            if (javaOption.startsWith("-Xmx")) {
               String msg = String.format("Not allowed to specify max heap(Xmx) memory settings through java options (was %s). Use the corresponding --driver-memory or spark.driver.memory configuration instead.", javaOptions);
               throw new IllegalArgumentException(msg);
            }
         }
      }

   }

   private List buildPySparkShellCommand(Map env) throws IOException {
      if (!this.appArgs.isEmpty() && ((String)this.appArgs.get(0)).endsWith(".py")) {
         System.err.println("Running python applications through 'pyspark' is not supported as of Spark 2.0.\nUse ./bin/spark-submit <python file>");
         System.exit(-1);
      }

      CommandBuilderUtils.checkArgument(this.appArgs.isEmpty(), "pyspark does not support any application options.");
      this.appResource = "pyspark-shell";
      this.constructEnvVarArgs(env, "PYSPARK_SUBMIT_ARGS", false);
      List<String> pyargs = new ArrayList();
      pyargs.add(CommandBuilderUtils.firstNonEmpty((String)this.conf.get("spark.pyspark.driver.python"), (String)this.conf.get("spark.pyspark.python"), System.getenv("PYSPARK_DRIVER_PYTHON"), System.getenv("PYSPARK_PYTHON"), "python3"));
      String pyOpts = System.getenv("PYSPARK_DRIVER_PYTHON_OPTS");
      if (this.conf.containsKey("spark.pyspark.python")) {
         env.put("PYSPARK_PYTHON", (String)this.conf.get("spark.pyspark.python"));
      }

      String remoteStr = CommandBuilderUtils.firstNonEmpty(this.remote, (String)this.conf.getOrDefault("spark.remote", (Object)null));
      String masterStr = CommandBuilderUtils.firstNonEmpty(this.master, (String)this.conf.getOrDefault("spark.master", (Object)null));
      String deployStr = CommandBuilderUtils.firstNonEmpty(this.deployMode, (String)this.conf.getOrDefault("spark.submit.deployMode", (Object)null));
      if (remoteStr == null || masterStr == null && deployStr == null) {
         String apiMode = getApiMode(this.conf);
         env.put("SPARK_API_MODE", apiMode);
         if (remoteStr != null) {
            env.put("SPARK_REMOTE", remoteStr);
            env.put("SPARK_CONNECT_MODE_ENABLED", "1");
         } else if ("connect".equalsIgnoreCase(apiMode)) {
            env.put("MASTER", CommandBuilderUtils.firstNonEmpty(masterStr, "local"));
            env.put("SPARK_CONNECT_MODE_ENABLED", "1");
         }

         if (!CommandBuilderUtils.isEmpty(pyOpts)) {
            pyargs.addAll(CommandBuilderUtils.parseOptionString(pyOpts));
         }

         return pyargs;
      } else {
         throw new IllegalStateException("Remote cannot be specified with master and/or deploy mode.");
      }
   }

   private List buildSparkRCommand(Map env) throws IOException {
      if (!this.appArgs.isEmpty() && (((String)this.appArgs.get(0)).endsWith(".R") || ((String)this.appArgs.get(0)).endsWith(".r"))) {
         System.err.println("Running R applications through 'sparkR' is not supported as of Spark 2.0.\nUse ./bin/spark-submit <R file>");
         System.exit(-1);
      }

      this.appResource = "sparkr-shell";
      this.constructEnvVarArgs(env, "SPARKR_SUBMIT_ARGS", true);
      String sparkHome = System.getenv("SPARK_HOME");
      env.put("R_PROFILE_USER", CommandBuilderUtils.join(File.separator, sparkHome, "R", "lib", "SparkR", "profile", "shell.R"));
      List<String> args = new ArrayList();
      args.add(CommandBuilderUtils.firstNonEmpty((String)this.conf.get("spark.r.shell.command"), System.getenv("SPARKR_DRIVER_R"), "R"));
      return args;
   }

   private void constructEnvVarArgs(Map env, String submitArgsEnvVariable, boolean includeRemote) throws IOException {
      CommandBuilderUtils.mergeEnvPathList(env, CommandBuilderUtils.getLibPathEnvName(), (String)this.getEffectiveConfig().get("spark.driver.extraLibraryPath"));
      StringBuilder submitArgs = new StringBuilder();

      for(String arg : this.buildSparkSubmitArgs(includeRemote)) {
         if (submitArgs.length() > 0) {
            submitArgs.append(" ");
         }

         submitArgs.append(CommandBuilderUtils.quoteForCommandString(arg));
      }

      env.put(submitArgsEnvVariable, submitArgs.toString());
   }

   boolean isClientMode(Map userProps) {
      String userMaster = CommandBuilderUtils.firstNonEmpty(this.master, (String)userProps.get("spark.master"));
      String userDeployMode = CommandBuilderUtils.firstNonEmpty(this.deployMode, (String)userProps.get("spark.submit.deployMode"));
      return userMaster == null || userDeployMode == null || "client".equals(userDeployMode);
   }

   private boolean isThriftServer(String mainClass) {
      return mainClass != null && mainClass.equals("org.apache.spark.sql.hive.thriftserver.HiveThriftServer2");
   }

   private boolean isConnectServer(String mainClass) {
      return mainClass != null && mainClass.equals("org.apache.spark.sql.connect.service.SparkConnectServer");
   }

   private String findExamplesAppJar() {
      boolean isTesting = "1".equals(this.getenv("SPARK_TESTING"));
      if (isTesting) {
         return "spark-internal";
      } else {
         for(String exampleJar : this.findExamplesJars()) {
            if ((new File(exampleJar)).getName().startsWith("spark-examples")) {
               return exampleJar;
            }
         }

         throw new IllegalStateException("Failed to find examples' main app jar.");
      }
   }

   private List findExamplesJars() {
      boolean isTesting = "1".equals(this.getenv("SPARK_TESTING"));
      List<String> examplesJars = new ArrayList();
      String sparkHome = this.getSparkHome();
      File jarsDir;
      if ((new File(sparkHome, "RELEASE")).isFile()) {
         jarsDir = new File(sparkHome, "examples/jars");
      } else {
         jarsDir = new File(sparkHome, String.format("examples/target/scala-%s/jars", this.getScalaVersion()));
      }

      boolean foundDir = jarsDir.isDirectory();
      CommandBuilderUtils.checkState(isTesting || foundDir, "Examples jars directory '%s' does not exist.", jarsDir.getAbsolutePath());
      if (foundDir) {
         for(File f : jarsDir.listFiles()) {
            examplesJars.add(f.getAbsolutePath());
         }
      }

      return examplesJars;
   }

   static {
      specialClasses.put("org.apache.spark.repl.Main", "spark-shell");
      specialClasses.put("org.apache.spark.sql.application.ConnectRepl", "connect-shell");
      specialClasses.put("org.apache.spark.sql.hive.thriftserver.SparkSQLCLIDriver", "spark-internal");
      specialClasses.put("org.apache.spark.sql.hive.thriftserver.HiveThriftServer2", "spark-internal");
      specialClasses.put("org.apache.spark.sql.connect.service.SparkConnectServer", "spark-internal");
   }

   private class OptionParser extends SparkSubmitOptionParser {
      boolean isSpecialCommand = false;
      private final boolean errorOnUnknownArgs;

      OptionParser(boolean errorOnUnknownArgs) {
         this.errorOnUnknownArgs = errorOnUnknownArgs;
      }

      protected boolean handle(String opt, String value) {
         switch (opt) {
            case "--master":
               SparkSubmitCommandBuilder.this.master = value;
               break;
            case "--remote":
               SparkSubmitCommandBuilder.this.isRemote = true;
               SparkSubmitCommandBuilder.this.remote = value;
               break;
            case "--deploy-mode":
               SparkSubmitCommandBuilder.this.deployMode = value;
               break;
            case "--properties-file":
               SparkSubmitCommandBuilder.this.propertiesFile = value;
               break;
            case "--driver-memory":
               SparkSubmitCommandBuilder.this.conf.put("spark.driver.memory", value);
               break;
            case "--driver-java-options":
               SparkSubmitCommandBuilder.this.conf.put("spark.driver.extraJavaOptions", value);
               break;
            case "--driver-library-path":
               SparkSubmitCommandBuilder.this.conf.put("spark.driver.extraLibraryPath", value);
               break;
            case "--driver-default-class-path":
               SparkSubmitCommandBuilder.this.conf.put("spark.driver.defaultExtraClassPath", value);
               break;
            case "--driver-class-path":
               SparkSubmitCommandBuilder.this.conf.put("spark.driver.extraClassPath", value);
               break;
            case "--conf":
               CommandBuilderUtils.checkArgument(value != null, "Missing argument to %s", "--conf");
               String[] setConf = value.split("=", 2);
               CommandBuilderUtils.checkArgument(setConf.length == 2, "Invalid argument to %s: %s", "--conf", value);
               if (setConf[0].equals("spark.remote")) {
                  SparkSubmitCommandBuilder.this.isRemote = true;
               } else if (setConf[0].equals("spark.api.mode")) {
                  SparkSubmitCommandBuilder.this.isRemote = setConf[1].equalsIgnoreCase("connect");
               }

               SparkSubmitCommandBuilder.this.conf.put(setConf[0], setConf[1]);
               break;
            case "--class":
               SparkSubmitCommandBuilder.this.mainClass = value;
               if (SparkSubmitCommandBuilder.specialClasses.containsKey(value)) {
                  SparkSubmitCommandBuilder.this.allowsMixedArguments = true;
                  SparkSubmitCommandBuilder.this.appResource = (String)SparkSubmitCommandBuilder.specialClasses.get(value);
               }
               break;
            case "--kill":
            case "--status":
               this.isSpecialCommand = true;
               SparkSubmitCommandBuilder.this.parsedArgs.add(opt);
               SparkSubmitCommandBuilder.this.parsedArgs.add(value);
               break;
            case "--help":
            case "--usage-error":
            case "--version":
               this.isSpecialCommand = true;
               SparkSubmitCommandBuilder.this.parsedArgs.add(opt);
               break;
            default:
               SparkSubmitCommandBuilder.this.parsedArgs.add(opt);
               if (value != null) {
                  SparkSubmitCommandBuilder.this.parsedArgs.add(value);
               }
         }

         return true;
      }

      protected boolean handleUnknown(String opt) {
         if (SparkSubmitCommandBuilder.this.allowsMixedArguments) {
            SparkSubmitCommandBuilder.this.appArgs.add(opt);
            return true;
         } else if (SparkSubmitCommandBuilder.this.isExample) {
            String className = opt;
            if (!opt.startsWith("org.apache.spark.examples.")) {
               className = "org.apache.spark.examples." + opt;
            }

            SparkSubmitCommandBuilder.this.mainClass = className;
            SparkSubmitCommandBuilder.this.appResource = SparkSubmitCommandBuilder.this.findExamplesAppJar();
            return false;
         } else if (this.errorOnUnknownArgs) {
            CommandBuilderUtils.checkArgument(!opt.startsWith("-"), "Unrecognized option: %s", opt);
            CommandBuilderUtils.checkState(SparkSubmitCommandBuilder.this.appResource == null, "Found unrecognized argument but resource is already set.");
            SparkSubmitCommandBuilder.this.appResource = opt;
            return false;
         } else {
            return true;
         }
      }

      protected void handleExtraArgs(List extra) {
         SparkSubmitCommandBuilder.this.appArgs.addAll(extra);
      }
   }
}
