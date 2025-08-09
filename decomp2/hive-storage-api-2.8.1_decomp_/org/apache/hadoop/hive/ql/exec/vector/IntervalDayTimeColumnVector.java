package org.apache.hadoop.hive.ql.exec.vector;

import java.util.Arrays;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.io.Writable;

public class IntervalDayTimeColumnVector extends ColumnVector {
   private long[] totalSeconds;
   private int[] nanos;
   private final HiveIntervalDayTime scratchIntervalDayTime;
   private Writable scratchWritable;

   public IntervalDayTimeColumnVector() {
      this(1024);
   }

   public IntervalDayTimeColumnVector(int len) {
      super(ColumnVector.Type.INTERVAL_DAY_TIME, len);
      this.totalSeconds = new long[len];
      this.nanos = new int[len];
      this.scratchIntervalDayTime = new HiveIntervalDayTime();
      this.scratchWritable = null;
   }

   public int getLength() {
      return this.totalSeconds.length;
   }

   public long getTotalSeconds(int elementNum) {
      return this.totalSeconds[elementNum];
   }

   public long getNanos(int elementNum) {
      return (long)this.nanos[elementNum];
   }

   public double getDouble(int elementNum) {
      return this.asScratchIntervalDayTime(elementNum).getDouble();
   }

   public void intervalDayTimeUpdate(HiveIntervalDayTime intervalDayTime, int elementNum) {
      intervalDayTime.set(this.totalSeconds[elementNum], this.nanos[elementNum]);
   }

   public HiveIntervalDayTime asScratchIntervalDayTime(int elementNum) {
      this.scratchIntervalDayTime.set(this.totalSeconds[elementNum], this.nanos[elementNum]);
      return this.scratchIntervalDayTime;
   }

   public HiveIntervalDayTime getScratchIntervalDayTime() {
      return this.scratchIntervalDayTime;
   }

   public int compareTo(int elementNum, HiveIntervalDayTime intervalDayTime) {
      return this.asScratchIntervalDayTime(elementNum).compareTo(intervalDayTime);
   }

   public int compareTo(HiveIntervalDayTime intervalDayTime, int elementNum) {
      return intervalDayTime.compareTo(this.asScratchIntervalDayTime(elementNum));
   }

   public int compareTo(int elementNum1, IntervalDayTimeColumnVector intervalDayTimeColVector2, int elementNum2) {
      return this.asScratchIntervalDayTime(elementNum1).compareTo(intervalDayTimeColVector2.asScratchIntervalDayTime(elementNum2));
   }

   public int compareTo(IntervalDayTimeColumnVector intervalDayTimeColVector1, int elementNum1, int elementNum2) {
      return intervalDayTimeColVector1.asScratchIntervalDayTime(elementNum1).compareTo(this.asScratchIntervalDayTime(elementNum2));
   }

   public void setElement(int outputElementNum, int inputElementNum, ColumnVector inputColVector) {
      if (this.isRepeating && outputElementNum != 0) {
         throw new RuntimeException("Output column number expected to be 0 when isRepeating");
      } else {
         if (inputColVector.isRepeating) {
            inputElementNum = 0;
         }

         if (this.noNulls || !this.isNull[outputElementNum]) {
            if (!inputColVector.noNulls && inputColVector.isNull[inputElementNum]) {
               this.isNull[outputElementNum] = true;
               this.noNulls = false;
            } else {
               IntervalDayTimeColumnVector timestampColVector = (IntervalDayTimeColumnVector)inputColVector;
               this.totalSeconds[outputElementNum] = timestampColVector.totalSeconds[inputElementNum];
               this.nanos[outputElementNum] = timestampColVector.nanos[inputElementNum];
            }

         }
      }
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();
      if (this.isRepeating) {
         this.isRepeating = false;
         long repeatFastTime = this.totalSeconds[0];
         int repeatNanos = this.nanos[0];
         if (selectedInUse) {
            for(int j = 0; j < size; ++j) {
               int i = sel[j];
               this.totalSeconds[i] = repeatFastTime;
               this.nanos[i] = repeatNanos;
            }
         } else {
            Arrays.fill(this.totalSeconds, 0, size, repeatFastTime);
            Arrays.fill(this.nanos, 0, size, repeatNanos);
         }

         this.flattenRepeatingNulls(selectedInUse, sel, size);
      }

      this.flattenNoNulls(selectedInUse, sel, size);
   }

   public void set(int elementNum, HiveIntervalDayTime intervalDayTime) {
      this.totalSeconds[elementNum] = intervalDayTime.getTotalSeconds();
      this.nanos[elementNum] = intervalDayTime.getNanos();
   }

   public void setFromScratchIntervalDayTime(int elementNum) {
      this.totalSeconds[elementNum] = this.scratchIntervalDayTime.getTotalSeconds();
      this.nanos[elementNum] = this.scratchIntervalDayTime.getNanos();
   }

   public void setNullValue(int elementNum) {
      this.totalSeconds[elementNum] = 0L;
      this.nanos[elementNum] = 1;
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      IntervalDayTimeColumnVector output = (IntervalDayTimeColumnVector)outputColVector;
      boolean[] outputIsNull = output.isNull;
      output.isRepeating = false;
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            outputIsNull[0] = true;
            output.noNulls = false;
         } else {
            outputIsNull[0] = false;
            output.totalSeconds[0] = this.totalSeconds[0];
            output.nanos[0] = this.nanos[0];
         }

         output.isRepeating = true;
      } else {
         if (this.noNulls) {
            if (selectedInUse) {
               if (!outputColVector.noNulls) {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     outputIsNull[i] = false;
                     output.totalSeconds[i] = this.totalSeconds[i];
                     output.nanos[i] = this.nanos[i];
                  }
               } else {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     output.totalSeconds[i] = this.totalSeconds[i];
                     output.nanos[i] = this.nanos[i];
                  }
               }
            } else {
               if (!outputColVector.noNulls) {
                  Arrays.fill(outputIsNull, false);
                  outputColVector.noNulls = true;
               }

               for(int i = 0; i != size; ++i) {
                  output.totalSeconds[i] = this.totalSeconds[i];
                  output.nanos[i] = this.nanos[i];
               }
            }
         } else {
            output.noNulls = false;
            if (selectedInUse) {
               for(int j = 0; j < size; ++j) {
                  int i = sel[j];
                  output.isNull[i] = this.isNull[i];
                  output.totalSeconds[i] = this.totalSeconds[i];
                  output.nanos[i] = this.nanos[i];
               }
            } else {
               System.arraycopy(this.isNull, 0, output.isNull, 0, size);
               System.arraycopy(this.totalSeconds, 0, output.totalSeconds, 0, size);
               System.arraycopy(this.nanos, 0, output.nanos, 0, size);
            }
         }

      }
   }

   public void fill(HiveIntervalDayTime intervalDayTime) {
      this.isRepeating = true;
      this.isNull[0] = false;
      this.totalSeconds[0] = intervalDayTime.getTotalSeconds();
      this.nanos[0] = intervalDayTime.getNanos();
   }

   public Writable getScratchWritable() {
      return this.scratchWritable;
   }

   public void setScratchWritable(Writable scratchWritable) {
      this.scratchWritable = scratchWritable;
   }

   public void stringifyValue(StringBuilder buffer, int row) {
      if (this.isRepeating) {
         row = 0;
      }

      if (!this.noNulls && this.isNull[row]) {
         buffer.append("null");
      } else {
         this.scratchIntervalDayTime.set(this.totalSeconds[row], this.nanos[row]);
         buffer.append(this.scratchIntervalDayTime.toString());
      }

   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);
      if (size > this.totalSeconds.length) {
         long[] oldTime = this.totalSeconds;
         int[] oldNanos = this.nanos;
         this.totalSeconds = new long[size];
         this.nanos = new int[size];
         if (preserveData) {
            if (this.isRepeating) {
               this.totalSeconds[0] = oldTime[0];
               this.nanos[0] = oldNanos[0];
            } else {
               System.arraycopy(oldTime, 0, this.totalSeconds, 0, oldTime.length);
               System.arraycopy(oldNanos, 0, this.nanos, 0, oldNanos.length);
            }
         }

      }
   }

   public void shallowCopyTo(ColumnVector otherCv) {
      IntervalDayTimeColumnVector other = (IntervalDayTimeColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.totalSeconds = this.totalSeconds;
      other.nanos = this.nanos;
   }
}
