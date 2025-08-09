package org.datanucleus;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public interface ClassLoaderResolver {
   Class classForName(String var1, ClassLoader var2);

   Class classForName(String var1, ClassLoader var2, boolean var3);

   Class classForName(String var1);

   Class classForName(String var1, boolean var2);

   boolean isAssignableFrom(String var1, Class var2);

   boolean isAssignableFrom(Class var1, String var2);

   boolean isAssignableFrom(String var1, String var2);

   Enumeration getResources(String var1, ClassLoader var2) throws IOException;

   URL getResource(String var1, ClassLoader var2);

   void setRuntimeClassLoader(ClassLoader var1);

   void registerUserClassLoader(ClassLoader var1);

   void setPrimary(ClassLoader var1);

   void unsetPrimary();
}
