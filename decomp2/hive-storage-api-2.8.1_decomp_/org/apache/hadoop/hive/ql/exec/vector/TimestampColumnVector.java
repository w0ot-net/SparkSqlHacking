package org.apache.hadoop.hive.ql.exec.vector;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import org.apache.hadoop.hive.common.type.CalendarUtils;
import org.apache.hadoop.io.Writable;
import org.apache.hive.common.util.SuppressFBWarnings;

public class TimestampColumnVector extends ColumnVector {
   public long[] time;
   public int[] nanos;
   private final Timestamp scratchTimestamp;
   private Writable scratchWritable;
   private boolean isUTC;
   private boolean usingProlepticCalendar;

   public TimestampColumnVector() {
      this(1024);
   }

   public TimestampColumnVector(int len) {
      super(ColumnVector.Type.TIMESTAMP, len);
      this.usingProlepticCalendar = false;
      this.time = new long[len];
      this.nanos = new int[len];
      this.scratchTimestamp = new Timestamp(0L);
      this.scratchWritable = null;
      this.isUTC = false;
   }

   public int getLength() {
      return this.time.length;
   }

   public long getTime(int elementNum) {
      return this.time[elementNum];
   }

   public int getNanos(int elementNum) {
      return this.nanos[elementNum];
   }

   public void timestampUpdate(Timestamp timestamp, int elementNum) {
      timestamp.setTime(this.time[elementNum]);
      timestamp.setNanos(this.nanos[elementNum]);
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public Timestamp asScratchTimestamp(int elementNum) {
      this.scratchTimestamp.setTime(this.time[elementNum]);
      this.scratchTimestamp.setNanos(this.nanos[elementNum]);
      return this.scratchTimestamp;
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public Timestamp getScratchTimestamp() {
      return this.scratchTimestamp;
   }

   public long getTimestampAsLong(int elementNum) {
      this.scratchTimestamp.setTime(this.time[elementNum]);
      this.scratchTimestamp.setNanos(this.nanos[elementNum]);
      return getTimestampAsLong(this.scratchTimestamp);
   }

   public static long getTimestampAsLong(Timestamp timestamp) {
      return millisToSeconds(timestamp.getTime());
   }

   private static long millisToSeconds(long millis) {
      return millis >= 0L ? millis / 1000L : (millis - 999L) / 1000L;
   }

   public double getDouble(int elementNum) {
      this.scratchTimestamp.setTime(this.time[elementNum]);
      this.scratchTimestamp.setNanos(this.nanos[elementNum]);
      return getDouble(this.scratchTimestamp);
   }

   public static double getDouble(Timestamp timestamp) {
      double seconds = (double)millisToSeconds(timestamp.getTime());
      double nanos = (double)timestamp.getNanos();
      return seconds + nanos / (double)1.0E9F;
   }

   public int compareTo(int elementNum, Timestamp timestamp) {
      return this.asScratchTimestamp(elementNum).compareTo(timestamp);
   }

   public int compareTo(Timestamp timestamp, int elementNum) {
      return timestamp.compareTo(this.asScratchTimestamp(elementNum));
   }

   public int compareTo(int elementNum1, TimestampColumnVector timestampColVector2, int elementNum2) {
      return this.asScratchTimestamp(elementNum1).compareTo(timestampColVector2.asScratchTimestamp(elementNum2));
   }

   public int compareTo(TimestampColumnVector timestampColVector1, int elementNum1, int elementNum2) {
      return timestampColVector1.asScratchTimestamp(elementNum1).compareTo(this.asScratchTimestamp(elementNum2));
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
               TimestampColumnVector timestampColVector = (TimestampColumnVector)inputColVector;
               this.time[outputElementNum] = timestampColVector.time[inputElementNum];
               this.nanos[outputElementNum] = timestampColVector.nanos[inputElementNum];
            }

         }
      }
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();
      if (this.isRepeating) {
         this.isRepeating = false;
         long repeatFastTime = this.time[0];
         int repeatNanos = this.nanos[0];
         if (selectedInUse) {
            for(int j = 0; j < size; ++j) {
               int i = sel[j];
               this.time[i] = repeatFastTime;
               this.nanos[i] = repeatNanos;
            }
         } else {
            Arrays.fill(this.time, 0, size, repeatFastTime);
            Arrays.fill(this.nanos, 0, size, repeatNanos);
         }

         this.flattenRepeatingNulls(selectedInUse, sel, size);
      }

      this.flattenNoNulls(selectedInUse, sel, size);
   }

   public void set(int elementNum, Timestamp timestamp) {
      if (timestamp == null) {
         this.isNull[elementNum] = true;
         this.noNulls = false;
      } else {
         this.time[elementNum] = timestamp.getTime();
         this.nanos[elementNum] = timestamp.getNanos();
      }
   }

   public void setFromScratchTimestamp(int elementNum) {
      this.time[elementNum] = this.scratchTimestamp.getTime();
      this.nanos[elementNum] = this.scratchTimestamp.getNanos();
   }

   public void setNullValue(int elementNum) {
      this.time[elementNum] = 0L;
      this.nanos[elementNum] = 1;
   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      TimestampColumnVector output = (TimestampColumnVector)outputColVector;
      boolean[] outputIsNull = output.isNull;
      output.isRepeating = false;
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            outputIsNull[0] = true;
            output.noNulls = false;
         } else {
            outputIsNull[0] = false;
            output.time[0] = this.time[0];
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
                     output.time[i] = this.time[i];
                     output.nanos[i] = this.nanos[i];
                  }
               } else {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     output.time[i] = this.time[i];
                     output.nanos[i] = this.nanos[i];
                  }
               }
            } else {
               if (!outputColVector.noNulls) {
                  Arrays.fill(outputIsNull, false);
                  outputColVector.noNulls = true;
               }

               System.arraycopy(this.time, 0, output.time, 0, size);
               System.arraycopy(this.nanos, 0, output.nanos, 0, size);
            }
         } else {
            output.noNulls = false;
            if (selectedInUse) {
               for(int j = 0; j < size; ++j) {
                  int i = sel[j];
                  output.isNull[i] = this.isNull[i];
                  output.time[i] = this.time[i];
                  output.nanos[i] = this.nanos[i];
               }
            } else {
               System.arraycopy(this.isNull, 0, output.isNull, 0, size);
               System.arraycopy(this.time, 0, output.time, 0, size);
               System.arraycopy(this.nanos, 0, output.nanos, 0, size);
            }
         }

      }
   }

   public void fill(Timestamp timestamp) {
      this.isRepeating = true;
      this.isNull[0] = false;
      this.time[0] = timestamp.getTime();
      this.nanos[0] = timestamp.getNanos();
   }

   public Writable getScratchWritable() {
      return this.scratchWritable;
   }

   public void setScratchWritable(Writable scratchWritable) {
      this.scratchWritable = scratchWritable;
   }

   public boolean isUTC() {
      return this.isUTC;
   }

   public void setIsUTC(boolean value) {
      this.isUTC = value;
   }

   public void stringifyValue(StringBuilder buffer, int row) {
      if (this.isRepeating) {
         row = 0;
      }

      if (!this.noNulls && this.isNull[row]) {
         buffer.append("null");
      } else {
         this.scratchTimestamp.setTime(this.time[row]);
         this.scratchTimestamp.setNanos(this.nanos[row]);
         if (this.isUTC) {
            LocalDateTime ts = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.time[row]), ZoneOffset.UTC).withNano(this.nanos[row]);
            buffer.append(ts.toLocalDate().toString() + ' ' + ts.toLocalTime().toString());
         } else {
            buffer.append(this.scratchTimestamp.toString());
         }
      }

   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);
      if (size > this.time.length) {
         long[] oldTime = this.time;
         int[] oldNanos = this.nanos;
         this.time = new long[size];
         this.nanos = new int[size];
         if (preserveData) {
            if (this.isRepeating) {
               this.time[0] = oldTime[0];
               this.nanos[0] = oldNanos[0];
            } else {
               System.arraycopy(oldTime, 0, this.time, 0, oldTime.length);
               System.arraycopy(oldNanos, 0, this.nanos, 0, oldNanos.length);
            }
         }

      }
   }

   public void shallowCopyTo(ColumnVector otherCv) {
      TimestampColumnVector other = (TimestampColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.time = this.time;
      other.nanos = this.nanos;
   }

   public void changeCalendar(boolean useProleptic, boolean updateData) {
      if (useProleptic != this.usingProlepticCalendar) {
         this.usingProlepticCalendar = useProleptic;
         if (updateData) {
            try {
               this.updateDataAccordingProlepticSetting();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }

      }
   }

   private void updateDataAccordingProlepticSetting() throws Exception {
      for(int i = 0; i < this.nanos.length; ++i) {
         if (this.time[i] < CalendarUtils.SWITCHOVER_MILLIS) {
            this.asScratchTimestamp(i);
            long offset = 0L;
            long millis = this.usingProlepticCalendar ? CalendarUtils.convertTimeToProleptic(this.scratchTimestamp.getTime()) : CalendarUtils.convertTimeToHybrid(this.scratchTimestamp.getTime());
            Timestamp newTimeStamp = Timestamp.from(Instant.ofEpochMilli(millis));
            this.scratchTimestamp.setTime(newTimeStamp.getTime() + offset);
            this.scratchTimestamp.setNanos(this.nanos[i]);
            this.setFromScratchTimestamp(i);
         }
      }

   }

   public TimestampColumnVector setUsingProlepticCalendar(boolean usingProlepticCalendar) {
      this.usingProlepticCalendar = usingProlepticCalendar;
      return this;
   }

   public boolean usingProlepticCalendar() {
      return this.usingProlepticCalendar;
   }
}
