package scala.reflect.internal;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JConstructor$ extends AbstractFunction1 implements Serializable {
   public static final JConstructor$ MODULE$ = new JConstructor$();

   public final String toString() {
      return "JConstructor";
   }

   public JConstructor apply(final Constructor m) {
      return new JConstructor(m);
   }

   public Option unapply(final JConstructor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.m()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JConstructor$.class);
   }

   private JConstructor$() {
   }
}
