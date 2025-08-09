package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JavaOutputOperationInfo$ extends AbstractFunction7 implements Serializable {
   public static final JavaOutputOperationInfo$ MODULE$ = new JavaOutputOperationInfo$();

   public final String toString() {
      return "JavaOutputOperationInfo";
   }

   public JavaOutputOperationInfo apply(final Time batchTime, final int id, final String name, final String description, final long startTime, final long endTime, final String failureReason) {
      return new JavaOutputOperationInfo(batchTime, id, name, description, startTime, endTime, failureReason);
   }

   public Option unapply(final JavaOutputOperationInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(x$0.batchTime(), BoxesRunTime.boxToInteger(x$0.id()), x$0.name(), x$0.description(), BoxesRunTime.boxToLong(x$0.startTime()), BoxesRunTime.boxToLong(x$0.endTime()), x$0.failureReason())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaOutputOperationInfo$.class);
   }

   private JavaOutputOperationInfo$() {
   }
}
