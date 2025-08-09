package org.apache.parquet.hadoop.util;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.SeekableInputStream;

public class HadoopInputFile implements InputFile {
   private final FileSystem fs;
   private final FileStatus stat;
   private final Configuration conf;

   public static HadoopInputFile fromPath(Path path, Configuration conf) throws IOException {
      FileSystem fs = path.getFileSystem(conf);
      return new HadoopInputFile(fs, fs.getFileStatus(path), conf);
   }

   public static HadoopInputFile fromPathUnchecked(Path path, Configuration conf) {
      try {
         return fromPath(path, conf);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static HadoopInputFile fromStatus(FileStatus stat, Configuration conf) throws IOException {
      FileSystem fs = stat.getPath().getFileSystem(conf);
      return new HadoopInputFile(fs, stat, conf);
   }

   private HadoopInputFile(FileSystem fs, FileStatus stat, Configuration conf) {
      this.fs = fs;
      this.stat = stat;
      this.conf = conf;
   }

   public Configuration getConfiguration() {
      return this.conf;
   }

   public Path getPath() {
      return this.stat.getPath();
   }

   public long getLength() {
      return this.stat.getLen();
   }

   public SeekableInputStream newStream() throws IOException {
      return HadoopStreams.wrap(this.fs.open(this.stat.getPath()));
   }

   public String toString() {
      return this.stat.getPath().toString();
   }
}
