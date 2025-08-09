package org.apache.hadoop.fs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.util.Progressable;

public class ProxyFileSystem extends FilterFileSystem {
   protected String myScheme;
   protected String myAuthority;
   protected URI myUri;
   protected String realScheme;
   protected String realAuthority;
   protected URI realUri;

   protected Path swizzleParamPath(Path p) {
      String pathUriString = p.toUri().toString();
      URI newPathUri = URI.create(pathUriString);
      return new Path(this.realScheme, this.realAuthority, newPathUri.getPath());
   }

   private Path swizzleReturnPath(Path p) {
      String pathUriString = p.toUri().toString();
      URI newPathUri = URI.create(pathUriString);
      return new Path(this.myScheme, this.myAuthority, newPathUri.getPath());
   }

   protected FileStatus swizzleFileStatus(FileStatus orig, boolean isParam) {
      FileStatus ret = new FileStatus(orig.getLen(), orig.isDir(), orig.getReplication(), orig.getBlockSize(), orig.getModificationTime(), orig.getAccessTime(), orig.getPermission(), orig.getOwner(), orig.getGroup(), isParam ? this.swizzleParamPath(orig.getPath()) : this.swizzleReturnPath(orig.getPath()));
      return ret;
   }

   public ProxyFileSystem() {
      throw new RuntimeException("Unsupported constructor");
   }

   public ProxyFileSystem(FileSystem fs) {
      throw new RuntimeException("Unsupported constructor");
   }

   public Path resolvePath(Path p) throws IOException {
      this.checkPath(p);
      return this.getFileStatus(p).getPath();
   }

   public ProxyFileSystem(FileSystem fs, URI myUri) {
      super(fs);
      URI realUri = fs.getUri();
      this.realScheme = realUri.getScheme();
      this.realAuthority = realUri.getAuthority();
      this.realUri = realUri;
      this.myScheme = myUri.getScheme();
      this.myAuthority = myUri.getAuthority();
      this.myUri = myUri;
   }

   public void initialize(URI name, Configuration conf) throws IOException {
      try {
         URI realUri = new URI(this.realScheme, this.realAuthority, name.getPath(), name.getQuery(), name.getFragment());
         super.initialize(realUri, conf);
      } catch (URISyntaxException e) {
         throw new RuntimeException(e);
      }
   }

   public URI getUri() {
      return this.myUri;
   }

   public String getName() {
      return this.getUri().toString();
   }

   public Path makeQualified(Path path) {
      return this.swizzleReturnPath(super.makeQualified(this.swizzleParamPath(path)));
   }

   protected void checkPath(Path path) {
      super.checkPath(this.swizzleParamPath(path));
   }

   public BlockLocation[] getFileBlockLocations(FileStatus file, long start, long len) throws IOException {
      return super.getFileBlockLocations(this.swizzleFileStatus(file, true), start, len);
   }

   public FSDataInputStream open(Path f, int bufferSize) throws IOException {
      return super.open(this.swizzleParamPath(f), bufferSize);
   }

   public FSDataOutputStream append(Path f, int bufferSize, Progressable progress) throws IOException {
      return super.append(this.swizzleParamPath(f), bufferSize, progress);
   }

   public FSDataOutputStream create(Path f, FsPermission permission, boolean overwrite, int bufferSize, short replication, long blockSize, Progressable progress) throws IOException {
      return super.create(this.swizzleParamPath(f), permission, overwrite, bufferSize, replication, blockSize, progress);
   }

   public boolean setReplication(Path src, short replication) throws IOException {
      return super.setReplication(this.swizzleParamPath(src), replication);
   }

   public boolean rename(Path src, Path dst) throws IOException {
      Path dest = this.swizzleParamPath(dst);
      return super.isFile(dest) ? false : super.rename(this.swizzleParamPath(src), dest);
   }

   public boolean delete(Path f, boolean recursive) throws IOException {
      return super.delete(this.swizzleParamPath(f), recursive);
   }

   public boolean deleteOnExit(Path f) throws IOException {
      return super.deleteOnExit(this.swizzleParamPath(f));
   }

   public FileStatus[] listStatus(Path f) throws IOException {
      FileStatus[] orig = super.listStatus(this.swizzleParamPath(f));
      FileStatus[] ret = new FileStatus[orig.length];

      for(int i = 0; i < orig.length; ++i) {
         ret[i] = this.swizzleFileStatus(orig[i], false);
      }

      return ret;
   }

   public Path getHomeDirectory() {
      return this.swizzleReturnPath(super.getHomeDirectory());
   }

   public void setWorkingDirectory(Path newDir) {
      super.setWorkingDirectory(this.swizzleParamPath(newDir));
   }

   public Path getWorkingDirectory() {
      return this.swizzleReturnPath(super.getWorkingDirectory());
   }

   public boolean mkdirs(Path f, FsPermission permission) throws IOException {
      return super.mkdirs(this.swizzleParamPath(f), permission);
   }

   public void copyFromLocalFile(boolean delSrc, Path src, Path dst) throws IOException {
      super.copyFromLocalFile(delSrc, this.swizzleParamPath(src), this.swizzleParamPath(dst));
   }

   public void copyFromLocalFile(boolean delSrc, boolean overwrite, Path[] srcs, Path dst) throws IOException {
      super.copyFromLocalFile(delSrc, overwrite, srcs, this.swizzleParamPath(dst));
   }

   public void copyFromLocalFile(boolean delSrc, boolean overwrite, Path src, Path dst) throws IOException {
      super.copyFromLocalFile(delSrc, overwrite, src, this.swizzleParamPath(dst));
   }

   public void copyToLocalFile(boolean delSrc, Path src, Path dst) throws IOException {
      super.copyToLocalFile(delSrc, this.swizzleParamPath(src), dst);
   }

   public Path startLocalOutput(Path fsOutputFile, Path tmpLocalFile) throws IOException {
      return super.startLocalOutput(this.swizzleParamPath(fsOutputFile), tmpLocalFile);
   }

   public void completeLocalOutput(Path fsOutputFile, Path tmpLocalFile) throws IOException {
      super.completeLocalOutput(this.swizzleParamPath(fsOutputFile), tmpLocalFile);
   }

   public ContentSummary getContentSummary(Path f) throws IOException {
      return super.getContentSummary(this.swizzleParamPath(f));
   }

   public FileStatus getFileStatus(Path f) throws IOException {
      return this.swizzleFileStatus(super.getFileStatus(this.swizzleParamPath(f)), false);
   }

   public FileChecksum getFileChecksum(Path f) throws IOException {
      return super.getFileChecksum(this.swizzleParamPath(f));
   }

   public void setOwner(Path p, String username, String groupname) throws IOException {
      super.setOwner(this.swizzleParamPath(p), username, groupname);
   }

   public void setTimes(Path p, long mtime, long atime) throws IOException {
      super.setTimes(this.swizzleParamPath(p), mtime, atime);
   }

   public void setPermission(Path p, FsPermission permission) throws IOException {
      super.setPermission(this.swizzleParamPath(p), permission);
   }
}
