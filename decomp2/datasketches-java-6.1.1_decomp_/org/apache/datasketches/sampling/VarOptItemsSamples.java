package org.apache.datasketches.sampling;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class VarOptItemsSamples implements Iterable {
   final VarOptItemsSketch sketch_;
   VarOptItemsSketch.Result sampleLists;
   final long n_;
   final int h_;
   final double rWeight_;

   VarOptItemsSamples(VarOptItemsSketch sketch) {
      Objects.requireNonNull(sketch, "sketch must not be null");
      this.sketch_ = sketch;
      this.n_ = sketch.getN();
      this.h_ = sketch.getHRegionCount();
      this.rWeight_ = sketch.getTau();
   }

   public Iterator iterator() {
      return new VarOptItemsIterator();
   }

   Iterator getHIterator() {
      return new VarOptItemsIterator(false);
   }

   Iterator getRIterator() {
      return new VarOptItemsIterator(true);
   }

   Iterator getWeightCorrRIter() {
      return new WeightCorrectingRRegionIterator();
   }

   public void setClass(Class clazz) {
      if (this.sampleLists == null) {
         this.sampleLists = this.sketch_.getSamplesAsArrays(clazz);
      }

   }

   public int getNumSamples() {
      this.loadArrays();
      return this.sampleLists != null && this.sampleLists.weights != null ? this.sampleLists.weights.length : 0;
   }

   public Object[] items() {
      this.loadArrays();
      return this.sampleLists == null ? null : this.sampleLists.items;
   }

   public Object items(int i) {
      this.loadArrays();
      return this.sampleLists != null && this.sampleLists.items != null ? this.sampleLists.items[i] : null;
   }

   public double[] weights() {
      this.loadArrays();
      return this.sampleLists == null ? null : this.sampleLists.weights;
   }

   public double weights(int i) {
      this.loadArrays();
      return this.sampleLists != null && this.sampleLists.weights != null ? this.sampleLists.weights[i] : Double.NaN;
   }

   private void loadArrays() {
      if (this.sampleLists == null) {
         this.sampleLists = this.sketch_.getSamplesAsArrays();
      }

   }

   public final class WeightedSample {
      private final int idx_;
      private double adjustedWeight_;

      WeightedSample(int i) {
         this.idx_ = i;
         this.adjustedWeight_ = Double.NaN;
      }

      WeightedSample(int i, double adjustedWeight) {
         this.idx_ = i;
         this.adjustedWeight_ = adjustedWeight;
      }

      public Object getItem() {
         return VarOptItemsSamples.this.sketch_.getItem(this.idx_);
      }

      public double getWeight() {
         if (this.idx_ > VarOptItemsSamples.this.h_) {
            return Double.isNaN(this.adjustedWeight_) ? VarOptItemsSamples.this.rWeight_ : this.adjustedWeight_;
         } else {
            return VarOptItemsSamples.this.sketch_.getWeight(this.idx_);
         }
      }

      boolean getMark() {
         return VarOptItemsSamples.this.sketch_.getMark(this.idx_);
      }
   }

   public class VarOptItemsIterator implements Iterator {
      int currIdx_;
      int finalIdx_;

      VarOptItemsIterator() {
         this.currIdx_ = VarOptItemsSamples.this.h_ == 0 ? 1 : 0;
         int k = VarOptItemsSamples.this.sketch_.getK();
         this.finalIdx_ = (int)(VarOptItemsSamples.this.n_ <= (long)k ? VarOptItemsSamples.this.n_ - 1L : (long)k);
      }

      VarOptItemsIterator(boolean useRRegion) {
         if (useRRegion) {
            this.currIdx_ = VarOptItemsSamples.this.h_ + 1;
            this.finalIdx_ = VarOptItemsSamples.this.sketch_.getNumSamples();
         } else {
            this.currIdx_ = 0;
            this.finalIdx_ = VarOptItemsSamples.this.h_ - 1;
         }

      }

      public boolean hasNext() {
         return this.currIdx_ <= this.finalIdx_;
      }

      public WeightedSample next() {
         if (VarOptItemsSamples.this.n_ != VarOptItemsSamples.this.sketch_.getN()) {
            throw new ConcurrentModificationException();
         } else if (this.currIdx_ > this.finalIdx_) {
            throw new NoSuchElementException();
         } else {
            int tgt = this.currIdx_++;
            if (this.currIdx_ == VarOptItemsSamples.this.h_ && (long)VarOptItemsSamples.this.h_ != VarOptItemsSamples.this.n_) {
               ++this.currIdx_;
            }

            return VarOptItemsSamples.this.new WeightedSample(tgt);
         }
      }
   }

   class WeightCorrectingRRegionIterator extends VarOptItemsIterator {
      private double cumWeight = (double)0.0F;

      WeightCorrectingRRegionIterator() {
         super(true);
      }

      public WeightedSample next() {
         if (VarOptItemsSamples.this.n_ != VarOptItemsSamples.this.sketch_.getN()) {
            throw new ConcurrentModificationException();
         } else if (this.currIdx_ > this.finalIdx_) {
            throw new NoSuchElementException();
         } else {
            int tgt = this.currIdx_++;
            VarOptItemsSamples<T>.WeightedSample sample;
            if (tgt == this.finalIdx_) {
               sample = VarOptItemsSamples.this.new WeightedSample(tgt, VarOptItemsSamples.this.sketch_.getTotalWtR() - this.cumWeight);
            } else {
               sample = VarOptItemsSamples.this.new WeightedSample(tgt);
               this.cumWeight += VarOptItemsSamples.this.rWeight_;
            }

            return sample;
         }
      }
   }
}
