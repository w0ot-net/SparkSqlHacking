package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ExcludedExecutor$ extends AbstractFunction2 implements Serializable {
   public static final ExcludedExecutor$ MODULE$ = new ExcludedExecutor$();

   public final String toString() {
      return "ExcludedExecutor";
   }

   public ExcludedExecutor apply(final String node, final long expiryTime) {
      return new ExcludedExecutor(node, expiryTime);
   }

   public Option unapply(final ExcludedExecutor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.node(), BoxesRunTime.boxToLong(x$0.expiryTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExcludedExecutor$.class);
   }

   private ExcludedExecutor$() {
   }
}
