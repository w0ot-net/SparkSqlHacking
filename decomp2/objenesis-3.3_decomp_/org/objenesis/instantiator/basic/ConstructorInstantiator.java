package org.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.NOT_COMPLIANT)
public class ConstructorInstantiator implements ObjectInstantiator {
   protected Constructor constructor;

   public ConstructorInstantiator(Class type) {
      try {
         this.constructor = type.getDeclaredConstructor((Class[])null);
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }

   public Object newInstance() {
      try {
         return this.constructor.newInstance((Object[])null);
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }
}
