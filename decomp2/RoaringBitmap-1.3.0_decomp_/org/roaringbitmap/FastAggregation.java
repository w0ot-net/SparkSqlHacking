package org.roaringbitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public final class FastAggregation {
   public static RoaringBitmap and(Iterator bitmaps) {
      return naive_and(bitmaps);
   }

   public static RoaringBitmap and(RoaringBitmap... bitmaps) {
      return bitmaps.length > 10 ? workShyAnd(new long[1024], bitmaps) : naive_and(bitmaps);
   }

   public static RoaringBitmap and(long[] aggregationBuffer, RoaringBitmap... bitmaps) {
      if (bitmaps.length > 10) {
         if (aggregationBuffer.length < 1024) {
            throw new IllegalArgumentException("buffer should have at least 1024 elements.");
         } else {
            RoaringBitmap var2;
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

   public static int andCardinality(RoaringBitmap... bitmaps) {
      switch (bitmaps.length) {
         case 0:
            return 0;
         case 1:
            return bitmaps[0].getCardinality();
         case 2:
            return RoaringBitmap.andCardinality(bitmaps[0], bitmaps[1]);
         default:
            return workShyAndCardinality(bitmaps);
      }
   }

   public static int orCardinality(RoaringBitmap... bitmaps) {
      switch (bitmaps.length) {
         case 0:
            return 0;
         case 1:
            return bitmaps[0].getCardinality();
         case 2:
            return RoaringBitmap.orCardinality(bitmaps[0], bitmaps[1]);
         default:
            return horizontalOrCardinality(bitmaps);
      }
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap horizontal_or(Iterator bitmaps) {
      return naive_or(bitmaps);
   }

   public static RoaringBitmap horizontal_or(List bitmaps) {
      RoaringBitmap answer = new RoaringBitmap();
      if (bitmaps.isEmpty()) {
         return answer;
      } else {
         PriorityQueue<ContainerPointer> pq = new PriorityQueue(bitmaps.size());

         for(int k = 0; k < bitmaps.size(); ++k) {
            ContainerPointer x = ((RoaringBitmap)bitmaps.get(k)).highLowContainer.getContainerPointer();
            if (x.getContainer() != null) {
               pq.add(x);
            }
         }

         while(!pq.isEmpty()) {
            ContainerPointer x1 = (ContainerPointer)pq.poll();
            if (!pq.isEmpty() && ((ContainerPointer)pq.peek()).key() == x1.key()) {
               ContainerPointer x2 = (ContainerPointer)pq.poll();
               Container newc = x1.getContainer().lazyOR(x2.getContainer());

               while(!pq.isEmpty() && ((ContainerPointer)pq.peek()).key() == x1.key()) {
                  ContainerPointer x = (ContainerPointer)pq.poll();
                  newc = newc.lazyIOR(x.getContainer());
                  x.advance();
                  if (x.getContainer() != null) {
                     pq.add(x);
                  } else if (pq.isEmpty()) {
                     break;
                  }
               }

               newc = newc.repairAfterLazy();
               answer.highLowContainer.append(x1.key(), newc);
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }

               x2.advance();
               if (x2.getContainer() != null) {
                  pq.add(x2);
               }
            } else {
               answer.highLowContainer.append(x1.key(), x1.getContainer().clone());
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }
            }
         }

         return answer;
      }
   }

   public static RoaringBitmap horizontal_or(RoaringBitmap... bitmaps) {
      RoaringBitmap answer = new RoaringBitmap();
      if (bitmaps.length == 0) {
         return answer;
      } else {
         PriorityQueue<ContainerPointer> pq = new PriorityQueue(bitmaps.length);

         for(int k = 0; k < bitmaps.length; ++k) {
            ContainerPointer x = bitmaps[k].highLowContainer.getContainerPointer();
            if (x.getContainer() != null) {
               pq.add(x);
            }
         }

         while(!pq.isEmpty()) {
            ContainerPointer x1 = (ContainerPointer)pq.poll();
            if (!pq.isEmpty() && ((ContainerPointer)pq.peek()).key() == x1.key()) {
               ContainerPointer x2 = (ContainerPointer)pq.poll();
               Container newc = x1.getContainer().lazyOR(x2.getContainer());

               while(!pq.isEmpty() && ((ContainerPointer)pq.peek()).key() == x1.key()) {
                  ContainerPointer x = (ContainerPointer)pq.poll();
                  newc = newc.lazyIOR(x.getContainer());
                  x.advance();
                  if (x.getContainer() != null) {
                     pq.add(x);
                  } else if (pq.isEmpty()) {
                     break;
                  }
               }

               newc = newc.repairAfterLazy();
               answer.highLowContainer.append(x1.key(), newc);
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }

               x2.advance();
               if (x2.getContainer() != null) {
                  pq.add(x2);
               }
            } else {
               answer.highLowContainer.append(x1.key(), x1.getContainer().clone());
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }
            }
         }

         return answer;
      }
   }

   public static RoaringBitmap horizontal_xor(RoaringBitmap... bitmaps) {
      RoaringBitmap answer = new RoaringBitmap();
      if (bitmaps.length == 0) {
         return answer;
      } else {
         PriorityQueue<ContainerPointer> pq = new PriorityQueue(bitmaps.length);

         for(int k = 0; k < bitmaps.length; ++k) {
            ContainerPointer x = bitmaps[k].highLowContainer.getContainerPointer();
            if (x.getContainer() != null) {
               pq.add(x);
            }
         }

         while(!pq.isEmpty()) {
            ContainerPointer x1 = (ContainerPointer)pq.poll();
            if (!pq.isEmpty() && ((ContainerPointer)pq.peek()).key() == x1.key()) {
               ContainerPointer x2 = (ContainerPointer)pq.poll();
               Container newc = x1.getContainer().xor(x2.getContainer());

               while(!pq.isEmpty() && ((ContainerPointer)pq.peek()).key() == x1.key()) {
                  ContainerPointer x = (ContainerPointer)pq.poll();
                  newc = newc.ixor(x.getContainer());
                  x.advance();
                  if (x.getContainer() != null) {
                     pq.add(x);
                  } else if (pq.isEmpty()) {
                     break;
                  }
               }

               answer.highLowContainer.append(x1.key(), newc);
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }

               x2.advance();
               if (x2.getContainer() != null) {
                  pq.add(x2);
               }
            } else {
               answer.highLowContainer.append(x1.key(), x1.getContainer().clone());
               x1.advance();
               if (x1.getContainer() != null) {
                  pq.add(x1);
               }
            }
         }

         return answer;
      }
   }

   public static RoaringBitmap naive_and(Iterator bitmaps) {
      if (!bitmaps.hasNext()) {
         return new RoaringBitmap();
      } else {
         RoaringBitmap answer = ((RoaringBitmap)bitmaps.next()).clone();

         while(bitmaps.hasNext() && !answer.isEmpty()) {
            answer.and((RoaringBitmap)bitmaps.next());
         }

         return answer;
      }
   }

   public static RoaringBitmap naive_and(RoaringBitmap... bitmaps) {
      if (bitmaps.length == 0) {
         return new RoaringBitmap();
      } else {
         RoaringBitmap smallest = bitmaps[0];

         for(int i = 1; i < bitmaps.length; ++i) {
            RoaringBitmap bitmap = bitmaps[i];
            if (bitmap.highLowContainer.size() < smallest.highLowContainer.size()) {
               smallest = bitmap;
            }
         }

         RoaringBitmap answer = smallest.clone();

         for(int k = 0; k < bitmaps.length && !answer.isEmpty(); ++k) {
            if (bitmaps[k] != smallest) {
               answer.and(bitmaps[k]);
            }
         }

         return answer;
      }
   }

   public static RoaringBitmap workShyAnd(long[] buffer, RoaringBitmap... bitmaps) {
      long[] words = buffer;
      RoaringBitmap first = bitmaps[0];

      for(int i = 0; i < first.highLowContainer.size; ++i) {
         char key = first.highLowContainer.keys[i];
         words[key >>> 6] |= 1L << key;
      }

      int numContainers = first.highLowContainer.size;

      for(int i = 1; i < bitmaps.length && numContainers > 0; ++i) {
         numContainers = Util.intersectArrayIntoBitmap(words, bitmaps[i].highLowContainer.keys, bitmaps[i].highLowContainer.size);
      }

      if (numContainers == 0) {
         return new RoaringBitmap();
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

         Container[][] containers = new Container[numContainers][bitmaps.length];

         for(int i = 0; i < bitmaps.length; ++i) {
            RoaringBitmap bitmap = bitmaps[i];
            int position = 0;

            for(int j = 0; j < bitmap.highLowContainer.size; ++j) {
               char key = bitmap.highLowContainer.keys[j];
               if ((words[key >>> 6] & 1L << key) != 0L) {
                  containers[position++][i] = bitmap.highLowContainer.values[j];
               }
            }
         }

         RoaringArray array = new RoaringArray(keys, new Container[numContainers], 0);

         for(int i = 0; i < numContainers; ++i) {
            Container[] slice = containers[i];
            Arrays.fill(words, -1L);
            Container tmp = new BitmapContainer(words, -1);

            for(Container container : slice) {
               Container and = tmp.iand(container);
               if (and != tmp) {
                  tmp = and;
               }
            }

            tmp = tmp.repairAfterLazy();
            if (!tmp.isEmpty()) {
               array.append(keys[i], tmp instanceof BitmapContainer ? tmp.clone() : tmp);
            }
         }

         return new RoaringBitmap(array);
      }
   }

   private static int workShyAndCardinality(RoaringBitmap... bitmaps) {
      long[] words = new long[1024];
      RoaringBitmap first = bitmaps[0];

      for(int i = 0; i < first.highLowContainer.size; ++i) {
         char key = first.highLowContainer.keys[i];
         words[key >>> 6] |= 1L << key;
      }

      int numKeys = first.highLowContainer.size;

      for(int i = 1; i < bitmaps.length && numKeys > 0; ++i) {
         numKeys = Util.intersectArrayIntoBitmap(words, bitmaps[i].highLowContainer.keys, bitmaps[i].highLowContainer.size);
      }

      if (numKeys == 0) {
         return 0;
      } else {
         char[] keys = new char[numKeys];
         int base = 0;
         int pos = 0;
         long[] var7 = words;
         int var8 = words.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            for(long word = var7[var9]; word != 0L; word &= word - 1L) {
               keys[pos++] = (char)(base + Long.numberOfTrailingZeros(word));
            }

            base += 64;
         }

         int cardinality = 0;

         for(int i = 0; i < numKeys; ++i) {
            Arrays.fill(words, -1L);
            Container tmp = new BitmapContainer(words, -1);

            for(RoaringBitmap bitmap : bitmaps) {
               int index = bitmap.highLowContainer.getIndex(keys[i]);
               if (index >= 0) {
                  Container container = bitmap.highLowContainer.getContainerAtIndex(index);
                  Container and = tmp.iand(container);
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

   private static int horizontalOrCardinality(RoaringBitmap... bitmaps) {
      long[] words = new long[1024];
      int minKey = 65535;
      int maxKey = 0;

      for(RoaringBitmap bitmap : bitmaps) {
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
      long[] var25 = words;
      int var27 = words.length;

      for(int var10 = 0; var10 < var27; ++var10) {
         for(long word = var25[var10]; word != 0L; word &= word - 1L) {
            keys[pos++] = (char)(base + Long.numberOfTrailingZeros(word));
         }

         base += 64;
      }

      int cardinality = 0;

      for(char key : keys) {
         Arrays.fill(words, 0L);
         Container tmp = new BitmapContainer(words, -1);

         for(RoaringBitmap bitmap : bitmaps) {
            int index = bitmap.highLowContainer.getIndex(key);
            if (index >= 0) {
               Container container = bitmap.highLowContainer.getContainerAtIndex(index);
               Container or = tmp.lazyIOR(container);
               if (or != tmp) {
                  tmp = or;
               }
            }
         }

         cardinality += tmp.repairAfterLazy().getCardinality();
      }

      return cardinality;
   }

   public static RoaringBitmap workAndMemoryShyAnd(long[] buffer, RoaringBitmap... bitmaps) {
      if (buffer.length < 1024) {
         throw new IllegalArgumentException("buffer should have at least 1024 elements.");
      } else {
         long[] words = buffer;
         RoaringBitmap first = bitmaps[0];

         for(int i = 0; i < first.highLowContainer.size; ++i) {
            char key = first.highLowContainer.keys[i];
            words[key >>> 6] |= 1L << key;
         }

         int numContainers = first.highLowContainer.size;

         for(int i = 1; i < bitmaps.length && numContainers > 0; ++i) {
            numContainers = Util.intersectArrayIntoBitmap(words, bitmaps[i].highLowContainer.keys, bitmaps[i].highLowContainer.size);
         }

         if (numContainers == 0) {
            return new RoaringBitmap();
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

            RoaringArray array = new RoaringArray(keys, new Container[numContainers], 0);

            for(int i = 0; i < numContainers; ++i) {
               char MatchingKey = keys[i];
               Arrays.fill(words, -1L);
               Container tmp = new BitmapContainer(words, -1);

               for(RoaringBitmap bitmap : bitmaps) {
                  int idx = bitmap.highLowContainer.getIndex(MatchingKey);
                  if (idx >= 0) {
                     Container container = bitmap.highLowContainer.getContainerAtIndex(idx);
                     Container and = tmp.iand(container);
                     if (and != tmp) {
                        tmp = and;
                     }
                  }
               }

               tmp = tmp.repairAfterLazy();
               if (!tmp.isEmpty()) {
                  array.append(keys[i], tmp instanceof BitmapContainer ? tmp.clone() : tmp);
               }
            }

            return new RoaringBitmap(array);
         }
      }
   }

   public static RoaringBitmap naive_or(Iterator bitmaps) {
      RoaringBitmap answer = new RoaringBitmap();

      while(bitmaps.hasNext()) {
         answer.naivelazyor((RoaringBitmap)bitmaps.next());
      }

      answer.repairAfterLazy();
      return answer;
   }

   public static RoaringBitmap naive_or(RoaringBitmap... bitmaps) {
      RoaringBitmap answer = new RoaringBitmap();

      for(int k = 0; k < bitmaps.length; ++k) {
         answer.naivelazyor(bitmaps[k]);
      }

      answer.repairAfterLazy();
      return answer;
   }

   public static RoaringBitmap naive_xor(Iterator bitmaps) {
      RoaringBitmap answer = new RoaringBitmap();

      while(bitmaps.hasNext()) {
         answer.xor((RoaringBitmap)bitmaps.next());
      }

      return answer;
   }

   public static RoaringBitmap naive_xor(RoaringBitmap... bitmaps) {
      RoaringBitmap answer = new RoaringBitmap();

      for(int k = 0; k < bitmaps.length; ++k) {
         answer.xor(bitmaps[k]);
      }

      return answer;
   }

   public static RoaringBitmap or(Iterator bitmaps) {
      return naive_or(bitmaps);
   }

   public static RoaringBitmap or(RoaringBitmap... bitmaps) {
      return naive_or(bitmaps);
   }

   public static RoaringBitmap priorityqueue_or(Iterator bitmaps) {
      if (!bitmaps.hasNext()) {
         return new RoaringBitmap();
      } else {
         ArrayList<RoaringBitmap> buffer = new ArrayList();

         while(bitmaps.hasNext()) {
            buffer.add((RoaringBitmap)bitmaps.next());
         }

         final long[] sizes = new long[buffer.size()];
         boolean[] istmp = new boolean[buffer.size()];

         for(int k = 0; k < sizes.length; ++k) {
            sizes[k] = ((RoaringBitmap)buffer.get(k)).getLongSizeInBytes();
         }

         PriorityQueue<Integer> pq = new PriorityQueue(128, new Comparator() {
            public int compare(Integer a, Integer b) {
               return (int)(sizes[a] - sizes[b]);
            }
         });

         for(int k = 0; k < sizes.length; ++k) {
            pq.add(k);
         }

         while(pq.size() > 1) {
            Integer x1 = (Integer)pq.poll();
            Integer x2 = (Integer)pq.poll();
            if (istmp[x2] && istmp[x1]) {
               buffer.set(x1, RoaringBitmap.lazyorfromlazyinputs((RoaringBitmap)buffer.get(x1), (RoaringBitmap)buffer.get(x2)));
               sizes[x1] = ((RoaringBitmap)buffer.get(x1)).getLongSizeInBytes();
               istmp[x1] = true;
               pq.add(x1);
            } else if (istmp[x2]) {
               ((RoaringBitmap)buffer.get(x2)).lazyor((RoaringBitmap)buffer.get(x1));
               sizes[x2] = ((RoaringBitmap)buffer.get(x2)).getLongSizeInBytes();
               pq.add(x2);
            } else if (istmp[x1]) {
               ((RoaringBitmap)buffer.get(x1)).lazyor((RoaringBitmap)buffer.get(x2));
               sizes[x1] = ((RoaringBitmap)buffer.get(x1)).getLongSizeInBytes();
               pq.add(x1);
            } else {
               buffer.set(x1, RoaringBitmap.lazyor((RoaringBitmap)buffer.get(x1), (RoaringBitmap)buffer.get(x2)));
               sizes[x1] = ((RoaringBitmap)buffer.get(x1)).getLongSizeInBytes();
               istmp[x1] = true;
               pq.add(x1);
            }
         }

         RoaringBitmap answer = (RoaringBitmap)buffer.get((Integer)pq.poll());
         answer.repairAfterLazy();
         return answer;
      }
   }

   public static RoaringBitmap priorityqueue_or(RoaringBitmap... bitmaps) {
      if (bitmaps.length == 0) {
         return new RoaringBitmap();
      } else {
         RoaringBitmap[] buffer = (RoaringBitmap[])Arrays.copyOf(bitmaps, bitmaps.length);
         final long[] sizes = new long[buffer.length];
         boolean[] istmp = new boolean[buffer.length];

         for(int k = 0; k < sizes.length; ++k) {
            sizes[k] = buffer[k].getLongSizeInBytes();
         }

         PriorityQueue<Integer> pq = new PriorityQueue(128, new Comparator() {
            public int compare(Integer a, Integer b) {
               return (int)(sizes[a] - sizes[b]);
            }
         });

         for(int k = 0; k < sizes.length; ++k) {
            pq.add(k);
         }

         while(pq.size() > 1) {
            Integer x1 = (Integer)pq.poll();
            Integer x2 = (Integer)pq.poll();
            if (istmp[x2] && istmp[x1]) {
               buffer[x1] = RoaringBitmap.lazyorfromlazyinputs(buffer[x1], buffer[x2]);
               sizes[x1] = buffer[x1].getLongSizeInBytes();
               istmp[x1] = true;
               pq.add(x1);
            } else if (istmp[x2]) {
               buffer[x2].lazyor(buffer[x1]);
               sizes[x2] = buffer[x2].getLongSizeInBytes();
               pq.add(x2);
            } else if (istmp[x1]) {
               buffer[x1].lazyor(buffer[x2]);
               sizes[x1] = buffer[x1].getLongSizeInBytes();
               pq.add(x1);
            } else {
               buffer[x1] = RoaringBitmap.lazyor(buffer[x1], buffer[x2]);
               sizes[x1] = buffer[x1].getLongSizeInBytes();
               istmp[x1] = true;
               pq.add(x1);
            }
         }

         RoaringBitmap answer = buffer[(Integer)pq.poll()];
         answer.repairAfterLazy();
         return answer;
      }
   }

   public static RoaringBitmap priorityqueue_xor(RoaringBitmap... bitmaps) {
      if (bitmaps.length == 0) {
         return new RoaringBitmap();
      } else {
         PriorityQueue<RoaringBitmap> pq = new PriorityQueue(bitmaps.length, new Comparator() {
            public int compare(RoaringBitmap a, RoaringBitmap b) {
               return (int)(a.getLongSizeInBytes() - b.getLongSizeInBytes());
            }
         });
         Collections.addAll(pq, bitmaps);

         while(pq.size() > 1) {
            RoaringBitmap x1 = (RoaringBitmap)pq.poll();
            RoaringBitmap x2 = (RoaringBitmap)pq.poll();
            pq.add(RoaringBitmap.xor(x1, x2));
         }

         return (RoaringBitmap)pq.poll();
      }
   }

   public static RoaringBitmap xor(Iterator bitmaps) {
      return naive_xor(bitmaps);
   }

   public static RoaringBitmap xor(RoaringBitmap... bitmaps) {
      return naive_xor(bitmaps);
   }

   private FastAggregation() {
   }
}
