package org.apache.ivy.core.cache;

public class CacheResourceOptions extends CacheDownloadOptions {
   private long ttl = 3600000L;

   public void setTtl(long ttl) {
      this.ttl = ttl;
   }

   public long getTtl() {
      return this.ttl;
   }
}
