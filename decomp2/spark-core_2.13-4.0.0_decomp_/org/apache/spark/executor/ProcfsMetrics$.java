package org.apache.spark.executor;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ProcfsMetrics$ extends AbstractFunction6 implements Serializable {
   public static final ProcfsMetrics$ MODULE$ = new ProcfsMetrics$();

   public final String toString() {
      return "ProcfsMetrics";
   }

   public ProcfsMetrics apply(final long jvmVmemTotal, final long jvmRSSTotal, final long pythonVmemTotal, final long pythonRSSTotal, final long otherVmemTotal, final long otherRSSTotal) {
      return new ProcfsMetrics(jvmVmemTotal, jvmRSSTotal, pythonVmemTotal, pythonRSSTotal, otherVmemTotal, otherRSSTotal);
   }

   public Option unapply(final ProcfsMetrics x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(BoxesRunTime.boxToLong(x$0.jvmVmemTotal()), BoxesRunTime.boxToLong(x$0.jvmRSSTotal()), BoxesRunTime.boxToLong(x$0.pythonVmemTotal()), BoxesRunTime.boxToLong(x$0.pythonRSSTotal()), BoxesRunTime.boxToLong(x$0.otherVmemTotal()), BoxesRunTime.boxToLong(x$0.otherRSSTotal()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProcfsMetrics$.class);
   }

   private ProcfsMetrics$() {
   }
}
