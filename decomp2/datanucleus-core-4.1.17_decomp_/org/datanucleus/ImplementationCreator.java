package org.datanucleus;

public interface ImplementationCreator {
   Object newInstance(Class var1, ClassLoaderResolver var2);

   ClassLoader getClassLoader();
}
