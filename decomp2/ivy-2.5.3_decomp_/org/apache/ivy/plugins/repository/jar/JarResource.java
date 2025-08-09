package org.apache.ivy.plugins.repository.jar;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import org.apache.ivy.plugins.repository.Resource;

public class JarResource implements Resource {
   private final JarFile jarFile;
   private final String path;
   private ZipEntry entry;

   public JarResource(JarFile jarFile, String path) {
      this.jarFile = jarFile;
      this.path = path;
      this.entry = jarFile.getEntry(path);
   }

   public String getName() {
      return this.path;
   }

   public long getLastModified() {
      return this.entry.getTime();
   }

   public long getContentLength() {
      return this.entry.getSize();
   }

   public boolean exists() {
      return this.entry != null;
   }

   public boolean isLocal() {
      return false;
   }

   public Resource clone(String cloneName) {
      return new JarResource(this.jarFile, cloneName);
   }

   public InputStream openStream() throws IOException {
      return this.jarFile.getInputStream(this.entry);
   }

   public String toString() {
      return this.jarFile.getName() + "!" + this.getName();
   }
}
