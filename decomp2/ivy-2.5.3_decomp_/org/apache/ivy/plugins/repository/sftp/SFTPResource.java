package org.apache.ivy.plugins.repository.sftp;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ivy.plugins.repository.Resource;

public class SFTPResource implements Resource {
   private SFTPRepository repository;
   private String path;
   private transient boolean init = false;
   private transient boolean exists;
   private transient long lastModified;
   private transient long contentLength;

   public SFTPResource(SFTPRepository repository, String path) {
      this.repository = repository;
      this.path = path;
   }

   public String getName() {
      return this.path;
   }

   public Resource clone(String cloneName) {
      return new SFTPResource(this.repository, cloneName);
   }

   public long getLastModified() {
      this.init();
      return this.lastModified;
   }

   public long getContentLength() {
      this.init();
      return this.contentLength;
   }

   public boolean exists() {
      this.init();
      return this.exists;
   }

   private void init() {
      if (!this.init) {
         Resource r = this.repository.resolveResource(this.path);
         this.contentLength = r.getContentLength();
         this.lastModified = r.getLastModified();
         this.exists = r.exists();
         this.init = true;
      }

   }

   public String toString() {
      return this.getName();
   }

   public boolean isLocal() {
      return false;
   }

   public InputStream openStream() throws IOException {
      return this.repository.openStream(this);
   }
}
