package py4j.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionShim {
   private static Method trySetAccessible;

   public static boolean trySetAccessible(AccessibleObject accessibleObject) {
      if (trySetAccessible != null) {
         try {
            return (Boolean)trySetAccessible.invoke(accessibleObject);
         } catch (IllegalAccessException e) {
            throw new AssertionError(e);
         } catch (InvocationTargetException e) {
            throw new AssertionError(e);
         }
      } else {
         accessibleObject.setAccessible(true);
         return true;
      }
   }

   static {
      try {
         trySetAccessible = AccessibleObject.class.getDeclaredMethod("trySetAccessible");
      } catch (NoSuchMethodException var1) {
         trySetAccessible = null;
      }

   }
}
