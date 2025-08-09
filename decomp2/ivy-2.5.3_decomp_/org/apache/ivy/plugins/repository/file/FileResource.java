package org.apache.ivy.plugins.repository.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ivy.plugins.repository.Resource;

public class FileResource implements Resource {
   private File file;
   private FileRepository repository;

   public FileResource(FileRepository repository, File f) {
      this.repository = repository;
      this.file = f;
   }

   public String getName() {
      return this.file.getPath();
   }

   public Resource clone(String cloneName) {
      return new FileResource(this.repository, this.repository.getFile(cloneName));
   }

   public long getLastModified() {
      return this.file.lastModified();
   }

   public long getContentLength() {
      return this.file.length();
   }

   public boolean exists() {
      return this.file.exists();
   }

   public String toString() {
      return this.getName();
   }

   public File getFile() {
      return this.file;
   }

   public FileRepository getRepository() {
      return this.repository;
   }

   public boolean isLocal() {
      return this.repository.isLocal();
   }

   public InputStream openStream() throws IOException {
      return new FileInputStream(this.file);
   }
}
