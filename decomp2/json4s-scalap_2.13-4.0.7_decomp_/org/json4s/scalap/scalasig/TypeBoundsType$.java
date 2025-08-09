package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class TypeBoundsType$ extends AbstractFunction2 implements Serializable {
   public static final TypeBoundsType$ MODULE$ = new TypeBoundsType$();

   public final String toString() {
      return "TypeBoundsType";
   }

   public TypeBoundsType apply(final Type lower, final Type upper) {
      return new TypeBoundsType(lower, upper);
   }

   public Option unapply(final TypeBoundsType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.lower(), x$0.upper())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TypeBoundsType$.class);
   }

   private TypeBoundsType$() {
   }
}
