package scala.reflect.internal;

import java.io.Serializable;
import java.lang.reflect.Method;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JMethod$ extends AbstractFunction1 implements Serializable {
   public static final JMethod$ MODULE$ = new JMethod$();

   public final String toString() {
      return "JMethod";
   }

   public JMethod apply(final Method m) {
      return new JMethod(m);
   }

   public Option unapply(final JMethod x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.m()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JMethod$.class);
   }

   private JMethod$() {
   }
}
