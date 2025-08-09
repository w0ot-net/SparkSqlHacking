package org.glassfish.jersey.server.internal.scanning;

import [Ljava.lang.String;;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ReflectPermission;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.glassfish.jersey.internal.OsgiRegistry;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.server.internal.AbstractResourceFinderAdapter;
import org.glassfish.jersey.uri.UriComponent;
import org.glassfish.jersey.uri.UriComponent.Type;

public final class PackageNamesScanner extends AbstractResourceFinderAdapter {
   private final boolean recursive;
   private final String[] packages;
   private final ClassLoader classloader;
   private final Map finderFactories;
   private CompositeResourceFinder compositeResourceFinder;

   public PackageNamesScanner(String[] packages, boolean recursive) {
      this((ClassLoader)AccessController.doPrivileged(ReflectionHelper.getContextClassLoaderPA()), Tokenizer.tokenize(packages, " ,;\n"), recursive);
   }

   public PackageNamesScanner(ClassLoader classLoader, String[] packages, final boolean recursive) {
      this.recursive = recursive;
      this.packages = (String[])((String;)packages).clone();
      this.classloader = classLoader;
      this.finderFactories = new HashMap();
      this.add(new JarZipSchemeResourceFinderFactory());
      this.add(new FileSchemeResourceFinderFactory());
      this.add(new VfsSchemeResourceFinderFactory());
      this.add(new BundleSchemeResourceFinderFactory());
      final OsgiRegistry osgiRegistry = ReflectionHelper.getOsgiRegistryInstance();
      if (osgiRegistry != null) {
         setResourcesProvider(new ResourcesProvider() {
            public Enumeration getResources(String packagePath, ClassLoader classLoader) throws IOException {
               return osgiRegistry.getPackageResources(packagePath, classLoader, recursive);
            }
         });
      }

      this.init();
   }

   private void add(UriSchemeResourceFinderFactory uriSchemeResourceFinderFactory) {
      for(String scheme : uriSchemeResourceFinderFactory.getSchemes()) {
         this.finderFactories.put(scheme.toLowerCase(Locale.ROOT), uriSchemeResourceFinderFactory);
      }

   }

   public boolean hasNext() {
      return this.compositeResourceFinder.hasNext();
   }

   public String next() {
      return this.compositeResourceFinder.next();
   }

   public InputStream open() {
      return this.compositeResourceFinder.open();
   }

   public void close() {
      this.compositeResourceFinder.close();
   }

   public void reset() {
      this.close();
      this.init();
   }

   public ClassLoader getClassloader() {
      return this.classloader;
   }

   private void init() {
      this.compositeResourceFinder = new CompositeResourceFinder();

      for(String p : this.packages) {
         try {
            Enumeration<URL> urls = PackageNamesScanner.ResourcesProvider.getInstance().getResources(p.replace('.', '/'), this.classloader);

            while(urls.hasMoreElements()) {
               try {
                  this.addResourceFinder(this.toURI((URL)urls.nextElement()));
               } catch (URISyntaxException e) {
                  throw new ResourceFinderException("Error when converting a URL to a URI", e);
               }
            }
         } catch (IOException e) {
            throw new ResourceFinderException("IO error when package scanning jar", e);
         }
      }

   }

   public static void setResourcesProvider(ResourcesProvider provider) throws SecurityException {
      PackageNamesScanner.ResourcesProvider.setInstance(provider);
   }

   private void addResourceFinder(URI u) {
      UriSchemeResourceFinderFactory finderFactory = (UriSchemeResourceFinderFactory)this.finderFactories.get(u.getScheme().toLowerCase(Locale.ROOT));
      if (finderFactory != null) {
         this.compositeResourceFinder.push(finderFactory.create(u, this.recursive));
      } else {
         throw new ResourceFinderException("The URI scheme " + u.getScheme() + " of the URI " + u + " is not supported. Package scanning deployment is not supported for such URIs.\nTry using a different deployment mechanism such as explicitly declaring root resource and provider classes using an extension of jakarta.ws.rs.core.Application");
      }
   }

   private URI toURI(URL url) throws URISyntaxException {
      try {
         return url.toURI();
      } catch (URISyntaxException var3) {
         return URI.create(this.toExternalForm(url));
      }
   }

   private String toExternalForm(URL u) {
      int len = u.getProtocol().length() + 1;
      if (u.getAuthority() != null && u.getAuthority().length() > 0) {
         len += 2 + u.getAuthority().length();
      }

      if (u.getPath() != null) {
         len += u.getPath().length();
      }

      if (u.getQuery() != null) {
         len += 1 + u.getQuery().length();
      }

      if (u.getRef() != null) {
         len += 1 + u.getRef().length();
      }

      StringBuilder result = new StringBuilder(len);
      result.append(u.getProtocol());
      result.append(":");
      if (u.getAuthority() != null && u.getAuthority().length() > 0) {
         result.append("//");
         result.append(u.getAuthority());
      }

      if (u.getPath() != null) {
         result.append(UriComponent.contextualEncode(u.getPath(), Type.PATH));
      }

      if (u.getQuery() != null) {
         result.append('?');
         result.append(UriComponent.contextualEncode(u.getQuery(), Type.QUERY));
      }

      if (u.getRef() != null) {
         result.append("#");
         result.append(u.getRef());
      }

      return result.toString();
   }

   public abstract static class ResourcesProvider {
      private static volatile ResourcesProvider provider;

      private static ResourcesProvider getInstance() {
         ResourcesProvider result = provider;
         if (result == null) {
            synchronized(ResourcesProvider.class) {
               result = provider;
               if (result == null) {
                  provider = result = new ResourcesProvider() {
                     public Enumeration getResources(String name, ClassLoader cl) throws IOException {
                        return cl.getResources(name);
                     }
                  };
               }
            }
         }

         return result;
      }

      private static void setInstance(ResourcesProvider provider) throws SecurityException {
         SecurityManager security = System.getSecurityManager();
         if (security != null) {
            ReflectPermission rp = new ReflectPermission("suppressAccessChecks");
            security.checkPermission(rp);
         }

         synchronized(ResourcesProvider.class) {
            PackageNamesScanner.ResourcesProvider.provider = provider;
         }
      }

      public abstract Enumeration getResources(String var1, ClassLoader var2) throws IOException;
   }
}
