package org.apache.spark.deploy.history;

import java.io.Serializable;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class CompactionResult$ extends AbstractFunction2 implements Serializable {
   public static final CompactionResult$ MODULE$ = new CompactionResult$();

   public final String toString() {
      return "CompactionResult";
   }

   public CompactionResult apply(final Enumeration.Value code, final Option compactIndex) {
      return new CompactionResult(code, compactIndex);
   }

   public Option unapply(final CompactionResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.code(), x$0.compactIndex())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CompactionResult$.class);
   }

   private CompactionResult$() {
   }
}
