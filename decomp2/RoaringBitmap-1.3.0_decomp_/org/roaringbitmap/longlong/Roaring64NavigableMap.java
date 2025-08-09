package org.roaringbitmap.longlong;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import org.roaringbitmap.BitmapDataProvider;
import org.roaringbitmap.BitmapDataProviderSupplier;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.IntIterator;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.RoaringBitmapPrivate;
import org.roaringbitmap.RoaringBitmapSupplier;
import org.roaringbitmap.Util;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmapPrivate;

public class Roaring64NavigableMap implements Externalizable, LongBitmapDataProvider {
   public static final int SERIALIZATION_MODE_LEGACY = 0;
   public static final int SERIALIZATION_MODE_PORTABLE = 1;
   public static int SERIALIZATION_MODE = 0;
   private NavigableMap highToBitmap;
   private boolean signedLongs;
   private transient BitmapDataProviderSupplier supplier;
   private transient boolean doCacheCardinalities;
   private transient int firstHighNotValid;
   private transient boolean allValid;
   private transient long[] sortedCumulatedCardinality;
   private transient int[] sortedHighs;
   private transient Map.Entry latestAddedHigh;
   private static final boolean DEFAULT_ORDER_IS_SIGNED = false;
   private static final boolean DEFAULT_CARDINALITIES_ARE_CACHED = true;

   public Roaring64NavigableMap() {
      this(false);
   }

   public Roaring64NavigableMap(boolean signedLongs) {
      this(signedLongs, true);
   }

   public Roaring64NavigableMap(boolean signedLongs, boolean cacheCardinalities) {
      this(signedLongs, cacheCardinalities, new RoaringBitmapSupplier());
   }

   public Roaring64NavigableMap(BitmapDataProviderSupplier supplier) {
      this(false, true, supplier);
   }

   public Roaring64NavigableMap(boolean signedLongs, BitmapDataProviderSupplier supplier) {
      this(signedLongs, true, supplier);
   }

   public Roaring64NavigableMap(boolean signedLongs, boolean cacheCardinalities, BitmapDataProviderSupplier supplier) {
      this.signedLongs = false;
      this.doCacheCardinalities = true;
      this.firstHighNotValid = this.highestHigh() + 1;
      this.allValid = false;
      this.sortedCumulatedCardinality = new long[0];
      this.sortedHighs = new int[0];
      this.latestAddedHigh = null;
      this.signedLongs = signedLongs;
      this.supplier = supplier;
      if (signedLongs) {
         this.highToBitmap = new TreeMap();
      } else {
         this.highToBitmap = new TreeMap(RoaringIntPacking.unsignedComparator());
      }

      this.doCacheCardinalities = cacheCardinalities;
      this.resetPerfHelpers();
   }

   private void resetPerfHelpers() {
      this.firstHighNotValid = RoaringIntPacking.highestHigh(this.signedLongs) + 1;
      this.allValid = false;
      this.sortedCumulatedCardinality = new long[0];
      this.sortedHighs = new int[0];
      this.latestAddedHigh = null;
   }

   NavigableMap getHighToBitmap() {
      return this.highToBitmap;
   }

   int getLowestInvalidHigh() {
      return this.firstHighNotValid;
   }

   long[] getSortedCumulatedCardinality() {
      return this.sortedCumulatedCardinality;
   }

   private static String getClassName(BitmapDataProvider bitmap) {
      return bitmap == null ? "null" : bitmap.getClass().getName();
   }

   public void addLong(long x) {
      int high = this.high(x);
      int low = this.low(x);
      Map.Entry<Integer, BitmapDataProvider> local = this.latestAddedHigh;
      BitmapDataProvider bitmap;
      if (local != null && (Integer)local.getKey() == high) {
         bitmap = (BitmapDataProvider)local.getValue();
      } else {
         bitmap = (BitmapDataProvider)this.highToBitmap.get(high);
         if (bitmap == null) {
            bitmap = this.newRoaringBitmap();
            this.pushBitmapForHigh(high, bitmap);
         }

         this.latestAddedHigh = new AbstractMap.SimpleImmutableEntry(high, bitmap);
      }

      bitmap.add(low);
      this.invalidateAboveHigh(high);
   }

   public void addInt(int x) {
      this.addLong(Util.toUnsignedLong(x));
   }

   private BitmapDataProvider newRoaringBitmap() {
      return this.supplier.newEmpty();
   }

   private void invalidateAboveHigh(int high) {
      if (this.compare(this.firstHighNotValid, high) > 0) {
         this.firstHighNotValid = high;
         int indexNotValid = this.binarySearch(this.sortedHighs, this.firstHighNotValid);
         int indexAfterWhichToReset;
         if (indexNotValid >= 0) {
            indexAfterWhichToReset = indexNotValid;
         } else {
            indexAfterWhichToReset = -indexNotValid - 1;
         }

         Arrays.fill(this.sortedHighs, indexAfterWhichToReset, this.sortedHighs.length, this.highestHigh());
      }

      this.allValid = false;
   }

   private int compare(int x, int y) {
      return this.signedLongs ? Integer.compare(x, y) : RoaringIntPacking.compareUnsigned(x, y);
   }

   private void pushBitmapForHigh(int high, BitmapDataProvider bitmap) {
      BitmapDataProvider previous = (BitmapDataProvider)this.highToBitmap.put(high, bitmap);

      assert previous == null : "Should push only not-existing high";

   }

   private int low(long id) {
      return RoaringIntPacking.low(id);
   }

   private int high(long id) {
      return RoaringIntPacking.high(id);
   }

   public long getLongCardinality() {
      if (this.doCacheCardinalities) {
         if (this.highToBitmap.isEmpty()) {
            return 0L;
         } else {
            int indexOk = this.ensureCumulatives(this.highestHigh());
            return this.highToBitmap.isEmpty() ? 0L : this.sortedCumulatedCardinality[indexOk - 1];
         }
      } else {
         long cardinality = 0L;

         for(BitmapDataProvider bitmap : this.highToBitmap.values()) {
            cardinality += bitmap.getLongCardinality();
         }

         return cardinality;
      }
   }

   public int getIntCardinality() throws UnsupportedOperationException {
      long cardinality = this.getLongCardinality();
      if (cardinality > 2147483647L) {
         throw new UnsupportedOperationException("Cannot call .getIntCardinality as the cardinality is bigger than Integer.MAX_VALUE");
      } else {
         return (int)cardinality;
      }
   }

   public long select(long j) throws IllegalArgumentException {
      if (!this.doCacheCardinalities) {
         return this.selectNoCache(j);
      } else {
         int indexOk = this.ensureCumulatives(this.highestHigh());
         if (this.highToBitmap.isEmpty()) {
            return this.throwSelectInvalidIndex(j);
         } else {
            int position = Arrays.binarySearch(this.sortedCumulatedCardinality, 0, indexOk, j);
            if (position >= 0) {
               if (position == indexOk - 1) {
                  return this.throwSelectInvalidIndex(j);
               } else {
                  int high = this.sortedHighs[position + 1];
                  BitmapDataProvider nextBitmap = (BitmapDataProvider)this.highToBitmap.get(high);
                  return RoaringIntPacking.pack(high, nextBitmap.select(0));
               }
            } else {
               int insertionPoint = -position - 1;
               long previousBucketCardinality;
               if (insertionPoint == 0) {
                  previousBucketCardinality = 0L;
               } else {
                  if (insertionPoint >= indexOk) {
                     return this.throwSelectInvalidIndex(j);
                  }

                  previousBucketCardinality = this.sortedCumulatedCardinality[insertionPoint - 1];
               }

               int givenBitmapSelect = (int)(j - previousBucketCardinality);
               int high = this.sortedHighs[insertionPoint];
               BitmapDataProvider lowBitmap = (BitmapDataProvider)this.highToBitmap.get(high);
               int low = lowBitmap.select(givenBitmapSelect);
               return RoaringIntPacking.pack(high, low);
            }
         }
      }
   }

   private long selectNoCache(long j) {
      long left = j;

      for(Map.Entry entry : this.highToBitmap.entrySet()) {
         long lowCardinality = (long)((BitmapDataProvider)entry.getValue()).getCardinality();
         if (left < lowCardinality) {
            int leftAsUnsignedInt = (int)left;
            return RoaringIntPacking.pack((Integer)entry.getKey(), ((BitmapDataProvider)entry.getValue()).select(leftAsUnsignedInt));
         }

         left -= lowCardinality;
      }

      return this.throwSelectInvalidIndex(j);
   }

   private long throwSelectInvalidIndex(long j) {
      throw new IllegalArgumentException("select " + j + " when the cardinality is " + this.getLongCardinality());
   }

   public Iterator iterator() {
      final LongIterator it = this.getLongIterator();
      return new Iterator() {
         public boolean hasNext() {
            return it.hasNext();
         }

         public Long next() {
            return it.next();
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }

   public void forEach(final LongConsumer lc) {
      for(final Map.Entry highEntry : this.highToBitmap.entrySet()) {
         ((BitmapDataProvider)highEntry.getValue()).forEach(new IntConsumer() {
            public void accept(int low) {
               lc.accept(RoaringIntPacking.pack((Integer)highEntry.getKey(), low));
            }
         });
      }

   }

   public long rankLong(long id) {
      int high = RoaringIntPacking.high(id);
      int low = RoaringIntPacking.low(id);
      if (!this.doCacheCardinalities) {
         return this.rankLongNoCache(high, low);
      } else {
         int indexOk = this.ensureCumulatives(high);
         int highPosition = this.binarySearch(this.sortedHighs, 0, indexOk, high);
         if (highPosition >= 0) {
            long previousBucketCardinality;
            if (highPosition == 0) {
               previousBucketCardinality = 0L;
            } else {
               previousBucketCardinality = this.sortedCumulatedCardinality[highPosition - 1];
            }

            BitmapDataProvider lowBitmap = (BitmapDataProvider)this.highToBitmap.get(this.sortedHighs[highPosition]);
            return previousBucketCardinality + lowBitmap.rankLong(low);
         } else {
            int insertionPoint = -highPosition - 1;
            return insertionPoint == 0 ? 0L : this.sortedCumulatedCardinality[insertionPoint - 1];
         }
      }
   }

   private long rankLongNoCache(int high, int low) {
      long result = 0L;
      BitmapDataProvider lastBitmap = (BitmapDataProvider)this.highToBitmap.get(high);
      if (lastBitmap == null) {
         for(Map.Entry bitmap : this.highToBitmap.entrySet()) {
            if ((Integer)bitmap.getKey() > high) {
               break;
            }

            result += ((BitmapDataProvider)bitmap.getValue()).getLongCardinality();
         }
      } else {
         for(BitmapDataProvider bitmap : this.highToBitmap.values()) {
            if (bitmap == lastBitmap) {
               result += bitmap.rankLong(low);
               break;
            }

            result += bitmap.getLongCardinality();
         }
      }

      return result;
   }

   protected int ensureCumulatives(int high) {
      if (this.allValid) {
         return this.highToBitmap.size();
      } else if (this.compare(high, this.firstHighNotValid) < 0) {
         int position = this.binarySearch(this.sortedHighs, high);
         if (position >= 0) {
            return position + 1;
         } else {
            int insertionPosition = -position - 1;
            return insertionPosition;
         }
      } else {
         SortedMap<Integer, BitmapDataProvider> tailMap = this.highToBitmap.tailMap(this.firstHighNotValid, true);
         int indexOk = this.highToBitmap.size() - tailMap.size();
         Iterator<Map.Entry<Integer, BitmapDataProvider>> it = tailMap.entrySet().iterator();

         while(it.hasNext()) {
            Map.Entry<Integer, BitmapDataProvider> e = (Map.Entry)it.next();
            int currentHigh = (Integer)e.getKey();
            if (this.compare(currentHigh, high) > 0) {
               break;
            }

            if (((BitmapDataProvider)e.getValue()).isEmpty()) {
               if (this.latestAddedHigh != null && (Integer)this.latestAddedHigh.getKey() == currentHigh) {
                  this.latestAddedHigh = null;
               }

               it.remove();
            } else {
               this.ensureOne(e, currentHigh, indexOk);
               ++indexOk;
            }
         }

         if (this.highToBitmap.isEmpty() || indexOk == this.highToBitmap.size()) {
            this.allValid = true;
         }

         return indexOk;
      }
   }

   private int binarySearch(int[] array, int key) {
      return this.signedLongs ? Arrays.binarySearch(array, key) : unsignedBinarySearch(array, 0, array.length, key, RoaringIntPacking.unsignedComparator());
   }

   private int binarySearch(int[] array, int from, int to, int key) {
      return this.signedLongs ? Arrays.binarySearch(array, from, to, key) : unsignedBinarySearch(array, from, to, key, RoaringIntPacking.unsignedComparator());
   }

   private static int unsignedBinarySearch(int[] a, int fromIndex, int toIndex, int key, Comparator c) {
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         int midVal = a[mid];
         int cmp = c.compare(midVal, key);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   private void ensureOne(Map.Entry e, int currentHigh, int indexOk) {
      assert indexOk <= this.sortedHighs.length : indexOk + " is bigger than " + this.sortedHighs.length;

      int index;
      if (indexOk == 0) {
         if (this.sortedHighs.length == 0) {
            index = -1;
         } else {
            index = -1;
         }
      } else if (indexOk < this.sortedHighs.length) {
         index = -indexOk - 1;
      } else {
         index = -this.sortedHighs.length - 1;
      }

      assert index == this.binarySearch(this.sortedHighs, 0, indexOk, currentHigh) : "Computed " + index + " differs from dummy binary-search index: " + this.binarySearch(this.sortedHighs, 0, indexOk, currentHigh);

      if (index >= 0) {
         throw new IllegalStateException("Unexpectedly found " + currentHigh + " in " + Arrays.toString(this.sortedHighs) + " strictly before index" + indexOk);
      } else {
         int insertionPosition = -index - 1;
         if (insertionPosition >= this.sortedHighs.length) {
            int previousSize = this.sortedHighs.length;
            int newSize = Math.min(Integer.MAX_VALUE, this.sortedHighs.length * 2 + 1);
            this.sortedHighs = Arrays.copyOf(this.sortedHighs, newSize);
            this.sortedCumulatedCardinality = Arrays.copyOf(this.sortedCumulatedCardinality, newSize);
            Arrays.fill(this.sortedHighs, previousSize, this.sortedHighs.length, this.highestHigh());
            Arrays.fill(this.sortedCumulatedCardinality, previousSize, this.sortedHighs.length, Long.MAX_VALUE);
         }

         this.sortedHighs[insertionPosition] = currentHigh;
         long previousCardinality;
         if (insertionPosition >= 1) {
            previousCardinality = this.sortedCumulatedCardinality[insertionPosition - 1];
         } else {
            previousCardinality = 0L;
         }

         this.sortedCumulatedCardinality[insertionPosition] = previousCardinality + ((BitmapDataProvider)e.getValue()).getLongCardinality();
         if (currentHigh == this.highestHigh()) {
            this.firstHighNotValid = currentHigh;
         } else {
            this.firstHighNotValid = currentHigh + 1;
         }

      }
   }

   private int highestHigh() {
      return RoaringIntPacking.highestHigh(this.signedLongs);
   }

   public void naivelazyor(Roaring64NavigableMap x2) {
      if (this != x2) {
         for(Map.Entry e2 : x2.highToBitmap.entrySet()) {
            Integer high = (Integer)e2.getKey();
            BitmapDataProvider lowBitmap1 = (BitmapDataProvider)this.highToBitmap.get(high);
            BitmapDataProvider lowBitmap2 = (BitmapDataProvider)e2.getValue();
            if (lowBitmap1 != null) {
               if (!(lowBitmap1 instanceof RoaringBitmap) || !(lowBitmap2 instanceof RoaringBitmap)) {
                  if (!(lowBitmap1 instanceof MutableRoaringBitmap) || !(lowBitmap2 instanceof MutableRoaringBitmap)) {
                     throw new UnsupportedOperationException(".naivelazyor(...) over " + getClassName(lowBitmap1) + " and " + getClassName(lowBitmap2));
                  }

                  MutableRoaringBitmapPrivate.naivelazyor((MutableRoaringBitmap)lowBitmap1, (MutableRoaringBitmap)lowBitmap2);
               } else {
                  RoaringBitmapPrivate.naivelazyor((RoaringBitmap)lowBitmap1, (RoaringBitmap)lowBitmap2);
               }
            } else {
               BitmapDataProvider lowBitmap2Clone;
               if (lowBitmap2 instanceof RoaringBitmap) {
                  lowBitmap2Clone = ((RoaringBitmap)lowBitmap2).clone();
               } else {
                  if (!(lowBitmap2 instanceof MutableRoaringBitmap)) {
                     throw new UnsupportedOperationException(".naivelazyor(...) over " + getClassName(lowBitmap2));
                  }

                  lowBitmap2Clone = ((MutableRoaringBitmap)lowBitmap2).clone();
               }

               this.pushBitmapForHigh(high, lowBitmap2Clone);
            }
         }

      }
   }

   public void or(Roaring64NavigableMap x2) {
      if (this != x2) {
         boolean firstBucket = true;

         for(Map.Entry e2 : x2.highToBitmap.entrySet()) {
            Integer high = (Integer)e2.getKey();
            BitmapDataProvider lowBitmap1 = (BitmapDataProvider)this.highToBitmap.get(high);
            BitmapDataProvider lowBitmap2 = (BitmapDataProvider)e2.getValue();
            if ((lowBitmap1 == null || lowBitmap1 instanceof RoaringBitmap) && lowBitmap2 instanceof RoaringBitmap) {
               if (lowBitmap1 == null) {
                  RoaringBitmap lowBitmap2Clone = ((RoaringBitmap)lowBitmap2).clone();
                  this.pushBitmapForHigh(high, lowBitmap2Clone);
               } else {
                  ((RoaringBitmap)lowBitmap1).or((RoaringBitmap)lowBitmap2);
               }
            } else {
               if (lowBitmap1 != null && !(lowBitmap1 instanceof MutableRoaringBitmap) || !(lowBitmap2 instanceof MutableRoaringBitmap)) {
                  throw new UnsupportedOperationException(".or(...) over " + getClassName(lowBitmap1) + " and " + getClassName(lowBitmap2));
               }

               if (lowBitmap1 == null) {
                  BitmapDataProvider lowBitmap2Clone = ((MutableRoaringBitmap)lowBitmap2).clone();
                  this.pushBitmapForHigh(high, lowBitmap2Clone);
               } else {
                  ((MutableRoaringBitmap)lowBitmap1).or((ImmutableRoaringBitmap)((MutableRoaringBitmap)lowBitmap2));
               }
            }

            if (firstBucket) {
               firstBucket = false;
               this.firstHighNotValid = Math.min(this.firstHighNotValid, high);
               this.allValid = false;
            }
         }

      }
   }

   public void xor(Roaring64NavigableMap x2) {
      if (x2 == this) {
         this.clear();
      } else {
         boolean firstBucket = true;

         for(Map.Entry e2 : x2.highToBitmap.entrySet()) {
            Integer high = (Integer)e2.getKey();
            BitmapDataProvider lowBitmap1 = (BitmapDataProvider)this.highToBitmap.get(high);
            BitmapDataProvider lowBitmap2 = (BitmapDataProvider)e2.getValue();
            if ((lowBitmap1 == null || lowBitmap1 instanceof RoaringBitmap) && lowBitmap2 instanceof RoaringBitmap) {
               if (lowBitmap1 == null) {
                  RoaringBitmap lowBitmap2Clone = ((RoaringBitmap)lowBitmap2).clone();
                  this.pushBitmapForHigh(high, lowBitmap2Clone);
               } else {
                  ((RoaringBitmap)lowBitmap1).xor((RoaringBitmap)lowBitmap2);
               }
            } else {
               if (lowBitmap1 != null && !(lowBitmap1 instanceof MutableRoaringBitmap) || !(lowBitmap2 instanceof MutableRoaringBitmap)) {
                  throw new UnsupportedOperationException(".or(...) over " + getClassName(lowBitmap1) + " and " + getClassName(lowBitmap2));
               }

               if (lowBitmap1 == null) {
                  BitmapDataProvider lowBitmap2Clone = ((MutableRoaringBitmap)lowBitmap2).clone();
                  this.pushBitmapForHigh(high, lowBitmap2Clone);
               } else {
                  ((MutableRoaringBitmap)lowBitmap1).xor((MutableRoaringBitmap)lowBitmap2);
               }
            }

            if (firstBucket) {
               firstBucket = false;
               this.firstHighNotValid = Math.min(this.firstHighNotValid, high);
               this.allValid = false;
            }
         }

      }
   }

   public void and(Roaring64NavigableMap x2) {
      if (x2 != this) {
         boolean firstBucket = true;
         Iterator<Map.Entry<Integer, BitmapDataProvider>> thisIterator = this.highToBitmap.entrySet().iterator();

         while(thisIterator.hasNext()) {
            Map.Entry<Integer, BitmapDataProvider> e1 = (Map.Entry)thisIterator.next();
            Integer high = (Integer)e1.getKey();
            BitmapDataProvider lowBitmap2 = (BitmapDataProvider)x2.highToBitmap.get(high);
            if (lowBitmap2 == null) {
               thisIterator.remove();
            } else {
               BitmapDataProvider lowBitmap1 = (BitmapDataProvider)e1.getValue();
               if (lowBitmap2 instanceof RoaringBitmap && lowBitmap1 instanceof RoaringBitmap) {
                  ((RoaringBitmap)lowBitmap1).and((RoaringBitmap)lowBitmap2);
               } else {
                  if (!(lowBitmap2 instanceof MutableRoaringBitmap) || !(lowBitmap1 instanceof MutableRoaringBitmap)) {
                     throw new UnsupportedOperationException(".and(...) over " + getClassName(lowBitmap1) + " and " + getClassName(lowBitmap2));
                  }

                  ((MutableRoaringBitmap)lowBitmap1).and((MutableRoaringBitmap)lowBitmap2);
               }
            }

            if (firstBucket) {
               firstBucket = false;
               this.firstHighNotValid = Math.min(this.firstHighNotValid, high);
               this.allValid = false;
            }
         }

      }
   }

   public void andNot(Roaring64NavigableMap x2) {
      if (x2 == this) {
         this.clear();
      } else {
         boolean firstBucket = true;

         for(Map.Entry e1 : this.highToBitmap.entrySet()) {
            Integer high = (Integer)e1.getKey();
            BitmapDataProvider lowBitmap2 = (BitmapDataProvider)x2.highToBitmap.get(high);
            if (lowBitmap2 != null) {
               BitmapDataProvider lowBitmap1 = (BitmapDataProvider)e1.getValue();
               if (lowBitmap2 instanceof RoaringBitmap && lowBitmap1 instanceof RoaringBitmap) {
                  ((RoaringBitmap)lowBitmap1).andNot((RoaringBitmap)lowBitmap2);
               } else {
                  if (!(lowBitmap2 instanceof MutableRoaringBitmap) || !(lowBitmap1 instanceof MutableRoaringBitmap)) {
                     throw new UnsupportedOperationException(".and(...) over " + getClassName(lowBitmap1) + " and " + getClassName(lowBitmap2));
                  }

                  ((MutableRoaringBitmap)lowBitmap1).andNot((MutableRoaringBitmap)lowBitmap2);
               }
            }

            if (firstBucket) {
               firstBucket = false;
               this.firstHighNotValid = Math.min(this.firstHighNotValid, high);
               this.allValid = false;
            }
         }

      }
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.serialize(out);
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.deserialize(in);
   }

   public String toString() {
      StringBuilder answer = new StringBuilder("{}".length() + "-1234567890123456789,".length() * 256);
      LongIterator i = this.getLongIterator();
      answer.append('{');
      if (i.hasNext()) {
         if (this.signedLongs) {
            answer.append(i.next());
         } else {
            answer.append(RoaringIntPacking.toUnsignedString(i.next()));
         }
      }

      while(i.hasNext()) {
         answer.append(',');
         if (answer.length() > 524288) {
            answer.append('.').append('.').append('.');
            break;
         }

         if (this.signedLongs) {
            answer.append(i.next());
         } else {
            answer.append(RoaringIntPacking.toUnsignedString(i.next()));
         }
      }

      answer.append("}");
      return answer.toString();
   }

   public LongIterator getLongIterator() {
      Iterator<Map.Entry<Integer, BitmapDataProvider>> it = this.highToBitmap.entrySet().iterator();
      return this.toIterator(it, false);
   }

   protected LongIterator toIterator(final Iterator it, final boolean reversed) {
      return new LongIterator() {
         protected int currentKey;
         protected IntIterator currentIt;

         public boolean hasNext() {
            if (this.currentIt == null && !this.moveToNextEntry(it)) {
               return false;
            } else {
               while(!this.currentIt.hasNext()) {
                  if (!this.moveToNextEntry(it)) {
                     return false;
                  }
               }

               return true;
            }
         }

         private boolean moveToNextEntry(Iterator itx) {
            if (it.hasNext()) {
               Map.Entry<Integer, BitmapDataProvider> next = (Map.Entry)it.next();
               this.currentKey = (Integer)next.getKey();
               if (reversed) {
                  this.currentIt = ((BitmapDataProvider)next.getValue()).getReverseIntIterator();
               } else {
                  this.currentIt = ((BitmapDataProvider)next.getValue()).getIntIterator();
               }

               return true;
            } else {
               return false;
            }
         }

         public long next() {
            if (this.hasNext()) {
               return RoaringIntPacking.pack(this.currentKey, this.currentIt.next());
            } else {
               throw new IllegalStateException("empty");
            }
         }

         public LongIterator clone() {
            throw new UnsupportedOperationException("TODO");
         }
      };
   }

   public boolean contains(long x) {
      int high = RoaringIntPacking.high(x);
      BitmapDataProvider lowBitmap = (BitmapDataProvider)this.highToBitmap.get(high);
      if (lowBitmap == null) {
         return false;
      } else {
         int low = RoaringIntPacking.low(x);
         return lowBitmap.contains(low);
      }
   }

   public int getSizeInBytes() {
      return (int)this.getLongSizeInBytes();
   }

   public long getLongSizeInBytes() {
      long size = 8L;
      size += this.highToBitmap.values().stream().mapToLong((p) -> p.getLongSizeInBytes()).sum();
      size += 8L + 40L * (long)this.highToBitmap.size();
      size += 16L * (long)this.highToBitmap.size();
      size += 8L * (long)this.sortedCumulatedCardinality.length;
      size += 4L * (long)this.sortedHighs.length;
      return size;
   }

   public boolean isEmpty() {
      return this.getLongCardinality() == 0L;
   }

   public ImmutableLongBitmapDataProvider limit(long x) {
      throw new UnsupportedOperationException("TODO");
   }

   public void repairAfterLazy() {
      for(BitmapDataProvider lowBitmap : this.highToBitmap.values()) {
         if (lowBitmap instanceof RoaringBitmap) {
            RoaringBitmapPrivate.repairAfterLazy((RoaringBitmap)lowBitmap);
         } else {
            if (!(lowBitmap instanceof MutableRoaringBitmap)) {
               throw new UnsupportedOperationException(".repairAfterLazy is not supported for " + lowBitmap.getClass());
            }

            MutableRoaringBitmapPrivate.repairAfterLazy((MutableRoaringBitmap)lowBitmap);
         }
      }

   }

   public boolean runOptimize() {
      boolean hasChanged = false;

      for(BitmapDataProvider lowBitmap : this.highToBitmap.values()) {
         if (lowBitmap instanceof RoaringBitmap) {
            hasChanged |= ((RoaringBitmap)lowBitmap).runOptimize();
         } else if (lowBitmap instanceof MutableRoaringBitmap) {
            hasChanged |= ((MutableRoaringBitmap)lowBitmap).runOptimize();
         }
      }

      return hasChanged;
   }

   public void serialize(DataOutput out) throws IOException {
      if (SERIALIZATION_MODE == 1) {
         this.serializePortable(out);
      } else {
         this.serializeLegacy(out);
      }

   }

   /** @deprecated */
   @Deprecated
   public void serializeLegacy(DataOutput out) throws IOException {
      out.writeBoolean(this.signedLongs);
      out.writeInt(this.highToBitmap.size());

      for(Map.Entry entry : this.highToBitmap.entrySet()) {
         out.writeInt((Integer)entry.getKey());
         ((BitmapDataProvider)entry.getValue()).serialize(out);
      }

   }

   public void serializePortable(DataOutput out) throws IOException {
      out.writeLong(Long.reverseBytes((long)this.highToBitmap.size()));

      for(Map.Entry entry : this.highToBitmap.entrySet()) {
         out.writeInt(Integer.reverseBytes((Integer)entry.getKey()));
         ((BitmapDataProvider)entry.getValue()).serialize(out);
      }

   }

   public void deserialize(DataInput in) throws IOException {
      if (SERIALIZATION_MODE == 1) {
         this.deserializePortable(in);
      } else {
         this.deserializeLegacy(in);
      }

   }

   public void deserializeLegacy(DataInput in) throws IOException {
      this.clear();
      this.signedLongs = in.readBoolean();
      int nbHighs = in.readInt();
      if (this.signedLongs) {
         this.highToBitmap = new TreeMap();
      } else {
         this.highToBitmap = new TreeMap(RoaringIntPacking.unsignedComparator());
      }

      for(int i = 0; i < nbHighs; ++i) {
         int high = in.readInt();
         BitmapDataProvider provider = this.newRoaringBitmap();
         if (provider instanceof RoaringBitmap) {
            ((RoaringBitmap)provider).deserialize(in);
         } else {
            if (!(provider instanceof MutableRoaringBitmap)) {
               throw new UnsupportedEncodingException("Cannot deserialize a " + provider.getClass());
            }

            ((MutableRoaringBitmap)provider).deserialize(in);
         }

         this.highToBitmap.put(high, provider);
      }

      this.resetPerfHelpers();
   }

   public void deserializePortable(DataInput in) throws IOException {
      this.clear();
      this.signedLongs = false;
      long nbHighs = Long.reverseBytes(in.readLong());
      this.highToBitmap = new TreeMap(RoaringIntPacking.unsignedComparator());

      for(int i = 0; (long)i < nbHighs; ++i) {
         int high = Integer.reverseBytes(in.readInt());
         BitmapDataProvider provider = this.newRoaringBitmap();
         if (provider instanceof RoaringBitmap) {
            ((RoaringBitmap)provider).deserialize(in);
         } else {
            if (!(provider instanceof MutableRoaringBitmap)) {
               throw new UnsupportedEncodingException("Cannot deserialize a " + provider.getClass());
            }

            ((MutableRoaringBitmap)provider).deserialize(in);
         }

         this.highToBitmap.put(high, provider);
      }

      this.resetPerfHelpers();
   }

   public long serializedSizeInBytes() {
      long nbBytes = 0L;
      if (SERIALIZATION_MODE == 1) {
         nbBytes += 8L;
      } else {
         ++nbBytes;
         nbBytes += 4L;
      }

      for(Map.Entry entry : this.highToBitmap.entrySet()) {
         nbBytes += 4L;
         nbBytes += (long)((BitmapDataProvider)entry.getValue()).serializedSizeInBytes();
      }

      return nbBytes;
   }

   public void clear() {
      this.highToBitmap.clear();
      this.resetPerfHelpers();
   }

   public long[] toArray() {
      long cardinality = this.getLongCardinality();
      if (cardinality > 2147483647L) {
         throw new IllegalStateException("The cardinality does not fit in an array");
      } else {
         long[] array = new long[(int)cardinality];
         int pos = 0;

         for(LongIterator it = this.getLongIterator(); it.hasNext(); array[pos++] = it.next()) {
         }

         return array;
      }
   }

   public static Roaring64NavigableMap bitmapOf(long... dat) {
      Roaring64NavigableMap ans = new Roaring64NavigableMap();
      ans.add(dat);
      return ans;
   }

   public void add(long... dat) {
      for(long oneLong : dat) {
         this.addLong(oneLong);
      }

   }

   /** @deprecated */
   @Deprecated
   public void add(long rangeStart, long rangeEnd) {
      this.addRange(rangeStart, rangeEnd);
   }

   public void addRange(long rangeStart, long rangeEnd) {
      int startHigh = this.high(rangeStart);
      int startLow = this.low(rangeStart);
      int endHigh = this.high(rangeEnd);
      int endLow = this.low(rangeEnd);
      int compareHigh = this.compare(startHigh, endHigh);
      if (compareHigh <= 0 && (compareHigh != 0 || Util.toUnsignedLong(startLow) < Util.toUnsignedLong(endLow))) {
         for(int high = startHigh; this.compare(high, endHigh) <= 0; ++high) {
            int currentStartLow;
            if (startHigh == high) {
               currentStartLow = startLow;
            } else {
               currentStartLow = 0;
            }

            long startLowAsLong = Util.toUnsignedLong(currentStartLow);
            long endLowAsLong;
            if (endHigh == high) {
               endLowAsLong = Util.toUnsignedLong(endLow);
            } else {
               endLowAsLong = Util.toUnsignedLong(-1) + 1L;
            }

            if (endLowAsLong > startLowAsLong) {
               BitmapDataProvider bitmap = (BitmapDataProvider)this.highToBitmap.get(high);
               if (bitmap == null) {
                  bitmap = this.newRoaringBitmap();
                  this.pushBitmapForHigh(high, bitmap);
               }

               bitmap.add(startLowAsLong, endLowAsLong);
            }

            if (high == this.highestHigh()) {
               break;
            }
         }

         this.invalidateAboveHigh(startHigh);
      } else {
         throw new IllegalArgumentException("Invalid range [" + rangeStart + "," + rangeEnd + ")");
      }
   }

   public LongIterator getReverseLongIterator() {
      return this.toIterator(this.highToBitmap.descendingMap().entrySet().iterator(), true);
   }

   public void removeLong(long x) {
      int high = this.high(x);
      BitmapDataProvider bitmap = (BitmapDataProvider)this.highToBitmap.get(high);
      if (bitmap != null) {
         int low = this.low(x);
         bitmap.remove(low);
         if (bitmap.isEmpty()) {
            this.highToBitmap.remove(high);
            this.latestAddedHigh = null;
         }

         this.invalidateAboveHigh(high);
      }

   }

   public void trim() {
      for(BitmapDataProvider bitmap : this.highToBitmap.values()) {
         bitmap.trim();
      }

   }

   public int hashCode() {
      return this.highToBitmap.hashCode();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Roaring64NavigableMap other = (Roaring64NavigableMap)obj;
         return Objects.equals(this.highToBitmap, other.highToBitmap);
      }
   }

   public void flip(long x) {
      int high = RoaringIntPacking.high(x);
      BitmapDataProvider lowBitmap = (BitmapDataProvider)this.highToBitmap.get(high);
      if (lowBitmap == null) {
         this.addLong(x);
      } else {
         int low = RoaringIntPacking.low(x);
         if (lowBitmap instanceof RoaringBitmap) {
            ((RoaringBitmap)lowBitmap).flip(low);
         } else if (lowBitmap instanceof MutableRoaringBitmap) {
            ((MutableRoaringBitmap)lowBitmap).flip(low);
         } else if (lowBitmap.contains(low)) {
            lowBitmap.remove(low);
         } else {
            lowBitmap.add(low);
         }
      }

      this.invalidateAboveHigh(high);
   }

   private void assertNonEmpty() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("Empty " + this.getClass().getSimpleName());
      }
   }

   public long first() {
      this.assertNonEmpty();
      Map.Entry<Integer, BitmapDataProvider> firstEntry = this.highToBitmap.firstEntry();
      return RoaringIntPacking.pack((Integer)firstEntry.getKey(), ((BitmapDataProvider)firstEntry.getValue()).first());
   }

   public long last() {
      this.assertNonEmpty();
      Map.Entry<Integer, BitmapDataProvider> lastEntry = this.highToBitmap.lastEntry();
      return RoaringIntPacking.pack((Integer)lastEntry.getKey(), ((BitmapDataProvider)lastEntry.getValue()).last());
   }
}
