package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CleanCheckpoint$ extends AbstractFunction1 implements Serializable {
   public static final CleanCheckpoint$ MODULE$ = new CleanCheckpoint$();

   public final String toString() {
      return "CleanCheckpoint";
   }

   public CleanCheckpoint apply(final int rddId) {
      return new CleanCheckpoint(rddId);
   }

   public Option unapply(final CleanCheckpoint x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.rddId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CleanCheckpoint$.class);
   }

   private CleanCheckpoint$() {
   }
}
