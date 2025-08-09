package org.apache.parquet.hadoop.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.io.PositionOutputStream;

public class HadoopOutputFile implements OutputFile {
   private static final int DFS_BUFFER_SIZE_DEFAULT = 4096;
   private static final Set BLOCK_FS_SCHEMES = new HashSet();
   private final FileSystem fs;
   private final Path path;
   private final Configuration conf;

   public static Set getBlockFileSystems() {
      return BLOCK_FS_SCHEMES;
   }

   private static boolean supportsBlockSize(FileSystem fs) {
      return BLOCK_FS_SCHEMES.contains(fs.getUri().getScheme());
   }

   public static HadoopOutputFile fromPath(Path path, Configuration conf) throws IOException {
      FileSystem fs = path.getFileSystem(conf);
      return new HadoopOutputFile(fs, fs.makeQualified(path), conf);
   }

   public static HadoopOutputFile fromPathUnchecked(Path path, Configuration conf) {
      try {
         return fromPath(path, conf);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private HadoopOutputFile(FileSystem fs, Path path, Configuration conf) {
      this.fs = fs;
      this.path = path;
      this.conf = conf;
   }

   public Configuration getConfiguration() {
      return this.conf;
   }

   public PositionOutputStream create(long blockSizeHint) throws IOException {
      return HadoopStreams.wrap(this.fs.create(this.path, false, 4096, this.fs.getDefaultReplication(this.path), Math.max(this.fs.getDefaultBlockSize(this.path), blockSizeHint)));
   }

   public PositionOutputStream createOrOverwrite(long blockSizeHint) throws IOException {
      return HadoopStreams.wrap(this.fs.create(this.path, true, 4096, this.fs.getDefaultReplication(this.path), Math.max(this.fs.getDefaultBlockSize(this.path), blockSizeHint)));
   }

   public boolean supportsBlockSize() {
      return supportsBlockSize(this.fs);
   }

   public long defaultBlockSize() {
      return this.fs.getDefaultBlockSize(this.path);
   }

   public String getPath() {
      return this.toString();
   }

   public String toString() {
      return this.path.toString();
   }

   static {
      BLOCK_FS_SCHEMES.add("hdfs");
      BLOCK_FS_SCHEMES.add("webhdfs");
      BLOCK_FS_SCHEMES.add("viewfs");
   }
}
