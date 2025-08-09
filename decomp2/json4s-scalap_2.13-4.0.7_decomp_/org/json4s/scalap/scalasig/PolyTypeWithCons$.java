package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class PolyTypeWithCons$ extends AbstractFunction3 implements Serializable {
   public static final PolyTypeWithCons$ MODULE$ = new PolyTypeWithCons$();

   public final String toString() {
      return "PolyTypeWithCons";
   }

   public PolyTypeWithCons apply(final Type typeRef, final Seq symbols, final String cons) {
      return new PolyTypeWithCons(typeRef, symbols, cons);
   }

   public Option unapply(final PolyTypeWithCons x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.typeRef(), x$0.symbols(), x$0.cons())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PolyTypeWithCons$.class);
   }

   private PolyTypeWithCons$() {
   }
}
