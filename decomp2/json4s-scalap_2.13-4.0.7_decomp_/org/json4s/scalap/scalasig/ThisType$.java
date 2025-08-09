package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ThisType$ extends AbstractFunction1 implements Serializable {
   public static final ThisType$ MODULE$ = new ThisType$();

   public final String toString() {
      return "ThisType";
   }

   public ThisType apply(final Symbol symbol) {
      return new ThisType(symbol);
   }

   public Option unapply(final ThisType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.symbol()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ThisType$.class);
   }

   private ThisType$() {
   }
}
