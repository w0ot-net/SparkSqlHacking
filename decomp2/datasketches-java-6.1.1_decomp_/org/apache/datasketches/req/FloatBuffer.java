package org.apache.datasketches.req;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.quantilescommon.InequalitySearch;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;

class FloatBuffer {
   private float[] arr_;
   private int count_;
   private int capacity_;
   private final int delta_;
   private boolean sorted_;
   private final boolean spaceAtBottom_;

   FloatBuffer(int capacity, int delta, boolean spaceAtBottom) {
      this.arr_ = new float[capacity];
      this.count_ = 0;
      this.capacity_ = capacity;
      this.delta_ = delta;
      this.sorted_ = true;
      this.spaceAtBottom_ = spaceAtBottom;
   }

   FloatBuffer(FloatBuffer buf) {
      this.arr_ = (float[])buf.arr_.clone();
      this.count_ = buf.count_;
      this.capacity_ = buf.capacity_;
      this.delta_ = buf.delta_;
      this.sorted_ = buf.sorted_;
      this.spaceAtBottom_ = buf.spaceAtBottom_;
   }

   private FloatBuffer(float[] arr, int count, int capacity, int delta, boolean sorted, boolean spaceAtBottom) {
      this.arr_ = arr;
      this.count_ = count;
      this.capacity_ = capacity;
      this.delta_ = delta;
      this.sorted_ = sorted;
      this.spaceAtBottom_ = spaceAtBottom;
   }

   static FloatBuffer reconstruct(float[] arr, int count, int capacity, int delta, boolean sorted, boolean sab) {
      float[] farr = new float[capacity];
      if (sab) {
         System.arraycopy(arr, 0, farr, capacity - count, count);
      } else {
         System.arraycopy(arr, 0, farr, 0, count);
      }

      return new FloatBuffer(farr, count, capacity, delta, sorted, sab);
   }

   static FloatBuffer wrap(float[] arr, boolean isSorted, boolean spaceAtBottom) {
      FloatBuffer buf = new FloatBuffer(arr, arr.length, arr.length, 0, isSorted, spaceAtBottom);
      buf.sort();
      return buf;
   }

   FloatBuffer append(float item) {
      this.ensureSpace(1);
      int index = this.spaceAtBottom_ ? this.capacity_ - this.count_ - 1 : this.count_;
      this.arr_[index] = item;
      ++this.count_;
      this.sorted_ = false;
      return this;
   }

   FloatBuffer ensureCapacity(int newCapacity) {
      if (newCapacity > this.capacity_) {
         float[] out = new float[newCapacity];
         int srcPos = this.spaceAtBottom_ ? this.capacity_ - this.count_ : 0;
         int destPos = this.spaceAtBottom_ ? newCapacity - this.count_ : 0;
         System.arraycopy(this.arr_, srcPos, out, destPos, this.count_);
         this.arr_ = out;
         this.capacity_ = newCapacity;
      }

      return this;
   }

   private FloatBuffer ensureSpace(int space) {
      if (this.count_ + space > this.capacity_) {
         int newCap = this.count_ + space + this.delta_;
         this.ensureCapacity(newCap);
      }

      return this;
   }

   float[] getArray() {
      return this.arr_;
   }

   int getCapacity() {
      return this.capacity_;
   }

   int getCountWithCriterion(float item, QuantileSearchCriteria searchCrit) {
      assert !Float.isNaN(item) : "Float items must not be NaN.";

      if (!this.sorted_) {
         this.sort();
      }

      int low = 0;
      int high = this.count_ - 1;
      if (this.spaceAtBottom_) {
         low = this.capacity_ - this.count_;
         high = this.capacity_ - 1;
      }

      InequalitySearch crit = searchCrit == QuantileSearchCriteria.INCLUSIVE ? InequalitySearch.LE : InequalitySearch.LT;
      int index = InequalitySearch.find(this.arr_, low, high, item, crit);
      return index == -1 ? 0 : index - low + 1;
   }

   FloatBuffer getEvensOrOdds(int startOffset, int endOffset, boolean odds) {
      int start = this.spaceAtBottom_ ? this.capacity_ - this.count_ + startOffset : startOffset;
      int end = this.spaceAtBottom_ ? this.capacity_ - this.count_ + endOffset : endOffset;
      this.sort();
      int range = endOffset - startOffset;
      if ((range & 1) == 1) {
         throw new SketchesArgumentException("Input range size must be even");
      } else {
         int odd = odds ? 1 : 0;
         float[] out = new float[range / 2];
         int i = start + odd;

         for(int j = 0; i < end; ++j) {
            out[j] = this.arr_[i];
            i += 2;
         }

         return wrap(out, true, this.spaceAtBottom_);
      }
   }

   float getItemFromIndex(int index) {
      return this.arr_[index];
   }

   float getItem(int offset) {
      int index = this.spaceAtBottom_ ? this.capacity_ - this.count_ + offset : offset;
      return this.arr_[index];
   }

   int getDelta() {
      return this.delta_;
   }

   int getCount() {
      return this.count_;
   }

   int getSpace() {
      return this.capacity_ - this.count_;
   }

   boolean isSpaceAtBottom() {
      return this.spaceAtBottom_;
   }

   boolean isEmpty() {
      return this.count_ == 0;
   }

   boolean isEqualTo(FloatBuffer that) {
      if (this.capacity_ == that.capacity_ && this.count_ == that.count_ && this.delta_ == that.delta_ && this.sorted_ == that.sorted_ && this.spaceAtBottom_ == that.spaceAtBottom_) {
         for(int i = 0; i < this.capacity_; ++i) {
            if (this.arr_[i] != that.arr_[i]) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   boolean isSorted() {
      return this.sorted_;
   }

   FloatBuffer mergeSortIn(FloatBuffer bufIn) {
      if (this.sorted_ && bufIn.isSorted()) {
         float[] arrIn = bufIn.getArray();
         int bufInLen = bufIn.getCount();
         this.ensureSpace(bufInLen);
         int totLen = this.count_ + bufInLen;
         if (this.spaceAtBottom_) {
            int tgtStart = this.capacity_ - totLen;
            int i = this.capacity_ - this.count_;
            int j = bufIn.capacity_ - bufIn.count_;

            for(int k = tgtStart; k < this.capacity_; ++k) {
               if (i < this.capacity_ && j < bufIn.capacity_) {
                  this.arr_[k] = this.arr_[i] <= arrIn[j] ? this.arr_[i++] : arrIn[j++];
               } else if (i < this.capacity_) {
                  this.arr_[k] = this.arr_[i++];
               } else {
                  if (j >= bufIn.capacity_) {
                     break;
                  }

                  this.arr_[k] = arrIn[j++];
               }
            }
         } else {
            int i = this.count_ - 1;
            int j = bufInLen - 1;
            int k = totLen;

            while(k-- > 0) {
               if (i >= 0 && j >= 0) {
                  this.arr_[k] = this.arr_[i] >= arrIn[j] ? this.arr_[i--] : arrIn[j--];
               } else if (i >= 0) {
                  this.arr_[k] = this.arr_[i--];
               } else {
                  if (j < 0) {
                     break;
                  }

                  this.arr_[k] = arrIn[j--];
               }
            }
         }

         this.count_ += bufInLen;
         this.sorted_ = true;
         return this;
      } else {
         throw new SketchesArgumentException("Both buffers must be sorted.");
      }
   }

   FloatBuffer sort() {
      if (this.sorted_) {
         return this;
      } else {
         int start = this.spaceAtBottom_ ? this.capacity_ - this.count_ : 0;
         int end = this.spaceAtBottom_ ? this.capacity_ : this.count_;
         Arrays.sort(this.arr_, start, end);
         this.sorted_ = true;
         return this;
      }
   }

   byte[] floatsToBytes() {
      int bytes = 4 * this.count_;
      byte[] arr = new byte[bytes];
      WritableBuffer wbuf = WritableMemory.writableWrap(arr).asWritableBuffer();
      if (this.spaceAtBottom_) {
         wbuf.putFloatArray(this.arr_, this.capacity_ - this.count_, this.count_);
      } else {
         wbuf.putFloatArray(this.arr_, 0, this.count_);
      }

      assert wbuf.getPosition() == (long)bytes;

      return arr;
   }

   String toHorizList(String fmt, int width) {
      StringBuilder sb = new StringBuilder();
      String spaces = "  ";
      int start = this.spaceAtBottom_ ? this.capacity_ - this.count_ : 0;
      int end = this.spaceAtBottom_ ? this.capacity_ : this.count_;
      int cnt = 0;
      sb.append("  ");

      for(int i = start; i < end; ++i) {
         float v = this.arr_[i];
         String str = String.format(fmt, v);
         if (i > start) {
            ++cnt;
            if (cnt % width == 0) {
               sb.append(Util.LS).append("  ");
            }
         }

         sb.append(str);
      }

      return sb.toString();
   }

   FloatBuffer trimCapacity() {
      if (this.count_ < this.capacity_) {
         float[] out = new float[this.count_];
         int start = this.spaceAtBottom_ ? this.capacity_ - this.count_ : 0;
         System.arraycopy(this.arr_, start, out, 0, this.count_);
         this.capacity_ = this.count_;
         this.arr_ = out;
      }

      return this;
   }

   FloatBuffer trimCount(int newCount) {
      if (newCount < this.count_) {
         this.count_ = newCount;
      }

      return this;
   }
}
