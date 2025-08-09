package org.apache.hive.beeline;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFrame;
import jline.console.completer.StringsCompleter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassNameCompleter extends StringsCompleter {
   private static final Logger LOG = LoggerFactory.getLogger(ClassNameCompleter.class.getName());
   public static final String clazzFileNameExtension = ".class";
   public static final String jarFileNameExtension = ".jar";

   public ClassNameCompleter(String... candidates) {
      super(candidates);
   }

   public static String[] getClassNames() throws IOException {
      Set urls = new HashSet();

      for(ClassLoader loader = Thread.currentThread().getContextClassLoader(); loader != null; loader = loader.getParent()) {
         if (loader instanceof URLClassLoader) {
            urls.addAll(Arrays.asList(((URLClassLoader)loader).getURLs()));
         }
      }

      Class[] systemClasses = new Class[]{String.class, JFrame.class};

      for(int i = 0; i < systemClasses.length; ++i) {
         URL classURL = systemClasses[i].getResource("/" + systemClasses[i].getName().replace('.', '/') + ".class");
         if (classURL != null) {
            URLConnection uc = classURL.openConnection();
            if (uc instanceof JarURLConnection) {
               urls.add(((JarURLConnection)uc).getJarFileURL());
            }
         }
      }

      Set classes = new HashSet();

      for(URL url : urls) {
         try {
            File file = new File(url.getFile());
            if (file.isDirectory()) {
               Set files = getClassFiles(file.getAbsolutePath(), new HashSet(), file, new int[]{200});
               classes.addAll(files);
            } else if (isJarFile(file)) {
               JarFile jf = new JarFile(file);
               Enumeration e = jf.entries();

               while(e.hasMoreElements()) {
                  JarEntry entry = (JarEntry)e.nextElement();
                  if (entry != null) {
                     String name = entry.getName();
                     if (isClazzFile(name)) {
                        classes.add(name);
                     } else if (isJarFile(name)) {
                        classes.addAll(getClassNamesFromJar(name));
                     }
                  }
               }
            }
         } catch (IOException e) {
            throw new IOException(String.format("Error reading classpath entry: %s", url), e);
         }
      }

      Set classNames = new TreeSet();

      for(String name : classes) {
         classNames.add(name.replace('/', '.').substring(0, name.length() - 6));
      }

      return (String[])classNames.toArray(new String[classNames.size()]);
   }

   private static Set getClassFiles(String root, Set holder, File directory, int[] maxDirectories) {
      int var10003 = maxDirectories[0];
      int var10000 = maxDirectories[0];
      maxDirectories[0] = var10003 - 1;
      if (var10000 < 0) {
         return holder;
      } else {
         File[] files = directory.listFiles();

         for(int i = 0; files != null && i < files.length; ++i) {
            String name = files[i].getAbsolutePath();
            if (name.startsWith(root)) {
               if (files[i].isDirectory()) {
                  getClassFiles(root, holder, files[i], maxDirectories);
               } else if (files[i].getName().endsWith(".class")) {
                  holder.add(files[i].getAbsolutePath().substring(root.length() + 1));
               }
            }
         }

         return holder;
      }
   }

   private static List getClassNamesFromJar(String path) {
      List<String> classNames = new ArrayList();
      ZipInputStream zip = null;

      try {
         zip = new ZipInputStream(new FileInputStream(path));

         for(ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
               StringBuilder className = new StringBuilder();

               for(String part : entry.getName().split("/")) {
                  if (className.length() != 0) {
                     className.append(".");
                  }

                  className.append(part);
                  if (part.endsWith(".class")) {
                     className.setLength(className.length() - ".class".length());
                  }
               }

               classNames.add(className.toString());
            }
         }
      } catch (IOException e) {
         LOG.error("Fail to parse the class name from the Jar file due to the exception:" + e);
      } finally {
         if (zip != null) {
            try {
               zip.close();
            } catch (IOException e) {
               LOG.error("Fail to close the file due to the exception:" + e);
            }
         }

      }

      return classNames;
   }

   private static boolean isJarFile(File file) {
      return file != null && file.isFile() && isJarFile(file.getName());
   }

   private static boolean isJarFile(String fileName) {
      return fileName.endsWith(".jar");
   }

   private static boolean isClazzFile(String clazzName) {
      return clazzName.endsWith(".class");
   }
}
