package org.objenesis.instantiator.basic;

import java.io.ObjectStreamClass;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class ObjectStreamClassInstantiator implements ObjectInstantiator {
   private static Method newInstanceMethod;
   private final ObjectStreamClass objStreamClass;

   private static void initialize() {
      if (newInstanceMethod == null) {
         try {
            newInstanceMethod = ObjectStreamClass.class.getDeclaredMethod("newInstance");
            newInstanceMethod.setAccessible(true);
         } catch (NoSuchMethodException | RuntimeException e) {
            throw new ObjenesisException(e);
         }
      }

   }

   public ObjectStreamClassInstantiator(Class type) {
      initialize();
      this.objStreamClass = ObjectStreamClass.lookup(type);
   }

   public Object newInstance() {
      try {
         return newInstanceMethod.invoke(this.objStreamClass);
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }
}
