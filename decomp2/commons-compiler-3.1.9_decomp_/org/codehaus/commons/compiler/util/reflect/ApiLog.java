package org.codehaus.commons.compiler.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.commons.nullanalysis.NotNullByDefault;

public final class ApiLog {
   private ApiLog() {
   }

   public static Object logMethodInvocations(final Object delegate) {
      if (!Boolean.getBoolean(ApiLog.class.getName() + ".enable")) {
         return delegate;
      } else {
         Class<? extends Object> c = delegate.getClass();
         return Proxy.newProxyInstance(c.getClassLoader(), getAllInterfaces(c), new InvocationHandler() {
            @NotNullByDefault(false)
            public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
               Object returnValue;
               try {
                  returnValue = method.invoke(delegate, arguments);
               } catch (InvocationTargetException ite) {
                  Throwable targetException = ite.getTargetException();
                  System.err.printf("%s.%s(%s) throws %s%n", delegate.getClass().getSimpleName(), method.getName(), ApiLog.truncate(Arrays.deepToString(arguments)), targetException);
                  throw targetException;
               }

               System.err.printf("%s.%s(%s) => %s%n", delegate.getClass().getSimpleName(), method.getName(), ApiLog.truncate(Arrays.deepToString(arguments)), ApiLog.truncate(String.valueOf(returnValue)));
               return returnValue;
            }
         });
      }
   }

   protected static String truncate(String s) {
      return s.length() < 200 ? s : s.substring(0, 100) + "...";
   }

   private static Class[] getAllInterfaces(Class c) {
      Set<Class<?>> result = new HashSet();
      getAllInterfaces(c, result);
      return (Class[])result.toArray(new Class[result.size()]);
   }

   private static void getAllInterfaces(Class c, Set result) {
      if (c.isInterface()) {
         result.add(c);
      }

      for(Class i : c.getInterfaces()) {
         getAllInterfaces(i, result);
      }

      Class<?> sc = c.getSuperclass();
      if (sc != null) {
         getAllInterfaces(sc, result);
      }

   }
}
