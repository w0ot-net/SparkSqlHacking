package org.apache.commons.lang3;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import org.apache.commons.lang3.exception.UncheckedException;

public class RandomUtils {
   private static RandomUtils INSECURE = new RandomUtils(ThreadLocalRandom::current);
   private static RandomUtils SECURE = new RandomUtils(SecureRandom::new);
   private static final Supplier SECURE_STRONG_SUPPLIER = () -> (Random)SECURE_RANDOM_STRONG.get();
   private static RandomUtils SECURE_STRONG;
   private static final ThreadLocal SECURE_RANDOM_STRONG;
   private final Supplier random;

   public static RandomUtils insecure() {
      return INSECURE;
   }

   /** @deprecated */
   @Deprecated
   public static boolean nextBoolean() {
      return secure().randomBoolean();
   }

   /** @deprecated */
   @Deprecated
   public static byte[] nextBytes(int count) {
      return secure().randomBytes(count);
   }

   /** @deprecated */
   @Deprecated
   public static double nextDouble() {
      return secure().randomDouble();
   }

   /** @deprecated */
   @Deprecated
   public static double nextDouble(double startInclusive, double endExclusive) {
      return secure().randomDouble(startInclusive, endExclusive);
   }

   /** @deprecated */
   @Deprecated
   public static float nextFloat() {
      return secure().randomFloat();
   }

   /** @deprecated */
   @Deprecated
   public static float nextFloat(float startInclusive, float endExclusive) {
      return secure().randomFloat(startInclusive, endExclusive);
   }

   /** @deprecated */
   @Deprecated
   public static int nextInt() {
      return secure().randomInt();
   }

   /** @deprecated */
   @Deprecated
   public static int nextInt(int startInclusive, int endExclusive) {
      return secure().randomInt(startInclusive, endExclusive);
   }

   /** @deprecated */
   @Deprecated
   public static long nextLong() {
      return secure().randomLong();
   }

   /** @deprecated */
   @Deprecated
   public static long nextLong(long startInclusive, long endExclusive) {
      return secure().randomLong(startInclusive, endExclusive);
   }

   public static RandomUtils secure() {
      return SECURE;
   }

   static SecureRandom secureRandom() {
      return (SecureRandom)SECURE_RANDOM_STRONG.get();
   }

   public static RandomUtils secureStrong() {
      return SECURE_STRONG;
   }

   /** @deprecated */
   @Deprecated
   public RandomUtils() {
      this(SECURE_STRONG_SUPPLIER);
   }

   private RandomUtils(Supplier random) {
      this.random = random;
   }

   Random random() {
      return (Random)this.random.get();
   }

   public boolean randomBoolean() {
      return this.random().nextBoolean();
   }

   public byte[] randomBytes(int count) {
      Validate.isTrue(count >= 0, "Count cannot be negative.");
      byte[] result = new byte[count];
      this.random().nextBytes(result);
      return result;
   }

   public double randomDouble() {
      return this.randomDouble((double)0.0F, Double.MAX_VALUE);
   }

   public double randomDouble(double startInclusive, double endExclusive) {
      Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.");
      Validate.isTrue(startInclusive >= (double)0.0F, "Both range values must be non-negative.");
      return startInclusive == endExclusive ? startInclusive : startInclusive + (endExclusive - startInclusive) * this.random().nextDouble();
   }

   public float randomFloat() {
      return this.randomFloat(0.0F, Float.MAX_VALUE);
   }

   public float randomFloat(float startInclusive, float endExclusive) {
      Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.");
      Validate.isTrue(startInclusive >= 0.0F, "Both range values must be non-negative.");
      return startInclusive == endExclusive ? startInclusive : startInclusive + (endExclusive - startInclusive) * this.random().nextFloat();
   }

   public int randomInt() {
      return this.randomInt(0, Integer.MAX_VALUE);
   }

   public int randomInt(int startInclusive, int endExclusive) {
      Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.");
      Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.");
      return startInclusive == endExclusive ? startInclusive : startInclusive + this.random().nextInt(endExclusive - startInclusive);
   }

   public long randomLong() {
      return this.randomLong(Long.MAX_VALUE);
   }

   private long randomLong(long n) {
      long bits;
      long val;
      do {
         bits = this.random().nextLong() >>> 1;
         val = bits % n;
      } while(bits - val + n - 1L < 0L);

      return val;
   }

   public long randomLong(long startInclusive, long endExclusive) {
      Validate.isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.");
      Validate.isTrue(startInclusive >= 0L, "Both range values must be non-negative.");
      return startInclusive == endExclusive ? startInclusive : startInclusive + this.randomLong(endExclusive - startInclusive);
   }

   public String toString() {
      return "RandomUtils [random=" + this.random() + "]";
   }

   static {
      SECURE_STRONG = new RandomUtils(SECURE_STRONG_SUPPLIER);
      SECURE_RANDOM_STRONG = ThreadLocal.withInitial(() -> {
         try {
            return SecureRandom.getInstanceStrong();
         } catch (NoSuchAlgorithmException e) {
            throw new UncheckedException(e);
         }
      });
   }
}
