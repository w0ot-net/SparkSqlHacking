package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class ClassPath {
   private static final Logger logger = Logger.getLogger(ClassPath.class.getName());
   private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(" ").omitEmptyStrings();
   private static final String CLASS_FILE_NAME_EXTENSION = ".class";
   private final ImmutableSet resources;

   private ClassPath(ImmutableSet resources) {
      this.resources = resources;
   }

   public static ClassPath from(ClassLoader classloader) throws IOException {
      ImmutableSet<LocationInfo> locations = locationsFrom(classloader);
      Set<File> scanned = new HashSet();

      for(LocationInfo location : locations) {
         scanned.add(location.file());
      }

      ImmutableSet.Builder<ResourceInfo> builder = ImmutableSet.builder();

      for(LocationInfo location : locations) {
         builder.addAll((Iterable)location.scanResources(scanned));
      }

      return new ClassPath(builder.build());
   }

   public ImmutableSet getResources() {
      return this.resources;
   }

   public ImmutableSet getAllClasses() {
      return FluentIterable.from((Iterable)this.resources).filter(ClassInfo.class).toSet();
   }

   public ImmutableSet getTopLevelClasses() {
      return FluentIterable.from((Iterable)this.resources).filter(ClassInfo.class).filter(ClassInfo::isTopLevel).toSet();
   }

   public ImmutableSet getTopLevelClasses(String packageName) {
      Preconditions.checkNotNull(packageName);
      ImmutableSet.Builder<ClassInfo> builder = ImmutableSet.builder();

      for(ClassInfo classInfo : this.getTopLevelClasses()) {
         if (classInfo.getPackageName().equals(packageName)) {
            builder.add((Object)classInfo);
         }
      }

      return builder.build();
   }

   public ImmutableSet getTopLevelClassesRecursive(String packageName) {
      Preconditions.checkNotNull(packageName);
      String packagePrefix = packageName + '.';
      ImmutableSet.Builder<ClassInfo> builder = ImmutableSet.builder();

      for(ClassInfo classInfo : this.getTopLevelClasses()) {
         if (classInfo.getName().startsWith(packagePrefix)) {
            builder.add((Object)classInfo);
         }
      }

      return builder.build();
   }

   static ImmutableSet locationsFrom(ClassLoader classloader) {
      ImmutableSet.Builder<LocationInfo> builder = ImmutableSet.builder();

      for(Map.Entry entry : getClassPathEntries(classloader).entrySet()) {
         builder.add((Object)(new LocationInfo((File)entry.getKey(), (ClassLoader)entry.getValue())));
      }

      return builder.build();
   }

   @VisibleForTesting
   static ImmutableSet getClassPathFromManifest(File jarFile, @CheckForNull Manifest manifest) {
      if (manifest == null) {
         return ImmutableSet.of();
      } else {
         ImmutableSet.Builder<File> builder = ImmutableSet.builder();
         String classpathAttribute = manifest.getMainAttributes().getValue(Name.CLASS_PATH.toString());
         if (classpathAttribute != null) {
            for(String path : CLASS_PATH_ATTRIBUTE_SEPARATOR.split(classpathAttribute)) {
               URL url;
               try {
                  url = getClassPathEntry(jarFile, path);
               } catch (MalformedURLException var8) {
                  logger.warning("Invalid Class-Path entry: " + path);
                  continue;
               }

               if (url.getProtocol().equals("file")) {
                  builder.add((Object)toFile(url));
               }
            }
         }

         return builder.build();
      }
   }

   @VisibleForTesting
   static ImmutableMap getClassPathEntries(ClassLoader classloader) {
      LinkedHashMap<File, ClassLoader> entries = Maps.newLinkedHashMap();
      ClassLoader parent = classloader.getParent();
      if (parent != null) {
         entries.putAll(getClassPathEntries(parent));
      }

      for(URL url : getClassLoaderUrls(classloader)) {
         if (url.getProtocol().equals("file")) {
            File file = toFile(url);
            if (!entries.containsKey(file)) {
               entries.put(file, classloader);
            }
         }
      }

      return ImmutableMap.copyOf((Map)entries);
   }

   private static ImmutableList getClassLoaderUrls(ClassLoader classloader) {
      if (classloader instanceof URLClassLoader) {
         return ImmutableList.copyOf((Object[])((URLClassLoader)classloader).getURLs());
      } else {
         return classloader.equals(ClassLoader.getSystemClassLoader()) ? parseJavaClassPath() : ImmutableList.of();
      }
   }

   @VisibleForTesting
   static ImmutableList parseJavaClassPath() {
      ImmutableList.Builder<URL> urls = ImmutableList.builder();

      for(String entry : Splitter.on(StandardSystemProperty.PATH_SEPARATOR.value()).split(StandardSystemProperty.JAVA_CLASS_PATH.value())) {
         try {
            try {
               urls.add((Object)(new File(entry)).toURI().toURL());
            } catch (SecurityException var4) {
               urls.add((Object)(new URL("file", (String)null, (new File(entry)).getAbsolutePath())));
            }
         } catch (MalformedURLException e) {
            logger.log(Level.WARNING, "malformed classpath entry: " + entry, e);
         }
      }

      return urls.build();
   }

   @VisibleForTesting
   static URL getClassPathEntry(File jarFile, String path) throws MalformedURLException {
      return new URL(jarFile.toURI().toURL(), path);
   }

   @VisibleForTesting
   static String getClassName(String filename) {
      int classNameEnd = filename.length() - ".class".length();
      return filename.substring(0, classNameEnd).replace('/', '.');
   }

   @VisibleForTesting
   static File toFile(URL url) {
      Preconditions.checkArgument(url.getProtocol().equals("file"));

      try {
         return new File(url.toURI());
      } catch (URISyntaxException var2) {
         return new File(url.getPath());
      }
   }

   public static class ResourceInfo {
      private final File file;
      private final String resourceName;
      final ClassLoader loader;

      static ResourceInfo of(File file, String resourceName, ClassLoader loader) {
         return (ResourceInfo)(resourceName.endsWith(".class") ? new ClassInfo(file, resourceName, loader) : new ResourceInfo(file, resourceName, loader));
      }

      ResourceInfo(File file, String resourceName, ClassLoader loader) {
         this.file = (File)Preconditions.checkNotNull(file);
         this.resourceName = (String)Preconditions.checkNotNull(resourceName);
         this.loader = (ClassLoader)Preconditions.checkNotNull(loader);
      }

      public final URL url() {
         URL url = this.loader.getResource(this.resourceName);
         if (url == null) {
            throw new NoSuchElementException(this.resourceName);
         } else {
            return url;
         }
      }

      public final ByteSource asByteSource() {
         return Resources.asByteSource(this.url());
      }

      public final CharSource asCharSource(Charset charset) {
         return Resources.asCharSource(this.url(), charset);
      }

      public final String getResourceName() {
         return this.resourceName;
      }

      final File getFile() {
         return this.file;
      }

      public int hashCode() {
         return this.resourceName.hashCode();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (!(obj instanceof ResourceInfo)) {
            return false;
         } else {
            ResourceInfo that = (ResourceInfo)obj;
            return this.resourceName.equals(that.resourceName) && this.loader == that.loader;
         }
      }

      public String toString() {
         return this.resourceName;
      }
   }

   public static final class ClassInfo extends ResourceInfo {
      private final String className;

      ClassInfo(File file, String resourceName, ClassLoader loader) {
         super(file, resourceName, loader);
         this.className = ClassPath.getClassName(resourceName);
      }

      public String getPackageName() {
         return Reflection.getPackageName(this.className);
      }

      public String getSimpleName() {
         int lastDollarSign = this.className.lastIndexOf(36);
         if (lastDollarSign != -1) {
            String innerClassName = this.className.substring(lastDollarSign + 1);
            return CharMatcher.inRange('0', '9').trimLeadingFrom(innerClassName);
         } else {
            String packageName = this.getPackageName();
            return packageName.isEmpty() ? this.className : this.className.substring(packageName.length() + 1);
         }
      }

      public String getName() {
         return this.className;
      }

      public boolean isTopLevel() {
         return this.className.indexOf(36) == -1;
      }

      public Class load() {
         try {
            return this.loader.loadClass(this.className);
         } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
         }
      }

      public String toString() {
         return this.className;
      }
   }

   static final class LocationInfo {
      final File home;
      private final ClassLoader classloader;

      LocationInfo(File home, ClassLoader classloader) {
         this.home = (File)Preconditions.checkNotNull(home);
         this.classloader = (ClassLoader)Preconditions.checkNotNull(classloader);
      }

      public final File file() {
         return this.home;
      }

      public ImmutableSet scanResources() throws IOException {
         return this.scanResources(new HashSet());
      }

      public ImmutableSet scanResources(Set scannedFiles) throws IOException {
         ImmutableSet.Builder<ResourceInfo> builder = ImmutableSet.builder();
         scannedFiles.add(this.home);
         this.scan(this.home, scannedFiles, builder);
         return builder.build();
      }

      private void scan(File file, Set scannedUris, ImmutableSet.Builder builder) throws IOException {
         try {
            if (!file.exists()) {
               return;
            }
         } catch (SecurityException e) {
            ClassPath.logger.warning("Cannot access " + file + ": " + e);
            return;
         }

         if (file.isDirectory()) {
            this.scanDirectory(file, builder);
         } else {
            this.scanJar(file, scannedUris, builder);
         }

      }

      private void scanJar(File file, Set scannedUris, ImmutableSet.Builder builder) throws IOException {
         JarFile jarFile;
         try {
            jarFile = new JarFile(file);
         } catch (IOException var14) {
            return;
         }

         try {
            for(File path : ClassPath.getClassPathFromManifest(file, jarFile.getManifest())) {
               if (scannedUris.add(path.getCanonicalFile())) {
                  this.scan(path, scannedUris, builder);
               }
            }

            this.scanJarFile(jarFile, builder);
         } finally {
            try {
               jarFile.close();
            } catch (IOException var13) {
            }

         }

      }

      private void scanJarFile(JarFile file, ImmutableSet.Builder builder) {
         Enumeration<JarEntry> entries = file.entries();

         while(entries.hasMoreElements()) {
            JarEntry entry = (JarEntry)entries.nextElement();
            if (!entry.isDirectory() && !entry.getName().equals("META-INF/MANIFEST.MF")) {
               builder.add((Object)ClassPath.ResourceInfo.of(new File(file.getName()), entry.getName(), this.classloader));
            }
         }

      }

      private void scanDirectory(File directory, ImmutableSet.Builder builder) throws IOException {
         Set<File> currentPath = new HashSet();
         currentPath.add(directory.getCanonicalFile());
         this.scanDirectory(directory, "", currentPath, builder);
      }

      private void scanDirectory(File directory, String packagePrefix, Set currentPath, ImmutableSet.Builder builder) throws IOException {
         File[] files = directory.listFiles();
         if (files == null) {
            ClassPath.logger.warning("Cannot read directory " + directory);
         } else {
            for(File f : files) {
               String name = f.getName();
               if (f.isDirectory()) {
                  File deref = f.getCanonicalFile();
                  if (currentPath.add(deref)) {
                     this.scanDirectory(deref, packagePrefix + name + "/", currentPath, builder);
                     currentPath.remove(deref);
                  }
               } else {
                  String resourceName = packagePrefix + name;
                  if (!resourceName.equals("META-INF/MANIFEST.MF")) {
                     builder.add((Object)ClassPath.ResourceInfo.of(f, resourceName, this.classloader));
                  }
               }
            }

         }
      }

      public boolean equals(@CheckForNull Object obj) {
         if (!(obj instanceof LocationInfo)) {
            return false;
         } else {
            LocationInfo that = (LocationInfo)obj;
            return this.home.equals(that.home) && this.classloader.equals(that.classloader);
         }
      }

      public int hashCode() {
         return this.home.hashCode();
      }

      public String toString() {
         return this.home.toString();
      }
   }
}
