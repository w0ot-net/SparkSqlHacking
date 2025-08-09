package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JString$ extends AbstractFunction1 implements Serializable {
   public static final JString$ MODULE$ = new JString$();

   public final String toString() {
      return "JString";
   }

   public JString apply(final String s) {
      return new JString(s);
   }

   public Option unapply(final JString x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.s()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JString$.class);
   }

   private JString$() {
   }
}
