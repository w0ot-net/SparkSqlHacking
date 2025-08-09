package org.apache.curator.shaded.com.google.common.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.StandardSystemProperty;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
abstract class TempFileCreator {
   static final TempFileCreator INSTANCE = pickSecureCreator();

   abstract File createTempDir();

   abstract File createTempFile(String prefix) throws IOException;

   private static TempFileCreator pickSecureCreator() {
      try {
         Class.forName("java.nio.file.Path");
         return new JavaNioCreator();
      } catch (ClassNotFoundException var5) {
         try {
            int version = (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object)null);
            int jellyBean = (Integer)Class.forName("android.os.Build$VERSION_CODES").getField("JELLY_BEAN").get((Object)null);
            if (version < jellyBean) {
               return new ThrowingCreator();
            }
         } catch (NoSuchFieldException var2) {
            return new ThrowingCreator();
         } catch (ClassNotFoundException var3) {
            return new ThrowingCreator();
         } catch (IllegalAccessException var4) {
            return new ThrowingCreator();
         }

         return new JavaIoCreator();
      }
   }

   private TempFileCreator() {
   }

   @IgnoreJRERequirement
   private static final class JavaNioCreator extends TempFileCreator {
      private static final FileAttribute RWX_USER_ONLY = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwx------"));
      private static final FileAttribute RW_USER_ONLY = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rw-------"));

      private JavaNioCreator() {
      }

      File createTempDir() {
         try {
            return java.nio.file.Files.createTempDirectory(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value()), (String)null, RWX_USER_ONLY).toFile();
         } catch (IOException e) {
            throw new IllegalStateException("Failed to create directory", e);
         }
      }

      File createTempFile(String prefix) throws IOException {
         return java.nio.file.Files.createTempFile(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value()), prefix, (String)null, RW_USER_ONLY).toFile();
      }
   }

   private static final class JavaIoCreator extends TempFileCreator {
      private static final int TEMP_DIR_ATTEMPTS = 10000;

      private JavaIoCreator() {
      }

      File createTempDir() {
         File baseDir = new File(StandardSystemProperty.JAVA_IO_TMPDIR.value());
         String baseName = System.currentTimeMillis() + "-";

         for(int counter = 0; counter < 10000; ++counter) {
            File tempDir = new File(baseDir, baseName + counter);
            if (tempDir.mkdir()) {
               return tempDir;
            }
         }

         throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + baseName + "0 to " + baseName + 9999 + ')');
      }

      File createTempFile(String prefix) throws IOException {
         return File.createTempFile(prefix, (String)null, (File)null);
      }
   }

   private static final class ThrowingCreator extends TempFileCreator {
      private static final String MESSAGE = "Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().";

      private ThrowingCreator() {
      }

      File createTempDir() {
         throw new IllegalStateException("Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().");
      }

      File createTempFile(String prefix) throws IOException {
         throw new IOException("Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().");
      }
   }
}
