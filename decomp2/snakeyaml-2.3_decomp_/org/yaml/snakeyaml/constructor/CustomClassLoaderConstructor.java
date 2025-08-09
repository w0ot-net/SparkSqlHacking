package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.LoaderOptions;

public class CustomClassLoaderConstructor extends Constructor {
   private final ClassLoader loader;

   public CustomClassLoaderConstructor(ClassLoader loader, LoaderOptions loadingConfig) {
      this(Object.class, loader, loadingConfig);
   }

   public CustomClassLoaderConstructor(Class theRoot, ClassLoader theLoader, LoaderOptions loadingConfig) {
      super(theRoot, loadingConfig);
      if (theLoader == null) {
         throw new NullPointerException("Loader must be provided.");
      } else {
         this.loader = theLoader;
      }
   }

   protected Class getClassForName(String name) throws ClassNotFoundException {
      return Class.forName(name, true, this.loader);
   }
}
