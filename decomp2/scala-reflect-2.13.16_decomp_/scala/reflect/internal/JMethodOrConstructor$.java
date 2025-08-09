package scala.reflect.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class JMethodOrConstructor$ {
   public static final JMethodOrConstructor$ MODULE$ = new JMethodOrConstructor$();

   public JMethodOrConstructor liftMethodToJmoc(final Method m) {
      return new JMethod(m);
   }

   public JMethodOrConstructor liftConstructorToJmoc(final Constructor m) {
      return new JConstructor(m);
   }

   private JMethodOrConstructor$() {
   }
}
