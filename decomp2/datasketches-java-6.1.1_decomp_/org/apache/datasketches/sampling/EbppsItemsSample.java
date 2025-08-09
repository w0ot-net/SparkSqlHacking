package org.apache.datasketches.sampling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;

final class EbppsItemsSample {
   private double c_;
   private Object partialItem_;
   private ArrayList data_;
   private Random rand_;

   EbppsItemsSample(int reservedSize) {
      this.c_ = (double)0.0F;
      this.data_ = new ArrayList(reservedSize);
      this.rand_ = ThreadLocalRandom.current();
   }

   EbppsItemsSample(EbppsItemsSample other) {
      this.c_ = other.c_;
      this.partialItem_ = other.partialItem_;
      this.data_ = new ArrayList(other.data_);
      this.rand_ = other.rand_;
   }

   EbppsItemsSample(ArrayList data, Object partialItem, double c) {
      if (!(c < (double)0.0F) && !Double.isNaN(c) && !Double.isInfinite(c)) {
         this.c_ = c;
         this.partialItem_ = partialItem;
         this.data_ = data;
         this.rand_ = ThreadLocalRandom.current();
      } else {
         throw new SketchesArgumentException("C must be nonnegative and finite. Found: " + c);
      }
   }

   void replaceContent(Object item, double theta) {
      if (!(theta < (double)0.0F) && !(theta > (double)1.0F) && !Double.isNaN(theta)) {
         this.c_ = theta;
         if (theta == (double)1.0F) {
            if (this.data_ != null && this.data_.size() == 1) {
               this.data_.set(0, item);
            } else {
               this.data_ = new ArrayList(1);
               this.data_.add(item);
            }

            this.partialItem_ = null;
         } else {
            this.data_ = null;
            this.partialItem_ = item;
         }

      } else {
         throw new SketchesArgumentException("Theta must be in the range [0.0, 1.0]. Found: " + theta);
      }
   }

   void reset() {
      this.c_ = (double)0.0F;
      this.partialItem_ = null;
      this.data_.clear();
   }

   ArrayList getSample() {
      double cFrac = this.c_ % (double)1.0F;
      boolean includePartial = this.partialItem_ != null && this.rand_.nextDouble() < cFrac;
      int resultSize = (this.data_ != null ? this.data_.size() : 0) + (includePartial ? 1 : 0);
      if (resultSize == 0) {
         return null;
      } else {
         ArrayList<T> result = new ArrayList(resultSize);
         if (this.data_ != null) {
            result.addAll(this.data_);
         }

         if (includePartial) {
            result.add(this.partialItem_);
         }

         return result;
      }
   }

   Object[] getAllSamples(Class clazz) {
      T[] itemsArray = (T[])((Object[])((Object[])Array.newInstance(clazz, this.getNumRetainedItems())));
      int i = 0;
      if (this.data_ != null) {
         for(Object item : this.data_) {
            if (item != null) {
               itemsArray[i++] = item;
            }
         }
      }

      if (this.partialItem_ != null) {
         itemsArray[i] = this.partialItem_;
      }

      return itemsArray;
   }

   ArrayList getFullItems() {
      return this.data_;
   }

   Object getPartialItem() {
      return this.partialItem_;
   }

   double getC() {
      return this.c_;
   }

   boolean hasPartialItem() {
      return this.partialItem_ != null;
   }

   void replaceRandom(Random r) {
      this.rand_ = r;
   }

   void downsample(double theta) {
      if (!(theta >= (double)1.0F)) {
         double newC = theta * this.c_;
         double newCInt = Math.floor(newC);
         double newCFrac = newC % (double)1.0F;
         double cInt = Math.floor(this.c_);
         double cFrac = this.c_ % (double)1.0F;
         if (newCInt == (double)0.0F) {
            if (this.rand_.nextDouble() > cFrac / this.c_) {
               this.swapWithPartialItem();
            }

            this.data_.clear();
         } else if (newCInt == cInt) {
            if (this.rand_.nextDouble() > ((double)1.0F - theta * cFrac) / ((double)1.0F - newCFrac)) {
               this.swapWithPartialItem();
            }
         } else if (this.rand_.nextDouble() < theta * cFrac) {
            this.subsample((int)newCInt);
            this.swapWithPartialItem();
         } else {
            this.subsample((int)newCInt + 1);
            this.moveOneToPartialItem();
         }

         if (newC == newCInt) {
            this.partialItem_ = null;
         }

         this.c_ = newC;
      }
   }

   void merge(EbppsItemsSample other) {
      double cFrac = this.c_ % (double)1.0F;
      double otherCFrac = other.c_ % (double)1.0F;
      this.c_ += other.c_;
      if (other.data_ != null) {
         this.data_.addAll(other.data_);
      }

      if (cFrac == (double)0.0F && otherCFrac == (double)0.0F) {
         this.partialItem_ = null;
      } else if (cFrac + otherCFrac != (double)1.0F && this.c_ != Math.floor(this.c_)) {
         if (cFrac + otherCFrac < (double)1.0F) {
            if (this.rand_.nextDouble() > cFrac / (cFrac + otherCFrac)) {
               this.partialItem_ = other.partialItem_;
            }
         } else if (this.rand_.nextDouble() <= ((double)1.0F - cFrac) / ((double)1.0F - cFrac + ((double)1.0F - otherCFrac))) {
            this.data_.add(other.partialItem_);
         } else {
            this.data_.add(this.partialItem_);
            this.partialItem_ = other.partialItem_;
         }
      } else {
         if (this.rand_.nextDouble() <= cFrac) {
            if (this.partialItem_ != null) {
               this.data_.add(this.partialItem_);
            }
         } else if (other.partialItem_ != null) {
            this.data_.add(other.partialItem_);
         }

         this.partialItem_ = null;
      }

   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("  sample:").append(Util.LS);
      int idx = 0;

      for(Object item : this.data_) {
         sb.append("\t").append(idx++).append(":\t").append(item.toString()).append(Util.LS);
      }

      sb.append("  partial: ");
      if (this.partialItem_ != null) {
         sb.append(this.partialItem_).append(Util.LS);
      } else {
         sb.append("NULL").append(Util.LS);
      }

      return sb.toString();
   }

   void subsample(int numSamples) {
      if (numSamples != this.data_.size()) {
         int dataLen = this.data_.size();

         for(int i = 0; i < numSamples; ++i) {
            int j = i + this.rand_.nextInt(dataLen - i);
            T tmp = (T)this.data_.get(i);
            this.data_.set(i, this.data_.get(j));
            this.data_.set(j, tmp);
         }

         this.data_.subList(numSamples, this.data_.size()).clear();
      }
   }

   void swapWithPartialItem() {
      if (this.partialItem_ == null) {
         this.moveOneToPartialItem();
      } else {
         int idx = this.rand_.nextInt(this.data_.size());
         T tmp = (T)this.partialItem_;
         this.partialItem_ = this.data_.get(idx);
         this.data_.set(idx, tmp);
      }

   }

   void moveOneToPartialItem() {
      int idx = this.rand_.nextInt(this.data_.size());
      int lastIdx = this.data_.size() - 1;
      if (idx != lastIdx) {
         T tmp = (T)this.data_.get(idx);
         this.data_.set(idx, this.data_.get(lastIdx));
         this.partialItem_ = tmp;
      } else {
         this.partialItem_ = this.data_.get(lastIdx);
      }

      this.data_.remove(lastIdx);
   }

   int getNumRetainedItems() {
      return (this.data_ != null ? this.data_.size() : 0) + (this.partialItem_ != null ? 1 : 0);
   }
}
