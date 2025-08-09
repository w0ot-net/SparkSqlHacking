package org.apache.spark.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

abstract class AbstractCommandBuilder {
   boolean verbose;
   String appName;
   String appResource;
   String deployMode;
   String javaHome;
   String mainClass;
   String master;
   String remote;
   protected String propertiesFile;
   final List appArgs = new ArrayList();
   final List jars = new ArrayList();
   final List files = new ArrayList();
   final List pyFiles = new ArrayList();
   final Map childEnv = new HashMap();
   final Map conf = new HashMap();
   private Map effectiveConfig;
   protected boolean isRemote = System.getenv().containsKey("SPARK_REMOTE");

   abstract List buildCommand(Map var1) throws IOException, IllegalArgumentException;

   List buildJavaCommand(String extraClassPath) throws IOException {
      List<String> cmd = new ArrayList();
      String firstJavaHome = CommandBuilderUtils.firstNonEmpty(this.javaHome, (String)this.childEnv.get("JAVA_HOME"), System.getenv("JAVA_HOME"), System.getProperty("java.home"));
      if (firstJavaHome != null) {
         cmd.add(CommandBuilderUtils.join(File.separator, firstJavaHome, "bin", "java"));
      }

      File javaOpts = new File(CommandBuilderUtils.join(File.separator, this.getConfDir(), "java-opts"));
      if (javaOpts.isFile()) {
         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(javaOpts), StandardCharsets.UTF_8));

         String line;
         try {
            while((line = br.readLine()) != null) {
               this.addOptionString(cmd, line);
            }
         } catch (Throwable var9) {
            try {
               br.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }

            throw var9;
         }

         br.close();
      }

      cmd.add("-cp");
      cmd.add(CommandBuilderUtils.join(File.pathSeparator, (Iterable)this.buildClassPath(extraClassPath)));
      return cmd;
   }

   void addOptionString(List cmd, String options) {
      if (!CommandBuilderUtils.isEmpty(options)) {
         cmd.addAll(CommandBuilderUtils.parseOptionString(options));
      }

   }

   List buildClassPath(String appClassPath) throws IOException {
      String sparkHome = this.getSparkHome();
      Set<String> cp = new LinkedHashSet();
      this.addToClassPath(cp, appClassPath);
      this.addToClassPath(cp, this.getConfDir());
      boolean prependClasses = !CommandBuilderUtils.isEmpty(this.getenv("SPARK_PREPEND_CLASSES"));
      boolean isTesting = "1".equals(this.getenv("SPARK_TESTING"));
      boolean isTestingSql = "1".equals(this.getenv("SPARK_SQL_TESTING"));
      String jarsDir = CommandBuilderUtils.findJarsDir(this.getSparkHome(), this.getScalaVersion(), !isTesting && !isTestingSql);
      if (prependClasses || isTesting) {
         String scala = this.getScalaVersion();
         List<String> projects = Arrays.asList("common/kvstore", "common/network-common", "common/network-shuffle", "common/network-yarn", "common/sketch", "common/tags", "common/unsafe", "sql/connect/common", "sql/connect/server", "core", "examples", "graphx", "launcher", "mllib", "repl", "resource-managers/yarn", "sql/catalyst", "sql/core", "sql/hive", "sql/hive-thriftserver", "streaming");
         if (prependClasses) {
            if (!isTesting) {
               System.err.println("NOTE: SPARK_PREPEND_CLASSES is set, placing locally compiled Spark classes ahead of assembly.");
            }

            boolean shouldPrePendSparkHive = isTesting || isTestingSql || this.isJarAvailable(jarsDir, "spark-hive_");
            boolean shouldPrePendSparkHiveThriftServer = isTesting || isTestingSql || shouldPrePendSparkHive && this.isJarAvailable(jarsDir, "spark-hive-thriftserver_");

            for(String project : projects) {
               if (!project.equals("sql/connect/server") && !project.equals("sql/connect/common") && (!this.isRemote || !"1".equals(this.getenv("SPARK_SCALA_SHELL")) || !project.equals("sql/core")) && (shouldPrePendSparkHive || !project.equals("sql/hive")) && (shouldPrePendSparkHiveThriftServer || !project.equals("sql/hive-thriftserver"))) {
                  this.addToClassPath(cp, String.format("%s/%s/target/scala-%s/classes", sparkHome, project, scala));
               }
            }
         }

         if (isTesting) {
            for(String project : projects) {
               this.addToClassPath(cp, String.format("%s/%s/target/scala-%s/test-classes", sparkHome, project, scala));
            }
         }

         this.addToClassPath(cp, String.format("%s/core/target/jars/*", sparkHome));
         this.addToClassPath(cp, String.format("%s/mllib/target/jars/*", sparkHome));
      }

      if (jarsDir != null) {
         for(File f : (new File(jarsDir)).listFiles()) {
            if (f.getName().startsWith("slf4j-api-")) {
               this.addToClassPath(cp, f.toString());
            }
         }

         if (this.isRemote) {
            for(File f : (new File(jarsDir)).listFiles()) {
               if (f.isDirectory() && f.getName().equals("connect-repl")) {
                  this.addToClassPath(cp, CommandBuilderUtils.join(File.separator, f.toString(), "*"));
               } else if (!f.getName().startsWith("spark-sql_") && !f.getName().startsWith("spark-connect_") && !f.getName().startsWith("spark-sql-api_") && !f.getName().startsWith("spark-connect-common_")) {
                  this.addToClassPath(cp, f.toString());
               }
            }
         } else {
            this.addToClassPath(cp, CommandBuilderUtils.join(File.separator, jarsDir, "*"));
         }
      }

      this.addToClassPath(cp, this.getenv("HADOOP_CONF_DIR"));
      this.addToClassPath(cp, this.getenv("YARN_CONF_DIR"));
      this.addToClassPath(cp, this.getenv("SPARK_DIST_CLASSPATH"));
      return new ArrayList(cp);
   }

   private void addToClassPath(Set cp, String entries) {
      if (!CommandBuilderUtils.isEmpty(entries)) {
         String[] split = entries.split(Pattern.quote(File.pathSeparator));

         for(String entry : split) {
            if (!CommandBuilderUtils.isEmpty(entry)) {
               if ((new File(entry)).isDirectory() && !entry.endsWith(File.separator)) {
                  entry = entry + File.separator;
               }

               cp.add(entry);
            }
         }

      }
   }

   private boolean isJarAvailable(String jarsDir, String jarNamePrefix) {
      if (jarsDir != null) {
         for(File f : (new File(jarsDir)).listFiles()) {
            if (f.getName().startsWith(jarNamePrefix)) {
               return true;
            }
         }
      }

      return false;
   }

   String getScalaVersion() {
      String scala = this.getenv("SPARK_SCALA_VERSION");
      if (scala != null) {
         return scala;
      } else {
         String sparkHome = this.getSparkHome();
         File scala213 = new File(sparkHome, "launcher/target/scala-2.13");
         CommandBuilderUtils.checkState(scala213.isDirectory(), "Cannot find any build directories.");
         return "2.13";
      }
   }

   String getSparkHome() {
      String path = this.getenv("SPARK_HOME");
      if (path == null && "1".equals(this.getenv("SPARK_TESTING"))) {
         path = System.getProperty("spark.test.home");
      }

      CommandBuilderUtils.checkState(path != null, "Spark home not found; set it explicitly or use the SPARK_HOME environment variable.");
      return path;
   }

   String getenv(String key) {
      return CommandBuilderUtils.firstNonEmpty((String)this.childEnv.get(key), System.getenv(key));
   }

   void setPropertiesFile(String path) {
      this.effectiveConfig = null;
      this.propertiesFile = path;
   }

   Map getEffectiveConfig() throws IOException {
      if (this.effectiveConfig == null) {
         this.effectiveConfig = new HashMap(this.conf);
         Properties p = this.loadPropertiesFile();
         p.stringPropertyNames().forEach((key) -> {
            Map var10000 = this.effectiveConfig;
            Objects.requireNonNull(p);
            var10000.computeIfAbsent(key, p::getProperty);
         });
         this.effectiveConfig.putIfAbsent("spark.driver.defaultExtraClassPath", "hive-jackson/*");
      }

      return this.effectiveConfig;
   }

   private Properties loadPropertiesFile() throws IOException {
      Properties props = new Properties();
      File propsFile;
      if (this.propertiesFile != null) {
         propsFile = new File(this.propertiesFile);
         CommandBuilderUtils.checkArgument(propsFile.isFile(), "Invalid properties file '%s'.", this.propertiesFile);
      } else {
         propsFile = new File(this.getConfDir(), "spark-defaults.conf");
      }

      if (propsFile.isFile()) {
         InputStreamReader isr = new InputStreamReader(new FileInputStream(propsFile), StandardCharsets.UTF_8);

         try {
            props.load(isr);

            for(Map.Entry e : props.entrySet()) {
               e.setValue(e.getValue().toString().trim());
            }
         } catch (Throwable var7) {
            try {
               isr.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         isr.close();
      }

      return props;
   }

   private String getConfDir() {
      String confDir = this.getenv("SPARK_CONF_DIR");
      return confDir != null ? confDir : CommandBuilderUtils.join(File.separator, this.getSparkHome(), "conf");
   }
}
