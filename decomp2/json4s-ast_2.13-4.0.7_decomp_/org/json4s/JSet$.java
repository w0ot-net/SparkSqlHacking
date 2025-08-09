package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Set;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JSet$ extends AbstractFunction1 implements Serializable {
   public static final JSet$ MODULE$ = new JSet$();

   public final String toString() {
      return "JSet";
   }

   public JSet apply(final Set set) {
      return new JSet(set);
   }

   public Option unapply(final JSet x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.set()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JSet$.class);
   }

   private JSet$() {
   }
}
