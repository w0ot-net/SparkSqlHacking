package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class NullaryMethodType$ extends AbstractFunction1 implements Serializable {
   public static final NullaryMethodType$ MODULE$ = new NullaryMethodType$();

   public final String toString() {
      return "NullaryMethodType";
   }

   public NullaryMethodType apply(final Type resultType) {
      return new NullaryMethodType(resultType);
   }

   public Option unapply(final NullaryMethodType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.resultType()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NullaryMethodType$.class);
   }

   private NullaryMethodType$() {
   }
}
