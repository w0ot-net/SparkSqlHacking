package org.objenesis.strategy;

import java.io.NotSerializableException;
import java.io.Serializable;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.android.AndroidSerializationInstantiator;
import org.objenesis.instantiator.basic.ObjectInputStreamInstantiator;
import org.objenesis.instantiator.basic.ObjectStreamClassInstantiator;
import org.objenesis.instantiator.gcj.GCJSerializationInstantiator;
import org.objenesis.instantiator.perc.PercSerializationInstantiator;
import org.objenesis.instantiator.sun.SunReflectionFactorySerializationInstantiator;

public class SerializingInstantiatorStrategy extends BaseInstantiatorStrategy {
   public ObjectInstantiator newInstantiatorOf(Class type) {
      if (!Serializable.class.isAssignableFrom(type)) {
         throw new ObjenesisException(new NotSerializableException(type + " not serializable"));
      } else if (!PlatformDescription.JVM_NAME.startsWith("Java HotSpot") && !PlatformDescription.isThisJVM("OpenJDK")) {
         if (PlatformDescription.JVM_NAME.startsWith("Dalvik")) {
            return (ObjectInstantiator)(PlatformDescription.isAndroidOpenJDK() ? new ObjectStreamClassInstantiator(type) : new AndroidSerializationInstantiator(type));
         } else if (PlatformDescription.JVM_NAME.startsWith("GNU libgcj")) {
            return new GCJSerializationInstantiator(type);
         } else {
            return (ObjectInstantiator)(PlatformDescription.JVM_NAME.startsWith("PERC") ? new PercSerializationInstantiator(type) : new SunReflectionFactorySerializationInstantiator(type));
         }
      } else {
         return (ObjectInstantiator)(PlatformDescription.isGoogleAppEngine() && PlatformDescription.SPECIFICATION_VERSION.equals("1.7") ? new ObjectInputStreamInstantiator(type) : new SunReflectionFactorySerializationInstantiator(type));
      }
   }
}
