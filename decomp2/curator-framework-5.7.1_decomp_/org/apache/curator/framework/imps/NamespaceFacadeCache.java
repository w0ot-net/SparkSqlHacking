package org.apache.curator.framework.imps;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.shaded.com.google.common.cache.CacheBuilder;
import org.apache.curator.shaded.com.google.common.cache.CacheLoader;
import org.apache.curator.shaded.com.google.common.cache.LoadingCache;

class NamespaceFacadeCache {
   private final CuratorFrameworkImpl client;
   private final AtomicReference nullNamespace;
   private final CacheLoader loader = new CacheLoader() {
      public NamespaceFacade load(String namespace) throws Exception {
         return new NamespaceFacade(NamespaceFacadeCache.this.client, namespace);
      }
   };
   private final LoadingCache cache;

   NamespaceFacadeCache(CuratorFrameworkImpl client) {
      this.cache = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build(this.loader);
      this.client = client;
      this.nullNamespace = new AtomicReference((Object)null);
   }

   private NamespaceFacade getNullNamespace() {
      NamespaceFacade facade = (NamespaceFacade)this.nullNamespace.get();
      if (facade != null) {
         return facade;
      } else {
         facade = new NamespaceFacade(this.client, (String)null);
         if (!this.nullNamespace.compareAndSet((Object)null, facade)) {
            facade = (NamespaceFacade)this.nullNamespace.get();
         }

         return facade;
      }
   }

   NamespaceFacade get(String namespace) {
      try {
         return namespace == null ? this.getNullNamespace() : (NamespaceFacade)this.cache.get(namespace);
      } catch (ExecutionException e) {
         throw new RuntimeException(e);
      }
   }
}
