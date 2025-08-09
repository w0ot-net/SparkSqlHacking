package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ReceiverErrorInfo$ extends AbstractFunction3 implements Serializable {
   public static final ReceiverErrorInfo$ MODULE$ = new ReceiverErrorInfo$();

   public String $lessinit$greater$default$1() {
      return "";
   }

   public String $lessinit$greater$default$2() {
      return "";
   }

   public long $lessinit$greater$default$3() {
      return -1L;
   }

   public final String toString() {
      return "ReceiverErrorInfo";
   }

   public ReceiverErrorInfo apply(final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return new ReceiverErrorInfo(lastErrorMessage, lastError, lastErrorTime);
   }

   public String apply$default$1() {
      return "";
   }

   public String apply$default$2() {
      return "";
   }

   public long apply$default$3() {
      return -1L;
   }

   public Option unapply(final ReceiverErrorInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.lastErrorMessage(), x$0.lastError(), BoxesRunTime.boxToLong(x$0.lastErrorTime()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReceiverErrorInfo$.class);
   }

   private ReceiverErrorInfo$() {
   }
}
