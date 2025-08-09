package org.roaringbitmap.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import org.roaringbitmap.Util;

public final class BufferUtil {
   public static MappeableContainer[] addOffset(MappeableContainer source, char offsets) {
      if (source instanceof MappeableArrayContainer) {
         return addOffsetArray((MappeableArrayContainer)source, offsets);
      } else if (source instanceof MappeableBitmapContainer) {
         return addOffsetBitmap((MappeableBitmapContainer)source, offsets);
      } else if (source instanceof MappeableRunContainer) {
         return addOffsetRun((MappeableRunContainer)source, offsets);
      } else {
         throw new RuntimeException("unknown container type");
      }
   }

   private static MappeableContainer[] addOffsetArray(MappeableArrayContainer source, char offsets) {
      int splitIndex;
      if (source.first() + offsets > 65535) {
         splitIndex = 0;
      } else if (source.last() + offsets < 65535) {
         splitIndex = source.cardinality;
      } else {
         splitIndex = unsignedBinarySearch(source.content, 0, source.cardinality, (char)(65536 - offsets));
         if (splitIndex < 0) {
            splitIndex = -splitIndex - 1;
         }
      }

      MappeableArrayContainer low = splitIndex == 0 ? new MappeableArrayContainer() : new MappeableArrayContainer(splitIndex);
      MappeableArrayContainer high = source.cardinality - splitIndex == 0 ? new MappeableArrayContainer() : new MappeableArrayContainer(source.cardinality - splitIndex);
      int lowCardinality = 0;

      for(int k = 0; k < splitIndex; ++k) {
         int val = source.content.get(k) + offsets;
         low.content.put(lowCardinality++, (char)val);
      }

      low.cardinality = lowCardinality;
      int highCardinality = 0;

      for(int k = splitIndex; k < source.cardinality; ++k) {
         int val = source.content.get(k) + offsets;
         high.content.put(highCardinality++, (char)val);
      }

      high.cardinality = highCardinality;
      return new MappeableContainer[]{low, high};
   }

   private static MappeableContainer[] addOffsetBitmap(MappeableBitmapContainer source, char offsets) {
      MappeableBitmapContainer c = source;
      MappeableBitmapContainer low = new MappeableBitmapContainer();
      MappeableBitmapContainer high = new MappeableBitmapContainer();
      low.cardinality = -1;
      high.cardinality = -1;
      int b = offsets >>> 6;
      int i = offsets % 64;
      if (i == 0) {
         for(int k = 0; k < 1024 - b; ++k) {
            low.bitmap.put(b + k, c.bitmap.get(k));
         }

         for(int k = 1024 - b; k < 1024; ++k) {
            high.bitmap.put(k - (1024 - b), c.bitmap.get(k));
         }
      } else {
         low.bitmap.put(b + 0, source.bitmap.get(0) << i);

         for(int k = 1; k < 1024 - b; ++k) {
            low.bitmap.put(b + k, c.bitmap.get(k) << i | c.bitmap.get(k - 1) >>> 64 - i);
         }

         for(int k = 1024 - b; k < 1024; ++k) {
            high.bitmap.put(k - (1024 - b), c.bitmap.get(k) << i | c.bitmap.get(k - 1) >>> 64 - i);
         }

         high.bitmap.put(b, c.bitmap.get(1023) >>> 64 - i);
      }

      return new MappeableContainer[]{low.repairAfterLazy(), high.repairAfterLazy()};
   }

   private static MappeableContainer[] addOffsetRun(MappeableRunContainer source, char offsets) {
      MappeableRunContainer c = source;
      MappeableRunContainer low = new MappeableRunContainer();
      MappeableRunContainer high = new MappeableRunContainer();

      for(int k = 0; k < c.nbrruns; ++k) {
         int val = c.getValue(k);
         val += offsets;
         int finalval = val + c.getLength(k);
         if (val <= 65535) {
            if (finalval <= 65535) {
               low.smartAppend((char)val, c.getLength(k));
            } else {
               low.smartAppend((char)val, (char)('\uffff' - val));
               high.smartAppend('\u0000', (char)finalval);
            }
         } else {
            high.smartAppend((char)val, c.getLength(k));
         }
      }

      return new MappeableContainer[]{low, high};
   }

   protected static int advanceUntil(CharBuffer array, int pos, int length, char min) {
      int lower = pos + 1;
      if (lower < length && array.get(lower) < min) {
         int spansize;
         for(spansize = 1; lower + spansize < length && array.get(lower + spansize) < min; spansize *= 2) {
         }

         int upper = lower + spansize < length ? lower + spansize : length - 1;
         if (array.get(upper) == min) {
            return upper;
         } else if (array.get(upper) < min) {
            return length;
         } else {
            lower += spansize >>> 1;

            while(lower + 1 != upper) {
               int mid = lower + upper >>> 1;
               char arraymid = array.get(mid);
               if (arraymid == min) {
                  return mid;
               }

               if (arraymid < min) {
                  lower = mid;
               } else {
                  upper = mid;
               }
            }

            return upper;
         }
      } else {
         return lower;
      }
   }

   public static int iterateUntil(CharBuffer array, int pos, int length, int min) {
      while(pos < length && array.get(pos) < min) {
         ++pos;
      }

      return pos;
   }

   protected static void arraycopy(CharBuffer src, int srcPos, CharBuffer dest, int destPos, int length) {
      if (isBackedBySimpleArray(src) && isBackedBySimpleArray(dest)) {
         System.arraycopy(src.array(), srcPos, dest.array(), destPos, length);
      } else if (srcPos < destPos) {
         for(int k = length - 1; k >= 0; --k) {
            dest.put(destPos + k, src.get(k + srcPos));
         }
      } else {
         for(int k = 0; k < length; ++k) {
            dest.put(destPos + k, src.get(k + srcPos));
         }
      }

   }

   protected static int branchyUnsignedBinarySearch(CharBuffer array, int begin, int end, char k) {
      if (end > 0 && array.get(end - 1) < k) {
         return -end - 1;
      } else {
         int low = begin;
         int high = end - 1;

         while(low <= high) {
            int middleIndex = low + high >>> 1;
            int middleValue = array.get(middleIndex);
            if (middleValue < k) {
               low = middleIndex + 1;
            } else {
               if (middleValue <= k) {
                  return middleIndex;
               }

               high = middleIndex - 1;
            }
         }

         return -(low + 1);
      }
   }

   protected static int branchyUnsignedBinarySearch(ByteBuffer array, int position, int begin, int end, char k) {
      if (end > 0 && array.getChar(position + (end - 1) * 2) < k) {
         return -end - 1;
      } else {
         int low = begin;
         int high = end - 1;

         while(low <= high) {
            int middleIndex = low + high >>> 1;
            int middleValue = array.getChar(position + 2 * middleIndex);
            if (middleValue < k) {
               low = middleIndex + 1;
            } else {
               if (middleValue <= k) {
                  return middleIndex;
               }

               high = middleIndex - 1;
            }
         }

         return -(low + 1);
      }
   }

   protected static void fillArrayAND(char[] container, LongBuffer bitmap1, LongBuffer bitmap2) {
      int pos = 0;
      if (bitmap1.limit() != bitmap2.limit()) {
         throw new IllegalArgumentException("not supported");
      } else {
         if (isBackedBySimpleArray(bitmap1) && isBackedBySimpleArray(bitmap2)) {
            int len = bitmap1.limit();
            long[] b1 = bitmap1.array();
            long[] b2 = bitmap2.array();

            for(int k = 0; k < len; ++k) {
               for(long bitset = b1[k] & b2[k]; bitset != 0L; bitset &= bitset - 1L) {
                  container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
               }
            }
         } else {
            int len = bitmap1.limit();

            for(int k = 0; k < len; ++k) {
               for(long bitset = bitmap1.get(k) & bitmap2.get(k); bitset != 0L; bitset &= bitset - 1L) {
                  container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
               }
            }
         }

      }
   }

   protected static void fillArrayANDNOT(char[] container, LongBuffer bitmap1, LongBuffer bitmap2) {
      int pos = 0;
      if (bitmap1.limit() != bitmap2.limit()) {
         throw new IllegalArgumentException("not supported");
      } else {
         if (isBackedBySimpleArray(bitmap1) && isBackedBySimpleArray(bitmap2)) {
            int len = bitmap1.limit();
            long[] b1 = bitmap1.array();
            long[] b2 = bitmap2.array();

            for(int k = 0; k < len; ++k) {
               for(long bitset = b1[k] & ~b2[k]; bitset != 0L; bitset &= bitset - 1L) {
                  container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
               }
            }
         } else {
            int len = bitmap1.limit();

            for(int k = 0; k < len; ++k) {
               for(long bitset = bitmap1.get(k) & ~bitmap2.get(k); bitset != 0L; bitset &= bitset - 1L) {
                  container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
               }
            }
         }

      }
   }

   protected static void fillArrayXOR(char[] container, LongBuffer bitmap1, LongBuffer bitmap2) {
      int pos = 0;
      if (bitmap1.limit() != bitmap2.limit()) {
         throw new IllegalArgumentException("not supported");
      } else {
         if (isBackedBySimpleArray(bitmap1) && isBackedBySimpleArray(bitmap2)) {
            Util.fillArrayXOR(container, bitmap1.array(), bitmap2.array());
         } else {
            int len = bitmap1.limit();

            for(int k = 0; k < len; ++k) {
               for(long bitset = bitmap1.get(k) ^ bitmap2.get(k); bitset != 0L; bitset &= bitset - 1L) {
                  container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
               }
            }
         }

      }
   }

   public static void flipBitmapRange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         Util.flipBitmapRange(bitmap.array(), start, end);
      } else if (start != end) {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         bitmap.put(firstword, bitmap.get(firstword) ^ ~(-1L << start));

         for(int i = firstword; i < endword; ++i) {
            bitmap.put(i, ~bitmap.get(i));
         }

         bitmap.put(endword, bitmap.get(endword) ^ -1L >>> -end);
      }
   }

   /** @deprecated */
   @Deprecated
   private static int cardinalityInBitmapWordRange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         return Util.cardinalityInBitmapWordRange(bitmap.array(), start, end);
      } else if (start >= end) {
         return 0;
      } else {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         int answer = 0;

         for(int i = firstword; i <= endword; ++i) {
            answer += Long.bitCount(bitmap.get(i));
         }

         return answer;
      }
   }

   public static int cardinalityInBitmapRange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         return Util.cardinalityInBitmapRange(bitmap.array(), start, end);
      } else if (start >= end) {
         return 0;
      } else {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         if (firstword == endword) {
            return Long.bitCount(bitmap.get(firstword) & -1L << start & -1L >>> -end);
         } else {
            int answer = Long.bitCount(bitmap.get(firstword) & -1L << start);

            for(int i = firstword + 1; i < endword; ++i) {
               answer += Long.bitCount(bitmap.get(i));
            }

            answer += Long.bitCount(bitmap.get(endword) & -1L >>> -end);
            return answer;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static int setBitmapRangeAndCardinalityChange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         return Util.setBitmapRangeAndCardinalityChange(bitmap.array(), start, end);
      } else {
         int cardbefore = cardinalityInBitmapWordRange(bitmap, start, end);
         setBitmapRange(bitmap, start, end);
         int cardafter = cardinalityInBitmapWordRange(bitmap, start, end);
         return cardafter - cardbefore;
      }
   }

   /** @deprecated */
   @Deprecated
   public static int flipBitmapRangeAndCardinalityChange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         return Util.flipBitmapRangeAndCardinalityChange(bitmap.array(), start, end);
      } else {
         int cardbefore = cardinalityInBitmapWordRange(bitmap, start, end);
         flipBitmapRange(bitmap, start, end);
         int cardafter = cardinalityInBitmapWordRange(bitmap, start, end);
         return cardafter - cardbefore;
      }
   }

   /** @deprecated */
   @Deprecated
   public static int resetBitmapRangeAndCardinalityChange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         return Util.resetBitmapRangeAndCardinalityChange(bitmap.array(), start, end);
      } else {
         int cardbefore = cardinalityInBitmapWordRange(bitmap, start, end);
         resetBitmapRange(bitmap, start, end);
         int cardafter = cardinalityInBitmapWordRange(bitmap, start, end);
         return cardafter - cardbefore;
      }
   }

   protected static int getSizeInBytesFromCardinalityEtc(int card, int numRuns, boolean isRunEncoded) {
      if (isRunEncoded) {
         return 2 + numRuns * 2 * 2;
      } else {
         boolean isBitmap = card > 4096;
         return isBitmap ? 8192 : card * 2;
      }
   }

   protected static char highbits(int x) {
      return (char)(x >>> 16);
   }

   protected static char highbits(long x) {
      return (char)((int)(x >>> 16));
   }

   protected static boolean isBackedBySimpleArray(Buffer b) {
      return b.hasArray() && b.arrayOffset() == 0;
   }

   protected static char lowbits(int x) {
      return (char)x;
   }

   protected static char lowbits(long x) {
      return (char)((int)x);
   }

   protected static int lowbitsAsInteger(long x) {
      return (int)(x & 65535L);
   }

   protected static char maxLowBit() {
      return '\uffff';
   }

   protected static int maxLowBitAsInteger() {
      return 65535;
   }

   public static void resetBitmapRange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         Util.resetBitmapRange(bitmap.array(), start, end);
      } else if (start != end) {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         if (firstword == endword) {
            bitmap.put(firstword, bitmap.get(firstword) & ~(-1L << start & -1L >>> -end));
         } else {
            bitmap.put(firstword, bitmap.get(firstword) & ~(-1L << start));

            for(int i = firstword + 1; i < endword; ++i) {
               bitmap.put(i, 0L);
            }

            bitmap.put(endword, bitmap.get(endword) & ~(-1L >>> -end));
         }
      }
   }

   public static void setBitmapRange(LongBuffer bitmap, int start, int end) {
      if (isBackedBySimpleArray(bitmap)) {
         Util.setBitmapRange(bitmap.array(), start, end);
      } else if (start != end) {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         if (firstword == endword) {
            bitmap.put(firstword, bitmap.get(firstword) | -1L << start & -1L >>> -end);
         } else {
            bitmap.put(firstword, bitmap.get(firstword) | -1L << start);

            for(int i = firstword + 1; i < endword; ++i) {
               bitmap.put(i, -1L);
            }

            bitmap.put(endword, bitmap.get(endword) | -1L >>> -end);
         }
      }
   }

   public static int unsignedBinarySearch(CharBuffer array, int begin, int end, char k) {
      return branchyUnsignedBinarySearch(array, begin, end, k);
   }

   public static int unsignedBinarySearch(ByteBuffer array, int position, int begin, int end, char k) {
      return branchyUnsignedBinarySearch(array, position, begin, end, k);
   }

   protected static int unsignedDifference(CharBuffer set1, int length1, CharBuffer set2, int length2, char[] buffer) {
      int pos = 0;
      int k1 = 0;
      int k2 = 0;
      if (0 == length2) {
         set1.get(buffer, 0, length1);
         return length1;
      } else if (0 == length1) {
         return 0;
      } else {
         char s1 = set1.get(k1);
         char s2 = set2.get(k2);

         while(true) {
            if (s1 < s2) {
               buffer[pos++] = s1;
               ++k1;
               if (k1 >= length1) {
                  break;
               }

               s1 = set1.get(k1);
            } else if (s1 == s2) {
               ++k1;
               ++k2;
               if (k1 >= length1) {
                  break;
               }

               if (k2 >= length2) {
                  set1.position(k1);
                  set1.get(buffer, pos, length1 - k1);
                  return pos + length1 - k1;
               }

               s1 = set1.get(k1);
               s2 = set2.get(k2);
            } else {
               ++k2;
               if (k2 >= length2) {
                  set1.position(k1);
                  set1.get(buffer, pos, length1 - k1);
                  return pos + length1 - k1;
               }

               s2 = set2.get(k2);
            }
         }

         return pos;
      }
   }

   public static int intersectArrayIntoBitmap(long[] bitmap, CharBuffer array, int length) {
      int lastWordIndex = 0;
      int wordIndex = 0;
      long word = 0L;
      int cardinality = 0;

      for(int i = 0; i < length; ++i) {
         wordIndex = array.get(i) >>> 6;
         if (wordIndex != lastWordIndex) {
            bitmap[lastWordIndex] &= word;
            cardinality += Long.bitCount(bitmap[lastWordIndex]);
            word = 0L;
            Arrays.fill(bitmap, lastWordIndex + 1, wordIndex, 0L);
            lastWordIndex = wordIndex;
         }

         word |= 1L << array.get(i);
      }

      if (word != 0L) {
         bitmap[wordIndex] &= word;
         cardinality += Long.bitCount(bitmap[lastWordIndex]);
      }

      if (wordIndex < bitmap.length) {
         Arrays.fill(bitmap, wordIndex + 1, bitmap.length, 0L);
      }

      return cardinality;
   }

   public static int intersectArrayIntoBitmap(LongBuffer bitmap, CharBuffer array, int length) {
      if (isBackedBySimpleArray(bitmap)) {
         return intersectArrayIntoBitmap(bitmap.array(), array, length);
      } else {
         int lastWordIndex = 0;
         int wordIndex = 0;
         long word = 0L;
         int cardinality = 0;

         for(int i = 0; i < length; ++i) {
            wordIndex = array.get(i) >>> 6;
            if (wordIndex != lastWordIndex) {
               long lastWord = bitmap.get(lastWordIndex);
               lastWord &= word;
               bitmap.put(lastWordIndex, lastWord);
               cardinality += Long.bitCount(lastWord);
               word = 0L;

               for(int j = lastWordIndex + 1; j < wordIndex; ++j) {
                  bitmap.put(j, 0L);
               }

               lastWordIndex = wordIndex;
            }

            word |= 1L << array.get(i);
         }

         if (word != 0L) {
            long currentWord = bitmap.get(wordIndex);
            currentWord &= word;
            bitmap.put(wordIndex, currentWord);
            cardinality += Long.bitCount(currentWord);
         }

         if (wordIndex < bitmap.limit()) {
            for(int j = wordIndex + 1; j < bitmap.limit(); ++j) {
               bitmap.put(j, 0L);
            }
         }

         return cardinality;
      }
   }

   protected static int unsignedExclusiveUnion2by2(CharBuffer set1, int length1, CharBuffer set2, int length2, char[] buffer) {
      int pos = 0;
      int k1 = 0;
      int k2 = 0;
      if (0 == length2) {
         set1.get(buffer, 0, length1);
         return length1;
      } else if (0 == length1) {
         set2.get(buffer, 0, length2);
         return length2;
      } else {
         char s1 = set1.get(k1);
         char s2 = set2.get(k2);

         while(true) {
            while(s1 >= s2) {
               if (s1 == s2) {
                  ++k1;
                  ++k2;
                  if (k1 >= length1) {
                     set2.position(k2);
                     set2.get(buffer, pos, length2 - k2);
                     return pos + length2 - k2;
                  }

                  if (k2 >= length2) {
                     set1.position(k1);
                     set1.get(buffer, pos, length1 - k1);
                     return pos + length1 - k1;
                  }

                  s1 = set1.get(k1);
                  s2 = set2.get(k2);
               } else {
                  buffer[pos++] = s2;
                  ++k2;
                  if (k2 >= length2) {
                     set1.position(k1);
                     set1.get(buffer, pos, length1 - k1);
                     return pos + length1 - k1;
                  }

                  s2 = set2.get(k2);
               }
            }

            buffer[pos++] = s1;
            ++k1;
            if (k1 >= length1) {
               set2.position(k2);
               set2.get(buffer, pos, length2 - k2);
               return pos + length2 - k2;
            }

            s1 = set1.get(k1);
         }
      }
   }

   protected static int unsignedIntersect2by2(CharBuffer set1, int length1, CharBuffer set2, int length2, char[] buffer) {
      int THRESHOLD = 34;
      if (length1 * 34 < length2) {
         return unsignedOneSidedGallopingIntersect2by2(set1, length1, set2, length2, buffer);
      } else {
         return length2 * 34 < length1 ? unsignedOneSidedGallopingIntersect2by2(set2, length2, set1, length1, buffer) : unsignedLocalIntersect2by2(set1, length1, set2, length2, buffer);
      }
   }

   public static boolean unsignedIntersects(CharBuffer set1, int length1, CharBuffer set2, int length2) {
      if (0 != length1 && 0 != length2) {
         int k1 = 0;
         int k2 = 0;
         char s1 = set1.get(k1);
         char s2 = set2.get(k2);

         while(true) {
            if (s2 < s1) {
               do {
                  ++k2;
                  if (k2 == length2) {
                     return false;
                  }

                  s2 = set2.get(k2);
               } while(s2 < s1);
            }

            if (s1 >= s2) {
               return true;
            }

            while(true) {
               ++k1;
               if (k1 == length1) {
                  return false;
               }

               s1 = set1.get(k1);
               if (s1 >= s2) {
                  break;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected static int unsignedLocalIntersect2by2(CharBuffer set1, int length1, CharBuffer set2, int length2, char[] buffer) {
      if (0 != length1 && 0 != length2) {
         int k1 = 0;
         int k2 = 0;
         int pos = 0;
         char s1 = set1.get(k1);
         char s2 = set2.get(k2);

         while(true) {
            if (s2 < s1) {
               do {
                  ++k2;
                  if (k2 == length2) {
                     return pos;
                  }

                  s2 = set2.get(k2);
               } while(s2 < s1);
            }

            if (s1 < s2) {
               while(true) {
                  ++k1;
                  if (k1 == length1) {
                     return pos;
                  }

                  s1 = set1.get(k1);
                  if (s1 >= s2) {
                     break;
                  }
               }
            } else {
               buffer[pos++] = s1;
               ++k1;
               if (k1 == length1) {
                  break;
               }

               s1 = set1.get(k1);
               ++k2;
               if (k2 == length2) {
                  break;
               }

               s2 = set2.get(k2);
            }
         }

         return pos;
      } else {
         return 0;
      }
   }

   protected static int unsignedLocalIntersect2by2Cardinality(CharBuffer set1, int length1, CharBuffer set2, int length2) {
      if (0 != length1 && 0 != length2) {
         int k1 = 0;
         int k2 = 0;
         int pos = 0;
         char s1 = set1.get(k1);
         char s2 = set2.get(k2);

         while(true) {
            if (s2 < s1) {
               do {
                  ++k2;
                  if (k2 == length2) {
                     return pos;
                  }

                  s2 = set2.get(k2);
               } while(s2 < s1);
            }

            if (s1 < s2) {
               while(true) {
                  ++k1;
                  if (k1 == length1) {
                     return pos;
                  }

                  s1 = set1.get(k1);
                  if (s1 >= s2) {
                     break;
                  }
               }
            } else {
               ++pos;
               ++k1;
               if (k1 == length1) {
                  break;
               }

               s1 = set1.get(k1);
               ++k2;
               if (k2 == length2) {
                  break;
               }

               s2 = set2.get(k2);
            }
         }

         return pos;
      } else {
         return 0;
      }
   }

   protected static int unsignedOneSidedGallopingIntersect2by2(CharBuffer smallSet, int smallLength, CharBuffer largeSet, int largeLength, char[] buffer) {
      if (0 == smallLength) {
         return 0;
      } else {
         int k1 = 0;
         int k2 = 0;
         int pos = 0;
         char s1 = largeSet.get(k1);
         char s2 = smallSet.get(k2);

         while(true) {
            if (s1 < s2) {
               k1 = advanceUntil(largeSet, k1, largeLength, s2);
               if (k1 == largeLength) {
                  break;
               }

               s1 = largeSet.get(k1);
            }

            if (s2 < s1) {
               ++k2;
               if (k2 == smallLength) {
                  break;
               }

               s2 = smallSet.get(k2);
            } else {
               buffer[pos++] = s2;
               ++k2;
               if (k2 == smallLength) {
                  break;
               }

               s2 = smallSet.get(k2);
               k1 = advanceUntil(largeSet, k1, largeLength, s2);
               if (k1 == largeLength) {
                  break;
               }

               s1 = largeSet.get(k1);
            }
         }

         return pos;
      }
   }

   protected static int unsignedUnion2by2(CharBuffer set1, int offset1, int length1, CharBuffer set2, int offset2, int length2, char[] buffer) {
      if (0 == length2) {
         set1.position(offset1);
         set1.get(buffer, 0, length1);
         return length1;
      } else if (0 == length1) {
         set2.position(offset2);
         set2.get(buffer, 0, length2);
         return length2;
      } else {
         int pos = 0;
         int k1 = offset1;
         int k2 = offset2;
         char s1 = set1.get(offset1);
         char s2 = set2.get(offset2);

         while(true) {
            while(s1 >= s2) {
               if (s1 == s2) {
                  buffer[pos++] = s1;
                  ++k1;
                  ++k2;
                  if (k1 >= length1 + offset1) {
                     set2.position(k2);
                     set2.get(buffer, pos, length2 - k2 + offset2);
                     return pos + length2 - k2 + offset2;
                  }

                  if (k2 >= length2 + offset2) {
                     set1.position(k1);
                     set1.get(buffer, pos, length1 - k1 + offset1);
                     return pos + length1 - k1 + offset1;
                  }

                  s1 = set1.get(k1);
                  s2 = set2.get(k2);
               } else {
                  buffer[pos++] = s2;
                  ++k2;
                  if (k2 >= length2 + offset2) {
                     set1.position(k1);
                     set1.get(buffer, pos, length1 - k1 + offset1);
                     return pos + length1 - k1 + offset1;
                  }

                  s2 = set2.get(k2);
               }
            }

            buffer[pos++] = s1;
            ++k1;
            if (k1 >= length1 + offset1) {
               set2.position(k2);
               set2.get(buffer, pos, length2 - k2 + offset2);
               return pos + length2 - k2 + offset2;
            }

            s1 = set1.get(k1);
         }
      }
   }

   private BufferUtil() {
   }
}
