package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class MappedTypeHints$ extends AbstractFunction2 implements Serializable {
   public static final MappedTypeHints$ MODULE$ = new MappedTypeHints$();

   public String $lessinit$greater$default$2() {
      return "jsonClass";
   }

   public final String toString() {
      return "MappedTypeHints";
   }

   public MappedTypeHints apply(final Map hintMap, final String typeHintFieldName) {
      return new MappedTypeHints(hintMap, typeHintFieldName);
   }

   public String apply$default$2() {
      return "jsonClass";
   }

   public Option unapply(final MappedTypeHints x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.hintMap(), x$0.typeHintFieldName())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MappedTypeHints$.class);
   }

   private MappedTypeHints$() {
   }
}
