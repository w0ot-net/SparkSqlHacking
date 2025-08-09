package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.math.BigInt;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JInt$ extends AbstractFunction1 implements Serializable {
   public static final JInt$ MODULE$ = new JInt$();

   public final String toString() {
      return "JInt";
   }

   public JInt apply(final BigInt num) {
      return new JInt(num);
   }

   public Option unapply(final JInt x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.num()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JInt$.class);
   }

   private JInt$() {
   }
}
