package jakarta.xml.bind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ModuleUtil {
   private static final Logger LOGGER = Logger.getLogger("jakarta.xml.bind");
   private static final boolean JPMS_SUPPORTED;

   static Class[] getClassesFromContextPath(String contextPath, ClassLoader classLoader) throws JAXBException {
      List<Class<?>> classes = new ArrayList();
      if (contextPath != null && !contextPath.isEmpty()) {
         String[] tokens = contextPath.split(":");

         for(String pkg : tokens) {
            try {
               Class<?> o = classLoader.loadClass(pkg + ".ObjectFactory");
               classes.add(o);
            } catch (ClassNotFoundException var10) {
               try {
                  Class<?> firstByJaxbIndex = findFirstByJaxbIndex(pkg, classLoader);
                  if (firstByJaxbIndex != null) {
                     classes.add(firstByJaxbIndex);
                  }
               } catch (IOException e) {
                  throw new JAXBException(e);
               }
            }
         }

         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Resolved classes from context path: {0}", classes);
         }

         return (Class[])classes.toArray(new Class[0]);
      } else {
         return (Class[])classes.toArray(new Class[0]);
      }
   }

   static Class findFirstByJaxbIndex(String pkg, ClassLoader classLoader) throws IOException, JAXBException {
      String resource = pkg.replace('.', '/') + "/jaxb.index";
      InputStream resourceAsStream = classLoader.getResourceAsStream(resource);
      if (resourceAsStream == null) {
         return null;
      } else {
         BufferedReader in = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));

         Class var6;
         label42: {
            try {
               for(String className = in.readLine(); className != null; className = in.readLine()) {
                  className = className.trim();
                  if (!className.startsWith("#") && !className.isEmpty()) {
                     try {
                        var6 = classLoader.loadClass(pkg + "." + className);
                        break label42;
                     } catch (ClassNotFoundException e) {
                        throw new JAXBException(Messages.format("ContextFinder.ErrorLoadClass", className, pkg), e);
                     }
                  }
               }
            } catch (Throwable var9) {
               try {
                  in.close();
               } catch (Throwable var7) {
                  var9.addSuppressed(var7);
               }

               throw var9;
            }

            in.close();
            return null;
         }

         in.close();
         return var6;
      }
   }

   public static void delegateAddOpensToImplModule(Class[] classes, Class factorySPI) throws JAXBException {
      if (JPMS_SUPPORTED) {
         Module implModule = factorySPI.getModule();
         Module jaxbModule = JAXBContext.class.getModule();
         if (!jaxbModule.isNamed()) {
            if (LOGGER.isLoggable(Level.FINE)) {
               LOGGER.log(Level.FINE, "Using jakarta.xml.bind-api on the class path.");
            }

            return;
         }

         for(Class cls : classes) {
            Class<?> jaxbClass = cls.isArray() ? cls.getComponentType() : cls;
            Module classModule = jaxbClass.getModule();
            String packageName = jaxbClass.getPackageName();
            if (classModule.isNamed() && !classModule.getName().equals("java.base")) {
               if (!classModule.isOpen(packageName, jaxbModule)) {
                  throw new JAXBException(Messages.format("JAXBClasses.notOpen", packageName, jaxbClass.getName(), classModule.getName()));
               }

               classModule.addOpens(packageName, implModule);
               if (LOGGER.isLoggable(Level.FINE)) {
                  LOGGER.log(Level.FINE, "Propagating openness of package {0} in {1} to {2}.", new String[]{packageName, classModule.getName(), implModule.getName()});
               }
            }
         }
      } else if (LOGGER.isLoggable(Level.FINE)) {
         LOGGER.log(Level.FINE, "Using jakarta.xml.bind-api with no JPMS related APIs, such as Class::getModule.");
      }

   }

   static {
      boolean b = false;

      try {
         JAXBContext.class.getModule();
         b = true;
      } catch (NoSuchMethodError var2) {
         b = false;
      }

      JPMS_SUPPORTED = b;
   }
}
