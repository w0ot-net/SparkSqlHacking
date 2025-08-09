package org.apache.spark.util.random;

import java.io.Serializable;
import java.nio.ByteBuffer;
import scala.runtime.ModuleSerializationProxy;
import scala.util.hashing.MurmurHash3.;

public final class XORShiftRandom$ implements Serializable {
   public static final XORShiftRandom$ MODULE$ = new XORShiftRandom$();

   public long hashSeed(final long seed) {
      byte[] bytes = ByteBuffer.allocate(8).putLong(seed).array();
      int lowBits = .MODULE$.bytesHash(bytes, 1007110753);
      int highBits = .MODULE$.bytesHash(bytes, lowBits);
      return (long)highBits << 32 | (long)lowBits & 4294967295L;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(XORShiftRandom$.class);
   }

   private XORShiftRandom$() {
   }
}
