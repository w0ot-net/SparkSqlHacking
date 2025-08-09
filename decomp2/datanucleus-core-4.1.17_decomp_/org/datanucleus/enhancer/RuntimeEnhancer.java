package org.datanucleus.enhancer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.util.StringUtils;

public class RuntimeEnhancer {
   private final ClassLoaderResolver clr;
   private final NucleusContext nucleusContext;
   Map runtimeLoaderByLoader = new HashMap();
   List classEnhancerOptions = new ArrayList();

   public RuntimeEnhancer(String api, Map contextProps) {
      this.nucleusContext = new EnhancementNucleusContextImpl(api, contextProps);
      this.clr = this.nucleusContext.getClassLoaderResolver((ClassLoader)null);
      this.classEnhancerOptions.add("generate-primary-key");
      this.classEnhancerOptions.add("generate-default-constructor");
   }

   public void setClassEnhancerOption(String optionName) {
      this.classEnhancerOptions.add(optionName);
   }

   public void unsetClassEnhancerOption(String optionName) {
      this.classEnhancerOptions.remove(optionName);
   }

   public byte[] enhance(String className, byte[] classdefinition, ClassLoader loader) {
      EnhancerClassLoader runtimeLoader = (EnhancerClassLoader)this.runtimeLoaderByLoader.get(loader);
      if (runtimeLoader == null) {
         runtimeLoader = new EnhancerClassLoader(loader);
         this.runtimeLoaderByLoader.put(loader, runtimeLoader);
      }

      this.clr.setPrimary(runtimeLoader);

      try {
         Class clazz = null;

         try {
            clazz = this.clr.classForName(className);
         } catch (ClassNotResolvedException e1) {
            DataNucleusEnhancer.LOGGER.debug(StringUtils.getStringFromStackTrace(e1));
            return null;
         }

         AbstractClassMetaData acmd = this.nucleusContext.getMetaDataManager().getMetaDataForClass(clazz, this.clr);
         if (acmd == null) {
            DataNucleusEnhancer.LOGGER.debug("Class " + className + " cannot be enhanced because no metadata has been found.");
            return null;
         } else {
            ClassEnhancer classEnhancer = new ClassEnhancerImpl((ClassMetaData)acmd, this.clr, this.nucleusContext.getMetaDataManager(), JDOEnhancementNamer.getInstance(), classdefinition);
            classEnhancer.setOptions(this.classEnhancerOptions);
            classEnhancer.enhance();
            return classEnhancer.getClassBytes();
         }
      } catch (Throwable ex) {
         DataNucleusEnhancer.LOGGER.error(StringUtils.getStringFromStackTrace(ex));
         return null;
      }
   }

   public static class EnhancerClassLoader extends ClassLoader {
      EnhancerClassLoader(ClassLoader loader) {
         super(loader);
      }

      protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
         Class c = super.findLoadedClass(name);
         if (c != null) {
            return c;
         } else if (name.startsWith("java.")) {
            return super.loadClass(name, resolve);
         } else if (name.startsWith("javax.")) {
            return super.loadClass(name, resolve);
         } else if (!name.startsWith("org.datanucleus.jpa.annotations") && !name.startsWith("org.datanucleus.api.jpa.annotations")) {
            String resource = name.replace('.', '/') + ".class";

            try {
               URL url = super.getResource(resource);
               if (url == null) {
                  throw new ClassNotFoundException(name);
               } else {
                  InputStream is = url.openStream();

                  Class var11;
                  try {
                     ByteArrayOutputStream os = new ByteArrayOutputStream();
                     byte[] b = new byte[2048];

                     int count;
                     while((count = is.read(b, 0, 2048)) != -1) {
                        os.write(b, 0, count);
                     }

                     byte[] bytes = os.toByteArray();
                     var11 = this.defineClass(name, bytes, 0, bytes.length);
                  } finally {
                     if (is != null) {
                        is.close();
                     }

                  }

                  return var11;
               }
            } catch (SecurityException var17) {
               return super.loadClass(name, resolve);
            } catch (IOException e) {
               throw new ClassNotFoundException(name, e);
            }
         } else {
            return super.loadClass(name, resolve);
         }
      }
   }
}
