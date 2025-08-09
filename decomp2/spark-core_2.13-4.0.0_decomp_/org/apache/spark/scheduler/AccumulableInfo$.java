package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AccumulableInfo$ extends AbstractFunction7 implements Serializable {
   public static final AccumulableInfo$ MODULE$ = new AccumulableInfo$();

   public Option $lessinit$greater$default$7() {
      return .MODULE$;
   }

   public final String toString() {
      return "AccumulableInfo";
   }

   public AccumulableInfo apply(final long id, final Option name, final Option update, final Option value, final boolean internal, final boolean countFailedValues, final Option metadata) {
      return new AccumulableInfo(id, name, update, value, internal, countFailedValues, metadata);
   }

   public Option apply$default$7() {
      return .MODULE$;
   }

   public Option unapply(final AccumulableInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(BoxesRunTime.boxToLong(x$0.id()), x$0.name(), x$0.update(), x$0.value(), BoxesRunTime.boxToBoolean(x$0.internal()), BoxesRunTime.boxToBoolean(x$0.countFailedValues()), x$0.metadata())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AccumulableInfo$.class);
   }

   private AccumulableInfo$() {
   }
}
