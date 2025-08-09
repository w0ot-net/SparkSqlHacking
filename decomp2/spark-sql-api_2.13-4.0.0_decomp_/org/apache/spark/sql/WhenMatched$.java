package org.apache.spark.sql;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class WhenMatched$ implements Serializable {
   public static final WhenMatched$ MODULE$ = new WhenMatched$();

   public final String toString() {
      return "WhenMatched";
   }

   public WhenMatched apply(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return new WhenMatched(mergeIntoWriter, condition);
   }

   public Option unapply(final WhenMatched x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.mergeIntoWriter(), x$0.condition())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WhenMatched$.class);
   }

   private WhenMatched$() {
   }
}
