package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AccumulatorMetadata$ extends AbstractFunction3 implements Serializable {
   public static final AccumulatorMetadata$ MODULE$ = new AccumulatorMetadata$();

   public final String toString() {
      return "AccumulatorMetadata";
   }

   public AccumulatorMetadata apply(final long id, final Option name, final boolean countFailedValues) {
      return new AccumulatorMetadata(id, name, countFailedValues);
   }

   public Option unapply(final AccumulatorMetadata x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.id()), x$0.name(), BoxesRunTime.boxToBoolean(x$0.countFailedValues()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AccumulatorMetadata$.class);
   }

   private AccumulatorMetadata$() {
   }
}
