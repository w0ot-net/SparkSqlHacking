package jodd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;
import jodd.JoddCore;
import jodd.io.FileUtil;
import jodd.io.StreamUtil;

public class ClassLoaderUtil {
   private static final String[] MANIFESTS = new String[]{"Manifest.mf", "manifest.mf", "MANIFEST.MF"};

   public static ClassLoader getDefaultClassLoader() {
      ClassLoader cl = getContextClassLoader();
      if (cl == null) {
         Class callerClass = ReflectUtil.getCallerClass(2);
         cl = callerClass.getClassLoader();
      }

      return cl;
   }

   public static ClassLoader getContextClassLoader() {
      return Thread.currentThread().getContextClassLoader();
   }

   public static void setContextClassLoader(ClassLoader classLoader) {
      Thread.currentThread().setContextClassLoader(classLoader);
   }

   public static ClassLoader getSystemClassLoader() {
      return ClassLoader.getSystemClassLoader();
   }

   public static void addFileToClassPath(File path, ClassLoader classLoader) {
      try {
         addUrlToClassPath(FileUtil.toURL(path), classLoader);
      } catch (MalformedURLException muex) {
         throw new IllegalArgumentException("Invalid path: " + path, muex);
      }
   }

   public static void addUrlToClassPath(URL url, ClassLoader classLoader) {
      try {
         ReflectUtil.invokeDeclared(URLClassLoader.class, classLoader, "addURL", new Class[]{URL.class}, new Object[]{url});
      } catch (Exception ex) {
         throw new IllegalArgumentException("Add URL failed: " + url, ex);
      }
   }

   public static Class defineClass(String className, byte[] classData) {
      return defineClass(className, classData, getDefaultClassLoader());
   }

   public static Class defineClass(String className, byte[] classData, ClassLoader classLoader) {
      try {
         return (Class)ReflectUtil.invokeDeclared(ClassLoader.class, classLoader, "defineClass", new Class[]{String.class, byte[].class, Integer.TYPE, Integer.TYPE}, new Object[]{className, classData, 0, classData.length});
      } catch (Throwable th) {
         throw new RuntimeException("Define class failed: " + className, th);
      }
   }

   public static Class findClass(String className, File[] classPath, ClassLoader parent) {
      URL[] urls = new URL[classPath.length];

      for(int i = 0; i < classPath.length; ++i) {
         File file = classPath[i];

         try {
            urls[i] = FileUtil.toURL(file);
         } catch (MalformedURLException var7) {
         }
      }

      return findClass(className, (URL[])urls, (ClassLoader)null);
   }

   public static Class findClass(String className, URL[] classPath, ClassLoader parent) {
      URLClassLoader tempClassLoader = parent != null ? new URLClassLoader(classPath, parent) : new URLClassLoader(classPath);

      try {
         return (Class)ReflectUtil.invokeDeclared(URLClassLoader.class, tempClassLoader, "findClass", new Class[]{String.class}, new Object[]{className});
      } catch (Throwable th) {
         throw new RuntimeException("Class not found: " + className, th);
      }
   }

   public static File findToolsJar() {
      String tools = (new File(SystemUtil.getJavaHome())).getAbsolutePath() + File.separatorChar + "lib" + File.separatorChar + "tools.jar";
      File toolsFile = new File(tools);
      return toolsFile.exists() ? toolsFile : null;
   }

   public static Manifest getClasspathItemManifest(File classpathItem) {
      Manifest manifest = null;
      if (classpathItem.isFile()) {
         FileInputStream fis = null;

         try {
            fis = new FileInputStream(classpathItem);
            JarFile jar = new JarFile(classpathItem);
            manifest = jar.getManifest();
         } catch (IOException var21) {
         } finally {
            StreamUtil.close((InputStream)fis);
         }
      } else {
         File metaDir = new File(classpathItem, "META-INF");
         File manifestFile = null;
         if (metaDir.isDirectory()) {
            for(String m : MANIFESTS) {
               File mFile = new File(metaDir, m);
               if (mFile.isFile()) {
                  manifestFile = mFile;
                  break;
               }
            }
         }

         if (manifestFile != null) {
            FileInputStream fis = null;

            try {
               fis = new FileInputStream(manifestFile);
               manifest = new Manifest(fis);
            } catch (IOException var19) {
            } finally {
               StreamUtil.close((InputStream)fis);
            }
         }
      }

      return manifest;
   }

   public static String getClasspathItemBaseDir(File classpathItem) {
      String base;
      if (classpathItem.isFile()) {
         base = classpathItem.getParent();
      } else {
         base = classpathItem.toString();
      }

      return base;
   }

   public static File[] getDefaultClasspath() {
      return getDefaultClasspath(getDefaultClassLoader());
   }

   public static File[] getDefaultClasspath(ClassLoader classLoader) {
      Set<File> classpaths;
      for(classpaths = new TreeSet(); classLoader != null; classLoader = classLoader.getParent()) {
         if (classLoader instanceof URLClassLoader) {
            URL[] urls = ((URLClassLoader)classLoader).getURLs();

            for(URL u : urls) {
               File f = FileUtil.toFile(u);
               if (f != null && f.exists()) {
                  try {
                     f = f.getCanonicalFile();
                     boolean newElement = classpaths.add(f);
                     if (newElement) {
                        addInnerClasspathItems(classpaths, f);
                     }
                  } catch (IOException var11) {
                  }
               }
            }
         }
      }

      String bootstrap = SystemUtil.getSunBoothClassPath();
      if (bootstrap != null) {
         String[] bootstrapFiles = StringUtil.splitc(bootstrap, File.pathSeparatorChar);

         for(String bootstrapFile : bootstrapFiles) {
            File f = new File(bootstrapFile);
            if (f.exists()) {
               try {
                  f = f.getCanonicalFile();
                  boolean newElement = classpaths.add(f);
                  if (newElement) {
                     addInnerClasspathItems(classpaths, f);
                  }
               } catch (IOException var10) {
               }
            }
         }
      }

      File[] result = new File[classpaths.size()];
      return (File[])classpaths.toArray(result);
   }

   private static void addInnerClasspathItems(Set classpaths, File item) {
      Manifest manifest = getClasspathItemManifest(item);
      if (manifest != null) {
         Attributes attributes = manifest.getMainAttributes();
         if (attributes != null) {
            String s = attributes.getValue(Name.CLASS_PATH);
            if (s != null) {
               String base = getClasspathItemBaseDir(item);
               String[] tokens = StringUtil.splitc(s, ' ');

               for(String t : tokens) {
                  File file;
                  try {
                     file = new File(base, t);
                     file = file.getCanonicalFile();
                     if (!file.exists()) {
                        file = null;
                     }
                  } catch (Exception var15) {
                     file = null;
                  }

                  if (file == null) {
                     try {
                        file = new File(t);
                        file = file.getCanonicalFile();
                        if (!file.exists()) {
                           file = null;
                        }
                     } catch (Exception var14) {
                        file = null;
                     }
                  }

                  if (file == null) {
                     try {
                        URL url = new URL(t);
                        file = new File(url.getFile());
                        file = file.getCanonicalFile();
                        if (!file.exists()) {
                           file = null;
                        }
                     } catch (Exception var13) {
                        file = null;
                     }
                  }

                  if (file != null && file.exists()) {
                     classpaths.add(file);
                  }
               }

            }
         }
      }
   }

   public static URL getResourceUrl(String resourceName) {
      return getResourceUrl(resourceName, (ClassLoader)null);
   }

   public static URL getResourceUrl(String resourceName, ClassLoader classLoader) {
      if (resourceName.startsWith("/")) {
         resourceName = resourceName.substring(1);
      }

      if (classLoader != null) {
         URL resourceUrl = classLoader.getResource(resourceName);
         if (resourceUrl != null) {
            return resourceUrl;
         }
      }

      ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
      if (currentThreadClassLoader != null && currentThreadClassLoader != classLoader) {
         URL resourceUrl = currentThreadClassLoader.getResource(resourceName);
         if (resourceUrl != null) {
            return resourceUrl;
         }
      }

      Class callerClass = ReflectUtil.getCallerClass(2);
      ClassLoader callerClassLoader = callerClass.getClassLoader();
      if (callerClassLoader != classLoader && callerClassLoader != currentThreadClassLoader) {
         URL resourceUrl = callerClassLoader.getResource(resourceName);
         if (resourceUrl != null) {
            return resourceUrl;
         }
      }

      return null;
   }

   public static File getResourceFile(String resourceName) {
      return getResourceFile(resourceName, (ClassLoader)null);
   }

   public static File getResourceFile(String resourceName, ClassLoader classLoader) {
      try {
         return new File(getResourceUrl(resourceName, classLoader).toURI());
      } catch (URISyntaxException var3) {
         return null;
      }
   }

   public static InputStream getResourceAsStream(String resourceName) throws IOException {
      return getResourceAsStream(resourceName, (ClassLoader)null);
   }

   public static InputStream getResourceAsStream(String resourceName, ClassLoader callingClass) throws IOException {
      URL url = getResourceUrl(resourceName, callingClass);
      return url != null ? url.openStream() : null;
   }

   public static InputStream getClassAsStream(Class clazz) throws IOException {
      return getResourceAsStream(getClassFileName(clazz), clazz.getClassLoader());
   }

   public static InputStream getClassAsStream(String className) throws IOException {
      return getResourceAsStream(getClassFileName(className));
   }

   public static Class loadClass(String className) throws ClassNotFoundException {
      return JoddCore.classLoaderStrategy.loadClass(className, (ClassLoader)null);
   }

   public static Class loadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
      return JoddCore.classLoaderStrategy.loadClass(className, classLoader);
   }

   public static String getClassFileName(Class clazz) {
      if (clazz.isArray()) {
         clazz = clazz.getComponentType();
      }

      return getClassFileName(clazz.getName());
   }

   public static String getClassFileName(String className) {
      return className.replace('.', '/') + ".class";
   }
}
