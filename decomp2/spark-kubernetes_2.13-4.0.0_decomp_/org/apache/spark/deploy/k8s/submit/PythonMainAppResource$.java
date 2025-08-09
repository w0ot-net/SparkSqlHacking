package org.apache.spark.deploy.k8s.submit;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class PythonMainAppResource$ extends AbstractFunction1 implements Serializable {
   public static final PythonMainAppResource$ MODULE$ = new PythonMainAppResource$();

   public final String toString() {
      return "PythonMainAppResource";
   }

   public PythonMainAppResource apply(final String primaryResource) {
      return new PythonMainAppResource(primaryResource);
   }

   public Option unapply(final PythonMainAppResource x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.primaryResource()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PythonMainAppResource$.class);
   }

   private PythonMainAppResource$() {
   }
}
