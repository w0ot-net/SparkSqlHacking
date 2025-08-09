package org.apache.spark;

import java.io.Serializable;
import scala.Array.;
import scala.runtime.ModuleSerializationProxy;

public final class SparkIllegalStateException$ implements Serializable {
   public static final SparkIllegalStateException$ MODULE$ = new SparkIllegalStateException$();

   public QueryContext[] $lessinit$greater$default$3() {
      return (QueryContext[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class));
   }

   public Throwable $lessinit$greater$default$4() {
      return null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkIllegalStateException$.class);
   }

   private SparkIllegalStateException$() {
   }
}
