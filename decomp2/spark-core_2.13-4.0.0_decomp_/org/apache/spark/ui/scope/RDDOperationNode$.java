package org.apache.spark.ui.scope;

import java.io.Serializable;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RDDOperationNode$ extends AbstractFunction6 implements Serializable {
   public static final RDDOperationNode$ MODULE$ = new RDDOperationNode$();

   public final String toString() {
      return "RDDOperationNode";
   }

   public RDDOperationNode apply(final int id, final String name, final boolean cached, final boolean barrier, final String callsite, final Enumeration.Value outputDeterministicLevel) {
      return new RDDOperationNode(id, name, cached, barrier, callsite, outputDeterministicLevel);
   }

   public Option unapply(final RDDOperationNode x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(BoxesRunTime.boxToInteger(x$0.id()), x$0.name(), BoxesRunTime.boxToBoolean(x$0.cached()), BoxesRunTime.boxToBoolean(x$0.barrier()), x$0.callsite(), x$0.outputDeterministicLevel())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDDOperationNode$.class);
   }

   private RDDOperationNode$() {
   }
}
