package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class AnnotatedType$ extends AbstractFunction2 implements Serializable {
   public static final AnnotatedType$ MODULE$ = new AnnotatedType$();

   public final String toString() {
      return "AnnotatedType";
   }

   public AnnotatedType apply(final Type typeRef, final List attribTreeRefs) {
      return new AnnotatedType(typeRef, attribTreeRefs);
   }

   public Option unapply(final AnnotatedType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.typeRef(), x$0.attribTreeRefs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnnotatedType$.class);
   }

   private AnnotatedType$() {
   }
}
