package org.apache.spark.deploy.k8s.submit;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JavaMainAppResource$ extends AbstractFunction1 implements Serializable {
   public static final JavaMainAppResource$ MODULE$ = new JavaMainAppResource$();

   public final String toString() {
      return "JavaMainAppResource";
   }

   public JavaMainAppResource apply(final Option primaryResource) {
      return new JavaMainAppResource(primaryResource);
   }

   public Option unapply(final JavaMainAppResource x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.primaryResource()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JavaMainAppResource$.class);
   }

   private JavaMainAppResource$() {
   }
}
