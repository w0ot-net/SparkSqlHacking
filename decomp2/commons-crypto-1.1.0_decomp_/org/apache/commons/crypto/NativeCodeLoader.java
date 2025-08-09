package org.apache.commons.crypto;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import org.apache.commons.crypto.utils.Utils;

final class NativeCodeLoader {
   private static final int EOF = -1;
   private static final Throwable loadingError = loadLibrary();
   private static final boolean nativeCodeLoaded;

   private static BufferedInputStream buffer(InputStream inputStream) {
      Objects.requireNonNull(inputStream, "inputStream");
      return inputStream instanceof BufferedInputStream ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream);
   }

   private static boolean contentsEquals(InputStream input1, InputStream input2) throws IOException {
      if (input1 == input2) {
         return true;
      } else if (input1 == null ^ input2 == null) {
         return false;
      } else {
         BufferedInputStream bufferedInput1 = buffer(input1);
         BufferedInputStream bufferedInput2 = buffer(input2);

         for(int ch = bufferedInput1.read(); -1 != ch; ch = bufferedInput1.read()) {
            int ch2 = bufferedInput2.read();
            if (ch != ch2) {
               return false;
            }
         }

         return bufferedInput2.read() == -1;
      }
   }

   private static void debug(String format, Object... args) {
      if (isDebug()) {
         System.out.println(String.format(format, args));
         if (args != null && args.length > 0 && args[0] instanceof Throwable) {
            ((Throwable)args[0]).printStackTrace(System.out);
         }
      }

   }

   private static File extractLibraryFile(String libFolderForCurrentOS, String libraryFileName, String targetFolder) {
      String nativeLibraryFilePath = libFolderForCurrentOS + "/" + libraryFileName;
      UUID uuid = UUID.randomUUID();
      String extractedLibFileName = String.format("commons-crypto-%s-%s", uuid, libraryFileName);
      File extractedLibFile = new File(targetFolder, extractedLibFileName);
      debug("Extracting '%s' to '%s'...", nativeLibraryFilePath, extractedLibFile);

      try {
         InputStream inputStream = NativeCodeLoader.class.getResourceAsStream(nativeLibraryFilePath);
         Throwable var8 = null;

         Object var9;
         try {
            if (inputStream != null) {
               try {
                  Path path = extractedLibFile.toPath();
                  long byteCount = Files.copy(inputStream, path, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
                  if (isDebug()) {
                     debug("Extracted '%s' to '%s': %,d bytes [%s]", nativeLibraryFilePath, extractedLibFile, byteCount, Files.isExecutable(path) ? "X+" : "X-");
                     PosixFileAttributes attributes = (PosixFileAttributes)Files.readAttributes(path, PosixFileAttributes.class);
                     if (attributes != null) {
                        debug("Attributes '%s': %s %s %s", extractedLibFile, attributes.permissions(), attributes.owner(), attributes.group());
                     }
                  }
               } finally {
                  debug("Delete on exit: %s", extractedLibFile);
                  extractedLibFile.deleteOnExit();
               }

               if (extractedLibFile.setReadable(true) && extractedLibFile.setExecutable(true) && extractedLibFile.setWritable(true, true)) {
                  InputStream nativeInputStream = NativeCodeLoader.class.getResourceAsStream(nativeLibraryFilePath);
                  Throwable var85 = null;

                  try {
                     InputStream extractedLibIn = new FileInputStream(extractedLibFile);
                     Throwable var86 = null;

                     try {
                        debug("Validating '%s'...", extractedLibFile);
                        if (!contentsEquals(nativeInputStream, extractedLibIn)) {
                           throw new IllegalStateException(String.format("Failed to write a native library file %s to %s", nativeLibraryFilePath, extractedLibFile));
                        }
                     } catch (Throwable var75) {
                        var86 = var75;
                        throw var75;
                     } finally {
                        if (extractedLibIn != null) {
                           if (var86 != null) {
                              try {
                                 extractedLibIn.close();
                              } catch (Throwable var73) {
                                 var86.addSuppressed(var73);
                              }
                           } else {
                              extractedLibIn.close();
                           }
                        }

                     }
                  } catch (Throwable var77) {
                     var85 = var77;
                     throw var77;
                  } finally {
                     if (nativeInputStream != null) {
                        if (var85 != null) {
                           try {
                              nativeInputStream.close();
                           } catch (Throwable var72) {
                              var85.addSuppressed(var72);
                           }
                        } else {
                           nativeInputStream.close();
                        }
                     }

                  }

                  File var84 = extractedLibFile;
                  return var84;
               }

               throw new IllegalStateException("Invalid path for library path " + extractedLibFile);
            }

            debug("Resource not found: %s", nativeLibraryFilePath);
            var9 = null;
         } catch (Throwable var79) {
            var8 = var79;
            throw var79;
         } finally {
            if (inputStream != null) {
               if (var8 != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var71) {
                     var8.addSuppressed(var71);
                  }
               } else {
                  inputStream.close();
               }
            }

         }

         return (File)var9;
      } catch (IOException e) {
         debug("Ignoring %s", e);
         return null;
      }
   }

   private static File findNativeLibrary() {
      Properties props = Utils.getDefaultProperties();
      String nativeLibraryPath = props.getProperty("commons.crypto.lib.path");
      String nativeLibraryName = props.getProperty("commons.crypto.lib.name");
      if (nativeLibraryName == null) {
         nativeLibraryName = System.mapLibraryName("commons-crypto");
      }

      if (nativeLibraryPath != null) {
         File nativeLib = new File(nativeLibraryPath, nativeLibraryName);
         if (nativeLib.exists()) {
            return nativeLib;
         }
      }

      nativeLibraryPath = "/org/apache/commons/crypto/native/" + OsInfo.getNativeLibFolderPathForCurrentOS();
      boolean hasNativeLib = hasResource(nativeLibraryPath + "/" + nativeLibraryName);
      if (!hasNativeLib) {
         String altName = "libcommons-crypto.jnilib";
         if (OsInfo.getOSName().equals("Mac") && hasResource(nativeLibraryPath + "/" + "libcommons-crypto.jnilib")) {
            nativeLibraryName = "libcommons-crypto.jnilib";
            hasNativeLib = true;
         }
      }

      if (!hasNativeLib) {
         String errorMessage = String.format("No native library is found for os.name=%s and os.arch=%s", OsInfo.getOSName(), OsInfo.getArchName());
         throw new IllegalStateException(errorMessage);
      } else {
         String tempFolder = (new File(props.getProperty("commons.crypto.lib.tempdir", System.getProperty("java.io.tmpdir")))).getAbsolutePath();
         return extractLibraryFile(nativeLibraryPath, nativeLibraryName, tempFolder);
      }
   }

   static Throwable getLoadingError() {
      return loadingError;
   }

   private static boolean hasResource(String path) {
      return NativeCodeLoader.class.getResource(path) != null;
   }

   private static boolean isDebug() {
      return Boolean.getBoolean("commons.crypto.debug");
   }

   static boolean isNativeCodeLoaded() {
      return nativeCodeLoaded;
   }

   static Throwable loadLibrary() {
      try {
         File nativeLibFile = findNativeLibrary();
         if (nativeLibFile != null) {
            String absolutePath = nativeLibFile.getAbsolutePath();
            debug("System.load('%s')", absolutePath);
            System.load(absolutePath);
         } else {
            String libname = "commons-crypto";
            debug("System.loadLibrary('%s')", "commons-crypto");
            System.loadLibrary("commons-crypto");
         }

         return null;
      } catch (Exception t) {
         return t;
      } catch (UnsatisfiedLinkError t) {
         return t;
      }
   }

   private NativeCodeLoader() {
   }

   static {
      nativeCodeLoaded = loadingError == null;
   }
}
