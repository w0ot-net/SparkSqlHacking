package io.vertx.core.file.impl;

import io.vertx.core.file.FileSystemProps;

public class FileSystemPropsImpl implements FileSystemProps {
   private final long totalSpace;
   private final long unallocatedSpace;
   private final long usableSpace;

   public FileSystemPropsImpl(long totalSpace, long unallocatedSpace, long usableSpace) {
      this.totalSpace = totalSpace;
      this.unallocatedSpace = unallocatedSpace;
      this.usableSpace = usableSpace;
   }

   public long totalSpace() {
      return this.totalSpace;
   }

   public long unallocatedSpace() {
      return this.unallocatedSpace;
   }

   public long usableSpace() {
      return this.usableSpace;
   }
}
