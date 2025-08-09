package org.apache.spark;

import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.executor.TaskMetrics$;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.metrics.MetricsSystem;
import scala.runtime.ModuleSerializationProxy;

public final class TaskContext$ implements Serializable {
   public static final TaskContext$ MODULE$ = new TaskContext$();
   private static final ThreadLocal taskContext = new ThreadLocal();

   public TaskContext get() {
      return (TaskContext)taskContext.get();
   }

   public int getPartitionId() {
      TaskContext tc = (TaskContext)taskContext.get();
      return tc == null ? 0 : tc.partitionId();
   }

   public void setTaskContext(final TaskContext tc) {
      taskContext.set(tc);
   }

   public void unset() {
      taskContext.remove();
   }

   public TaskContextImpl empty() {
      return new TaskContextImpl(0, 0, 0, 0L, 0, 1, (TaskMemoryManager)null, new Properties(), (MetricsSystem)null, TaskMetrics$.MODULE$.empty(), 1, TaskContextImpl$.MODULE$.$lessinit$greater$default$12());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskContext$.class);
   }

   private TaskContext$() {
   }
}
