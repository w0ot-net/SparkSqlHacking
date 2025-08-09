package org.apache.orc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.util.Progressable;
import org.apache.orc.OrcConf;

public class StreamWrapperFileSystem extends FileSystem {
   private final FSDataInputStream stream;
   private final FileStatus status;

   public StreamWrapperFileSystem(FSDataInputStream stream, FileStatus status, Configuration conf) {
      this.stream = stream;
      this.status = status;
      this.setConf(conf);
   }

   public StreamWrapperFileSystem(FSDataInputStream stream, Path path, long fileSize, Configuration conf) {
      this(stream, new FileStatus(fileSize, false, 1, (long)OrcConf.BLOCK_SIZE.getInt(conf), 0L, path), conf);
   }

   public URI getUri() {
      return URI.create("stream://" + String.valueOf(this.status.getPath()));
   }

   public FSDataInputStream open(Path path, int bufferSize) throws IOException {
      if (this.status.getPath().equals(path)) {
         return this.stream;
      } else {
         throw new FileNotFoundException(path.toString());
      }
   }

   public FSDataOutputStream create(Path path, FsPermission fsPermission, boolean b, int i, short i1, long l, Progressable progressable) {
      throw new UnsupportedOperationException("Write operations on " + this.getClass().getName());
   }

   public FSDataOutputStream append(Path path, int i, Progressable progressable) {
      throw new UnsupportedOperationException("Write operations on " + this.getClass().getName());
   }

   public boolean rename(Path path, Path path1) {
      throw new UnsupportedOperationException("Write operations on " + this.getClass().getName());
   }

   public boolean delete(Path path, boolean b) {
      throw new UnsupportedOperationException("Write operations on " + this.getClass().getName());
   }

   public void setWorkingDirectory(Path path) {
      throw new UnsupportedOperationException("Write operations on " + this.getClass().getName());
   }

   public Path getWorkingDirectory() {
      return this.status.getPath().getParent();
   }

   public boolean mkdirs(Path path, FsPermission fsPermission) {
      throw new UnsupportedOperationException("Write operations on " + this.getClass().getName());
   }

   public FileStatus[] listStatus(Path path) throws IOException {
      return new FileStatus[]{this.getFileStatus(path)};
   }

   public FileStatus getFileStatus(Path path) throws IOException {
      if (this.status.getPath().equals(path)) {
         return this.status;
      } else {
         throw new FileNotFoundException(path.toString());
      }
   }
}
