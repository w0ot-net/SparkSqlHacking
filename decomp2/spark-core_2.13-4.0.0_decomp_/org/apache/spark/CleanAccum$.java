package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CleanAccum$ extends AbstractFunction1 implements Serializable {
   public static final CleanAccum$ MODULE$ = new CleanAccum$();

   public final String toString() {
      return "CleanAccum";
   }

   public CleanAccum apply(final long accId) {
      return new CleanAccum(accId);
   }

   public Option unapply(final CleanAccum x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.accId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CleanAccum$.class);
   }

   private CleanAccum$() {
   }
}
