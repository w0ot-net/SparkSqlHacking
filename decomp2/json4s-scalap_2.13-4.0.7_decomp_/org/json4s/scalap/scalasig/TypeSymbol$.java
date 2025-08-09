package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class TypeSymbol$ extends AbstractFunction1 implements Serializable {
   public static final TypeSymbol$ MODULE$ = new TypeSymbol$();

   public final String toString() {
      return "TypeSymbol";
   }

   public TypeSymbol apply(final SymbolInfo symbolInfo) {
      return new TypeSymbol(symbolInfo);
   }

   public Option unapply(final TypeSymbol x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.symbolInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TypeSymbol$.class);
   }

   private TypeSymbol$() {
   }
}
