package org.apache.curator.shaded.com.google.common.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
public abstract class AbstractInvocationHandler implements InvocationHandler {
   private static final Object[] NO_ARGS = new Object[0];

   @CheckForNull
   public final Object invoke(Object proxy, Method method, @CheckForNull @Nullable Object[] args) throws Throwable {
      if (args == null) {
         args = NO_ARGS;
      }

      if (args.length == 0 && method.getName().equals("hashCode")) {
         return this.hashCode();
      } else if (args.length == 1 && method.getName().equals("equals") && method.getParameterTypes()[0] == Object.class) {
         Object arg = args[0];
         if (arg == null) {
            return false;
         } else {
            return proxy == arg ? true : isProxyOfSameInterfaces(arg, proxy.getClass()) && this.equals(Proxy.getInvocationHandler(arg));
         }
      } else {
         return args.length == 0 && method.getName().equals("toString") ? this.toString() : this.handleInvocation(proxy, method, args);
      }
   }

   @CheckForNull
   protected abstract Object handleInvocation(Object proxy, Method method, @Nullable Object[] args) throws Throwable;

   public boolean equals(@CheckForNull Object obj) {
      return super.equals(obj);
   }

   public int hashCode() {
      return super.hashCode();
   }

   public String toString() {
      return super.toString();
   }

   private static boolean isProxyOfSameInterfaces(Object arg, Class proxyClass) {
      return proxyClass.isInstance(arg) || Proxy.isProxyClass(arg.getClass()) && Arrays.equals(arg.getClass().getInterfaces(), proxyClass.getInterfaces());
   }
}
