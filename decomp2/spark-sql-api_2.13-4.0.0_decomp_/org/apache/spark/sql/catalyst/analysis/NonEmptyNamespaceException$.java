package org.apache.spark.sql.catalyst.analysis;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class NonEmptyNamespaceException$ extends AbstractFunction3 implements Serializable {
   public static final NonEmptyNamespaceException$ MODULE$ = new NonEmptyNamespaceException$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public final String toString() {
      return "NonEmptyNamespaceException";
   }

   public NonEmptyNamespaceException apply(final String[] namespace, final String details, final Option cause) {
      return new NonEmptyNamespaceException(namespace, details, cause);
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public Option unapply(final NonEmptyNamespaceException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.namespace(), x$0.details(), x$0.cause())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NonEmptyNamespaceException$.class);
   }

   private NonEmptyNamespaceException$() {
   }
}
