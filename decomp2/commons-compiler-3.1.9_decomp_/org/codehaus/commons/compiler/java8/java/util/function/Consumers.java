package org.codehaus.commons.compiler.java8.java.util.function;

import org.codehaus.commons.compiler.util.reflect.Classes;
import org.codehaus.commons.compiler.util.reflect.Methods;
import org.codehaus.commons.compiler.util.reflect.Proxies;

public final class Consumers {
   private Consumers() {
   }

   public static Consumer from(final Object delegate) {
      return new Consumer() {
         public void accept(Object t) {
            Methods.invoke(Consumer.METHOD_accept__T, delegate, t);
         }
      };
   }

   public static Object from(Consumer delegate) {
      return Proxies.newInstance(delegate, Consumer.METHOD_accept__T, Classes.getDeclaredMethod(delegate.getClass(), "accept", Object.class));
   }
}
