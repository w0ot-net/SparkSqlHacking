package org.json4s.reflect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class PrimitiveDescriptor$ extends AbstractFunction2 implements Serializable {
   public static final PrimitiveDescriptor$ MODULE$ = new PrimitiveDescriptor$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public final String toString() {
      return "PrimitiveDescriptor";
   }

   public PrimitiveDescriptor apply(final ScalaType erasure, final Option default) {
      return new PrimitiveDescriptor(erasure, default);
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Option unapply(final PrimitiveDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.erasure(), x$0.default())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PrimitiveDescriptor$.class);
   }

   private PrimitiveDescriptor$() {
   }
}
