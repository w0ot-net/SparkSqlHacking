package org.codehaus.commons.compiler.util.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileResource implements LocatableResource {
   private final File file;

   public FileResource(File file) {
      this.file = file;
   }

   public URL getLocation() throws IOException {
      return this.file.toURI().toURL();
   }

   public final String getFileName() {
      return this.file.toString();
   }

   public final InputStream open() throws IOException {
      return new FileInputStream(this.file);
   }

   public final long lastModified() {
      return this.file.lastModified();
   }

   public final File getFile() {
      return this.file;
   }

   public final String toString() {
      return this.getFileName();
   }
}
