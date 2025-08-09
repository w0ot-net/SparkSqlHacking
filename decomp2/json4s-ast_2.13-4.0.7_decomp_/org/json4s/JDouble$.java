package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JDouble$ extends AbstractFunction1 implements Serializable {
   public static final JDouble$ MODULE$ = new JDouble$();

   public final String toString() {
      return "JDouble";
   }

   public JDouble apply(final double num) {
      return new JDouble(num);
   }

   public Option unapply(final JDouble x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToDouble(x$0.num())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JDouble$.class);
   }

   private JDouble$() {
   }
}
