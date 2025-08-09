package org.apache.spark.mllib.rdd;

import java.io.Serializable;
import org.apache.spark.util.Utils.;
import scala.runtime.ModuleSerializationProxy;

public final class RandomVectorRDD$ implements Serializable {
   public static final RandomVectorRDD$ MODULE$ = new RandomVectorRDD$();

   public long $lessinit$greater$default$6() {
      return .MODULE$.random().nextLong();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomVectorRDD$.class);
   }

   private RandomVectorRDD$() {
   }
}
