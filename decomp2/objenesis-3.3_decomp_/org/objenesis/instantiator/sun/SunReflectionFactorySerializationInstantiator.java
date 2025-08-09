package org.objenesis.instantiator.sun;

import java.io.NotSerializableException;
import java.lang.reflect.Constructor;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.SerializationInstantiatorHelper;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class SunReflectionFactorySerializationInstantiator implements ObjectInstantiator {
   private final Constructor mungedConstructor;

   public SunReflectionFactorySerializationInstantiator(Class type) {
      Class<? super T> nonSerializableAncestor = SerializationInstantiatorHelper.getNonSerializableSuperClass(type);

      Constructor<? super T> nonSerializableAncestorConstructor;
      try {
         nonSerializableAncestorConstructor = nonSerializableAncestor.getDeclaredConstructor((Class[])null);
      } catch (NoSuchMethodException var5) {
         throw new ObjenesisException(new NotSerializableException(type + " has no suitable superclass constructor"));
      }

      this.mungedConstructor = SunReflectionFactoryHelper.newConstructorForSerialization(type, nonSerializableAncestorConstructor);
      this.mungedConstructor.setAccessible(true);
   }

   public Object newInstance() {
      try {
         return this.mungedConstructor.newInstance((Object[])null);
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }
}
