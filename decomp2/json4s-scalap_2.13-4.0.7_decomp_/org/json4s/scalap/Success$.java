package org.json4s.scalap;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Success$ implements Serializable {
   public static final Success$ MODULE$ = new Success$();

   public final String toString() {
      return "Success";
   }

   public Success apply(final Object out, final Object value) {
      return new Success(out, value);
   }

   public Option unapply(final Success x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.out(), x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Success$.class);
   }

   private Success$() {
   }
}
