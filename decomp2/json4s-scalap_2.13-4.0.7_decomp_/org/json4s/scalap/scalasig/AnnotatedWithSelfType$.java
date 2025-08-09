package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class AnnotatedWithSelfType$ extends AbstractFunction3 implements Serializable {
   public static final AnnotatedWithSelfType$ MODULE$ = new AnnotatedWithSelfType$();

   public final String toString() {
      return "AnnotatedWithSelfType";
   }

   public AnnotatedWithSelfType apply(final Type typeRef, final Symbol symbol, final List attribTreeRefs) {
      return new AnnotatedWithSelfType(typeRef, symbol, attribTreeRefs);
   }

   public Option unapply(final AnnotatedWithSelfType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.typeRef(), x$0.symbol(), x$0.attribTreeRefs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnnotatedWithSelfType$.class);
   }

   private AnnotatedWithSelfType$() {
   }
}
