package org.objenesis.instantiator.gcj;

import java.lang.reflect.InvocationTargetException;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class GCJInstantiator extends GCJInstantiatorBase {
   public GCJInstantiator(Class type) {
      super(type);
   }

   public Object newInstance() {
      try {
         return this.type.cast(newObjectMethod.invoke(dummyStream, this.type, Object.class));
      } catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
         throw new ObjenesisException(e);
      }
   }
}
