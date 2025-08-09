package org.roaringbitmap.buffer;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import org.roaringbitmap.CharIterator;
import org.roaringbitmap.Container;
import org.roaringbitmap.ContainerBatchIterator;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.RunContainer;
import org.roaringbitmap.Util;

public final class MappeableRunContainer extends MappeableContainer implements Cloneable {
   private static final int DEFAULT_INIT_SIZE = 4;
   private static final long serialVersionUID = 1L;
   protected CharBuffer valueslength;
   protected int nbrruns;

   private static int branchyBufferedUnsignedInterleavedBinarySearch(CharBuffer sb, int begin, int end, char k) {
      int low = begin;
      int high = end - 1;

      while(low <= high) {
         int middleIndex = low + high >>> 1;
         int middleValue = sb.get(2 * middleIndex);
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

   private static int branchyBufferedUnsignedInterleavedBinarySearch(ByteBuffer sb, int position, int begin, int end, char k) {
      int low = begin;
      int high = end - 1;

      while(low <= high) {
         int middleIndex = low + high >>> 1;
         int middleValue = sb.getChar(position + 2 * middleIndex * 2);
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

   private static int bufferedUnsignedInterleavedBinarySearch(CharBuffer sb, int begin, int end, char k) {
      return branchyBufferedUnsignedInterleavedBinarySearch(sb, begin, end, k);
   }

   private static int bufferedUnsignedInterleavedBinarySearch(ByteBuffer sb, int position, int begin, int end, char k) {
      return branchyBufferedUnsignedInterleavedBinarySearch(sb, position, begin, end, k);
   }

   protected static int getArraySizeInBytes(int nbrruns) {
      return 2 + 4 * nbrruns;
   }

   private static char getLength(char[] vl, int index) {
      return vl[2 * index + 1];
   }

   private static char getValue(char[] vl, int index) {
      return vl[2 * index];
   }

   protected static int serializedSizeInBytes(int numberOfRuns) {
      return 2 + 4 * numberOfRuns;
   }

   public MappeableRunContainer() {
      this(4);
   }

   public MappeableRunContainer(int capacity) {
      this.nbrruns = 0;
      this.valueslength = CharBuffer.allocate(2 * capacity);
   }

   private MappeableRunContainer(int nbrruns, CharBuffer valueslength) {
      this.nbrruns = 0;
      this.nbrruns = nbrruns;
      CharBuffer tmp = valueslength.duplicate();
      this.valueslength = CharBuffer.allocate(Math.max(2 * nbrruns, tmp.limit()));
      tmp.rewind();
      this.valueslength.put(tmp);
   }

   protected MappeableRunContainer(MappeableArrayContainer arr, int nbrRuns) {
      this.nbrruns = 0;
      this.nbrruns = nbrRuns;
      this.valueslength = CharBuffer.allocate(2 * nbrRuns);
      char[] vl = this.valueslength.array();
      if (nbrRuns != 0) {
         int prevVal = -2;
         int runLen = 0;
         int runCount = 0;
         if (BufferUtil.isBackedBySimpleArray(arr.content)) {
            char[] a = arr.content.array();

            for(int i = 0; i < arr.cardinality; ++i) {
               int curVal = a[i];
               if (curVal == prevVal + 1) {
                  ++runLen;
               } else {
                  if (runCount > 0) {
                     vl[2 * (runCount - 1) + 1] = (char)runLen;
                  }

                  vl[2 * runCount] = (char)curVal;
                  runLen = 0;
                  ++runCount;
               }

               prevVal = curVal;
            }
         } else {
            for(int i = 0; i < arr.cardinality; ++i) {
               int curVal = arr.content.get(i);
               if (curVal == prevVal + 1) {
                  ++runLen;
               } else {
                  if (runCount > 0) {
                     vl[2 * (runCount - 1) + 1] = (char)runLen;
                  }

                  vl[2 * runCount] = (char)curVal;
                  runLen = 0;
                  ++runCount;
               }

               prevVal = curVal;
            }
         }

         vl[2 * (runCount - 1) + 1] = (char)runLen;
      }
   }

   public MappeableRunContainer(int firstOfRun, int lastOfRun) {
      this.nbrruns = 0;
      this.nbrruns = 1;
      char[] vl = new char[]{(char)firstOfRun, (char)(lastOfRun - 1 - firstOfRun)};
      this.valueslength = CharBuffer.wrap(vl);
   }

   protected MappeableRunContainer(MappeableBitmapContainer bc, int nbrRuns) {
      this.nbrruns = 0;
      this.nbrruns = nbrRuns;
      this.valueslength = CharBuffer.allocate(2 * nbrRuns);
      if (!BufferUtil.isBackedBySimpleArray(this.valueslength)) {
         throw new RuntimeException("Unexpected internal error.");
      } else {
         char[] vl = this.valueslength.array();
         if (nbrRuns != 0) {
            if (bc.isArrayBacked()) {
               long[] b = bc.bitmap.array();
               int longCtr = 0;
               long curWord = b[0];
               int runCount = 0;
               int len = bc.bitmap.limit();

               while(true) {
                  while(curWord != 0L || longCtr >= len - 1) {
                     if (curWord == 0L) {
                        return;
                     }

                     int localRunStart = Long.numberOfTrailingZeros(curWord);
                     int runStart = localRunStart + 64 * longCtr;
                     long curWordWith1s = curWord | curWord - 1L;

                     for(int runEnd = 0; curWordWith1s == -1L && longCtr < len - 1; curWordWith1s = b[longCtr]) {
                        ++longCtr;
                     }

                     if (curWordWith1s == -1L) {
                        int var26 = 64 + longCtr * 64;
                        vl[2 * runCount] = (char)runStart;
                        vl[2 * runCount + 1] = (char)(var26 - runStart - 1);
                        return;
                     }

                     int localRunEnd = Long.numberOfTrailingZeros(~curWordWith1s);
                     int var25 = localRunEnd + longCtr * 64;
                     vl[2 * runCount] = (char)runStart;
                     vl[2 * runCount + 1] = (char)(var25 - runStart - 1);
                     ++runCount;
                     curWord = curWordWith1s & curWordWith1s + 1L;
                  }

                  ++longCtr;
                  curWord = b[longCtr];
               }
            } else {
               int longCtr = 0;
               long curWord = bc.bitmap.get(0);
               int runCount = 0;
               int len = bc.bitmap.limit();

               while(true) {
                  while(curWord != 0L || longCtr >= len - 1) {
                     if (curWord == 0L) {
                        return;
                     }

                     int localRunStart = Long.numberOfTrailingZeros(curWord);
                     int runStart = localRunStart + 64 * longCtr;
                     long curWordWith1s = curWord | curWord - 1L;

                     for(int runEnd = 0; curWordWith1s == -1L && longCtr < len - 1; curWordWith1s = bc.bitmap.get(longCtr)) {
                        ++longCtr;
                     }

                     if (curWordWith1s == -1L) {
                        int var23 = 64 + longCtr * 64;
                        vl[2 * runCount] = (char)runStart;
                        vl[2 * runCount + 1] = (char)(var23 - runStart - 1);
                        return;
                     }

                     int localRunEnd = Long.numberOfTrailingZeros(~curWordWith1s);
                     int var22 = localRunEnd + longCtr * 64;
                     vl[2 * runCount] = (char)runStart;
                     vl[2 * runCount + 1] = (char)(var22 - runStart - 1);
                     ++runCount;
                     curWord = curWordWith1s & curWordWith1s + 1L;
                  }

                  ++longCtr;
                  curWord = bc.bitmap.get(longCtr);
               }
            }
         }
      }
   }

   public MappeableRunContainer(RunContainer bc) {
      this.nbrruns = 0;
      this.nbrruns = bc.numberOfRuns();
      this.valueslength = bc.toCharBuffer();
   }

   public MappeableRunContainer(CharBuffer array, int numRuns) {
      this.nbrruns = 0;
      if (array.limit() < 2 * numRuns) {
         throw new RuntimeException("Mismatch between buffer and numRuns");
      } else {
         this.nbrruns = numRuns;
         this.valueslength = array;
      }
   }

   public MappeableContainer add(int begin, int end) {
      MappeableRunContainer rc = (MappeableRunContainer)this.clone();
      return rc.iadd(begin, end);
   }

   public MappeableContainer add(char k) {
      int index = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, k);
      if (index >= 0) {
         return this;
      } else {
         index = -index - 2;
         if (index >= 0) {
            int offset = k - this.getValue(index);
            int le = this.getLength(index);
            if (offset <= le) {
               return this;
            }

            if (offset == le + 1) {
               if (index + 1 < this.nbrruns && this.getValue(index + 1) == k + 1) {
                  this.setLength(index, (char)(this.getValue(index + 1) + this.getLength(index + 1) - this.getValue(index)));
                  this.recoverRoomAtIndex(index + 1);
                  return this;
               }

               this.incrementLength(index);
               return this;
            }

            if (index + 1 < this.nbrruns && this.getValue(index + 1) == k + 1) {
               this.setValue(index + 1, k);
               this.setLength(index + 1, (char)(this.getLength(index + 1) + 1));
               return this;
            }
         }

         if (index == -1 && 0 < this.nbrruns && this.getValue(0) == k + 1) {
            this.incrementLength(0);
            this.decrementValue();
            return this;
         } else {
            this.makeRoomAtIndex(index + 1);
            this.setValue(index + 1, k);
            this.setLength(index + 1, '\u0000');
            return this;
         }
      }
   }

   public boolean isEmpty() {
      return this.nbrruns == 0;
   }

   public MappeableContainer and(MappeableArrayContainer x) {
      MappeableArrayContainer ac = new MappeableArrayContainer(x.cardinality);
      if (this.nbrruns == 0) {
         return ac;
      } else {
         int rlepos = 0;
         int arraypos = 0;
         int rleval = this.getValue(rlepos);
         int rlelength = this.getLength(rlepos);

         while(arraypos < x.cardinality) {
            int arrayval;
            for(arrayval = x.content.get(arraypos); rleval + rlelength < arrayval; rlelength = this.getLength(rlepos)) {
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  return ac;
               }

               rleval = this.getValue(rlepos);
            }

            if (rleval > arrayval) {
               arraypos = BufferUtil.advanceUntil(x.content, arraypos, x.cardinality, (char)rleval);
            } else {
               ac.content.put(ac.cardinality, (char)arrayval);
               ++ac.cardinality;
               ++arraypos;
            }
         }

         return ac;
      }
   }

   public MappeableContainer and(MappeableBitmapContainer x) {
      int card = this.getCardinality();
      if (card <= 4096) {
         if (card > x.cardinality) {
            card = x.cardinality;
         }

         MappeableArrayContainer answer = new MappeableArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int runStart = this.getValue(rlepos);
            int runEnd = runStart + this.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               if (x.contains((char)runValue)) {
                  answer.content.put(answer.cardinality++, (char)runValue);
               }
            }
         }

         return answer;
      } else {
         MappeableBitmapContainer answer = x.clone();
         int start = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int end = this.getValue(rlepos);
            int prevOnes = answer.cardinalityInRange(start, end);
            BufferUtil.resetBitmapRange(answer.bitmap, start, end);
            answer.updateCardinality(prevOnes, 0);
            start = end + this.getLength(rlepos) + 1;
         }

         int ones = answer.cardinalityInRange(start, 65536);
         BufferUtil.resetBitmapRange(answer.bitmap, start, 65536);
         answer.updateCardinality(ones, 0);
         return (MappeableContainer)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
      }
   }

   public MappeableContainer and(MappeableRunContainer x) {
      MappeableRunContainer answer = new MappeableRunContainer(CharBuffer.allocate(2 * (this.nbrruns + x.nbrruns)), 0);
      char[] vl = answer.valueslength.array();
      int rlepos = 0;
      int xrlepos = 0;
      int start = this.getValue(rlepos);
      int end = start + this.getLength(rlepos) + 1;
      int xstart = x.getValue(xrlepos);
      int xend = xstart + x.getLength(xrlepos) + 1;

      while(rlepos < this.nbrruns && xrlepos < x.nbrruns) {
         if (end <= xstart) {
            ++rlepos;
            if (rlepos < this.nbrruns) {
               start = this.getValue(rlepos);
               end = start + this.getLength(rlepos) + 1;
            }
         } else if (xend <= start) {
            ++xrlepos;
            if (xrlepos < x.nbrruns) {
               xstart = x.getValue(xrlepos);
               xend = xstart + x.getLength(xrlepos) + 1;
            }
         } else {
            int lateststart = Math.max(start, xstart);
            int earliestend;
            if (end == xend) {
               earliestend = end;
               ++rlepos;
               ++xrlepos;
               if (rlepos < this.nbrruns) {
                  start = this.getValue(rlepos);
                  end = start + this.getLength(rlepos) + 1;
               }

               if (xrlepos < x.nbrruns) {
                  xstart = x.getValue(xrlepos);
                  xend = xstart + x.getLength(xrlepos) + 1;
               }
            } else if (end < xend) {
               earliestend = end;
               ++rlepos;
               if (rlepos < this.nbrruns) {
                  start = this.getValue(rlepos);
                  end = start + this.getLength(rlepos) + 1;
               }
            } else {
               earliestend = xend;
               ++xrlepos;
               if (xrlepos < x.nbrruns) {
                  xstart = x.getValue(xrlepos);
                  xend = xstart + x.getLength(xrlepos) + 1;
               }
            }

            vl[2 * answer.nbrruns] = (char)lateststart;
            vl[2 * answer.nbrruns + 1] = (char)(earliestend - lateststart - 1);
            ++answer.nbrruns;
         }
      }

      return answer;
   }

   public MappeableContainer andNot(MappeableArrayContainer x) {
      int arbitrary_threshold = 32;
      if (x.getCardinality() < 32) {
         return this.lazyandNot(x).toEfficientContainer();
      } else {
         int card = this.getCardinality();
         if (card <= 4096) {
            MappeableArrayContainer ac = new MappeableArrayContainer(card);
            ac.cardinality = Util.unsignedDifference(this.getCharIterator(), x.getCharIterator(), ac.content.array());
            return ac;
         } else {
            return this.toBitmapOrArrayContainer(card).iandNot(x);
         }
      }
   }

   public MappeableContainer andNot(MappeableBitmapContainer x) {
      int card = this.getCardinality();
      if (card <= 4096) {
         MappeableArrayContainer answer = new MappeableArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int runStart = this.getValue(rlepos);
            int runEnd = runStart + this.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               if (!x.contains((char)runValue)) {
                  answer.content.put(answer.cardinality++, (char)runValue);
               }
            }
         }

         return answer;
      } else {
         MappeableBitmapContainer answer = x.clone();
         int lastPos = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            int prevOnes = answer.cardinalityInRange(lastPos, start);
            int flippedOnes = answer.cardinalityInRange(start, end);
            BufferUtil.resetBitmapRange(answer.bitmap, lastPos, start);
            BufferUtil.flipBitmapRange(answer.bitmap, start, end);
            answer.updateCardinality(prevOnes + flippedOnes, end - start - flippedOnes);
            lastPos = end;
         }

         int ones = answer.cardinalityInRange(lastPos, 65536);
         BufferUtil.resetBitmapRange(answer.bitmap, lastPos, answer.bitmap.capacity() * 64);
         answer.updateCardinality(ones, 0);
         return (MappeableContainer)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
      }
   }

   public MappeableContainer andNot(MappeableRunContainer x) {
      MappeableRunContainer answer = new MappeableRunContainer(CharBuffer.allocate(2 * (this.nbrruns + x.nbrruns)), 0);
      char[] vl = answer.valueslength.array();
      int rlepos = 0;
      int xrlepos = 0;
      int start = this.getValue(rlepos);
      int end = start + this.getLength(rlepos) + 1;
      int xstart = x.getValue(xrlepos);
      int xend = xstart + x.getLength(xrlepos) + 1;

      while(rlepos < this.nbrruns && xrlepos < x.nbrruns) {
         if (end <= xstart) {
            vl[2 * answer.nbrruns] = (char)start;
            vl[2 * answer.nbrruns + 1] = (char)(end - start - 1);
            ++answer.nbrruns;
            ++rlepos;
            if (rlepos < this.nbrruns) {
               start = this.getValue(rlepos);
               end = start + this.getLength(rlepos) + 1;
            }
         } else if (xend <= start) {
            ++xrlepos;
            if (xrlepos < x.nbrruns) {
               xstart = x.getValue(xrlepos);
               xend = xstart + x.getLength(xrlepos) + 1;
            }
         } else {
            if (start < xstart) {
               vl[2 * answer.nbrruns] = (char)start;
               vl[2 * answer.nbrruns + 1] = (char)(xstart - start - 1);
               ++answer.nbrruns;
            }

            if (xend < end) {
               start = xend;
            } else {
               ++rlepos;
               if (rlepos < this.nbrruns) {
                  start = this.getValue(rlepos);
                  end = start + this.getLength(rlepos) + 1;
               }
            }
         }
      }

      if (rlepos < this.nbrruns) {
         vl[2 * answer.nbrruns] = (char)start;
         vl[2 * answer.nbrruns + 1] = (char)(end - start - 1);
         ++answer.nbrruns;
         ++rlepos;

         while(rlepos < this.nbrruns) {
            vl[2 * answer.nbrruns] = this.valueslength.get(2 * rlepos);
            vl[2 * answer.nbrruns + 1] = this.valueslength.get(2 * rlepos + 1);
            ++answer.nbrruns;
            ++rlepos;
         }
      }

      return answer;
   }

   private void appendValueLength(int value, int index) {
      int previousValue = this.getValue(index);
      int length = this.getLength(index);
      int offset = value - previousValue;
      if (offset > length) {
         this.setLength(index, (char)offset);
      }

   }

   private boolean canPrependValueLength(int value, int index) {
      if (index < this.nbrruns) {
         int nextValue = this.getValue(index);
         return nextValue == value + 1;
      } else {
         return false;
      }
   }

   public void clear() {
      this.nbrruns = 0;
   }

   public MappeableContainer clone() {
      return new MappeableRunContainer(this.nbrruns, this.valueslength);
   }

   private void closeValueLength(int value, int index) {
      int initialValue = this.getValue(index);
      this.setLength(index, (char)(value - initialValue));
   }

   public boolean contains(char x) {
      int index = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, x);
      if (index >= 0) {
         return true;
      } else {
         index = -index - 2;
         if (index != -1) {
            int offset = x - this.getValue(index);
            int le = this.getLength(index);
            return offset <= le;
         } else {
            return false;
         }
      }
   }

   public static boolean contains(ByteBuffer buf, int position, char x, int numRuns) {
      int index = bufferedUnsignedInterleavedBinarySearch(buf, position, 0, numRuns, x);
      if (index >= 0) {
         return true;
      } else {
         index = -index - 2;
         if (index != -1) {
            int offset = x - buf.getChar(position + index * 2 * 2);
            int le = buf.getChar(position + index * 2 * 2 + 2);
            return offset <= le;
         } else {
            return false;
         }
      }
   }

   private MappeableContainer convertToLazyBitmapIfNeeded() {
      if (this.nbrruns <= 4096) {
         return this;
      } else {
         MappeableBitmapContainer answer = new MappeableBitmapContainer();

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            BufferUtil.setBitmapRange(answer.bitmap, start, end);
         }

         answer.cardinality = -1;
         return answer;
      }
   }

   private void copyToOffset(int offset) {
      int minCapacity = 2 * (offset + this.nbrruns);
      Optional<CharBuffer> newvalueslength = computeNewCapacity(this.valueslength.capacity(), minCapacity);
      if (newvalueslength.isPresent()) {
         this.copyValuesLength(this.valueslength, 0, (CharBuffer)newvalueslength.get(), offset, this.nbrruns);
         this.valueslength = (CharBuffer)newvalueslength.get();
      } else {
         this.copyValuesLength(this.valueslength, 0, this.valueslength, offset, this.nbrruns);
      }

   }

   private static Optional computeNewCapacity(int oldCapacity, int minCapacity) {
      if (oldCapacity >= minCapacity) {
         return Optional.empty();
      } else {
         int newCapacity = oldCapacity;

         while((newCapacity = computeNewCapacity(newCapacity)) < minCapacity) {
         }

         return Optional.of(CharBuffer.allocate(newCapacity));
      }
   }

   private static int computeNewCapacity(int oldCapacity) {
      return oldCapacity == 0 ? 4 : (oldCapacity < 64 ? oldCapacity * 2 : (oldCapacity < 1024 ? oldCapacity * 3 / 2 : oldCapacity * 5 / 4));
   }

   private void copyValuesLength(CharBuffer src, int srcIndex, CharBuffer dst, int dstIndex, int length) {
      if (BufferUtil.isBackedBySimpleArray(src) && BufferUtil.isBackedBySimpleArray(dst)) {
         System.arraycopy(src.array(), 2 * srcIndex, dst.array(), 2 * dstIndex, 2 * length);
      } else {
         CharBuffer temp = CharBuffer.allocate(2 * length);

         for(int i = 0; i < 2 * length; ++i) {
            temp.put(src.get(2 * srcIndex + i));
         }

         temp.flip();

         for(int i = 0; i < 2 * length; ++i) {
            dst.put(2 * dstIndex + i, temp.get());
         }

      }
   }

   private void decrementLength(int index) {
      this.valueslength.put(2 * index + 1, (char)(this.valueslength.get(2 * index + 1) - 1));
   }

   private void decrementValue() {
      this.valueslength.put(0, (char)(this.valueslength.get(0) - 1));
   }

   private void ensureCapacity(int minNbRuns) {
      Optional<CharBuffer> nv = computeNewCapacity(this.valueslength.capacity(), 2 * minNbRuns);
      if (nv.isPresent()) {
         this.valueslength.rewind();
         ((CharBuffer)nv.get()).put(this.valueslength);
         this.valueslength = (CharBuffer)nv.get();
      }

   }

   public boolean equals(Object o) {
      if (o instanceof MappeableRunContainer) {
         return this.equals((MappeableRunContainer)o);
      } else if (o instanceof MappeableArrayContainer) {
         return this.equals((MappeableArrayContainer)o);
      } else if (o instanceof MappeableContainer) {
         if (((MappeableContainer)o).getCardinality() != this.getCardinality()) {
            return false;
         } else {
            CharIterator me = this.getCharIterator();
            CharIterator you = ((MappeableContainer)o).getCharIterator();

            while(me.hasNext()) {
               if (me.next() != you.next()) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private boolean equals(MappeableRunContainer runContainer) {
      if (runContainer.nbrruns != this.nbrruns) {
         return false;
      } else {
         for(int i = 0; i < this.nbrruns; ++i) {
            if (this.getValue(i) != runContainer.getValue(i)) {
               return false;
            }

            if (this.getLength(i) != runContainer.getLength(i)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean equals(MappeableArrayContainer arrayContainer) {
      int pos = 0;

      for(char i = 0; i < this.nbrruns; ++i) {
         char runStart = this.getValue(i);
         int length = this.getLength(i);
         if (pos + length >= arrayContainer.getCardinality()) {
            return false;
         }

         if (arrayContainer.select(pos) != runStart) {
            return false;
         }

         if (arrayContainer.select(pos + length) != (char)(runStart + length)) {
            return false;
         }

         pos += length + 1;
      }

      return pos == arrayContainer.getCardinality();
   }

   public void fillLeastSignificant16bits(int[] x, int i, int mask) {
      int pos = i;

      for(int k = 0; k < this.nbrruns; ++k) {
         int limit = this.getLength(k);
         int base = this.getValue(k);

         for(int le = 0; le <= limit; ++le) {
            x[pos++] = base + le | mask;
         }
      }

   }

   public MappeableContainer flip(char x) {
      return this.contains(x) ? this.remove(x) : this.add(x);
   }

   protected int getArraySizeInBytes() {
      return 2 + 4 * this.nbrruns;
   }

   public int getCardinality() {
      int sum = this.nbrruns;
      int limit = this.nbrruns * 2;
      if (this.isArrayBacked()) {
         char[] vl = this.valueslength.array();

         for(int k = 1; k < limit; k += 2) {
            sum += vl[k];
         }
      } else {
         for(int k = 1; k < limit; k += 2) {
            sum += this.valueslength.get(k);
         }
      }

      return sum;
   }

   public char getLength(int index) {
      return this.valueslength.get(2 * index + 1);
   }

   public CharIterator getReverseCharIterator() {
      return (CharIterator)(this.isArrayBacked() ? new RawReverseMappeableRunContainerCharIterator(this) : new ReverseMappeableRunContainerCharIterator(this));
   }

   public PeekableCharIterator getCharIterator() {
      return (PeekableCharIterator)(this.isArrayBacked() ? new RawMappeableRunContainerCharIterator(this) : new MappeableRunContainerCharIterator(this));
   }

   public ContainerBatchIterator getBatchIterator() {
      return new RunBatchIterator(this);
   }

   public int getSizeInBytes() {
      return this.nbrruns * 4 + 4;
   }

   public char getValue(int index) {
      return this.valueslength.get(2 * index);
   }

   public int hashCode() {
      int hash = 0;

      for(int k = 0; k < this.nbrruns * 2; ++k) {
         hash += 31 * hash + this.valueslength.get(k);
      }

      return hash;
   }

   public MappeableContainer iadd(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         if (begin == end - 1) {
            this.add((char)begin);
            return this;
         } else {
            int bIndex = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, (char)begin);
            int eIndex = bufferedUnsignedInterleavedBinarySearch(this.valueslength, bIndex >= 0 ? bIndex : -bIndex - 1, this.nbrruns, (char)(end - 1));
            if (bIndex >= 0 && eIndex >= 0) {
               this.mergeValuesLength(bIndex, eIndex);
               return this;
            } else if (bIndex >= 0) {
               eIndex = -eIndex - 2;
               if (this.canPrependValueLength(end - 1, eIndex + 1)) {
                  this.mergeValuesLength(bIndex, eIndex + 1);
                  return this;
               } else {
                  this.appendValueLength(end - 1, eIndex);
                  this.mergeValuesLength(bIndex, eIndex);
                  return this;
               }
            } else if (eIndex >= 0) {
               bIndex = -bIndex - 2;
               if (bIndex >= 0 && this.valueLengthContains(begin - 1, bIndex)) {
                  this.mergeValuesLength(bIndex, eIndex);
                  return this;
               } else {
                  this.prependValueLength(begin, bIndex + 1);
                  this.mergeValuesLength(bIndex + 1, eIndex);
                  return this;
               }
            } else {
               bIndex = -bIndex - 2;
               eIndex = -eIndex - 2;
               if (eIndex >= 0) {
                  if (bIndex >= 0) {
                     if (!this.valueLengthContains(begin - 1, bIndex)) {
                        if (bIndex == eIndex) {
                           if (this.canPrependValueLength(end - 1, eIndex + 1)) {
                              this.prependValueLength(begin, eIndex + 1);
                              return this;
                           }

                           this.makeRoomAtIndex(eIndex + 1);
                           this.setValue(eIndex + 1, (char)begin);
                           this.setLength(eIndex + 1, (char)(end - 1 - begin));
                           return this;
                        }

                        ++bIndex;
                        this.prependValueLength(begin, bIndex);
                     }
                  } else {
                     bIndex = 0;
                     this.prependValueLength(begin, bIndex);
                  }

                  if (this.canPrependValueLength(end - 1, eIndex + 1)) {
                     this.mergeValuesLength(bIndex, eIndex + 1);
                     return this;
                  } else {
                     this.appendValueLength(end - 1, eIndex);
                     this.mergeValuesLength(bIndex, eIndex);
                     return this;
                  }
               } else {
                  if (this.canPrependValueLength(end - 1, 0)) {
                     this.prependValueLength(begin, 0);
                  } else {
                     this.makeRoomAtIndex(0);
                     this.setValue(0, (char)begin);
                     this.setLength(0, (char)(end - 1 - begin));
                  }

                  return this;
               }
            }
         }
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public MappeableContainer iand(MappeableArrayContainer x) {
      return this.and(x);
   }

   public MappeableContainer iand(MappeableBitmapContainer x) {
      return this.and(x);
   }

   public MappeableContainer iand(MappeableRunContainer x) {
      return this.and(x);
   }

   public MappeableContainer iandNot(MappeableArrayContainer x) {
      return this.andNot(x);
   }

   public MappeableContainer iandNot(MappeableBitmapContainer x) {
      return this.andNot(x);
   }

   public MappeableContainer iandNot(MappeableRunContainer x) {
      return this.andNot(x);
   }

   MappeableContainer ilazyor(MappeableArrayContainer x) {
      return (MappeableContainer)(this.isFull() ? this : this.ilazyorToRun(x));
   }

   private MappeableContainer ilazyorToRun(MappeableArrayContainer x) {
      if (this.isFull()) {
         return full();
      } else {
         int nbrruns = this.nbrruns;
         int offset = Math.max(nbrruns, x.getCardinality());
         this.copyToOffset(offset);
         char[] vl = this.valueslength.array();
         int rlepos = 0;
         this.nbrruns = 0;
         PeekableCharIterator i = x.getCharIterator();

         while(i.hasNext() && rlepos < nbrruns) {
            if (getValue(vl, rlepos + offset) - i.peekNext() <= 0) {
               this.smartAppend(vl, getValue(vl, rlepos + offset), getLength(vl, rlepos + offset));
               ++rlepos;
            } else {
               this.smartAppend(vl, i.next());
            }
         }

         if (i.hasNext()) {
            while(i.hasNext()) {
               this.smartAppend(vl, i.next());
            }
         } else {
            while(rlepos < nbrruns) {
               this.smartAppend(vl, getValue(vl, rlepos + offset), getLength(vl, rlepos + offset));
               ++rlepos;
            }
         }

         return this.convertToLazyBitmapIfNeeded();
      }
   }

   private void increaseCapacity() {
      int newCapacity = computeNewCapacity(this.valueslength.capacity());
      CharBuffer nv = CharBuffer.allocate(newCapacity);
      this.valueslength.rewind();
      nv.put(this.valueslength);
      this.valueslength = nv;
   }

   private void incrementLength(int index) {
      this.valueslength.put(2 * index + 1, (char)(1 + this.valueslength.get(2 * index + 1)));
   }

   private void incrementValue(int index) {
      this.valueslength.put(2 * index, (char)(1 + this.valueslength.get(2 * index)));
   }

   private void initValueLength(int value, int index) {
      int initialValue = this.getValue(index);
      int length = this.getLength(index);
      this.setValue(index, (char)value);
      this.setLength(index, (char)(length - (value - initialValue)));
   }

   public MappeableContainer inot(int rangeStart, int rangeEnd) {
      if (rangeEnd <= rangeStart) {
         return this;
      } else {
         char[] vl = this.valueslength.array();
         if (vl.length <= 2 * this.nbrruns + 1) {
            boolean lastValueBeforeRange = false;
            boolean firstValuePastRange = false;
            if (rangeStart > 0) {
               lastValueBeforeRange = this.contains((char)(rangeStart - 1));
            }

            boolean firstValueInRange = this.contains((char)rangeStart);
            if (lastValueBeforeRange == firstValueInRange) {
               boolean lastValueInRange = this.contains((char)(rangeEnd - 1));
               if (rangeEnd != 65536) {
                  firstValuePastRange = this.contains((char)rangeEnd);
               }

               if (lastValueInRange == firstValuePastRange) {
                  return this.not(rangeStart, rangeEnd);
               }
            }
         }

         int myNbrRuns = this.nbrruns;
         MappeableRunContainer ans = this;
         int k = 0;

         for(this.nbrruns = 0; k < myNbrRuns && this.getValue(k) < rangeStart; ++k) {
            ++ans.nbrruns;
         }

         char bufferedValue = 0;
         char bufferedLength = 0;
         char nextValue = 0;
         char nextLength = 0;
         if (k < myNbrRuns) {
            bufferedValue = vl[2 * k];
            bufferedLength = vl[2 * k + 1];
         }

         ans.smartAppendExclusive(vl, (char)rangeStart, (char)(rangeEnd - rangeStart - 1));

         while(k < myNbrRuns) {
            if (ans.nbrruns > k + 1) {
               throw new RuntimeException("internal error in inot, writer has overtaken reader!! " + k + " " + ans.nbrruns);
            }

            if (k + 1 < myNbrRuns) {
               nextValue = vl[2 * (k + 1)];
               nextLength = vl[2 * (k + 1) + 1];
            }

            ans.smartAppendExclusive(vl, bufferedValue, bufferedLength);
            bufferedValue = nextValue;
            bufferedLength = nextLength;
            ++k;
         }

         return ans.toEfficientContainer();
      }
   }

   public boolean intersects(MappeableArrayContainer x) {
      if (this.nbrruns == 0) {
         return false;
      } else {
         int rlepos = 0;
         int arraypos = 0;
         int rleval = this.getValue(rlepos);

         for(int rlelength = this.getLength(rlepos); arraypos < x.cardinality; arraypos = BufferUtil.advanceUntil(x.content, arraypos, x.cardinality, this.getValue(rlepos))) {
            int arrayval;
            for(arrayval = x.content.get(arraypos); rleval + rlelength < arrayval; rlelength = this.getLength(rlepos)) {
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  return false;
               }

               rleval = this.getValue(rlepos);
            }

            if (rleval <= arrayval) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean intersects(MappeableBitmapContainer x) {
      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int runStart = this.getValue(rlepos);
         int runEnd = runStart + this.getLength(rlepos);
         if (x.intersects(runStart, runEnd + 1)) {
            return true;
         }
      }

      return false;
   }

   public boolean intersects(MappeableRunContainer x) {
      int rlepos = 0;
      int xrlepos = 0;
      int start = this.getValue(rlepos);
      int end = start + this.getLength(rlepos) + 1;
      int xstart = x.getValue(xrlepos);
      int xend = xstart + x.getLength(xrlepos) + 1;

      while(rlepos < this.nbrruns && xrlepos < x.nbrruns) {
         if (end <= xstart) {
            ++rlepos;
            if (rlepos < this.nbrruns) {
               start = this.getValue(rlepos);
               end = start + this.getLength(rlepos) + 1;
            }
         } else {
            if (xend > start) {
               return true;
            }

            ++xrlepos;
            if (xrlepos < x.nbrruns) {
               xstart = x.getValue(xrlepos);
               xend = xstart + x.getLength(xrlepos) + 1;
            }
         }
      }

      return false;
   }

   public MappeableContainer ior(MappeableArrayContainer x) {
      if (this.isFull()) {
         return this;
      } else {
         int nbrruns = this.nbrruns;
         int offset = Math.max(nbrruns, x.getCardinality());
         this.copyToOffset(offset);
         char[] vl = this.valueslength.array();
         int rlepos = 0;
         this.nbrruns = 0;
         PeekableCharIterator i = x.getCharIterator();

         while(i.hasNext() && rlepos < nbrruns) {
            if (getValue(vl, rlepos + offset) - i.peekNext() <= 0) {
               this.smartAppend(vl, getValue(vl, rlepos + offset), getLength(vl, rlepos + offset));
               ++rlepos;
            } else {
               this.smartAppend(vl, i.next());
            }
         }

         if (i.hasNext()) {
            while(i.hasNext()) {
               this.smartAppend(vl, i.next());
            }
         } else {
            while(rlepos < nbrruns) {
               this.smartAppend(vl, getValue(vl, rlepos + offset), getLength(vl, rlepos + offset));
               ++rlepos;
            }
         }

         return this.toEfficientContainer();
      }
   }

   public MappeableContainer ior(MappeableBitmapContainer x) {
      return (MappeableContainer)(this.isFull() ? this : this.or(x));
   }

   public MappeableContainer ior(MappeableRunContainer x) {
      if (this.isFull()) {
         return this;
      } else {
         int nbrruns = this.nbrruns;
         int xnbrruns = x.nbrruns;
         int offset = Math.max(nbrruns, xnbrruns);
         this.copyToOffset(offset);
         this.nbrruns = 0;
         int rlepos = 0;
         int xrlepos = 0;
         char[] vl = this.valueslength.array();

         while(rlepos < nbrruns && xrlepos < xnbrruns) {
            char value = getValue(vl, offset + rlepos);
            char xvalue = x.getValue(xrlepos);
            char length = getLength(vl, offset + rlepos);
            char xlength = x.getLength(xrlepos);
            if (value - xvalue <= 0) {
               this.smartAppend(vl, value, length);
               ++rlepos;
            } else {
               this.smartAppend(vl, xvalue, xlength);
               ++xrlepos;
            }
         }

         while(rlepos < nbrruns) {
            this.smartAppend(vl, getValue(vl, offset + rlepos), getLength(vl, offset + rlepos));
            ++rlepos;
         }

         while(xrlepos < xnbrruns) {
            this.smartAppend(vl, x.getValue(xrlepos), x.getLength(xrlepos));
            ++xrlepos;
         }

         return this.toEfficientContainer();
      }
   }

   public MappeableContainer iremove(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         if (begin == end - 1) {
            this.remove((char)begin);
            return this;
         } else {
            int bIndex = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, (char)begin);
            int eIndex = bufferedUnsignedInterleavedBinarySearch(this.valueslength, bIndex >= 0 ? bIndex : -bIndex - 1, this.nbrruns, (char)(end - 1));
            if (bIndex >= 0) {
               if (eIndex < 0) {
                  eIndex = -eIndex - 2;
               }

               if (this.valueLengthContains(end, eIndex)) {
                  this.initValueLength(end, eIndex);
                  this.recoverRoomsInRange(bIndex - 1, eIndex - 1);
               } else {
                  this.recoverRoomsInRange(bIndex - 1, eIndex);
               }
            } else if (eIndex >= 0) {
               bIndex = -bIndex - 2;
               if (bIndex >= 0 && this.valueLengthContains(begin, bIndex)) {
                  this.closeValueLength(begin - 1, bIndex);
               }

               if (this.getLength(eIndex) == 0) {
                  this.recoverRoomsInRange(eIndex - 1, eIndex);
               } else {
                  this.incrementValue(eIndex);
                  this.decrementLength(eIndex);
               }

               this.recoverRoomsInRange(bIndex, eIndex - 1);
            } else {
               bIndex = -bIndex - 2;
               eIndex = -eIndex - 2;
               if (eIndex >= 0) {
                  if (bIndex >= 0) {
                     if (bIndex == eIndex) {
                        if (this.valueLengthContains(begin, bIndex)) {
                           if (this.valueLengthContains(end, eIndex)) {
                              this.makeRoomAtIndex(bIndex);
                              this.closeValueLength(begin - 1, bIndex);
                              this.initValueLength(end, bIndex + 1);
                              return this;
                           }

                           this.closeValueLength(begin - 1, bIndex);
                        }
                     } else {
                        if (this.valueLengthContains(begin, bIndex)) {
                           this.closeValueLength(begin - 1, bIndex);
                        }

                        if (this.valueLengthContains(end, eIndex)) {
                           this.initValueLength(end, eIndex);
                           --eIndex;
                        }

                        this.recoverRoomsInRange(bIndex, eIndex);
                     }
                  } else if (this.valueLengthContains(end, eIndex)) {
                     this.initValueLength(end, eIndex);
                     this.recoverRoomsInRange(bIndex, eIndex - 1);
                  } else {
                     this.recoverRoomsInRange(bIndex, eIndex);
                  }
               }
            }

            return this;
         }
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   protected boolean isArrayBacked() {
      return BufferUtil.isBackedBySimpleArray(this.valueslength);
   }

   public boolean isFull() {
      return this.nbrruns == 1 && this.getValue(0) == 0 && this.getLength(0) == '\uffff';
   }

   public void orInto(long[] bits) {
      for(int r = 0; r < this.numberOfRuns(); ++r) {
         int start = this.valueslength.get(r << 1);
         int length = this.valueslength.get((r << 1) + 1);
         Util.setBitmapRange(bits, start, start + length + 1);
      }

   }

   public void andInto(long[] bits) {
      int prev = 0;

      for(int r = 0; r < this.numberOfRuns(); ++r) {
         int start = this.valueslength.get(r << 1);
         int length = this.valueslength.get((r << 1) + 1);
         Util.resetBitmapRange(bits, prev, start);
         prev = start + length + 1;
      }

      Util.resetBitmapRange(bits, prev, 65536);
   }

   public void removeFrom(long[] bits) {
      for(int r = 0; r < this.numberOfRuns(); ++r) {
         int start = this.valueslength.get(r << 1);
         int length = this.valueslength.get((r << 1) + 1);
         Util.resetBitmapRange(bits, start, start + length + 1);
      }

   }

   public static MappeableRunContainer full() {
      return new MappeableRunContainer(0, 65536);
   }

   public Iterator iterator() {
      final CharIterator i = this.getCharIterator();
      return new Iterator() {
         public boolean hasNext() {
            return i.hasNext();
         }

         public Character next() {
            return i.next();
         }

         public void remove() {
            i.remove();
         }
      };
   }

   public MappeableContainer ixor(MappeableArrayContainer x) {
      return this.xor(x);
   }

   public MappeableContainer ixor(MappeableBitmapContainer x) {
      return this.xor(x);
   }

   public MappeableContainer ixor(MappeableRunContainer x) {
      return this.xor(x);
   }

   private MappeableRunContainer lazyandNot(MappeableArrayContainer x) {
      if (x.isEmpty()) {
         return this;
      } else {
         MappeableRunContainer answer = new MappeableRunContainer(CharBuffer.allocate(2 * (this.nbrruns + x.cardinality)), 0);
         char[] vl = answer.valueslength.array();
         int rlepos = 0;
         int xrlepos = 0;
         int start = this.getValue(rlepos);
         int end = start + this.getLength(rlepos) + 1;
         int xstart = x.content.get(xrlepos);

         while(rlepos < this.nbrruns && xrlepos < x.cardinality) {
            if (end <= xstart) {
               vl[2 * answer.nbrruns] = (char)start;
               vl[2 * answer.nbrruns + 1] = (char)(end - start - 1);
               ++answer.nbrruns;
               ++rlepos;
               if (rlepos < this.nbrruns) {
                  start = this.getValue(rlepos);
                  end = start + this.getLength(rlepos) + 1;
               }
            } else if (xstart + 1 <= start) {
               ++xrlepos;
               if (xrlepos < x.cardinality) {
                  xstart = x.content.get(xrlepos);
               }
            } else {
               if (start < xstart) {
                  vl[2 * answer.nbrruns] = (char)start;
                  vl[2 * answer.nbrruns + 1] = (char)(xstart - start - 1);
                  ++answer.nbrruns;
               }

               if (xstart + 1 < end) {
                  start = xstart + 1;
               } else {
                  ++rlepos;
                  if (rlepos < this.nbrruns) {
                     start = this.getValue(rlepos);
                     end = start + this.getLength(rlepos) + 1;
                  }
               }
            }
         }

         if (rlepos < this.nbrruns) {
            vl[2 * answer.nbrruns] = (char)start;
            vl[2 * answer.nbrruns + 1] = (char)(end - start - 1);
            ++answer.nbrruns;
            ++rlepos;

            while(rlepos < this.nbrruns) {
               vl[2 * answer.nbrruns] = this.valueslength.get(2 * rlepos);
               vl[2 * answer.nbrruns + 1] = this.valueslength.get(2 * rlepos + 1);
               ++answer.nbrruns;
               ++rlepos;
            }
         }

         return answer;
      }
   }

   protected MappeableContainer lazyor(MappeableArrayContainer x) {
      return this.lazyorToRun(x);
   }

   private MappeableContainer lazyorToRun(MappeableArrayContainer x) {
      if (this.isFull()) {
         return full();
      } else {
         MappeableRunContainer answer = new MappeableRunContainer(CharBuffer.allocate(2 * (this.nbrruns + x.getCardinality())), 0);
         char[] vl = answer.valueslength.array();
         int rlepos = 0;
         PeekableCharIterator i = x.getCharIterator();

         while(rlepos < this.nbrruns && i.hasNext()) {
            if (this.getValue(rlepos) - i.peekNext() <= 0) {
               answer.smartAppend(vl, this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
            } else {
               answer.smartAppend(vl, i.next());
            }
         }

         if (i.hasNext()) {
            while(i.hasNext()) {
               answer.smartAppend(vl, i.next());
            }
         } else {
            while(rlepos < this.nbrruns) {
               answer.smartAppend(vl, this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
            }
         }

         return (MappeableContainer)(answer.isFull() ? full() : answer.convertToLazyBitmapIfNeeded());
      }
   }

   private MappeableContainer lazyxor(MappeableArrayContainer x) {
      if (x.isEmpty()) {
         return this;
      } else if (this.nbrruns == 0) {
         return x;
      } else {
         MappeableRunContainer answer = new MappeableRunContainer(CharBuffer.allocate(2 * (this.nbrruns + x.getCardinality())), 0);
         char[] vl = answer.valueslength.array();
         int rlepos = 0;
         CharIterator i = x.getCharIterator();
         char cv = i.next();

         while(true) {
            if (this.getValue(rlepos) - cv < 0) {
               answer.smartAppendExclusive(vl, this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  answer.smartAppendExclusive(vl, cv);

                  while(i.hasNext()) {
                     answer.smartAppendExclusive(vl, i.next());
                  }

                  return answer;
               }
            } else {
               answer.smartAppendExclusive(vl, cv);
               if (!i.hasNext()) {
                  while(rlepos < this.nbrruns) {
                     answer.smartAppendExclusive(vl, this.getValue(rlepos), this.getLength(rlepos));
                     ++rlepos;
                  }

                  return answer;
               }

               cv = i.next();
            }
         }
      }
   }

   public MappeableContainer limit(int maxcardinality) {
      if (maxcardinality >= this.getCardinality()) {
         return this.clone();
      } else {
         int cardinality = 0;

         int r;
         for(r = 0; r < this.nbrruns; ++r) {
            cardinality += this.getLength(r) + 1;
            if (maxcardinality <= cardinality) {
               break;
            }
         }

         CharBuffer newBuf;
         if (BufferUtil.isBackedBySimpleArray(this.valueslength)) {
            char[] newArray = Arrays.copyOf(this.valueslength.array(), 2 * (r + 1));
            newBuf = CharBuffer.wrap(newArray);
         } else {
            newBuf = CharBuffer.allocate(2 * (r + 1));

            for(int i = 0; i < 2 * (r + 1); ++i) {
               newBuf.put(this.valueslength.get(i));
            }
         }

         MappeableRunContainer rc = new MappeableRunContainer(newBuf, r + 1);
         rc.setLength(r, (char)(rc.getLength(r) - cardinality + maxcardinality));
         return rc;
      }
   }

   private void makeRoomAtIndex(int index) {
      if (2 * (this.nbrruns + 1) > this.valueslength.capacity()) {
         this.increaseCapacity();
      }

      this.copyValuesLength(this.valueslength, index, this.valueslength, index + 1, this.nbrruns - index);
      ++this.nbrruns;
   }

   private void mergeValuesLength(int begin, int end) {
      if (begin < end) {
         int bValue = this.getValue(begin);
         int eValue = this.getValue(end);
         int eLength = this.getLength(end);
         int newLength = eValue - bValue + eLength;
         this.setLength(begin, (char)newLength);
         this.recoverRoomsInRange(begin, end);
      }

   }

   public MappeableContainer not(int rangeStart, int rangeEnd) {
      if (rangeEnd <= rangeStart) {
         return this.clone();
      } else {
         MappeableRunContainer ans = new MappeableRunContainer(this.nbrruns + 1);
         if (!ans.isArrayBacked()) {
            throw new RuntimeException("internal bug");
         } else {
            char[] vl = ans.valueslength.array();
            int k = 0;
            if (this.isArrayBacked()) {
               char[] myVl;
               for(myVl = this.valueslength.array(); k < this.nbrruns && getValue(myVl, k) < rangeStart; ++k) {
                  vl[2 * k] = myVl[2 * k];
                  vl[2 * k + 1] = myVl[2 * k + 1];
                  ++ans.nbrruns;
               }

               ans.smartAppendExclusive(vl, (char)rangeStart, (char)(rangeEnd - rangeStart - 1));

               while(k < this.nbrruns) {
                  ans.smartAppendExclusive(vl, getValue(myVl, k), getLength(myVl, k));
                  ++k;
               }
            } else {
               while(k < this.nbrruns && this.getValue(k) < rangeStart) {
                  vl[2 * k] = this.getValue(k);
                  vl[2 * k + 1] = this.getLength(k);
                  ++ans.nbrruns;
                  ++k;
               }

               ans.smartAppendExclusive(vl, (char)rangeStart, (char)(rangeEnd - rangeStart - 1));

               while(k < this.nbrruns) {
                  ans.smartAppendExclusive(vl, this.getValue(k), this.getLength(k));
                  ++k;
               }
            }

            return ans.toEfficientContainer();
         }
      }
   }

   public int numberOfRuns() {
      return this.nbrruns;
   }

   public MappeableContainer or(MappeableArrayContainer x) {
      return this.lazyorToRun(x).repairAfterLazy();
   }

   public MappeableContainer or(MappeableBitmapContainer x) {
      if (this.isFull()) {
         return full();
      } else {
         MappeableBitmapContainer answer = x.clone();

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            int prevOnesInRange = answer.cardinalityInRange(start, end);
            BufferUtil.setBitmapRange(answer.bitmap, start, end);
            answer.updateCardinality(prevOnesInRange, end - start);
         }

         return (MappeableContainer)(answer.isFull() ? full() : answer);
      }
   }

   public MappeableContainer or(MappeableRunContainer x) {
      if (!this.isFull() && !x.isFull()) {
         MappeableRunContainer answer = new MappeableRunContainer(CharBuffer.allocate(2 * (this.nbrruns + x.nbrruns)), 0);
         char[] vl = answer.valueslength.array();
         int rlepos = 0;
         int xrlepos = 0;

         while(rlepos < this.nbrruns && xrlepos < x.nbrruns) {
            if (this.getValue(rlepos) - x.getValue(xrlepos) <= 0) {
               answer.smartAppend(vl, this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
            } else {
               answer.smartAppend(vl, x.getValue(xrlepos), x.getLength(xrlepos));
               ++xrlepos;
            }
         }

         while(xrlepos < x.nbrruns) {
            answer.smartAppend(vl, x.getValue(xrlepos), x.getLength(xrlepos));
            ++xrlepos;
         }

         while(rlepos < this.nbrruns) {
            answer.smartAppend(vl, this.getValue(rlepos), this.getLength(rlepos));
            ++rlepos;
         }

         return (MappeableContainer)(answer.isFull() ? full() : answer.toEfficientContainer());
      } else {
         return full();
      }
   }

   private void prependValueLength(int value, int index) {
      int initialValue = this.getValue(index);
      int length = this.getLength(index);
      this.setValue(index, (char)value);
      this.setLength(index, (char)(initialValue - value + length));
   }

   public int rank(char lowbits) {
      int answer = 0;

      for(int k = 0; k < this.nbrruns; ++k) {
         int value = this.getValue(k);
         int length = this.getLength(k);
         if (lowbits < value) {
            return answer;
         }

         if (value + length + 1 > lowbits) {
            return answer + lowbits - value + 1;
         }

         answer += length + 1;
      }

      return answer;
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.nbrruns = Character.reverseBytes(in.readChar());
      if (this.valueslength.capacity() < 2 * this.nbrruns) {
         this.valueslength = CharBuffer.allocate(2 * this.nbrruns);
      }

      for(int k = 0; k < 2 * this.nbrruns; ++k) {
         this.valueslength.put(k, Character.reverseBytes(in.readChar()));
      }

   }

   private void recoverRoomAtIndex(int index) {
      this.copyValuesLength(this.valueslength, index + 1, this.valueslength, index, this.nbrruns - index - 1);
      --this.nbrruns;
   }

   private void recoverRoomsInRange(int begin, int end) {
      if (end + 1 < this.nbrruns) {
         this.copyValuesLength(this.valueslength, end + 1, this.valueslength, begin + 1, this.nbrruns - 1 - end);
      }

      this.nbrruns -= end - begin;
   }

   public MappeableContainer remove(int begin, int end) {
      MappeableRunContainer rc = (MappeableRunContainer)this.clone();
      return rc.iremove(begin, end);
   }

   public MappeableContainer remove(char x) {
      int index = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, x);
      if (index >= 0) {
         if (this.getLength(index) == 0) {
            this.recoverRoomAtIndex(index);
         } else {
            this.incrementValue(index);
            this.decrementLength(index);
         }

         return this;
      } else {
         index = -index - 2;
         if (index >= 0) {
            int offset = x - this.getValue(index);
            int le = this.getLength(index);
            if (offset < le) {
               this.setLength(index, (char)(offset - 1));
               int newvalue = x + 1;
               int newlength = le - offset - 1;
               this.makeRoomAtIndex(index + 1);
               this.setValue(index + 1, (char)newvalue);
               this.setLength(index + 1, (char)newlength);
               return this;
            }

            if (offset == le) {
               this.decrementLength(index);
            }
         }

         return this;
      }
   }

   public MappeableContainer repairAfterLazy() {
      return this.toEfficientContainer();
   }

   public MappeableContainer runOptimize() {
      return this.toEfficientContainer();
   }

   public char select(int j) {
      int offset = 0;

      for(int k = 0; k < this.nbrruns; ++k) {
         int nextOffset = offset + this.getLength(k) + 1;
         if (nextOffset > j) {
            return (char)(this.getValue(k) + (j - offset));
         }

         offset = nextOffset;
      }

      throw new IllegalArgumentException("Cannot select " + j + " since cardinality is " + this.getCardinality());
   }

   public int serializedSizeInBytes() {
      return serializedSizeInBytes(this.nbrruns);
   }

   private void setLength(int index, char v) {
      this.setLength(this.valueslength, index, v);
   }

   private void setLength(CharBuffer valueslength, int index, char v) {
      valueslength.put(2 * index + 1, v);
   }

   private void setValue(int index, char v) {
      this.setValue(this.valueslength, index, v);
   }

   private void setValue(CharBuffer valueslength, int index, char v) {
      valueslength.put(2 * index, v);
   }

   private void smartAppend(char[] vl, char val) {
      int oldend;
      if (this.nbrruns != 0 && val <= (oldend = vl[2 * (this.nbrruns - 1)] + vl[2 * (this.nbrruns - 1) + 1]) + 1) {
         if (val == (char)(oldend + 1)) {
            ++vl[2 * (this.nbrruns - 1) + 1];
         }

      } else {
         vl[2 * this.nbrruns] = val;
         vl[2 * this.nbrruns + 1] = 0;
         ++this.nbrruns;
      }
   }

   void smartAppend(char start, char length) {
      int oldend;
      if (this.nbrruns != 0 && start <= (oldend = this.getValue(this.nbrruns - 1) + this.getLength(this.nbrruns - 1)) + 1) {
         int newend = start + length + 1;
         if (newend > oldend) {
            this.setLength(this.nbrruns - 1, (char)(newend - 1 - this.getValue(this.nbrruns - 1)));
         }

      } else {
         this.ensureCapacity(this.nbrruns + 1);
         this.valueslength.put(2 * this.nbrruns, start);
         this.valueslength.put(2 * this.nbrruns + 1, length);
         ++this.nbrruns;
      }
   }

   private void smartAppend(char[] vl, char start, char length) {
      int oldend;
      if (this.nbrruns != 0 && start <= (oldend = vl[2 * (this.nbrruns - 1)] + vl[2 * (this.nbrruns - 1) + 1]) + 1) {
         int newend = start + length + 1;
         if (newend > oldend) {
            vl[2 * (this.nbrruns - 1) + 1] = (char)(newend - 1 - vl[2 * (this.nbrruns - 1)]);
         }

      } else {
         vl[2 * this.nbrruns] = start;
         vl[2 * this.nbrruns + 1] = length;
         ++this.nbrruns;
      }
   }

   private void smartAppendExclusive(char[] vl, char val) {
      int oldend;
      if (this.nbrruns != 0 && val <= (oldend = this.getValue(this.nbrruns - 1) + this.getLength(this.nbrruns - 1) + 1)) {
         if (oldend == val) {
            ++vl[2 * (this.nbrruns - 1) + 1];
         } else {
            int newend = val + 1;
            if (val == this.getValue(this.nbrruns - 1)) {
               if (newend != oldend) {
                  this.setValue(this.nbrruns - 1, (char)newend);
                  this.setLength(this.nbrruns - 1, (char)(oldend - newend - 1));
               } else {
                  --this.nbrruns;
               }
            } else {
               this.setLength(this.nbrruns - 1, (char)(val - this.getValue(this.nbrruns - 1) - 1));
               if (newend < oldend) {
                  this.setValue(this.nbrruns, (char)newend);
                  this.setLength(this.nbrruns, (char)(oldend - newend - 1));
                  ++this.nbrruns;
               }

            }
         }
      } else {
         vl[2 * this.nbrruns] = val;
         vl[2 * this.nbrruns + 1] = 0;
         ++this.nbrruns;
      }
   }

   private void smartAppendExclusive(char[] vl, char start, char length) {
      int oldend;
      if (this.nbrruns != 0 && start <= (oldend = this.getValue(this.nbrruns - 1) + this.getLength(this.nbrruns - 1) + 1)) {
         if (oldend == start) {
            int var10001 = 2 * (this.nbrruns - 1) + 1;
            vl[var10001] = (char)(vl[var10001] + length + 1);
         } else {
            int newend = start + length + 1;
            if (start == this.getValue(this.nbrruns - 1)) {
               if (newend < oldend) {
                  this.setValue(this.nbrruns - 1, (char)newend);
                  this.setLength(this.nbrruns - 1, (char)(oldend - newend - 1));
               } else if (newend > oldend) {
                  this.setValue(this.nbrruns - 1, (char)oldend);
                  this.setLength(this.nbrruns - 1, (char)(newend - oldend - 1));
               } else {
                  --this.nbrruns;
               }
            } else {
               this.setLength(this.nbrruns - 1, (char)(start - this.getValue(this.nbrruns - 1) - 1));
               if (newend < oldend) {
                  this.setValue(this.nbrruns, (char)newend);
                  this.setLength(this.nbrruns, (char)(oldend - newend - 1));
                  ++this.nbrruns;
               } else if (newend > oldend) {
                  this.setValue(this.nbrruns, (char)oldend);
                  this.setLength(this.nbrruns, (char)(newend - oldend - 1));
                  ++this.nbrruns;
               }

            }
         }
      } else {
         vl[2 * this.nbrruns] = start;
         vl[2 * this.nbrruns + 1] = length;
         ++this.nbrruns;
      }
   }

   MappeableContainer toBitmapOrArrayContainer(int card) {
      if (card <= 4096) {
         MappeableArrayContainer answer = new MappeableArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int runStart = this.getValue(rlepos);
            int runEnd = runStart + this.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               answer.content.put(answer.cardinality++, (char)runValue);
            }
         }

         return answer;
      } else {
         MappeableBitmapContainer answer = new MappeableBitmapContainer();

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            BufferUtil.setBitmapRange(answer.bitmap, start, end);
         }

         answer.cardinality = card;
         return answer;
      }
   }

   public Container toContainer() {
      return new RunContainer(this);
   }

   private MappeableContainer toEfficientContainer() {
      int sizeAsRunContainer = serializedSizeInBytes(this.nbrruns);
      int sizeAsBitmapContainer = MappeableBitmapContainer.serializedSizeInBytes(0);
      int card = this.getCardinality();
      int sizeAsArrayContainer = MappeableArrayContainer.serializedSizeInBytes(card);
      if (sizeAsRunContainer <= Math.min(sizeAsBitmapContainer, sizeAsArrayContainer)) {
         return this;
      } else if (card <= 4096) {
         MappeableArrayContainer answer = new MappeableArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int runStart = this.getValue(rlepos);
            int runEnd = runStart + this.getLength(rlepos);
            if (BufferUtil.isBackedBySimpleArray(answer.content)) {
               char[] ba = answer.content.array();

               for(int runValue = runStart; runValue <= runEnd; ++runValue) {
                  ba[answer.cardinality++] = (char)runValue;
               }
            } else {
               for(int runValue = runStart; runValue <= runEnd; ++runValue) {
                  answer.content.put(answer.cardinality++, (char)runValue);
               }
            }
         }

         return answer;
      } else {
         MappeableBitmapContainer answer = new MappeableBitmapContainer();

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            BufferUtil.setBitmapRange(answer.bitmap, start, end);
         }

         answer.cardinality = card;
         return answer;
      }
   }

   public char[] toCharArray() {
      char[] answer = new char[2 * this.nbrruns];
      this.valueslength.rewind();
      this.valueslength.get(answer);
      return answer;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("[]".length() + "-123456789,".length() * this.nbrruns);

      for(int k = 0; k < this.nbrruns; ++k) {
         sb.append('[');
         sb.append(this.getValue(k));
         sb.append(',');
         sb.append(this.getValue(k) + this.getLength(k));
         sb.append(']');
      }

      return sb.toString();
   }

   public void trim() {
      if (this.valueslength.limit() != 2 * this.nbrruns) {
         if (BufferUtil.isBackedBySimpleArray(this.valueslength)) {
            this.valueslength = CharBuffer.wrap(Arrays.copyOf(this.valueslength.array(), 2 * this.nbrruns));
         } else {
            CharBuffer co = CharBuffer.allocate(2 * this.nbrruns);
            char[] a = co.array();

            for(int k = 0; k < 2 * this.nbrruns; ++k) {
               a[k] = this.valueslength.get(k);
            }

            this.valueslength = co;
         }

      }
   }

   private boolean valueLengthContains(int value, int index) {
      int initialValue = this.getValue(index);
      int length = this.getLength(index);
      return value <= initialValue + length;
   }

   protected void writeArray(DataOutput out) throws IOException {
      out.writeShort(Character.reverseBytes((char)this.nbrruns));

      for(int k = 0; k < 2 * this.nbrruns; ++k) {
         out.writeShort(Character.reverseBytes(this.valueslength.get(k)));
      }

   }

   protected void writeArray(ByteBuffer buffer) {
      assert buffer.order() == ByteOrder.LITTLE_ENDIAN;

      CharBuffer source = this.valueslength.duplicate();
      source.position(0);
      source.limit(this.nbrruns * 2);
      CharBuffer target = buffer.asCharBuffer();
      target.put((char)this.nbrruns);
      target.put(source);
      int bytesWritten = (this.nbrruns * 2 + 1) * 2;
      buffer.position(buffer.position() + bytesWritten);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeShort(Character.reverseBytes((char)this.nbrruns));

      for(int k = 0; k < 2 * this.nbrruns; ++k) {
         out.writeShort(Character.reverseBytes(this.valueslength.get(k)));
      }

   }

   public MappeableContainer xor(MappeableArrayContainer x) {
      int arbitrary_threshold = 32;
      if (x.getCardinality() < 32) {
         return this.lazyxor(x).repairAfterLazy();
      } else {
         int card = this.getCardinality();
         return card <= 4096 ? x.xor((CharIterator)this.getCharIterator()) : this.toBitmapOrArrayContainer(card).ixor(x);
      }
   }

   public MappeableContainer xor(MappeableBitmapContainer x) {
      MappeableBitmapContainer answer = x.clone();

      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int start = this.getValue(rlepos);
         int end = start + this.getLength(rlepos) + 1;
         int prevOnes = answer.cardinalityInRange(start, end);
         BufferUtil.flipBitmapRange(answer.bitmap, start, end);
         answer.updateCardinality(prevOnes, end - start - prevOnes);
      }

      return (MappeableContainer)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
   }

   public MappeableContainer xor(MappeableRunContainer x) {
      if (x.nbrruns == 0) {
         return this.clone();
      } else if (this.nbrruns == 0) {
         return x.clone();
      } else {
         MappeableRunContainer answer = new MappeableRunContainer(CharBuffer.allocate(2 * (this.nbrruns + x.nbrruns)), 0);
         char[] vl = answer.valueslength.array();
         int rlepos = 0;
         int xrlepos = 0;

         while(true) {
            if (this.getValue(rlepos) - x.getValue(xrlepos) < 0) {
               answer.smartAppendExclusive(vl, this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  while(xrlepos < x.nbrruns) {
                     answer.smartAppendExclusive(vl, x.getValue(xrlepos), x.getLength(xrlepos));
                     ++xrlepos;
                  }

                  return answer.toEfficientContainer();
               }
            } else {
               answer.smartAppendExclusive(vl, x.getValue(xrlepos), x.getLength(xrlepos));
               ++xrlepos;
               if (xrlepos == x.nbrruns) {
                  while(rlepos < this.nbrruns) {
                     answer.smartAppendExclusive(vl, this.getValue(rlepos), this.getLength(rlepos));
                     ++rlepos;
                  }

                  return answer.toEfficientContainer();
               }
            }
         }
      }
   }

   public void forEach(char msb, IntConsumer ic) {
      int high = msb << 16;

      for(int k = 0; k < this.nbrruns; ++k) {
         int base = this.getValue(k) & '\uffff' | high;
         int le = this.getLength(k) & '\uffff';

         for(int l = base; l - le <= base; ++l) {
            ic.accept(l);
         }
      }

   }

   public int andCardinality(MappeableArrayContainer x) {
      if (this.nbrruns == 0) {
         return 0;
      } else {
         int rlepos = 0;
         int arraypos = 0;
         int andCardinality = 0;
         int rleval = this.getValue(rlepos);
         int rlelength = this.getLength(rlepos);

         while(arraypos < x.cardinality) {
            int arrayval;
            for(arrayval = x.content.get(arraypos); rleval + rlelength < arrayval; rlelength = this.getLength(rlepos)) {
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  return andCardinality;
               }

               rleval = this.getValue(rlepos);
            }

            if (rleval > arrayval) {
               arraypos = BufferUtil.advanceUntil(x.content, arraypos, x.cardinality, this.getValue(rlepos));
            } else {
               ++andCardinality;
               ++arraypos;
            }
         }

         return andCardinality;
      }
   }

   public int andCardinality(MappeableBitmapContainer x) {
      int cardinality = 0;

      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int runStart = this.getValue(rlepos);
         int runEnd = runStart + this.getLength(rlepos);
         cardinality += x.cardinalityInRange(runStart, runEnd + 1);
      }

      return cardinality;
   }

   public int andCardinality(MappeableRunContainer x) {
      int cardinality = 0;
      int rlepos = 0;
      int xrlepos = 0;
      int start = this.getValue(rlepos);
      int end = start + this.getLength(rlepos) + 1;
      int xstart = x.getValue(xrlepos);
      int xend = xstart + x.getLength(xrlepos) + 1;

      while(rlepos < this.nbrruns && xrlepos < x.nbrruns) {
         if (end <= xstart) {
            ++rlepos;
            if (rlepos < this.nbrruns) {
               start = this.getValue(rlepos);
               end = start + this.getLength(rlepos) + 1;
            }
         } else if (xend <= start) {
            ++xrlepos;
            if (xrlepos < x.nbrruns) {
               xstart = x.getValue(xrlepos);
               xend = xstart + x.getLength(xrlepos) + 1;
            }
         } else {
            int lateststart = Math.max(start, xstart);
            int earliestend;
            if (end == xend) {
               earliestend = end;
               ++rlepos;
               ++xrlepos;
               if (rlepos < this.nbrruns) {
                  start = this.getValue(rlepos);
                  end = start + this.getLength(rlepos) + 1;
               }

               if (xrlepos < x.nbrruns) {
                  xstart = x.getValue(xrlepos);
                  xend = xstart + x.getLength(xrlepos) + 1;
               }
            } else if (end < xend) {
               earliestend = end;
               ++rlepos;
               if (rlepos < this.nbrruns) {
                  start = this.getValue(rlepos);
                  end = start + this.getLength(rlepos) + 1;
               }
            } else {
               earliestend = xend;
               ++xrlepos;
               if (xrlepos < x.nbrruns) {
                  xstart = x.getValue(xrlepos);
                  xend = xstart + x.getLength(xrlepos) + 1;
               }
            }

            cardinality += earliestend - lateststart;
         }
      }

      return cardinality;
   }

   public MappeableBitmapContainer toBitmapContainer() {
      int card = this.getCardinality();
      MappeableBitmapContainer answer = new MappeableBitmapContainer();

      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int start = this.getValue(rlepos);
         int end = start + this.getLength(rlepos) + 1;
         BufferUtil.setBitmapRange(answer.bitmap, start, end);
      }

      answer.cardinality = card;
      return answer;
   }

   public int first() {
      this.assertNonEmpty(this.numberOfRuns() == 0);
      return this.getValue(0);
   }

   public int last() {
      this.assertNonEmpty(this.numberOfRuns() == 0);
      int index = this.numberOfRuns() - 1;
      int start = this.getValue(index);
      int length = this.getLength(index);
      return start + length;
   }

   public int nextValue(char fromValue) {
      int index = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
      int effectiveIndex = index >= 0 ? index : -index - 2;
      if (effectiveIndex == -1) {
         return this.first();
      } else {
         int startValue = this.getValue(effectiveIndex);
         int offset = fromValue - startValue;
         int le = this.getLength(effectiveIndex);
         if (offset <= le) {
            return fromValue;
         } else {
            return effectiveIndex + 1 < this.numberOfRuns() ? this.getValue(effectiveIndex + 1) : -1;
         }
      }
   }

   public int previousValue(char fromValue) {
      int index = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
      int effectiveIndex = index >= 0 ? index : -index - 2;
      if (effectiveIndex == -1) {
         return -1;
      } else {
         int startValue = this.getValue(effectiveIndex);
         int offset = fromValue - startValue;
         int le = this.getLength(effectiveIndex);
         return offset >= 0 && offset <= le ? fromValue : startValue + le;
      }
   }

   public int nextAbsentValue(char fromValue) {
      int index = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
      int effectiveIndex = index >= 0 ? index : -index - 2;
      if (effectiveIndex == -1) {
         return fromValue;
      } else {
         int startValue = this.getValue(effectiveIndex);
         int offset = fromValue - startValue;
         int le = this.getLength(effectiveIndex);
         return offset <= le ? startValue + le + 1 : fromValue;
      }
   }

   public int previousAbsentValue(char fromValue) {
      int index = bufferedUnsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
      int effectiveIndex = index >= 0 ? index : -index - 2;
      if (effectiveIndex == -1) {
         return fromValue;
      } else {
         int startValue = this.getValue(effectiveIndex);
         int offset = fromValue - startValue;
         int le = this.getLength(effectiveIndex);
         return offset <= le ? startValue - 1 : fromValue;
      }
   }

   protected boolean contains(MappeableRunContainer runContainer) {
      int i1 = 0;
      int i2 = 0;

      while(i1 < this.numberOfRuns() && i2 < runContainer.numberOfRuns()) {
         int start1 = this.getValue(i1);
         int stop1 = start1 + this.getLength(i1);
         int start2 = runContainer.getValue(i2);
         int stop2 = start2 + runContainer.getLength(i2);
         if (start1 > start2) {
            return false;
         }

         if (stop1 > stop2) {
            ++i2;
         } else if (stop1 == stop2) {
            ++i1;
            ++i2;
         } else {
            ++i1;
         }
      }

      return i2 == runContainer.numberOfRuns();
   }

   protected boolean contains(MappeableArrayContainer arrayContainer) {
      int cardinality = this.getCardinality();
      int runCount = this.numberOfRuns();
      if (arrayContainer.getCardinality() > cardinality) {
         return false;
      } else {
         int ia = 0;
         int ir = 0;

         while(ia < arrayContainer.getCardinality() && ir < runCount) {
            int start = this.getValue(ir);
            int stop = start + this.getLength(ir);
            int value = arrayContainer.content.get(ia);
            if (value < start) {
               return false;
            }

            if (value > stop) {
               ++ir;
            } else {
               ++ia;
            }
         }

         return ia == arrayContainer.getCardinality();
      }
   }

   protected boolean contains(MappeableBitmapContainer bitmapContainer) {
      int cardinality = this.getCardinality();
      if (bitmapContainer.getCardinality() != -1 && bitmapContainer.getCardinality() > cardinality) {
         return false;
      } else {
         int runCount = this.numberOfRuns();
         char ib = 0;
         char ir = 0;
         int start = this.getValue(0);

         for(int stop = start + this.getLength(0); ib < 1024 && ir < runCount; ++ib) {
            long w = bitmapContainer.bitmap.get(ib);

            while(w != 0L) {
               long r = (long)(ib * 64 + Long.numberOfTrailingZeros(w));
               if (r < (long)start) {
                  return false;
               }

               if (r > (long)stop) {
                  ++ir;
                  if (ir == runCount) {
                     break;
                  }

                  start = this.getValue(ir);
                  stop = start + this.getLength(ir);
               } else if (ib * 64 + 64 < stop) {
                  ib = (char)(stop / 64);
                  w = bitmapContainer.bitmap.get(ib);
               } else {
                  w &= w - 1L;
               }
            }

            if (w != 0L) {
               return false;
            }
         }

         if (ib < 1024) {
            while(ib < 1024) {
               if (bitmapContainer.bitmap.get(ib) != 0L) {
                  return false;
               }

               ++ib;
            }
         }

         return true;
      }
   }

   public boolean intersects(int minimum, int supremum) {
      if (minimum >= 0 && supremum >= minimum && supremum <= 65536) {
         for(int i = 0; i < this.numberOfRuns(); ++i) {
            char runFirstValue = this.getValue(i);
            char runLastValue = (char)(runFirstValue + this.getLength(i));
            if (runFirstValue < supremum && runLastValue - (char)minimum >= 0) {
               return true;
            }
         }

         return false;
      } else {
         throw new RuntimeException("This should never happen (bug).");
      }
   }

   public boolean contains(int minimum, int supremum) {
      for(int i = 0; i < this.numberOfRuns(); ++i) {
         int start = this.getValue(i);
         int length = this.getLength(i);
         int stop = start + length + 1;
         if (start >= supremum) {
            break;
         }

         if (minimum >= start && supremum <= stop) {
            return true;
         }
      }

      return false;
   }
}
