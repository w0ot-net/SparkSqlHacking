package org.apache.spark.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class ChildFirstURLClassLoader extends MutableURLClassLoader {
   private ParentClassLoader parent;

   public ChildFirstURLClassLoader(URL[] urls, ClassLoader parent) {
      super(urls, (ClassLoader)null);
      this.parent = new ParentClassLoader(parent);
   }

   public ChildFirstURLClassLoader(URL[] urls, ClassLoader parent, ClassLoader grandparent) {
      super(urls, grandparent);
      this.parent = new ParentClassLoader(parent);
   }

   public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
      try {
         return super.loadClass(name, resolve);
      } catch (ClassNotFoundException var4) {
         return this.parent.loadClass(name, resolve);
      }
   }

   public Enumeration getResources(String name) throws IOException {
      ArrayList<URL> urls = Collections.list(super.getResources(name));
      urls.addAll(Collections.list(this.parent.getResources(name)));
      return Collections.enumeration(urls);
   }

   public URL getResource(String name) {
      URL url = super.getResource(name);
      return url != null ? url : this.parent.getResource(name);
   }

   static {
      ClassLoader.registerAsParallelCapable();
   }
}
