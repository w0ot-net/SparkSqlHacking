package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class HostTaskLocation$ extends AbstractFunction1 implements Serializable {
   public static final HostTaskLocation$ MODULE$ = new HostTaskLocation$();

   public final String toString() {
      return "HostTaskLocation";
   }

   public HostTaskLocation apply(final String host) {
      return new HostTaskLocation(host);
   }

   public Option unapply(final HostTaskLocation x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.host()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HostTaskLocation$.class);
   }

   private HostTaskLocation$() {
   }
}
