package org.roaringbitmap.longlong;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.roaringbitmap.ArrayContainer;
import org.roaringbitmap.Container;
import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.RelativeRangeConsumer;
import org.roaringbitmap.RunContainer;
import org.roaringbitmap.Util;
import org.roaringbitmap.art.ContainerIterator;
import org.roaringbitmap.art.KeyIterator;
import org.roaringbitmap.art.LeafNode;
import org.roaringbitmap.art.LeafNodeIterator;

public class Roaring64Bitmap implements Externalizable, LongBitmapDataProvider {
   private HighLowContainer highLowContainer = new HighLowContainer();

   public void addInt(int x) {
      this.addLong(Util.toUnsignedLong(x));
   }

   public void addLong(long x) {
      byte[] high = LongUtils.highPart(x);
      char low = LongUtils.lowPart(x);
      ContainerWithIndex containerWithIndex = this.highLowContainer.searchContainer(high);
      if (containerWithIndex != null) {
         Container container = containerWithIndex.getContainer();
         Container freshOne = container.add(low);
         this.highLowContainer.replaceContainer(containerWithIndex.getContainerIdx(), freshOne);
      } else {
         ArrayContainer arrayContainer = new ArrayContainer();
         arrayContainer.add(low);
         this.highLowContainer.put(high, arrayContainer);
      }

   }

   public long getLongCardinality() {
      if (this.highLowContainer.isEmpty()) {
         return 0L;
      } else {
         Iterator<Container> containerIterator = this.highLowContainer.containerIterator();

         long cardinality;
         Container container;
         for(cardinality = 0L; containerIterator.hasNext(); cardinality += (long)container.getCardinality()) {
            container = (Container)containerIterator.next();
         }

         return cardinality;
      }
   }

   public int getIntCardinality() throws UnsupportedOperationException {
      long cardinality = this.getLongCardinality();
      if (cardinality > 2147483647L) {
         throw new UnsupportedOperationException("Can not call .getIntCardinality as the cardinality is bigger than Integer.MAX_VALUE");
      } else {
         return (int)cardinality;
      }
   }

   public long select(long j) throws IllegalArgumentException {
      long left = j;

      int card;
      for(LeafNodeIterator leafNodeIterator = this.highLowContainer.highKeyLeafNodeIterator(false); leafNodeIterator.hasNext(); left -= (long)card) {
         LeafNode leafNode = leafNodeIterator.next();
         long containerIdx = leafNode.getContainerIdx();
         Container container = this.highLowContainer.getContainer(containerIdx);
         card = container.getCardinality();
         if (left < (long)card) {
            byte[] high = leafNode.getKeyBytes();
            int leftAsUnsignedInt = (int)left;
            char low = container.select(leftAsUnsignedInt);
            return LongUtils.toLong(high, low);
         }
      }

      return this.throwSelectInvalidIndex(j);
   }

   private long throwSelectInvalidIndex(long j) {
      throw new IllegalArgumentException("select " + j + " when the cardinality is " + this.getLongCardinality());
   }

   public long first() {
      return this.highLowContainer.first();
   }

   public long last() {
      return this.highLowContainer.last();
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

   public void forEach(LongConsumer lc) {
      KeyIterator keyIterator = this.highLowContainer.highKeyIterator();

      while(keyIterator.hasNext()) {
         byte[] high = keyIterator.next();
         long containerIdx = keyIterator.currentContainerIdx();
         Container container = this.highLowContainer.getContainer(containerIdx);
         PeekableCharIterator charIterator = container.getCharIterator();

         while(charIterator.hasNext()) {
            char low = charIterator.next();
            long v = LongUtils.toLong(high, low);
            lc.accept(v);
         }
      }

   }

   public void forAllInRange(long start, int length, RelativeRangeConsumer rrc) {
      LeafNodeIterator leafIterator = this.highLowContainer.highKeyLeafNodeIteratorFrom(start, false);
      if (!leafIterator.hasNext()) {
         rrc.acceptAllAbsent(0, length);
      } else {
         long end = start + (long)length;
         long endHigh = LongUtils.rightShiftHighPart(end);
         long filledUntil = start;
         LeafNode node = leafIterator.next();

         for(long high = node.getKey(); high <= endHigh; high = node.getKey()) {
            long containerStart = LongUtils.toLong(high, '\u0000');
            if (filledUntil < containerStart) {
               rrc.acceptAllAbsent((int)(filledUntil - start), (int)(containerStart - start));
               filledUntil = containerStart;
            }

            long containerIdx = node.getContainerIdx();
            Container container = this.highLowContainer.getContainer(containerIdx);
            long containerEnd = LongUtils.toLong(high, '\uffff') + 1L;
            int containerRangeStartOffset = (int)(filledUntil - start);
            boolean startInContainer = containerStart < start;
            boolean endInContainer = end < containerEnd;
            if (startInContainer && endInContainer) {
               char containerRangeStart = LongUtils.lowPart(start);
               char containerRangeEnd = LongUtils.lowPart(end);
               container.forAllInRange(LongUtils.lowPart(start), LongUtils.lowPart(end), rrc);
               filledUntil += (long)(containerRangeEnd - containerRangeStart);
            } else if (startInContainer) {
               char containerRangeStart = LongUtils.lowPart(start);
               container.forAllFrom(containerRangeStart, rrc);
               filledUntil += (long)(65536 - containerRangeStart);
            } else if (endInContainer) {
               char containerRangeEnd = LongUtils.lowPart(end);
               container.forAllUntil(containerRangeStartOffset, containerRangeEnd, rrc);
               filledUntil += (long)containerRangeEnd;
            } else {
               container.forAll(containerRangeStartOffset, rrc);
               filledUntil += 65536L;
            }

            if (!leafIterator.hasNext()) {
               break;
            }

            node = leafIterator.next();
         }

         if (filledUntil < end) {
            rrc.acceptAllAbsent((int)(filledUntil - start), length);
         }

      }
   }

   public void forEachInRange(long start, int length, LongConsumer lc) {
      this.forAllInRange(start, length, new LongConsumerRelativeRangeAdapter(start, lc));
   }

   public long rankLong(long id) {
      long result = 0L;
      long high = LongUtils.rightShiftHighPart(id);
      byte[] highBytes = LongUtils.highPart(id);
      char low = LongUtils.lowPart(id);
      ContainerWithIndex containerWithIndex = this.highLowContainer.searchContainer(highBytes);
      KeyIterator keyIterator = this.highLowContainer.highKeyIterator();
      if (containerWithIndex == null) {
         while(keyIterator.hasNext()) {
            long highKey = keyIterator.nextKey();
            if (highKey > high) {
               break;
            }

            long containerIdx = keyIterator.currentContainerIdx();
            Container container = this.highLowContainer.getContainer(containerIdx);
            result += (long)container.getCardinality();
         }
      } else {
         while(keyIterator.hasNext()) {
            long key = keyIterator.nextKey();
            long containerIdx = keyIterator.currentContainerIdx();
            Container container = this.highLowContainer.getContainer(containerIdx);
            if (key == high) {
               result += (long)container.rank(low);
               break;
            }

            result += (long)container.getCardinality();
         }
      }

      return result;
   }

   public void or(Roaring64Bitmap x2) {
      if (this != x2) {
         KeyIterator highIte2 = x2.highLowContainer.highKeyIterator();

         while(highIte2.hasNext()) {
            byte[] high = highIte2.next();
            long containerIdx = highIte2.currentContainerIdx();
            Container container2 = x2.highLowContainer.getContainer(containerIdx);
            ContainerWithIndex containerWithIdx = this.highLowContainer.searchContainer(high);
            if (containerWithIdx == null) {
               Container container2clone = container2.clone();
               this.highLowContainer.put(high, container2clone);
            } else {
               Container freshContainer = containerWithIdx.getContainer().ior(container2);
               this.highLowContainer.replaceContainer(containerWithIdx.getContainerIdx(), freshContainer);
            }
         }

      }
   }

   public static Roaring64Bitmap or(Roaring64Bitmap x1, Roaring64Bitmap x2) {
      Roaring64Bitmap result = new Roaring64Bitmap();
      KeyIterator it1 = x1.highLowContainer.highKeyIterator();
      KeyIterator it2 = x2.highLowContainer.highKeyIterator();
      byte[] highKey1 = null;
      byte[] highKey2 = null;
      if (it1.hasNext()) {
         highKey1 = it1.next();
      }

      if (it2.hasNext()) {
         highKey2 = it2.next();
      }

      while(highKey1 != null || highKey2 != null) {
         int compare = HighLowContainer.compareUnsigned(highKey1, highKey2);
         if (compare == 0) {
            long containerIdx1 = it1.currentContainerIdx();
            long containerIdx2 = it2.currentContainerIdx();
            Container container1 = x1.highLowContainer.getContainer(containerIdx1);
            Container container2 = x2.highLowContainer.getContainer(containerIdx2);
            Container orResult = container1.or(container2);
            result.highLowContainer.put(highKey1, orResult);
            highKey1 = it1.hasNext() ? it1.next() : null;
            highKey2 = it2.hasNext() ? it2.next() : null;
         } else if (compare < 0) {
            long containerIdx1 = it1.currentContainerIdx();
            Container container1 = x1.highLowContainer.getContainer(containerIdx1);
            result.highLowContainer.put(highKey1, container1.clone());
            highKey1 = it1.hasNext() ? it1.next() : null;
         } else {
            long containerIdx2 = it2.currentContainerIdx();
            Container container2 = x2.highLowContainer.getContainer(containerIdx2);
            result.highLowContainer.put(highKey2, container2.clone());
            highKey2 = it2.hasNext() ? it2.next() : null;
         }
      }

      return result;
   }

   public void xor(Roaring64Bitmap x2) {
      if (x2 == this) {
         this.clear();
      } else {
         KeyIterator keyIterator = x2.highLowContainer.highKeyIterator();

         while(keyIterator.hasNext()) {
            byte[] high = keyIterator.next();
            long containerIdx = keyIterator.currentContainerIdx();
            Container container = x2.highLowContainer.getContainer(containerIdx);
            ContainerWithIndex containerWithIndex = this.highLowContainer.searchContainer(high);
            if (containerWithIndex == null) {
               Container containerClone2 = container.clone();
               this.highLowContainer.put(high, containerClone2);
            } else {
               Container freshOne = containerWithIndex.getContainer().ixor(container);
               this.highLowContainer.replaceContainer(containerWithIndex.getContainerIdx(), freshOne);
            }
         }

      }
   }

   public static Roaring64Bitmap xor(Roaring64Bitmap x1, Roaring64Bitmap x2) {
      Roaring64Bitmap result = new Roaring64Bitmap();
      KeyIterator it1 = x1.highLowContainer.highKeyIterator();
      KeyIterator it2 = x2.highLowContainer.highKeyIterator();
      byte[] highKey1 = null;
      byte[] highKey2 = null;
      if (it1.hasNext()) {
         highKey1 = it1.next();
      }

      if (it2.hasNext()) {
         highKey2 = it2.next();
      }

      while(highKey1 != null || highKey2 != null) {
         int compare = HighLowContainer.compareUnsigned(highKey1, highKey2);
         if (compare == 0) {
            long containerIdx1 = it1.currentContainerIdx();
            long containerIdx2 = it2.currentContainerIdx();
            Container container1 = x1.highLowContainer.getContainer(containerIdx1);
            Container container2 = x2.highLowContainer.getContainer(containerIdx2);
            Container xorResult = container1.xor(container2);
            result.highLowContainer.put(highKey1, xorResult);
            highKey1 = it1.hasNext() ? it1.next() : null;
            highKey2 = it2.hasNext() ? it2.next() : null;
         } else if (compare < 0) {
            long containerIdx1 = it1.currentContainerIdx();
            Container container1 = x1.highLowContainer.getContainer(containerIdx1);
            result.highLowContainer.put(highKey1, container1.clone());
            highKey1 = it1.hasNext() ? it1.next() : null;
         } else {
            long containerIdx2 = it2.currentContainerIdx();
            Container container2 = x2.highLowContainer.getContainer(containerIdx2);
            result.highLowContainer.put(highKey2, container2.clone());
            highKey2 = it2.hasNext() ? it2.next() : null;
         }
      }

      return result;
   }

   public void and(Roaring64Bitmap x2) {
      if (x2 != this) {
         KeyIterator thisIterator = this.highLowContainer.highKeyIterator();

         while(thisIterator.hasNext()) {
            byte[] highKey = thisIterator.next();
            long containerIdx = thisIterator.currentContainerIdx();
            ContainerWithIndex containerWithIdx = x2.highLowContainer.searchContainer(highKey);
            if (containerWithIdx == null) {
               thisIterator.remove();
            } else {
               Container container1 = this.highLowContainer.getContainer(containerIdx);
               Container freshContainer = container1.iand(containerWithIdx.getContainer());
               if (!freshContainer.isEmpty()) {
                  this.highLowContainer.replaceContainer(containerIdx, freshContainer);
               } else {
                  thisIterator.remove();
               }
            }
         }

      }
   }

   public static Roaring64Bitmap and(Roaring64Bitmap x1, Roaring64Bitmap x2) {
      Roaring64Bitmap result = new Roaring64Bitmap();
      KeyIterator it1 = x1.highLowContainer.highKeyIterator();

      while(it1.hasNext()) {
         byte[] highKey = it1.next();
         long containerIdx1 = it1.currentContainerIdx();
         ContainerWithIndex containerWithIdx2 = x2.highLowContainer.searchContainer(highKey);
         if (containerWithIdx2 != null) {
            Container container1 = x1.highLowContainer.getContainer(containerIdx1);
            Container container2 = containerWithIdx2.getContainer();
            Container andResult = container1.and(container2);
            if (!andResult.isEmpty()) {
               result.highLowContainer.put(highKey, andResult);
            }
         }
      }

      return result;
   }

   public static boolean intersects(Roaring64Bitmap x1, Roaring64Bitmap x2) {
      KeyIterator it1 = x1.highLowContainer.highKeyIterator();
      KeyIterator it2 = x2.highLowContainer.highKeyIterator();
      byte[] highKey1 = it1.hasNext() ? it1.next() : null;
      byte[] highKey2 = it2.hasNext() ? it2.next() : null;

      while(highKey1 != null && highKey2 != null) {
         int compare = HighLowContainer.compareUnsigned(highKey1, highKey2);
         if (compare == 0) {
            long containerIdx1 = it1.currentContainerIdx();
            long containerIdx2 = it2.currentContainerIdx();
            Container container1 = x1.highLowContainer.getContainer(containerIdx1);
            Container container2 = x2.highLowContainer.getContainer(containerIdx2);
            if (container1.intersects(container2)) {
               return true;
            }

            highKey1 = it1.hasNext() ? it1.next() : null;
            highKey2 = it2.hasNext() ? it2.next() : null;
         } else if (compare < 0) {
            highKey1 = it1.hasNext() ? it1.next() : null;
         } else {
            highKey2 = it2.hasNext() ? it2.next() : null;
         }
      }

      return false;
   }

   public static long andCardinality(Roaring64Bitmap x1, Roaring64Bitmap x2) {
      long cardinality = 0L;
      KeyIterator it1 = x1.highLowContainer.highKeyIterator();
      KeyIterator it2 = x2.highLowContainer.highKeyIterator();
      byte[] highKey1 = null;
      byte[] highKey2 = null;
      if (it1.hasNext()) {
         highKey1 = it1.next();
      }

      if (it2.hasNext()) {
         highKey2 = it2.next();
      }

      while(highKey1 != null && highKey2 != null) {
         int compare = HighLowContainer.compareUnsigned(highKey1, highKey2);
         if (compare == 0) {
            long containerIdx1 = it1.currentContainerIdx();
            long containerIdx2 = it2.currentContainerIdx();
            Container container1 = x1.highLowContainer.getContainer(containerIdx1);
            Container container2 = x2.highLowContainer.getContainer(containerIdx2);
            cardinality += (long)container1.andCardinality(container2);
            highKey1 = it1.hasNext() ? it1.next() : null;
            highKey2 = it2.hasNext() ? it2.next() : null;
         } else if (compare < 0) {
            highKey1 = it1.hasNext() ? it1.next() : null;
         } else {
            highKey2 = it2.hasNext() ? it2.next() : null;
         }
      }

      return cardinality;
   }

   public void andNot(Roaring64Bitmap x2) {
      if (x2 == this) {
         this.clear();
      } else {
         KeyIterator thisKeyIterator = this.highLowContainer.highKeyIterator();

         while(thisKeyIterator.hasNext()) {
            byte[] high = thisKeyIterator.next();
            long containerIdx = thisKeyIterator.currentContainerIdx();
            ContainerWithIndex containerWithIdx2 = x2.highLowContainer.searchContainer(high);
            if (containerWithIdx2 != null) {
               Container thisContainer = this.highLowContainer.getContainer(containerIdx);
               Container freshContainer = thisContainer.iandNot(containerWithIdx2.getContainer());
               this.highLowContainer.replaceContainer(containerIdx, freshContainer);
               if (!freshContainer.isEmpty()) {
                  this.highLowContainer.replaceContainer(containerIdx, freshContainer);
               } else {
                  thisKeyIterator.remove();
               }
            }
         }

      }
   }

   public static Roaring64Bitmap andNot(Roaring64Bitmap x1, Roaring64Bitmap x2) {
      Roaring64Bitmap result = new Roaring64Bitmap();
      KeyIterator it1 = x1.highLowContainer.highKeyIterator();

      while(it1.hasNext()) {
         byte[] highKey = it1.next();
         long containerIdx = it1.currentContainerIdx();
         ContainerWithIndex containerWithIdx2 = x2.highLowContainer.searchContainer(highKey);
         Container container1 = x1.highLowContainer.getContainer(containerIdx);
         if (containerWithIdx2 != null) {
            Container andNotResult = container1.andNot(containerWithIdx2.getContainer());
            if (!andNotResult.isEmpty()) {
               result.highLowContainer.put(highKey, andNotResult);
            }
         } else {
            result.highLowContainer.put(highKey, container1.clone());
         }
      }

      return result;
   }

   public void flip(long rangeStart, long rangeEnd) {
      if (rangeEnd < 0L || rangeStart < rangeEnd) {
         if (rangeStart >= 0L || rangeStart < rangeEnd) {
            if (rangeStart >= 0L || rangeEnd <= 0L) {
               byte[] hbStart = LongUtils.highPart(rangeStart);
               char lbStart = LongUtils.lowPart(rangeStart);
               char lbLast = LongUtils.lowPart(rangeEnd - 1L);
               long shStart = LongUtils.rightShiftHighPart(rangeStart);
               long shEnd = LongUtils.rightShiftHighPart(rangeEnd - 1L);

               for(long hb = shStart; hb <= shEnd; ++hb) {
                  int containerStart = hb == shStart ? lbStart : 0;
                  int containerLast = hb == shEnd ? lbLast : LongUtils.maxLowBitAsInteger();
                  ContainerWithIndex cwi = this.highLowContainer.searchContainer(LongUtils.highPartInPlace(LongUtils.leftShiftHighPart(hb), hbStart));
                  if (cwi != null) {
                     long i = cwi.getContainerIdx();
                     Container c = cwi.getContainer().inot(containerStart, containerLast + 1);
                     if (!c.isEmpty()) {
                        this.highLowContainer.replaceContainer(i, c);
                     } else {
                        this.highLowContainer.remove(hbStart);
                     }
                  } else {
                     Container newContainer = Container.rangeOfOnes(containerStart, containerLast + 1);
                     this.highLowContainer.put(hbStart, newContainer);
                  }
               }

            }
         }
      }
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.serialize((DataOutput)out);
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.deserialize((DataInput)in);
   }

   public String toString() {
      StringBuilder answer = new StringBuilder("{}".length() + "-1234567890123456789,".length() * 256);
      LongIterator i = this.getLongIterator();
      answer.append('{');
      if (i.hasNext()) {
         answer.append(i.next());
      }

      while(i.hasNext()) {
         answer.append(',');
         if (answer.length() > 524288) {
            answer.append('.').append('.').append('.');
            break;
         }

         answer.append(i.next());
      }

      answer.append("}");
      return answer.toString();
   }

   public PeekableLongIterator getLongIterator() {
      LeafNodeIterator leafNodeIterator = this.highLowContainer.highKeyLeafNodeIterator(false);
      return new ForwardPeekableIterator(leafNodeIterator);
   }

   LeafNodeIterator getLeafNodeIterator() {
      return this.highLowContainer.highKeyLeafNodeIterator(false);
   }

   public PeekableLongIterator getLongIteratorFrom(long minval) {
      LeafNodeIterator leafNodeIterator = this.highLowContainer.highKeyLeafNodeIteratorFrom(minval, false);
      ForwardPeekableIterator fpi = new ForwardPeekableIterator(leafNodeIterator);
      fpi.advanceIfNeeded(minval);
      return fpi;
   }

   public boolean contains(long x) {
      byte[] high = LongUtils.highPart(x);
      ContainerWithIndex containerWithIdx = this.highLowContainer.searchContainer(high);
      if (containerWithIdx == null) {
         return false;
      } else {
         char low = LongUtils.lowPart(x);
         return containerWithIdx.getContainer().contains(low);
      }
   }

   public int getSizeInBytes() {
      return (int)this.getLongSizeInBytes();
   }

   public long getLongSizeInBytes() {
      return this.serializedSizeInBytes();
   }

   public boolean isEmpty() {
      return this.getLongCardinality() == 0L;
   }

   public ImmutableLongBitmapDataProvider limit(long x) {
      throw new UnsupportedOperationException("TODO");
   }

   public boolean runOptimize() {
      boolean hasChanged = false;
      ContainerIterator containerIterator = this.highLowContainer.containerIterator();

      while(containerIterator.hasNext()) {
         Container container = containerIterator.next();
         Container freshContainer = container.runOptimize();
         if (freshContainer instanceof RunContainer) {
            hasChanged = true;
            containerIterator.replace(freshContainer);
         }
      }

      return hasChanged;
   }

   public void serialize(DataOutput out) throws IOException {
      this.highLowContainer.serialize(out);
   }

   public void serialize(ByteBuffer byteBuffer) throws IOException {
      this.highLowContainer.serialize(byteBuffer);
   }

   public void deserialize(DataInput in) throws IOException {
      this.clear();
      this.highLowContainer.deserialize(in);
   }

   public void deserialize(ByteBuffer in) throws IOException {
      this.clear();
      this.highLowContainer.deserialize(in);
   }

   public long serializedSizeInBytes() {
      long nbBytes = this.highLowContainer.serializedSizeInBytes();
      return nbBytes;
   }

   public void clear() {
      this.highLowContainer.clear();
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

   public static Roaring64Bitmap bitmapOf(long... dat) {
      Roaring64Bitmap ans = new Roaring64Bitmap();
      ans.add(dat);
      return ans;
   }

   public void remove(long x) {
      byte[] highKey = LongUtils.highPart(x);
      ContainerWithIndex containerWithIdx = this.highLowContainer.searchContainer(highKey);
      if (containerWithIdx != null) {
         char low = LongUtils.lowPart(x);
         containerWithIdx.getContainer().remove(low);
         if (containerWithIdx.getContainer().isEmpty()) {
            this.highLowContainer.remove(highKey);
         }
      }

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
      if (rangeEnd != 0L && Long.compareUnsigned(rangeStart, rangeEnd) < 0) {
         long startHigh = LongUtils.rightShiftHighPart(rangeStart);
         int startLow = LongUtils.lowPart(rangeStart);
         long endHigh = LongUtils.rightShiftHighPart(rangeEnd - 1L);
         int endLow = LongUtils.lowPart(rangeEnd - 1L);
         long rangeStartVal = rangeStart;
         long startHighKey = LongUtils.rightShiftHighPart(rangeStart);

         for(byte[] startHighKeyBytes = LongUtils.highPart(rangeStart); startHighKey <= endHigh; startHighKeyBytes = LongUtils.highPart(rangeStartVal)) {
            int containerStart = startHighKey == startHigh ? startLow : 0;
            int containerLast = startHighKey == endHigh ? endLow : Util.maxLowBitAsInteger();
            ContainerWithIndex containerWithIndex = this.highLowContainer.searchContainer(startHighKeyBytes);
            if (containerWithIndex != null) {
               long containerIdx = containerWithIndex.getContainerIdx();
               Container freshContainer = this.highLowContainer.getContainer(containerIdx).iadd(containerStart, containerLast + 1);
               this.highLowContainer.replaceContainer(containerIdx, freshContainer);
            } else {
               Container freshContainer = Container.rangeOfOnes(containerStart, containerLast + 1);
               this.highLowContainer.put(startHighKeyBytes, freshContainer);
            }

            if (LongUtils.isMaxHigh(startHighKey)) {
               break;
            }

            rangeStartVal = rangeStartVal + (long)(containerLast - containerStart) + 1L;
            startHighKey = LongUtils.rightShiftHighPart(rangeStartVal);
         }

      } else {
         throw new IllegalArgumentException("Invalid range [" + rangeStart + "," + rangeEnd + ")");
      }
   }

   public PeekableLongIterator getReverseLongIterator() {
      LeafNodeIterator leafNodeIterator = this.highLowContainer.highKeyLeafNodeIterator(true);
      return new ReversePeekableIterator(leafNodeIterator);
   }

   public PeekableLongIterator getReverseLongIteratorFrom(long maxval) {
      LeafNodeIterator leafNodeIterator = this.highLowContainer.highKeyLeafNodeIteratorFrom(maxval, true);
      ReversePeekableIterator rpi = new ReversePeekableIterator(leafNodeIterator);
      rpi.advanceIfNeeded(maxval);
      return rpi;
   }

   public void removeLong(long x) {
      byte[] high = LongUtils.highPart(x);
      ContainerWithIndex containerWithIdx = this.highLowContainer.searchContainer(high);
      if (containerWithIdx != null) {
         char low = LongUtils.lowPart(x);
         Container container = containerWithIdx.getContainer();
         Container freshContainer = container.remove(low);
         if (freshContainer.isEmpty()) {
            this.highLowContainer.remove(high);
         } else {
            this.highLowContainer.replaceContainer(containerWithIdx.getContainerIdx(), freshContainer);
         }
      }

   }

   public void trim() {
      if (!this.highLowContainer.isEmpty()) {
         KeyIterator keyIterator = this.highLowContainer.highKeyIterator();

         while(keyIterator.hasNext()) {
            long containerIdx = keyIterator.currentContainerIdx();
            Container container = this.highLowContainer.getContainer(containerIdx);
            if (container.isEmpty()) {
               keyIterator.remove();
            } else {
               container.trim();
            }
         }

      }
   }

   public int hashCode() {
      return this.highLowContainer.hashCode();
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Roaring64Bitmap other = (Roaring64Bitmap)obj;
         return Objects.equals(this.highLowContainer, other.highLowContainer);
      }
   }

   public void flip(long x) {
      byte[] high = LongUtils.highPart(x);
      ContainerWithIndex containerWithIndex = this.highLowContainer.searchContainer(high);
      if (containerWithIndex == null) {
         this.addLong(x);
      } else {
         char low = LongUtils.lowPart(x);
         Container freshOne = containerWithIndex.getContainer().flip(low);
         this.highLowContainer.replaceContainer(containerWithIndex.getContainerIdx(), freshOne);
      }

   }

   public Roaring64Bitmap clone() {
      long sizeInBytesL = this.serializedSizeInBytes();
      if (sizeInBytesL >= 2147483647L) {
         throw new UnsupportedOperationException();
      } else {
         int sizeInBytesInt = (int)sizeInBytesL;
         ByteBuffer byteBuffer = ByteBuffer.allocate(sizeInBytesInt).order(ByteOrder.LITTLE_ENDIAN);

         try {
            this.serialize(byteBuffer);
            byteBuffer.flip();
            Roaring64Bitmap freshOne = new Roaring64Bitmap();
            freshOne.deserialize(byteBuffer);
            return freshOne;
         } catch (Exception e) {
            throw new RuntimeException("fail to clone thorough the ser/deser", e);
         }
      }
   }

   private abstract class PeekableIterator implements PeekableLongIterator {
      private final LeafNodeIterator keyIte;
      private byte[] high;
      private PeekableCharIterator charIterator;

      PeekableIterator(final LeafNodeIterator keyIte) {
         this.keyIte = keyIte;
      }

      abstract PeekableCharIterator getIterator(Container var1);

      abstract boolean compare(long var1, long var3);

      public boolean hasNext() {
         if (this.charIterator != null && this.charIterator.hasNext()) {
            return true;
         } else {
            while(this.keyIte.hasNext()) {
               LeafNode leafNode = this.keyIte.next();
               this.high = leafNode.getKeyBytes();
               long containerIdx = leafNode.getContainerIdx();
               Container container = Roaring64Bitmap.this.highLowContainer.getContainer(containerIdx);
               this.charIterator = this.getIterator(container);
               if (this.charIterator.hasNext()) {
                  return true;
               }
            }

            return false;
         }
      }

      public long next() {
         if (this.hasNext()) {
            char low = this.charIterator.next();
            return LongUtils.toLong(this.high, low);
         } else {
            throw new IllegalStateException("empty");
         }
      }

      public void advanceIfNeeded(long minval) {
         if (this.hasNext()) {
            if (!this.compare(this.peekNext(), minval)) {
               if (this.high != null) {
                  long minHigh = LongUtils.rightShiftHighPart(minval);
                  long high = LongUtils.toLong(this.high);
                  if (minHigh != high && this.keyIte.hasNext()) {
                     LeafNode leafNode = this.keyIte.next();
                     this.high = leafNode.getKeyBytes();
                     if (this.compare(leafNode.getKey(), minHigh)) {
                        long containerIdx = leafNode.getContainerIdx();
                        Container container = Roaring64Bitmap.this.highLowContainer.getContainer(containerIdx);
                        this.charIterator = this.getIterator(container);
                        if (!this.charIterator.hasNext()) {
                           return;
                        }
                     } else {
                        this.keyIte.seek(minval);
                        if (!this.keyIte.hasNext()) {
                           this.charIterator = null;
                           return;
                        }

                        leafNode = this.keyIte.next();
                        this.high = leafNode.getKeyBytes();
                        long containerIdx = leafNode.getContainerIdx();
                        Container container = Roaring64Bitmap.this.highLowContainer.getContainer(containerIdx);
                        this.charIterator = this.getIterator(container);
                        if (!this.charIterator.hasNext()) {
                           return;
                        }
                     }
                  }

                  byte[] minHighBytes = LongUtils.highPart(minval);
                  if (Arrays.equals(this.high, minHighBytes)) {
                     char low = LongUtils.lowPart(minval);
                     this.charIterator.advanceIfNeeded(low);
                  }

               }
            }
         }
      }

      public long peekNext() {
         if (this.hasNext()) {
            char low = this.charIterator.peekNext();
            return LongUtils.toLong(this.high, low);
         } else {
            throw new IllegalStateException("empty");
         }
      }

      public PeekableLongIterator clone() {
         throw new UnsupportedOperationException("TODO");
      }
   }

   private class ForwardPeekableIterator extends PeekableIterator {
      public ForwardPeekableIterator(final LeafNodeIterator keyIte) {
         super(keyIte);
      }

      PeekableCharIterator getIterator(Container container) {
         return container.getCharIterator();
      }

      boolean compare(long next, long val) {
         return Long.compareUnsigned(next, val) >= 0;
      }
   }

   private class ReversePeekableIterator extends PeekableIterator {
      public ReversePeekableIterator(final LeafNodeIterator keyIte) {
         super(keyIte);
      }

      PeekableCharIterator getIterator(Container container) {
         return container.getReverseCharIterator();
      }

      boolean compare(long next, long val) {
         return Long.compareUnsigned(next, val) <= 0;
      }
   }
}
