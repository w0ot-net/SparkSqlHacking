package shaded.parquet.net.openhft.hashing;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
enum UnknownJvmStringHash implements StringHash {
   INSTANCE;

   public long longHash(String s, LongHashFunction hashFunction, int off, int len) {
      return hashFunction.hashNativeChars(s, off, len);
   }

   public void hash(String s, LongTupleHashFunction hashFunction, int off, int len, long[] result) {
      LongTupleHashFunction.hashNativeChars(hashFunction, s, off, len, result);
   }
}
