package org.apache.zookeeper.server.util;

public class AdHash {
   private volatile long hash;

   public AdHash addDigest(long digest) {
      this.hash += digest;
      return this;
   }

   public AdHash removeDigest(long digest) {
      this.hash -= digest;
      return this;
   }

   public long getHash() {
      return this.hash;
   }

   public boolean equals(Object other) {
      return other instanceof AdHash && ((AdHash)other).hash == this.hash;
   }

   public int hashCode() {
      return Long.hashCode(this.hash);
   }

   public String toString() {
      return Long.toHexString(this.hash);
   }

   public void clear() {
      this.hash = 0L;
   }
}
