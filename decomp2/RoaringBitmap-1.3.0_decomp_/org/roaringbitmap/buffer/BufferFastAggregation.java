package org.roaringbitmap.buffer;

import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import org.roaringbitmap.Util;

public final class BufferFastAggregation {
   public static MutableRoaringBitmap and(ImmutableRoaringBitmap... bitmaps) {
      return bitmaps.length > 10 ? workShyAnd(new long[1024], bitmaps) : naive_and(bitmaps);
   }

   public static MutableRoaringBitmap and(long[] aggregationBuffer, ImmutableRoaringBitmap... bitmaps) {
      if (bitmaps.length > 10) {
         if (aggregationBuffer.length < 1024) {
            throw new IllegalArgumentException("buffer should have at least 1024 elements.");
         } else {
            MutableRoaringBitmap var2;
            try {
               var2 = workShyAnd(aggregationBuffer, bitmaps);
            } finally {
               Arrays.fill(aggregationBuffer, 0L);
            }

            return var2;
         }
      } else {
         return naive_and(bitmaps);
      }
   }

   public static MutableRoaringBitmap and(Iterator bitmaps) {
      return and(new long[1024], bitmaps);
   }

   public static MutableRoaringBitmap and(long[] aggregationBuffer, Iterator bitmaps) {
      if (bitmaps.hasNext()) {
         MutableRoaringBitmap var2;
         try {
            var2 = workShyAnd(aggregationBuffer, bitmaps);
         } finally {
            Arrays.fill(aggregationBuffer, 0L);
         }

         return var2;
      } else {
         return new MutableRoaringBitmap();
      }
   }

   public static MutableRoaringBitmap and(MutableRoaringBitmap... bitmaps) {
      return and(convertToImmutable(bitmaps));
   }

   public static int andCardinality(ImmutableRoaringBitmap... bitmaps) {
      switch (bitmaps.length) {
         case 0:
            return 0;
         case 1:
            return bitmaps[0].getCardinality();
         case 2:
            return ImmutableRoaringBitmap.andCardinality(bitmaps[0], bitmaps[1]);
         default:
            return workShyAndCardinality(bitmaps);
      }
   }

   public static int orCardinality(ImmutableRoaringBitmap... bitmaps) {
      switch (bitmaps.length) {
         case 0:
            return 0;
         case 1:
            return bitmaps[0].getCardinality();
         case 2:
            return ImmutableRoaringBitmap.orCardinality(bitmaps[0], bitmaps[1]);
         default:
            return horizontalOrCardinality(bitmaps);
      }
   }

   public static Iterator convertToImmutable(final Iterator i) {
      return new Iterator() {
         public boolean hasNext() {
            return i.hasNext();
         }

         public ImmutableRoaringBitmap next() {
            return (ImmutableRoaringBitmap)i.next();
         }

         public void remove() {
         }
      };
   }

   private static ImmutableRoaringBitmap[] convertToImmutable(MutableRoaringBitmap[] array) {
      ImmutableRoaringBitmap[] answer = new ImmutableRoaringBitmap[array.length];
      System.arraycopy(array, 0, answer, 0, answer.length);
      return answer;
   }

   public static MutableRoaringBitmap horizontal_or(ImmutableRoaringBitmap... bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      if (bitmaps.length == 0) {
         return answer;
      } else {
         PriorityQueue<MappeableContainerPointer> pq = new PriorityQueue(bitmaps.length);

         for(int k = 0; k < bitmaps.length; ++k) {
            MappeableContainerPointer x = bitmaps[k].highLowContainer.getContainerPointer();
            if (x.getContainer() != null) {
               pq.add(x);
            }
         }

         while(!pq.isEmpty()) {
            MappeableContainerPointer x1 = (MappeableContainerPointer)pq.poll();
            if (!pq.isEmpty() && ((MappeableContainerPointer)pq.peek()).key() == x1.key()) {
               MappeableContainerPointer x2 = (MappeableContainerPointer)pq.poll();
               MappeableContainer newc = x1.getContainer().lazyOR(x2.getContainer());

               while(!pq.isEmpty() && ((MappeableContainerPointer)pq.peek()).key() == x1.key()) {
                  MappeableContainerPointer x = (MappeableContainerPointer)pq.poll();
                  newc = newc.lazyIOR(x.getContainer());
                  x.advance();
                  if (x.getContainer() != null) {
                     pq.add(x);
                  } else if (pq.isEmpty()) {
                     break;
                  }
               }

               newc = newc.repairAfterLazy();
               answer.getMappeableRoaringArray().append(x1.key(), newc);
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }

               x2.advance();
               if (x2.getContainer() != null) {
                  pq.add(x2);
               }
            } else {
               answer.getMappeableRoaringArray().append(x1.key(), x1.getContainer().clone());
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }
            }
         }

         return answer;
      }
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap horizontal_or(Iterator bitmaps) {
      return naive_or(bitmaps);
   }

   public static MutableRoaringBitmap horizontal_or(MutableRoaringBitmap... bitmaps) {
      return horizontal_or(convertToImmutable(bitmaps));
   }

   public static MutableRoaringBitmap horizontal_xor(ImmutableRoaringBitmap... bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      if (bitmaps.length == 0) {
         return answer;
      } else {
         PriorityQueue<MappeableContainerPointer> pq = new PriorityQueue(bitmaps.length);

         for(int k = 0; k < bitmaps.length; ++k) {
            MappeableContainerPointer x = bitmaps[k].highLowContainer.getContainerPointer();
            if (x.getContainer() != null) {
               pq.add(x);
            }
         }

         while(!pq.isEmpty()) {
            MappeableContainerPointer x1 = (MappeableContainerPointer)pq.poll();
            if (!pq.isEmpty() && ((MappeableContainerPointer)pq.peek()).key() == x1.key()) {
               MappeableContainerPointer x2 = (MappeableContainerPointer)pq.poll();
               MappeableContainer newc = x1.getContainer().xor(x2.getContainer());

               while(!pq.isEmpty() && ((MappeableContainerPointer)pq.peek()).key() == x1.key()) {
                  MappeableContainerPointer x = (MappeableContainerPointer)pq.poll();
                  newc = newc.ixor(x.getContainer());
                  x.advance();
                  if (x.getContainer() != null) {
                     pq.add(x);
                  } else if (pq.isEmpty()) {
                     break;
                  }
               }

               answer.getMappeableRoaringArray().append(x1.key(), newc);
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }

               x2.advance();
               if (x2.getContainer() != null) {
                  pq.add(x2);
               }
            } else {
               answer.getMappeableRoaringArray().append(x1.key(), x1.getContainer().clone());
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }
            }
         }

         return answer;
      }
   }

   public static MutableRoaringBitmap horizontal_xor(MutableRoaringBitmap... bitmaps) {
      return horizontal_xor(convertToImmutable(bitmaps));
   }

   public static MutableRoaringBitmap naive_and(ImmutableRoaringBitmap... bitmaps) {
      MutableRoaringBitmap answer;
      if (bitmaps.length > 0) {
         ImmutableRoaringBitmap smallest = bitmaps[0];

         for(int i = 1; i < bitmaps.length; ++i) {
            ImmutableRoaringBitmap bitmap = bitmaps[i];
            if (bitmap.highLowContainer.size() < smallest.highLowContainer.size()) {
               smallest = bitmap;
            }
         }

         answer = smallest.toMutableRoaringBitmap();

         for(ImmutableRoaringBitmap bitmap : bitmaps) {
            if (bitmap != smallest) {
               answer.and(bitmap);
            }
         }
      } else {
         answer = new MutableRoaringBitmap();
      }

      return answer;
   }

   public static MutableRoaringBitmap naive_and(Iterator bitmaps) {
      if (!bitmaps.hasNext()) {
         return new MutableRoaringBitmap();
      } else {
         MutableRoaringBitmap answer = ((ImmutableRoaringBitmap)bitmaps.next()).toMutableRoaringBitmap();

         while(bitmaps.hasNext()) {
            answer.and((ImmutableRoaringBitmap)bitmaps.next());
         }

         return answer;
      }
   }

   public static MutableRoaringBitmap naive_and(MutableRoaringBitmap... bitmaps) {
      if (bitmaps.length == 0) {
         return new MutableRoaringBitmap();
      } else {
         MutableRoaringBitmap answer = bitmaps[0].clone();

         for(int k = 1; k < bitmaps.length; ++k) {
            answer.and(bitmaps[k]);
         }

         return answer;
      }
   }

   static MutableRoaringBitmap workShyAnd(long[] aggregationBuffer, ImmutableRoaringBitmap... bitmaps) {
      long[] words = aggregationBuffer;
      ImmutableRoaringBitmap first = bitmaps[0];

      for(int i = 0; i < first.highLowContainer.size(); ++i) {
         char key = first.highLowContainer.getKeyAtIndex(i);
         words[key >>> 6] |= 1L << key;
      }

      int numContainers = first.highLowContainer.size();

      for(int i = 1; i < bitmaps.length && numContainers > 0; ++i) {
         char[] keys;
         if (bitmaps[i].highLowContainer instanceof MutableRoaringArray) {
            keys = ((MutableRoaringArray)bitmaps[i].highLowContainer).keys;
         } else {
            keys = new char[bitmaps[i].highLowContainer.size()];

            for(int j = 0; j < keys.length; ++j) {
               keys[j] = bitmaps[i].highLowContainer.getKeyAtIndex(j);
            }
         }

         numContainers = BufferUtil.intersectArrayIntoBitmap(words, CharBuffer.wrap(keys), bitmaps[i].highLowContainer.size());
      }

      if (numContainers == 0) {
         return new MutableRoaringBitmap();
      } else {
         char[] keys = new char[numContainers];
         int base = 0;
         int pos = 0;
         long[] var8 = words;
         int var9 = words.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            for(long word = var8[var10]; word != 0L; word &= word - 1L) {
               keys[pos++] = (char)(base + Long.numberOfTrailingZeros(word));
            }

            base += 64;
         }

         MappeableContainer[][] containers = new MappeableContainer[numContainers][bitmaps.length];

         for(int i = 0; i < bitmaps.length; ++i) {
            ImmutableRoaringBitmap bitmap = bitmaps[i];
            int position = 0;

            for(int j = 0; j < bitmap.highLowContainer.size(); ++j) {
               char key = bitmap.highLowContainer.getKeyAtIndex(j);
               if ((words[key >>> 6] & 1L << key) != 0L) {
                  containers[position++][i] = bitmap.highLowContainer.getContainerAtIndex(j);
               }
            }
         }

         MutableRoaringArray array = new MutableRoaringArray(keys, new MappeableContainer[numContainers], 0);

         for(int i = 0; i < numContainers; ++i) {
            MappeableContainer[] slice = containers[i];
            Arrays.fill(words, -1L);
            MappeableContainer tmp = new MappeableBitmapContainer(LongBuffer.wrap(words), -1);

            for(MappeableContainer container : slice) {
               MappeableContainer and = tmp.iand(container);
               if (and != tmp) {
                  tmp = and;
               }
            }

            tmp = tmp.repairAfterLazy();
            if (!tmp.isEmpty()) {
               array.append(keys[i], tmp instanceof MappeableBitmapContainer ? tmp.clone() : tmp);
            }
         }

         return new MutableRoaringBitmap(array);
      }
   }

   static MutableRoaringBitmap workShyAnd(long[] aggregationBuffer, Iterator bitmaps) {
      long[] words = aggregationBuffer;
      List<ImmutableRoaringBitmap> collected = new ArrayList();
      ImmutableRoaringBitmap first = (ImmutableRoaringBitmap)bitmaps.next();
      collected.add(first);

      for(int i = 0; i < first.highLowContainer.size(); ++i) {
         char key = first.highLowContainer.getKeyAtIndex(i);
         words[key >>> 6] |= 1L << key;
      }

      int bitmapCount = 1;

      char[] keys;
      ImmutableRoaringBitmap bitmap;
      int numContainers;
      for(numContainers = first.highLowContainer.size(); numContainers > 0 && bitmaps.hasNext(); numContainers = BufferUtil.intersectArrayIntoBitmap(words, CharBuffer.wrap(keys), bitmap.highLowContainer.size())) {
         bitmap = (ImmutableRoaringBitmap)bitmaps.next();
         collected.add(bitmap);
         ++bitmapCount;
         if (bitmap.highLowContainer instanceof MutableRoaringArray) {
            keys = ((MutableRoaringArray)bitmap.highLowContainer).keys;
         } else {
            keys = new char[bitmap.highLowContainer.size()];

            for(int j = 0; j < keys.length; ++j) {
               keys[j] = bitmap.highLowContainer.getKeyAtIndex(j);
            }
         }
      }

      if (numContainers == 0) {
         return new MutableRoaringBitmap();
      } else {
         keys = new char[numContainers];
         int base = 0;
         int pos = 0;
         long[] var10 = words;
         int var11 = words.length;

         for(int var12 = 0; var12 < var11; ++var12) {
            for(long word = var10[var12]; word != 0L; word &= word - 1L) {
               keys[pos++] = (char)(base + Long.numberOfTrailingZeros(word));
            }

            base += 64;
         }

         MappeableContainer[][] containers = new MappeableContainer[numContainers][bitmapCount];

         for(int i = 0; i < bitmapCount; ++i) {
            ImmutableRoaringBitmap bitmap = (ImmutableRoaringBitmap)collected.get(i);
            int position = 0;

            for(int j = 0; j < bitmap.highLowContainer.size(); ++j) {
               char key = bitmap.highLowContainer.getKeyAtIndex(j);
               if ((words[key >>> 6] & 1L << key) != 0L) {
                  containers[position++][i] = bitmap.highLowContainer.getContainerAtIndex(j);
               }
            }
         }

         MutableRoaringArray array = new MutableRoaringArray(keys, new MappeableContainer[numContainers], 0);

         for(int i = 0; i < numContainers; ++i) {
            MappeableContainer[] slice = containers[i];
            Arrays.fill(words, -1L);
            MappeableContainer tmp = new MappeableBitmapContainer(LongBuffer.wrap(words), -1);

            for(MappeableContainer container : slice) {
               MappeableContainer and = tmp.iand(container);
               if (and != tmp) {
                  tmp = and;
               }
            }

            tmp = tmp.repairAfterLazy();
            if (!tmp.isEmpty()) {
               array.append(keys[i], tmp instanceof MappeableBitmapContainer ? tmp.clone() : tmp);
            }
         }

         return new MutableRoaringBitmap(array);
      }
   }

   private static int workShyAndCardinality(ImmutableRoaringBitmap... bitmaps) {
      long[] words = new long[1024];
      ImmutableRoaringBitmap first = bitmaps[0];

      for(int i = 0; i < first.highLowContainer.size(); ++i) {
         char key = first.highLowContainer.getKeyAtIndex(i);
         words[key >>> 6] |= 1L << key;
      }

      int numKeys = first.highLowContainer.size();

      for(int i = 1; i < bitmaps.length && numKeys > 0; ++i) {
         char[] keys;
         if (bitmaps[i].highLowContainer instanceof MutableRoaringArray) {
            keys = ((MutableRoaringArray)bitmaps[i].highLowContainer).keys;
         } else {
            keys = new char[bitmaps[i].highLowContainer.size()];

            for(int j = 0; j < keys.length; ++j) {
               keys[j] = bitmaps[i].highLowContainer.getKeyAtIndex(j);
            }
         }

         numKeys = BufferUtil.intersectArrayIntoBitmap(words, CharBuffer.wrap(keys), bitmaps[i].highLowContainer.size());
      }

      if (numKeys == 0) {
         return 0;
      } else {
         char[] keys = new char[numKeys];
         int base = 0;
         int pos = 0;
         long[] var7 = words;
         int cardinality = words.length;

         for(int var9 = 0; var9 < cardinality; ++var9) {
            for(long word = var7[var9]; word != 0L; word &= word - 1L) {
               keys[pos++] = (char)(base + Long.numberOfTrailingZeros(word));
            }

            base += 64;
         }

         LongBuffer longBuffer = LongBuffer.wrap(words);
         cardinality = 0;

         for(char key : keys) {
            Arrays.fill(words, -1L);
            MappeableContainer tmp = new MappeableBitmapContainer(longBuffer, -1);

            for(ImmutableRoaringBitmap bitmap : bitmaps) {
               int index = bitmap.highLowContainer.getIndex(key);
               if (index >= 0) {
                  MappeableContainer container = bitmap.highLowContainer.getContainerAtIndex(index);
                  MappeableContainer and = tmp.iand(container);
                  if (and != tmp) {
                     tmp = and;
                  }
               }
            }

            cardinality += tmp.repairAfterLazy().getCardinality();
         }

         return cardinality;
      }
   }

   private static int horizontalOrCardinality(ImmutableRoaringBitmap... bitmaps) {
      long[] words = new long[1024];
      int minKey = 65535;
      int maxKey = 0;

      for(ImmutableRoaringBitmap bitmap : bitmaps) {
         for(int i = 0; i < bitmap.highLowContainer.size(); ++i) {
            char key = bitmap.highLowContainer.getKeyAtIndex(i);
            words[key >>> 6] |= 1L << key;
            minKey = Math.min(minKey, key);
            maxKey = Math.max(maxKey, key);
         }
      }

      int numKeys = Util.cardinalityInBitmapRange(words, minKey, maxKey + 1);
      char[] keys = new char[numKeys];
      int base = 0;
      int pos = 0;
      long[] var26 = words;
      int cardinality = words.length;

      for(int var10 = 0; var10 < cardinality; ++var10) {
         for(long word = var26[var10]; word != 0L; word &= word - 1L) {
            keys[pos++] = (char)(base + Long.numberOfTrailingZeros(word));
         }

         base += 64;
      }

      LongBuffer longBuffer = LongBuffer.wrap(words);
      cardinality = 0;

      for(char key : keys) {
         Arrays.fill(words, 0L);
         MappeableContainer tmp = new MappeableBitmapContainer(longBuffer, -1);

         for(ImmutableRoaringBitmap bitmap : bitmaps) {
            int index = bitmap.highLowContainer.getIndex(key);
            if (index >= 0) {
               MappeableContainer container = bitmap.highLowContainer.getContainerAtIndex(index);
               MappeableContainer or = tmp.lazyIOR(container);
               if (or != tmp) {
                  tmp = or;
               }
            }
         }

         cardinality += tmp.repairAfterLazy().getCardinality();
      }

      return cardinality;
   }

   public static MutableRoaringBitmap workAndMemoryShyAnd(long[] buffer, ImmutableRoaringBitmap... bitmaps) {
      if (buffer.length < 1024) {
         throw new IllegalArgumentException("buffer should have at least 1024 elements.");
      } else {
         long[] words = buffer;
         ImmutableRoaringBitmap first = bitmaps[0];

         for(int i = 0; i < first.highLowContainer.size(); ++i) {
            char key = first.highLowContainer.getKeyAtIndex(i);
            words[key >>> 6] |= 1L << key;
         }

         int numContainers = first.highLowContainer.size();

         for(int i = 1; i < bitmaps.length && numContainers > 0; ++i) {
            char[] keys;
            if (bitmaps[i].highLowContainer instanceof MutableRoaringArray) {
               keys = ((MutableRoaringArray)bitmaps[i].highLowContainer).keys;
            } else {
               keys = new char[bitmaps[i].highLowContainer.size()];

               for(int j = 0; j < keys.length; ++j) {
                  keys[j] = bitmaps[i].highLowContainer.getKeyAtIndex(j);
               }
            }

            numContainers = BufferUtil.intersectArrayIntoBitmap(words, CharBuffer.wrap(keys), bitmaps[i].highLowContainer.size());
         }

         if (numContainers == 0) {
            return new MutableRoaringBitmap();
         } else {
            char[] keys = new char[numContainers];
            int base = 0;
            int pos = 0;
            long[] var8 = words;
            int var9 = words.length;

            for(int var10 = 0; var10 < var9; ++var10) {
               for(long word = var8[var10]; word != 0L; word &= word - 1L) {
                  keys[pos++] = (char)(base + Long.numberOfTrailingZeros(word));
               }

               base += 64;
            }

            MutableRoaringArray array = new MutableRoaringArray(keys, new MappeableContainer[numContainers], 0);

            for(int i = 0; i < numContainers; ++i) {
               char MatchingKey = keys[i];
               Arrays.fill(words, -1L);
               MappeableContainer tmp = new MappeableBitmapContainer(LongBuffer.wrap(words), -1);

               for(ImmutableRoaringBitmap bitmap : bitmaps) {
                  int idx = bitmap.highLowContainer.getIndex(MatchingKey);
                  if (idx >= 0) {
                     MappeableContainer container = bitmap.highLowContainer.getContainerAtIndex(idx);
                     MappeableContainer and = tmp.iand(container);
                     if (and != tmp) {
                        tmp = and;
                     }
                  }
               }

               tmp = tmp.repairAfterLazy();
               if (!tmp.isEmpty()) {
                  array.append(keys[i], tmp instanceof MappeableBitmapContainer ? tmp.clone() : tmp);
               }
            }

            return new MutableRoaringBitmap(array);
         }
      }
   }

   public static MutableRoaringBitmap naive_or(ImmutableRoaringBitmap... bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();

      for(int k = 0; k < bitmaps.length; ++k) {
         answer.naivelazyor(bitmaps[k]);
      }

      answer.repairAfterLazy();
      return answer;
   }

   public static MutableRoaringBitmap naive_or(Iterator bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();

      while(bitmaps.hasNext()) {
         answer.naivelazyor((ImmutableRoaringBitmap)bitmaps.next());
      }

      answer.repairAfterLazy();
      return answer;
   }

   public static MutableRoaringBitmap naive_or(MutableRoaringBitmap... bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();

      for(int k = 0; k < bitmaps.length; ++k) {
         answer.lazyor(bitmaps[k]);
      }

      answer.repairAfterLazy();
      return answer;
   }

   public static MutableRoaringBitmap naive_xor(ImmutableRoaringBitmap... bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();

      for(int k = 0; k < bitmaps.length; ++k) {
         answer.xor(bitmaps[k]);
      }

      return answer;
   }

   public static MutableRoaringBitmap naive_xor(Iterator bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();

      while(bitmaps.hasNext()) {
         answer.xor((ImmutableRoaringBitmap)bitmaps.next());
      }

      return answer;
   }

   public static MutableRoaringBitmap naive_xor(MutableRoaringBitmap... bitmaps) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();

      for(int k = 0; k < bitmaps.length; ++k) {
         answer.xor(bitmaps[k]);
      }

      return answer;
   }

   public static MutableRoaringBitmap or(ImmutableRoaringBitmap... bitmaps) {
      return naive_or(bitmaps);
   }

   public static MutableRoaringBitmap or(Iterator bitmaps) {
      return naive_or(bitmaps);
   }

   public static MutableRoaringBitmap or(MutableRoaringBitmap... bitmaps) {
      return naive_or(bitmaps);
   }

   public static MutableRoaringBitmap priorityqueue_or(ImmutableRoaringBitmap... bitmaps) {
      if (bitmaps.length == 0) {
         return new MutableRoaringBitmap();
      } else if (bitmaps.length == 1) {
         return bitmaps[0].toMutableRoaringBitmap();
      } else {
         ImmutableRoaringBitmap[] buffer = (ImmutableRoaringBitmap[])Arrays.copyOf(bitmaps, bitmaps.length);
         final int[] sizes = new int[buffer.length];
         boolean[] istmp = new boolean[buffer.length];

         for(int k = 0; k < sizes.length; ++k) {
            sizes[k] = buffer[k].serializedSizeInBytes();
         }

         PriorityQueue<Integer> pq = new PriorityQueue(128, new Comparator() {
            public int compare(Integer a, Integer b) {
               return sizes[a] - sizes[b];
            }
         });

         for(int k = 0; k < sizes.length; ++k) {
            pq.add(k);
         }

         while(pq.size() > 1) {
            Integer x1 = (Integer)pq.poll();
            Integer x2 = (Integer)pq.poll();
            if (istmp[x1] && istmp[x2]) {
               buffer[x1] = MutableRoaringBitmap.lazyorfromlazyinputs((MutableRoaringBitmap)buffer[x1], (MutableRoaringBitmap)buffer[x2]);
               sizes[x1] = buffer[x1].serializedSizeInBytes();
               pq.add(x1);
            } else if (istmp[x2]) {
               ((MutableRoaringBitmap)buffer[x2]).lazyor(buffer[x1]);
               sizes[x2] = buffer[x2].serializedSizeInBytes();
               pq.add(x2);
            } else if (istmp[x1]) {
               ((MutableRoaringBitmap)buffer[x1]).lazyor(buffer[x2]);
               sizes[x1] = buffer[x1].serializedSizeInBytes();
               pq.add(x1);
            } else {
               buffer[x1] = ImmutableRoaringBitmap.lazyor(buffer[x1], buffer[x2]);
               sizes[x1] = buffer[x1].serializedSizeInBytes();
               istmp[x1] = true;
               pq.add(x1);
            }
         }

         MutableRoaringBitmap answer = (MutableRoaringBitmap)buffer[(Integer)pq.poll()];
         answer.repairAfterLazy();
         return answer;
      }
   }

   public static MutableRoaringBitmap priorityqueue_or(Iterator bitmaps) {
      if (!bitmaps.hasNext()) {
         return new MutableRoaringBitmap();
      } else {
         ArrayList<ImmutableRoaringBitmap> buffer = new ArrayList();

         while(bitmaps.hasNext()) {
            buffer.add((ImmutableRoaringBitmap)bitmaps.next());
         }

         final long[] sizes = new long[buffer.size()];
         boolean[] istmp = new boolean[buffer.size()];

         for(int k = 0; k < sizes.length; ++k) {
            sizes[k] = ((ImmutableRoaringBitmap)buffer.get(k)).getLongSizeInBytes();
         }

         PriorityQueue<Integer> pq = new PriorityQueue(128, new Comparator() {
            public int compare(Integer a, Integer b) {
               return (int)(sizes[a] - sizes[b]);
            }
         });

         for(int k = 0; k < sizes.length; ++k) {
            pq.add(k);
         }

         if (pq.size() == 1) {
            return ((ImmutableRoaringBitmap)buffer.get((Integer)pq.poll())).toMutableRoaringBitmap();
         } else {
            while(pq.size() > 1) {
               Integer x1 = (Integer)pq.poll();
               Integer x2 = (Integer)pq.poll();
               if (istmp[x1] && istmp[x2]) {
                  buffer.set(x1, MutableRoaringBitmap.lazyorfromlazyinputs((MutableRoaringBitmap)buffer.get(x1), (MutableRoaringBitmap)buffer.get(x2)));
                  sizes[x1] = ((ImmutableRoaringBitmap)buffer.get(x1)).getLongSizeInBytes();
                  pq.add(x1);
               } else if (istmp[x2]) {
                  ((MutableRoaringBitmap)buffer.get(x2)).lazyor((ImmutableRoaringBitmap)buffer.get(x1));
                  sizes[x2] = ((ImmutableRoaringBitmap)buffer.get(x2)).getLongSizeInBytes();
                  pq.add(x2);
               } else if (istmp[x1]) {
                  ((MutableRoaringBitmap)buffer.get(x1)).lazyor((ImmutableRoaringBitmap)buffer.get(x2));
                  sizes[x1] = ((ImmutableRoaringBitmap)buffer.get(x1)).getLongSizeInBytes();
                  pq.add(x1);
               } else {
                  buffer.set(x1, ImmutableRoaringBitmap.lazyor((ImmutableRoaringBitmap)buffer.get(x1), (ImmutableRoaringBitmap)buffer.get(x2)));
                  sizes[x1] = ((ImmutableRoaringBitmap)buffer.get(x1)).getLongSizeInBytes();
                  istmp[x1] = true;
                  pq.add(x1);
               }
            }

            MutableRoaringBitmap answer = (MutableRoaringBitmap)buffer.get((Integer)pq.poll());
            answer.repairAfterLazy();
            return answer;
         }
      }
   }

   public static MutableRoaringBitmap priorityqueue_xor(ImmutableRoaringBitmap... bitmaps) {
      if (bitmaps.length < 2) {
         throw new IllegalArgumentException("Expecting at least 2 bitmaps");
      } else {
         PriorityQueue<ImmutableRoaringBitmap> pq = new PriorityQueue(bitmaps.length, new Comparator() {
            public int compare(ImmutableRoaringBitmap a, ImmutableRoaringBitmap b) {
               return (int)(a.getLongSizeInBytes() - b.getLongSizeInBytes());
            }
         });
         Collections.addAll(pq, bitmaps);

         while(pq.size() > 1) {
            ImmutableRoaringBitmap x1 = (ImmutableRoaringBitmap)pq.poll();
            ImmutableRoaringBitmap x2 = (ImmutableRoaringBitmap)pq.poll();
            pq.add(ImmutableRoaringBitmap.xor(x1, x2));
         }

         return (MutableRoaringBitmap)pq.poll();
      }
   }

   public static MutableRoaringBitmap xor(ImmutableRoaringBitmap... bitmaps) {
      return naive_xor(bitmaps);
   }

   public static MutableRoaringBitmap xor(Iterator bitmaps) {
      return naive_xor(bitmaps);
   }

   public static MutableRoaringBitmap xor(MutableRoaringBitmap... bitmaps) {
      return naive_xor(bitmaps);
   }

   private BufferFastAggregation() {
   }
}
