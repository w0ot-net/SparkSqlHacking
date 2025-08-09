package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class TaskCompletionListenerException$ implements Serializable {
   public static final TaskCompletionListenerException$ MODULE$ = new TaskCompletionListenerException$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskCompletionListenerException$.class);
   }

   private TaskCompletionListenerException$() {
   }
}
