package org.objenesis.strategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;

public class SingleInstantiatorStrategy implements InstantiatorStrategy {
   private final Constructor constructor;

   public SingleInstantiatorStrategy(Class instantiator) {
      try {
         this.constructor = instantiator.getConstructor(Class.class);
      } catch (NoSuchMethodException e) {
         throw new ObjenesisException(e);
      }
   }

   public ObjectInstantiator newInstantiatorOf(Class type) {
      try {
         return (ObjectInstantiator)this.constructor.newInstance(type);
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
         throw new ObjenesisException(e);
      }
   }
}
