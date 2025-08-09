package shaded.parquet.net.openhft.hashing;

import javax.annotation.ParametersAreNonnullByDefault;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ParametersAreNonnullByDefault
abstract class DualHashFunction extends LongTupleHashFunction {
   private static final long serialVersionUID = 0L;
   private final transient int resultLength = this.newResultArray().length;
   @NotNull
   private final transient LongHashFunction longHashFunction = new LongHashFunction() {
      public long hashLong(long input) {
         return DualHashFunction.this.dualHashLong(input, (long[])null);
      }

      public long hashInt(int input) {
         return DualHashFunction.this.dualHashInt(input, (long[])null);
      }

      public long hashShort(short input) {
         return DualHashFunction.this.dualHashShort(input, (long[])null);
      }

      public long hashChar(char input) {
         return DualHashFunction.this.dualHashChar(input, (long[])null);
      }

      public long hashByte(byte input) {
         return DualHashFunction.this.dualHashByte(input, (long[])null);
      }

      public long hashVoid() {
         return DualHashFunction.this.dualHashVoid((long[])null);
      }

      public long hash(@Nullable Object input, Access access, long off, long len) {
         return DualHashFunction.this.dualHash(input, access, off, len, (long[])null);
      }
   };

   private void checkResult(long[] result) {
      if (null == result) {
         throw new NullPointerException();
      } else if (result.length < this.resultLength) {
         throw new IllegalArgumentException("The input result array has not enough space!");
      }
   }

   protected abstract long dualHashLong(long var1, @Nullable long[] var3);

   public void hashLong(long input, long[] result) {
      this.checkResult(result);
      this.dualHashLong(input, result);
   }

   protected abstract long dualHashInt(int var1, @Nullable long[] var2);

   public void hashInt(int input, long[] result) {
      this.checkResult(result);
      this.dualHashInt(input, result);
   }

   protected abstract long dualHashShort(short var1, @Nullable long[] var2);

   public void hashShort(short input, long[] result) {
      this.checkResult(result);
      this.dualHashShort(input, result);
   }

   protected abstract long dualHashChar(char var1, @Nullable long[] var2);

   public void hashChar(char input, long[] result) {
      this.checkResult(result);
      this.dualHashChar(input, result);
   }

   protected abstract long dualHashByte(byte var1, @Nullable long[] var2);

   public void hashByte(byte input, long[] result) {
      this.checkResult(result);
      this.dualHashByte(input, result);
   }

   protected abstract long dualHashVoid(@Nullable long[] var1);

   public void hashVoid(long[] result) {
      this.checkResult(result);
      this.dualHashVoid(result);
   }

   protected abstract long dualHash(@Nullable Object var1, Access var2, long var3, long var5, @Nullable long[] var7);

   public void hash(@Nullable Object input, Access access, long off, long len, long[] result) {
      this.checkResult(result);
      this.dualHash(input, access, off, len, result);
   }

   public long[] hash(@Nullable Object input, Access access, long off, long len) {
      long[] result = this.newResultArray();
      this.dualHash(input, access, off, len, result);
      return result;
   }

   @NotNull
   protected LongHashFunction asLongHashFunction() {
      return this.longHashFunction;
   }
}
