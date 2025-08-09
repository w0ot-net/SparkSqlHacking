package org.objenesis.instantiator.gcj;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.SerializationInstantiatorHelper;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class GCJSerializationInstantiator extends GCJInstantiatorBase {
   private final Class superType;

   public GCJSerializationInstantiator(Class type) {
      super(type);
      this.superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(type);
   }

   public Object newInstance() {
      try {
         return this.type.cast(newObjectMethod.invoke(dummyStream, this.type, this.superType));
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }
}
