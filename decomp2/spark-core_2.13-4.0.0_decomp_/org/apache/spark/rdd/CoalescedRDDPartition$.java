package org.apache.spark.rdd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CoalescedRDDPartition$ extends AbstractFunction4 implements Serializable {
   public static final CoalescedRDDPartition$ MODULE$ = new CoalescedRDDPartition$();

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public final String toString() {
      return "CoalescedRDDPartition";
   }

   public CoalescedRDDPartition apply(final int index, final RDD rdd, final int[] parentsIndices, final Option preferredLocation) {
      return new CoalescedRDDPartition(index, rdd, parentsIndices, preferredLocation);
   }

   public Option apply$default$4() {
      return .MODULE$;
   }

   public Option unapply(final CoalescedRDDPartition x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.index()), x$0.rdd(), x$0.parentsIndices(), x$0.preferredLocation())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CoalescedRDDPartition$.class);
   }

   private CoalescedRDDPartition$() {
   }
}
