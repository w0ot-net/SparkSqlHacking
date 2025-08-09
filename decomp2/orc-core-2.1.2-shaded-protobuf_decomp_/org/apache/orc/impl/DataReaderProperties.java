package org.apache.orc.impl;

import java.util.function.Supplier;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.orc.OrcConf;

public final class DataReaderProperties {
   private final Supplier fileSystemSupplier;
   private final Path path;
   private final FSDataInputStream file;
   private final InStream.StreamOptions compression;
   private final boolean zeroCopy;
   private final int maxDiskRangeChunkLimit;
   private final int minSeekSize;
   private final double minSeekSizeTolerance;

   private DataReaderProperties(Builder builder) {
      this.fileSystemSupplier = builder.fileSystemSupplier;
      this.path = builder.path;
      this.file = builder.file;
      this.compression = builder.compression;
      this.zeroCopy = builder.zeroCopy;
      this.maxDiskRangeChunkLimit = builder.maxDiskRangeChunkLimit;
      this.minSeekSize = builder.minSeekSize;
      this.minSeekSizeTolerance = builder.minSeekSizeTolerance;
   }

   public Supplier getFileSystemSupplier() {
      return this.fileSystemSupplier;
   }

   public Path getPath() {
      return this.path;
   }

   public FSDataInputStream getFile() {
      return this.file;
   }

   public InStream.StreamOptions getCompression() {
      return this.compression;
   }

   public boolean getZeroCopy() {
      return this.zeroCopy;
   }

   public int getMaxDiskRangeChunkLimit() {
      return this.maxDiskRangeChunkLimit;
   }

   public static Builder builder() {
      return new Builder();
   }

   public int getMinSeekSize() {
      return this.minSeekSize;
   }

   public double getMinSeekSizeTolerance() {
      return this.minSeekSizeTolerance;
   }

   public static class Builder {
      private Supplier fileSystemSupplier;
      private Path path;
      private FSDataInputStream file;
      private InStream.StreamOptions compression;
      private boolean zeroCopy;
      private int maxDiskRangeChunkLimit;
      private int minSeekSize;
      private double minSeekSizeTolerance;

      private Builder() {
         this.maxDiskRangeChunkLimit = (Integer)OrcConf.ORC_MAX_DISK_RANGE_CHUNK_LIMIT.getDefaultValue();
         this.minSeekSize = (Integer)OrcConf.ORC_MIN_DISK_SEEK_SIZE.getDefaultValue();
         this.minSeekSizeTolerance = (Double)OrcConf.ORC_MIN_DISK_SEEK_SIZE_TOLERANCE.getDefaultValue();
      }

      public Builder withFileSystemSupplier(Supplier supplier) {
         this.fileSystemSupplier = supplier;
         return this;
      }

      public Builder withFileSystem(FileSystem filesystem) {
         this.fileSystemSupplier = () -> filesystem;
         return this;
      }

      public Builder withPath(Path path) {
         this.path = path;
         return this;
      }

      public Builder withFile(FSDataInputStream file) {
         this.file = file;
         return this;
      }

      public Builder withCompression(InStream.StreamOptions value) {
         this.compression = value;
         return this;
      }

      public Builder withZeroCopy(boolean zeroCopy) {
         this.zeroCopy = zeroCopy;
         return this;
      }

      public Builder withMaxDiskRangeChunkLimit(int value) {
         this.maxDiskRangeChunkLimit = value;
         return this;
      }

      public Builder withMinSeekSize(int value) {
         this.minSeekSize = value;
         return this;
      }

      public Builder withMinSeekSizeTolerance(double value) {
         this.minSeekSizeTolerance = value;
         return this;
      }

      public DataReaderProperties build() {
         if (this.fileSystemSupplier != null && this.path != null) {
            return new DataReaderProperties(this);
         } else {
            String var10002 = String.valueOf(this.fileSystemSupplier);
            throw new NullPointerException("Filesystem = " + var10002 + ", path = " + String.valueOf(this.path));
         }
      }
   }
}
