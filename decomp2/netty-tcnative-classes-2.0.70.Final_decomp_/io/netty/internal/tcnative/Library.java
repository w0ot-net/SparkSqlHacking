package io.netty.internal.tcnative;

import java.io.File;

public final class Library {
   private static final String[] NAMES = new String[]{"netty_tcnative", "libnetty_tcnative"};
   private static final String PROVIDED = "provided";
   private static Library _instance = null;

   private static void tryLoadClasses(ClassLoader classLoader, Class... classes) {
      for(Class clazz : classes) {
         tryLoadClass(classLoader, clazz.getName());
      }

   }

   private static void tryLoadClass(ClassLoader classLoader, String className) {
      try {
         Class.forName(className, true, classLoader);
      } catch (ClassNotFoundException var3) {
      } catch (SecurityException var4) {
      }

   }

   private Library() throws Exception {
      boolean loaded = false;
      String path = System.getProperty("java.library.path");
      String[] paths = path.split(File.pathSeparator);
      StringBuilder err = new StringBuilder();

      for(int i = 0; i < NAMES.length; ++i) {
         try {
            loadLibrary(NAMES[i]);
            loaded = true;
         } catch (ThreadDeath t) {
            throw t;
         } catch (VirtualMachineError t) {
            throw t;
         } catch (Throwable t) {
            String name = System.mapLibraryName(NAMES[i]);

            for(int j = 0; j < paths.length; ++j) {
               File fd = new File(paths[j], name);
               if (fd.exists()) {
                  throw new RuntimeException(t);
               }
            }

            if (i > 0) {
               err.append(", ");
            }

            err.append(t.getMessage());
         }

         if (loaded) {
            break;
         }
      }

      if (!loaded) {
         throw new UnsatisfiedLinkError(err.toString());
      }
   }

   private Library(String libraryName) {
      if (!"provided".equals(libraryName)) {
         loadLibrary(libraryName);
      }

   }

   private static void loadLibrary(String libraryName) {
      System.loadLibrary(calculatePackagePrefix().replace('.', '_') + libraryName);
   }

   private static String calculatePackagePrefix() {
      String maybeShaded = Library.class.getName();
      String expected = "io!netty!internal!tcnative!Library".replace('!', '.');
      if (!maybeShaded.endsWith(expected)) {
         throw new UnsatisfiedLinkError(String.format("Could not find prefix added to %s to get %s. When shading, only adding a package prefix is supported", expected, maybeShaded));
      } else {
         return maybeShaded.substring(0, maybeShaded.length() - expected.length());
      }
   }

   private static native boolean initialize0();

   private static native boolean aprHasThreads();

   private static native int aprMajorVersion();

   private static native String aprVersionString();

   public static boolean initialize() throws Exception {
      return initialize("provided", (String)null);
   }

   public static boolean initialize(String libraryName, String engine) throws Exception {
      if (_instance == null) {
         _instance = libraryName == null ? new Library() : new Library(libraryName);
         if (aprMajorVersion() < 1) {
            throw new UnsatisfiedLinkError("Unsupported APR Version (" + aprVersionString() + ")");
         }

         if (!aprHasThreads()) {
            throw new UnsatisfiedLinkError("Missing APR_HAS_THREADS");
         }
      }

      return initialize0() && SSL.initialize(engine) == 0;
   }

   static {
      tryLoadClasses(ClassLoader.getSystemClassLoader(), Exception.class, NullPointerException.class, IllegalArgumentException.class, OutOfMemoryError.class, String.class, byte[].class, SSLTask.class, CertificateCallbackTask.class, CertificateCallback.class, SSLPrivateKeyMethodTask.class, SSLPrivateKeyMethodSignTask.class, SSLPrivateKeyMethodDecryptTask.class);
   }
}
