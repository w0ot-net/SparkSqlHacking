package org.apache.spark.deploy.history;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ApplicationStoreInfo$ extends AbstractFunction5 implements Serializable {
   public static final ApplicationStoreInfo$ MODULE$ = new ApplicationStoreInfo$();

   public final String toString() {
      return "ApplicationStoreInfo";
   }

   public ApplicationStoreInfo apply(final String path, final long lastAccess, final String appId, final Option attemptId, final long size) {
      return new ApplicationStoreInfo(path, lastAccess, appId, attemptId, size);
   }

   public Option unapply(final ApplicationStoreInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.path(), BoxesRunTime.boxToLong(x$0.lastAccess()), x$0.appId(), x$0.attemptId(), BoxesRunTime.boxToLong(x$0.size()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ApplicationStoreInfo$.class);
   }

   private ApplicationStoreInfo$() {
   }
}
