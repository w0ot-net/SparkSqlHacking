package org.apache.spark.rdd;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class CoalescedRDD$ implements Serializable {
   public static final CoalescedRDD$ MODULE$ = new CoalescedRDD$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CoalescedRDD$.class);
   }

   private CoalescedRDD$() {
   }
}
