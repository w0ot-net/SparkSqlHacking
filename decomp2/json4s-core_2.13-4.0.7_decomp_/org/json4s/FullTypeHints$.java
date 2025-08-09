package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class FullTypeHints$ extends AbstractFunction2 implements Serializable {
   public static final FullTypeHints$ MODULE$ = new FullTypeHints$();

   public String $lessinit$greater$default$2() {
      return "jsonClass";
   }

   public final String toString() {
      return "FullTypeHints";
   }

   public FullTypeHints apply(final List hints, final String typeHintFieldName) {
      return new FullTypeHints(hints, typeHintFieldName);
   }

   public String apply$default$2() {
      return "jsonClass";
   }

   public Option unapply(final FullTypeHints x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.hints(), x$0.typeHintFieldName())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FullTypeHints$.class);
   }

   private FullTypeHints$() {
   }
}
