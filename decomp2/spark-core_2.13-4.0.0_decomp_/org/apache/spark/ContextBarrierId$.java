package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ContextBarrierId$ extends AbstractFunction2 implements Serializable {
   public static final ContextBarrierId$ MODULE$ = new ContextBarrierId$();

   public final String toString() {
      return "ContextBarrierId";
   }

   public ContextBarrierId apply(final int stageId, final int stageAttemptId) {
      return new ContextBarrierId(stageId, stageAttemptId);
   }

   public Option unapply(final ContextBarrierId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0.stageId(), x$0.stageAttemptId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ContextBarrierId$.class);
   }

   private ContextBarrierId$() {
   }
}
