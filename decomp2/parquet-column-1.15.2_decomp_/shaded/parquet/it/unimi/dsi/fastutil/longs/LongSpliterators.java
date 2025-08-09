package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.bytes.ByteSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.chars.CharSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.shorts.ShortSpliterator;

public final class LongSpliterators {
   static final int BASE_SPLITERATOR_CHARACTERISTICS = 256;
   public static final int COLLECTION_SPLITERATOR_CHARACTERISTICS = 320;
   public static final int LIST_SPLITERATOR_CHARACTERISTICS = 16720;
   public static final int SET_SPLITERATOR_CHARACTERISTICS = 321;
   private static final int SORTED_CHARACTERISTICS = 20;
   public static final int SORTED_SET_SPLITERATOR_CHARACTERISTICS = 341;
   public static final EmptySpliterator EMPTY_SPLITERATOR = new EmptySpliterator();

   private LongSpliterators() {
   }

   public static LongSpliterator singleton(long element) {
      return new SingletonSpliterator(element);
   }

   public static LongSpliterator singleton(long element, LongComparator comparator) {
      return new SingletonSpliterator(element, comparator);
   }

   public static LongSpliterator wrap(long[] array, int offset, int length) {
      LongArrays.ensureOffsetLength(array, offset, length);
      return new ArraySpliterator(array, offset, length, 0);
   }

   public static LongSpliterator wrap(long[] array) {
      return new ArraySpliterator(array, 0, array.length, 0);
   }

   public static LongSpliterator wrap(long[] array, int offset, int length, int additionalCharacteristics) {
      LongArrays.ensureOffsetLength(array, offset, length);
      return new ArraySpliterator(array, offset, length, additionalCharacteristics);
   }

   public static LongSpliterator wrapPreSorted(long[] array, int offset, int length, int additionalCharacteristics, LongComparator comparator) {
      LongArrays.ensureOffsetLength(array, offset, length);
      return new ArraySpliteratorWithComparator(array, offset, length, additionalCharacteristics, comparator);
   }

   public static LongSpliterator wrapPreSorted(long[] array, int offset, int length, LongComparator comparator) {
      return wrapPreSorted(array, offset, length, 0, comparator);
   }

   public static LongSpliterator wrapPreSorted(long[] array, LongComparator comparator) {
      return wrapPreSorted(array, 0, array.length, comparator);
   }

   public static LongSpliterator asLongSpliterator(Spliterator i) {
      if (i instanceof LongSpliterator) {
         return (LongSpliterator)i;
      } else {
         return (LongSpliterator)(i instanceof Spliterator.OfLong ? new PrimitiveSpliteratorWrapper((Spliterator.OfLong)i) : new SpliteratorWrapper(i));
      }
   }

   public static LongSpliterator asLongSpliterator(Spliterator i, LongComparator comparatorOverride) {
      if (i instanceof LongSpliterator) {
         throw new IllegalArgumentException("Cannot override comparator on instance that is already a " + LongSpliterator.class.getSimpleName());
      } else {
         return (LongSpliterator)(i instanceof Spliterator.OfLong ? new PrimitiveSpliteratorWrapperWithComparator((Spliterator.OfLong)i, comparatorOverride) : new SpliteratorWrapperWithComparator(i, comparatorOverride));
      }
   }

   public static void onEachMatching(LongSpliterator spliterator, java.util.function.LongPredicate predicate, java.util.function.LongConsumer action) {
      Objects.requireNonNull(predicate);
      Objects.requireNonNull(action);
      spliterator.forEachRemaining((LongConsumer)((value) -> {
         if (predicate.test(value)) {
            action.accept(value);
         }

      }));
   }

   public static LongSpliterator fromTo(long from, long to) {
      return new IntervalSpliterator(from, to);
   }

   public static LongSpliterator concat(LongSpliterator... a) {
      return concat(a, 0, a.length);
   }

   public static LongSpliterator concat(LongSpliterator[] a, int offset, int length) {
      return new SpliteratorConcatenator(a, offset, length);
   }

   public static LongSpliterator asSpliterator(LongIterator iter, long size, int additionalCharacterisitcs) {
      return new SpliteratorFromIterator(iter, size, additionalCharacterisitcs);
   }

   public static LongSpliterator asSpliteratorFromSorted(LongIterator iter, long size, int additionalCharacterisitcs, LongComparator comparator) {
      return new SpliteratorFromIteratorWithComparator(iter, size, additionalCharacterisitcs, comparator);
   }

   public static LongSpliterator asSpliteratorUnknownSize(LongIterator iter, int characterisitcs) {
      return new SpliteratorFromIterator(iter, characterisitcs);
   }

   public static LongSpliterator asSpliteratorFromSortedUnknownSize(LongIterator iter, int additionalCharacterisitcs, LongComparator comparator) {
      return new SpliteratorFromIteratorWithComparator(iter, additionalCharacterisitcs, comparator);
   }

   public static LongIterator asIterator(LongSpliterator spliterator) {
      return new IteratorFromSpliterator(spliterator);
   }

   public static LongSpliterator wrap(ByteSpliterator spliterator) {
      return new ByteSpliteratorWrapper(spliterator);
   }

   public static LongSpliterator wrap(ShortSpliterator spliterator) {
      return new ShortSpliteratorWrapper(spliterator);
   }

   public static LongSpliterator wrap(CharSpliterator spliterator) {
      return new CharSpliteratorWrapper(spliterator);
   }

   public static LongSpliterator wrap(IntSpliterator spliterator) {
      return new IntSpliteratorWrapper(spliterator);
   }

   public static class EmptySpliterator implements LongSpliterator, Serializable, Cloneable {
      private static final long serialVersionUID = 8379247926738230492L;
      private static final int CHARACTERISTICS = 16448;

      protected EmptySpliterator() {
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         return false;
      }

      /** @deprecated */
      @Deprecated
      public boolean tryAdvance(Consumer action) {
         return false;
      }

      public LongSpliterator trySplit() {
         return null;
      }

      public long estimateSize() {
         return 0L;
      }

      public int characteristics() {
         return 16448;
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
      }

      /** @deprecated */
      @Deprecated
      public void forEachRemaining(Consumer action) {
      }

      public Object clone() {
         return LongSpliterators.EMPTY_SPLITERATOR;
      }

      private Object readResolve() {
         return LongSpliterators.EMPTY_SPLITERATOR;
      }
   }

   private static class SingletonSpliterator implements LongSpliterator {
      private final long element;
      private final LongComparator comparator;
      private boolean consumed;
      private static final int CHARACTERISTICS = 17749;

      public SingletonSpliterator(long element) {
         this(element, (LongComparator)null);
      }

      public SingletonSpliterator(long element, LongComparator comparator) {
         this.consumed = false;
         this.element = element;
         this.comparator = comparator;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         if (this.consumed) {
            return false;
         } else {
            this.consumed = true;
            action.accept(this.element);
            return true;
         }
      }

      public LongSpliterator trySplit() {
         return null;
      }

      public long estimateSize() {
         return this.consumed ? 0L : 1L;
      }

      public int characteristics() {
         return 17749;
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         if (!this.consumed) {
            this.consumed = true;
            action.accept(this.element);
         }

      }

      public LongComparator getComparator() {
         return this.comparator;
      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else if (n != 0L && !this.consumed) {
            this.consumed = true;
            return 1L;
         } else {
            return 0L;
         }
      }
   }

   private static class ArraySpliterator implements LongSpliterator {
      private static final int BASE_CHARACTERISTICS = 16720;
      final long[] array;
      private final int offset;
      private int length;
      private int curr;
      final int characteristics;

      public ArraySpliterator(long[] array, int offset, int length, int additionalCharacteristics) {
         this.array = array;
         this.offset = offset;
         this.length = length;
         this.characteristics = 16720 | additionalCharacteristics;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         if (this.curr >= this.length) {
            return false;
         } else {
            Objects.requireNonNull(action);
            action.accept(this.array[this.offset + this.curr++]);
            return true;
         }
      }

      public long estimateSize() {
         return (long)(this.length - this.curr);
      }

      public int characteristics() {
         return this.characteristics;
      }

      protected ArraySpliterator makeForSplit(int newOffset, int newLength) {
         return new ArraySpliterator(this.array, newOffset, newLength, this.characteristics);
      }

      public LongSpliterator trySplit() {
         int retLength = this.length - this.curr >> 1;
         if (retLength <= 1) {
            return null;
         } else {
            int myNewCurr = this.curr + retLength;
            int retOffset = this.offset + this.curr;
            this.curr = myNewCurr;
            return this.makeForSplit(retOffset, retLength);
         }
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);

         while(this.curr < this.length) {
            action.accept(this.array[this.offset + this.curr]);
            ++this.curr;
         }

      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else if (this.curr >= this.length) {
            return 0L;
         } else {
            int remaining = this.length - this.curr;
            if (n < (long)remaining) {
               this.curr = SafeMath.safeLongToInt((long)this.curr + n);
               return n;
            } else {
               n = (long)remaining;
               this.curr = this.length;
               return n;
            }
         }
      }
   }

   private static class ArraySpliteratorWithComparator extends ArraySpliterator {
      private final LongComparator comparator;

      public ArraySpliteratorWithComparator(long[] array, int offset, int length, int additionalCharacteristics, LongComparator comparator) {
         super(array, offset, length, additionalCharacteristics | 20);
         this.comparator = comparator;
      }

      protected ArraySpliteratorWithComparator makeForSplit(int newOffset, int newLength) {
         return new ArraySpliteratorWithComparator(this.array, newOffset, newLength, this.characteristics, this.comparator);
      }

      public LongComparator getComparator() {
         return this.comparator;
      }
   }

   private static class SpliteratorWrapper implements LongSpliterator {
      final Spliterator i;

      public SpliteratorWrapper(Spliterator i) {
         this.i = i;
      }

      public boolean tryAdvance(LongConsumer action) {
         return this.i.tryAdvance(action);
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         Spliterator var10000 = this.i;
         Consumer var10001;
         if (action instanceof Consumer) {
            var10001 = (Consumer)action;
         } else {
            Objects.requireNonNull(action);
            var10001 = action::accept;
         }

         return var10000.tryAdvance(var10001);
      }

      /** @deprecated */
      @Deprecated
      public boolean tryAdvance(Consumer action) {
         return this.i.tryAdvance(action);
      }

      public void forEachRemaining(LongConsumer action) {
         this.i.forEachRemaining(action);
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         Spliterator var10000 = this.i;
         Consumer var10001;
         if (action instanceof Consumer) {
            var10001 = (Consumer)action;
         } else {
            Objects.requireNonNull(action);
            var10001 = action::accept;
         }

         var10000.forEachRemaining(var10001);
      }

      /** @deprecated */
      @Deprecated
      public void forEachRemaining(Consumer action) {
         this.i.forEachRemaining(action);
      }

      public long estimateSize() {
         return this.i.estimateSize();
      }

      public int characteristics() {
         return this.i.characteristics();
      }

      public LongComparator getComparator() {
         return LongComparators.asLongComparator(this.i.getComparator());
      }

      public LongSpliterator trySplit() {
         Spliterator<Long> innerSplit = this.i.trySplit();
         return innerSplit == null ? null : new SpliteratorWrapper(innerSplit);
      }
   }

   private static class SpliteratorWrapperWithComparator extends SpliteratorWrapper {
      final LongComparator comparator;

      public SpliteratorWrapperWithComparator(Spliterator i, LongComparator comparator) {
         super(i);
         this.comparator = comparator;
      }

      public LongComparator getComparator() {
         return this.comparator;
      }

      public LongSpliterator trySplit() {
         Spliterator<Long> innerSplit = this.i.trySplit();
         return innerSplit == null ? null : new SpliteratorWrapperWithComparator(innerSplit, this.comparator);
      }
   }

   private static class PrimitiveSpliteratorWrapper implements LongSpliterator {
      final Spliterator.OfLong i;

      public PrimitiveSpliteratorWrapper(Spliterator.OfLong i) {
         this.i = i;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         return this.i.tryAdvance(action);
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         this.i.forEachRemaining(action);
      }

      public long estimateSize() {
         return this.i.estimateSize();
      }

      public int characteristics() {
         return this.i.characteristics();
      }

      public LongComparator getComparator() {
         return LongComparators.asLongComparator(this.i.getComparator());
      }

      public LongSpliterator trySplit() {
         Spliterator.OfLong innerSplit = this.i.trySplit();
         return innerSplit == null ? null : new PrimitiveSpliteratorWrapper(innerSplit);
      }
   }

   private static class PrimitiveSpliteratorWrapperWithComparator extends PrimitiveSpliteratorWrapper {
      final LongComparator comparator;

      public PrimitiveSpliteratorWrapperWithComparator(Spliterator.OfLong i, LongComparator comparator) {
         super(i);
         this.comparator = comparator;
      }

      public LongComparator getComparator() {
         return this.comparator;
      }

      public LongSpliterator trySplit() {
         Spliterator.OfLong innerSplit = this.i.trySplit();
         return innerSplit == null ? null : new PrimitiveSpliteratorWrapperWithComparator(innerSplit, this.comparator);
      }
   }

   public abstract static class AbstractIndexBasedSpliterator extends AbstractLongSpliterator {
      protected int pos;

      protected AbstractIndexBasedSpliterator(int initialPos) {
         this.pos = initialPos;
      }

      protected abstract long get(int var1);

      protected abstract int getMaxPos();

      protected abstract LongSpliterator makeForSplit(int var1, int var2);

      protected int computeSplitPoint() {
         return this.pos + (this.getMaxPos() - this.pos) / 2;
      }

      private void splitPointCheck(int splitPoint, int observedMax) {
         if (splitPoint < this.pos || splitPoint > observedMax) {
            throw new IndexOutOfBoundsException("splitPoint " + splitPoint + " outside of range of current position " + this.pos + " and range end " + observedMax);
         }
      }

      public int characteristics() {
         return 16720;
      }

      public long estimateSize() {
         return (long)this.getMaxPos() - (long)this.pos;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         if (this.pos >= this.getMaxPos()) {
            return false;
         } else {
            action.accept(this.get(this.pos++));
            return true;
         }
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         for(int max = this.getMaxPos(); this.pos < max; ++this.pos) {
            action.accept(this.get(this.pos));
         }

      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else {
            int max = this.getMaxPos();
            if (this.pos >= max) {
               return 0L;
            } else {
               int remaining = max - this.pos;
               if (n < (long)remaining) {
                  this.pos = SafeMath.safeLongToInt((long)this.pos + n);
                  return n;
               } else {
                  n = (long)remaining;
                  this.pos = max;
                  return n;
               }
            }
         }
      }

      public LongSpliterator trySplit() {
         int max = this.getMaxPos();
         int splitPoint = this.computeSplitPoint();
         if (splitPoint != this.pos && splitPoint != max) {
            this.splitPointCheck(splitPoint, max);
            int oldPos = this.pos;
            LongSpliterator maybeSplit = this.makeForSplit(oldPos, splitPoint);
            if (maybeSplit != null) {
               this.pos = splitPoint;
            }

            return maybeSplit;
         } else {
            return null;
         }
      }
   }

   public abstract static class EarlyBindingSizeIndexBasedSpliterator extends AbstractIndexBasedSpliterator {
      protected final int maxPos;

      protected EarlyBindingSizeIndexBasedSpliterator(int initialPos, int maxPos) {
         super(initialPos);
         this.maxPos = maxPos;
      }

      protected final int getMaxPos() {
         return this.maxPos;
      }
   }

   public abstract static class LateBindingSizeIndexBasedSpliterator extends AbstractIndexBasedSpliterator {
      protected int maxPos = -1;
      private boolean maxPosFixed;

      protected LateBindingSizeIndexBasedSpliterator(int initialPos) {
         super(initialPos);
         this.maxPosFixed = false;
      }

      protected LateBindingSizeIndexBasedSpliterator(int initialPos, int fixedMaxPos) {
         super(initialPos);
         this.maxPos = fixedMaxPos;
         this.maxPosFixed = true;
      }

      protected abstract int getMaxPosFromBackingStore();

      protected final int getMaxPos() {
         return this.maxPosFixed ? this.maxPos : this.getMaxPosFromBackingStore();
      }

      public LongSpliterator trySplit() {
         LongSpliterator maybeSplit = super.trySplit();
         if (!this.maxPosFixed && maybeSplit != null) {
            this.maxPos = this.getMaxPosFromBackingStore();
            this.maxPosFixed = true;
         }

         return maybeSplit;
      }
   }

   private static class IntervalSpliterator implements LongSpliterator {
      private static final int DONT_SPLIT_THRESHOLD = 2;
      private static final long MAX_SPLIT_SIZE = 1073741824L;
      private static final int CHARACTERISTICS = 17749;
      private long curr;
      private long to;

      public IntervalSpliterator(long from, long to) {
         this.curr = from;
         this.to = to;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         if (this.curr >= this.to) {
            return false;
         } else {
            action.accept((long)(this.curr++));
            return true;
         }
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);

         while(this.curr < this.to) {
            action.accept(this.curr);
            ++this.curr;
         }

      }

      public long estimateSize() {
         return this.to - this.curr;
      }

      public int characteristics() {
         return 17749;
      }

      public LongComparator getComparator() {
         return null;
      }

      public LongSpliterator trySplit() {
         long remaining = this.to - this.curr;
         long mid = this.curr + (remaining >> 1);
         if (remaining > 2147483648L || remaining < 0L) {
            mid = this.curr + 1073741824L;
         }

         if (remaining >= 0L && remaining <= 2L) {
            return null;
         } else {
            long old_curr = this.curr;
            this.curr = mid;
            return new IntervalSpliterator(old_curr, mid);
         }
      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else if (this.curr >= this.to) {
            return 0L;
         } else {
            long newCurr = this.curr + n;
            if (newCurr <= this.to && newCurr >= this.curr) {
               this.curr = newCurr;
               return n;
            } else {
               n = this.to - this.curr;
               this.curr = this.to;
               return n;
            }
         }
      }
   }

   private static class SpliteratorConcatenator implements LongSpliterator {
      private static final int EMPTY_CHARACTERISTICS = 16448;
      private static final int CHARACTERISTICS_NOT_SUPPORTED_WHILE_MULTIPLE = 5;
      final LongSpliterator[] a;
      int offset;
      int length;
      long remainingEstimatedExceptCurrent = Long.MAX_VALUE;
      int characteristics = 0;

      public SpliteratorConcatenator(LongSpliterator[] a, int offset, int length) {
         this.a = a;
         this.offset = offset;
         this.length = length;
         this.remainingEstimatedExceptCurrent = this.recomputeRemaining();
         this.characteristics = this.computeCharacteristics();
      }

      private long recomputeRemaining() {
         int curLength = this.length - 1;
         int curOffset = this.offset + 1;
         long result = 0L;

         while(curLength > 0) {
            long cur = this.a[curOffset++].estimateSize();
            --curLength;
            if (cur == Long.MAX_VALUE) {
               return Long.MAX_VALUE;
            }

            result += cur;
            if (result == Long.MAX_VALUE || result < 0L) {
               return Long.MAX_VALUE;
            }
         }

         return result;
      }

      private int computeCharacteristics() {
         if (this.length <= 0) {
            return 16448;
         } else {
            int current = -1;
            int curLength = this.length;
            int curOffset = this.offset;
            if (curLength > 1) {
               current &= -6;
            }

            while(curLength > 0) {
               current &= this.a[curOffset++].characteristics();
               --curLength;
            }

            return current;
         }
      }

      private void advanceNextSpliterator() {
         if (this.length <= 0) {
            throw new AssertionError("advanceNextSpliterator() called with none remaining");
         } else {
            ++this.offset;
            --this.length;
            this.remainingEstimatedExceptCurrent = this.recomputeRemaining();
         }
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         boolean any = false;

         while(this.length > 0) {
            if (this.a[this.offset].tryAdvance((java.util.function.LongConsumer)action)) {
               any = true;
               break;
            }

            this.advanceNextSpliterator();
         }

         return any;
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         while(this.length > 0) {
            this.a[this.offset].forEachRemaining((java.util.function.LongConsumer)action);
            this.advanceNextSpliterator();
         }

      }

      /** @deprecated */
      @Deprecated
      public void forEachRemaining(Consumer action) {
         while(this.length > 0) {
            this.a[this.offset].forEachRemaining(action);
            this.advanceNextSpliterator();
         }

      }

      public long estimateSize() {
         if (this.length <= 0) {
            return 0L;
         } else {
            long est = this.a[this.offset].estimateSize() + this.remainingEstimatedExceptCurrent;
            return est < 0L ? Long.MAX_VALUE : est;
         }
      }

      public int characteristics() {
         return this.characteristics;
      }

      public LongComparator getComparator() {
         if (this.length == 1 && (this.characteristics & 4) != 0) {
            return this.a[this.offset].getComparator();
         } else {
            throw new IllegalStateException();
         }
      }

      public LongSpliterator trySplit() {
         switch (this.length) {
            case 0:
               return null;
            case 1:
               LongSpliterator split = this.a[this.offset].trySplit();
               this.characteristics = this.a[this.offset].characteristics();
               return split;
            case 2:
               LongSpliterator split = this.a[this.offset++];
               --this.length;
               this.characteristics = this.a[this.offset].characteristics();
               this.remainingEstimatedExceptCurrent = 0L;
               return split;
            default:
               int mid = this.length >> 1;
               int ret_offset = this.offset;
               int new_offset = this.offset + mid;
               int new_length = this.length - mid;
               this.offset = new_offset;
               this.length = new_length;
               this.remainingEstimatedExceptCurrent = this.recomputeRemaining();
               this.characteristics = this.computeCharacteristics();
               return new SpliteratorConcatenator(this.a, ret_offset, mid);
         }
      }

      public long skip(long n) {
         long skipped = 0L;
         if (this.length <= 0) {
            return 0L;
         } else {
            while(skipped < n && this.length >= 0) {
               long curSkipped = this.a[this.offset].skip(n - skipped);
               skipped += curSkipped;
               if (skipped < n) {
                  this.advanceNextSpliterator();
               }
            }

            return skipped;
         }
      }
   }

   private static class SpliteratorFromIterator implements LongSpliterator {
      private static final int BATCH_INCREMENT_SIZE = 1024;
      private static final int BATCH_MAX_SIZE = 33554432;
      private final LongIterator iter;
      final int characteristics;
      private final boolean knownSize;
      private long size = Long.MAX_VALUE;
      private int nextBatchSize = 1024;
      private LongSpliterator delegate = null;

      SpliteratorFromIterator(LongIterator iter, int characteristics) {
         this.iter = iter;
         this.characteristics = 256 | characteristics;
         this.knownSize = false;
      }

      SpliteratorFromIterator(LongIterator iter, long size, int additionalCharacteristics) {
         this.iter = iter;
         this.knownSize = true;
         this.size = size;
         if ((additionalCharacteristics & 4096) != 0) {
            this.characteristics = 256 | additionalCharacteristics;
         } else {
            this.characteristics = 16704 | additionalCharacteristics;
         }

      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         if (this.delegate != null) {
            boolean hadRemaining = this.delegate.tryAdvance((java.util.function.LongConsumer)action);
            if (!hadRemaining) {
               this.delegate = null;
            }

            return hadRemaining;
         } else if (!this.iter.hasNext()) {
            return false;
         } else {
            --this.size;
            action.accept(this.iter.nextLong());
            return true;
         }
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         if (this.delegate != null) {
            this.delegate.forEachRemaining((java.util.function.LongConsumer)action);
            this.delegate = null;
         }

         this.iter.forEachRemaining((java.util.function.LongConsumer)action);
         this.size = 0L;
      }

      public long estimateSize() {
         if (this.delegate != null) {
            return this.delegate.estimateSize();
         } else if (!this.iter.hasNext()) {
            return 0L;
         } else {
            return this.knownSize && this.size >= 0L ? this.size : Long.MAX_VALUE;
         }
      }

      public int characteristics() {
         return this.characteristics;
      }

      protected LongSpliterator makeForSplit(long[] batch, int len) {
         return LongSpliterators.wrap(batch, 0, len, this.characteristics);
      }

      public LongSpliterator trySplit() {
         if (!this.iter.hasNext()) {
            return null;
         } else {
            int batchSizeEst = this.knownSize && this.size > 0L ? (int)Math.min((long)this.nextBatchSize, this.size) : this.nextBatchSize;
            long[] batch = new long[batchSizeEst];

            int actualSeen;
            for(actualSeen = 0; actualSeen < batchSizeEst && this.iter.hasNext(); --this.size) {
               batch[actualSeen++] = this.iter.nextLong();
            }

            if (batchSizeEst < this.nextBatchSize && this.iter.hasNext()) {
               for(batch = Arrays.copyOf(batch, this.nextBatchSize); this.iter.hasNext() && actualSeen < this.nextBatchSize; --this.size) {
                  batch[actualSeen++] = this.iter.nextLong();
               }
            }

            this.nextBatchSize = Math.min(33554432, this.nextBatchSize + 1024);
            LongSpliterator split = this.makeForSplit(batch, actualSeen);
            if (!this.iter.hasNext()) {
               this.delegate = split;
               return split.trySplit();
            } else {
               return split;
            }
         }
      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else if (this.iter instanceof LongBigListIterator) {
            long skipped = ((LongBigListIterator)this.iter).skip(n);
            this.size -= skipped;
            return skipped;
         } else {
            long skippedSoFar;
            int skipped;
            for(skippedSoFar = 0L; skippedSoFar < n && this.iter.hasNext(); skippedSoFar += (long)skipped) {
               skipped = this.iter.skip(SafeMath.safeLongToInt(Math.min(n, 2147483647L)));
               this.size -= (long)skipped;
            }

            return skippedSoFar;
         }
      }
   }

   private static class SpliteratorFromIteratorWithComparator extends SpliteratorFromIterator {
      private final LongComparator comparator;

      SpliteratorFromIteratorWithComparator(LongIterator iter, int additionalCharacteristics, LongComparator comparator) {
         super(iter, additionalCharacteristics | 20);
         this.comparator = comparator;
      }

      SpliteratorFromIteratorWithComparator(LongIterator iter, long size, int additionalCharacteristics, LongComparator comparator) {
         super(iter, size, additionalCharacteristics | 20);
         this.comparator = comparator;
      }

      public LongComparator getComparator() {
         return this.comparator;
      }

      protected LongSpliterator makeForSplit(long[] array, int len) {
         return LongSpliterators.wrapPreSorted(array, 0, len, this.characteristics, this.comparator);
      }
   }

   private static final class IteratorFromSpliterator implements LongIterator, LongConsumer {
      private final LongSpliterator spliterator;
      private long holder = 0L;
      private boolean hasPeeked = false;

      IteratorFromSpliterator(LongSpliterator spliterator) {
         this.spliterator = spliterator;
      }

      public void accept(long item) {
         this.holder = item;
      }

      public boolean hasNext() {
         if (this.hasPeeked) {
            return true;
         } else {
            boolean hadElement = this.spliterator.tryAdvance((LongConsumer)this);
            if (!hadElement) {
               return false;
            } else {
               this.hasPeeked = true;
               return true;
            }
         }
      }

      public long nextLong() {
         if (this.hasPeeked) {
            this.hasPeeked = false;
            return this.holder;
         } else {
            boolean hadElement = this.spliterator.tryAdvance((LongConsumer)this);
            if (!hadElement) {
               throw new NoSuchElementException();
            } else {
               return this.holder;
            }
         }
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         if (this.hasPeeked) {
            this.hasPeeked = false;
            action.accept(this.holder);
         }

         this.spliterator.forEachRemaining((java.util.function.LongConsumer)action);
      }

      public int skip(int n) {
         if (n < 0) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else {
            int skipped = 0;
            if (this.hasPeeked) {
               this.hasPeeked = false;
               this.spliterator.skip(1L);
               ++skipped;
               --n;
            }

            if (n > 0) {
               skipped += SafeMath.safeLongToInt(this.spliterator.skip((long)n));
            }

            return skipped;
         }
      }
   }

   private static final class ByteSpliteratorWrapper implements LongSpliterator {
      final ByteSpliterator spliterator;

      public ByteSpliteratorWrapper(ByteSpliterator spliterator) {
         this.spliterator = spliterator;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         ByteSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         return var10000.tryAdvance(action::accept);
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         ByteSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         var10000.forEachRemaining(action::accept);
      }

      public long estimateSize() {
         return this.spliterator.estimateSize();
      }

      public int characteristics() {
         return this.spliterator.characteristics();
      }

      public long skip(long n) {
         return this.spliterator.skip(n);
      }

      public LongSpliterator trySplit() {
         ByteSpliterator possibleSplit = this.spliterator.trySplit();
         return possibleSplit == null ? null : new ByteSpliteratorWrapper(possibleSplit);
      }
   }

   private static final class ShortSpliteratorWrapper implements LongSpliterator {
      final ShortSpliterator spliterator;

      public ShortSpliteratorWrapper(ShortSpliterator spliterator) {
         this.spliterator = spliterator;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         ShortSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         return var10000.tryAdvance(action::accept);
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         ShortSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         var10000.forEachRemaining(action::accept);
      }

      public long estimateSize() {
         return this.spliterator.estimateSize();
      }

      public int characteristics() {
         return this.spliterator.characteristics();
      }

      public long skip(long n) {
         return this.spliterator.skip(n);
      }

      public LongSpliterator trySplit() {
         ShortSpliterator possibleSplit = this.spliterator.trySplit();
         return possibleSplit == null ? null : new ShortSpliteratorWrapper(possibleSplit);
      }
   }

   private static final class CharSpliteratorWrapper implements LongSpliterator {
      final CharSpliterator spliterator;

      public CharSpliteratorWrapper(CharSpliterator spliterator) {
         this.spliterator = spliterator;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         CharSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         return var10000.tryAdvance(action::accept);
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         CharSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         var10000.forEachRemaining(action::accept);
      }

      public long estimateSize() {
         return this.spliterator.estimateSize();
      }

      public int characteristics() {
         return this.spliterator.characteristics();
      }

      public long skip(long n) {
         return this.spliterator.skip(n);
      }

      public LongSpliterator trySplit() {
         CharSpliterator possibleSplit = this.spliterator.trySplit();
         return possibleSplit == null ? null : new CharSpliteratorWrapper(possibleSplit);
      }
   }

   private static final class IntSpliteratorWrapper implements LongSpliterator {
      final IntSpliterator spliterator;

      public IntSpliteratorWrapper(IntSpliterator spliterator) {
         this.spliterator = spliterator;
      }

      public boolean tryAdvance(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         IntSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         return var10000.tryAdvance(action::accept);
      }

      public void forEachRemaining(java.util.function.LongConsumer action) {
         Objects.requireNonNull(action);
         IntSpliterator var10000 = this.spliterator;
         Objects.requireNonNull(action);
         var10000.forEachRemaining(action::accept);
      }

      public long estimateSize() {
         return this.spliterator.estimateSize();
      }

      public int characteristics() {
         return this.spliterator.characteristics();
      }

      public long skip(long n) {
         return this.spliterator.skip(n);
      }

      public LongSpliterator trySplit() {
         IntSpliterator possibleSplit = this.spliterator.trySplit();
         return possibleSplit == null ? null : new IntSpliteratorWrapper(possibleSplit);
      }
   }
}
