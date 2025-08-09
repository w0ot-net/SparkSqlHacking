package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class PolyType$ extends AbstractFunction2 implements Serializable {
   public static final PolyType$ MODULE$ = new PolyType$();

   public final String toString() {
      return "PolyType";
   }

   public PolyType apply(final Type typeRef, final Seq symbols) {
      return new PolyType(typeRef, symbols);
   }

   public Option unapply(final PolyType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.typeRef(), x$0.symbols())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PolyType$.class);
   }

   private PolyType$() {
   }
}
