package org.apache.datasketches.sampling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class EbppsItemsSketch {
   private static final int MAX_K = 2147483645;
   private static final int EBPPS_C_DOUBLE = 40;
   private static final int EBPPS_ITEMS_START = 48;
   private int k_;
   private long n_;
   private double cumulativeWt_;
   private double wtMax_;
   private double rho_;
   private EbppsItemsSample sample_;
   private final EbppsItemsSample tmp_;

   public EbppsItemsSketch(int k) {
      checkK(k);
      this.k_ = k;
      this.rho_ = (double)1.0F;
      this.sample_ = new EbppsItemsSample(k);
      this.tmp_ = new EbppsItemsSample(1);
   }

   private EbppsItemsSketch(EbppsItemsSketch other) {
      this.k_ = other.k_;
      this.n_ = other.n_;
      this.rho_ = other.rho_;
      this.cumulativeWt_ = other.cumulativeWt_;
      this.wtMax_ = other.wtMax_;
      this.sample_ = new EbppsItemsSample(other.sample_);
      this.tmp_ = new EbppsItemsSample(1);
   }

   private EbppsItemsSketch(EbppsItemsSample sample, int k, long n, double cumWt, double maxWt, double rho) {
      this.k_ = k;
      this.n_ = n;
      this.cumulativeWt_ = cumWt;
      this.wtMax_ = maxWt;
      this.rho_ = rho;
      this.sample_ = sample;
      this.tmp_ = new EbppsItemsSample(1);
   }

   public static EbppsItemsSketch heapify(Memory srcMem, ArrayOfItemsSerDe serDe) {
      int numPreLongs = PreambleUtil.getAndCheckPreLongs(srcMem);
      int serVer = PreambleUtil.extractSerVer(srcMem);
      int familyId = PreambleUtil.extractFamilyID(srcMem);
      int flags = PreambleUtil.extractFlags(srcMem);
      boolean isEmpty = (flags & 4) != 0;
      boolean hasPartialItem = (flags & 8) != 0;
      if (isEmpty) {
         if (numPreLongs != Family.EBPPS.getMinPreLongs()) {
            throw new SketchesArgumentException("Possible corruption: Must be " + Family.EBPPS.getMinPreLongs() + " for an empty sketch. Found: " + numPreLongs);
         }
      } else if (numPreLongs != Family.EBPPS.getMaxPreLongs()) {
         throw new SketchesArgumentException("Possible corruption: Must be " + Family.EBPPS.getMaxPreLongs() + " for a non-empty sketch. Found: " + numPreLongs);
      }

      if (serVer != 1) {
         throw new SketchesArgumentException("Possible Corruption: Ser Ver must be 1: " + serVer);
      } else {
         int reqFamilyId = Family.EBPPS.getID();
         if (familyId != reqFamilyId) {
            throw new SketchesArgumentException("Possible Corruption: FamilyID must be " + reqFamilyId + ": " + familyId);
         } else {
            int k = PreambleUtil.extractK(srcMem);
            if (k >= 1 && k <= 2147483645) {
               if (isEmpty) {
                  return new EbppsItemsSketch(k);
               } else {
                  long n = PreambleUtil.extractN(srcMem);
                  if (n < 0L) {
                     throw new SketchesArgumentException("Possible Corruption: n cannot be negative: " + n);
                  } else {
                     double cumWt = PreambleUtil.extractEbppsCumulativeWeight(srcMem);
                     if (!(cumWt < (double)0.0F) && !Double.isNaN(cumWt) && !Double.isInfinite(cumWt)) {
                        double maxWt = PreambleUtil.extractEbppsMaxWeight(srcMem);
                        if (!(maxWt < (double)0.0F) && !Double.isNaN(maxWt) && !Double.isInfinite(maxWt)) {
                           double rho = PreambleUtil.extractEbppsRho(srcMem);
                           if (!(rho < (double)0.0F) && !(rho > (double)1.0F) && !Double.isNaN(rho) && !Double.isInfinite(rho)) {
                              double c = srcMem.getDouble(40L);
                              if (!(c < (double)0.0F) && !(c >= (double)(k + 1)) && !Double.isNaN(c) && !Double.isInfinite(c)) {
                                 int numTotalItems = (int)Math.ceil(c);
                                 int numFullItems = (int)Math.floor(c);
                                 int offsetBytes = 48;
                                 T[] rawItems = (T[])serDe.deserializeFromMemory(srcMem.region(48L, srcMem.getCapacity() - 48L), 0L, numTotalItems);
                                 List<T> itemsList = Arrays.asList(rawItems);
                                 ArrayList<T> data;
                                 T partialItem;
                                 if (hasPartialItem) {
                                    if (numFullItems >= numTotalItems) {
                                       throw new SketchesArgumentException("Possible Corruption: Expected partial item but none found");
                                    }

                                    data = new ArrayList(itemsList.subList(0, numFullItems));
                                    partialItem = (T)itemsList.get(numFullItems);
                                 } else {
                                    data = new ArrayList(itemsList);
                                    partialItem = (T)null;
                                 }

                                 EbppsItemsSample<T> sample = new EbppsItemsSample(data, partialItem, c);
                                 return new EbppsItemsSketch(sample, k, n, cumWt, maxWt, rho);
                              } else {
                                 throw new SketchesArgumentException("Possible Corruption: c must be between 0 and k: " + c);
                              }
                           } else {
                              throw new SketchesArgumentException("Possible Corruption: rho must be in [0.0, 1.0]: " + rho);
                           }
                        } else {
                           throw new SketchesArgumentException("Possible Corruption: maxWt must be nonnegative and finite: " + maxWt);
                        }
                     } else {
                        throw new SketchesArgumentException("Possible Corruption: cumWt must be nonnegative and finite: " + cumWt);
                     }
                  }
               }
            } else {
               throw new SketchesArgumentException("Possible Corruption: k must be at least 1 and less than 2147483645. Found: " + k);
            }
         }
      }
   }

   public void update(Object item) {
      this.update(item, (double)1.0F);
   }

   public void update(Object item, double weight) {
      if (!(weight < (double)0.0F) && !Double.isNaN(weight) && !Double.isInfinite(weight)) {
         if (weight != (double)0.0F) {
            double newCumWt = this.cumulativeWt_ + weight;
            double newWtMax = Math.max(this.wtMax_, weight);
            double newRho = Math.min((double)1.0F / newWtMax, (double)this.k_ / newCumWt);
            if (this.cumulativeWt_ > (double)0.0F) {
               this.sample_.downsample(newRho / this.rho_);
            }

            this.tmp_.replaceContent(item, newRho * weight);
            this.sample_.merge(this.tmp_);
            this.cumulativeWt_ = newCumWt;
            this.wtMax_ = newWtMax;
            this.rho_ = newRho;
            ++this.n_;
         }
      } else {
         throw new SketchesArgumentException("Item weights must be nonnegative and finite. Found: " + weight);
      }
   }

   public void merge(EbppsItemsSketch other) {
      if (other.getCumulativeWeight() != (double)0.0F) {
         if (other.getCumulativeWeight() > this.cumulativeWt_) {
            EbppsItemsSketch<T> copy = new EbppsItemsSketch(other);
            copy.internalMerge(this);
            this.k_ = copy.k_;
            this.n_ = copy.n_;
            this.cumulativeWt_ = copy.cumulativeWt_;
            this.wtMax_ = copy.wtMax_;
            this.rho_ = copy.rho_;
            this.sample_ = copy.sample_;
         } else {
            this.internalMerge(other);
         }

      }
   }

   private void internalMerge(EbppsItemsSketch other) {
      double finalCumWt = this.cumulativeWt_ + other.cumulativeWt_;
      double newWtMax = Math.max(this.wtMax_, other.wtMax_);
      this.k_ = Math.min(this.k_, other.k_);
      long newN = this.n_ + other.n_;
      double avgWt = other.cumulativeWt_ / other.getC();
      ArrayList<T> items = other.sample_.getFullItems();
      if (items != null) {
         for(Object item : items) {
            double newCumWt = this.cumulativeWt_ + avgWt;
            double newRho = Math.min((double)1.0F / newWtMax, (double)this.k_ / newCumWt);
            if (this.cumulativeWt_ > (double)0.0F) {
               this.sample_.downsample(newRho / this.rho_);
            }

            this.tmp_.replaceContent(item, newRho * avgWt);
            this.sample_.merge(this.tmp_);
            this.cumulativeWt_ = newCumWt;
            this.rho_ = newRho;
         }
      }

      if (other.sample_.hasPartialItem()) {
         double otherCFrac = other.getC() % (double)1.0F;
         double newCumWt = this.cumulativeWt_ + otherCFrac * avgWt;
         double newRho = Math.min((double)1.0F / newWtMax, (double)this.k_ / newCumWt);
         if (this.cumulativeWt_ > (double)0.0F) {
            this.sample_.downsample(newRho / this.rho_);
         }

         this.tmp_.replaceContent(other.sample_.getPartialItem(), newRho * otherCFrac * avgWt);
         this.sample_.merge(this.tmp_);
         this.rho_ = newRho;
      }

      this.cumulativeWt_ = finalCumWt;
      this.n_ = newN;
   }

   public ArrayList getResult() {
      return this.sample_.getSample();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(Util.LS);
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("   k            : ").append(this.k_).append(Util.LS);
      sb.append("   n            : ").append(this.n_).append(Util.LS);
      sb.append("   Cum. weight  : ").append(this.cumulativeWt_).append(Util.LS);
      sb.append("   wtMax        : ").append(this.wtMax_).append(Util.LS);
      sb.append("   rho          : ").append(this.rho_).append(Util.LS);
      sb.append("   C            : ").append(this.sample_.getC()).append(Util.LS);
      sb.append("### END SKETCH SUMMARY").append(Util.LS);
      return sb.toString();
   }

   public int getK() {
      return this.k_;
   }

   public long getN() {
      return this.n_;
   }

   public double getCumulativeWeight() {
      return this.cumulativeWt_;
   }

   public double getC() {
      return this.sample_.getC();
   }

   public boolean isEmpty() {
      return this.n_ == 0L;
   }

   public void reset() {
      this.n_ = 0L;
      this.cumulativeWt_ = (double)0.0F;
      this.wtMax_ = (double)0.0F;
      this.rho_ = (double)1.0F;
      this.sample_ = new EbppsItemsSample(this.k_);
   }

   public int getSerializedSizeBytes(ArrayOfItemsSerDe serDe) {
      if (this.isEmpty()) {
         return Family.EBPPS.getMinPreLongs() << 3;
      } else {
         return this.sample_.getC() < (double)1.0F ? this.getSerializedSizeBytes(serDe, this.sample_.getPartialItem().getClass()) : this.getSerializedSizeBytes(serDe, this.sample_.getSample().get(0).getClass());
      }
   }

   public int getSerializedSizeBytes(ArrayOfItemsSerDe serDe, Class clazz) {
      if (this.n_ == 0L) {
         return Family.EBPPS.getMinPreLongs() << 3;
      } else {
         int preLongs = Family.EBPPS.getMaxPreLongs();
         byte[] itemBytes = serDe.serializeToByteArray(this.sample_.getAllSamples(clazz));
         return (preLongs << 3) + 8 + itemBytes.length;
      }
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      if (this.n_ == 0L) {
         return this.toByteArray(serDe, (Class)null);
      } else {
         return this.sample_.getC() < (double)1.0F ? this.toByteArray(serDe, this.sample_.getPartialItem().getClass()) : this.toByteArray(serDe, this.sample_.getSample().get(0).getClass());
      }
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe, Class clazz) {
      boolean empty = this.n_ == 0L;
      byte[] itemBytes = null;
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = 1;
         outBytes = 8;
      } else {
         preLongs = Family.EBPPS.getMaxPreLongs();
         itemBytes = serDe.serializeToByteArray(this.sample_.getAllSamples(clazz));
         outBytes = (preLongs << 3) + 8 + itemBytes.length;
      }

      byte[] outArr = new byte[outBytes];
      WritableMemory mem = WritableMemory.writableWrap(outArr);
      PreambleUtil.insertPreLongs(mem, preLongs);
      PreambleUtil.insertSerVer(mem, 1);
      PreambleUtil.insertFamilyID(mem, Family.EBPPS.getID());
      if (empty) {
         PreambleUtil.insertFlags(mem, 4);
      } else {
         PreambleUtil.insertFlags(mem, this.sample_.hasPartialItem() ? 8 : 0);
      }

      PreambleUtil.insertK(mem, this.k_);
      if (!empty) {
         PreambleUtil.insertN(mem, this.n_);
         PreambleUtil.insertEbppsCumulativeWeight(mem, this.cumulativeWt_);
         PreambleUtil.insertEbppsMaxWeight(mem, this.wtMax_);
         PreambleUtil.insertEbppsRho(mem, this.rho_);
         mem.putDouble(40L, this.sample_.getC());
         mem.putByteArray(48L, itemBytes, 0, itemBytes.length);
      }

      return outArr;
   }

   private static void checkK(int k) {
      if (k <= 0 || k > 2147483645) {
         throw new SketchesArgumentException("k must be strictly positive and less than 2147483645");
      }
   }
}
