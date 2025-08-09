package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class RDDBlockId$ extends AbstractFunction2 implements Serializable {
   public static final RDDBlockId$ MODULE$ = new RDDBlockId$();

   public final String toString() {
      return "RDDBlockId";
   }

   public RDDBlockId apply(final int rddId, final int splitIndex) {
      return new RDDBlockId(rddId, splitIndex);
   }

   public Option unapply(final RDDBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0.rddId(), x$0.splitIndex())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDDBlockId$.class);
   }

   private RDDBlockId$() {
   }
}
