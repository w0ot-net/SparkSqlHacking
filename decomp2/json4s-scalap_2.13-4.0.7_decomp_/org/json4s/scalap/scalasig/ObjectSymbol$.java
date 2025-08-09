package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ObjectSymbol$ extends AbstractFunction1 implements Serializable {
   public static final ObjectSymbol$ MODULE$ = new ObjectSymbol$();

   public final String toString() {
      return "ObjectSymbol";
   }

   public ObjectSymbol apply(final SymbolInfo symbolInfo) {
      return new ObjectSymbol(symbolInfo);
   }

   public Option unapply(final ObjectSymbol x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.symbolInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ObjectSymbol$.class);
   }

   private ObjectSymbol$() {
   }
}
