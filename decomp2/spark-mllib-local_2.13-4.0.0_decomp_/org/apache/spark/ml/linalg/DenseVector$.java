package org.apache.spark.ml.linalg;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;

public final class DenseVector$ implements Serializable {
   public static final DenseVector$ MODULE$ = new DenseVector$();

   public Option unapply(final DenseVector dv) {
      return new Some(dv.values());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DenseVector$.class);
   }

   private DenseVector$() {
   }
}
