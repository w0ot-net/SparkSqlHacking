package org.apache.spark.launcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class SparkClassCommandBuilder extends AbstractCommandBuilder {
   private final String className;
   private final List classArgs;

   SparkClassCommandBuilder(String className, List classArgs) {
      this.className = className;
      this.classArgs = classArgs;
   }

   public List buildCommand(Map env) throws IOException, IllegalArgumentException {
      List<String> javaOptsKeys = new ArrayList();
      String extraClassPath = null;
      String var10000;
      switch (this.className) {
         case "org.apache.spark.deploy.master.Master":
            javaOptsKeys.add("SPARK_DAEMON_JAVA_OPTS");
            javaOptsKeys.add("SPARK_MASTER_OPTS");
            extraClassPath = this.getenv("SPARK_DAEMON_CLASSPATH");
            var10000 = "SPARK_DAEMON_MEMORY";
            break;
         case "org.apache.spark.deploy.worker.Worker":
            javaOptsKeys.add("SPARK_DAEMON_JAVA_OPTS");
            javaOptsKeys.add("SPARK_WORKER_OPTS");
            extraClassPath = this.getenv("SPARK_DAEMON_CLASSPATH");
            var10000 = "SPARK_DAEMON_MEMORY";
            break;
         case "org.apache.spark.deploy.history.HistoryServer":
            javaOptsKeys.add("SPARK_DAEMON_JAVA_OPTS");
            javaOptsKeys.add("SPARK_HISTORY_OPTS");
            extraClassPath = this.getenv("SPARK_DAEMON_CLASSPATH");
            var10000 = "SPARK_DAEMON_MEMORY";
            break;
         case "org.apache.spark.executor.CoarseGrainedExecutorBackend":
            javaOptsKeys.add("SPARK_EXECUTOR_OPTS");
            extraClassPath = this.getenv("SPARK_EXECUTOR_CLASSPATH");
            var10000 = "SPARK_EXECUTOR_MEMORY";
            break;
         case "org.apache.spark.deploy.ExternalShuffleService":
            javaOptsKeys.add("SPARK_DAEMON_JAVA_OPTS");
            javaOptsKeys.add("SPARK_SHUFFLE_OPTS");
            extraClassPath = this.getenv("SPARK_DAEMON_CLASSPATH");
            var10000 = "SPARK_DAEMON_MEMORY";
            break;
         case "org.apache.hive.beeline.BeeLine":
            javaOptsKeys.add("SPARK_BEELINE_OPTS");
            var10000 = "SPARK_BEELINE_MEMORY";
            break;
         default:
            var10000 = "SPARK_DRIVER_MEMORY";
      }

      String memKey = var10000;
      List<String> cmd = this.buildJavaCommand(extraClassPath);

      for(String key : javaOptsKeys) {
         String envValue = System.getenv(key);
         if (!CommandBuilderUtils.isEmpty(envValue) && envValue.contains("Xmx")) {
            String msg = String.format("%s is not allowed to specify max heap(Xmx) memory settings (was %s). Use the corresponding configuration instead.", key, envValue);
            throw new IllegalArgumentException(msg);
         }

         this.addOptionString(cmd, envValue);
      }

      String mem = CommandBuilderUtils.firstNonEmpty(memKey != null ? System.getenv(memKey) : null, "1g");
      cmd.add("-Xmx" + mem);
      cmd.add(this.className);
      cmd.addAll(this.classArgs);
      return cmd;
   }
}
