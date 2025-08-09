package org.objenesis.strategy;

import java.io.Serializable;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.android.Android10Instantiator;
import org.objenesis.instantiator.android.Android17Instantiator;
import org.objenesis.instantiator.android.Android18Instantiator;
import org.objenesis.instantiator.basic.AccessibleInstantiator;
import org.objenesis.instantiator.basic.ObjectInputStreamInstantiator;
import org.objenesis.instantiator.gcj.GCJInstantiator;
import org.objenesis.instantiator.perc.PercInstantiator;
import org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;
import org.objenesis.instantiator.sun.UnsafeFactoryInstantiator;

public class StdInstantiatorStrategy extends BaseInstantiatorStrategy {
   public ObjectInstantiator newInstantiatorOf(Class type) {
      if (!PlatformDescription.isThisJVM("Java HotSpot") && !PlatformDescription.isThisJVM("OpenJDK")) {
         if (PlatformDescription.isThisJVM("Dalvik")) {
            if (PlatformDescription.isAndroidOpenJDK()) {
               return new UnsafeFactoryInstantiator(type);
            } else if (PlatformDescription.ANDROID_VERSION <= 10) {
               return new Android10Instantiator(type);
            } else {
               return (ObjectInstantiator)(PlatformDescription.ANDROID_VERSION <= 17 ? new Android17Instantiator(type) : new Android18Instantiator(type));
            }
         } else if (PlatformDescription.isThisJVM("GNU libgcj")) {
            return new GCJInstantiator(type);
         } else {
            return (ObjectInstantiator)(PlatformDescription.isThisJVM("PERC") ? new PercInstantiator(type) : new UnsafeFactoryInstantiator(type));
         }
      } else if (PlatformDescription.isGoogleAppEngine() && PlatformDescription.SPECIFICATION_VERSION.equals("1.7")) {
         return (ObjectInstantiator)(Serializable.class.isAssignableFrom(type) ? new ObjectInputStreamInstantiator(type) : new AccessibleInstantiator(type));
      } else {
         return new SunReflectionFactoryInstantiator(type);
      }
   }
}
