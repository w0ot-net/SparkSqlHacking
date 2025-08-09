package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JArray$ extends AbstractFunction1 implements Serializable {
   public static final JArray$ MODULE$ = new JArray$();

   public final String toString() {
      return "JArray";
   }

   public JArray apply(final List arr) {
      return new JArray(arr);
   }

   public Option unapply(final JArray x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.arr()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JArray$.class);
   }

   private JArray$() {
   }
}
