package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

public class JarArchiveInputStream extends ZipArchiveInputStream {
   public static boolean matches(byte[] signature, int length) {
      return ZipArchiveInputStream.matches(signature, length);
   }

   public JarArchiveInputStream(InputStream inputStream) {
      super(inputStream);
   }

   public JarArchiveInputStream(InputStream inputStream, String encoding) {
      super(inputStream, encoding);
   }

   public JarArchiveEntry getNextEntry() throws IOException {
      return this.getNextJarEntry();
   }

   /** @deprecated */
   @Deprecated
   public JarArchiveEntry getNextJarEntry() throws IOException {
      ZipArchiveEntry entry = this.getNextZipEntry();
      return entry == null ? null : new JarArchiveEntry(entry);
   }
}
