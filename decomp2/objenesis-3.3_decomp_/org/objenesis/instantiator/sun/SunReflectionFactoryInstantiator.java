package org.objenesis.instantiator.sun;

import java.lang.reflect.Constructor;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class SunReflectionFactoryInstantiator implements ObjectInstantiator {
   private final Constructor mungedConstructor;

   public SunReflectionFactoryInstantiator(Class type) {
      Constructor<Object> javaLangObjectConstructor = getJavaLangObjectConstructor();
      this.mungedConstructor = SunReflectionFactoryHelper.newConstructorForSerialization(type, javaLangObjectConstructor);
      this.mungedConstructor.setAccessible(true);
   }

   public Object newInstance() {
      try {
         return this.mungedConstructor.newInstance((Object[])null);
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }

   private static Constructor getJavaLangObjectConstructor() {
      try {
         return Object.class.getConstructor((Class[])null);
      } catch (NoSuchMethodException e) {
         throw new ObjenesisException(e);
      }
   }
}
