package org.datanucleus.plugin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public interface PluginRegistry {
   ExtensionPoint getExtensionPoint(String var1);

   ExtensionPoint[] getExtensionPoints();

   void registerExtensionPoints();

   void registerExtensions();

   Object createExecutableExtension(ConfigurationElement var1, String var2, Class[] var3, Object[] var4) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException;

   Class loadClass(String var1, String var2) throws ClassNotFoundException;

   URL resolveURLAsFileURL(URL var1) throws IOException;

   void resolveConstraints();

   Bundle[] getBundles();
}
