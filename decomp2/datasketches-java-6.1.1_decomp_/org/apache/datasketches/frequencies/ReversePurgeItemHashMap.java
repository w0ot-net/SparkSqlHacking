package org.apache.datasketches.frequencies;

import java.lang.reflect.Array;
import org.apache.datasketches.thetacommon.QuickSelect;

class ReversePurgeItemHashMap {
   private static final double LOAD_FACTOR = (double)0.75F;
   private static final int DRIFT_LIMIT = 1024;
   private int lgLength;
   protected int loadThreshold;
   protected Object[] keys;
   protected long[] values;
   protected short[] states;
   protected int numActive = 0;

   ReversePurgeItemHashMap(int mapSize) {
      this.lgLength = org.apache.datasketches.common.Util.exactLog2OfInt(mapSize, "mapSize");
      this.loadThreshold = (int)((double)mapSize * (double)0.75F);
      this.keys = new Object[mapSize];
      this.values = new long[mapSize];
      this.states = new short[mapSize];
   }

   boolean isActive(int probe) {
      return this.states[probe] > 0;
   }

   long get(Object key) {
      if (key == null) {
         return 0L;
      } else {
         int probe = this.hashProbe(key);
         if (this.states[probe] > 0) {
            assert this.keys[probe].equals(key);

            return this.values[probe];
         } else {
            return 0L;
         }
      }
   }

   void adjustOrPutValue(Object key, long adjustAmount) {
      int arrayMask = this.keys.length - 1;
      int probe = (int)Util.hash((long)key.hashCode()) & arrayMask;
      int drift = 1;

      while(this.states[probe] != 0 && !this.keys[probe].equals(key)) {
         probe = probe + 1 & arrayMask;
         ++drift;

         assert drift < 1024 : "drift: " + drift + " >= DRIFT_LIMIT";
      }

      if (this.states[probe] == 0) {
         assert this.numActive <= this.loadThreshold : "numActive: " + this.numActive + " > loadThreshold: " + this.loadThreshold;

         this.keys[probe] = key;
         this.values[probe] = adjustAmount;
         this.states[probe] = (short)drift;
         ++this.numActive;
      } else {
         assert this.keys[probe].equals(key);

         long[] var10000 = this.values;
         var10000[probe] += adjustAmount;
      }

   }

   void keepOnlyPositiveCounts() {
      int firstProbe;
      for(firstProbe = this.states.length - 1; this.states[firstProbe] > 0; --firstProbe) {
      }

      int probe = firstProbe;

      while(probe-- > 0) {
         if (this.states[probe] > 0 && this.values[probe] <= 0L) {
            this.hashDelete(probe);
            --this.numActive;
         }
      }

      probe = this.states.length;

      while(probe-- > firstProbe) {
         if (this.states[probe] > 0 && this.values[probe] <= 0L) {
            this.hashDelete(probe);
            --this.numActive;
         }
      }

   }

   void adjustAllValuesBy(long adjustAmount) {
      long[] var4;
      for(int i = this.values.length; i-- > 0; var4[i] += adjustAmount) {
         var4 = this.values;
      }

   }

   Object[] getActiveKeys() {
      if (this.numActive == 0) {
         return null;
      } else {
         T[] returnedKeys = null;
         int j = 0;

         for(int i = 0; i < this.keys.length; ++i) {
            if (this.isActive(i)) {
               if (returnedKeys == null) {
                  returnedKeys = (T[])((Object[])((Object[])Array.newInstance(this.keys[i].getClass(), this.numActive)));
               }

               returnedKeys[j] = this.keys[i];
               ++j;
            }
         }

         assert j == this.numActive : "j: " + j + " != numActive: " + this.numActive;

         return returnedKeys;
      }
   }

   long[] getActiveValues() {
      if (this.numActive == 0) {
         return null;
      } else {
         long[] returnedValues = new long[this.numActive];
         int j = 0;

         for(int i = 0; i < this.values.length; ++i) {
            if (this.isActive(i)) {
               returnedValues[j] = this.values[i];
               ++j;
            }
         }

         assert j == this.numActive;

         return returnedValues;
      }
   }

   void resize(int newSize) {
      Object[] oldKeys = this.keys;
      long[] oldValues = this.values;
      short[] oldStates = this.states;
      this.keys = new Object[newSize];
      this.values = new long[newSize];
      this.states = new short[newSize];
      this.loadThreshold = (int)((double)newSize * (double)0.75F);
      this.lgLength = Integer.numberOfTrailingZeros(newSize);
      this.numActive = 0;

      for(int i = 0; i < oldKeys.length; ++i) {
         if (oldStates[i] > 0) {
            this.adjustOrPutValue(oldKeys[i], oldValues[i]);
         }
      }

   }

   int getLength() {
      return this.keys.length;
   }

   int getLgLength() {
      return this.lgLength;
   }

   int getCapacity() {
      return this.loadThreshold;
   }

   int getNumActive() {
      return this.numActive;
   }

   public String toString() {
      String fmt = "  %12d:%11d%12d %s";
      String hfmt = "  %12s:%11s%12s %s";
      StringBuilder sb = new StringBuilder();
      sb.append("ReversePurgeItemHashMap").append(org.apache.datasketches.common.Util.LS);
      sb.append(String.format("  %12s:%11s%12s %s", "Index", "States", "Values", "Keys")).append(org.apache.datasketches.common.Util.LS);

      for(int i = 0; i < this.keys.length; ++i) {
         if (this.states[i] > 0) {
            sb.append(String.format("  %12d:%11d%12d %s", i, this.states[i], this.values[i], this.keys[i].toString()));
            sb.append(org.apache.datasketches.common.Util.LS);
         }
      }

      return sb.toString();
   }

   static double getLoadFactor() {
      return (double)0.75F;
   }

   long purge(int sampleSize) {
      int limit = Math.min(sampleSize, this.getNumActive());
      int numSamples = 0;
      int i = 0;

      long[] samples;
      for(samples = new long[limit]; numSamples < limit; ++i) {
         if (this.isActive(i)) {
            samples[numSamples] = this.values[i];
            ++numSamples;
         }
      }

      long val = QuickSelect.select((long[])samples, 0, numSamples - 1, limit / 2);
      this.adjustAllValuesBy(-1L * val);
      this.keepOnlyPositiveCounts();
      return val;
   }

   private void hashDelete(int deleteProbe) {
      this.states[deleteProbe] = 0;
      int drift = 1;
      int arrayMask = this.keys.length - 1;
      int probe = deleteProbe + drift & arrayMask;

      while(this.states[probe] != 0) {
         if (this.states[probe] > drift) {
            this.keys[deleteProbe] = this.keys[probe];
            this.values[deleteProbe] = this.values[probe];
            this.states[deleteProbe] = (short)(this.states[probe] - drift);
            this.states[probe] = 0;
            drift = 0;
            deleteProbe = probe;
         }

         probe = probe + 1 & arrayMask;
         ++drift;

         assert drift < 1024 : "drift: " + drift + " >= DRIFT_LIMIT";
      }

   }

   private int hashProbe(Object key) {
      int arrayMask = this.keys.length - 1;

      int probe;
      for(probe = (int)Util.hash((long)key.hashCode()) & arrayMask; this.states[probe] > 0 && !this.keys[probe].equals(key); probe = probe + 1 & arrayMask) {
      }

      return probe;
   }

   Iterator iterator() {
      return new Iterator(this.keys, this.values, this.states, this.numActive);
   }

   static class Iterator {
      private static final double GOLDEN_RATIO_RECIPROCAL = (Math.sqrt((double)5.0F) - (double)1.0F) / (double)2.0F;
      private final Object[] keys_;
      private final long[] values_;
      private final short[] states_;
      private final int numActive_;
      private final int stride_;
      private final int mask_;
      private int i_;
      private int count_;

      Iterator(Object[] keys, long[] values, short[] states, int numActive) {
         this.keys_ = keys;
         this.values_ = values;
         this.states_ = states;
         this.numActive_ = numActive;
         this.stride_ = (int)((double)keys.length * GOLDEN_RATIO_RECIPROCAL) | 1;
         this.mask_ = keys.length - 1;
         this.i_ = -this.stride_;
         this.count_ = 0;
      }

      boolean next() {
         for(this.i_ = this.i_ + this.stride_ & this.mask_; this.count_ < this.numActive_; this.i_ = this.i_ + this.stride_ & this.mask_) {
            if (this.states_[this.i_] > 0) {
               ++this.count_;
               return true;
            }
         }

         return false;
      }

      Object getKey() {
         return this.keys_[this.i_];
      }

      long getValue() {
         return this.values_[this.i_];
      }
   }
}
