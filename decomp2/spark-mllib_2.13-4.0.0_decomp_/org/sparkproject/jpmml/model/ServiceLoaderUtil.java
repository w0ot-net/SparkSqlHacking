package org.sparkproject.jpmml.model;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.sparkproject.dmg.pmml.PMMLObject;

public class ServiceLoaderUtil {
   private ServiceLoaderUtil() {
   }

   public static PMMLObject load(Class clazz, ClassLoader clazzLoader) {
      ServiceLoader<E> serviceLoader;
      try {
         serviceLoader = ServiceLoader.load(clazz, clazzLoader);
      } catch (ServiceConfigurationError sce) {
         throw new IllegalArgumentException(sce);
      }

      Iterator<E> it = serviceLoader.iterator();
      if (it.hasNext()) {
         E object = (E)((PMMLObject)it.next());
         if (it.hasNext()) {
            throw new IllegalArgumentException();
         } else {
            return object;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }
}
