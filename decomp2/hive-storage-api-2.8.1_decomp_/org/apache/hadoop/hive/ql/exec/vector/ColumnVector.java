package org.apache.hadoop.hive.ql.exec.vector;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ColumnVector {
   private AtomicInteger refCount = new AtomicInteger(0);
   public final Type type;
   public boolean[] isNull;
   public boolean noNulls;
   public boolean isRepeating;
   private boolean preFlattenIsRepeating;
   private boolean preFlattenNoNulls;

   public ColumnVector(Type type, int len) {
      this.type = type;
      this.isNull = new boolean[len];
      this.noNulls = true;
      this.isRepeating = false;
      this.preFlattenNoNulls = true;
      this.preFlattenIsRepeating = false;
   }

   public void reset() {
      assert this.refCount.get() == 0;

      if (!this.noNulls) {
         Arrays.fill(this.isNull, false);
      }

      this.noNulls = true;
      this.isRepeating = false;
      this.preFlattenNoNulls = true;
      this.preFlattenIsRepeating = false;
   }

   public final void incRef() {
      this.refCount.incrementAndGet();
   }

   public final int getRef() {
      return this.refCount.get();
   }

   public final int decRef() {
      int i = this.refCount.decrementAndGet();

      assert i >= 0;

      return i;
   }

   public void setRepeating(boolean isRepeating) {
      this.isRepeating = isRepeating;
   }

   public abstract void flatten(boolean var1, int[] var2, int var3);

   protected void flattenRepeatingNulls(boolean selectedInUse, int[] sel, int size) {
      boolean nullFillValue;
      if (this.noNulls) {
         nullFillValue = false;
      } else {
         nullFillValue = this.isNull[0];
      }

      if (selectedInUse) {
         for(int j = 0; j < size; ++j) {
            int i = sel[j];
            this.isNull[i] = nullFillValue;
         }
      } else {
         Arrays.fill(this.isNull, 0, size, nullFillValue);
      }

      this.noNulls = false;
   }

   protected void flattenNoNulls(boolean selectedInUse, int[] sel, int size) {
      if (this.noNulls) {
         this.noNulls = false;
         if (selectedInUse) {
            for(int j = 0; j < size; ++j) {
               this.isNull[sel[j]] = false;
            }
         } else {
            Arrays.fill(this.isNull, 0, size, false);
         }
      }

   }

   public void unFlatten() {
      this.isRepeating = this.preFlattenIsRepeating;
      this.noNulls = this.preFlattenNoNulls;
   }

   protected void flattenPush() {
      this.preFlattenIsRepeating = this.isRepeating;
      this.preFlattenNoNulls = this.noNulls;
   }

   public abstract void setElement(int var1, int var2, ColumnVector var3);

   public abstract void copySelected(boolean var1, int[] var2, int var3, ColumnVector var4);

   public void init() {
   }

   public void ensureSize(int size, boolean preserveData) {
      if (this.isNull.length < size) {
         boolean[] oldArray = this.isNull;
         this.isNull = new boolean[size];
         if (preserveData && !this.noNulls) {
            if (this.isRepeating) {
               this.isNull[0] = oldArray[0];
            } else {
               System.arraycopy(oldArray, 0, this.isNull, 0, oldArray.length);
            }
         }
      }

   }

   public abstract void stringifyValue(StringBuilder var1, int var2);

   public void shallowCopyTo(ColumnVector otherCv) {
      otherCv.isNull = this.isNull;
      otherCv.noNulls = this.noNulls;
      otherCv.isRepeating = this.isRepeating;
      otherCv.preFlattenIsRepeating = this.preFlattenIsRepeating;
      otherCv.preFlattenNoNulls = this.preFlattenNoNulls;
      otherCv.refCount = this.refCount;
   }

   public static enum Type {
      NONE,
      LONG,
      DOUBLE,
      BYTES,
      DECIMAL,
      DECIMAL_64,
      TIMESTAMP,
      INTERVAL_DAY_TIME,
      STRUCT,
      LIST,
      MAP,
      UNION,
      VOID;
   }
}
