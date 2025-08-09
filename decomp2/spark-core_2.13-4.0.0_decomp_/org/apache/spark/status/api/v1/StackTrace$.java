package org.apache.spark.status.api.v1;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StackTrace$ extends AbstractFunction1 implements Serializable {
   public static final StackTrace$ MODULE$ = new StackTrace$();

   public final String toString() {
      return "StackTrace";
   }

   public StackTrace apply(final Seq elems) {
      return new StackTrace(elems);
   }

   public Option unapply(final StackTrace x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.elems()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StackTrace$.class);
   }

   private StackTrace$() {
   }
}
