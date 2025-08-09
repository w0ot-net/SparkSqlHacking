package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.SparkEnv$;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.executor.TaskMetrics$;
import scala.Option;
import scala.reflect.ClassTag.;
import scala.runtime.ModuleSerializationProxy;

public final class Task$ implements Serializable {
   public static final Task$ MODULE$ = new Task$();

   public Properties $lessinit$greater$default$6() {
      return new Properties();
   }

   public byte[] $lessinit$greater$default$7() {
      return SparkEnv$.MODULE$.get().closureSerializer().newInstance().serialize(TaskMetrics$.MODULE$.registered(), .MODULE$.apply(TaskMetrics.class)).array();
   }

   public Option $lessinit$greater$default$8() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$9() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$10() {
      return scala.None..MODULE$;
   }

   public boolean $lessinit$greater$default$11() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Task$.class);
   }

   private Task$() {
   }
}
