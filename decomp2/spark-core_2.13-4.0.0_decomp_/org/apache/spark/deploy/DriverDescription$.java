package org.apache.spark.deploy;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class DriverDescription$ extends AbstractFunction6 implements Serializable {
   public static final DriverDescription$ MODULE$ = new DriverDescription$();

   public Seq $lessinit$greater$default$6() {
      return (Seq).MODULE$.Seq().empty();
   }

   public final String toString() {
      return "DriverDescription";
   }

   public DriverDescription apply(final String jarUrl, final int mem, final int cores, final boolean supervise, final Command command, final Seq resourceReqs) {
      return new DriverDescription(jarUrl, mem, cores, supervise, command, resourceReqs);
   }

   public Seq apply$default$6() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Option unapply(final DriverDescription x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple6(x$0.jarUrl(), BoxesRunTime.boxToInteger(x$0.mem()), BoxesRunTime.boxToInteger(x$0.cores()), BoxesRunTime.boxToBoolean(x$0.supervise()), x$0.command(), x$0.resourceReqs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DriverDescription$.class);
   }

   private DriverDescription$() {
   }
}
