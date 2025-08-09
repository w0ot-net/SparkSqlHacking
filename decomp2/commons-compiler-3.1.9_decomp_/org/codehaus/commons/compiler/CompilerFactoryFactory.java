package org.codehaus.commons.compiler;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import org.codehaus.commons.nullanalysis.Nullable;

public final class CompilerFactoryFactory {
   @Nullable
   private static ICompilerFactory defaultCompilerFactory;

   private CompilerFactoryFactory() {
   }

   /** @deprecated */
   @Deprecated
   public static ICompilerFactory getDefaultCompilerFactory() throws Exception {
      return getDefaultCompilerFactory(Thread.currentThread().getContextClassLoader());
   }

   public static ICompilerFactory getDefaultCompilerFactory(ClassLoader classLoader) throws Exception {
      if (defaultCompilerFactory != null) {
         return defaultCompilerFactory;
      } else {
         InputStream is = classLoader.getResourceAsStream("org.codehaus.commons.compiler.properties");
         if (is == null) {
            throw new ClassNotFoundException("No implementation of org.codehaus.commons.compiler could be loaded. Typically, you'd have  \"janino.jar\", or \"commons-compiler-jdk.jar\", or both on the classpath, and use the \"ClassLoader.getSystemClassLoader\" to load them.");
         } else {
            Properties properties;
            try {
               properties = new Properties();
               properties.load(is);
            } finally {
               is.close();
            }

            String compilerFactoryClassName = properties.getProperty("compilerFactory");
            return defaultCompilerFactory = getCompilerFactory(compilerFactoryClassName, classLoader);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static ICompilerFactory[] getAllCompilerFactories() throws Exception {
      return getAllCompilerFactories(Thread.currentThread().getContextClassLoader());
   }

   public static ICompilerFactory[] getAllCompilerFactories(ClassLoader classLoader) throws Exception {
      List<ICompilerFactory> factories = new ArrayList();
      Enumeration<URL> en = classLoader.getResources("org.codehaus.commons.compiler.properties");

      while(en.hasMoreElements()) {
         URL url = (URL)en.nextElement();
         Properties properties = new Properties();
         InputStream is = url.openStream();

         try {
            properties.load(is);
         } finally {
            is.close();
         }

         String compilerFactoryClassName = properties.getProperty("compilerFactory");
         if (compilerFactoryClassName == null) {
            throw new IllegalStateException(url.toString() + " does not specify the 'compilerFactory' property");
         }

         factories.add(getCompilerFactory(compilerFactoryClassName, classLoader));
      }

      return (ICompilerFactory[])factories.toArray(new ICompilerFactory[factories.size()]);
   }

   /** @deprecated */
   @Deprecated
   public static ICompilerFactory getCompilerFactory(String compilerFactoryClassName) throws Exception {
      return getCompilerFactory(compilerFactoryClassName, Thread.currentThread().getContextClassLoader());
   }

   public static ICompilerFactory getCompilerFactory(String compilerFactoryClassName, ClassLoader classLoader) throws Exception {
      return (ICompilerFactory)classLoader.loadClass(compilerFactoryClassName).newInstance();
   }

   public static String getSpecificationVersion() {
      return CompilerFactoryFactory.class.getPackage().getSpecificationVersion();
   }
}
