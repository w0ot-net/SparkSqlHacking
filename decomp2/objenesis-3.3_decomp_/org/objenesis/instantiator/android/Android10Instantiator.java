package org.objenesis.instantiator.android;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class Android10Instantiator implements ObjectInstantiator {
   private final Class type;
   private final Method newStaticMethod;

   public Android10Instantiator(Class type) {
      this.type = type;
      this.newStaticMethod = getNewStaticMethod();
   }

   public Object newInstance() {
      try {
         return this.type.cast(this.newStaticMethod.invoke((Object)null, this.type, Object.class));
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }

   private static Method getNewStaticMethod() {
      try {
         Method newStaticMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
         newStaticMethod.setAccessible(true);
         return newStaticMethod;
      } catch (NoSuchMethodException | RuntimeException e) {
         throw new ObjenesisException(e);
      }
   }
}
