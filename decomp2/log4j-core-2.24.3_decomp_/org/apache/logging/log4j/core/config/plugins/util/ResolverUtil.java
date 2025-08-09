package org.apache.logging.log4j.core.config.plugins.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Loader;
import org.apache.logging.log4j.status.StatusLogger;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;

public class ResolverUtil {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final String VFSZIP = "vfszip";
   private static final String VFS = "vfs";
   private static final String JAR = "jar";
   private static final String BUNDLE_RESOURCE = "bundleresource";
   private final Set classMatches = new HashSet();
   private final Set resourceMatches = new HashSet();
   private ClassLoader classloader;

   public Set getClasses() {
      return this.classMatches;
   }

   public Set getResources() {
      return this.resourceMatches;
   }

   public ClassLoader getClassLoader() {
      return this.classloader != null ? this.classloader : (this.classloader = Loader.getClassLoader(ResolverUtil.class, (Class)null));
   }

   public void setClassLoader(final ClassLoader aClassloader) {
      this.classloader = aClassloader;
   }

   public void find(final Test test, final String... packageNames) {
      if (packageNames != null) {
         for(String pkg : packageNames) {
            this.findInPackage(test, pkg);
         }

      }
   }

   @SuppressFBWarnings(
      value = {"URLCONNECTION_SSRF_FD", "PATH_TRAVERSAL_IN"},
      justification = "The URLs used come from the classloader."
   )
   public void findInPackage(final Test test, String packageName) {
      packageName = packageName.replace('.', '/');
      ClassLoader loader = this.getClassLoader();

      Enumeration<URL> urls;
      try {
         urls = loader.getResources(packageName);
      } catch (IOException ioe) {
         LOGGER.warn("Could not read package: {}", packageName, ioe);
         return;
      }

      while(urls.hasMoreElements()) {
         try {
            URL url = (URL)urls.nextElement();
            String urlPath = this.extractPath(url);
            LOGGER.debug("Scanning for classes in '{}' matching criteria {}", urlPath, test);
            if ("vfszip".equals(url.getProtocol())) {
               String path = urlPath.substring(0, urlPath.length() - packageName.length() - 2);
               URL newURL = new URL(url.getProtocol(), url.getHost(), path);
               JarInputStream stream = new JarInputStream(newURL.openStream());

               try {
                  this.loadImplementationsInJar(test, packageName, path, stream);
               } finally {
                  this.close(stream, newURL);
               }
            } else if (!"vfs".equals(url.getProtocol())) {
               if ("bundleresource".equals(url.getProtocol())) {
                  this.loadImplementationsInBundle(test, packageName);
               } else if ("jar".equals(url.getProtocol())) {
                  this.loadImplementationsInJar(test, packageName, url);
               } else {
                  File file = new File(urlPath);
                  if (file.isDirectory()) {
                     this.loadImplementationsInDirectory(test, packageName, file);
                  } else {
                     this.loadImplementationsInJar(test, packageName, file);
                  }
               }
            } else {
               String containerPath = urlPath.substring(1, urlPath.length() - packageName.length() - 2);
               File containerFile = new File(containerPath);
               if (containerFile.exists()) {
                  if (containerFile.isDirectory()) {
                     this.loadImplementationsInDirectory(test, packageName, new File(containerFile, packageName));
                  } else {
                     this.loadImplementationsInJar(test, packageName, containerFile);
                  }
               } else {
                  String path = urlPath.substring(0, urlPath.length() - packageName.length() - 2);
                  URL newURL = new URL(url.getProtocol(), url.getHost(), path);
                  InputStream is = newURL.openStream();

                  try {
                     JarInputStream jarStream;
                     if (is instanceof JarInputStream) {
                        jarStream = (JarInputStream)is;
                     } else {
                        jarStream = new JarInputStream(is);
                     }

                     this.loadImplementationsInJar(test, packageName, path, jarStream);
                  } catch (Throwable var22) {
                     if (is != null) {
                        try {
                           is.close();
                        } catch (Throwable var20) {
                           var22.addSuppressed(var20);
                        }
                     }

                     throw var22;
                  }

                  if (is != null) {
                     is.close();
                  }
               }
            }
         } catch (URISyntaxException | IOException ioe) {
            LOGGER.warn("Could not read entries", ioe);
         }
      }

   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The URLs used come from the classloader."
   )
   String extractPath(final URL url) throws UnsupportedEncodingException, URISyntaxException {
      String urlPath = url.getPath();
      if (urlPath.startsWith("jar:")) {
         urlPath = urlPath.substring(4);
      }

      if (urlPath.startsWith("file:")) {
         urlPath = urlPath.substring(5);
      }

      int bangIndex = urlPath.indexOf(33);
      if (bangIndex > 0) {
         urlPath = urlPath.substring(0, bangIndex);
      }

      String protocol = url.getProtocol();
      List<String> neverDecode = Arrays.asList("vfs", "vfszip", "bundleresource");
      if (neverDecode.contains(protocol)) {
         return urlPath;
      } else {
         String cleanPath = (new URI(urlPath)).getPath();
         return (new File(cleanPath)).exists() ? cleanPath : URLDecoder.decode(urlPath, StandardCharsets.UTF_8.name());
      }
   }

   private void loadImplementationsInBundle(final Test test, final String packageName) {
      BundleWiring wiring = (BundleWiring)FrameworkUtil.getBundle(ResolverUtil.class).adapt(BundleWiring.class);

      for(String name : wiring.listResources(packageName, "*.class", 1)) {
         this.addIfMatching(test, name);
      }

   }

   private void loadImplementationsInDirectory(final Test test, final String parent, final File location) {
      File[] files = location.listFiles();
      if (files != null) {
         for(File file : files) {
            StringBuilder builder = new StringBuilder();
            builder.append(parent).append('/').append(file.getName());
            String packageOrClass = parent == null ? file.getName() : builder.toString();
            if (file.isDirectory()) {
               this.loadImplementationsInDirectory(test, packageOrClass, file);
            } else if (this.isTestApplicable(test, file.getName())) {
               this.addIfMatching(test, packageOrClass);
            }
         }

      }
   }

   private boolean isTestApplicable(final Test test, final String path) {
      return test.doesMatchResource() || path.endsWith(".class") && test.doesMatchClass();
   }

   private void loadImplementationsInJar(final Test test, final String parent, final URL url) {
      JarURLConnection connection = null;

      try {
         connection = (JarURLConnection)url.openConnection();
         if (connection != null) {
            connection.setUseCaches(false);
            JarFile jarFile = connection.getJarFile();

            try {
               Enumeration<JarEntry> entries = jarFile.entries();

               while(entries.hasMoreElements()) {
                  JarEntry entry = (JarEntry)entries.nextElement();
                  String name = entry.getName();
                  if (!entry.isDirectory() && name.startsWith(parent) && this.isTestApplicable(test, name)) {
                     this.addIfMatching(test, name);
                  }
               }
            } catch (Throwable var10) {
               if (jarFile != null) {
                  try {
                     jarFile.close();
                  } catch (Throwable var9) {
                     var10.addSuppressed(var9);
                  }
               }

               throw var10;
            }

            if (jarFile != null) {
               jarFile.close();
            }
         } else {
            LOGGER.error("Could not establish connection to {}", url.toString());
         }
      } catch (IOException ex) {
         LOGGER.error("Could not search JAR file '{}' for classes matching criteria {}, file not found", url.toString(), test, ex);
      }

   }

   private void loadImplementationsInJar(final Test test, final String parent, final File jarFile) {
      JarInputStream jarStream = null;

      try {
         jarStream = new JarInputStream(new FileInputStream(jarFile));
         this.loadImplementationsInJar(test, parent, jarFile.getPath(), jarStream);
      } catch (IOException ex) {
         LOGGER.error("Could not search JAR file '{}' for classes matching criteria {}, file not found", jarFile, test, ex);
      } finally {
         this.close(jarStream, jarFile);
      }

   }

   private void close(final JarInputStream jarStream, final Object source) {
      if (jarStream != null) {
         try {
            jarStream.close();
         } catch (IOException e) {
            LOGGER.error("Error closing JAR file stream for {}", source, e);
         }
      }

   }

   private void loadImplementationsInJar(final Test test, final String parent, final String path, final JarInputStream stream) {
      JarEntry entry;
      try {
         while((entry = stream.getNextJarEntry()) != null) {
            String name = entry.getName();
            if (!entry.isDirectory() && name.startsWith(parent) && this.isTestApplicable(test, name)) {
               this.addIfMatching(test, name);
            }
         }
      } catch (IOException ioe) {
         LOGGER.error("Could not search JAR file '{}' for classes matching criteria {} due to an IOException", path, test, ioe);
      }

   }

   protected void addIfMatching(final Test test, final String fqn) {
      try {
         ClassLoader loader = this.getClassLoader();
         if (test.doesMatchClass()) {
            String externalName = fqn.substring(0, fqn.indexOf(46)).replace('/', '.');
            LOGGER.debug("Checking to see if class {} matches criteria {}", externalName, test);
            Class<?> type = loader.loadClass(externalName);
            if (test.matches(type)) {
               this.classMatches.add(type);
            }
         }

         if (test.doesMatchResource()) {
            URL url = loader.getResource(fqn);
            if (url == null) {
               url = loader.getResource(fqn.substring(1));
            }

            if (url != null && test.matches(url.toURI())) {
               this.resourceMatches.add(url.toURI());
            }
         }
      } catch (Throwable throwable) {
         LOGGER.debug("Could not scan class `{}`.", fqn, throwable);
      }

   }

   public interface Test {
      boolean matches(Class type);

      boolean matches(URI resource);

      boolean doesMatchClass();

      boolean doesMatchResource();
   }
}
