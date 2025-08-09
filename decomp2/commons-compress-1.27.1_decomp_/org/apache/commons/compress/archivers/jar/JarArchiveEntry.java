package org.apache.commons.compress.archivers.jar;

import java.security.cert.Certificate;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public class JarArchiveEntry extends ZipArchiveEntry {
   public JarArchiveEntry(JarEntry entry) throws ZipException {
      super((ZipEntry)entry);
   }

   public JarArchiveEntry(String name) {
      super(name);
   }

   public JarArchiveEntry(ZipArchiveEntry entry) throws ZipException {
      super(entry);
   }

   public JarArchiveEntry(ZipEntry entry) throws ZipException {
      super(entry);
   }

   /** @deprecated */
   @Deprecated
   public Certificate[] getCertificates() {
      return null;
   }

   /** @deprecated */
   @Deprecated
   public Attributes getManifestAttributes() {
      return null;
   }
}
