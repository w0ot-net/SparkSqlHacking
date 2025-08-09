package org.rocksdb;

public abstract class AbstractNativeReference implements AutoCloseable {
   protected abstract boolean isOwningHandle();

   public abstract void close();
}
