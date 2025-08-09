package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.SequencePreallocator;
import org.apache.derby.shared.common.error.StandardException;

public class SequenceGenerator {
   private static final int PREALLOCATION_THRESHHOLD = 1;
   public static final int RET_I_AM_CONFUSED = 0;
   public static final int RET_OK = 1;
   public static final int RET_MARK_EXHAUSTED = 2;
   public static final int RET_ALLOCATE_NEW_VALUES = 3;
   public static final int CVAA_STATUS = 0;
   public static final int CVAA_CURRENT_VALUE = 1;
   public static final int CVAA_LAST_ALLOCATED_VALUE = 2;
   public static final int CVAA_NUMBER_OF_VALUES_ALLOCATED = 3;
   public static final int CVAA_LENGTH = 4;
   private final boolean _CAN_CYCLE;
   private final boolean _STEP_INCREASES;
   private final long _INCREMENT;
   private final long _MAX_VALUE;
   private final long _MIN_VALUE;
   private final long _RESTART_VALUE;
   private final String _SCHEMA_NAME;
   private final String _SEQUENCE_NAME;
   private final SequencePreallocator _PREALLOCATOR;
   private boolean _isExhausted;
   private long _currentValue;
   private long _remainingPreallocatedValues;

   public SequenceGenerator(Long var1, boolean var2, long var3, long var5, long var7, long var9, String var11, String var12, SequencePreallocator var13) {
      if (var1 == null) {
         this._isExhausted = true;
         this._currentValue = 0L;
      } else {
         this._isExhausted = false;
         this._currentValue = var1;
      }

      this._CAN_CYCLE = var2;
      this._INCREMENT = var3;
      this._MAX_VALUE = var5;
      this._MIN_VALUE = var7;
      this._RESTART_VALUE = var9;
      this._STEP_INCREASES = this._INCREMENT > 0L;
      this._SCHEMA_NAME = var11;
      this._SEQUENCE_NAME = var12;
      this._PREALLOCATOR = var13;
      this._remainingPreallocatedValues = 1L;
   }

   public synchronized SequenceGenerator clone(boolean var1) {
      Long var2;
      if (var1) {
         var2 = this._RESTART_VALUE;
      } else if (this._isExhausted) {
         var2 = null;
      } else {
         var2 = this._currentValue;
      }

      return new SequenceGenerator(var2, this._CAN_CYCLE, this._INCREMENT, this._MAX_VALUE, this._MIN_VALUE, this._RESTART_VALUE, this._SCHEMA_NAME, this._SEQUENCE_NAME, this._PREALLOCATOR);
   }

   public synchronized SequenceGenerator clone(Long var1) {
      return new SequenceGenerator(var1, this._CAN_CYCLE, this._INCREMENT, this._MAX_VALUE, this._MIN_VALUE, this._RESTART_VALUE, this._SCHEMA_NAME, this._SEQUENCE_NAME, this._PREALLOCATOR);
   }

   public synchronized String getSchemaName() {
      return this._SCHEMA_NAME;
   }

   public synchronized String getName() {
      return this._SEQUENCE_NAME;
   }

   public synchronized void allocateNewRange(long var1, long var3) {
      if (this._currentValue == var1) {
         this._remainingPreallocatedValues = var3;
      }

   }

   public synchronized Long peekAtCurrentValue() {
      Long var1 = null;
      if (!this._isExhausted) {
         var1 = this._currentValue;
      }

      return var1;
   }

   public synchronized long[] getCurrentValueAndAdvance() throws StandardException {
      if (this._isExhausted) {
         throw StandardException.newException("2200H.S", new Object[]{this._SCHEMA_NAME, this._SEQUENCE_NAME});
      } else {
         long[] var1 = new long[]{0L, this._currentValue, 0L, 0L};
         this.advanceValue(var1);
         return var1;
      }
   }

   private void advanceValue(long[] var1) throws StandardException {
      long var2 = this._currentValue + this._INCREMENT;
      if (this.overflowed(this._currentValue, var2)) {
         if (!this._CAN_CYCLE) {
            this.markExhausted(var1);
            return;
         }

         if (this._INCREMENT > 0L) {
            var2 = this._MIN_VALUE;
         } else {
            var2 = this._MAX_VALUE;
         }
      }

      --this._remainingPreallocatedValues;
      if (this._remainingPreallocatedValues < 1L) {
         this.computeNewAllocation(this._currentValue, var1);
      } else {
         this._currentValue = var2;
         var1[0] = 1L;
      }
   }

   private void markExhausted(long[] var1) {
      this._isExhausted = true;
      var1[0] = 2L;
   }

   private boolean overflowed(long var1, long var3) {
      boolean var5 = this._STEP_INCREASES == var3 < var1;
      if (!var5) {
         if (this._STEP_INCREASES) {
            var5 = var3 > this._MAX_VALUE;
         } else {
            var5 = var3 < this._MIN_VALUE;
         }
      }

      return var5;
   }

   private void computeNewAllocation(long var1, long[] var3) throws StandardException {
      int var4 = this.computePreAllocationCount();
      long var5 = this.computeRemainingValues(var1);
      long var7;
      long var9;
      if (var5 >= (long)var4) {
         var7 = var1 + (long)var4 * this._INCREMENT;
         var9 = (long)var4;
      } else if (this._CAN_CYCLE) {
         long var11 = (long)var4 - var5;
         --var11;
         var7 = this._RESTART_VALUE + var11 * this._INCREMENT;
         var9 = (long)var4;
      } else {
         if (var5 <= 0L) {
            this.markExhausted(var3);
            return;
         }

         var9 = var5;
         var7 = var1 + var5 * this._INCREMENT;
      }

      var3[3] = var9 + 1L;
      var3[2] = var7;
      var3[0] = 3L;
   }

   private long computeRemainingValues(long var1) {
      long var3 = this._STEP_INCREASES ? this._MAX_VALUE - var1 : -(this._MIN_VALUE - var1);
      if (var3 < 0L) {
         var3 = Long.MAX_VALUE;
      }

      long var5 = this._STEP_INCREASES ? this._INCREMENT : -this._INCREMENT;
      return var3 / var5;
   }

   private int computePreAllocationCount() {
      int var1 = this._PREALLOCATOR.nextRangeSize(this._SCHEMA_NAME, this._SEQUENCE_NAME);
      byte var2 = 1;
      if (var1 < var2) {
         return var2;
      } else {
         double var3 = (double)this._MIN_VALUE;
         double var5 = (double)this._MAX_VALUE;
         double var7 = var5 - var3;
         double var9 = (double)this._INCREMENT;
         if (var9 < (double)0.0F) {
            var9 = -var9;
         }

         double var11 = var9 * (double)var1;
         if (var11 > (double)Long.MAX_VALUE) {
            return var2;
         } else {
            return var11 > var7 ? var2 : var1;
         }
      }
   }
}
