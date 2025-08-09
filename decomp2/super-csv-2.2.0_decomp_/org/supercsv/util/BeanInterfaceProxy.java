package org.supercsv.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public final class BeanInterfaceProxy implements InvocationHandler {
   private final Map beanState = new HashMap();

   private BeanInterfaceProxy() {
   }

   public static Object createProxy(Class proxyInterface) {
      if (proxyInterface == null) {
         throw new NullPointerException("proxyInterface should not be null");
      } else {
         return proxyInterface.cast(Proxy.newProxyInstance(proxyInterface.getClassLoader(), new Class[]{proxyInterface}, new BeanInterfaceProxy()));
      }
   }

   public Object invoke(Object proxy, Method method, Object[] args) {
      String methodName = method.getName();
      if (methodName.startsWith("get")) {
         if (method.getParameterTypes().length > 0) {
            throw new IllegalArgumentException(String.format("method %s.%s() should have no parameters to be a valid getter", method.getDeclaringClass().getName(), methodName));
         } else {
            return this.beanState.get(methodName.substring("get".length()));
         }
      } else if (methodName.startsWith("set")) {
         if (args != null && args.length == 1) {
            this.beanState.put(methodName.substring("set".length()), args[0]);
            return proxy;
         } else {
            throw new IllegalArgumentException(String.format("method  %s.%s() should have exactly one parameter to be a valid setter", method.getDeclaringClass().getName(), methodName));
         }
      } else {
         throw new IllegalArgumentException(String.format("method %s.%s() is not a valid getter/setter", method.getDeclaringClass().getName(), methodName));
      }
   }
}
