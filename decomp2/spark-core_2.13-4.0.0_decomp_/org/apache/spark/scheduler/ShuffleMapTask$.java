package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class ShuffleMapTask$ implements Serializable {
   public static final ShuffleMapTask$ MODULE$ = new ShuffleMapTask$();

   public Option $lessinit$greater$default$10() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$11() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$12() {
      return .MODULE$;
   }

   public boolean $lessinit$greater$default$13() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShuffleMapTask$.class);
   }

   private ShuffleMapTask$() {
   }
}
