package org.roaringbitmap.longlong;

import java.io.DataOutput;
import java.io.IOException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

public interface ImmutableLongBitmapDataProvider {
   boolean contains(long var1);

   long getLongCardinality();

   void forEach(LongConsumer var1);

   LongIterator getLongIterator();

   LongIterator getReverseLongIterator();

   default LongStream stream() {
      int characteristics = 85;
      Spliterator.OfLong x = Spliterators.spliterator(new RoaringOfLong(this.getLongIterator()), this.getLongCardinality(), characteristics);
      return StreamSupport.longStream(x, false);
   }

   default LongStream reverseStream() {
      int characteristics = 81;
      Spliterator.OfLong x = Spliterators.spliterator(new RoaringOfLong(this.getLongIterator()), this.getLongCardinality(), characteristics);
      return StreamSupport.longStream(x, false);
   }

   int getSizeInBytes();

   long getLongSizeInBytes();

   boolean isEmpty();

   ImmutableLongBitmapDataProvider limit(long var1);

   long rankLong(long var1);

   long select(long var1);

   long first();

   long last();

   void serialize(DataOutput var1) throws IOException;

   long serializedSizeInBytes();

   long[] toArray();

   public static final class RoaringOfLong implements PrimitiveIterator.OfLong {
      private final LongIterator iterator;

      public RoaringOfLong(LongIterator iterator) {
         this.iterator = iterator;
      }

      public long nextLong() {
         return this.iterator.next();
      }

      public boolean hasNext() {
         return this.iterator.hasNext();
      }
   }
}
