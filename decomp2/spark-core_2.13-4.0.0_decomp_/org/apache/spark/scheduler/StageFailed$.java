package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StageFailed$ extends AbstractFunction3 implements Serializable {
   public static final StageFailed$ MODULE$ = new StageFailed$();

   public final String toString() {
      return "StageFailed";
   }

   public StageFailed apply(final int stageId, final String reason, final Option exception) {
      return new StageFailed(stageId, reason, exception);
   }

   public Option unapply(final StageFailed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.stageId()), x$0.reason(), x$0.exception())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StageFailed$.class);
   }

   private StageFailed$() {
   }
}
