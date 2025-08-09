package org.apache.spark.status.api.v1;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.collection.Seq;
import scala.runtime.AbstractFunction7;
import scala.runtime.ModuleSerializationProxy;

public final class ApplicationInfo$ extends AbstractFunction7 implements Serializable {
   public static final ApplicationInfo$ MODULE$ = new ApplicationInfo$();

   public final String toString() {
      return "ApplicationInfo";
   }

   public ApplicationInfo apply(final String id, final String name, final Option coresGranted, final Option maxCores, final Option coresPerExecutor, final Option memoryPerExecutorMB, final Seq attempts) {
      return new ApplicationInfo(id, name, coresGranted, maxCores, coresPerExecutor, memoryPerExecutorMB, attempts);
   }

   public Option unapply(final ApplicationInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(x$0.id(), x$0.name(), x$0.coresGranted(), x$0.maxCores(), x$0.coresPerExecutor(), x$0.memoryPerExecutorMB(), x$0.attempts())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ApplicationInfo$.class);
   }

   private ApplicationInfo$() {
   }
}
