package org.apache.spark.util;

import java.net.URL;
import java.net.URLClassLoader;

public class MutableURLClassLoader extends URLClassLoader {
   public MutableURLClassLoader(URL[] urls, ClassLoader parent) {
      super(urls, parent);
   }

   public void addURL(URL url) {
      super.addURL(url);
   }

   static {
      ClassLoader.registerAsParallelCapable();
   }
}
