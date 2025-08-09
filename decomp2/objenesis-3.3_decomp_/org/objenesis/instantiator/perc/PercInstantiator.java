package org.objenesis.instantiator.perc;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class PercInstantiator implements ObjectInstantiator {
   private final Method newInstanceMethod;
   private final Object[] typeArgs;

   public PercInstantiator(Class type) {
      this.typeArgs = new Object[]{null, Boolean.FALSE};
      this.typeArgs[0] = type;

      try {
         this.newInstanceMethod = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Boolean.TYPE);
         this.newInstanceMethod.setAccessible(true);
      } catch (NoSuchMethodException | RuntimeException e) {
         throw new ObjenesisException(e);
      }
   }

   public Object newInstance() {
      try {
         return this.newInstanceMethod.invoke((Object)null, this.typeArgs);
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }
}
