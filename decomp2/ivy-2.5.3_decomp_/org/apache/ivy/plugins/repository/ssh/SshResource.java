package org.apache.ivy.plugins.repository.ssh;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.Message;

public class SshResource implements Resource {
   private boolean resolved = false;
   private String uri = null;
   private boolean bExists = false;
   private long len = 0L;
   private long lastModified = 0L;
   private SshRepository repository = null;

   public SshResource() {
      this.resolved = true;
   }

   public SshResource(SshRepository repository, String uri) {
      this.uri = uri;
      this.repository = repository;
      this.resolved = false;
   }

   public SshResource(SshRepository repository, String uri, boolean bExists, long len, long lastModified) {
      this.uri = uri;
      this.bExists = bExists;
      this.len = len;
      this.lastModified = lastModified;
      this.repository = repository;
      this.resolved = true;
   }

   public boolean exists() {
      if (!this.resolved) {
         this.resolve();
      }

      return this.bExists;
   }

   public long getContentLength() {
      if (!this.resolved) {
         this.resolve();
      }

      return this.len;
   }

   public long getLastModified() {
      if (!this.resolved) {
         this.resolve();
      }

      return this.lastModified;
   }

   private void resolve() {
      Message.debug("SShResource: resolving " + this.uri);
      SshResource res = this.repository.resolveResource(this.uri);
      this.len = res.getContentLength();
      this.lastModified = res.getLastModified();
      this.bExists = res.exists();
      this.resolved = true;
      Message.debug("SShResource: resolved " + this);
   }

   public String getName() {
      return this.uri;
   }

   public String toString() {
      return "SshResource:" + this.uri + " (" + this.len + ")]";
   }

   public boolean isLocal() {
      return false;
   }

   public Resource clone(String cloneName) {
      return new SshResource(this.repository, cloneName);
   }

   public InputStream openStream() throws IOException {
      return this.repository.openStream(this);
   }
}
