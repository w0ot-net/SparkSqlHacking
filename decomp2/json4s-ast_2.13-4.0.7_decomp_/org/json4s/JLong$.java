package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class JLong$ extends AbstractFunction1 implements Serializable {
   public static final JLong$ MODULE$ = new JLong$();

   public final String toString() {
      return "JLong";
   }

   public JLong apply(final long num) {
      return new JLong(num);
   }

   public Option unapply(final JLong x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(x$0.num())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JLong$.class);
   }

   private JLong$() {
   }
}
