package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class ResultTask$ implements Serializable {
   public static final ResultTask$ MODULE$ = new ResultTask$();

   public Option $lessinit$greater$default$11() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$12() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$13() {
      return .MODULE$;
   }

   public boolean $lessinit$greater$default$14() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResultTask$.class);
   }

   private ResultTask$() {
   }
}
