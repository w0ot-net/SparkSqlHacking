package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ClearMetadata$ extends AbstractFunction1 implements Serializable {
   public static final ClearMetadata$ MODULE$ = new ClearMetadata$();

   public final String toString() {
      return "ClearMetadata";
   }

   public ClearMetadata apply(final Time time) {
      return new ClearMetadata(time);
   }

   public Option unapply(final ClearMetadata x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.time()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClearMetadata$.class);
   }

   private ClearMetadata$() {
   }
}
