package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.math.BigDecimal;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JDecimal$ extends AbstractFunction1 implements Serializable {
   public static final JDecimal$ MODULE$ = new JDecimal$();

   public final String toString() {
      return "JDecimal";
   }

   public JDecimal apply(final BigDecimal num) {
      return new JDecimal(num);
   }

   public Option unapply(final JDecimal x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.num()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JDecimal$.class);
   }

   private JDecimal$() {
   }
}
