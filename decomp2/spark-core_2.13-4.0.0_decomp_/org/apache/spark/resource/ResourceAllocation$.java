package org.apache.spark.resource;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ResourceAllocation$ extends AbstractFunction2 implements Serializable {
   public static final ResourceAllocation$ MODULE$ = new ResourceAllocation$();

   public final String toString() {
      return "ResourceAllocation";
   }

   public ResourceAllocation apply(final ResourceID id, final Seq addresses) {
      return new ResourceAllocation(id, addresses);
   }

   public Option unapply(final ResourceAllocation x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.id(), x$0.addresses())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResourceAllocation$.class);
   }

   private ResourceAllocation$() {
   }
}
