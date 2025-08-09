package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.records.ApplicationAccessType;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.launcher.YarnCommandBuilderUtils$;
import org.apache.spark.resource.ExecutorResourceRequest;
import scala.Predef;
import scala.Tuple2;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.util.matching.Regex;

public final class YarnSparkHadoopUtil$ {
   public static final YarnSparkHadoopUtil$ MODULE$ = new YarnSparkHadoopUtil$();
   private static final double AM_MEMORY_OVERHEAD_FACTOR = 0.1;
   private static final String ANY_HOST = "*";
   private static final Priority RM_REQUEST_PRIORITY = Priority.newInstance(1);
   private static final String envVarNameRegex = "[A-Za-z_][A-Za-z0-9_]*";

   public double AM_MEMORY_OVERHEAD_FACTOR() {
      return AM_MEMORY_OVERHEAD_FACTOR;
   }

   public String ANY_HOST() {
      return ANY_HOST;
   }

   public Priority RM_REQUEST_PRIORITY() {
      return RM_REQUEST_PRIORITY;
   }

   public void addPathToEnvironment(final HashMap env, final String key, final String value) {
      String newValue = env.contains(key) ? (String)env.apply(key) + "<CPS>" + value : value;
      env.put(key, newValue);
   }

   private String envVarNameRegex() {
      return envVarNameRegex;
   }

   public String replaceEnvVars(final String unresolvedString, final Map env, final boolean isWindows) {
      String var10000;
      if (isWindows) {
         Regex windowsPattern = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(?i)(?:\\^\\^|\\^%|%%|%(" + this.envVarNameRegex() + ")%)"));
         var10000 = windowsPattern.replaceAllIn(unresolvedString, (m) -> {
            Regex var10000 = scala.util.matching.Regex..MODULE$;
            String var3 = m.matched();
            String var10001;
            switch (var3 == null ? 0 : var3.hashCode()) {
               case 1184:
                  if ("%%".equals(var3)) {
                     var10001 = "%";
                     return var10000.quoteReplacement(var10001);
                  }
                  break;
               case 2951:
                  if ("^%".equals(var3)) {
                     var10001 = "%";
                     return var10000.quoteReplacement(var10001);
                  }
                  break;
               case 3008:
                  if ("^^".equals(var3)) {
                     var10001 = "^";
                     return var10000.quoteReplacement(var10001);
                  }
            }

            var10001 = (String)env.getOrElse(m.group(1), () -> "");
            return var10000.quoteReplacement(var10001);
         });
      } else {
         StringOps var8 = .MODULE$;
         Predef var10001 = scala.Predef..MODULE$;
         String var10002 = this.envVarNameRegex();
         Regex unixPattern = var8.r$extension(var10001.augmentString("(?i)(?:\\\\\\\\|\\\\\\$|\\$(" + var10002 + ")|\\$\\{(" + this.envVarNameRegex() + ")})"));
         var10000 = unixPattern.replaceAllIn(unresolvedString, (m) -> {
            Regex var10000 = scala.util.matching.Regex..MODULE$;
            String var3 = m.matched();
            String var10001;
            switch (var3 == null ? 0 : var3.hashCode()) {
               case 2888:
                  if ("\\$".equals(var3)) {
                     var10001 = "$";
                     return var10000.quoteReplacement(var10001);
                  }
                  break;
               case 2944:
                  if ("\\\\".equals(var3)) {
                     var10001 = "\\";
                     return var10000.quoteReplacement(var10001);
                  }
            }

            var10001 = var3.startsWith("${") ? (String)env.getOrElse(m.group(2), () -> "") : (String)env.getOrElse(m.group(1), () -> "");
            return var10000.quoteReplacement(var10001);
         });
      }

      String osResolvedString = var10000;
      Regex yarnPattern = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(?i)\\{\\{(" + this.envVarNameRegex() + ")}}"));
      return yarnPattern.replaceAllIn(osResolvedString, (m) -> scala.util.matching.Regex..MODULE$.quoteReplacement((String)env.getOrElse(m.group(1), () -> "")));
   }

   public boolean replaceEnvVars$default$3() {
      return org.apache.spark.util.Utils..MODULE$.isWindows();
   }

   public void addOutOfMemoryErrorArgument(final ListBuffer javaOpts) {
      if (!javaOpts.exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$addOutOfMemoryErrorArgument$1(x$1)))) {
         if (org.apache.spark.util.Utils..MODULE$.isWindows()) {
            javaOpts.$plus$eq(this.escapeForShell("-XX:OnOutOfMemoryError=taskkill /F /PID %%%%p"));
         } else {
            javaOpts.$plus$eq("-XX:OnOutOfMemoryError='kill %p'");
         }
      }
   }

   public String escapeForShell(final String arg) {
      if (arg != null) {
         if (org.apache.spark.util.Utils..MODULE$.isWindows()) {
            return YarnCommandBuilderUtils$.MODULE$.quoteForBatchScript(arg);
         } else {
            StringBuilder escaped = new StringBuilder("'");
            .MODULE$.foreach$extension(scala.Predef..MODULE$.augmentString(arg), (x0$1) -> $anonfun$escapeForShell$1(escaped, BoxesRunTime.unboxToChar(x0$1)));
            return escaped.append("'").toString();
         }
      } else {
         return arg;
      }
   }

   public Map getApplicationAclsForYarn(final SecurityManager securityMgr) {
      Map var10000 = scala.Predef..MODULE$.Map();
      ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
      Tuple2[] var10002 = new Tuple2[2];
      Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
      Object var10006 = scala.Predef..MODULE$.ArrowAssoc(ApplicationAccessType.VIEW_APP);
      String var10007 = securityMgr.getViewAcls();
      var10002[0] = var10005.$minus$greater$extension(var10006, var10007 + " " + securityMgr.getViewAclsGroups());
      var10005 = scala.Predef.ArrowAssoc..MODULE$;
      var10006 = scala.Predef..MODULE$.ArrowAssoc(ApplicationAccessType.MODIFY_APP);
      var10007 = securityMgr.getModifyAcls();
      var10002[1] = var10005.$minus$greater$extension(var10006, var10007 + " " + securityMgr.getModifyAclsGroups());
      return (Map)var10000.apply(var10001.wrapRefArray((Object[])var10002));
   }

   public ContainerId getContainerId() {
      String containerIdString = System.getenv(Environment.CONTAINER_ID.name());
      return ContainerId.fromString(containerIdString);
   }

   public long executorOffHeapMemorySizeAsMb(final SparkConf sparkConf, final ExecutorResourceRequest execRequest) {
      return org.apache.spark.util.Utils..MODULE$.checkOffHeapEnabled(sparkConf, execRequest.amount());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$addOutOfMemoryErrorArgument$1(final String x$1) {
      return x$1.contains("-XX:OnOutOfMemoryError");
   }

   // $FF: synthetic method
   public static final StringBuilder $anonfun$escapeForShell$1(final StringBuilder escaped$1, final char x0$1) {
      switch (x0$1) {
         case '"' -> {
            return escaped$1.append("\\\"");
         }
         case '$' -> {
            return escaped$1.append("\\$");
         }
         case '\'' -> {
            return escaped$1.append("'\\''");
         }
         default -> {
            return escaped$1.append(x0$1);
         }
      }
   }

   private YarnSparkHadoopUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
