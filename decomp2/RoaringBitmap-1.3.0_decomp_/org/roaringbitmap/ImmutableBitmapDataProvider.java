package org.roaringbitmap;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public interface ImmutableBitmapDataProvider {
   boolean contains(int var1);

   int getCardinality();

   long getLongCardinality();

   void forEach(IntConsumer var1);

   PeekableIntIterator getIntIterator();

   PeekableIntIterator getSignedIntIterator();

   IntIterator getReverseIntIterator();

   default IntStream stream() {
      int characteristics = 85;
      Spliterator.OfInt x = Spliterators.spliterator(new RoaringOfInt(this.getIntIterator()), (long)this.getCardinality(), characteristics);
      return StreamSupport.intStream(x, false);
   }

   default IntStream reverseStream() {
      int characteristics = 81;
      Spliterator.OfInt x = Spliterators.spliterator(new RoaringOfInt(this.getReverseIntIterator()), (long)this.getCardinality(), characteristics);
      return StreamSupport.intStream(x, false);
   }

   BatchIterator getBatchIterator();

   int getSizeInBytes();

   long getLongSizeInBytes();

   boolean isEmpty();

   ImmutableBitmapDataProvider limit(int var1);

   int rank(int var1);

   long rankLong(int var1);

   long rangeCardinality(long var1, long var3);

   int select(int var1);

   int first();

   int last();

   int firstSigned();

   int lastSigned();

   long nextValue(int var1);

   long previousValue(int var1);

   long nextAbsentValue(int var1);

   long previousAbsentValue(int var1);

   void serialize(DataOutput var1) throws IOException;

   void serialize(ByteBuffer var1);

   int serializedSizeInBytes();

   int[] toArray();

   int getContainerCount();

   public static final class RoaringOfInt implements PrimitiveIterator.OfInt {
      private final IntIterator iterator;

      public RoaringOfInt(IntIterator iterator) {
         this.iterator = iterator;
      }

      public int nextInt() {
         return this.iterator.next();
      }

      public boolean hasNext() {
         return this.iterator.hasNext();
      }
   }
}
