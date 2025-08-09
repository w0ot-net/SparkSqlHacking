package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class AliasSymbol$ extends AbstractFunction1 implements Serializable {
   public static final AliasSymbol$ MODULE$ = new AliasSymbol$();

   public final String toString() {
      return "AliasSymbol";
   }

   public AliasSymbol apply(final SymbolInfo symbolInfo) {
      return new AliasSymbol(symbolInfo);
   }

   public Option unapply(final AliasSymbol x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.symbolInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AliasSymbol$.class);
   }

   private AliasSymbol$() {
   }
}
