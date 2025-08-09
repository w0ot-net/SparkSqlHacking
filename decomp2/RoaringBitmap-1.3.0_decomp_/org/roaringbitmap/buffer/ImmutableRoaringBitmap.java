package org.roaringbitmap.buffer;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.roaringbitmap.BatchIterator;
import org.roaringbitmap.CharIterator;
import org.roaringbitmap.ImmutableBitmapDataProvider;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.IntIterator;
import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.PeekableIntIterator;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.Util;

public class ImmutableRoaringBitmap implements Iterable, Cloneable, ImmutableBitmapDataProvider {
   PointableRoaringArray highLowContainer = null;

   public static MutableRoaringBitmap and(Iterator bitmaps, long rangeStart, long rangeEnd) {
      MutableRoaringBitmap.rangeSanityCheck(rangeStart, rangeEnd);
      Iterator<ImmutableRoaringBitmap> bitmapsIterator = selectRangeWithoutCopy(bitmaps, rangeStart, rangeEnd);
      return BufferFastAggregation.and(bitmapsIterator);
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap and(Iterator bitmaps, int rangeStart, int rangeEnd) {
      return and(bitmaps, (long)rangeStart, (long)rangeEnd);
   }

   public static MutableRoaringBitmap and(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            MappeableContainer c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            MappeableContainer c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            MappeableContainer c = c1.and(c2);
            if (!c.isEmpty()) {
               answer.getMappeableRoaringArray().append(s1, c);
            }

            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            pos1 = x1.highLowContainer.advanceUntil(s2, pos1);
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      return answer;
   }

   public static int andCardinality(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      int answer = 0;
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            MappeableContainer c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            MappeableContainer c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            answer += c1.andCardinality(c2);
            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            pos1 = x1.highLowContainer.advanceUntil(s2, pos1);
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      return answer;
   }

   public static int xorCardinality(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      return x1.getCardinality() + x2.getCardinality() - 2 * andCardinality(x1, x2);
   }

   public static int andNotCardinality(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      return x1.getCardinality() - andCardinality(x1, x2);
   }

   public static MutableRoaringBitmap andNot(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2, long rangeStart, long rangeEnd) {
      MutableRoaringBitmap.rangeSanityCheck(rangeStart, rangeEnd);
      MutableRoaringBitmap rb1 = selectRangeWithoutCopy(x1, rangeStart, rangeEnd);
      MutableRoaringBitmap rb2 = selectRangeWithoutCopy(x2, rangeStart, rangeEnd);
      return andNot(rb1, rb2);
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap andNot(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2, int rangeStart, int rangeEnd) {
      return andNot(x1, x2, (long)rangeStart, (long)rangeEnd);
   }

   public static MutableRoaringBitmap andNot(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            MappeableContainer c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            MappeableContainer c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            MappeableContainer c = c1.andNot(c2);
            if (!c.isEmpty()) {
               answer.getMappeableRoaringArray().append(s1, c);
            }

            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            int nextPos1 = x1.highLowContainer.advanceUntil(s2, pos1);
            answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer, pos1, nextPos1);
            pos1 = nextPos1;
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      if (pos2 == length2) {
         answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   public static MutableRoaringBitmap orNot(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2, long rangeEnd) {
      MutableRoaringBitmap.rangeSanityCheck(0L, rangeEnd);
      int maxKey = (int)(rangeEnd - 1L >>> 16);
      int lastRun = (rangeEnd & 65535L) == 0L ? 65536 : (int)(rangeEnd & 65535L);
      int size = 0;
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      int s1 = length1 > 0 ? x1.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
      int s2 = length2 > 0 ? x2.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
      int remainder = 0;

      for(int i = x1.highLowContainer.size() - 1; i >= 0 && x1.highLowContainer.getKeyAtIndex(i) > maxKey; --i) {
         ++remainder;
      }

      int correction = 0;

      for(int i = 0; i < x2.highLowContainer.size() - remainder; ++i) {
         correction += x2.highLowContainer.getContainerAtIndex(i).isFull() ? 1 : 0;
         if (x2.highLowContainer.getKeyAtIndex(i) >= maxKey) {
            break;
         }
      }

      int maxSize = Math.min(maxKey + 1 + remainder - correction + x1.highLowContainer.size(), 65536);
      if (maxSize == 0) {
         return new MutableRoaringBitmap();
      } else {
         char[] newKeys = new char[maxSize];
         MappeableContainer[] newValues = new MappeableContainer[maxSize];

         for(int key = 0; key <= maxKey && size < maxSize; ++key) {
            if (key == s1 && key == s2) {
               newValues[size] = x1.highLowContainer.getContainerAtIndex(pos1).orNot(x2.highLowContainer.getContainerAtIndex(pos2), key == maxKey ? lastRun : 65536);
               ++pos1;
               ++pos2;
               s1 = pos1 < length1 ? x1.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
               s2 = pos2 < length2 ? x2.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
            } else if (key == s1) {
               newValues[size] = (MappeableContainer)(key == maxKey ? x1.highLowContainer.getContainerAtIndex(pos1).ior(MappeableRunContainer.rangeOfOnes(0, lastRun)) : MappeableRunContainer.full());
               ++pos1;
               s1 = pos1 < length1 ? x1.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
            } else if (key == s2) {
               newValues[size] = x2.highLowContainer.getContainerAtIndex(pos2).not(0, key == maxKey ? lastRun : 65536);
               ++pos2;
               s2 = pos2 < length2 ? x2.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
            } else {
               newValues[size] = (MappeableContainer)(key == maxKey ? MappeableRunContainer.rangeOfOnes(0, lastRun) : MappeableRunContainer.full());
            }

            if (newValues[size].isEmpty()) {
               newValues[size] = null;
            } else {
               newKeys[size++] = (char)key;
            }
         }

         if (remainder > 0) {
            for(int i = 0; i < remainder; ++i) {
               int source = x1.highLowContainer.size() - remainder + i;
               int target = size + i;
               newKeys[target] = x1.highLowContainer.getKeyAtIndex(source);
               newValues[target] = x1.highLowContainer.getContainerAtIndex(source).clone();
            }
         }

         return new MutableRoaringBitmap(new MutableRoaringArray(newKeys, newValues, size + remainder));
      }
   }

   public static ImmutableRoaringBitmap bitmapOf(int... data) {
      return MutableRoaringBitmap.bitmapOf(data);
   }

   public static MutableRoaringBitmap flip(ImmutableRoaringBitmap bm, long rangeStart, long rangeEnd) {
      MutableRoaringBitmap.rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart >= rangeEnd) {
         throw new RuntimeException("Invalid range " + rangeStart + " -- " + rangeEnd);
      } else {
         MutableRoaringBitmap answer = new MutableRoaringBitmap();
         char hbStart = BufferUtil.highbits(rangeStart);
         char lbStart = BufferUtil.lowbits(rangeStart);
         char hbLast = BufferUtil.highbits(rangeEnd - 1L);
         char lbLast = BufferUtil.lowbits(rangeEnd - 1L);
         answer.getMappeableRoaringArray().appendCopiesUntil(bm.highLowContainer, hbStart);
         int max = BufferUtil.maxLowBit();

         for(char hb = hbStart; hb <= hbLast; ++hb) {
            int containerStart = hb == hbStart ? lbStart : 0;
            int containerLast = hb == hbLast ? lbLast : max;
            int i = bm.highLowContainer.getIndex(hb);
            int j = answer.getMappeableRoaringArray().getIndex(hb);

            assert j < 0;

            if (i >= 0) {
               MappeableContainer c = bm.highLowContainer.getContainerAtIndex(i).not(containerStart, containerLast + 1);
               if (!c.isEmpty()) {
                  answer.getMappeableRoaringArray().insertNewKeyValueAt(-j - 1, hb, c);
               }
            } else {
               answer.getMappeableRoaringArray().insertNewKeyValueAt(-j - 1, hb, MappeableContainer.rangeOfOnes(containerStart, containerLast + 1));
            }
         }

         answer.getMappeableRoaringArray().appendCopiesAfter(bm.highLowContainer, hbLast);
         return answer;
      }
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap flip(ImmutableRoaringBitmap bm, int rangeStart, int rangeEnd) {
      return rangeStart >= 0 ? flip(bm, (long)rangeStart, (long)rangeEnd) : flip(bm, (long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   private static Iterator selectRangeWithoutCopy(final Iterator bitmaps, final long rangeStart, final long rangeEnd) {
      Iterator<ImmutableRoaringBitmap> bitmapsIterator = new Iterator() {
         public boolean hasNext() {
            return bitmaps.hasNext();
         }

         public ImmutableRoaringBitmap next() {
            ImmutableRoaringBitmap next = (ImmutableRoaringBitmap)bitmaps.next();
            return ImmutableRoaringBitmap.selectRangeWithoutCopy(next, rangeStart, rangeEnd);
         }

         public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
         }
      };
      return bitmapsIterator;
   }

   public MutableRoaringBitmap selectRange(long rangeStart, long rangeEnd) {
      int hbStart = BufferUtil.highbits(rangeStart);
      int lbStart = BufferUtil.lowbits(rangeStart);
      int hbLast = BufferUtil.highbits(rangeEnd - 1L);
      int lbLast = BufferUtil.lowbits(rangeEnd - 1L);
      MutableRoaringBitmap answer = new MutableRoaringBitmap();

      assert rangeStart >= 0L && rangeEnd >= 0L;

      if (rangeEnd <= rangeStart) {
         return answer;
      } else if (hbStart == hbLast) {
         int i = this.highLowContainer.getIndex((char)hbStart);
         if (i >= 0) {
            MappeableContainer c = this.highLowContainer.getContainerAtIndex(i).remove(0, lbStart).iremove(lbLast + 1, Util.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c);
            }
         }

         return answer;
      } else {
         int ifirst = this.highLowContainer.getIndex((char)hbStart);
         int ilast = this.highLowContainer.getIndex((char)hbLast);
         if (ifirst >= 0) {
            MappeableContainer c = this.highLowContainer.getContainerAtIndex(ifirst).remove(0, lbStart);
            if (!c.isEmpty()) {
               ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c.clone());
            }
         }

         for(int hb = hbStart + 1; hb <= hbLast - 1; ++hb) {
            int i = this.highLowContainer.getIndex((char)hb);
            int j = answer.highLowContainer.getIndex((char)hb);

            assert j < 0;

            if (i >= 0) {
               MappeableContainer c = this.highLowContainer.getContainerAtIndex(i);
               ((MutableRoaringArray)answer.highLowContainer).insertNewKeyValueAt(-j - 1, (char)hb, c.clone());
            }
         }

         if (ilast >= 0) {
            MappeableContainer c = this.highLowContainer.getContainerAtIndex(ilast).remove(lbLast + 1, Util.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               ((MutableRoaringArray)answer.highLowContainer).append((char)hbLast, c);
            }
         }

         return answer;
      }
   }

   private static MutableRoaringBitmap selectRangeWithoutCopy(ImmutableRoaringBitmap rb, long rangeStart, long rangeEnd) {
      int hbStart = BufferUtil.highbits(rangeStart);
      int lbStart = BufferUtil.lowbits(rangeStart);
      int hbLast = BufferUtil.highbits(rangeEnd - 1L);
      int lbLast = BufferUtil.lowbits(rangeEnd - 1L);
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      if (rangeEnd <= rangeStart) {
         return answer;
      } else if (hbStart == hbLast) {
         int i = rb.highLowContainer.getIndex((char)hbStart);
         if (i >= 0) {
            MappeableContainer c = rb.highLowContainer.getContainerAtIndex(i).remove(0, lbStart).iremove(lbLast + 1, BufferUtil.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c);
            }
         }

         return answer;
      } else {
         int ifirst = rb.highLowContainer.getIndex((char)hbStart);
         int ilast = rb.highLowContainer.getIndex((char)hbLast);
         if (ifirst >= 0) {
            MappeableContainer c = rb.highLowContainer.getContainerAtIndex(ifirst).remove(0, lbStart);
            if (!c.isEmpty()) {
               ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c);
            }
         }

         for(int hb = hbStart + 1; hb <= hbLast - 1; ++hb) {
            int i = rb.highLowContainer.getIndex((char)hb);
            int j = answer.getMappeableRoaringArray().getIndex((char)hb);

            assert j < 0;

            if (i >= 0) {
               MappeableContainer c = rb.highLowContainer.getContainerAtIndex(i);
               answer.getMappeableRoaringArray().insertNewKeyValueAt(-j - 1, (char)hb, c);
            }
         }

         if (ilast >= 0) {
            MappeableContainer c = rb.highLowContainer.getContainerAtIndex(ilast).remove(lbLast + 1, BufferUtil.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               ((MutableRoaringArray)answer.highLowContainer).append((char)hbLast, c);
            }
         }

         return answer;
      }
   }

   public static boolean intersects(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            MappeableContainer c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            MappeableContainer c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            if (c1.intersects(c2)) {
               return true;
            }

            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            pos1 = x1.highLowContainer.advanceUntil(s2, pos1);
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      return false;
   }

   protected static MutableRoaringBitmap lazyor(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      MappeableContainerPointer i1 = x1.highLowContainer.getContainerPointer();
      MappeableContainerPointer i2 = x2.highLowContainer.getContainerPointer();
      if (i1.hasContainer() && i2.hasContainer()) {
         label48:
         do {
            while(i1.key() != i2.key()) {
               if (i1.key() < i2.key()) {
                  answer.getMappeableRoaringArray().appendCopy(i1.key(), i1.getContainer());
                  i1.advance();
                  if (!i1.hasContainer()) {
                     break label48;
                  }
               } else {
                  answer.getMappeableRoaringArray().appendCopy(i2.key(), i2.getContainer());
                  i2.advance();
                  if (!i2.hasContainer()) {
                     break label48;
                  }
               }
            }

            answer.getMappeableRoaringArray().append(i1.key(), i1.getContainer().lazyOR(i2.getContainer()));
            i1.advance();
            i2.advance();
         } while(i1.hasContainer() && i2.hasContainer());
      }

      if (!i1.hasContainer()) {
         while(i2.hasContainer()) {
            answer.getMappeableRoaringArray().appendCopy(i2.key(), i2.getContainer());
            i2.advance();
         }
      } else if (!i2.hasContainer()) {
         while(i1.hasContainer()) {
            answer.getMappeableRoaringArray().appendCopy(i1.key(), i1.getContainer());
            i1.advance();
         }
      }

      return answer;
   }

   public static MutableRoaringBitmap or(ImmutableRoaringBitmap... bitmaps) {
      return BufferFastAggregation.or(bitmaps);
   }

   public static MutableRoaringBitmap or(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      MappeableContainerPointer i1 = x1.highLowContainer.getContainerPointer();
      MappeableContainerPointer i2 = x2.highLowContainer.getContainerPointer();
      if (i1.hasContainer() && i2.hasContainer()) {
         label48:
         do {
            while(i1.key() != i2.key()) {
               if (i1.key() < i2.key()) {
                  answer.getMappeableRoaringArray().appendCopy(i1.key(), i1.getContainer());
                  i1.advance();
                  if (!i1.hasContainer()) {
                     break label48;
                  }
               } else {
                  answer.getMappeableRoaringArray().appendCopy(i2.key(), i2.getContainer());
                  i2.advance();
                  if (!i2.hasContainer()) {
                     break label48;
                  }
               }
            }

            answer.getMappeableRoaringArray().append(i1.key(), i1.getContainer().or(i2.getContainer()));
            i1.advance();
            i2.advance();
         } while(i1.hasContainer() && i2.hasContainer());
      }

      if (!i1.hasContainer()) {
         while(i2.hasContainer()) {
            answer.getMappeableRoaringArray().appendCopy(i2.key(), i2.getContainer());
            i2.advance();
         }
      } else if (!i2.hasContainer()) {
         while(i1.hasContainer()) {
            answer.getMappeableRoaringArray().appendCopy(i1.key(), i1.getContainer());
            i1.advance();
         }
      }

      return answer;
   }

   public static MutableRoaringBitmap or(Iterator bitmaps) {
      return BufferFastAggregation.or(bitmaps);
   }

   public static MutableRoaringBitmap or(Iterator bitmaps, long rangeStart, long rangeEnd) {
      MutableRoaringBitmap.rangeSanityCheck(rangeStart, rangeEnd);
      Iterator<ImmutableRoaringBitmap> bitmapsIterator = selectRangeWithoutCopy(bitmaps, rangeStart, rangeEnd);
      return or(bitmapsIterator);
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap or(Iterator bitmaps, int rangeStart, int rangeEnd) {
      return or(bitmaps, (long)rangeStart, (long)rangeEnd);
   }

   public static int orCardinality(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      return x1.getCardinality() + x2.getCardinality() - andCardinality(x1, x2);
   }

   public static MutableRoaringBitmap xor(Iterator bitmaps, long rangeStart, long rangeEnd) {
      Iterator<ImmutableRoaringBitmap> bitmapsIterator = selectRangeWithoutCopy(bitmaps, rangeStart, rangeEnd);
      return BufferFastAggregation.xor(bitmapsIterator);
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap xor(Iterator bitmaps, int rangeStart, int rangeEnd) {
      return xor(bitmaps, (long)rangeStart, (long)rangeEnd);
   }

   public static MutableRoaringBitmap xor(ImmutableRoaringBitmap x1, ImmutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      MappeableContainerPointer i1 = x1.highLowContainer.getContainerPointer();
      MappeableContainerPointer i2 = x2.highLowContainer.getContainerPointer();
      if (i1.hasContainer() && i2.hasContainer()) {
         label53:
         do {
            while(i1.key() != i2.key()) {
               if (i1.key() < i2.key()) {
                  answer.getMappeableRoaringArray().appendCopy(i1.key(), i1.getContainer());
                  i1.advance();
                  if (!i1.hasContainer()) {
                     break label53;
                  }
               } else {
                  answer.getMappeableRoaringArray().appendCopy(i2.key(), i2.getContainer());
                  i2.advance();
                  if (!i2.hasContainer()) {
                     break label53;
                  }
               }
            }

            MappeableContainer c = i1.getContainer().xor(i2.getContainer());
            if (!c.isEmpty()) {
               answer.getMappeableRoaringArray().append(i1.key(), c);
            }

            i1.advance();
            i2.advance();
         } while(i1.hasContainer() && i2.hasContainer());
      }

      if (!i1.hasContainer()) {
         while(i2.hasContainer()) {
            answer.getMappeableRoaringArray().appendCopy(i2.key(), i2.getContainer());
            i2.advance();
         }
      } else if (!i2.hasContainer()) {
         while(i1.hasContainer()) {
            answer.getMappeableRoaringArray().appendCopy(i1.key(), i1.getContainer());
            i1.advance();
         }
      }

      return answer;
   }

   protected ImmutableRoaringBitmap() {
   }

   public ImmutableRoaringBitmap(ByteBuffer b) {
      this.highLowContainer = new ImmutableRoaringArray(b);
   }

   public ImmutableRoaringBitmap clone() {
      try {
         ImmutableRoaringBitmap x = (ImmutableRoaringBitmap)super.clone();
         x.highLowContainer = this.highLowContainer.clone();
         return x;
      } catch (CloneNotSupportedException e) {
         throw new RuntimeException("shouldn't happen with clone", e);
      }
   }

   public boolean contains(int x) {
      char hb = BufferUtil.highbits(x);
      int index = this.highLowContainer.getContainerIndex(hb);
      return index >= 0 && this.highLowContainer.containsForContainerAtIndex(index, BufferUtil.lowbits(x));
   }

   public boolean contains(long minimum, long supremum) {
      MutableRoaringBitmap.rangeSanityCheck(minimum, supremum);
      if (supremum <= minimum) {
         return false;
      } else {
         char firstKey = BufferUtil.highbits(minimum);
         char lastKey = BufferUtil.highbits(supremum);
         int span = lastKey - firstKey;
         int len = this.highLowContainer.size();
         if (len < span) {
            return false;
         } else {
            int begin = this.highLowContainer.getIndex(firstKey);
            int end = this.highLowContainer.getIndex(lastKey);
            end = end < 0 ? -end - 1 : end;
            if (begin >= 0 && end - begin == span) {
               int min = (char)((int)minimum);
               int sup = (char)((int)supremum);
               if (firstKey == lastKey) {
                  return this.highLowContainer.getContainerAtIndex(begin).contains(min, (supremum & 65535L) == 0L ? 65536 : sup);
               } else if (!this.highLowContainer.getContainerAtIndex(begin).contains(min, 65536)) {
                  return false;
               } else if (sup != 0 && end < len && !this.highLowContainer.getContainerAtIndex(end).contains(0, sup)) {
                  return false;
               } else {
                  for(int i = begin + 1; i < end; ++i) {
                     if (this.highLowContainer.getContainerAtIndex(i).getCardinality() != 65536) {
                        return false;
                     }
                  }

                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }

   public boolean contains(ImmutableRoaringBitmap subset) {
      int length1 = this.highLowContainer.size();
      int length2 = subset.highLowContainer.size();
      int pos1 = 0;
      int pos2 = 0;

      while(pos1 < length1 && pos2 < length2) {
         char s1 = this.highLowContainer.getKeyAtIndex(pos1);
         char s2 = subset.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            MappeableContainer c1 = this.highLowContainer.getContainerAtIndex(pos1);
            MappeableContainer c2 = subset.highLowContainer.getContainerAtIndex(pos2);
            if (!c1.contains(c2)) {
               return false;
            }

            ++pos1;
            ++pos2;
         } else {
            if (s1 - s2 > 0) {
               return false;
            }

            pos1 = subset.highLowContainer.advanceUntil(s2, pos1);
         }
      }

      return pos2 == length2;
   }

   public boolean equals(Object o) {
      if (o instanceof ImmutableRoaringBitmap) {
         if (this.highLowContainer.size() != ((ImmutableRoaringBitmap)o).highLowContainer.size()) {
            return false;
         } else {
            MappeableContainerPointer mp1 = this.highLowContainer.getContainerPointer();
            MappeableContainerPointer mp2 = ((ImmutableRoaringBitmap)o).highLowContainer.getContainerPointer();

            while(mp1.hasContainer()) {
               if (mp1.key() != mp2.key()) {
                  return false;
               }

               if (mp1.getCardinality() != mp2.getCardinality()) {
                  return false;
               }

               if (!mp1.getContainer().equals(mp2.getContainer())) {
                  return false;
               }

               mp1.advance();
               mp2.advance();
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public boolean isHammingSimilar(ImmutableRoaringBitmap other, int tolerance) {
      int size1 = this.highLowContainer.size();
      int size2 = other.highLowContainer.size();
      int pos1 = 0;
      int pos2 = 0;
      int budget = tolerance;

      while(budget >= 0 && pos1 < size1 && pos2 < size2) {
         char key1 = this.highLowContainer.getKeyAtIndex(pos1);
         char key2 = other.highLowContainer.getKeyAtIndex(pos2);
         MappeableContainer left = this.highLowContainer.getContainerAtIndex(pos1);
         MappeableContainer right = other.highLowContainer.getContainerAtIndex(pos2);
         if (key1 == key2) {
            budget -= left.xorCardinality(right);
            ++pos1;
            ++pos2;
         } else if (key1 < key2) {
            budget -= left.getCardinality();
            ++pos1;
         } else {
            budget -= right.getCardinality();
            ++pos2;
         }
      }

      while(budget >= 0 && pos1 < size1) {
         MappeableContainer container = this.highLowContainer.getContainerAtIndex(pos1++);
         budget -= container.getCardinality();
      }

      while(budget >= 0 && pos2 < size2) {
         MappeableContainer container = other.highLowContainer.getContainerAtIndex(pos2++);
         budget -= container.getCardinality();
      }

      return budget >= 0;
   }

   public boolean intersects(long minimum, long supremum) {
      MutableRoaringBitmap.rangeSanityCheck(minimum, supremum);
      if (supremum <= minimum) {
         return false;
      } else {
         int minKey = (int)(minimum >>> 16);
         int supKey = (int)(supremum >>> 16);
         int length = this.highLowContainer.size();

         int pos;
         for(pos = 0; pos < length && minKey > this.highLowContainer.getKeyAtIndex(pos); ++pos) {
         }

         if (pos == length) {
            return false;
         } else {
            int offset = minKey == this.highLowContainer.getKeyAtIndex(pos) ? BufferUtil.lowbitsAsInteger(minimum) : 0;
            int limit = BufferUtil.lowbitsAsInteger(supremum);
            if (supKey == this.highLowContainer.getKeyAtIndex(pos)) {
               if (supKey > minKey) {
                  offset = 0;
               }

               return this.highLowContainer.getContainerAtIndex(pos).intersects(offset, limit);
            } else {
               while(pos < length && supKey > this.highLowContainer.getKeyAtIndex(pos)) {
                  MappeableContainer container = this.highLowContainer.getContainerAtIndex(pos);
                  if (container.intersects(offset, 65536)) {
                     return true;
                  }

                  offset = 0;
                  ++pos;
               }

               return pos < length && supKey == this.highLowContainer.getKeyAtIndex(pos) && this.highLowContainer.getContainerAtIndex(pos).intersects(offset, limit);
            }
         }
      }
   }

   public long getLongCardinality() {
      long size = 0L;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         size += (long)this.highLowContainer.getCardinality(i);
      }

      return size;
   }

   public int getCardinality() {
      return (int)this.getLongCardinality();
   }

   public boolean cardinalityExceeds(long threshold) {
      long size = 0L;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         size += (long)this.highLowContainer.getContainerAtIndex(i).getCardinality();
         if (size > threshold) {
            return true;
         }
      }

      return false;
   }

   public void forEach(IntConsumer ic) {
      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         this.highLowContainer.getContainerAtIndex(i).forEach(this.highLowContainer.getKeyAtIndex(i), ic);
      }

   }

   public MappeableContainerPointer getContainerPointer() {
      return this.highLowContainer.getContainerPointer();
   }

   public PeekableIntIterator getIntIterator() {
      return new ImmutableRoaringIntIterator();
   }

   public PeekableIntIterator getSignedIntIterator() {
      return new ImmutableRoaringSignedIntIterator();
   }

   public IntIterator getReverseIntIterator() {
      return new ImmutableRoaringReverseIntIterator();
   }

   public BatchIterator getBatchIterator() {
      return new RoaringBatchIterator(null == this.highLowContainer ? null : this.getContainerPointer());
   }

   public long getLongSizeInBytes() {
      long size = 4L;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         if (this.highLowContainer.getContainerAtIndex(i) instanceof MappeableRunContainer) {
            MappeableRunContainer thisRunContainer = (MappeableRunContainer)this.highLowContainer.getContainerAtIndex(i);
            size += (long)(4 + BufferUtil.getSizeInBytesFromCardinalityEtc(0, thisRunContainer.nbrruns, true));
         } else {
            size += (long)(4 + BufferUtil.getSizeInBytesFromCardinalityEtc(this.highLowContainer.getCardinality(i), 0, false));
         }
      }

      return size;
   }

   public int getSizeInBytes() {
      return (int)this.getLongSizeInBytes();
   }

   public int hashCode() {
      return this.highLowContainer.hashCode();
   }

   public boolean hasRunCompression() {
      return this.highLowContainer.hasRunCompression();
   }

   public boolean isEmpty() {
      return this.highLowContainer.size() == 0;
   }

   public Iterator iterator() {
      return (new Iterator() {
         int hs = 0;
         CharIterator iter;
         int pos = 0;
         int x;

         public boolean hasNext() {
            return this.pos < ImmutableRoaringBitmap.this.highLowContainer.size();
         }

         public Iterator init() {
            if (this.pos < ImmutableRoaringBitmap.this.highLowContainer.size()) {
               this.iter = ImmutableRoaringBitmap.this.highLowContainer.getContainerAtIndex(this.pos).getCharIterator();
               this.hs = ImmutableRoaringBitmap.this.highLowContainer.getKeyAtIndex(this.pos) << 16;
            }

            return this;
         }

         public Integer next() {
            this.x = this.iter.nextAsInt() | this.hs;
            if (!this.iter.hasNext()) {
               ++this.pos;
               this.init();
            }

            return this.x;
         }

         public void remove() {
            throw new RuntimeException("Cannot modify.");
         }
      }).init();
   }

   public MutableRoaringBitmap limit(int maxcardinality) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      int currentcardinality = 0;

      for(int i = 0; currentcardinality < maxcardinality && i < this.highLowContainer.size(); ++i) {
         MappeableContainer c = this.highLowContainer.getContainerAtIndex(i);
         if (c.getCardinality() + currentcardinality > maxcardinality) {
            int leftover = maxcardinality - currentcardinality;
            MappeableContainer limited = c.limit(leftover);
            ((MutableRoaringArray)answer.highLowContainer).append(this.highLowContainer.getKeyAtIndex(i), limited);
            break;
         }

         ((MutableRoaringArray)answer.highLowContainer).append(this.highLowContainer.getKeyAtIndex(i), c.clone());
         currentcardinality += c.getCardinality();
      }

      return answer;
   }

   public long rankLong(int x) {
      long size = 0L;
      char xhigh = BufferUtil.highbits(x);

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         char key = this.highLowContainer.getKeyAtIndex(i);
         if (key < xhigh) {
            size += (long)this.highLowContainer.getCardinality(i);
         } else if (key == xhigh) {
            return size + (long)this.highLowContainer.getContainerAtIndex(i).rank(BufferUtil.lowbits(x));
         }
      }

      return size;
   }

   public long rangeCardinality(long start, long end) {
      if (Long.compareUnsigned(start, end) >= 0) {
         return 0L;
      } else {
         long size = 0L;
         int startIndex = this.highLowContainer.getIndex(BufferUtil.highbits(start));
         if (startIndex < 0) {
            startIndex = -startIndex - 1;
         } else {
            int inContainerStart = BufferUtil.lowbits(start);
            if (inContainerStart != 0) {
               size -= (long)this.highLowContainer.getContainerAtIndex(startIndex).rank((char)(inContainerStart - 1));
            }
         }

         char xhigh = BufferUtil.highbits(end - 1L);

         for(int i = startIndex; i < this.highLowContainer.size(); ++i) {
            char key = this.highLowContainer.getKeyAtIndex(i);
            if (key < xhigh) {
               size += (long)this.highLowContainer.getContainerAtIndex(i).getCardinality();
            } else if (key == xhigh) {
               return size + (long)this.highLowContainer.getContainerAtIndex(i).rank(BufferUtil.lowbits((int)(end - 1L)));
            }
         }

         return size;
      }
   }

   public int rank(int x) {
      return (int)this.rankLong(x);
   }

   public int select(int j) {
      long leftover = Util.toUnsignedLong(j);

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         int thiscard = this.highLowContainer.getCardinality(i);
         if ((long)thiscard > leftover) {
            int keycontrib = this.highLowContainer.getKeyAtIndex(i) << 16;
            MappeableContainer c = this.highLowContainer.getContainerAtIndex(i);
            int lowcontrib = c.select((int)leftover);
            return lowcontrib + keycontrib;
         }

         leftover -= (long)thiscard;
      }

      throw new IllegalArgumentException("You are trying to select the " + j + "th value when the cardinality is " + this.getCardinality() + ".");
   }

   public int first() {
      return this.highLowContainer.first();
   }

   public int last() {
      return this.highLowContainer.last();
   }

   public int firstSigned() {
      return this.highLowContainer.firstSigned();
   }

   public int lastSigned() {
      return this.highLowContainer.lastSigned();
   }

   public long nextValue(int fromValue) {
      char key = BufferUtil.highbits(fromValue);
      int containerIndex = this.highLowContainer.advanceUntil(key, -1);

      long nextSetBit;
      for(nextSetBit = -1L; containerIndex < this.highLowContainer.size() && nextSetBit == -1L; ++containerIndex) {
         char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
         MappeableContainer container = this.highLowContainer.getContainerAtIndex(containerIndex);
         int bit = containerKey - key > 0 ? container.first() : container.nextValue(BufferUtil.lowbits(fromValue));
         nextSetBit = bit == -1 ? -1L : Util.toUnsignedLong(containerKey << 16 | bit);
      }

      assert nextSetBit <= 4294967295L;

      assert nextSetBit == -1L || nextSetBit >= Util.toUnsignedLong(fromValue);

      return nextSetBit;
   }

   public long previousValue(int fromValue) {
      if (this.isEmpty()) {
         return -1L;
      } else {
         char key = BufferUtil.highbits(fromValue);
         int containerIndex = this.highLowContainer.advanceUntil(key, -1);
         if (containerIndex == this.highLowContainer.size()) {
            return Util.toUnsignedLong(this.last());
         } else {
            if (this.highLowContainer.getKeyAtIndex(containerIndex) > key) {
               --containerIndex;
            }

            long prevSetBit;
            for(prevSetBit = -1L; containerIndex != -1 && prevSetBit == -1L; --containerIndex) {
               char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
               MappeableContainer container = this.highLowContainer.getContainerAtIndex(containerIndex);
               int bit = containerKey < key ? container.last() : container.previousValue(BufferUtil.lowbits(fromValue));
               prevSetBit = bit == -1 ? -1L : Util.toUnsignedLong(containerKey << 16 | bit);
            }

            assert prevSetBit <= 4294967295L;

            assert prevSetBit <= Util.toUnsignedLong(fromValue);

            return prevSetBit;
         }
      }
   }

   public long nextAbsentValue(int fromValue) {
      long nextAbsentBit = this.computeNextAbsentValue(fromValue);
      return nextAbsentBit == 4294967296L ? -1L : nextAbsentBit;
   }

   private long computeNextAbsentValue(int fromValue) {
      char key = BufferUtil.highbits(fromValue);
      int containerIndex = this.highLowContainer.advanceUntil(key, -1);
      int size = this.highLowContainer.size();
      if (containerIndex == size) {
         return Util.toUnsignedLong(fromValue);
      } else {
         char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
         if (fromValue < containerKey << 16) {
            return Util.toUnsignedLong(fromValue);
         } else {
            MappeableContainer container = this.highLowContainer.getContainerAtIndex(containerIndex);

            int bit;
            for(bit = container.nextAbsentValue(BufferUtil.lowbits(fromValue)); bit == 65536; bit = container.nextAbsentValue('\u0000')) {
               assert container.last() == 65535;

               if (containerIndex == size - 1) {
                  return Util.toUnsignedLong(this.highLowContainer.last()) + 1L;
               }

               ++containerIndex;
               char nextContainerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
               if (containerKey + 1 < nextContainerKey) {
                  return Util.toUnsignedLong(containerKey + 1 << 16);
               }

               containerKey = nextContainerKey;
               container = this.highLowContainer.getContainerAtIndex(containerIndex);
            }

            return Util.toUnsignedLong(containerKey << 16 | bit);
         }
      }
   }

   public long previousAbsentValue(int fromValue) {
      long prevAbsentBit = this.computePreviousAbsentValue(fromValue);

      assert prevAbsentBit <= 4294967295L;

      assert prevAbsentBit <= Util.toUnsignedLong(fromValue);

      assert !this.contains((int)prevAbsentBit);

      return prevAbsentBit;
   }

   private long computePreviousAbsentValue(int fromValue) {
      char key = BufferUtil.highbits(fromValue);
      int containerIndex = this.highLowContainer.advanceUntil(key, -1);
      if (containerIndex == this.highLowContainer.size()) {
         return Util.toUnsignedLong(fromValue);
      } else {
         char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
         if (fromValue < containerKey << 16) {
            return Util.toUnsignedLong(fromValue);
         } else {
            MappeableContainer container = this.highLowContainer.getContainerAtIndex(containerIndex);

            int bit;
            for(bit = container.previousAbsentValue(BufferUtil.lowbits(fromValue)); bit == -1; bit = container.previousAbsentValue('\uffff')) {
               assert container.first() == 0;

               if (containerIndex == 0) {
                  return Util.toUnsignedLong(this.highLowContainer.first()) - 1L;
               }

               --containerIndex;
               char nextContainerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
               if (nextContainerKey < containerKey - 1) {
                  return Util.toUnsignedLong(containerKey << 16) - 1L;
               }

               containerKey = nextContainerKey;
               container = this.highLowContainer.getContainerAtIndex(containerIndex);
            }

            return Util.toUnsignedLong(containerKey << 16 | bit);
         }
      }
   }

   public void serialize(DataOutput out) throws IOException {
      this.highLowContainer.serialize(out);
   }

   public void serialize(ByteBuffer buffer) {
      this.highLowContainer.serialize(buffer);
   }

   public int serializedSizeInBytes() {
      return this.highLowContainer.serializedSizeInBytes();
   }

   public int[] toArray() {
      int[] array = new int[this.getCardinality()];
      int pos = 0;

      MappeableContainer c;
      for(int pos2 = 0; pos < this.highLowContainer.size(); pos2 += c.getCardinality()) {
         int hs = this.highLowContainer.getKeyAtIndex(pos) << 16;
         c = this.highLowContainer.getContainerAtIndex(pos++);
         c.fillLeastSignificant16bits(array, pos2, hs);
      }

      return array;
   }

   public MutableRoaringBitmap toMutableRoaringBitmap() {
      MutableRoaringBitmap c = new MutableRoaringBitmap();
      MappeableContainerPointer mcp = this.highLowContainer.getContainerPointer();

      while(mcp.hasContainer()) {
         c.getMappeableRoaringArray().appendCopy(mcp.key(), mcp.getContainer());
         mcp.advance();
      }

      return c;
   }

   public RoaringBitmap toRoaringBitmap() {
      return new RoaringBitmap(this);
   }

   public String toString() {
      StringBuilder answer = new StringBuilder("{}".length() + "-123456789,".length() * 256);
      IntIterator i = this.getIntIterator();
      answer.append('{');
      if (i.hasNext()) {
         answer.append((long)i.next() & 4294967295L);
      }

      while(i.hasNext()) {
         answer.append(',');
         if (answer.length() > 524288) {
            answer.append('.').append('.').append('.');
            break;
         }

         answer.append((long)i.next() & 4294967295L);
      }

      answer.append('}');
      return answer.toString();
   }

   public int getContainerCount() {
      return this.highLowContainer.size();
   }

   private class ImmutableRoaringIntIterator implements PeekableIntIterator {
      private boolean wrap;
      private MappeableContainerPointer cp;
      private int iterations = 0;
      private int hs = 0;
      private PeekableCharIterator iter;
      private boolean ok;

      public ImmutableRoaringIntIterator() {
         char index = this.findStartingContainerIndex();
         this.wrap = index != 0;
         this.cp = ImmutableRoaringBitmap.this.highLowContainer.getContainerPointer(index);
         this.nextContainer();
      }

      char findStartingContainerIndex() {
         return '\u0000';
      }

      public PeekableIntIterator clone() {
         try {
            ImmutableRoaringIntIterator x = (ImmutableRoaringIntIterator)super.clone();
            if (this.iter != null) {
               x.iter = this.iter.clone();
            }

            if (this.cp != null) {
               x.cp = this.cp.clone();
            }

            x.wrap = this.wrap;
            x.iterations = this.iterations;
            return x;
         } catch (CloneNotSupportedException var2) {
            return null;
         }
      }

      public boolean hasNext() {
         return this.ok;
      }

      public int next() {
         int x = this.iter.nextAsInt() | this.hs;
         if (!this.iter.hasNext()) {
            this.cp.advance();
            this.nextContainer();
         }

         return x;
      }

      private void nextContainer() {
         int containerSize = ImmutableRoaringBitmap.this.highLowContainer.size();
         if (!this.wrap && this.iterations >= containerSize) {
            this.ok = false;
         } else {
            this.ok = this.cp.hasContainer();
            if (!this.ok && this.wrap && this.iterations < containerSize) {
               this.cp = ImmutableRoaringBitmap.this.highLowContainer.getContainerPointer();
               this.wrap = false;
               this.ok = this.cp.hasContainer();
            }

            if (this.ok) {
               this.iter = this.cp.getContainer().getCharIterator();
               this.hs = this.cp.key() << 16;
               ++this.iterations;
            }
         }

      }

      public void advanceIfNeeded(int minval) {
         while(this.hasNext() && this.shouldAdvanceContainer(this.hs, minval)) {
            this.cp.advance();
            this.nextContainer();
         }

         if (this.ok && this.hs >>> 16 == minval >>> 16) {
            this.iter.advanceIfNeeded(BufferUtil.lowbits(minval));
            if (!this.iter.hasNext()) {
               this.cp.advance();
               this.nextContainer();
            }
         }

      }

      boolean shouldAdvanceContainer(int hs, int minval) {
         return hs >>> 16 < minval >>> 16;
      }

      public int peekNext() {
         return this.iter.peekNext() | this.hs;
      }
   }

   private class ImmutableRoaringSignedIntIterator extends ImmutableRoaringIntIterator {
      private ImmutableRoaringSignedIntIterator() {
      }

      char findStartingContainerIndex() {
         char index = (char)ImmutableRoaringBitmap.this.highLowContainer.advanceUntil('耀', -1);
         if (index == ImmutableRoaringBitmap.this.highLowContainer.size()) {
            index = 0;
         }

         return index;
      }

      boolean shouldAdvanceContainer(int hs, int minval) {
         return hs >> 16 < minval >> 16;
      }
   }

   private final class ImmutableRoaringReverseIntIterator implements IntIterator {
      private MappeableContainerPointer cp;
      private int hs;
      private CharIterator iter;
      private boolean ok;

      public ImmutableRoaringReverseIntIterator() {
         this.cp = ImmutableRoaringBitmap.this.highLowContainer.getContainerPointer(ImmutableRoaringBitmap.this.highLowContainer.size() - 1);
         this.hs = 0;
         this.nextContainer();
      }

      public IntIterator clone() {
         try {
            ImmutableRoaringReverseIntIterator x = (ImmutableRoaringReverseIntIterator)super.clone();
            if (this.iter != null) {
               x.iter = this.iter.clone();
            }

            if (this.cp != null) {
               x.cp = this.cp.clone();
            }

            return x;
         } catch (CloneNotSupportedException var2) {
            return null;
         }
      }

      public boolean hasNext() {
         return this.ok;
      }

      public int next() {
         int x = this.iter.nextAsInt() | this.hs;
         if (!this.iter.hasNext()) {
            this.cp.previous();
            this.nextContainer();
         }

         return x;
      }

      private void nextContainer() {
         this.ok = this.cp.hasContainer();
         if (this.ok) {
            this.iter = this.cp.getContainer().getReverseCharIterator();
            this.hs = this.cp.key() << 16;
         }

      }
   }
}
