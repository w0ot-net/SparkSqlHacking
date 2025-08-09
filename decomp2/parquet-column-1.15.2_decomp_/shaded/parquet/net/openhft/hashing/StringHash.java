package shaded.parquet.net.openhft.hashing;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
interface StringHash {
   long longHash(String var1, LongHashFunction var2, int var3, int var4);

   void hash(String var1, LongTupleHashFunction var2, int var3, int var4, long[] var5);
}
