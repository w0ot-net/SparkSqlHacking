package org.objenesis.instantiator.perc;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class PercSerializationInstantiator implements ObjectInstantiator {
   private final Object[] typeArgs;
   private final Method newInstanceMethod;

   public PercSerializationInstantiator(Class type) {
      Class<? super T> unserializableType;
      for(unserializableType = type; Serializable.class.isAssignableFrom(unserializableType); unserializableType = unserializableType.getSuperclass()) {
      }

      try {
         Class<?> percMethodClass = Class.forName("COM.newmonics.PercClassLoader.Method");
         this.newInstanceMethod = ObjectInputStream.class.getDeclaredMethod("noArgConstruct", Class.class, Object.class, percMethodClass);
         this.newInstanceMethod.setAccessible(true);
         Class<?> percClassClass = Class.forName("COM.newmonics.PercClassLoader.PercClass");
         Method getPercClassMethod = percClassClass.getDeclaredMethod("getPercClass", Class.class);
         Object someObject = getPercClassMethod.invoke((Object)null, unserializableType);
         Method findMethodMethod = someObject.getClass().getDeclaredMethod("findMethod", String.class);
         Object percMethod = findMethodMethod.invoke(someObject, "<init>()V");
         this.typeArgs = new Object[]{unserializableType, type, percMethod};
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
         throw new ObjenesisException(e);
      }
   }

   public Object newInstance() {
      try {
         return this.newInstanceMethod.invoke((Object)null, this.typeArgs);
      } catch (InvocationTargetException | IllegalAccessException e) {
         throw new ObjenesisException(e);
      }
   }
}
