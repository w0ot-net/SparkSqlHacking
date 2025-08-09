package org.apache.curator.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryLoop;

/** @deprecated */
@Deprecated
public class EnsurePath {
   private final String path;
   private final boolean makeLastNode;
   private final InternalACLProvider aclProvider;
   private final AtomicReference helper;
   private static final Helper doNothingHelper = new Helper() {
      public void ensure(CuratorZookeeperClient client, String path, boolean makeLastNode) throws Exception {
      }
   };

   public EnsurePath(String path) {
      this(path, (AtomicReference)null, true, (InternalACLProvider)null);
   }

   public EnsurePath(String path, InternalACLProvider aclProvider) {
      this(path, (AtomicReference)null, true, aclProvider);
   }

   public void ensure(CuratorZookeeperClient client) throws Exception {
      Helper localHelper = (Helper)this.helper.get();
      localHelper.ensure(client, this.path, this.makeLastNode);
   }

   public EnsurePath excludingLast() {
      return new EnsurePath(this.path, this.helper, false, this.aclProvider);
   }

   protected EnsurePath(String path, AtomicReference helper, boolean makeLastNode, InternalACLProvider aclProvider) {
      this.path = path;
      this.makeLastNode = makeLastNode;
      this.aclProvider = aclProvider;
      this.helper = helper != null ? helper : new AtomicReference(new InitialHelper());
   }

   public String getPath() {
      return this.path;
   }

   protected boolean asContainers() {
      return false;
   }

   private class InitialHelper implements Helper {
      private boolean isSet;

      private InitialHelper() {
         this.isSet = false;
      }

      public synchronized void ensure(final CuratorZookeeperClient client, final String path, final boolean makeLastNode) throws Exception {
         if (!this.isSet) {
            RetryLoop.callWithRetry(client, new Callable() {
               public Object call() throws Exception {
                  ZKPaths.mkdirs(client.getZooKeeper(), path, makeLastNode, EnsurePath.this.aclProvider, EnsurePath.this.asContainers());
                  EnsurePath.this.helper.set(EnsurePath.doNothingHelper);
                  InitialHelper.this.isSet = true;
                  return null;
               }
            });
         }

      }
   }

   interface Helper {
      void ensure(CuratorZookeeperClient var1, String var2, boolean var3) throws Exception;
   }
}
