package org.xerial.snappy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.UUID;

public class SnappyLoader {
   public static final String SNAPPY_SYSTEM_PROPERTIES_FILE = "org-xerial-snappy.properties";
   public static final String KEY_SNAPPY_LIB_PATH = "org.xerial.snappy.lib.path";
   public static final String KEY_SNAPPY_LIB_NAME = "org.xerial.snappy.lib.name";
   public static final String KEY_SNAPPY_PUREJAVA = "org.xerial.snappy.purejava";
   public static final String KEY_SNAPPY_TEMPDIR = "org.xerial.snappy.tempdir";
   public static final String KEY_SNAPPY_USE_SYSTEMLIB = "org.xerial.snappy.use.systemlib";
   public static final String KEY_SNAPPY_DISABLE_BUNDLED_LIBS = "org.xerial.snappy.disable.bundled.libs";
   private static boolean isLoaded = false;
   private static volatile SnappyApi snappyApi = null;
   private static volatile BitShuffleNative bitshuffleApi = null;
   private static File nativeLibFile = null;

   static void cleanUpExtractedNativeLib() {
      if (nativeLibFile != null && nativeLibFile.exists()) {
         boolean var0 = nativeLibFile.delete();
         if (!var0) {
         }

         snappyApi = null;
         bitshuffleApi = null;
      }

   }

   static synchronized void setSnappyApi(SnappyApi var0) {
      snappyApi = var0;
   }

   private static void loadSnappySystemProperties() {
      try {
         InputStream var0 = Thread.currentThread().getContextClassLoader().getResourceAsStream("org-xerial-snappy.properties");
         if (var0 == null) {
            return;
         }

         Properties var1 = new Properties();
         var1.load(var0);
         var0.close();
         Enumeration var2 = var1.propertyNames();

         while(var2.hasMoreElements()) {
            String var3 = (String)var2.nextElement();
            if (var3.startsWith("org.xerial.snappy.") && System.getProperty(var3) == null) {
               System.setProperty(var3, var1.getProperty(var3));
            }
         }
      } catch (Throwable var4) {
         System.err.println("Could not load 'org-xerial-snappy.properties' from classpath: " + var4.toString());
      }

   }

   static synchronized SnappyApi loadSnappyApi() {
      if (snappyApi != null) {
         return snappyApi;
      } else {
         loadNativeLibrary();
         setSnappyApi(new SnappyNative());
         return snappyApi;
      }
   }

   static synchronized BitShuffleNative loadBitShuffleApi() {
      if (bitshuffleApi != null) {
         return bitshuffleApi;
      } else {
         loadNativeLibrary();
         bitshuffleApi = new BitShuffleNative();
         return bitshuffleApi;
      }
   }

   private static synchronized void loadNativeLibrary() {
      if (!isLoaded) {
         try {
            nativeLibFile = findNativeLibrary();
            if (nativeLibFile != null) {
               System.load(nativeLibFile.getAbsolutePath());
            } else {
               System.loadLibrary("snappyjava");
            }
         } catch (Exception var1) {
            var1.printStackTrace();
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, var1.getMessage());
         }

         isLoaded = true;
      }

   }

   private static boolean contentsEquals(InputStream var0, InputStream var1) throws IOException {
      if (!(var0 instanceof BufferedInputStream)) {
         var0 = new BufferedInputStream((InputStream)var0);
      }

      if (!(var1 instanceof BufferedInputStream)) {
         var1 = new BufferedInputStream((InputStream)var1);
      }

      for(int var2 = ((InputStream)var0).read(); var2 != -1; var2 = ((InputStream)var0).read()) {
         int var3 = ((InputStream)var1).read();
         if (var2 != var3) {
            return false;
         }
      }

      int var4 = ((InputStream)var1).read();
      return var4 == -1;
   }

   private static File extractLibraryFile(String var0, String var1, String var2) {
      String var3 = var0 + "/" + var1;
      String var4 = UUID.randomUUID().toString();
      String var5 = String.format("snappy-%s-%s-%s", getVersion(), var4, var1);
      File var6 = new File(var2, var5);

      try {
         InputStream var7 = null;
         FileOutputStream var8 = null;

         try {
            var7 = getResourceAsInputStream(var3);

            try {
               var8 = new FileOutputStream(var6);
               byte[] var9 = new byte[8192];
               int var10 = 0;

               while((var10 = var7.read(var9)) != -1) {
                  var8.write(var9, 0, var10);
               }
            } finally {
               if (var8 != null) {
                  var8.close();
               }

            }
         } finally {
            if (var7 != null) {
               var7.close();
            }

            var6.deleteOnExit();
         }

         boolean var30 = var6.setReadable(true) && var6.setWritable(true, true) && var6.setExecutable(true);
         if (!var30) {
         }

         InputStream var32 = null;
         FileInputStream var11 = null;

         try {
            var32 = getResourceAsInputStream(var3);
            var11 = new FileInputStream(var6);
            if (!contentsEquals(var32, var11)) {
               throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, String.format("Failed to write a native library file at %s", var6));
            }
         } finally {
            if (var32 != null) {
               var32.close();
            }

            if (var11 != null) {
               ((InputStream)var11).close();
            }

         }

         return new File(var2, var5);
      } catch (IOException var29) {
         var29.printStackTrace(System.err);
         return null;
      }
   }

   static File findNativeLibrary() {
      boolean var0 = Boolean.parseBoolean(System.getProperty("org.xerial.snappy.use.systemlib", "false"));
      boolean var1 = Boolean.parseBoolean(System.getProperty("org.xerial.snappy.disable.bundled.libs", "false"));
      if (!var0 && !var1) {
         String var2 = System.getProperty("org.xerial.snappy.lib.path");
         String var3 = System.getProperty("org.xerial.snappy.lib.name");
         if (var3 == null) {
            var3 = System.mapLibraryName("snappyjava");
         }

         if (var2 != null) {
            File var4 = new File(var2, var3);
            if (var4.exists()) {
               return var4;
            }
         }

         var2 = "/org/xerial/snappy/native/" + OSInfo.getNativeLibFolderPathForCurrentOS();
         boolean var8 = hasResource(var2 + "/" + var3);
         if (!var8 && OSInfo.getOSName().equals("Mac")) {
            String var5 = "libsnappyjava.dylib";
            if (hasResource(var2 + "/" + var5)) {
               var3 = var5;
               var8 = true;
            }
         }

         if (!var8) {
            String var10 = String.format("no native library is found for os.name=%s and os.arch=%s", OSInfo.getOSName(), OSInfo.getArchName());
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, var10);
         } else {
            File var9 = new File(System.getProperty("org.xerial.snappy.tempdir", System.getProperty("java.io.tmpdir")));
            if (!var9.exists()) {
               boolean var6 = var9.mkdirs();
               if (!var6) {
               }
            }

            return extractLibraryFile(var2, var3, var9.getAbsolutePath());
         }
      } else {
         return null;
      }
   }

   private static boolean hasResource(String var0) {
      return SnappyLoader.class.getResource(var0) != null;
   }

   public static String getVersion() {
      URL var0 = SnappyLoader.class.getResource("/META-INF/maven/org.xerial.snappy/snappy-java/pom.properties");
      if (var0 == null) {
         var0 = SnappyLoader.class.getResource("/org/xerial/snappy/VERSION");
      }

      String var1 = "unknown";

      try {
         if (var0 != null) {
            Properties var2 = new Properties();
            var2.load(var0.openStream());
            var1 = var2.getProperty("version", var1);
            if (var1.equals("unknown")) {
               var1 = var2.getProperty("SNAPPY_VERSION", var1);
            }

            var1 = var1.trim().replaceAll("[^0-9M\\.]", "");
         }
      } catch (IOException var3) {
         System.err.println(var3);
      }

      return var1;
   }

   private static InputStream getResourceAsInputStream(String var0) throws IOException {
      URL var1 = SnappyLoader.class.getResource(var0);
      URLConnection var2 = var1.openConnection();
      if (var2 instanceof JarURLConnection) {
         JarURLConnection var3 = (JarURLConnection)var2;
         var3.setUseCaches(false);
         return var3.getInputStream();
      } else {
         return var2.getInputStream();
      }
   }

   static {
      loadSnappySystemProperties();
   }
}
