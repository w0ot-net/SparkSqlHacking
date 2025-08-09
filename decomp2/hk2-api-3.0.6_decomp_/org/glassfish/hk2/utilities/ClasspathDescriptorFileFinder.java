package org.glassfish.hk2.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import org.glassfish.hk2.api.DescriptorFileFinder;
import org.glassfish.hk2.api.DescriptorFileFinderInformation;
import org.glassfish.hk2.utilities.reflection.Logger;

public class ClasspathDescriptorFileFinder implements DescriptorFileFinder, DescriptorFileFinderInformation {
   private static final String DEBUG_DESCRIPTOR_FINDER_PROPERTY = "org.jvnet.hk2.properties.debug.descriptor.file.finder";
   private static final boolean DEBUG_DESCRIPTOR_FINDER = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
      public Boolean run() {
         return Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.debug.descriptor.file.finder", "false"));
      }
   });
   private static final String DEFAULT_NAME = "default";
   private final ClassLoader classLoader;
   private final String[] names;
   private final List identifiers;
   private final String resourceBase;

   public ClasspathDescriptorFileFinder() {
      this(ClasspathDescriptorFileFinder.class.getClassLoader(), "default");
   }

   public ClasspathDescriptorFileFinder(ClassLoader cl) {
      this(cl, "default");
   }

   public ClasspathDescriptorFileFinder(ClassLoader cl, String... names) {
      this("META-INF/hk2-locator/", cl, names);
   }

   public ClasspathDescriptorFileFinder(String resourceBase, ClassLoader cl) {
      this(resourceBase, cl, "default");
   }

   private ClasspathDescriptorFileFinder(String resourceBase, ClassLoader cl, String... names) {
      this.identifiers = new ArrayList();
      this.resourceBase = resourceBase.endsWith("/") ? resourceBase : resourceBase + "/";
      this.classLoader = cl;
      this.names = names;
   }

   public List findDescriptorFiles() throws IOException {
      this.identifiers.clear();
      List<InputStream> returnList = new ArrayList();

      for(String name : this.names) {
         String resourceName = this.resourceBase + name;

         InputStream inputStream;
         for(Enumeration<URL> e = this.classLoader.getResources(resourceName); e.hasMoreElements(); returnList.add(inputStream)) {
            URL url = (URL)e.nextElement();
            if (DEBUG_DESCRIPTOR_FINDER) {
               Logger.getLogger().debug("Adding in URL to set being parsed: " + url + " from " + resourceName);
            }

            try {
               this.identifiers.add(url.toURI().toString());
            } catch (URISyntaxException e1) {
               throw new IOException(e1);
            }

            try {
               inputStream = url.openStream();
            } catch (IOException var12) {
               if (DEBUG_DESCRIPTOR_FINDER) {
                  Logger.getLogger().debug("IOException for url " + url, var12);
               }

               throw var12;
            } catch (Throwable var13) {
               if (DEBUG_DESCRIPTOR_FINDER) {
                  Logger.getLogger().debug("Unexpected exception for url " + url, var13);
               }

               throw new IOException(var13);
            }

            if (DEBUG_DESCRIPTOR_FINDER) {
               Logger.getLogger().debug("Input stream for: " + url + " from " + resourceName + " has succesfully been opened");
            }
         }
      }

      return returnList;
   }

   public List getDescriptorFileInformation() {
      return this.identifiers;
   }

   public String toString() {
      ClassLoader var10000 = this.classLoader;
      return "ClasspathDescriptorFileFinder(" + var10000 + "," + Arrays.toString(this.names) + "," + System.identityHashCode(this) + ")";
   }
}
