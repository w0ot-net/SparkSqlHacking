package io.vertx.core.file.impl;

import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.util.Objects;

public class WindowsFileSystem extends FileSystemImpl {
   private static final Logger log = LoggerFactory.getLogger(WindowsFileSystem.class);

   public WindowsFileSystem(VertxInternal vertx) {
      super(vertx);
   }

   private static void logInternal(String perms) {
      if (perms != null && log.isDebugEnabled()) {
         log.debug("You are running on Windows and POSIX style file permissions are not supported");
      }

   }

   protected FileSystemImpl.BlockingAction chmodInternal(String path, String perms, String dirPerms) {
      Objects.requireNonNull(path);
      Objects.requireNonNull(perms);
      logInternal(perms);
      logInternal(dirPerms);
      if (log.isDebugEnabled()) {
         log.debug("You are running on Windows and POSIX style file permissions are not supported!");
      }

      return new FileSystemImpl.BlockingAction() {
         public Void perform() {
            return null;
         }
      };
   }

   protected FileSystemImpl.BlockingAction mkdirInternal(String path, String perms, boolean createParents) {
      logInternal(perms);
      return super.mkdirInternal(path, (String)null, createParents);
   }

   protected AsyncFile doOpen(String path, OpenOptions options, ContextInternal context) {
      logInternal(options.getPerms());
      return new AsyncFileImpl(this.vertx, path, options, context);
   }

   protected FileSystemImpl.BlockingAction createFileInternal(String p, String perms) {
      logInternal(perms);
      return super.createFileInternal(p, (String)null);
   }

   protected FileSystemImpl.BlockingAction chownInternal(String path, String user, String group) {
      if (group != null && log.isDebugEnabled()) {
         log.debug("You are running on Windows and POSIX style file ownership is not supported");
      }

      return super.chownInternal(path, user, group);
   }
}
