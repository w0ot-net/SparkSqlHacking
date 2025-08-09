package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class MethodType$ extends AbstractFunction2 implements Serializable {
   public static final MethodType$ MODULE$ = new MethodType$();

   public final String toString() {
      return "MethodType";
   }

   public MethodType apply(final Type resultType, final Seq paramSymbols) {
      return new MethodType(resultType, paramSymbols);
   }

   public Option unapply(final MethodType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.resultType(), x$0.paramSymbols())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MethodType$.class);
   }

   private MethodType$() {
   }
}
