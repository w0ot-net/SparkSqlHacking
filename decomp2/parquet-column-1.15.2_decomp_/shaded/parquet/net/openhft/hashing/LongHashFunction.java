package shaded.parquet.net.openhft.hashing;

import java.io.Serializable;
import java.nio.ByteBuffer;
import org.jetbrains.annotations.NotNull;
import sun.nio.ch.DirectBuffer;

public abstract class LongHashFunction implements Serializable {
   private static final long serialVersionUID = 0L;

   public static LongHashFunction city_1_1() {
      return CityAndFarmHash_1_1.asLongHashFunctionWithoutSeed();
   }

   public static LongHashFunction city_1_1(long seed) {
      return CityAndFarmHash_1_1.asLongHashFunctionWithSeed(seed);
   }

   public static LongHashFunction city_1_1(long seed0, long seed1) {
      return CityAndFarmHash_1_1.asLongHashFunctionWithTwoSeeds(seed0, seed1);
   }

   public static LongHashFunction farmNa() {
      return CityAndFarmHash_1_1.naWithoutSeeds();
   }

   public static LongHashFunction farmNa(long seed) {
      return CityAndFarmHash_1_1.naWithSeed(seed);
   }

   public static LongHashFunction farmNa(long seed0, long seed1) {
      return CityAndFarmHash_1_1.naWithSeeds(seed0, seed1);
   }

   public static LongHashFunction farmUo() {
      return CityAndFarmHash_1_1.uoWithoutSeeds();
   }

   public static LongHashFunction farmUo(long seed) {
      return CityAndFarmHash_1_1.uoWithSeed(seed);
   }

   public static LongHashFunction farmUo(long seed0, long seed1) {
      return CityAndFarmHash_1_1.uoWithSeeds(seed0, seed1);
   }

   public static LongHashFunction murmur_3() {
      return MurmurHash_3.asLongHashFunctionWithoutSeed();
   }

   public static LongHashFunction murmur_3(long seed) {
      return MurmurHash_3.asLongHashFunctionWithSeed(seed);
   }

   public static LongHashFunction xx() {
      return XxHash.asLongHashFunctionWithoutSeed();
   }

   public static LongHashFunction xx(long seed) {
      return XxHash.asLongHashFunctionWithSeed(seed);
   }

   public static LongHashFunction xx3() {
      return XXH3.asLongHashFunctionWithoutSeed();
   }

   public static LongHashFunction xx3(long seed) {
      return XXH3.asLongHashFunctionWithSeed(seed);
   }

   public static LongHashFunction xx128low() {
      return XXH3.asLongTupleLowHashFunctionWithoutSeed();
   }

   public static LongHashFunction xx128low(long seed) {
      return XXH3.asLongTupleLowHashFunctionWithSeed(seed);
   }

   public static LongHashFunction wy_3() {
      return WyHash.asLongHashFunctionWithoutSeed();
   }

   public static LongHashFunction wy_3(long seed) {
      return WyHash.asLongHashFunctionWithSeed(seed);
   }

   public static LongHashFunction metro() {
      return MetroHash.asLongHashFunctionWithoutSeed();
   }

   public static LongHashFunction metro(long seed) {
      return MetroHash.asLongHashFunctionWithSeed(seed);
   }

   protected LongHashFunction() {
   }

   public abstract long hashLong(long var1);

   public abstract long hashInt(int var1);

   public abstract long hashShort(short var1);

   public abstract long hashChar(char var1);

   public abstract long hashByte(byte var1);

   public abstract long hashVoid();

   public abstract long hash(Object var1, Access var2, long var3, long var5);

   private long unsafeHash(Object input, long off, long len) {
      return this.hash(input, UnsafeAccess.INSTANCE, off, len);
   }

   public long hashBoolean(boolean input) {
      return this.hashByte(input ? UnsafeAccess.TRUE_BYTE_VALUE : UnsafeAccess.FALSE_BYTE_VALUE);
   }

   public long hashBooleans(@NotNull boolean[] input) {
      return this.unsafeHash(input, UnsafeAccess.BOOLEAN_BASE, (long)input.length);
   }

   public long hashBooleans(@NotNull boolean[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      return this.unsafeHash(input, UnsafeAccess.BOOLEAN_BASE + (long)off, (long)len);
   }

   public long hashBytes(@NotNull byte[] input) {
      return this.unsafeHash(input, UnsafeAccess.BYTE_BASE, (long)input.length);
   }

   public long hashBytes(@NotNull byte[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      return this.unsafeHash(input, UnsafeAccess.BYTE_BASE + (long)off, (long)len);
   }

   public long hashBytes(ByteBuffer input) {
      return this.hashByteBuffer(input, input.position(), input.remaining());
   }

   public long hashBytes(@NotNull ByteBuffer input, int off, int len) {
      Util.checkArrayOffs(input.capacity(), off, len);
      return this.hashByteBuffer(input, off, len);
   }

   private long hashByteBuffer(@NotNull ByteBuffer input, int off, int len) {
      if (input.hasArray()) {
         return this.unsafeHash(input.array(), UnsafeAccess.BYTE_BASE + (long)input.arrayOffset() + (long)off, (long)len);
      } else {
         return input instanceof DirectBuffer ? this.unsafeHash((Object)null, ((DirectBuffer)input).address() + (long)off, (long)len) : this.hash(input, ByteBufferAccess.INSTANCE, (long)off, (long)len);
      }
   }

   public long hashMemory(long address, long len) {
      return this.unsafeHash((Object)null, address, len);
   }

   public long hashChars(@NotNull char[] input) {
      return this.unsafeHash(input, UnsafeAccess.CHAR_BASE, (long)input.length * 2L);
   }

   public long hashChars(@NotNull char[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      return this.unsafeHash(input, UnsafeAccess.CHAR_BASE + (long)off * 2L, (long)len * 2L);
   }

   public long hashChars(@NotNull String input) {
      return Util.VALID_STRING_HASH.longHash(input, this, 0, input.length());
   }

   public long hashChars(@NotNull String input, int off, int len) {
      Util.checkArrayOffs(input.length(), off, len);
      return Util.VALID_STRING_HASH.longHash(input, this, off, len);
   }

   public long hashChars(@NotNull StringBuilder input) {
      return this.hashNativeChars(input);
   }

   public long hashChars(@NotNull StringBuilder input, int off, int len) {
      Util.checkArrayOffs(input.length(), off, len);
      return this.hashNativeChars(input, off, len);
   }

   long hashNativeChars(CharSequence input) {
      return this.hashNativeChars(input, 0, input.length());
   }

   long hashNativeChars(CharSequence input, int off, int len) {
      return this.hash(input, CharSequenceAccess.nativeCharSequenceAccess(), (long)off * 2L, (long)len * 2L);
   }

   public long hashShorts(@NotNull short[] input) {
      return this.unsafeHash(input, UnsafeAccess.SHORT_BASE, (long)input.length * 2L);
   }

   public long hashShorts(@NotNull short[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      return this.unsafeHash(input, UnsafeAccess.SHORT_BASE + (long)off * 2L, (long)len * 2L);
   }

   public long hashInts(@NotNull int[] input) {
      return this.unsafeHash(input, UnsafeAccess.INT_BASE, (long)input.length * 4L);
   }

   public long hashInts(@NotNull int[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      return this.unsafeHash(input, UnsafeAccess.INT_BASE + (long)off * 4L, (long)len * 4L);
   }

   public long hashLongs(@NotNull long[] input) {
      return this.unsafeHash(input, UnsafeAccess.LONG_BASE, (long)input.length * 8L);
   }

   public long hashLongs(@NotNull long[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      return this.unsafeHash(input, UnsafeAccess.LONG_BASE + (long)off * 8L, (long)len * 8L);
   }
}
