package io.vertx.core.impl.verticle;

import io.vertx.core.net.impl.URIDecoder;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

public class PackageHelper {
   private static final String CLASS_FILE = ".class";
   private final ClassLoader classLoader;

   public PackageHelper(ClassLoader classLoader) {
      this.classLoader = classLoader;
   }

   public List find(String packageName) throws IOException {
      String javaPackageName = packageName.replaceAll("\\.", "/");
      List<JavaFileObject> result = new ArrayList();
      Enumeration<URL> urlEnumeration = this.classLoader.getResources(javaPackageName);

      while(urlEnumeration.hasMoreElements()) {
         URL resource = (URL)urlEnumeration.nextElement();
         File directory = new File(URIDecoder.decodeURIComponent(resource.getFile(), false));
         if (directory.isDirectory()) {
            result.addAll(browseDir(packageName, directory));
         } else {
            result.addAll(browseJar(resource));
         }
      }

      return result;
   }

   private static List browseDir(String packageName, File directory) {
      List<JavaFileObject> result = new ArrayList();

      for(File childFile : directory.listFiles()) {
         if (childFile.isFile() && childFile.getName().endsWith(".class")) {
            String binaryName = packageName + "." + childFile.getName().replaceAll(".class$", "");
            result.add(new CustomJavaFileObject(childFile.toURI(), Kind.CLASS, binaryName));
         }
      }

      return result;
   }

   private static List browseJar(URL packageFolderURL) {
      List<JavaFileObject> result = new ArrayList();

      try {
         String jarUri = packageFolderURL.toExternalForm().split("!")[0];
         JarURLConnection jarConn = (JarURLConnection)packageFolderURL.openConnection();
         String rootEntryName = jarConn.getEntryName();
         int rootEnd = rootEntryName.length() + 1;
         Enumeration<JarEntry> entryEnum = jarConn.getJarFile().entries();

         while(entryEnum.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry)entryEnum.nextElement();
            String name = jarEntry.getName();
            if (name.startsWith(rootEntryName) && name.indexOf(47, rootEnd) == -1 && name.endsWith(".class")) {
               String binaryName = name.replaceAll("/", ".").replaceAll(".class$", "");
               result.add(new CustomJavaFileObject(URI.create(jarUri + "!/" + name), Kind.CLASS, binaryName));
            }
         }

         return result;
      } catch (Exception e) {
         throw new RuntimeException(packageFolderURL + " is not a JAR file", e);
      }
   }
}
