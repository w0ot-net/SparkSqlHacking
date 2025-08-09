package org.apache.spark.ml.linalg;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SparseVector$ implements Serializable {
   public static final SparseVector$ MODULE$ = new SparseVector$();

   public Option unapply(final SparseVector sv) {
      return new Some(new Tuple3(BoxesRunTime.boxToInteger(sv.size()), sv.indices(), sv.values()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparseVector$.class);
   }

   private SparseVector$() {
   }
}
