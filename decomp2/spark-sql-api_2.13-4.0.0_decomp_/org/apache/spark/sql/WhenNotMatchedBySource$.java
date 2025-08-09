package org.apache.spark.sql;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class WhenNotMatchedBySource$ implements Serializable {
   public static final WhenNotMatchedBySource$ MODULE$ = new WhenNotMatchedBySource$();

   public final String toString() {
      return "WhenNotMatchedBySource";
   }

   public WhenNotMatchedBySource apply(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return new WhenNotMatchedBySource(mergeIntoWriter, condition);
   }

   public Option unapply(final WhenNotMatchedBySource x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.mergeIntoWriter(), x$0.condition())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WhenNotMatchedBySource$.class);
   }

   private WhenNotMatchedBySource$() {
   }
}
