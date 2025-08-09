package io.vertx.core.file.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.file.AsyncFileLock;
import io.vertx.core.file.FileSystemException;
import io.vertx.core.impl.VertxInternal;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Objects;

public class AsyncFileLockImpl implements AsyncFileLock {
   private final VertxInternal vertx;
   private final FileLock fileLock;

   public AsyncFileLockImpl(VertxInternal vertx, FileLock fileLock) {
      this.vertx = (VertxInternal)Objects.requireNonNull(vertx, "vertx is null");
      this.fileLock = (FileLock)Objects.requireNonNull(fileLock, "fileLock is null");
   }

   public long position() {
      return this.fileLock.position();
   }

   public long size() {
      return this.fileLock.size();
   }

   public boolean isShared() {
      return this.fileLock.isShared();
   }

   public boolean overlaps(long position, long size) {
      return this.fileLock.overlaps(position, size);
   }

   public boolean isValidBlocking() {
      return this.fileLock.isValid();
   }

   public Future isValid() {
      return this.vertx.getOrCreateContext().executeBlockingInternal((Handler)((prom) -> prom.complete(this.isValidBlocking())));
   }

   public void isValid(Handler handler) {
      Future<Boolean> future = this.isValid();
      if (handler != null) {
         future.onComplete(handler);
      }

   }

   public void releaseBlocking() {
      try {
         this.fileLock.release();
      } catch (IOException e) {
         throw new FileSystemException(e);
      }
   }

   public Future release() {
      return this.vertx.getOrCreateContext().executeBlockingInternal((Handler)((prom) -> {
         try {
            this.fileLock.release();
            prom.complete();
         } catch (IOException e) {
            prom.fail((Throwable)(new FileSystemException(e)));
         }

      }));
   }

   public void release(Handler handler) {
      Future<Void> future = this.release();
      if (handler != null) {
         future.onComplete(handler);
      }

   }
}
