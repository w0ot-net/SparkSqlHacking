package org.apache.spark.mllib.linalg;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class QRDecomposition$ implements Serializable {
   public static final QRDecomposition$ MODULE$ = new QRDecomposition$();

   public final String toString() {
      return "QRDecomposition";
   }

   public QRDecomposition apply(final Object Q, final Object R) {
      return new QRDecomposition(Q, R);
   }

   public Option unapply(final QRDecomposition x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.Q(), x$0.R())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(QRDecomposition$.class);
   }

   private QRDecomposition$() {
   }
}
