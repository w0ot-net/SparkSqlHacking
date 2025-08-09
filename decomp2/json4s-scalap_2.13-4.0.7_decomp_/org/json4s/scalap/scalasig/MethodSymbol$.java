package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class MethodSymbol$ extends AbstractFunction2 implements Serializable {
   public static final MethodSymbol$ MODULE$ = new MethodSymbol$();

   public final String toString() {
      return "MethodSymbol";
   }

   public MethodSymbol apply(final SymbolInfo symbolInfo, final Option aliasRef) {
      return new MethodSymbol(symbolInfo, aliasRef);
   }

   public Option unapply(final MethodSymbol x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.symbolInfo(), x$0.aliasRef())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MethodSymbol$.class);
   }

   private MethodSymbol$() {
   }
}
