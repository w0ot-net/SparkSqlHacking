package org.apache.spark.sql;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class WhenNotMatched$ implements Serializable {
   public static final WhenNotMatched$ MODULE$ = new WhenNotMatched$();

   public final String toString() {
      return "WhenNotMatched";
   }

   public WhenNotMatched apply(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return new WhenNotMatched(mergeIntoWriter, condition);
   }

   public Option unapply(final WhenNotMatched x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.mergeIntoWriter(), x$0.condition())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WhenNotMatched$.class);
   }

   private WhenNotMatched$() {
   }
}
