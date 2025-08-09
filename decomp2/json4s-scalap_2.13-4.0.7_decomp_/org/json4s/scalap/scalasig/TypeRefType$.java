package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class TypeRefType$ extends AbstractFunction3 implements Serializable {
   public static final TypeRefType$ MODULE$ = new TypeRefType$();

   public final String toString() {
      return "TypeRefType";
   }

   public TypeRefType apply(final Type prefix, final Symbol symbol, final Seq typeArgs) {
      return new TypeRefType(prefix, symbol, typeArgs);
   }

   public Option unapply(final TypeRefType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.prefix(), x$0.symbol(), x$0.typeArgs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TypeRefType$.class);
   }

   private TypeRefType$() {
   }
}
