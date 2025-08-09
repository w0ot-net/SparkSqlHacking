package shaded.parquet.net.openhft.hashing;

import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.ParametersAreNonnullByDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.nio.ch.DirectBuffer;

@ParametersAreNonnullByDefault
public abstract class LongTupleHashFunction implements Serializable {
   private static final long serialVersionUID = 0L;
   @NotNull
   private static final Access OBJECT_ACCESS;
   @NotNull
   private static final Access CHAR_SEQ_ACCESS;
   @NotNull
   private static final Access BYTE_BUF_ACCESS;

   @NotNull
   public static LongTupleHashFunction murmur_3() {
      return MurmurHash_3.asLongTupleHashFunctionWithoutSeed();
   }

   @NotNull
   public static LongTupleHashFunction murmur_3(long seed) {
      return MurmurHash_3.asLongTupleHashFunctionWithSeed(seed);
   }

   @NotNull
   public static LongTupleHashFunction xx128() {
      return XXH3.asLongTupleHashFunctionWithoutSeed();
   }

   @NotNull
   public static LongTupleHashFunction xx128(long seed) {
      return XXH3.asLongTupleHashFunctionWithSeed(seed);
   }

   protected LongTupleHashFunction() {
   }

   public abstract int bitsLength();

   @NotNull
   public long[] newResultArray() {
      return new long[(this.bitsLength() + 63) / 64];
   }

   public abstract void hashLong(long var1, long[] var3);

   @NotNull
   public long[] hashLong(long input) {
      long[] result = this.newResultArray();
      this.hashLong(input, result);
      return result;
   }

   public abstract void hashInt(int var1, long[] var2);

   @NotNull
   public long[] hashInt(int input) {
      long[] result = this.newResultArray();
      this.hashInt(input, result);
      return result;
   }

   public abstract void hashShort(short var1, long[] var2);

   @NotNull
   public long[] hashShort(short input) {
      long[] result = this.newResultArray();
      this.hashShort(input, result);
      return result;
   }

   public abstract void hashChar(char var1, long[] var2);

   @NotNull
   public long[] hashChar(char input) {
      long[] result = this.newResultArray();
      this.hashChar(input, result);
      return result;
   }

   public abstract void hashByte(byte var1, long[] var2);

   @NotNull
   public long[] hashByte(byte input) {
      long[] result = this.newResultArray();
      this.hashByte(input, result);
      return result;
   }

   public abstract void hashVoid(long[] var1);

   @NotNull
   public long[] hashVoid() {
      long[] result = this.newResultArray();
      this.hashVoid(result);
      return result;
   }

   public abstract void hash(@Nullable Object var1, Access var2, long var3, long var5, long[] var7);

   @NotNull
   public long[] hash(@Nullable Object input, Access access, long off, long len) {
      long[] result = this.newResultArray();
      this.hash(input, access, off, len, result);
      return result;
   }

   public void hashBoolean(boolean input, long[] result) {
      this.hashByte(input ? UnsafeAccess.TRUE_BYTE_VALUE : UnsafeAccess.FALSE_BYTE_VALUE, result);
   }

   @NotNull
   public long[] hashBoolean(boolean input) {
      long[] result = this.newResultArray();
      this.hashByte(input ? UnsafeAccess.TRUE_BYTE_VALUE : UnsafeAccess.FALSE_BYTE_VALUE, result);
      return result;
   }

   public void hashBooleans(boolean[] input, long[] result) {
      unsafeHash(this, input, UnsafeAccess.BOOLEAN_BASE, (long)input.length, result);
   }

   @NotNull
   public long[] hashBooleans(boolean[] input) {
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.BOOLEAN_BASE, (long)input.length, result);
      return result;
   }

   public void hashBooleans(boolean[] input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length, off, len);
      unsafeHash(this, input, UnsafeAccess.BOOLEAN_BASE + (long)off, (long)len, result);
   }

   @NotNull
   public long[] hashBooleans(boolean[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.BOOLEAN_BASE + (long)off, (long)len, result);
      return result;
   }

   public void hashBytes(byte[] input, long[] result) {
      unsafeHash(this, input, UnsafeAccess.BYTE_BASE, (long)input.length, result);
   }

   @NotNull
   public long[] hashBytes(byte[] input) {
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.BYTE_BASE, (long)input.length, result);
      return result;
   }

   public void hashBytes(byte[] input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length, off, len);
      unsafeHash(this, input, UnsafeAccess.BYTE_BASE + (long)off, (long)len, result);
   }

   @NotNull
   public long[] hashBytes(byte[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.BYTE_BASE + (long)off, (long)len, result);
      return result;
   }

   public void hashBytes(ByteBuffer input, long[] result) {
      hashByteBuffer(this, input, input.position(), input.remaining(), result);
   }

   @NotNull
   public long[] hashBytes(ByteBuffer input) {
      long[] result = this.newResultArray();
      hashByteBuffer(this, input, input.position(), input.remaining(), result);
      return result;
   }

   public void hashBytes(ByteBuffer input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.capacity(), off, len);
      hashByteBuffer(this, input, off, len, result);
   }

   @NotNull
   public long[] hashBytes(ByteBuffer input, int off, int len) {
      Util.checkArrayOffs(input.capacity(), off, len);
      long[] result = this.newResultArray();
      hashByteBuffer(this, input, off, len, result);
      return result;
   }

   public void hashMemory(long address, long len, long[] result) {
      unsafeHash(this, (Object)null, address, len, result);
   }

   @NotNull
   public long[] hashMemory(long address, long len) {
      long[] result = this.newResultArray();
      unsafeHash(this, (Object)null, address, len, result);
      return result;
   }

   public void hashChars(char[] input, long[] result) {
      unsafeHash(this, input, UnsafeAccess.CHAR_BASE, (long)input.length * 2L, result);
   }

   @NotNull
   public long[] hashChars(char[] input) {
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.CHAR_BASE, (long)input.length * 2L, result);
      return result;
   }

   public void hashChars(char[] input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length, off, len);
      unsafeHash(this, input, UnsafeAccess.CHAR_BASE + (long)off * 2L, (long)len * 2L, result);
   }

   @NotNull
   public long[] hashChars(char[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.CHAR_BASE + (long)off * 2L, (long)len * 2L, result);
      return result;
   }

   public void hashChars(String input, long[] result) {
      Util.VALID_STRING_HASH.hash(input, this, 0, input.length(), result);
   }

   @NotNull
   public long[] hashChars(String input) {
      long[] result = this.newResultArray();
      Util.VALID_STRING_HASH.hash(input, this, 0, input.length(), result);
      return result;
   }

   public void hashChars(String input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length(), off, len);
      Util.VALID_STRING_HASH.hash(input, this, off, len, result);
   }

   @NotNull
   public long[] hashChars(String input, int off, int len) {
      Util.checkArrayOffs(input.length(), off, len);
      long[] result = this.newResultArray();
      Util.VALID_STRING_HASH.hash(input, this, off, len, result);
      return result;
   }

   public void hashChars(CharSequence input, long[] result) {
      hashNativeChars(this, input, 0, input.length(), result);
   }

   @NotNull
   public long[] hashChars(CharSequence input) {
      long[] result = this.newResultArray();
      hashNativeChars(this, input, 0, input.length(), result);
      return result;
   }

   public void hashChars(CharSequence input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length(), off, len);
      hashNativeChars(this, input, off, len, result);
   }

   @NotNull
   public long[] hashChars(CharSequence input, int off, int len) {
      Util.checkArrayOffs(input.length(), off, len);
      long[] result = this.newResultArray();
      hashNativeChars(this, input, off, len, result);
      return result;
   }

   public void hashShorts(short[] input, long[] result) {
      unsafeHash(this, input, UnsafeAccess.SHORT_BASE, (long)input.length * 2L, result);
   }

   @NotNull
   public long[] hashShorts(short[] input) {
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.SHORT_BASE, (long)input.length * 2L, result);
      return result;
   }

   public void hashShorts(short[] input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length, off, len);
      unsafeHash(this, input, UnsafeAccess.SHORT_BASE + (long)off * 2L, (long)len * 2L, result);
   }

   @NotNull
   public long[] hashShorts(short[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.SHORT_BASE + (long)off * 2L, (long)len * 2L, result);
      return result;
   }

   public void hashInts(int[] input, long[] result) {
      unsafeHash(this, input, UnsafeAccess.INT_BASE, (long)input.length * 4L, result);
   }

   @NotNull
   public long[] hashInts(int[] input) {
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.INT_BASE, (long)input.length * 4L, result);
      return result;
   }

   public void hashInts(int[] input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length, off, len);
      unsafeHash(this, input, UnsafeAccess.INT_BASE + (long)off * 4L, (long)len * 4L, result);
   }

   @NotNull
   public long[] hashInts(int[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.INT_BASE + (long)off * 4L, (long)len * 4L, result);
      return result;
   }

   public void hashLongs(long[] input, long[] result) {
      unsafeHash(this, input, UnsafeAccess.LONG_BASE, (long)input.length * 8L, result);
   }

   @NotNull
   public long[] hashLongs(long[] input) {
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.LONG_BASE, (long)input.length * 8L, result);
      return result;
   }

   public void hashLongs(long[] input, int off, int len, long[] result) {
      Util.checkArrayOffs(input.length, off, len);
      unsafeHash(this, input, UnsafeAccess.LONG_BASE + (long)off * 8L, (long)len * 8L, result);
   }

   @NotNull
   public long[] hashLongs(long[] input, int off, int len) {
      Util.checkArrayOffs(input.length, off, len);
      long[] result = this.newResultArray();
      unsafeHash(this, input, UnsafeAccess.LONG_BASE + (long)off * 8L, (long)len * 8L, result);
      return result;
   }

   private static void unsafeHash(LongTupleHashFunction f, @Nullable Object input, long off, long len, long[] result) {
      f.hash(input, OBJECT_ACCESS, off, len, result);
   }

   private static void hashByteBuffer(LongTupleHashFunction f, ByteBuffer input, int off, int len, long[] result) {
      if (input.hasArray()) {
         unsafeHash(f, input.array(), UnsafeAccess.BYTE_BASE + (long)input.arrayOffset() + (long)off, (long)len, result);
      } else if (input instanceof DirectBuffer) {
         unsafeHash(f, (Object)null, ((DirectBuffer)input).address() + (long)off, (long)len, result);
      } else {
         f.hash(input, BYTE_BUF_ACCESS, (long)off, (long)len, result);
      }

   }

   static void hashNativeChars(LongTupleHashFunction f, CharSequence input, int off, int len, long[] result) {
      f.hash(input, CHAR_SEQ_ACCESS, (long)off * 2L, (long)len * 2L, result);
   }

   static {
      OBJECT_ACCESS = UnsafeAccess.INSTANCE;
      CHAR_SEQ_ACCESS = CharSequenceAccess.nativeCharSequenceAccess();
      BYTE_BUF_ACCESS = ByteBufferAccess.INSTANCE;
   }
}
