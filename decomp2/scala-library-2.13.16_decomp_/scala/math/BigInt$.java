package scala.math;

import java.io.Serializable;
import java.math.BigInteger;
import scala.runtime.ModuleSerializationProxy;
import scala.util.Random;

public final class BigInt$ implements Serializable {
   public static final BigInt$ MODULE$ = new BigInt$();
   private static final BigInteger scala$math$BigInt$$longMinValueBigInteger = BigInteger.valueOf(Long.MIN_VALUE);
   private static final BigInt longMinValue;
   private static final int minCached;
   private static final int maxCached;
   private static final BigInt[] cache;
   private static final BigInteger scala$math$BigInt$$minusOne;

   static {
      longMinValue = new BigInt(MODULE$.scala$math$BigInt$$longMinValueBigInteger(), Long.MIN_VALUE);
      minCached = -1024;
      maxCached = 1024;
      cache = new BigInt[maxCached - minCached + 1];
      scala$math$BigInt$$minusOne = BigInteger.valueOf(-1L);
   }

   public BigInteger scala$math$BigInt$$longMinValueBigInteger() {
      return scala$math$BigInt$$longMinValueBigInteger;
   }

   private BigInt longMinValue() {
      return longMinValue;
   }

   private BigInt getCached(final int i) {
      int offset = i - minCached;
      BigInt n = cache[offset];
      if (n == null) {
         n = new BigInt((BigInteger)null, (long)i);
         cache[offset] = n;
      }

      return n;
   }

   public BigInteger scala$math$BigInt$$minusOne() {
      return scala$math$BigInt$$minusOne;
   }

   public BigInt apply(final int i) {
      return minCached <= i && i <= maxCached ? this.getCached(i) : this.apply((long)i);
   }

   public BigInt apply(final long l) {
      if ((long)minCached <= l && l <= (long)maxCached) {
         return this.getCached((int)l);
      } else {
         return l == Long.MIN_VALUE ? this.longMinValue() : new BigInt((BigInteger)null, l);
      }
   }

   public BigInt apply(final byte[] x) {
      return this.apply(new BigInteger(x));
   }

   public BigInt apply(final int signum, final byte[] magnitude) {
      return this.apply(new BigInteger(signum, magnitude));
   }

   public BigInt apply(final int bitlength, final int certainty, final Random rnd) {
      return this.apply(new BigInteger(bitlength, certainty, rnd.self()));
   }

   public BigInt apply(final int numbits, final Random rnd) {
      return this.apply(new BigInteger(numbits, rnd.self()));
   }

   public BigInt apply(final String x) {
      return this.apply(new BigInteger(x));
   }

   public BigInt apply(final String x, final int radix) {
      return this.apply(new BigInteger(x, radix));
   }

   public BigInt apply(final BigInteger x) {
      if (x.bitLength() <= 63) {
         long l = x.longValue();
         return (long)minCached <= l && l <= (long)maxCached ? this.getCached((int)l) : new BigInt(x, l);
      } else {
         return new BigInt(x, Long.MIN_VALUE);
      }
   }

   public BigInt probablePrime(final int bitLength, final Random rnd) {
      return this.apply(BigInteger.probablePrime(bitLength, rnd.self()));
   }

   public BigInt int2bigInt(final int i) {
      return this.apply(i);
   }

   public BigInt long2bigInt(final long l) {
      return this.apply(l);
   }

   public BigInt javaBigInteger2bigInt(final BigInteger x) {
      return x == null ? null : this.apply(x);
   }

   public long scala$math$BigInt$$longGcd(final long a, final long b) {
      if (a == 0L) {
         return b;
      } else if (b == 0L) {
         return a;
      } else {
         int aTwos = Long.numberOfTrailingZeros(a);
         long a1 = a >> aTwos;
         int bTwos = Long.numberOfTrailingZeros(b);

         for(long b1 = b >> bTwos; a1 != b1; a1 >>= Long.numberOfTrailingZeros(a1)) {
            long delta = a1 - b1;
            long minDeltaOrZero = delta & delta >> 63;
            a1 = delta - minDeltaOrZero - minDeltaOrZero;
            b1 += minDeltaOrZero;
         }

         package$ var10001 = package$.MODULE$;
         return a1 << Math.min(aTwos, bTwos);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BigInt$.class);
   }

   private BigInt$() {
   }
}
