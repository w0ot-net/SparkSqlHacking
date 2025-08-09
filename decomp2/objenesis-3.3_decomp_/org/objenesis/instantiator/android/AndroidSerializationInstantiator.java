package org.objenesis.instantiator.android;

import java.io.ObjectStreamClass;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class AndroidSerializationInstantiator implements ObjectInstantiator {
   private final Class type;
   private final ObjectStreamClass objectStreamClass;
   private final Method newInstanceMethod;

   public AndroidSerializationInstantiator(Class type) {
      this.type = type;
      this.newInstanceMethod = getNewInstanceMethod();

      Method m;
      try {
         m = ObjectStreamClass.class.getMethod("lookupAny", Class.class);
      } catch (NoSuchMethodException e) {
         throw new ObjenesisException(e);
      }

      try {
         this.objectStreamClass = (ObjectStreamClass)m.invoke((Object)null, type);
      } catch (InvocationTargetException | IllegalAccessException e) {
         throw new ObjenesisException(e);
      }
   }

   public Object newInstance() {
      try {
         return this.type.cast(this.newInstanceMethod.invoke(this.objectStreamClass, this.type));
      } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
         throw new ObjenesisException(e);
      }
   }

   private static Method getNewInstanceMethod() {
      try {
         Method newInstanceMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class);
         newInstanceMethod.setAccessible(true);
         return newInstanceMethod;
      } catch (NoSuchMethodException | RuntimeException e) {
         throw new ObjenesisException(e);
      }
   }
}
