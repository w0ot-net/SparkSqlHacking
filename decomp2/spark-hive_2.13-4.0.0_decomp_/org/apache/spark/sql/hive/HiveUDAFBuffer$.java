package org.apache.spark.sql.hive;

import java.io.Serializable;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class HiveUDAFBuffer$ extends AbstractFunction2 implements Serializable {
   public static final HiveUDAFBuffer$ MODULE$ = new HiveUDAFBuffer$();

   public final String toString() {
      return "HiveUDAFBuffer";
   }

   public HiveUDAFBuffer apply(final GenericUDAFEvaluator.AggregationBuffer buf, final boolean canDoMerge) {
      return new HiveUDAFBuffer(buf, canDoMerge);
   }

   public Option unapply(final HiveUDAFBuffer x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.buf(), BoxesRunTime.boxToBoolean(x$0.canDoMerge()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HiveUDAFBuffer$.class);
   }

   private HiveUDAFBuffer$() {
   }
}
