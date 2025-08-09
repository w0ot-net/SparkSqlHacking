package org.glassfish.jersey.servlet.internal;

import java.lang.reflect.Method;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ThreadLocalNamedInvoker extends ThreadLocalInvoker {
   private final String name;

   public ThreadLocalNamedInvoker(String name) {
      this.name = name;
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (this.get() == null) {
         Context ctx = new InitialContext();
         T t = (T)ctx.lookup(this.name);
         this.set(t);
      }

      return super.invoke(proxy, method, args);
   }
}
