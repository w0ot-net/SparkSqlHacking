package org.apache.spark.util.random;

import java.io.Serializable;
import java.util.Random;
import scala.runtime.ModuleSerializationProxy;

public final class GapSamplingReplacement$ implements Serializable {
   public static final GapSamplingReplacement$ MODULE$ = new GapSamplingReplacement$();

   public Random $lessinit$greater$default$2() {
      return RandomSampler$.MODULE$.newDefaultRNG();
   }

   public double $lessinit$greater$default$3() {
      return RandomSampler$.MODULE$.rngEpsilon();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GapSamplingReplacement$.class);
   }

   private GapSamplingReplacement$() {
   }
}
