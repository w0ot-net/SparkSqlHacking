package org.apache.derby.impl.store.raw.log;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.raw.log.LogInstant;

public class LogCounter implements LogInstant {
   public static final long INVALID_LOG_INSTANT = 0L;
   public static final long DERBY_10_0_MAX_LOGFILE_NUMBER = 4194303L;
   public static final long MAX_LOGFILE_NUMBER = 2147483647L;
   private static final long FILE_NUMBER_SHIFT = 32L;
   public static final long MAX_LOGFILE_SIZE = 268435455L;
   private static final long FILE_POSITION_MASK = 2147483647L;
   private long fileNumber;
   private long filePosition;

   public LogCounter(long var1) {
      this.fileNumber = getLogFileNumber(var1);
      this.filePosition = getLogFilePosition(var1);
   }

   public LogCounter(long var1, long var3) {
      this.fileNumber = var1;
      this.filePosition = var3;
   }

   public LogCounter() {
   }

   public static final long makeLogInstantAsLong(long var0, long var2) {
      return var0 << 32 | var2;
   }

   public static final long getLogFilePosition(long var0) {
      return var0 & 2147483647L;
   }

   public static final long getLogFileNumber(long var0) {
      return var0 >>> 32;
   }

   public boolean lessThan(DatabaseInstant var1) {
      LogCounter var2 = (LogCounter)var1;
      return this.fileNumber == var2.fileNumber ? this.filePosition < var2.filePosition : this.fileNumber < var2.fileNumber;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof LogCounter)) {
         return false;
      } else {
         LogCounter var2 = (LogCounter)var1;
         return this.fileNumber == var2.fileNumber && this.filePosition == var2.filePosition;
      }
   }

   public DatabaseInstant next() {
      return new LogCounter(makeLogInstantAsLong(this.fileNumber, this.filePosition) + 1L);
   }

   public DatabaseInstant prior() {
      return new LogCounter(makeLogInstantAsLong(this.fileNumber, this.filePosition) - 1L);
   }

   public int hashCode() {
      return (int)(this.filePosition ^ this.fileNumber);
   }

   public String toString() {
      return "(" + this.fileNumber + "," + this.filePosition + ")";
   }

   public static String toDebugString(long var0) {
      return null;
   }

   public long getValueAsLong() {
      return makeLogInstantAsLong(this.fileNumber, this.filePosition);
   }

   public long getLogFilePosition() {
      return this.filePosition;
   }

   public long getLogFileNumber() {
      return this.fileNumber;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.fileNumber = CompressedNumber.readLong((DataInput)var1);
      this.filePosition = CompressedNumber.readLong((DataInput)var1);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      CompressedNumber.writeLong((DataOutput)var1, this.fileNumber);
      CompressedNumber.writeLong((DataOutput)var1, this.filePosition);
   }

   public int getTypeFormatId() {
      return 130;
   }
}
