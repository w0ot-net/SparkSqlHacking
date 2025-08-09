package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparkUserAppException$ extends AbstractFunction1 implements Serializable {
   public static final SparkUserAppException$ MODULE$ = new SparkUserAppException$();

   public final String toString() {
      return "SparkUserAppException";
   }

   public SparkUserAppException apply(final int exitCode) {
      return new SparkUserAppException(exitCode);
   }

   public Option unapply(final SparkUserAppException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.exitCode())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkUserAppException$.class);
   }

   private SparkUserAppException$() {
   }
}
