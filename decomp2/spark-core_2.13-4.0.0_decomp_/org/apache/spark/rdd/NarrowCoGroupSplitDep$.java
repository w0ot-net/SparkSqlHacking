package org.apache.spark.rdd;

import java.io.Serializable;
import org.apache.spark.Partition;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class NarrowCoGroupSplitDep$ extends AbstractFunction3 implements Serializable {
   public static final NarrowCoGroupSplitDep$ MODULE$ = new NarrowCoGroupSplitDep$();

   public final String toString() {
      return "NarrowCoGroupSplitDep";
   }

   public NarrowCoGroupSplitDep apply(final RDD rdd, final int splitIndex, final Partition split) {
      return new NarrowCoGroupSplitDep(rdd, splitIndex, split);
   }

   public Option unapply(final NarrowCoGroupSplitDep x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.rdd(), BoxesRunTime.boxToInteger(x$0.splitIndex()), x$0.split())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NarrowCoGroupSplitDep$.class);
   }

   private NarrowCoGroupSplitDep$() {
   }
}
