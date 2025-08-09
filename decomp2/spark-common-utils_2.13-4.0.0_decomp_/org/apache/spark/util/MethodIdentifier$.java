package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class MethodIdentifier$ implements Serializable {
   public static final MethodIdentifier$ MODULE$ = new MethodIdentifier$();

   public final String toString() {
      return "MethodIdentifier";
   }

   public MethodIdentifier apply(final Class cls, final String name, final String desc) {
      return new MethodIdentifier(cls, name, desc);
   }

   public Option unapply(final MethodIdentifier x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.cls(), x$0.name(), x$0.desc())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MethodIdentifier$.class);
   }

   private MethodIdentifier$() {
   }
}
