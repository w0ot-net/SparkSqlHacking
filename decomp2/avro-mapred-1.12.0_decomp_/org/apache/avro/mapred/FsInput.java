package org.apache.avro.mapred;

import java.io.Closeable;
import java.io.IOException;
import org.apache.avro.file.SeekableInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FsInput implements Closeable, SeekableInput {
   private final FSDataInputStream stream;
   private final long len;

   public FsInput(Path path, Configuration conf) throws IOException {
      this(path, path.getFileSystem(conf));
   }

   public FsInput(Path path, FileSystem fileSystem) throws IOException {
      this.len = fileSystem.getFileStatus(path).getLen();
      this.stream = fileSystem.open(path);
   }

   public long length() {
      return this.len;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.stream.read(b, off, len);
   }

   public void seek(long p) throws IOException {
      this.stream.seek(p);
   }

   public long tell() throws IOException {
      return this.stream.getPos();
   }

   public void close() throws IOException {
      this.stream.close();
   }
}
