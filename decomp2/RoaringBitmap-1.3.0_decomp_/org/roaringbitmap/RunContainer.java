package org.roaringbitmap;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.roaringbitmap.buffer.MappeableContainer;
import org.roaringbitmap.buffer.MappeableRunContainer;

public final class RunContainer extends Container implements Cloneable {
   private static final int DEFAULT_INIT_SIZE = 4;
   private static final boolean ENABLE_GALLOPING_AND = false;
   private static final long serialVersionUID = 1L;
   private char[] valueslength;
   int nbrruns;

   private static int branchyUnsignedInterleavedBinarySearch(char[] array, int begin, int end, char k) {
      int low = begin;
      int high = end - 1;

      while(low <= high) {
         int middleIndex = low + high >>> 1;
         int middleValue = array[2 * middleIndex];
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

   private static int hybridUnsignedInterleavedBinarySearch(char[] array, int begin, int end, char k) {
      int low = begin;
      int high = end - 1;

      while(low + 16 <= high) {
         int middleIndex = low + high >>> 1;
         int middleValue = array[2 * middleIndex];
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
         int val = array[2 * x];
         if (val >= k) {
            if (val == k) {
               return x;
            }
            break;
         }
      }

      return -(x + 1);
   }

   protected static int serializedSizeInBytes(int numberOfRuns) {
      return 2 + 4 * numberOfRuns;
   }

   private static int unsignedInterleavedBinarySearch(char[] array, int begin, int end, char k) {
      return hybridUnsignedInterleavedBinarySearch(array, begin, end, k);
   }

   public RunContainer() {
      this(4);
   }

   protected RunContainer(ArrayContainer arr, int nbrRuns) {
      this.nbrruns = 0;
      this.nbrruns = nbrRuns;
      this.valueslength = new char[2 * nbrRuns];
      if (nbrRuns != 0) {
         int prevVal = -2;
         int runLen = 0;
         int runCount = 0;

         for(int i = 0; i < arr.cardinality; ++i) {
            int curVal = arr.content[i];
            if (curVal == prevVal + 1) {
               ++runLen;
            } else {
               if (runCount > 0) {
                  this.setLength(runCount - 1, (char)runLen);
               }

               this.setValue(runCount, (char)curVal);
               runLen = 0;
               ++runCount;
            }

            prevVal = curVal;
         }

         this.setLength(runCount - 1, (char)runLen);
      }
   }

   public RunContainer(int firstOfRun, int lastOfRun) {
      this.nbrruns = 0;
      this.nbrruns = 1;
      this.valueslength = new char[]{(char)firstOfRun, (char)(lastOfRun - 1 - firstOfRun)};
   }

   protected RunContainer(BitmapContainer bc, int nbrRuns) {
      this.nbrruns = 0;
      this.nbrruns = nbrRuns;
      this.valueslength = new char[2 * nbrRuns];
      if (nbrRuns != 0) {
         int longCtr = 0;
         long curWord = bc.bitmap[0];
         int runCount = 0;

         while(true) {
            while(curWord != 0L || longCtr >= bc.bitmap.length - 1) {
               if (curWord == 0L) {
                  return;
               }

               int localRunStart = Long.numberOfTrailingZeros(curWord);
               int runStart = localRunStart + 64 * longCtr;

               long curWordWith1s;
               for(curWordWith1s = curWord | curWord - 1L; curWordWith1s == -1L && longCtr < bc.bitmap.length - 1; curWordWith1s = bc.bitmap[longCtr]) {
                  ++longCtr;
               }

               if (curWordWith1s == -1L) {
                  int runEnd = 64 + longCtr * 64;
                  this.setValue(runCount, (char)runStart);
                  this.setLength(runCount, (char)(runEnd - runStart - 1));
                  return;
               }

               int localRunEnd = Long.numberOfTrailingZeros(~curWordWith1s);
               int runEnd = localRunEnd + longCtr * 64;
               this.setValue(runCount, (char)runStart);
               this.setLength(runCount, (char)(runEnd - runStart - 1));
               ++runCount;
               curWord = curWordWith1s & curWordWith1s + 1L;
            }

            ++longCtr;
            curWord = bc.bitmap[longCtr];
         }
      }
   }

   public RunContainer(int capacity) {
      this.nbrruns = 0;
      this.valueslength = new char[2 * capacity];
   }

   private RunContainer(int nbrruns, char[] valueslength) {
      this.nbrruns = 0;
      this.nbrruns = nbrruns;
      this.valueslength = Arrays.copyOf(valueslength, valueslength.length);
   }

   public RunContainer(MappeableRunContainer bc) {
      this.nbrruns = 0;
      this.nbrruns = bc.numberOfRuns();
      this.valueslength = bc.toCharArray();
   }

   public RunContainer(char[] array, int numRuns) {
      this.nbrruns = 0;
      if (array.length < 2 * numRuns) {
         throw new RuntimeException("Mismatch between buffer and numRuns");
      } else {
         this.nbrruns = numRuns;
         this.valueslength = array;
      }
   }

   public Container add(int begin, int end) {
      RunContainer rc = (RunContainer)this.clone();
      return rc.iadd(begin, end);
   }

   public Container add(char k) {
      int index = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, k);
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
            this.decrementValue(0);
            return this;
         } else {
            this.makeRoomAtIndex(index + 1);
            this.setValue(index + 1, k);
            this.setLength(index + 1, '\u0000');
            return this;
         }
      }
   }

   public Container and(ArrayContainer x) {
      ArrayContainer ac = new ArrayContainer(x.cardinality);
      if (this.nbrruns == 0) {
         return ac;
      } else {
         int rlepos = 0;
         int arraypos = 0;
         int rleval = this.getValue(rlepos);
         int rlelength = this.getLength(rlepos);

         while(arraypos < x.cardinality) {
            int arrayval;
            for(arrayval = x.content[arraypos]; rleval + rlelength < arrayval; rlelength = this.getLength(rlepos)) {
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  return ac;
               }

               rleval = this.getValue(rlepos);
            }

            if (rleval > arrayval) {
               arraypos = Util.advanceUntil(x.content, arraypos, x.cardinality, (char)rleval);
            } else {
               ac.content[ac.cardinality] = (char)arrayval;
               ++ac.cardinality;
               ++arraypos;
            }
         }

         return ac;
      }
   }

   public Container and(BitmapContainer x) {
      int card = this.getCardinality();
      if (card <= 4096) {
         if (card > x.cardinality) {
            card = x.cardinality;
         }

         ArrayContainer answer = new ArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int runStart = this.getValue(rlepos);
            int runEnd = runStart + this.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               if (x.contains((char)runValue)) {
                  answer.content[answer.cardinality++] = (char)runValue;
               }
            }
         }

         return answer;
      } else {
         BitmapContainer answer = x.clone();
         int start = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int end = this.getValue(rlepos);
            int prevOnes = answer.cardinalityInRange(start, end);
            Util.resetBitmapRange(answer.bitmap, start, end);
            answer.updateCardinality(prevOnes, 0);
            start = end + this.getLength(rlepos) + 1;
         }

         int ones = answer.cardinalityInRange(start, 65536);
         Util.resetBitmapRange(answer.bitmap, start, 65536);
         answer.updateCardinality(ones, 0);
         return (Container)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
      }
   }

   public Container and(RunContainer x) {
      int maxRunsAfterIntersection = this.nbrruns + x.nbrruns;
      RunContainer answer = new RunContainer(new char[2 * maxRunsAfterIntersection], 0);
      if (this.isEmpty()) {
         return answer;
      } else {
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

               answer.valueslength[2 * answer.nbrruns] = (char)lateststart;
               answer.valueslength[2 * answer.nbrruns + 1] = (char)(earliestend - lateststart - 1);
               ++answer.nbrruns;
            }
         }

         return answer.toEfficientContainer();
      }
   }

   public int andCardinality(ArrayContainer x) {
      if (this.nbrruns == 0) {
         return x.cardinality;
      } else {
         int rlepos = 0;
         int arraypos = 0;
         int andCardinality = 0;
         int rleval = this.getValue(rlepos);
         int rlelength = this.getLength(rlepos);

         while(arraypos < x.cardinality) {
            int arrayval;
            for(arrayval = x.content[arraypos]; rleval + rlelength < arrayval; rlelength = this.getLength(rlepos)) {
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  return andCardinality;
               }

               rleval = this.getValue(rlepos);
            }

            if (rleval > arrayval) {
               arraypos = Util.advanceUntil(x.content, arraypos, x.cardinality, this.getValue(rlepos));
            } else {
               ++andCardinality;
               ++arraypos;
            }
         }

         return andCardinality;
      }
   }

   public int andCardinality(BitmapContainer x) {
      int cardinality = 0;

      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int runStart = this.getValue(rlepos);
         int runEnd = runStart + this.getLength(rlepos);
         cardinality += x.cardinalityInRange(runStart, runEnd + 1);
      }

      return cardinality;
   }

   public int andCardinality(RunContainer x) {
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

   public Container andNot(ArrayContainer x) {
      int arbitrary_threshold = 32;
      if (x.getCardinality() < 32) {
         return this.lazyandNot(x).toEfficientContainer();
      } else {
         int card = this.getCardinality();
         if (card <= 4096) {
            ArrayContainer ac = new ArrayContainer(card);
            ac.cardinality = Util.unsignedDifference(this.getCharIterator(), x.getCharIterator(), ac.content);
            return ac;
         } else {
            return this.toBitmapOrArrayContainer(card).iandNot(x);
         }
      }
   }

   public Container andNot(BitmapContainer x) {
      int card = this.getCardinality();
      if (card <= 4096) {
         ArrayContainer answer = new ArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int runStart = this.getValue(rlepos);
            int runEnd = runStart + this.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               if (!x.contains((char)runValue)) {
                  answer.content[answer.cardinality++] = (char)runValue;
               }
            }
         }

         return answer;
      } else {
         BitmapContainer answer = x.clone();
         int lastPos = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            int prevOnes = answer.cardinalityInRange(lastPos, start);
            int flippedOnes = answer.cardinalityInRange(start, end);
            Util.resetBitmapRange(answer.bitmap, lastPos, start);
            Util.flipBitmapRange(answer.bitmap, start, end);
            answer.updateCardinality(prevOnes + flippedOnes, end - start - flippedOnes);
            lastPos = end;
         }

         int ones = answer.cardinalityInRange(lastPos, 65536);
         Util.resetBitmapRange(answer.bitmap, lastPos, 65536);
         answer.updateCardinality(ones, 0);
         return (Container)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
      }
   }

   public Container andNot(RunContainer x) {
      RunContainer answer = new RunContainer(new char[2 * (this.nbrruns + x.nbrruns)], 0);
      int rlepos = 0;
      int xrlepos = 0;
      int start = this.getValue(rlepos);
      int end = start + this.getLength(rlepos) + 1;
      int xstart = x.getValue(xrlepos);
      int xend = xstart + x.getLength(xrlepos) + 1;

      while(rlepos < this.nbrruns && xrlepos < x.nbrruns) {
         if (end <= xstart) {
            answer.valueslength[2 * answer.nbrruns] = (char)start;
            answer.valueslength[2 * answer.nbrruns + 1] = (char)(end - start - 1);
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
               answer.valueslength[2 * answer.nbrruns] = (char)start;
               answer.valueslength[2 * answer.nbrruns + 1] = (char)(xstart - start - 1);
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
         answer.valueslength[2 * answer.nbrruns] = (char)start;
         answer.valueslength[2 * answer.nbrruns + 1] = (char)(end - start - 1);
         ++answer.nbrruns;
         ++rlepos;
         if (rlepos < this.nbrruns) {
            System.arraycopy(this.valueslength, 2 * rlepos, answer.valueslength, 2 * answer.nbrruns, 2 * (this.nbrruns - rlepos));
            answer.nbrruns = answer.nbrruns + this.nbrruns - rlepos;
         }
      }

      return answer.toEfficientContainer();
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

   public Container clone() {
      return new RunContainer(this.nbrruns, this.valueslength);
   }

   public boolean isEmpty() {
      return this.nbrruns == 0;
   }

   private void closeValueLength(int value, int index) {
      int initialValue = this.getValue(index);
      this.setLength(index, (char)(value - initialValue));
   }

   public boolean contains(char x) {
      int index = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, x);
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

   protected boolean contains(RunContainer runContainer) {
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

   protected boolean contains(ArrayContainer arrayContainer) {
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
            int ac = arrayContainer.content[ia];
            if (ac < start) {
               return false;
            }

            if (ac > stop) {
               ++ir;
            } else {
               ++ia;
            }
         }

         return ia == arrayContainer.getCardinality();
      }
   }

   protected boolean contains(BitmapContainer bitmapContainer) {
      int cardinality = this.getCardinality();
      if (bitmapContainer.getCardinality() != -1 && bitmapContainer.getCardinality() > cardinality) {
         return false;
      } else {
         int runCount = this.numberOfRuns();
         char ib = 0;
         char ir = 0;
         int start = this.getValue(ir);

         for(int stop = start + this.getLength(ir); ib < bitmapContainer.bitmap.length && ir < runCount; ++ib) {
            long w = bitmapContainer.bitmap[ib];

            while(w != 0L) {
               long r = (long)ib * 64L + (long)Long.numberOfTrailingZeros(w);
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
                  w = bitmapContainer.bitmap[ib];
               } else {
                  w &= w - 1L;
               }
            }

            if (w != 0L) {
               return false;
            }
         }

         if (ib < bitmapContainer.bitmap.length) {
            while(ib < bitmapContainer.bitmap.length) {
               if (bitmapContainer.bitmap[ib] != 0L) {
                  return false;
               }

               ++ib;
            }
         }

         return true;
      }
   }

   private Container convertToLazyBitmapIfNeeded() {
      if (this.nbrruns <= 4096) {
         return this;
      } else {
         BitmapContainer answer = new BitmapContainer();

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            Util.setBitmapRange(answer.bitmap, start, end);
         }

         answer.cardinality = -1;
         return answer;
      }
   }

   private void copyToOffset(int offset) {
      if (!this.ensureCapacity(offset, 2 * (offset + this.nbrruns))) {
         this.copyValuesLength(this.valueslength, 0, this.valueslength, offset, this.nbrruns);
      }

   }

   private void copyValuesLength(char[] src, int srcIndex, char[] dst, int dstIndex, int length) {
      System.arraycopy(src, 2 * srcIndex, dst, 2 * dstIndex, 2 * length);
   }

   private void decrementLength(int index) {
      --this.valueslength[2 * index + 1];
   }

   private void decrementValue(int index) {
      --this.valueslength[2 * index];
   }

   public void deserialize(DataInput in) throws IOException {
      this.nbrruns = Character.reverseBytes(in.readChar());
      if (this.valueslength.length < 2 * this.nbrruns) {
         this.valueslength = new char[2 * this.nbrruns];
      }

      for(int k = 0; k < 2 * this.nbrruns; ++k) {
         this.valueslength[k] = Character.reverseBytes(in.readChar());
      }

   }

   boolean ensureCapacity(int offset, int minNbRuns) {
      int minCapacity = 2 * minNbRuns;
      if (this.valueslength.length >= minCapacity) {
         return false;
      } else {
         int newCapacity;
         for(newCapacity = this.valueslength.length; newCapacity < minCapacity; newCapacity = this.computeCapacity(newCapacity)) {
         }

         char[] nv = new char[newCapacity];
         this.copyValuesLength(this.valueslength, 0, nv, offset, this.nbrruns);
         this.valueslength = nv;
         return true;
      }
   }

   public boolean equals(Object o) {
      if (o instanceof RunContainer) {
         return this.equals((RunContainer)o);
      } else if (o instanceof ArrayContainer) {
         return this.equals((ArrayContainer)o);
      } else if (o instanceof Container) {
         if (((Container)o).getCardinality() != this.getCardinality()) {
            return false;
         } else {
            CharIterator me = this.getCharIterator();
            CharIterator you = ((Container)o).getCharIterator();

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

   private boolean equals(RunContainer rc) {
      return ArraysShim.equals(this.valueslength, 0, 2 * this.nbrruns, rc.valueslength, 0, 2 * rc.nbrruns);
   }

   private boolean equals(ArrayContainer arrayContainer) {
      int pos = 0;

      for(char i = 0; i < this.nbrruns; ++i) {
         char runStart = this.getValue(i);
         int length = this.getLength(i);
         if (pos + length >= arrayContainer.getCardinality()) {
            return false;
         }

         if (arrayContainer.content[pos] != runStart) {
            return false;
         }

         if (arrayContainer.content[pos + length] != (char)(runStart + length)) {
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

   public Container flip(char x) {
      return this.contains(x) ? this.remove(x) : this.add(x);
   }

   public int getArraySizeInBytes() {
      return 2 + 4 * this.nbrruns;
   }

   public int getCardinality() {
      int sum = this.nbrruns;

      for(int k = 1; k < this.nbrruns * 2; k += 2) {
         sum += this.valueslength[k];
      }

      return sum;
   }

   public char getLength(int index) {
      return this.valueslength[2 * index + 1];
   }

   public PeekableCharIterator getReverseCharIterator() {
      return new ReverseRunContainerCharIterator(this);
   }

   public PeekableCharIterator getCharIterator() {
      return new RunContainerCharIterator(this);
   }

   public PeekableCharRankIterator getCharRankIterator() {
      return new RunContainerCharRankIterator(this);
   }

   public ContainerBatchIterator getBatchIterator() {
      return new RunBatchIterator(this);
   }

   public int getSizeInBytes() {
      return this.nbrruns * 4 + 4;
   }

   public char getValue(int index) {
      return this.valueslength[2 * index];
   }

   public int hashCode() {
      int hash = 0;

      for(int k = 0; k < this.nbrruns * 2; ++k) {
         hash += 31 * hash + this.valueslength[k];
      }

      return hash;
   }

   public Container iadd(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         if (begin == end - 1) {
            this.add((char)begin);
            return this;
         } else {
            int bIndex = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, (char)begin);
            int eIndex = unsignedInterleavedBinarySearch(this.valueslength, bIndex >= 0 ? bIndex : -bIndex - 1, this.nbrruns, (char)(end - 1));
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

   public Container iand(ArrayContainer x) {
      return this.and(x);
   }

   public Container iand(BitmapContainer x) {
      return this.and(x);
   }

   public Container iand(RunContainer x) {
      return this.and(x);
   }

   public Container iandNot(ArrayContainer x) {
      return this.andNot(x);
   }

   public Container iandNot(BitmapContainer x) {
      return this.andNot(x);
   }

   public Container iandNot(RunContainer x) {
      return this.andNot(x);
   }

   Container ilazyor(ArrayContainer x) {
      return (Container)(this.isFull() ? this : this.ilazyorToRun(x));
   }

   private Container ilazyorToRun(ArrayContainer x) {
      if (this.isFull()) {
         return full();
      } else {
         int nbrruns = this.nbrruns;
         int offset = Math.max(nbrruns, x.getCardinality());
         this.copyToOffset(offset);
         int rlepos = 0;
         this.nbrruns = 0;
         PeekableCharIterator i = x.getCharIterator();

         while(i.hasNext() && rlepos < nbrruns) {
            if (this.getValue(rlepos + offset) - i.peekNext() <= 0) {
               this.smartAppend(this.getValue(rlepos + offset), this.getLength(rlepos + offset));
               ++rlepos;
            } else {
               this.smartAppend(i.next());
            }
         }

         if (i.hasNext()) {
            while(i.hasNext()) {
               this.smartAppend(i.next());
            }
         } else {
            while(rlepos < nbrruns) {
               this.smartAppend(this.getValue(rlepos + offset), this.getLength(rlepos + offset));
               ++rlepos;
            }
         }

         return this.convertToLazyBitmapIfNeeded();
      }
   }

   private int computeCapacity(int oldCapacity) {
      return oldCapacity == 0 ? 4 : (oldCapacity < 64 ? oldCapacity * 2 : (oldCapacity < 1024 ? oldCapacity * 3 / 2 : oldCapacity * 5 / 4));
   }

   private void incrementLength(int index) {
      ++this.valueslength[2 * index + 1];
   }

   private void incrementValue(int index) {
      ++this.valueslength[2 * index];
   }

   private void initValueLength(int value, int index) {
      int initialValue = this.getValue(index);
      int length = this.getLength(index);
      this.setValue(index, (char)value);
      this.setLength(index, (char)(length - (value - initialValue)));
   }

   public Container inot(int rangeStart, int rangeEnd) {
      if (rangeEnd <= rangeStart) {
         return this;
      } else {
         if (this.valueslength.length <= 2 * this.nbrruns + 1) {
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
         RunContainer ans = this;
         int k = 0;

         for(this.nbrruns = 0; k < myNbrRuns && this.getValue(k) < rangeStart; ++k) {
            ++ans.nbrruns;
         }

         char bufferedValue = 0;
         char bufferedLength = 0;
         char nextValue = 0;
         char nextLength = 0;
         if (k < myNbrRuns) {
            bufferedValue = this.getValue(k);
            bufferedLength = this.getLength(k);
         }

         ans.smartAppendExclusive((char)rangeStart, (char)(rangeEnd - rangeStart - 1));

         while(k < myNbrRuns) {
            if (ans.nbrruns > k + 1) {
               throw new RuntimeException("internal error in inot, writer has overtaken reader!! " + k + " " + ans.nbrruns);
            }

            if (k + 1 < myNbrRuns) {
               nextValue = this.getValue(k + 1);
               nextLength = this.getLength(k + 1);
            }

            ans.smartAppendExclusive(bufferedValue, bufferedLength);
            bufferedValue = nextValue;
            bufferedLength = nextLength;
            ++k;
         }

         return ans.toEfficientContainer();
      }
   }

   public boolean intersects(ArrayContainer x) {
      if (this.nbrruns == 0) {
         return false;
      } else {
         int rlepos = 0;
         int arraypos = 0;
         int rleval = this.getValue(rlepos);

         for(int rlelength = this.getLength(rlepos); arraypos < x.cardinality; arraypos = Util.advanceUntil(x.content, arraypos, x.cardinality, this.getValue(rlepos))) {
            int arrayval;
            for(arrayval = x.content[arraypos]; rleval + rlelength < arrayval; rlelength = this.getLength(rlepos)) {
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

   public boolean intersects(BitmapContainer x) {
      for(int run = 0; run < this.nbrruns; ++run) {
         int runStart = this.getValue(run);
         int runEnd = runStart + this.getLength(run);
         if (x.intersects(runStart, runEnd + 1)) {
            return true;
         }
      }

      return false;
   }

   public boolean intersects(RunContainer x) {
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

   public boolean intersects(int minimum, int supremum) {
      if (minimum >= 0 && supremum >= minimum && supremum <= 65536) {
         for(int i = 0; i < this.numberOfRuns(); ++i) {
            int runFirstValue = this.getValue(i);
            int runLastValue = (char)(runFirstValue + this.getLength(i)) + 1;
            if (supremum > runFirstValue && minimum < runLastValue) {
               return true;
            }
         }

         return false;
      } else {
         throw new RuntimeException("This should never happen (bug).");
      }
   }

   public Container ior(ArrayContainer x) {
      if (this.isFull()) {
         return this;
      } else {
         int nbrruns = this.nbrruns;
         int offset = Math.max(nbrruns, x.getCardinality());
         this.copyToOffset(offset);
         int rlepos = 0;
         this.nbrruns = 0;
         PeekableCharIterator i = x.getCharIterator();

         while(i.hasNext() && rlepos < nbrruns) {
            if (this.getValue(rlepos + offset) - i.peekNext() <= 0) {
               this.smartAppend(this.getValue(rlepos + offset), this.getLength(rlepos + offset));
               ++rlepos;
            } else {
               this.smartAppend(i.next());
            }
         }

         if (i.hasNext()) {
            while(i.hasNext()) {
               this.smartAppend(i.next());
            }
         } else {
            while(rlepos < nbrruns) {
               this.smartAppend(this.getValue(rlepos + offset), this.getLength(rlepos + offset));
               ++rlepos;
            }
         }

         return this.toEfficientContainer();
      }
   }

   public Container ior(BitmapContainer x) {
      return (Container)(this.isFull() ? this : this.or(x));
   }

   public Container ior(RunContainer x) {
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

         while(rlepos < nbrruns && xrlepos < xnbrruns) {
            char value = this.getValue(offset + rlepos);
            char xvalue = x.getValue(xrlepos);
            char length = this.getLength(offset + rlepos);
            char xlength = x.getLength(xrlepos);
            if (value - xvalue <= 0) {
               this.smartAppend(value, length);
               ++rlepos;
            } else {
               this.smartAppend(xvalue, xlength);
               ++xrlepos;
            }
         }

         while(rlepos < nbrruns) {
            this.smartAppend(this.getValue(offset + rlepos), this.getLength(offset + rlepos));
            ++rlepos;
         }

         while(xrlepos < xnbrruns) {
            this.smartAppend(x.getValue(xrlepos), x.getLength(xrlepos));
            ++xrlepos;
         }

         return this.toEfficientContainer();
      }
   }

   public Container iremove(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         if (begin == end - 1) {
            this.remove((char)begin);
            return this;
         } else {
            int bIndex = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, (char)begin);
            int eIndex = unsignedInterleavedBinarySearch(this.valueslength, bIndex >= 0 ? bIndex : -bIndex - 1, this.nbrruns, (char)(end - 1));
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

   public boolean isFull() {
      return this.nbrruns == 1 && this.getValue(0) == 0 && this.getLength(0) == '\uffff';
   }

   public static RunContainer full() {
      return new RunContainer(0, 65536);
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

   public Container ixor(ArrayContainer x) {
      return this.xor(x);
   }

   public Container ixor(BitmapContainer x) {
      return this.xor(x);
   }

   public Container ixor(RunContainer x) {
      return this.xor(x);
   }

   private RunContainer lazyandNot(ArrayContainer x) {
      if (x.isEmpty()) {
         return this;
      } else {
         RunContainer answer = new RunContainer(new char[2 * (this.nbrruns + x.cardinality)], 0);
         int rlepos = 0;
         int xrlepos = 0;
         int start = this.getValue(rlepos);
         int end = start + this.getLength(rlepos) + 1;
         int xstart = x.content[xrlepos];

         while(rlepos < this.nbrruns && xrlepos < x.cardinality) {
            if (end <= xstart) {
               answer.valueslength[2 * answer.nbrruns] = (char)start;
               answer.valueslength[2 * answer.nbrruns + 1] = (char)(end - start - 1);
               ++answer.nbrruns;
               ++rlepos;
               if (rlepos < this.nbrruns) {
                  start = this.getValue(rlepos);
                  end = start + this.getLength(rlepos) + 1;
               }
            } else if (xstart + 1 <= start) {
               ++xrlepos;
               if (xrlepos < x.cardinality) {
                  xstart = x.content[xrlepos];
               }
            } else {
               if (start < xstart) {
                  answer.valueslength[2 * answer.nbrruns] = (char)start;
                  answer.valueslength[2 * answer.nbrruns + 1] = (char)(xstart - start - 1);
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
            answer.valueslength[2 * answer.nbrruns] = (char)start;
            answer.valueslength[2 * answer.nbrruns + 1] = (char)(end - start - 1);
            ++answer.nbrruns;
            ++rlepos;
            if (rlepos < this.nbrruns) {
               System.arraycopy(this.valueslength, 2 * rlepos, answer.valueslength, 2 * answer.nbrruns, 2 * (this.nbrruns - rlepos));
               answer.nbrruns = answer.nbrruns + this.nbrruns - rlepos;
            }
         }

         return answer;
      }
   }

   protected Container lazyor(ArrayContainer x) {
      return this.lazyorToRun(x);
   }

   private Container lazyorToRun(ArrayContainer x) {
      if (this.isFull()) {
         return full();
      } else {
         RunContainer answer = new RunContainer(new char[2 * (this.nbrruns + x.getCardinality())], 0);
         int rlepos = 0;
         PeekableCharIterator i = x.getCharIterator();

         while(i.hasNext() && rlepos < this.nbrruns) {
            if (this.getValue(rlepos) - i.peekNext() <= 0) {
               answer.smartAppend(this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
            } else {
               answer.smartAppend(i.next());
            }
         }

         if (i.hasNext()) {
            while(i.hasNext()) {
               answer.smartAppend(i.next());
            }
         } else {
            while(rlepos < this.nbrruns) {
               answer.smartAppend(this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
            }
         }

         return (Container)(answer.isFull() ? full() : answer.convertToLazyBitmapIfNeeded());
      }
   }

   private Container lazyxor(ArrayContainer x) {
      if (x.isEmpty()) {
         return this;
      } else if (this.nbrruns == 0) {
         return x;
      } else {
         RunContainer answer = new RunContainer(new char[2 * (this.nbrruns + x.getCardinality())], 0);
         int rlepos = 0;
         CharIterator i = x.getCharIterator();
         char cv = i.next();

         while(true) {
            if (this.getValue(rlepos) < cv) {
               answer.smartAppendExclusive(this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  answer.smartAppendExclusive(cv);

                  while(i.hasNext()) {
                     answer.smartAppendExclusive(i.next());
                  }

                  return answer;
               }
            } else {
               answer.smartAppendExclusive(cv);
               if (!i.hasNext()) {
                  while(rlepos < this.nbrruns) {
                     answer.smartAppendExclusive(this.getValue(rlepos), this.getLength(rlepos));
                     ++rlepos;
                  }

                  return answer;
               }

               cv = i.next();
            }
         }
      }
   }

   public Container limit(int maxcardinality) {
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

         RunContainer rc = new RunContainer(Arrays.copyOf(this.valueslength, 2 * (r + 1)), r + 1);
         rc.setLength(r, (char)(rc.getLength(r) - cardinality + maxcardinality));
         return rc;
      }
   }

   private void makeRoomAtIndex(int index) {
      if (2 * (this.nbrruns + 1) > this.valueslength.length) {
         int newCapacity = this.computeCapacity(this.valueslength.length);
         char[] newValuesLength = new char[newCapacity];
         this.copyValuesLength(this.valueslength, 0, newValuesLength, 0, this.nbrruns);
         this.valueslength = newValuesLength;
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

   public Container not(int rangeStart, int rangeEnd) {
      if (rangeEnd <= rangeStart) {
         return this.clone();
      } else {
         RunContainer ans = new RunContainer(this.nbrruns + 1);

         int k;
         for(k = 0; k < this.nbrruns && this.getValue(k) < rangeStart; ++k) {
            ans.valueslength[2 * k] = this.valueslength[2 * k];
            ans.valueslength[2 * k + 1] = this.valueslength[2 * k + 1];
            ++ans.nbrruns;
         }

         ans.smartAppendExclusive((char)rangeStart, (char)(rangeEnd - rangeStart - 1));

         while(k < this.nbrruns) {
            ans.smartAppendExclusive(this.getValue(k), this.getLength(k));
            ++k;
         }

         return ans.toEfficientContainer();
      }
   }

   public int numberOfRuns() {
      return this.nbrruns;
   }

   public Container or(ArrayContainer x) {
      return this.lazyor(x).repairAfterLazy();
   }

   public Container or(BitmapContainer x) {
      if (this.isFull()) {
         return full();
      } else {
         BitmapContainer answer = x.clone();

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            int prevOnesInRange = answer.cardinalityInRange(start, end);
            Util.setBitmapRange(answer.bitmap, start, end);
            answer.updateCardinality(prevOnesInRange, end - start);
         }

         return (Container)(answer.isFull() ? full() : answer);
      }
   }

   public Container or(RunContainer x) {
      if (this.isFull()) {
         return full();
      } else if (x.isFull()) {
         return full();
      } else {
         RunContainer answer = new RunContainer(new char[2 * (this.nbrruns + x.nbrruns)], 0);
         int rlepos = 0;
         int xrlepos = 0;

         while(xrlepos < x.nbrruns && rlepos < this.nbrruns) {
            if (this.getValue(rlepos) - x.getValue(xrlepos) <= 0) {
               answer.smartAppend(this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
            } else {
               answer.smartAppend(x.getValue(xrlepos), x.getLength(xrlepos));
               ++xrlepos;
            }
         }

         while(xrlepos < x.nbrruns) {
            answer.smartAppend(x.getValue(xrlepos), x.getLength(xrlepos));
            ++xrlepos;
         }

         while(rlepos < this.nbrruns) {
            answer.smartAppend(this.getValue(rlepos), this.getLength(rlepos));
            ++rlepos;
         }

         return (Container)(answer.isFull() ? full() : answer.toEfficientContainer());
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
      this.deserialize(in);
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

   public Container remove(int begin, int end) {
      RunContainer rc = (RunContainer)this.clone();
      return rc.iremove(begin, end);
   }

   public Container remove(char x) {
      int index = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, x);
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

   public Container repairAfterLazy() {
      return this.toEfficientContainer();
   }

   public Container runOptimize() {
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

   public void serialize(DataOutput out) throws IOException {
      this.writeArray(out);
   }

   public int serializedSizeInBytes() {
      return serializedSizeInBytes(this.nbrruns);
   }

   private void setLength(int index, char v) {
      this.setLength(this.valueslength, index, v);
   }

   private void setLength(char[] valueslength, int index, char v) {
      valueslength[2 * index + 1] = v;
   }

   private void setValue(int index, char v) {
      this.setValue(this.valueslength, index, v);
   }

   private void setValue(char[] valueslength, int index, char v) {
      valueslength[2 * index] = v;
   }

   private int skipAhead(RunContainer skippingOn, int pos, int targetToExceed) {
      int left = pos;
      int span = 1;

      int probePos;
      int end;
      do {
         probePos = left + span;
         if (probePos >= skippingOn.nbrruns - 1) {
            probePos = skippingOn.nbrruns - 1;
            end = skippingOn.getValue(probePos) + skippingOn.getLength(probePos) + 1;
            if (end <= targetToExceed) {
               return skippingOn.nbrruns;
            }
         }

         end = skippingOn.getValue(probePos) + skippingOn.getLength(probePos) + 1;
         span *= 2;
      } while(end <= targetToExceed);

      int right = probePos;

      while(right - left > 1) {
         int mid = (right + left) / 2;
         int midVal = skippingOn.getValue(mid) + skippingOn.getLength(mid) + 1;
         if (midVal > targetToExceed) {
            right = mid;
         } else {
            left = mid;
         }
      }

      return right;
   }

   private void smartAppend(char val) {
      int oldend;
      if (this.nbrruns != 0 && val <= (oldend = this.valueslength[2 * (this.nbrruns - 1)] + this.valueslength[2 * (this.nbrruns - 1) + 1]) + 1) {
         if (val == (char)(oldend + 1)) {
            ++this.valueslength[2 * (this.nbrruns - 1) + 1];
         }

      } else {
         this.valueslength[2 * this.nbrruns] = val;
         this.valueslength[2 * this.nbrruns + 1] = 0;
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
         this.ensureCapacity(0, this.nbrruns + 1);
         this.valueslength[2 * this.nbrruns] = start;
         this.valueslength[2 * this.nbrruns + 1] = length;
         ++this.nbrruns;
      }
   }

   private void smartAppendExclusive(char val) {
      int oldend;
      if (this.nbrruns != 0 && val <= (oldend = this.getValue(this.nbrruns - 1) + this.getLength(this.nbrruns - 1) + 1)) {
         if (oldend == val) {
            ++this.valueslength[2 * (this.nbrruns - 1) + 1];
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
         this.valueslength[2 * this.nbrruns] = val;
         this.valueslength[2 * this.nbrruns + 1] = 0;
         ++this.nbrruns;
      }
   }

   private void smartAppendExclusive(char start, char length) {
      int oldend;
      if (this.nbrruns != 0 && start <= (oldend = this.getValue(this.nbrruns - 1) + this.getLength(this.nbrruns - 1) + 1)) {
         if (oldend == start) {
            char[] var10000 = this.valueslength;
            int var10001 = 2 * (this.nbrruns - 1) + 1;
            var10000[var10001] = (char)(var10000[var10001] + length + 1);
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
         this.valueslength[2 * this.nbrruns] = start;
         this.valueslength[2 * this.nbrruns + 1] = length;
         ++this.nbrruns;
      }
   }

   Container toBitmapOrArrayContainer(int card) {
      if (card <= 4096) {
         ArrayContainer answer = new ArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int runStart = this.getValue(rlepos);
            int runEnd = runStart + this.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               answer.content[answer.cardinality++] = (char)runValue;
            }
         }

         return answer;
      } else {
         BitmapContainer answer = new BitmapContainer();

         for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
            int start = this.getValue(rlepos);
            int end = start + this.getLength(rlepos) + 1;
            Util.setBitmapRange(answer.bitmap, start, end);
         }

         answer.cardinality = card;
         return answer;
      }
   }

   private Container toEfficientContainer() {
      int sizeAsRunContainer = serializedSizeInBytes(this.nbrruns);
      int sizeAsBitmapContainer = BitmapContainer.serializedSizeInBytes(0);
      int card = this.getCardinality();
      int sizeAsArrayContainer = ArrayContainer.serializedSizeInBytes(card);
      return (Container)(sizeAsRunContainer <= Math.min(sizeAsBitmapContainer, sizeAsArrayContainer) ? this : this.toBitmapOrArrayContainer(card));
   }

   public MappeableContainer toMappeableContainer() {
      return new MappeableRunContainer(this);
   }

   public CharBuffer toCharBuffer() {
      CharBuffer sb = CharBuffer.allocate(this.nbrruns * 2);
      sb.put(this.valueslength, 0, this.nbrruns * 2);
      return sb;
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
      if (this.valueslength.length != 2 * this.nbrruns) {
         this.valueslength = Arrays.copyOf(this.valueslength, 2 * this.nbrruns);
      }
   }

   private boolean valueLengthContains(int value, int index) {
      int initialValue = this.getValue(index);
      int length = this.getLength(index);
      return value <= initialValue + length;
   }

   public void writeArray(DataOutput out) throws IOException {
      out.writeShort(Character.reverseBytes((char)this.nbrruns));

      for(int k = 0; k < 2 * this.nbrruns; ++k) {
         out.writeShort(Character.reverseBytes(this.valueslength[k]));
      }

   }

   public void writeArray(ByteBuffer buffer) {
      assert buffer.order() == ByteOrder.LITTLE_ENDIAN;

      CharBuffer buf = buffer.asCharBuffer();
      buf.put((char)this.nbrruns);
      buf.put(this.valueslength, 0, this.nbrruns * 2);
      int bytesWritten = (this.nbrruns * 2 + 1) * 2;
      buffer.position(buffer.position() + bytesWritten);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.serialize(out);
   }

   public Container xor(ArrayContainer x) {
      int arbitrary_threshold = 32;
      if (x.getCardinality() < 32) {
         return this.lazyxor(x).repairAfterLazy();
      } else {
         int card = this.getCardinality();
         return card <= 4096 ? x.xor((CharIterator)this.getCharIterator()) : this.toBitmapOrArrayContainer(card).ixor(x);
      }
   }

   public Container xor(BitmapContainer x) {
      BitmapContainer answer = x.clone();

      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int start = this.getValue(rlepos);
         int end = start + this.getLength(rlepos) + 1;
         int prevOnes = answer.cardinalityInRange(start, end);
         Util.flipBitmapRange(answer.bitmap, start, end);
         answer.updateCardinality(prevOnes, end - start - prevOnes);
      }

      return (Container)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
   }

   public Container xor(RunContainer x) {
      if (x.nbrruns == 0) {
         return this.clone();
      } else if (this.nbrruns == 0) {
         return x.clone();
      } else {
         RunContainer answer = new RunContainer(new char[2 * (this.nbrruns + x.nbrruns)], 0);
         int rlepos = 0;
         int xrlepos = 0;

         while(true) {
            if (this.getValue(rlepos) < x.getValue(xrlepos)) {
               answer.smartAppendExclusive(this.getValue(rlepos), this.getLength(rlepos));
               ++rlepos;
               if (rlepos == this.nbrruns) {
                  while(xrlepos < x.nbrruns) {
                     answer.smartAppendExclusive(x.getValue(xrlepos), x.getLength(xrlepos));
                     ++xrlepos;
                  }

                  return answer.toEfficientContainer();
               }
            } else {
               answer.smartAppendExclusive(x.getValue(xrlepos), x.getLength(xrlepos));
               ++xrlepos;
               if (xrlepos == x.nbrruns) {
                  while(rlepos < this.nbrruns) {
                     answer.smartAppendExclusive(this.getValue(rlepos), this.getLength(rlepos));
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
         int base = this.getValue(k) | high;
         int le = this.getLength(k);

         for(int l = base; l - le <= base; ++l) {
            ic.accept(l);
         }
      }

   }

   public void forAll(int offset, RelativeRangeConsumer rrc) {
      int next = 0;

      for(int run = 0; run < this.nbrruns; ++run) {
         int runPos = run << 1;
         char runStart = this.valueslength[runPos];
         char runLength = this.valueslength[runPos + 1];
         if (next < runStart) {
            rrc.acceptAllAbsent(offset + next, offset + runStart);
         }

         rrc.acceptAllPresent(offset + runStart, offset + runStart + runLength + 1);
         next = runStart + runLength + 1;
      }

      if (next <= 65535) {
         rrc.acceptAllAbsent(offset + next, offset + '\uffff' + 1);
      }

   }

   public void forAllFrom(char startValue, RelativeRangeConsumer rrc) {
      int startOffset = startValue;
      int next = startValue;

      for(int run = 0; run < this.nbrruns; ++run) {
         int runPos = run << 1;
         char runStart = this.valueslength[runPos];
         char runLength = this.valueslength[runPos + 1];
         int runEnd = runStart + runLength;
         if (runEnd >= startValue) {
            if (runStart < next) {
               assert next == startValue;

               rrc.acceptAllPresent(0, runStart + runLength + 1 - startOffset);
            } else {
               if (next < runStart) {
                  rrc.acceptAllAbsent(next - startOffset, runStart - startOffset);
               }

               rrc.acceptAllPresent(runStart - startOffset, runStart + runLength + 1 - startOffset);
            }

            next = runStart + runLength + 1;
         }
      }

      if (next <= 65535) {
         rrc.acceptAllAbsent(next - startOffset, 65536 - startOffset);
      }

   }

   public void forAllUntil(int offset, char endValue, RelativeRangeConsumer rrc) {
      int next = 0;

      for(int run = 0; run < this.nbrruns; ++run) {
         int runPos = run << 1;
         char runStart = this.valueslength[runPos];
         char runLength = this.valueslength[runPos + 1];
         if (endValue <= runStart) {
            break;
         }

         if (next < runStart) {
            rrc.acceptAllAbsent(offset + next, offset + runStart);
         }

         char runEnd = (char)(runStart + runLength);
         if (endValue <= runEnd) {
            rrc.acceptAllPresent(offset + runStart, offset + endValue);
            return;
         }

         rrc.acceptAllPresent(offset + runStart, offset + runEnd + 1);
         next = runEnd + 1;
      }

      if (next < endValue) {
         rrc.acceptAllAbsent(offset + next, offset + endValue);
      }

   }

   public void forAllInRange(char startValue, char endValue, RelativeRangeConsumer rrc) {
      if (endValue <= startValue) {
         throw new IllegalArgumentException("startValue (" + startValue + ") must be less than endValue (" + endValue + ")");
      } else {
         int startOffset = startValue;
         int next = startValue;

         for(int run = 0; run < this.nbrruns; ++run) {
            int runPos = run << 1;
            char runStart = this.valueslength[runPos];
            char runLength = this.valueslength[runPos + 1];
            int runEnd = runStart + runLength;
            if (runEnd >= startValue) {
               if (endValue <= runStart) {
                  break;
               }

               if (runStart < next) {
                  if (endValue <= runEnd) {
                     rrc.acceptAllPresent(0, endValue - startOffset);
                     return;
                  }

                  rrc.acceptAllPresent(0, runEnd + 1 - startOffset);
               } else {
                  if (next < runStart) {
                     rrc.acceptAllAbsent(next - startOffset, runStart - startOffset);
                  }

                  if (endValue <= runEnd) {
                     rrc.acceptAllPresent(runStart - startOffset, endValue - startOffset);
                     return;
                  }

                  rrc.acceptAllPresent(runStart - startOffset, runStart + runLength + 1 - startOffset);
               }

               next = runStart + runLength + 1;
            }
         }

         if (next < endValue) {
            rrc.acceptAllAbsent(next - startOffset, endValue - startOffset);
         }

      }
   }

   public BitmapContainer toBitmapContainer() {
      int card = 0;
      BitmapContainer answer = new BitmapContainer();

      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int start = this.getValue(rlepos);
         int end = start + this.getLength(rlepos) + 1;
         card += end - start;
         Util.setBitmapRange(answer.bitmap, start, end);
      }

      assert card == this.getCardinality();

      answer.cardinality = card;
      return answer;
   }

   public void copyBitmapTo(long[] dest, int position) {
      int offset = position * 64;

      for(int rlepos = 0; rlepos < this.nbrruns; ++rlepos) {
         int start = offset + this.getValue(rlepos);
         int end = start + this.getLength(rlepos) + 1;
         Util.setBitmapRange(dest, start, end);
      }

   }

   public int nextValue(char fromValue) {
      int index = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
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
      int index = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
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
      int index = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
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
      int index = unsignedInterleavedBinarySearch(this.valueslength, 0, this.nbrruns, fromValue);
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

   public int first() {
      this.assertNonEmpty(this.numberOfRuns() == 0);
      return this.valueslength[0];
   }

   public int last() {
      this.assertNonEmpty(this.numberOfRuns() == 0);
      int index = this.numberOfRuns() - 1;
      int start = this.getValue(index);
      int length = this.getLength(index);
      return start + length;
   }
}
