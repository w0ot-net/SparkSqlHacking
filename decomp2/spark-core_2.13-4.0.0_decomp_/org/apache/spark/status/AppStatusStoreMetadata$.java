package org.apache.spark.status;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AppStatusStoreMetadata$ extends AbstractFunction1 implements Serializable {
   public static final AppStatusStoreMetadata$ MODULE$ = new AppStatusStoreMetadata$();

   public final String toString() {
      return "AppStatusStoreMetadata";
   }

   public AppStatusStoreMetadata apply(final long version) {
      return new AppStatusStoreMetadata(version);
   }

   public Option unapply(final AppStatusStoreMetadata x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.version())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AppStatusStoreMetadata$.class);
   }

   private AppStatusStoreMetadata$() {
   }
}
