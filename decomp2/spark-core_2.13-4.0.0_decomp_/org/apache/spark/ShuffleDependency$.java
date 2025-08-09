package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.shuffle.ShuffleWriteProcessor;
import scala.None;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleDependency$ implements Serializable {
   public static final ShuffleDependency$ MODULE$ = new ShuffleDependency$();

   public Serializer $lessinit$greater$default$3() {
      return SparkEnv$.MODULE$.get().serializer();
   }

   public None $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public None $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public boolean $lessinit$greater$default$6() {
      return false;
   }

   public ShuffleWriteProcessor $lessinit$greater$default$7() {
      return new ShuffleWriteProcessor();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleDependency$.class);
   }

   private ShuffleDependency$() {
   }
}
