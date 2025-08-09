package org.apache.hadoop.fs;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.io.MD5Hash;

public class ProxyLocalFileSystem extends FilterFileSystem {
   protected LocalFileSystem localFs;
   private String scheme;

   public ProxyLocalFileSystem() {
      this.localFs = new LocalFileSystem();
   }

   public ProxyLocalFileSystem(FileSystem fs) {
      throw new RuntimeException("Unsupported Constructor");
   }

   public void initialize(URI name, Configuration conf) throws IOException {
      this.scheme = name.getScheme();
      String nameUriString = name.toString();
      String authority = name.getAuthority() != null ? name.getAuthority() : "";
      String proxyUriString = this.scheme + "://" + authority + "/";
      this.fs = ShimLoader.getHadoopShims().createProxyFileSystem(this.localFs, URI.create(proxyUriString));
      this.fs.initialize(name, conf);
   }

   public String getScheme() {
      return this.scheme;
   }

   public FileChecksum getFileChecksum(Path f) throws IOException {
      return this.scheme.equalsIgnoreCase("pfile") && this.fs.isFile(f) ? this.getPFileChecksum(f) : this.fs.getFileChecksum(f);
   }

   private FileChecksum getPFileChecksum(Path f) throws IOException {
      try {
         MessageDigest md5Digest = MessageDigest.getInstance("MD5");
         MD5Hash md5Hash = new MD5Hash(getMD5Checksum(this.fs.open(f)));
         return new PFileChecksum(md5Hash, md5Digest.getAlgorithm());
      } catch (Exception e) {
         throw new IOException(e);
      }
   }

   static byte[] getMD5Checksum(FSDataInputStream fsInputStream) throws Exception {
      byte[] buffer = new byte[1024];
      MessageDigest md5Digest = MessageDigest.getInstance("MD5");
      int numRead = 0;

      while(numRead != -1) {
         numRead = fsInputStream.read(buffer);
         if (numRead > 0) {
            md5Digest.update(buffer, 0, numRead);
         }
      }

      fsInputStream.close();
      return md5Digest.digest();
   }

   public static class PFileChecksum extends FileChecksum {
      private MD5Hash md5;
      private String algorithmName;

      public PFileChecksum(MD5Hash md5, String algorithmName) {
         this.md5 = md5;
         this.algorithmName = algorithmName;
      }

      public void write(DataOutput out) throws IOException {
         this.md5.write(out);
      }

      public void readFields(DataInput in) throws IOException {
         this.md5.readFields(in);
      }

      public String getAlgorithmName() {
         return this.algorithmName;
      }

      public int getLength() {
         return this.md5 != null ? this.md5.getDigest().length : 0;
      }

      public byte[] getBytes() {
         return this.md5 != null ? this.md5.getDigest() : new byte[0];
      }
   }
}
