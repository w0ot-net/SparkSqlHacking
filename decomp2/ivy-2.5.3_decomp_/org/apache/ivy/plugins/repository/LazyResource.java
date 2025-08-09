package org.apache.ivy.plugins.repository;

public abstract class LazyResource implements Resource {
   private boolean init = false;
   private boolean local;
   private String name;
   private long lastModified;
   private long contentLength;
   private boolean exists;

   public LazyResource(String name) {
      this.name = name;
   }

   protected abstract void init();

   private void checkInit() {
      if (!this.init) {
         this.init();
         this.init = true;
      }

   }

   public boolean exists() {
      this.checkInit();
      return this.exists;
   }

   public long getContentLength() {
      this.checkInit();
      return this.contentLength;
   }

   public long getLastModified() {
      this.checkInit();
      return this.lastModified;
   }

   public String getName() {
      return this.name;
   }

   public boolean isLocal() {
      this.checkInit();
      return this.local;
   }

   public String toString() {
      return this.getName();
   }

   protected void setContentLength(long contentLength) {
      this.contentLength = contentLength;
   }

   protected void setExists(boolean exists) {
      this.exists = exists;
   }

   protected void setLastModified(long lastModified) {
      this.lastModified = lastModified;
   }

   protected void setLocal(boolean local) {
      this.local = local;
   }

   protected void init(Resource r) {
      this.setContentLength(r.getContentLength());
      this.setLocal(r.isLocal());
      this.setLastModified(r.getLastModified());
      this.setExists(r.exists());
   }
}
