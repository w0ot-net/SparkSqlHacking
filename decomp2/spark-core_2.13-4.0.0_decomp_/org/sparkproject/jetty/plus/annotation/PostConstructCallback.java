package org.sparkproject.jetty.plus.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PostConstructCallback extends LifeCycleCallback {
   public PostConstructCallback(Class clazz, String methodName) {
      super(clazz, methodName);
   }

   public PostConstructCallback(String className, String methodName) {
      super(className, methodName);
   }

   public void validate(Class clazz, Method method) {
      if (method.getExceptionTypes().length > 0) {
         String var4 = clazz.getName();
         throw new IllegalArgumentException(var4 + "." + method.getName() + " cannot not throw a checked exception");
      } else if (!method.getReturnType().equals(Void.TYPE)) {
         String var3 = clazz.getName();
         throw new IllegalArgumentException(var3 + "." + method.getName() + " cannot not have a return type");
      } else if (Modifier.isStatic(method.getModifiers())) {
         String var10002 = clazz.getName();
         throw new IllegalArgumentException(var10002 + "." + method.getName() + " cannot be static");
      }
   }

   public void callback(Object instance) throws SecurityException, IllegalArgumentException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
      super.callback(instance);
   }

   public boolean equals(Object o) {
      return super.equals(o) && o instanceof PostConstructCallback;
   }
}
