package org.glassfish.jaxb.runtime.v2;

import jakarta.xml.bind.JAXBException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jaxb.core.v2.ClassFactory;

final class MUtils {
   private static final Logger LOGGER = Logger.getLogger("org.glassfish.jaxb.runtime");

   static void open(Class[] classes) throws JAXBException {
      Module coreModule = ClassFactory.class.getModule();
      Module rtModule = JAXBContextFactory.class.getModule();
      if (rtModule != coreModule && rtModule.isNamed()) {
         for(Class cls : classes) {
            Class<?> jaxbClass = cls.isArray() ? cls.getComponentType() : cls;
            Module classModule = jaxbClass.getModule();
            if (classModule.isNamed() && !"java.base".equals(classModule.getName())) {
               String packageName = jaxbClass.getPackageName();
               if (!classModule.isOpen(packageName, rtModule)) {
                  throw new JAXBException(MessageFormat.format("Package {0} with class {1} defined in a module {2} must be open to at least {3} module.", packageName, jaxbClass.getName(), classModule.getName(), rtModule.getName()));
               }

               classModule.addOpens(packageName, coreModule);
               if (LOGGER.isLoggable(Level.FINE)) {
                  LOGGER.log(Level.FINE, "Openning package {0} in {1} to {2}.", new String[]{packageName, classModule.getName(), coreModule.getName()});
               }
            }
         }

      }
   }
}
