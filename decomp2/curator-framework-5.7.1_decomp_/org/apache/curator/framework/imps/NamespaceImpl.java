package org.apache.curator.framework.imps;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryLoop;
import org.apache.curator.utils.EnsurePath;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;

class NamespaceImpl {
   private final CuratorFrameworkImpl client;
   private final String namespace;
   private final AtomicBoolean ensurePathNeeded;

   NamespaceImpl(CuratorFrameworkImpl client, String namespace) {
      if (namespace != null) {
         try {
            PathUtils.validatePath("/" + namespace);
         } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid namespace: " + namespace + ", " + e.getMessage());
         }
      }

      this.client = client;
      this.namespace = namespace;
      this.ensurePathNeeded = new AtomicBoolean(namespace != null);
   }

   String getNamespace() {
      return this.namespace;
   }

   String unfixForNamespace(String path) {
      if (this.namespace != null && path != null) {
         String namespacePath = ZKPaths.makePath(this.namespace, (String)null);
         if (!namespacePath.equals("/") && path.startsWith(namespacePath)) {
            path = path.length() > namespacePath.length() ? path.substring(namespacePath.length()) : "/";
         }
      }

      return path;
   }

   String fixForNamespace(String path, boolean isSequential) {
      if (this.ensurePathNeeded.get()) {
         try {
            final CuratorZookeeperClient zookeeperClient = this.client.getZookeeperClient();
            RetryLoop.callWithRetry(zookeeperClient, new Callable() {
               public Object call() throws Exception {
                  ZKPaths.mkdirs(zookeeperClient.getZooKeeper(), ZKPaths.makePath("/", NamespaceImpl.this.namespace), true, NamespaceImpl.this.client.getAclProvider(), true);
                  return null;
               }
            });
            this.ensurePathNeeded.set(false);
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.client.logError("Ensure path threw exception", e);
         }
      }

      return ZKPaths.fixForNamespace(this.namespace, path, isSequential);
   }

   EnsurePath newNamespaceAwareEnsurePath(String path) {
      return new EnsurePath(this.fixForNamespace(path, false), this.client.getAclProvider());
   }
}
