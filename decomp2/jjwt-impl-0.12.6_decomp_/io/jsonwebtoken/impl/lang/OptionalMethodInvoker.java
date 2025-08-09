package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Classes;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OptionalMethodInvoker extends ReflectionFunction {
   private final Class CLASS;
   private final Method METHOD;
   private final Class[] PARAM_TYPES;
   private final boolean STATIC;

   public OptionalMethodInvoker(String fqcn, String methodName) {
      this(fqcn, methodName, (Class)null, false);
   }

   public OptionalMethodInvoker(String fqcn, String methodName, Class paramType, boolean isStatic) {
      Class<?> clazz = null;
      Method method = null;
      Class<?>[] paramTypes = paramType != null ? new Class[]{paramType} : null;

      try {
         clazz = Classes.forName(fqcn);
         method = clazz.getMethod(methodName, paramTypes);
      } catch (Throwable var9) {
      }

      this.CLASS = clazz;
      this.METHOD = method;
      this.PARAM_TYPES = paramTypes;
      this.STATIC = isStatic;
   }

   protected boolean supports(Object input) {
      Class<?> clazz = null;
      if (this.CLASS != null && this.METHOD != null) {
         clazz = this.STATIC && this.PARAM_TYPES != null ? this.PARAM_TYPES[0] : this.CLASS;
      }

      return clazz != null && clazz.isInstance(input);
   }

   protected Object invoke(Object input) throws InvocationTargetException, IllegalAccessException {
      return this.STATIC ? this.METHOD.invoke((Object)null, input) : this.METHOD.invoke(input);
   }
}
