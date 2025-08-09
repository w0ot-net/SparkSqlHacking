package org.json4s.scalap;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Error$ implements Serializable {
   public static final Error$ MODULE$ = new Error$();

   public final String toString() {
      return "Error";
   }

   public Error apply(final Object error) {
      return new Error(error);
   }

   public Option unapply(final Error x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.error()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Error$.class);
   }

   private Error$() {
   }
}
