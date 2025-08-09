package org.apache.spark.sql.internal;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class WindowSpec$ extends AbstractFunction3 implements Serializable {
   public static final WindowSpec$ MODULE$ = new WindowSpec$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public final String toString() {
      return "WindowSpec";
   }

   public WindowSpec apply(final Seq partitionColumns, final Seq sortColumns, final Option frame) {
      return new WindowSpec(partitionColumns, sortColumns, frame);
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public Option unapply(final WindowSpec x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.partitionColumns(), x$0.sortColumns(), x$0.frame())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WindowSpec$.class);
   }

   private WindowSpec$() {
   }
}
