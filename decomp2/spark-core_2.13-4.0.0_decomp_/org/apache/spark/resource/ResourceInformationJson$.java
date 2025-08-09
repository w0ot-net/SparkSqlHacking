package org.apache.spark.resource;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ResourceInformationJson$ extends AbstractFunction2 implements Serializable {
   public static final ResourceInformationJson$ MODULE$ = new ResourceInformationJson$();

   public final String toString() {
      return "ResourceInformationJson";
   }

   public ResourceInformationJson apply(final String name, final Seq addresses) {
      return new ResourceInformationJson(name, addresses);
   }

   public Option unapply(final ResourceInformationJson x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.addresses())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResourceInformationJson$.class);
   }

   private ResourceInformationJson$() {
   }
}
