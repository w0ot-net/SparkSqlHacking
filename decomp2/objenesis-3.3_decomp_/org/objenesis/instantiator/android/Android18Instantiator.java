package org.objenesis.instantiator.android;

import java.io.ObjectStreamClass;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class Android18Instantiator implements ObjectInstantiator {
   private final Class type;
   private final Method newInstanceMethod;
   private final Long objectConstructorId;

   public Android18Instantiator(Class type) {
      this.type = type;
      this.newInstanceMethod = getNewInstanceMethod();
      this.objectConstructorId = findConstructorIdForJavaLangObjectConstructor();
   }

   public Object newInstance() {
      try {
         return this.type.cast(this.newInstanceMethod.invoke((Object)null, this.type, this.objectConstructorId));
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }

   private static Method getNewInstanceMethod() {
      try {
         Method newInstanceMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Long.TYPE);
         newInstanceMethod.setAccessible(true);
         return newInstanceMethod;
      } catch (NoSuchMethodException | RuntimeException e) {
         throw new ObjenesisException(e);
      }
   }

   private static Long findConstructorIdForJavaLangObjectConstructor() {
      try {
         Method newInstanceMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
         newInstanceMethod.setAccessible(true);
         return (Long)newInstanceMethod.invoke((Object)null, Object.class);
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | RuntimeException e) {
         throw new ObjenesisException(e);
      }
   }
}
