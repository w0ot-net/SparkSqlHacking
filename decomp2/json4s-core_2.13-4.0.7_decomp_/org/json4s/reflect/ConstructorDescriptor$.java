package org.json4s.reflect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ConstructorDescriptor$ extends AbstractFunction3 implements Serializable {
   public static final ConstructorDescriptor$ MODULE$ = new ConstructorDescriptor$();

   public final String toString() {
      return "ConstructorDescriptor";
   }

   public ConstructorDescriptor apply(final Seq params, final Executable constructor, final boolean isPrimary) {
      return new ConstructorDescriptor(params, constructor, isPrimary);
   }

   public Option unapply(final ConstructorDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.params(), x$0.constructor(), BoxesRunTime.boxToBoolean(x$0.isPrimary()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConstructorDescriptor$.class);
   }

   private ConstructorDescriptor$() {
   }
}
