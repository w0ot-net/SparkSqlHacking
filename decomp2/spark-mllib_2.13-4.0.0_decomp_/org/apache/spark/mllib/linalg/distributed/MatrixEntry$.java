package org.apache.spark.mllib.linalg.distributed;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class MatrixEntry$ extends AbstractFunction3 implements Serializable {
   public static final MatrixEntry$ MODULE$ = new MatrixEntry$();

   public final String toString() {
      return "MatrixEntry";
   }

   public MatrixEntry apply(final long i, final long j, final double value) {
      return new MatrixEntry(i, j, value);
   }

   public Option unapply(final MatrixEntry x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.i()), BoxesRunTime.boxToLong(x$0.j()), BoxesRunTime.boxToDouble(x$0.value()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MatrixEntry$.class);
   }

   private MatrixEntry$() {
   }
}
