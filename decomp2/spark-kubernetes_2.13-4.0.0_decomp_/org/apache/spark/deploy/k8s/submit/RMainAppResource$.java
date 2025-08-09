package org.apache.spark.deploy.k8s.submit;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class RMainAppResource$ extends AbstractFunction1 implements Serializable {
   public static final RMainAppResource$ MODULE$ = new RMainAppResource$();

   public final String toString() {
      return "RMainAppResource";
   }

   public RMainAppResource apply(final String primaryResource) {
      return new RMainAppResource(primaryResource);
   }

   public Option unapply(final RMainAppResource x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.primaryResource()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RMainAppResource$.class);
   }

   private RMainAppResource$() {
   }
}
