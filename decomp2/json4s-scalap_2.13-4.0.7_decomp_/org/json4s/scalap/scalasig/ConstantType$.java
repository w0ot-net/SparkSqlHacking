package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ConstantType$ extends AbstractFunction1 implements Serializable {
   public static final ConstantType$ MODULE$ = new ConstantType$();

   public final String toString() {
      return "ConstantType";
   }

   public ConstantType apply(final Object constant) {
      return new ConstantType(constant);
   }

   public Option unapply(final ConstantType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.constant()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConstantType$.class);
   }

   private ConstantType$() {
   }
}
