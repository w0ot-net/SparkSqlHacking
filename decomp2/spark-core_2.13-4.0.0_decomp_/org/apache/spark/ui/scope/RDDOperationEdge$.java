package org.apache.spark.ui.scope;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class RDDOperationEdge$ extends AbstractFunction2 implements Serializable {
   public static final RDDOperationEdge$ MODULE$ = new RDDOperationEdge$();

   public final String toString() {
      return "RDDOperationEdge";
   }

   public RDDOperationEdge apply(final int fromId, final int toId) {
      return new RDDOperationEdge(fromId, toId);
   }

   public Option unapply(final RDDOperationEdge x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0.fromId(), x$0.toId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDDOperationEdge$.class);
   }

   private RDDOperationEdge$() {
   }
}
