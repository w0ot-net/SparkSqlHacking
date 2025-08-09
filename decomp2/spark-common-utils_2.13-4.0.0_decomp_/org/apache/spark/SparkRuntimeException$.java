package org.apache.spark;

import java.io.Serializable;
import scala.Array.;
import scala.runtime.ModuleSerializationProxy;

public final class SparkRuntimeException$ implements Serializable {
   public static final SparkRuntimeException$ MODULE$ = new SparkRuntimeException$();

   public Throwable $lessinit$greater$default$3() {
      return null;
   }

   public QueryContext[] $lessinit$greater$default$4() {
      return (QueryContext[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class));
   }

   public String $lessinit$greater$default$5() {
      return "";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkRuntimeException$.class);
   }

   private SparkRuntimeException$() {
   }
}
