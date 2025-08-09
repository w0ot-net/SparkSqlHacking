package org.apache.spark.mllib.linalg;

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

   public SparseVector fromML(final org.apache.spark.ml.linalg.SparseVector v) {
      return new SparseVector(v.size(), v.indices(), v.values());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparseVector$.class);
   }

   private SparseVector$() {
   }
}
