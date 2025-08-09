package org.apache.spark;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class SparkClassNotFoundException$ implements Serializable {
   public static final SparkClassNotFoundException$ MODULE$ = new SparkClassNotFoundException$();

   public Throwable $lessinit$greater$default$3() {
      return null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkClassNotFoundException$.class);
   }

   private SparkClassNotFoundException$() {
   }
}
