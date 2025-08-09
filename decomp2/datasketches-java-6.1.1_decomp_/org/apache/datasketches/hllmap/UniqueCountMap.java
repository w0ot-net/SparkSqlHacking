package org.apache.datasketches.hllmap;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;

public final class UniqueCountMap {
   private static final int NUM_LEVELS = 10;
   private static final int NUM_TRAVERSE_MAPS = 3;
   private static final int HLL_K = 1024;
   private static final int INITIAL_NUM_ENTRIES = 1000003;
   private static final int MIN_INITIAL_NUM_ENTRIES = 157;
   private final int keySizeBytes_;
   private final Map[] maps_;

   public UniqueCountMap(int keySizeBytes) {
      this(1000003, keySizeBytes);
   }

   public UniqueCountMap(int initialNumEntries, int keySizeBytes) {
      checkConstructorKeySize(keySizeBytes);
      int initEntries = Math.max(initialNumEntries, 157);
      this.keySizeBytes_ = keySizeBytes;
      this.maps_ = new Map[10];
      this.maps_[0] = SingleCouponMap.getInstance(initEntries, keySizeBytes);
   }

   public double update(byte[] key, byte[] identifier) {
      if (key == null) {
         return Double.NaN;
      } else {
         this.checkMethodKeySize(key);
         if (identifier == null) {
            return this.getEstimate(key);
         } else {
            short coupon = (short)Map.coupon16(identifier);
            int baseMapIndex = this.maps_[0].findOrInsertKey(key);
            double baseMapEstimate = this.maps_[0].update(baseMapIndex, coupon);
            if (baseMapEstimate > (double)0.0F) {
               return baseMapEstimate;
            } else {
               int level = -((int)baseMapEstimate);
               if (level == 0) {
                  return this.promote(key, coupon, this.maps_[0], baseMapIndex, level, baseMapIndex, (double)0.0F);
               } else {
                  Map map = this.maps_[level];
                  int index = map.findOrInsertKey(key);
                  double estimate = map.update(index, coupon);
                  return estimate > (double)0.0F ? estimate : this.promote(key, coupon, map, index, level, baseMapIndex, -estimate);
               }
            }
         }
      }
   }

   public double getEstimate(byte[] key) {
      if (key == null) {
         return Double.NaN;
      } else {
         this.checkMethodKeySize(key);
         double est = this.maps_[0].getEstimate(key);
         if (est >= (double)0.0F) {
            return est;
         } else {
            int level = -((int)est);
            Map map = this.maps_[level];
            return map.getEstimate(key);
         }
      }
   }

   public double getUpperBound(byte[] key) {
      if (key == null) {
         return Double.NaN;
      } else {
         this.checkMethodKeySize(key);
         double est = this.maps_[0].getEstimate(key);
         if (est >= (double)0.0F) {
            return est;
         } else {
            int level = -((int)est);
            Map map = this.maps_[level];
            return map.getUpperBound(key);
         }
      }
   }

   public double getLowerBound(byte[] key) {
      if (key == null) {
         return Double.NaN;
      } else {
         this.checkMethodKeySize(key);
         double est = this.maps_[0].getEstimate(key);
         if (est >= (double)0.0F) {
            return est;
         } else {
            int level = -((int)est);
            Map map = this.maps_[level];
            return map.getLowerBound(key);
         }
      }
   }

   public int getActiveEntries() {
      return this.maps_[0].getCurrentCountEntries();
   }

   public long getMemoryUsageBytes() {
      long total = 0L;

      for(int i = 0; i < this.maps_.length; ++i) {
         if (this.maps_[i] != null) {
            total += this.maps_[i].getMemoryUsageBytes();
         }
      }

      return total;
   }

   public long getKeyMemoryUsageBytes() {
      long total = 0L;

      for(int i = 0; i < this.maps_.length; ++i) {
         if (this.maps_[i] != null) {
            total += (long)this.maps_[i].getActiveEntries() * (long)this.keySizeBytes_;
         }
      }

      return total;
   }

   public double getAverageSketchMemoryPerKey() {
      return (double)(this.getMemoryUsageBytes() - this.getKeyMemoryUsageBytes()) / (double)this.getActiveEntries();
   }

   int getActiveMaps() {
      int levels = 0;
      int iMapsLen = this.maps_.length;

      for(int i = 0; i < iMapsLen; ++i) {
         if (this.maps_[i] != null) {
            ++levels;
         }
      }

      return levels;
   }

   Map getBaseMap() {
      return this.maps_[0];
   }

   Map getHllMap() {
      return this.maps_[this.maps_.length - 1];
   }

   public String toString() {
      long totKeys = (long)this.getActiveEntries();
      long totMem = this.getMemoryUsageBytes();
      long keyMem = this.getKeyMemoryUsageBytes();
      double avgValMemPerKey = this.getAverageSketchMemoryPerKey();
      String ksb = Map.fmtLong((long)this.keySizeBytes_);
      String alvls = Map.fmtLong((long)this.getActiveMaps());
      String tKeys = Map.fmtLong(totKeys);
      String tMem = Map.fmtLong(totMem);
      String kMem = Map.fmtLong(keyMem);
      String avgValMem = Map.fmtDouble(avgValMemPerKey);
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append("## ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("   Key Size Bytes             : ").append(ksb).append(Util.LS);
      sb.append("   Active Map Levels          : ").append(alvls).append(Util.LS);
      sb.append("   Total keys                 : ").append(tKeys).append(Util.LS);
      sb.append("   Total Memory Bytes         : ").append(tMem).append(Util.LS);
      sb.append("   Total Key Memory Bytes     : ").append(kMem).append(Util.LS);
      sb.append("   Avg Sketch Memory Bytes/Key: ").append(avgValMem).append(Util.LS);
      sb.append(Util.LS);

      for(int i = 0; i < this.maps_.length; ++i) {
         Map cMap = this.maps_[i];
         if (cMap != null) {
            sb.append(cMap.toString());
            sb.append(Util.LS);
         }
      }

      sb.append("## ").append("END UNIQUE COUNT MAP SUMMARY");
      sb.append(Util.LS);
      return sb.toString();
   }

   private void setLevelInBaseMap(int index, int level) {
      ((SingleCouponMap)this.maps_[0]).setLevel(index, level);
   }

   private double promote(byte[] key, short coupon, Map fromMap, int fromIndex, int fromLevel, int baseMapIndex, double estimate) {
      Map newMap = this.getMapForLevel(fromLevel + 1);
      int newMapIndex = newMap.findOrInsertKey(key);
      CouponsIterator it = fromMap.getCouponsIterator(fromIndex);

      while(it.next()) {
         double est = newMap.update(newMapIndex, it.getValue());

         assert est > (double)0.0F;
      }

      fromMap.deleteKey(fromIndex);
      newMap.updateEstimate(newMapIndex, estimate);
      double newEstimate = newMap.update(newMapIndex, coupon);
      this.setLevelInBaseMap(baseMapIndex, fromLevel + 1);

      assert newEstimate > (double)0.0F;

      return newEstimate;
   }

   private Map getMapForLevel(int level) {
      if (this.maps_[level] == null) {
         int newLevelCapacity = 1 << level;
         if (level <= 3) {
            this.maps_[level] = CouponTraverseMap.getInstance(this.keySizeBytes_, newLevelCapacity);
         } else if (level < this.maps_.length - 1) {
            this.maps_[level] = CouponHashMap.getInstance(this.keySizeBytes_, newLevelCapacity);
         } else {
            this.maps_[level] = HllMap.getInstance(this.keySizeBytes_, 1024);
         }
      }

      return this.maps_[level];
   }

   private static final void checkConstructorKeySize(int keySizeBytes) {
      if (keySizeBytes < 4) {
         throw new SketchesArgumentException("KeySizeBytes must be >= 4: " + keySizeBytes);
      }
   }

   private final void checkMethodKeySize(byte[] key) {
      if (key.length != this.keySizeBytes_) {
         throw new SketchesArgumentException("Key size must be " + this.keySizeBytes_ + " bytes.");
      }
   }
}
