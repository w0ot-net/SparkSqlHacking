package scala.reflect.internal;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.jar.JarFile;

public final class JDK9Reflectors {
   private static final MethodHandle RUNTIME_VERSION_PARSE = lookupRuntimeVersionParse();
   private static final MethodHandle RUNTIME_VERSION = lookupRuntimeVersion();
   private static final MethodHandle RUNTIME_VERSION_MAJOR = lookupRuntimeVersionMajor();
   private static final MethodHandle NEW_JAR_FILE = lookupNewJarFile();

   public static Object runtimeVersionParse(String string) {
      try {
         return RUNTIME_VERSION_PARSE == null ? null : RUNTIME_VERSION_PARSE.invoke(string);
      } catch (Throwable var2) {
         return null;
      }
   }

   public static Object runtimeVersion() {
      try {
         return RUNTIME_VERSION == null ? null : RUNTIME_VERSION.invoke();
      } catch (Throwable var1) {
         return null;
      }
   }

   public static Integer runtimeVersionMajor(Object version) {
      try {
         return RUNTIME_VERSION_MAJOR == null ? null : RUNTIME_VERSION_MAJOR.invoke(version);
      } catch (Throwable var2) {
         return null;
      }
   }

   public static JarFile newJarFile(File file, boolean verify, int mode, Object version) throws IOException {
      try {
         if (version == null) {
            return new JarFile(file, verify, mode);
         } else {
            return NEW_JAR_FILE == null ? null : NEW_JAR_FILE.invoke(file, verify, mode, version);
         }
      } catch (IllegalArgumentException | SecurityException | IOException ex) {
         throw ex;
      } catch (Throwable t) {
         throw new RuntimeException(t);
      }
   }

   private static MethodHandle lookupRuntimeVersionParse() {
      try {
         return MethodHandles.lookup().findStatic(runtimeVersionClass(), "parse", MethodType.methodType(runtimeVersionClass(), String.class));
      } catch (Throwable var1) {
         return null;
      }
   }

   private static MethodHandle lookupRuntimeVersion() {
      try {
         return MethodHandles.lookup().findStatic(Runtime.class, "version", MethodType.methodType(runtimeVersionClass()));
      } catch (Throwable var1) {
         return null;
      }
   }

   private static MethodHandle lookupRuntimeVersionMajor() {
      try {
         return MethodHandles.lookup().findVirtual(runtimeVersionClass(), "major", MethodType.methodType(Integer.TYPE));
      } catch (Throwable var1) {
         return null;
      }
   }

   private static MethodHandle lookupNewJarFile() {
      try {
         return MethodHandles.lookup().findConstructor(JarFile.class, MethodType.methodType(Void.TYPE, File.class, Boolean.TYPE, Integer.TYPE, runtimeVersionClass()));
      } catch (Throwable var1) {
         return null;
      }
   }

   private static Class runtimeVersionClass() throws ClassNotFoundException {
      return Class.forName("java.lang.Runtime$Version");
   }
}
