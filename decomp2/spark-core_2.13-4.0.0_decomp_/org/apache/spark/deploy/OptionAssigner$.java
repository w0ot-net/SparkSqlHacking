package org.apache.spark.deploy;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OptionAssigner$ extends AbstractFunction6 implements Serializable {
   public static final OptionAssigner$ MODULE$ = new OptionAssigner$();

   public String $lessinit$greater$default$4() {
      return null;
   }

   public String $lessinit$greater$default$5() {
      return null;
   }

   public Option $lessinit$greater$default$6() {
      return .MODULE$;
   }

   public final String toString() {
      return "OptionAssigner";
   }

   public OptionAssigner apply(final String value, final int clusterManager, final int deployMode, final String clOption, final String confKey, final Option mergeFn) {
      return new OptionAssigner(value, clusterManager, deployMode, clOption, confKey, mergeFn);
   }

   public String apply$default$4() {
      return null;
   }

   public String apply$default$5() {
      return null;
   }

   public Option apply$default$6() {
      return .MODULE$;
   }

   public Option unapply(final OptionAssigner x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.value(), BoxesRunTime.boxToInteger(x$0.clusterManager()), BoxesRunTime.boxToInteger(x$0.deployMode()), x$0.clOption(), x$0.confKey(), x$0.mergeFn())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OptionAssigner$.class);
   }

   private OptionAssigner$() {
   }
}
