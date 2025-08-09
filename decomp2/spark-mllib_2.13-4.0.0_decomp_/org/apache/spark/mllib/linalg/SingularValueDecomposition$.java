package org.apache.spark.mllib.linalg;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class SingularValueDecomposition$ implements Serializable {
   public static final SingularValueDecomposition$ MODULE$ = new SingularValueDecomposition$();

   public final String toString() {
      return "SingularValueDecomposition";
   }

   public SingularValueDecomposition apply(final Object U, final Vector s, final Object V) {
      return new SingularValueDecomposition(U, s, V);
   }

   public Option unapply(final SingularValueDecomposition x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.U(), x$0.s(), x$0.V())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SingularValueDecomposition$.class);
   }

   private SingularValueDecomposition$() {
   }
}
