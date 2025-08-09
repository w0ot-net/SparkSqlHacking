package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import java.util.function.UnaryOperator;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;

public final class ExecutorPodsLifecycleManager$ {
   public static final ExecutorPodsLifecycleManager$ MODULE$ = new ExecutorPodsLifecycleManager$();
   private static final int UNKNOWN_EXIT_CODE = -1;

   public Clock $lessinit$greater$default$4() {
      return new SystemClock();
   }

   public int UNKNOWN_EXIT_CODE() {
      return UNKNOWN_EXIT_CODE;
   }

   public String describeExitCode(final int code) {
      String var10000;
      switch (code) {
         case 0:
            var10000 = "(success)";
            break;
         case 1:
            var10000 = "(generic, look at logs to clarify)";
            break;
         case 10:
         case 50:
            var10000 = "(Uncaught exception)";
            break;
         case 42:
            var10000 = "(Douglas Adams fan)";
            break;
         case 52:
            var10000 = "(JVM OOM)";
            break;
         case 53:
            var10000 = "(DiskStore failed to create temp dir)";
            break;
         case 126:
            var10000 = "(not executable - possibly perm or arch)";
            break;
         case 137:
            var10000 = "(SIGKILL, possible container OOM)";
            break;
         case 139:
            var10000 = "(SIGSEGV: that's unexpected)";
            break;
         case 255:
            var10000 = "(exit-1, your guess is as good as mine)";
            break;
         default:
            var10000 = "(unexpected)";
      }

      String humanStr = var10000;
      return code + humanStr;
   }

   public UnaryOperator executorInactivationFn() {
      return (p) -> ((PodBuilder)((PodFluent.MetadataNested)(new PodBuilder(p)).editOrNewMetadata().addToLabels(Constants$.MODULE$.SPARK_EXECUTOR_INACTIVE_LABEL(), "true")).endMetadata()).build();
   }

   private ExecutorPodsLifecycleManager$() {
   }
}
