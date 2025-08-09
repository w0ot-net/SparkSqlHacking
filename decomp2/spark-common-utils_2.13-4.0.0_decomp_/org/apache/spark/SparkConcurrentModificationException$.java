package org.apache.spark;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class SparkConcurrentModificationException$ implements Serializable {
   public static final SparkConcurrentModificationException$ MODULE$ = new SparkConcurrentModificationException$();

   public Throwable $lessinit$greater$default$3() {
      return null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkConcurrentModificationException$.class);
   }

   private SparkConcurrentModificationException$() {
   }
}
