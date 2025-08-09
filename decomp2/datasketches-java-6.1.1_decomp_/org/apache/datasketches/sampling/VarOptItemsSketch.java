package org.apache.datasketches.sampling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.apache.datasketches.common.ArrayOfBooleansSerDe;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class VarOptItemsSketch {
   private static final int MIN_LG_ARR_ITEMS = 4;
   private static final ResizeFactor DEFAULT_RESIZE_FACTOR;
   private static final ArrayOfBooleansSerDe MARK_SERDE;
   private int k_;
   private int currItemsAlloc_;
   private final ResizeFactor rf_;
   private ArrayList data_;
   private ArrayList weights_;
   private long n_;
   private int h_;
   private int m_;
   private int r_;
   private double totalWtR_;
   private int numMarksInH_;
   private ArrayList marks_;

   private VarOptItemsSketch(int k, ResizeFactor rf) {
      if (k >= 1 && k <= 2147483646) {
         this.k_ = k;
         this.n_ = 0L;
         this.rf_ = rf;
         this.h_ = 0;
         this.m_ = 0;
         this.r_ = 0;
         this.totalWtR_ = (double)0.0F;
         this.numMarksInH_ = 0;
         int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(this.k_), "VarOptItemsSketch");
         int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, this.rf_.lg(), 4);
         this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.k_, 1 << initialLgSize);
         if (this.currItemsAlloc_ == this.k_) {
            ++this.currItemsAlloc_;
         }

         this.data_ = new ArrayList(this.currItemsAlloc_);
         this.weights_ = new ArrayList(this.currItemsAlloc_);
         this.marks_ = null;
      } else {
         throw new SketchesArgumentException("k must be at least 1 and less than 2147483647. Found: " + k);
      }
   }

   private VarOptItemsSketch(ArrayList dataList, ArrayList weightList, int k, long n, int currItemsAlloc, ResizeFactor rf, int hCount, int rCount, double totalWtR) {
      assert dataList != null;

      assert weightList != null;

      assert dataList.size() == weightList.size();

      assert currItemsAlloc >= dataList.size();

      assert k >= 2;

      assert n >= 0L;

      assert hCount >= 0;

      assert rCount >= 0;

      assert rCount == 0 && dataList.size() == hCount || rCount > 0 && dataList.size() == k + 1;

      this.k_ = k;
      this.n_ = n;
      this.h_ = hCount;
      this.r_ = rCount;
      this.m_ = 0;
      this.totalWtR_ = totalWtR;
      this.currItemsAlloc_ = currItemsAlloc;
      this.rf_ = rf;
      this.data_ = dataList;
      this.weights_ = weightList;
      this.numMarksInH_ = 0;
      this.marks_ = null;
   }

   public static VarOptItemsSketch newInstance(int k) {
      return new VarOptItemsSketch(k, DEFAULT_RESIZE_FACTOR);
   }

   public static VarOptItemsSketch newInstance(int k, ResizeFactor rf) {
      return new VarOptItemsSketch(k, rf);
   }

   static VarOptItemsSketch newInstanceAsGadget(int k) {
      VarOptItemsSketch<T> sketch = new VarOptItemsSketch(k, DEFAULT_RESIZE_FACTOR);
      sketch.marks_ = new ArrayList(sketch.currItemsAlloc_);
      return sketch;
   }

   static VarOptItemsSketch newInstanceFromUnionResult(ArrayList dataList, ArrayList weightList, int k, long n, int hCount, int rCount, double totalWtR) {
      VarOptItemsSketch<T> sketch = new VarOptItemsSketch(dataList, weightList, k, n, dataList.size(), DEFAULT_RESIZE_FACTOR, hCount, rCount, totalWtR);
      sketch.convertToHeap();
      return sketch;
   }

   public static VarOptItemsSketch heapify(Memory srcMem, ArrayOfItemsSerDe serDe) {
      int numPreLongs = PreambleUtil.getAndCheckPreLongs(srcMem);
      ResizeFactor rf = ResizeFactor.getRF(PreambleUtil.extractResizeFactor(srcMem));
      int serVer = PreambleUtil.extractSerVer(srcMem);
      int familyId = PreambleUtil.extractFamilyID(srcMem);
      int flags = PreambleUtil.extractFlags(srcMem);
      boolean isEmpty = (flags & 4) != 0;
      boolean isGadget = (flags & 128) != 0;
      if (isEmpty) {
         if (numPreLongs != PreambleUtil.VO_PRELONGS_EMPTY) {
            throw new SketchesArgumentException("Possible corruption: Must be " + PreambleUtil.VO_PRELONGS_EMPTY + " for an empty sketch. Found: " + numPreLongs);
         }
      } else if (numPreLongs != 3 && numPreLongs != PreambleUtil.VO_PRELONGS_FULL) {
         throw new SketchesArgumentException("Possible corruption: Must be 3 or " + PreambleUtil.VO_PRELONGS_FULL + " for a non-empty sketch. Found: " + numPreLongs);
      }

      if (serVer != 2) {
         throw new SketchesArgumentException("Possible Corruption: Ser Ver must be 2: " + serVer);
      } else {
         int reqFamilyId = Family.VAROPT.getID();
         if (familyId != reqFamilyId) {
            throw new SketchesArgumentException("Possible Corruption: FamilyID must be " + reqFamilyId + ": " + familyId);
         } else {
            int k = PreambleUtil.extractK(srcMem);
            if (k < 1) {
               throw new SketchesArgumentException("Possible Corruption: k must be at least 1: " + k);
            } else if (isEmpty) {
               assert numPreLongs == Family.VAROPT.getMinPreLongs();

               return new VarOptItemsSketch(k, rf);
            } else {
               long n = PreambleUtil.extractN(srcMem);
               if (n < 0L) {
                  throw new SketchesArgumentException("Possible Corruption: n cannot be negative: " + n);
               } else {
                  int hCount = PreambleUtil.extractHRegionItemCount(srcMem);
                  int rCount = PreambleUtil.extractRRegionItemCount(srcMem);
                  if (hCount < 0) {
                     throw new SketchesArgumentException("Possible Corruption: H region count cannot be negative: " + hCount);
                  } else if (rCount < 0) {
                     throw new SketchesArgumentException("Possible Corruption: R region count cannot be negative: " + rCount);
                  } else {
                     double totalRWeight = (double)0.0F;
                     if (numPreLongs == Family.VAROPT.getMaxPreLongs()) {
                        if (rCount <= 0) {
                           throw new SketchesArgumentException("Possible Corruption: " + Family.VAROPT.getMaxPreLongs() + " preLongs but no items in R region");
                        }

                        totalRWeight = PreambleUtil.extractTotalRWeight(srcMem);
                     }

                     int preLongBytes = numPreLongs << 3;
                     int totalItems = hCount + rCount;
                     int allocatedItems = k + 1;
                     if (rCount == 0) {
                        int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(k), "heapify");
                        int minLgSize = Util.exactLog2OfInt(Util.ceilingPowerOf2(hCount), "heapify");
                        int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, rf.lg(), Math.max(minLgSize, 4));
                        allocatedItems = SamplingUtil.getAdjustedSize(k, 1 << initialLgSize);
                        if (allocatedItems == k) {
                           ++allocatedItems;
                        }
                     }

                     long weightOffsetBytes = (long)(24 + (rCount > 0 ? 8 : 0));
                     ArrayList<Double> weightList = new ArrayList(allocatedItems);
                     double[] wts = new double[allocatedItems];
                     srcMem.getDoubleArray(weightOffsetBytes, wts, 0, hCount);

                     for(int i = 0; i < hCount; ++i) {
                        if (wts[i] <= (double)0.0F) {
                           throw new SketchesArgumentException("Possible Corruption: Non-positive weight in heapify(): " + wts[i]);
                        }

                        weightList.add(wts[i]);
                     }

                     long markBytes = 0L;
                     int markCount = 0;
                     ArrayList<Boolean> markList = null;
                     if (isGadget) {
                        long markOffsetBytes = (long)preLongBytes + (long)hCount * 8L;
                        markBytes = (long)ArrayOfBooleansSerDe.computeBytesNeeded(hCount);
                        markList = new ArrayList(allocatedItems);
                        ArrayOfBooleansSerDe booleansSerDe = new ArrayOfBooleansSerDe();
                        Boolean[] markArray = booleansSerDe.deserializeFromMemory(srcMem.region(markOffsetBytes, (long)((hCount >>> 3) + 1)), 0L, hCount);

                        for(Boolean mark : markArray) {
                           if (mark) {
                              ++markCount;
                           }
                        }

                        markList.addAll(Arrays.asList(markArray));
                     }

                     long offsetBytes = (long)preLongBytes + (long)hCount * 8L + markBytes;
                     T[] data = (T[])serDe.deserializeFromMemory(srcMem.region(offsetBytes, srcMem.getCapacity() - offsetBytes), 0L, totalItems);
                     List<T> wrappedData = Arrays.asList(data);
                     ArrayList<T> dataList = new ArrayList(allocatedItems);
                     dataList.addAll(wrappedData.subList(0, hCount));
                     if (rCount > 0) {
                        weightList.add((double)-1.0F);
                        if (isGadget) {
                           markList.add(false);
                        }

                        for(int i = 0; i < rCount; ++i) {
                           weightList.add((double)-1.0F);
                           if (isGadget) {
                              markList.add(false);
                           }
                        }

                        dataList.add((Object)null);
                        dataList.addAll(wrappedData.subList(hCount, totalItems));
                     }

                     VarOptItemsSketch<T> sketch = new VarOptItemsSketch(dataList, weightList, k, n, allocatedItems, rf, hCount, rCount, totalRWeight);
                     if (isGadget) {
                        sketch.marks_ = markList;
                        sketch.numMarksInH_ = markCount;
                     }

                     return sketch;
                  }
               }
            }
         }
      }
   }

   public int getK() {
      return this.k_;
   }

   public long getN() {
      return this.n_;
   }

   public int getNumSamples() {
      return Math.min(this.k_, this.h_ + this.r_);
   }

   public VarOptItemsSamples getSketchSamples() {
      return new VarOptItemsSamples(this);
   }

   public void update(Object item, double weight) {
      this.update(item, weight, false);
   }

   public void reset() {
      int ceilingLgK = Util.exactLog2OfInt(Util.ceilingPowerOf2(this.k_), "VarOptItemsSketch");
      int initialLgSize = SamplingUtil.startingSubMultiple(ceilingLgK, this.rf_.lg(), 4);
      this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.k_, 1 << initialLgSize);
      if (this.currItemsAlloc_ == this.k_) {
         ++this.currItemsAlloc_;
      }

      this.data_ = new ArrayList(this.currItemsAlloc_);
      this.weights_ = new ArrayList(this.currItemsAlloc_);
      if (this.marks_ != null) {
         this.marks_ = new ArrayList(this.currItemsAlloc_);
      }

      this.n_ = 0L;
      this.h_ = 0;
      this.m_ = 0;
      this.r_ = 0;
      this.numMarksInH_ = 0;
      this.totalWtR_ = (double)0.0F;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append(Util.LS);
      sb.append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("   k            : ").append(this.k_).append(Util.LS);
      sb.append("   h            : ").append(this.h_).append(Util.LS);
      sb.append("   r            : ").append(this.r_).append(Util.LS);
      sb.append("   weight_r     : ").append(this.totalWtR_).append(Util.LS);
      sb.append("   Current size : ").append(this.currItemsAlloc_).append(Util.LS);
      sb.append("   Resize factor: ").append(this.rf_).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      return sb.toString();
   }

   public static String toString(byte[] byteArr) {
      return PreambleUtil.preambleToString(byteArr);
   }

   public static String toString(Memory mem) {
      return PreambleUtil.preambleToString(mem);
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      if (this.r_ == 0 && this.h_ == 0) {
         return this.toByteArray(serDe, (Class)null);
      } else {
         int validIndex = this.h_ == 0 ? 1 : 0;
         Class<?> clazz = this.data_.get(validIndex).getClass();
         return this.toByteArray(serDe, clazz);
      }
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe, Class clazz) {
      boolean empty = this.r_ == 0 && this.h_ == 0;
      byte[] itemBytes = null;
      int flags = this.marks_ == null ? 0 : 128;
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = Family.VAROPT.getMinPreLongs();
         outBytes = Family.VAROPT.getMinPreLongs() << 3;
         flags |= 4;
      } else {
         preLongs = this.r_ == 0 ? 3 : Family.VAROPT.getMaxPreLongs();
         itemBytes = serDe.serializeToByteArray(this.getDataSamples(clazz));
         int numMarkBytes = this.marks_ == null ? 0 : ArrayOfBooleansSerDe.computeBytesNeeded(this.h_);
         outBytes = (preLongs << 3) + this.h_ * 8 + numMarkBytes + itemBytes.length;
      }

      byte[] outArr = new byte[outBytes];
      WritableMemory mem = WritableMemory.writableWrap(outArr);
      PreambleUtil.insertPreLongs(mem, preLongs);
      PreambleUtil.insertLgResizeFactor(mem, this.rf_.lg());
      PreambleUtil.insertSerVer(mem, 2);
      PreambleUtil.insertFamilyID(mem, Family.VAROPT.getID());
      PreambleUtil.insertFlags(mem, flags);
      PreambleUtil.insertK(mem, this.k_);
      if (!empty) {
         PreambleUtil.insertN(mem, this.n_);
         PreambleUtil.insertHRegionItemCount(mem, this.h_);
         PreambleUtil.insertRRegionItemCount(mem, this.r_);
         if (this.r_ > 0) {
            PreambleUtil.insertTotalRWeight(mem, this.totalWtR_);
         }

         int offset = preLongs << 3;

         for(int i = 0; i < this.h_; ++i) {
            mem.putDouble((long)offset, (Double)this.weights_.get(i));
            offset += 8;
         }

         if (this.marks_ != null) {
            byte[] markBytes = MARK_SERDE.serializeToByteArray((Boolean[])this.marks_.subList(0, this.h_).toArray(new Boolean[0]));
            mem.putByteArray((long)offset, markBytes, 0, markBytes.length);
            offset += markBytes.length;
         }

         mem.putByteArray((long)offset, itemBytes, 0, itemBytes.length);
      }

      return outArr;
   }

   public SampleSubsetSummary estimateSubsetSum(Predicate predicate) {
      if (this.n_ == 0L) {
         return new SampleSubsetSummary((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
      } else {
         double totalWtH = (double)0.0F;
         double hTrueWeight = (double)0.0F;

         int idx;
         for(idx = 0; idx < this.h_; ++idx) {
            double wt = (Double)this.weights_.get(idx);
            totalWtH += wt;
            if (predicate.test(this.data_.get(idx))) {
               hTrueWeight += wt;
            }
         }

         if (this.r_ == 0) {
            return new SampleSubsetSummary(hTrueWeight, hTrueWeight, hTrueWeight, hTrueWeight);
         } else {
            long numSampled = this.n_ - (long)this.h_;

            assert numSampled > 0L;

            double effectiveSamplingRate = (double)this.r_ / (double)numSampled;

            assert effectiveSamplingRate >= (double)0.0F;

            assert effectiveSamplingRate <= (double)1.0F;

            int rTrueCount = 0;
            ++idx;

            for(; idx < this.k_ + 1; ++idx) {
               if (predicate.test(this.data_.get(idx))) {
                  ++rTrueCount;
               }
            }

            double lbTrueFraction = SamplingUtil.pseudoHypergeometricLBonP((long)this.r_, rTrueCount, effectiveSamplingRate);
            double estimatedTrueFraction = (double)1.0F * (double)rTrueCount / (double)this.r_;
            double ubTrueFraction = SamplingUtil.pseudoHypergeometricUBonP((long)this.r_, rTrueCount, effectiveSamplingRate);
            return new SampleSubsetSummary(hTrueWeight + this.totalWtR_ * lbTrueFraction, hTrueWeight + this.totalWtR_ * estimatedTrueFraction, hTrueWeight + this.totalWtR_ * ubTrueFraction, totalWtH + this.totalWtR_);
         }
      }
   }

   Result getSamplesAsArrays() {
      if (this.r_ + this.h_ == 0) {
         return null;
      } else {
         int validIndex = this.h_ == 0 ? 1 : 0;
         Class<?> clazz = this.data_.get(validIndex).getClass();
         return this.getSamplesAsArrays(clazz);
      }
   }

   VarOptItemsSketch copyAndSetN(boolean asSketch, long adjustedN) {
      VarOptItemsSketch<T> sketch = new VarOptItemsSketch(this.data_, this.weights_, this.k_, this.n_, this.currItemsAlloc_, this.rf_, this.h_, this.r_, this.totalWtR_);
      if (!asSketch) {
         sketch.marks_ = this.marks_;
         sketch.numMarksInH_ = this.numMarksInH_;
      }

      if (adjustedN >= 0L) {
         sketch.n_ = adjustedN;
      }

      return sketch;
   }

   void stripMarks() {
      assert this.marks_ != null;

      this.numMarksInH_ = 0;
      this.marks_ = null;
   }

   Result getSamplesAsArrays(Class clazz) {
      if (this.r_ + this.h_ == 0) {
         return null;
      } else {
         int numSamples = this.getNumSamples();
         T[] prunedItems = (T[])((Object[])((Object[])Array.newInstance(clazz, numSamples)));
         double[] prunedWeights = new double[numSamples];
         int j = 0;
         double rWeight = this.totalWtR_ / (double)this.r_;

         for(int i = 0; j < numSamples; ++i) {
            T item = (T)this.data_.get(i);
            if (item != null) {
               prunedItems[j] = item;
               prunedWeights[j] = (Double)this.weights_.get(i) > (double)0.0F ? (Double)this.weights_.get(i) : rWeight;
               ++j;
            }
         }

         VarOptItemsSketch<T>.Result output = new Result();
         output.items = prunedItems;
         output.weights = prunedWeights;
         return output;
      }
   }

   Object getItem(int idx) {
      return this.data_.get(idx);
   }

   double getWeight(int idx) {
      return (Double)this.weights_.get(idx);
   }

   boolean getMark(int idx) {
      return (Boolean)this.marks_.get(idx);
   }

   int getHRegionCount() {
      return this.h_;
   }

   int getRRegionCount() {
      return this.r_;
   }

   int getNumMarksInH() {
      return this.numMarksInH_;
   }

   double getTau() {
      return this.r_ == 0 ? Double.NaN : this.totalWtR_ / (double)this.r_;
   }

   double getTotalWtR() {
      return this.totalWtR_;
   }

   void forceSetK(int k) {
      assert k > 0;

      this.k_ = k;
   }

   void update(Object item, double weight, boolean mark) {
      if (item != null) {
         if (weight <= (double)0.0F) {
            throw new SketchesArgumentException("Item weights must be strictly positive: " + weight + ", for item " + item.toString());
         } else {
            ++this.n_;
            if (this.r_ == 0) {
               this.updateWarmupPhase(item, weight, mark);
            } else {
               assert this.h_ == 0 || this.peekMin() >= this.getTau();

               double hypotheticalTau = (weight + this.totalWtR_) / (double)(this.r_ + 1 - 1);
               boolean condition1 = this.h_ == 0 || weight <= this.peekMin();
               boolean condition2 = weight < hypotheticalTau;
               if (condition1 && condition2) {
                  this.updateLight(item, weight, mark);
               } else if (this.r_ == 1) {
                  this.updateHeavyREq1(item, weight, mark);
               } else {
                  this.updateHeavyGeneral(item, weight, mark);
               }
            }

         }
      }
   }

   void decreaseKBy1() {
      if (this.k_ <= 1) {
         throw new SketchesStateException("Cannot decrease k below 1 in union");
      } else {
         if (this.h_ == 0 && this.r_ == 0) {
            --this.k_;
         } else if (this.h_ > 0 && this.r_ == 0) {
            --this.k_;
            if (this.h_ > this.k_) {
               this.transitionFromWarmup();
            }
         } else if (this.h_ > 0 && this.r_ > 0) {
            int oldGapIdx = this.h_;
            int oldFinalRIdx = this.h_ + 1 + this.r_ - 1;

            assert oldFinalRIdx == this.k_;

            this.swapValues(oldFinalRIdx, oldGapIdx);
            int pulledIdx = this.h_ - 1;
            T pulledItem = (T)this.data_.get(pulledIdx);
            double pulledWeight = (Double)this.weights_.get(pulledIdx);
            boolean pulledMark = (Boolean)this.marks_.get(pulledIdx);
            if (pulledMark) {
               --this.numMarksInH_;
            }

            this.weights_.set(pulledIdx, (double)-1.0F);
            --this.h_;
            --this.k_;
            --this.n_;
            this.update(pulledItem, pulledWeight, pulledMark);
         } else if (this.h_ == 0 && this.r_ > 0) {
            assert this.r_ >= 2;

            int rIdxToDelete = 1 + SamplingUtil.rand().nextInt(this.r_);
            int rightmostRIdx = 1 + this.r_ - 1;
            this.swapValues(rIdxToDelete, rightmostRIdx);
            this.weights_.set(rightmostRIdx, (double)-1.0F);
            --this.k_;
            --this.r_;
         }

      }
   }

   private void updateLight(Object item, double weight, boolean mark) {
      assert this.r_ >= 1;

      assert this.r_ + this.h_ == this.k_;

      int mSlot = this.h_;
      this.data_.set(mSlot, item);
      this.weights_.set(mSlot, weight);
      if (this.marks_ != null) {
         this.marks_.set(mSlot, mark);
      }

      ++this.m_;
      this.growCandidateSet(this.totalWtR_ + weight, this.r_ + 1);
   }

   private void updateHeavyGeneral(Object item, double weight, boolean mark) {
      assert this.m_ == 0;

      assert this.r_ >= 2;

      assert this.r_ + this.h_ == this.k_;

      this.push(item, weight, mark);
      this.growCandidateSet(this.totalWtR_, this.r_);
   }

   private void updateHeavyREq1(Object item, double weight, boolean mark) {
      assert this.m_ == 0;

      assert this.r_ == 1;

      assert this.r_ + this.h_ == this.k_;

      this.push(item, weight, mark);
      this.popMinToMRegion();
      int mSlot = this.k_ - 1;
      this.growCandidateSet((Double)this.weights_.get(mSlot) + this.totalWtR_, 2);
   }

   private void updateWarmupPhase(Object item, double wt, boolean mark) {
      assert this.r_ == 0;

      assert this.m_ == 0;

      assert this.h_ <= this.k_;

      if (this.h_ >= this.currItemsAlloc_) {
         this.growDataArrays();
      }

      this.data_.add(this.h_, item);
      this.weights_.add(this.h_, wt);
      if (this.marks_ != null) {
         this.marks_.add(this.h_, mark);
      }

      ++this.h_;
      this.numMarksInH_ += mark ? 1 : 0;
      if (this.h_ > this.k_) {
         this.transitionFromWarmup();
      }

   }

   private void transitionFromWarmup() {
      this.convertToHeap();
      this.popMinToMRegion();
      this.popMinToMRegion();
      --this.m_;
      ++this.r_;

      assert this.h_ == this.k_ - 1;

      assert this.m_ == 1;

      assert this.r_ == 1;

      this.totalWtR_ = (Double)this.weights_.get(this.k_);
      this.weights_.set(this.k_, (double)-1.0F);
      this.growCandidateSet((Double)this.weights_.get(this.k_ - 1) + this.totalWtR_, 2);
   }

   private void convertToHeap() {
      if (this.h_ >= 2) {
         int lastSlot = this.h_ - 1;
         int lastNonLeaf = (lastSlot + 1) / 2 - 1;

         for(int j = lastNonLeaf; j >= 0; --j) {
            this.restoreTowardsLeaves(j);
         }

      }
   }

   private void restoreTowardsLeaves(int slotIn) {
      assert this.h_ > 0;

      int lastSlot = this.h_ - 1;

      assert slotIn <= lastSlot;

      int slot = slotIn;

      for(int child = 2 * slotIn + 1; child <= lastSlot; child = 2 * child + 1) {
         int child2 = child + 1;
         if (child2 <= lastSlot && (Double)this.weights_.get(child2) < (Double)this.weights_.get(child)) {
            child = child2;
         }

         if ((Double)this.weights_.get(slot) <= (Double)this.weights_.get(child)) {
            break;
         }

         this.swapValues(slot, child);
         slot = child;
      }

   }

   private void restoreTowardsRoot(int slotIn) {
      int slot = slotIn;

      for(int p = (slotIn + 1) / 2 - 1; slot > 0 && (Double)this.weights_.get(slot) < (Double)this.weights_.get(p); p = (p + 1) / 2 - 1) {
         this.swapValues(slot, p);
         slot = p;
      }

   }

   private void push(Object item, double wt, boolean mark) {
      this.data_.set(this.h_, item);
      this.weights_.set(this.h_, wt);
      if (this.marks_ != null) {
         this.marks_.set(this.h_, mark);
         this.numMarksInH_ += mark ? 1 : 0;
      }

      ++this.h_;
      this.restoreTowardsRoot(this.h_ - 1);
   }

   private double peekMin() {
      assert this.h_ > 0;

      return (Double)this.weights_.get(0);
   }

   private void popMinToMRegion() {
      assert this.h_ > 0;

      assert this.h_ + this.m_ + this.r_ == this.k_ + 1;

      if (this.h_ == 1) {
         ++this.m_;
         --this.h_;
      } else {
         int tgt = this.h_ - 1;
         this.swapValues(0, tgt);
         ++this.m_;
         --this.h_;
         this.restoreTowardsLeaves(0);
      }

      if (this.isMarked(this.h_)) {
         --this.numMarksInH_;
      }

   }

   private void growCandidateSet(double wtCands, int numCands) {
      assert this.h_ + this.m_ + this.r_ == this.k_ + 1;

      assert numCands >= 2;

      assert numCands == this.m_ + this.r_;

      assert this.m_ == 0 || this.m_ == 1;

      while(this.h_ > 0) {
         double nextWt = this.peekMin();
         double nextTotWt = wtCands + nextWt;
         if (!(nextWt * (double)numCands < nextTotWt)) {
            break;
         }

         wtCands = nextTotWt;
         ++numCands;
         this.popMinToMRegion();
      }

      this.downsampleCandidateSet(wtCands, numCands);
   }

   private int pickRandomSlotInR() {
      assert this.r_ > 0;

      int offset = this.h_ + this.m_;
      return this.r_ == 1 ? offset : offset + SamplingUtil.rand().nextInt(this.r_);
   }

   private int chooseDeleteSlot(double wtCand, int numCand) {
      assert this.r_ > 0;

      if (this.m_ == 0) {
         return this.pickRandomSlotInR();
      } else if (this.m_ == 1) {
         double wtMCand = (Double)this.weights_.get(this.h_);
         return wtCand * SamplingUtil.nextDoubleExcludeZero() < (double)(numCand - 1) * wtMCand ? this.pickRandomSlotInR() : this.h_;
      } else {
         int deleteSlot = this.chooseWeightedDeleteSlot(wtCand, numCand);
         int firstRSlot = this.h_ + this.m_;
         return deleteSlot == firstRSlot ? this.pickRandomSlotInR() : deleteSlot;
      }
   }

   private int chooseWeightedDeleteSlot(double wtCand, int numCand) {
      assert this.m_ >= 1;

      int offset = this.h_;
      int finalM = offset + this.m_ - 1;
      int numToKeep = numCand - 1;
      double leftSubtotal = (double)0.0F;
      double rightSubtotal = (double)-1.0F * wtCand * SamplingUtil.nextDoubleExcludeZero();

      for(int i = offset; i <= finalM; ++i) {
         leftSubtotal += (double)numToKeep * (Double)this.weights_.get(i);
         rightSubtotal += wtCand;
         if (leftSubtotal < rightSubtotal) {
            return i;
         }
      }

      return finalM + 1;
   }

   private void downsampleCandidateSet(double wtCands, int numCands) {
      assert numCands >= 2;

      assert this.h_ + numCands == this.k_ + 1;

      int deleteSlot = this.chooseDeleteSlot(wtCands, numCands);
      int leftmostCandSlot = this.h_;

      assert deleteSlot >= leftmostCandSlot;

      assert deleteSlot <= this.k_;

      int stopIdx = leftmostCandSlot + this.m_;

      for(int j = leftmostCandSlot; j < stopIdx; ++j) {
         this.weights_.set(j, (double)-1.0F);
      }

      this.data_.set(deleteSlot, this.data_.get(leftmostCandSlot));
      this.data_.set(leftmostCandSlot, (Object)null);
      this.m_ = 0;
      this.r_ = numCands - 1;
      this.totalWtR_ = wtCands;
   }

   private void swapValues(int src, int dst) {
      T item = (T)this.data_.get(src);
      this.data_.set(src, this.data_.get(dst));
      this.data_.set(dst, item);
      Double wt = (Double)this.weights_.get(src);
      this.weights_.set(src, this.weights_.get(dst));
      this.weights_.set(dst, wt);
      if (this.marks_ != null) {
         Boolean mark = (Boolean)this.marks_.get(src);
         this.marks_.set(src, this.marks_.get(dst));
         this.marks_.set(dst, mark);
      }

   }

   private boolean isMarked(int idx) {
      return this.marks_ != null ? (Boolean)this.marks_.get(idx) : false;
   }

   private Object[] getDataSamples(Class clazz) {
      assert this.h_ + this.r_ > 0;

      T[] prunedList = (T[])((Object[])((Object[])Array.newInstance(clazz, this.getNumSamples())));
      int i = 0;

      for(Object item : this.data_) {
         if (item != null) {
            prunedList[i++] = item;
         }
      }

      return prunedList;
   }

   private void growDataArrays() {
      this.currItemsAlloc_ = SamplingUtil.getAdjustedSize(this.k_, this.currItemsAlloc_ << this.rf_.lg());
      if (this.currItemsAlloc_ == this.k_) {
         ++this.currItemsAlloc_;
      }

      this.data_.ensureCapacity(this.currItemsAlloc_);
      this.weights_.ensureCapacity(this.currItemsAlloc_);
      if (this.marks_ != null) {
         this.marks_.ensureCapacity(this.currItemsAlloc_);
      }

   }

   static {
      DEFAULT_RESIZE_FACTOR = ResizeFactor.X8;
      MARK_SERDE = new ArrayOfBooleansSerDe();
   }

   class Result {
      Object[] items;
      double[] weights;
   }
}
