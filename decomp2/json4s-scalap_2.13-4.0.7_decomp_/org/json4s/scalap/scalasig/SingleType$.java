package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class SingleType$ extends AbstractFunction2 implements Serializable {
   public static final SingleType$ MODULE$ = new SingleType$();

   public final String toString() {
      return "SingleType";
   }

   public SingleType apply(final Type typeRef, final Symbol symbol) {
      return new SingleType(typeRef, symbol);
   }

   public Option unapply(final SingleType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.typeRef(), x$0.symbol())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SingleType$.class);
   }

   private SingleType$() {
   }
}
