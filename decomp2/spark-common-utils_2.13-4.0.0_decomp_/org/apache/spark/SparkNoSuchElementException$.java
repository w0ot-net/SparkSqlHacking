package org.apache.spark;

import java.io.Serializable;
import scala.Array.;
import scala.runtime.ModuleSerializationProxy;

public final class SparkNoSuchElementException$ implements Serializable {
   public static final SparkNoSuchElementException$ MODULE$ = new SparkNoSuchElementException$();

   public QueryContext[] $lessinit$greater$default$3() {
      return (QueryContext[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class));
   }

   public String $lessinit$greater$default$4() {
      return "";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkNoSuchElementException$.class);
   }

   private SparkNoSuchElementException$() {
   }
}
