package org.apache.spark.deploy.history;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FsHistoryProviderMetadata$ extends AbstractFunction3 implements Serializable {
   public static final FsHistoryProviderMetadata$ MODULE$ = new FsHistoryProviderMetadata$();

   public final String toString() {
      return "FsHistoryProviderMetadata";
   }

   public FsHistoryProviderMetadata apply(final long version, final long uiVersion, final String logDir) {
      return new FsHistoryProviderMetadata(version, uiVersion, logDir);
   }

   public Option unapply(final FsHistoryProviderMetadata x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.version()), BoxesRunTime.boxToLong(x$0.uiVersion()), x$0.logDir())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FsHistoryProviderMetadata$.class);
   }

   private FsHistoryProviderMetadata$() {
   }
}
