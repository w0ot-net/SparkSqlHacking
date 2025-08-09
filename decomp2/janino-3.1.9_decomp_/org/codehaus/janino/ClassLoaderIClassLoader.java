package org.codehaus.janino;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.commons.nullanalysis.Nullable;

public class ClassLoaderIClassLoader extends IClassLoader {
   private static final Logger LOGGER = Logger.getLogger(ClassLoaderIClassLoader.class.getName());
   private final ClassLoader classLoader;

   public ClassLoaderIClassLoader(ClassLoader classLoader) {
      super((IClassLoader)null);
      this.classLoader = classLoader;
      super.postConstruct();
   }

   public ClassLoaderIClassLoader() {
      this(Thread.currentThread().getContextClassLoader());
   }

   public ClassLoader getClassLoader() {
      return this.classLoader;
   }

   @Nullable
   protected IClass findIClass(String descriptor) throws ClassNotFoundException {
      LOGGER.entering((String)null, "findIClass", descriptor);

      Class<?> clazz;
      try {
         clazz = this.classLoader.loadClass(Descriptor.toClassName(descriptor));
      } catch (NoClassDefFoundError ncdfe) {
         if (ncdfe.getMessage().contains("wrong name")) {
            return null;
         }

         throw ncdfe;
      } catch (ClassNotFoundException e) {
         Throwable t;
         for(t = e.getCause(); t instanceof ClassNotFoundException; t = t.getCause()) {
         }

         if (t == null) {
            return null;
         }

         throw e;
      }

      LOGGER.log(Level.FINE, "clazz={0}", clazz);
      IClass result = new ReflectionIClass(clazz, this);
      this.defineIClass(result);
      return result;
   }
}
