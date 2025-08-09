package org.codehaus.commons.compiler.util.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.codehaus.commons.nullanalysis.Nullable;

public final class Methods {
   private Methods() {
   }

   public static Object invoke(Method method, @Nullable Object obj, Object... args) throws Throwable {
      try {
         R returnValue = (R)method.invoke(obj, args);
         return returnValue;
      } catch (InvocationTargetException ite) {
         EX targetException = (EX)ite.getTargetException();
         throw targetException;
      } catch (Exception e) {
         throw new AssertionError(e);
      }
   }
}
