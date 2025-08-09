package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.executor.TaskMetrics$;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class TaskContextImpl$ implements Serializable {
   public static final TaskContextImpl$ MODULE$ = new TaskContextImpl$();

   public TaskMetrics $lessinit$greater$default$10() {
      return TaskMetrics$.MODULE$.empty();
   }

   public int $lessinit$greater$default$11() {
      return BoxesRunTime.unboxToInt(SparkEnv$.MODULE$.get().conf().get(org.apache.spark.internal.config.package$.MODULE$.CPUS_PER_TASK()));
   }

   public Map $lessinit$greater$default$12() {
      return .MODULE$.Map().empty();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskContextImpl$.class);
   }

   private TaskContextImpl$() {
   }
}
