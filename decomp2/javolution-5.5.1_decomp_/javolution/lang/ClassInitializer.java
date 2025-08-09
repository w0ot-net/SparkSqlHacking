package javolution.lang;

import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javolution.context.LogContext;

public class ClassInitializer {
   private ClassInitializer() {
   }

   public static void initializeAll() {
      initializeRuntime();
      initializeClassPath();
   }

   public static void initializeRuntime() {
      String bootPath = System.getProperty("sun.boot.class.path");
      String pathSeparator = System.getProperty("path.separator");
      if (bootPath != null && pathSeparator != null) {
         initialize(bootPath, pathSeparator);
         String javaHome = System.getProperty("java.home");
         String fileSeparator = System.getProperty("file.separator");
         if (javaHome != null && fileSeparator != null) {
            File extDir = new File(javaHome + fileSeparator + "lib" + fileSeparator + "ext");
            if (!extDir.getClass().getName().equals("java.io.File")) {
               LogContext.warning((CharSequence)"Extension classes initialization not supported for J2ME build");
            } else {
               if (extDir.isDirectory()) {
                  File[] files = extDir.listFiles();

                  for(int i = 0; i < files.length; ++i) {
                     String path = files[i].getPath();
                     if (path.endsWith(".jar") || path.endsWith(".zip")) {
                        initializeJar(path);
                     }
                  }
               } else {
                  LogContext.warning((CharSequence)(extDir + " is not a directory"));
               }

            }
         } else {
            LogContext.warning((CharSequence)"Cannot initialize extension library through system properties");
         }
      } else {
         LogContext.warning((CharSequence)"Cannot initialize boot path through system properties");
      }
   }

   public static void initializeClassPath() {
      String classPath = System.getProperty("java.class.path");
      String pathSeparator = System.getProperty("path.separator");
      if (classPath != null && pathSeparator != null) {
         initialize(classPath, pathSeparator);
      } else {
         LogContext.warning((CharSequence)"Cannot initialize classpath through system properties");
      }
   }

   private static void initialize(String classPath, String pathSeparator) {
      LogContext.info((CharSequence)("Initialize classpath: " + classPath));

      while(classPath.length() > 0) {
         int index = classPath.indexOf(pathSeparator);
         String name;
         if (index < 0) {
            name = classPath;
            classPath = "";
         } else {
            name = classPath.substring(0, index);
            classPath = classPath.substring(index + pathSeparator.length());
         }

         if (!name.endsWith(".jar") && !name.endsWith(".zip")) {
            initializeDir(name);
         } else {
            initializeJar(name);
         }
      }

   }

   public static void initialize(Class cls) {
      try {
         Class.forName(cls.getName(), true, cls.getClassLoader());
      } catch (ClassNotFoundException e) {
         LogContext.error((Throwable)e);
      }
   }

   public static void initialize(String className) {
      try {
         Class cls = Reflection.getInstance().getClass(className);
         if (cls == null) {
            LogContext.warning((CharSequence)("Class + " + className + " not found"));
         }
      } catch (Throwable error) {
         LogContext.error(error);
      }

   }

   public static void initializeJar(String jarName) {
      try {
         LogContext.info((CharSequence)("Initialize Jar file: " + jarName));
         ZipFile jarFile = new ZipFile(jarName);
         if (!jarFile.getClass().getName().equals("java.util.zip.ZipFile")) {
            LogContext.warning((CharSequence)"Initialization of classes in jar file not supported for J2ME build");
            return;
         }

         Enumeration e = jarFile.entries();

         while(e.hasMoreElements()) {
            ZipEntry entry = (ZipEntry)e.nextElement();
            String entryName = entry.getName();
            if (entryName.endsWith(".class")) {
               String className = entryName.substring(0, entryName.length() - 6);
               className = className.replace('/', '.');
               initialize(className);
            }
         }
      } catch (Exception e) {
         LogContext.error((Throwable)e);
      }

   }

   public static void initializeDir(String dirName) {
      LogContext.info((CharSequence)("Initialize Directory: " + dirName));
      File file = new File(dirName);
      if (!file.getClass().getName().equals("java.io.File")) {
         LogContext.warning((CharSequence)"Initialization of classes in directory not supported for J2ME build");
      } else {
         if (file.isDirectory()) {
            File[] files = file.listFiles();

            for(int i = 0; i < files.length; ++i) {
               initialize("", files[i]);
            }
         } else {
            LogContext.warning((CharSequence)(dirName + " is not a directory"));
         }

      }
   }

   private static void initialize(String prefix, File file) {
      String name = file.getName();
      if (file.isDirectory()) {
         File[] files = file.listFiles();
         String newPrefix = prefix.length() == 0 ? name : prefix + "." + name;

         for(int i = 0; i < files.length; ++i) {
            initialize(newPrefix, files[i]);
         }
      } else if (name.endsWith(".class")) {
         String className = prefix + "." + name.substring(0, name.length() - 6);
         initialize(className);
      }

   }
}
