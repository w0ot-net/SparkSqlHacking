package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ExistentialType$ extends AbstractFunction2 implements Serializable {
   public static final ExistentialType$ MODULE$ = new ExistentialType$();

   public final String toString() {
      return "ExistentialType";
   }

   public ExistentialType apply(final Type typeRef, final Seq symbols) {
      return new ExistentialType(typeRef, symbols);
   }

   public Option unapply(final ExistentialType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.typeRef(), x$0.symbols())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExistentialType$.class);
   }

   private ExistentialType$() {
   }
}
