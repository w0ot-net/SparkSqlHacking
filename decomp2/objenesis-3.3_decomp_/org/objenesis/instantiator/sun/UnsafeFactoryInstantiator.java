package org.objenesis.instantiator.sun;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

@Instantiator(Typology.STANDARD)
public class UnsafeFactoryInstantiator implements ObjectInstantiator {
   private final Unsafe unsafe = UnsafeUtils.getUnsafe();
   private final Class type;

   public UnsafeFactoryInstantiator(Class type) {
      this.type = type;
   }

   public Object newInstance() {
      try {
         return this.type.cast(this.unsafe.allocateInstance(this.type));
      } catch (InstantiationException e) {
         throw new ObjenesisException(e);
      }
   }
}
