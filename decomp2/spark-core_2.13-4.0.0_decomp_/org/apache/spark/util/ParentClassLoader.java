package org.apache.spark.util;

public class ParentClassLoader extends ClassLoader {
   public ParentClassLoader(ClassLoader parent) {
      super(parent);
   }

   public Class findClass(String name) throws ClassNotFoundException {
      return super.findClass(name);
   }

   public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
      return super.loadClass(name, resolve);
   }

   static {
      ClassLoader.registerAsParallelCapable();
   }
}
