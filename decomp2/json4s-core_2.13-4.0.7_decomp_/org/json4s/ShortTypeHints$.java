package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ShortTypeHints$ extends AbstractFunction2 implements Serializable {
   public static final ShortTypeHints$ MODULE$ = new ShortTypeHints$();

   public String $lessinit$greater$default$2() {
      return "jsonClass";
   }

   public final String toString() {
      return "ShortTypeHints";
   }

   public ShortTypeHints apply(final List hints, final String typeHintFieldName) {
      return new ShortTypeHints(hints, typeHintFieldName);
   }

   public String apply$default$2() {
      return "jsonClass";
   }

   public Option unapply(final ShortTypeHints x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.hints(), x$0.typeHintFieldName())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShortTypeHints$.class);
   }

   private ShortTypeHints$() {
   }
}
