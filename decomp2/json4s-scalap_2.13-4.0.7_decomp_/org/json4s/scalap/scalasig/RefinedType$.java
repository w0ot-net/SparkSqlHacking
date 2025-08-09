package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class RefinedType$ extends AbstractFunction2 implements Serializable {
   public static final RefinedType$ MODULE$ = new RefinedType$();

   public final String toString() {
      return "RefinedType";
   }

   public RefinedType apply(final Symbol classSym, final List typeRefs) {
      return new RefinedType(classSym, typeRefs);
   }

   public Option unapply(final RefinedType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.classSym(), x$0.typeRefs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RefinedType$.class);
   }

   private RefinedType$() {
   }
}
