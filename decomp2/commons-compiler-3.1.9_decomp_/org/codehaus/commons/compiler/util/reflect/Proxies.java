package org.codehaus.commons.compiler.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import org.codehaus.commons.nullanalysis.NotNullByDefault;

public final class Proxies {
   private Proxies() {
   }

   public static Object newInstance(final Object delegate, final Method... methodsAndDelegateMethod) {
      Class<?> declaringInterface = methodsAndDelegateMethod[0].getDeclaringClass();

      assert declaringInterface.isInterface();

      for(int i = 0; i < methodsAndDelegateMethod.length; i += 2) {
         Method method = methodsAndDelegateMethod[i];
         Method delegateMethod = methodsAndDelegateMethod[i + 1];

         assert method.getDeclaringClass().equals(declaringInterface);

         delegateMethod.setAccessible(true);

         assert method.getReturnType().equals(delegateMethod.getReturnType());

         assert Arrays.equals(method.getParameterTypes(), delegateMethod.getParameterTypes());
      }

      T result = (T)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{declaringInterface}, new InvocationHandler() {
         @NotNullByDefault(false)
         public Object invoke(Object proxy, Method actualMethod, Object[] args) throws Throwable {
            for(int i = 0; i < methodsAndDelegateMethod.length; i += 2) {
               Method method = methodsAndDelegateMethod[i];
               Method delegateMethod = methodsAndDelegateMethod[i + 1];
               if (actualMethod.equals(method)) {
                  return delegateMethod.invoke(delegate, args);
               }
            }

            String message = "Expected invocation of [";

            for(int i = 2; i < methodsAndDelegateMethod.length; i += 2) {
               Method method = methodsAndDelegateMethod[i];
               if (i > 0) {
                  message = message + ", ";
               }

               message = message + method;
            }

            message = message + "], but was " + actualMethod;
            throw new AssertionError(message);
         }
      });
      return result;
   }
}
