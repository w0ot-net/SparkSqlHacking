package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class Alias$ extends AbstractFunction4 implements Serializable {
   public static final Alias$ MODULE$ = new Alias$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Origin $lessinit$greater$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "Alias";
   }

   public Alias apply(final ColumnNode child, final Seq name, final Option metadata, final Origin origin) {
      return new Alias(child, name, metadata, origin);
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public Origin apply$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final Alias x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.child(), x$0.name(), x$0.metadata(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Alias$.class);
   }

   private Alias$() {
   }
}
