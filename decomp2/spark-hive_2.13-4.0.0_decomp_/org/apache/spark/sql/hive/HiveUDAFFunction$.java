package org.apache.spark.sql.hive;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class HiveUDAFFunction$ extends AbstractFunction6 implements Serializable {
   public static final HiveUDAFFunction$ MODULE$ = new HiveUDAFFunction$();

   public boolean $lessinit$greater$default$4() {
      return false;
   }

   public int $lessinit$greater$default$5() {
      return 0;
   }

   public int $lessinit$greater$default$6() {
      return 0;
   }

   public final String toString() {
      return "HiveUDAFFunction";
   }

   public HiveUDAFFunction apply(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children, final boolean isUDAFBridgeRequired, final int mutableAggBufferOffset, final int inputAggBufferOffset) {
      return new HiveUDAFFunction(name, funcWrapper, children, isUDAFBridgeRequired, mutableAggBufferOffset, inputAggBufferOffset);
   }

   public boolean apply$default$4() {
      return false;
   }

   public int apply$default$5() {
      return 0;
   }

   public int apply$default$6() {
      return 0;
   }

   public Option unapply(final HiveUDAFFunction x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.name(), x$0.funcWrapper(), x$0.children(), BoxesRunTime.boxToBoolean(x$0.isUDAFBridgeRequired()), BoxesRunTime.boxToInteger(x$0.mutableAggBufferOffset()), BoxesRunTime.boxToInteger(x$0.inputAggBufferOffset()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HiveUDAFFunction$.class);
   }

   private HiveUDAFFunction$() {
   }
}
