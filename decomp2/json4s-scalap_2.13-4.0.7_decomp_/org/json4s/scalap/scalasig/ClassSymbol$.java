package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ClassSymbol$ extends AbstractFunction2 implements Serializable {
   public static final ClassSymbol$ MODULE$ = new ClassSymbol$();

   public final String toString() {
      return "ClassSymbol";
   }

   public ClassSymbol apply(final SymbolInfo symbolInfo, final Option thisTypeRef) {
      return new ClassSymbol(symbolInfo, thisTypeRef);
   }

   public Option unapply(final ClassSymbol x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.symbolInfo(), x$0.thisTypeRef())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassSymbol$.class);
   }

   private ClassSymbol$() {
   }
}
