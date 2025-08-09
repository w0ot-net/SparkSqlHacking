package org.roaringbitmap;

import java.util.Arrays;

public final class Util {
   public static final boolean USE_HYBRID_BINSEARCH = true;

   public static Container[] addOffset(Container source, char offsets) {
      if (source instanceof ArrayContainer) {
         return addOffsetArray((ArrayContainer)source, offsets);
      } else if (source instanceof BitmapContainer) {
         return addOffsetBitmap((BitmapContainer)source, offsets);
      } else if (source instanceof RunContainer) {
         return addOffsetRun((RunContainer)source, offsets);
      } else {
         throw new RuntimeException("unknown container type");
      }
   }

   private static Container[] addOffsetArray(ArrayContainer source, char offsets) {
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

      ArrayContainer low = splitIndex == 0 ? new ArrayContainer() : new ArrayContainer(splitIndex);
      ArrayContainer high = source.cardinality - splitIndex == 0 ? new ArrayContainer() : new ArrayContainer(source.cardinality - splitIndex);
      int lowCardinality = 0;

      for(int k = 0; k < splitIndex; ++k) {
         int val = source.content[k] + offsets;
         low.content[lowCardinality++] = (char)val;
      }

      low.cardinality = lowCardinality;
      int highCardinality = 0;

      for(int k = splitIndex; k < source.cardinality; ++k) {
         int val = source.content[k] + offsets;
         high.content[highCardinality++] = (char)val;
      }

      high.cardinality = highCardinality;
      return new Container[]{low, high};
   }

   private static Container[] addOffsetBitmap(BitmapContainer source, char offsets) {
      BitmapContainer c = source;
      BitmapContainer low = new BitmapContainer();
      BitmapContainer high = new BitmapContainer();
      low.cardinality = -1;
      high.cardinality = -1;
      int b = offsets >>> 6;
      int i = offsets % 64;
      if (i == 0) {
         System.arraycopy(source.bitmap, 0, low.bitmap, b, 1024 - b);
         System.arraycopy(source.bitmap, 1024 - b, high.bitmap, 0, b);
      } else {
         low.bitmap[b] = source.bitmap[0] << i;

         for(int k = 1; k < 1024 - b; ++k) {
            low.bitmap[b + k] = c.bitmap[k] << i | c.bitmap[k - 1] >>> 64 - i;
         }

         for(int k = 1024 - b; k < 1024; ++k) {
            high.bitmap[k - (1024 - b)] = c.bitmap[k] << i | c.bitmap[k - 1] >>> 64 - i;
         }

         high.bitmap[b] = c.bitmap[1023] >>> 64 - i;
      }

      return new Container[]{low.repairAfterLazy(), high.repairAfterLazy()};
   }

   private static Container[] addOffsetRun(RunContainer source, char offsets) {
      RunContainer input = source;
      RunContainer low = new RunContainer();
      RunContainer high = new RunContainer();

      for(int k = 0; k < input.nbrruns; ++k) {
         int val = input.getValue(k) + offsets;
         int finalval = val + input.getLength(k);
         if (val <= 65535) {
            if (finalval <= 65535) {
               low.smartAppend((char)val, input.getLength(k));
            } else {
               low.smartAppend((char)val, (char)('\uffff' - val));
               high.smartAppend('\u0000', (char)finalval);
            }
         } else {
            high.smartAppend((char)val, input.getLength(k));
         }
      }

      return new Container[]{low, high};
   }

   public static int advanceUntil(char[] array, int pos, int length, char min) {
      int lower = pos + 1;
      if (lower < length && array[lower] < min) {
         int spansize;
         for(spansize = 1; lower + spansize < length && array[lower + spansize] < min; spansize *= 2) {
         }

         int upper = lower + spansize < length ? lower + spansize : length - 1;
         if (array[upper] == min) {
            return upper;
         } else if (array[upper] < min) {
            return length;
         } else {
            lower += spansize >>> 1;

            while(lower + 1 != upper) {
               int mid = lower + upper >>> 1;
               char arraymid = array[mid];
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

   public static int reverseUntil(char[] array, int pos, int length, char max) {
      int lower = pos - 1;
      if (lower > 0 && array[lower] > max) {
         int spansize;
         for(spansize = 1; lower - spansize > 0 && array[lower - spansize] > max; spansize *= 2) {
         }

         int upper = lower - spansize > 0 ? lower - spansize : 0;
         if (array[upper] == max) {
            return upper;
         } else if (array[upper] > max) {
            return 0;
         } else {
            lower -= spansize >>> 1;

            while(lower - 1 != upper) {
               int mid = lower + upper >>> 1;
               char arraymid = array[mid];
               if (arraymid == max) {
                  return mid;
               }

               if (arraymid > max) {
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

   public static int iterateUntil(char[] array, int pos, int length, int min) {
      while(pos < length && array[pos] < min) {
         ++pos;
      }

      return pos;
   }

   protected static int branchyUnsignedBinarySearch(char[] array, int begin, int end, char k) {
      if (end > 0 && array[end - 1] < k) {
         return -end - 1;
      } else {
         int low = begin;
         int high = end - 1;

         while(low <= high) {
            int middleIndex = low + high >>> 1;
            int middleValue = array[middleIndex];
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

   public static void fillArrayAND(char[] container, long[] bitmap1, long[] bitmap2) {
      int pos = 0;
      if (bitmap1.length != bitmap2.length) {
         throw new IllegalArgumentException("not supported");
      } else {
         for(int k = 0; k < bitmap1.length; ++k) {
            for(long bitset = bitmap1[k] & bitmap2[k]; bitset != 0L; bitset &= bitset - 1L) {
               container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
            }
         }

      }
   }

   public static void fillArrayANDNOT(char[] container, long[] bitmap1, long[] bitmap2) {
      int pos = 0;
      if (bitmap1.length != bitmap2.length) {
         throw new IllegalArgumentException("not supported");
      } else {
         for(int k = 0; k < bitmap1.length; ++k) {
            for(long bitset = bitmap1[k] & ~bitmap2[k]; bitset != 0L; bitset &= bitset - 1L) {
               container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
            }
         }

      }
   }

   public static void fillArrayXOR(char[] container, long[] bitmap1, long[] bitmap2) {
      int pos = 0;
      if (bitmap1.length != bitmap2.length) {
         throw new IllegalArgumentException("not supported");
      } else {
         for(int k = 0; k < bitmap1.length; ++k) {
            for(long bitset = bitmap1[k] ^ bitmap2[k]; bitset != 0L; bitset &= bitset - 1L) {
               container[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
            }
         }

      }
   }

   public static void flipBitmapRange(long[] bitmap, int start, int end) {
      if (start != end) {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         bitmap[firstword] ^= ~(-1L << start);

         for(int i = firstword; i < endword; ++i) {
            bitmap[i] = ~bitmap[i];
         }

         bitmap[endword] ^= -1L >>> -end;
      }
   }

   /** @deprecated */
   @Deprecated
   public static int cardinalityInBitmapWordRange(long[] bitmap, int start, int end) {
      if (start >= end) {
         return 0;
      } else {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         int answer = 0;

         for(int i = firstword; i <= endword; ++i) {
            answer += Long.bitCount(bitmap[i]);
         }

         return answer;
      }
   }

   public static int cardinalityInBitmapRange(long[] bitmap, int start, int end) {
      if (start >= end) {
         return 0;
      } else {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         if (firstword == endword) {
            return Long.bitCount(bitmap[firstword] & -1L << start & -1L >>> -end);
         } else {
            int answer = Long.bitCount(bitmap[firstword] & -1L << start);

            for(int i = firstword + 1; i < endword; ++i) {
               answer += Long.bitCount(bitmap[i]);
            }

            answer += Long.bitCount(bitmap[endword] & -1L >>> -end);
            return answer;
         }
      }
   }

   protected static char highbits(int x) {
      return (char)(x >>> 16);
   }

   protected static char highbits(long x) {
      return (char)((int)(x >>> 16));
   }

   protected static int hybridUnsignedBinarySearch(char[] array, int begin, int end, char k) {
      if (end > 0 && array[end - 1] < k) {
         return -end - 1;
      } else {
         int low = begin;
         int high = end - 1;

         while(low + 32 <= high) {
            int middleIndex = low + high >>> 1;
            int middleValue = array[middleIndex];
            if (middleValue < k) {
               low = middleIndex + 1;
            } else {
               if (middleValue <= k) {
                  return middleIndex;
               }

               high = middleIndex - 1;
            }
         }

         int x;
         for(x = low; x <= high; ++x) {
            int val = array[x];
            if (val >= k) {
               if (val == k) {
                  return x;
               }
               break;
            }
         }

         return -(x + 1);
      }
   }

   protected static char lowbits(int x) {
      return (char)x;
   }

   protected static char lowbits(long x) {
      return (char)((int)x);
   }

   protected static int lowbitsAsInteger(int x) {
      return x & '\uffff';
   }

   protected static int lowbitsAsInteger(long x) {
      return (int)(x & 65535L);
   }

   public static int maxLowBitAsInteger() {
      return 65535;
   }

   public static void resetBitmapRange(long[] bitmap, int start, int end) {
      if (start != end) {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         if (firstword == endword) {
            bitmap[firstword] &= ~(-1L << start & -1L >>> -end);
         } else {
            bitmap[firstword] &= ~(-1L << start);

            for(int i = firstword + 1; i < endword; ++i) {
               bitmap[i] = 0L;
            }

            bitmap[endword] &= ~(-1L >>> -end);
         }
      }
   }

   public static int intersectArrayIntoBitmap(long[] bitmap, char[] array, int length) {
      int lastWordIndex = 0;
      int wordIndex = 0;
      long word = 0L;
      int cardinality = 0;

      for(int i = 0; i < length; ++i) {
         wordIndex = array[i] >>> 6;
         if (wordIndex != lastWordIndex) {
            bitmap[lastWordIndex] &= word;
            cardinality += Long.bitCount(bitmap[lastWordIndex]);
            word = 0L;
            Arrays.fill(bitmap, lastWordIndex + 1, wordIndex, 0L);
            lastWordIndex = wordIndex;
         }

         word |= 1L << array[i];
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

   public static int select(long w, int j) {
      int seen = 0;
      int part = (int)w;
      int n = Integer.bitCount(part);
      if (n <= j) {
         part = (int)(w >>> 32);
         seen += 32;
         j -= n;
      }

      part &= 65535;
      n = Integer.bitCount(part);
      if (n <= j) {
         part >>>= 16;
         seen += 16;
         j -= n;
      }

      part &= 255;
      n = Integer.bitCount(part);
      if (n <= j) {
         part >>>= 8;
         seen += 8;
         j -= n;
      }

      int var13 = part;

      int counter;
      for(counter = 0; counter < 8; ++counter) {
         j -= var13 >>> counter & 1;
         if (j < 0) {
            break;
         }
      }

      return seen + counter;
   }

   public static void setBitmapRange(long[] bitmap, int start, int end) {
      if (start != end) {
         int firstword = start / 64;
         int endword = (end - 1) / 64;
         if (firstword == endword) {
            bitmap[firstword] |= -1L << start & -1L >>> -end;
         } else {
            bitmap[firstword] |= -1L << start;

            for(int i = firstword + 1; i < endword; ++i) {
               bitmap[i] = -1L;
            }

            bitmap[endword] |= -1L >>> -end;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static int setBitmapRangeAndCardinalityChange(long[] bitmap, int start, int end) {
      int cardbefore = cardinalityInBitmapWordRange(bitmap, start, end);
      setBitmapRange(bitmap, start, end);
      int cardafter = cardinalityInBitmapWordRange(bitmap, start, end);
      return cardafter - cardbefore;
   }

   /** @deprecated */
   @Deprecated
   public static int flipBitmapRangeAndCardinalityChange(long[] bitmap, int start, int end) {
      int cardbefore = cardinalityInBitmapWordRange(bitmap, start, end);
      flipBitmapRange(bitmap, start, end);
      int cardafter = cardinalityInBitmapWordRange(bitmap, start, end);
      return cardafter - cardbefore;
   }

   /** @deprecated */
   @Deprecated
   public static int resetBitmapRangeAndCardinalityChange(long[] bitmap, int start, int end) {
      int cardbefore = cardinalityInBitmapWordRange(bitmap, start, end);
      resetBitmapRange(bitmap, start, end);
      int cardafter = cardinalityInBitmapWordRange(bitmap, start, end);
      return cardafter - cardbefore;
   }

   public static int unsignedBinarySearch(char[] array, int begin, int end, char k) {
      return hybridUnsignedBinarySearch(array, begin, end, k);
   }

   public static int unsignedDifference(char[] set1, int length1, char[] set2, int length2, char[] buffer) {
      int pos = 0;
      int k1 = 0;
      int k2 = 0;
      if (0 == length2) {
         System.arraycopy(set1, 0, buffer, 0, length1);
         return length1;
      } else if (0 == length1) {
         return 0;
      } else {
         char s1 = set1[k1];
         char s2 = set2[k2];

         while(true) {
            if (s1 < s2) {
               buffer[pos++] = s1;
               ++k1;
               if (k1 >= length1) {
                  break;
               }

               s1 = set1[k1];
            } else if (s1 == s2) {
               ++k1;
               ++k2;
               if (k1 >= length1) {
                  break;
               }

               if (k2 >= length2) {
                  System.arraycopy(set1, k1, buffer, pos, length1 - k1);
                  return pos + length1 - k1;
               }

               s1 = set1[k1];
               s2 = set2[k2];
            } else {
               ++k2;
               if (k2 >= length2) {
                  System.arraycopy(set1, k1, buffer, pos, length1 - k1);
                  return pos + length1 - k1;
               }

               s2 = set2[k2];
            }
         }

         return pos;
      }
   }

   public static int unsignedDifference(CharIterator set1, CharIterator set2, char[] buffer) {
      int pos = 0;
      if (!set2.hasNext()) {
         while(set1.hasNext()) {
            buffer[pos++] = set1.next();
         }

         return pos;
      } else if (!set1.hasNext()) {
         return 0;
      } else {
         char v1 = set1.next();
         char v2 = set2.next();

         while(true) {
            while(v1 >= v2) {
               if (v1 == v2) {
                  if (!set1.hasNext()) {
                     return pos;
                  }

                  if (!set2.hasNext()) {
                     while(set1.hasNext()) {
                        buffer[pos++] = set1.next();
                     }

                     return pos;
                  }

                  v1 = set1.next();
                  v2 = set2.next();
               } else {
                  if (!set2.hasNext()) {
                     for(buffer[pos++] = v1; set1.hasNext(); buffer[pos++] = set1.next()) {
                     }

                     return pos;
                  }

                  v2 = set2.next();
               }
            }

            buffer[pos++] = v1;
            if (!set1.hasNext()) {
               return pos;
            }

            v1 = set1.next();
         }
      }
   }

   public static int unsignedExclusiveUnion2by2(char[] set1, int length1, char[] set2, int length2, char[] buffer) {
      int pos = 0;
      int k1 = 0;
      int k2 = 0;
      if (0 == length2) {
         System.arraycopy(set1, 0, buffer, 0, length1);
         return length1;
      } else if (0 == length1) {
         System.arraycopy(set2, 0, buffer, 0, length2);
         return length2;
      } else {
         char s1 = set1[k1];
         char s2 = set2[k2];

         while(true) {
            while(s1 >= s2) {
               if (s1 == s2) {
                  ++k1;
                  ++k2;
                  if (k1 >= length1) {
                     System.arraycopy(set2, k2, buffer, pos, length2 - k2);
                     return pos + length2 - k2;
                  }

                  if (k2 >= length2) {
                     System.arraycopy(set1, k1, buffer, pos, length1 - k1);
                     return pos + length1 - k1;
                  }

                  s1 = set1[k1];
                  s2 = set2[k2];
               } else {
                  buffer[pos++] = s2;
                  ++k2;
                  if (k2 >= length2) {
                     System.arraycopy(set1, k1, buffer, pos, length1 - k1);
                     return pos + length1 - k1;
                  }

                  s2 = set2[k2];
               }
            }

            buffer[pos++] = s1;
            ++k1;
            if (k1 >= length1) {
               System.arraycopy(set2, k2, buffer, pos, length2 - k2);
               return pos + length2 - k2;
            }

            s1 = set1[k1];
         }
      }
   }

   public static int unsignedIntersect2by2(char[] set1, int length1, char[] set2, int length2, char[] buffer) {
      int THRESHOLD = 25;
      if (set1.length * 25 < set2.length) {
         return unsignedOneSidedGallopingIntersect2by2(set1, length1, set2, length2, buffer);
      } else {
         return set2.length * 25 < set1.length ? unsignedOneSidedGallopingIntersect2by2(set2, length2, set1, length1, buffer) : unsignedLocalIntersect2by2(set1, length1, set2, length2, buffer);
      }
   }

   public static boolean unsignedIntersects(char[] set1, int length1, char[] set2, int length2) {
      if (0 != length1 && 0 != length2) {
         int k1 = 0;
         int k2 = 0;
         char s1 = set1[k1];
         char s2 = set2[k2];

         while(true) {
            if (s2 < s1) {
               do {
                  ++k2;
                  if (k2 == length2) {
                     return false;
                  }

                  s2 = set2[k2];
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

               s1 = set1[k1];
               if (s1 >= s2) {
                  break;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected static int unsignedLocalIntersect2by2(char[] set1, int length1, char[] set2, int length2, char[] buffer) {
      if (0 != length1 && 0 != length2) {
         int k1 = 0;
         int k2 = 0;
         int pos = 0;
         char s1 = set1[k1];
         char s2 = set2[k2];

         while(true) {
            int v1 = s1;
            int v2 = s2;
            if (s2 < s1) {
               do {
                  ++k2;
                  if (k2 == length2) {
                     return pos;
                  }

                  s2 = set2[k2];
                  v2 = s2;
               } while(s2 < v1);
            }

            if (v1 < v2) {
               while(true) {
                  ++k1;
                  if (k1 == length1) {
                     return pos;
                  }

                  s1 = set1[k1];
                  if (s1 >= v2) {
                     break;
                  }
               }
            } else {
               buffer[pos++] = s1;
               ++k1;
               if (k1 == length1) {
                  break;
               }

               ++k2;
               if (k2 == length2) {
                  break;
               }

               s1 = set1[k1];
               s2 = set2[k2];
            }
         }

         return pos;
      } else {
         return 0;
      }
   }

   public static int unsignedLocalIntersect2by2Cardinality(char[] set1, int length1, char[] set2, int length2) {
      if (0 != length1 && 0 != length2) {
         int k1 = 0;
         int k2 = 0;
         int pos = 0;
         char s1 = set1[k1];
         char s2 = set2[k2];

         while(true) {
            int v1 = s1;
            int v2 = s2;
            if (s2 < s1) {
               do {
                  ++k2;
                  if (k2 == length2) {
                     return pos;
                  }

                  s2 = set2[k2];
                  v2 = s2;
               } while(s2 < v1);
            }

            if (v1 < v2) {
               while(true) {
                  ++k1;
                  if (k1 == length1) {
                     return pos;
                  }

                  s1 = set1[k1];
                  if (s1 >= v2) {
                     break;
                  }
               }
            } else {
               ++pos;
               ++k1;
               if (k1 == length1) {
                  break;
               }

               ++k2;
               if (k2 == length2) {
                  break;
               }

               s1 = set1[k1];
               s2 = set2[k2];
            }
         }

         return pos;
      } else {
         return 0;
      }
   }

   protected static int unsignedOneSidedGallopingIntersect2by2(char[] smallSet, int smallLength, char[] largeSet, int largeLength, char[] buffer) {
      if (0 == smallLength) {
         return 0;
      } else {
         int k1 = 0;
         int k2 = 0;
         int pos = 0;
         char s1 = largeSet[k1];
         char s2 = smallSet[k2];

         while(true) {
            if (s1 < s2) {
               k1 = advanceUntil(largeSet, k1, largeLength, s2);
               if (k1 == largeLength) {
                  break;
               }

               s1 = largeSet[k1];
            }

            if (s2 < s1) {
               ++k2;
               if (k2 == smallLength) {
                  break;
               }

               s2 = smallSet[k2];
            } else {
               buffer[pos++] = s2;
               ++k2;
               if (k2 == smallLength) {
                  break;
               }

               s2 = smallSet[k2];
               k1 = advanceUntil(largeSet, k1, largeLength, s2);
               if (k1 == largeLength) {
                  break;
               }

               s1 = largeSet[k1];
            }
         }

         return pos;
      }
   }

   public static int unsignedUnion2by2(char[] set1, int offset1, int length1, char[] set2, int offset2, int length2, char[] buffer) {
      if (0 == length2) {
         System.arraycopy(set1, offset1, buffer, 0, length1);
         return length1;
      } else if (0 == length1) {
         System.arraycopy(set2, offset2, buffer, 0, length2);
         return length2;
      } else {
         int pos = 0;
         int k1 = offset1;
         int k2 = offset2;
         char s1 = set1[offset1];
         char s2 = set2[offset2];

         while(true) {
            while(s1 >= s2) {
               if (s1 == s2) {
                  buffer[pos++] = s1;
                  ++k1;
                  ++k2;
                  if (k1 >= length1 + offset1) {
                     System.arraycopy(set2, k2, buffer, pos, length2 - k2 + offset2);
                     return pos + length2 - k2 + offset2;
                  }

                  if (k2 >= length2 + offset2) {
                     System.arraycopy(set1, k1, buffer, pos, length1 - k1 + offset1);
                     return pos + length1 - k1 + offset1;
                  }

                  s1 = set1[k1];
                  s2 = set2[k2];
               } else {
                  buffer[pos++] = s2;
                  ++k2;
                  if (k2 >= length2 + offset2) {
                     System.arraycopy(set1, k1, buffer, pos, length1 - k1 + offset1);
                     return pos + length1 - k1 + offset1;
                  }

                  s2 = set2[k2];
               }
            }

            buffer[pos++] = s1;
            ++k1;
            if (k1 >= length1 + offset1) {
               System.arraycopy(set2, k2, buffer, pos, length2 - k2 + offset2);
               return pos + length2 - k2 + offset2;
            }

            s1 = set1[k1];
         }
      }
   }

   public static long toUnsignedLong(int x) {
      return (long)x & 4294967295L;
   }

   public static void partialRadixSort(int[] data) {
      int[] low = new int[257];
      int[] high = new int[257];

      for(int value : data) {
         ++low[(value >>> 16 & 255) + 1];
         ++high[(value >>> 24) + 1];
      }

      boolean sortLow = low[1] < data.length;
      boolean sortHigh = high[1] < data.length;
      if (sortLow || sortHigh) {
         int[] copy = new int[data.length];
         if (sortLow) {
            for(int i = 1; i < low.length; ++i) {
               low[i] += low[i - 1];
            }

            for(int value : data) {
               int var10002 = value >>> 16 & 255;
               int var10004 = low[value >>> 16 & 255];
               low[var10002] = low[value >>> 16 & 255] + 1;
               copy[var10004] = value;
            }
         }

         if (sortHigh) {
            for(int i = 1; i < high.length; ++i) {
               high[i] += high[i - 1];
            }

            if (sortLow) {
               for(int value : copy) {
                  int var24 = value >>> 24;
                  int var26 = high[value >>> 24];
                  high[var24] = high[value >>> 24] + 1;
                  data[var26] = value;
               }
            } else {
               for(int value : data) {
                  int var25 = value >>> 24;
                  int var27 = high[value >>> 24];
                  high[var25] = high[value >>> 24] + 1;
                  copy[var27] = value;
               }

               System.arraycopy(copy, 0, data, 0, data.length);
            }
         } else {
            System.arraycopy(copy, 0, data, 0, data.length);
         }

      }
   }

   private Util() {
   }

   public static void fillArray(long[] bitmap, char[] array) {
      int pos = 0;
      int base = 0;

      for(int k = 0; k < bitmap.length; ++k) {
         for(long bitset = bitmap[k]; bitset != 0L; bitset &= bitset - 1L) {
            array[pos++] = (char)(base + Long.numberOfTrailingZeros(bitset));
         }

         base += 64;
      }

   }
}
