package org.apache.spark.resource;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ResourceRequirement$ extends AbstractFunction3 implements Serializable {
   public static final ResourceRequirement$ MODULE$ = new ResourceRequirement$();

   public int $lessinit$greater$default$3() {
      return 1;
   }

   public final String toString() {
      return "ResourceRequirement";
   }

   public ResourceRequirement apply(final String resourceName, final int amount, final int numParts) {
      return new ResourceRequirement(resourceName, amount, numParts);
   }

   public int apply$default$3() {
      return 1;
   }

   public Option unapply(final ResourceRequirement x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.resourceName(), BoxesRunTime.boxToInteger(x$0.amount()), BoxesRunTime.boxToInteger(x$0.numParts()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResourceRequirement$.class);
   }

   private ResourceRequirement$() {
   }
}
