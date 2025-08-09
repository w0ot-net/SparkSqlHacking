package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.primitives.Ints;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class DiscreteDomain {
   final boolean supportsFastOffset;

   public static DiscreteDomain integers() {
      return DiscreteDomain.IntegerDomain.INSTANCE;
   }

   public static DiscreteDomain longs() {
      return DiscreteDomain.LongDomain.INSTANCE;
   }

   public static DiscreteDomain bigIntegers() {
      return DiscreteDomain.BigIntegerDomain.INSTANCE;
   }

   protected DiscreteDomain() {
      this(false);
   }

   private DiscreteDomain(boolean supportsFastOffset) {
      this.supportsFastOffset = supportsFastOffset;
   }

   Comparable offset(Comparable origin, long distance) {
      C current = origin;
      CollectPreconditions.checkNonnegative(distance, "distance");

      for(long i = 0L; i < distance; ++i) {
         current = (C)this.next(current);
         if (current == null) {
            throw new IllegalArgumentException("overflowed computing offset(" + origin + ", " + distance + ")");
         }
      }

      return current;
   }

   @CheckForNull
   public abstract Comparable next(Comparable value);

   @CheckForNull
   public abstract Comparable previous(Comparable value);

   public abstract long distance(Comparable start, Comparable end);

   @CanIgnoreReturnValue
   public Comparable minValue() {
      throw new NoSuchElementException();
   }

   @CanIgnoreReturnValue
   public Comparable maxValue() {
      throw new NoSuchElementException();
   }

   private static final class IntegerDomain extends DiscreteDomain implements Serializable {
      private static final IntegerDomain INSTANCE = new IntegerDomain();
      private static final long serialVersionUID = 0L;

      IntegerDomain() {
         super(true, null);
      }

      @CheckForNull
      public Integer next(Integer value) {
         int i = value;
         return i == Integer.MAX_VALUE ? null : i + 1;
      }

      @CheckForNull
      public Integer previous(Integer value) {
         int i = value;
         return i == Integer.MIN_VALUE ? null : i - 1;
      }

      Integer offset(Integer origin, long distance) {
         CollectPreconditions.checkNonnegative(distance, "distance");
         return Ints.checkedCast(origin.longValue() + distance);
      }

      public long distance(Integer start, Integer end) {
         return (long)end - (long)start;
      }

      public Integer minValue() {
         return Integer.MIN_VALUE;
      }

      public Integer maxValue() {
         return Integer.MAX_VALUE;
      }

      private Object readResolve() {
         return INSTANCE;
      }

      public String toString() {
         return "DiscreteDomain.integers()";
      }
   }

   private static final class LongDomain extends DiscreteDomain implements Serializable {
      private static final LongDomain INSTANCE = new LongDomain();
      private static final long serialVersionUID = 0L;

      LongDomain() {
         super(true, null);
      }

      @CheckForNull
      public Long next(Long value) {
         long l = value;
         return l == Long.MAX_VALUE ? null : l + 1L;
      }

      @CheckForNull
      public Long previous(Long value) {
         long l = value;
         return l == Long.MIN_VALUE ? null : l - 1L;
      }

      Long offset(Long origin, long distance) {
         CollectPreconditions.checkNonnegative(distance, "distance");
         long result = origin + distance;
         if (result < 0L) {
            Preconditions.checkArgument(origin < 0L, "overflow");
         }

         return result;
      }

      public long distance(Long start, Long end) {
         long result = end - start;
         if (end > start && result < 0L) {
            return Long.MAX_VALUE;
         } else {
            return end < start && result > 0L ? Long.MIN_VALUE : result;
         }
      }

      public Long minValue() {
         return Long.MIN_VALUE;
      }

      public Long maxValue() {
         return Long.MAX_VALUE;
      }

      private Object readResolve() {
         return INSTANCE;
      }

      public String toString() {
         return "DiscreteDomain.longs()";
      }
   }

   private static final class BigIntegerDomain extends DiscreteDomain implements Serializable {
      private static final BigIntegerDomain INSTANCE = new BigIntegerDomain();
      private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
      private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
      private static final long serialVersionUID = 0L;

      BigIntegerDomain() {
         super(true, null);
      }

      public BigInteger next(BigInteger value) {
         return value.add(BigInteger.ONE);
      }

      public BigInteger previous(BigInteger value) {
         return value.subtract(BigInteger.ONE);
      }

      BigInteger offset(BigInteger origin, long distance) {
         CollectPreconditions.checkNonnegative(distance, "distance");
         return origin.add(BigInteger.valueOf(distance));
      }

      public long distance(BigInteger start, BigInteger end) {
         return end.subtract(start).max(MIN_LONG).min(MAX_LONG).longValue();
      }

      private Object readResolve() {
         return INSTANCE;
      }

      public String toString() {
         return "DiscreteDomain.bigIntegers()";
      }
   }
}
