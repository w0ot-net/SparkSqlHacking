package org.rocksdb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.rocksdb.util.Environment;

public class NativeLibraryLoader {
   private static final NativeLibraryLoader instance = new NativeLibraryLoader();
   private static boolean initialized = false;
   private static final String ROCKSDB_LIBRARY_NAME = "rocksdb";
   private static final String sharedLibraryName = Environment.getSharedLibraryName("rocksdb");
   private static final String jniLibraryName = Environment.getJniLibraryName("rocksdb");
   private static final String fallbackJniLibraryName = Environment.getFallbackJniLibraryName("rocksdb");
   private static final String jniLibraryFileName = Environment.getJniLibraryFileName("rocksdb");
   private static final String fallbackJniLibraryFileName = Environment.getFallbackJniLibraryFileName("rocksdb");
   private static final String tempFilePrefix = "librocksdbjni";
   private static final String tempFileSuffix = Environment.getJniLibraryExtension();

   public static NativeLibraryLoader getInstance() {
      return instance;
   }

   public synchronized void loadLibrary(String var1) throws IOException {
      try {
         System.loadLibrary(sharedLibraryName);
      } catch (UnsatisfiedLinkError var5) {
         try {
            System.loadLibrary(jniLibraryName);
         } catch (UnsatisfiedLinkError var4) {
            if (fallbackJniLibraryName != null) {
               try {
                  System.loadLibrary(fallbackJniLibraryName);
                  return;
               } catch (UnsatisfiedLinkError var3) {
               }
            }

            this.loadLibraryFromJar(var1);
         }
      }
   }

   void loadLibraryFromJar(String var1) throws IOException {
      if (!initialized) {
         System.load(this.loadLibraryFromJarToTemp(var1).getAbsolutePath());
         initialized = true;
      }

   }

   private File createTemp(String var1, String var2) throws IOException {
      File var3;
      if (var1 != null && !var1.isEmpty()) {
         File var4 = new File(var1);
         if (!var4.exists()) {
            throw new RuntimeException("Directory: " + var4.getAbsolutePath() + " does not exist!");
         }

         var3 = new File(var4, var2);
         if (var3.exists() && !var3.delete()) {
            throw new RuntimeException("File: " + var3.getAbsolutePath() + " already exists and cannot be removed.");
         }

         if (!var3.createNewFile()) {
            throw new RuntimeException("File: " + var3.getAbsolutePath() + " could not be created.");
         }
      } else {
         var3 = File.createTempFile("librocksdbjni", tempFileSuffix);
      }

      if (var3.exists()) {
         var3.deleteOnExit();
         return var3;
      } else {
         throw new RuntimeException("File " + var3.getAbsolutePath() + " does not exist.");
      }
   }

   File loadLibraryFromJarToTemp(String var1) throws IOException {
      InputStream var2 = this.getClass().getClassLoader().getResourceAsStream(jniLibraryFileName);
      Throwable var3 = null;

      try {
         if (var2 != null) {
            File var4 = this.createTemp(var1, jniLibraryFileName);
            Files.copy(var2, var4.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            File var5 = var4;
            return var5;
         }
      } catch (Throwable var33) {
         var3 = var33;
         throw var33;
      } finally {
         if (var2 != null) {
            if (var3 != null) {
               try {
                  var2.close();
               } catch (Throwable var30) {
                  var3.addSuppressed(var30);
               }
            } else {
               var2.close();
            }
         }

      }

      if (fallbackJniLibraryFileName == null) {
         throw new RuntimeException(fallbackJniLibraryFileName + " was not found inside JAR.");
      } else {
         var2 = this.getClass().getClassLoader().getResourceAsStream(fallbackJniLibraryFileName);
         var3 = null;

         File var38;
         try {
            if (var2 == null) {
               throw new RuntimeException(jniLibraryFileName + " was not found inside JAR.");
            }

            File var37 = this.createTemp(var1, fallbackJniLibraryFileName);
            Files.copy(var2, var37.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            var38 = var37;
         } catch (Throwable var31) {
            var3 = var31;
            throw var31;
         } finally {
            if (var2 != null) {
               if (var3 != null) {
                  try {
                     var2.close();
                  } catch (Throwable var29) {
                     var3.addSuppressed(var29);
                  }
               } else {
                  var2.close();
               }
            }

         }

         return var38;
      }
   }

   private NativeLibraryLoader() {
   }
}
