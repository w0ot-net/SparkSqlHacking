package org.apache.spark.serializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class DummyInvocationHandler$ implements InvocationHandler {
   public static final DummyInvocationHandler$ MODULE$ = new DummyInvocationHandler$();

   public Object invoke(final Object proxy, final Method method, final Object[] args) {
      throw new UnsupportedOperationException("Not implemented");
   }

   private DummyInvocationHandler$() {
   }
}
