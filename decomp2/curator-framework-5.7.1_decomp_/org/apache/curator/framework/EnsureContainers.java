package org.apache.curator.framework;

import java.util.concurrent.atomic.AtomicBoolean;

public class EnsureContainers {
   private final CuratorFramework client;
   private final String path;
   private final AtomicBoolean ensureNeeded = new AtomicBoolean(true);

   public EnsureContainers(CuratorFramework client, String path) {
      this.client = client;
      this.path = path;
   }

   public void ensure() throws Exception {
      if (this.ensureNeeded.get()) {
         this.internalEnsure();
      }

   }

   public void reset() {
      this.ensureNeeded.set(true);
   }

   private synchronized void internalEnsure() throws Exception {
      if (this.ensureNeeded.compareAndSet(true, false)) {
         this.client.createContainers(this.path);
      }

   }
}
