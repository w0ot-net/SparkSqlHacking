package org.apache.spark.scheduler.local;

import java.io.Serializable;
import scala.runtime.AbstractFunction0;
import scala.runtime.ModuleSerializationProxy;

public final class StopExecutor$ extends AbstractFunction0 implements Serializable {
   public static final StopExecutor$ MODULE$ = new StopExecutor$();

   public final String toString() {
      return "StopExecutor";
   }

   public StopExecutor apply() {
      return new StopExecutor();
   }

   public boolean unapply(final StopExecutor x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StopExecutor$.class);
   }

   private StopExecutor$() {
   }
}
