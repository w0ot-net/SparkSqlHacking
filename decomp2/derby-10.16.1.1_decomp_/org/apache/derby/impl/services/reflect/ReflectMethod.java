package org.apache.derby.impl.services.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.shared.common.error.StandardException;

class ReflectMethod implements GeneratedMethod {
   private final Method realMethod;

   ReflectMethod(Method var1) {
      this.realMethod = var1;
   }

   public Object invoke(Object var1) throws StandardException {
      Object var2;
      try {
         return this.realMethod.invoke(var1, (Object[])null);
      } catch (IllegalAccessException var4) {
         var2 = var4;
      } catch (IllegalArgumentException var5) {
         var2 = var5;
      } catch (InvocationTargetException var6) {
         var2 = var6;
      }

      throw StandardException.unexpectedUserException((Throwable)var2);
   }
}
