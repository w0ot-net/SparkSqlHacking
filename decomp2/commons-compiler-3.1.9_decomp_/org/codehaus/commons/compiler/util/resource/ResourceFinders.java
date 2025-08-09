package org.codehaus.commons.compiler.util.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.commons.compiler.lang.ClassLoaders;
import org.codehaus.commons.nullanalysis.Nullable;

public final class ResourceFinders {
   private ResourceFinders() {
   }

   public static ListableResourceFinder fromClassLoader(final ClassLoader classLoader) {
      return new ListableResourceFinder() {
         @Nullable
         public Resource findResource(String resourceName) {
            if (resourceName.startsWith("/")) {
               resourceName = resourceName.substring(1);
            }

            URL url = classLoader.getResource(resourceName);
            return url == null ? null : ResourceFinders.resourceFromUrl(url, resourceName);
         }

         @Nullable
         public Iterable list(String resourceNamePrefix, boolean recurse) throws IOException {
            Map<String, URL> allSubresources = ClassLoaders.getSubresources(classLoader, resourceNamePrefix, false, recurse);
            Collection<Resource> result = new ArrayList(allSubresources.size());

            for(Map.Entry e : allSubresources.entrySet()) {
               String name = (String)e.getKey();
               URL url = (URL)e.getValue();
               if (name.endsWith(".class")) {
                  result.add(ResourceFinders.resourceFromUrl(url, name));
               }
            }

            return result;
         }
      };
   }

   public static ResourceFinder debugResourceFinder(final ResourceFinder delegate) {
      return delegate instanceof ListableResourceFinder ? debugListableResourceFinder((ListableResourceFinder)delegate) : new ResourceFinder() {
         @Nullable
         public Resource findResource(String resourceName) {
            Resource result = delegate.findResource(resourceName);
            System.err.println("findResource(\"" + resourceName + "\") => " + result);
            return result;
         }
      };
   }

   private static ResourceFinder debugListableResourceFinder(final ListableResourceFinder delegate) {
      return new ListableResourceFinder() {
         @Nullable
         public Resource findResource(String resourceName) {
            Resource result = delegate.findResource(resourceName);
            System.err.println("findResource(\"" + resourceName + "\") => " + result);
            return result;
         }

         @Nullable
         public Iterable list(String resourceNamePrefix, boolean recurse) throws IOException {
            Iterable<Resource> result = delegate.list(resourceNamePrefix, recurse);
            System.err.println("list(\"" + resourceNamePrefix + "\", " + recurse + ") => " + ResourceFinders.toString(result));
            return result;
         }
      };
   }

   private static String toString(Iterable i) {
      Iterator<T> it = i.iterator();
      if (!it.hasNext()) {
         return "[]";
      } else {
         StringBuilder sb = (new StringBuilder("[ ")).append(it.next().toString());

         while(it.hasNext()) {
            sb.append(", ").append(it.next());
         }

         return sb.append(" ]").toString();
      }
   }

   private static LocatableResource resourceFromUrl(final URL url, final String resourceName) {
      return new LocatableResource() {
         public URL getLocation() throws IOException {
            return url;
         }

         public InputStream open() throws IOException {
            return url.openStream();
         }

         public String getFileName() {
            return resourceName;
         }

         public long lastModified() {
            return 0L;
         }
      };
   }
}
